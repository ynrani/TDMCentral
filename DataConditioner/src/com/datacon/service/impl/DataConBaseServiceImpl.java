package com.datacon.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.springframework.stereotype.Component;

import com.datacon.constant.AppConstant;

@Component
public class DataConBaseServiceImpl
{

	@PersistenceUnit(unitName = AppConstant.PERSISTENCE_UNIT_USER)
	private EntityManagerFactory factoryUser;

	public EntityManager openUserEntityManager() {
		EntityManager managerUser = factoryUser.createEntityManager();

		return managerUser;
	}

	public void closeUserEntityManager(EntityManager managerUser) {
		managerUser.close();
	}

}
