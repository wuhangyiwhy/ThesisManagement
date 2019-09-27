package cn.why.thesis.entity;

import org.springframework.beans.factory.annotation.Autowired;

public class Student {
	@Autowired
	private String xh;
	private String xm;
	private String sfzh;
	private String nj;
	private String bj;
	private String image;
	private int major_id;
	private Major major;
	public String getXh() {
		return xh;
	}
	public String getXm() {
		return xm;
	}
	public String getSfzh() {
		return sfzh;
	}

	public String getNj() {
		return nj;
	}
	public String getBj() {
		return bj;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}

	public void setNj(String nj) {
		this.nj = nj;
	}
	public void setBj(String bj) {
		this.bj = bj;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getMajor_id() {
		return major_id;
	}
	public Major getMajor() {
		return major;
	}
	public void setMajor_id(int major_id) {
		this.major_id = major_id;
	}
	public void setMajor(Major major) {
		this.major = major;
	}
}
