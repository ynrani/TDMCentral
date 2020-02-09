package com.tdm.model.DTO;

public class PropertyPolicyParamsDTO {
	
	private String scenarioNo;
	private String riskState;
	private String automationScenario;
	private String paymentPlan;
	private String noOfDrivers;
	private String noOfVehicles;
	private String noOfPolicies;
	private String additionalInformation;
	private String policyType;
	private String product;
	private String mortgage;
	private String additionalInterest;
	private String scenarioId;
	private String description;
	private String dgType;
	private String effectiveDate;
	private Boolean isActive;
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
	public String getPolicyType() {
		return policyType;
	}
	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((additionalInformation == null) ? 0 : additionalInformation
						.hashCode());
		result = prime * result
				+ ((noOfDrivers == null) ? 0 : noOfDrivers.hashCode());
		result = prime * result
				+ ((noOfPolicies == null) ? 0 : noOfPolicies.hashCode());
		result = prime * result
				+ ((noOfVehicles == null) ? 0 : noOfVehicles.hashCode());
		result = prime * result
				+ ((paymentPlan == null) ? 0 : paymentPlan.hashCode());
		result = prime * result
				+ ((policyType == null) ? 0 : policyType.hashCode());
		result = prime * result
				+ ((riskState == null) ? 0 : riskState.hashCode());
		result = prime * result
				+ ((scenarioNo == null) ? 0 : scenarioNo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PropertyPolicyParamsDTO other = (PropertyPolicyParamsDTO) obj;
		if (additionalInformation == null) {
			if (other.additionalInformation != null)
				return false;
		} else if (!additionalInformation.equals(other.additionalInformation))
			return false;
		if (noOfDrivers == null) {
			if (other.noOfDrivers != null)
				return false;
		} else if (!noOfDrivers.equals(other.noOfDrivers))
			return false;
		if (noOfPolicies == null) {
			if (other.noOfPolicies != null)
				return false;
		} else if (!noOfPolicies.equals(other.noOfPolicies))
			return false;
		if (noOfVehicles == null) {
			if (other.noOfVehicles != null)
				return false;
		} else if (!noOfVehicles.equals(other.noOfVehicles))
			return false;
		if (paymentPlan == null) {
			if (other.paymentPlan != null)
				return false;
		} else if (!paymentPlan.equals(other.paymentPlan))
			return false;
		if (policyType == null) {
			if (other.policyType != null)
				return false;
		} else if (!policyType.equals(other.policyType))
			return false;
		if (riskState == null) {
			if (other.riskState != null)
				return false;
		} else if (!riskState.equals(other.riskState))
			return false;
		if (scenarioNo == null) {
			if (other.scenarioNo != null)
				return false;
		} else if (!scenarioNo.equals(other.scenarioNo))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "PropertyPolicyParamsDTO [scenarioNo=" + scenarioNo
				+ ", riskState=" + riskState + ", paymentPlan=" + paymentPlan
				+ ", noOfDrivers=" + noOfDrivers + ", noOfVehicles="
				+ noOfVehicles + ", noOfPolicies=" + noOfPolicies
				+ ", additionalInformation=" + additionalInformation
				+ ", policyType=" + policyType + "]";
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
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getMortgage() {
		return mortgage;
	}
	public void setMortgage(String mortgage) {
		this.mortgage = mortgage;
	}
	public String getAdditionalInterest() {
		return additionalInterest;
	}
	public void setAdditionalInterest(String additionalInterest) {
		this.additionalInterest = additionalInterest;
	}
	public String getAutomationScenario() {
		return automationScenario;
	}
	public void setAutomationScenario(String automationScenario) {
		this.automationScenario = automationScenario;
	}
	public String getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	

}
