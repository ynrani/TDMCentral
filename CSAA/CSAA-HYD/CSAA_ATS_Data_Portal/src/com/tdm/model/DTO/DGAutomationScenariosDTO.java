package com.tdm.model.DTO;

import java.util.List;

import com.tdm.model.DO.AssignedGroup;
import com.tdm.model.DO.ConsumerGroup;
import com.tdm.model.DO.TDMDataSource;
import com.tdm.model.DO.TDMEnvironment;
import com.tdm.model.DO.TDMRequestPriority;
import com.tdm.model.DO.TDMRequestStatus;
import com.tdm.model.DO.TDMSupportUser;

public class DGAutomationScenariosDTO {
	private String requestId;
	private String userName;
	private String userId;
	private String requestedBy;
	private String applicationOwner;
	private String approver;
	private String status;
	private String createdOn;
	private String statusChange;
	private List<AssignedGroup> assignedGroupList;
	private List<ConsumerGroup> consumerGroupList;
	private List<TDMDataSource> dataSourceList;
	private List<TDMSupportUser> supportUserList;
	private List<TDMEnvironment> environmentList;
	private List<TDMRequestStatus> requestStatusList;
	private List<TDMRequestPriority> requestPriorityList;
	private List<FieldListDTO>        riskStateList;
	private List<FieldListDTO>       paymentPlanList;
	private String assignedGroup;
	private String assignedToId;
	private String subject;
	private String consumerGroup;
	private String dataSource;
	private String expectedDate;
	private String priority;
	private String autoPolicyType;
	private String propPolicyType;
	private String environment;
	private String autoProductType;
	private String propertyProductType;
	private String serviceType;
	private String serviceIdentifier;
	private String consumerGroupName;
	private String priorityName;
	private List<DGAutoScenarioDTO>  dgAutoScenarioDTOList;
	private List<DGPropertyScearioDTO>  dgPropertyScenarioDTOList;
	public List<DGAutoScenarioDTO> getDgAutoScenarioDTOList() {
		return dgAutoScenarioDTOList;
	}
	public void setDgAutoScenarioDTOList(
			List<DGAutoScenarioDTO> dgAutoScenarioDTOList) {
		this.dgAutoScenarioDTOList = dgAutoScenarioDTOList;
	}
	public List<DGPropertyScearioDTO> getDgPropertyScenarioDTOList() {
		return dgPropertyScenarioDTOList;
	}
	public void setDgPropertyScenarioDTOList(
			List<DGPropertyScearioDTO> dgPropertyScenarioDTOList) {
		this.dgPropertyScenarioDTOList = dgPropertyScenarioDTOList;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((dgAutoScenarioDTOList == null) ? 0 : dgAutoScenarioDTOList
						.hashCode());
		result = prime
				* result
				+ ((dgPropertyScenarioDTOList == null) ? 0
						: dgPropertyScenarioDTOList.hashCode());
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
		DGAutomationScenariosDTO other = (DGAutomationScenariosDTO) obj;
		if (dgAutoScenarioDTOList == null) {
			if (other.dgAutoScenarioDTOList != null)
				return false;
		} else if (!dgAutoScenarioDTOList.equals(other.dgAutoScenarioDTOList))
			return false;
		if (dgPropertyScenarioDTOList == null) {
			if (other.dgPropertyScenarioDTOList != null)
				return false;
		} else if (!dgPropertyScenarioDTOList
				.equals(other.dgPropertyScenarioDTOList))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "DGAutomationScenariosDTO [dgAutoScenarioDTOList="
				+ dgAutoScenarioDTOList + ", dgPropertyScenarioDTOList="
				+ dgPropertyScenarioDTOList + ", getDgAutoScenarioDTOList()="
				+ getDgAutoScenarioDTOList()
				+ ", getDgPropertyScenarioDTOList()="
				+ getDgPropertyScenarioDTOList() + ", hashCode()=" + hashCode()
				+ ", getClass()=" + getClass() + ", toString()="
				+ super.toString() + "]";
	}
	
	
}
