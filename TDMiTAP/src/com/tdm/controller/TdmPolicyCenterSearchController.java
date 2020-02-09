package com.tdm.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tdm.exception.BaseException;
import com.tdm.exception.ServiceException;
import com.tdm.model.DTO.PctlCountryDTO;
import com.tdm.model.DTO.PctlPolicycontactroleDTO;
import com.tdm.model.DTO.PctlStateDTO;
import com.tdm.model.DTO.TdmPolicyCenterSearchDTO;
import com.tdm.model.DTO.TdmPolicyCenterSearchResultDTO;
import com.tdm.service.TdmPolicyCenterSearchService;
import com.tdm.util.PaginationUtil;

/**
 * @author
 * @version 1.0
 */

@Controller
//@Scope("session")
public class TdmPolicyCenterSearchController
{

	final static Logger logger = Logger.getLogger(TdmPolicyCenterSearchController.class);
	private int noOfRecForpage = 10;
	private TdmPolicyCenterSearchDTO tdmPolicyCenterSearchDTO1 = null;
	
	@Resource(name = "tdmPolicyCenterSearchService")
	TdmPolicyCenterSearchService tdmPolicyCenterSearchService;

	@RequestMapping(value = "/policyProp", method = RequestMethod.GET)
	public String policyPropGet(
			@ModelAttribute("tdmPolicyCenterSearchDTO") TdmPolicyCenterSearchDTO tdmPolicyCenterSearchDTO,
			ModelMap model, HttpServletRequest request, HttpServletResponse response,
			Principal principal) throws BaseException {
		List<PctlCountryDTO> pctlcountryDto = tdmPolicyCenterSearchService.getCountrycodeValues();
		List<PctlStateDTO> pctlstateDto = tdmPolicyCenterSearchService.getStatecodeValues();
		model.addAttribute("pctlCountrydos", pctlcountryDto);
		model.addAttribute("pctlStatedos", pctlstateDto);
		return "policyCenterSearch";

	}
	
