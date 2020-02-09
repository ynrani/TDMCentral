/*
 * Object Name : PolicyCenterDTO.java
 * Modification Block
 * ------------------------------------------------------------------
 * S.No.	Name 			Date			Bug_Fix_No			Desc
 * ------------------------------------------------------------------
 * 	1.	  vkrish14		5:59:17 PM				Created
 * ------------------------------------------------------------------
 * Copyrights: 2015 Capgemini.com
 */
package com.tdm.model.DTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author vkrish14
 *
 */
public class TdmPolicyCenterSearchDTO{
	
	private String accountNumber;
	private String policyNumber;
	private String productCode;
	private Date originalEffectvieDate;
	private Date expireDate;
	private String searchCriti;
	private String testCaseId;
	private String testCaseName;
	private String insurerType;

	private String searchRecordsNo;
	private boolean showHideFlag;
	private boolean showHideFlagDoc;
	private boolean msgFlag;
	private int count;
	private String country;
	private String state;
	private String gender;
	private String status;
	private List<AutoEmailDTO> autoEmailDTOs;

	private List<TdmPolicyCenterSearchResultDTO> listTdmPolicyCenterSearchResultDTO = new ArrayList<TdmPolicyCenterSearchResultDTO>();
	public String getAccountNumber(){
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber){
		this.accountNumber = accountNumber;
	}
	public String getPolicyNumber(){
		return policyNumber;
	}
	public void setPolicyNumber(String policyNumber){
		this.policyNumber = policyNumber;
	}
	public String getProductCode(){
		return productCode;
	}
	public void setProductCode(String productCode){
		this.productCode = productCode;
	}
	public Date getOriginalEffectvieDate(){
		return originalEffectvieDate;
	}
	public void setOriginalEffectvieDate(Date originalEffectvieDate){
		this.originalEffectvieDate = originalEffectvieDate;
	}
	public Date getExpireDate(){
		return expireDate;
	}
	public void setExpireDate(Date expireDate){
		this.expireDate = expireDate;
	}
	public List<TdmPolicyCenterSearchResultDTO> getListTdmPolicyCenterSearchResultDTO(){
		return listTdmPolicyCenterSearchResultDTO;
	}
	public void setListTdmPolicyCenterSearchResultDTO(List<TdmPolicyCenterSearchResultDTO> listTdmPolicyCenterSearchResultDTO){
		this.listTdmPolicyCenterSearchResultDTO = listTdmPolicyCenterSearchResultDTO;
	}
	public String getSearchCriti(){
		return searchCriti;
	}
	public void setSearchCriti(String searchCriti){
		this.searchCriti = searchCriti;
	}
	public String getTestCaseId(){
		return testCaseId;
	}
	public void setTestCaseId(String testCaseId){
		this.testCaseId = testCaseId;
	}
	public String getTestCaseName(){
		return testCaseName;
	}
	public void setTestCaseName(String testCaseName){
		this.testCaseName = testCaseName;
	}
	public String getSearchRecordsNo(){
		return searchRecordsNo;
	}
	public void setSearchRecordsNo(String searchRecordsNo){
		this.searchRecordsNo = searchRecordsNo;
	}
	public boolean isShowHideFlag(){
		return showHideFlag;
	}
	public void setShowHideFlag(boolean showHideFlag){
		this.showHideFlag = showHideFlag;
	}
	public boolean isShowHideFlagDoc(){
		return showHideFlagDoc;
	}
	public void setShowHideFlagDoc(boolean showHideFlagDoc){
		this.showHideFlagDoc = showHideFlagDoc;
	}
	public boolean isMsgFlag(){
		return msgFlag;
	}
	public void setMsgFlag(boolean msgFlag){
		this.msgFlag = msgFlag;
	}
	public int getCount(){
		return count;
	}
	public void setCount(int count){
		this.count = count;
	}
	public String getCountry(){
		return country;
	}
	public void setCountry(String country){
		this.country = country;
	}
	public String getState(){
		return state;
	}
	public void setState(String state){
		this.state = state;
	}
	public String getGender(){
		return gender;
	}
	public void setGender(String gender){
		this.gender = gender;
	}
	public String getStatus(){
		return status;
	}
	public void setStatus(String status){
		this.status = status;
	}
	public List<AutoEmailDTO> getAutoEmailDTOs(){
		return autoEmailDTOs;
	}
	public void setAutoEmailDTOs(List<AutoEmailDTO> autoEmailDTOs){
		this.autoEmailDTOs = autoEmailDTOs;
	}
	public String getInsurerType(){
		return insurerType;
	}
	public void setInsurerType(String insurerType){
		this.insurerType = insurerType;
	}
	
	
}
