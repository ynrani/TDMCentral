/*---------------------------------------------------------------------------------------
 * Object Name: TDMDemandController.Java
 * 
 * --------------------------------------------------------------------------------------
 *
 * Copyright: 2015 Capgemini Financial Services
 *---------------------------------------------------------------------------------------*/

package com.tesda.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tesda.constants.MessageConstants;
import com.tesda.constants.TDMConstants;
import com.tesda.model.DTO.TdmChangeMgmtDTO;
import com.tesda.model.DTO.TdmDataRefreshDTO;
import com.tesda.model.DTO.TdmDataSubsettingDTO;
import com.tesda.model.DTO.TdmTestDataRequestDTO;

/**
 * Controller class which will handle the Data Sub setting and Data Refreshing.
 * Currently we are not supporting, but future purpose keeping this class.
 */

@Controller
@Scope(TDMConstants.SCOPE_SESSION)
public class TDMDemandController
{
	private static final Logger logger = LoggerFactory.getLogger(TDMDemandController.class);

	/**
	 * @param tdgDataSubsettingDTO
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = TDMConstants.MAP_TDMDATA_SUBSETTING, method = RequestMethod.GET)
	public String tdmDataSubsettingGet(
			@ModelAttribute(TDMConstants.MODEL_TDGDATA_SUBSETTING_DTO) TdmDataSubsettingDTO tdgDataSubsettingDTO,
			ModelMap model, HttpServletRequest request, HttpServletResponse response)
	{
		logger.info(MessageConstants.DMND_CNTRL + MessageConstants.GETDTSUBSET
				+ MessageConstants.LOG_INFO_RETURN);
		return TDMConstants.TDM_DATA_SUBSETTING;
	}

	/**
	 * @param tdgChangeMgmtDTO
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = TDMConstants.MAP_TDM_CHNAGEMGMT, method = RequestMethod.GET)
	public String tdmDataChangeMgmtGet(
			@ModelAttribute(TDMConstants.MODEL_TDM_CHANGEMGMT_DTO) TdmChangeMgmtDTO tdgChangeMgmtDTO,
			ModelMap model, HttpServletRequest request, HttpServletResponse response)
	{
		logger.info(MessageConstants.DMND_CNTRL + MessageConstants.CHNGMGMTGET
				+ MessageConstants.LOG_INFO_RETURN);
		return TDMConstants.TDM_CHANGE_MANAGEMENT;
	}

	/**
	 * @param tdgDataRefreshDTO
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = TDMConstants.MAP_TDM_DATAREFRESH, method = RequestMethod.GET)
	public String tdmDataRefreshGet(
			@ModelAttribute(TDMConstants.MODEL_TDM_DATAREFRESH_DTO) TdmDataRefreshDTO tdgDataRefreshDTO,
			ModelMap model, HttpServletRequest request, HttpServletResponse response)
	{
		logger.info(MessageConstants.DMND_CNTRL + MessageConstants.DTREFRESHGET
				+ MessageConstants.LOG_INFO_RETURN);
		return TDMConstants.TDM_DATAREFRESH;
	}

	/**
	 * @param tdgTestDataRequestDTO
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = TDMConstants.MAP_TDM_TESTDATA_REQ, method = RequestMethod.GET)
	public String tdmTestDataRequestGet(
			@ModelAttribute(TDMConstants.MODEL_TDG_TESTDATAREQ_DTO) TdmTestDataRequestDTO tdgTestDataRequestDTO,
			ModelMap model, HttpServletRequest request, HttpServletResponse response)
	{
		logger.info(MessageConstants.DMND_CNTRL + MessageConstants.DTREQGET
				+ MessageConstants.LOG_INFO_RETURN);
		return TDMConstants.TDM_TESTDATA_REQ;
	}

}
