/*
 * Object Name : TdmSpringScheduleController.java
 * Modification Block
 * ------------------------------------------------------------------
 * S.No.	Name 			Date			Bug_Fix_No			Desc
 * ------------------------------------------------------------------
 * 	1.	  vkrish14		3:20:25 PM				Created
 * ------------------------------------------------------------------
 * Copyrights: 2015 Capgemini.com
 */
package com.tdm.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Controller;

import com.tdm.exception.ServiceException;
import com.tdm.model.DTO.PctlCountryDTO;
import com.tdm.model.DTO.PctlStateDTO;
import com.tdm.model.DTO.TdmPolicyCenterSearchDTO;
import com.tdm.model.DTO.TdmPolicyCenterSearchResultDTO;
import com.tdm.service.impl.TdmPolicyCenterScheduler;
import com.tdm.view.TdmPolicyCenterSchedulerExcelUtil;

/**
 * @author vkrish14
 *
 */
@Controller
public class TdmSpringScheduleController extends QuartzJobBean {
	//private static final String pathValue ="D:\\Users\\vkrish14\\Auto\\";//D:\\TDM@2015\\LeaveMgmt@TDM\\GuideWireAutoSchedule\\
	private static final String pathValue ="D:\\TDM@2015\\LeaveMgmt@TDM\\GuideWireAutoSchedule\\";
	private TdmSpringScheduleController runMeTask;
	public void setRunMeTask(TdmSpringScheduleController runMeTask) {
		this.runMeTask = runMeTask;
	}

	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {

		runMeTask.printMe();

	}
	
	private TdmPolicyCenterScheduler tdmPolicyCenterSearchServiceImpl;

	public synchronized void printMe(){
		System.out.println("This is from controller");
		try {
			doFetchRecords();
		} catch (ServiceException | IOException e) {
			e.printStackTrace();
		}
		
	}
	
	

