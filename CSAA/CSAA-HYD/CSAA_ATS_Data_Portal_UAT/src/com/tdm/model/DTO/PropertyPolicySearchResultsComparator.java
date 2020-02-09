package com.tdm.model.DTO;

import java.util.Comparator;

import org.springframework.util.StringUtils;

public class PropertyPolicySearchResultsComparator implements
		Comparator<TdmPolicyPropertySearchResultDTO> {
	private String columnToSort;

	public PropertyPolicySearchResultsComparator(String columnToSort) {
		this.columnToSort = columnToSort;
	}

	@Override
	public int compare(TdmPolicyPropertySearchResultDTO obj1,
			TdmPolicyPropertySearchResultDTO obj2) {

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
			} else if (columnToSort.equals("TotalDue")) {
				if (obj1.getTotalDue() != null && obj2.getTotalDue() != null) {
					return obj1.getTotalDue().compareTo(obj2.getTotalDue());
				}
			}

		}

		return 0;

	}
}
