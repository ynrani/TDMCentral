package com.tdm.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.tdm.constant.AppConstant;
import com.tdm.constant.MessageConstant;
import com.tdm.constant.RequestStatusENUM;
import com.tdm.constant.UIScreenENUM;
import com.tdm.exception.BaseException;
import com.tdm.exception.DAOException;
import com.tdm.exception.ServiceException;
import com.tdm.model.DTO.ActivityAuditLog;
import com.tdm.model.DTO.ActivityScreenName;
import com.tdm.model.DTO.AutoPolicyParamsDTO;
import com.tdm.model.DTO.DCAccessLogDTO;
import com.tdm.model.DTO.FieldListDTO;
import com.tdm.model.DTO.PropertyPolicyParamsDTO;
import com.tdm.model.DTO.TDMDataCreationDTO;
import com.tdm.service.DataCreationRequestService;
import com.tdm.service.SearchService;
import com.tdm.service.TDMAdminService;
import com.tdm.service.TdmActivityAuditService;
import com.tdm.service.TdmPolicyAutoPropSearchService;
import com.tdm.util.PaginationUtil;

/***
 * To handle data creation requests
 * 
 * @author sujillel
 *
 */
@Controller
@Scope("session")
public class DCreationRequestController {
	private static Logger logger = Logger
			.getLogger(DCreationRequestController.class);
	private static final String AUTO_RISK_STATE = "Auto Risk State";
	private static final String AUTO_PAYMENT_PLAN = "Auto Payment Plan";
	private static final String PROPERTY_RISK_STATE = "Property Risk State";
	private static final String PROPERTY_PAYMENT_PLAN = "Property Payment Plan";
	private static final String PROPERTY_POLICY_TYPE ="Property Policy Type";
	
	@Autowired
	DataCreationRequestService dataRequestService;

	@Autowired
	SearchService searchService;
	@Resource(name = MessageConstant.SERVICE_ADMIN)
	TDMAdminService tDMAdminService;
	@Resource(name = "activityAuditService")
	TdmActivityAuditService activityAuditService;
	@Resource(name = "tdmPolicyAutoPropSearchService")
	TdmPolicyAutoPropSearchService tdmPolicyAutoSearchService;

	@RequestMapping(value = "/requestData", method = RequestMethod.GET)
	public String findTestDataNonStandPost(
			@RequestParam(value = AppConstant.SEARCH, required = false) String search,
			@RequestParam(value = AppConstant.RESERVE, required = false) String reserve,
			@ModelAttribute(MessageConstant.TDM_REQUEST_DATA) TDMDataCreationDTO requestDataDTO,
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
		requestDataDTO = dataRequestService.getAllDropdownDetails();
		requestDataDTO.setUserId(userId);
		requestDataDTO.setApplicationOwner(requestData.getApplicationOwner());
		requestDataDTO.setApprover(requestData.getApprover());
		requestDataDTO.setRequestedBy(userId);

		//genearte scenario drop down values
		genarateScenarioDropDownValues( model );
		List<PropertyPolicyParamsDTO> list = new ArrayList<PropertyPolicyParamsDTO>();
		list.add(new PropertyPolicyParamsDTO());

		requestDataDTO.setPropertyPolicyParamList(list);

		List<AutoPolicyParamsDTO> list1 = new ArrayList<AutoPolicyParamsDTO>();
		list1.add(new AutoPolicyParamsDTO());

		requestDataDTO.setPropertyPolicyParamList(list);
		requestDataDTO.setAutoPolicyParamList(list1);

		model.addAttribute("requestDataDTO", requestDataDTO);
		String userRole = null;
		userRole=(String) request.getSession().getAttribute(AppConstant.ROLE);
		model.addAttribute("userRole", userRole);
		// Audit logging
		activityAuditService.auditActivityLog(ActivityScreenName.DATA_GENERATION_MANUAL, ActivityAuditLog.DG_MENU_CLICKED, userId, null);
		return "requestTab";
	}

