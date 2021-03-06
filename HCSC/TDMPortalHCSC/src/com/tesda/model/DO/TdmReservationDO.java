/*---------------------------------------------------------------------------------------
 * Object Name: TdmReservationDO.Java
 * --------------------------------------------------------------------------------------
 *
 * Copyright: 2015 Capgemini Financial Services
 *---------------------------------------------------------------------------------------*/

package com.tesda.model.DO;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.tesda.constants.TDMConstants;

/**
 * The persistent class for the RESERVATION database table.
 */
@Entity
@Table(name = TDMConstants.RESERVATION_TABLE)
@NamedQuery(name = "TdmReservationDO.findAll", query = "SELECT t FROM TdmReservationDO t")
public class TdmReservationDO implements Serializable
{
	private static final long serialVersionUID = 1L;

	// @Id
	// @SequenceGenerator(name = "RESERVATION_1_ID_SEQ", sequenceName =
	// "ID_SEQ", allocationSize = 1)
	// @GeneratedValue(strategy = GenerationType.SEQUENCE, generator =
	// "RESERVATION_1_ID_SEQ")
	// @Column(name = "RECORD_ID")
	// private long recordId;
	@Id
	@Column(name = "SUB_ID")
	private String subscrId;

	@Column(name = "USER_ID")
	private String userId;

