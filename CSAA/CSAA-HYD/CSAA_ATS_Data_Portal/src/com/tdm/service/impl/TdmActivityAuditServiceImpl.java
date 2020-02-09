package com.tdm.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.tdm.dao.TdmActivityAuditDAO;
import com.tdm.model.DTO.ActivityAuditLog;
import com.tdm.model.DTO.ActivityScreenName;
import com.tdm.service.TdmActivityAuditService;

@Component
@Service("activityAuditService")
public class TdmActivityAuditServiceImpl extends TdmBaseServiceImpl implements TdmActivityAuditService {

	
	final static Logger logger = Logger
			.getLogger(TdmActivityAuditServiceImpl.class);
	
	@Autowired
	TdmActivityAuditDAO activityAuditDAO;

	public void auditActivityLog(ActivityScreenName screenName, ActivityAuditLog activityName, String userID,
			String additionalDetails) {
		activityAuditDAO.auditActivityLog(screenName, activityName, userID, additionalDetails);
	}

}
