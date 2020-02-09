/*
 * Object Name : AppLocaleConstant.java
 * Modification Block
 * ------------------------------------------------------------------
 * S.No.	Name 			Date			Bug_Fix_No			Desc
 * ------------------------------------------------------------------
 * 	1.	  vkrish14		4:26:32 PM				Created
 * ------------------------------------------------------------------
 * Copyrights: 2015 Capgemini.com
 */
package com.tdm.constant;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

/**
 * @author vkrish14
 *
 */
public class AppLocaleConstant{
	//
	//static InputStream input = null;

	public static Properties getProperties(String locale){
		InputStream input = null;
		Properties prop = new Properties();
	try {


		//input = new FileInputStream("../properties/messages_"+locale+".properties");
		input = AppLocaleConstant.class.getResourceAsStream("/properties/messages_"+locale+".properties");
		// load a properties file
		prop.load(new InputStreamReader(input, Charset.forName("UTF-8")));

	} catch (IOException ex) {
		ex.printStackTrace();
	} finally {
		if (input != null) {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	return prop;
	}
}
