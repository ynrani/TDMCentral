package com.tdm.model.DTO;

import java.util.List;

public class TDMPolicyPropertyNewSearchDTO {

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
	private String policyWithCurBal;
	private String policyWithPayOfAmt;
	private String policyWithAutopayElig;
	private String noOfDrivers;
	private String noOfVehi;
	private String noOfViola;
	private String noOfNamedInsu;
	private String policyType;
	private String riskState;
	private String totalDue;
	private boolean showHideFlag;
	private String testCaseName;
	private String testCaseId;
	private String searchCriti;
	private int count;
	private int recordNumber;
	private String totDisa;
	private String propDeath;
	private String totalDueFlag;
	private String minDueFlag;
	private String propCurrBal;
	private String propPay;

	private String [] policyNumners;
	
	private String PolicyNumberList;
	
	private List<TdmPolicyPropertySearchResultDTO> tdmPolicyPropertySearchResultDTOList;
	private List<TDMReservedTestDataDTO> reservedTestDataList;
	public String getPropPay() {
		return propPay;
	}
	
	public void setPropPay(String propPay) {
		this.propPay = propPay;
	}

	public boolean isShowHideFlag() {
		return showHideFlag;
	}

	public void setShowHideFlag(boolean showHideFlag) {
		this.showHideFlag = showHideFlag;
	}

	public String getTotalDue() {
		return totalDue;
	}

	public void setTotalDue(String totalDue) {
		this.totalDue = totalDue;
	}

	public List<TdmPolicyPropertySearchResultDTO> getTdmPolicyPropertySearchResultDTOList() {
		return tdmPolicyPropertySearchResultDTOList;
	}

	public void setTdmPolicyPropertySearchResultDTOList(
			List<TdmPolicyPropertySearchResultDTO> tdmPolicyPropertySearchResultDTOList) {
		this.tdmPolicyPropertySearchResultDTOList = tdmPolicyPropertySearchResultDTOList;
	}

	public String getRiskState() {
		return riskState;
	}

	public void setRiskState(String riskState) {
		this.riskState = riskState;
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

	public String getPolicyWithCurBal() {
		return policyWithCurBal;
	}

	public void setPolicyWithCurBal(String policyWithCurBal) {
		this.policyWithCurBal = policyWithCurBal;
	}

	public String getPolicyWithPayOfAmt() {
		return policyWithPayOfAmt;
	}

	public void setPolicyWithPayOfAmt(String policyWithPayOfAmt) {
		this.policyWithPayOfAmt = policyWithPayOfAmt;
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

	public String getPolicyType() {
		return policyType;
	}

	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}

	public String getTestCaseName() {
		return testCaseName;
	}

	public void setTestCaseName(String testCaseName) {
		this.testCaseName = testCaseName;
	}

	public String getTestCaseId() {
		return testCaseId;
	}

	public void setTestCaseId(String testCaseId) {
		this.testCaseId = testCaseId;
	}

	public String getSearchCriti() {
		return searchCriti;
	}

	public void setSearchCriti(String searchCriti) {
		this.searchCriti = searchCriti;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<TDMReservedTestDataDTO> getReservedTestDataList() {
		return reservedTestDataList;
	}

	public void setReservedTestDataList(
			List<TDMReservedTestDataDTO> reservedTestDataList) {
		this.reservedTestDataList = reservedTestDataList;
	}
	
	public int getRecordNumber() {
		return recordNumber;
	}

	public void setRecordNumber(int recordNumber) {
		this.recordNumber = recordNumber;
	}
	public String[] getPolicyNumners() {
		return policyNumners;
	}

	public void setPolicyNumners(String[] policyNumners) {
		this.policyNumners = policyNumners;
	}

	

	

	public String getTotalDueFlag() {
		return totalDueFlag;
	}

	public void setTotalDueFlag(String propPayOff) {
		this.totalDueFlag = propPayOff;
	}

	public String getMinDueFlag() {
		return minDueFlag;
	}

	public void setMinDueFlag(String propPay) {
		this.minDueFlag = propPay;
	}

	

	public String getTotDisa() {
		return totDisa;
	}

	public void setTotDisa(String totDisa) {
		this.totDisa = totDisa;
	}

	public String getPropCurrBal() {
		return propCurrBal;
	}

	public void setPropCurrBal(String propCurrBal) {
		this.propCurrBal = propCurrBal;
	}

	public String getPropDeath() {
		return propDeath;
	}

	public void setPropDeath(String propDeath) {
		this.propDeath = propDeath;
	}

	public String getPolicyNumberList() {
		return PolicyNumberList;
	}

	public void setPolicyNumberList(String policyNumberList) {
		PolicyNumberList = policyNumberList;
	}
}