	@Temporal(TemporalType.DATE)
	@Column(name = "REC_CREATE_DATE")
	private Date recCreateDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "RESV_DATE")
	private Date reserveDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "UNRESV_DATE")
	private Date unreservDate;

	@Column(name = "TEST_CASE_ID")
	private String testCaseId;

	@Column(name = "TEST_CASE_NAME")
	private String testCaseName;

	@Column(name = "PROJECT_ID")
	private String projectId;

	@Column(name = "LOCKED")
	private String locked;

	@Column(name = "MBR_TYPE")
	private String membrType;

	@Column(name = "FIRST_NM")
	private String firstName;

	@Column(name = "LAST_NM")
	private String lastName;

	@Column(name = "DOB")
	private String dob;

	@Column(name = "HOME_ZIP_CD")
	private String homeZipCode;

	@Column(name = "ACCNT_NUM")
	private String accountNum;

	@Column(name = "ACCNT_NM")
	private String accountName;

	@Column(name = "BLENDED_CAT")
	private String blendedCat;

	@Column(name = "COVERAGE")
	private String coverage;

	@Column(name = "EXISTING_CLAIM_TYPE")
	private String extClaimType;

	@Column(name = "GRP_NUM")
	private String groupNum;

	@Column(name = "EXCHANGE_TYPE")
	private String exchangeType;

	@Column(name = "GNDR_CD")
	private String gender;

	@Temporal(TemporalType.DATE)
	@Column(name = "MBR_ORIG_EFF_DT")
	private Date memOrginalEffDt;

	@Temporal(TemporalType.DATE)
	@Column(name = "MBR_EFF_DT")
	private Date memEffDt;

	@Temporal(TemporalType.DATE)
	@Column(name = "MBR_END_DT")
	private Date memEndDt;

	@Temporal(TemporalType.DATE)
	@Column(name = "MBRSHP_COVRG_GRP_SECT_EFF_DT")
	private Date memShipCovGrpSecEffDt;

	@Temporal(TemporalType.DATE)
	@Column(name = "MBRSHP_COVRG_GRP_SECT_END_DT")
	private Date memShipCovGrpSecEndDt;

	@Column(name = "PROD_TYPE")
	private String productType;

	@Column(name = "CORP_ID")
	private String corpId;

	@Column(name = "FUNDING_IND")
	private String fundingInd;

	@Column(name = "PCP_PCPMG")
	private String pcpcpmgId;

	public String getFundingInd()
	{
		return fundingInd;
	}

	public void setFundingInd(String fundingInd)
	{
		this.fundingInd = fundingInd;
	}

	public String getPcpcpmgId()
	{
		return pcpcpmgId;
	}

	public void setPcpcpmgId(String pcpcpmgId)
	{
		this.pcpcpmgId = pcpcpmgId;
	}

	public String getExchangeType()
	{
		return exchangeType;
	}

	public void setExchangeType(String exchangeType)
	{
		this.exchangeType = exchangeType;
	}

	public String getGender()
	{
		return gender;
	}

	public void setGender(String gender)
	{
		this.gender = gender;
	}

	public Date getMemOrginalEffDt()
	{
		return memOrginalEffDt;
	}

	public void setMemOrginalEffDt(Date memOrginalEffDt)
	{
		this.memOrginalEffDt = memOrginalEffDt;
	}

	public Date getMemEffDt()
	{
		return memEffDt;
	}

	public void setMemEffDt(Date memEffDt)
	{
		this.memEffDt = memEffDt;
	}

	public Date getMemEndDt()
	{
		return memEndDt;
	}

	public void setMemEndDt(Date memEndDt)
	{
		this.memEndDt = memEndDt;
	}

	public Date getMemShipCovGrpSecEffDt()
	{
		return memShipCovGrpSecEffDt;
	}

	public void setMemShipCovGrpSecEffDt(Date memShipCovGrpSecEffDt)
	{
		this.memShipCovGrpSecEffDt = memShipCovGrpSecEffDt;
	}

	public Date getMemShipCovGrpSecEndDt()
	{
		return memShipCovGrpSecEndDt;
	}

	public void setMemShipCovGrpSecEndDt(Date memShipCovGrpSecEndDt)
	{
		this.memShipCovGrpSecEndDt = memShipCovGrpSecEndDt;
	}

	public TdmReservationDO()
	{
	}

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public Date getRecCreateDate()
	{
		return recCreateDate;
	}

	public void setRecCreateDate(Date recCreateDate)
	{
		this.recCreateDate = recCreateDate;
	}

	public Date getReserveDate()
	{
		return reserveDate;
	}

	public void setReserveDate(Date reserveDate)
	{
		this.reserveDate = reserveDate;
	}

	public Date getUnreservDate()
	{
		return unreservDate;
	}

	public void setUnreservDate(Date unreservDate)
	{
		this.unreservDate = unreservDate;
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

	public String getProjectId()
	{
		return projectId;
	}

	public void setProjectId(String projectId)
	{
		this.projectId = projectId;
	}

	public String getLocked()
	{
		return locked;
	}

	public void setLocked(String locked)
	{
		this.locked = locked;
	}

	public String getSubscrId()
	{
		return subscrId;
	}

	public void setSubscrId(String subscrId)
	{
		this.subscrId = subscrId;
	}

	public String getMembrType()
	{
		return membrType;
	}

	public void setMembrType(String membrType)
	{
		this.membrType = membrType;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getDob()
	{
		return dob;
	}

	public void setDob(String dob)
	{
		this.dob = dob;
	}

	public String getHomeZipCode()
	{
		return homeZipCode;
	}

	public void setHomeZipCode(String homeZipCode)
	{
		this.homeZipCode = homeZipCode;
	}

	public String getAccountNum()
	{
		return accountNum;
	}

	public void setAccountNum(String accountNum)
	{
		this.accountNum = accountNum;
	}

	public String getAccountName()
	{
		return accountName;
	}

	public void setAccountName(String accountName)
	{
		this.accountName = accountName;
	}

	public String getBlendedCat()
	{
		return blendedCat;
	}

	public void setBlendedCat(String blendedCat)
	{
		this.blendedCat = blendedCat;
	}

	public String getCoverage()
	{
		return coverage;
	}

	public void setCoverage(String coverage)
	{
		this.coverage = coverage;
	}

	public String getExtClaimType()
	{
		return extClaimType;
	}

	public void setExtClaimType(String extClaimType)
	{
		this.extClaimType = extClaimType;
	}

	public String getGroupNum()
	{
		return groupNum;
	}

	public void setGroupNum(String groupNum)
	{
		this.groupNum = groupNum;
	}

	public String getProductType()
	{
		return productType;
	}

	public void setProductType(String productType)
	{
		this.productType = productType;
	}

	public String getCorpId()
	{
		return corpId;
	}

	public void setCorpId(String corpId)
	{
		this.corpId = corpId;
	}

}