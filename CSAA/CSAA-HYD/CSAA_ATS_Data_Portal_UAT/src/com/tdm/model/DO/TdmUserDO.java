package com.tdm.model.DO;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The persistent class for the USERS database table.
 * 
 */
@Entity
@Table(name = "USERS")
@NamedQueries({
		@NamedQuery(name = "TdmUserDO.findAll", query = "SELECT t FROM TdmUserDO t"),
		@NamedQuery(name = "TdmUserDO.findByUserId", query = "SELECT t FROM TdmUserDO t WHERE t.userId =:userId") })
public class TdmUserDO extends TDMObject
{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "USER_ID")
	private String userId;

	@Column(name = "EMAIL_ID")
	private String emailId;

	private String enabled;

	@Column(name = "MOBILE_NO")
	private String mobileNo;

	private String password;
	
	private String manager;
	
	@Column(name = "ACCESS_REASON")
	private String accessReason;

	private String username;

	// bi-directional many-to-one association to TdmUsersAuthDO
	@OneToOne(mappedBy = "tdmUser", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private TdmUsersAuthDO tdmUsersAuths;
	
	@Column(name = "LAST_LOGIN")
	private Timestamp lastLogin;

	public TdmUserDO()
	{
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmailId() {
		return this.emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getEnabled() {
		return this.enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getMobileNo() {
		return this.mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public TdmUsersAuthDO getTdmUsersAuths() {
		return this.tdmUsersAuths;
	}

	public void setTdmUsersAuths(TdmUsersAuthDO tdmUsersAuths) {
		this.tdmUsersAuths = tdmUsersAuths;
	}

	public Timestamp getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getAccessReason() {
		return accessReason;
	}

	public void setAccessReason(String accessReason) {
		this.accessReason = accessReason;
	}

}