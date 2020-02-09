/**
 * 
 */
package com.tdm.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tdm.constant.AppConstant;
import com.tdm.constant.MessageConstant;
import com.tdm.constant.UIScreenENUM;
import com.tdm.exception.BaseException;
import com.tdm.model.DTO.ActivityAuditLog;
import com.tdm.model.DTO.ActivityScreenName;
import com.tdm.model.DTO.AutoPolicySearhResultsComparator;
import com.tdm.model.DTO.FieldListDTO;
import com.tdm.model.DTO.PropertyPolicySearchResultsComparator;
import com.tdm.model.DTO.TDMAutoPolicySearchDTO;
import com.tdm.model.DTO.TDMPolicyPropertyNewSearchDTO;
import com.tdm.model.DTO.TdmPolicyAutoSearchResultDTO;
import com.tdm.model.DTO.TdmPolicyAutoSearchResultJsonDTO;
import com.tdm.model.DTO.TdmPolicyPropertySearchResultDTO;
import com.tdm.model.DTO.TdmPolicyPropertySearchResultJsonDTO;
import com.tdm.service.SearchService;
import com.tdm.service.TDMDataMiningService;
import com.tdm.service.TdmActivityAuditService;
import com.tdm.util.FastQuickSort;
import com.tdm.util.PaginationUtil;

/**
 * 
 * This class is used to load Auto and Property search pages( GET). THis class
 * is also serves the search functionlity for Auto and Property (POST).
 * 
 * @author ramakell
 *
 */
@Controller
@Scope("session")
public class TdmDataMiningController {

	final static Logger logger = Logger
			.getLogger(TdmPolicyPropertySearchController.class);

	private static final String ANY = "Any";
	private static final String PAYMENT_PLAN = "Auto Payment Plan";
	private static final String POLICY_LEVEL_COVERAGE = "Auto Policy Level Coverage";
	private static final String VEHICLE_LEVEL_COVERAGE = "Auto Vehicle level coverage";
	private static final String POLICY_TERM = "Policy Term";
	private static final String RISK_STATE = "Auto Risk State";
	private static final String POLICY_STATUS = "Auto Policy Status";
	private static final String PRODUCT_TYPE = "Auto Product Type";
	public static final String ENVIRONMENT = "Environment";

	private int noOfRecForpage = 25;
	private TDMAutoPolicySearchDTO tdmPolicyPropertySearchDTO1 = null;

	private TDMPolicyPropertyNewSearchDTO tdmPolicyPropertyNewSearchDTO1 = null;

	// Auto
	List<FieldListDTO> autoEnvironmentList = null;
	List<FieldListDTO> autoProductTypeList = null;
	List<FieldListDTO> autoPolicyStatusList = null;
	List<FieldListDTO> autoRiskStateList = null;
	List<FieldListDTO> autoPolicyTermList = null;
	List<FieldListDTO> autoVehicleLevelCoverageList = null;
	List<FieldListDTO> autoPolicyLevelCoverageList = null;
	List<FieldListDTO> autoPaymentPlanList = null;

	// Property
	List<FieldListDTO> propertyEnvironmentList = null;
	List<FieldListDTO> propertyProductTypeList = null;
	List<FieldListDTO> propertyPolicyStatusList = null;
	List<FieldListDTO> propertyRiskStateList = null;
	List<FieldListDTO> propertyPolicyTypeList = null;
	List<FieldListDTO> propertyPaymentPlanList = null;
	List<FieldListDTO> propertyPolicyLevelList = null;

	@Autowired
	SearchService searchService;

	@Resource(name = "tdmFineTestDataService")
	TDMDataMiningService tdmFineTestDataService;

	@Resource(name = "activityAuditService")
	TdmActivityAuditService activityAuditService;

	@Autowired
	private MessageSource messageSource;

	private String sortColumn;
	private String sortOrder;

	/**
	 * This method is called when the auto search page loads.
	 * 
	 * @param search
	 * @param reserve
	 * @param tdmAtsSearchDTO
	 * @param model
	 * @param request
	 * @param response
	 * @return String (view name)
	 */

	@RequestMapping(value = "/dataMinningAutoPolicySearch", method = RequestMethod.GET)
	public String loadDataMinningAutoPolicyPage(
			@RequestParam(value = AppConstant.SEARCH, required = false) String search,
			@RequestParam(value = AppConstant.RESERVE, required = false) String reserve,
			@ModelAttribute(MessageConstant.TDM_ATS_SEARCH_DTO) TDMAutoPolicySearchDTO tdmAtsSearchDTO,
			ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		String userName=null;
		if (null != (String) request.getSession().getAttribute("UserId")) {
			userName = (String) request.getSession().getAttribute("UserId");
		}
		int flag = 0;
		Map<String, List<FieldListDTO>> pageDataMap = searchService
				.getAutoSearchPageData(UIScreenENUM.AUTO_DATA_SEARCH
						.getPageName());

		getFieldData(model, pageDataMap);

		autoProductTypeList = pageDataMap.get(PRODUCT_TYPE);
		System.out.println("productTypeList  : " + autoProductTypeList);
		model.addAttribute("producttype", autoProductTypeList);

		autoPolicyStatusList = pageDataMap.get(POLICY_STATUS);
		System.out.println("policyStatusList  : " + autoPolicyStatusList);
		model.addAttribute("policystatus", autoPolicyStatusList);

		autoRiskStateList = pageDataMap.get(RISK_STATE);

		System.out.println("riskStateList  : " + autoRiskStateList);
		model.addAttribute("riskstate", autoRiskStateList);

		autoPolicyTermList = pageDataMap.get(POLICY_TERM);

		autoPolicyTermList = putFirstObjectAsAnyInList(autoPolicyTermList);
		System.out.println("policyTermList  : " + autoPolicyTermList);
		model.addAttribute("policyterm", autoPolicyTermList);

		autoVehicleLevelCoverageList = pageDataMap.get(VEHICLE_LEVEL_COVERAGE);
		System.out.println("vehicleLevelCoverageList  : "
				+ autoVehicleLevelCoverageList);
		model.addAttribute("vehiclelevel", autoVehicleLevelCoverageList);

		autoPolicyLevelCoverageList = pageDataMap.get(POLICY_LEVEL_COVERAGE);
		System.out.println("policyLevelCoverageList  : "
				+ autoPolicyLevelCoverageList);
		model.addAttribute("policylevel", autoPolicyLevelCoverageList);

		autoPaymentPlanList = pageDataMap.get(PAYMENT_PLAN);
		model.addAttribute("paymentplan", autoPaymentPlanList);
		model.addAttribute("flag", flag);

		tdmAtsSearchDTO.setAutoDeath("Any");
		tdmAtsSearchDTO.setAutoTotDisa("Any");
		tdmAtsSearchDTO.setMinimumDue("Any");
		tdmAtsSearchDTO.setAutoPay("Any");
		tdmAtsSearchDTO.setTotalDue("Any");

		model.addAttribute("tdmAtsSearchDTO", tdmAtsSearchDTO);

		// Audit logging

		activityAuditService.auditActivityLog(ActivityScreenName.DATA_MINING, ActivityAuditLog.AUTO_POLICY_TAB_CLICKED, userName, null);
		return "dataMinningAutoPolicySearch";
	}

	@RequestMapping(value = "/SearchNavigation", method = RequestMethod.GET)
	public String loadsearchNavigation(

	HttpServletRequest request, HttpServletResponse response) {

		return "SearchNavigation";
	}

	/**
	 * This method is called when the user clicks on auto search page search.
	 * 
	 * @param search
	 * @param reserve
	 * @param autoPolicySearchDTO
	 * @param model
	 * @param request
	 * @param response
	 * @return String (view name)
	 * @throws BaseException
	 */
	@RequestMapping(value = "/dataMinningAutoPolicySearch", method = RequestMethod.POST)
	public String dataMinningAutoPolicySearch(
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "reserve", required = false) String reserve,
			@RequestParam(value = "ok", required = false) String ok,
			@ModelAttribute("tdmAtsSearchDTO") TDMAutoPolicySearchDTO autoPolicySearchDTO,
			ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		int flag = 1;
		String policyNmbers = request.getParameter("policyNumners");
		String testCaseId = request.getParameter("testCaseId");
		if (org.springframework.util.StringUtils.hasText(autoPolicySearchDTO
				.getEnvType())) {
			request.getSession().setAttribute("tdmAtsSearchDTO",
					autoPolicySearchDTO);
		}

		String amendPolicyCovge = autoPolicySearchDTO.getPolicyCovge();
		String addRiskCovge = autoPolicySearchDTO.getAddRiskCovge();
		String riskState = autoPolicySearchDTO.getRiskState();
		String riskStateKey = null;
		String riskStateValue = null;
		for (FieldListDTO field : autoRiskStateList) {
			if (field.getValueCode().equalsIgnoreCase(riskState)
					|| field.getListValue().equalsIgnoreCase(riskState)) {
				riskStateValue = field.getValueCode();
				riskStateKey = field.getListValue();
				autoPolicySearchDTO.setRiskState(riskStateValue);
				break;
			}
		}

		String productTypeforAuto = autoPolicySearchDTO.getAddproductType();

		String userName = null;
		String reserveFlag = null;
		int countPerPage = 0;
		// removeDeplicates(autoPolicySearchDTO);
		String recordsToBeDisplayed = request.getParameter("ok");

