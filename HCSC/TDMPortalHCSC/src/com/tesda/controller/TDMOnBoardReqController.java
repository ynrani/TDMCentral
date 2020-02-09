/*---------------------------------------------------------------------------------------
 * Object Name: TDMOnBoardReqController.Java
 * --------------------------------------------------------------------------------------
 *
 * Copyright: 2015 Capgemini Financial Services
 *---------------------------------------------------------------------------------------*/

package com.tesda.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.tesda.model.DTO.TdmChangeReqDTO;
import com.tesda.model.DTO.TdmDataMaskingDTO;
import com.tesda.model.DTO.TdmDataMaskingNoOfAppsDTO;
import com.tesda.model.DTO.TdmOnBoardReqDTO;
import com.tesda.service.TDMDataMaskingService;
import com.tesda.util.PaginationUtil;

/**
 * Controller class handle the request to create On Boarding Request. Handle the
 * request to change the already created On Boarding Request. Handle the request
 * to export the records from On Boarding and CR On Boarding dash boards. Handle
 * the request to cancel the On Boarding request. Handle the request to delete
 * the rows Application Name, Database, No Of Tables.
 */

@Controller
@Scope(TDMConstants.SCOPE_SESSION)
public class TDMOnBoardReqController
{

	private static final Logger logger = LoggerFactory.getLogger(TDMOnBoardReqController.class);

	List<TdmDataMaskingDTO> tdgDtMaskRequestListDTOfr = null;
	List<TdmDataMaskingDTO> tdgDtMaskRequestListDTOcr = null;

	@Resource(name = TDMConstants.DATAMASKING_SERVICE)
	TDMDataMaskingService dataMaskingService;

	/**
	 * @param reqId
	 * @param tdmOnboardReqDTO
	 * @param model
	 * @param request
	 * @param response
	 * @param principal
	 * @return
	 * @throws TDMException
	 */
	@RequestMapping(value = TDMConstants.MAP_TDM_ONBOARD_REQ, method = RequestMethod.GET)
	public String tdmGetOnboardReq(
			@RequestParam(value = "reqId", required = false) String reqId,
			@ModelAttribute(TDMConstants.MODEL_TDM_ONBORAD_REQDTO) TdmOnBoardReqDTO tdmOnboardReqDTO,
			ModelMap model, HttpServletRequest request, HttpServletResponse response,
			Principal principal) throws TDMException
	{
		try
		{
			logger.info(MessageConstants.ONBRDREQ_CNTRL + MessageConstants.GET_ONBRDREQ);
			boolean chngeReqYN = false;
			if (StringUtils.isNotEmpty(reqId))
			{
				tdmOnboardReqDTO = dataMaskingService.getEditableDetails(reqId);
				chngeReqYN = true;
				logger.info(MessageConstants.ONBRDREQ_CNTRL + MessageConstants.GET_ONBRDREQ + reqId);
			}
			else
			{

				if (null != (String) request.getSession().getAttribute(TDMConstants.USER_ID))
				{
					tdmOnboardReqDTO.setUserId((String) request.getSession().getAttribute(
							TDMConstants.USER_ID));

					/*
					 * if (null != (String)
					 * request.getSession().getAttribute(TDMConstants
					 * .PROJECT_ID)) { tdmOnboardReqDTO.setAppName((String)
					 * request.getSession().getAttribute(
					 * TDMConstants.PROJECT_ID)); }
					 */
					tdmOnboardReqDTO.setEmailId((String) request.getSession().getAttribute(
							TDMConstants.EMAIL_ID));
					tdmOnboardReqDTO.setUserName((String) request.getSession().getAttribute(
							TDMConstants.USER_NAME));
					List<TdmDataMaskingNoOfAppsDTO> tdgDataMaskingNoOfAppsDTOs = new ArrayList<TdmDataMaskingNoOfAppsDTO>();
					TdmDataMaskingNoOfAppsDTO tdgDataMaskingNoOfAppsDTO = new TdmDataMaskingNoOfAppsDTO();
					tdgDataMaskingNoOfAppsDTOs.add(tdgDataMaskingNoOfAppsDTO);
					tdmOnboardReqDTO.setTdgDataMaskingNoOfAppsDTOs(tdgDataMaskingNoOfAppsDTOs);
				}
				chngeReqYN = false;
			}

			List<String> listValues = new ArrayList<String>();
			listValues.add("ABC");
			listValues.add("ABC");
			listValues.add("ABC");
			listValues.add("ABC");
			model.addAttribute("listValues", listValues);
			model.addAttribute(TDMConstants.IS_CHANGE_REQ, chngeReqYN);
			model.addAttribute(TDMConstants.REQ_ID, tdmOnboardReqDTO.getOnboardReqId());
			model.addAttribute(TDMConstants.MODEL_TDM_ONBORAD_REQDTO, tdmOnboardReqDTO);
			logger.info(MessageConstants.ONBRDREQ_CNTRL + MessageConstants.GET_ONBRDREQ
					+ MessageConstants.LOG_INFO_RETURN);
			return TDMConstants.TDM_ONBOARDING_REQ;
		}
		catch (ServiceException ex)
		{
			model.addAttribute(TDMConstants.MODEL_TDM_ONBORAD_REQDTO, tdmOnboardReqDTO);
			model.addAttribute(TDMConstants.ERROR, TDMExceptionCode.getExceptionMsg(ex));
			logger.error(MessageConstants.ONBRDREQ_CNTRL + MessageConstants.GET_ONBRDREQ,
					TDMExceptionCode.getExceptionMsg(ex));
			if (ex.getErrorCode().equals(TDMExceptionCode.NORESULT))
			{
				model.addAttribute(TDMConstants.MODEL_TDM_CHANGEREQ_DTO, new TdmChangeReqDTO());
				return TDMConstants.TDM_CHANGE_REQEXT;
			}
			return TDMConstants.TDM_ONBOARDING_REQ;
		}

	}

