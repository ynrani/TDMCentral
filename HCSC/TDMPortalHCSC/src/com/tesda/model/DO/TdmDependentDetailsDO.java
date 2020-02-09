/*---------------------------------------------------------------------------------------
 * Object Name: TdmDependentDetailsDO.Java
 * --------------------------------------------------------------------------------------
 *
 * Copyright: 2015 Capgemini Financial Services
 *---------------------------------------------------------------------------------------*/

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

import org.hibernate.annotations.GenericGenerator;

import com.tesda.constants.TDMConstants;

/**
 * The persistent class for the DEPENDENT_DETAILS database table.
 */
@Entity
@Table(name = TDMConstants.DEPENDENT_DETAILS_TABLE)
@NamedQuery(name = "TdmDependentDetailsDO.findAll", query = "SELECT t FROM TdmDependentDetailsDO t")
public class TdmDependentDetailsDO implements Serializable
{

	private static final long serialVersionUID = 1L;

	@Column(name = "SUBS_ID")
	private String subscriberId;

	@Column(name = "SUBS_PTY_ID")
	private String subscrPtyId;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "DEPT_PTY_ID")
	private String deptPtyId;

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

	@Column(nullable = true, name = "LOC_ID")
	private Integer locId;

	@Column(name = "MBR_CAT")
	private String membrCat;

	@Column(name = "LGCY_SYS_NM")
	private String lgcySysNum;

	@Column(name = "DEPT_STATUS")
	private String deptStatus;

	@Column(name = "SUBS_STATUS")
	private String subscrStatus;

	@Temporal(TemporalType.DATE)
	@Column(name = "DEPT_EFF_DT")
	private Date deptEffectiveDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "DEPT_END_DT")
	private Date deptEffectiveEndDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "MBR_SUB_ID_EFF_DT")
	private Date mbrSubIdEffectiveDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "MBR_SUB_ID_END_DT")
	private Date mbrSubIdEndDate;

	@Column(name = "RELSHP_CD")
	private String reltionShipCd;

	@Column(name = "RELSHP_NM")
	private String relationShipName;

	@Column(name = "RELSHP")
	private String relshp;

	@Column(name = "GNDR_CD")
	private String gender;

	/*
	 * @ManyToOne(fetch = FetchType.LAZY)
	 * @JoinColumn(name = "SUBS_ID", insertable = false, updatable = false)
	 * private TdmSubscriberDetailsDO subScDetails;
	 */

	public String getSubscriberId()
	{
		return subscriberId;
	}

	public void setSubscriberId(String subscriberId)
	{
		this.subscriberId = subscriberId;
	}

	public String getSubscrPtyId()
	{
		return subscrPtyId;
	}

	public void setSubscrPtyId(String subscrPtyId)
	{
		this.subscrPtyId = subscrPtyId;
	}

	public String getDeptPtyId()
	{
		return deptPtyId;
	}

	public void setDeptPtyId(String deptPtyId)
	{
		this.deptPtyId = deptPtyId;
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

	public Integer getLocId()
	{
		return locId;
	}

	public void setLocId(int locId)
	{
		this.locId = locId;
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

	public String getDeptStatus()
	{
		return deptStatus;
	}

	public void setDeptStatus(String deptStatus)
	{
		this.deptStatus = deptStatus;
	}

	public String getSubscrStatus()
	{
		return subscrStatus;
	}

	public void setSubscrStatus(String subscrStatus)
	{
		this.subscrStatus = subscrStatus;
	}

	public Date getDeptEffectiveDate()
	{
		return deptEffectiveDate;
	}

	public void setDeptEffectiveDate(Date deptEffectiveDate)
	{
		this.deptEffectiveDate = deptEffectiveDate;
	}

	public Date getDeptEffectiveEndDate()
	{
		return deptEffectiveEndDate;
	}

	public void setDeptEffectiveEndDate(Date deptEffectiveEndDate)
	{
		this.deptEffectiveEndDate = deptEffectiveEndDate;
	}

	public Date getMbrSubIdEffectiveDate()
	{
		return mbrSubIdEffectiveDate;
	}

	public void setMbrSubIdEffectiveDate(Date mbrSubIdEffectiveDate)
	{
		this.mbrSubIdEffectiveDate = mbrSubIdEffectiveDate;
	}

	public Date getMbrSubIdEndDate()
	{
		return mbrSubIdEndDate;
	}

	public void setMbrSubIdEndDate(Date mbrSubIdEndDate)
	{
		this.mbrSubIdEndDate = mbrSubIdEndDate;
	}

	public String getReltionShipCd()
	{
		return reltionShipCd;
	}

	public void setReltionShipCd(String reltionShipCd)
	{
		this.reltionShipCd = reltionShipCd;
	}

	public String getRelationShipName()
	{
		return relationShipName;
	}

	public void setRelationShipName(String relationShipName)
	{
		this.relationShipName = relationShipName;
	}

	public String getRelshp()
	{
		return relshp;
	}

	public void setRelshp(String relshp)
	{
		this.relshp = relshp;
	}

	/*
	 * public TdmSubscriberDetailsDO getSubScDetails() { return subScDetails; }
	 * public void setSubScDetails(TdmSubscriberDetailsDO subScDetails) {
	 * this.subScDetails = subScDetails; }
	 */

	public String getGender()
	{
		return gender;
	}

	public void setGender(String gender)
	{
		this.gender = gender;
	}
}
