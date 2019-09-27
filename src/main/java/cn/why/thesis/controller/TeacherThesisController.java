package cn.why.thesis.controller;

import java.beans.IntrospectionException;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.google.protobuf.TextFormat.ParseException;

import cn.why.thesis.entity.AjaxResult;
import cn.why.thesis.entity.Student;
import cn.why.thesis.entity.Teacher;
import cn.why.thesis.entity.Thesis;
import cn.why.thesis.service.TeacherService;

@Controller
@RequestMapping("/teacher/thesis")
public class TeacherThesisController {
	@Autowired
	private TeacherService teacherService;
	
	/**
	 * 老师登录进来之后显示论文列表
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
	public String thesismanage(HttpSession session,Model model,Thesis thesis) {
		return "thesis/teacher/thesis/list";
	}
	
	/**
	 * 老师登录进来之后显示学生论文列表
	 * @param model
	 * @param page
	 * @param limit
	 * @return
	 */
	@RequestMapping("/listpage")
	@ResponseBody 
	public AjaxResult<Thesis> thesismanage(Model model ,@RequestParam(value="page") int page,
			@RequestParam(value="limit") int limit,@RequestParam(value="key[keyword]",required=false) String key) {
		AjaxResult<Thesis> ar = new AjaxResult<Thesis>();
		if(key ==null) {
			ar.setMsg("没有数据");
		}
		ar.setCount(teacherService.queryCountThesis());
	    ar.setData(teacherService.queryThesis(page,limit,key));
		return ar;
	}
	
	
	/**
	 * 老师登录进来之后删除论文信息
	 * @param thesis
	 * @return
	 */
	@RequestMapping("/delThesisInfo")
	@ResponseBody
	public AjaxResult<Student> delThesisInfo(Thesis thesis) {
		AjaxResult<Student> ar = new AjaxResult<Student>();
		int count = teacherService.delThesisInfo(thesis);
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
	
	
	
}