	/***
	 * To save DTO data to Service_request table
	 * 
	 * @param save
	 * @param requestDataDTO
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping(value = "/requestDataNew", method = RequestMethod.POST)
	public String dataRequestSave(
			@RequestParam(value = AppConstant.SAVE, required = false) String save,
			@RequestParam(value = "btnSubmit1", required = false) String submit,
			@ModelAttribute("requestDataDTO") TDMDataCreationDTO requestDataDTO,
			@RequestParam(value = AppConstant.BACK, required = false) String back,
			ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		String userId = null;
		String roleUser = "";
		if (null != (String) request.getSession().getAttribute("UserId")) {
			userId = (String) request.getSession().getAttribute("UserId");
		} else {
			return null;
		}

		if (save != null) {
			roleUser = (String) request.getSession().getAttribute(
					AppConstant.ROLE);
			logger.info("roleUser		::	" + roleUser);
			// Work flow engine code Start
			if (roleUser != null && roleUser.equalsIgnoreCase("ROLE_ADMIN")) {
				logger.info("Role Admin RequestDataDTO		::	" + requestDataDTO);
				populateRequestData(requestDataDTO, request);
				//populateRequestData(requestDataDTO, request);
				processWorkFlow(userId, requestDataDTO);
				// model.addAttribute(AppConstant.TDM_ADMIN_DISPLAY_USER,
				// redirectFindRequest(userId,roleUser,request));
				// return "myRequests";
				model.addAttribute("dashboardReqList",
						redirectServiceDesk(request));
				return "adminDashBoard";
			} else {
				requestDataDTO = populateDataGenarationForm(requestDataDTO,
						model, request, userId);
			}
			
			// Audit logging
			activityAuditService.auditActivityLog(ActivityScreenName.DATA_GENERATION_MANUAL, ActivityAuditLog.SAVE_CLICKED, userId, null);
		}
		if (submit != null) {
			String requestId = "";
			requestDataDTO = populateDataGenarationForm(requestDataDTO, model,
					request, userId);
			String conGroup = requestDataDTO.getConsumerGroup();
			String priority = requestDataDTO.getPriority();
			String dataSource = requestDataDTO.getDataSource();
			String environment = requestDataDTO.getEnvironment();
			requestDataDTO.setStatus("2");

			processWorkFlow(userId, requestDataDTO);
			/*
			 * model.addAttribute(AppConstant.TDM_ADMIN_DISPLAY_USER,
			 * redirectFindRequest(userId,roleUser,request)); return
			 * "myRequests";
			 */
			requestId = requestDataDTO.getRequestId();
			// if(StringUtils.hasText(requestId))
			TDMDataCreationDTO requestDataByReqId = searchService
					.getServiceRequestByReqId(requestDataDTO.getRequestId());
			model.addAttribute("requestDataByReqId", requestDataByReqId);
			TDMDataCreationDTO reqDetailDataByReqId = searchService
					.getReqDetailDataByReqId(requestDataDTO.getRequestId());
			reqDetailDataByReqId.getPropertyPolicyParamList();
			model.addAttribute("autoPolicyList",
					reqDetailDataByReqId.getAutoPolicyParamList());
			model.addAttribute("propertyPolicyList",
					reqDetailDataByReqId.getPropertyPolicyParamList());
			requestDataDTO.setUserId(userId);
			requestDataDTO = dataRequestService.getAllDropdownDetails();
			requestDataDTO.setUserId(userId);
			requestDataDTO.setRequestId(requestId);
			requestDataDTO.setRequestedBy(requestDataByReqId.getRequestedBy());
			requestDataDTO.setApprover(requestDataByReqId.getApprover());
			requestDataDTO.setSubject(requestDataByReqId.getSubject());
			requestDataDTO.setApplicationOwner(requestDataByReqId
					.getApplicationOwner());
			requestDataDTO
					.setExpectedDate(requestDataByReqId.getExpectedDate());
			requestDataDTO.setCreatedOn(requestDataByReqId.getCreatedOn());
			requestDataDTO.setStatus(requestDataByReqId.getStatus());
			requestDataDTO.setEnvDesc(requestDataByReqId.getEnvDesc());
			requestDataDTO.setConsumerGroup(conGroup);
			requestDataDTO.setPriority(priority);
			requestDataDTO.setDataSource(dataSource);
			requestDataDTO.setEnvironment(environment);

			model.addAttribute("requestDataDTO", requestDataDTO);
			Gson gsonOb = new Gson();
			String autoPolicyLst = gsonOb.toJson(reqDetailDataByReqId
					.getAutoPolicyParamList());
			model.addAttribute("autoPolicyLst", autoPolicyLst);
			String propPolicyLst = gsonOb.toJson(reqDetailDataByReqId
					.getPropertyPolicyParamList());
			model.addAttribute("propPolicyLst", propPolicyLst);
			
