package com.tdm.model.DO;

import java.io.Serializable;
import java.util.Date;

public class TDMSupportUser implements Serializable {
	private String supportUserId;
	private String supportUserName;
	private char isActive;
	private String assignedGroupId;
	private String createdBy;
	private String modifiedBy;
	private Date createdDate;
	private Date modifiedDate;
	public String getSupportUserId() {
		return supportUserId;
	}
	public void setSupportUserId(String supportUserId) {
		this.supportUserId = supportUserId;
	}
	public String getSupportUserName() {
		return supportUserName;
	}
	public void setSupportUserName(String supportUserName) {
		this.supportUserName = supportUserName;
	}
	public char getIsActive() {
		return isActive;
	}
	public void setIsActive(char isActive) {
		this.isActive = isActive;
	}
	public String getAssignedGroupId() {
		return assignedGroupId;
	}
	public void setAssignedGroupId(String assignedGroupId) {
		this.assignedGroupId = assignedGroupId;
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
