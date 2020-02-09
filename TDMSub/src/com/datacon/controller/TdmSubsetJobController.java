/*
 * Object Name : TdmSubsetJobController.java
 * Modification Block
 * ------------------------------------------------------------------
 * S.No.	Name 			Date			Bug_Fix_No			Desc
 * ------------------------------------------------------------------
 * 	1.	  vkrish14		2:05:41 PM				Created
 * ------------------------------------------------------------------
 * Copyrights: 2015 Capgemini.com
 */
package com.datacon.controller;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.datacon.exception.ServiceException;
import com.datacon.model.DTO.DbConnectionsDTO;
import com.datacon.model.DTO.TdmProfilerDTO;

/**
 * @author vkrish14
 *
 */
@Controller
public class TdmSubsetJobController{
	
	private static Logger logger = Logger.getLogger(TdmSubsetJobController.class);
	
	public static final String strClassName = "TdmSubsetJobController";
	
	
}
