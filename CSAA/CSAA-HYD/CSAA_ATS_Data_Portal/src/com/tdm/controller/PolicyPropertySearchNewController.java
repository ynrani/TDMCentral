package com.tdm.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tdm.constant.AppConstant;
import com.tdm.constant.MessageConstant;
import com.tdm.constant.UIScreenENUM;
import com.tdm.exception.BaseException;
import com.tdm.model.DTO.FieldListDTO;
import com.tdm.model.DTO.TDMPolicyPropertyNewSearchDTO;
import com.tdm.service.SearchService;

/**
 * @author mamuppar
 *
 */
@Controller
public class PolicyPropertySearchNewController {
	public static final String ENVIRONMENT = "Environment";
	@Autowired
	SearchService searchService;

	@RequestMapping(value = "/policyPropertyNew", method = RequestMethod.GET)
	public String findTestDataNonStandPost(
			@RequestParam(value = AppConstant.SEARCH, required = false) String search,
			@RequestParam(value = AppConstant.RESERVE, required = false) String reserve,
			@ModelAttribute(MessageConstant.TDM_ATS_SEARCH_NEW_DTO) TDMPolicyPropertyNewSearchDTO tdmPolicyPropertyNewSearchDTO,
			ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, List<FieldListDTO>> pageDataMap = searchService
				.getAutoSearchPageData(UIScreenENUM.PROPERTY_DATA_SEARCH
						.getPageName());

		List<FieldListDTO> environmentList = pageDataMap.get(ENVIRONMENT);
		model.addAttribute("environment", environmentList);

		List<FieldListDTO> productTypeList = pageDataMap
				.get("Property Product Type");
		model.addAttribute("producttype", productTypeList);

		List<FieldListDTO> policyStatusList = pageDataMap.get("Policy Status");
		model.addAttribute("policystatus", policyStatusList);

		List<FieldListDTO> riskStateList = pageDataMap
				.get("Property Risk State");
		model.addAttribute("riskstate", riskStateList);

		List<FieldListDTO> policyTypeList = pageDataMap.get("Policy Type");
		model.addAttribute("policytype", policyTypeList);

		List<FieldListDTO> paymentPlanList = pageDataMap
				.get("Property Payment Plan");
		model.addAttribute("paymentplan", paymentPlanList);

		List<FieldListDTO> policyLevelList = pageDataMap
				.get("Property Policy level coverage");
		model.addAttribute("policylevel", policyLevelList);

		return "propertyPolicySearchNew";
	}

	@RequestMapping(value = "/policyPropertyNew", method = RequestMethod.POST)
	public String policyAtsSearch(
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "reserve", required = false) String reserve,
			@ModelAttribute(MessageConstant.TDM_ATS_SEARCH_NEW_DTO) TDMPolicyPropertyNewSearchDTO tdmPolicyPropertyNewSearchDTO,
			ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws BaseException {

		String policyCoverage = tdmPolicyPropertyNewSearchDTO.getPolicyCovge();
		System.out.println("policyCoverage : " + policyCoverage);

		return "propertyPolicySearchNew";
	}

}
