/*
 * Object Name : TdmPolicyCenterResultDTO.java
 * Modification Block
 * ------------------------------------------------------------------
 * S.No.	Name 			Date			Bug_Fix_No			Desc
 * ------------------------------------------------------------------
 * 	1.	  vkrish14		6:03:07 PM				Created
 * ------------------------------------------------------------------
 * Copyrights: 2015 Capgemini.com
 */
package com.tdm.model.DTO;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author vkrish14
 *
 */
public class TdmPolicyCenterSearchResultDTO{
	
	private String secondaryNamedInsured;
	private int numberOfViolations;
	private int numberOfAccidents;
	private String carrier;
	private String color;
	private BigDecimal year;
	private String model;
	private String issuedate;
	private long totalVehicles;
	private BigDecimal amountofcoverage;
	private BigDecimal coveragelimit;
	private String coverageType;
	private BigDecimal paymentplan;
	private BigDecimal paymentmethod;
	private String nextrenewalcheckdate;
	private String reservedYN;
	
	private String policynumber;
	private String accoutnumber;
	private String emailAddress;
	private String emailAddress2;
	private String workPhoneNo;
	private String firstName;
	private String lastName;
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	private String state;
	private String citydenorm;
	private String country;
	private String code;
	private String productType;
	private String originalEffectiveDate;
	private String expirationDate;
	private String insurerType;
	
	private String revExpairDate;
	private String testCaseId;
	private String testCaseName;
	private String userId;
	/*private String secondarynamedinsured;
	private String numberofViolations;
	private String numberofAccidents;
	private String carrier;*/
	public String getSecondaryNamedInsured(){
		return secondaryNamedInsured;
	}
	public String getPolicynumber(){
		return policynumber;
	}
	public void setPolicynumber(String policynumber){
		this.policynumber = policynumber;
	}
	public String getAccoutnumber(){
		return accoutnumber;
	}
	public void setAccoutnumber(String accoutnumber){
		this.accoutnumber = accoutnumber;
	}
	public String getEmailAddress(){
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress){
		this.emailAddress = emailAddress;
	}
	public String getWorkPhoneNo(){
		return workPhoneNo;
	}
	public void setWorkPhoneNo(String workPhoneNo){
		this.workPhoneNo = workPhoneNo;
	}
	public String getFirstName(){
		return firstName;
	}
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	public String getLastName(){
		return lastName;
	}
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	public String getAddressLine1(){
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1){
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2(){
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2){
		this.addressLine2 = addressLine2;
	}
	public String getState(){
		return state;
	}
	public void setState(String state){
		this.state = state;
	}
	public String getCitydenorm(){
		return citydenorm;
	}
	public void setCitydenorm(String citydenorm){
		this.citydenorm = citydenorm;
	}
	public String getCountry(){
		return country;
	}
	public void setCountry(String country){
		this.country = country;
	}
	public String getCode(){
		return code;
	}
	public void setCode(String code){
		this.code = code;
	}
	public String getProductType(){
		return productType;
	}
	public void setProductType(String productType){
		this.productType = productType;
	}
	public String getOriginalEffectiveDate(){
		return originalEffectiveDate;
	}
	public void setOriginalEffectiveDate(String originalEffectiveDate){
		this.originalEffectiveDate = originalEffectiveDate;
	}
	public String getExpirationDate(){
		return expirationDate;
	}
	public void setExpirationDate(String expirationDate){
		this.expirationDate = expirationDate;
	}
	public void setSecondaryNamedInsured(String secondaryNamedInsured){
		this.secondaryNamedInsured = secondaryNamedInsured;
	}
	public int getNumberOfViolations(){
		return numberOfViolations;
	}
	public void setNumberOfViolations(int numberOfViolations){
		this.numberOfViolations = numberOfViolations;
	}
	public int getNumberOfAccidents(){
		return numberOfAccidents;
	}
	public void setNumberOfAccidents(int numberOfAccidents){
		this.numberOfAccidents = numberOfAccidents;
	}
	public String getCarrier(){
		return carrier;
	}
	public void setCarrier(String carrier){
		this.carrier = carrier;
	}
	public String getColor(){
		return color;
	}
	public void setColor(String color){
		this.color = color;
	}
	public BigDecimal getYear(){
		return year;
	}
	public void setYear(BigDecimal year){
		this.year = year;
	}
	public String getModel(){
		return model;
	}
	public void setModel(String model){
		this.model = model;
	}
	public String getIssuedate(){
		return issuedate;
	}
	public void setIssuedate(String issuedate){
		this.issuedate = issuedate;
	}
	public long getTotalVehicles(){
		return totalVehicles;
	}
	public void setTotalVehicles(long totalVehicles){
		this.totalVehicles = totalVehicles;
	}
	public BigDecimal getAmountofcoverage(){
		return amountofcoverage;
	}
	public void setAmountofcoverage(BigDecimal amountofcoverage){
		this.amountofcoverage = amountofcoverage;
	}
	public BigDecimal getCoveragelimit(){
		return coveragelimit;
	}
	public void setCoveragelimit(BigDecimal coveragelimit){
		this.coveragelimit = coveragelimit;
	}
	public String getCoverageType(){
		return coverageType;
	}
	public void setCoverageType(String coverageType){
		this.coverageType = coverageType;
	}
	public BigDecimal getPaymentplan(){
		return paymentplan;
	}
	public void setPaymentplan(BigDecimal paymentplan){
		this.paymentplan = paymentplan;
	}
	public BigDecimal getPaymentmethod(){
		return paymentmethod;
	}
	public void setPaymentmethod(BigDecimal paymentmethod){
		this.paymentmethod = paymentmethod;
	}
	public String getNextrenewalcheckdate(){
		return nextrenewalcheckdate;
	}
	public void setNextrenewalcheckdate(String nextrenewalcheckdate){
		this.nextrenewalcheckdate = nextrenewalcheckdate;
	}
	public String getReservedYN(){
		return reservedYN;
	}
	public void setReservedYN(String reservedYN){
		this.reservedYN = reservedYN;
	}
	public String getTestCaseName(){
		return testCaseName;
	}
	public void setTestCaseName(String testCaseName){
		this.testCaseName = testCaseName;
	}
	public String getTestCaseId(){
		return testCaseId;
	}
	public void setTestCaseId(String testCaseId){
		this.testCaseId = testCaseId;
	}
	public String getUserId(){
		return userId;
	}
	public void setUserId(String userId){
		this.userId = userId;
	}
	public String getRevExpairDate(){
		return revExpairDate;
	}
	public void setRevExpairDate(String revExpairDate){
		this.revExpairDate = revExpairDate;
	}
	public String getEmailAddress2(){
		return emailAddress2;
	}
	public void setEmailAddress2(String emailAddress2){
		this.emailAddress2 = emailAddress2;
	}
	public String getAddressLine3(){
		return addressLine3;
	}
	public void setAddressLine3(String addressLine3){
		this.addressLine3 = addressLine3;
	}
	public String getInsurerType(){
		return insurerType;
	}
	public void setInsurerType(String insurerType){
		this.insurerType = insurerType;
	}
	
	
	
}
