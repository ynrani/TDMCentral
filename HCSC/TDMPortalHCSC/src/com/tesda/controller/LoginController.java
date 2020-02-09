/*---------------------------------------------------------------------------------------
 * Object Name: LoginController.Java
 * 
 * --------------------------------------------------------------------------------------
 *
 * Copyright: 2015 Capgemini Financial Services
 *---------------------------------------------------------------------------------------*/

package com.tesda.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tesda.constants.AppConstant;
import com.tesda.constants.MessageConstants;
import com.tesda.constants.TDMConstants;
import com.tesda.model.DTO.ForgotPassword;
import com.tesda.model.DTO.TDMLoginUsersDTO;
import com.tesda.service.TDMAdminService;
import com.tesda.service.TDMNonStandSearchService;

/**
 * Controller class which will handle the login request. Based on authentication
 * user will be redirected or informed with a message.
 */

@Controller
public class LoginController
{

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Resource(name = TDMConstants.NONSTAND_SEARCH_MGMT_SERVICE)
	TDMNonStandSearchService searchManagementService;

	@Resource(name = TDMConstants.TDM_ADMIN_SERVICE)
	TDMAdminService tDMAdminService;

	/**
	 * @param error
	 * @param logout
	 * @return
	 */
	@RequestMapping(value = TDMConstants.MAP_LOGIN, method = RequestMethod.GET)
	public ModelAndView login(
			@RequestParam(value = TDMConstants.ERROR, required = false) String error,
			@RequestParam(value = TDMConstants.LOGOUT, required = false) String logout)
	{

		logger.info(MessageConstants.LOGIN_CNTRL + MessageConstants.LOGIN);

		ModelAndView model = new ModelAndView();
		if (error != null)
		{
			model.addObject(TDMConstants.ERROR, "Invalid username and password!");
			model.addObject("msg",
					"You are not a registered user, Please contact administrator.");
		}
		else
		{
			model.addObject("msg",
					"You are not allowed to perform 'Back' or You have not logged in or Session Expired.");
		}
		if (logout != null)
		{
			// logger.info("logout called");
			model.addObject("msg", "You've been logged out successfully.");

		}

		model.setViewName(TDMConstants.LOGIN);

		logger.info(MessageConstants.LOGIN_CNTRL + MessageConstants.LOGIN
				+ MessageConstants.LOG_INFO_RETURN);
		return model;

	}

	/**
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(TDMConstants.MAP_TESDA_LOGIN)
	public String login(ModelMap model, HttpServletRequest request, HttpServletResponse response)
	{
		return TDMConstants.LOGIN;
	}

	/**
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/")
	public String login2(ModelMap model, HttpServletRequest request, HttpServletResponse response)
	{
		return "login";
	}

	/**
	 * @return
	 */
	@RequestMapping(TDMConstants.MAP_TESDA_ADMIN)
	public String admin()
	{
		return TDMConstants.ADMIN;
	}

