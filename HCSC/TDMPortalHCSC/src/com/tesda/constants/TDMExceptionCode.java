/*---------------------------------------------------------------------------------------
 * Object Name: TDMOnBoardReqController.Java
 * 
 * --------------------------------------------------------------------------------------
 *
 * Copyright: 2015 Capgemini Financial Services
 *---------------------------------------------------------------------------------------*/

package com.tesda.constants;

import java.io.IOException;
import java.util.Properties;

import com.tesda.exception.TDMException;

/**
 * Exception code class which provides different types of exception codes which
 * are defined in the file properties/messages.properties
 */

public class TDMExceptionCode
{
	protected static Properties prop;

	static
	{
		prop = new Properties();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		try
		{
			prop.load(loader.getResourceAsStream("properties/messages.properties"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static final String EXCEPTION = "exception";

	public static final String NULL_POINTR = "null";
	public static final String ILLEGAL_STATE = "illegalstate";
	public static final String ILLEGAL_ARGUMENT = "illegalargument";
	public static final String NORESULT = "noresult";

	public static String getExceptionMsg(TDMException ex)
	{
		if (ex.getErrorCode().equals(EXCEPTION))
		{
			return ex.getMessage();
		}
		else
		{
			return prop.getProperty(ex.getErrorCode());
		}
	}
}
