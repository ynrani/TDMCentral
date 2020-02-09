package com.tdm.model.DTO;

import java.util.Date;

public class TDMReservedTestDataDTO {

	private Date exipryDt;
	private String userId;
	private String userName;
	private Integer dayToExpire;
	private Integer noOfRecordsResvByUser;
	private String testCaseId;
	private String testCaseName;
	private Date createdDate;

	private  Integer sno;
	

	public Integer getSno() {
		return sno;
	}

	public void setSno(Integer sno) {
		this.sno = sno;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getExipryDt() {
		return exipryDt;
	}

	public void setExipryDt(Date exipryDt) {
		this.exipryDt = exipryDt;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getDayToExpire() {
		return dayToExpire;
	}

	public void setDayToExpire(Integer dayToExpire) {
		this.dayToExpire = dayToExpire;
	}


	@Override
	public String toString() {
		return "TDMReservedTestDataDTO [exipryDt=" + exipryDt + ", userId="
				+ userId + ", dayToExpire=" + dayToExpire
				+ ", noOfRecordsResvByUser=" + noOfRecordsResvByUser
				+ ", testCaseId=" + testCaseId + ", testCaseName="
				+ testCaseName + ", createdDate=" + createdDate + "]";
	}

	public Integer getNoOfRecordsResvByUser() {
		return noOfRecordsResvByUser;
	}

	public void setNoOfRecordsResvByUser(Integer noOfRecordsResvByUser) {
		this.noOfRecordsResvByUser = noOfRecordsResvByUser;
	}

	public String getTestCaseId() {
		return testCaseId;
	}

	public void setTestCaseId(String testCaseId) {
		this.testCaseId = testCaseId;
	}

	public String getTestCaseName() {
		return testCaseName;
	}

	public void setTestCaseName(String testCaseName) {
		this.testCaseName = testCaseName;
	}
	
		public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
