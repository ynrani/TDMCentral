/*---------------------------------------------------------------------------------------
 * Object Name: TDMNonStandardSearchDTO.Java
 * 
 * --------------------------------------------------------------------------------------
 *
 * Copyright: 2015 Capgemini Financial Services
 *---------------------------------------------------------------------------------------*/

package com.tesda.model.DTO;

import java.util.List;
import java.util.Map;

/**
 * TDMNonStandardSearchDTO data model attribute class
 */
public class TDMNonStandardSearchDTO
{
	private String memCat;
	private String ageGroup;
	private String subscRelation;
	private String provState;
	private String accountName;
	private String accountNum;
	private String planType;
	private String subscId;
	private String coverageCode;
	private String subscStatus;
	private String extClaim;
	private String searchRecordsNo;
	private boolean showHideFlag;
	private String testCaseId;
	private String testCaseName;
	private String originalEffDate;
	private String retailOnOff;
	private String onBefAfterOED;

	private String memId;
	private String memType;
	private String firstName;
	private String lastName;
	private String dob;
	private String homeZipCode;
	private String groupNum;
	private String acNum;
	private String acName;
	private String eobSuppressed;
	private String blendGroup;
	private String blendGov;
	private String blendRetail;
	private String reservedYN;
	private String applicationId;
	private String pcpMG;
	private String fundingInd;
	private String pbm;

	private List<TdmNonStandardSearchResultListDTO> tdmNonStandardSrchResultListDTOs;
	private List<TDMNonStandReservationDTO> tdmNonStandReservationDtos;
	private Map<String, List<DependentDetailsDTO>> tdmDependentDetails;

	private TDMNonStandSearchFieldsDTO nonStandSrchFldsDTO;
	private List<String> subscriberIds;
	private List<Integer> blendedPtyIDs;
	private List<String> membrIds;

	public String getRetailOnOff()
	{
		return retailOnOff;
	}

	public void setRetailOnOff(String retailOnOff)
	{
		this.retailOnOff = retailOnOff;
	}

	public String getOriginalEffDate()
	{
		return originalEffDate;
	}

	public void setOriginalEffDate(String originalEffDate)
	{
		this.originalEffDate = originalEffDate;
	}

	public String getMemId()
	{
		return memId;
	}

	public void setMemId(String memId)
	{
		this.memId = memId;
	}

	public String getMemType()
	{
		return memType;
	}

