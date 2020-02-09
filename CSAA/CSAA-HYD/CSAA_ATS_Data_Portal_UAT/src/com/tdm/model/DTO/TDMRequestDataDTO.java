package com.tdm.model.DTO;

import java.util.Date;
import java.util.List;

import com.tdm.model.DO.ServiceRequestDetailsDO;

/****
 * To handshake DTO between UI and server side
 * @author mamuppar
 *
 */
public class TDMRequestDataDTO {
	private String requestId;
	private String userName;
	private String userId;
	private String requestedBy;
	private String applicationOwner;
	private String approver;
	private String status;
	private String createdOn;
	private String assignedGroup;
	private String assignGroupId;
	private String assignedToId;
	private String subject;
	public String getAssignGroupId() {
		return assignGroupId;
	}
	public void setAssignGroupId(String assignGroupId) {
		this.assignGroupId = assignGroupId;
	}
	private String consumerGroup;
	private String dataSource;
	private String expectedDate;
	private String priority;
	private String environment;
	private String autoProductType;
	private String propertyProductType;
	private List<ServiceRequestDetailsDO> autoPolicyParamList;
	private List<ServiceRequestDetailsDO> propertyPolicyParamList;
	
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRequestedBy() {
		return requestedBy;
	}
	public void setRequestedBy(String requestedBy) {
		this.requestedBy = requestedBy;
	}
	public String getApplicationOwner() {
		return applicationOwner;
	}
	public void setApplicationOwner(String applicationOwner) {
		this.applicationOwner = applicationOwner;
	}
	public String getApprover() {
		return approver;
	}
	public void setApprover(String approver) {
		this.approver = approver;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	public String getAssignedGroup() {
		return assignedGroup;
	}
	public void setAssignedGroup(String assignedGroup) {
		this.assignedGroup = assignedGroup;
	}
	public String getAssignedToId() {
		return assignedToId;
	}
	public void setAssignedToId(String assignedToId) {
		this.assignedToId = assignedToId;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getConsumerGroup() {
		return consumerGroup;
	}
	public void setConsumerGroup(String consumerGroup) {
		this.consumerGroup = consumerGroup;
	}
	public String getDataSource() {
		return dataSource;
	}
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}
	public String getExpectedDate() {
		return expectedDate;
	}
	public void setExpectedDate(String expectedDate) {
		this.expectedDate = expectedDate;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getEnvironment() {
		return environment;
	}
	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	public String getAutoProductType() {
		return autoProductType;
	}
	public void setAutoProductType(String autoProductType) {
		this.autoProductType = autoProductType;
	}
	public String getPropertyProductType() {
		return propertyProductType;
	}
	public void setPropertyProductType(String propertyProductType) {
		this.propertyProductType = propertyProductType;
	}
	public List<ServiceRequestDetailsDO> getAutoPolicyParamList() {
		return autoPolicyParamList;
	}
	public void setAutoPolicyParamList(List<ServiceRequestDetailsDO> autoPolicyParamList) {
		this.autoPolicyParamList = autoPolicyParamList;
	}
	public List<ServiceRequestDetailsDO> getPropertyPolicyParamList() {
		return propertyPolicyParamList;
	}
	public void setPropertyPolicyParamList(
			List<ServiceRequestDetailsDO> propertyPolicyParamList) {
		this.propertyPolicyParamList = propertyPolicyParamList;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((assignedToId == null) ? 0 : assignedToId.hashCode());
		result = prime * result
				+ ((assignedGroup == null) ? 0 : assignedGroup.hashCode());
		result = prime
				* result
				+ ((propertyProductType == null) ? 0 : propertyProductType
						.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result
				+ ((userName == null) ? 0 : userName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TDMRequestDataDTO other = (TDMRequestDataDTO) obj;
		if (assignedToId == null) {
			if (other.assignedToId != null)
				return false;
		} else if (!assignedToId.equals(other.assignedToId))
			return false;
		if (assignedGroup == null) {
			if (other.assignedGroup != null)
				return false;
		} else if (!assignedGroup.equals(other.assignedGroup))
			return false;
		if (propertyProductType == null) {
			if (other.propertyProductType != null)
				return false;
		} else if (!propertyProductType.equals(other.propertyProductType))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ATSDataCreationDTO [requestId=" + requestId + ", userName="
				+ userName + ", userId=" + userId + ", requestedBy="
				+ requestedBy + ", applicationOwner=" + applicationOwner
				+ ", approver=" + approver + ", status=" + status
				+ ", createdOn=" + createdOn + ", assignedGroupId="
				+ assignedGroup + ", AssignedToId=" + assignedToId
				+ ", subject=" + subject + ", consumerGroup=" + consumerGroup
				+ ", dataSource=" + dataSource + ", expectedDate="
				+ expectedDate + ", priority=" + priority + ", environment="
				+ environment + ", autoProductType=" + autoProductType
				+ ", propertyProductType=" + propertyProductType
				+ ", autoPolicyParamList=" + autoPolicyParamList
				+ ", propertyPolicyParamList=" + propertyPolicyParamList + "]";
	}
	

}
