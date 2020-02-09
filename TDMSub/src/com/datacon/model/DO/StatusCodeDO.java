package com.datacon.model.DO;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the STATUS_CODE database table.
 * 
 */
@Entity
@Table(name="STATUS_CODE")
@NamedQuery(name="StatusCodeDO.findAll", query="SELECT s FROM StatusCodeDO s")
public class StatusCodeDO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="STATUS_CODE")
	private String statusCode;

	@Column(name="STATUS_DESC")
	private String statusDesc;

	//bi-directional many-to-one association to JobDO
	@OneToMany(mappedBy="statusCodeBean")
	private List<JobDO> jobs;

	public StatusCodeDO() {
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

	public List<JobDO> getJobs() {
		return this.jobs;
	}

	public void setJobs(List<JobDO> jobs) {
		this.jobs = jobs;
	}

	public JobDO addJob(JobDO job) {
		getJobs().add(job);
		job.setStatusCodeBean(this);

		return job;
	}

	public JobDO removeJob(JobDO job) {
		getJobs().remove(job);
		job.setStatusCodeBean(null);

		return job;
	}

}