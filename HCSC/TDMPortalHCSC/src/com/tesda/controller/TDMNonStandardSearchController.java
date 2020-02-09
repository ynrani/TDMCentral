/*---------------------------------------------------------------------------------------
 * Object Name: TDMNonStandardSearchController.Java
 * --------------------------------------------------------------------------------------
 *
 * Copyright: 2015 Capgemini Financial Services
 *---------------------------------------------------------------------------------------*/

package com.tesda.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tesda.constants.MessageConstants;
import com.tesda.constants.TDMConstants;
import com.tesda.constants.TDMExceptionCode;
import com.tesda.exception.ServiceException;
import com.tesda.exception.TDMException;
import com.tesda.model.DTO.DependentDetailsDTO;
import com.tesda.model.DTO.TDMNonStandSearchFieldsDTO;
import com.tesda.model.DTO.TDMNonStandardSearchDTO;
import com.tesda.service.TDMNonStandSearchService;
import com.tesda.util.PaginationUtil;

/*
 * Controller class which will handle the request in order to find the test data with the 
 * given search criteria. * 
 * Handle the request to reserve the selected records from the find test data search results.
 * Handle the request to export records to excel sheet from find test data search results.
 * Handle the requests for wild card search fields Account Name, Account Number, Subscriber Id
 * of Find Test Data.
 * Handle the request to Reset the Find Test Data UI.
 * Handle the pagination requests of Find Test data search results. 
 */
@Controller
@Scope(TDMConstants.SCOPE_SESSION)
public class TDMNonStandardSearchController
{

	private static final Logger logger = LoggerFactory
			.getLogger(TDMNonStandardSearchController.class);

	private int noOfRecForpage = 5;
	@Resource(name = TDMConstants.NONSTAND_SEARCH_MGMT_SERVICE)
	TDMNonStandSearchService searchManagementService;

	TDMNonStandardSearchDTO tdmNonStandardSearchDTO;

	private TDMNonStandSearchFieldsDTO nonStandSearchFieldsDTO = null;

	private int reserveCount = 0;
	private int nextOffset = 0;

	/**
	 * @param model
	 * @param request
	 * @param response
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = TDMConstants.MAP_RESERVATIONNS, method = RequestMethod.GET)
	public String tdmGetNonStandReserve(ModelMap model, HttpServletRequest request,
			HttpServletResponse response, Principal principal)
	{
		logger.info(MessageConstants.NONSTAND_CNTRL + MessageConstants.NONSATND_RESRVE
				+ MessageConstants.LOG_INFO_RETURN);
		return TDMConstants.RESERVATION_NS;
	}

	/**
	 * @param tdmNonStandSearchDTO
	 * @param model
	 * @param request
	 * @param response
	 * @param principal
	 * @return
	 * @throws TDMException
	 */
	@RequestMapping(value = TDMConstants.MAP_TDMNS_SEARCH, method = RequestMethod.GET)
	public String tdmGetNonStandFields(
			@ModelAttribute(TDMConstants.MODEL_TDMNON_STANDARDSEARCH_DTO) TDMNonStandardSearchDTO tdmNonStandSearchDTO,
			ModelMap model, HttpServletRequest request, HttpServletResponse response,
			Principal principal) throws TDMException
	{
		try
		{
			logger.info(MessageConstants.NONSTAND_CNTRL + MessageConstants.NONSTAND_FIELDS);
			nonStandSearchFieldsDTO = searchManagementService.getSearchFields();
			setNSDefaultvalues(tdmNonStandSearchDTO);
			model.addAttribute(TDMConstants.SHOW, false);
			tdmNonStandSearchDTO.setNonStandSrchFldsDTO(nonStandSearchFieldsDTO);
			logger.info(MessageConstants.NONSTAND_CNTRL + MessageConstants.NONSTAND_FIELDS
					+ MessageConstants.LOG_INFO_RETURN);
			return TDMConstants.TDM_NONSTANDARD_SEARCH;
		}
		catch (ServiceException ex)
		{
			model.addAttribute(TDMConstants.ERROR, TDMExceptionCode.getExceptionMsg(ex));
			if (ex.getErrorCode() != null)
			{
				logger.error(MessageConstants.NONSTAND_CNTRL + MessageConstants.NONSTAND_FIELDS,
						TDMExceptionCode.getExceptionMsg(ex));
			}
			return TDMConstants.TDM_NONSTANDARD_SEARCH;
		}
	}

