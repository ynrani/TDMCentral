package com.tesda.model.DO;

/**
 * The persistent class for the TDMPORTAL_LOGIN_USERS database table.
 */

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.tesda.constants.TDMConstants;

@Entity
@Table(name = TDMConstants.TDM_LOGIN_USERS_TABLE)
@NamedQuery(name = "TDMLoginUsersDO.findAll", query = "SELECT l FROM TDMLoginUsersDO l")
public class TDMLoginUsersDO implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private int Id;

	@Column(name = "USER_ID")
	private String userId;

	@Column(name = "ROLE")
	private String role;

	@Column(name = "APPLICATION_NAME")
	private String applicationName;

	@Column(name = "USERNAME")
	private String userName;

	@Column(name = "EMAIL_ID")
	private String emailId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FIRST_LOGIN_DATE")
	private Date firstLoginDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_LOGIN_DATE")
	private Date lastLoginDate;

	@Column(name = "LOGIN_COUNT")
	private int loginCount;

	public int getId()
	{
		return Id;
	}

	public void setId(int id)
	{
		Id = id;
	}

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

	public Date getFirstLoginDate()
	{
		return firstLoginDate;
	}

	public void setFirstLoginDate(Date firstLoginDate)
	{
		this.firstLoginDate = firstLoginDate;
	}

	public Date getLastLoginDate()
	{
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate)
	{
		this.lastLoginDate = lastLoginDate;
	}

	public int getLoginCount()
	{
		return loginCount;
	}

	public void setLoginCount(int loginCount)
	{
		this.loginCount = loginCount;
	}

}
