package cn.why.thesis.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.why.thesis.entity.Student;
import cn.why.thesis.entity.StudentExport;

public interface StudentMapper {
	
	public List<Student> selStudentByAdminAndxhAndsfzh(@Param("xh")String xh,@Param("xm")String xm,@Param("sfzh")String sfzh);

	public int updateMyInfo(Student student);

	public Student queryStudent(Student student);
	//public Student selStudentByAdminAndxhAndsfzh(Student student);

	public int addphoto(Student student);

	public int updateByXh(Student student);
	
	public List<Student> queryStudentPage(@Param("page")int page, @Param("limit")int limit);//学生分页查询
	public int queryStudentCount();//查询学生总数

	public Student getByXh(String xh);

	public int updateStudent(Student student);

	public int insertStudent(Student student);
	
	public List<Student> queryStudentByXh(String xh);//通过学号查询学生数据
	
	public List<StudentExport> selectStudentInfo();
}
