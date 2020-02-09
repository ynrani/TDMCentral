package com.tdm.model.DO;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@Entity
@Table(name = "CFG_REQUEST_STATUS")
@NamedQuery(name = "TDMRequestStatusDO.findAll", query = "SELECT t FROM TDMRequestStatusDO t order by sortOrder")
public class TDMRequestStatusDO implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	@Id
    @Column(name = "REQUEST_STATUS_ID")
	private  String requestStatusId;
	@Column(name = "STATUS_NAME")
	private String statusName;
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

	public String getRequestStatusId() {
		return requestStatusId;
	}

	public void setRequestStatusId(String requestStatusId) {
		this.requestStatusId = requestStatusId;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
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
