package com.tdm.model.DTO;

public class DGPropertyScearioDTO {
	private String scenarioId;
	private String description;
	private String dgType;
	private String scenarioNo;
	private String riskState;
	private String paymentPlan;
	private String product;
	private String effectiveDate;
	private String policyType;
	private String mortgage;
	private String additionalInterest;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((dgType == null) ? 0 : dgType.hashCode());
		result = prime * result
				+ ((scenarioId == null) ? 0 : scenarioId.hashCode());
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
		DGPropertyScearioDTO other = (DGPropertyScearioDTO) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (dgType == null) {
			if (other.dgType != null)
				return false;
		} else if (!dgType.equals(other.dgType))
			return false;
		if (scenarioId == null) {
			if (other.scenarioId != null)
				return false;
		} else if (!scenarioId.equals(other.scenarioId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "DGPropertyScearioDTO [scenarioId=" + scenarioId
				+ ", description=" + description + ", dgType=" + dgType
				+ ", getScenarioId()=" + getScenarioId()
				+ ", getDescription()=" + getDescription() + ", getDgType()="
				+ getDgType() + ", hashCode()=" + hashCode() + ", getClass()="
				+ getClass() + ", toString()=" + super.toString() + "]";
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
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public String getPolicyType() {
		return policyType;
	}
	public void setPolicyType(String policyType) {
		this.policyType = policyType;
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
}
