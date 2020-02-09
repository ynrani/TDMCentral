package com.tdm.model.DTO;

import java.util.Comparator;

import org.springframework.util.StringUtils;

public class AutoPolicySearhResultsComparator implements
		Comparator<TdmPolicyAutoSearchResultDTO> {

	private String columnToSort;

	public AutoPolicySearhResultsComparator(String columnToSort) {
		this.columnToSort = columnToSort;
	}

	@Override
	public int compare(TdmPolicyAutoSearchResultDTO obj1,
			TdmPolicyAutoSearchResultDTO obj2) {

		if (StringUtils.hasText(columnToSort)) {

			if (columnToSort.equals("PolicyNumber")) {
				if (StringUtils.hasText(obj1.getPolicynumber())
						&& StringUtils.hasText(obj2.getPolicynumber())) {
					return obj1.getPolicynumber().compareTo(
							obj2.getPolicynumber());
				}
			} else if (columnToSort.equals("PolicyStage")) {

				if (StringUtils.hasText(obj1.getPolicyStage())
						&& StringUtils.hasText(obj2.getPolicyStage())) {
					return obj1.getPolicyStage().compareTo(
							obj2.getPolicyStage());
				}
			} else if (columnToSort.equals("PolicyState")) {
				if (StringUtils.hasText(obj1.getPolicyState())
						&& StringUtils.hasText(obj2.getPolicyState())) {
					return obj1.getPolicyState().compareTo(
							obj2.getPolicyState());
				}
			} else if (columnToSort.equals("PolicyEffectDt")) {
				if (StringUtils.hasText(obj1.getPolicyEffectDt())
						&& StringUtils.hasText(obj2.getPolicyEffectDt())) {
					return obj1.getPolicyEffectDt().compareTo(
							obj2.getPolicyEffectDt());
				}
			} else if (columnToSort.equals("NoOfDrivers")) {
				if (obj1.getNoOfDrivers() != null
						&& obj2.getNoOfDrivers() != null) {
					return obj1.getNoOfDrivers().compareTo(
							obj2.getNoOfDrivers());
				}
			} else if (columnToSort.equals("NoOfVehi")) {
				if (obj1.getNoOfVehi() != null && obj2.getNoOfVehi() != null) {
					return obj1.getNoOfVehi().compareTo(obj2.getNoOfVehi());
				}
			} else if (columnToSort.equals("NoOfViola")) {
				if (obj1.getNoOfViola() != null && obj2.getNoOfViola() != null) {
					return obj1.getNoOfViola().compareTo(obj2.getNoOfViola());
				}
			} else if (columnToSort.equals("TotalDue")) {
				if (obj1.getTotalAmountDue() != null
						&& obj2.getTotalAmountDue() != null) {
					return obj1.getTotalAmountDue().compareTo(
							obj2.getTotalAmountDue());
				}
			}

		}

		return 0;

	}
}
