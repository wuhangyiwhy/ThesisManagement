package cn.why.thesis.controller;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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

import com.alibaba.fastjson.JSON;

import cn.why.thesis.entity.AjaxResult;
import cn.why.thesis.entity.Student;
import cn.why.thesis.entity.Thesis;
import cn.why.thesis.service.StudentService;
import cn.why.thesis.service.ThesisService;

@Controller
@RequestMapping("/thesis")
public class ThesisController {
	@Autowired
	private ThesisService thesisservice;
	@Autowired
	private StudentService studentservice;
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addthesis")
	public String addthesis(HttpSession session,Model model) {
		Student stu = (Student) session.getAttribute("student");
		Thesis thesis = thesisservice.queryByXh(stu.getXh());
		Student student =  studentservice.queryStudent(stu);
		model.addAttribute("student", student);
		model.addAttribute("thesis", thesis);
		return "thesis/student/addthesis";
	}
	
	/**
	 *提交论文表单
	 * @param thesis
	 * @param session
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult save(Thesis thesis,HttpSession session) {
		AjaxResult ar = new AjaxResult();
		int count = 0;
		if(thesis.getXh()!="") {//修改操作
			count = thesisservice.updateByXh(thesis);
		}else {//增加操作
			Student stu = (Student) session.getAttribute("student");
			thesis.setXh(stu.getXh());	//得到前台的参数的学号
			count = thesisservice.save(thesis);
		}
		if (count > 0) {
			ar.setCode(0);
			ar.setMsg("保存论文成功!");
		}else {
			ar.setCode(0);
			ar.setMsg("保存论文失败!");
		}
		return ar;
	}
	
	/**
	 * 上传论文和附件方法
	 * @param file
	 * @param request
	 * @return
	 */
	@RequestMapping("/douploadthesis")
	@ResponseBody
	public AjaxResult douploadthesis(MultipartFile file,HttpServletRequest request) {
		AjaxResult ar = new AjaxResult();
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
	 * 下载论文和附件方法
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
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型    
        response.setContentType("multipart/form-data");   
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());  
        FileUtils.copyFile(new File(path), response.getOutputStream());
    }
	
}
