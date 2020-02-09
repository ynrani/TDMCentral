package com.tdm.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tdm.dao.TdmActivityAuditDAO;
import com.tdm.model.DTO.ActivityAuditLog;
import com.tdm.model.DTO.ActivityScreenName;
@Repository
public class TdmActivityAuditDAOImpl implements TdmActivityAuditDAO {
	@Autowired
	private DataSource tdmUserDS;

	public void auditActivityLog(ActivityScreenName screenName, ActivityAuditLog activityName, String userID,
			String additionalDetails) {
		java.sql.Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
		try {
			Connection connection = tdmUserDS.getConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement("INSERT INTO DM_ACTIVITY_AUDIT(ACTIVITY_ID,ACTIVITY_NAME,USER_ID,ACTIVITY_DATE,ADDITIONAL_INFO,SCREEN_NAME) VALUES (?,?,?,?,?,?)");
			preparedStatement.setInt(1, activityName.getActivityId());
			preparedStatement.setString(2, activityName.name());
			preparedStatement.setString(3, userID);
			preparedStatement.setTimestamp(4,timeStamp);
			preparedStatement.setString(5, additionalDetails);
			preparedStatement.setString(6, screenName.toString());
			preparedStatement.executeUpdate();
		} catch (Exception e) {

		}
	}
}
