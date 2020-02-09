/*---------------------------------------------------------------------------------------
 * Object Name: TdmMcgDetailsDO.Java
 * 
 * --------------------------------------------------------------------------------------
 *
 * Copyright: 2015 Capgemini Financial Services
 *---------------------------------------------------------------------------------------*/

package com.tesda.model.DO;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.tesda.constants.TDMConstants;

/**
 * The persistent class for the MCG_DETAILS database table.
 */
@Entity
@Table(name = TDMConstants.MCG_DETAILS_TABLE)
@NamedQuery(name = "TdmMcgDetailsDO.findAll", query = "SELECT t FROM TdmMcgDetailsDO t")
public class TdmMcgDetailsDO implements Serializable
{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "SUBS_ID")
	private String subscriberId;

	@Column(name = "MBR_ID")
	private int membrId;

	@Column(name = "GRP_NBR")
	private String grpNbr;

	@Column(name = "COVRG_AGRMT_ID")
	private int covrgAgrmtID;

	@Temporal(TemporalType.DATE)
	@Column(name = "MBR_SUB_ID_EFF_DT")
	private Date membrSubIdEffctDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "MBR_SUB_ID_END_DT")
	private Date membrSubIdEndDate;

	@Column(name = "SUBS_STATUS")
	private String subscrStatus;

	@Column(name = "AGRMT_STATUS")
	private String agrmtStatus;

	@Temporal(TemporalType.DATE)
	@Column(name = "MBRSHP_COVRG_GRP_SECT_EFF_DT")
	private Date membrshpCovrgGrpSectEffctDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "MBRSHP_COVRG_GRP_SECT_END_DT")
	private Date membrshpCovrgGrpSectEndDate;

	@Column(name = "LGCY_SYS_NM")
	private String lgcySysName;

	// @Column(name = "CA_COVRG_AGRMT_ID")
	// private int caCovrgAgrmtId;

	@Column(name = "PROD_CD")
	private String prodCD;

	@Column(name = "PROD_TYPE")
	private String prodType;

	@Column(name = "VISION_COVG_IND")
	private String visionCoverId;

	@Column(name = "PRESC_COVG_IND")
	private String prescCovgInd;

	@Column(name = "DENTAL_COVG_IND")
	private String dentalCovgInd;

	@Column(name = "MEDICAL_COVG_IND")
	private String medicalCovgInd;

	@Column(name = "OTHER_COVG_IND")
	private String otherCovgInd;

	@Column(name = "EXCHANGE_TYPE")
	private String exchangeType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SUBS_ID", insertable = false, updatable = false)
	private TdmSubscriberDetailsDO subScDetails;

	public String getSubscriberId()
	{
		return subscriberId;
	}

	public void setSubscriberId(String subscriberId)
	{
		this.subscriberId = subscriberId;
	}

	public int getMembrId()
	{
		return membrId;
	}

	public void setMembrId(int membrId)
	{
		this.membrId = membrId;
	}

	public String getGrpNbr()
	{
		return grpNbr;
	}

	public void setGrpNbr(String grpNbr)
	{
		this.grpNbr = grpNbr;
	}

	public int getCovrgAgrmtID()
	{
		return covrgAgrmtID;
	}

	public void setCovrgAgrmtID(int covrgAgrmtID)
	{
		this.covrgAgrmtID = covrgAgrmtID;
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

	public String getAgrmtStatus()
	{
		return agrmtStatus;
	}

	public void setAgrmtStatus(String agrmtStatus)
	{
		this.agrmtStatus = agrmtStatus;
	}

	public Date getMembrshpCovrgGrpSectEffctDate()
	{
		return membrshpCovrgGrpSectEffctDate;
	}

	public void setMembrshpCovrgGrpSectEffctDate(Date membrshpCovrgGrpSectEffctDate)
	{
		this.membrshpCovrgGrpSectEffctDate = membrshpCovrgGrpSectEffctDate;
	}

	public Date getMembrshpCovrgGrpSectEndDate()
	{
		return membrshpCovrgGrpSectEndDate;
	}

	public void setMembrshpCovrgGrpSectEndDate(Date membrshpCovrgGrpSectEndDate)
	{
		this.membrshpCovrgGrpSectEndDate = membrshpCovrgGrpSectEndDate;
	}

	public String getLgcySysName()
	{
		return lgcySysName;
	}

	public void setLgcySysName(String lgcySysName)
	{
		this.lgcySysName = lgcySysName;
	}

	/*
	 * public int getCaCovrgAgrmtId() { return caCovrgAgrmtId; } public void
	 * setCaCovrgAgrmtId(int caCovrgAgrmtId) { this.caCovrgAgrmtId =
	 * caCovrgAgrmtId; }
	 */

	public String getProdCD()
	{
		return prodCD;
	}

	public void setProdCD(String prodCD)
	{
		this.prodCD = prodCD;
	}

	public String getProdType()
	{
		return prodType;
	}

	public void setProdType(String prodType)
	{
		this.prodType = prodType;
	}

	public String getVisionCoverId()
	{
		return visionCoverId;
	}

	public void setVisionCoverId(String visionCoverId)
	{
		this.visionCoverId = visionCoverId;
	}

	public String getPrescCovgInd()
	{
		return prescCovgInd;
	}

	public void setPrescCovgInd(String prescCovgInd)
	{
		this.prescCovgInd = prescCovgInd;
	}

	public String getDentalCovgInd()
	{
		return dentalCovgInd;
	}

	public void setDentalCovgInd(String dentalCovgInd)
	{
		this.dentalCovgInd = dentalCovgInd;
	}

	public String getMedicalCovgInd()
	{
		return medicalCovgInd;
	}

	public void setMedicalCovgInd(String medicalCovgInd)
	{
		this.medicalCovgInd = medicalCovgInd;
	}

	public String getOtherCovgInd()
	{
		return otherCovgInd;
	}

	public void setOtherCovgInd(String otherCovgInd)
	{
		this.otherCovgInd = otherCovgInd;
	}

	public TdmSubscriberDetailsDO getSubScDetails()
	{
		return subScDetails;
	}

	public void setSubScDetails(TdmSubscriberDetailsDO subScDetails)
	{
		this.subScDetails = subScDetails;
	}

	public String getExchangeType()
	{
		return exchangeType;
	}

	public void setExchangeType(String exchangeType)
	{
		this.exchangeType = exchangeType;
	}
}
