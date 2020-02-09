package com.tdm.model.DTO;

public enum ActivityAuditLog {
	AUTO_POLICY_TAB_CLICKED(1),
	PROPERTY_POLICY_TAB_CLICKED(2),
	AUTO_POLICY_SEARCH(3),
	PROPERTY_POLICY_SEARCH(4),
	AUTO_POLICY_RESERVE(5),
	PROPERTY_POLICY_RESERVE(6),
	AUTO_POLICY_EXPORT(7),
	PROPERTY_POLICY_EXPORT(8),
	
	DG_MENU_CLICKED(9),
	ACTIVITY_LOG_TAB_CLICKED(10),
	REQUEST_ID_CLICKED(11),
	SUBMIT_CLICKED(12),
	SAVE_CLICKED(13),
	BACK_CLICKED(14),
	
	UNRESERVE_CLICKED(15),
	EXPORT_CLICKED(16),

	LOGIN_SUCCUSS(17),
	LOGIN_FAILED(18),
	LOGOUT(19),
	
	SERVICE_DESK_TAB(20),
	MY_REQUEST_TAB(21),
	
	NEW_REGISTRATION_CLICKED(22),
	SUBMITTED_REGISTRARION_REQUEST(24),
	CONTACT_US_CLICKED(25),
	GOVERNANCE_CLICKED(26);

	private int activityId;

	private ActivityAuditLog(int activityId){  
		this.activityId=activityId;  
		
	}  
	
	public int getActivityId() {
		return activityId;
	}
}