	@RequestMapping(value = "/policyProp", method = RequestMethod.POST)
	public String policyAutoPost(
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "reserve", required = false) String reserve,
			@ModelAttribute("tdmPolicyCenterSearchDTO") TdmPolicyCenterSearchDTO tdmPolicyCenterSearchDTO,
			ModelMap model, HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		String userName = null;
		String reserveFlag = null;
		int countPerPage = 0;
		TdmPolicyCenterSearchDTO tdmPolicyCenterSearchDTOtemp = null;
		try {
			if (null != (String) request.getSession().getAttribute("UserId")) {
				userName = (String) request.getSession().getAttribute("UserId");
			}
			Long totalRecords = 0L;
			model.addAttribute("result", getSearchCriteria(tdmPolicyCenterSearchDTO));
			PaginationUtil pagenation = new PaginationUtil();
			int recordsperpage = 10; // Integer.valueOf(tdmPolicyPropertySearchDTO.getSearchRecordsNo());
			noOfRecForpage = recordsperpage;
			tdmPolicyCenterSearchDTO1 = tdmPolicyCenterSearchDTO;
			int offSet = pagenation.getOffset(request, recordsperpage);
			if (null != search) {
				if (logger.isInfoEnabled()) {
					logger.info("policy center controller searching records  : ");
				}if(StringUtils.isNotEmpty(tdmPolicyCenterSearchDTO.getGender()) &&tdmPolicyCenterSearchDTO.getGender().equalsIgnoreCase("Both")){
					tdmPolicyCenterSearchDTO.setGender(null);
				}
				
				totalRecords = tdmPolicyCenterSearchService.getTotalRecordsCount(tdmPolicyCenterSearchDTO,userName);
				tdmPolicyCenterSearchDTOtemp = tdmPolicyCenterSearchService
						.searchPolicyCenter(tdmPolicyCenterSearchDTO,
								offSet, recordsperpage, true, userName);
				

			} else if (null != reserve) {
				if (logger.isInfoEnabled()) {
					logger.info("property controller reserving records  : ");
				}
				StringBuffer strBuffer = new StringBuffer();
				if(StringUtils.isEmpty(tdmPolicyCenterSearchDTO.getTestCaseId()) && StringUtils.isEmpty(tdmPolicyCenterSearchDTO.getTestCaseName())){
					strBuffer.append("Test case Id and Name are manadatory");
				}else if(StringUtils.isEmpty(tdmPolicyCenterSearchDTO.getTestCaseId())){
					strBuffer.append("Test case Id is manadatory");
				}else if(StringUtils.isEmpty(tdmPolicyCenterSearchDTO.getTestCaseName())){
					strBuffer.append("Test case Name is manadatory");
				}
				if(!strBuffer.toString().isEmpty()){
					model.addAttribute("error", strBuffer.toString());
					return "policyCenterSearch";
				}
				tdmPolicyCenterSearchDTO
						.setSearchCriti(getSearchCriteria(tdmPolicyCenterSearchDTO));
				int cntRec = tdmPolicyCenterSearchService.saveReservedData(
						tdmPolicyCenterSearchDTO, userName,
						tdmPolicyCenterSearchDTO.getPolicyNumber()+"");
				tdmPolicyCenterSearchDTOtemp = tdmPolicyCenterSearchService
						.searchPolicyCenter(tdmPolicyCenterSearchDTO,
								offSet, recordsperpage, true, userName);
				reserveFlag = cntRec + " Record(s) successfully reserved for Test Case Id : "
						+ tdmPolicyCenterSearchDTO.getTestCaseId() + " and Test Case Name : "
						+ tdmPolicyCenterSearchDTO.getTestCaseName();
				tdmPolicyCenterSearchDTO.setTestCaseId(null);
				tdmPolicyCenterSearchDTO.setTestCaseName(null);
			}
			/*if (null != tdmPolicyCenterSearchDTO.getListTdmPolicyCenterSearchResultDTO()
					&& tdmPolicyCenterSearchDTO.getListTdmPolicyCenterSearchResultDTO().size() >= 25) {
				totalRecords = 10L;
			} else {
				totalRecords = 25L;
			}*/
			// set the size to no of records variable
			if (null != tdmPolicyCenterSearchDTOtemp.getListTdmPolicyCenterSearchResultDTO()
					&& tdmPolicyCenterSearchDTOtemp.getListTdmPolicyCenterSearchResultDTO().size() > 0) {
				countPerPage = tdmPolicyCenterSearchDTOtemp.getListTdmPolicyCenterSearchResultDTO()
						.size();
			}
			pagenation.paginate(totalRecords, request, Double.valueOf(recordsperpage),
					recordsperpage);
			int noOfPages = (int) Math.ceil(totalRecords.doubleValue() / recordsperpage);

			///tdmPolicyCenterSearchDTO1 = tdmPolicyCenterSearchDTO;
			request.setAttribute("noOfPages", noOfPages);
			model.addAttribute("reserveFlag", reserveFlag);
			model.addAttribute("totalRecords", countPerPage);
			//model.addAttribute("count", tdmPolicyCenterSearchDTO.getCount());
			/*model.addAttribute("tdmPolicyPropertySearchResultDTOList",
					tdmPolicyCenterSearchDTOtemp.getListTdmPolicyCenterSearchResultDTO());*/
			tdmPolicyCenterSearchDTO1.setListTdmPolicyCenterSearchResultDTO(tdmPolicyCenterSearchDTOtemp.getListTdmPolicyCenterSearchResultDTO());
			tdmPolicyCenterSearchDTO.setListTdmPolicyCenterSearchResultDTO(tdmPolicyCenterSearchDTOtemp.getListTdmPolicyCenterSearchResultDTO());
			List<PctlCountryDTO> pctlcountryDto = tdmPolicyCenterSearchService.getCountrycodeValues();
			List<PctlStateDTO> pctlstateDto = tdmPolicyCenterSearchService.getStatecodeValues();
			model.addAttribute("pctlCountrydos", pctlcountryDto);
			model.addAttribute("pctlStatedos", pctlstateDto);
			model.addAttribute("tdmPolicyCenterSearchDTO",
					tdmPolicyCenterSearchDTO);
			return "policyCenterSearch";
		} catch (BaseException baseEx) {
			if (baseEx.getErrorCode() != null) {
				model.addAttribute("error", baseEx.getErrorCode() + " : " + baseEx.getRootCause());
				return "policyCenterSearch";
			}
		}
		return "policyCenterSearch";
	}
	
	@Autowired
	private MessageSource messageSource;

	public String getSearchCriteria(TdmPolicyCenterSearchDTO tdmPolicyPropertySearchDTO) {
		if (logger.isInfoEnabled()) {
			logger.info("property controller getting search criterai  : ");
		}
		String searchCriteria = "";

		if (null != tdmPolicyPropertySearchDTO) {
			searchCriteria += "Search Result for: ";

			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO.getProductCode())) {

				searchCriteria += "Policy Type : "
						+ tdmPolicyPropertySearchDTO.getProductCode() + "  ";
			}

			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO.getAccountNumber())) {
				searchCriteria += " + "
						+ " Account Number : "
						+ tdmPolicyPropertySearchDTO.getAccountNumber() + "  ";
			}
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO.getPolicyNumber())) {
				searchCriteria += " + Policy Number : " + tdmPolicyPropertySearchDTO.getPolicyNumber() + "  ";
			}
			
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO.getStatus())) {
				searchCriteria += " + "
						+  "Account Status : "
						+ tdmPolicyPropertySearchDTO.getStatus() + "  ";
			}
			
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO.getGender())) {
				searchCriteria += " + "
						+  "Gender : "
						+ tdmPolicyPropertySearchDTO.getGender().equalsIgnoreCase("1") != null ? "FeMale" :(tdmPolicyPropertySearchDTO.getGender().equalsIgnoreCase("2") ? "Male" :"Both") + "  ";
			}
			
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO.getCountry())) {
				try {
					List<PctlCountryDTO> pctlcountryDto = tdmPolicyCenterSearchService.getCountrycodeValues();
					for(PctlCountryDTO dto : pctlcountryDto){
						if(dto.getId() == Long.parseLong(tdmPolicyPropertySearchDTO.getCountry())){
					searchCriteria += " + "
							+  "Country : "
							+ dto.getName() + "  ";
					break;
						}
					}
				} catch (ServiceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO.getState())) {

				try {
					List<PctlStateDTO> pctlstateDto = tdmPolicyCenterSearchService.getStatecodeValues();
					for(PctlStateDTO dto : pctlstateDto){
						if(dto.getId() == Long.parseLong(tdmPolicyPropertySearchDTO.getState())){
					searchCriteria += " + "
							+  "State : "
							+ dto.getName() + "  ";
					break;
						}
					}
				} catch (ServiceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO.getInsurerType())) {
				try {
					List<PctlPolicycontactroleDTO> pctlPolicycontactroleDTO = tdmPolicyCenterSearchService.getInsurerTypeValues();
					for(PctlPolicycontactroleDTO dto : pctlPolicycontactroleDTO){
						if(dto.getId() == Long.parseLong(tdmPolicyPropertySearchDTO.getInsurerType())){
					searchCriteria += " + "
							+  "Insurer Type : "
							+ dto.getTypecode() + "  ";
					break;
						}
					}
				} catch (ServiceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			

		}

		return searchCriteria;
	}


