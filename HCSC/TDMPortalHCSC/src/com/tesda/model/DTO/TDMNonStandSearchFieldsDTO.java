/*---------------------------------------------------------------------------------------
 * Object Name: TDMNonStandSearchFieldsDTO.Java
 * --------------------------------------------------------------------------------------
 *
 * Copyright: 2015 Capgemini Financial Services
 *---------------------------------------------------------------------------------------*/

package com.tesda.model.DTO;

import java.util.Set;

/**
 * TDMNonStandSearchFieldsDTO data model attribute class
 */
public class TDMNonStandSearchFieldsDTO
{

	private Set<String> claimTypes;
	private Set<String> planTypes;
	private Set<String> memCatagories;
	private Set<String> coverageTypes;
	private Set<String> memStatus;
	private Set<String> stateTypes;
	private Set<String> subscRelations;
	private Set<String> ageGroups;
	private Set<String> fundingInd;
	private Set<String> pbms;

	public Set<String> getClaimTypes()
	{
		return claimTypes;
	}

	public void setClaimTypes(Set<String> claimTypes)
	{
		this.claimTypes = claimTypes;
	}

	public Set<String> getPlanTypes()
	{
		return planTypes;
	}

	public void setPlanTypes(Set<String> planTypes)
	{
		this.planTypes = planTypes;
	}

	public Set<String> getMemCatagories()
	{
		return memCatagories;
	}

	public void setMemCatagories(Set<String> memCatagories)
	{
		this.memCatagories = memCatagories;
	}

	public Set<String> getCoverageTypes()
	{
		return coverageTypes;
	}

	public void setCoverageTypes(Set<String> coverageTypes)
	{
		this.coverageTypes = coverageTypes;
	}

	public Set<String> getMemStatus()
	{
		return memStatus;
	}

	public void setMemStatus(Set<String> memStatus)
	{
		this.memStatus = memStatus;
	}

	public Set<String> getStateTypes()
	{
		return stateTypes;
	}

	public void setStateTypes(Set<String> stateTypes)
	{
		this.stateTypes = stateTypes;
	}

	public Set<String> getSubscRelations()
	{
		return subscRelations;
	}

	public void setSubscRelations(Set<String> subscRelations)
	{
		this.subscRelations = subscRelations;
	}

	public Set<String> getAgeGroups()
	{
		return ageGroups;
	}

	public void setAgeGroups(Set<String> ageGroups)
	{
		this.ageGroups = ageGroups;
	}

	public Set<String> getFundingInd()
	{
		return fundingInd;
	}

	public void setFundingInd(Set<String> fundingInd)
	{
		this.fundingInd = fundingInd;
	}

	public Set<String> getPbms()
	{
		return pbms;
	}

	public void setPbms(Set<String> pbms)
	{
		this.pbms = pbms;
	}
}