	/**
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(TDMConstants.MAP_INDEX)
	public String index(ModelMap model, HttpServletRequest request, HttpServletResponse response)
	{
		try
		{

			TDMLoginUsersDTO loginUsersDTO = new TDMLoginUsersDTO();
			loginUsersDTO.setApplicationName((String) request.getSession().getAttribute(
					TDMConstants.PROJECT_ID));
			loginUsersDTO.setEmailId((String) request.getSession().getAttribute(
					TDMConstants.EMAIL_ID));
			loginUsersDTO.setUserId((String) request.getSession()
					.getAttribute(TDMConstants.USER_ID));
			loginUsersDTO.setUserName((String) request.getSession().getAttribute(
					TDMConstants.USER_NAME));
			loginUsersDTO.setRole((String) request.getSession().getAttribute(TDMConstants.ROLE));

			tDMAdminService.saveLoginUserDetails(loginUsersDTO);

			/*
			 * String userId = (String)
			 * request.getSession().getAttribute(TDMConstants.USER_ID);
			 * TdmUserDTO userDto = tDMAdminService.getEditUser(userId);
			 * request.getSession().setAttribute(TDMConstants.USER_NAME,
			 * userDto.getUsername());
			 */
			return TDMConstants.INDEX;
		}
		catch (Exception ex)
		{
			logger.error(MessageConstants.TDM_LOGIN_CTLR + TDMConstants.INDEX
					+ MessageConstants.LOG_ERROR_EXCEPTION + ex);
			return TDMConstants.INDEX;
		}
	}

	/**
	 * @return
	 */
	@RequestMapping(TDMConstants.MAP_INDEXCOMMANDOR)
	public String indexCmdCtr()
	{
		return TDMConstants.INDEX_COMMAND_CENTER;
	}

	/**
	 * @return
	 */
	@RequestMapping(TDMConstants.MAP_403PAGE)
	public String accessDenied()
	{
		return TDMConstants.REDIRCT_ACCESSDENIED;
	}

	/**
	 * @return
	 */
	@RequestMapping(TDMConstants.MAP_SESSIONEXP)
	public String sessionExp()
	{
		return TDMConstants.LOGIN;
	}

	/**
	 * @return
	 */
	@RequestMapping(TDMConstants.MAP_AUTHFAIL)
	public String authFail()
	{
		return TDMConstants.LOGIN;
	}

	/**
	 * @return
	 */
	@RequestMapping(TDMConstants.MAP_BACK)
	public String backToLogin()
	{
		return TDMConstants.LOGIN;
	}

	/**
	 * @return
	 */
	@RequestMapping(TDMConstants.MAP_TDM_TRAINING)
	public String tdmTraining()
	{
		return TDMConstants.TDM_TRAINING;
	}

	/**
	 * @param model
	 * @param principal
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(TDMConstants.MAP_LOGOUT)
	public String logout(ModelMap model, Principal principal, HttpServletRequest request,
			HttpServletResponse response)
	{

		logger.info(MessageConstants.LOGIN_CNTRL + MessageConstants.LOGOUT);

		String currentUser = null;

		if (null != (String) request.getSession().getAttribute(TDMConstants.USER_ID))
		{
			currentUser = (String) request.getSession().getAttribute(TDMConstants.USER_ID);
		}
		if (null != currentUser)
		{

			request.getSession().invalidate();
			try
			{
				request.logout();
			}
			catch (ServletException e)
			{
				System.out.println("In logout request.logout()");
				logger.error(MessageConstants.LOGIN_CNTRL + MessageConstants.LOGOUT,
						"Exception occured " + e);
				// e.printStackTrace();
			}
			/*
			 * for (Object principalObj : sessionRegistry.getAllPrincipals()) {
			 * for (SessionInformation session :
			 * sessionRegistry.getAllSessions(principalObj, false)) { if
			 * (principal.getName().equalsIgnoreCase(currentUser)) {
			 * session.expireNow(); } } }
			 */
		}

		logger.info(MessageConstants.LOGIN_CNTRL + MessageConstants.LOGOUT
				+ MessageConstants.LOG_INFO_RETURN);
		return TDMConstants.LOGIN;
	}

	@RequestMapping(value = TDMConstants.MAP_FORGOT_PASSWORD, method = RequestMethod.GET)
	public String forgotPassword()
	{
		logger.info("forgot password initiated");

		return TDMConstants.FORGOT_PASSWORD;
	}

	/**
	 * @param forgotPasswordDto
	 * @param modelmap
	 * @return
	 */
	@RequestMapping(value = AppConstant.TDM_LOGIN_FORGOT, method = RequestMethod.POST)
	public String forgotPass(@ModelAttribute ForgotPassword forgotPasswordDto, ModelMap modelmap)
			throws Exception
	{
		logger.info(MessageConstants.TDM_LOGIN_CTLR + MessageConstants.TDM_FORGOT_PASS
				+ MessageConstants.LOG_INFO_PARAMS_NO);
		try
		{
			if (searchManagementService.forgotPassword(forgotPasswordDto))
			{
				modelmap.addAttribute("msg", "Password sent to registered email Id.");
				return "login";
			}
			else
			{
				modelmap.addAttribute("msg",
						"User ID is not registered, please contact administrator!!");
			}
			logger.info(MessageConstants.TDM_LOGIN_CTLR + MessageConstants.TDM_FORGOT_PASS
					+ MessageConstants.LOG_INFO_RETURN);
			modelmap.addAttribute(TDMConstants.MODEL_FORGOTPASSWORD_DTO, forgotPasswordDto);
			return "login";
		}
		catch (Exception baseEx)
		{
			logger.error(MessageConstants.TDM_LOGIN_CTLR + MessageConstants.TDM_FORGOT_PASS
					+ MessageConstants.LOG_ERROR_EXCEPTION + baseEx);
			return "login";
		}
	}

	/**
	 * @param forgotPasswordDto
	 * @param modelmap
	 * @return
	 */
	@RequestMapping(value = TDMConstants.MAP_LOGINFORGOT_PASSWORD, method = RequestMethod.GET)
	public String forgotPassOld(
			@ModelAttribute(TDMConstants.MODEL_FORGOTPASSWORD_DTO) ForgotPassword forgotPasswordDto,
			ModelMap modelmap)
	{
		/*
		 * if (null != forgotPasswordDto) modelmap.addAttribute("email",
		 * "Email sent"); else modelmap.addAttribute("email",
		 * "Email sent failed. Please Try Again");
		 */

		logger.info(MessageConstants.LOGIN_CNTRL + MessageConstants.FORGOTPASS);
		return TDMConstants.FORGOT_PASSWORD;
	}

	/**
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = TDMConstants.MAP_DOWNLOAD_USERMANUAL, method = RequestMethod.GET)
	public void doDownloadTdm(HttpServletRequest request, HttpServletResponse response)
			throws IOException
	{

		logger.info(MessageConstants.LOGIN_CNTRL + MessageConstants.USERMANUAL);
		// get absolute path of the application
		ServletContext context = request.getServletContext();
		String appPath = context.getRealPath("");

		// construct the complete absolute path of the file
		String fullPath = appPath + TDMConstants.FILE_USER_MANUAL;
		File downloadFile = new File(fullPath);
		FileInputStream inputStream = new FileInputStream(downloadFile);

		// get MIME type of the file
		String mimeType = context.getMimeType(fullPath);
		if (mimeType == null)
		{
			// set to binary type if MIME mapping not found
			mimeType = TDMConstants.MIME_MAPPING;
		}

		// set content attributes for the response
		response.setContentType(mimeType);
		response.setContentLength((int) downloadFile.length());

		// set headers for the response
		String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
		response.setHeader(TDMConstants.HEADER_KEY, headerValue);

		// get output stream of the response
		OutputStream outStream = response.getOutputStream();

		byte[] buffer = new byte[TDMConstants.BUFFER_SIZE];
		int bytesRead = -1;

		// write bytes read from the input stream into the output stream
		while ((bytesRead = inputStream.read(buffer)) != -1)
		{
			outStream.write(buffer, 0, bytesRead);
		}

		inputStream.close();
		outStream.close();
		logger.info(MessageConstants.LOGIN_CNTRL + MessageConstants.USERMANUAL
				+ MessageConstants.LOG_INFO_RETURN);
	}
}