	private void doFetchRecords() throws ServiceException, IOException{
		
		TdmPolicyCenterSearchDTO inputDTO = new TdmPolicyCenterSearchDTO();
		//input.setProductCode("BusinessOwners");
		
		File file = new File(pathValue);
		File[] files = file.listFiles();
		int iRecordstoFetch = 10;
		if(files != null)
        for(File f: files){
            if(!f.getName().endsWith("Processed.properties")  && !f.getName().endsWith(".xlsx") && !f.getName().endsWith(".xls")){
            	String strFileName = "";
            	File newFile = new File(pathValue+f.getName().substring(0,f.getName().indexOf("."))+"Processed.properties");
            	f.renameTo(newFile);
            	FileInputStream input = new FileInputStream(newFile.getAbsoluteFile());
            	Properties props = new Properties();
				props.load(input);
				if(StringUtils.isNotEmpty(props.getProperty("POLICY_TYPE")))
				inputDTO.setProductCode(props.getProperty("POLICY_TYPE"));
				if(StringUtils.isNotEmpty(props.getProperty("ACCOUNT_STATUS")))
					inputDTO.setState(props.getProperty("POLICY_TYPE"));
				if(StringUtils.isNotEmpty(props.getProperty("COUNTRY")))
					inputDTO.setCountry(props.getProperty("COUNTRY"));
				if(StringUtils.isNotEmpty(props.getProperty("STATE")))
					inputDTO.setState(props.getProperty("STATE"));
				if(StringUtils.isNotEmpty(props.getProperty("INSURER_TYPE")))
					inputDTO.setInsurerType(props.getProperty("INSURER_TYPE"));
				if(StringUtils.isNotEmpty(props.getProperty("FETCH_RECORDS")))
					iRecordstoFetch = Integer.parseInt(props.getProperty("FETCH_RECORDS"));
				
				inputDTO.setSearchCriti(getSearchCriteria(inputDTO));
				String testCaseId = "Test"+(int)(Math.random() * 1000000);
				strFileName = f.getName().substring(0,f.getName().indexOf("."))+testCaseId;
				inputDTO.setTestCaseId(testCaseId);
				inputDTO.setTestCaseName(strFileName);
				if(StringUtils.isNotEmpty(inputDTO.getProductCode())){
				TdmPolicyCenterSearchDTO tdmPolicyCenterSearchDTOtemp = getTdmPolicyCenterSearchServiceImpl().searchPolicyCenter(inputDTO,
						0, iRecordstoFetch, true, "demo");
				List<TdmPolicyCenterSearchResultDTO> resultDTO = new ArrayList<TdmPolicyCenterSearchResultDTO>();
				List<String> listPolicyNumbers = new ArrayList<String>();
		if(tdmPolicyCenterSearchDTOtemp != null && tdmPolicyCenterSearchDTOtemp.getListTdmPolicyCenterSearchResultDTO() != null && !tdmPolicyCenterSearchDTOtemp.getListTdmPolicyCenterSearchResultDTO().isEmpty()){
			for(TdmPolicyCenterSearchResultDTO tdmPolicyCenterSearchResultDTO: tdmPolicyCenterSearchDTOtemp.getListTdmPolicyCenterSearchResultDTO()){
				//System.out.println(tdmPolicyCenterSearchResultDTO.getPolicynumber());
				TdmPolicyCenterSearchResultDTO tdmPolicyCenterSearchResultDTOTemp= new TdmPolicyCenterSearchResultDTO();
				tdmPolicyCenterSearchResultDTOTemp.setReservedYN("Y");
				tdmPolicyCenterSearchResultDTOTemp.setPolicynumber(tdmPolicyCenterSearchResultDTO.getPolicynumber());
				resultDTO.add(tdmPolicyCenterSearchResultDTOTemp);
				
				listPolicyNumbers.add(tdmPolicyCenterSearchResultDTO.getPolicynumber());
			}
			inputDTO.setListTdmPolicyCenterSearchResultDTO(resultDTO);
			
			int iReserveCount = getTdmPolicyCenterSearchServiceImpl().saveReservedData(inputDTO, "demo", null);
			if(iReserveCount == inputDTO.getListTdmPolicyCenterSearchResultDTO().size()){
				System.out.println("reserveredData successfully");
				TdmPolicyCenterSearchDTO tdmPolicyCenterSearchDTO = getTdmPolicyCenterSearchServiceImpl().getReservedRecordForUser("demo", 0, iRecordstoFetch, true,  listPolicyNumbers);
				TdmPolicyCenterSchedulerExcelUtil excelUtil = new  TdmPolicyCenterSchedulerExcelUtil();
				try {
					excelUtil.buildExcelDocument(tdmPolicyCenterSearchDTO.getListTdmPolicyCenterSearchResultDTO(),pathValue+strFileName+".xlsx",testCaseId,strFileName);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else
				System.out.println("error occured while reserving the data");
			
		}
			
			//getTdmPolicyCenterSearchServiceImpl().saveReservedData(tdmPolicyCenterSearchDTO, userName, enviro)
		}
            }else{
            		System.out.println("No valid input exist........");
            }
        }
        
		
		/*TdmPolicyCenterSearchDTO tdmPolicyCenterSearchDTOtemp = getTdmPolicyCenterSearchServiceImpl().searchPolicyCenter(inputDTO,
						0, 10, true, "demo");
		if(tdmPolicyCenterSearchDTOtemp != null && tdmPolicyCenterSearchDTOtemp.getListTdmPolicyCenterSearchResultDTO() != null && !tdmPolicyCenterSearchDTOtemp.getListTdmPolicyCenterSearchResultDTO().isEmpty()){
			for(TdmPolicyCenterSearchResultDTO tdmPolicyCenterSearchResultDTO: tdmPolicyCenterSearchDTOtemp.getListTdmPolicyCenterSearchResultDTO()){
				System.out.println(tdmPolicyCenterSearchResultDTO.getPolicynumber());
			}
			//getTdmPolicyCenterSearchServiceImpl().saveReservedData(tdmPolicyCenterSearchDTO, userName, enviro)
		}*/
		
	}

	public TdmPolicyCenterScheduler getTdmPolicyCenterSearchServiceImpl(){
		return tdmPolicyCenterSearchServiceImpl;
	}

	public void setTdmPolicyCenterSearchServiceImpl(TdmPolicyCenterScheduler tdmPolicyCenterSearchServiceImpl){
		this.tdmPolicyCenterSearchServiceImpl = tdmPolicyCenterSearchServiceImpl;
	}
	
	
	public String getSearchCriteria(TdmPolicyCenterSearchDTO tdmPolicyPropertySearchDTO) {
		
		String searchCriteria = "";

		if (null != tdmPolicyPropertySearchDTO) {
			searchCriteria += "Search Result for: ";

			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO.getProductCode())) {

				searchCriteria += "Policy Type : "
						+ tdmPolicyPropertySearchDTO.getProductCode() + "  ";
			}

			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO.getAccountNumber())) {
				searchCriteria += " + "
						+ " Account Number : "
						+ tdmPolicyPropertySearchDTO.getAccountNumber() + "  ";
			}
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO.getPolicyNumber())) {
				searchCriteria += " + Policy Number : " + tdmPolicyPropertySearchDTO.getPolicyNumber() + "  ";
			}
			
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO.getStatus())) {
				searchCriteria += " + "
						+  "Account Status : "
						+ tdmPolicyPropertySearchDTO.getStatus() + "  ";
			}
			
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO.getGender())) {
				searchCriteria += " + "
						+  "Gender : "
						+ tdmPolicyPropertySearchDTO.getGender().equalsIgnoreCase("f") != null ? "FeMale" :(tdmPolicyPropertySearchDTO.getGender().equalsIgnoreCase("m") ? "Male" :"Both") + "  ";
			}
			
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO.getCountry())) {
				try {
					List<PctlCountryDTO> pctlcountryDto = tdmPolicyCenterSearchServiceImpl.getCountrycodeValues();
					for(PctlCountryDTO dto : pctlcountryDto){
						if(dto.getId() == Long.parseLong(tdmPolicyPropertySearchDTO.getCountry())){
					searchCriteria += " + "
							+  "Country : "
							+ dto.getName() + "  ";
					break;
						}
					}
				} catch (ServiceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO.getState())) {

				try {
					List<PctlStateDTO> pctlstateDto = tdmPolicyCenterSearchServiceImpl.getStatecodeValues();
					for(PctlStateDTO dto : pctlstateDto){
						if(dto.getId() == Long.parseLong(tdmPolicyPropertySearchDTO.getState())){
					searchCriteria += " + "
							+  "State : "
							+ dto.getName() + "  ";
					break;
						}
					}
				} catch (ServiceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			

		}

		return searchCriteria;
	}

	 

}