@RequestMapping(value = "/policyProp", method = RequestMethod.GET, params = "page")
public String policyAutoPagiNation(
		@ModelAttribute("tdmPolicyCenterSearchDTO") TdmPolicyCenterSearchDTO tdmPolicyCenterSearchDTO,
		ModelMap model, HttpServletRequest request, HttpServletResponse response) {
	TdmPolicyCenterSearchDTO tdmPolicyCenterSearchDTOtemp = null;
	
	int countPerPage = 0;
	String userName = null;
	try {
		if (null != (String) request.getSession().getAttribute("UserId")) {
			userName = (String) request.getSession().getAttribute("UserId");
		}

		tdmPolicyCenterSearchDTO = tdmPolicyCenterSearchDTO1;
		model.addAttribute("result", getSearchCriteria(tdmPolicyCenterSearchDTO));
		Long totalRecords = 0L;
		PaginationUtil pagenation = new PaginationUtil();
		int recordsperpage = noOfRecForpage;
		int offSet = pagenation.getOffset(request, recordsperpage);
		totalRecords = tdmPolicyCenterSearchService.getTotalRecordsCount(tdmPolicyCenterSearchDTO,userName);
		tdmPolicyCenterSearchDTOtemp = tdmPolicyCenterSearchService
				.searchPolicyCenter(tdmPolicyCenterSearchDTO, offSet,
						recordsperpage, true, userName);
		//totalRecords = tdmPolicyCenterSearchService.getTotalRecordsCount(tdmPolicyCenterSearchDTO);
		// set the size to no of records variable
		if (null != tdmPolicyCenterSearchDTO.getListTdmPolicyCenterSearchResultDTO()
				&& tdmPolicyCenterSearchDTO.getListTdmPolicyCenterSearchResultDTO().size() > 0) {
			countPerPage = tdmPolicyCenterSearchDTO.getListTdmPolicyCenterSearchResultDTO()
					.size();
		}
		pagenation.paginate(totalRecords, request, (Double.valueOf(recordsperpage)),
				recordsperpage);
		int noOfPages = (int) Math.ceil(totalRecords.doubleValue() / recordsperpage);
		request.setAttribute("noOfPages", noOfPages);
		model.addAttribute("totalRecords", countPerPage);
		model.addAttribute("tdmPolicyPropertySearchResultDTOList",
				tdmPolicyCenterSearchDTOtemp.getListTdmPolicyCenterSearchResultDTO());
		tdmPolicyCenterSearchDTO.setListTdmPolicyCenterSearchResultDTO(tdmPolicyCenterSearchDTOtemp.getListTdmPolicyCenterSearchResultDTO());
		List<PctlCountryDTO> pctlcountryDto = tdmPolicyCenterSearchService.getCountrycodeValues();
		List<PctlStateDTO> pctlstateDto = tdmPolicyCenterSearchService.getStatecodeValues();
		model.addAttribute("pctlCountrydos", pctlcountryDto);
		model.addAttribute("pctlStatedos", pctlstateDto);
		model.addAttribute("tdmPolicyCenterSearchDTO", tdmPolicyCenterSearchDTO1);
	} catch (BaseException e) {
		e.printStackTrace();
		return "policyCenterSearch";
	}
	return "policyCenterSearch";
}

