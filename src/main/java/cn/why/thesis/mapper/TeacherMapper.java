package cn.why.thesis.mapper;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.why.thesis.entity.Student;
import cn.why.thesis.entity.Teacher;
import cn.why.thesis.entity.Thesis;

public interface TeacherMapper {

	public Teacher selTeacherByNoAndName(@Param("teacherNo")String teacherNo, @Param("teacherName")String teacherName);

	public Teacher queryTeacher(Teacher teacher);

	public int selectCountTeacher();

	public List<Teacher> queryPageTable(@Param("page")int page, @Param("limit")int limit);

	public List<Thesis> queryThesis(HashMap<String,String> hm);

	public int queryCountThesis();//查

	public Thesis queryThsisByXh(Thesis thesis);//通过学号查询学生论文

	
	public int delStudent(Student student);//通过学号来删除学生信息

	public int updateStudentInfo(Student student);

	public void insertInfoBatch(List<Student> studentList);

	public int updateStudentInfo(HashMap<String, String> hm);

	public int delThesisInfo(Thesis thesis);

	
	
}
