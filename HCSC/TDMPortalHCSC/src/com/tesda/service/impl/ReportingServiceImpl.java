package com.tesda.service.impl;

import javax.persistence.EntityManager;

import com.tesda.dao.TDMReportingDao;
import com.tesda.model.DO.TDMSessionReportDO;
import com.tesda.service.ReportingService;

public class ReportingServiceImpl extends TdmBaseServiceImpl implements ReportingService
{

	@Override
	public void updateSessionDetails(EntityManager manager, TDMReportingDao tdmReportingDao,
			TDMSessionReportDO reportDO)
	{
		try
		{
			tdmReportingDao.updateSessionInfo(manager, reportDO);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			manager.close();
		}
	}

}
