/*---------------------------------------------------------------------------------------
 * Object Name: DependentDetailsDTO.Java
 * --------------------------------------------------------------------------------------
 *
 * Copyright: 2015 Capgemini Financial Services
 *---------------------------------------------------------------------------------------*/

package com.tesda.model.DTO;

import java.util.List;

/**
 * DependentDetailsDTO data model attribute class
 */

public class DependentDetailsDTO
{
	private String subId;
	private String subPtyId;
	private String deptPtyId;
	private String firstName;
	private String lastName;
	private String dob;
	private String gender;
	private String state;
	private String zipCode;
	private String memCategory;
	private String depStatus;
	private String subStatus;
	private String deptEffDate;
	private String deptEndDate;
	private String memSubIDEffDate;
	private String memSubIDEndDate;
	private String relationShip;
	private String relationShipName;
	private String relationShipCode;

	List<DependentDetailsDTO> dependentDetailsDTOList;

	public String getSubPtyId()
	{
		return subPtyId;
	}

	public void setSubPtyId(String subPtyId)
	{
		this.subPtyId = subPtyId;
	}

	public String getDeptPtyId()
	{
		return deptPtyId;
	}

	public void setDeptPtyId(String deptPtyId)
	{
		this.deptPtyId = deptPtyId;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getDob()
	{
		return dob;
	}

	public void setDob(String dob)
	{
		this.dob = dob;
	}

	public String getGender()
	{
		return gender;
	}

	public void setGender(String gender)
	{
		this.gender = gender;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public String getZipCode()
	{
		return zipCode;
	}

	public void setZipCode(String zipCode)
	{
		this.zipCode = zipCode;
	}

	public String getMemCategory()
	{
		return memCategory;
	}

	public void setMemCategory(String memCategory)
	{
		this.memCategory = memCategory;
	}

	public String getDepStatus()
	{
		return depStatus;
	}

	public void setDepStatus(String depStatus)
	{
		this.depStatus = depStatus;
	}

	public String getSubStatus()
	{
		return subStatus;
	}

	public void setSubStatus(String subStatus)
	{
		this.subStatus = subStatus;
	}

	public String getDeptEffDate()
	{
		return deptEffDate;
	}

	public void setDeptEffDate(String deptEffDate)
	{
		this.deptEffDate = deptEffDate;
	}

	public String getDeptEndDate()
	{
		return deptEndDate;
	}

	public void setDeptEndDate(String deptEndDate)
	{
		this.deptEndDate = deptEndDate;
	}

	public String getMemSubIDEffDate()
	{
		return memSubIDEffDate;
	}

	public void setMemSubIDEffDate(String memSubIDEffDate)
	{
		this.memSubIDEffDate = memSubIDEffDate;
	}

	public String getMemSubIDEndDate()
	{
		return memSubIDEndDate;
	}

	public void setMemSubIDEndDate(String memSubIDEndDate)
	{
		this.memSubIDEndDate = memSubIDEndDate;
	}

	public String getRelationShip()
	{
		return relationShip;
	}

	public void setRelationShip(String relationShip)
	{
		this.relationShip = relationShip;
	}

	public List<DependentDetailsDTO> getDependentDetailsDTOList()
	{
		return dependentDetailsDTOList;
	}

	public void setDependentDetailsDTOList(List<DependentDetailsDTO> dependentDetailsDTOList)
	{
		this.dependentDetailsDTOList = dependentDetailsDTOList;
	}

	public String getRelationShipCode()
	{
		return relationShipCode;
	}

	public void setRelationShipCode(String relationShipCode)
	{
		this.relationShipCode = relationShipCode;
	}

	public String getSubId()
	{
		return subId;
	}

	public void setSubId(String subId)
	{
		this.subId = subId;
	}

	public String getRelationShipName()
	{
		return relationShipName;
	}

	public void setRelationShipName(String relationShipName)
	{
		this.relationShipName = relationShipName;
	}
}
