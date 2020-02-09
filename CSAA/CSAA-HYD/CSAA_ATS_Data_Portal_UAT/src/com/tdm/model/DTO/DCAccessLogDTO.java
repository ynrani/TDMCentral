package com.tdm.model.DTO;

import java.util.Date;

/***
 * DC_ACCESS_LOG DTO
 * @author mamuppar
 *
 */
public class DCAccessLogDTO {

	private Integer id;
	private String requestId;
	private String requestStatus;
	private String modifiedDate;
	private String modifiedBy;
	private String comments;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getRequestStatus() {
		return requestStatus;
	}
	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}
	public String getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(String modifiedDate) {
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
	public String toString() {
		return "DCAccessLogDTO [id=" + id + ", requestId=" + requestId
				+ ", requestStatus=" + requestStatus + ", modifiedDate="
				+ modifiedDate + ", modifiedBy=" + modifiedBy + ", comments="
				+ comments + "]";
	}
}
