package com.tdm.constant;

public enum RequestStatusENUM {
	SAVED(1,"Saved"),
	NEW(2,"New"),
	OPEN(3,"Open"),
	IN_PROGRESS(4,"In-Progress"),
	COMPLETED(5,"Completed"),
	SENT_FOR_CLARIFICATION(6,"Sent for Clarification"),
	REJECTED(7,"Rejected"),
	RE_OPENED(8,"Re-Opened"),
	CANCELLED(9,"Cancelled"),
	CLOSED(10,"Closed");
	
	private Integer requestStatusCode;
	private String requestStatusDesc;
	
	private RequestStatusENUM(Integer code, String value) {
		this.requestStatusCode=code; 
		this.requestStatusDesc=value; 
	}

	public Integer getRequestStatusCode() {
		return requestStatusCode;
	}
	
	public String getRequestStatusDesc() {
		return requestStatusDesc;
	}
}
