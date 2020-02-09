package com.tdm.dao;

import com.tdm.model.DTO.ActivityAuditLog;
import com.tdm.model.DTO.ActivityScreenName;

public interface TdmActivityAuditDAO {
	public void auditActivityLog(ActivityScreenName screenName, ActivityAuditLog activityName, String userID,
			String additionalDetails) ;
}
