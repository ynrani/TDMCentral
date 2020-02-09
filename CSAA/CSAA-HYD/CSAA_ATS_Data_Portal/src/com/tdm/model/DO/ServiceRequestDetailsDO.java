package com.tdm.model.DO;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the ATS_SERVICE_REQUEST_DETAILS DB table.
 * 
 */
@Entity
@Table(name = "SERVICE_REQUEST_DETAILS")
@NamedQuery(name = "ServiceRequestDetailsDO.findAll", query = "SELECT sr FROM ServiceRequestDetailsDO sr")

public class ServiceRequestDetailsDO extends TDMObject {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "seqGenerator", sequenceName = "SERVICE_REQUEST_DETAILS_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGenerator")
	private Long id;
	
	@Column(name = "SEQ_NO")
	private String seqNo;
	
    @Column(name="REQUEST_ID")
	private String requestId;
	
	@Column(name = "COLUMN_NAME")
	private String columnName;
	
	@Column(name = "COLUMN_VALUE")
	private String columnValue;
	
	@Column(name = "POLICY_TYPE")
	private String policyType;
	
	@Column(name = "CREATED_BY")
	private String createdBy;
	
	@Column(name = "CREATED_DATE")
	private Date createdDate;

	// bi-directional many-to-one association to CcPolicyDO
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REQUEST_ID", insertable = false, updatable = false)
	private ServiceRequestDO serviceRequestDO;
	
	public ServiceRequestDetailsDO() {
		
	}
	public ServiceRequestDetailsDO(String scenarioNo, String columnName, String columnValue
			, String policyType, String createdBy) {
		this.seqNo = scenarioNo;
		this.columnName = columnName;
		this.columnValue = columnValue;
		this.policyType = policyType;
		this.createdBy = createdBy;
		this.createdDate = new Date();
	}
	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnValue() {
		return columnValue;
	}

	public void setColumnValue(String columnValue) {
		this.columnValue = columnValue;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((columnName == null) ? 0 : columnName.hashCode());
		result = prime * result
				+ ((columnValue == null) ? 0 : columnValue.hashCode());
		result = prime * result
				+ ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result
				+ ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result
				+ ((requestId == null) ? 0 : requestId.hashCode());
		result = prime * result + ((seqNo == null) ? 0 : seqNo.hashCode());
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
		ServiceRequestDetailsDO other = (ServiceRequestDetailsDO) obj;
		if (columnName == null) {
			if (other.columnName != null)
				return false;
		} else if (!columnName.equals(other.columnName))
			return false;
		if (columnValue == null) {
			if (other.columnValue != null)
				return false;
		} else if (!columnValue.equals(other.columnValue))
			return false;
		if (createdBy == null) {
			if (other.createdBy != null)
				return false;
		} else if (!createdBy.equals(other.createdBy))
			return false;
		if (createdDate == null) {
			if (other.createdDate != null)
				return false;
		} else if (!createdDate.equals(other.createdDate))
			return false;
		if (requestId == null) {
			if (other.requestId != null)
				return false;
		} else if (!requestId.equals(other.requestId))
			return false;
		if (seqNo == null) {
			if (other.seqNo != null)
				return false;
		} else if (!seqNo.equals(other.seqNo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ServiceRequestDetailsDO [id=" + id + ", seqNo=" + seqNo
				+ ", requestId=" + requestId + ", columnName=" + columnName
				+ ", columnValue=" + columnValue + ", policyType=" + policyType
				+ ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ ", serviceRequestDO=" + serviceRequestDO + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPolicyType() {
		return policyType;
	}

	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}

	public ServiceRequestDO getServiceRequestDO() {
		return serviceRequestDO;
	}

	public void setServiceRequestDO(ServiceRequestDO serviceRequestDO) {
		this.serviceRequestDO = serviceRequestDO;
	}

}