@RequestMapping(value = "/policyProp", method = RequestMethod.POST, params = "export")
public ModelAndView policyPropExport(
		@ModelAttribute("tdmPolicyCenterSearchDTO") TdmPolicyCenterSearchDTO tdmPolicyCenterSearchDTO,
		ModelMap model, HttpServletRequest request, HttpServletResponse response) {
	if (logger.isInfoEnabled()) {
		logger.info("property controller click in Export button  : ");
	}
	String UserId = (String) request.getSession().getAttribute("UserId");
	List<TdmPolicyCenterSearchResultDTO> list = null;
	model.addAttribute("result", getSearchCriteria(tdmPolicyCenterSearchDTO));
	if (null != tdmPolicyCenterSearchDTO.getListTdmPolicyCenterSearchResultDTO()
			&& 0 < tdmPolicyCenterSearchDTO.getListTdmPolicyCenterSearchResultDTO().size()) {
		list = new ArrayList<TdmPolicyCenterSearchResultDTO>();
		for (int i = 0; i < tdmPolicyCenterSearchDTO
				.getListTdmPolicyCenterSearchResultDTO().size(); i++) {
			if (null != tdmPolicyCenterSearchDTO.getListTdmPolicyCenterSearchResultDTO()
					.get(i).getReservedYN()) {
				tdmPolicyCenterSearchDTO.getListTdmPolicyCenterSearchResultDTO().get(i)
						.setUserId(UserId);
				list.add(tdmPolicyCenterSearchDTO.getListTdmPolicyCenterSearchResultDTO()
						.get(i));
			}
		}
	}
	/*return new ModelAndView(MessageConstant.TDM_PROV_EXPO_XLS,
			MessageConstant.TDM_PROV_RESULT_EXPO_DTOS, list);*/
	return new ModelAndView("searchPolicyListExcelViewNew",
			"tdmPolicyCenterSearchDTO", list);
}			 
}

