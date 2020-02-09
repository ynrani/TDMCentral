package com.tdm.model.DTO;

import java.util.Date;
import java.util.List;

public class TDMAutoPolicySearchDTO {

	private String envType;
	private String addproductType;
	private String policyState;
	private String policyStage;
	private String policyTerm;
	private String addPayReq;
	private String addRiskCovge;
	private String addPaymentType;
	private String addPaymentPlan;
	private String addDocReq;
	private String policyCovge;
	private String minimumDue;
	private String totalDue;
	private String policyWithAutopayElig;
	private String noOfDrivers;
	private String noOfVehi;
	private String noOfViola;
	private String noOfNamedInsu;
	// new properties
	private String addPolicyCovge;
	private String addPayMethod;
	private String addDocType;
	private String addYearBuilt;
	private String addConType;
	private String zipCode;
	private String leinIndi;
	private String optnlCovge;
	private String testCaseId;
	private String testCaseName;
	private String searchRecordsNo;
	private String searchCriti;
	private boolean showHideFlag;
	private boolean showHideFlagDoc;
	private boolean msgFlag;
	private int count;
	private Date createdDate;
	private Date expiryDate;
	private String userId;
	private Integer daysToExpire;
	private String productcd;
	private String codeBranch;
	private String policyType;
	private String assoPayReq;
	private String riskCovge;
	private String riskState;
	private String assoDocReq;
	private String deathBenifit;
	private String totalDisability;
	private int recordNumber;
	private String autoDeath;
	private String autoTotDisa;
	private String payOff;
	private String autoPay;
	private String currBal;
	private String riskStateValue;
	private String riskStateKey;

	private String[] policyNumbers;

	private String policyNumberList;

	public String[] getPolicyNumbers() {
		return policyNumbers;
	}

	public void setPolicyNumbers(String[] policyNumners) {
		this.policyNumbers = policyNumners;
	}

	public String getAssoDocReq() {
		return assoDocReq;
	}

	public void setAssoDocReq(String assoDocReq) {
		this.assoDocReq = assoDocReq;
	}

	public String getRiskCovge() {
		return riskCovge;
	}

	public void setRiskCovge(String riskCovge) {
		this.riskCovge = riskCovge;
	}

	public String getAssoPayReq() {
		return assoPayReq;
	}

	public void setAssoPayReq(String assoPayReq) {
		this.assoPayReq = assoPayReq;
	}

	private List<TdmPolicyPropertySearchResultDTO> tdmPolicyPropertySearchResultDTOList;
	private List<TDMReservedTestDataDTO> reservedTestDataList;
	private List<TdmPolicyAutoSearchResultDTO> tdmPolicyAutoSearchResultDTOList;

	public List<TdmPolicyAutoSearchResultDTO> getTdmPolicyAutoSearchResultDTOList() {
		return tdmPolicyAutoSearchResultDTOList;
	}

	public void setTdmPolicyAutoSearchResultDTOList(
			List<TdmPolicyAutoSearchResultDTO> tdmPolicyAutoSearchResultDTOList) {
		this.tdmPolicyAutoSearchResultDTOList = tdmPolicyAutoSearchResultDTOList;
	}

	public List<TDMReservedTestDataDTO> getReservedTestDataList() {
		return reservedTestDataList;
	}

	public void setReservedTestDataList(
			List<TDMReservedTestDataDTO> reservedTestDataList) {
		this.reservedTestDataList = reservedTestDataList;
	}

	public List<TdmPolicyPropertySearchResultDTO> getTdmPolicyPropertySearchResultDTOList() {
		return tdmPolicyPropertySearchResultDTOList;
	}

	public void setTdmPolicyPropertySearchResultDTOList(
			List<TdmPolicyPropertySearchResultDTO> tdmPolicyPropertySearchResultDTOList) {
		this.tdmPolicyPropertySearchResultDTOList = tdmPolicyPropertySearchResultDTOList;
	}

	public String getCodeBranch() {
		return codeBranch;
	}

	public void setCodeBranch(String codeBranch) {
		this.codeBranch = codeBranch;
	}

