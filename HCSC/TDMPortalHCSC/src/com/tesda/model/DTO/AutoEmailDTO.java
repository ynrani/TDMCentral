/*---------------------------------------------------------------------------------------
 * Object Name: AutoEmailDTO.Java
 * --------------------------------------------------------------------------------------
 *
 * Copyright: 2015 Capgemini Financial Services
 *---------------------------------------------------------------------------------------*/

package com.tesda.model.DTO;

/**
 * AutoEmailDTO data model attribute class
 */
public class AutoEmailDTO
{

	private String userId;
	private String testCaseId;
	private String testCaseName;

	private String from;
	private String to;
	private String cc;
	private String subject;
	private String msg;
	private String userName;
	private String result;

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public String getTestCaseId()
	{
		return testCaseId;
	}

	public void setTestCaseId(String testCaseId)
	{
		this.testCaseId = testCaseId;
	}

	public String getTestCaseName()
	{
		return testCaseName;
	}

	public void setTestCaseName(String testCaseName)
	{
		this.testCaseName = testCaseName;
	}

	public String getFrom()
	{
		return from;
	}

	public void setFrom(String from)
	{
		this.from = from;
	}

	public String getTo()
	{
		return to;
	}

	public void setTo(String to)
	{
		this.to = to;
	}

	public String getCc()
	{
		return cc;
	}

	public void setCc(String cc)
	{
		this.cc = cc;
	}

	public String getSubject()
	{
		return subject;
	}

	public void setSubject(String subject)
	{
		this.subject = subject;
	}

	public String getMsg()
	{
		return msg;
	}

	public void setMsg(String msg)
	{
		this.msg = msg;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getResult()
	{
		return result;
	}

	public void setResult(String result)
	{
		this.result = result;
	}
}