			// Audit logging
			activityAuditService.auditActivityLog(ActivityScreenName.DATA_GENERATION_MANUAL, ActivityAuditLog.SUBMIT_CLICKED, userId, null);

		}
		if (back != null) {
			if (requestDataDTO.getServiceIdentifier() == null) {
				return "index";
			}
			if (requestDataDTO.getServiceIdentifier().equalsIgnoreCase(
					"myRequest")) {
				String userRole = (String) request.getSession().getAttribute(
						AppConstant.ROLE);
				logger.info("roleUser		::	" + userRole);
				try {
					Long totalRecords = 0L;
					PaginationUtil pagenation = new PaginationUtil();
					int recordsperpage = 25;
					int offSet = pagenation.getOffset(request, recordsperpage);
					totalRecords = tDMAdminService
							.getReqByUserIdRecordCount(userId);
					List<TDMDataCreationDTO> reqListByUserId = tDMAdminService
							.getRequestByUserId(userId, userRole, offSet,
									recordsperpage, true);
					pagenation.paginate(totalRecords, request,
							Double.valueOf(recordsperpage), recordsperpage);
					int noOfPages = (int) Math.ceil(totalRecords.doubleValue()
							/ recordsperpage);
					request.setAttribute("noOfPages", noOfPages);
					model.addAttribute(AppConstant.TDM_ADMIN_DISPLAY_USER,
							reqListByUserId);
					logger.info(MessageConstant.TDM_ADMIN_CTRL + "find request"
							+ MessageConstant.LOG_INFO_RETURN);
					activityAuditService.auditActivityLog(ActivityScreenName.DATA_GENERATION_MANUAL, ActivityAuditLog.BACK_CLICKED, userId, null);
					return "myRequests";
				} catch (BaseException baseEx) {
					logger.error(MessageConstant.TDM_ADMIN_CTRL
							+ "find request"
							+ MessageConstant.LOG_ERROR_EXCEPTION + baseEx);
					if (null != baseEx.getErrorCode()
							|| baseEx.getErrorCode().equalsIgnoreCase("null")) {
						if (baseEx.getErrorCode().startsWith("")) {
							return "myRequests";
						}
					}
					model.addAttribute(AppConstant.ERROR,
							MessageConstant.EXCEPTION_ADMIN);
										// Audit logging
					activityAuditService.auditActivityLog(ActivityScreenName.DATA_GENERATION_MANUAL, ActivityAuditLog.BACK_CLICKED, userId, null);
					return "myRequests";
				}
			} else {
				Long totalRecords = 0L;
				PaginationUtil pagenation = new PaginationUtil();
				int recordsperpage = 10;
				int offSet = pagenation.getOffset(request, recordsperpage);
				totalRecords = tDMAdminService.getReqRecordCount();
				List<TDMDataCreationDTO> dashBoardRequestList = tDMAdminService
						.getAllRequest(offSet, recordsperpage, true);
				logger.info(MessageConstant.TDM_ADMIN_CTRL + "find request"
						+ MessageConstant.LOG_INFO_RETURN);
				pagenation.paginate(totalRecords, request,
						Double.valueOf(recordsperpage), recordsperpage);
				int noOfPages = (int) Math.ceil(totalRecords.doubleValue()
						/ recordsperpage);
				request.setAttribute("noOfPages", noOfPages);
				model.addAttribute("dashboardReqList", dashBoardRequestList);
				activityAuditService.auditActivityLog(ActivityScreenName.DATA_GENERATION_MANUAL, ActivityAuditLog.BACK_CLICKED, userId, null);
				return "adminDashBoard";
			}

		}
		return "requestTab";

	}

	private TDMDataCreationDTO populateDataGenarationForm(
			TDMDataCreationDTO requestDataDTO, ModelMap model,
			HttpServletRequest request, String userId) {
		populateRequestData(requestDataDTO, request);
		try {
			TDMDataCreationDTO requestData = new TDMDataCreationDTO();

			requestDataDTO.setRequestedBy(userId);
			requestDataDTO.setUserId(userId);
			requestDataDTO.setAssignedGroup("0");
			requestDataDTO.setAssignedToId("None");
			requestDataDTO.setStatus("1");

			String conGroup = requestDataDTO.getConsumerGroup();
			String priority = requestDataDTO.getPriority();
			String dataSource = requestDataDTO.getDataSource();
			String environment = requestDataDTO.getEnvironment();

			requestData = dataRequestService
					.saveServiceRequestData(requestDataDTO, "MANUAL");
			request.getSession().setAttribute("requestId",
					requestData.getRequestId());
			// Get the dropdown data
			requestDataDTO = dataRequestService.getAllDropdownDetails();
			requestDataDTO.setStatus("1");
			requestDataDTO.setRequestId(requestData.getRequestId());
			// Getting Request Details By Passing Request Id
			TDMDataCreationDTO requestDataByReqId = searchService
					.getServiceRequestByReqId(requestData.getRequestId());
			model.addAttribute("requestDataByReqId", requestDataByReqId);
			TDMDataCreationDTO reqDetailDataByReqId = searchService
					.getReqDetailDataByReqId(requestData.getRequestId());
			reqDetailDataByReqId.getPropertyPolicyParamList();
			Gson gsonOb = new Gson();
			String autoPolicyLst = gsonOb.toJson(reqDetailDataByReqId
					.getAutoPolicyParamList());
			model.addAttribute("autoPolicyLst", autoPolicyLst);
			model.addAttribute("autoPolicyList",
					reqDetailDataByReqId.getAutoPolicyParamList());
			String propPolicyLst = gsonOb.toJson(reqDetailDataByReqId
					.getPropertyPolicyParamList());

			model.addAttribute("propPolicyLst", propPolicyLst);
			model.addAttribute("propertyPolicyList",
					reqDetailDataByReqId.getPropertyPolicyParamList());
			requestDataDTO.setUserId(userId);
			requestDataDTO = dataRequestService.getAllDropdownDetails();
			requestDataDTO.setUserId(userId);
			requestDataDTO.setRequestId(requestData.getRequestId());
			requestDataDTO.setRequestedBy(requestDataByReqId.getRequestedBy());
			requestDataDTO.setApprover(requestDataByReqId.getApprover());
			requestDataDTO.setSubject(requestDataByReqId.getSubject());
			requestDataDTO.setEnvDesc(requestDataByReqId.getEnvDesc());
			requestDataDTO.setApplicationOwner(requestDataByReqId
					.getApplicationOwner());
			requestDataDTO
					.setExpectedDate(requestDataByReqId.getExpectedDate());
			requestDataDTO.setCreatedOn(requestDataByReqId.getCreatedOn());
			requestDataDTO.setStatus(requestDataByReqId.getStatus());

			requestDataDTO.setConsumerGroup(conGroup);
			requestDataDTO.setPriority(priority);
			requestDataDTO.setDataSource(dataSource);
			requestDataDTO.setEnvironment(environment);
			//genearte scenario drop down values
			genarateScenarioDropDownValues( model );
			model.addAttribute("requestDataDTO", requestDataDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return requestDataDTO;
	}

	private void populateRequestData(TDMDataCreationDTO requestDataDTO,
			HttpServletRequest request) {
		String autoPolicye1 = request.getParameter("autoPolicye");
		String propPolicye1 = request.getParameter("propPolicye");
		String genDet1 = request.getParameter("genDet");

		AutoPolicyParamsDTO autoPolicyParamsDTO;
		PropertyPolicyParamsDTO propertyPolicyParamsDTO;
		ArrayList autoPolicyList = new ArrayList();
		ArrayList propPolicyList = new ArrayList();
		String autoPolicyStr = null;
		String propPolicyStr = null;
		String generalDetails = null;
		Enumeration<String> enumeration = request.getParameterNames();
		/*
		 * while (enumeration.hasMoreElements()) { Object objOri =
		 * enumeration.nextElement(); String param = (String) objOri; if
		 * (param.equals("autoPolicye")) { autoPolicyStr =
		 * request.getParameter(param); } if (param.equals("propPolicye")) {
		 * propPolicyStr = request.getParameter(param); } if
		 * (param.equals("genDet")) { generalDetails =
		 * request.getParameter(param); } }
		 */
		Gson gsonObj = new Gson();
		List<Map<String, String>> rowsData = (List<Map<String, String>>) gsonObj
				.fromJson(autoPolicye1, List.class);
		List<Map<String, String>> rowsData1 = (List<Map<String, String>>) gsonObj
				.fromJson(propPolicye1, List.class);
		List<Map<String, String>> genDetails = (List<Map<String, String>>) gsonObj
				.fromJson(genDet1, List.class);
		Iterator<Map<String, String>> autoList = rowsData.iterator();
		Iterator<Map<String, String>> propList = rowsData1.iterator();
		Iterator<Map<String, String>> genDet = genDetails.iterator();
		while (autoList.hasNext()) {
			autoPolicyParamsDTO = new AutoPolicyParamsDTO();
			Map<String, String> autoMap = (Map<String, String>) autoList.next();
			autoPolicyParamsDTO.setRiskState(autoMap.get("rs"));
			autoPolicyParamsDTO.setScenarioNo(autoMap.get("sno"));
			autoPolicyParamsDTO.setPaymentPlan(autoMap.get("pp"));
			autoPolicyParamsDTO.setNoOfDrivers(autoMap.get("nod"));
			autoPolicyParamsDTO.setNoOfPolicies(autoMap.get("nop"));
			autoPolicyParamsDTO.setNoOfVehicles(autoMap.get("nov"));
			autoPolicyParamsDTO.setAdditionalInformation(autoMap.get("ad"));
			autoPolicyParamsDTO.setEffectiveDate(autoMap.get("ed"));
			autoPolicyList.add(autoPolicyParamsDTO);
		}

		while (propList.hasNext()) {
			propertyPolicyParamsDTO = new PropertyPolicyParamsDTO();
			Map<String, String> propMap = (Map<String, String>) propList.next();
			propertyPolicyParamsDTO.setRiskState(propMap.get("rs"));
			propertyPolicyParamsDTO.setScenarioNo(propMap.get("sno"));
			propertyPolicyParamsDTO.setPaymentPlan(propMap.get("pp"));
			propertyPolicyParamsDTO.setNoOfPolicies(propMap.get("nop"));
			propertyPolicyParamsDTO.setAdditionalInformation(propMap.get("ad"));
			propertyPolicyParamsDTO.setPolicyType(propMap.get("pt"));

			propPolicyList.add(propertyPolicyParamsDTO);
		}
		while (genDet.hasNext()) {
			Map<String, String> genDetMap = (Map<String, String>) genDet.next();
			requestDataDTO.setServiceType(genDetMap.get("st"));
			requestDataDTO.setCreatedOn(genDetMap.get("co"));
			requestDataDTO.setRequestId(genDetMap.get("rid"));
			requestDataDTO.setStatus(genDetMap.get("ss"));
			requestDataDTO.setConsumerGroup(genDetMap.get("cg"));
			requestDataDTO.setPriority(genDetMap.get("pr"));
			requestDataDTO.setDataSource(genDetMap.get("ds"));
			requestDataDTO.setEnvironment(genDetMap.get("en"));
			requestDataDTO.setExpectedDate(genDetMap.get("ed"));
			requestDataDTO.setSubject(genDetMap.get("sb"));
			requestDataDTO.setRequestedBy(genDetMap.get("rb"));
			requestDataDTO.setApplicationOwner(genDetMap.get("ao"));
			requestDataDTO.setAssignedGroup(genDetMap.get("ag"));
			requestDataDTO.setApprover(genDetMap.get("ap"));
			requestDataDTO.setAssignedToId(genDetMap.get("at"));
			requestDataDTO.setStatusChange(genDetMap.get("sc"));
			requestDataDTO.setAutoPolicyType(genDetMap.get("apt"));
			requestDataDTO.setPropPolicyType(genDetMap.get("ppt"));
			
			requestDataDTO.setEnvDesc(genDetMap.get("envd"));
		}
		requestDataDTO.setAutoPolicyParamList(autoPolicyList);
		requestDataDTO.setPropertyPolicyParamList(propPolicyList);
	}

	public List<TDMDataCreationDTO> redirectFindRequest(String userId,
			String userRole, HttpServletRequest request) {
		List<TDMDataCreationDTO> reqListByUserId = null;
		try {
			Long totalRecords = 0L;
			PaginationUtil pagenation = new PaginationUtil();
			int recordsperpage = 10;
			int offSet = pagenation.getOffset(request, recordsperpage);
			totalRecords = tDMAdminService.getReqByUserIdRecordCount(userId);

			// List<TDMDataCreationDTO> reqListByUserId =
			// tDMAdminService.getRequestByUserId(userId);
			reqListByUserId = tDMAdminService.getRequestByUserId(userId,
					userRole, offSet, recordsperpage, true);

			pagenation.paginate(totalRecords, request,
					Double.valueOf(recordsperpage), recordsperpage);

			int noOfPages = (int) Math.ceil(totalRecords.doubleValue()
					/ recordsperpage);
			request.setAttribute("noOfPages", noOfPages);
			logger.info(MessageConstant.TDM_ADMIN_CTRL + "find request"
					+ MessageConstant.LOG_INFO_RETURN);
			return reqListByUserId;

		} catch (BaseException baseEx) {
			logger.error(MessageConstant.TDM_ADMIN_CTRL + "find request"
					+ MessageConstant.LOG_ERROR_EXCEPTION + baseEx);
			if (null != baseEx.getErrorCode()
					|| baseEx.getErrorCode().equalsIgnoreCase("null")) {

			}

		}
		return reqListByUserId;
	}

	/**
	 * 
	 * @param user
	 * @param reserveId
	 * @param result
	 * @param request
	 * @param response
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = AppConstant.WORK_FLOW_ENGINE, method = RequestMethod.GET)
	public String popupEmailGet(
			// @ModelAttribute(AppConstant.AUTO_EMAIL_DTO) DCAutoEmailDTO
			// autoEmailDTO, ModelMap model,
			@RequestParam(value = "requestId", required = true) String requestId,
			@RequestParam(value = "status", required = true) String status,
			@RequestParam(value = "assignedGroupId", required = false) String assignedGroupIdStr,
			@RequestParam(value = "assignedToId", required = false) String assignedToIdStr,
			@RequestParam(value = "expectedDate", required = false) String expectedDate,
			HttpServletRequest request, HttpServletResponse response,
			Principal principal) {
		String userId = null;
		if (null != (String) request.getSession().getAttribute("UserId")) {
			userId = (String) request.getSession().getAttribute("UserId");
		} else {
			return null;
		}
		Long assignedGroupId = null;
		String assignedToId = null;
		try {
			if (status != null && !status.isEmpty()) {
				Integer statusInt = Integer.valueOf(status);
				if (statusInt.intValue() == RequestStatusENUM.OPEN
						.getRequestStatusCode().intValue()) {
					assignedGroupId = Long.valueOf(assignedGroupIdStr);
					assignedToId = assignedToIdStr;
				}
				dataRequestService.processServiceRequest(userId, requestId,
						statusInt, assignedGroupId, assignedToId, "",
						expectedDate);
			}
		} catch (NumberFormatException | ServiceException | DAOException
				| NoSuchMessageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return AppConstant.TDM_INDEX;
	}

	public void processWorkFlow(String userId, TDMDataCreationDTO requestDataDTO) {

		logger.info("Submit		::	" + requestDataDTO);
		if (requestDataDTO != null) {
			String requestId = requestDataDTO.getRequestId();
			String status = requestDataDTO.getStatus();
			String assignedGroupIdStr = requestDataDTO.getAssignedGroup();
			String assignedToIdStr = requestDataDTO.getAssignedToId();
			Long assignedGroupId = null;
			String assignedToId = null;
			String comments = requestDataDTO.getStatusChange();
			String exceptedDate = requestDataDTO.getExpectedDate();

			try {
				if (status != null && !status.isEmpty()) {
					Integer statusInt = Integer.valueOf(status);
					if (statusInt.intValue() == RequestStatusENUM.OPEN
							.getRequestStatusCode().intValue()) {
						assignedGroupId = Long.valueOf(assignedGroupIdStr);
						assignedToId = assignedToIdStr;
					}
					dataRequestService.processServiceRequest(userId, requestId,
							statusInt, assignedGroupId, assignedToId, comments,
							exceptedDate);
				}
			} catch (NumberFormatException | ServiceException | DAOException
					| NoSuchMessageException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public List<TDMDataCreationDTO> redirectServiceDesk(
			HttpServletRequest request) {
		List<TDMDataCreationDTO> dashBoardRequestList = null;
		try {
			Long totalRecords = 0L;
			PaginationUtil pagenation = new PaginationUtil();
			int recordsperpage = 10;
			int offSet = pagenation.getOffset(request, recordsperpage);
			totalRecords = tDMAdminService.getReqRecordCount();
			dashBoardRequestList = tDMAdminService.getAllRequest(offSet,
					recordsperpage, true);
			pagenation.paginate(totalRecords, request,
					Double.valueOf(recordsperpage), recordsperpage);
			int noOfPages = (int) Math.ceil(totalRecords.doubleValue()
					/ recordsperpage);
			request.setAttribute("noOfPages", noOfPages);
		} catch (BaseException baseEx) {
			logger.error(MessageConstant.TDM_ADMIN_CTRL + "find request"
					+ MessageConstant.LOG_ERROR_EXCEPTION + baseEx);
			if (null != baseEx.getErrorCode()
					|| baseEx.getErrorCode().equalsIgnoreCase("null")) {
			}
		}
		return dashBoardRequestList;
	}

	@RequestMapping(value = "/dcActivityLog", method = RequestMethod.GET)
	public String getAccessLogByReqId(
			// @ModelAttribute(AppConstant.AUTO_EMAIL_DTO) DCAutoEmailDTO
			// autoEmailDTO, ModelMap model,
			@RequestParam(value = "requestId", required = true) String requestId,
			ModelMap model, HttpServletRequest request,
			HttpServletResponse response, Principal principal)
			throws DAOException {
		String userId = null;
		if (null != (String) request.getSession().getAttribute("UserId")) {
			userId = (String) request.getSession().getAttribute("UserId");
		} 
		if (null != requestId) {
			try {
				List<DCAccessLogDTO> accessLogList = dataRequestService
						.getAccessLogByReqId(requestId);
				model.addAttribute("accessLog", accessLogList);
				model.addAttribute("requestId", requestId);
				request.getSession().setAttribute("reqId", requestId);
			} catch (NumberFormatException | ServiceException
					| NoSuchMessageException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Audit Log
			
			activityAuditService.auditActivityLog(ActivityScreenName.DATA_GENERATION_MANUAL, ActivityAuditLog.ACTIVITY_LOG_TAB_CLICKED, userId, null);
			return "dcActivityLog";
		} else {
			activityAuditService.auditActivityLog(ActivityScreenName.DATA_GENERATION_MANUAL, ActivityAuditLog.ACTIVITY_LOG_TAB_CLICKED, userId, null);
			return "dcActivityLog";
		}

	}

	@RequestMapping(value = "/requestDataByReqId", method = RequestMethod.GET)
	public String findReqDataByReqId(
			@RequestParam(value = AppConstant.SEARCH, required = false) String search,
			@RequestParam(value = AppConstant.RESERVE, required = false) String reserve,
			@ModelAttribute(MessageConstant.TDM_REQUEST_DATA) TDMDataCreationDTO requestDataDTO,
			@RequestParam(value = "requestId", required = false) String requestId,
			@RequestParam(value = "serviceIdentifier", required = false) String serviceIdentifier,
			ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws ServiceException, JSONException {
		String userId = null;
		if (null != (String) request.getSession().getAttribute("UserId")) {
			userId = (String) request.getSession().getAttribute("UserId");
		} else {
			return null;
		}
		if (requestId == null || requestId.equals("") || requestId.isEmpty()) {

			if (null != (String) request.getSession().getAttribute("UserId")) {
				userId = (String) request.getSession().getAttribute("UserId");
			} else {
				return null;
			}
			TDMDataCreationDTO requestData = dataRequestService
					.getRequestGeneralDetails(userId);
			requestDataDTO = dataRequestService.getAllDropdownDetails();
			requestDataDTO.setUserId(userId);
			requestDataDTO.setApplicationOwner(requestData
					.getApplicationOwner());
			requestDataDTO.setApprover(requestData.getApprover());
			requestDataDTO.setRequestedBy(userId);

			/*Map<String, List<FieldListDTO>> autoDataMap = searchService
					.getAutoSearchPageData(UIScreenENUM.AUTO_DATA_SEARCH
							.getPageName());
			Map<String, List<FieldListDTO>> propertyDataMap = searchService
					.getAutoSearchPageData(UIScreenENUM.PROPERTY_DATA_SEARCH
							.getPageName());

			model.addAttribute("autoRiskStateList",
					autoDataMap.get(AUTO_RISK_STATE));
			model.addAttribute("autoPaymentPlanList",
					autoDataMap.get(AUTO_PAYMENT_PLAN));
			model.addAttribute("propertyRiskStateList",
					propertyDataMap.get(PROPERTY_RISK_STATE));
			model.addAttribute("propertyPaymentPlanList",
					propertyDataMap.get(PROPERTY_PAYMENT_PLAN));*/

			List<PropertyPolicyParamsDTO> list = new ArrayList<PropertyPolicyParamsDTO>();
			list.add(new PropertyPolicyParamsDTO());

			requestDataDTO.setPropertyPolicyParamList(list);

			List<AutoPolicyParamsDTO> list1 = new ArrayList<AutoPolicyParamsDTO>();
			list1.add(new AutoPolicyParamsDTO());

			requestDataDTO.setPropertyPolicyParamList(list);
			requestDataDTO.setAutoPolicyParamList(list1);
			model.addAttribute("requestDataDTO", requestDataDTO);
			if(serviceIdentifier.equalsIgnoreCase("myRequest")){
				activityAuditService.auditActivityLog(ActivityScreenName.MY_REQUESTS, ActivityAuditLog.REQUEST_ID_CLICKED, userId, requestId);
			}else if(serviceIdentifier.equalsIgnoreCase("activityLog")){
				activityAuditService.auditActivityLog(ActivityScreenName.DATA_GENERATION_MANUAL, ActivityAuditLog.REQUEST_ID_CLICKED, userId, requestId);
			}else{
				activityAuditService.auditActivityLog(ActivityScreenName.SERVICE_DESK, ActivityAuditLog.REQUEST_ID_CLICKED, userId, requestId);
			}
			return "requestTab";

		}

		TDMDataCreationDTO requestDataByReqId = searchService
				.getServiceRequestByReqId(requestId);
		model.addAttribute("requestDataByReqId", requestDataByReqId);

		TDMDataCreationDTO reqDetailDataByReqId = searchService
				.getReqDetailDataByReqId(requestId);

		reqDetailDataByReqId.getPropertyPolicyParamList();
		model.addAttribute("autoPolicyList",
				reqDetailDataByReqId.getAutoPolicyParamList());
		model.addAttribute("propertyPolicyList",
				reqDetailDataByReqId.getPropertyPolicyParamList());
		Gson gsonOb = new Gson();
		String autoPolicyLst = gsonOb.toJson(reqDetailDataByReqId
				.getAutoPolicyParamList());
		model.addAttribute("autoPolicyLst", autoPolicyLst);
		model.addAttribute("autoPolicyList",
				reqDetailDataByReqId.getAutoPolicyParamList());
		String propPolicyLst = gsonOb.toJson(reqDetailDataByReqId
				.getPropertyPolicyParamList());
		requestDataDTO.setUserId(userId);
		model.addAttribute("propPolicyLst", propPolicyLst);
		requestDataDTO = dataRequestService.getAllDropdownDetails();
		requestDataDTO.setUserId(userId);
		requestDataDTO.setRequestId(requestId);
		requestDataDTO.setRequestedBy(requestDataByReqId.getRequestedBy());
		requestDataDTO.setApprover(requestDataByReqId.getApprover());
		requestDataDTO.setSubject(requestDataByReqId.getSubject());
		requestDataDTO.setApplicationOwner(requestDataByReqId
				.getApplicationOwner());
		requestDataDTO.setExpectedDate(requestDataByReqId.getExpectedDate());
		requestDataDTO.setCreatedOn(requestDataByReqId.getCreatedOn());
		requestDataDTO.setStatus(requestDataByReqId.getStatus());

		requestDataDTO.setAssignedToId(requestDataByReqId.getAssignedToId());
		requestDataDTO.setAssignedGroup(requestDataByReqId.getAssignedGroup());
		requestDataDTO.setConsumerGroup(requestDataByReqId.getConsumerGroup());
		requestDataDTO.setPriority(requestDataByReqId.getPriority());
		requestDataDTO.setDataSource(requestDataByReqId.getDataSource());
		requestDataDTO.setEnvironment(requestDataByReqId.getEnvironment());
		requestDataDTO.setEnvDesc(requestDataByReqId.getEnvDesc());

		request.getSession().setAttribute("timestamp",
				requestDataByReqId.getCreatedOn());
		model.addAttribute("requestDataDTO", requestDataDTO);
		model.addAttribute("assignedGroupList",
				requestDataDTO.getAssignedGroupList());
		model.addAttribute("consumerGroupList",
				requestDataDTO.getConsumerGroupList());
		model.addAttribute("dataSourceList", requestDataDTO.getDataSourceList());
		model.addAttribute("supportUserList",
				requestDataDTO.getSupportUserList());
		model.addAttribute("environmentList",
				requestDataDTO.getEnvironmentList());
		model.addAttribute("requestStatusList",
				requestDataDTO.getRequestStatusList());
		model.addAttribute("requestPriortyList",
				requestDataDTO.getRequestPriorityList());
		model.addAttribute("requestStatusList",
				requestDataDTO.getRequestStatusList());
		model.addAttribute("serviceIdentifier", serviceIdentifier);
		genarateScenarioDropDownValues(model ) ;
		
		if(serviceIdentifier.equalsIgnoreCase("myRequest")){
				activityAuditService.auditActivityLog(ActivityScreenName.MY_REQUESTS, ActivityAuditLog.REQUEST_ID_CLICKED, userId, requestId);
			}else if(serviceIdentifier.equalsIgnoreCase("activityLog")){
				activityAuditService.auditActivityLog(ActivityScreenName.DATA_GENERATION_MANUAL, ActivityAuditLog.REQUEST_ID_CLICKED, userId, requestId);
			}else{
				activityAuditService.auditActivityLog(ActivityScreenName.SERVICE_DESK, ActivityAuditLog.REQUEST_ID_CLICKED, userId, requestId);
			}
		return "requestTab";
	}
	public void genarateScenarioDropDownValues( ModelMap model ) throws JSONException
	{
		Map<String, List<FieldListDTO>> autoDataMap = searchService
				.getAutoSearchPageData(UIScreenENUM.AUTO_DATA_SEARCH
						.getPageName());
		Map<String, List<FieldListDTO>> propertyDataMap = searchService
				.getAutoSearchPageData(UIScreenENUM.PROPERTY_DATA_SEARCH
						.getPageName());
		List<FieldListDTO> autoList=autoDataMap.get(AUTO_RISK_STATE);
		List<FieldListDTO>autoPayment=autoDataMap.get(AUTO_PAYMENT_PLAN);
		List<FieldListDTO>propertyPolicyType=propertyDataMap.get(PROPERTY_POLICY_TYPE);
		List<FieldListDTO>propertyRiskState=propertyDataMap.get(PROPERTY_RISK_STATE);
		List<FieldListDTO>propertyPaymentPlan=propertyDataMap.get(PROPERTY_PAYMENT_PLAN);
		Gson gsonObj = new Gson();
		String jsonObjAutoCountry= gsonObj.toJson(autoList);
		String jsonObjAutoPaymentPlan= gsonObj.toJson(autoPayment);
		String jsonObjjPropertyPolicyType= gsonObj.toJson(propertyPolicyType);
		String jsonObjjPropertyRiskState= gsonObj.toJson(propertyRiskState);
		String jsonObjjPropertyPaymentPlan= gsonObj.toJson(propertyPaymentPlan);
		
         model.addAttribute("AutoCountryList", jsonObjAutoCountry);
         model.addAttribute("AutoPaymentPlan", jsonObjAutoPaymentPlan);
         model.addAttribute("PropertyPolicyType", jsonObjjPropertyPolicyType);
         model.addAttribute("PropertyRiskState", jsonObjjPropertyRiskState);
         model.addAttribute("PropertyPaymentPlan", jsonObjjPropertyPaymentPlan);
	}

}
