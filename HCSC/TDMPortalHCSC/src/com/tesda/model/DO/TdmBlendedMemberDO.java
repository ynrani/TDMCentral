/*---------------------------------------------------------------------------------------
 * Object Name: TdmBlendedMemberDO.Java
 * --------------------------------------------------------------------------------------
 *
 * Copyright: 2015 Capgemini Financial Services
 *---------------------------------------------------------------------------------------*/

package com.tesda.model.DO;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.tesda.constants.TDMConstants;

/**
 * The persistent class for the BLENDED_MEMBER database table.
 */
@Entity
@Table(name = TDMConstants.BLENDED_MEMBER_TABLE)
@NamedQuery(name = "TdmBlendedMemberDO.findAll", query = "SELECT t FROM TdmBlendedMemberDO t")
public class TdmBlendedMemberDO implements Serializable
{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "BLND_PTY_ID")
	private int blndPtyId;

	@Column(name = "GOV_PTY_ID")
	private int govPtyId;

	@Column(name = "BLND_LGCY_SYS_NM")
	private String blndLgcySysNm;

	@Column(name = "GOVT_LGCY_SYS_NM")
	private String govtLgcySysNm;

	@Column(name = "BLNDED_TYPE")
	private String blendedType;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "blendDo", targetEntity = TdmSubscriberDetailsDO.class)
	private TdmSubscriberDetailsDO subScDetails;

	public int getBlndPtyId()
	{
		return blndPtyId;
	}

	public void setBlndPtyId(int blndPtyId)
	{
		this.blndPtyId = blndPtyId;
	}

	public int getGovPtyId()
	{
		return govPtyId;
	}

	public void setGovPtyId(int govPtyId)
	{
		this.govPtyId = govPtyId;
	}

	public String getBlndLgcySysNm()
	{
		return blndLgcySysNm;
	}

	public void setBlndLgcySysNm(String blndLgcySysNm)
	{
		this.blndLgcySysNm = blndLgcySysNm;
	}

	public String getGovtLgcySysNm()
	{
		return govtLgcySysNm;
	}

	public void setGovtLgcySysNm(String govtLgcySysNm)
	{
		this.govtLgcySysNm = govtLgcySysNm;
	}

	public String getBlendedType()
	{
		return blendedType;
	}

	public void setBlendedType(String blendedType)
	{
		this.blendedType = blendedType;
	}

	public TdmSubscriberDetailsDO getSubScDetails()
	{
		return subScDetails;
	}

	public void setSubScDetails(TdmSubscriberDetailsDO subScDetails)
	{
		this.subScDetails = subScDetails;
	}
}
