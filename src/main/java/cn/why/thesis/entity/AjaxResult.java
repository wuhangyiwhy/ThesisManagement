package cn.why.thesis.entity;

import java.util.List;

public class AjaxResult<T> {
	private int code;
	private String msg;
	private int count;
	private List<T> data;
	private Object student;
	public int getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	public Object getStudent() {
		return student;
	}
	public void setStudent(Object student) {
		this.student = student;
	}
}
