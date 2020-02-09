package com.tdm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.tdm.constant.AppConstant;
import com.tdm.constant.MessageConstant;
import com.tdm.constant.UIScreenENUM;
import com.tdm.model.DO.TDMEnvironment;
import com.tdm.model.DTO.FieldListDTO;
import com.tdm.model.DTO.SubsetScenarioDTO;
import com.tdm.model.DTO.TDMDataCreationDTO;
import com.tdm.service.DataCreationRequestService;
import com.tdm.service.SearchService;
import com.tdm.service.TDMAdminService;
import com.tdm.service.TdmPolicyAutoPropSearchService;


@Controller
@Scope("session")
public class DataSubSetController {
	private static Logger logger = Logger
			.getLogger(DataSubSetController.class);
		
	
	
	private static final String ANY = "Any";
	private static final String PAYMENT_PLAN = "Auto Payment Plan";
	private static final String POLICY_LEVEL_COVERAGE = "Auto Policy Level Coverage";
	private static final String VEHICLE_LEVEL_COVERAGE = "Auto Vehicle level coverage";
	private static final String POLICY_TERM = "Policy Term";
	private static final String RISK_STATE = "Auto Risk State";
	private static final String POLICY_STATUS = "Auto Policy Status";
	private static final String PRODUCT_TYPE = "Auto Product Type";
	public static final String ENVIRONMENT = "Environment";
	
	List<FieldListDTO> autoRiskStateList = null;
	List<FieldListDTO> autoProductTypeList = null;
	List<FieldListDTO> autoPolicyStatusList = null;
	List<FieldListDTO> autoPaymentPlanList = null;
	List<FieldListDTO> autoPolicyTermList = null;
	
	
	
	
	@Autowired
	DataCreationRequestService dataRequestService;

	@Autowired
	SearchService searchService;
	
	@Resource(name = MessageConstant.SERVICE_ADMIN)
	TDMAdminService tDMAdminService;

	@Resource(name = "tdmPolicyAutoPropSearchService")
	TdmPolicyAutoPropSearchService tdmPolicyAutoSearchService;

	
//raji
/*	@RequestMapping(value = "/dataSubSet", method = RequestMethod.GET)
	public String findTestDataNonStandPost(
			@RequestParam(value = AppConstant.SEARCH, required = false) String search,
			@RequestParam(value = AppConstant.RESERVE, required = false) String reserve,
			@ModelAttribute(MessageConstant.TDM_SUBSET_DTO) TDMDataSubSetDTO subsetDTO,
			ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws JSONException {
		String userId = null;
		String userName = null;
		if (null != (String) request.getSession().getAttribute("UserId")) {
			userId = (String) request.getSession().getAttribute("UserId");
		} else {
			return null;
		}
		if (null != (String) request.getSession().getAttribute(
				AppConstant.SESSION_UNAME)) {
			userName = (String) request.getSession().getAttribute(
					AppConstant.SESSION_UNAME);
		} else {
			return null;
		}
		TDMDataSubSetDTO subsetData = dataSubSetService
				.getRequestGeneralDetails(userId);
		
		subsetDTO.setUserId(userId);
		subsetDTO.setUserName(userName);
		subsetDTO = dataSubSetService.getAllDropdownDetails();
		
		subsetDTO.setUserId(userId);
		subsetDTO.setApplicationOwner(subsetData.getApplicationOwner());
		subsetDTO.setApprover(subsetData.getApprover());
		subsetDTO.setRequestedBy(userId);
		model.addAttribute("subSetDTO", subsetDTO);
				return "dataSubSet";
	}
	*/
	
	
	//chandu
	
