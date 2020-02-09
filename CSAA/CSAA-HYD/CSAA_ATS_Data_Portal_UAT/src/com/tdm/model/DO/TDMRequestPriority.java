package com.tdm.model.DO;

import java.io.Serializable;
import java.util.Date;

public class TDMRequestPriority implements Serializable {

	private String priorityID;
	private String priorityName;
	private char isActive;
	private String createdBy;
	private String modifiedBy;
	private Date createdDate;
	private Date modifiedDate;
	public String getPriorityID() {
		return priorityID;
	}
	public void setPriorityID(String priorityID) {
		this.priorityID = priorityID;
	}
	public String getPriorityName() {
		return priorityName;
	}
	public void setPriorityName(String priorityName) {
		this.priorityName = priorityName;
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
