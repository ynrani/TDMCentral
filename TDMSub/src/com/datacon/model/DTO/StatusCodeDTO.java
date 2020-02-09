package com.datacon.model.DTO;

import java.io.Serializable;
import java.util.List;


public class StatusCodeDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String statusCode;
	private String statusDesc;

	private List<JobDTO> jobs;

	public StatusCodeDTO() {
	}

	public String getStatusCode() {
		return this.statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusDesc() {
		return this.statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public List<JobDTO> getJobs() {
		return this.jobs;
	}

	public void setJobs(List<JobDTO> jobs) {
		this.jobs = jobs;
	}

	public JobDTO addJob(JobDTO job) {
		getJobs().add(job);
		job.setStatusCodeBean(this);

		return job;
	}

	public JobDTO removeJob(JobDTO job) {
		getJobs().remove(job);
		job.setStatusCodeBean(null);

		return job;
	}

}