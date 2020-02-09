/*---------------------------------------------------------------------------------------
 * Object Name: ValidationResponse.Java
 * --------------------------------------------------------------------------------------
 *
 * Copyright: 2015 Capgemini Financial Services
 *---------------------------------------------------------------------------------------*/

package com.tesda.model.DTO;

/**
 * ValidationResponse data model attribute class
 */
public class ValidationResponse
{

	private String status = null;
	private Object result = null;

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public Object getResult()
	{
		return result;
	}

	public void setResult(Object result)
	{
		this.result = result;
	}

}