/*	@RequestMapping(value = "/policyProp", method = RequestMethod.POST)
	public String policyAutoPost(
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "reserve", required = false) String reserve,
			@ModelAttribute("tdmPolicyCenterSearchDTO") TdmPolicyCenterSearchDTO tdmPolicyCenterSearchDTO,
			ModelMap model, HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		String userName = null;
		String reserveFlag = null;
		int countPerPage = 0;
		try {
			if (null != (String) request.getSession().getAttribute("UserId")) {
				userName = (String) request.getSession().getAttribute("UserId");
			}
			Long totalRecords = 0L;
			if (StringUtils.isNotEmpty(tdmPolicyCenterSearchDTO.getAddYearBuilt())
					|| StringUtils.isNotEmpty(tdmPolicyCenterSearchDTO.getAddConType())
					|| StringUtils.isNotEmpty(tdmPolicyCenterSearchDTO.getZipCode())
					|| StringUtils.isNotEmpty(tdmPolicyCenterSearchDTO.getLeinIndi())
					|| StringUtils.isNotEmpty(tdmPolicyCenterSearchDTO.getOptnlCovge())) {
				tdmPolicyCenterSearchDTO.setShowHideFlag(true);
			}
			if (StringUtils.isNotEmpty(tdmPolicyCenterSearchDTO.getAddDocType())) {
				tdmPolicyCenterSearchDTO.setShowHideFlagDoc(true);
			}
			model.addAttribute("result", getSearchCriteria(tdmPolicyCenterSearchDTO));
			PaginationUtil pagenation = new PaginationUtil();
			int recordsperpage = 25; // Integer.valueOf(tdmPolicyPropertySearchDTO.getSearchRecordsNo());
			noOfRecForpage = recordsperpage;
			int offSet = pagenation.getOffset(request, recordsperpage);
			if (null != search) {
				if (logger.isInfoEnabled()) {
					logger.info("property controller searching records  : ");
				}
				tdmPolicyCenterSearchDTO = tdmPolicyAutoSearchService
						.searchPolicyPropRecordsByPolicySearchNew(tdmPolicyCenterSearchDTO,
								offSet, recordsperpage, true, userName);

			} else if (null != reserve) {
				if (logger.isInfoEnabled()) {
					logger.info("property controller reserving records  : ");
				}
				tdmPolicyCenterSearchDTO
						.setSearchCriti(getSearchCriteria(tdmPolicyCenterSearchDTO));
				int cntRec = tdmPolicyAutoSearchService.saveReservedData(
						tdmPolicyCenterSearchDTO, userName,
						tdmPolicyCenterSearchDTO.getEnvType());
				tdmPolicyCenterSearchDTO = tdmPolicyAutoSearchService
						.searchPolicyPropRecordsByPolicySearchNew(tdmPolicyCenterSearchDTO,
								offSet, recordsperpage, true, userName);
				reserveFlag = cntRec + " Record(s) successfully reserved for Test Case Id : "
						+ tdmPolicyCenterSearchDTO.getTestCaseId() + " and Test Case Name : "
						+ tdmPolicyCenterSearchDTO.getTestCaseName();
				tdmPolicyCenterSearchDTO.setTestCaseId(null);
				tdmPolicyCenterSearchDTO.setTestCaseName(null);
			}
			if (null != tdmPolicyCenterSearchDTO.getTdmPolicyPropertySearchResultDTOList()
					&& tdmPolicyCenterSearchDTO.getTdmPolicyPropertySearchResultDTOList().size() >= 25) {
				totalRecords = 10L;
			} else {
				totalRecords = 25L;
			}
			// set the size to no of records variable
			if (null != tdmPolicyCenterSearchDTO.getTdmPolicyPropertySearchResultDTOList()
					&& tdmPolicyCenterSearchDTO.getTdmPolicyPropertySearchResultDTOList().size() > 0) {
				countPerPage = tdmPolicyCenterSearchDTO.getTdmPolicyPropertySearchResultDTOList()
						.size();
			}
			pagenation.paginate(totalRecords, request, Double.valueOf(recordsperpage),
					recordsperpage);
			int noOfPages = (int) Math.ceil(totalRecords.doubleValue() / recordsperpage);

			tdmPolicyPropertySearchDTO1 = tdmPolicyCenterSearchDTO;
			request.setAttribute("noOfPages", noOfPages);
			model.addAttribute("reserveFlag", reserveFlag);
			model.addAttribute("totalRecords", countPerPage);
			model.addAttribute("count", tdmPolicyCenterSearchDTO.getCount());
			model.addAttribute("tdmPolicyPropertySearchResultDTOList",
					tdmPolicyCenterSearchDTO.getTdmPolicyPropertySearchResultDTOList());
			return "propertyPolicySearch";
		} catch (BaseException baseEx) {
			if (baseEx.getErrorCode() != null) {
				model.addAttribute("error", baseEx.getErrorCode() + " : " + baseEx.getRootCause());
				return "propertyPolicySearch";
			}
		}
		return "propertyPolicySearch";
	}

	@RequestMapping(value = "/policyProp", method = RequestMethod.GET, params = "page")
	public String policyAutoPagiNation(
			@ModelAttribute("tdmPolicyPropertySearchDTO") TdmPolicyPropertySearchDTO tdmPolicyPropertySearchDTO,
			ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		if (logger.isInfoEnabled()) {
			logger.info("property controller reserving records  : ");
		}
		int countPerPage = 0;
		String userName = null;
		try {
			if (null != (String) request.getSession().getAttribute("UserId")) {
				userName = (String) request.getSession().getAttribute("UserId");
			}
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO.getAddYearBuilt())
					|| StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO.getAddConType())
					|| StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO.getZipCode())
					|| StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO.getLeinIndi())
					|| StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO.getOptnlCovge())) {
				tdmPolicyPropertySearchDTO.setShowHideFlag(true);
			}
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO.getAddDocType())) {
				tdmPolicyPropertySearchDTO.setShowHideFlagDoc(true);
			}
			tdmPolicyPropertySearchDTO = tdmPolicyPropertySearchDTO1;
			model.addAttribute("result", getSearchCriteria(tdmPolicyPropertySearchDTO));
			Long totalRecords = 10L;
			PaginationUtil pagenation = new PaginationUtil();
			int recordsperpage = noOfRecForpage;
			int offSet = pagenation.getOffset(request, recordsperpage);
			tdmPolicyPropertySearchDTO = tdmPolicyAutoSearchService
					.searchPolicyPropRecordsByPolicySearchNew(tdmPolicyPropertySearchDTO, offSet,
							recordsperpage, true, userName);

			// set the size to no of records variable
			if (null != tdmPolicyPropertySearchDTO.getTdmPolicyPropertySearchResultDTOList()
					&& tdmPolicyPropertySearchDTO.getTdmPolicyPropertySearchResultDTOList().size() > 0) {
				countPerPage = tdmPolicyPropertySearchDTO.getTdmPolicyPropertySearchResultDTOList()
						.size();
			}
			pagenation.paginate(totalRecords, request, (Double.valueOf(recordsperpage)),
					recordsperpage);
			int noOfPages = (int) Math.ceil(totalRecords.doubleValue() / recordsperpage);
			request.setAttribute("noOfPages", noOfPages);
			model.addAttribute("totalRecords", countPerPage);
			model.addAttribute("tdmPolicyPropertySearchResultDTOList",
					tdmPolicyPropertySearchDTO.getTdmPolicyPropertySearchResultDTOList());
			model.addAttribute("tdmPolicyPropertySearchDTO", tdmPolicyPropertySearchDTO);
		} catch (BaseException e) {
			e.printStackTrace();
			return "propertyPolicySearch";
		}
		return "propertyPolicySearch";
	}

	@RequestMapping(value = "/policyPropBack", method = RequestMethod.GET)
	public String policyPropBack(
			@ModelAttribute("tdmPolicyPropertySearchDTO") TdmPolicyPropertySearchDTO tdmPolicyPropertySearchDTO,
			ModelMap model, HttpServletRequest request, HttpServletResponse response,
			Principal principal) {

		if (logger.isInfoEnabled()) {
			logger.info("property controller click in Back button  : ");
		}
		int countPerPage = 0;
		String reserveFlag = null;
		String userName = null;
		try {
			if (null != tdmPolicyPropertySearchDTO1) {
				if (null != (String) request.getSession().getAttribute("UserId")) {
					userName = (String) request.getSession().getAttribute("UserId");
				}
				if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO.getAddYearBuilt())
						|| StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO.getAddConType())
						|| StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO.getZipCode())
						|| StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO.getLeinIndi())
						|| StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO.getOptnlCovge())) {
					tdmPolicyPropertySearchDTO.setShowHideFlag(true);
				}
				if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO.getAddDocType())) {
					tdmPolicyPropertySearchDTO.setShowHideFlagDoc(true);
				}
				tdmPolicyPropertySearchDTO = tdmPolicyPropertySearchDTO1;
				model.addAttribute("result", getSearchCriteria(tdmPolicyPropertySearchDTO));
				Long totalRecords = 0L;
				PaginationUtil pagenation = new PaginationUtil();
				int recordsperpage = 25;
				noOfRecForpage = recordsperpage;
				int offSet = pagenation.getOffset(request, recordsperpage);
				tdmPolicyPropertySearchDTO = tdmPolicyAutoSearchService
						.searchPolicyPropRecordsByPolicySearchNew(tdmPolicyPropertySearchDTO,
								offSet, recordsperpage, true, userName);
				// set the size to no of records variable
				if (null != tdmPolicyPropertySearchDTO.getTdmPolicyPropertySearchResultDTOList()
						&& tdmPolicyPropertySearchDTO.getTdmPolicyPropertySearchResultDTOList()
								.size() > 0) {
					countPerPage = tdmPolicyPropertySearchDTO
							.getTdmPolicyPropertySearchResultDTOList().size();
				}
				pagenation.paginate(totalRecords, request, Double.valueOf(recordsperpage),
						recordsperpage);
				int noOfPages = (int) Math.ceil(totalRecords.doubleValue() / recordsperpage);
				request.setAttribute("noOfPages", noOfPages);
				model.addAttribute("reserveFlag", reserveFlag);
				model.addAttribute("count", tdmPolicyPropertySearchDTO.getCount());
				model.addAttribute("totalRecords", countPerPage);
				model.addAttribute("tdmPolicyPropertySearchDTO", tdmPolicyPropertySearchDTO);
				model.addAttribute("tdmPolicyPropertySearchResultDTOList",
						tdmPolicyPropertySearchDTO.getTdmPolicyPropertySearchResultDTOList());
				return "propertyPolicySearch";
			} else {
				return "redirect:policyProp";
			}
		} catch (BaseException e) {
			e.printStackTrace();
			return "propertyPolicySearch";
		}
	}

	@RequestMapping(value = "/policyProp", method = RequestMethod.POST, params = "export")
	public ModelAndView policyPropExport(
			@ModelAttribute("tdmPolicyPropertySearchDTO") TdmPolicyPropertySearchDTO tdmPolicyPropertySearchDTO,
			ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		if (logger.isInfoEnabled()) {
			logger.info("property controller click in Export button  : ");
		}
		String UserId = (String) request.getSession().getAttribute("UserId");
		List<TdmPolicyPropertySearchResultDTO> list = null;
		model.addAttribute("result", getSearchCriteria(tdmPolicyPropertySearchDTO));
		if (null != tdmPolicyPropertySearchDTO.getTdmPolicyPropertySearchResultDTOList()
				&& 0 < tdmPolicyPropertySearchDTO.getTdmPolicyPropertySearchResultDTOList().size()) {
			list = new ArrayList<TdmPolicyPropertySearchResultDTO>();
			for (int i = 0; i < tdmPolicyPropertySearchDTO
					.getTdmPolicyPropertySearchResultDTOList().size(); i++) {
				if (null != tdmPolicyPropertySearchDTO.getTdmPolicyPropertySearchResultDTOList()
						.get(i).getReservedYN()) {
					tdmPolicyPropertySearchDTO.getTdmPolicyPropertySearchResultDTOList().get(i)
							.setUserId(UserId);
					list.add(tdmPolicyPropertySearchDTO.getTdmPolicyPropertySearchResultDTOList()
							.get(i));
				}
			}
		}
		return new ModelAndView("searchPolicyPropListExcelView",
				"tdmPolicyPropertySearchResultDTOs", list);
	}

	@Autowired
	private MessageSource messageSource;

	public String getSearchCriteria(TdmPolicyPropertySearchDTO tdmPolicyPropertySearchDTO) {
		if (logger.isInfoEnabled()) {
			logger.info("property controller getting search criterai  : ");
		}
		String searchCriteria = "";

		if (null != tdmPolicyPropertySearchDTO) {
			searchCriteria += "Search Result for: ";

			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO.getEnvType())) {

				searchCriteria += messageSource.getMessage("label.env", null, null) + " : "
						+ tdmPolicyPropertySearchDTO.getEnvType() + "  ";
			}

			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO.getPolicyStage())) {
				searchCriteria += " + "
						+ messageSource.getMessage("label.policy.stage", null, null) + " : "
						+ tdmPolicyPropertySearchDTO.getPolicyStage() + "  ";
			}
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO.getPolicyTerm())) {
				searchCriteria += " + " + messageSource.getMessage("label.policy.term", null, null)
						+ " : " + tdmPolicyPropertySearchDTO.getPolicyTerm() + "  ";
			}
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO.getPolicyState())) {
				searchCriteria += " + "
						+ messageSource.getMessage("label.policy.state", null, null) + " : "
						+ tdmPolicyPropertySearchDTO.getPolicyState() + "  ";
			}
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO.getAddproductType())) {
				searchCriteria += " + "
						+ messageSource.getMessage("label.policy.prodType", null, null) + " : "
						+ tdmPolicyPropertySearchDTO.getAddproductType() + "  ";
			}
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO.getAddPolicyCovge())) {
				searchCriteria += " + "
						+ messageSource.getMessage("label.policy.poliCov", null, null) + " : "
						+ tdmPolicyPropertySearchDTO.getAddPolicyCovge() + "  ";
			}
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO.getAddPayMethod())) {
				searchCriteria += " + "
						+ messageSource.getMessage("label.policy.payType", null, null) + " : "
						+ tdmPolicyPropertySearchDTO.getAddPayMethod() + "  ";
			}
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO.getAddPayReq())) {
				String yorn = null;
				if (tdmPolicyPropertySearchDTO.getAddPayReq().equalsIgnoreCase("Y")) {
					yorn = "Yes";
				} else if (tdmPolicyPropertySearchDTO.getAddPayReq().equalsIgnoreCase("N")) {
					yorn = "No";
				} else {
					yorn = "Any";
				}

				searchCriteria += " + "
						+ messageSource.getMessage("label.policy.assoPayReqAdd", null, null)
						+ " : " + yorn + "  ";
			}

			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO.getAddDocReq())) {
				String yorn = null;
				if (tdmPolicyPropertySearchDTO.getAddDocReq().equalsIgnoreCase("Y")) {
					yorn = "Yes";
				} else if (tdmPolicyPropertySearchDTO.getAddDocReq().equalsIgnoreCase("N")) {
					yorn = "No";
				} else {
					yorn = "Any";
				}

				searchCriteria += " + "
						+ messageSource.getMessage("label.policy.assoDoc", null, null) + " : "
						+ yorn + "  ";
			}

			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO.getAddDocType())) {
				searchCriteria += " + "
						+ messageSource.getMessage("label.policy.docTypeAdd", null, null) + " : "
						+ tdmPolicyPropertySearchDTO.getAddDocType() + "  ";
			}

			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO.getAddYearBuilt())) {
				searchCriteria += " + " + messageSource.getMessage("label.policy.year", null, null)
						+ " : " + tdmPolicyPropertySearchDTO.getAddYearBuilt() + "  ";
			}
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO.getAddConType())) {
				searchCriteria += " + "
						+ messageSource.getMessage("label.policy.consType", null, null) + " : "
						+ tdmPolicyPropertySearchDTO.getAddConType() + "  ";
			}
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO.getZipCode())) {
				searchCriteria += " + "
						+ messageSource.getMessage("label.policy.zipCode", null, null) + " : "
						+ tdmPolicyPropertySearchDTO.getZipCode() + "  ";
			}
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO.getLeinIndi())) {

				String yorn = null;
				if (tdmPolicyPropertySearchDTO.getLeinIndi().equalsIgnoreCase("Y")) {
					yorn = "Yes";
				} else {
					yorn = "No";
				}

				searchCriteria += " + "
						+ messageSource.getMessage("label.policy.leiindi", null, null) + " : "
						+ yorn + "  ";
			}
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO.getOptnlCovge())) {
				searchCriteria += " + "
						+ messageSource.getMessage("label.policy.opnlCovg", null, null) + " : "
						+ tdmPolicyPropertySearchDTO.getOptnlCovge() + "  ";
			}

		}

		return searchCriteria;
	}
*/
//}
