/*---------------------------------------------------------------------------------------
 * Object Name: TdmUserAuthDTO.Java
 * --------------------------------------------------------------------------------------
 *
 * Copyright: 2015 Capgemini Financial Services
 *---------------------------------------------------------------------------------------*/

package com.tesda.model.DTO;

/**
 * TdmUserAuthDTO data model attribute class
 */
public class TdmUserAuthDTO extends AbstractBaseDTO
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String role;
	private TdmUserDTO tdmUserDTO;

	public String getRole()
	{
		return role;
	}

	public void setRole(String role)
	{
		this.role = role;
	}

	public TdmUserDTO getTdmUserDTO()
	{
		return tdmUserDTO;
	}

	public void setTdmUserDTO(TdmUserDTO tdmUserDTO)
	{
		this.tdmUserDTO = tdmUserDTO;
	}

}