		if (org.springframework.util.StringUtils.hasText(recordsToBeDisplayed)) {
			autoPolicySearchDTO.setRecordNumber(Integer
					.valueOf(recordsToBeDisplayed.trim()));
		}
		setNullIfTheValueIsAnyInAutoSearch(autoPolicySearchDTO);
		autoPolicySearchDTO
				.setSearchCriti(getSearchAutoCriteria(autoPolicySearchDTO));
		try {

			if (org.springframework.util.StringUtils.hasText(ok)) {
				autoPolicySearchDTO
						.setRecordNumber(Integer.parseInt(ok.trim()));

			} else {
				autoPolicySearchDTO.setRecordNumber(10000);
			}
			if (null != (String) request.getSession().getAttribute("UserId")) {
				userName = (String) request.getSession().getAttribute("UserId");
			}

			Long totalRecords = 0L;
			if (StringUtils.isNotEmpty(autoPolicySearchDTO.getAddYearBuilt())
					|| StringUtils.isNotEmpty(autoPolicySearchDTO
							.getAddConType())
					|| StringUtils.isNotEmpty(autoPolicySearchDTO.getZipCode())
					|| StringUtils
							.isNotEmpty(autoPolicySearchDTO.getLeinIndi())
					|| StringUtils.isNotEmpty(autoPolicySearchDTO
							.getOptnlCovge())) {
				autoPolicySearchDTO.setShowHideFlag(true);
			}
			if (StringUtils.isNotEmpty(autoPolicySearchDTO.getAddDocType())) {
				autoPolicySearchDTO.setShowHideFlagDoc(true);
			}
			model.addAttribute("result",
					getSearchAutoCriteria(autoPolicySearchDTO));
			autoPolicySearchDTO
					.setSearchCriti(getSearchAutoCriteria(autoPolicySearchDTO));
			PaginationUtil pagenation = new PaginationUtil();
			int recordsperpage = 25; // Integer.valueOf(tdmPolicyPropertySearchDTO.getSearchRecordsNo());
			noOfRecForpage = recordsperpage;
			int offSet = pagenation.getOffset(request, recordsperpage);
			if (null != search) {
				if (logger.isInfoEnabled()) {
					logger.info("property controller searching records  : ");
				}
				autoPolicySearchDTO = tdmFineTestDataService.searchAutoPolicy(
						autoPolicySearchDTO, offSet, recordsperpage, true,
						userName);

			} else if (org.springframework.util.StringUtils
					.hasText(policyNmbers)) {
				if (logger.isInfoEnabled()) {
					logger.info("property controller reserving records  : ");
				}
				if (request.getSession().getAttribute("tdmAtsSearchDTO") != null) {
					autoPolicySearchDTO = (TDMAutoPolicySearchDTO) request
							.getSession().getAttribute("tdmAtsSearchDTO");

					autoPolicySearchDTO
							.setSearchCriti(getSearchAutoCriteria(autoPolicySearchDTO));

					autoPolicySearchDTO.setPolicyNumberList(policyNmbers);
					autoPolicySearchDTO.setTestCaseId(testCaseId);

				}

				int cntRec = tdmFineTestDataService
						.saveReservedDataForAutoPolicy(autoPolicySearchDTO,
								userName, autoPolicySearchDTO.getEnvType());
				autoPolicySearchDTO = tdmFineTestDataService.searchAutoPolicy(
						autoPolicySearchDTO, offSet, recordsperpage, true,
						userName);
				reserveFlag = cntRec + " Record(s) successfully reserved.";

				model.addAttribute("reservedRecords", reserveFlag);

				model.addAttribute("display", "1");
				autoPolicySearchDTO.setTestCaseId(null);
				autoPolicySearchDTO.setTestCaseName(null);

				request.getSession().setAttribute(
						"SearchResults",
						autoPolicySearchDTO
								.getTdmPolicyAutoSearchResultDTOList());
				activityAuditService.auditActivityLog(ActivityScreenName.DATA_MINING, ActivityAuditLog.AUTO_POLICY_RESERVE, userName,reserveFlag);
			} else {
				model.addAttribute("noRecordsSelect",
						"Please select atleast one record to Reserve.");
			}
			String sortColCode = request.getParameter("sortColCode");
			String sortType = request.getParameter("sortType");

			if (org.springframework.util.StringUtils.hasText(sortColCode)
					&& org.springframework.util.StringUtils.hasText(sortType)) {

				sortColumn = sortColCode;
				sortOrder = sortType;

				if (request.getSession().getAttribute("tdmAtsSearchDTO") != null) {
					autoPolicySearchDTO = (TDMAutoPolicySearchDTO) request
							.getSession().getAttribute("tdmAtsSearchDTO");

					autoPolicySearchDTO = tdmFineTestDataService
							.searchAutoPolicy(autoPolicySearchDTO, offSet,
									recordsperpage, true, userName);

					List<TdmPolicyAutoSearchResultDTO> tdmPolicyAutoSearchResultDTOList = autoPolicySearchDTO
							.getTdmPolicyAutoSearchResultDTOList();

					request.setAttribute("sortColCode", sortColCode);
					request.setAttribute("sortType", sortType);

					if (!CollectionUtils
							.isEmpty(tdmPolicyAutoSearchResultDTOList)) {

						try {

							Collection tdmPolicyAutoSearchResultDTOList1 = getSortedAutoSearchResults(
									tdmPolicyAutoSearchResultDTOList,
									sortColCode, sortType);

							autoPolicySearchDTO
									.setTdmPolicyAutoSearchResultDTOList((List) tdmPolicyAutoSearchResultDTOList1);

							request.getSession().setAttribute("sort",
									tdmPolicyAutoSearchResultDTOList1);

						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}

			autoPolicySearchDTO = tdmFineTestDataService
					.searchAutoPolicy(autoPolicySearchDTO, offSet,
							recordsperpage, true, userName);
			if (CollectionUtils.isEmpty(autoPolicySearchDTO
					.getTdmPolicyAutoSearchResultDTOList())) {
				request.setAttribute("NO_AUTO_RECORDS",
						"No records found for the given search criteria");
			}

			if (null != autoPolicySearchDTO
					.getTdmPolicyAutoSearchResultDTOList()
					&& autoPolicySearchDTO
							.getTdmPolicyAutoSearchResultDTOList().size() >= 25) {
				totalRecords = 50L;
			} else {
				totalRecords = 25L;
			}
			// set the size to no of records variable
			if (null != autoPolicySearchDTO
					.getTdmPolicyAutoSearchResultDTOList()
					&& autoPolicySearchDTO
							.getTdmPolicyAutoSearchResultDTOList().size() > 0) {
				countPerPage = autoPolicySearchDTO
						.getTdmPolicyAutoSearchResultDTOList().size();
			}
			pagenation.paginate(totalRecords, request,
					Double.valueOf(recordsperpage), recordsperpage);
			int noOfPages = (int) Math.ceil(totalRecords.doubleValue()
					/ recordsperpage);

			

			tdmPolicyPropertySearchDTO1 = autoPolicySearchDTO;
			request.setAttribute("noOfPages", noOfPages);

			model.addAttribute("totalRecords", countPerPage);
			model.addAttribute("count", autoPolicySearchDTO.getCount());
			model.addAttribute("tdmPolicyPropertySearchResultDTOList",
					autoPolicySearchDTO
							.getTdmPolicyPropertySearchResultDTOList());
			model.addAttribute("reservedTestDataListPerUser",
					autoPolicySearchDTO.getReservedTestDataList());

			model.addAttribute("environment", autoEnvironmentList);
			model.addAttribute("producttype", autoProductTypeList);
			model.addAttribute("policystatus", autoPolicyStatusList);
			//adding RiskStateList based on product type
			ArrayList<FieldListDTO> autoRiskStateList1 = new ArrayList<FieldListDTO>(autoRiskStateList);
			if(productTypeforAuto.equals("AAA_SS"))
			{
				Iterator<FieldListDTO> iter = autoRiskStateList1.iterator();

				while (iter.hasNext()) {
					FieldListDTO fieldListDTO = iter.next();

				    if (fieldListDTO.getListValue().equals("CA - California"))
				        iter.remove();
					}
			}else if(productTypeforAuto.equals("AAA_CSA"))
			{
				autoRiskStateList1.clear();
				FieldListDTO field=new FieldListDTO();
				field.setListValue("CA - California");
				field.setValueCode("CA");
				autoRiskStateList1.add(field);
			}
			else
			{
			autoRiskStateList1=(ArrayList<FieldListDTO>) autoRiskStateList;
			}
			
			model.addAttribute("riskstate", autoRiskStateList1);
			model.addAttribute("policyterm", autoPolicyTermList);
			model.addAttribute("vehiclelevel", autoVehicleLevelCoverageList);
			model.addAttribute("policylevel", autoPolicyLevelCoverageList);
			model.addAttribute("paymentplan", autoPaymentPlanList);
			
			autoPolicySearchDTO.setAddproductType(productTypeforAuto);
			autoPolicySearchDTO.setRiskStateValue(riskStateValue);
			autoPolicySearchDTO.setRiskStateKey(riskStateKey);
			autoPolicySearchDTO.setTotalDue(request.getParameter("totalDue"));
			autoPolicySearchDTO.setMinimumDue(request.getParameter("minimumDue"));
			
			model.addAttribute("flag", flag);
			autoPolicySearchDTO.setPolicyCovge(amendPolicyCovge);
			autoPolicySearchDTO.setAddRiskCovge(addRiskCovge);
			
			
			if(autoPolicySearchDTO
					.getTdmPolicyAutoSearchResultDTOList() !=null && autoPolicySearchDTO
			.getTdmPolicyAutoSearchResultDTOList().size()>0){
				model.addAttribute("revervationSwitch", "OFF");
				model.addAttribute("searchSwitch", "ON");
			}else{
				
				autoPolicySearchDTO = tdmFineTestDataService
						.getTDMReservedAutoTestDataListPerUser(autoPolicySearchDTO);
				model.addAttribute("revervationSwitch", "ON");
				model.addAttribute("searchSwitch", "OFF");
			}
			Gson gsonObject = new Gson();
			String autoDataStr = gsonObject.toJson(autoPolicySearchDTO.getTdmPolicyAutoSearchResultDTOList());
			model.addAttribute("tdmPolicyAutoSearchDTO", autoPolicySearchDTO);
			model.addAttribute("tdmPolicyAutoSearchDTO1", autoDataStr);
		} catch (BaseException baseEx) {
			if (baseEx.getErrorCode() != null) {
				model.addAttribute("error", baseEx.getErrorCode() + " : "
						+ baseEx.getRootCause());
				return "dataMinningAutoPolicySearch";
			}
		}
		activityAuditService.auditActivityLog(ActivityScreenName.DATA_MINING, ActivityAuditLog.AUTO_POLICY_SEARCH, userName, getSearchAutoCriteria(autoPolicySearchDTO));
		return "dataMinningAutoPolicySearch";
	}

	public Collection getSortedAutoSearchResults(List orders,
			String sortColCode, String sortType) throws Exception {

		AutoPolicySearhResultsComparator autoAssignComparator = new AutoPolicySearhResultsComparator(
				sortColCode);
		FastQuickSort fastQuickSort = new FastQuickSort(autoAssignComparator);
		Collection coll = fastQuickSort.sort(orders);

		if (sortType != null && sortType.trim().equalsIgnoreCase("ASCE")) {
			return coll;

		}
		if (sortType != null && sortType.trim().equalsIgnoreCase("DESC")) {

			ArrayList allOrders = new ArrayList(coll);
			Iterator iter = coll.iterator();
			Collection desColl = new ArrayList();
			for (int i = (coll.size() - 1); i >= 0; i--) {
				TdmPolicyAutoSearchResultDTO order = (TdmPolicyAutoSearchResultDTO) allOrders
						.get(i);
				desColl.add(order);
			}
			coll = desColl;
		} else {
		}
		return coll;
	}

	public Collection getSortedPropertySearchResults(List orders,
			String sortColCode, String sortType) throws Exception {

		PropertyPolicySearchResultsComparator autoAssignComparator = new PropertyPolicySearchResultsComparator(
				sortColCode);
		FastQuickSort fastQuickSort = new FastQuickSort(autoAssignComparator);
		Collection coll = fastQuickSort.sort(orders);

		if (sortType != null && sortType.trim().equalsIgnoreCase("ASCE")) {
			return coll;

		}
		if (sortType != null && sortType.trim().equalsIgnoreCase("DESC")) {

			ArrayList allOrders = new ArrayList(coll);
			Iterator iter = coll.iterator();
			Collection desColl = new ArrayList();
			for (int i = (coll.size() - 1); i >= 0; i--) {
				TdmPolicyPropertySearchResultDTO order = (TdmPolicyPropertySearchResultDTO) allOrders
						.get(i);
				desColl.add(order);
			}
			coll = desColl;
		} else {
		}
		return coll;
	}

	/**
	 * This method is called on load of the Property search page
	 * 
	 * @param search
	 * @param reserve
	 * @param tdmPolicyPropertyNewSearchDTO
	 * @param model
	 * @param request
	 * @param response
	 * @return String (view name)
	 */
	@RequestMapping(value = "/dataMinningPropertyPolicySearch", method = RequestMethod.GET)
	public String loadDataMinningProperyPolicyPage(
			@RequestParam(value = AppConstant.SEARCH, required = false) String search,
			@RequestParam(value = AppConstant.RESERVE, required = false) String reserve,
			@ModelAttribute(MessageConstant.TDM_ATS_SEARCH_NEW_DTO) TDMPolicyPropertyNewSearchDTO tdmPolicyPropertyNewSearchDTO,
			ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		int flag = 0;
		Map<String, List<FieldListDTO>> pageDataMap = searchService
				.getAutoSearchPageData(UIScreenENUM.PROPERTY_DATA_SEARCH
						.getPageName());

		propertyEnvironmentList = pageDataMap.get(ENVIRONMENT);
		propertyEnvironmentList = putFirstObjectInEnvironmenAsPasep2(propertyEnvironmentList);
		model.addAttribute("environment", propertyEnvironmentList);

		propertyProductTypeList = pageDataMap.get("Property Product Type");
		model.addAttribute("producttype", propertyProductTypeList);

		propertyPolicyStatusList = pageDataMap.get("Property Policy Status");
		model.addAttribute("policystatus", propertyPolicyStatusList);

		propertyRiskStateList = pageDataMap.get("Property Risk State");
		model.addAttribute("riskstate", propertyRiskStateList);

		propertyPolicyTypeList = pageDataMap.get("Property Policy Type");
		model.addAttribute("policytype", propertyPolicyTypeList);

		propertyPaymentPlanList = pageDataMap.get("Property Payment Plan");
		model.addAttribute("paymentplan", propertyPaymentPlanList);

		propertyPolicyLevelList = pageDataMap.get("Property Coverage");
		model.addAttribute("policylevel", propertyPolicyLevelList);
		model.addAttribute("flag", flag);

		// tdmPolicyPropertyNewSearchDTO.setTotDisa("Any");
		tdmPolicyPropertyNewSearchDTO.setPropPay("Any");
		tdmPolicyPropertyNewSearchDTO.setTotalDueFlag("Any");
		tdmPolicyPropertyNewSearchDTO.setMinDueFlag("Any");
		tdmPolicyPropertyNewSearchDTO.setPropCurrBal("Any");

		model.addAttribute("tdmPolicyPropertyNewSearchDTO",
				tdmPolicyPropertyNewSearchDTO);
		String userName = null;
		if (null != (String) request.getSession().getAttribute("UserId")) {
			userName = (String) request.getSession().getAttribute("UserId");
		}
		activityAuditService.auditActivityLog(ActivityScreenName.DATA_MINING, ActivityAuditLog.PROPERTY_POLICY_TAB_CLICKED, userName, null);
		return "dataMiningPropertyPolicySearch";
	}

	/**
	 * This method is called when the user clicks on property search page
	 * search.
	 * 
	 * @param search
	 * @param reserve
	 * @param tdmPolicyPropertyNewSearchDTO
	 * @param model
	 * @param request
	 * @param response
	 * @return String (view name)
	 * @throws BaseException
	 */
	@RequestMapping(value = "/dataMinningPropertyPolicySearch", method = RequestMethod.POST)
	public String dataMinningPropertyPolicySearch(
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "reserve", required = false) String reserve,
			@ModelAttribute(MessageConstant.TDM_ATS_SEARCH_NEW_DTO) TDMPolicyPropertyNewSearchDTO tdmPolicyPropertyNewSearchDTO,
			ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		int flag = 1;

		String userName = null;
		String reserveFlag = null;
		int countPerPage = 0;
		String policyCovge = tdmPolicyPropertyNewSearchDTO.getPolicyCovge();
		String policyNmbers = request.getParameter("policyNumners");
		String testCaseId = request.getParameter("testCaseId");

		if (org.springframework.util.StringUtils
				.hasText(tdmPolicyPropertyNewSearchDTO.getEnvType())) {
			request.getSession().setAttribute("tdmPolicyPropertyNewSearchDTO",
					tdmPolicyPropertyNewSearchDTO);
		}

		try {

			setNullIfTheValueIsAnyInPropertySearch(tdmPolicyPropertyNewSearchDTO);
			if (null != (String) request.getSession().getAttribute("UserId")) {
				userName = (String) request.getSession().getAttribute("UserId");
			}
			if (!org.springframework.util.StringUtils.hasText(userName)) {
				userName = "ramakell";
			}
			Long totalRecords = 0L;
			tdmPolicyPropertyNewSearchDTO
					.setSearchCriti(getSearchPropertyCriteria(tdmPolicyPropertyNewSearchDTO));
			model.addAttribute("result",
					getSearchPropertyCriteria(tdmPolicyPropertyNewSearchDTO));
			PaginationUtil pagenation = new PaginationUtil();
			int recordsperpage = 25; // Integer.valueOf(tdmPolicyPropertySearchDTO.getSearchRecordsNo());
			noOfRecForpage = recordsperpage;
			int offSet = pagenation.getOffset(request, recordsperpage);

			tdmPolicyPropertyNewSearchDTO = tdmFineTestDataService
					.searchPropertyPolicy(tdmPolicyPropertyNewSearchDTO,
							offSet, recordsperpage, true, userName);

			if (CollectionUtils.isEmpty(tdmPolicyPropertyNewSearchDTO
					.getTdmPolicyPropertySearchResultDTOList())) {
				request.setAttribute("NO_AUTO_RECORDS",
						"No records found for the given search criteria");
			}
			if (null != search) {
				if (logger.isInfoEnabled()) {
					logger.info("property controller searching records  : ");
				}
				tdmPolicyPropertyNewSearchDTO = tdmFineTestDataService
						.searchPropertyPolicy(tdmPolicyPropertyNewSearchDTO,
								offSet, recordsperpage, true, userName);

			} else if (org.springframework.util.StringUtils
					.hasText(policyNmbers)) {
				if (logger.isInfoEnabled()) {
					logger.info("property controller reserving records  : ");
				}

				if (request.getSession().getAttribute(
						"tdmPolicyPropertyNewSearchDTO") != null) {
					tdmPolicyPropertyNewSearchDTO = (TDMPolicyPropertyNewSearchDTO) request
							.getSession().getAttribute(
									"tdmPolicyPropertyNewSearchDTO");
					tdmPolicyPropertyNewSearchDTO
							.setSearchCriti(getSearchPropertyCriteria(tdmPolicyPropertyNewSearchDTO));
					
					tdmPolicyPropertyNewSearchDTO.setTestCaseId(testCaseId);
					if (org.springframework.util.StringUtils
							.hasText(policyNmbers)) {
						Gson gsonObject = new Gson();
						tdmPolicyPropertyNewSearchDTO
								.setPolicyNumberList(policyNmbers);
					}
					int cntRec = tdmFineTestDataService
							.saveReservedDataForPropertyPolicy(
									tdmPolicyPropertyNewSearchDTO, userName,
									tdmPolicyPropertyNewSearchDTO.getEnvType());
					tdmPolicyPropertyNewSearchDTO = tdmFineTestDataService
							.searchPropertyPolicy(
									tdmPolicyPropertyNewSearchDTO, offSet,
									recordsperpage, true, userName);
					reserveFlag = cntRec + " Record(s) successfully reserved ";
					tdmPolicyPropertyNewSearchDTO.setTestCaseId(null);
					tdmPolicyPropertyNewSearchDTO.setTestCaseName(null);
				}
				activityAuditService.auditActivityLog(ActivityScreenName.DATA_MINING, ActivityAuditLog.PROPERTY_POLICY_RESERVE, userName,reserveFlag);
			}

			String sortColCode = request.getParameter("sortColCode");
			String sortType = request.getParameter("sortType");

			if (org.springframework.util.StringUtils.hasText(sortColCode)
					&& org.springframework.util.StringUtils.hasText(sortType)) {

				sortColumn = sortColCode;
				sortOrder = sortType;

				if (request.getSession().getAttribute(
						"tdmPolicyPropertyNewSearchDTO") != null) {
					tdmPolicyPropertyNewSearchDTO = (TDMPolicyPropertyNewSearchDTO) request
							.getSession().getAttribute(
									"tdmPolicyPropertyNewSearchDTO");

					tdmPolicyPropertyNewSearchDTO = tdmFineTestDataService
							.searchPropertyPolicy(
									tdmPolicyPropertyNewSearchDTO, offSet,
									recordsperpage, true, userName);

					List<TdmPolicyPropertySearchResultDTO> tdmPolicyPropertySearchResultDTOList = tdmPolicyPropertyNewSearchDTO
							.getTdmPolicyPropertySearchResultDTOList();

					if (!CollectionUtils
							.isEmpty(tdmPolicyPropertySearchResultDTOList)) {

						try {

							Collection tdmPolicyAutoSearchResultDTOList1 = getSortedPropertySearchResults(
									tdmPolicyPropertySearchResultDTOList,
									sortColCode, sortType);

							tdmPolicyPropertyNewSearchDTO
									.setTdmPolicyPropertySearchResultDTOList(tdmPolicyPropertySearchResultDTOList);

							request.getSession().setAttribute("sortProp",
									tdmPolicyAutoSearchResultDTOList1);

							request.setAttribute("sortColCode", sortColCode);
							request.setAttribute("sortType", sortType);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
			if (null != tdmPolicyPropertyNewSearchDTO
					.getTdmPolicyPropertySearchResultDTOList()
					&& tdmPolicyPropertyNewSearchDTO
							.getTdmPolicyPropertySearchResultDTOList().size() >= 25) {
				totalRecords = 50L;
			} else {
				totalRecords = 25L;
			}
			// set the size to no of records variable
			if (null != tdmPolicyPropertyNewSearchDTO
					.getTdmPolicyPropertySearchResultDTOList()
					&& tdmPolicyPropertyNewSearchDTO
							.getTdmPolicyPropertySearchResultDTOList().size() > 0) {
				countPerPage = tdmPolicyPropertyNewSearchDTO
						.getTdmPolicyPropertySearchResultDTOList().size();
			}

			pagenation.paginate(totalRecords, request,
					Double.valueOf(recordsperpage), recordsperpage);
			int noOfPages = (int) Math.ceil(totalRecords.doubleValue()
					/ recordsperpage);

			tdmPolicyPropertyNewSearchDTO1 = tdmPolicyPropertyNewSearchDTO;
			request.setAttribute("noOfPages", noOfPages);
			model.addAttribute("reserveFlag", reserveFlag);
			model.addAttribute("totalRecords", countPerPage);
			model.addAttribute("count",
					tdmPolicyPropertyNewSearchDTO.getCount());

			model.addAttribute("tdmPolicyPropertySearchResultDTOList",
					tdmPolicyPropertyNewSearchDTO
							.getTdmPolicyPropertySearchResultDTOList());
			/**
			 * model.addAttribute("reservedTestDataListPerUser",
			 * tdmPolicyPropertySearchDTO.getReservedTestDataList());
			 */

			
			tdmPolicyPropertyNewSearchDTO.setPolicyCovge(policyCovge);
			model.addAttribute("environment", propertyEnvironmentList);
			model.addAttribute("producttype", propertyProductTypeList);
			model.addAttribute("policystatus", propertyPolicyStatusList);
			model.addAttribute("riskstate", propertyRiskStateList);
			model.addAttribute("policytype", propertyPolicyTypeList);
			model.addAttribute("riskstate", propertyRiskStateList);
			model.addAttribute("paymentplan", propertyPaymentPlanList);
			model.addAttribute("policylevel", propertyPolicyLevelList);
			
			model.addAttribute("flag", flag);
			
			if(tdmPolicyPropertyNewSearchDTO.getTdmPolicyPropertySearchResultDTOList()!=null && tdmPolicyPropertyNewSearchDTO
							.getTdmPolicyPropertySearchResultDTOList().size()>0){
				model.addAttribute("revervationSwitch", "OFF");
				model.addAttribute("searchSwitch", "ON");
			}else{
				tdmPolicyPropertyNewSearchDTO = tdmFineTestDataService
						.getTDMReservedPropertyTestDataListPerUser(tdmPolicyPropertyNewSearchDTO);
				model.addAttribute("revervationSwitch", "ON");
				model.addAttribute("searchSwitch", "OFF");
			}
			model.addAttribute("tdmPolicyPropertyNewSearchDTO",
					tdmPolicyPropertyNewSearchDTO);
			Gson gsonObject = new Gson();
			String propPolicyTableData = gsonObject.toJson(tdmPolicyPropertyNewSearchDTO.getTdmPolicyPropertySearchResultDTOList());
			model.addAttribute("tdmPolicyPropertyNewSearchDTO1",
			propPolicyTableData);
		} catch (BaseException baseEx) {
			if (baseEx.getErrorCode() != null) {
				model.addAttribute("error", baseEx.getErrorCode() + " : "
						+ baseEx.getRootCause());
				return "dataMiningPropertyPolicySearch";
			}
		}
		activityAuditService.auditActivityLog(ActivityScreenName.DATA_MINING, ActivityAuditLog.PROPERTY_POLICY_SEARCH, userName, getSearchPropertyCriteria(tdmPolicyPropertyNewSearchDTO));
		return "dataMiningPropertyPolicySearch";
	}

	@RequestMapping(value = "/dataMinningAutoPolicySearch", method = RequestMethod.GET, params = "page")
	public String policyAutoPagiNation(
			@ModelAttribute(MessageConstant.TDM_ATS_SEARCH_DTO) TDMAutoPolicySearchDTO tdmAtsSearchDTO,
			ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {

		logger.info("In auto search controller  policyAutoPagiNation methos: ");
		String userName = null;
		int countPerPage = 0;
		try {
			tdmAtsSearchDTO = tdmPolicyPropertySearchDTO1;
			if (null != (String) request.getSession().getAttribute("UserId")) {
				userName = (String) request.getSession().getAttribute("UserId");
			}
			model.addAttribute("result", getSearchAutoCriteria(tdmAtsSearchDTO));
			Long totalRecords = 50L;
			PaginationUtil pagenation = new PaginationUtil();
			int recordsperpage = noOfRecForpage;
			int offSet = pagenation.getOffset(request, recordsperpage);
			tdmAtsSearchDTO = tdmFineTestDataService.searchAutoPolicy(
					tdmAtsSearchDTO, offSet, recordsperpage, true, userName);
			// set the size to no of records variable
			if (null != tdmAtsSearchDTO.getTdmPolicyAutoSearchResultDTOList()
					&& tdmAtsSearchDTO.getTdmPolicyAutoSearchResultDTOList()
							.size() > 0) {
				countPerPage = tdmAtsSearchDTO
						.getTdmPolicyAutoSearchResultDTOList().size();
			}
			pagenation.paginate(totalRecords, request,
					(Double.valueOf(recordsperpage)), recordsperpage);
			int noOfPages = (int) Math.ceil(totalRecords.doubleValue()
					/ recordsperpage);
			request.setAttribute("noOfPages", noOfPages);
			model.addAttribute("totalRecords", countPerPage);
			model.addAttribute("tdmPolicyAutoSearchResultDTOList",
					tdmAtsSearchDTO.getTdmPolicyAutoSearchResultDTOList());
			model.addAttribute("environment", autoEnvironmentList);
			model.addAttribute("producttype", autoProductTypeList);
			model.addAttribute("policystatus", autoPolicyStatusList);
			model.addAttribute("riskstate", autoRiskStateList);
			model.addAttribute("policyterm", autoPolicyTermList);
			model.addAttribute("vehiclelevel", autoVehicleLevelCoverageList);
			model.addAttribute("policylevel", autoPolicyLevelCoverageList);
			model.addAttribute("paymentplan", autoPaymentPlanList);

			model.addAttribute("tdmPolicyAutoSearchDTO", tdmAtsSearchDTO);

		} catch (BaseException e) {
			e.printStackTrace();
			return "dataMinningAutoPolicySearch";
		}
		logger.info("In auto search controller  policyAutoPagiNation methos return to UI: ");
		return "dataMinningAutoPolicySearch";

	}

	@RequestMapping(value = "/dataMinningPropertyPolicySearch", method = RequestMethod.GET, params = "page")
	public String policyPropertyPagiNation(
			@ModelAttribute("tdmPolicyPropertyNewSearchDTO") TDMPolicyPropertyNewSearchDTO tdmPolicyPropertySearchDTO,
			ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		if (logger.isInfoEnabled()) {
			logger.info("property controller reserving records  : ");
		}
		int countPerPage = 0;
		String userName = null;
		try {
			if (null != (String) request.getSession().getAttribute("UserId")) {
				userName = (String) request.getSession().getAttribute("UserId");
			}
			tdmPolicyPropertySearchDTO = tdmPolicyPropertyNewSearchDTO1;
			model.addAttribute("result",
					getSearchPropertyCriteria(tdmPolicyPropertySearchDTO));
			Long totalRecords = 50L;
			PaginationUtil pagenation = new PaginationUtil();
			int recordsperpage = noOfRecForpage;
			int offSet = pagenation.getOffset(request, recordsperpage);
			tdmPolicyPropertySearchDTO = tdmFineTestDataService
					.searchPropertyPolicy(tdmPolicyPropertySearchDTO, offSet,
							recordsperpage, true, userName);

			// set the size to no of records variable
			if (null != tdmPolicyPropertySearchDTO
					.getTdmPolicyPropertySearchResultDTOList()
					&& tdmPolicyPropertySearchDTO
							.getTdmPolicyPropertySearchResultDTOList().size() > 0) {
				countPerPage = tdmPolicyPropertySearchDTO
						.getTdmPolicyPropertySearchResultDTOList().size();
			}
			pagenation.paginate(totalRecords, request,
					(Double.valueOf(recordsperpage)), recordsperpage);
			int noOfPages = (int) Math.ceil(totalRecords.doubleValue()
					/ recordsperpage);
			request.setAttribute("noOfPages", noOfPages);
			model.addAttribute("totalRecords", countPerPage);
			model.addAttribute("tdmPolicyPropertySearchResultDTOList",
					tdmPolicyPropertySearchDTO
							.getTdmPolicyPropertySearchResultDTOList());
			model.addAttribute("environment", propertyEnvironmentList);
			model.addAttribute("producttype", propertyProductTypeList);
			model.addAttribute("policystatus", propertyPolicyStatusList);
			model.addAttribute("riskstate", propertyRiskStateList);
			model.addAttribute("policytype", propertyPolicyTypeList);
			model.addAttribute("riskstate", propertyRiskStateList);
			model.addAttribute("paymentplan", propertyPolicyLevelList);

			model.addAttribute("tdmPolicyPropertySearchDTO",
					tdmPolicyPropertySearchDTO);
		} catch (BaseException e) {
			e.printStackTrace();
			return "dataMiningPropertyPolicySearch";
		}
		return "dataMiningPropertyPolicySearch";
	}

	private void getFieldData(ModelMap model,
			Map<String, List<FieldListDTO>> pageDataMap) {
		autoEnvironmentList = pageDataMap.get(ENVIRONMENT);
		autoEnvironmentList = putFirstObjectInEnvironmenAsPasep2(autoEnvironmentList);
		System.out.println("environmentList  : " + autoEnvironmentList);
		model.addAttribute("environment", autoEnvironmentList);
	}

	private static List<FieldListDTO> putFirstObjectInEnvironmenAsPasep2(
			List<FieldListDTO> list) {
		int count = 0;
		if (list!= null && list.size()>0 && !list.get(0).getListValue().equalsIgnoreCase("PAS-EP2")) {
			for (FieldListDTO fieldListDTO : list) {
				if (org.springframework.util.StringUtils.hasText(fieldListDTO
						.getListValue())
						&& fieldListDTO.getListValue().equalsIgnoreCase(
								"PAS-EP2")) {
					list.remove(count);
					list.add(0, fieldListDTO);
					break;
				}
				count++;
			}
		}
		return list;
	}

	public String getSearchAutoCriteria(
			TDMAutoPolicySearchDTO tdmPolicyPropertySearchDTO) {
		if (logger.isInfoEnabled()) {
			logger.info("property controller getting search criterai  : ");
		}
		String searchCriteria = "";

		if (null != tdmPolicyPropertySearchDTO) {
			searchCriteria += "Search Result for: ";

			// environment

			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO.getEnvType())) {

				searchCriteria += messageSource.getMessage("label.env", null,
						null)
						+ " : "
						+ tdmPolicyPropertySearchDTO.getEnvType()
						+ "  ";
			}

			// Policy Status

			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
					.getPolicyStage())) {
				searchCriteria += " + "
						+ messageSource.getMessage("label.policy.stage", null,
								null) + " : "
						+ tdmPolicyPropertySearchDTO.getPolicyStage() + "  ";
			}

			// Policy Term
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
					.getPolicyTerm())) {
				searchCriteria += " + "
						+ messageSource.getMessage("label.policy.term", null,
								null) + " : "
						+ tdmPolicyPropertySearchDTO.getPolicyTerm() + "  ";
			}

			// Risk State
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
					.getPolicyState())) {
				searchCriteria += " + "
						+ messageSource.getMessage("label.policy.state", null,
								null) + " : "
						+ tdmPolicyPropertySearchDTO.getPolicyState() + "  ";
			}

