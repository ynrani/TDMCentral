package com.tdm.model.DO;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@Entity
@Table(name = "CFG_REQUEST_PRIORITY")
@NamedQuery(name = "TDMRequestPriorityDO.findAll", query = "SELECT t FROM TDMRequestPriorityDO t ")
public class TDMRequestPriorityDO {
	@Id
	@Column(name = "PRIORITY_ID")
	private String priorityID;
	@Column(name = "PRIORITY_NAME")
	private String priorityName;
	@Column(name = "IS_ACTIVE")
	private char isActive;
	@Column(name = "CREATED_BY")
	private String createdBy;
	@Column(name = "MODIFIED_BY")
	private String modifiedBy;
	@Column(name = "CREATED_DATE")
	private Date createdDate;
	@Column(name = "MODIFIED_DATE")
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
