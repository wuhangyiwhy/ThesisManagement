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
@RequestMapping("/teacher")
public class TeacherController {
	@Autowired
	private TeacherService teacherService;
	
	/**
	 * 跳转到老师端的登录
	 * @param request
	 * @return
	 */
	@RequestMapping("/login")
	public String login(HttpServletRequest request) {
		return "thesis/teacher/teacher_login";
	}
	
	/**
	 * 通过判断进行登录操作
	 * @param yzm
	 * @param teacher
	 * @param session
	 * @param request
	 * @param xh
	 * @param xm
	 * @param sfzh
	 * @return
	 */
	@RequestMapping("/dologin")
	@ResponseBody	
	public AjaxResult<Teacher> dologin(@RequestParam(value="imagecode", required=false) String yzm,Teacher teacher,HttpSession session,HttpServletRequest request,
			@RequestParam("teacherNo")String teacherNo,@RequestParam("teacherName")String teacherName)
		{
		AjaxResult<Teacher> ar = new AjaxResult<Teacher>();
		if(!yzm.equalsIgnoreCase((String) session.getAttribute("RANDOMVALIDATECODEKEY"))) {
			ar.setCode(1);
			ar.setMsg("验证码不正确");
		}else {
			Teacher stu = teacherService.selTeacherByNoAndName(teacherNo,teacherName);
			if(stu ==null) {
				ar.setCode(2);
				ar.setMsg("输入信息不正确！");
			} else {
				request.getSession().setAttribute("teacher", stu);
				ar.setCode(0);
				ar.setMsg("登录成功");
			}
		}
		return ar;
	}
	
	
	
	/**
	 * 老师端退出系统
	 * @param session
	 * @param m
	 * @return
	 */
	@RequestMapping("/loginOut")
	public ModelAndView  removesession(HttpSession session,ModelAndView m) {
		session.invalidate();
		m.setViewName("redirect:login");
		return m;
		}
	
	
	
}
