package com.tdm.model.DTO;

import java.util.List;

import com.tdm.model.DO.AssignedGroup;
import com.tdm.model.DO.ConsumerGroup;
import com.tdm.model.DO.TDMDataSource;
import com.tdm.model.DO.TDMEnvironment;
import com.tdm.model.DO.TDMRequestPriority;
import com.tdm.model.DO.TDMRequestStatus;
import com.tdm.model.DO.TDMSupportUser;

public class TDMDataCreationDTO {
	private String requestId;
	private String userName;
	private String userId;
	private String requestedBy;
	private String applicationOwner;
	private String approver;
	private String status;
	private String createdOn;
	private String statusChange;
	private List<AssignedGroup> assignedGroupList;
	private List<ConsumerGroup> consumerGroupList;
	private List<TDMDataSource> dataSourceList;
	private List<TDMSupportUser> supportUserList;
	private List<TDMEnvironment> environmentList;
	private List<TDMRequestStatus> requestStatusList;
	private List<TDMRequestPriority> requestPriorityList;
	private List<FieldListDTO>        riskStateList;
	private List<FieldListDTO>       paymentPlanList;
	private String assignedGroup;
	private String assignedToId;
	private String subject;
	private String consumerGroup;
	private String dataSource;
	private String expectedDate;
	private String priority;
	private String autoPolicyType;
    
	private String propPolicyType;

	private String environment;
	private String autoProductType;
	private String propertyProductType;
	private String serviceType;
	private String serviceIdentifier;
	private String consumerGroupName;
	private String priorityName;
	private String lastModifiedDate;
	
	private String envDesc;
	
	public String getEnvDesc() {
		return envDesc;
	}

	public void setEnvDesc(String envDesc) {
		this.envDesc = envDesc;
	}

		
	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	private List<AutoPolicyParamsDTO> autoPolicyParamList;
	private List<PropertyPolicyParamsDTO> propertyPolicyParamList;

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

	public List<AssignedGroup> getAssignedGroupList() {
		return assignedGroupList;
	}

	public void setAssignedGroupList(List<AssignedGroup> assignedGroupList) {
		this.assignedGroupList = assignedGroupList;
	}
	public List<ConsumerGroup> getConsumerGroupList() {
		return consumerGroupList;
	}

	public void setConsumerGroupList(List<ConsumerGroup> consumerGroupList) {
		this.consumerGroupList = consumerGroupList;
	}
	public List<TDMDataSource> getDataSourceList() {
		return dataSourceList;
	}
	public void setDataSourceList(List<TDMDataSource> dataSourceList) {
		this.dataSourceList = dataSourceList;
	}
	public List<TDMSupportUser> getSupportUserList() {
		return supportUserList;
	}

	public void setSupportUserList(List<TDMSupportUser> supportUserList) {
		this.supportUserList = supportUserList;
	}
	public List<TDMEnvironment> getEnvironmentList() {
		return environmentList;
	}
	public void setEnvironmentList(List<TDMEnvironment> environmentList) {
		this.environmentList = environmentList;
	}
	public List<TDMRequestStatus> getRequestStatusList() {
		return requestStatusList;
	}
	public void setRequestStatusList(List<TDMRequestStatus> requestStatusList) {
		this.requestStatusList = requestStatusList;
	}

	public List<TDMRequestPriority> getRequestPriorityList() {
		return requestPriorityList;
	}

