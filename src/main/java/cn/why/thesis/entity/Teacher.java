package cn.why.thesis.entity;

import org.springframework.beans.factory.annotation.Autowired;

public class Teacher {
	@Autowired
	private String teacherNo;
	private String teacherName;
	public String getTeacherNo() {
		return teacherNo;
	}
	public void setTeacherNo(String teacherNo) {
		this.teacherNo = teacherNo;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

}