	@RequestMapping(value = "/dataSubSet", method = RequestMethod.GET)
	public String findTestDataNonStandPost(
			@RequestParam(value = AppConstant.SEARCH, required = false) String search,
			@RequestParam(value = AppConstant.RESERVE, required = false) String reserve,
			@ModelAttribute(MessageConstant.TDM_REQUEST_DATA) TDMDataCreationDTO subSetDTO,
			ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws JSONException {
		String userId = null;
		String userName = null;
		if (null != (String) request.getSession().getAttribute("UserId")) {
			userId = (String) request.getSession().getAttribute("UserId");
		} else {
			return null;
		}
		if (null != (String) request.getSession().getAttribute(
				AppConstant.SESSION_UNAME)) {
			userName = (String) request.getSession().getAttribute(
					AppConstant.SESSION_UNAME);
		} else {
			return null;
		}
		TDMDataCreationDTO requestData = dataRequestService
				.getRequestGeneralDetails(userId);
		requestData.setUserId(userId);
		requestData.setUserName(userName);
		subSetDTO = dataRequestService.getAllDropdownDetails();
		subSetDTO.setUserId(userId);
		subSetDTO.setApplicationOwner(requestData.getApplicationOwner());
		subSetDTO.setApprover(requestData.getApprover());
		subSetDTO.setApprover2(requestData.getApprover2());
		subSetDTO.setRequestedBy(userId);
		List<TDMEnvironment> envList=subSetDTO.getEnvironmentList();
		//Iterator itr=envList.iterator();
		Map<String,String> instanceMap=new HashMap<String,String>();
		Map<String,String> schemaMap=new HashMap<String,String>();
		
		
		/*while(itr.hasNext())
		{
			TDMEnvironment env=(TDMEnvironment) itr.next();
			
			instanceMap.put(env.getEnvironmetnId(),env.getInstanceName());
			schemaMap.put(env.getEnvironmetnId(),env.getSchemaName());
			
		}*/
		//model.addAttribute("instanceName", instanceMap);
		Gson gsonObj = new Gson();
		String envStr = gsonObj.toJson(envList);
		model.addAttribute("envObject",envStr);
		
		model.addAttribute("subSetDTO", subSetDTO);
		
		
		
		Map<String, List<FieldListDTO>> pageDataMap = searchService
				.getAutoSearchPageData(UIScreenENUM.AUTO_DATA_SEARCH
						.getPageName());
		autoProductTypeList = pageDataMap.get(PRODUCT_TYPE);
		String productTypeList = gsonObj.toJson(autoProductTypeList);
		model.addAttribute("producttype", productTypeList);

		autoPolicyStatusList = pageDataMap.get(POLICY_STATUS);
		String policyStatusList = gsonObj.toJson(autoPolicyStatusList);
		model.addAttribute("policystatus", policyStatusList);

		autoRiskStateList = pageDataMap.get(RISK_STATE);
		String riskStateList = gsonObj.toJson(autoRiskStateList);
		model.addAttribute("riskstate", riskStateList);

		autoPolicyTermList = pageDataMap.get(POLICY_TERM);

		autoPolicyTermList = putFirstObjectAsAnyInList(autoPolicyTermList);
		String policyTermList = gsonObj.toJson(autoPolicyTermList);
		model.addAttribute("policyterm", policyTermList);
		
		autoPaymentPlanList = pageDataMap.get(PAYMENT_PLAN);
		String paymentPlanList = gsonObj.toJson(autoPaymentPlanList);
		model.addAttribute("paymentplan", paymentPlanList);
		
		String userRole = null;
		userRole=(String) request.getSession().getAttribute(AppConstant.ROLE);
		model.addAttribute("userRole", userRole);
		
		
		//to get the default subset scenarios
		List <SubsetScenarioDTO> values=dataRequestService.getSubsetScenarios(userId);
		String subsetScenarios = gsonObj.toJson(values);
		model.addAttribute("subsetScenarios", subsetScenarios);
		
		return "dataSubSet";
	}
	
	private static List<FieldListDTO> putFirstObjectAsAnyInList(
			List<FieldListDTO> list) {
		int count = 0;
		if (!CollectionUtils.isEmpty(list)
				&& !list.get(0).getListValue().equalsIgnoreCase(ANY)) {
			for (FieldListDTO fieldListDTO : list) {
				if (org.springframework.util.StringUtils.hasText(fieldListDTO
						.getListValue())
						&& fieldListDTO.getListValue().equalsIgnoreCase(ANY)) {
					list.remove(count);
					list.add(0, fieldListDTO);
					break;
				}
				count++;
			}
		}
		return list;
	}
	
}