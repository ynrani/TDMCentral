package com.tdm.model.DO;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@Entity
@Table(name = "CFG_ASSIGNED_GROUP")
@NamedQuery(name = "AssignedGroupDO.findAll", query = "SELECT t FROM AssignedGroupDO t order by sortOrder")
public class AssignedGroupDO implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "ASSIGN_GROUP_ID")
	private String assignGroupId;

	@Column(name = "ASSIGNED_GROUP_NAME")
	private String assignGroupName;

	@Column(name = "IS_ACTIVE")
	private char isActive;
	
	@Column(name = "CREATED_BY")
	private String createdBy;
	
	@Column(name = "CREATED_DATE")
	private Date createdDate;
	
	@Column(name = "MODIFIED_BY")
	private String modifiedBy;
	
	@Column(name = "MODIFIED_DATE")
	private Date modifiedDate;
	
	@Column(name = "SORT_ORDER")
	private Integer sortOrder;

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	
}
