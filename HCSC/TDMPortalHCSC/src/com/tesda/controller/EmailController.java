/*---------------------------------------------------------------------------------------
 * Object Name: EmailController.Java
 * 
 * --------------------------------------------------------------------------------------
 *
 * Copyright: 2015 Capgemini Financial Services
 *---------------------------------------------------------------------------------------*/
package com.tesda.controller;

import java.security.Principal;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.tesda.constants.MessageConstants;
import com.tesda.constants.TDMConstants;
import com.tesda.exception.ServiceException;
import com.tesda.model.DTO.AutoEmailDTO;
import com.tesda.service.TDMNonStandSearchService;

/**
 * Controller class which will provides the auto email functionality. Used in
 * case of No records found while using Find test Data and to resolve the user
 * requests.
 */

@Controller
@Scope(TDMConstants.SCOPE_SESSION)
public class EmailController
{
	@Resource(name = TDMConstants.NONSTAND_SEARCH_MGMT_SERVICE)
	TDMNonStandSearchService searchManagementService;

	@Autowired
	private MessageSource messageSource;

	AutoEmailDTO autoEmailDTOs;

	private static final Logger logger = LoggerFactory.getLogger(EmailController.class);

	/**
	 * @param autoEmailDTO
	 * @param model
	 * @param result
	 * @param request
	 * @param response
	 * @param principal
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping(value = TDMConstants.MAP_POPEMAIL, method = RequestMethod.GET)
	public String popupEmailL1L2Get(
			@ModelAttribute(TDMConstants.MODEL_AUTOEMAIL_DTO) AutoEmailDTO autoEmailDTO,
			ModelMap model,
			@RequestParam(value = TDMConstants.RESULT, required = false) String result,
			HttpServletRequest request, HttpServletResponse response, Principal principal)
			throws ServiceException
	{
		String userId = null;
		if (null != (String) request.getSession().getAttribute(TDMConstants.USER_ID))
		{
			userId = (String) request.getSession().getAttribute(TDMConstants.USER_ID);

			// searchManagementService.getUserDetails(autoEmailDTO, userId);
		}
		logger.info(MessageConstants.EMAIL_CNTRL + MessageConstants.L1L2GET + result);

		result = result.replaceAll("Search Result for: ", " ");
		String msg = " Hi Team, \n \t Test Data for the below Search Criteria requested by "
				+ userId + " is not available. \n \t Search Criteria: \n" + result
				+ " \n \n Please look into this request." + "\n \n Thanks!!!";
		autoEmailDTO.setSubject("Test Data Request by " + userId);
		autoEmailDTO.setResult(msg);
		autoEmailDTO.setUserId(userId);
		autoEmailDTO.setTo(messageSource.getMessage("mail.username", null, null));
		autoEmailDTO.setCc((String) request.getSession().getAttribute(TDMConstants.EMAIL_ID));
		logger.info(MessageConstants.EMAIL_CNTRL + MessageConstants.L1L2GET
				+ MessageConstants.LOG_INFO_RETURN);
		autoEmailDTOs = autoEmailDTO;
		autoEmailDTOs.setMsg(result);
		return TDMConstants.EMAIL_POPUP;
	}

	@RequestMapping(value = TDMConstants.MAP_L1L2SENDMAIL, method = RequestMethod.POST)
	@ResponseBody
	public String popupEmailL1L2Send(
			@ModelAttribute(TDMConstants.MODEL_AUTOEMAIL_DTO) AutoEmailDTO autoEmailDTO,
			ModelMap model,
			@RequestParam(value = TDMConstants.RESULT, required = false) String result,
			HttpServletRequest request, HttpServletResponse response, Principal principal)
			throws ServiceException
	{
		autoEmailDTOs.setMsg(autoEmailDTOs.getMsg().replace(";", "<br>"));
		searchManagementService.l1l2SupportNS(autoEmailDTOs);

		logger.info(MessageConstants.EMAIL_CNTRL + MessageConstants.L1L2GET
				+ MessageConstants.LOG_INFO_RETURN);
		return "emailsendsuccessfully";
	}

	/**
	 * @param autoEmailDTO
	 * @param model
	 * @param user
	 * @param reserveId
	 * @param result
	 * @param request
	 * @param response
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = TDMConstants.MAP_POPUPEMAIL_USER, method = RequestMethod.GET)
	public String popupEmailGet(
			@ModelAttribute(TDMConstants.MODEL_AUTOEMAIL_DTO) AutoEmailDTO autoEmailDTO,
			ModelMap model, @RequestParam(value = TDMConstants.USER, required = false) String user,
			@RequestParam(value = TDMConstants.RESERVE_ID, required = false) String reserveId,
			@RequestParam(value = TDMConstants.RESULT, required = false) String result,

			HttpServletRequest request, HttpServletResponse response, Principal principal)
	{
		logger.info(MessageConstants.EMAIL_CNTRL + MessageConstants.POPUPEMAIL + user);
		String tmp = "Dear " + user
				+ ", \n \t Please Un-reserve the record for the following search scenario \n" + "'"
				+ result + "'" + " and Test Case Id: " + reserveId + "\n\nRegards, \n"
				+ (String) request.getSession().getAttribute(TDMConstants.USER_ID);

		autoEmailDTO.setSubject("Data Un-reserve For Following Search Criteria and Test Case ID");
		autoEmailDTO.setTo(user + "@capgemini.com");
		autoEmailDTO.setMsg(tmp);

		logger.info(MessageConstants.EMAIL_CNTRL + MessageConstants.POPUPEMAIL
				+ MessageConstants.LOG_INFO_RETURN);
		return TDMConstants.EMAIL_POPUP;
	}

}
