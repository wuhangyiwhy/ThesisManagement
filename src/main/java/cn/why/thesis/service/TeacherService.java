package cn.why.thesis.service;

import java.beans.IntrospectionException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.protobuf.TextFormat.ParseException;

import cn.why.thesis.entity.Major;
import cn.why.thesis.entity.ExcelBean;
import cn.why.thesis.entity.Student;
import cn.why.thesis.entity.Teacher;
import cn.why.thesis.entity.Thesis;
import cn.why.thesis.mapper.TeacherMapper;
import cn.why.thesis.utils.ExcelUtil;

@Service
public class TeacherService {
	@Autowired
	private TeacherMapper teacherMapper;
	
	public Teacher selTeacherByNoAndName(String teacherNo,String teacherName) {
		return teacherMapper.selTeacherByNoAndName(teacherNo,teacherName);
	}
	
	public Teacher queryTeacher(Teacher teacher) {
		return teacherMapper.queryTeacher(teacher);
	}

	public int selectCountTeacher() {
		return teacherMapper.selectCountTeacher();
	}

	public List<Teacher> queryPageTable(int page, int limit) {
		/*HashMap<String,Integer> hm = new HashMap<String,Integer>();
		hm.put("start", page);
		hm.put("pageCount",limit);*/
		List<Teacher> lsTeacher =teacherMapper.queryPageTable(page*limit-limit,limit);
		return lsTeacher;
	}

	public List<Thesis> queryThesis(int page, int limit,String key) {
		String keyword = "1=1";
		HashMap<String,String> hm = new HashMap<String,String>();
		if(key != null) {
			keyword=" t.xh like '%"+key+"%' or c.xy like '%"+key+"%'";
		}
		hm.put("keyword", keyword);
		hm.put("page", String.valueOf(page*limit-limit));
		hm.put("limit",String.valueOf(limit));
		List<Thesis> lsthesis =teacherMapper.queryThesis(hm);
		return lsthesis;
	}    
	
	public int queryCountThesis() {
		return teacherMapper.queryCountThesis();
	}

	public Thesis queryThsisByXh(Thesis thesis) {
		return teacherMapper.queryThsisByXh(thesis);
	}

	
	public int delStudent(Student student) {
		return teacherMapper.delStudent(student); 
	}

	public int updateStudentInfo(String xh,String field,String fieldvalue) {
		HashMap<String,String> hm = new HashMap<String,String>(); 
		hm.put("xh", xh);
		hm.put("field", field);
		hm.put("fieldvalue", fieldvalue);
		return teacherMapper.updateStudentInfo(hm);
	}

	public void importExcelInfo(InputStream in, MultipartFile file) throws Exception{
	    List<List<Object>> listob = ExcelUtil.getBankListByExcel(in,file.getOriginalFilename());
	    List<Student> studentList = new ArrayList<Student>();
	    //遍历listob数据，把数据放到List中
	    for (int i = 0; i < listob.size(); i++) {
	        List<Object> ob = listob.get(i);
	        Student stu = new Student();
	        //通过遍历实现把每一列封装成一个model中，再把所有的model用List集合装载
	        stu.setXh(String.valueOf(ob.get(0)));
	        stu.setXm(String.valueOf(ob.get(1)));
	        stu.setSfzh(String.valueOf(ob.get(2)));
	        stu.setNj(String.valueOf(ob.get(3)));
	        stu.setMajor_id(Integer.valueOf((String) ob.get(4)));
	        stu.setBj(String.valueOf(ob.get(5)));
	        studentList.add(stu);
	    }
	    //批量插入
	    teacherMapper.insertInfoBatch(studentList);

	}


	public int delThesisInfo(Thesis thesis) {
		return teacherMapper.delThesisInfo(thesis);
	}

	


	
}
