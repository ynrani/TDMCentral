package com.datacon.model.DTO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ProjectDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String projectId;

	private Date dateCreated;

	private String description;

	private String projectName;
	private TdmUserDTO user;

	private List<SubsetCriteriaDTO> subsetCriterias;

	public ProjectDTO() {
	}

	public String getProjectId() {
		return this.projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public Date getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public TdmUserDTO getUser() {
		return this.user;
	}

	public void setUser(TdmUserDTO user) {
		this.user = user;
	}

	public List<SubsetCriteriaDTO> getSubsetCriterias() {
		return this.subsetCriterias;
	}

	public void setSubsetCriterias(List<SubsetCriteriaDTO> subsetCriterias) {
		this.subsetCriterias = subsetCriterias;
	}

	public SubsetCriteriaDTO addSubsetCriteria(SubsetCriteriaDTO subsetCriteria) {
		getSubsetCriterias().add(subsetCriteria);
		subsetCriteria.setProject(this);

		return subsetCriteria;
	}

	public SubsetCriteriaDTO removeSubsetCriteria(SubsetCriteriaDTO subsetCriteria) {
		getSubsetCriterias().remove(subsetCriteria);
		subsetCriteria.setProject(null);

		return subsetCriteria;
	}

}