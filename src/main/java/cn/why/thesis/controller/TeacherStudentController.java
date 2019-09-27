package cn.why.thesis.controller;

import java.beans.IntrospectionException;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.google.protobuf.TextFormat.ParseException;

import cn.why.thesis.entity.AjaxResult;
import cn.why.thesis.entity.Major;
import cn.why.thesis.entity.Student;
import cn.why.thesis.entity.Teacher;
import cn.why.thesis.entity.Thesis;
import cn.why.thesis.service.MajorService;
import cn.why.thesis.service.StudentService;
import cn.why.thesis.service.TeacherService;

@Controller
@RequestMapping("/teacher/stu")
public class TeacherStudentController {
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private MajorService majorService;
	/**
	 * 老师登录成功跳转到学生管理界面
	 * @param model
	 * @param session
	 * @param teacher
	 * @return
	 */
	@RequestMapping("/list")
	public String list(Model model,HttpSession session,Teacher teacher) {
		return "thesis/teacher/student/list";
	}
	
	/**
	 * 老师登录进来之后显示学生信息列表
	 * @param model
	 * @param page
	 * @param limit
	 * @param student
	 * @return
	 */
	@RequestMapping("/listpage")
	@ResponseBody
	public AjaxResult<Student> studentList(Model model ,@RequestParam(value="page") int page,
			@RequestParam(value="limit") int limit,Student student) {
		AjaxResult<Student> ar = new AjaxResult<Student>();
		ar.setCount(studentService.queryStudentCount());
	    ar.setData(studentService.queryStudentPage(page,limit));
		return ar;
	} 
	
	@RequestMapping("/edit")
	public String edit(Model model,@RequestParam(value="xh") String xh) {
		Student student = studentService.getByXh(xh);
		List<Major> lsmajor=majorService.findAllXy();
		model.addAttribute("lsmajor", lsmajor);
		model.addAttribute("student", student);
		return "thesis/teacher/student/edit";
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult<Student> save(Student student ,HttpSession session) {
		AjaxResult<Student> ar = new AjaxResult<Student>();
		int count = 0;
		if(student.getXh()!="") {//修改操作
			count = studentService.updateStudent(student);
		}else {//增加操作
			count = studentService.insertStudent(student);
		}
		if (count > 0) {
			ar.setCode(0);
			ar.setMsg("保存信息成功!");
		}else {
			ar.setCode(0);
			ar.setMsg("保存信息失败!");
		}
		return ar;
	}
	
	/**
	 * 老师登录进来之后删除学生信息
	 * @param request
	 * @param student
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("/delStudentInfo")
	@ResponseBody
	public AjaxResult<Student> delStudentInfo(HttpServletRequest request,Student student,HttpSession session,Model model) {
		AjaxResult<Student> ar = new AjaxResult<Student>();
		int count = teacherService.delStudent(student);
		if(count > 0) 
		{
			ar.setCode(0);
			ar.setMsg("删除成功");
		}else{
			ar.setCode(1);
			ar.setMsg("删除失败");
		}
		return ar;
	}
	
	/**
	 * 老师登录进来之后修改学生数据
	 * @param xh
	 * @param field
	 * @param fieldvalue
	 * @return
	 */
	@RequestMapping("/updateStudentInfo")
	@ResponseBody
	public AjaxResult<Student> updateStudentInfo(@RequestParam(value="xh") String xh,@RequestParam(value="field") String field,
			@RequestParam(value="fieldvalue") String fieldvalue) {
		AjaxResult<Student> ar = new AjaxResult<Student>();
		int count = teacherService.updateStudentInfo(xh,field,fieldvalue);
		if(count > 0) 
		{
			ar.setCode(0);
			ar.setMsg("修改成功");
		}else{
			ar.setCode(1);
			ar.setMsg("修改失败");
		}
		return ar;
	}
	
	
	/**
	 * 老师登录进来之后查询学生数据
	 * @param request
	 * @param student
	 * @param session
	 * @param model
	 * @param xh
	 * @return
	 */
	@RequestMapping("/queryStudentInfo")
	@ResponseBody
	public AjaxResult<Student> queryStudentInfo(HttpServletRequest request,Student student,HttpSession session,Model model
			,@RequestParam(value="xh") String xh) {
		AjaxResult<Student> ar = new AjaxResult<Student>();
	    ar.setData(studentService.queryStudentByXh(xh));
		return ar;
	}
	
	/**
	 * 老师登录进来之后批量导入学生数据
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/import")
	@ResponseBody
	public AjaxResult<Student> impotr(HttpServletRequest request, Model model) throws Exception {
		AjaxResult<Student> ar = new AjaxResult<Student>();
	     //获取上传的文件
	     MultipartHttpServletRequest multipart = (MultipartHttpServletRequest) request;
	     MultipartFile file = multipart.getFile("file");
	     InputStream in = file.getInputStream();
	     //数据导入
	     teacherService.importExcelInfo(in,file);
	     in.close();
	     return ar;
	}
	
	/**
	 * 老师登录进来之后导出学生数据
	 * @param request
	 * @param response
	 * @throws ClassNotFoundException
	 * @throws IntrospectionException
	 * @throws IllegalAccessException
	 * @throws ParseException
	 * @throws InvocationTargetException
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/export")
	@ResponseBody 
	public void export(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, IntrospectionException, IllegalAccessException, ParseException, InvocationTargetException, UnsupportedEncodingException {
	    String salaryDate = request.getParameter("salaryDate");
	    if(salaryDate!=""){
	        response.reset(); //清除buffer缓存
	        Map<String,Object> map=new HashMap<String,Object>();
	        // 指定下载的文件名，浏览器都会使用本地编码，即GBK，浏览器收到这个文件名后，用ISO-8859-1来解码，然后用GBK来显示
	        // 所以我们用GBK解码，ISO-8859-1来编码，在浏览器那边会反过来执行。
	        response.setHeader("Content-Disposition", "attachment;filename=" );
	        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
	        response.setHeader("Pragma", "no-cache");
	        response.setHeader("Cache-Control", "no-cache");
	        response.setDateHeader("Expires", 0);
	        XSSFWorkbook workbook=null;
	        //导出Excel对象
	        workbook = studentService.exportExcelInfo(  salaryDate);
	        OutputStream output;
	        try {
	            output = response.getOutputStream();
	            BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
	            bufferedOutPut.flush();
	            workbook.write(bufferedOutPut);
	            bufferedOutPut.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
}
