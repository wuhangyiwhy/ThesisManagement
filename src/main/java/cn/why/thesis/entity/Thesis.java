package cn.why.thesis.entity;

import org.springframework.beans.factory.annotation.Autowired;

public class Thesis {
	@Autowired
	 private int id;
	  private String xh;
	  private String tm;
	  private String zy;
	  private String gjz;
	  private String zw;
	  private String lwcclj;
	  private String fjcclj;
	  private Student student;
	  private Major collegesAndMajors;
	public int getId() {
		return id;
	}
	public String getXh() {
		return xh;
	}
	public String getTm() {
		return tm;
	}
	public String getZy() {
		return zy;
	}
	public String getGjz() {
		return gjz;
	}
	public String getZw() {
		return zw;
	}
	public String getLwcclj() {
		return lwcclj;
	}
	public String getFjcclj() {
		return fjcclj;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public void setTm(String tm) {
		this.tm = tm;
	}
	public void setZy(String zy) {
		this.zy = zy;
	}
	public void setGjz(String gjz) {
		this.gjz = gjz;
	}
	public void setZw(String zw) {
		this.zw = zw;
	}
	public void setLwcclj(String lwcclj) {
		this.lwcclj = lwcclj;
	}
	public void setFjcclj(String fjcclj) {
		this.fjcclj = fjcclj;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Major getCollegesAndMajors() {
		return collegesAndMajors;
	}
	public void setCollegesAndMajors(Major collegesAndMajors) {
		this.collegesAndMajors = collegesAndMajors;
	}
}
