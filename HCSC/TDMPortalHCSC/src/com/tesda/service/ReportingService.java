package com.tesda.service;

import javax.persistence.EntityManager;

import com.tesda.dao.TDMReportingDao;
import com.tesda.model.DO.TDMSessionReportDO;

public interface ReportingService
{

	public void updateSessionDetails(EntityManager manager, TDMReportingDao tdmReportingDao,
			TDMSessionReportDO reportDO);

}
