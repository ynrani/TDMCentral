/*---------------------------------------------------------------------------------------
 * Object Name: TdmSubscriberDetailsDO.Java
 * --------------------------------------------------------------------------------------
 *
 * Copyright: 2015 Capgemini Financial Services
 *---------------------------------------------------------------------------------------*/

package com.tesda.model.DO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.tesda.constants.TDMConstants;

/**
 * The persistent class for the SUBSCRIBER_DETAILS database table.
 */
@Entity
@Table(name = TDMConstants.SUBSCRIBER_DETAILS_TABLE)
@NamedQuery(name = "TdmSubscriberDetailsDO.findAll", query = "SELECT t FROM TdmSubscriberDetailsDO t")
public class TdmSubscriberDetailsDO implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "SUBS_ID")
	private String subscriberId;

	@Column(name = "SUBS_PTY_ID")
	private int subscrPtyId;

	@Column(name = "MBR_ID")
	private int membrId;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Temporal(TemporalType.DATE)
	@Column(name = "DOB")
	private Date dob;

	@Column(name = "AGE")
	private int age;

	@Column(name = "STATE_CD")
	private String state;

	@Column(name = "ZIPCODE")
	private String zipCode;

	@Column(name = "LOC_ID")
	private int locId;

	@Column(name = "ACCOUNT_NAME")
	private String accountName;

	@Column(name = "ACCOUNT_NUMBER")
	private String accountNum;

	@Column(name = "MBR_CAT")
	private String membrCat;

	@Column(name = "LGCY_SYS_NM")
	private String lgcySysNum;

	@Temporal(TemporalType.DATE)
	@Column(name = "MBR_EFF_DT")
	private Date membrEffectiveDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "MBR_END_DT")
	private Date membrEndDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "MBR_SUB_ID_EFF_DT")
	private Date membrSubIdEffctDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "MBR_SUB_ID_END_DT")
	private Date membrSubIdEndDate;

	@Column(name = "SUBS_STATUS")
	private String subscrStatus;

	@Column(name = "GNDR_CD")
	private String gender;

	@Temporal(TemporalType.DATE)
	@Column(name = "MBR_ORIG_EFF_DT")
	private Date membrOrigEffDate;

	@Column(name = "SUBS_INLY_IND")
	private String subsInlyInd;

	@Column(name = "PRESCRIPTION_CLAIM_IND")
	private String prescClaimInd;

	@Column(name = "DENTAL_CLAIM_IND")
	private String dentalClaimInd;

	@Column(name = "MED_CLAIM_IND")
	private String medClaimInd;

	@OneToMany(mappedBy = "subScDetails", fetch = FetchType.LAZY)
	private List<TdmMcgDetailsDO> mcgDetails;

	/*
	 * @OneToMany(mappedBy = "subScDetails", fetch = FetchType.LAZY) private
	 * List<TdmDependentDetailsDO> depndDetails;
	 */

	@JoinColumn(name = "SUBS_PTY_ID", insertable = false, updatable = false)
	@OneToOne(fetch = FetchType.LAZY)
	private TdmBlendedMemberDO blendDo;

	public String getSubscriberId()
	{
		return subscriberId;
	}

	public void setSubscriberId(String subscriberId)
	{
		this.subscriberId = subscriberId;
	}

	public int getSubscrPtyId()
	{
		return subscrPtyId;
	}

	public void setSubscrPtyId(int subscrPtyId)
	{
		this.subscrPtyId = subscrPtyId;
	}

	public int getMembrId()
	{
		return membrId;
	}

	public void setMembrId(int membrId)
	{
		this.membrId = membrId;
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

	public Date getDob()
	{
		return dob;
	}

	public void setDob(Date dob)
	{
		this.dob = dob;
	}

	public int getAge()
	{
		return age;
	}

	public void setAge(int age)
	{
		this.age = age;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public String getZipCode()
	{
		return zipCode;
	}

	public void setZipCode(String zipCode)
	{
		this.zipCode = zipCode;
	}

	public int getLocId()
	{
		return locId;
	}

	public void setLocId(int locId)
	{
		this.locId = locId;
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

	public String getMembrCat()
	{
		return membrCat;
	}

	public void setMembrCat(String membrCat)
	{
		this.membrCat = membrCat;
	}

	public String getLgcySysNum()
	{
		return lgcySysNum;
	}

	public void setLgcySysNum(String lgcySysNum)
	{
		this.lgcySysNum = lgcySysNum;
	}

	public Date getMembrEffectiveDate()
	{
		return membrEffectiveDate;
	}

	public void setMembrEffectiveDate(Date membrEffectiveDate)
	{
		this.membrEffectiveDate = membrEffectiveDate;
	}

	public Date getMembrEndDate()
	{
		return membrEndDate;
	}

	public void setMembrEndDate(Date membrEndDate)
	{
		this.membrEndDate = membrEndDate;
	}

	public Date getMembrSubIdEffctDate()
	{
		return membrSubIdEffctDate;
	}

	public void setMembrSubIdEffctDate(Date membrSubIdEffctDate)
	{
		this.membrSubIdEffctDate = membrSubIdEffctDate;
	}

	public Date getMembrSubIdEndDate()
	{
		return membrSubIdEndDate;
	}

	public void setMembrSubIdEndDate(Date membrSubIdEndDate)
	{
		this.membrSubIdEndDate = membrSubIdEndDate;
	}

	public String getSubscrStatus()
	{
		return subscrStatus;
	}

	public void setSubscrStatus(String subscrStatus)
	{
		this.subscrStatus = subscrStatus;
	}

	public String getGender()
	{
		return gender;
	}

	public void setGender(String gender)
	{
		this.gender = gender;
	}

	public Date getMembrOrigEffDate()
	{
		return membrOrigEffDate;
	}

	public void setMembrOrigEffDate(Date membrOrigEffDate)
	{
		this.membrOrigEffDate = membrOrigEffDate;
	}

	public String getSubsInlyInd()
	{
		return subsInlyInd;
	}

	public void setSubsInlyInd(String subsInlyInd)
	{
		this.subsInlyInd = subsInlyInd;
	}

	public String getPrescClaimInd()
	{
		return prescClaimInd;
	}

	public void setPrescClaimInd(String prescClaimInd)
	{
		this.prescClaimInd = prescClaimInd;
	}

	public String getDentalClaimInd()
	{
		return dentalClaimInd;
	}

	public void setDentalClaimInd(String dentalClaimInd)
	{
		this.dentalClaimInd = dentalClaimInd;
	}

	public String getMedClaimInd()
	{
		return medClaimInd;
	}

	public void setMedClaimInd(String medClaimInd)
	{
		this.medClaimInd = medClaimInd;
	}

	public List<TdmMcgDetailsDO> getMcgDetails()
	{
		return mcgDetails;
	}

	public void setMcgDetails(List<TdmMcgDetailsDO> mcgDetails)
	{
		this.mcgDetails = mcgDetails;
	}

	/*
	 * public List<TdmDependentDetailsDO> getDepndDetails() { return
	 * depndDetails; } public void setDepndDetails(List<TdmDependentDetailsDO>
	 * depndDetails) { this.depndDetails = depndDetails; }
	 */

	public TdmBlendedMemberDO getBlendDo()
	{
		return blendDo;
	}

	public void setBlendDo(TdmBlendedMemberDO blendDo)
	{
		this.blendDo = blendDo;
	}

	/*
	 * @Column(name = "COVRG_AGRMT_ID") private int covrgAgrmtID;
	 * @Column(name = "DENT_COVG_IND") private String dentCovgInd;
	 * @Column(name = "MED_COVG_IND") private String medCoverInd;
	 * @Column(name = "PRESC_COVG_IND") private String prescCovgInd;
	 * @Column(name = "PROD_CD") private String prodCD;
	 * @Column(name = "RELSHP_CD") private String reltionShipCd;
	 * @Column(name = "RELSHP_NM") private String relationShipName;
	 * @Column(name = "VISION_COVG_IND") private String visionCoverId; //
	 * Columns removed
	 * @Column(name = "COVRG_ELE_CD") private String covrgELeCD;
	 * @Column(name = "COVRG_ELE_NM") private String covrgEleName;
	 * @Column(name = "LOB_CD") private String lobCD;
	 */
}