	/**
	 * @param tdmOnboardReqDTO
	 * @param model
	 * @param request
	 * @param response
	 * @param principal
	 * @return
	 * @throws TDMException
	 */
	@RequestMapping(value = TDMConstants.MAP_TDM_ONBOARD_REQ, method = RequestMethod.POST)
	@ResponseBody
	public String tdmPostOnboardReq(
			@ModelAttribute(TDMConstants.MODEL_TDM_ONBORAD_REQDTO) TdmOnBoardReqDTO tdmOnboardReqDTO,
			ModelMap model, HttpServletRequest request, HttpServletResponse response,
			Principal principal) throws TDMException
	{
		try
		{
			logger.info(MessageConstants.ONBRDREQ_CNTRL + MessageConstants.POST_ONBRDREQ);
			boolean chngeReqYN = false;
			tdmOnboardReqDTO = dataMaskingService.getSaveOnboardingReq(tdmOnboardReqDTO);
			if (StringUtils.isNotEmpty(tdmOnboardReqDTO.getChngReqCmmt()))
			{
				chngeReqYN = true;
			}
			model.addAttribute(TDMConstants.REQ_ID, tdmOnboardReqDTO.getOnboardReqId());
			model.addAttribute(TDMConstants.IS_CHANGE_REQ, chngeReqYN);
			logger.info(MessageConstants.ONBRDREQ_CNTRL + MessageConstants.POST_ONBRDREQ
					+ MessageConstants.LOG_INFO_RETURN);
			return tdmOnboardReqDTO.getOnboardReqId();
		}
		catch (ServiceException ex)
		{
			model.addAttribute(TDMConstants.ERROR, TDMExceptionCode.getExceptionMsg(ex));
			logger.error(MessageConstants.ONBRDREQ_CNTRL + MessageConstants.POST_ONBRDREQ,
					TDMExceptionCode.getExceptionMsg(ex));
			return TDMConstants.TDM_ONBOARDING_REQ;
		}
	}

