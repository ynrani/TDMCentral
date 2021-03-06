/*---------------------------------------------------------------------------------------
 * Object Name: TDMCmdCenterMetricsController.Java
 * 
 * Modification Block:
 * --------------------------------------------------------------------------------------
 * S.No. Name                Date      Bug Fix no. 		Desc
 * --------------------------------------------------------------------------------------
 * 1     Seshadri Chowdary   11/04/15  NA          		Created
 * 2		Sikandar Shaikh		18/06/15  Logging changes	Updated
 * --------------------------------------------------------------------------------------
 *
 * Copyright: 2015 <CapGemini>
 *---------------------------------------------------------------------------------------*/

package com.tesda.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tesda.constants.MessageConstants;
import com.tesda.constants.TDMConstants;

/**
 * @author
 * @version 1.0
 */

@Controller
public class TDMCmdCenterMetricsController
{

	private static final Logger logger = LoggerFactory
			.getLogger(TDMCmdCenterMetricsController.class);

	@RequestMapping(TDMConstants.MAP_TDM_COMMANDCENTER_METRICS)
	public String indexMatrics()
	{
		logger.info(MessageConstants.MTRICS_CNTRL + MessageConstants.INDXMTRICS
				+ MessageConstants.LOG_INFO_RETURN);
		return TDMConstants.TDM_CMDCENTER_METRICS;
	}

	/**
	 * Method for handling file download request from client
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = TDMConstants.MAP_TDM_COMMANDCENTER_METRICSDOWNLOAD, method = RequestMethod.GET)
	public void doDownloadMetrics(HttpServletRequest request, HttpServletResponse response)
			throws IOException
	{
		logger.info(MessageConstants.MTRICS_CNTRL + MessageConstants.DOWNLD_MTRCS);

		// get absolute path of the application
		ServletContext context = request.getServletContext();
		String appPath = context.getRealPath("");

		// construct the complete absolute path of the file
		String fullPath = appPath + TDMConstants.FILE_METRICS_DATATEMPLATE;
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
		logger.info(MessageConstants.MTRICS_CNTRL + MessageConstants.INDXMTRICS
				+ MessageConstants.LOG_INFO_RETURN);
	}
}
