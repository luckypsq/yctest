package com.yc.favorite.bean;

public class Favorite implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer fId;
	private String fLabel;
	private String fUrl;
	private String fTags;
	private String fDesc;
	public Integer getfId() {
		return fId;
	}
	public void setfId(Integer fId) {
		this.fId = fId;
	}
	public String getfLabel() {
		return fLabel;
	}
	public void setfLabel(String fLabel) {
		this.fLabel = fLabel;
	}
	public String getfUrl() {
		return fUrl;
	}
	public void setfUrl(String fUrl) {
		this.fUrl = fUrl;
	}
	public String getfTags() {
		return fTags;
	}
	public void setfTags(String fTags) {
		this.fTags = fTags;
	}
	public String getfDesc() {
		return fDesc;
	}
	public void setfDesc(String fDesc) {
		this.fDesc = fDesc;
	}
	

}
