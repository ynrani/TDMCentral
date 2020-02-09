package com.tdm.service;

import java.util.List;

import javax.persistence.EntityManager;

import com.tdm.exception.DAOException;
import com.tdm.exception.ServiceException;
import com.tdm.model.DO.TdmReservationDO;
import com.tdm.model.DTO.TDMAutoPolicySearchDTO;
import com.tdm.model.DTO.TDMPolicyPropertyNewSearchDTO;

public interface TDMDataMiningService {
	public TDMAutoPolicySearchDTO searchAutoPolicy(
			TDMAutoPolicySearchDTO tdmPolicyPropertySearchDTO, int offSet,
			int recordsperpage, boolean pageNationOnOffFlag, String userName)
			throws ServiceException;

	public TDMPolicyPropertyNewSearchDTO searchPropertyPolicy(
			TDMPolicyPropertyNewSearchDTO tdmPolicyPropertySearchDTO,
			int offSet, int recordsperpage, boolean pageNationOnOffFlag,
			String userName) throws ServiceException;

	public int saveReservedDataForAutoPolicy(
			TDMAutoPolicySearchDTO tdmPolicyAutoSearchDTO, String userName,
			String enviro) throws ServiceException;

	public int saveReservedDataForPropertyPolicy(
			TDMPolicyPropertyNewSearchDTO tdmPolicyPropertySearchDTO,
			String userName, String enviro) throws ServiceException;

	public List<TdmReservationDO> saveReservedData(
			List<TdmReservationDO> reservationDOList, String testCaseId,
			String testCaseName, EntityManager managerCsaaUser)
			throws DAOException;

	public TDMPolicyPropertyNewSearchDTO getTDMReservedPropertyTestDataListPerUser(
			TDMPolicyPropertyNewSearchDTO tdmPolicyPropertySearchDTO)
			throws DAOException;

	public TDMAutoPolicySearchDTO getTDMReservedAutoTestDataListPerUser(
			TDMAutoPolicySearchDTO tdmPolicyPropertySearchDTO)
			throws DAOException;

	public int getTDMAutoPolicyReservationPerUser(
			TDMAutoPolicySearchDTO tdmPolicyPropertySearchDTO)
			throws DAOException;

	public int getTDMPropertyPolicyReservationPerUser(
			TDMAutoPolicySearchDTO tdmPolicyPropertySearchDTO)
			throws DAOException;
	
	public int getPoliciesToExport(
			TDMAutoPolicySearchDTO tdmPolicyAutoSearchDTO, String userName
			) throws ServiceException;
	
	public int getPropertyPoliciesToExport(
			TDMPolicyPropertyNewSearchDTO tdmPolicyAutoSearchDTO, String userName
			) throws ServiceException;
}
