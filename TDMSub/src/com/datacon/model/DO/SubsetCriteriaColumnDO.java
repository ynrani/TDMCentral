package com.datacon.model.DO;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * The persistent class for the SUBSET_CRITERIA_COLUMNS database table.
 * 
 */
@Entity
@Table(name="SUBSET_CRITERIA_COLUMNS")
@NamedQuery(name="SubsetCriteriaColumnDO.findAll", query="SELECT s FROM SubsetCriteriaColumnDO s")
public class SubsetCriteriaColumnDO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="SUB_COL_ID")
	private String subsetCriteraiId;

	@Column(name="COLUMN_CONDITION")
	private String columnCondition;

	@Column(name="COLUMN_NAME")
	private String columnName;

	//bi-directional one-to-one association to SubsetCriteriaDO
	@OneToOne
	@JoinColumn(name="SUBSET_ID")
	private SubsetCriteriaDO subsetCriteria;

	public SubsetCriteriaColumnDO() {
	}

	public String getSubsetCriteraiId() {
		return this.subsetCriteraiId;
	}

	public void setSubsetCriteraiId(String subsetCriteraiId) {
		this.subsetCriteraiId = subsetCriteraiId;
	}

	public String getColumnCondition() {
		return this.columnCondition;
	}

	public void setColumnCondition(String columnCondition) {
		this.columnCondition = columnCondition;
	}

	public String getColumnName() {
		return this.columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	

	public SubsetCriteriaDO getSubsetCriteria() {
		return this.subsetCriteria;
	}

	public void setSubsetCriteria(SubsetCriteriaDO subsetCriteria) {
		this.subsetCriteria = subsetCriteria;
	}

}