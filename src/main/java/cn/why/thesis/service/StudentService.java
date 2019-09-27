package cn.why.thesis.service;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.protobuf.TextFormat.ParseException;

import cn.why.thesis.entity.ExcelBean;
import cn.why.thesis.entity.Major;
import cn.why.thesis.entity.Student;
import cn.why.thesis.entity.StudentExport;
import cn.why.thesis.entity.Thesis;
import cn.why.thesis.mapper.StudentMapper;
import cn.why.thesis.utils.ExcelUtil;

@Service
public class StudentService {
	@Autowired
	private StudentMapper studentmapper;
	@Transactional(readOnly=true)
	public List<Student> selStudentByAdminAndxhAndsfzh(String xh,String xm,String sfzh) {
		
		return studentmapper.selStudentByAdminAndxhAndsfzh(xh,xm,sfzh);
	}
	
	public int updateMyInfo(Student student) {
		
		return studentmapper.updateMyInfo(student);
	}

	public Student queryStudent(Student student) {
		
		return studentmapper.queryStudent(student);
	}

	public int addphoto(Student  student) {
		return studentmapper.addphoto(student);
	}

	public int updateByXh(Student student) {
		return studentmapper.updateByXh(student);
	}
	
	public int queryStudentCount() {
		return studentmapper.queryStudentCount();
	}

	public List<Student> queryStudentPage(int page, int limit) {
		List<Student> lsStudent =studentmapper.queryStudentPage(page*limit-limit,limit);
		return lsStudent;
	}

	public Student getByXh(String xh) {
		return studentmapper.getByXh(xh);
	}

	public int updateStudent(Student student) {
		return studentmapper.updateStudent(student);
	}

	public int insertStudent(Student student) {
		return studentmapper.insertStudent(student);
	}
	
	public List<Student> queryStudentByXh(String xh) {
		return studentmapper.queryStudentByXh(xh);
	}
	
	public XSSFWorkbook exportExcelInfo(String salaryDate) throws InvocationTargetException, ClassNotFoundException, IntrospectionException, ParseException, IllegalAccessException {
			//根据条件查询数据，把数据装载到一个list中
		    List<StudentExport> list = studentmapper.selectStudentInfo();
		    List<ExcelBean> excel=new ArrayList<>();
		    Map<Integer,List<ExcelBean>> map=new LinkedHashMap<>();
		    XSSFWorkbook xssfWorkbook=null;
		    //设置标题栏
		    excel.add(new ExcelBean("学号","xh",0));
		    excel.add(new ExcelBean("姓名","xm",0));
		    excel.add(new ExcelBean("身份证号","sfzh",0));
		    excel.add(new ExcelBean("学院","xy",0)); 
		    excel.add(new ExcelBean("专业","zy",0)); 
		    excel.add(new ExcelBean("年级","nj",0));
		    excel.add(new ExcelBean("班级","bj",0));
		    map.put(0, excel);
		    String sheetName = "学生信息表";
		    //调用ExcelUtil的方法
		    xssfWorkbook = ExcelUtil.createExcelFile(StudentExport.class, list, map, sheetName);
		    return xssfWorkbook;

		}
	
}
