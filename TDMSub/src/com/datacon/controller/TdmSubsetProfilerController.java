/*
 * Object Name : TdmSubsetProfilerController.java
 * Modification Block
 * ------------------------------------------------------------------
 * S.No.	Name 			Date			Bug_Fix_No			Desc
 * ------------------------------------------------------------------
 * 	1.	  vkrish14		10:28:50 AM				Created
 * ------------------------------------------------------------------
 * Copyrights: 2015 Capgemini.com
 */
package com.datacon.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.datacon.constant.AppConstant;
import com.datacon.constant.MessageConstant;
import com.datacon.exception.BaseException;
import com.datacon.exception.ServiceException;
import com.datacon.model.DTO.DbConnectionsDTO;
import com.datacon.model.DTO.ProfilerDashboardListDTO;
import com.datacon.model.DTO.TdmProfilerDTO;
import com.datacon.service.DbConnectionService;
import com.datacon.service.ProfilerCreateService;
import com.datacon.util.PaginationUtil;
import com.datacon.util.TdgCentralConstant;

/**
 * @author vkrish14
 *
 */
@Controller
public class TdmSubsetProfilerController{
	
	private static Logger logger = Logger.getLogger(TdmSubsetProfilerController.class);
	
	public static final String strClassName = "TdmSubsetProfilerController";
	
	@Resource(name = MessageConstant.DB_CON_SERVICE)
	DbConnectionService dbConnectionService;
	
	@Resource(name = "profilerCreateService")
	ProfilerCreateService profilerCreateService;
	List<DbConnectionsDTO> lstAvailableConnections = null;
	TdmProfilerDTO tempTdmProfilerDTO = null;
	List<String> lstCons = null;
	
