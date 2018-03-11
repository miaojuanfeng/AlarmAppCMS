package com.alarm.model;

import java.util.Date;

public class Expert {
	private Integer id;
	private Integer discussId;
	private String content;
	private Date createDate;
	private Date modifyDate;
	private Integer deleted;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getDiscussId() {
		return discussId;
	}
	public void setDiscussId(Integer discussId) {
		this.discussId = discussId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public Integer getDeleted() {
		return deleted;
	}
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	
}