	public void setRequestPriorityList(List<TDMRequestPriority> requestPriorityList) {
		this.requestPriorityList = requestPriorityList;
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
	public void setAssignedToId(String assignedTo) {
		this.assignedToId = assignedTo;
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
	public List<FieldListDTO> getRiskStateList() {
		return riskStateList;
	}

	public void setRiskStateList(List<FieldListDTO> riskStateList) {
		this.riskStateList = riskStateList;
	}

	public List<FieldListDTO> getPaymentPlanList() {
		return paymentPlanList;
	}

	public void setPaymentPlanList(List<FieldListDTO> paymentPlanList) {
		this.paymentPlanList = paymentPlanList;
	}

	public List<AutoPolicyParamsDTO> getAutoPolicyParamList() {
		return autoPolicyParamList;
	}

	public void setAutoPolicyParamList(List<AutoPolicyParamsDTO> autoPolicyParamList) {
		this.autoPolicyParamList = autoPolicyParamList;
	}

	public List<PropertyPolicyParamsDTO> getPropertyPolicyParamList() {
		return propertyPolicyParamList;
	}

	public void setPropertyPolicyParamList(
			List<PropertyPolicyParamsDTO> propertyPolicyParamList) {
		this.propertyPolicyParamList = propertyPolicyParamList;
	}

	public String getStatusChange() {
		return statusChange;
	}

	public void setStatusChange(String statusChange) {
		this.statusChange = statusChange;
	}
	public String getAutoPolicyType() {
		return autoPolicyType;
	}

	public void setAutoPolicyType(String autoPolicyType) {
		this.autoPolicyType = autoPolicyType;
	}
	public String getPropPolicyType() {
		return propPolicyType;
	}

	public void setPropPolicyType(String propPolicyType) {
		this.propPolicyType = propPolicyType;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((applicationOwner == null) ? 0 : applicationOwner.hashCode());
		result = prime * result
				+ ((approver == null) ? 0 : approver.hashCode());
		result = prime * result
				+ ((assignedGroup == null) ? 0 : assignedGroup.hashCode());
		result = prime
				* result
				+ ((assignedGroupList == null) ? 0 : assignedGroupList
						.hashCode());
		result = prime * result
				+ ((assignedToId == null) ? 0 : assignedToId.hashCode());
		result = prime
				* result
				+ ((autoPolicyParamList == null) ? 0 : autoPolicyParamList
						.hashCode());
		result = prime * result
				+ ((autoProductType == null) ? 0 : autoProductType.hashCode());
		result = prime * result
				+ ((consumerGroup == null) ? 0 : consumerGroup.hashCode());
		result = prime
				* result
				+ ((consumerGroupList == null) ? 0 : consumerGroupList
						.hashCode());
		result = prime * result
				+ ((createdOn == null) ? 0 : createdOn.hashCode());
		result = prime * result
				+ ((dataSource == null) ? 0 : dataSource.hashCode());
		result = prime * result
				+ ((dataSourceList == null) ? 0 : dataSourceList.hashCode());
		result = prime * result
				+ ((environment == null) ? 0 : environment.hashCode());
		result = prime * result
				+ ((environmentList == null) ? 0 : environmentList.hashCode());
		result = prime * result
				+ ((expectedDate == null) ? 0 : expectedDate.hashCode());
		result = prime * result
				+ ((paymentPlanList == null) ? 0 : paymentPlanList.hashCode());
		result = prime * result
				+ ((priority == null) ? 0 : priority.hashCode());
		result = prime
				* result
				+ ((propertyPolicyParamList == null) ? 0
						: propertyPolicyParamList.hashCode());
		result = prime
				* result
				+ ((propertyProductType == null) ? 0 : propertyProductType
						.hashCode());
		result = prime * result
				+ ((requestId == null) ? 0 : requestId.hashCode());
		result = prime
				* result
				+ ((requestPriorityList == null) ? 0 : requestPriorityList
						.hashCode());
		result = prime
				* result
				+ ((requestStatusList == null) ? 0 : requestStatusList
						.hashCode());
		result = prime * result
				+ ((requestedBy == null) ? 0 : requestedBy.hashCode());
		result = prime * result
				+ ((riskStateList == null) ? 0 : riskStateList.hashCode());
		result = prime * result
				+ ((serviceType == null) ? 0 : serviceType.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((statusChange == null) ? 0 : statusChange.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		result = prime * result
				+ ((supportUserList == null) ? 0 : supportUserList.hashCode());
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
		TDMDataCreationDTO other = (TDMDataCreationDTO) obj;
		if (applicationOwner == null) {
			if (other.applicationOwner != null)
				return false;
		} else if (!applicationOwner.equals(other.applicationOwner))
			return false;
		if (approver == null) {
			if (other.approver != null)
				return false;
		} else if (!approver.equals(other.approver))
			return false;
		if (assignedGroup == null) {
			if (other.assignedGroup != null)
				return false;
		} else if (!assignedGroup.equals(other.assignedGroup))
			return false;
		if (assignedGroupList == null) {
			if (other.assignedGroupList != null)
				return false;
		} else if (!assignedGroupList.equals(other.assignedGroupList))
			return false;
		if (assignedToId == null) {
			if (other.assignedToId != null)
				return false;
		} else if (!assignedToId.equals(other.assignedToId))
			return false;
		if (autoPolicyParamList == null) {
			if (other.autoPolicyParamList != null)
				return false;
		} else if (!autoPolicyParamList.equals(other.autoPolicyParamList))
			return false;
		if (autoProductType == null) {
			if (other.autoProductType != null)
				return false;
		} else if (!autoProductType.equals(other.autoProductType))
			return false;
		if (consumerGroup == null) {
			if (other.consumerGroup != null)
				return false;
		} else if (!consumerGroup.equals(other.consumerGroup))
			return false;
		if (consumerGroupList == null) {
			if (other.consumerGroupList != null)
				return false;
		} else if (!consumerGroupList.equals(other.consumerGroupList))
			return false;
		if (createdOn == null) {
			if (other.createdOn != null)
				return false;
		} else if (!createdOn.equals(other.createdOn))
			return false;
		if (dataSource == null) {
			if (other.dataSource != null)
				return false;
		} else if (!dataSource.equals(other.dataSource))
			return false;
		if (dataSourceList == null) {
			if (other.dataSourceList != null)
				return false;
		} else if (!dataSourceList.equals(other.dataSourceList))
			return false;
		if (environment == null) {
			if (other.environment != null)
				return false;
		} else if (!environment.equals(other.environment))
			return false;
		if (environmentList == null) {
			if (other.environmentList != null)
				return false;
		} else if (!environmentList.equals(other.environmentList))
			return false;
		if (expectedDate == null) {
			if (other.expectedDate != null)
				return false;
		} else if (!expectedDate.equals(other.expectedDate))
			return false;
		if (paymentPlanList == null) {
			if (other.paymentPlanList != null)
				return false;
		} else if (!paymentPlanList.equals(other.paymentPlanList))
			return false;
		if (priority == null) {
			if (other.priority != null)
				return false;
		} else if (!priority.equals(other.priority))
			return false;
		if (propertyPolicyParamList == null) {
			if (other.propertyPolicyParamList != null)
				return false;
		} else if (!propertyPolicyParamList
				.equals(other.propertyPolicyParamList))
			return false;
		if (propertyProductType == null) {
			if (other.propertyProductType != null)
				return false;
		} else if (!propertyProductType.equals(other.propertyProductType))
			return false;
		if (requestId == null) {
			if (other.requestId != null)
				return false;
		} else if (!requestId.equals(other.requestId))
			return false;
		if (requestPriorityList == null) {
			if (other.requestPriorityList != null)
				return false;
		} else if (!requestPriorityList.equals(other.requestPriorityList))
			return false;
		if (requestStatusList == null) {
			if (other.requestStatusList != null)
				return false;
		} else if (!requestStatusList.equals(other.requestStatusList))
			return false;
		if (requestedBy == null) {
			if (other.requestedBy != null)
				return false;
		} else if (!requestedBy.equals(other.requestedBy))
			return false;
		if (riskStateList == null) {
			if (other.riskStateList != null)
				return false;
		} else if (!riskStateList.equals(other.riskStateList))
			return false;
		if (serviceType == null) {
			if (other.serviceType != null)
				return false;
		} else if (!serviceType.equals(other.serviceType))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (statusChange == null) {
			if (other.statusChange != null)
				return false;
		} else if (!statusChange.equals(other.statusChange))
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		if (supportUserList == null) {
			if (other.supportUserList != null)
				return false;
		} else if (!supportUserList.equals(other.supportUserList))
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
		return "TDMDataCreationDTO [requestId=" + requestId + ", userName="
				+ userName + ", userId=" + userId + ", requestedBy=" + requestedBy
				+ ", applicationOwner=" + applicationOwner + ", approver="
				+ approver + ", status=" + status + ", createdOn=" + createdOn
				+ ", statusChange=" + statusChange + ", assignedGroupList="
				+ assignedGroupList + ", consumerGroupList=" + consumerGroupList
				+ ", dataSourceList=" + dataSourceList + ", supportUserList="
				+ supportUserList + ", environmentList=" + environmentList
				+ ", requestStatusList=" + requestStatusList
				+ ", requestPriorityList=" + requestPriorityList
				+ ", riskStateList=" + riskStateList + ", paymentPlanList="
				+ paymentPlanList + ", assignedGroup=" + assignedGroup
				+ ", assignedToId=" + assignedToId + ", subject=" + subject
				+ ", consumerGroup=" + consumerGroup + ", dataSource=" + dataSource
				+ ", expectedDate=" + expectedDate + ", priority=" + priority
				+ ", environment=" + environment + ", autoProductType="
				+ autoProductType + ", propertyProductType=" + propertyProductType
				+ ", serviceType=" + serviceType + ", autoPolicyParamList="
				+ autoPolicyParamList + ", propertyPolicyParamList="
				+ propertyPolicyParamList + "]";
	}
	public String getServiceIdentifier() {
		return serviceIdentifier;
	}
	public void setServiceIdentifier(String serviceIdentifier) {
		this.serviceIdentifier = serviceIdentifier;
	}
		public String getConsumerGroupName() {
		return consumerGroupName;
	}
	public void setConsumerGroupName(String consumerGroupName) {
		this.consumerGroupName = consumerGroupName;
	}
	public String getPriorityName() {
		return priorityName;
	}
	public void setPriorityName(String priorityName) {
		this.priorityName = priorityName;
	}
	public String getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(String lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

}