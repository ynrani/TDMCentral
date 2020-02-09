package com.tdm.model.DO;

import java.io.Serializable;
import java.util.Date;

public class AssignedGroup  implements Serializable{

	private static final long serialVersionUID = 1L;
	private String assignGroupId;
	private String assignGroupName;
	private char isActive;
	private String createdBy;
	private String modifiedBy;
	private Date createdDate;
	private Date modifiedDate;
	
	public String getAssignGroupId() {
		return assignGroupId;
	}
	public void setAssignGroupId(String assignGroupId) {
		this.assignGroupId = assignGroupId;
	}
	public String getAssignGroupName() {
		return assignGroupName;
	}
	public void setAssignGroupName(String assignGroupName) {
		this.assignGroupName = assignGroupName;
	}
	public char getIsActive() {
		return isActive;
	}
	public void setIsActive(char isActive) {
		this.isActive = isActive;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
}
