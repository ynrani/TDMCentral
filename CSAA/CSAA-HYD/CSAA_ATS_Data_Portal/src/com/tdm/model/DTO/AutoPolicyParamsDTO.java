package com.tdm.model.DTO;

public class AutoPolicyParamsDTO {
	private String scenarioNo;
	private String riskState;
	private String automationScenario;
	private String paymentPlan;
	private String noOfDrivers;
	private String noOfVehicles;
	private String noOfPolicies;
	private String additionalInformation;
	private String effectiveDate;
	private String productType;
	private String term;
	private String scenarioId;
	private String description;
	private String dgType;
	private String autoScenarioType;
	private Boolean isActive;
	public AutoPolicyParamsDTO() {
		super();

	}
	
	public AutoPolicyParamsDTO(String scenarioNo, String riskState, String autoScenario,
			String paymentPlan, String noOfDrivers, String noOfVehicles,
			String noOfPolicies, String additionalInformation) {
		super();
		this.scenarioNo = scenarioNo;
		this.automationScenario = autoScenario;
		this.riskState = riskState;
		this.paymentPlan = paymentPlan;
		this.noOfDrivers = noOfDrivers;
		this.noOfVehicles = noOfVehicles;
		this.noOfPolicies = noOfPolicies;
		this.additionalInformation = additionalInformation;
	}
	
	public String getScenarioNo() {
		return scenarioNo;
	}
	public void setScenarioNo(String scenarioNo) {
		this.scenarioNo = scenarioNo;
	}
	public String getRiskState() {
		return riskState;
	}
	
	public void setRiskState(String riskState) {
		this.riskState = riskState;
	}
	public String getPaymentPlan() {
		return paymentPlan;
	}
	public void setPaymentPlan(String paymentPlan) {
		this.paymentPlan = paymentPlan;
	}
	public String getNoOfDrivers() {
		return noOfDrivers;
	}
	public void setNoOfDrivers(String noOfDrivers) {
		this.noOfDrivers = noOfDrivers;
	}
	public String getNoOfVehicles() {
		return noOfVehicles;
	}
	public void setNoOfVehicles(String noOfVehicles) {
		this.noOfVehicles = noOfVehicles;
	}
	public String getNoOfPolicies() {
		return noOfPolicies;
	}
	public void setNoOfPolicies(String noOfPolicies) {
		this.noOfPolicies = noOfPolicies;
	}
	public String getAdditionalInformation() {
		return additionalInformation;
	}
	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}
		public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getScenarioId() {
		return scenarioId;
	}

	public void setScenarioId(String scenarioId) {
		this.scenarioId = scenarioId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDgType() {
		return dgType;
	}

	public void setDgType(String dgType) {
		this.dgType = dgType;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getAutoScenarioType() {
		return autoScenarioType;
	}

	public void setAutoScenarioType(String autoScenarioType) {
		this.autoScenarioType = autoScenarioType;
	}

	public String getAutomationScenario() {
		return automationScenario;
	}

	public void setAutomationScenario(String automationScenario) {
		this.automationScenario = automationScenario;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	
	
}
