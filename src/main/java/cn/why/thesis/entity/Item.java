package cn.why.thesis.entity;

public class Item {
	private int id;
	private int pid;
	private String name;
	private String url;
	private String target;
	public int getId() {
		return id;
	}
	public int getPid() {
		return pid;
	}
	public String getName() {
		return name;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public String getTarget() {
		return target;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setTarget(String target) {
		this.target = target;
	}
}
