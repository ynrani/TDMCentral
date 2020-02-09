package com.tdm.service;

import com.tdm.model.DTO.ActivityAuditLog;
import com.tdm.model.DTO.ActivityScreenName;

public interface TdmActivityAuditService {
	
	public void auditActivityLog(ActivityScreenName screenName, ActivityAuditLog activityName, String userID, String additionalDetails);

}
