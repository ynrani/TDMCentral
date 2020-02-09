package com.datacon.model.DO;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the PROJECT database table.
 * 
 */
@Entity
@Table(name="PROJECT")
@NamedQueries({@NamedQuery(name="ProjectDO.findAll", query="SELECT p FROM ProjectDO p"),
	@NamedQuery(name="ProjectDO.findByProjectId", query="SELECT p FROM ProjectDO p WHERE p.projectId = :projectId"),
	@NamedQuery(name="ProjectDO.findByProjectName", query="SELECT p FROM ProjectDO p WHERE p.projectName= :projectName")
})
public class ProjectDO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="PROJECT_ID")
	private String projectId;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_CREATED")
	private Date dateCreated;

	private String description;

	@Column(name="PROJECT_NAME")
	private String projectName;

	//bi-directional many-to-one association to UserDO
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private TdmUserDO user;

	//bi-directional many-to-one association to SubsetCriteriaDO
	@OneToMany(mappedBy="project")
	private List<SubsetCriteriaDO> subsetCriterias;

	public ProjectDO() {
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

	public TdmUserDO getUser() {
		return this.user;
	}

	public void setUser(TdmUserDO user) {
		this.user = user;
	}

	public List<SubsetCriteriaDO> getSubsetCriterias() {
		return this.subsetCriterias;
	}

	public void setSubsetCriterias(List<SubsetCriteriaDO> subsetCriterias) {
		this.subsetCriterias = subsetCriterias;
	}

	public SubsetCriteriaDO addSubsetCriteria(SubsetCriteriaDO subsetCriteria) {
		getSubsetCriterias().add(subsetCriteria);
		subsetCriteria.setProject(this);

		return subsetCriteria;
	}

	public SubsetCriteriaDO removeSubsetCriteria(SubsetCriteriaDO subsetCriteria) {
		getSubsetCriterias().remove(subsetCriteria);
		subsetCriteria.setProject(null);

		return subsetCriteria;
	}

}