			// Product Type
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
					.getAddproductType())) {
				searchCriteria += " + "
						+ messageSource.getMessage("label.policy.prodType",
								null, null) + " : "
						+ tdmPolicyPropertySearchDTO.getAddproductType() + "  ";
			}

			// Policy Level Coverage
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
					.getAddPolicyCovge())) {
				searchCriteria += " + "
						+ messageSource.getMessage("label.policy.poliCov",
								null, null) + " : "
						+ tdmPolicyPropertySearchDTO.getAddPolicyCovge() + "  ";
			}

			// Payment Plan
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
					.getAddPayMethod())) {
				searchCriteria += " + "
						+ messageSource.getMessage("label.atsdata.pymtplan",
								null, null) + " : "
						+ tdmPolicyPropertySearchDTO.getAddPaymentPlan() + "  ";
			}
			// Vehicle Level Coverage
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
					.getAddRiskCovge())) {
				searchCriteria += " + "
						+ messageSource.getMessage("label.policy.riskCov",
								null, null) + " : "
						+ tdmPolicyPropertySearchDTO.getAddRiskCovge() + "  ";
			}
			// Minimum Due
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
					.getMinimumDue())) {
				String yorn = null;
				if (tdmPolicyPropertySearchDTO.getMinimumDue()
						.equalsIgnoreCase("Y")) {
					yorn = "Yes";
				} else if (tdmPolicyPropertySearchDTO.getMinimumDue()
						.equalsIgnoreCase("N")) {
					yorn = "No";
				} else {
					yorn = ANY;
				}

				searchCriteria += " + "
						+ messageSource.getMessage("label.policy.minDue", null,
								null) + " : " + yorn + "  ";
			}
			// Total Due
			if (StringUtils
					.isNotEmpty(tdmPolicyPropertySearchDTO.getTotalDue())) {
				String yorn = null;
				if (tdmPolicyPropertySearchDTO.getTotalDue().equalsIgnoreCase(
						"Y")) {
					yorn = "Yes";
				} else if (tdmPolicyPropertySearchDTO.getTotalDue()
						.equalsIgnoreCase("N")) {
					yorn = "No";
				} else {
					yorn = ANY;
				}

				searchCriteria += " + "
						+ messageSource.getMessage("label.policy.totalDue",
								null, null) + " : " + yorn + "  ";
			}
			// PolicyWithAutopayElig
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
					.getPolicyWithAutopayElig())) {
				String yorn = null;
				if (tdmPolicyPropertySearchDTO.getPolicyWithAutopayElig()
						.equalsIgnoreCase("Y")) {
					yorn = "Yes";
				} else if (tdmPolicyPropertySearchDTO
						.getPolicyWithAutopayElig().equalsIgnoreCase("N")) {
					yorn = "No";
				} else {
					yorn = ANY;
				}

				searchCriteria += " + "
						+ messageSource.getMessage(
								"label.policy.poliWithAutpayElg", null, null)
						+ " : " + yorn + "  ";
			}
			// DeathBenifit
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
					.getDeathBenifit())) {
				String yorn = null;
				if (tdmPolicyPropertySearchDTO.getDeathBenifit()
						.equalsIgnoreCase("Y")) {
					yorn = "Yes";
				} else if (tdmPolicyPropertySearchDTO.getDeathBenifit()
						.equalsIgnoreCase("N")) {
					yorn = "No";
				} else {
					yorn = ANY;
				}

				searchCriteria += " + "
						+ messageSource.getMessage(
								"label.policy.deathBenifit=", null, null)
						+ " : " + yorn + "  ";
			}
			// Total Disability()
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
					.getTotalDisability())) {
				String yorn = null;
				if (tdmPolicyPropertySearchDTO.getTotalDisability()
						.equalsIgnoreCase("Y")) {
					yorn = "Yes";
				} else if (tdmPolicyPropertySearchDTO.getTotalDisability()
						.equalsIgnoreCase("N")) {
					yorn = "No";
				} else {
					yorn = ANY;
				}

				searchCriteria += " + "
						+ messageSource.getMessage(
								"label.policy.totalDisability", null, null)
						+ " : " + yorn + "  ";
			}
		}
		return searchCriteria;
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

	/**
	 * This method will set the value of a Property as null if the value of that
	 * property comes as Any from Auto Policy Search screen.
	 * 
	 * @param tdmAtsSearchDTO
	 */

	private void setNullIfTheValueIsAnyInAutoSearch(
			TDMAutoPolicySearchDTO tdmAtsSearchDTO) {

		if (tdmAtsSearchDTO != null) {
			if (org.springframework.util.StringUtils.hasText(tdmAtsSearchDTO
					.getAddproductType())
					&& tdmAtsSearchDTO.getAddproductType()
							.equalsIgnoreCase(ANY)) {
				tdmAtsSearchDTO.setAddproductType(null);

			}
		}
		if (org.springframework.util.StringUtils.hasText(tdmAtsSearchDTO
				.getPolicyStage())
				&& tdmAtsSearchDTO.getPolicyStage().equalsIgnoreCase(ANY)) {
			tdmAtsSearchDTO.setPolicyStage(null);
		}
		if (org.springframework.util.StringUtils.hasText(tdmAtsSearchDTO
				.getPolicyTerm())
				&& tdmAtsSearchDTO.getPolicyTerm().equalsIgnoreCase(ANY)) {
			tdmAtsSearchDTO.setPolicyTerm(null);
		}

		if (org.springframework.util.StringUtils.hasText(tdmAtsSearchDTO
				.getAddPaymentPlan())
				&& tdmAtsSearchDTO.getAddPaymentPlan().equalsIgnoreCase(ANY)) {
			tdmAtsSearchDTO.setAddPaymentPlan(null);
		}
		if (org.springframework.util.StringUtils.hasText(tdmAtsSearchDTO
				.getPolicyCovge())
				&& tdmAtsSearchDTO.getPolicyCovge().equalsIgnoreCase(ANY)) {
			tdmAtsSearchDTO.setPolicyCovge(null);
		}
		if (org.springframework.util.StringUtils.hasText(tdmAtsSearchDTO
				.getRiskCovge())
				&& tdmAtsSearchDTO.getRiskCovge().equalsIgnoreCase(ANY)) {
			tdmAtsSearchDTO.setRiskCovge(null);
		}
		if (org.springframework.util.StringUtils.hasText(tdmAtsSearchDTO
				.getAddRiskCovge())
				&& tdmAtsSearchDTO.getAddRiskCovge().equalsIgnoreCase(ANY)) {
			tdmAtsSearchDTO.setAddRiskCovge(null);
		}

		if (org.springframework.util.StringUtils.hasText(tdmAtsSearchDTO
				.getMinimumDue())
				&& tdmAtsSearchDTO.getMinimumDue().equalsIgnoreCase(ANY)) {
			tdmAtsSearchDTO.setMinimumDue(null);
		}

		if (org.springframework.util.StringUtils.hasText(tdmAtsSearchDTO
				.getTotalDue())
				&& tdmAtsSearchDTO.getTotalDue().equalsIgnoreCase(ANY)) {
			tdmAtsSearchDTO.setTotalDue(null);
		}

		if (org.springframework.util.StringUtils.hasText(tdmAtsSearchDTO
				.getRiskState())
				&& tdmAtsSearchDTO.getRiskState().equalsIgnoreCase(ANY)) {
			tdmAtsSearchDTO.setRiskState(null);
		}

		if (org.springframework.util.StringUtils.hasText(tdmAtsSearchDTO
				.getNoOfDrivers())
				&& tdmAtsSearchDTO.getNoOfDrivers().equalsIgnoreCase(ANY)) {
			tdmAtsSearchDTO.setNoOfDrivers(null);
		}
		if (org.springframework.util.StringUtils.hasText(tdmAtsSearchDTO
				.getNoOfNamedInsu())
				&& tdmAtsSearchDTO.getNoOfNamedInsu().equalsIgnoreCase(ANY)) {
			tdmAtsSearchDTO.setNoOfNamedInsu(null);
		}

		if (org.springframework.util.StringUtils.hasText(tdmAtsSearchDTO
				.getNoOfVehi())
				&& tdmAtsSearchDTO.getNoOfVehi().equalsIgnoreCase(ANY)) {
			tdmAtsSearchDTO.setNoOfVehi(null);
		}
		if (org.springframework.util.StringUtils.hasText(tdmAtsSearchDTO
				.getNoOfViola())
				&& tdmAtsSearchDTO.getNoOfViola().equalsIgnoreCase(ANY)) {
			tdmAtsSearchDTO.setNoOfViola(null);
		}

		if (org.springframework.util.StringUtils.hasText(tdmAtsSearchDTO
				.getMinimumDue())
				&& tdmAtsSearchDTO.getMinimumDue().equalsIgnoreCase(ANY)) {
			tdmAtsSearchDTO.setMinimumDue(null);
		}

		if (org.springframework.util.StringUtils.hasText(tdmAtsSearchDTO
				.getTotalDue())
				&& tdmAtsSearchDTO.getTotalDue().equalsIgnoreCase(ANY)) {
			tdmAtsSearchDTO.setTotalDue(null);
		}

		if (org.springframework.util.StringUtils.hasText(tdmAtsSearchDTO
				.getPolicyWithAutopayElig())
				&& tdmAtsSearchDTO.getPolicyWithAutopayElig().equalsIgnoreCase(
						ANY)) {
			tdmAtsSearchDTO.setPolicyWithAutopayElig(null);
		}

		if (org.springframework.util.StringUtils.hasText(tdmAtsSearchDTO
				.getDeathBenifit())
				&& tdmAtsSearchDTO.getDeathBenifit().equalsIgnoreCase(ANY)) {
			tdmAtsSearchDTO.setDeathBenifit(null);
		}

		if (org.springframework.util.StringUtils.hasText(tdmAtsSearchDTO
				.getTotalDisability())
				&& tdmAtsSearchDTO.getTotalDisability().equalsIgnoreCase(ANY)) {
			tdmAtsSearchDTO.setTotalDisability(null);
		}
	}

	/**
	 * 
	 * This method will set the value of a Property as null if the value of that
	 * property comes as Any from Property Policy Search screen.
	 * 
	 * @param tdmPolicyPropertyNewSearchDTO
	 */
	private void setNullIfTheValueIsAnyInPropertySearch(
			TDMPolicyPropertyNewSearchDTO tdmPolicyPropertyNewSearchDTO) {

		if (tdmPolicyPropertyNewSearchDTO != null) {
			if (org.springframework.util.StringUtils
					.hasText(tdmPolicyPropertyNewSearchDTO.getEnvType())
					&& tdmPolicyPropertyNewSearchDTO.getEnvType()
							.equalsIgnoreCase(ANY)) {
				tdmPolicyPropertyNewSearchDTO.setEnvType(null);

			}
		}

		if (tdmPolicyPropertyNewSearchDTO != null) {
			if (org.springframework.util.StringUtils
					.hasText(tdmPolicyPropertyNewSearchDTO.getAddproductType())
					&& tdmPolicyPropertyNewSearchDTO.getAddproductType()
							.equalsIgnoreCase(ANY)) {
				tdmPolicyPropertyNewSearchDTO.setAddproductType(null);

			}
		}

		if (tdmPolicyPropertyNewSearchDTO != null) {
			if (org.springframework.util.StringUtils
					.hasText(tdmPolicyPropertyNewSearchDTO.getPolicyState())
					&& tdmPolicyPropertyNewSearchDTO.getPolicyState()
							.equalsIgnoreCase(ANY)) {
				tdmPolicyPropertyNewSearchDTO.setPolicyState(null);

			}
		}

		if (tdmPolicyPropertyNewSearchDTO != null) {
			if (org.springframework.util.StringUtils
					.hasText(tdmPolicyPropertyNewSearchDTO.getPolicyStage())
					&& tdmPolicyPropertyNewSearchDTO.getPolicyStage()
							.equalsIgnoreCase(ANY)) {
				tdmPolicyPropertyNewSearchDTO.setPolicyStage(null);

			}
		}

		if (tdmPolicyPropertyNewSearchDTO != null) {
			if (org.springframework.util.StringUtils
					.hasText(tdmPolicyPropertyNewSearchDTO.getPolicyTerm())
					&& tdmPolicyPropertyNewSearchDTO.getPolicyTerm()
							.equalsIgnoreCase(ANY)) {
				tdmPolicyPropertyNewSearchDTO.setPolicyTerm(null);

			}
		}

		if (tdmPolicyPropertyNewSearchDTO != null) {
			if (org.springframework.util.StringUtils
					.hasText(tdmPolicyPropertyNewSearchDTO.getAddPayReq())
					&& tdmPolicyPropertyNewSearchDTO.getAddPayReq()
							.equalsIgnoreCase(ANY)) {
				tdmPolicyPropertyNewSearchDTO.setAddPayReq(null);

			}
		}

		if (tdmPolicyPropertyNewSearchDTO != null) {
			if (org.springframework.util.StringUtils
					.hasText(tdmPolicyPropertyNewSearchDTO.getAddRiskCovge())
					&& tdmPolicyPropertyNewSearchDTO.getAddRiskCovge()
							.equalsIgnoreCase(ANY)) {
				tdmPolicyPropertyNewSearchDTO.setAddRiskCovge(null);

			}
		}

		if (tdmPolicyPropertyNewSearchDTO != null) {
			if (org.springframework.util.StringUtils
					.hasText(tdmPolicyPropertyNewSearchDTO.getAddPaymentType())
					&& tdmPolicyPropertyNewSearchDTO.getAddPaymentType()
							.equalsIgnoreCase(ANY)) {
				tdmPolicyPropertyNewSearchDTO.setAddPaymentType(null);

			}
		}

		if (tdmPolicyPropertyNewSearchDTO != null) {
			if (org.springframework.util.StringUtils
					.hasText(tdmPolicyPropertyNewSearchDTO.getAddPaymentPlan())
					&& tdmPolicyPropertyNewSearchDTO.getAddPaymentPlan()
							.equalsIgnoreCase(ANY)) {
				tdmPolicyPropertyNewSearchDTO.setAddPaymentPlan(null);

			}
		}

		if (tdmPolicyPropertyNewSearchDTO != null) {
			if (org.springframework.util.StringUtils
					.hasText(tdmPolicyPropertyNewSearchDTO.getAddDocReq())
					&& tdmPolicyPropertyNewSearchDTO.getAddDocReq()
							.equalsIgnoreCase(ANY)) {
				tdmPolicyPropertyNewSearchDTO.setAddDocReq(null);

			}
		}

		if (tdmPolicyPropertyNewSearchDTO != null) {
			if (org.springframework.util.StringUtils
					.hasText(tdmPolicyPropertyNewSearchDTO.getPolicyCovge())
					&& tdmPolicyPropertyNewSearchDTO.getPolicyCovge()
							.equalsIgnoreCase(ANY)) {
				tdmPolicyPropertyNewSearchDTO.setPolicyCovge(null);

			}
		}

		if (tdmPolicyPropertyNewSearchDTO != null) {
			if (org.springframework.util.StringUtils
					.hasText(tdmPolicyPropertyNewSearchDTO
							.getPolicyWithCurBal())
					&& tdmPolicyPropertyNewSearchDTO.getPolicyWithCurBal()
							.equalsIgnoreCase(ANY)) {
				tdmPolicyPropertyNewSearchDTO.setPolicyWithCurBal(null);

			}
		}

		if (tdmPolicyPropertyNewSearchDTO != null) {
			if (org.springframework.util.StringUtils
					.hasText(tdmPolicyPropertyNewSearchDTO
							.getPolicyWithPayOfAmt())
					&& tdmPolicyPropertyNewSearchDTO.getPolicyWithPayOfAmt()
							.equalsIgnoreCase(ANY)) {
				tdmPolicyPropertyNewSearchDTO.setPolicyWithPayOfAmt(null);

			}
		}

		if (tdmPolicyPropertyNewSearchDTO != null) {
			if (org.springframework.util.StringUtils
					.hasText(tdmPolicyPropertyNewSearchDTO
							.getPolicyWithAutopayElig())
					&& tdmPolicyPropertyNewSearchDTO.getPolicyWithAutopayElig()
							.equalsIgnoreCase(ANY)) {
				tdmPolicyPropertyNewSearchDTO.setPolicyWithAutopayElig(null);

			}
		}

		if (tdmPolicyPropertyNewSearchDTO != null) {
			if (org.springframework.util.StringUtils
					.hasText(tdmPolicyPropertyNewSearchDTO.getPolicyType())
					&& tdmPolicyPropertyNewSearchDTO.getPolicyType()
							.equalsIgnoreCase(ANY)) {
				tdmPolicyPropertyNewSearchDTO.setPolicyType(null);

			}
		}

		if (tdmPolicyPropertyNewSearchDTO != null) {
			if (org.springframework.util.StringUtils
					.hasText(tdmPolicyPropertyNewSearchDTO.getRiskState())
					&& tdmPolicyPropertyNewSearchDTO.getRiskState()
							.equalsIgnoreCase(ANY)) {
				tdmPolicyPropertyNewSearchDTO.setRiskState(null);

			}
		}

		if (tdmPolicyPropertyNewSearchDTO != null) {
			if (org.springframework.util.StringUtils
					.hasText(tdmPolicyPropertyNewSearchDTO.getTotalDue())
					&& tdmPolicyPropertyNewSearchDTO.getTotalDue()
							.equalsIgnoreCase(ANY)) {
				tdmPolicyPropertyNewSearchDTO.setTotalDue(null);

			}
		}

	}

	public String getSearchPropertyCriteria(
			TDMPolicyPropertyNewSearchDTO tdmPolicyPropertySearchDTO) {
		if (logger.isInfoEnabled()) {
			logger.info("property controller getting search criterai  : ");
		}
		String searchCriteria = "";

		if (null != tdmPolicyPropertySearchDTO) {
			searchCriteria += "Search Result for: ";
			// Environment
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO.getEnvType())) {

				searchCriteria += messageSource.getMessage("label.env", null,
						null)
						+ " : "
						+ tdmPolicyPropertySearchDTO.getEnvType()
						+ "  ";
			}
			// Policy Status

			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
					.getPolicyStage())) {
				searchCriteria += " + "
						+ messageSource.getMessage("label.policy.stage", null,
								null) + " : "
						+ tdmPolicyPropertySearchDTO.getPolicyStage() + "  ";
			}

			// Policy Term
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
					.getPolicyTerm())) {
				searchCriteria += " + "
						+ messageSource.getMessage("label.policy.term", null,
								null) + " : "
						+ tdmPolicyPropertySearchDTO.getPolicyTerm() + "  ";
			}
			// Risk State
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
					.getPolicyState())) {
				searchCriteria += " + "
						+ messageSource.getMessage("label.policy.state", null,
								null) + " : "
						+ tdmPolicyPropertySearchDTO.getPolicyState() + "  ";
			}
			// Product Type
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
					.getAddproductType())) {
				searchCriteria += " + "
						+ messageSource.getMessage("label.policy.prodType",
								null, null) + " : "
						+ tdmPolicyPropertySearchDTO.getAddproductType() + "  ";
			}

			// Policy type
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
					.getPolicyType())) {
				searchCriteria += " + "
						+ messageSource.getMessage("label.policy.type", null,
								null) + " : "
						+ tdmPolicyPropertySearchDTO.getPolicyType() + "  ";
			}
			// Policy Coverage
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
					.getPolicyCovge())) {
				searchCriteria += " + "
						+ messageSource.getMessage("label.policy.coverage",
								null, null) + " : "
						+ tdmPolicyPropertySearchDTO.getPolicyCovge() + "  ";
			}
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
					.getAddPaymentPlan())) {
				searchCriteria += " + "
						+ messageSource.getMessage("label.atsdata.pymtplan",
								null, null) + " : "
						+ tdmPolicyPropertySearchDTO.getAddPaymentPlan() + "  ";
			}

			// Minimum Due
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
					.getPolicyWithCurBal())) {
				String yorn = null;
				if (tdmPolicyPropertySearchDTO.getPolicyWithCurBal()
						.equalsIgnoreCase("Y")) {
					yorn = "Yes";
				} else if (tdmPolicyPropertySearchDTO.getPolicyWithCurBal()
						.equalsIgnoreCase("N")) {
					yorn = "No";
				} else {
					yorn = "Any";
				}

				searchCriteria += " + "
						+ messageSource.getMessage(
								"label.policy.poliWithMinDue", null, null)
						+ " : " + yorn + "  ";
			}
			// Total Due
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
					.getPolicyWithAutopayElig())) {
				String yorn = null;
				if (tdmPolicyPropertySearchDTO.getPolicyWithAutopayElig()
						.equalsIgnoreCase("Y")) {
					yorn = "Yes";
				} else if (tdmPolicyPropertySearchDTO
						.getPolicyWithAutopayElig().equalsIgnoreCase("N")) {
					yorn = "No";
				} else {
					yorn = "Any";
				}

				searchCriteria += " + "
						+ messageSource.getMessage(
								"label.policy.poliWithTotalDue", null, null)
						+ " : " + yorn + "  ";
			}

			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
					.getPolicyWithAutopayElig())) {
				String yorn = null;
				if (tdmPolicyPropertySearchDTO.getPolicyWithAutopayElig()
						.equalsIgnoreCase("Y")) {
					yorn = "Yes";
				} else if (tdmPolicyPropertySearchDTO
						.getPolicyWithAutopayElig().equalsIgnoreCase("N")) {
					yorn = "No";
				} else {
					yorn = "Any";
				}

				searchCriteria += " + "
						+ messageSource.getMessage(
								"label.policy.poliWithAutpayElg", null, null)
						+ " : " + yorn + "  ";
			}

		}

		return searchCriteria;
	}

	@RequestMapping(value = "/dataMinningAutoPolicySearch", method = RequestMethod.POST, params = "export")
	public ModelAndView policyAutoPolicySearchResultsExport(
			@ModelAttribute(MessageConstant.TDM_ATS_SEARCH_DTO) TDMAutoPolicySearchDTO tdmAtsSearchDTO,
			ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("In auto search controller  policyPropExport methos: ");

		String policyNumbers = request.getParameter("policyNumners");

		logger.info("In auto search controller  policyPropExport methos: ");

		String UserId = (String) request.getSession().getAttribute("UserId");

		if (org.springframework.util.StringUtils.hasText(policyNumbers)) {
			tdmAtsSearchDTO.setPolicyNumberList(policyNumbers);
			try {

				tdmFineTestDataService.getPoliciesToExport(tdmAtsSearchDTO,
						UserId);
			} catch (Exception e) {

			}
		}
		List<TdmPolicyAutoSearchResultDTO> list = null;
		if (null != tdmAtsSearchDTO.getTdmPolicyAutoSearchResultDTOList()
				&& 0 < tdmAtsSearchDTO.getTdmPolicyAutoSearchResultDTOList()
						.size()) {
			list = new ArrayList<TdmPolicyAutoSearchResultDTO>();
			for (int i = 0; i < tdmAtsSearchDTO
					.getTdmPolicyAutoSearchResultDTOList().size(); i++) {
				if (null != tdmAtsSearchDTO
						.getTdmPolicyAutoSearchResultDTOList().get(i)) {
					tdmAtsSearchDTO.getTdmPolicyAutoSearchResultDTOList()
							.get(i).setUserId(UserId);
					list.add(tdmAtsSearchDTO
							.getTdmPolicyAutoSearchResultDTOList().get(i));
				}
			}
		}

		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ new File("Auto_policy.xls"));
		// model.addAttribute("result", getSearchCriteria(tdmAtsSearchDTO));
		String userName = null;
		if (null != (String) request.getSession().getAttribute("UserId")) {
			userName = (String) request.getSession().getAttribute("UserId");
		}
		activityAuditService.auditActivityLog(ActivityScreenName.DATA_MINING, ActivityAuditLog.AUTO_POLICY_EXPORT, userName, "No Of Records Export: "+String.valueOf(tdmAtsSearchDTO.getTdmPolicyAutoSearchResultDTOList().size()));
		return new ModelAndView("searchPolicyAutoListExcelView",
				"tdmPolicyAutoSearchResultDTOs", list);
	}

	@RequestMapping(value = "/dataMinningPropertyPolicySearch", method = RequestMethod.POST, params = "export")
	public ModelAndView policyPropertyPolicySearchResultsExport(
			@ModelAttribute(MessageConstant.TDM_ATS_SEARCH_DTO) TDMPolicyPropertyNewSearchDTO tdmAtsSearchDTO,
			ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("In auto search controller  policyPropExport methos: ");

		String policyNumbers = request.getParameter("policyNumners");

		logger.info("In auto search controller  policyPropExport methos: ");

		String UserId = (String) request.getSession().getAttribute("UserId");

		if (org.springframework.util.StringUtils.hasText(policyNumbers)) {
			tdmAtsSearchDTO.setPolicyNumberList(policyNumbers);
			try {

				tdmFineTestDataService.getPropertyPoliciesToExport(
						tdmAtsSearchDTO, UserId);
			} catch (Exception e) {

			}
		}
		List<TdmPolicyPropertySearchResultDTO> list = null;
		if (null != tdmAtsSearchDTO.getTdmPolicyPropertySearchResultDTOList()
				&& 0 < tdmAtsSearchDTO
						.getTdmPolicyPropertySearchResultDTOList().size()) {
			list = new ArrayList<TdmPolicyPropertySearchResultDTO>();
			for (int i = 0; i < tdmAtsSearchDTO
					.getTdmPolicyPropertySearchResultDTOList().size(); i++) {
				if (null != tdmAtsSearchDTO
						.getTdmPolicyPropertySearchResultDTOList().get(i)) {
					tdmAtsSearchDTO.getTdmPolicyPropertySearchResultDTOList()
							.get(i).setUserId(UserId);
					list.add(tdmAtsSearchDTO
							.getTdmPolicyPropertySearchResultDTOList().get(i));
				}
			}
		}

		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ new File("Property_policy.xls"));
		// model.addAttribute("result", getSearchCriteria(tdmAtsSearchDTO));
		
		String userName = null;
		if (null != (String) request.getSession().getAttribute("UserId")) {
			userName = (String) request.getSession().getAttribute("UserId");
		}
		activityAuditService.auditActivityLog(
				ActivityScreenName.DATA_MINING,
				ActivityAuditLog.PROPERTY_POLICY_EXPORT,
				userName,
				"No Of Records Export: "
						+ String.valueOf(tdmAtsSearchDTO
								.getTdmPolicyPropertySearchResultDTOList().size()));
		return new ModelAndView("searchPolicyPropertyListExcelView",
				"tdmPolicyPropertySearchResultDTOs", list);
	}

	public String getSearchCriteria(
			TDMAutoPolicySearchDTO tdmPolicyAutoSearchDTO) {

		String searchCriteria = "";
		logger.info("In auto search controller  getSearchCriteria methos: ");

		if (null != tdmPolicyAutoSearchDTO) {
			searchCriteria += "Search Result for: ";

			if (StringUtils.isNotEmpty(tdmPolicyAutoSearchDTO.getEnvType())) {
				searchCriteria += messageSource.getMessage("label.env", null,
						null)
						+ " : "
						+ tdmPolicyAutoSearchDTO.getEnvType()
						+ "  ";
			}

			if (StringUtils.isNotEmpty(tdmPolicyAutoSearchDTO.getPolicyStage())) {
				searchCriteria += " + "
						+ messageSource.getMessage("label.policy.stage", null,
								null) + " : "
						+ tdmPolicyAutoSearchDTO.getPolicyStage() + "  ";
			}
			if (StringUtils.isNotEmpty(tdmPolicyAutoSearchDTO.getPolicyTerm())) {
				searchCriteria += " + "
						+ messageSource.getMessage("label.policy.term", null,
								null) + " : "
						+ tdmPolicyAutoSearchDTO.getPolicyTerm() + "  ";
			}
			if (StringUtils.isNotEmpty(tdmPolicyAutoSearchDTO.getPolicyState())) {
				searchCriteria += " + "
						+ messageSource.getMessage("label.policy.state", null,
								null) + " : "
						+ tdmPolicyAutoSearchDTO.getPolicyState() + "  ";
			}
			if (StringUtils.isNotEmpty(tdmPolicyAutoSearchDTO.getPolicyCovge())) {
				searchCriteria += " + "
						+ messageSource.getMessage("label.policy.coverage",
								null, null) + " : "
						+ tdmPolicyAutoSearchDTO.getPolicyCovge() + "  ";
			}
			
			if (StringUtils.isNotEmpty(tdmPolicyAutoSearchDTO.getAssoPayReq())) {

				String yorn = null;
				if (tdmPolicyAutoSearchDTO.getAssoPayReq()
						.equalsIgnoreCase("Y")) {
					yorn = "Yes";
				} else if (tdmPolicyAutoSearchDTO.getAssoPayReq()
						.equalsIgnoreCase("N")) {
					yorn = "No";
				} else {
					yorn = "Any";
				}

				searchCriteria += " + "
						+ messageSource.getMessage("label.policy.assoPayReq",
								null, null) + " : " + yorn + "  ";
			}
			if (StringUtils.isNotEmpty(tdmPolicyAutoSearchDTO.getAssoDocReq())) {

				String yorn = null;
				if (tdmPolicyAutoSearchDTO.getAssoDocReq()
						.equalsIgnoreCase("Y")) {
					yorn = "Yes";
				} else if (tdmPolicyAutoSearchDTO.getAssoDocReq()
						.equalsIgnoreCase("N")) {
					yorn = "No";
				} else {
					yorn = "Any";
				}

				searchCriteria += " + "
						+ messageSource.getMessage("label.policy.assoDoc",
								null, null) + " : " + yorn + "  ";
			}
			
			if (StringUtils.isNotEmpty(tdmPolicyAutoSearchDTO.getNoOfDrivers())) {
				searchCriteria += " + "
						+ messageSource.getMessage("label.policy.noDrivers",
								null, null) + " : "
						+ tdmPolicyAutoSearchDTO.getNoOfDrivers() + "  ";
			}
			if (StringUtils.isNotEmpty(tdmPolicyAutoSearchDTO.getNoOfVehi())) {
				searchCriteria += " + "
						+ messageSource.getMessage("label.policy.noVehi", null,
								null) + " : "
						+ tdmPolicyAutoSearchDTO.getNoOfVehi() + "  ";
			}
			if (StringUtils.isNotEmpty(tdmPolicyAutoSearchDTO
					.getNoOfNamedInsu())) {
				searchCriteria += " + "
						+ messageSource.getMessage("label.policy.noInsu", null,
								null) + " : "
						+ tdmPolicyAutoSearchDTO.getNoOfNamedInsu() + "  ";
			}
			if (StringUtils.isNotEmpty(tdmPolicyAutoSearchDTO.getNoOfViola())) {
				searchCriteria += " + "
						+ messageSource.getMessage("label.policy.noViola",
								null, null) + " : "
						+ tdmPolicyAutoSearchDTO.getNoOfViola() + "  ";
			}

		} else {
			searchCriteria = "No Search Records Found";
		}
		logger.info("In auto search controller return search criteria : ");
		return searchCriteria;
	}

	private void removeDeplicates(TDMAutoPolicySearchDTO autoPolicySearchDTO) {

		autoPolicySearchDTO.setEnvType(removeDuplicates(autoPolicySearchDTO
				.getEnvType()));
		autoPolicySearchDTO.setProductcd(removeDuplicates(autoPolicySearchDTO
				.getProductcd()));
		autoPolicySearchDTO.setPolicyCovge(removeDuplicates(autoPolicySearchDTO
				.getPolicyCovge()));
		autoPolicySearchDTO.setPolicyStage(removeDuplicates(autoPolicySearchDTO
				.getPolicyStage()));

		autoPolicySearchDTO.setPolicyState(removeDuplicates(autoPolicySearchDTO
				.getPolicyState()));
		autoPolicySearchDTO.setPolicyTerm(removeDuplicates(autoPolicySearchDTO
				.getPolicyTerm()));

		autoPolicySearchDTO.setPolicyType(removeDuplicates(autoPolicySearchDTO
				.getPolicyType()));
		autoPolicySearchDTO
				.setPolicyWithAutopayElig(removeDuplicates(autoPolicySearchDTO
						.getPolicyWithAutopayElig()));
		autoPolicySearchDTO
				.setDeathBenifit(removeDuplicates(autoPolicySearchDTO
						.getDeathBenifit()));
		autoPolicySearchDTO.setTotalDue(removeDuplicates(autoPolicySearchDTO
				.getTotalDue()));
		autoPolicySearchDTO
				.setTotalDisability(removeDuplicates(autoPolicySearchDTO
						.getTotalDisability()));
		autoPolicySearchDTO
				.setAddRiskCovge(removeDuplicates(autoPolicySearchDTO
						.getAddRiskCovge()));
		autoPolicySearchDTO.setNoOfDrivers(removeDuplicates(autoPolicySearchDTO
				.getNoOfDrivers()));
		autoPolicySearchDTO.setNoOfVehi(removeDuplicates(autoPolicySearchDTO
				.getNoOfVehi()));

		autoPolicySearchDTO.setNoOfViola(removeDuplicates(autoPolicySearchDTO
				.getNoOfViola()));
		autoPolicySearchDTO
				.setNoOfNamedInsu(removeDuplicates(autoPolicySearchDTO
						.getNoOfNamedInsu()));

		autoPolicySearchDTO
				.setAddproductType(removeDuplicates(autoPolicySearchDTO
						.getAddproductType()));

	}

	private String removeDuplicates(String str) {
		String s = null;
		if (org.springframework.util.StringUtils.hasText(str)) {
			String arrStr[] = str.split(",");
			s = arrStr[0];
		}
		return s;
	}

	@RequestMapping(value = "/dataMinningAutoPolicySearchGetRcds", method = RequestMethod.GET)
	public @ResponseBody String getSelectedRecordsForAutoPolicySearch(
			HttpServletRequest request,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "reserve", required = false) String reserve,
			@ModelAttribute("tdmAtsSearchDTO") TDMAutoPolicySearchDTO autoPolicySearchDTO,
			ModelMap model, HttpServletResponse response) throws IOException

	{

		if (tdmPolicyPropertySearchDTO1 != null) {
			autoPolicySearchDTO = tdmPolicyPropertySearchDTO1;
		}
		String json2 = "";

		Integer pageNumber = 0;
		// Fetch search parameter
		String searchParameter = request.getParameter("sSearch");
		String iDisplayStart = request.getParameter("iDisplayStart");
		// Fetch Page display length
		String pageDisplayLength = request.getParameter("iDisplayLength");

		
		
		if (org.springframework.util.StringUtils.hasText(iDisplayStart)
				&& org.springframework.util.StringUtils
						.hasText(pageDisplayLength)) {
			if (!org.springframework.util.StringUtils.hasText(sortOrder)
					&& !org.springframework.util.StringUtils
							.hasText(sortColumn)) {
				request.getSession().setAttribute("pageDisplayLength",
						pageDisplayLength);
			}

			if ((String) request.getSession().getAttribute("pageDisplayLength") != null) {
				pageDisplayLength = (String) request.getSession().getAttribute(
						"pageDisplayLength");
			}

			pageNumber = (Integer.valueOf(iDisplayStart) / Integer
					.valueOf(pageDisplayLength)) + 1;

		}
		int flag = 1;
		String userName = null;
		String reserveFlag = null;
		int countPerPage = 0;

		autoPolicySearchDTO.setRecordNumber(Integer.valueOf(pageDisplayLength));
		
		setNullIfTheValueIsAnyInAutoSearch(autoPolicySearchDTO);
		autoPolicySearchDTO
				.setSearchCriti(getSearchAutoCriteria(autoPolicySearchDTO));
		try {

			if (null != (String) request.getSession().getAttribute("UserId")) {
				userName = (String) request.getSession().getAttribute("UserId");
			}
			Long totalRecords = 0L;
			if (StringUtils.isNotEmpty(autoPolicySearchDTO.getAddYearBuilt())
					|| StringUtils.isNotEmpty(autoPolicySearchDTO
							.getAddConType())
					|| StringUtils.isNotEmpty(autoPolicySearchDTO.getZipCode())
					|| StringUtils
							.isNotEmpty(autoPolicySearchDTO.getLeinIndi())
					|| StringUtils.isNotEmpty(autoPolicySearchDTO
							.getOptnlCovge())) {
				autoPolicySearchDTO.setShowHideFlag(true);
			}
			if (StringUtils.isNotEmpty(autoPolicySearchDTO.getAddDocType())) {
				autoPolicySearchDTO.setShowHideFlagDoc(true);
			}
			model.addAttribute("result",
					getSearchAutoCriteria(autoPolicySearchDTO));
			autoPolicySearchDTO
					.setSearchCriti(getSearchAutoCriteria(autoPolicySearchDTO));
			PaginationUtil pagenation = new PaginationUtil();
			int recordsperpage = 25; // Integer.valueOf(tdmPolicyPropertySearchDTO.getSearchRecordsNo());
			noOfRecForpage = recordsperpage;
			int offSet = pagenation.getOffset(request, recordsperpage);
			if (null != search) {
				if (logger.isInfoEnabled()) {
					logger.info("property controller searching records  : ");
				}
				autoPolicySearchDTO = tdmFineTestDataService.searchAutoPolicy(
						autoPolicySearchDTO, offSet, recordsperpage, true,
						userName);

			} else if (null != reserve) {
				if (logger.isInfoEnabled()) {
					logger.info("property controller reserving records  : ");
				}
				autoPolicySearchDTO
						.setSearchCriti(getSearchAutoCriteria(autoPolicySearchDTO));
				int cntRec = tdmFineTestDataService
						.saveReservedDataForAutoPolicy(autoPolicySearchDTO,
								userName, autoPolicySearchDTO.getEnvType());
				autoPolicySearchDTO = tdmFineTestDataService.searchAutoPolicy(
						autoPolicySearchDTO, offSet, recordsperpage, true,
						userName);
				reserveFlag = cntRec + " Record(s) successfully reserved.";
				autoPolicySearchDTO.setTestCaseId(null);
				autoPolicySearchDTO.setTestCaseName(null);
			}

			if (Integer.valueOf(pageDisplayLength) != null
					&& Integer.valueOf(pageDisplayLength) > 0) {
				autoPolicySearchDTO = tdmFineTestDataService.searchAutoPolicy(
						autoPolicySearchDTO, offSet, recordsperpage, true,
						userName);

				List<TdmPolicyAutoSearchResultDTO> tdmPolicyAutoSearchResultDTOList = getListBasedOnSearchParameter(
						searchParameter,
						autoPolicySearchDTO
								.getTdmPolicyAutoSearchResultDTOList());

				TdmPolicyAutoSearchResultJsonDTO jsonDTO = new TdmPolicyAutoSearchResultJsonDTO();
				tdmPolicyAutoSearchResultDTOList = getSpecificNumberOfRecordsForAuto(
						tdmPolicyAutoSearchResultDTOList,
						Integer.valueOf(pageDisplayLength), pageNumber);
				if (CollectionUtils.isEmpty(autoPolicySearchDTO
						.getTdmPolicyAutoSearchResultDTOList())) {
					request.setAttribute("NO_AUTO_RECORDS",
							"No records found for the given search criteria");
				} else {
					System.out.println("sortOrder :" + sortOrder);
					System.out.println("sortColumn : " + sortColumn);
					Collection<TdmPolicyAutoSearchResultDTO> sortedCollection = null;
					if (org.springframework.util.StringUtils.hasText(sortOrder)
							&& org.springframework.util.StringUtils
									.hasText(sortColumn)) {
						try {

							sortedCollection = getSortedAutoSearchResults(
									tdmPolicyAutoSearchResultDTOList,
									sortColumn, sortOrder);
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						sortOrder = null;
						sortColumn = null;
					}
					jsonDTO.setiTotalDisplayRecords(autoPolicySearchDTO
							.getTdmPolicyAutoSearchResultDTOList().size());
					// Set Total record
					jsonDTO.setiTotalRecords(autoPolicySearchDTO
							.getTdmPolicyAutoSearchResultDTOList().size());
					if (CollectionUtils.isEmpty(sortedCollection)) {
						jsonDTO.setAaData(tdmPolicyAutoSearchResultDTOList);
					} else {
						jsonDTO.setAaData((List<TdmPolicyAutoSearchResultDTO>) sortedCollection);
					}
				}
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				json2 = gson.toJson(jsonDTO);
				System.out.println("json2 : " + json2);
			}

			if (null != autoPolicySearchDTO
					.getTdmPolicyAutoSearchResultDTOList()
					&& autoPolicySearchDTO
							.getTdmPolicyAutoSearchResultDTOList().size() >= 25) {
				totalRecords = 50L;
			} else {
				totalRecords = 25L;
			}
			// set the size to no of records variable
			if (null != autoPolicySearchDTO
					.getTdmPolicyAutoSearchResultDTOList()
					&& autoPolicySearchDTO
							.getTdmPolicyAutoSearchResultDTOList().size() > 0) {
				countPerPage = autoPolicySearchDTO
						.getTdmPolicyAutoSearchResultDTOList().size();
			}
			pagenation.paginate(totalRecords, request,
					Double.valueOf(recordsperpage), recordsperpage);
			int noOfPages = (int) Math.ceil(totalRecords.doubleValue()
					/ recordsperpage);

			autoPolicySearchDTO = tdmFineTestDataService
					.getTDMReservedAutoTestDataListPerUser(autoPolicySearchDTO);

			tdmPolicyPropertySearchDTO1 = autoPolicySearchDTO;
			request.setAttribute("noOfPages", noOfPages);
			model.addAttribute("reserveFlag", reserveFlag);
			model.addAttribute("totalRecords", countPerPage);
			model.addAttribute("count", autoPolicySearchDTO.getCount());
			model.addAttribute("tdmPolicyPropertySearchResultDTOList",
					autoPolicySearchDTO
							.getTdmPolicyPropertySearchResultDTOList());
			model.addAttribute("reservedTestDataListPerUser",
					autoPolicySearchDTO.getReservedTestDataList());

			model.addAttribute("environment", autoEnvironmentList);
			model.addAttribute("producttype", autoProductTypeList);
			model.addAttribute("policystatus", autoPolicyStatusList);
			model.addAttribute("riskstate", autoRiskStateList);
			model.addAttribute("policyterm", autoPolicyTermList);
			model.addAttribute("vehiclelevel", autoVehicleLevelCoverageList);
			model.addAttribute("policylevel", autoPolicyLevelCoverageList);
			model.addAttribute("paymentplan", autoPaymentPlanList);

			model.addAttribute("tdmPolicyAutoSearchDTO", autoPolicySearchDTO);
			model.addAttribute("flag", flag);
			model.addAttribute("tdmAtsSearchDTO", autoPolicySearchDTO);
		} catch (BaseException baseEx) {
			if (baseEx.getErrorCode() != null) {
				model.addAttribute("error", baseEx.getErrorCode() + " : "
						+ baseEx.getRootCause());
				return "dataMinningAutoPolicySearch";
			}
		}
		return json2;
	}

	private List<TdmPolicyAutoSearchResultDTO> getSpecificNumberOfRecordsForAuto(
			List<TdmPolicyAutoSearchResultDTO> tdmPolicyAutoSearchResultDTOCol,
			Integer pageSize, Integer pageNumber) {
		List<TdmPolicyAutoSearchResultDTO> filteredRecords = new ArrayList<TdmPolicyAutoSearchResultDTO>();
		if (tdmPolicyAutoSearchResultDTOCol == null) {
			return null;
		}
		int countOfRecords = tdmPolicyAutoSearchResultDTOCol.size();
		// now work out where the sub-list should start and end
		if (pageNumber > 0) {
			Integer startingIndex = pageSize * (pageNumber - 1);
			if (startingIndex < 0) {
				startingIndex = 0;
			}
			Integer endingIndex = startingIndex + pageSize;
			if (endingIndex > countOfRecords) {
				endingIndex = countOfRecords;
			}
			tdmPolicyAutoSearchResultDTOCol = tdmPolicyAutoSearchResultDTOCol
					.subList(startingIndex, endingIndex);
		}

		int displayedCount = 0;
		for (TdmPolicyAutoSearchResultDTO autoSearchResultDTO : tdmPolicyAutoSearchResultDTOCol) {
			filteredRecords.add(autoSearchResultDTO);
			displayedCount++;
			if (pageSize.intValue() == displayedCount) {
				break;
			}
		}
		return filteredRecords;

	}

	private List<TdmPolicyPropertySearchResultDTO> getSpecificNumberOfRecordsForPolicy(
			List<TdmPolicyPropertySearchResultDTO> tdmPolicyAutoSearchResultDTOCol,
			Integer pageSize, Integer pageNumber) {
		List<TdmPolicyPropertySearchResultDTO> filteredRecords = new ArrayList<TdmPolicyPropertySearchResultDTO>();
		if (tdmPolicyAutoSearchResultDTOCol == null) {
			return null;
		}
		int countOfRecords = tdmPolicyAutoSearchResultDTOCol.size();
		// now work out where the sub-list should start and end
		if (pageNumber > 0) {
			Integer startingIndex = pageSize * (pageNumber - 1);
			if (startingIndex < 0) {
				startingIndex = 0;
			}
			Integer endingIndex = startingIndex + pageSize;
			if (endingIndex > countOfRecords) {
				endingIndex = countOfRecords;
			}
			tdmPolicyAutoSearchResultDTOCol = tdmPolicyAutoSearchResultDTOCol
					.subList(startingIndex, endingIndex);
		}

		int displayedCount = 0;
		for (TdmPolicyPropertySearchResultDTO autoSearchResultDTO : tdmPolicyAutoSearchResultDTOCol) {
			filteredRecords.add(autoSearchResultDTO);
			displayedCount++;
			if (pageSize.intValue() == displayedCount) {
				break;
			}
		}
		return filteredRecords;

	}

	private List<TdmPolicyAutoSearchResultDTO> getListBasedOnSearchParameter(
			String searchParameter,
			List<TdmPolicyAutoSearchResultDTO> tdmPolicyAutoSearchResultDTOList) {

		if (null != searchParameter && !searchParameter.equals("")) {
			List<TdmPolicyAutoSearchResultDTO> personsListForSearch = new ArrayList<TdmPolicyAutoSearchResultDTO>();
			searchParameter = searchParameter.toUpperCase();
			for (TdmPolicyAutoSearchResultDTO person : tdmPolicyAutoSearchResultDTOList) {
				if (person.getPolicynumber().toUpperCase()
						.indexOf(searchParameter) != -1
						|| person.getProductType().toUpperCase()
								.indexOf(searchParameter) != -1
						|| person.getPolicyType().toUpperCase()
								.indexOf(searchParameter) != -1
						|| person.getPolicynumber().toUpperCase()
								.indexOf(searchParameter) != -1) {
					personsListForSearch.add(person);
				}

			}
			tdmPolicyAutoSearchResultDTOList = personsListForSearch;
			personsListForSearch = null;
		}
		return tdmPolicyAutoSearchResultDTOList;
	}

	@RequestMapping(value = "/dataMinningPropertyPolicySearchGetRcds", method = RequestMethod.GET)
	public @ResponseBody String getSelectedRecordsForPropertyPolicySearch(
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "reserve", required = false) String reserve,
			@ModelAttribute(MessageConstant.TDM_ATS_SEARCH_NEW_DTO) TDMPolicyPropertyNewSearchDTO tdmPolicyPropertyNewSearchDTO,
			ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws BaseException

	{
		if (tdmPolicyPropertyNewSearchDTO1 != null) {
			tdmPolicyPropertyNewSearchDTO = tdmPolicyPropertyNewSearchDTO1;
		}
		int flag = 1;

		String userName = null;
		String reserveFlag = null;
		int countPerPage = 0;
		String json2 = "";
		// Fetch search parameter
		String searchParameter = request.getParameter("sSearch");
		// Fetch Page display length
		String pageDisplayLength = request.getParameter("iDisplayLength");
		Integer pageNumber = 0;
		String iDisplayStart = request.getParameter("iDisplayStart");

			
		if (org.springframework.util.StringUtils.hasText(iDisplayStart)
				&& org.springframework.util.StringUtils
						.hasText(pageDisplayLength)) {
			if (!org.springframework.util.StringUtils.hasText(sortOrder)
					&& !org.springframework.util.StringUtils
							.hasText(sortColumn)) {
				request.getSession().setAttribute("pageDisplayLength",
						pageDisplayLength);
			}

			if ((String) request.getSession().getAttribute("pageDisplayLength") != null) {
				pageDisplayLength = (String) request.getSession().getAttribute(
						"pageDisplayLength");
			}
			pageNumber = (Integer.valueOf(iDisplayStart) / Integer
					.valueOf(pageDisplayLength)) + 1;

		}

		tdmPolicyPropertyNewSearchDTO.setRecordNumber(Integer
				.valueOf(pageDisplayLength));

		setNullIfTheValueIsAnyInPropertySearch(tdmPolicyPropertyNewSearchDTO);
		tdmPolicyPropertyNewSearchDTO
				.setSearchCriti(getSearchPropertyCriteria(tdmPolicyPropertyNewSearchDTO));
		try {

			if (null != (String) request.getSession().getAttribute("UserId")) {
				userName = (String) request.getSession().getAttribute("UserId");
			}
			Long totalRecords = 0L;

			model.addAttribute("result",
					getSearchPropertyCriteria(tdmPolicyPropertyNewSearchDTO));
			tdmPolicyPropertyNewSearchDTO
					.setSearchCriti(getSearchPropertyCriteria(tdmPolicyPropertyNewSearchDTO));
			PaginationUtil pagenation = new PaginationUtil();
			int recordsperpage = 25; // Integer.valueOf(tdmPolicyPropertySearchDTO.getSearchRecordsNo());
			noOfRecForpage = recordsperpage;
			int offSet = pagenation.getOffset(request, recordsperpage);
			if (null != search) {
				if (logger.isInfoEnabled()) {
					logger.info("property controller searching records  : ");
				}
				tdmPolicyPropertyNewSearchDTO = tdmFineTestDataService
						.searchPropertyPolicy(tdmPolicyPropertyNewSearchDTO,
								offSet, recordsperpage, true, userName);

			} else if (null != reserve) {
				if (logger.isInfoEnabled()) {
					logger.info("property controller reserving records  : ");
				}
				tdmPolicyPropertyNewSearchDTO
						.setSearchCriti(getSearchPropertyCriteria(tdmPolicyPropertyNewSearchDTO));
				int cntRec = tdmFineTestDataService
						.saveReservedDataForPropertyPolicy(
								tdmPolicyPropertyNewSearchDTO, userName,
								tdmPolicyPropertyNewSearchDTO.getEnvType());
				tdmPolicyPropertyNewSearchDTO = tdmFineTestDataService
						.searchPropertyPolicy(tdmPolicyPropertyNewSearchDTO,
								offSet, recordsperpage, true, userName);
				reserveFlag = cntRec + " Record(s) successfully reserved.";
				tdmPolicyPropertyNewSearchDTO.setTestCaseId(null);
				tdmPolicyPropertyNewSearchDTO.setTestCaseName(null);
			}

			if (Integer.valueOf(pageDisplayLength) != null
					&& Integer.valueOf(pageDisplayLength) > 0) {
				tdmPolicyPropertyNewSearchDTO = tdmFineTestDataService
						.searchPropertyPolicy(tdmPolicyPropertyNewSearchDTO,
								offSet, recordsperpage, true, userName);

				List<TdmPolicyPropertySearchResultDTO> tdmPolicyAutoSearchResultDTOList = getListBasedOnSearchParameterProperty(
						searchParameter,
						tdmPolicyPropertyNewSearchDTO
								.getTdmPolicyPropertySearchResultDTOList());

				TdmPolicyPropertySearchResultJsonDTO jsonDTO = new TdmPolicyPropertySearchResultJsonDTO();

				tdmPolicyAutoSearchResultDTOList = getSpecificNumberOfRecordsForPolicy(
						tdmPolicyAutoSearchResultDTOList,
						Integer.valueOf(pageDisplayLength), pageNumber);

				if (!CollectionUtils.isEmpty(tdmPolicyAutoSearchResultDTOList))

				{

					System.out.println("sortOrder :" + sortOrder);
					System.out.println("sortColumn : " + sortColumn);
					Collection<TdmPolicyPropertySearchResultDTO> sortedCollection = null;
					if (org.springframework.util.StringUtils.hasText(sortOrder)
							&& org.springframework.util.StringUtils
									.hasText(sortColumn)) {
						try {

							sortedCollection = getSortedPropertySearchResults(
									tdmPolicyAutoSearchResultDTOList,
									sortColumn, sortOrder);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					jsonDTO.setiTotalDisplayRecords(tdmPolicyPropertyNewSearchDTO
							.getTdmPolicyPropertySearchResultDTOList().size());
					// Set Total record
					jsonDTO.setiTotalRecords(tdmPolicyPropertyNewSearchDTO
							.getTdmPolicyPropertySearchResultDTOList().size());

					if (CollectionUtils.isEmpty(sortedCollection)) {
						jsonDTO.setAaData(tdmPolicyAutoSearchResultDTOList);
					} else {
						jsonDTO.setAaData((List<TdmPolicyPropertySearchResultDTO>) sortedCollection);
					}
				}

				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				json2 = gson.toJson(jsonDTO);
			}

			if (null != tdmPolicyPropertyNewSearchDTO
					.getTdmPolicyPropertySearchResultDTOList()
					&& tdmPolicyPropertyNewSearchDTO
							.getTdmPolicyPropertySearchResultDTOList().size() >= 25) {
				totalRecords = 50L;
			} else {
				totalRecords = 25L;
			}
			// set the size to no of records variable
			if (null != tdmPolicyPropertyNewSearchDTO
					.getTdmPolicyPropertySearchResultDTOList()
					&& tdmPolicyPropertyNewSearchDTO
							.getTdmPolicyPropertySearchResultDTOList().size() > 0) {
				countPerPage = tdmPolicyPropertyNewSearchDTO
						.getTdmPolicyPropertySearchResultDTOList().size();
			}
			pagenation.paginate(totalRecords, request,
					Double.valueOf(recordsperpage), recordsperpage);
			int noOfPages = (int) Math.ceil(totalRecords.doubleValue()
					/ recordsperpage);

			tdmPolicyPropertyNewSearchDTO = tdmFineTestDataService
					.getTDMReservedPropertyTestDataListPerUser(tdmPolicyPropertyNewSearchDTO);

			tdmPolicyPropertyNewSearchDTO1 = tdmPolicyPropertyNewSearchDTO;
			request.setAttribute("noOfPages", noOfPages);
			model.addAttribute("reserveFlag", reserveFlag);
			model.addAttribute("totalRecords", countPerPage);
			model.addAttribute("count",
					tdmPolicyPropertyNewSearchDTO.getCount());

			model.addAttribute("tdmPolicyPropertySearchResultDTOList",
					tdmPolicyPropertyNewSearchDTO
							.getTdmPolicyPropertySearchResultDTOList());
			/**
			 * model.addAttribute("reservedTestDataListPerUser",
			 * tdmPolicyPropertySearchDTO.getReservedTestDataList());
			 */
			String policyCoverage = tdmPolicyPropertyNewSearchDTO
					.getPolicyCovge();
			System.out.println("policyCoverage : " + policyCoverage);

			tdmPolicyPropertyNewSearchDTO = tdmFineTestDataService
					.getTDMReservedPropertyTestDataListPerUser(tdmPolicyPropertyNewSearchDTO);

			model.addAttribute("environment", propertyEnvironmentList);
			model.addAttribute("producttype", propertyProductTypeList);
			model.addAttribute("policystatus", propertyPolicyStatusList);
			model.addAttribute("riskstate", propertyRiskStateList);
			model.addAttribute("policytype", propertyPolicyTypeList);
			model.addAttribute("riskstate", propertyRiskStateList);
			model.addAttribute("paymentplan", propertyPolicyLevelList);
			model.addAttribute("policylevel", propertyPolicyLevelList);
			model.addAttribute("tdmPolicyPropertySearchDTO",
					tdmPolicyPropertyNewSearchDTO);
			model.addAttribute("flag", flag);
			sortOrder = null;
			sortColumn = null;
		} catch (BaseException baseEx) {
			if (baseEx.getErrorCode() != null) {
				model.addAttribute("error", baseEx.getErrorCode() + " : "
						+ baseEx.getRootCause());
				return "dataMinningAutoPolicySearch";
			}
		}
		return json2;
	}

	private List<TdmPolicyPropertySearchResultDTO> getListBasedOnSearchParameterProperty(
			String searchParameter,
			List<TdmPolicyPropertySearchResultDTO> tdmPolicyAutoSearchResultDTOList) {

		if (null != searchParameter && !searchParameter.equals("")) {
			List<TdmPolicyPropertySearchResultDTO> personsListForSearch = new ArrayList<TdmPolicyPropertySearchResultDTO>();
			searchParameter = searchParameter.toUpperCase();
			for (TdmPolicyPropertySearchResultDTO person : tdmPolicyAutoSearchResultDTOList) {
				if (person.getPolicynumber().toUpperCase()
						.indexOf(searchParameter) != -1
						|| person.getProductType().toUpperCase()
								.indexOf(searchParameter) != -1
						|| person.getPolicyType().toUpperCase()
								.indexOf(searchParameter) != -1
						|| person.getPolicynumber().toUpperCase()
								.indexOf(searchParameter) != -1) {
					personsListForSearch.add(person);
				}

			}
			tdmPolicyAutoSearchResultDTOList = personsListForSearch;
			personsListForSearch = null;
		}
		return tdmPolicyAutoSearchResultDTOList;
	}

}
