package com.tdm.model.DO;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@Entity
@Table(name = "CFG_SUPPORT_USER")
@NamedQuery(name = "TDMSupportUserDO.findAll", query = "SELECT t FROM TDMSupportUserDO t order by sortOrder")
public class TDMSupportUserDO implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "SUPPORT_USER_ID")
	private String supportUserId;

	@Column(name = "ASSIGNED_GROUP_ID")
	private String assignedGroupId;

	@Column(name = "SUPPORT_USER_NAME")
	private String supportUserName;

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

	@Column(name = "SORT_ORDER")
	private Integer sortOrder;

	public Integer getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}
	
	public String getSupportUserId() {
		return supportUserId;
	}
	public void setAssignedGroupId(String assignedGroupId) {
		this.assignedGroupId = assignedGroupId;
	}

	public String getAssignedGroupId() {
		return assignedGroupId;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
