package com.tesda.model.DO;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TDMPORTAL_SESSION_INFO")
@NamedQuery(name = "TDMSessionReportDO.findAll", query = "SELECT r FROM TDMSessionReportDO r")
public class TDMSessionReportDO implements Serializable
{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private String id;

	@Column(name = "USER_ID")
	private String userID;//

	@Column(name = "ROLE")
	private String role;

	@Column(name = "APPLICATION")
	private String application;

	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "EMAIL")
	private String email;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LOGIN_TIME")
	private Date inTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LOGOUT_TIME")
	private Date outTime;

	@Column(name = "SEARCH_COUNT")
	private int queryCount;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getUserID()
	{
		return userID;
	}

	public void setUserID(String userID)
	{
		this.userID = userID;
	}

	public String getRole()
	{
		return role;
	}

	public void setRole(String role)
	{
		this.role = role;
	}

	public String getApplication()
	{
		return application;
	}

	public void setApplication(String application)
	{
		this.application = application;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public Date getInTime()
	{
		return inTime;
	}

	public void setInTime(Date inTime)
	{
		this.inTime = inTime;
	}

	public Date getOutTime()
	{
		return outTime;
	}

	public void setOutTime(Date outTime)
	{
		this.outTime = outTime;
	}

	public int getQueryCount()
	{
		return queryCount;
	}

	public void setQueryCount(int queryCount)
	{
		this.queryCount = queryCount;
	}
}