	/**
	 * @param page
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws TDMException
	 */
	@RequestMapping(value = TDMConstants.MAP_TDMONBOARDING_DASHBOARD, method = RequestMethod.GET)
	public String tdmDtMaskDashboard(
			@RequestParam(value = TDMConstants.PAGE, required = false) String page, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws TDMException
	{
		try
		{
			logger.info(MessageConstants.ONBRDREQ_CNTRL + MessageConstants.DTMSK_DASHBRD);
			String type = TDMConstants.FR;
			String role = null;
			Long totalRecords = 0L;
			PaginationUtil pagenation = new PaginationUtil();
			int recordsperpage = 10;
			int offSet = pagenation.getOffset(request, recordsperpage);
			if (null != (String) request.getSession().getAttribute(TDMConstants.ROLE))
			{
				role = (String) request.getSession().getAttribute(TDMConstants.ROLE);
			}
			totalRecords = dataMaskingService.getReservedRecordsCountOnBoard(role, (String) request
					.getSession().getAttribute(TDMConstants.USER_ID), type);

			List<TdmDataMaskingDTO> tdgDtMaskRequestListDTOs = dataMaskingService
					.getAllOnBoardRequestedRecordForPagination(role, offSet, recordsperpage, true,
							(String) request.getSession().getAttribute(TDMConstants.USER_ID), type);
			pagenation.paginate(totalRecords, request, (double) recordsperpage, recordsperpage);
			int noOfPages = (int) Math.ceil(totalRecords.doubleValue() / recordsperpage);
			tdgDtMaskRequestListDTOfr = tdgDtMaskRequestListDTOs;
			request.setAttribute(TDMConstants.NUM_OF_PAGES, noOfPages);
			model.addAttribute(TDMConstants.MODEL_TDG_DATAMASK_REQLISTDTO, tdgDtMaskRequestListDTOs);
			logger.info(MessageConstants.ONBRDREQ_CNTRL + MessageConstants.DTMSK_DASHBRD
					+ MessageConstants.LOG_INFO_RETURN);
			return TDMConstants.TDM_ONBOARDING_DASHBOARD;
		}
		catch (ServiceException se)
		{
			model.addAttribute(TDMConstants.ERROR, TDMExceptionCode.getExceptionMsg(se));
			logger.error(MessageConstants.ONBRDREQ_CNTRL + MessageConstants.DTMSK_DASHBRD,
					TDMExceptionCode.getExceptionMsg(se));
			return TDMConstants.TDM_ONBOARDING_DASHBOARD;
		}
	}

	/**
	 * @param page
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws TDMException
	 */
	@RequestMapping(value = TDMConstants.MAP_TDMONBOARDING_DASHBOARDCR, method = RequestMethod.GET)
	public String tdmDtMaskDashboardCR(
			@RequestParam(value = TDMConstants.PAGE, required = false) String page, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws TDMException
	{
		try
		{
			String role = null;
			logger.info(MessageConstants.ONBRDREQ_CNTRL + MessageConstants.DTMSK_DASHBRDCR);
			String type = TDMConstants.CR;
			Long totalRecords = 0L;
			PaginationUtil pagenation = new PaginationUtil();
			int recordsperpage = 10;
			int offSet = pagenation.getOffset(request, recordsperpage);
			if (null != (String) request.getSession().getAttribute(TDMConstants.ROLE))
			{
				role = (String) request.getSession().getAttribute(TDMConstants.ROLE);
			}
			totalRecords = dataMaskingService.getReservedRecordsCountOnBoard(role, (String) request
					.getSession().getAttribute(TDMConstants.USER_ID), type);

			List<TdmDataMaskingDTO> tdgDtMaskRequestListDTOs = dataMaskingService
					.getAllOnBoardRequestedRecordForPagination(role, offSet, recordsperpage, true,
							(String) request.getSession().getAttribute(TDMConstants.USER_ID), type);
			pagenation.paginate(totalRecords, request, (double) recordsperpage, recordsperpage);
			int noOfPages = (int) Math.ceil(totalRecords.doubleValue() / recordsperpage);
			tdgDtMaskRequestListDTOcr = tdgDtMaskRequestListDTOs;
			request.setAttribute(TDMConstants.NUM_OF_PAGES, noOfPages);
			model.addAttribute(TDMConstants.MODEL_TDG_DATAMASK_REQLISTDTO, tdgDtMaskRequestListDTOs);
			logger.info(MessageConstants.ONBRDREQ_CNTRL + MessageConstants.DTMSK_DASHBRDCR
					+ MessageConstants.LOG_INFO_RETURN);
			return TDMConstants.TDM_ONBOARDING_DASHBOARDCR;
		}
		catch (ServiceException se)
		{
			model.addAttribute(TDMConstants.ERROR, TDMExceptionCode.getExceptionMsg(se));
			logger.error(MessageConstants.ONBRDREQ_CNTRL + MessageConstants.DTMSK_DASHBRDCR,
					TDMExceptionCode.getExceptionMsg(se));
			return TDMConstants.TDM_ONBOARDING_DASHBOARDCR;
		}
	}

	/**
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = TDMConstants.MAP_TDMONBOARDING_EXPORTFR, method = RequestMethod.POST, params = TDMConstants.EXPORT)
	public ModelAndView tdmOnBoardingExportFR(ModelMap model, HttpServletRequest request,
			HttpServletResponse response)
	{
		logger.info(MessageConstants.ONBRDREQ_CNTRL + MessageConstants.ONBRD_EXPORTFR
				+ MessageConstants.LOG_INFO_RETURN);
		return new ModelAndView(TDMConstants.TDM_DATA_MASKING_DASH_BOARD_XLS,
				TDMConstants.MODEL_TDM_DATA_MASKING_DTOS, tdgDtMaskRequestListDTOfr);
	}

	/**
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = TDMConstants.MAP_TDMONBOARDING_EXPORTCR, method = RequestMethod.POST, params = TDMConstants.EXPORT)
	public ModelAndView tdmOnBoardingExportCR(ModelMap model, HttpServletRequest request,
			HttpServletResponse response)
	{
		logger.info(MessageConstants.ONBRDREQ_CNTRL + MessageConstants.DTMSK_DASHBRDCR
				+ MessageConstants.LOG_INFO_RETURN);
		return new ModelAndView(TDMConstants.TDM_DATA_MASKING_DASH_BOARD_XLS,
				TDMConstants.MODEL_TDM_DATA_MASKING_DTOS, tdgDtMaskRequestListDTOcr);
	}

	/**
	 * @param reqId
	 * @param request
	 * @param response
	 * @return
	 * @throws TDMException
	 */
	@RequestMapping(value = TDMConstants.MAP_CANCEL_ONBOARDREQ, method = RequestMethod.POST)
	@ResponseBody
	public String tdmCancelOnboardReq(
			@RequestParam(value = TDMConstants.REQ_ID, required = false) String reqId,
			HttpServletRequest request, HttpServletResponse response) throws TDMException
	{
		try
		{
			logger.info(MessageConstants.ONBRDREQ_CNTRL + MessageConstants.CANCEL_ONBRDREQ);
			boolean isConcelled = dataMaskingService.cancelOnBoardingRequest(reqId);
			if (isConcelled)
			{
				logger.info(MessageConstants.ONBRDREQ_CNTRL + MessageConstants.CANCEL_ONBRDREQ
						+ MessageConstants.LOG_INFO_RETURN + isConcelled);
				return TDMConstants.TRUE;
			}
			else
			{
				logger.info(MessageConstants.ONBRDREQ_CNTRL + MessageConstants.CANCEL_ONBRDREQ
						+ MessageConstants.LOG_INFO_RETURN + isConcelled);
				return TDMConstants.FALSE;
			}
		}
		catch (ServiceException se)
		{
			if (se.getErrorCode().equals(TDMExceptionCode.NORESULT))
			{
				logger.error(MessageConstants.ONBRDREQ_CNTRL + MessageConstants.CANCEL_ONBRDREQ,
						TDMExceptionCode.getExceptionMsg(se));
			}
			return TDMConstants.ERROR;
		}
	}

	@RequestMapping(value = TDMConstants.TDM_ONBOARD_REQ_DEL_ROW, method = RequestMethod.GET)
	@ResponseBody
	public String tdmDeleteOnboardReqRow(
			@RequestParam(value = TDMConstants._ID, required = false) String rowId,
			@RequestParam(value = TDMConstants.ITEM_TYPE, required = false) String reqType,
			@RequestParam(value = "_", required = false) String param, HttpServletRequest request,
			HttpServletResponse response) throws TDMException
	{
		try
		{
			logger.info(MessageConstants.ONBRDREQ_CNTRL + MessageConstants.DELETE_ONBOARD_REQROW);
			boolean isDeleted = dataMaskingService.deleteRow(reqType, rowId);
			if (isDeleted)
			{
				logger.info(MessageConstants.ONBRDREQ_CNTRL
						+ MessageConstants.DELETE_ONBOARD_REQROW + MessageConstants.LOG_INFO_RETURN
						+ isDeleted);
				return TDMConstants.TRUE;
			}
			else
			{
				logger.info(MessageConstants.ONBRDREQ_CNTRL
						+ MessageConstants.DELETE_ONBOARD_REQROW + MessageConstants.LOG_INFO_RETURN
						+ isDeleted);
				return TDMConstants.FALSE;
			}
		}
		catch (ServiceException se)
		{
			if (se.getErrorCode().equals(TDMExceptionCode.NORESULT))
			{
				logger.error(MessageConstants.ONBRDREQ_CNTRL
						+ MessageConstants.DELETE_ONBOARD_REQROW,
						TDMExceptionCode.getExceptionMsg(se));
			}
			return TDMConstants.FALSE;
		}
	}
}
