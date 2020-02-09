package com.tdm.controller;

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

import com.tdm.constant.MessageConstant;
import com.tdm.exception.BaseException;
import com.tdm.model.DTO.TDMPolicyPropertyNewSearchDTO;
import com.tdm.model.DTO.TdmPolicyAutoSearchDTO;
import com.tdm.model.DTO.TdmPolicyAutoSearchResultDTO;
import com.tdm.model.DTO.TdmPolicyPropertySearchDTO;
import com.tdm.model.DTO.TdmPolicyPropertySearchResultDTO;
import com.tdm.service.TdmMyReservationService;
import com.tdm.util.PaginationUtil;

/**
 * @author
 * @version 1.0
 */

@Controller
public class TdmLifeMyReservationController
{
	@Autowired
	private MessageSource messageSource;

	final static Logger logger = Logger.getLogger(TdmLifeMyReservationController.class);
	@Resource(name = "tdmMyReservationService")
	TdmMyReservationService tdmMyReservationService;
	List<TdmPolicyAutoSearchResultDTO> tdmPolicyAutoSearchResultDTO = null;
	List<TdmPolicyPropertySearchResultDTO> tdmPolicyPropertySearchResultDTO = null;

	@RequestMapping(value = "/myReservationAuto", method = RequestMethod.GET)
	public String myReservationRecordAuto(
			@RequestParam(value = "page", required = false) String page, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws BaseException {

		logger.info("In reservation controller: ");
		try {
			String UserId = (String) request.getSession().getAttribute("UserId");
			Long totalRecords = 0L;
			PaginationUtil pagenation = new PaginationUtil();
			Integer recordsperpage = Integer.valueOf(10);
			int offSet = pagenation.getOffset(request, recordsperpage);
			TdmPolicyAutoSearchDTO tdmPolicyAutoSearchDTO = tdmMyReservationService
					.getReservedRecordForUser(UserId, offSet, recordsperpage, true);
			String searchType = "CSAA_AU";
			totalRecords = tdmMyReservationService.getReservedRecordsCount(searchType, UserId);
			pagenation
					.paginate(totalRecords, request, recordsperpage.doubleValue(), recordsperpage);
			Double noOfPages = Math.ceil(totalRecords.doubleValue() / recordsperpage.doubleValue());
			request.setAttribute("noOfPages", noOfPages.intValue());
			model.addAttribute("tdmPolicyAutoSearchResultDTOList",
					tdmPolicyAutoSearchDTO.getTdmPolicyAutoSearchResultDTOList());
			request.getSession().setAttribute("tdmPolicyAutoSearchResultDTOList_EXCEL",tdmPolicyAutoSearchDTO.getTdmPolicyAutoSearchResultDTOList());
			tdmPolicyAutoSearchResultDTO = tdmPolicyAutoSearchDTO
					.getTdmPolicyAutoSearchResultDTOList();
			return "myReservationsAuto";
		} catch (BaseException baseEx) {
			logger.error("MyReservationRecordAuto error while reserving records!", baseEx);

			if (baseEx.getErrorCode() != null) {
				model.addAttribute("error", baseEx.getErrorCode() + " : " + baseEx.getRootCause());
				return "myReservationsAuto";
			}
		}
		return "myReservationsAuto";
	}

	@RequestMapping(value = "/unreserveAuto", method = RequestMethod.GET)
	public String unreserveRecordAuto(@RequestParam(value = "id", required = false) String id,
			ModelMap model, HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		try {
			logger.info("In controller to unreserve records : ");
			String UserId = (String) request.getSession().getAttribute("UserId");
			Long totalRecords = 0L;

			PaginationUtil pagenation = new PaginationUtil();
			Integer recordsperpage = Integer.valueOf(10);
			int offSet = pagenation.getOffset(request, recordsperpage);

			tdmMyReservationService.unReservedRecordForUser(id);
			TdmPolicyAutoSearchDTO tdmPolicyAutoSearchDTO = tdmMyReservationService
					.getReservedRecordForUser(UserId, offSet, recordsperpage, true);
			String searchType = "CSAA_AU";
			totalRecords = tdmMyReservationService.getReservedRecordsCount(searchType, UserId);
			pagenation
					.paginate(totalRecords, request, recordsperpage.doubleValue(), recordsperpage);
			Double noOfPages = Math.ceil(totalRecords.doubleValue() / recordsperpage.doubleValue());
			request.setAttribute("noOfPages", noOfPages.intValue());
			model.addAttribute("tdmPolicyAutoSearchResultDTOList",
					tdmPolicyAutoSearchDTO.getTdmPolicyAutoSearchResultDTOList());
			tdmPolicyAutoSearchResultDTO = tdmPolicyAutoSearchDTO
					.getTdmPolicyAutoSearchResultDTOList();
			return "myReservationsAuto";
		} catch (BaseException baseEx) {
			logger.error("Error while reserving the records!", baseEx);
			if (baseEx.getErrorCode() != null) {
				model.addAttribute("error", baseEx.getErrorCode() + " : " + baseEx.getRootCause());
				return "myReservationsAuto";
			}

		}
		return "myReservationsAuto";
	}
	
	@RequestMapping(value = "/myReservationProp", method = RequestMethod.GET)
	public String myReservationRecordProperty(
			@RequestParam(value = "page", required = false) String page, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws BaseException {

		try {
			logger.info("In My Reservation saving Record for Property : ");

			String UserId = (String) request.getSession().getAttribute("UserId");
			Long totalRecords = 0L;
			PaginationUtil pagenation = new PaginationUtil();
			Integer recordsperpage = Integer.valueOf(10);
			int offSet = pagenation.getOffset(request, recordsperpage);

			TdmPolicyPropertySearchDTO tdmPolicyPropertySearchDTO = tdmMyReservationService
					.getReservedRecordForUserProperty(UserId, offSet, recordsperpage, true);
			String searchType = "CSAA_PO";
			totalRecords = tdmMyReservationService.getReservedRecordsCount(searchType, UserId);
			pagenation
					.paginate(totalRecords, request, recordsperpage.doubleValue(), recordsperpage);
			Double noOfPages = Math.ceil(totalRecords.doubleValue() / recordsperpage.doubleValue());
			request.setAttribute("noOfPages", noOfPages.intValue());
			model.addAttribute("tdmPolicyPropertySearchResultDTOList",
					tdmPolicyPropertySearchDTO.getTdmPolicyPropertySearchResultDTOList());
			request.getSession().setAttribute("tdmPolicyPropertySearchResultDTOList_EXCEL",tdmPolicyPropertySearchDTO.getTdmPolicyPropertySearchResultDTOList());
			tdmPolicyPropertySearchResultDTO = tdmPolicyPropertySearchDTO
					.getTdmPolicyPropertySearchResultDTOList();
			return "myReservationsProperty";
		} catch (BaseException baseEx) {
			logger.error("Sorry, something wrong while reserving record for property!", baseEx);

			if (baseEx.getErrorCode() != null) {
				model.addAttribute("error", baseEx.getErrorCode() + " : " + baseEx.getRootCause());
				return "myReservationsProperty";
			}
		}
		return "myReservationsProperty";
	}
   
	@RequestMapping(value = "/unreserveProp", method = RequestMethod.GET)
	public String unreserveRecordProperty(@RequestParam(value = "id", required = false) String id,
			ModelMap model, HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		try {
			logger.info("Unreserve Record for Property : ");
			String UserId = (String) request.getSession().getAttribute("UserId");
			Long totalRecords = 0L;

			PaginationUtil pagenation = new PaginationUtil();
			Integer recordsperpage = Integer.valueOf(10);
			int offSet = pagenation.getOffset(request, recordsperpage);

			tdmMyReservationService.unReservedRecordForUser(id);
			TdmPolicyPropertySearchDTO tdmPolicyPropertySearchDTO = tdmMyReservationService
					.getReservedRecordForUserProperty(UserId, offSet, recordsperpage, true);
			String searchType = "CSAA_PO";
			totalRecords = tdmMyReservationService.getReservedRecordsCount(searchType, UserId);
			pagenation
					.paginate(totalRecords, request, recordsperpage.doubleValue(), recordsperpage);
			Double noOfPages = Math.ceil(totalRecords.doubleValue() / recordsperpage.doubleValue());
			request.setAttribute("noOfPages", noOfPages.intValue());
			model.addAttribute("tdmPolicyPropertySearchResultDTOList",
					tdmPolicyPropertySearchDTO.getTdmPolicyPropertySearchResultDTOList());
		//	 request.getSession().setAttribute("tdmPolicyPropertySearchResultDTOList", tdmPolicyPropertySearchDTO.getTdmPolicyPropertySearchResultDTOList());
			tdmPolicyPropertySearchResultDTO = tdmPolicyPropertySearchDTO
					.getTdmPolicyPropertySearchResultDTOList();
			return "myReservationsProperty";
		} catch (BaseException baseEx) {
			logger.error("Sorry, something wrong while Unreserving record for property!", baseEx);
			if (baseEx.getErrorCode() != null) {
				model.addAttribute("error", baseEx.getErrorCode() + " : " + baseEx.getRootCause());
				return "myReservationsProperty";
			}

		}
		return "myReservationsProperty";
	}
	
/*	@RequestMapping(value = "/myReservationAuto", method = RequestMethod.POST, params = "export")
	public ModelAndView policyAutoRevdataExport(ModelMap model, 
			@ModelAttribute("tdmPolicyPropertySearchDTO") TDMPolicyPropertyNewSearchDTO tdmPolicyPropertySearchDTO,
			HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		if (logger.isInfoEnabled()) {
			logger.info("property controller click in Export button  : ");
		}
		String UserId = (String) request.getSession().getAttribute("UserId");
		List<TdmPolicyPropertySearchResultDTO> list = null;
		model.addAttribute("result", getSearchPropertyCriteria(tdmPolicyPropertySearchDTO));
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
		return new ModelAndView("myReservationAutoPolicyExcelView",
				"tdmPolicyPropertySearchResultDTOs", list);
		
	}*/
	
			@RequestMapping(value = "/myReservationAuto", method = RequestMethod.POST, params = "export")
			public ModelAndView policyPropExport(
					@ModelAttribute(MessageConstant.TDM_ATS_SEARCH_DTO) TdmPolicyAutoSearchDTO tdmAtsSearchDTO,
					ModelMap model, HttpServletRequest request,
					HttpServletResponse response) {
				logger.info("In auto search controller  policyPropExport methos: ");
				String UserId = (String) request.getSession().getAttribute("UserId");
       List tdmPolicyAutoSearchResultDTOList =(List)request.getSession().getAttribute("tdmPolicyAutoSearchResultDTOList_EXCEL");
            tdmAtsSearchDTO.setTdmPolicyAutoSearchResultDTOList(tdmPolicyAutoSearchResultDTOList);
				List<TdmPolicyAutoSearchResultDTO> list = null;
				if (null != tdmAtsSearchDTO.getTdmPolicyAutoSearchResultDTOList()
						&& 0 < tdmAtsSearchDTO.getTdmPolicyAutoSearchResultDTOList()
								.size()) {
					list = new ArrayList<TdmPolicyAutoSearchResultDTO>();
					for (int i = 0; i < tdmAtsSearchDTO
							.getTdmPolicyAutoSearchResultDTOList().size(); i++) {
						if (null != tdmAtsSearchDTO
								.getTdmPolicyAutoSearchResultDTOList().get(i)
								.getReservedYN()) {
							tdmAtsSearchDTO.getTdmPolicyAutoSearchResultDTOList()
									.get(i).setUserId(UserId);
							list.add(tdmAtsSearchDTO
									.getTdmPolicyAutoSearchResultDTOList().get(i));
						}
					}
				}
				model.addAttribute("result", getSearchCriteria(tdmAtsSearchDTO));
				return new ModelAndView("searchPolicyAutoListExcelView",
						"tdmPolicyAutoSearchResultDTOs", list);
			}
	
			public String getSearchCriteria(
					TdmPolicyAutoSearchDTO tdmPolicyAutoSearchDTO) {

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
					/*
					 * if
					 * (StringUtils.isNotEmpty(tdmPolicyAutoSearchDTO.getPayMethod())) {
					 * searchCriteria += " + " +
					 * messageSource.getMessage("label.policy.payType", null, null) +
					 * " : " + tdmPolicyAutoSearchDTO.getPayMethod() + "  "; }
					 */
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
					/*
					 * if
					 * (StringUtils.isNotEmpty(tdmPolicyAutoSearchDTO.getAssoBillPlanType
					 * ())) { searchCriteria += " + " +
					 * messageSource.getMessage("label.policy.planType", null, null) +
					 * " : " + tdmPolicyAutoSearchDTO.getAssoBillPlanType() + "  "; }
					 * 
					 * if
					 * (StringUtils.isNotEmpty(tdmPolicyAutoSearchDTO.getAssoDocType()))
					 * { searchCriteria += " + " +
					 * messageSource.getMessage("label.policy.docType", null, null) +
					 * " : " + tdmPolicyAutoSearchDTO.getAssoDocType() + "  "; }
					 */
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
	
	@RequestMapping(value = "/myReservationProp", method = RequestMethod.POST, params = "export")
	public ModelAndView policyPropertyRevdataExport(ModelMap model, 
			@ModelAttribute("tdmPolicyPropertySearchDTO") TDMPolicyPropertyNewSearchDTO tdmPolicyPropertySearchDTO,
			HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		if (logger.isInfoEnabled()) {
			logger.info("property controller click in Export button  : ");
		}
		String UserId = (String) request.getSession().getAttribute("UserId");
		List<TdmPolicyPropertySearchResultDTO> list = null;
		List	tdmPolicyPropertySearchResultDTOList=(List)request.getSession().getAttribute("tdmPolicyPropertySearchResultDTOList_EXCEL");
		tdmPolicyPropertySearchDTO.setTdmPolicyPropertySearchResultDTOList(tdmPolicyPropertySearchResultDTOList);
		model.addAttribute("result", getSearchPropertyCriteria(tdmPolicyPropertySearchDTO));
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
		return new ModelAndView("myReservationPropPolicyExcelView",
				"tdmPolicyPropertySearchResultDTOs", list);
		
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
/*	@RequestMapping(value = "/myReservationProp", method = RequestMethod.POST, params = "export")
	public ModelAndView policyPropertyRevdataExport(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
            
		logger.info("TdmMyReservationController class policyPropertyRevdataExport export : ");
		System.out.println("TdmMyReservationController class policyPropertyRevdataExport export");
		return new ModelAndView("searchPolicyPropListExcelView",
				"tdmPolicyPropertySearchResultDTOs", tdmPolicyPropertySearchResultDTO);
	}*/

	/*@RequestMapping(value = "/myReservationAuto", method = RequestMethod.POST, params = "export")
	public ModelAndView policyAutoRevdataExport(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {

		System.out.println("TdmMyReservationController class findTestProvReservedDataPost export");
		return new ModelAndView("searchPolicyAutoListExcelView", "tdmPolicyAutoSearchResultDTOs",
				tdmPolicyAutoSearchResultDTO);

	}*/

}
