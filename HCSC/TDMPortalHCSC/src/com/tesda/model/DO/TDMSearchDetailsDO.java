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
@Table(name = "TDM_SEARCH_DETAILS")
@NamedQuery(name = "TDMSearchDetailsDO.findAll", query = "SELECT r FROM TDMSearchDetailsDO r")
public class TDMSearchDetailsDO implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;
	
	@Column(name = "USER_ID")
	private String userId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SEARCH_TIME")
	private Date searchTime;
	
	@Column(name = "SUBS_ID")
	private String subscriberId;
	
	@Column(name = "MBR_CAT")
	private String memberCat;
	
	@Column(name = "CORP_ID")
	private String corpId;
	
	@Column(name = "EXCHANGE_TYPE")
	private String exchangeType;
	
	@Column(name = "COVERAGE")
	private String coverage;
	
	@Column(name = "PRODUCT_TYPE")
	private String productType;
	
	@Column(name = "MEMBER_TYPE")
	private String memberType;
	
	@Column(name = "AGE")
	private String age;
	
	@Column(name = "SUB_STATUS")
	private String subscrbrStatus;
	
	@Column(name = "ORIGINAL_EFFDT")
	private Date originalEffDate;
	
	@Column(name = "EXISTING_CLAIMS")
	private String existingClaims;
	
	@Column(name = "ACCOUNT_NAME")
	private String accountName;
	
	@Column(name = "ACCOUNT_NUMBER")
	private String accountNum;
	
	@Column(name = "FUNDING_IND")
	private String fundInd;
	
	@Column(name = "PROJECT_ID")
	private String projectID;
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getUserId()
	{
		return userId;
	}
	public void setUserId(String userId)
	{
		this.userId = userId;
	}
	public Date getSearchTime()
	{
		return searchTime;
	}
	public void setSearchTime(Date searchTime)
	{
		this.searchTime = searchTime;
	}
	public String getSubscriberId()
	{
		return subscriberId;
	}
	public void setSubscriberId(String subscriberId)
	{
		this.subscriberId = subscriberId;
	}
	public String getMemberCat()
	{
		return memberCat;
	}
	public void setMemberCat(String memberCat)
	{
		this.memberCat = memberCat;
	}
	public String getCorpId()
	{
		return corpId;
	}
	public void setCorpId(String corpId)
	{
		this.corpId = corpId;
	}
	public String getExchangeType()
	{
		return exchangeType;
	}
	public void setExchangeType(String exchangeType)
	{
		this.exchangeType = exchangeType;
	}
	public String getCoverage()
	{
		return coverage;
	}
	public void setCoverage(String coverage)
	{
		this.coverage = coverage;
	}
	public String getProductType()
	{
		return productType;
	}
	public void setProductType(String productType)
	{
		this.productType = productType;
	}
	public String getMemberType()
	{
		return memberType;
	}
	public void setMemberType(String memberType)
	{
		this.memberType = memberType;
	}
	public String getAge()
	{
		return age;
	}
	public void setAge(String age)
	{
		this.age = age;
	}
	public String getSubscrbrStatus()
	{
		return subscrbrStatus;
	}
	public void setSubscrbrStatus(String subscrbrStatus)
	{
		this.subscrbrStatus = subscrbrStatus;
	}
	public Date getOriginalEffDate()
	{
		return originalEffDate;
	}
	public void setOriginalEffDate(Date originalEffDate)
	{
		this.originalEffDate = originalEffDate;
	}
	public String getExistingClaims()
	{
		return existingClaims;
	}
	public void setExistingClaims(String existingClaims)
	{
		this.existingClaims = existingClaims;
	}
	public String getAccountName()
	{
		return accountName;
	}
	public void setAccountName(String accountName)
	{
		this.accountName = accountName;
	}
	public String getAccountNum()
	{
		return accountNum;
	}
	public void setAccountNum(String accountNum)
	{
		this.accountNum = accountNum;
	}
	public String getFundInd()
	{
		return fundInd;
	}
	public void setFundInd(String fundInd)
	{
		this.fundInd = fundInd;
	}
	public String getProjectID()
	{
		return projectID;
	}
	public void setProjectID(String projectID)
	{
		this.projectID = projectID;
	}

	
}
