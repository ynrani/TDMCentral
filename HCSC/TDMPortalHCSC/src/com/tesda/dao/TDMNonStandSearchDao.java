/*---------------------------------------------------------------------------------------
 * Object Name: TDMNonStandSearchDao.Java
 * 
 * --------------------------------------------------------------------------------------
 *
 * Copyright: 2015 Capgemini Financial Services
 *---------------------------------------------------------------------------------------*/

package com.tesda.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.tesda.exception.DAOException;
import com.tesda.model.DO.TDMSearchDetailsDO;
import com.tesda.model.DO.TdmBlendedMemberDO;
import com.tesda.model.DO.TdmLookupDO;
import com.tesda.model.DO.TdmReservationDO;
import com.tesda.model.DO.TdmSubscriberDetailsDO;
import com.tesda.model.DO.TdmUserDO;
import com.tesda.model.DTO.TDMNonStandardSearchDTO;

public interface TDMNonStandSearchDao
{

	public List<TdmLookupDO> getTDMSearchFields(EntityManager managerLookup) throws DAOException;

	public List<TdmSubscriberDetailsDO> getTDMNonStandSearchRecords(
			TDMNonStandardSearchDTO tdmNonStandDTO, int offSet, int recordsperpage,
			EntityManager managerSubscr) throws DAOException;

	public List<Object[]> getTDMNonStandardSearchRecords(TDMNonStandardSearchDTO tdmNonStandDTO,
			int offSet, int recordsperpage, EntityManager managerSubscr) throws DAOException;

	public List<TdmReservationDO> saveReserveData(List<TdmReservationDO> tdmResrveList,
			String testCaseName, String testCaseid, EntityManager managerUsr) throws DAOException;

	public List<TdmReservationDO> getReservedRecords(String role, String userId, int offSet,
			int recPerPage, EntityManager managerUsr) throws DAOException;

	public List<TdmReservationDO> getReservedRecords(EntityManager managerUsr) throws DAOException;

	public void unReserveResrvedRecords(List<TdmReservationDO> tdmUnResrveList, String userId,
			EntityManager managerUsr) throws DAOException;

	public List<String> getSubscriberAcNameNumDetails(String type, String token,
			EntityManager managerSubscrbr) throws DAOException;

	public long getTDMNonStandardSearchRecordCount(TDMNonStandardSearchDTO tdmNonStandDTO,
			EntityManager managerSubscr) throws DAOException;

	public long getNonStandardSearchRecordCount(TDMNonStandardSearchDTO tdmNonStandDTO,
			EntityManager managerSubscr) throws DAOException;

	public long getReservedRecordsCount(String role, String userId, EntityManager managerUsr)
			throws DAOException;

	public TdmUserDO checkAvailabilityOfUserId(String userId, EntityManager managerUser)
			throws Exception;

	public List<TdmBlendedMemberDO> getBlendedRecords(EntityManager managerSubscr)
			throws DAOException;

	public List<Object[]> getDependentDetails(String subId, EntityManager managerSubscr)
			throws DAOException;

	public List<Object[]> getDependentDetails(TDMNonStandardSearchDTO tdmNonStandDTO,
			EntityManager managerSubscr) throws DAOException;
	
	public void saveSearchDetails(TDMSearchDetailsDO serachDtlsDO, EntityManager managerUser)
			throws Exception;
}
