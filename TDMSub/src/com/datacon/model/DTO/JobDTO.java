package com.datacon.model.DTO;

import java.io.Serializable;
import java.util.Date;


public class JobDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String jobId;
	private String errorCode;

	private Date jobEndTime;

	private Date jobStartTime;

	private StatusCodeDTO statusCodeBean;

	private SubsetCriteriaDTO subsetCriteria;

	private TdmUserDTO user;

	public JobDTO() {
	}

	public String getJobId() {
		return this.jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getErrorCode() {
		return this.errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public Date getJobEndTime() {
		return this.jobEndTime;
	}

	public void setJobEndTime(Date jobEndTime) {
		this.jobEndTime = jobEndTime;
	}

	public Date getJobStartTime() {
		return this.jobStartTime;
	}

	public void setJobStartTime(Date jobStartTime) {
		this.jobStartTime = jobStartTime;
	}

	public StatusCodeDTO getStatusCodeBean() {
		return this.statusCodeBean;
	}

	public void setStatusCodeBean(StatusCodeDTO statusCodeBean) {
		this.statusCodeBean = statusCodeBean;
	}

	public SubsetCriteriaDTO getSubsetCriteria() {
		return this.subsetCriteria;
	}

	public void setSubsetCriteria(SubsetCriteriaDTO subsetCriteria) {
		this.subsetCriteria = subsetCriteria;
	}

	public TdmUserDTO getUser() {
		return this.user;
	}

	public void setUser(TdmUserDTO user) {
		this.user = user;
	}

}