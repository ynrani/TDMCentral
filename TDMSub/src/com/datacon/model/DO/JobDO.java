package com.datacon.model.DO;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the JOB database table.
 * 
 */
@Entity
@Table(name="JOB")
@NamedQuery(name="JobDO.findAll", query="SELECT j FROM JobDO j")
public class JobDO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="JOB_ID")
	private String jobId;

	@Column(name="ERROR_CODE")
	private String errorCode;

	@Temporal(TemporalType.DATE)
	@Column(name="JOB_END_TIME")
	private Date jobEndTime;

	@Temporal(TemporalType.DATE)
	@Column(name="JOB_START_TIME")
	private Date jobStartTime;

	//bi-directional many-to-one association to StatusCodeDO
	@ManyToOne
	@JoinColumn(name="STATUS_CODE")
	private StatusCodeDO statusCodeBean;

	//bi-directional many-to-one association to SubsetCriteriaDO
	@ManyToOne
	@JoinColumn(name="SUBSET_ID")
	private SubsetCriteriaDO subsetCriteria;

	//bi-directional many-to-one association to UserDO
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private TdmUserDO user;

	public JobDO() {
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

	public StatusCodeDO getStatusCodeBean() {
		return this.statusCodeBean;
	}

	public void setStatusCodeBean(StatusCodeDO statusCodeBean) {
		this.statusCodeBean = statusCodeBean;
	}

	public SubsetCriteriaDO getSubsetCriteria() {
		return this.subsetCriteria;
	}

	public void setSubsetCriteria(SubsetCriteriaDO subsetCriteria) {
		this.subsetCriteria = subsetCriteria;
	}

	public TdmUserDO getUser() {
		return this.user;
	}

	public void setUser(TdmUserDO user) {
		this.user = user;
	}

}