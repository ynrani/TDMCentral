package com.tdm.model.DO;

import java.io.Serializable;
import java.util.Date;

public class ConsumerGroup implements Serializable {
	private String consumerGroupId;
	private String consumerGroupName;
	private char isActive;
	private String createdBy;
	private String modifiedBy;
	private Date createdDate;
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
	
}
