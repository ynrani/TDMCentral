/*---------------------------------------------------------------------------------------
 * Object Name: TdmBaseServiceImpl.Java
 * --------------------------------------------------------------------------------------
 *
 * Copyright: 2015 Capgemini Financial Services
 *---------------------------------------------------------------------------------------*/

package com.tesda.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.springframework.stereotype.Component;

import com.tesda.constants.TDMConstants;

/**
 * Service class which provides the following services.
 * Creates the entity manager factory object for the persistent units.
 * Currently TDM portal having 3 persistent units which are 
 * 1.userPersistenceUnit
 * 2.subscPersistenceUnit
 * 3.dataMaskingPersistenceUnit
 * For more details please refer the file persistent.xml which is under WEB-INF folder.
 *
 */

@Component
public class TdmBaseServiceImpl
{

	@PersistenceUnit(unitName = TDMConstants.USER_UNIT)
	private EntityManagerFactory factoryUser;

	@PersistenceUnit(unitName = TDMConstants.DTMASKING_UNIT)
	private EntityManagerFactory factoryDataMasking;

	@PersistenceUnit(unitName = TDMConstants.SUBSCR_UNIT)
	private EntityManagerFactory factorySubscriber;

	public EntityManager openUserEntityManager()
	{
		EntityManager managerUser = factoryUser.createEntityManager();

		return managerUser;
	}

	public void closeUserEntityManager(EntityManager managerUser)
	{
		managerUser.close();
	}

	public void closeClaimEntityManager(EntityManager managerClaim)
	{
		managerClaim.close();
	}

	public EntityManager openDataMaskingEntityManager()
	{
		EntityManager managerDataMasking = factoryDataMasking.createEntityManager();

		return managerDataMasking;
	}

	public void closeDataMaskingEntityManager(EntityManager managerDataMasking)
	{
		managerDataMasking.close();
	}

	public EntityManager openSubscriberEntityManager()
	{
		EntityManager managerSubscr = factorySubscriber.createEntityManager();

		return managerSubscr;
	}

	public void closeSubscriberEntityManager(EntityManager managerSubscr)
	{
		managerSubscr.close();
	}
}
