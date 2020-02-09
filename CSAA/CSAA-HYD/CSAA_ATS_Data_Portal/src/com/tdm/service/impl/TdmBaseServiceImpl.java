package com.tdm.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.springframework.stereotype.Component;

import com.tdm.constant.AppConstant;
import com.tdm.exception.ServiceException;
import com.tdm.model.DTO.TDMAutoPolicySearchDTO;

@Component
public class TdmBaseServiceImpl
{
	@PersistenceUnit(unitName = AppConstant.PERSISTENCE_UNIT_PROV)
	private EntityManagerFactory factoryProv;

	// @PersistenceUnit(unitName = AppConstant.PERSISTENCE_UNIT_SUBC)
	private EntityManagerFactory factorySubsc;

	// @PersistenceUnit(unitName = AppConstant.PERSISTENCE_UNIT_CLAIM)
	private EntityManagerFactory factoryClaim;

	@PersistenceUnit(unitName = AppConstant.PERSISTENCE_UNIT_USER)
	private EntityManagerFactory factoryUser;

	// @PersistenceUnit(unitName = AppConstant.PERSISTENCE_UNIT_DTMSK)
	private EntityManagerFactory factoryDataMasking;

	@PersistenceUnit(unitName = AppConstant.PERSISTENCE_UNIT_POLICY_SEARCH)
	private EntityManagerFactory factoryPolicySearch;

	@PersistenceUnit(unitName = AppConstant.PERSISTENCE_UNIT_CLAIM_SEARCH)
	private EntityManagerFactory factoryClaimSearch;

	@PersistenceUnit(unitName = "newcsaaUserPersistenceUnit")
	private EntityManagerFactory factoryCsaaUser;

	public EntityManager openCsaaUserEntityManager() {
		EntityManager managerCsaaUser = factoryUser.createEntityManager();
		return managerCsaaUser;
	}

	public void closeCsaaUserEntityManager(EntityManager managerCsaaUser) {
		managerCsaaUser.close();
	}

	public EntityManager openPolicySearchEntityManager() {
		EntityManager managerPolicySearch = factoryUser.createEntityManager();

		return managerPolicySearch;
	}

	public void closePolicySearchEntityManager(EntityManager managerPolicySearch) {
		managerPolicySearch.close();
	}

	public EntityManager openClaimSearchEntityManager() {
		EntityManager managerClaimSearch = factoryUser.createEntityManager();

		return managerClaimSearch;
	}

	public void closeClaimSearchEntityManager(EntityManager managerClaimSearch) {
		managerClaimSearch.close();
	}

	public EntityManager openUserEntityManager() {
		EntityManager managerUser = factoryUser.createEntityManager();

		return managerUser;
	}

	public void closeUserEntityManager(EntityManager managerUser) {
		managerUser.close();
	}

	public EntityManager openProviderEntityManager() {
		EntityManager managerUser = factoryUser.createEntityManager();

		return managerUser;
	}

	public void closeProviderEntityManager(EntityManager managerProv) {
		managerProv.close();
	}

	public EntityManager openSubscriberEntityManager() {
		EntityManager managerSubsc = factoryUser.createEntityManager();

		return managerSubsc;
	}

	public void closeSubscriberEntityManager(EntityManager managerSubsc) {
		managerSubsc.close();
	}

	public EntityManager openClaimEntityManager() {
		EntityManager managerClaim = factoryUser.createEntityManager();

		return managerClaim;
	}

	public void closeClaimEntityManager(EntityManager managerClaim) {
		managerClaim.close();
	}

	public EntityManager openDataMaskingEntityManager() {
		EntityManager managerDataMasking = factoryUser.createEntityManager();

		return managerDataMasking;
	}

	public void closeDataMaskingEntityManager(EntityManager managerDataMasking) {
		managerDataMasking.close();
	}

	public int saveReservedData(TDMAutoPolicySearchDTO tdmPolicyAutoSearchDTO,
			String userName, String enviro) throws ServiceException {
		// TODO Auto-generated method stub
		return 0;
	}

}
