/*---------------------------------------------------------------------------------------
 * Object Name: TDMCmdCenterMetricsController.Java
 * 
 * --------------------------------------------------------------------------------------
 *
 * Copyright: 2015 Capgemini Financial Services
 *---------------------------------------------------------------------------------------*/
package com.tesda.controller;

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

import com.tesda.constants.AppConstant;
import com.tesda.constants.MessageConstants;
import com.tesda.constants.TDMConstants;
import com.tesda.constants.TDMExceptionCode;
import com.tesda.exception.ServiceException;
import com.tesda.exception.TDMException;
import com.tesda.model.DTO.TdmDataMaskingDTO;
import com.tesda.model.DTO.TdmDataMaskingNoOfAppsDTO;
import com.tesda.service.TDMDataMaskingService;
import com.tesda.util.PaginationUtil;

/**
 * Controller class which will handle the Data masking request.
 * Supports following functionalities.
 * Creates the Data Masking Request.
 * Changes the Data Masking Request. 
 * Cancels the Data Masking Request.
 * Deletes the rows of Application Name, Database, No Of Tables. 
 * Exports the records to excel sheet from Data Masking and CR Data Masking dash boards.
 */

@Controller
@Scope(TDMConstants.SCOPE_SESSION)
public class TDMDataMaskingController
{
	private static final Logger logger = LoggerFactory.getLogger(TDMDataMaskingController.class);

	@Resource(name = TDMConstants.DATAMASKING_SERVICE)
	TDMDataMaskingService tdmDataMaskingService;

