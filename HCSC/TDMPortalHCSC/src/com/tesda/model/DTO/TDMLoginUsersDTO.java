package com.tesda.model.DTO;

/**
 * TDMLoginUsersDTO data model attribute class
 */
public class TDMLoginUsersDTO
{

	private String userId;
	private String role;
	private String applicationName;
	private String userName;
	private String emailId;
	private String firstLoginDate;
	private String lastLoginDate;
	private String loginCount;

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public String getRole()
	{
		return role;
	}

	public void setRole(String role)
	{
		this.role = role;
	}

	public String getApplicationName()
	{
		return applicationName;
	}

	public void setApplicationName(String applicationName)
	{
		this.applicationName = applicationName;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getEmailId()
	{
		return emailId;
	}

	public void setEmailId(String emailId)
	{
		this.emailId = emailId;
	}

	public String getFirstLoginDate()
	{
		return firstLoginDate;
	}

	public void setFirstLoginDate(String firstLoginDate)
	{
		this.firstLoginDate = firstLoginDate;
	}

	public String getLastLoginDate()
	{
		return lastLoginDate;
	}

	public void setLastLoginDate(String lastLoginDate)
	{
		this.lastLoginDate = lastLoginDate;
	}

	public String getLoginCount()
	{
		return loginCount;
	}

	public void setLoginCount(String loginCount)
	{
		this.loginCount = loginCount;
	}
}
