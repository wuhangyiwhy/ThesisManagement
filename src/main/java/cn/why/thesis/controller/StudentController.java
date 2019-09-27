package cn.why.thesis.controller;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import cn.why.thesis.entity.AjaxResult;
import cn.why.thesis.entity.Student;
import cn.why.thesis.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {
	@Autowired
	private StudentService studentservice;
	
	/**
	 * 跳转到学生登陆界面
	 * @param request
	 * @return
	 */
	@RequestMapping("/login")
	public String login(HttpServletRequest request) {
		return "thesis/student/student_login";
	}
	
	/**
	 * 通过判断进行登录操作
	 * @param yzm
	 * @param student
	 * @param session
	 * @param request
	 * @param xh
	 * @param xm
	 * @param sfzh
	 * @return
	 */
	@RequestMapping("/dologin")
	@ResponseBody	
	public AjaxResult<Student> dologin(@RequestParam(value="imagecode", required=false) String yzm,Student student,HttpSession session,HttpServletRequest request,
			@RequestParam("xh") String xh,@RequestParam("xm") String xm,@RequestParam("sfzh") String sfzh) {
		AjaxResult<Student>  ar = new AjaxResult<Student> ();
		if(!yzm.equalsIgnoreCase((String) session.getAttribute("RANDOMVALIDATECODEKEY"))) {
			ar.setCode(1);
			ar.setMsg("验证码不正确");
		}else {
			List<Student> stu = studentservice.selStudentByAdminAndxhAndsfzh(xh,xm,sfzh);
			if(stu.size()==0) {
				ar.setCode(2);
				ar.setMsg("输入信息不正确！");
			} else {
				request.getSession().setAttribute("student", stu.get(0));
				ar.setCode(0);
				ar.setMsg("登录成功");
			}
		}
		return ar;
	}
	
	/**
	 * 登录成功跳转到自己主页的界面
	 * @param model
	 * @param session
	 * @param student
	 * @return
	 */
	@RequestMapping("/myinfo")
	public String index(Model model,HttpSession session,Student student) {
		Student students =  (Student) session.getAttribute("student");
		Student stu =  studentservice.queryStudent(students);
		model.addAttribute("student", stu);
		return "thesis/student/myinfo";
	}
	
	/**
	 * 通过判断学号是否为空修改自己的信息
	 * @param student
	 * @return
	 */
	@RequestMapping("/updateMyInfo")
	@ResponseBody	
	public AjaxResult<Student>  updateMyInfo(Student student) {
		AjaxResult<Student>  ar = new AjaxResult<Student> ();
		int count = 0;
		if(student.getXh()!="") {
			count = studentservice.updateMyInfo(student);
		}
		if(count > 0) {
			ar.setCode(0);
			ar.setMsg("信息修改成功");
		}else {
			ar.setCode(1);
			ar.setMsg("信息修改失败");
		}
		return ar;
	}
	
	/**
	 * 学生提交自己的头像到数据库
	 * @param file
	 * @param request
	 * @return
	 */
	@RequestMapping("/douploadphoto")
	@ResponseBody
	public AjaxResult<Student>  douploadthesis(MultipartFile file,HttpServletRequest request) {
		AjaxResult<Student>  ar = new AjaxResult<Student> ();
		//格式化时间 为年月日时分秒毫秒
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
		 String res = sdf.format(new Date());
		 //上传文件夹位置
		 String rootPath = request.getSession().getServletContext().getRealPath("resource/uploads/"); 
		 //原始文件名称
		 String originalFileName = file.getOriginalFilename();
		 //新文件名称  命名规则为 时间加上传文件名的后缀名  如2019 04 11 12:10:20:30.docx
		 String newFileName = res + originalFileName.substring(originalFileName.lastIndexOf("."));
		 //创建年月文件夹   separator为分隔符
		 Calendar date = Calendar.getInstance();
		 File dateDirs = new File(date.get(Calendar.YEAR) + File.separator + (date.get(Calendar.MONTH)+1));
		 //新文件
		 File newFile = new File(rootPath + File.separator + dateDirs + File.separator + newFileName);
		 // 判断目标文件所在目录是否存在
		 if( !newFile.getParentFile().exists()) {
	            // 如果目标文件所在的目录不存在，则创建父目录
	            newFile.getParentFile().mkdirs();
	        }
		 try {
			file.transferTo(newFile);
		} catch (IllegalStateException e) {
			ar.setCode(1);
			ar.setMsg("出现异常:IllegalStateException");
			e.printStackTrace();
		} catch (IOException e) {
			ar.setCode(1);
			ar.setMsg("出现异常:IOException");
			e.printStackTrace();
		}
		 // 完整的url
	        String fileUrl = date.get(Calendar.YEAR) + "/" + (date.get(Calendar.MONTH)+1) + "/" + newFileName;
	        ar.setCode(0);
			ar.setMsg(fileUrl);
	        return  ar;
	}
	
	/**
	 * 通过学号判断是否为空来增加或修改自己头像
	 * @param student
	 * @param session
	 * @return
	 */
	@RequestMapping("/addphoto")
	@ResponseBody
	public AjaxResult<Student> save(Student student,HttpSession session) {
		AjaxResult<Student> ar = new AjaxResult<Student>();
		int count = 0;
		if(student.getXh()!="") {//修改操作
			count = studentservice.updateByXh(student);
		}else {//增加操作
			Student stu = (Student) session.getAttribute("student");
			student.setXh(stu.getXh());	//得到前台的参数的学号
			count = studentservice.addphoto(student);
		}
		if (count > 0) {
			ar.setCode(0);
			ar.setMsg("图片上传成功!");
		}else {
			ar.setCode(0);
			ar.setMsg("图片上传失败!");
		}
		return ar;
	}
	
	
	/**
	 * jsp上显示学生头像
	 * @param filename
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/download",method=RequestMethod.GET)
    public void download(@RequestParam(value="filename")String filename,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
		//filename : 如2019/4/名字.后缀名
        String path = request.getSession().getServletContext().getRealPath("resource/uploads/"+filename);  
        //转码，免得文件名中文乱码  
        filename = URLEncoder.encode(filename,"UTF-8");  
        //设置文件下载头  
        response.addHeader("Content-Disposition", "attachment;filename=" + filename);    
        //设置文件ContentType类型，这样设置，会自动判断下载文件类型    
        response.setContentType("multipart/form-data");   
        FileUtils.copyFile(new File(path), response.getOutputStream());
    }
	
	
	/**学生退出登录跳转到学生登录界面
	 * 
	 * @param session
	 * @param m
	 * @return
	 */
	@RequestMapping("/loginOut")
	public ModelAndView  loginOut(HttpSession session,ModelAndView m) {
		session.invalidate();
		m.setViewName("redirect:login");
		return m;
	}
	
	
	
}
