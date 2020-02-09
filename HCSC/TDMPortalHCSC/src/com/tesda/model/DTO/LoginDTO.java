/*---------------------------------------------------------------------------------------
 * Object Name: LoginDTO.Java
 * --------------------------------------------------------------------------------------
 *
 * Copyright: 2015 Capgemini Financial Services
 *---------------------------------------------------------------------------------------*/

package com.tesda.model.DTO;

/**
 * LoginDTO data model attribute class
 */
public class LoginDTO
{

	private String userId;
	private String password;

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

}
