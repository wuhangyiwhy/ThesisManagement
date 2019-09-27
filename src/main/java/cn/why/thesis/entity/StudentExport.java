package cn.why.thesis.entity;

import org.springframework.beans.factory.annotation.Autowired;

public class StudentExport {
	@Autowired
	private String xh;
	private String xm;
	private String sfzh;
	private String nj;
	private String bj;
	private String xy;
	private String zy;
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
	public String getXy() {
		return xy;
	}
	public void setXy(String xy) {
		this.xy = xy;
	}
	public String getZy() {
		return zy;
	}
	public void setZy(String zy) {
		this.zy = zy;
	}

	
}
