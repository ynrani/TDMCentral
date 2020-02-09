package com.tesda.dao.impl;

import javax.persistence.EntityManager;

import com.tesda.dao.TDMReportingDao;
import com.tesda.model.DO.TDMSessionReportDO;

public class TDMReportingDAOImpl implements TDMReportingDao
{

	@Override
	public void updateSessionInfo(EntityManager userManager, TDMSessionReportDO reportDO)
	{
		try
		{
			userManager.getTransaction().begin();
			userManager.merge(reportDO);
			userManager.getTransaction().commit();

		}
		catch (Exception ex)
		{
			ex.printStackTrace();

		}

	}

}
