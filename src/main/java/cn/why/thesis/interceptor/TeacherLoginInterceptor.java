package cn.why.thesis.interceptor;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class TeacherLoginInterceptor implements HandlerInterceptor {
	private String uncheckedUrls="/student/login,/student/dologin,/getVerify,/teacher/login,/teacher/dologin,/teacher/queryStudentInfo";
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
				
		String servletPath = request.getServletPath();    
		//2.检测1中获取的servletPath是否为不需要检测的URl中的一个.若是,放行
		List<String> urls = Arrays.asList(uncheckedUrls.split(","));
		if (urls.contains(servletPath)) 
		{ 
			return true;
		}
		//3.从session中获取SessionKey对应值,若值不存在,则重定向到redirectUrl 
		Object stu =  request.getSession().getAttribute("teacher"); 
		if (stu == null) { 
			response.sendRedirect(request.getContextPath()+"/teacher/login");            
			return false; 
		} 
		//4.若存在,则放行 
		return true;
//		String path = request.getRequestURI();
//		request.setCharacterEncoding("utf-8");
//		response.setCharacterEncoding("utf-8");
//		HttpSession session = request.getSession();
//		Worker worker =(Worker) session.getAttribute("loginfilter");
//		
//		if( path.indexOf("/login")>-1 || path.indexOf("/css")>-1 || path.indexOf("/js")>-1 	||path.indexOf("/message/Enterlogin")>-1)  {
//			return true;
//		}
//		if(worker!=null) {	
//			return true;
//		}else {
//			response.sendRedirect("/springmybatis/message/login");
//		}
//		return true;	

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
