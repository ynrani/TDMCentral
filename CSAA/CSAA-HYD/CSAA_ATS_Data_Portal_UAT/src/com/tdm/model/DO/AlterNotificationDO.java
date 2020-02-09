package com.tdm.model.DO;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * The persistent class for the DC_ACCESS_LOG database table.
 * @author vchintha
 * 
 */
@Entity
@Table(name = "CFG_ALERT_NOTIFICATION")
@NamedQuery(name = "AlterNotificationDO.findAll", query = "SELECT sr FROM AlterNotificationDO sr")
public class AlterNotificationDO extends TDMObject {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ALERT_ID")
	private Integer alertId;
	
	@Column(name = "ALERT_DESC")
	private String alertDesc;
	
	@Column(name = "IS_ACTIVE")
	private String isActive;
	
	@Column(name = "CREATED_BY")
	private String createdBy;
	
	@Column(name = "CREATED_DATE")
	private Date createdDate;
	
	@Column(name = "MODIFIED_BY")
	private String modifiedBy;

	@Column(name = "MODIFIED_DATE")
	private Date modifiedDate;
	
	@Column(name = "TEMPLATE")
	private String template;
	
	@Transient
	private String userEmail;
	


	public AlterNotificationDO() {
	}

	public int getAlertId() {
		return alertId;
	}

	public void setAlertId(int alertId) {
		this.alertId = alertId;
	}

	public String getAlertDesc() {
		return alertDesc;
	}

	public void setAlertDesc(String alertDesc) {
		this.alertDesc = alertDesc;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
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
	
	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((alertDesc == null) ? 0 : alertDesc.hashCode());
		result = prime * result + ((alertId == null) ? 0 : alertId.hashCode());
		result = prime * result
				+ ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result
				+ ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result
				+ ((isActive == null) ? 0 : isActive.hashCode());
		result = prime * result
				+ ((modifiedBy == null) ? 0 : modifiedBy.hashCode());
		result = prime * result
				+ ((modifiedDate == null) ? 0 : modifiedDate.hashCode());
		result = prime * result
				+ ((template == null) ? 0 : template.hashCode());
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
		AlterNotificationDO other = (AlterNotificationDO) obj;
		if (alertDesc == null) {
			if (other.alertDesc != null)
				return false;
		} else if (!alertDesc.equals(other.alertDesc))
			return false;
		if (alertId == null) {
			if (other.alertId != null)
				return false;
		} else if (!alertId.equals(other.alertId))
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
		if (isActive == null) {
			if (other.isActive != null)
				return false;
		} else if (!isActive.equals(other.isActive))
			return false;
		if (modifiedBy == null) {
			if (other.modifiedBy != null)
				return false;
		} else if (!modifiedBy.equals(other.modifiedBy))
			return false;
		if (modifiedDate == null) {
			if (other.modifiedDate != null)
				return false;
		} else if (!modifiedDate.equals(other.modifiedDate))
			return false;
		if (template == null) {
			if (other.template != null)
				return false;
		} else if (!template.equals(other.template))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AlterNotificationDO [alertId=" + alertId + ", alertDesc="
				+ alertDesc + ", isActive=" + isActive + ", createdBy="
				+ createdBy + ", createdDate=" + createdDate + ", modifiedBy="
				+ modifiedBy + ", modifiedDate=" + modifiedDate + ", template="
				+ template + "]";
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	
}