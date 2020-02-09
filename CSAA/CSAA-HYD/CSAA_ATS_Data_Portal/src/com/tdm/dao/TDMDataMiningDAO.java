package com.tdm.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.tdm.exception.DAOException;
import com.tdm.model.DO.PolicySummaryStg;
import com.tdm.model.DO.TdmReservationDO;
import com.tdm.model.DTO.TDMAutoPolicySearchDTO;
import com.tdm.model.DTO.TDMPolicyPropertyNewSearchDTO;

public interface TDMDataMiningDAO {
	public List<PolicySummaryStg> searchAutoPolicy(
			TDMAutoPolicySearchDTO tdmPolicyPropertySearchDTO, int offSet,
			int recordsperpage, boolean pageNationOnOffFlag,
			StringBuffer policyProps, String policytype,
			EntityManager managerCsaauser) throws DAOException;

	public List<PolicySummaryStg> searchPropertyPolicy(
			TDMPolicyPropertyNewSearchDTO tdmPolicyPropertySearchDTO,
			int offSet, int recordsperpage, boolean pageNationOnOffFlag,
			StringBuffer policyProps, String policytype,
			EntityManager managerCsaauser) throws DAOException;

	public List<TdmReservationDO> saveReservedData(
			List<TdmReservationDO> reservationDOs, String testCaseId,
			String testCaseName, EntityManager managerCsaaUser)
			throws DAOException;

	public List<TdmReservationDO> getReservedRecords(String userId,
			String searchType, EntityManager managerCsaaUser)
			throws DAOException;

	public TDMPolicyPropertyNewSearchDTO getTDMReservedPropertyTestDataListPerUser(
			TDMPolicyPropertyNewSearchDTO tdmPolicyPropertySearchDTO)
			throws Exception;

	public TDMAutoPolicySearchDTO getTDMReservedAutoTestDataListPerUser(
			TDMAutoPolicySearchDTO tdmPolicyPropertySearchDTO) throws Exception;

	public int getTDMAutoPolicyReservationPerUser(
			TDMAutoPolicySearchDTO tdmPolicyPropertySearchDTO) throws Exception;

	public int getTDMPropertyPolicyReservationPerUser(
			TDMAutoPolicySearchDTO tdmPolicyPropertySearchDTO) throws Exception;
	
	
	
	public List<PolicySummaryStg> getPoliciesToReserve(TDMAutoPolicySearchDTO tdmPolicyPropertySearchDTO,EntityManager managerCsaauser)throws DAOException;
	public List<PolicySummaryStg> getPoliciesToReserve(TDMPolicyPropertyNewSearchDTO tdmPolicyPropertySearchDTO ,EntityManager managerCsaauser)throws DAOException;
}
