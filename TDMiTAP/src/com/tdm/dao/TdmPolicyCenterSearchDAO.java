package com.tdm.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.tdm.exception.DAOException;
import com.tdm.model.DO.PctlCountryDO;
import com.tdm.model.DO.PctlPolicycontactroleDO;
import com.tdm.model.DO.PctlStateDO;
import com.tdm.model.DO.TdmReservationDO;
import com.tdm.model.DTO.TdmPolicyCenterSearchDTO;

public interface TdmPolicyCenterSearchDAO
{
	public Long searchPolicyRecordsCount(TdmPolicyCenterSearchDTO tdmPolicySearchDTO,
			StringBuffer policys, EntityManager managerPolicySearch) throws DAOException;

	public List<TdmReservationDO> getReservedRecords(String userName, String searchType,
			EntityManager managerUser) throws DAOException;

	public List<TdmReservationDO> getReservedRecords(String userName, EntityManager managerUser)
			throws DAOException;

	public List<TdmReservationDO> saveReservedData(List<TdmReservationDO> reservationDOs,
			String testCaseId, String testCaseName, EntityManager managerUser) throws DAOException;

	public boolean unReservedRecordForUser(String policyNo, EntityManager managerUser)
			throws DAOException;

	public List<TdmReservationDO> getReservedRecords(String searchType, String userId, int offSet,
			int recordsperpage, boolean pageNationOnOffFlag, EntityManager managerUser)
			throws DAOException;

	public Long getReservedRecordsCount(String searchType, String userId, EntityManager managerUser)
			throws DAOException;

	List<Object[]> getPolicyStatus(EntityManager managerPolicySearch) throws DAOException;

	List<Object[]> searchPolicyCenterRecords(TdmPolicyCenterSearchDTO tdmPolicyCenterSearchDTO,
			int offSet, int recordsperpage, boolean pageNationOnOffFlag, StringBuffer policys,
			boolean btotalRecords, EntityManager managerPolicySearch, boolean isCalledByReserve) throws DAOException;

	public List<Object[]> getPolicyRecords(StringBuffer policys, EntityManager managerPolicySearch) throws DAOException;

	public List<PctlCountryDO> getCountryCodes(EntityManager managerPolicySearch) throws DAOException;

	public List<PctlStateDO> getStateCodes(EntityManager managerPolicySearch) throws DAOException;

	public List<PctlPolicycontactroleDO> getInsurerTypes(EntityManager managerPolicySearch) throws DAOException;

}
