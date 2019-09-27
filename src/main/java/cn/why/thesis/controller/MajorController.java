package cn.why.thesis.controller;
import java.beans.IntrospectionException;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
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

import com.google.protobuf.TextFormat.ParseException;

import cn.why.thesis.entity.AjaxResult;
import cn.why.thesis.entity.Major;
import cn.why.thesis.entity.Student;
import cn.why.thesis.entity.Thesis;
import cn.why.thesis.service.MajorService;

@Controller
@RequestMapping("teacher/major")
public class MajorController {
	@Autowired
	private MajorService majorservice;
	
	/**
	 * 显示学院信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/majorlist")
	public String majorlist(HttpServletRequest request) {
		return "thesis/teacher/major/list";
	}
	
	/**
	 * 编辑页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/editmajor")
	public String editmajor(@RequestParam(value="id") int id,Model model) {
		Major m = majorservice.findById(id);
		model.addAttribute("major", m);
		return "thesis/teacher/major/edit";
	}
	
	/**
	 * 增加页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/addmajor")
	public String addmajor() {
		return "thesis/teacher/major/add";
	}
	
	@RequestMapping("/zenjiamajor")
	@ResponseBody
	public AjaxResult<Major> zenjiamajor(Major major,HttpSession session) {
		AjaxResult<Major> ar = new AjaxResult<Major>();
		int count = 0;
		count = majorservice.insertMajor(major);
		if (count > 0) {
			ar.setCode(0);
			ar.setMsg("增加成功!");
		}else {
			ar.setCode(0);
			ar.setMsg("增加失败!");
		}
		return ar;
	}
	
	/**
	 * 查询学院表数据
	 * @param page
	 * @param limit
	 * @return
	 */
	@RequestMapping("/querymajor")
	@ResponseBody
	public AjaxResult<Major> querymajor(@RequestParam(value="page") int page,
			@RequestParam(value="limit") int limit) {
		AjaxResult<Major> ar = new AjaxResult<Major>();
		ar.setCount(majorservice.querymajorCount());
	    ar.setData(majorservice.querymajor(page,limit));
		return ar;
	}
	
	
	@RequestMapping("/delmajor")
	@ResponseBody
	public AjaxResult<Major> delmajor(HttpServletRequest request,Major major,HttpSession session,Model model) {
		AjaxResult<Major> ar = new AjaxResult<Major>();
		int count = majorservice.delmajor(major);
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
	
	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult<Major> save(Major major ,HttpSession session) {
		AjaxResult<Major> ar = new AjaxResult<Major>();
		int count = 0;
		if(major.getId()!=0) {//修改操作
			count = majorservice.updateMajor(major);
		}else {//增加操作
			count = majorservice.insertMajor(major);
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
	 * 导出学院信息
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
	        workbook = majorservice.exportExcelInfo(salaryDate);
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
	
	@RequestMapping("/findzy")
	@ResponseBody
	public AjaxResult<Major> findzy(@RequestParam(value="xy")String xy ) {
		AjaxResult<Major> ar = new AjaxResult<Major>();
	    ar.setData(majorservice.findZyByXy(xy));
		return ar;
	}
	
	
	
	
	
}
