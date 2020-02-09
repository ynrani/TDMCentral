/*
 * Object Name : ProfilerRunningService.java
 * Modification Block
 * ------------------------------------------------------------------
 * S.No.	Name 			Date			Bug_Fix_No			Desc
 * ------------------------------------------------------------------
 * 	1.	  vkrish14		11:05:47 AM				Created
 * ------------------------------------------------------------------
 * Copyrights: 2015 Capgemini.com
 */
package com.datacon.service;

import java.util.List;

import com.datacon.exception.ServiceException;

/**
 * @author vkrish14
 *
 */
public interface ProfilerRunningService{
	
	List<String> getGenerateQueries(String string, String string2, String string3) throws ServiceException;
}