	public String getPolicyType() {
		return policyType;
	}

	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}

	public String getAddPolicyCovge() {
		return addPolicyCovge;
	}

	public void setAddPolicyCovge(String addPolicyCovge) {
		this.addPolicyCovge = addPolicyCovge;
	}

	public String getAddPayMethod() {
		return addPayMethod;
	}

	public void setAddPayMethod(String addPayMethod) {
		this.addPayMethod = addPayMethod;
	}

	public String getAddDocType() {
		return addDocType;
	}

	public void setAddDocType(String addDocType) {
		this.addDocType = addDocType;
	}

	public String getAddYearBuilt() {
		return addYearBuilt;
	}

	public void setAddYearBuilt(String addYearBuilt) {
		this.addYearBuilt = addYearBuilt;
	}

	public String getAddConType() {
		return addConType;
	}

	public void setAddConType(String addConType) {
		this.addConType = addConType;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getLeinIndi() {
		return leinIndi;
	}

	public void setLeinIndi(String leinIndi) {
		this.leinIndi = leinIndi;
	}

	public String getOptnlCovge() {
		return optnlCovge;
	}

	public void setOptnlCovge(String optnlCovge) {
		this.optnlCovge = optnlCovge;
	}

	public String getTestCaseId() {
		return testCaseId;
	}

	public void setTestCaseId(String testCaseId) {
		this.testCaseId = testCaseId;
	}

	public String getTestCaseName() {
		return testCaseName;
	}

	public void setTestCaseName(String testCaseName) {
		this.testCaseName = testCaseName;
	}

	public String getSearchRecordsNo() {
		return searchRecordsNo;
	}

	public void setSearchRecordsNo(String searchRecordsNo) {
		this.searchRecordsNo = searchRecordsNo;
	}

	public String getSearchCriti() {
		return searchCriti;
	}

	public void setSearchCriti(String searchCriti) {
		this.searchCriti = searchCriti;
	}

	public boolean isShowHideFlag() {
		return showHideFlag;
	}

	public void setShowHideFlag(boolean showHideFlag) {
		this.showHideFlag = showHideFlag;
	}

	public boolean isShowHideFlagDoc() {
		return showHideFlagDoc;
	}

	public void setShowHideFlagDoc(boolean showHideFlagDoc) {
		this.showHideFlagDoc = showHideFlagDoc;
	}

	public boolean isMsgFlag() {
		return msgFlag;
	}

	public void setMsgFlag(boolean msgFlag) {
		this.msgFlag = msgFlag;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getDaysToExpire() {
		return daysToExpire;
	}

	public void setDaysToExpire(Integer daysToExpire) {
		this.daysToExpire = daysToExpire;
	}

	public String getProductcd() {
		return productcd;
	}

	public void setProductcd(String productcd) {
		this.productcd = productcd;
	}

	public String getEnvType() {
		return envType;
	}

	public void setEnvType(String envType) {
		this.envType = envType;
	}

	public String getAddproductType() {
		return addproductType;
	}

	public void setAddproductType(String addproductType) {
		this.addproductType = addproductType;
	}

	public String getPolicyState() {
		return policyState;
	}

	public void setPolicyState(String policyState) {
		this.policyState = policyState;
	}

	public String getPolicyStage() {
		return policyStage;
	}

	public void setPolicyStage(String policyStage) {
		this.policyStage = policyStage;
	}

	public String getPolicyTerm() {
		return policyTerm;
	}

	public void setPolicyTerm(String policyTerm) {
		this.policyTerm = policyTerm;
	}

	public String getAddPayReq() {
		return addPayReq;
	}

	public void setAddPayReq(String addPayReq) {
		this.addPayReq = addPayReq;
	}

	public String getAddRiskCovge() {
		return addRiskCovge;
	}

	public void setAddRiskCovge(String addRiskCovge) {
		this.addRiskCovge = addRiskCovge;
	}

	public String getAddPaymentType() {
		return addPaymentType;
	}

	public void setAddPaymentType(String addPaymentType) {
		this.addPaymentType = addPaymentType;
	}

	public String getAddPaymentPlan() {
		return addPaymentPlan;
	}

	public void setAddPaymentPlan(String addPaymentPlan) {
		this.addPaymentPlan = addPaymentPlan;
	}

	public String getAddDocReq() {
		return addDocReq;
	}

	public void setAddDocReq(String addDocReq) {
		this.addDocReq = addDocReq;
	}

	public String getPolicyCovge() {
		return policyCovge;
	}

	public void setPolicyCovge(String policyCovge) {
		this.policyCovge = policyCovge;
	}

	public String getPolicyWithAutopayElig() {
		return policyWithAutopayElig;
	}

	public void setPolicyWithAutopayElig(String policyWithAutopayElig) {
		this.policyWithAutopayElig = policyWithAutopayElig;
	}

	public String getNoOfDrivers() {
		return noOfDrivers;
	}

	public void setNoOfDrivers(String noOfDrivers) {
		this.noOfDrivers = noOfDrivers;
	}

	public String getNoOfVehi() {
		return noOfVehi;
	}

	public void setNoOfVehi(String noOfVehi) {
		this.noOfVehi = noOfVehi;
	}

	public String getNoOfViola() {
		return noOfViola;
	}

	public void setNoOfViola(String noOfViola) {
		this.noOfViola = noOfViola;
	}

	public String getNoOfNamedInsu() {
		return noOfNamedInsu;
	}

	public void setNoOfNamedInsu(String noOfNamedInsu) {
		this.noOfNamedInsu = noOfNamedInsu;
	}

	public String getDeathBenifit() {
		return deathBenifit;
	}

	public void setDeathBenifit(String deathBenifit) {
		this.deathBenifit = deathBenifit;
	}

	public String getTotalDisability() {
		return totalDisability;
	}

	public void setTotalDisability(String totalDisability) {
		this.totalDisability = totalDisability;
	}

	public String getRiskState() {
		return riskState;
	}

	public void setRiskState(String riskState) {
		this.riskState = riskState;
	}

	public String getMinimumDue() {
		return minimumDue;
	}

	public void setMinimumDue(String minimumDue) {
		this.minimumDue = minimumDue;
	}

	public String getTotalDue() {
		return totalDue;
	}

	public void setTotalDue(String totalDue) {
		this.totalDue = totalDue;
	}

	@Override
	public String toString() {
		return "TDMAutoPolicySearchDTO [envType=" + envType
				+ ", addproductType=" + addproductType + ", policyState="
				+ policyState + ", policyStage=" + policyStage
				+ ", policyTerm=" + policyTerm + ", addPayReq=" + addPayReq
				+ ", addRiskCovge=" + addRiskCovge + ", addPaymentType="
				+ addPaymentType + ", addPaymentPlan=" + addPaymentPlan
				+ ", addDocReq=" + addDocReq + ", policyCovge=" + policyCovge
				+ ", minimumDue=" + minimumDue + ", totalDue=" + totalDue
				+ ", policyWithAutopayElig=" + policyWithAutopayElig
				+ ", noOfDrivers=" + noOfDrivers + ", noOfVehi=" + noOfVehi
				+ ", noOfViola=" + noOfViola + ", noOfNamedInsu="
				+ noOfNamedInsu + ", addPolicyCovge=" + addPolicyCovge
				+ ", addPayMethod=" + addPayMethod + ", addDocType="
				+ addDocType + ", addYearBuilt=" + addYearBuilt
				+ ", addConType=" + addConType + ", zipCode=" + zipCode
				+ ", leinIndi=" + leinIndi + ", optnlCovge=" + optnlCovge
				+ ", testCaseId=" + testCaseId + ", testCaseName="
				+ testCaseName + ", searchRecordsNo=" + searchRecordsNo
				+ ", searchCriti=" + searchCriti + ", showHideFlag="
				+ showHideFlag + ", showHideFlagDoc=" + showHideFlagDoc
				+ ", msgFlag=" + msgFlag + ", count=" + count
				+ ", createdDate=" + createdDate + ", expiryDate=" + expiryDate
				+ ", userId=" + userId + ", daysToExpire=" + daysToExpire
				+ ", productcd=" + productcd + ", codeBranch=" + codeBranch
				+ ", policyType=" + policyType + ", assoPayReq=" + assoPayReq
				+ ", riskCovge=" + riskCovge + ", riskState=" + riskState
				+ ", assoDocReq=" + assoDocReq + ", deathBenifit="
				+ deathBenifit + ", totalDisability=" + totalDisability
				+ ", tdmPolicyPropertySearchResultDTOList="
				+ tdmPolicyPropertySearchResultDTOList
				+ ", reservedTestDataList=" + reservedTestDataList
				+ ", tdmPolicyAutoSearchResultDTOList="
				+ tdmPolicyAutoSearchResultDTOList + "]";
	}

	public int getRecordNumber() {
		return recordNumber;
	}

	public void setRecordNumber(int recordNumber) {
		this.recordNumber = recordNumber;
	}

	public String getAutoDeath() {
		return autoDeath;
	}

	public void setAutoDeath(String autoDeath) {
		this.autoDeath = autoDeath;
	}

	public String getAutoTotDisa() {
		return autoTotDisa;
	}

	public void setAutoTotDisa(String autoTotDisa) {
		this.autoTotDisa = autoTotDisa;
	}

	public String getPayOff() {
		return payOff;
	}

	public void setPayOff(String payOff) {
		this.payOff = payOff;
	}

	public String getAutoPay() {
		return autoPay;
	}

	public void setAutoPay(String autoPay) {
		this.autoPay = autoPay;
	}

	public String getCurrBal() {
		return currBal;
	}

	public void setCurrBal(String currBal) {
		this.currBal = currBal;
	}

	public String getPolicyNumberList() {
		return policyNumberList;
	}

	public void setPolicyNumberList(String policyNumberList) {
		this.policyNumberList = policyNumberList;
	}

	public String getRiskStateValue() {
		return riskStateValue;
	}

	public void setRiskStateValue(String riskStateValue) {
		this.riskStateValue = riskStateValue;
	}

	public String getRiskStateKey() {
		return riskStateKey;
	}

	public void setRiskStateKey(String riskStateKey) {
		this.riskStateKey = riskStateKey;
	}

}