	/**
	 * @param search
	 * @param reserve
	 * @param tdmNonStandSearchDTO
	 * @param model
	 * @param request
	 * @param response
	 * @param principal
	 * @return
	 * @throws TDMException
	 */
	@RequestMapping(value = TDMConstants.MAP_TDMNS_SEARCH, method = RequestMethod.POST)
	public String tdmGetNonStandSearchRecords(
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "reserve", required = false) String reserve,
			@RequestParam(value = "next", required = false) String nextHun,
			@ModelAttribute(TDMConstants.MODEL_TDMNON_STANDARDSEARCH_DTO) TDMNonStandardSearchDTO tdmNonStandSearchDTO,
			ModelMap model, HttpServletRequest request, HttpServletResponse response,
			Principal principal) throws TDMException
	{
		String userName = null;
		try
		{
			logger.info(MessageConstants.NONSTAND_CNTRL + MessageConstants.NONSTAND_SRCHRECORDS);
			if (null != (String) request.getSession().getAttribute(TDMConstants.USER_ID))
			{
				userName = (String) request.getSession().getAttribute(TDMConstants.USER_ID);
			}
			Long totalRecords = 5L;
			PaginationUtil pagenation = new PaginationUtil();
			int recordsperpage = Integer.parseInt(tdmNonStandSearchDTO.getSearchRecordsNo());
			noOfRecForpage = recordsperpage;
			int offSet = pagenation.getOffset(request, recordsperpage);
			model.addAttribute(TDMConstants.SHOW, true);
			String proj = (String) request.getSession().getAttribute(TDMConstants.PROJECT_ID);
			if (null != search || nextHun != null)
			{
				reserveCount = 0;
				if (nextHun != null)
				{
					nextOffset += 100;
					offSet = nextOffset;
					tdmNonStandSearchDTO = tdmNonStandardSearchDTO;
				}
				else
				{
					nextOffset = 0;
					int searchCount = (Integer) request.getSession().getAttribute(
							TDMConstants.SEARCH_COUNT);
					request.getSession().setAttribute(TDMConstants.SEARCH_COUNT, searchCount + 1);
					tdmNonStandSearchDTO.setApplicationId(proj.toUpperCase());
					searchManagementService.saveSearchDetails(tdmNonStandSearchDTO, userName);
				}
				logger.info(MessageConstants.NONSTAND_CNTRL + MessageConstants.NONSTAND_SRCHRECORDS
						+ search);
				tdmNonStandSearchDTO = searchManagementService.getNonStandSearchRecords(
						tdmNonStandSearchDTO, offSet, recordsperpage);
				totalRecords = searchManagementService.getNonStandardSearchRecordCount(
						tdmNonStandSearchDTO, userName);

				
				tdmNonStandSearchDTO.setApplicationId(proj.toUpperCase());
			}
			else if (null != reserve)
			{
				logger.info(MessageConstants.NONSTAND_CNTRL + MessageConstants.NONSTAND_SRCHRECORDS
						+ reserve);
				int recordCount = searchManagementService.saveReservedData(tdmNonStandSearchDTO,
						userName);
				reserveCount += recordCount;
				if (nextOffset > 0)
				{
					offSet = nextOffset;
				}
				tdmNonStandSearchDTO = searchManagementService.getNonStandSearchRecords(
						tdmNonStandSearchDTO, offSet, recordsperpage);
				totalRecords = searchManagementService.getNonStandardSearchRecordCount(
						tdmNonStandSearchDTO, userName);
				String msg = recordCount + TDMConstants.RESRVE_SUCC
						+ tdmNonStandSearchDTO.getTestCaseId() + TDMConstants.RESRVE_SUCC1
						+ tdmNonStandSearchDTO.getTestCaseName();
				model.addAttribute(TDMConstants.RESERVE_FLAG, msg);
				model.addAttribute(TDMConstants.COUNT, reserveCount);
				tdmNonStandSearchDTO.setTestCaseName(null);
				tdmNonStandSearchDTO.setTestCaseId(null);
				if (totalRecords == 0)
				{
					model.addAttribute(TDMConstants.RESERVED_ONLY, TDMConstants.TRUE);
				}
				else
				{
					model.addAttribute(TDMConstants.RESERVED_ONLY, TDMConstants.FALSE);
				}
			}

			if (totalRecords > 100)
			{
				if (totalRecords > (nextOffset + 100))
				{
					request.setAttribute("next_100", 100);
					totalRecords = 100L;
				}
				else
				{
					request.setAttribute("next_100", 0);
					totalRecords = totalRecords - nextOffset;
				}

			}
			else
			{
				request.setAttribute("next_100", 0);
			}

			pagenation.paginate(totalRecords, request, (double) recordsperpage, recordsperpage);
			int noOfPages = (int) Math.ceil(totalRecords.doubleValue() / recordsperpage);
			request.setAttribute(TDMConstants.NUM_OF_PAGES, noOfPages);
			tdmNonStandardSearchDTO = tdmNonStandSearchDTO;
			model.addAttribute(TDMConstants.TOTAL_RECORDS, totalRecords);
			tdmNonStandSearchDTO.setNonStandSrchFldsDTO(nonStandSearchFieldsDTO);
			request.setAttribute(TDMConstants.RESULT, getSearchCriteria(tdmNonStandSearchDTO));

			model.addAttribute(TDMConstants.TDM_NONSTAND_RESULTDTOLIST,
					tdmNonStandSearchDTO.getTdmNonStandardSrchResultListDTOs());
			model.addAttribute(TDMConstants.MODEL_TDMNON_STANDARDSEARCH_DTO, tdmNonStandSearchDTO);
			logger.info(MessageConstants.NONSTAND_CNTRL + MessageConstants.NONSTAND_SRCHRECORDS
					+ MessageConstants.LOG_INFO_RETURN);
			return TDMConstants.TDM_NONSTANDARD_SEARCH;
		}
		catch (ServiceException se)
		{
			tdmNonStandSearchDTO.setTdmNonStandardSrchResultListDTOs(null);
			request.setAttribute(TDMConstants.RESULT, getSearchCriteria(tdmNonStandSearchDTO));
			model.addAttribute(TDMConstants.MODEL_TDMNON_STANDARDSEARCH_DTO, tdmNonStandSearchDTO);
			tdmNonStandSearchDTO.setNonStandSrchFldsDTO(nonStandSearchFieldsDTO);
			logger.error(MessageConstants.NONSTAND_CNTRL + MessageConstants.NONSTAND_SRCHRECORDS,
					TDMExceptionCode.getExceptionMsg(se));
			return TDMConstants.TDM_NONSTANDARD_SEARCH;
		}
		catch (Exception se)
		{
			tdmNonStandSearchDTO.setTdmNonStandardSrchResultListDTOs(null);
			request.setAttribute(TDMConstants.RESULT, getSearchCriteria(tdmNonStandSearchDTO));
			model.addAttribute(TDMConstants.MODEL_TDMNON_STANDARDSEARCH_DTO, tdmNonStandSearchDTO);
			tdmNonStandSearchDTO.setNonStandSrchFldsDTO(nonStandSearchFieldsDTO);
			logger.error(MessageConstants.NONSTAND_CNTRL + MessageConstants.NONSTAND_SRCHRECORDS,
					se.getMessage());
			return TDMConstants.TDM_NONSTANDARD_SEARCH;
		}
	}

	/**
	 * @param search
	 * @param reserve
	 * @param tdmNonStandSearchDTO
	 * @param model
	 * @param request
	 * @param response
	 * @param principal
	 * @return
	 * @throws TDMException
	 */
	@RequestMapping(value = TDMConstants.MAP_TDMNS_SEARCH, method = RequestMethod.GET, params = TDMConstants.PAGE)
	public String tdmGetNonStandSearchPagination(
			@RequestParam(value = "next", required = false) String next_100,
			@ModelAttribute(TDMConstants.MODEL_TDMNON_STANDARDSEARCH_DTO) TDMNonStandardSearchDTO tdmNonStandSearchDTO,
			ModelMap model, HttpServletRequest request, HttpServletResponse response,
			Principal principal) throws TDMException
	{
		String userName = null;
		try
		{
			tdmNonStandSearchDTO = tdmNonStandardSearchDTO;
			logger.info(MessageConstants.NONSTAND_CNTRL + MessageConstants.TDM_NS_PAGINATE);
			if (null != (String) request.getSession().getAttribute(TDMConstants.USER_ID))
			{
				userName = (String) request.getSession().getAttribute(TDMConstants.USER_ID);
			}
			Long totalRecords = 5L;
			PaginationUtil pagenation = new PaginationUtil();
			int recordsperpage = noOfRecForpage;
			int recordsToFetch = recordsperpage;
			int offSet = pagenation.getOffset(request, recordsperpage);
			model.addAttribute(TDMConstants.SHOW, true);
			if ((offSet + recordsToFetch) > 100)
			{
				recordsToFetch = 100 - offSet;
			}
			if (nextOffset > 0)
			{
				offSet += nextOffset;
			}

			tdmNonStandSearchDTO = searchManagementService.getNonStandSearchRecords(
					tdmNonStandSearchDTO, offSet, recordsToFetch);
			totalRecords = searchManagementService.getNonStandardSearchRecordCount(
					tdmNonStandSearchDTO, userName);
			if (totalRecords > 100)
			{
				if (totalRecords > (nextOffset + 100))
				{
					request.setAttribute("next_100", 100);
					totalRecords = 100L;
				}
				else
				{
					request.setAttribute("next_100", 0);
					totalRecords = totalRecords - nextOffset;
				}
			}
			else
			{
				request.setAttribute("next_100", 0);
			}
			String proj = (String) request.getSession().getAttribute(TDMConstants.PROJECT_ID);
			tdmNonStandSearchDTO.setApplicationId(proj.toUpperCase());
			pagenation.paginate(totalRecords, request, (double) recordsperpage, recordsperpage);
			int noOfPages = (int) Math.ceil(totalRecords.doubleValue() / recordsperpage);
			request.setAttribute(TDMConstants.NUM_OF_PAGES, noOfPages);
			model.addAttribute(TDMConstants.TOTAL_RECORDS, totalRecords);
			request.setAttribute(TDMConstants.RESULT, getSearchCriteria(tdmNonStandSearchDTO));

			tdmNonStandSearchDTO.setNonStandSrchFldsDTO(nonStandSearchFieldsDTO);
			model.addAttribute(TDMConstants.TDM_NONSTAND_RESULTDTOLIST,
					tdmNonStandSearchDTO.getTdmNonStandardSrchResultListDTOs());
			tdmNonStandardSearchDTO = tdmNonStandSearchDTO;
			model.addAttribute(TDMConstants.MODEL_TDMNON_STANDARDSEARCH_DTO, tdmNonStandSearchDTO);
			logger.info(MessageConstants.NONSTAND_CNTRL + MessageConstants.TDM_NS_PAGINATE
					+ MessageConstants.LOG_INFO_RETURN);
			return TDMConstants.TDM_NONSTANDARD_SEARCH;
		}
		catch (ServiceException se)
		{
			tdmNonStandSearchDTO.setNonStandSrchFldsDTO(nonStandSearchFieldsDTO);
			request.setAttribute(TDMConstants.RESULT, getSearchCriteria(tdmNonStandSearchDTO));
			model.addAttribute(TDMConstants.MODEL_TDMNON_STANDARDSEARCH_DTO, tdmNonStandSearchDTO);
			logger.error(MessageConstants.NONSTAND_CNTRL + MessageConstants.TDM_NS_PAGINATE,
					TDMExceptionCode.getExceptionMsg(se));
			return TDMConstants.TDM_NONSTANDARD_SEARCH;
		}
		catch (Exception se)
		{
			request.setAttribute(TDMConstants.RESULT, getSearchCriteria(tdmNonStandSearchDTO));
			tdmNonStandSearchDTO.setNonStandSrchFldsDTO(nonStandSearchFieldsDTO);
			model.addAttribute(TDMConstants.MODEL_TDMNON_STANDARDSEARCH_DTO, tdmNonStandSearchDTO);
			logger.error(MessageConstants.NONSTAND_CNTRL + MessageConstants.TDM_NS_PAGINATE,
					se.getMessage());
			return TDMConstants.TDM_NONSTANDARD_SEARCH;
		}
	}

	/**
	 * @param tdmNonStandSearchDTO
	 * @param model
	 * @param request
	 * @param response
	 * @param principal
	 * @return
	 * @throws TDMException
	 */
	@RequestMapping(value = TDMConstants.MAP_TDMNONSTANDARD_SEARCH, method = RequestMethod.GET)
	public String tdmResetNonStand(
			@ModelAttribute(TDMConstants.MODEL_TDMNON_STANDARDSEARCH_DTO) TDMNonStandardSearchDTO tdmNonStandSearchDTO,
			ModelMap model, HttpServletRequest request, HttpServletResponse response,
			Principal principal) throws TDMException
	{
		logger.info(MessageConstants.NONSTAND_CNTRL + MessageConstants.RESET_NONSTAND);
		setNSDefaultvalues(tdmNonStandSearchDTO);
		model.addAttribute(TDMConstants.SHOW, false);
		tdmNonStandSearchDTO.setNonStandSrchFldsDTO(nonStandSearchFieldsDTO);
		logger.info(MessageConstants.NONSTAND_CNTRL + MessageConstants.RESET_NONSTAND
				+ MessageConstants.LOG_INFO_RETURN);
		return TDMConstants.TDM_NONSTANDARD_SEARCH;
	}

	@RequestMapping(value = TDMConstants.MAP_TDMNONSTAND_AUTO, method = RequestMethod.GET, headers = "Accept=*/*")
	@ResponseBody
	public Set<String> getAccountNamesList(
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "term", required = false) String reqToken, ModelMap model,
			HttpServletRequest request, HttpServletResponse response, Principal principal)
			throws TDMException
	{
		Set<String> resultSet = null;
		try
		{
			resultSet = new HashSet<String>();
			logger.info(MessageConstants.NONSTAND_CNTRL + MessageConstants.RESET_NONSTAND + type);
			List<String> list = searchManagementService.getAccountNameNumberList(type, reqToken);
			logger.info(MessageConstants.NONSTAND_CNTRL + MessageConstants.RESET_NONSTAND
					+ MessageConstants.LOG_INFO_RETURN);
			resultSet.addAll(list);
			return resultSet;
		}
		catch (ServiceException se)
		{
			logger.error(MessageConstants.NONSTAND_CNTRL + MessageConstants.RESET_NONSTAND,
					TDMExceptionCode.getExceptionMsg(se));
			model.addAttribute(TDMConstants.ERROR, TDMExceptionCode.getExceptionMsg(se));
			return resultSet;
		}
	}

	/**
	 * @param tdmNonStandSearchDTO
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws TDMException
	 */
	@RequestMapping(value = TDMConstants.MAP_TDMNS_SEARCH, method = RequestMethod.POST, params = "export")
	public ModelAndView tdmNonStandResultExport(
			@ModelAttribute(TDMConstants.MODEL_TDMNON_STANDARDSEARCH_DTO) TDMNonStandardSearchDTO tdmNonStandSearchDTO,
			ModelMap model, HttpServletRequest request, HttpServletResponse response,
			Principal principal) throws TDMException
	{
		try
		{
			logger.info(MessageConstants.NONSTAND_CNTRL + MessageConstants.EXPORT_NONSTAND
					+ MessageConstants.LOG_INFO_RETURN);
			if (!tdmNonStandardSearchDTO.getSubscRelation().equalsIgnoreCase(
					TDMConstants.SUBSCRIBER_ONLY)
					|| (StringUtils.isNotEmpty(tdmNonStandardSearchDTO.getSubscId()))
					|| (StringUtils.isNotEmpty(tdmNonStandardSearchDTO.getAccountName()))
					|| (StringUtils.isNotEmpty(tdmNonStandardSearchDTO.getAccountNum())))
			{
				tdmNonStandSearchDTO = searchManagementService
						.getDependentDetailsToExport(tdmNonStandardSearchDTO);
			}
			return new ModelAndView(TDMConstants.TDM_NONSTAND_SERCHRESULT_XLS,
					TDMConstants.TDM_NONSTAND_SEARCHDTOLIST, tdmNonStandardSearchDTO);
		}
		catch (ServiceException se)
		{
			logger.error(MessageConstants.NONSTAND_CNTRL + MessageConstants.RESET_NONSTAND,
					TDMExceptionCode.getExceptionMsg(se));
			model.addAttribute(TDMConstants.ERROR, TDMExceptionCode.getExceptionMsg(se));
			return new ModelAndView(TDMConstants.TDM_NONSTAND_SERCHRESULT_XLS,
					TDMConstants.TDM_NONSTAND_SEARCHDTOLIST, tdmNonStandardSearchDTO);
		}
	}

	@RequestMapping(value = TDMConstants.MAP_GET_DEPENDENT_DETAILS, method = RequestMethod.GET)
	public String getDependentDetails(
			@RequestParam(value = "subId", required = false) String subId,
			@ModelAttribute(TDMConstants.MODEL_DEPENDENT_DETAILS_DTO) DependentDetailsDTO dependentDetailsDTO,
			ModelMap model, HttpServletRequest request, HttpServletResponse response,
			Principal principal) throws TDMException
	{
		try
		{
			logger.info(MessageConstants.NONSTAND_CNTRL + MessageConstants.GET_DEPNDENT_DETAILS
					+ subId);
			List<DependentDetailsDTO> depenDetailsList = searchManagementService
					.getDependentDetails(subId);
			dependentDetailsDTO.setDependentDetailsDTOList(depenDetailsList);
			model.addAttribute("depenDetailsList", depenDetailsList);
			model.addAttribute(TDMConstants.MODEL_DEPENDENT_DETAILS_DTO, dependentDetailsDTO);
			logger.info(MessageConstants.NONSTAND_CNTRL + MessageConstants.GET_DEPNDENT_DETAILS
					+ MessageConstants.LOG_INFO_RETURN);
			return TDMConstants.DEPENDENT_DETAILSVIEW;
		}
		catch (ServiceException se)
		{
			logger.error(MessageConstants.NONSTAND_CNTRL + MessageConstants.GET_DEPNDENT_DETAILS,
					TDMExceptionCode.getExceptionMsg(se));
			model.addAttribute(TDMConstants.MODEL_DEPENDENT_DETAILS_DTO, dependentDetailsDTO);
			return TDMConstants.DEPENDENT_DETAILSVIEW;
		}
	}

	@Autowired
	private MessageSource messageSource;

	/**
	 * @param tdmNonStandSearchDTO
	 * @return
	 */
	public String getSearchCriteria(TDMNonStandardSearchDTO tdmNonStandSearchDTO)
	{
		logger.info(MessageConstants.NONSTAND_CNTRL + MessageConstants.SEARCH_CRITERIA);
		StringBuffer searchCriteria = new StringBuffer();
		if (null != tdmNonStandSearchDTO)
		{
			boolean isDisregard = false;
			String acName = tdmNonStandSearchDTO.getAccountName();
			String acNum = tdmNonStandSearchDTO.getAccountNum();
			String subscrbrId = tdmNonStandSearchDTO.getSubscId();
			if (false
					|| (StringUtils.isNotEmpty(acName) && StringUtils.isNotEmpty(acName.trim()))
					|| (StringUtils.isNotEmpty(acNum) && StringUtils.isNotEmpty(acNum.trim()))
					|| (StringUtils.isNotEmpty(subscrbrId) && StringUtils.isNotEmpty(subscrbrId
							.trim())))
			{
				isDisregard = true;
			}
			if (StringUtils.isNotEmpty(acName))
			{
				tdmNonStandSearchDTO.setAccountName(acName.trim());
			}

			if (StringUtils.isNotEmpty(acNum))
			{
				tdmNonStandSearchDTO.setAccountNum(acNum.trim());
			}
			if (StringUtils.isNotEmpty(subscrbrId))
			{
				tdmNonStandSearchDTO.setSubscId(subscrbrId.trim());
			}
			searchCriteria.append("Search Result for: ");
			if (StringUtils.isNotEmpty(tdmNonStandSearchDTO.getMemCat()) && !isDisregard)
			{
				searchCriteria.append(messageSource.getMessage("label.ns.memCatt", null, null))
						.append(" = ");
				if (tdmNonStandSearchDTO.getMemCat().equalsIgnoreCase(TDMConstants.RETAIL))
				{
					searchCriteria.append(tdmNonStandSearchDTO.getMemCat())
							.append(" " + tdmNonStandSearchDTO.getRetailOnOff()).append("; ");
				}
				else
				{
					searchCriteria.append(tdmNonStandSearchDTO.getMemCat()).append("; ");
				}
			}
			if (StringUtils.isNotEmpty(tdmNonStandSearchDTO.getProvState()) && !isDisregard)
			{
				searchCriteria.append(messageSource.getMessage("label.ns.state", null, null))
						.append(" = ");
				searchCriteria.append(tdmNonStandSearchDTO.getProvState()).append("; ");
			}

			if (StringUtils.isNotEmpty(tdmNonStandSearchDTO.getCoverageCode()) && !isDisregard)
			{
				searchCriteria.append(messageSource.getMessage("label.ns.cov", null, null)).append(
						" = ");
				searchCriteria.append(tdmNonStandSearchDTO.getCoverageCode()).append("; ");
			}
			if (StringUtils.isNotEmpty(tdmNonStandSearchDTO.getPlanType()) && !isDisregard)
			{
				searchCriteria.append(messageSource.getMessage("label.ns.plnType", null, null))
						.append(" = ");
				searchCriteria.append(tdmNonStandSearchDTO.getPlanType()).append("; ");
			}

			if (StringUtils.isNotEmpty(tdmNonStandSearchDTO.getSubscRelation()) && !isDisregard)
			{
				searchCriteria.append(messageSource.getMessage("label.ns.memType", null, null))
						.append(" = ");
				searchCriteria.append(tdmNonStandSearchDTO.getSubscRelation()).append("; ");
			}
			if (StringUtils.isNotEmpty(tdmNonStandSearchDTO.getAgeGroup()) && !isDisregard)
			{
				searchCriteria.append(messageSource.getMessage("label.ns.ageGrp", null, null));
				if (tdmNonStandSearchDTO.getAgeGroup().equalsIgnoreCase("Any"))
				{
					searchCriteria.append("=");
				}
				searchCriteria.append(tdmNonStandSearchDTO.getAgeGroup()).append("; ");
			}

			if (StringUtils.isNotEmpty(tdmNonStandSearchDTO.getSubscStatus()) && !isDisregard)
			{
				searchCriteria.append(messageSource.getMessage("label.ns.sts", null, null)).append(
						" = ");
				searchCriteria.append(tdmNonStandSearchDTO.getSubscStatus()).append("; ");
			}

			if (StringUtils.isNotEmpty(tdmNonStandSearchDTO.getOriginalEffDate()) && !isDisregard)
			{
				searchCriteria
						.append(messageSource.getMessage("label.ns.orignlEfffDt", null, null))
						.append(" = ");
				searchCriteria.append(tdmNonStandSearchDTO.getOriginalEffDate()).append("; ");
			}

			if (StringUtils.isNotEmpty(tdmNonStandSearchDTO.getExtClaim()) && !isDisregard)
			{
				searchCriteria.append(messageSource.getMessage("label.ns.extClm", null, null))
						.append(" = ");
				searchCriteria.append(tdmNonStandSearchDTO.getExtClaim()).append(";");
			}
			if (StringUtils.isNotEmpty(tdmNonStandSearchDTO.getAccountName())
					&& StringUtils.isNotEmpty(tdmNonStandSearchDTO.getAccountName().replaceAll(" ",
							""))
					&& (!StringUtils.isNotEmpty(subscrbrId) && !StringUtils.isNotEmpty(subscrbrId
							.trim())))
			{
				searchCriteria.append(messageSource.getMessage("label.ns.acName", null, null))
						.append(" = ");
				searchCriteria.append(tdmNonStandSearchDTO.getAccountName()).append("; ");
			}
			if (StringUtils.isNotEmpty(tdmNonStandSearchDTO.getAccountNum())
					&& StringUtils.isNotEmpty(tdmNonStandSearchDTO.getAccountNum().replaceAll(" ",
							""))
					&& (!StringUtils.isNotEmpty(subscrbrId) && !StringUtils.isNotEmpty(subscrbrId
							.trim())))
			{
				searchCriteria.append(messageSource.getMessage("label.ns.acNum", null, null))
						.append(" = ");
				searchCriteria.append(tdmNonStandSearchDTO.getAccountNum()).append("; ");
			}

			if (StringUtils.isNotEmpty(tdmNonStandSearchDTO.getSubscId())
					&& StringUtils
							.isNotEmpty(tdmNonStandSearchDTO.getSubscId().replaceAll(" ", "")))
			{
				searchCriteria.append(messageSource.getMessage("label.ns.subId", null, null))
						.append(" = ");
				searchCriteria.append(tdmNonStandSearchDTO.getSubscId()).append("; ");
			}

			if (StringUtils.isNotEmpty(tdmNonStandSearchDTO.getFundingInd()) && !isDisregard)
			{
				searchCriteria.append(messageSource.getMessage("label.ns.fundInd", null, null))
						.append(" = ");
				searchCriteria.append(tdmNonStandSearchDTO.getFundingInd()).append(" ; ");
			}

			if (StringUtils.isNotEmpty(tdmNonStandSearchDTO.getPbm()) && !isDisregard)
			{
				searchCriteria.append(messageSource.getMessage("label.ns.pbm", null, null)).append(
						" = ");
				searchCriteria.append(tdmNonStandSearchDTO.getPbm()).append(" ; ");
			}
		}
		else
		{
			searchCriteria.append(TDMConstants.NO_RECORDS);
		}
		logger.info(MessageConstants.NONSTAND_CNTRL + MessageConstants.SEARCH_CRITERIA
				+ MessageConstants.LOG_INFO_RETURN);
		return searchCriteria.toString();
	}

	public void setNSDefaultvalues(TDMNonStandardSearchDTO tdmNonStandSearchDTO)
	{
		tdmNonStandSearchDTO.setCoverageCode(TDMConstants.MEDICAL);
		tdmNonStandSearchDTO.setPlanType(TDMConstants.ANY);
		tdmNonStandSearchDTO.setProvState(TDMConstants.ANY);
		tdmNonStandSearchDTO.setMemCat(TDMConstants.GROUP);
		tdmNonStandSearchDTO.setSubscRelation(TDMConstants.SUBSCRIBER_ONLY);
		tdmNonStandSearchDTO.setAgeGroup(TDMConstants.ANY);
		tdmNonStandSearchDTO.setSubscStatus(TDMConstants.SUB_ACTIVE);
		tdmNonStandSearchDTO.setExtClaim(TDMConstants.NONE);
		tdmNonStandSearchDTO.setFundingInd(TDMConstants.ANY);
		tdmNonStandSearchDTO.setPbm(TDMConstants.ANY);
	}
}
