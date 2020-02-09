package com.datacon.model.DTO;

import java.io.Serializable;
import java.util.List;


public class SubsetCriteriaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String subsetId;

	private String subsetCondCol;

	private String subsetCondTab;

	private String subsetCondVal;

	private String tableListId;

	private List<JobDTO> jobs;

	private DbConnectionsDTO dataConConnection;

	private ProjectDTO project;


	public SubsetCriteriaDTO() {
	}

	public String getSubsetId() {
		return this.subsetId;
	}

	public void setSubsetId(String subsetId) {
		this.subsetId = subsetId;
	}

	public String getSubsetCondCol() {
		return this.subsetCondCol;
	}

	public void setSubsetCondCol(String subsetCondCol) {
		this.subsetCondCol = subsetCondCol;
	}

	public String getSubsetCondTab() {
		return this.subsetCondTab;
	}

	public void setSubsetCondTab(String subsetCondTab) {
		this.subsetCondTab = subsetCondTab;
	}

	public String getSubsetCondVal() {
		return this.subsetCondVal;
	}

	public void setSubsetCondVal(String subsetCondVal) {
		this.subsetCondVal = subsetCondVal;
	}

	public String getTableListId() {
		return this.tableListId;
	}

	public void setTableListId(String tableListId) {
		this.tableListId = tableListId;
	}

	public List<JobDTO> getJobs() {
		return this.jobs;
	}

	public void setJobs(List<JobDTO> jobs) {
		this.jobs = jobs;
	}

	public JobDTO addJob(JobDTO job) {
		getJobs().add(job);
		job.setSubsetCriteria(this);

		return job;
	}

	public JobDTO removeJob(JobDTO job) {
		getJobs().remove(job);
		job.setSubsetCriteria(null);

		return job;
	}

	

	public DbConnectionsDTO getDataConConnection() {
		return this.dataConConnection;
	}

	public void setDataConConnection(DbConnectionsDTO dataConConnection) {
		this.dataConConnection = dataConConnection;
	}

	public ProjectDTO getProject() {
		return this.project;
	}

	public void setProject(ProjectDTO project) {
		this.project = project;
	}

	/*public SubsetCriteriaColumnDO getSubsetCriteriaColumn() {
		return this.subsetCriteriaColumn;
	}

	public void setSubsetCriteriaColumn(SubsetCriteriaColumnDO subsetCriteriaColumn) {
		this.subsetCriteriaColumn = subsetCriteriaColumn;
	}*/

}