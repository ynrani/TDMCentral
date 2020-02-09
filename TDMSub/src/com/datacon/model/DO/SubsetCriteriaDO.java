package com.datacon.model.DO;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the SUBSET_CRITERIA database table.
 * 
 */
@Entity
@Table(name="SUBSET_CRITERIA")
@NamedQuery(name="SubsetCriteriaDO.findAll", query="SELECT s FROM SubsetCriteriaDO s")
public class SubsetCriteriaDO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="SUBSET_ID")
	private String subsetId;

	@Column(name="SUBSET_COND_COL")
	private String subsetCondCol;

	@Column(name="SUBSET_COND_TAB")
	private String subsetCondTab;

	@Column(name="SUBSET_COND_VAL")
	private String subsetCondVal;

	@Column(name="TABLE_LIST_ID")
	private String tableListId;

	//bi-directional many-to-one association to JobDO
	@OneToMany(mappedBy="subsetCriteria")
	private List<JobDO> jobs;
	
	@Column(name="SUBSET_START_TABLE")
	private String startTable;

	//bi-directional many-to-one association to ConnectionDO

	//bi-directional many-to-one association to DataConConnectionDO
	@ManyToOne
	@JoinColumn(name="CONNECTION_ID")
	private DataConConnectionsDO dataConConnection;

	//bi-directional many-to-one association to ProjectDO
	@ManyToOne
	@JoinColumn(name="PROJECT_ID")
	private ProjectDO project;

	//bi-directional one-to-one association to SubsetCriteriaColumnDO
	/*@OneToOne(mappedBy="subsetCriteria")
	private SubsetCriteriaColumnDO subsetCriteriaColumn;*/

	public SubsetCriteriaDO() {
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

	public List<JobDO> getJobs() {
		return this.jobs;
	}

	public void setJobs(List<JobDO> jobs) {
		this.jobs = jobs;
	}

	public JobDO addJob(JobDO job) {
		getJobs().add(job);
		job.setSubsetCriteria(this);

		return job;
	}

	public JobDO removeJob(JobDO job) {
		getJobs().remove(job);
		job.setSubsetCriteria(null);

		return job;
	}

	

	public DataConConnectionsDO getDataConConnection() {
		return this.dataConConnection;
	}

	public void setDataConConnection(DataConConnectionsDO dataConConnection) {
		this.dataConConnection = dataConConnection;
	}

	public ProjectDO getProject() {
		return this.project;
	}

	public void setProject(ProjectDO project) {
		this.project = project;
	}

	public String getStartTable(){
		return startTable;
	}

	public void setStartTable(String startTable){
		this.startTable = startTable;
	}

	/*public SubsetCriteriaColumnDO getSubsetCriteriaColumn() {
		return this.subsetCriteriaColumn;
	}

	public void setSubsetCriteriaColumn(SubsetCriteriaColumnDO subsetCriteriaColumn) {
		this.subsetCriteriaColumn = subsetCriteriaColumn;
	}*/

}