	public void setMemType(String memType)
	{
		this.memType = memType;
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

	public String getGroupNum()
	{
		return groupNum;
	}

	public void setGroupNum(String groupNum)
	{
		this.groupNum = groupNum;
	}

	public String getAcNum()
	{
		return acNum;
	}

	public void setAcNum(String acNum)
	{
		this.acNum = acNum;
	}

	public String getAcName()
	{
		return acName;
	}

	public void setAcName(String acName)
	{
		this.acName = acName;
	}

	public String getEobSuppressed()
	{
		return eobSuppressed;
	}

	public void setEobSuppressed(String eobSuppressed)
	{
		this.eobSuppressed = eobSuppressed;
	}

	public String getBlendGroup()
	{
		return blendGroup;
	}

	public void setBlendGroup(String blendGroup)
	{
		this.blendGroup = blendGroup;
	}

	public String getBlendGov()
	{
		return blendGov;
	}

	public void setBlendGov(String blendGov)
	{
		this.blendGov = blendGov;
	}

	public String getBlendRetail()
	{
		return blendRetail;
	}

	public void setBlendRetail(String blendRetail)
	{
		this.blendRetail = blendRetail;
	}

	public String getMemCat()
	{
		return memCat;
	}

	public void setMemCat(String memCat)
	{
		this.memCat = memCat;
	}

	public String getAgeGroup()
	{
		return ageGroup;
	}

	public void setAgeGroup(String ageGroup)
	{
		this.ageGroup = ageGroup;
	}

	public String getSubscRelation()
	{
		return subscRelation;
	}

	public void setSubscRelation(String subscRelation)
	{
		this.subscRelation = subscRelation;
	}

	public String getProvState()
	{
		return provState;
	}

	public void setProvState(String provState)
	{
		this.provState = provState;
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

	public String getPlanType()
	{
		return planType;
	}

	public void setPlanType(String planType)
	{
		this.planType = planType;
	}

	public String getSubscId()
	{
		return subscId;
	}

	public void setSubscId(String subscId)
	{
		this.subscId = subscId;
	}

	public String getCoverageCode()
	{
		return coverageCode;
	}

	public void setCoverageCode(String coverageCode)
	{
		this.coverageCode = coverageCode;
	}

	public String getSubscStatus()
	{
		return subscStatus;
	}

	public void setSubscStatus(String subscStatus)
	{
		this.subscStatus = subscStatus;
	}

	public String getExtClaim()
	{
		return extClaim;
	}

	public void setExtClaim(String extClaim)
	{
		this.extClaim = extClaim;
	}

	public String getSearchRecordsNo()
	{
		return searchRecordsNo;
	}

	public void setSearchRecordsNo(String searchRecordsNo)
	{
		this.searchRecordsNo = searchRecordsNo;
	}

	public List<TdmNonStandardSearchResultListDTO> getTdmNonStandardSrchResultListDTOs()
	{
		return tdmNonStandardSrchResultListDTOs;
	}

	public void setTdmNonStandardSrchResultListDTOs(
			List<TdmNonStandardSearchResultListDTO> tdmNonStandardSrchResultListDTOs)
	{
		this.tdmNonStandardSrchResultListDTOs = tdmNonStandardSrchResultListDTOs;
	}

	public boolean isShowHideFlag()
	{
		return showHideFlag;
	}

	public void setShowHideFlag(boolean showHideFlag)
	{
		this.showHideFlag = showHideFlag;
	}

	public String getTestCaseName()
	{
		return testCaseName;
	}

	public void setTestCaseName(String testCaseName)
	{
		this.testCaseName = testCaseName;
	}

	public String getTestCaseId()
	{
		return testCaseId;
	}

	public void setTestCaseId(String testCaseId)
	{
		this.testCaseId = testCaseId;
	}

	public String getReservedYN()
	{
		return reservedYN;
	}

	public void setReservedYN(String reservedYN)
	{
		this.reservedYN = reservedYN;
	}

	public String getApplicationId()
	{
		return applicationId;
	}

	public void setApplicationId(String applicationId)
	{
		this.applicationId = applicationId;
	}

	public List<TDMNonStandReservationDTO> getTdmNonStandReservationDtos()
	{
		return tdmNonStandReservationDtos;
	}

	public void setTdmNonStandReservationDtos(
			List<TDMNonStandReservationDTO> tdmNonStandReservationDtos)
	{
		this.tdmNonStandReservationDtos = tdmNonStandReservationDtos;
	}

	public TDMNonStandSearchFieldsDTO getNonStandSrchFldsDTO()
	{
		return nonStandSrchFldsDTO;
	}

	public void setNonStandSrchFldsDTO(TDMNonStandSearchFieldsDTO nonStandSrchFldsDTO)
	{
		this.nonStandSrchFldsDTO = nonStandSrchFldsDTO;
	}

	public List<String> getSubscriberIds()
	{
		return subscriberIds;
	}

	public void setSubscriberIds(List<String> subscriberIds)
	{
		this.subscriberIds = subscriberIds;
	}

	public List<Integer> getBlendedPtyIDs()
	{
		return blendedPtyIDs;
	}

	public void setBlendedPtyIDs(List<Integer> blendedPyIDs)
	{
		this.blendedPtyIDs = blendedPyIDs;
	}

	public String getOnBefAfterOED()
	{
		return onBefAfterOED;
	}

	public void setOnBefAfterOED(String onBefAfterOED)
	{
		this.onBefAfterOED = onBefAfterOED;
	}

	public Map<String, List<DependentDetailsDTO>> getTdmDependentDetails()
	{
		return tdmDependentDetails;
	}

	public void setTdmDependentDetails(Map<String, List<DependentDetailsDTO>> tdmDependentDetails)
	{
		this.tdmDependentDetails = tdmDependentDetails;
	}

	public List<String> getMembrIds()
	{
		return membrIds;
	}

	public void setMembrIds(List<String> membrIds)
	{
		this.membrIds = membrIds;
	}

	public String getPcpMG()
	{
		return pcpMG;
	}

	public void setPcpMG(String pcpMG)
	{
		this.pcpMG = pcpMG;
	}

	public String getFundingInd()
	{
		return fundingInd;
	}

	public void setFundingInd(String fundingInd)
	{
		this.fundingInd = fundingInd;
	}

	public String getPbm()
	{
		return pbm;
	}

	public void setPbm(String pbm)
	{
		this.pbm = pbm;
	}
}