	/**
	 * @param reqId
	 * @param status
	 * @param edit
	 * @param tdgDataMaskingDTO
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = TDMConstants.MAP_TDM_DATA_MASKING_1_NEW, method = RequestMethod.GET)
	public String tdmDataMaskingReqDtlGet(
			@RequestParam(value = TDMConstants.REQ_ID, required = false) String reqId,
			@RequestParam(value = TDMConstants.STATUS, required = false) String status,
			@RequestParam(value = TDMConstants.EDIT, required = false) String edit,
			@ModelAttribute(TDMConstants.MODEL_TDG_DATAMASKING_DTO) TdmDataMaskingDTO tdgDataMaskingDTO,
			ModelMap model, HttpServletRequest request, HttpServletResponse response)
			throws TDMException
	{
		logger.info(MessageConstants.MSKING_CNTRL + MessageConstants.GETREQDTLS);
		boolean reqTypeCR = false;
		boolean checkexistingReq = false;
		try
		{
			if (null == reqId)
			{
				String userId = (String) request.getSession().getAttribute(TDMConstants.USER_ID);
				if (null != userId)
				{
					logger.info(MessageConstants.MSKING_CNTRL + MessageConstants.GETREQDTLS
							+ userId);
					tdgDataMaskingDTO.setUserId(userId);
					checkexistingReq = tdmDataMaskingService
							.getCheckExistingReqYesNo(tdgDataMaskingDTO);
					if (!checkexistingReq)
					{
						/*if (null != (String) request.getSession().getAttribute(
								AppConstant.SESSION_PROJ))
						{
							tdgDataMaskingDTO.setProjectId((String) request.getSession()
									.getAttribute(AppConstant.SESSION_PROJ));
						}*/
						tdgDataMaskingDTO.setEmailId((String) request.getSession().getAttribute(
								TDMConstants.EMAIL_ID));
						tdgDataMaskingDTO.setUserName((String) request.getSession().getAttribute(
								TDMConstants.USER_NAME));
					}
				}
			}
			else
			{
				logger.info(MessageConstants.MSKING_CNTRL + MessageConstants.GETREQDTLS + reqId);
				if (null == status)
				{
					reqTypeCR = true;
					logger.info(MessageConstants.MSKING_CNTRL + MessageConstants.GETREQDTLS
							+ status);
				}
				if (null != edit)
				{
					tdgDataMaskingDTO.setEdit(edit);
					logger.info(MessageConstants.MSKING_CNTRL + MessageConstants.GETREQDTLS + edit);
				}
				tdgDataMaskingDTO.setId(reqId);
				tdgDataMaskingDTO.setReqTypeCR(reqTypeCR);
				tdgDataMaskingDTO = tdmDataMaskingService.getSavedDtls(tdgDataMaskingDTO, true,
						false, false);
			}
			if (null != status)
			{
				if (status.equalsIgnoreCase(TDMConstants.STATUS_SUBMITTED))
				{
					tdgDataMaskingDTO.setStatus(status);
					model.addAttribute(TDMConstants.READONLY, true);
				}
			}
		}
		catch (ServiceException baseEx)
		{
			if (null != baseEx.getErrorCode() || baseEx.getErrorCode().equalsIgnoreCase("null"))
			{
				// String exceptionMsg = passcodes and get msg from properties
				// file by passing key
				// as
				// baseEx.getErrorCode();
				logger.error(MessageConstants.MSKING_CNTRL + MessageConstants.GETREQDTLS,
						TDMExceptionCode.getExceptionMsg(baseEx));
				if (baseEx.getErrorCode().startsWith(""))
				{
					return TDMConstants.TDM_DATA_MASKING_1_NEW_VIEW;
				}
			}
			model.addAttribute(AppConstant.TGD_DT_MASK_DTO, tdgDataMaskingDTO);
			if (checkexistingReq)
			{
				return AppConstant.TDM_DATA_MASKING_REDIRECT_DASHBOARD + checkexistingReq;
			}
			else
			{
				return TDMConstants.TDM_DATA_MASKING_1_NEW_VIEW;
			}
		}
		model.addAttribute(TDMConstants.MODEL_TDG_DATAMASKING_DTO, tdgDataMaskingDTO);
		logger.info(MessageConstants.MSKING_CNTRL + MessageConstants.GETREQDTLS
				+ MessageConstants.LOG_INFO_RETURN);
		if (checkexistingReq)
		{
			return AppConstant.TDM_DATA_MASKING_REDIRECT_DASHBOARD + checkexistingReq;
		}
		else
		{
			return TDMConstants.TDM_DATA_MASKING_1_NEW_VIEW;
		}
	}

	/**
	 * @param submitType
	 * @param tdgDataMaskingDTO
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */

	@RequestMapping(value = TDMConstants.MAP_TDM_DATA_MASKING_1_NEW, method = RequestMethod.POST)
	public String tdmDataMaskingReqDtlPost(
			@RequestParam(value = TDMConstants.SUBMIT, required = false) String submitType,
			@ModelAttribute(TDMConstants.MODEL_TDG_DATAMASKING_DTO) TdmDataMaskingDTO tdgDataMaskingDTO,
			ModelMap model, HttpServletRequest request, HttpServletResponse response)
			throws TDMException
	{
		try
		{
			logger.info(MessageConstants.MSKING_CNTRL + MessageConstants.POSTREQDTLS + submitType);
			if (null != submitType)
			{
				if (submitType.equalsIgnoreCase(TDMConstants.SAVE_AND_CONTI))
				{
					tdgDataMaskingDTO = tdmDataMaskingService.saveReqDtls(tdgDataMaskingDTO, true,
							false, false);
				}
				else if (!submitType.equalsIgnoreCase(TDMConstants.SAVE_AND_CONTI))
				{
					model.addAttribute(TDMConstants.READONLY, true);
				}
			}
		}
		catch (ServiceException baseEx)
		{
			if (null != baseEx.getErrorCode() || baseEx.getErrorCode().equalsIgnoreCase("null"))
			{
				// String exceptionMsg = passcodes and get msg from properties
				// file by passing key
				// as
				// baseEx.getErrorCode();

				logger.error(MessageConstants.MSKING_CNTRL + MessageConstants.POSTREQDTLS,
						TDMExceptionCode.getExceptionMsg(baseEx));
				if (baseEx.getErrorCode().startsWith(""))
				{
					return TDMConstants.TDM_DATA_MASKING_PAGE2_REDIRECT + tdgDataMaskingDTO.getId()
							+ TDMConstants.TDM_DATA_MASKING_PARAM_STS
							+ tdgDataMaskingDTO.getStatus();
				}
			}
			// responseMsg = passcodes and get msg from properties file by
			// passing key as
			// baseEx.getErrorCode();
			return TDMConstants.TDM_DATA_MASKING_PAGE2_REDIRECT + tdgDataMaskingDTO.getId()
					+ TDMConstants.TDM_DATA_MASKING_PARAM_STS + tdgDataMaskingDTO.getStatus();
		}
		logger.info(MessageConstants.MSKING_CNTRL + MessageConstants.POSTREQDTLS
				+ MessageConstants.LOG_INFO_RETURN);
		return TDMConstants.TDM_DATA_MASKING_PAGE2_REDIRECT + tdgDataMaskingDTO.getId()
				+ TDMConstants.TDM_DATA_MASKING_PARAM_STS + tdgDataMaskingDTO.getStatus();
	}

	/**
	 * @param reqId
	 * @param status
	 * @param tdgDataMaskingDTO
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = TDMConstants.MAP_TDM_DATA_MASKING_2_NEW, method = RequestMethod.GET)
	public String tdmDataMaskingPreReqGet(
			@RequestParam(value = TDMConstants.REQ_ID, required = false) String reqId,
			@RequestParam(value = TDMConstants.STATUS, required = false) String status,
			@ModelAttribute(TDMConstants.MODEL_TDG_DATAMASKING_DTO) TdmDataMaskingDTO tdgDataMaskingDTO,
			ModelMap model, HttpServletRequest request, HttpServletResponse response)
			throws TDMException
	{
		try
		{
			logger.info(MessageConstants.MSKING_CNTRL + MessageConstants.GETPREREQ + status);
			tdgDataMaskingDTO.setId(reqId);
			tdgDataMaskingDTO = tdmDataMaskingService.getSavedDtls(tdgDataMaskingDTO, false, true,
					false);
			if (null != status)
			{
				if (status.equalsIgnoreCase(TDMConstants.STATUS_SUBMITTED))
				{
					tdgDataMaskingDTO.setStatus(status);
					model.addAttribute(TDMConstants.READONLY, true);
				}
			}
			model.addAttribute(TDMConstants.MODEL_TDG_DATAMASKING_DTO, tdgDataMaskingDTO);
		}
		catch (ServiceException baseEx)
		{
			if (null != baseEx.getErrorCode() || baseEx.getErrorCode().equalsIgnoreCase("null"))
			{
				// String exceptionMsg = passcodes and get msg from properties
				// file by passing key
				// as
				// baseEx.getErrorCode();
				logger.error(MessageConstants.MSKING_CNTRL + MessageConstants.GETPREREQ,
						TDMExceptionCode.getExceptionMsg(baseEx));
				if (baseEx.getErrorCode().startsWith(""))
				{
					return TDMConstants.TDM_DATA_MASKING_2_NEW_VIEW;
				}
			}
			// responseMsg = passcodes and get msg from properties file by
			// passing key as
			// baseEx.getErrorCode();
			return TDMConstants.TDM_DATA_MASKING_2_NEW_VIEW;
		}
		logger.info(MessageConstants.MSKING_CNTRL + MessageConstants.GETPREREQ
				+ MessageConstants.LOG_INFO_RETURN);
		return TDMConstants.TDM_DATA_MASKING_2_NEW_VIEW;
	}

	/**
	 * @param submit
	 * @param tdgDataMaskingDTO
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = TDMConstants.MAP_TDM_DATA_MASKING_2_NEW, method = RequestMethod.POST)
	public String tdmDataMaskingPreReqPost(
			@RequestParam(value = TDMConstants.SUBMIT, required = false) String submit,
			@ModelAttribute(TDMConstants.MODEL_TDG_DATAMASKING_DTO) TdmDataMaskingDTO tdgDataMaskingDTO,
			ModelMap model, HttpServletRequest request, HttpServletResponse response)
			throws TDMException
	{
		try
		{
			logger.info(MessageConstants.MSKING_CNTRL + MessageConstants.POSTPREREQ + submit);
			if (null != submit)
			{
				if (submit.equalsIgnoreCase(TDMConstants.SAVE_AND_CONTI))
				{
					tdgDataMaskingDTO = tdmDataMaskingService.saveReqDtls(tdgDataMaskingDTO, false,
							true, false);
				}
				else if (!submit.equalsIgnoreCase(TDMConstants.SAVE_AND_CONTI))
				{
					model.addAttribute(TDMConstants.READONLY, true);
				}
			}
			if (null != submit && !submit.equalsIgnoreCase(TDMConstants.BACK))
			{
				logger.info(MessageConstants.MSKING_CNTRL + MessageConstants.POSTPREREQ
						+ MessageConstants.LOG_INFO_RETURN);
				return TDMConstants.TDM_DATA_MASKING_PAGE3_REDIRECT + tdgDataMaskingDTO.getId()
						+ TDMConstants.TDM_DATA_MASKING_PARAM_STS + tdgDataMaskingDTO.getStatus();
			}
			else
			{
				logger.info(MessageConstants.MSKING_CNTRL + MessageConstants.POSTPREREQ
						+ MessageConstants.LOG_INFO_RETURN);
				return TDMConstants.TDM_DATA_MASKING_PAGE1_REDIRECT + tdgDataMaskingDTO.getId()
						+ TDMConstants.TDM_DATA_MASKING_PARAM_STS + tdgDataMaskingDTO.getStatus();
			}
		}
		catch (ServiceException baseEx)
		{
			if (null != baseEx.getErrorCode() || baseEx.getErrorCode().equalsIgnoreCase("null"))
			{
				// String exceptionMsg = passcodes and get msg from properties
				// file by passing key
				// as
				// baseEx.getErrorCode();
				logger.error(MessageConstants.MSKING_CNTRL + MessageConstants.POSTPREREQ,
						TDMExceptionCode.getExceptionMsg(baseEx));
				if (baseEx.getErrorCode().startsWith(""))
				{
					return TDMConstants.TDM_DATA_MASKING_2_NEW_VIEW;
				}
			}
			// responseMsg = passcodes and get msg from properties file by
			// passing key as
			// baseEx.getErrorCode();
			return TDMConstants.TDM_DATA_MASKING_2_NEW_VIEW;
		}
	}

	/**
	 * @param reqId
	 * @param status
	 * @param tdgDataMaskingDTO
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = TDMConstants.MAP_TDM_DATA_MASKING_3_NEW, method = RequestMethod.GET)
	public String tdmDataMaskingMskDtlGet(
			@RequestParam(value = TDMConstants.REQ_ID, required = false) String reqId,
			@RequestParam(value = TDMConstants.STATUS, required = false) String status,
			@ModelAttribute(TDMConstants.MODEL_TDG_DATAMASKING_DTO) TdmDataMaskingDTO tdgDataMaskingDTO,
			ModelMap model, HttpServletRequest request, HttpServletResponse response)
			throws TDMException
	{
		tdgDataMaskingDTO.setId(reqId);
		try
		{
			logger.info(MessageConstants.MSKING_CNTRL + MessageConstants.GETMSKDTLS + status);
			tdgDataMaskingDTO = tdmDataMaskingService.getSavedDtls(tdgDataMaskingDTO, false, false,
					true);
			if (null != status)
			{
				if (status.equalsIgnoreCase(TDMConstants.STATUS_SUBMITTED))
				{
					tdgDataMaskingDTO.setStatus(status);
					model.addAttribute(TDMConstants.READONLY, true);
				}
			}
			if ((StringUtils.isEmpty(status) && reqId.contains(TDMConstants.CR))
					|| StringUtils.isNotEmpty(tdgDataMaskingDTO.getChngReqCmmt()))
			{
				if (null == tdgDataMaskingDTO.getTdgDataMaskingNoOfAppsDTOs())
				{
					List<TdmDataMaskingNoOfAppsDTO> tdgDataMaskingNoOfAppsDTOs = new ArrayList<TdmDataMaskingNoOfAppsDTO>();
					TdmDataMaskingNoOfAppsDTO tdgDataMaskingNoOfAppsDTO = new TdmDataMaskingNoOfAppsDTO();
					tdgDataMaskingNoOfAppsDTOs.add(tdgDataMaskingNoOfAppsDTO);
					tdgDataMaskingDTO.setTdgDataMaskingNoOfAppsDTOs(tdgDataMaskingNoOfAppsDTOs);
				}
				model.addAttribute(TDMConstants.IS_CHANGE_REQ, true);
			}
			model.addAttribute(TDMConstants.MODEL_TDG_DATAMASKING_DTO, tdgDataMaskingDTO);
			logger.info(MessageConstants.MSKING_CNTRL + MessageConstants.GETMSKDTLS
					+ MessageConstants.LOG_INFO_RETURN);
			return TDMConstants.TDM_DATA_MASKING_3_NEW_VIEW;
		}
		catch (ServiceException baseEx)
		{
			if (null != baseEx.getErrorCode() || baseEx.getErrorCode().equalsIgnoreCase("null"))
			{
				// String exceptionMsg = passcodes and get msg from properties
				// file by passing key
				// as
				// baseEx.getErrorCode();
				logger.error(MessageConstants.MSKING_CNTRL + MessageConstants.GETMSKDTLS,
						TDMExceptionCode.getExceptionMsg(baseEx));
				if (baseEx.getErrorCode().startsWith(""))
				{
					return TDMConstants.TDM_DATA_MASKING_3_NEW_VIEW;
				}
			}
			// responseMsg = passcodes and get msg from properties file by
			// passing key as
			// baseEx.getErrorCode();
			return TDMConstants.TDM_DATA_MASKING_3_NEW_VIEW;
		}

	}

	/**
	 * @param submit
	 * @param tdgDataMaskingDTO
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = TDMConstants.MAP_TDM_DATA_MASKING_3_NEW, method = RequestMethod.POST)
	@ResponseBody
	public String tdmDataMaskingMskDtlPost(
			@RequestParam(value = TDMConstants.SUBMIT, required = false) String submit,
			@ModelAttribute(TDMConstants.MODEL_TDG_DATAMASKING_DTO) TdmDataMaskingDTO tdgDataMaskingDTO,
			ModelMap model, HttpServletRequest request, HttpServletResponse response)
			throws TDMException
	{
		try
		{
			logger.info(MessageConstants.MSKING_CNTRL + MessageConstants.POSTMSKDTLS + submit);
			tdgDataMaskingDTO = tdmDataMaskingService.saveReqDtls(tdgDataMaskingDTO, false, false,
					true);
			model.addAttribute(TDMConstants.DISABLED, TDMConstants.TRUE);
			model.addAttribute(TDMConstants.MODEL_TDG_DATAMASKING_DTO, tdgDataMaskingDTO);
			logger.info(MessageConstants.MSKING_CNTRL + MessageConstants.POSTMSKDTLS
					+ MessageConstants.LOG_INFO_RETURN);
			return tdgDataMaskingDTO.getId();
		}
		catch (ServiceException baseEx)
		{
			if (null != baseEx.getErrorCode() || baseEx.getErrorCode().equalsIgnoreCase("null"))
			{
				// String exceptionMsg = passcodes and get msg from properties
				// file by passing key
				// as
				// baseEx.getErrorCode();
				logger.error(MessageConstants.MSKING_CNTRL + MessageConstants.POSTMSKDTLS,
						TDMExceptionCode.getExceptionMsg(baseEx));
				if (baseEx.getErrorCode().startsWith(""))
				{
					return tdgDataMaskingDTO.getId();
				}
			}
			// responseMsg = passcodes and get msg from properties file by
			// passing key as
			// baseEx.getErrorCode();
			return tdgDataMaskingDTO.getId();
		}
	}

	/**
	 * @param tdgDataMaskingDTO
	 * @param reqId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = TDMConstants.MAP_TDM_DATA_MASKING_3_NEW_EXPORT, method = RequestMethod.GET)
	public ModelAndView tdmMaskingExportFRGet(
			@ModelAttribute(TDMConstants.MODEL_TDG_DATAMASKING_DTO) TdmDataMaskingDTO tdgDataMaskingDTO,
			@RequestParam(value = TDMConstants.REQ_ID, required = false) String reqId,
			HttpServletRequest request, HttpServletResponse response) throws TDMException
	{
		logger.info(MessageConstants.MSKING_CNTRL + MessageConstants.MSKNGEXPRT);
		List<TdmDataMaskingDTO> list = null;
		try
		{
			tdgDataMaskingDTO.setId(reqId);
			tdgDataMaskingDTO = tdmDataMaskingService.getSavedDtls(tdgDataMaskingDTO, true, true,
					true);
			if (null == tdgDataMaskingDTO.getTdgDataMaskingDTOs())
			{
				list = new ArrayList<TdmDataMaskingDTO>();
				list.add(tdgDataMaskingDTO);
			}
			else
			{
				list = tdgDataMaskingDTO.getTdgDataMaskingDTOs();
			}
			logger.info(MessageConstants.MSKING_CNTRL + MessageConstants.MSKNGEXPRT
					+ MessageConstants.LOG_INFO_RETURN);
			return new ModelAndView(TDMConstants.TDM_DATA_MASKING_DASH_BOARD_XLS,
					TDMConstants.MODEL_TDM_DATA_MASKING_DTOS, list);
		}
		catch (ServiceException baseEx)
		{
			if (null != baseEx.getErrorCode() || baseEx.getErrorCode().equalsIgnoreCase("null"))
			{
				// String exceptionMsg = passcodes and get msg from properties
				// file by passing key
				// as
				// baseEx.getErrorCode();
				logger.error(MessageConstants.MSKING_CNTRL + MessageConstants.MSKNGEXPRT,
						TDMExceptionCode.getExceptionMsg(baseEx));
				if (baseEx.getErrorCode().startsWith(""))
				{
					return new ModelAndView(TDMConstants.TDM_DATA_MASKING_DASH_BOARD_XLS,
							TDMConstants.MODEL_TDM_DATA_MASKING_DTOS, list);
				}
			}
			// responseMsg = passcodes and get msg from properties file by
			// passing key as
			// baseEx.getErrorCode();
			return new ModelAndView(TDMConstants.TDM_DATA_MASKING_DASH_BOARD_XLS,
					TDMConstants.MODEL_TDM_DATA_MASKING_DTOS, list);
		}
	}

	/**
	 * @param page
	 * @param tdgDataMaskingDTO
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = TDMConstants.MAP_TDM_DTMASKDASHBORAD, method = RequestMethod.GET)
	public String tdmDtMaskDashboard(
			@RequestParam(value = TDMConstants.PAGE, required = false) String page,
			@RequestParam(value = AppConstant.FLAG, required = false) String flag,
			@ModelAttribute(TDMConstants.MODEL_TDG_DATAMASKING_DTO) TdmDataMaskingDTO tdgDataMaskingDTO,
			ModelMap model, HttpServletRequest request, HttpServletResponse response)
			throws TDMException
	{
		String type = TDMConstants.FR;
		Long totalRecords = 0L;
		String role = null;
		try
		{
			logger.info(MessageConstants.MSKING_CNTRL + MessageConstants.MSKNGDASHBRD);
			PaginationUtil pagenation = new PaginationUtil();
			int recordsperpage = Integer.valueOf(10);
			int offSet = pagenation.getOffset(request, recordsperpage);
			tdgDataMaskingDTO.setUserId((String) request.getSession().getAttribute(
					TDMConstants.USER_ID));

			if (null != (String) request.getSession().getAttribute(TDMConstants.ROLE))
			{
				role = (String) request.getSession().getAttribute(TDMConstants.ROLE);
				tdgDataMaskingDTO.setRole(role);
			}
			totalRecords = tdmDataMaskingService.getReservedRecordsCount(role,
					tdgDataMaskingDTO.getUserId(), type);
			tdgDataMaskingDTO = tdmDataMaskingService.getAllDtMaskRequestedRecordForPagination(
					offSet, recordsperpage, true, tdgDataMaskingDTO, type);
			pagenation.paginate(totalRecords, request, (double) recordsperpage, recordsperpage);
			int noOfPages = (int) Math.ceil(totalRecords.doubleValue() / recordsperpage);
			request.setAttribute(TDMConstants.NUM_OF_PAGES, noOfPages);
			model.addAttribute(TDMConstants.MODEL_TDG_DATAMASK_REQLISTDTO,
					tdgDataMaskingDTO.getTdgDataMaskingDTOs());
			model.addAttribute(AppConstant.FLAG, flag);
			logger.info(MessageConstants.MSKING_CNTRL + MessageConstants.MSKNGDASHBRD
					+ MessageConstants.LOG_INFO_RETURN);
			return TDMConstants.TDM_DATAMASKING_DASHBOARD;

		}
		catch (ServiceException baseEx)
		{
			if (null != baseEx.getErrorCode() || baseEx.getErrorCode().equalsIgnoreCase("null"))
			{
				// String exceptionMsg = passcodes and get msg from properties
				// file by passing key
				// as
				// baseEx.getErrorCode();
				logger.error(MessageConstants.MSKING_CNTRL + MessageConstants.MSKNGDASHBRD,
						TDMExceptionCode.getExceptionMsg(baseEx));
				if (baseEx.getErrorCode().startsWith(""))
				{
					return TDMConstants.TDM_DATAMASKING_DASHBOARD;
				}
			}
			// responseMsg = passcodes and get msg from properties file by
			// passing key as
			// baseEx.getErrorCode();
			return TDMConstants.TDM_DATAMASKING_DASHBOARD;
		}
	}

	/**
	 * @param page
	 * @param tdgDataMaskingDTO
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = TDMConstants.TDM_DATAMASKING_DASHBOARD_CR, method = RequestMethod.GET)
	public String tdmDtMaskDashboardCR(
			@RequestParam(value = TDMConstants.PAGE, required = false) String page,
			@ModelAttribute(TDMConstants.MODEL_TDG_DATAMASKING_DTO) TdmDataMaskingDTO tdgDataMaskingDTO,
			ModelMap model, HttpServletRequest request, HttpServletResponse response)
			throws TDMException
	{
		String type = TDMConstants.CR;
		Long totalRecords = 0L;
		String role = null;
		try
		{
			logger.info(MessageConstants.MSKING_CNTRL + MessageConstants.MSKNG_DASHBRDCR);
			PaginationUtil pagenation = new PaginationUtil();
			int recordsperpage = Integer.valueOf(10);
			int offSet = pagenation.getOffset(request, recordsperpage);
			tdgDataMaskingDTO.setUserId((String) request.getSession().getAttribute(
					TDMConstants.USER_ID));
			if (null != (String) request.getSession().getAttribute(TDMConstants.ROLE))
			{
				role = (String) request.getSession().getAttribute(TDMConstants.ROLE);
				tdgDataMaskingDTO.setRole(role);
			}
			totalRecords = tdmDataMaskingService.getReservedRecordsCount(role,
					tdgDataMaskingDTO.getUserId(), type);
			tdgDataMaskingDTO = tdmDataMaskingService.getAllDtMaskRequestedRecordForPagination(
					offSet, recordsperpage, true, tdgDataMaskingDTO, type);
			pagenation.paginate(totalRecords, request, (double) recordsperpage, recordsperpage);
			int noOfPages = (int) Math.ceil(totalRecords.doubleValue() / recordsperpage);
			request.setAttribute(TDMConstants.NUM_OF_PAGES, noOfPages);
			model.addAttribute(TDMConstants.MODEL_TDG_DATAMASK_REQLISTDTO,
					tdgDataMaskingDTO.getTdgDataMaskingDTOs());
			logger.info(MessageConstants.MSKING_CNTRL + MessageConstants.MSKNG_DASHBRDCR
					+ MessageConstants.LOG_INFO_RETURN);
			return TDMConstants.TDM_DATAMASKING_DASHBOARD_CR;
		}
		catch (ServiceException baseEx)
		{
			if (null != baseEx.getErrorCode() || baseEx.getErrorCode().equalsIgnoreCase("null"))
			{
				// String exceptionMsg = passcodes and get msg from properties
				// file by passing key
				// as
				// baseEx.getErrorCode();
				logger.error(MessageConstants.MSKING_CNTRL + MessageConstants.MSKNG_DASHBRDCR,
						TDMExceptionCode.getExceptionMsg(baseEx));
				if (baseEx.getErrorCode().startsWith(""))
				{
					return TDMConstants.TDM_DATAMASKING_DASHBOARD_CR;
				}
			}
			// responseMsg = passcodes and get msg from properties file by
			// passing key as
			// baseEx.getErrorCode();
			return TDMConstants.TDM_DATAMASKING_DASHBOARD_CR;
		}
	}

	/**
	 * @param tdgDataMaskingDTO
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = TDMConstants.MAP_TDMMASKING_FR, method = RequestMethod.POST, params = TDMConstants.EXPORT)
	public ModelAndView tdmMaskingExportFR(
			@ModelAttribute(TDMConstants.MODEL_TDG_DATAMASKING_DTO) TdmDataMaskingDTO tdgDataMaskingDTO,
			ModelMap model, HttpServletRequest request, HttpServletResponse response)
			throws TDMException
	{
		List<TdmDataMaskingDTO> list = null;
		String role = null;
		try
		{
			logger.info(MessageConstants.MSKING_CNTRL + MessageConstants.MSKNGEXPRT_FR);
			tdgDataMaskingDTO.setUserId((String) request.getSession().getAttribute(
					TDMConstants.USER_ID));
			role = (String) request.getSession().getAttribute(TDMConstants.ROLE);
			String type = TDMConstants.FR;
			tdgDataMaskingDTO = tdmDataMaskingService.getSavedDtlsforExport(role,
					tdgDataMaskingDTO, true, true, true, type);
			if (null == tdgDataMaskingDTO.getTdgDataMaskingDTOs())
			{
				list = new ArrayList<TdmDataMaskingDTO>();
				list.add(tdgDataMaskingDTO);
			}
			else
			{
				list = tdgDataMaskingDTO.getTdgDataMaskingDTOs();
			}
			logger.info(MessageConstants.MSKING_CNTRL + MessageConstants.MSKNGEXPRT_FR
					+ MessageConstants.LOG_INFO_RETURN);
			return new ModelAndView(TDMConstants.TDM_DATA_MASKING_DASH_BOARD_XLS,
					TDMConstants.MODEL_TDM_DATA_MASKING_DTOS, list);
		}
		catch (ServiceException baseEx)
		{
			if (null != baseEx.getErrorCode() || baseEx.getErrorCode().equalsIgnoreCase("null"))
			{
				// String exceptionMsg = passcodes and get msg from properties
				// file by passing key
				// as
				// baseEx.getErrorCode();
				logger.error(MessageConstants.MSKING_CNTRL + MessageConstants.MSKNGEXPRT_FR,
						TDMExceptionCode.getExceptionMsg(baseEx));
				if (baseEx.getErrorCode().startsWith(""))
				{
					return new ModelAndView(TDMConstants.TDM_DATA_MASKING_DASH_BOARD_XLS,
							TDMConstants.MODEL_TDM_DATA_MASKING_DTOS, list);
				}
			}
			// responseMsg = passcodes and get msg from properties file by
			// passing key as
			// baseEx.getErrorCode();
			return new ModelAndView(TDMConstants.TDM_DATA_MASKING_DASH_BOARD_XLS,
					TDMConstants.MODEL_TDM_DATA_MASKING_DTOS, list);
		}
	}

	/**
	 * @param tdgDataMaskingDTO
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = TDMConstants.MAP_TDMMASKING_CR, method = RequestMethod.POST, params = TDMConstants.EXPORT)
	public ModelAndView tdmMaskingExportCR(
			@ModelAttribute(TDMConstants.MODEL_TDG_DATAMASKING_DTO) TdmDataMaskingDTO tdgDataMaskingDTO,
			ModelMap model, HttpServletRequest request, HttpServletResponse response)
			throws TDMException
	{
		List<TdmDataMaskingDTO> list = null;
		try
		{
			String role = null;
			logger.info(MessageConstants.MSKING_CNTRL + MessageConstants.MSKNGEXPRT_CR);
			tdgDataMaskingDTO.setUserId((String) request.getSession().getAttribute(
					TDMConstants.USER_ID));
			String type = TDMConstants.CR;
			role = (String) request.getSession().getAttribute(TDMConstants.ROLE);
			tdgDataMaskingDTO = tdmDataMaskingService.getSavedDtlsforExport(role,
					tdgDataMaskingDTO, true, true, true, type);
			if (null == tdgDataMaskingDTO.getTdgDataMaskingDTOs())
			{
				list = new ArrayList<TdmDataMaskingDTO>();
				list.add(tdgDataMaskingDTO);
			}
			else
			{
				list = tdgDataMaskingDTO.getTdgDataMaskingDTOs();
			}
			logger.info(MessageConstants.MSKING_CNTRL + MessageConstants.MSKNGEXPRT_CR
					+ MessageConstants.LOG_INFO_RETURN);
			return new ModelAndView(TDMConstants.TDM_DATA_MASKING_DASH_BOARD_XLS,
					TDMConstants.MODEL_TDM_DATA_MASKING_DTOS, list);
		}
		catch (ServiceException baseEx)
		{
			if (null != baseEx.getErrorCode() || baseEx.getErrorCode().equalsIgnoreCase("null"))
			{
				// String exceptionMsg = passcodes and get msg from properties
				// file by passing key
				// as
				// baseEx.getErrorCode();
				logger.error(MessageConstants.MSKING_CNTRL + MessageConstants.MSKNGEXPRT_CR,
						TDMExceptionCode.getExceptionMsg(baseEx));
				if (baseEx.getErrorCode().startsWith(""))
				{
					return new ModelAndView(TDMConstants.TDM_DATA_MASKING_DASH_BOARD_XLS,
							TDMConstants.MODEL_TDM_DATA_MASKING_DTOS, list);
				}
			}
			// responseMsg = passcodes and get msg from properties file by
			// passing key as
			// baseEx.getErrorCode();
			return new ModelAndView(TDMConstants.TDM_DATA_MASKING_DASH_BOARD_XLS,
					TDMConstants.MODEL_TDM_DATA_MASKING_DTOS, list);
		}
	}

	/**
	 * @param reqId
	 * @param request
	 * @param response
	 * @return
	 * @throws TDMException
	 */
	@RequestMapping(value = TDMConstants.MAP_CANCEL_MASKINGREQ, method = RequestMethod.POST)
	@ResponseBody
	public String tdmCancelOnboardReq(
			@RequestParam(value = "reqId", required = false) String reqId,
			HttpServletRequest request, HttpServletResponse response) throws TDMException
	{
		try
		{
			logger.info(MessageConstants.MSKING_CNTRL + MessageConstants.CANCL_ONBRDREQ);
			boolean isCancelled = tdmDataMaskingService.cancelMaskingRequest(reqId);
			if (isCancelled)
			{
				logger.info(MessageConstants.MSKING_CNTRL + MessageConstants.CANCL_ONBRDREQ
						+ MessageConstants.LOG_INFO_RETURN + isCancelled);
				return TDMConstants.TRUE;
			}
			else
			{
				logger.info(MessageConstants.MSKING_CNTRL + MessageConstants.CANCL_ONBRDREQ
						+ MessageConstants.LOG_INFO_RETURN + isCancelled);
				return TDMConstants.FALSE;
			}
		}
		catch (ServiceException se)
		{
			logger.error(MessageConstants.MSKING_CNTRL + MessageConstants.CANCL_ONBRDREQ,
					TDMExceptionCode.getExceptionMsg(se));
			return TDMConstants.ERROR;
		}
	}

	@RequestMapping(value = TDMConstants.MAP_DATA_MASKING_3_CANCEL, method = RequestMethod.GET)
	public String tdmDtMaskCancelReq(
			@RequestParam(value = AppConstant.REQ_ID, required = false) String reqId,
			ModelMap model, HttpServletRequest request, HttpServletResponse response)
			throws TDMException
	{
		logger.info(MessageConstants.MSKING_CNTRL + MessageConstants.TDM_CANCEL_REQ);
		try
		{
			tdmDataMaskingService.dtMaskCancelReq(reqId);

			return AppConstant.TDM_DATA_MASKING_REDIRECT_DASHBOARD + "T";
		}
		catch (ServiceException baseEx)
		{
			logger.error(MessageConstants.MSKING_CNTRL + MessageConstants.TDM_CANCEL_REQ + baseEx);
			if (null != baseEx.getErrorCode() || baseEx.getErrorCode().equalsIgnoreCase("null"))
			{
				// String exceptionMsg = passcodes and get msg from properties
				// file by passing key
				// as
				// baseEx.getErrorCode();
				if (baseEx.getErrorCode().startsWith(""))
				{
					return AppConstant.TDM_DATA_MASKING_REDIRECT_DASHBOARD + "F";
				}
			}
			// responseMsg = passcodes and get msg from properties file by
			// passing key as
			// baseEx.getErrorCode();
			return AppConstant.TDM_DATA_MASKING_REDIRECT_DASHBOARD + "F";
		}
	}

	@RequestMapping(value = TDMConstants.TDM_DATA_MASKING_3_DEL_ROW, method = RequestMethod.GET)
	@ResponseBody
	public String tdmDeleteMaskingReqRow(
			@RequestParam(value = TDMConstants._ID, required = false) String rowId,
			@RequestParam(value = TDMConstants.ITEM_TYPE, required = false) String reqType,
			@RequestParam(value = "_", required = false) String param, HttpServletRequest request,
			HttpServletResponse response) throws TDMException
	{
		try
		{
			logger.info(MessageConstants.MSKING_CNTRL + MessageConstants.TDM_DEL_MSKING_REQROW);
			boolean isDeleted = tdmDataMaskingService.deleteRow(reqType, rowId);
			if (isDeleted)
			{
				logger.info(MessageConstants.MSKING_CNTRL + MessageConstants.TDM_DEL_MSKING_REQROW
						+ MessageConstants.LOG_INFO_RETURN + isDeleted);
				return TDMConstants.TRUE;
			}
			else
			{
				logger.info(MessageConstants.MSKING_CNTRL + MessageConstants.DELETE_ONBOARD_REQROW
						+ MessageConstants.LOG_INFO_RETURN + isDeleted);
				return TDMConstants.FALSE;
			}
		}
		catch (ServiceException se)
		{
			if (se.getErrorCode().equals(TDMExceptionCode.NORESULT))
			{
				logger.error(
						MessageConstants.MSKING_CNTRL + MessageConstants.TDM_DEL_MSKING_REQROW,
						TDMExceptionCode.getExceptionMsg(se));
			}
			return TDMConstants.FALSE;
		}
	}
}
