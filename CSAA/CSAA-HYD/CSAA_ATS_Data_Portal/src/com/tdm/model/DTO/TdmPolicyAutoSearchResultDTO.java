package com.tdm.model.DTO;

import java.util.Map;

public class TdmPolicyAutoSearchResultDTO
{

	private String policynumber;
	private String envType;
	private String policyType;
	private String policyStage;
	private String policyState;
	private String policyTerm;

	private String policyEffectDt;
	private String policyExpDt;
	private String renewalcount;

	private String policyCovge;
	private String riskCovge;
	private String productType;

	private Long noOfDrivers;
	private Long noOfVehi;
	private Long noOfNamedInsu;
	private Long noOfViola;
	private String payMethod;
	private String assoPayReq;
	private String assoDocReq;
	private String assoDocType;
	private String assoBillPlanType;

	private String docType;

	private String testCaseId;
	private String testCaseName;

	private boolean showHideFlag;
	private boolean msgFlag;
	private Integer count;

	private String reservedYN;
	private String expairDate;
	private long policyDetailId;
	private String userId;
	private String scenario;
	private Double totalAmountDue;
	
	

	private Map<String, Map<String, String>> mapDerivedFields;

	public String getEnvType() {
		return envType;
	}

	public void setEnvType(String envType) {
		this.envType = envType;
	}

	public String getPolicyType() {
		return policyType;
	}

	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}

	public String getPolicyStage() {
		return policyStage;
	}

	public void setPolicyStage(String policyStage) {
		this.policyStage = policyStage;
	}

	public String getPolicyState() {
		return policyState;
	}

	public void setPolicyState(String policyState) {
		this.policyState = policyState;
	}

	public String getPolicyTerm() {
		return policyTerm;
	}

	public void setPolicyTerm(String policyTerm) {
		this.policyTerm = policyTerm;
	}

	public String getPolicyCovge() {
		return policyCovge;
	}

	public void setPolicyCovge(String policyCovge) {
		this.policyCovge = policyCovge;
	}

	

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public String getAssoPayReq() {
		return assoPayReq;
	}

	public void setAssoPayReq(String assoPayReq) {
		this.assoPayReq = assoPayReq;
	}

	public String getAssoDocReq() {
		return assoDocReq;
	}

	public void setAssoDocReq(String assoDocReq) {
		this.assoDocReq = assoDocReq;
	}

	public String getAssoDocType() {
		return assoDocType;
	}

	public void setAssoDocType(String assoDocType) {
		this.assoDocType = assoDocType;
	}

	public String getAssoBillPlanType() {
		return assoBillPlanType;
	}

	public void setAssoBillPlanType(String assoBillPlanType) {
		this.assoBillPlanType = assoBillPlanType;
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

	public boolean isShowHideFlag() {
		return showHideFlag;
	}

	public void setShowHideFlag(boolean showHideFlag) {
		this.showHideFlag = showHideFlag;
	}

	public boolean isMsgFlag() {
		return msgFlag;
	}

	public void setMsgFlag(boolean msgFlag) {
		this.msgFlag = msgFlag;
	}

	

	public String getPolicynumber() {
		return policynumber;
	}

	public void setPolicynumber(String policynumber) {
		this.policynumber = policynumber;
	}

	public String getReservedYN() {
		return reservedYN;
	}

	public void setReservedYN(String reservedYN) {
		this.reservedYN = reservedYN;
	}

	public String getExpairDate() {
		return expairDate;
	}

	public void setExpairDate(String expairDate) {
		this.expairDate = expairDate;
	}

	public String getPolicyEffectDt() {
		return policyEffectDt;
	}

	public void setPolicyEffectDt(String policyEffectDt) {
		this.policyEffectDt = policyEffectDt;
	}

	public String getPolicyExpDt() {
		return policyExpDt;
	}

	public void setPolicyExpDt(String policyExpDt) {
		this.policyExpDt = policyExpDt;
	}

	public String getRenewalcount() {
		return renewalcount;
	}

	public void setRenewalcount(String renewalcount) {
		this.renewalcount = renewalcount;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getScenario() {
		return scenario;
	}

	public void setScenario(String scenario) {
		this.scenario = scenario;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public long getPolicyDetailId() {
		return policyDetailId;
	}

	public void setPolicyDetailId(long policyDetailId) {
		this.policyDetailId = policyDetailId;
	}

	public String getRiskCovge() {
		return riskCovge;
	}

	public void setRiskCovge(String riskCovge) {
		this.riskCovge = riskCovge;
	}

	public Map<String, Map<String, String>> getMapDerivedFields() {
		return mapDerivedFields;
	}

	public void setMapDerivedFields(Map<String, Map<String, String>> mapDerivedFields) {
		this.mapDerivedFields = mapDerivedFields;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	

	public Double getTotalAmountDue() {
		return totalAmountDue;
	}

	public void setTotalAmountDue(Double totalAmountDue) {
		this.totalAmountDue = totalAmountDue;
	}

	@Override
	public String toString() {
		return "TdmPolicyAutoSearchResultDTO [policynumber=" + policynumber
				+ ", policyStage=" + policyStage + "]";
	}

	public Long getNoOfDrivers() {
		return noOfDrivers;
	}

	public void setNoOfDrivers(Long noOfDrivers) {
		this.noOfDrivers = noOfDrivers;
	}

	public Long getNoOfVehi() {
		return noOfVehi;
	}

	public void setNoOfVehi(Long noOfVehi) {
		this.noOfVehi = noOfVehi;
	}

	public Long getNoOfNamedInsu() {
		return noOfNamedInsu;
	}

	public void setNoOfNamedInsu(Long noOfNamedInsu) {
		this.noOfNamedInsu = noOfNamedInsu;
	}

	public Long getNoOfViola() {
		return noOfViola;
	}

	public void setNoOfViola(Long noOfViola) {
		this.noOfViola = noOfViola;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}
