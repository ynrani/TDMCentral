/*---------------------------------------------------------------------------------------
 * Object Name: OperationParameters.Java
 * --------------------------------------------------------------------------------------
 *
 * Copyright: 2015 Capgemini Financial Services
 *---------------------------------------------------------------------------------------*/

package com.tesda.model.DTO;

/**
 * OperationParameters data model attribute class
 */
public class OperationParameters
{

	private String appName;
	private String dbName;
	private String noOfTables;
	private String id;

	public String getAppName()
	{
		return appName;
	}

	public void setAppName(String appName)
	{
		this.appName = appName;
	}

	public String getDbName()
	{
		return dbName;
	}

	public void setDbName(String dbName)
	{
		this.dbName = dbName;
	}

	public String getNoOfTables()
	{
		return noOfTables;
	}

	public void setNoOfTables(String noOfTables)
	{
		this.noOfTables = noOfTables;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

}