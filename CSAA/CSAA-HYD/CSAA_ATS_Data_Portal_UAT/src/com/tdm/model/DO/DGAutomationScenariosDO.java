package com.tdm.model.DO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the DG_AUTOMATION_SCENARIOS database table.
 * 
 */
@Entity
@Table(name = "DG_AUTOMATION_SCENARIOS")
@NamedQueries({
@NamedQuery(name = "DGAutomationScenariosDO.findAll", query = "SELECT sr FROM DGAutomationScenariosDO sr")
 })

public class DGAutomationScenariosDO extends TDMObject {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "SCENARIO_ID")
	private String scenarioId;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "DG_TYPE")
	private String dgType;

	@Column(name = "IS_ACTIVE")
	private String isActive;
	
	public String getScenarioId() {
		return scenarioId;
	}

	public void setScenarioId(String scenarioId) {
		this.scenarioId = scenarioId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDgType() {
		return dgType;
	}

	public void setDgType(String dgType) {
		this.dgType = dgType;
	}

	@Override
	public String toString() {
		return "DGAutomationScenariosDO [scenarioId=" + scenarioId
				+ ", description=" + description + ", dgType=" + dgType + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((dgType == null) ? 0 : dgType.hashCode());
		result = prime * result
				+ ((scenarioId == null) ? 0 : scenarioId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DGAutomationScenariosDO other = (DGAutomationScenariosDO) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (dgType == null) {
			if (other.dgType != null)
				return false;
		} else if (!dgType.equals(other.dgType))
			return false;
		if (scenarioId == null) {
			if (other.scenarioId != null)
				return false;
		} else if (!scenarioId.equals(other.scenarioId))
			return false;
		return true;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

}