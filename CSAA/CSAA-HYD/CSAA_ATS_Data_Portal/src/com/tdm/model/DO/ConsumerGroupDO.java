package com.tdm.model.DO;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@Entity
@Table(name = "CFG_CONSUMER_GROUP")
@NamedQuery(name = "ConsumerGroupDO.findAll", query = "SELECT t FROM ConsumerGroupDO t")
public class ConsumerGroupDO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "CONSUMER_GROUP_ID")
	private String consumerGroupId;
	@Column(name = "CONSUMER_GROUP_NAME")
	private String consumerGroupName;
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

	public String getConsumerGroupId() {
		return consumerGroupId;
	}

	public void setConsumerGroupId(String consumerGroupId) {
		this.consumerGroupId = consumerGroupId;
	}

	public String getConsumerGroupName() {
		return consumerGroupName;
	}

	public void setConsumerGroupName(String consumerGroupName) {
		this.consumerGroupName = consumerGroupName;
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
