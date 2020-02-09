package com.tesda.dao;

import javax.persistence.EntityManager;

import com.tesda.model.DO.TDMSessionReportDO;

public interface TDMReportingDao
{

	public void updateSessionInfo(EntityManager userManager, TDMSessionReportDO reportDO);
}