	@RequestMapping(value = "/createProfiler",  method = RequestMethod.GET)
	public String createProfilerDictionary(
			@ModelAttribute("tdmProfilerDTO")TdmProfilerDTO tdmProfilerDTO, ModelMap model){
		logger.info(strClassName+" ~ createProfilerDictionary ~ inside method");
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		try {
			tempTdmProfilerDTO = null;
			lstAvailableConnections = dbConnectionService.getAvailableConnections(user.getUsername());
			lstCons = new ArrayList<String>();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(lstAvailableConnections != null && !lstAvailableConnections.isEmpty()){
			for(DbConnectionsDTO dto : lstAvailableConnections){
				lstCons.add(dto.getConnectionName());
			}
		}
		
			tdmProfilerDTO.setConnectionNames(lstCons);
			
		model.addAttribute("tdmProfilerDTO", tdmProfilerDTO);
		logger.info(strClassName+" ~ createProfilerDictionary ~ returned from method");
		return "createProfilePage1";
		//}
		//logger.info(strClassName+" ~ createMasterDictionary ~ returned from method");
		//return "tdgCreateDictionaryPage1";
	}
	
	
	@RequestMapping(value = "/createProfiler",  method = RequestMethod.POST)
	public String createNextProfilerDictionary(
			@ModelAttribute("tdmProfilerDTO") TdmProfilerDTO tdmProfilerDTO, ModelMap model){
		logger.info(strClassName + " ~ createNextProfilerDictionary ~ inside method");
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		try {
			if (lstAvailableConnections == null || lstAvailableConnections.isEmpty())
				lstAvailableConnections = dbConnectionService.getAvailableConnections(user
						.getUsername());
			//List<String> lstCons = new ArrayList<String>();
			String status = profilerCreateService.checkProfilerName(tdmProfilerDTO.getProfilerName());
			if(status.contains(AppConstant.FAILED)){
				tdmProfilerDTO.setMessage("Profile name already registerd");
					tdmProfilerDTO.setConnectionNames(lstCons);
				model.addAttribute("tdmProfilerDTO", tdmProfilerDTO);
				return "createProfilePage1";
			}
			if (lstAvailableConnections != null && !lstAvailableConnections.isEmpty()) {
				for (DbConnectionsDTO dto : lstAvailableConnections) {
					if (tdmProfilerDTO.getSelectedConnectionName().equalsIgnoreCase(dto.getConnectionName())) {
						StringBuffer url = new StringBuffer();
						StringBuffer username = new StringBuffer();
						StringBuffer password = new StringBuffer();
						url.append(getUrl(dto));
						username.append(dto.getUser());
						password.append(dto.getPass());
						List<String> listFinalTabs = new ArrayList<String>();
						// listFinalTabs.add("All");
						listFinalTabs.addAll(profilerCreateService.getAllTabs(url.toString(),
								username.toString(), password.toString()));
						//lstCons.add(dto.getHostName());
						tdmProfilerDTO.setListTables(listFinalTabs);
						tdmProfilerDTO.setStrUrl(url.toString());
						tdmProfilerDTO.setUserName(username.toString());
						tdmProfilerDTO.setPassword(password.toString());
					}
				}
			}
			//tdmProfilerDTO.setConnectionNames(lstCons);
		} catch (BaseException baseEx) {
		}
		model.addAttribute("tdmProfilerDTO", tdmProfilerDTO);
		tempTdmProfilerDTO = tdmProfilerDTO;
		tempTdmProfilerDTO.setUserId(user.getUsername());
		logger.info(strClassName + " ~ createProfilerDictionary ~ returned from method");
		return "redirect:createProfilePage";
		// }
		// logger.info(strClassName+" ~ createMasterDictionary ~ returned from method");
		// return "tdgCreateDictionaryPage1";
	}
	
	@RequestMapping(value = "/createProfilePage",  method = RequestMethod.GET)
	public String createProfilerFinalStep1Dictionary(
			@ModelAttribute("tdmProfilerDTO")TdmProfilerDTO tdmProfilerDTO, ModelMap model){
		logger.info(strClassName+" ~ createProfilerFinalStep1Dictionary ~ inside method");
		/*try{
		if(StringUtils.isNotEmpty(reqVals) && tempTdmProfilerDTO != null){
			for (DbConnectionsDTO dto : lstAvailableConnections) {
				if (tdmProfilerDTO.getSelectedConnectionName().equalsIgnoreCase(dto.getHostName())) {
					StringBuffer url = new StringBuffer();
					StringBuffer username = new StringBuffer();
					StringBuffer password = new StringBuffer();
					url.append(getUrl(dto));
					username.append(dto.getUser());
					password.append(dto.getPass());
					List<String> lstpassedTabs = new ArrayList<String>();
					List<String> listFinalColumns = new ArrayList<String>();
					if(reqVals.contains(",")){
						String[] splits = reqVals.split(",");
						lstpassedTabs.addAll(Arrays.asList(splits));
					}else
						lstpassedTabs.add(reqVals);
					lstpassedTabs.removeAll(Arrays.asList(null,""));
					// listFinalTabs.add("All");
					listFinalColumns.addAll(profilerCreateService.getColsByTabs(tempTdmProfilerDTO.getStrUrl(),
							tempTdmProfilerDTO.getUserName(), tempTdmProfilerDTO.getPassword(),lstpassedTabs));
					//lstCons.add(dto.getHostName());
					tempTdmProfilerDTO.setListColumns(listFinalColumns);
				}
			}
		}
	} catch (BaseException baseEx) {
	}*/
		tdmProfilerDTO = tempTdmProfilerDTO;
		model.addAttribute("tdmProfilerDTO", tdmProfilerDTO);
		//tempTdmProfilerDTO = tdmProfilerDTO;
		logger.info(strClassName+" ~ createProfilerFinalStep1Dictionary ~ returned from method");
		return "createProfilePage2";
		//}
		//logger.info(strClassName+" ~ createMasterDictionary ~ returned from method");
		//return "tdgCreateDictionaryPage1";
	}
	
	@RequestMapping(value = "/createProfileSubmitPage",  method = RequestMethod.GET)
	public @ResponseBody String createProfilerFinalStep(
			@ModelAttribute("tdmProfilerDTO")TdmProfilerDTO tdmProfilerDTO, ModelMap model,@RequestParam(required = false, value = "reqVals") String reqVals){
		//String response= AppConstant.FAILED+"# Error occurred while creating profile";
		try{
		if(StringUtils.isNotEmpty(reqVals) && reqVals.contains("CREATEPROFILER")){
			String strResposne = profilerCreateService.saveProfiler(tempTdmProfilerDTO,reqVals.replaceAll("$CREATEPROFILER", ""));
			if(strResposne.contains("SUCCESS"))
				return AppConstant.SUCCESS+"#Profile created Successfully";
			else 
				return AppConstant.FAILED+"#Error occurred while creating profile";
		}
		} catch (BaseException baseEx) {
		}
			tdmProfilerDTO = tempTdmProfilerDTO;
			model.addAttribute("tdmProfilerDTO", tdmProfilerDTO);
			//tempTdmProfilerDTO = tdmProfilerDTO;
			logger.info(strClassName+" ~ createProfilerFinalStep1Dictionary ~ returned from method");
			return "createProfilePage2";
	}
			
	@RequestMapping(value = "/createProfilePage",  method = RequestMethod.POST)
	public String createProfilerFinal2Step1Dictionary(
			@ModelAttribute("tdmProfilerDTO")TdmProfilerDTO tdmProfilerDTO, ModelMap model,@RequestParam(required = false, value = "reqVals") String reqVals){
		logger.info(strClassName+" ~ createProfilerFinalStep1Dictionary ~ inside method");
		try{
		if((StringUtils.isNotEmpty(reqVals) || StringUtils.isNotEmpty(tdmProfilerDTO.getFinalTabs())) && tempTdmProfilerDTO != null){
			/*for (DbConnectionsDTO dto : lstAvailableConnections) {
				if (tdmProfilerDTO.getSelectedConnectionName().equalsIgnoreCase(dto.getHostName())) {
					StringBuffer url = new StringBuffer();
					StringBuffer username = new StringBuffer();
					StringBuffer password = new StringBuffer();
					url.append(getUrl(dto));
					username.append(dto.getUser());
					password.append(dto.getPass());*/
			if(StringUtils.isNotEmpty(reqVals) && reqVals.contains("CREATEPROFILER")){
				String strResposne = profilerCreateService.saveProfiler(tempTdmProfilerDTO,reqVals.replaceAll("$CREATEPROFILER", ""));
				if(strResposne.contains("SUCCESS"))
					return "Profile created Successfully";
				else 
					return "Profile creation is failed";
			}else{
					List<String> lstpassedTabs = new ArrayList<String>();
					List<String> listFinalColumns = new ArrayList<String>();
					
						if(tdmProfilerDTO.getFinalTabs().contains(",")){
							while(true){
								if(!tdmProfilerDTO.getFinalTabs().contains(",,"))
									break;
								if(tdmProfilerDTO.getFinalTabs().contains(",,")){
									tdmProfilerDTO.setFinalTabs(tdmProfilerDTO.getFinalTabs().replaceAll(",,", ","));
								}
								
							}
							String[] splits = tdmProfilerDTO.getFinalTabs().split(",");
							lstpassedTabs.addAll(Arrays.asList(splits));
						}else
							lstpassedTabs.add(tdmProfilerDTO.getFinalTabs());
						//}
					lstpassedTabs.removeAll(Arrays.asList(null,""));
					lstpassedTabs.remove("ALL");
					// listFinalTabs.add("All");
					listFinalColumns.addAll(profilerCreateService.getColsByTabs(tempTdmProfilerDTO.getStrUrl(),
							tempTdmProfilerDTO.getUserName(), tempTdmProfilerDTO.getPassword(),lstpassedTabs));
					//lstCons.add(dto.getHostName());
					tempTdmProfilerDTO.setStartTable(tdmProfilerDTO.getStartTable());
					tempTdmProfilerDTO.setFinalTabs(tdmProfilerDTO.getFinalTabs());
					tempTdmProfilerDTO.setListColumns(listFinalColumns);
					tempTdmProfilerDTO.setListSelectedTabs(lstpassedTabs);
			}
					//tempTdmProfilerDTO.setStrPostMethodTables("#"+reqVals.replaceAll(",", "#")+"#");
				/*}
			}*/
		}
	} catch (BaseException baseEx) {
	}
		tdmProfilerDTO = tempTdmProfilerDTO;
		model.addAttribute("tdmProfilerDTO", tdmProfilerDTO);
		//tempTdmProfilerDTO = tdmProfilerDTO;
		logger.info(strClassName+" ~ createProfilerFinalStep1Dictionary ~ returned from method");
		return "createProfilePage2";
		//}
		//logger.info(strClassName+" ~ createMasterDictionary ~ returned from method");
		//return "tdgCreateDictionaryPage1";
	}
	
	public StringBuffer getUrl(DbConnectionsDTO dbConnectionsDTO){
		StringBuffer url = new StringBuffer();
		try {
			if (null != dbConnectionsDTO) {
				// build url start
				if (StringUtils.isNotEmpty(dbConnectionsDTO.getDbType())) {
					if (dbConnectionsDTO.getDbType().equalsIgnoreCase("Oracle")) {
						// driver class
						//Class.forName(AppConstant.ORA_DRIVER);
						// driver url
						url.append(AppConstant.ORA_URL);
						// Host name
						url.append(dbConnectionsDTO.getHostName());
						// port number
						url.append(':');
						url.append(dbConnectionsDTO.getPort());
						// sid/service/db name
						url.append(':');
						url.append(dbConnectionsDTO.getSid());
					} else if (dbConnectionsDTO.getDbType().equalsIgnoreCase("SqlServer")) {
						// driver class
						//Class.forName(AppConstant.SQL_SERVER_DRIVER);
						// driver url
						url.append(AppConstant.SQL_SERVER_URL);
						// Host name
						url.append(dbConnectionsDTO.getHostName());
						// port number
						url.append(':');
						url.append(dbConnectionsDTO.getPort());
						// sid/service/db name
						url.append(";databaseName=");
						url.append(dbConnectionsDTO.getSid());
						url.append(";");
					} else if (dbConnectionsDTO.getDbType().equalsIgnoreCase("MySql")) {
						// driver class
						//Class.forName(AppConstant.MYSQL_DRIVER);
						// driver url
						url.append(AppConstant.MYSQL_URL);
						// Host name
						url.append(dbConnectionsDTO.getHostName());
						// port number
						url.append(':');
						url.append(dbConnectionsDTO.getPort());
						// sid/service/db name
						url.append('/');
						url.append(dbConnectionsDTO.getSid());
					} else if (dbConnectionsDTO.getDbType().equalsIgnoreCase("DB2")) {
						// driver class
						//Class.forName(AppConstant.DB2_DRIVER);
						// driver url
						// jdbc:db2://<hostname>:50000/SAMPLE:currentSchema=USRES;
						url.append(AppConstant.DB2_URL);
						// Host name
						url.append(dbConnectionsDTO.getHostName());
						// port number
						url.append(':');
						url.append(dbConnectionsDTO.getPort());
						// sid/service/db name
						url.append("/SAMPLE:currentSchema=");
						url.append(dbConnectionsDTO.getSid());
					}
				}// build url end
			}
			return url;
		} catch (Exception otherEx) {
			// releaseEntityMgrForRollback(entityManager);
			otherEx.printStackTrace();
			return null;
		}
	}
	
	
	@RequestMapping(value = "/tdmProfilersDashboard", method = RequestMethod.GET)
	public String tdmsubsetProfilersDashboard(
			@ModelAttribute("profilerDashboardListDTO") ProfilerDashboardListDTO profilerDashboardListDTO,
			HttpServletRequest request, HttpServletResponse response, ModelMap model){
		String strMethodName = " [ tdmsubsetProfilers() ]";
		logger.info(strClassName + strMethodName + " inside of tdmsubsetProfilers get method ");
		

			try {
			Long totalRecords = 0L;
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			PaginationUtil pagenation = new PaginationUtil();
			long lRequestId =  0;
			int recordsperpage = Integer.valueOf(TdgCentralConstant.PAGINATION_SIZE);
			int offSet;
				offSet = pagenation.getOffset(request, recordsperpage);
			
			totalRecords = profilerCreateService
					.getExistingProfilersCount(user.getUsername());
			logger.debug(strClassName + strMethodName + " Total records found in server is :: "
					+ totalRecords);
			ProfilerDashboardListDTO tempprofilerDashboardListDTO = profilerCreateService.fetchAllProfiles(
					lRequestId, offSet, recordsperpage, true,user.getUsername());
			pagenation.paginate(totalRecords, request, Double.valueOf(recordsperpage),
					recordsperpage);
			int noOfPages = (int) Math.ceil(totalRecords.doubleValue() / recordsperpage);
			request.setAttribute("noOfPages", noOfPages);
			model.addAttribute("profilerDashboardListDTO", tempprofilerDashboardListDTO);
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		logger.info(strClassName + strMethodName + " return from tdmsubsetProfilers get method ");
		return "profilerLists";
	}

	@RequestMapping(value = "/tdmProfilersDashboard", method = RequestMethod.POST)
	public String tdgDashBoardDetails(@RequestParam(value = "page", required = false) String page,
			@ModelAttribute("profilerDashboardListDTO") ProfilerDashboardListDTO profilerDashboardListDTO,
			ModelMap model, HttpServletRequest request, HttpServletResponse response){
		String strMethodName = " [ tdgDashBoardDetails() ]";
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		logger.info(strClassName + strMethodName + " inside of tdgDashBoardDetails post method ");
		try {
			Long totalRecords = 0L;
			PaginationUtil pagenation = new PaginationUtil();
			int recordsperpage = Integer.valueOf(TdgCentralConstant.PAGINATION_SIZE);
			int offSet = pagenation.getOffset(request, recordsperpage);
			long lRequestId = 0;
			totalRecords = profilerCreateService.getExistingProfilersCount(user.getUsername());
			logger.debug(strClassName + strMethodName + " Total records found in server is :: "
					+ totalRecords);
			ProfilerDashboardListDTO tempprofilerDashboardListDTO = profilerCreateService
					.fetchAllProfiles(lRequestId, offSet, recordsperpage, true, user.getUsername());
			pagenation.paginate(totalRecords, request, Double.valueOf(recordsperpage),
					recordsperpage);
			int noOfPages = (int) Math.ceil(totalRecords.doubleValue() / recordsperpage);
			request.setAttribute("noOfPages", noOfPages);
			model.addAttribute("profilerDashboardListDTO", tempprofilerDashboardListDTO);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("profilerDashboardListDTO", profilerDashboardListDTO);
		}
		logger.info(strClassName + strMethodName + " return from tdgDashBoardDetails post method ");
		return "profilerLists";
	}
	
	@RequestMapping(value = "/deleteProfiler",  method = RequestMethod.GET)
	public String deleteProfile(@RequestParam(required = true, value = "reqprofileName") String reqVals,
			@ModelAttribute("tdmProfilerDTO")TdmProfilerDTO tdmProfilerDTO, ModelMap model){
		logger.info(strClassName+" ~ createProfilerDictionary ~ inside method");
		try {
			if(StringUtils.isNotEmpty(reqVals)){
				profilerCreateService.deleteProfiler(reqVals);
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ProfilerDashboardListDTO profilerDashboardListDTO = new ProfilerDashboardListDTO();
		model.addAttribute("profilerDashboardListDTO", profilerDashboardListDTO);
		logger.info(strClassName+" ~ createProfilerDictionary ~ returned from method");
		return "redirect:tdmProfilersDashboard";
		//}
		//logger.info(strClassName+" ~ createMasterDictionary ~ returned from method");
		//return "tdgCreateDictionaryPage1";
	}
	@RequestMapping(value = "/editProfiler",  method = RequestMethod.POST)
	public String editFetchColsProfiler(@ModelAttribute("tdmProfilerDTO") TdmProfilerDTO tdmProfilerDTO1, ModelMap model){
		try {
			List<String> lstpassedTabs = new ArrayList<String>();
			List<String> listFinalColumns = new ArrayList<String>();
			if (tdmProfilerDTO1.getFinalTabs().contains(",")) {
				String[] splits = tdmProfilerDTO1.getFinalTabs().split(",");
				lstpassedTabs.addAll(Arrays.asList(splits));
			} else
				lstpassedTabs.add(tdmProfilerDTO1.getFinalTabs());
			// }
			lstpassedTabs.removeAll(Arrays.asList(null, ""));
			if(lstpassedTabs.size() > 1){
			StringBuffer buffer =new StringBuffer();
			for(int i=0;i<lstpassedTabs.size();i++){
				buffer.append(lstpassedTabs.get(i));
				if(i != lstpassedTabs.size())
					buffer.append(",");
			}
			tempTdmProfilerDTO.setFinalTabs(buffer.toString());
			}else
				tempTdmProfilerDTO.setFinalTabs(tdmProfilerDTO1.getFinalTabs());
			listFinalColumns.addAll(profilerCreateService.getColsByTabs(
					tempTdmProfilerDTO.getStrUrl(), tempTdmProfilerDTO.getUserName(),
					tempTdmProfilerDTO.getPassword(), lstpassedTabs));
			tempTdmProfilerDTO.setListSelectedTabs(lstpassedTabs);
			tempTdmProfilerDTO.setListColumns(listFinalColumns);
			
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("tdmProfilerDTO", tempTdmProfilerDTO);
		// tempTdmProfilerDTO = tdmProfilerDTO;
		logger.info(strClassName + " ~ createProfilerFinalStep1Dictionary ~ returned from method");
		return "editProfilePage2";
	}
	
	@RequestMapping(value = "/editProfiler",  method = RequestMethod.GET)
	public String editProfiler(@RequestParam(required = true, value = "reqprofileName") String reqVals,
			@ModelAttribute("tdmProfilerDTO")TdmProfilerDTO tdmProfilerDTO1, ModelMap model){
		logger.info(strClassName+" ~ createProfilerDictionary ~ inside method");
		try {
			if(StringUtils.isNotEmpty(reqVals)){
				User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				TdmProfilerDTO profilerDTO = profilerCreateService.fetchProfileDetails(reqVals);
				
				if (lstAvailableConnections == null || lstAvailableConnections.isEmpty())
					lstAvailableConnections = dbConnectionService.getAvailableConnections(user
							.getUsername());
				
				if (lstAvailableConnections != null && !lstAvailableConnections.isEmpty()) {
					for (DbConnectionsDTO dto : lstAvailableConnections) {
						if (profilerDTO.getConnectionName().equalsIgnoreCase(dto.getConnectionName())) {
							StringBuffer url = new StringBuffer();
							StringBuffer username = new StringBuffer();
							StringBuffer password = new StringBuffer();
							url.append(getUrl(dto));
							username.append(dto.getUser());
							password.append(dto.getPass());
							List<String> listFinalTabs = new ArrayList<String>();
							// listFinalTabs.add("All");
							listFinalTabs.addAll(profilerCreateService.getAllTabs(url.toString(),
									username.toString(), password.toString()));
							//lstCons.add(dto.getHostName());
							profilerDTO.setListTables(listFinalTabs);
							profilerDTO.setStrUrl(url.toString());
							profilerDTO.setUserName(username.toString());
							profilerDTO.setPassword(password.toString());break;
						}
					}
				}
				//tempTdmProfilerDTO.setConnectionName(profilerDTO.);
				List<String> lstpassedTabs = new ArrayList<String>();
				List<String> listFinalColumns = new ArrayList<String>();
				
					if(profilerDTO.getFinalTabs().contains(",")){
						String[] splits = profilerDTO.getFinalTabs().split(",");
						lstpassedTabs.addAll(Arrays.asList(splits));
					}else
						lstpassedTabs.add(profilerDTO.getFinalTabs());
					//}
				lstpassedTabs.removeAll(Arrays.asList(null,""));
				// listFinalTabs.add("All");
				listFinalColumns.addAll(profilerCreateService.getColsByTabs(profilerDTO.getStrUrl(),
						profilerDTO.getUserName(), profilerDTO.getPassword(),lstpassedTabs));
				profilerDTO.setEditflag(true);
				//lstCons.add(dto.getHostName());
				tempTdmProfilerDTO = profilerDTO;
				//tempTdmProfilerDTO.setFinalTabs(profilerDTO.getFinalTabs());
				tempTdmProfilerDTO.setListColumns(listFinalColumns);
				tempTdmProfilerDTO.setListSelectedTabs(lstpassedTabs);
				//tempTdmProfilerDTO.setStartTable(tdmProfilerDTO.getStartTable());
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("tdmProfilerDTO", tempTdmProfilerDTO);
		//tempTdmProfilerDTO = tdmProfilerDTO;
		logger.info(strClassName+" ~ createProfilerFinalStep1Dictionary ~ returned from method");
		return "editProfilePage2";
		//}
		//logger.info(strClassName+" ~ createMasterDictionary ~ returned from method");
		//return "tdgCreateDictionaryPage1";
	}
	
	
	@RequestMapping(value = "/editProfilerSubmit",  method = RequestMethod.POST)
	public @ResponseBody String editProfilerFinalStep(
			@ModelAttribute("tdmProfilerDTO")TdmProfilerDTO tdmProfilerDTO, ModelMap model,@RequestParam(required = false, value = "reqVals") String reqVals){
		//String response= AppConstant.FAILED+"# Error occurred while creating profile";
		try{
		if(StringUtils.isNotEmpty(reqVals) && reqVals.contains("CREATEPROFILER")){
			String strResposne = profilerCreateService.updateProfiler(tempTdmProfilerDTO,reqVals.replaceAll("$CREATEPROFILER", ""));
			if(strResposne.contains("SUCCESS"))
				return AppConstant.SUCCESS+"#Profile updated Successfully";
			else 
				return AppConstant.FAILED+"#Error occurred while updating profile";
		}
		} catch (BaseException baseEx) {
		}
			tdmProfilerDTO = tempTdmProfilerDTO;
			model.addAttribute("tdmProfilerDTO", tdmProfilerDTO);
			//tempTdmProfilerDTO = tdmProfilerDTO;
			logger.info(strClassName+" ~ createProfilerFinalStep1Dictionary ~ returned from method");
			return "createProfilePage2";
	}

	@RequestMapping(value = "/fetchRelationTabs",  method = RequestMethod.GET)
	public @ResponseBody String fetchRelationTables(@RequestParam(required = true, value = "reqVals") String reqVals){
		//String response= AppConstant.FAILED+"# Error occurred while creating profile";
		try{
		if(StringUtils.isNotEmpty(reqVals)){
			String strResposne = profilerCreateService.fetchAllRelationalTabs(tempTdmProfilerDTO,reqVals);
			return strResposne;
		}
		} catch (BaseException baseEx) {
		}
		return "";
	}

	
	@RequestMapping(value = "/downloadProfilerQueries",  method = RequestMethod.GET)
	public void downloadSampleQueries(@RequestParam(required = true, value = "reqprofileName") String reqVals,HttpServletRequest request, HttpServletResponse response){
		//String response= AppConstant.FAILED+"# Error occurred while creating profile";
		try{
		if(StringUtils.isNotEmpty(reqVals)){
			List<String> listQueries = profilerCreateService.generateQueries(reqVals);
			response.setContentType("text/csv");
			String disposition = "attachment; fileName=downloadTdgRequest.csv";
			response.setHeader("Content-Disposition", disposition);
			StringBuffer sb = new  StringBuffer();
			if(listQueries != null && !listQueries.isEmpty()){
				for(String strValue : listQueries){
					sb.append(strValue).append(System.getProperty("line.separator"));
				}
			}
			response.getWriter().append(sb.toString());
		}
		} catch (BaseException | IOException baseEx) {
		}
	}

	/*@RequestMapping(value = "/createNextProfilePage2", method = RequestMethod.GET)
	public String createNextStepMasterDictionary(
			@ModelAttribute("tdmProfilerDTO") TdmProfilerDTO tdmProfilerDTO,
			ModelMap model){
		logger.info(strClassName + " ~ tdgaNextCreateNextProfile ~ inside method");
		// lstAvailableConnections = tdgDictionariesService.getAvailableConnections();
		// List<String> lstCons = new ArrayList<String>();
		if(tdmProfilerDTO !=null){
			if(StringUtils.isNotEmpty(tdmProfilerDTO.getSelectedConnectionName())){
				StringBuffer url = new StringBuffer();
				StringBuffer username = new StringBuffer();
				StringBuffer password = new StringBuffer();
				for(DbConnectionsDTO dto : lstAvailableConnections){
					if(dto.getConnectionName().equalsIgnoreCase(tdmProfilerDTO.getSelectedConnectionName())){						
						url.append(getUrl(dto));
						username.append(dto.getUser());
						password.append(dto.getPass());
						break;
					}
				}
				
				List<String> listFinalTabs = new ArrayList<String>();
				listFinalTabs.add("All");
				listFinalTabs.addAll(profilerCreateService.getAllTabs(url.toString(),username.toString(),password.toString()));
			}
			
		}
		model.addAttribute("tdmProfilerDTO", tdmProfilerDTO);
		logger.info(strClassName + " ~ createNextStepMasterDictionary ~ returned from method");
		return "createNextProfilePage2";
	}

	@RequestMapping(value = "/tdgaNextCreateMasterDictionary", method = RequestMethod.POST)
	public String createNextStepMasterDictionary2(@RequestParam(required = false, value = "reqVals") String reqVals,
			@ModelAttribute("tdgMasterDictionaryDTO") TdgMasterDictionaryDTO tdgMasterDictionaryDTO,
			ModelMap model){
		//System.out.println(tdgMasterDictionaryDTOTemp);
		//User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//if(StringUtils.isEmpty(reqVals)){
		try{
		List<String> listTabs = new ArrayList<String>();
		if(tdgMasterDictionaryDTOTemp != null && StringUtils.isNotEmpty(tdgMasterDictionaryDTOTemp.getPassedTabs())){
			if(tdgMasterDictionaryDTOTemp.getPassedTabs().contains(",")){
				String[] splits = tdgMasterDictionaryDTOTemp.getPassedTabs().split(",");
				for(int i=0;i<splits.length;i++){
					if(!"All".equalsIgnoreCase(splits[i]) && !"TDG_PASSED_TABS".equalsIgnoreCase(splits[i]))
					listTabs.add(splits[i]);
				}
			}
		}
		StringBuffer url = new StringBuffer();
		StringBuffer username = new StringBuffer();
		StringBuffer password = new StringBuffer();
		//tdgMasterDictionaryDTO  = tdgMasterDictionaryDTOTemp;
		if(tdgMasterDictionaryDTOTemp.getConSelected().trim().contains("TDGCON")){
			String[] strSplits = tdgMasterDictionaryDTOTemp.getConSelected().split("TDGCON");
			for(int i=0;i<strSplits.length;i++){
				for(DbConnectionsDTO dto : lstAvailableConnections){
					if(dto.getDisplayName().equalsIgnoreCase(strSplits[i])){
						if(url.length() > 0){
							url.append("#");
							username.append("#");
							password.append("#");
						}
						url.append(getUrl(dto));
						username.append(dto.getUser());
						password.append(dto.getPass());
						break;
					}
				}
			}
		}else{
			for(DbConnectionsDTO dto : lstAvailableConnections){
				if(dto.getDisplayName().equalsIgnoreCase(tdgMasterDictionaryDTOTemp.getConSelected())){						
					url.append(getUrl(dto));
					username.append(dto.getUser());
					password.append(dto.getPass());
					break;
				}
			}
		}
		List<String> listColumnsFetched = tdgDictionariesService.getColsByTabs(url.toString(),username.toString(),password.toString(),listTabs);
		tdgMasterDictionaryDTO.setListColumns(listColumnsFetched);
		tdgMasterDictionaryDTOTemp.setListColumns(listColumnsFetched);
		}catch(Exception e){
			tdgMasterDictionaryDTO.setMessageConstant(TdgCentralConstant.FAILED_MESSAGE);
			tdgMasterDictionaryDTO.setMessage(TdgDictionariesController.DB_ERROR);
		}
		model.addAttribute("tdgMasterDictionaryDTO", tdgMasterDictionaryDTO);
		logger.info(strClassName + " ~ createNextStepMasterDictionary2 ~ returned from method");
		return "tdgCreateDictionaryPage2";
		}
		//}/*else{
			
			
			TdgSchemaDTO tdgSchemaDTO = new TdgSchemaDTO();
			Set<TdgGuiDetailsDTO> setTdgGuiDetailsDTOs = new HashSet<TdgGuiDetailsDTO>();
			tdgSchemaDTO.setUrl(url.toString());
			tdgSchemaDTO.setUsername(username.toString());
			tdgSchemaDTO.setPassword(password.toString());
			tdgSchemaDTO.setColumnsdepends(tdgMasterDictionaryDTOTemp.getDependentDbs());
			tdgSchemaDTO.setSchemamastertables(tdgMasterDictionaryDTOTemp.getMasterTabs());
			tdgSchemaDTO.setSeqtableprefix(tdgMasterDictionaryDTOTemp.getSequencePrefixTabs());
			tdgSchemaDTO.setSchemapasstabs(tdgMasterDictionaryDTOTemp.getPassedTabs());
			tdgSchemaDTO.setUserid(user.getUsername());
			tdgSchemaDTO.setDateformate(tdgMasterDictionaryDTOTemp.getDateFormates());
			tdgSchemaDTO.setRequiredcolumns(tdgMasterDictionaryDTOTemp.getRequiredCols());
			tdgSchemaDTO.setBusinessrules(tdgMasterDictionaryDTOTemp.getBusinessRules());
			tdgSchemaDTO.setSchemaname(tdgMasterDictionaryDTOTemp.getDictionaryName());
			if(reqVals.contains("$")){
				String[] strColumns = reqVals.split("$");
				for(int i=0;i<strColumns.length;i++){
					TdgGuiDetailsDTO tdgGuiDetailsDTO = new TdgGuiDetailsDTO();
					if(strColumns[i].contains("#")){
						String[] strValues = strColumns[i].split("#");
						//for(int j=0;j<4;j++){
							tdgGuiDetailsDTO.setColumnname(strValues[0]);
							tdgGuiDetailsDTO.setColumnLabel(strValues[1]);
							tdgGuiDetailsDTO.setColumnType(strValues[2]);
							if(strValues.length == 4)
								tdgGuiDetailsDTO.setColumnValues(strValues[3]);
							
						//}
					}
				}
			}else{
				TdgGuiDetailsDTO tdgGuiDetailsDTO = new TdgGuiDetailsDTO();
				if(reqVals.contains("#")){
					String[] strValues = reqVals.split("#");
					//for(int j=0;j<4;j++){
						tdgGuiDetailsDTO.setColumnname(strValues[0]);
						tdgGuiDetailsDTO.setColumnLabel(strValues[1]);
						tdgGuiDetailsDTO.setColumnType(strValues[2]);
						if(strValues.length == 4)
							tdgGuiDetailsDTO.setColumnValues(strValues[3]);
			}			
			tdgSchemaDTO.setTdgGuiDetailsDTOs(setTdgGuiDetailsDTOs);
			String strMessage = tdgDictionariesService.saveTdgSchemaDetails(tdgSchemaDTO);
			tdgMasterDictionaryDTO.set
			model.addAttribute("tdgMasterDictionaryDTO", tdgMasterDictionaryDTO);
			logger.info(strClassName + " ~ createMasterDictionary ~ returned from method");
			return "tdgCreateDictionaryPage2";
			}
		//}
		
		@RequestMapping(value = "/tdgaFinalCreateMasterDictionary", method = RequestMethod.GET)
		public @ResponseBody String createFinalStepMasterDictionary2(@RequestParam(required = false, value = "reqVals") String reqVals,
				@ModelAttribute("tdgMasterDictionaryDTO") TdgMasterDictionaryDTO tdgMasterDictionaryDTO,
				ModelMap model){
			//System.out.println(tdgMasterDictionaryDTOTemp);
			StringBuffer strSuccess = new StringBuffer();
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if(StringUtils.isNotEmpty(reqVals)){
				StringBuffer url = new StringBuffer();
				StringBuffer username = new StringBuffer();
				StringBuffer password = new StringBuffer();
				//tdgMasterDictionaryDTO  = tdgMasterDictionaryDTOTemp;
				if(tdgMasterDictionaryDTOTemp.getConSelected().trim().contains("TDGCON")){
					String[] strSplits = tdgMasterDictionaryDTOTemp.getConSelected().split("TDGCON");
					for(int i=0;i<strSplits.length;i++){
						for(DbConnectionsDTO dto : lstAvailableConnections){
							if(dto.getDisplayName().equalsIgnoreCase(strSplits[i])){
								if(url.length() > 0){
									url.append("#");
									username.append("#");
									password.append("#");
								}
								url.append(getUrl(dto));
								username.append(dto.getUser());
								password.append(dto.getPass());
								break;
							}
						}
					}
				}else{
					for(DbConnectionsDTO dto : lstAvailableConnections){
						if(dto.getDisplayName().equalsIgnoreCase(tdgMasterDictionaryDTOTemp.getConSelected())){						
							url.append(getUrl(dto));
							username.append(dto.getUser());
							password.append(dto.getPass());
							break;
						}
					}
				}
				TdgSchemaDTO tdgSchemaDTO = new TdgSchemaDTO();
				Set<TdgGuiDetailsDTO> setTdgGuiDetailsDTOs = new HashSet<TdgGuiDetailsDTO>();
				tdgSchemaDTO.setUrl(url.toString());
				tdgSchemaDTO.setUsername(username.toString());
				tdgSchemaDTO.setPassword(password.toString());
				tdgSchemaDTO.setColumnsdepends(tdgMasterDictionaryDTOTemp.getDependentDbs());
				tdgSchemaDTO.setSchemamastertables(tdgMasterDictionaryDTOTemp.getMasterTabs()+";");
				tdgSchemaDTO.setSeqtableprefix(tdgMasterDictionaryDTOTemp.getSequencePrefixTabs()+";");
				tdgSchemaDTO.setSchemapasstabs(tdgMasterDictionaryDTOTemp.getPassedTabs()+";");
				tdgSchemaDTO.setUserid(user.getUsername());
				tdgSchemaDTO.setDateformate(tdgMasterDictionaryDTOTemp.getDateFormates());
				tdgSchemaDTO.setRequiredcolumns(tdgMasterDictionaryDTOTemp.getRequiredCols());
				tdgSchemaDTO.setBusinessrules(tdgMasterDictionaryDTOTemp.getBusinessRules());
				tdgSchemaDTO.setSchemaname(tdgMasterDictionaryDTOTemp.getDictionaryName());
				tdgSchemaDTO.setDataconnections(tdgMasterDictionaryDTOTemp.getConSelected());
				if(reqVals.contains("$")){
					String[] strColumns = reqVals.split("\\$");
					for(int i=0;i<strColumns.length;i++){
						TdgGuiDetailsDTO tdgGuiDetailsDTO = new TdgGuiDetailsDTO();
						if(strColumns[i].contains("#")){
							String[] strValues = strColumns[i].split("#");
							//for(int j=0;j<4;j++){
								tdgGuiDetailsDTO.setColumnname(strValues[0]);
								tdgGuiDetailsDTO.setColumnLabel(strValues[1]);
								tdgGuiDetailsDTO.setColumnType(strValues[2]);
								if(strValues.length == 4)
									tdgGuiDetailsDTO.setColumnValues(strValues[3]);
								
							//}
						}else{
							if(strColumns[i].startsWith("TDG_DATE_FORMAT")){
								tdgSchemaDTO.setDateformate(strColumns[i].substring("TDG_DATE_FORMAT".length()));
							}else if(strColumns[i].startsWith("TDG_SEQUENCE_PREFIX_TABS")){
								tdgSchemaDTO.setSeqtableprefix(strColumns[i].substring("TDG_SEQUENCE_PREFIX_TABS".length()));
							}else if(strColumns[i].startsWith("TDG_BUSINESS_RULES")){
								tdgSchemaDTO.setBusinessrules(strColumns[i].substring("TDG_BUSINESS_RULES".length()));
							}else if(strColumns[i].startsWith("TDG_DEPENDENT_DBS")){
								tdgSchemaDTO.setColumnsdepends(strColumns[i].substring("TDG_DEPENDENT_DBS".length()));
							}
							
						}
						setTdgGuiDetailsDTOs.add(tdgGuiDetailsDTO);
					}
				}else{
					TdgGuiDetailsDTO tdgGuiDetailsDTO = new TdgGuiDetailsDTO();
					if(reqVals.contains("#")){
						String[] strValues = reqVals.split("#");
						//for(int j=0;j<4;j++){
							tdgGuiDetailsDTO.setColumnname(strValues[0]);
							tdgGuiDetailsDTO.setColumnLabel(strValues[1]);
							tdgGuiDetailsDTO.setColumnType(strValues[2]);
							if(strValues.length == 4)
								tdgGuiDetailsDTO.setColumnValues(strValues[3]);
					}
					setTdgGuiDetailsDTOs.add(tdgGuiDetailsDTO);
				}
				tdgSchemaDTO.setTdgGuiDetailsDTOs(setTdgGuiDetailsDTOs);
				String strResponse = tdgDictionariesService.saveTdgSchemaDetails(tdgSchemaDTO);
				
				if (strResponse.contains(TdgCentralConstant.SUCCESS_MESSAGE)) {
					tdgSchemaDTO.setMessageConstant(TdgCentralConstant.SUCCESS_MESSAGE);
					strSuccess.append(tdgMasterDictionaryDTOTemp.getDictionaryName()).append(" master dictionary is created");					
				} else {
					tdgSchemaDTO.setMessageConstant(TdgCentralConstant.FAILED_MESSAGE);
					strSuccess.append("error while creating in master dictionary");
				}
			}
				model.addAttribute("tdgMasterDictionaryDTO", tdgMasterDictionaryDTO);
				logger.info(strClassName + " ~ createFinalStepMasterDictionary2 ~ returned from method");
				return strSuccess.toString();
				//}
			//}
		
	}
			
	public StringBuffer getUrl(DbConnectionsDTO dbConnectionsDTO){
		StringBuffer url = new StringBuffer();
		try {
			if (null != dbConnectionsDTO) {
				// build url start
				if (StringUtils.isNotEmpty(dbConnectionsDTO.getDbType())) {
					if (dbConnectionsDTO.getDbType().equalsIgnoreCase("Oracle")) {
						// driver class
						//Class.forName(AppConstant.ORA_DRIVER);
						// driver url
						url.append(AppConstant.ORA_URL);
						// Host name
						url.append(dbConnectionsDTO.getHostName());
						// port number
						url.append(':');
						url.append(dbConnectionsDTO.getPort());
						// sid/service/db name
						url.append(':');
						url.append(dbConnectionsDTO.getSid());
					} else if (dbConnectionsDTO.getDbType().equalsIgnoreCase("SqlServer")) {
						// driver class
						//Class.forName(AppConstant.SQL_SERVER_DRIVER);
						// driver url
						url.append(AppConstant.SQL_SERVER_URL);
						// Host name
						url.append(dbConnectionsDTO.getHostName());
						// port number
						url.append(':');
						url.append(dbConnectionsDTO.getPort());
						// sid/service/db name
						url.append(";databaseName=");
						url.append(dbConnectionsDTO.getSid());
						url.append(";");
					} else if (dbConnectionsDTO.getDbType().equalsIgnoreCase("MySql")) {
						// driver class
						//Class.forName(AppConstant.MYSQL_DRIVER);
						// driver url
						url.append(AppConstant.MYSQL_URL);
						// Host name
						url.append(dbConnectionsDTO.getHostName());
						// port number
						url.append(':');
						url.append(dbConnectionsDTO.getPort());
						// sid/service/db name
						url.append('/');
						url.append(dbConnectionsDTO.getSid());
					} else if (dbConnectionsDTO.getDbType().equalsIgnoreCase("DB2")) {
						// driver class
						//Class.forName(AppConstant.DB2_DRIVER);
						// driver url
						// jdbc:db2://<hostname>:50000/SAMPLE:currentSchema=USRES;
						url.append(AppConstant.DB2_URL);
						// Host name
						url.append(dbConnectionsDTO.getHostName());
						// port number
						url.append(':');
						url.append(dbConnectionsDTO.getPort());
						// sid/service/db name
						url.append("/SAMPLE:currentSchema=");
						url.append(dbConnectionsDTO.getSid());
					}
				}// build url end
			}
			return url;
		} catch (Exception otherEx) {
			// releaseEntityMgrForRollback(entityManager);
			otherEx.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value = "/addPassedTabs")
	public String addRequiredTabsGet(
			@ModelAttribute("tdgMasterDictionaryDTO") TdgMasterDictionaryDTO tdgMasterDictionaryDTO, ModelMap model,
			HttpServletRequest request, HttpServletResponse response){
		String strMethodName = " [ addRequiredTabsGet() ]";
		logger.info(strClassName + strMethodName
				+ " inside of addRequiredTabsGet get method ");
		boolean bException =false;
		if(StringUtils.isNotEmpty(tdgMasterDictionaryDTO.getSelectedTabs())){
			if("TDG_PASSED_TABS".equalsIgnoreCase(tdgMasterDictionaryDTO.getSelectedTabs())){
				try{
				if(StringUtils.isNotEmpty(tdgMasterDictionaryDTOTemp.getPassedTabs()))
				//|| "TDG_MASTER_TABS".equalsIgnoreCase(tdgMasterDictionaryDTO.getSelectedTabs()) || "TDG_REQUIRED_COLS".equalsIgnoreCase(tdgMasterDictionaryDTO.getSelectedTabs())){
				tdgMasterDictionaryDTOTemp.setSelectedTabs(tdgMasterDictionaryDTOTemp.getPassedTabs());
				else
					tdgMasterDictionaryDTOTemp.setSelectedTabs("TDG_PASSED_TABS");
				}catch(Exception e){
					e.printStackTrace();
					bException =true;
				}
				tdgMasterDictionaryDTO=  tdgMasterDictionaryDTOTemp;
				if(bException){
					tdgMasterDictionaryDTO.setMessageConstant(TdgCentralConstant.FAILED_MESSAGE);
					tdgMasterDictionaryDTO.setMessage("Database connection error....kindly check database details...");
				}
				//tdgMasterDictionaryDTO.setSelectedTabs("");
				model.addAttribute("tdgMasterDictionaryDTO", tdgMasterDictionaryDTO);
				
				return "addTables";				
			}else if("TDG_MASTER_TABS".equalsIgnoreCase(tdgMasterDictionaryDTO.getSelectedTabs())){
				try{
				if(StringUtils.isNotEmpty(tdgMasterDictionaryDTOTemp.getMasterTabs()))
				//|| "TDG_MASTER_TABS".equalsIgnoreCase(tdgMasterDictionaryDTO.getSelectedTabs()) || "TDG_REQUIRED_COLS".equalsIgnoreCase(tdgMasterDictionaryDTO.getSelectedTabs())){
				tdgMasterDictionaryDTOTemp.setSelectedTabs(tdgMasterDictionaryDTOTemp.getMasterTabs());
				else
					tdgMasterDictionaryDTOTemp.setSelectedTabs("TDG_MASTER_TABS");
				}catch(Exception e){
					bException = true;
					e.printStackTrace();
				}
				tdgMasterDictionaryDTO=  tdgMasterDictionaryDTOTemp;
				//tdgMasterDictionaryDTO.setSelectedTabs("");
				if(bException){
					tdgMasterDictionaryDTO.setMessageConstant(TdgCentralConstant.FAILED_MESSAGE);
					tdgMasterDictionaryDTO.setMessage("Database connection error....kindly check database details...");
				}
				model.addAttribute("tdgMasterDictionaryDTO", tdgMasterDictionaryDTO);
				
				return "addTables";				
			}else if("TDG_REQUIRED_COLS".equalsIgnoreCase(tdgMasterDictionaryDTO.getSelectedTabs())){
				try{
				if(StringUtils.isNotEmpty(tdgMasterDictionaryDTOTemp.getRequiredCols()))
				//|| "TDG_MASTER_TABS".equalsIgnoreCase(tdgMasterDictionaryDTO.getSelectedTabs()) || "TDG_REQUIRED_COLS".equalsIgnoreCase(tdgMasterDictionaryDTO.getSelectedTabs())){
				tdgMasterDictionaryDTOTemp.setSelectedTabs(tdgMasterDictionaryDTOTemp.getRequiredCols());
				else
					tdgMasterDictionaryDTOTemp.setSelectedTabs("TDG_REQUIRED_COLS");
				}catch(Exception e){
					e.printStackTrace();
					bException = true;
				}
				tdgMasterDictionaryDTO=  tdgMasterDictionaryDTOTemp;
				//tdgMasterDictionaryDTO.setSelectedTabs("");
				if(bException){
					tdgMasterDictionaryDTO.setMessageConstant(TdgCentralConstant.FAILED_MESSAGE);
					tdgMasterDictionaryDTO.setMessage("Database connection error....kindly check database details...");
				}
				model.addAttribute("tdgMasterDictionaryDTO", tdgMasterDictionaryDTO);
				
				return "addTables";				
			}else{
			//tdgMasterDictionaryDTOTemp.setSelectedTabs(tdgMasterDictionaryDTO.getSelectedTabs());
			if("TDG_PASSED_TABS".equalsIgnoreCase(tdgMasterDictionaryDTOTemp.getSelectedTabs())){
				tdgMasterDictionaryDTOTemp.setPassedTabs(tdgMasterDictionaryDTO.getPassedTabs());
			}else if("TDG_MASTER_TABS".equalsIgnoreCase(tdgMasterDictionaryDTOTemp.getSelectedTabs())){
				tdgMasterDictionaryDTOTemp.setMasterTabs(tdgMasterDictionaryDTO.getMasterTabs());
			}else if("TDG_REQUIRED_COLS".equalsIgnoreCase(tdgMasterDictionaryDTOTemp.getSelectedTabs())){
				tdgMasterDictionaryDTOTemp.setRequiredCols(tdgMasterDictionaryDTO.getMasterTabs());					
			}
				if(tdgMasterDictionaryDTO.getSelectedTabs().startsWith("TDG_PASSED_TABS"))
					tdgMasterDictionaryDTOTemp.setPassedTabs(tdgMasterDictionaryDTO.getSelectedTabs());
				else if(tdgMasterDictionaryDTO.getSelectedTabs().startsWith("TDG_MASTER_TABS")){
					tdgMasterDictionaryDTOTemp.setMasterTabs(tdgMasterDictionaryDTO.getSelectedTabs());
				}else if(tdgMasterDictionaryDTO.getSelectedTabs().startsWith("TDG_REQUIRED_COLS")){
					tdgMasterDictionaryDTOTemp.setRequiredCols(tdgMasterDictionaryDTO.getSelectedTabs());					
				}
			tdgMasterDictionaryDTOTemp.setSelectedTabs("");
			tdgMasterDictionaryDTO=  tdgMasterDictionaryDTOTemp;
			//tdgMasterDictionaryDTO.setSelectedTabs("");
			model.addAttribute("tdgMasterDictionaryDTO", tdgMasterDictionaryDTO);
			
			return "redirect:tdgaNextCreateMasterDictionary";
			}
		}else{
			tdgMasterDictionaryDTO=  tdgMasterDictionaryDTOTemp;
			tdgMasterDictionaryDTO.setSelectedTabs("");
			model.addAttribute("tdgMasterDictionaryDTO", tdgMasterDictionaryDTO);			
			return "addTables";
		}		
	}
	
	
	@RequestMapping(value = "/addMasterTabs", method = RequestMethod.GET)
	public String addMasterTabs(
			@ModelAttribute("tdgMasterDictionaryDTO") TdgMasterDictionaryDTO tdgMasterDictionaryDTO, ModelMap model,
			HttpServletRequest request, HttpServletResponse response){
		String strMethodName = " [ addMasterTabs() ]";
		logger.info(strClassName + strMethodName
				+ " inside of addMasterTabs get method ");
		tdgMasterDictionaryDTO=  tdgMasterDictionaryDTOTemp;
		model.addAttribute("tdgMasterDictionaryDTO", tdgMasterDictionaryDTO);
		return "addTables";
	}
*/
}
