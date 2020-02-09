package com.tdm.model.DO;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the DC_ACCESS_LOG database table.
 * @author vchintha
 * 
 */
@Entity
@Table(name = "DC_ACCESS_LOG")
@NamedQueries({
@NamedQuery(name = "DCAccessLogDO.findAll", query = "SELECT sr FROM DCAccessLogDO sr"),
@NamedQuery(name = "DCAccessLogDO.findAccessLogByReqId", query = "SELECT e FROM DCAccessLogDO e  WHERE e.requestId =:requestId order by e.requestStatus asc")})
public class DCAccessLogDO extends TDMObject {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "seqGenerator", sequenceName = "DC_ACCESS_LOG_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGenerator")
	private Integer id;

	@Column(name = "REQUEST_ID")
	private String requestId;

	@Column(name = "REQUEST_STATUS")
	private Integer requestStatus;

	@Column(name = "MODIFIED_DATE")
	private Date modifiedDate;

	@Column(name = "MODIFIED_BY")
	private String modifiedBy;

	@Column(name = "COMMENTS")
	private String comments;

	public DCAccessLogDO() {
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(int requestStatus) {
		this.requestStatus = requestStatus;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((modifiedBy == null) ? 0 : modifiedBy.hashCode());
		result = prime * result
				+ ((modifiedDate == null) ? 0 : modifiedDate.hashCode());
		result = prime * result
				+ ((requestId == null) ? 0 : requestId.hashCode());
		result = prime * result
				+ ((requestStatus == null) ? 0 : requestStatus.hashCode());
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
		DCAccessLogDO other = (DCAccessLogDO) obj;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
		if (requestId == null) {
			if (other.requestId != null)
				return false;
		} else if (!requestId.equals(other.requestId))
			return false;
		if (requestStatus == null) {
			if (other.requestStatus != null)
				return false;
		} else if (!requestStatus.equals(other.requestStatus))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DCAccessLogDO [id=" + id + ", requestId=" + requestId
				+ ", requestStatus=" + requestStatus + ", modifiedDate="
				+ modifiedDate + ", modifiedBy=" + modifiedBy + ", comments="
				+ comments + "]";
	}


}