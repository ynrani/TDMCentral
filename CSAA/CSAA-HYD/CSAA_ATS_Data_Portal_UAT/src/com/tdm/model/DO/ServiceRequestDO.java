package com.tdm.model.DO;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * The persistent class for the ATS_SERVICE_REQUEST database table.
 * 
 */
@Entity
@Table(name = "SERVICE_REQUEST")
@NamedQueries({
@NamedQuery(name = "ServiceRequestDO.findAll", query = "SELECT sr FROM ServiceRequestDO sr"),
@NamedQuery(name = "ServiceRequestDO.findRequestByUserId", query = "SELECT e FROM ServiceRequestDO e  WHERE e.requestedBy =:requestedBy order by e.requestId asc"),
@NamedQuery(name = "ServiceRequestDO.findRequestBySupportUserId", query = "SELECT e FROM ServiceRequestDO e  WHERE e.assignedTo =:assignedTo"),
@NamedQuery(name = "ServiceRequestDO.findAllRequest", query = "SELECT p FROM ServiceRequestDO p where p.requestStatus !=:requestStatus order by p.expectedDate desc")
 })

public class ServiceRequestDO extends TDMObject {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "REQUEST_ID")
	private String requestId;
	
	@Column(name = "SERVICE_TYPE")
	private String serviceType;
	
	@Column(name = "REQUESTED_BY")
	private String requestedBy;
	
	@Column(name = "APPLICATION_OWNER")
	private String applicationOwner;
	
	@Column(name = "APPROVER")
	private String approver;
	
	@Column(name = "ASSIGNED_GROUP")
	private Long assignedGroupId;
	
	@Column(name = "ASSIGNED_TO")
	private String assignedTo;
	
	@Column(name = "REQUEST_STATUS")
	private Integer requestStatus;
	
	@Column(name = "SUBJECT")
	private String subject;
	
	@Column(name = "CONSUMER_GROUP")
	private Long consumerGroup;
	
	@Column(name = "PRIORITY")
	private Long priority;
	
	@Column(name = "DATA_SOURCE")
	private Long dataSource;
	
	@Column(name = "ENVIRONMENT")
	private Long environment;
	
	@OneToMany(mappedBy = "serviceRequestDO", fetch = FetchType.LAZY)
	private List<ServiceRequestDetailsDO> autoRequestDetailList;

	@OneToMany(mappedBy = "serviceRequestDO", fetch = FetchType.LAZY)
//	@JoinColumn(name="REQUEST_ID")
	private List<ServiceRequestDetailsDO> propertyRequestDetailList;
	
	@Column(name = "REASON")
	private Long reason;
	
	@Column(name = "CREATE_DATE")
	private Date createDate;
	
	@Column(name = "EXPECTED_DATE")
	private Date expectedDate;
	
	@Column(name = "RESOLVED_DATE")
	private Date resolvedDate;
	
	@Column(name = "CLOSED_DATE")
	private Date closedDate;
	
	@Column(name = "CREATED_BY")
	private String createdBy;
	
	@Column(name = "UPDATED_BY")
	private String updatedBy;
	
	@Column(name = "ENVIRONMENT_DETAILS")
	private String envDesc;
	
	public String getEnvDesc() {
		return envDesc;
	}

	public void setEnvDesc(String envDesc) {
		this.envDesc = envDesc;
	}
	
	@Transient
	private String consumerGroupName;
	@Transient
	private String priorityName;
	@Transient
    private Date lastModifiedDate;

	public ServiceRequestDO() {
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
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

	public Long getAssignedGroupId() {
		return assignedGroupId;
	}

	public void setAssignedGroupId(Long assignedGroupId) {
		this.assignedGroupId = assignedGroupId;
	}

	public String getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

	public Integer getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(Integer requestStatus) {
		this.requestStatus = requestStatus;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Long getConsumerGroup() {
		return consumerGroup;
	}

	public void setConsumerGroup(Long consumerGroup) {
		this.consumerGroup = consumerGroup;
	}

	public Long getPriority() {
		return priority;
	}

	public void setPriority(Long priority) {
		this.priority = priority;
	}

	public Long getDataSource() {
		return dataSource;
	}

	public void setDataSource(Long dataSource) {
		this.dataSource = dataSource;
	}

	public Long getEnvironment() {
		return environment;
	}

	public void setEnvironment(Long environment) {
		this.environment = environment;
	}

	public Long getReason() {
		return reason;
	}

	public void setReason(Long reason) {
		this.reason = reason;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createate) {
		this.createDate = createate;
	}

	public Date getExpectedDate() {
		return expectedDate;
	}

	public void setExpectedDate(Date expectedDate) {
		this.expectedDate = expectedDate;
	}

	public Date getResolvedDate() {
		return resolvedDate;
	}

	public void setResolvedDate(Date resolvedDate) {
		this.resolvedDate = resolvedDate;
	}

	public Date getClosedDate() {
		return closedDate;
	}

	public void setClosedDate(Date closedDate) {
		this.closedDate = closedDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((applicationOwner == null) ? 0 : applicationOwner.hashCode());
		result = prime * result
				+ ((requestStatus == null) ? 0 : requestStatus.hashCode());
		result = prime * result
				+ ((updatedBy == null) ? 0 : updatedBy.hashCode());
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
		ServiceRequestDO other = (ServiceRequestDO) obj;
		if (applicationOwner == null) {
			if (other.applicationOwner != null)
				return false;
		} else if (!applicationOwner.equals(other.applicationOwner))
			return false;
		if (requestId != other.requestId)
			return false;
		if (requestStatus == null) {
			if (other.requestStatus != null)
				return false;
		} else if (!requestStatus.equals(other.requestStatus))
			return false;
		if (updatedBy == null) {
			if (other.updatedBy != null)
				return false;
		} else if (!updatedBy.equals(other.updatedBy))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ServiceRequestDO [requestId=" + requestId + ", requestedBy="
				+ requestedBy + ", applicationOwner=" + applicationOwner
				+ ", approver=" + approver + ", assignedGroup=" + assignedGroupId
				+ ", assignedTo=" + assignedTo + ", requestStatus="
				+ requestStatus + ", subject=" + subject + ", consumerGroup="
				+ consumerGroup + ", priority=" + priority + ", dataSource="
				+ dataSource + ", environment=" + environment + ", reason="
				+ reason + ", createate=" + createDate + ", expectedDate="
				+ expectedDate + ", resolvedDate=" + resolvedDate
				+ ", closedDate=" + closedDate + ", createdBy=" + createdBy
				+ ", updatedBy=" + updatedBy + "]";
	}

	public List<ServiceRequestDetailsDO> getAutoRequestDetailList() {
		return autoRequestDetailList;
	}

	public void setAutoRequestDetailList(
			List<ServiceRequestDetailsDO> autoRequestDetailList) {
		this.autoRequestDetailList = autoRequestDetailList;
	}

	public List<ServiceRequestDetailsDO> getPropertyRequestDetailList() {
		return propertyRequestDetailList;
	}

	public void setPropertyRequestDetailList(
			List<ServiceRequestDetailsDO> propertyRequestDetailList) {
		this.propertyRequestDetailList = propertyRequestDetailList;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
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

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

}