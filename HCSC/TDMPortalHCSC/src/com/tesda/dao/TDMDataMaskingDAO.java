/*---------------------------------------------------------------------------------------
 * Object Name: TDGDataMaskingDAO.Java
 * 
 * --------------------------------------------------------------------------------------
 *
 * Copyright: 2015 Capgemini Financial Services
 *---------------------------------------------------------------------------------------*/

package com.tesda.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import com.tesda.exception.DAOException;
import com.tesda.model.DO.ReqChDO;
import com.tesda.model.DO.RequestorDO;
import com.tesda.model.DO.TdmOnboardReqDO;
import com.tesda.model.DTO.TdmDataMaskingDTO;
import com.tesda.model.DTO.TdmOnBoardReqDTO;

public interface TDMDataMaskingDAO
{
	public String saveMaskingData(RequestorDO requestorDO, EntityManager managerDtmask)
			throws DAOException;

	public List<RequestorDO> getAllDtMaskRequestedRecord(String userId, EntityManager managerDtmask)
			throws DAOException;

	public Map<RequestorDO, List<ReqChDO>> getDtMaskRequestedRecord(EntityManager managerDtmask,
			String userId, String reqId) throws DAOException;

	public List<RequestorDO> searchDataMaskingRecords(String role, int offSet, int recordsperpage,
			boolean pageNationOnOffFlag, String userId, String type, EntityManager managerDtmask)
			throws DAOException;

	public Long getDataMaskingRecordsCount(String role, String userId, String type,
			EntityManager managerDtmask) throws DAOException;

	public TdmDataMaskingDTO getUserDetails(EntityManager managerDtmask, String userId)
			throws DAOException;

	public TdmOnBoardReqDTO getUserDetailsOnboard(String userId, EntityManager managerUser,
			TdmOnBoardReqDTO tdmOnBoardReqDTO) throws DAOException;

	public TdmOnboardReqDO getSaveOnboardingReq(EntityManager managerDtmask,
			TdmOnboardReqDO tdmOnboardReqDO) throws DAOException;;

	public TdmOnboardReqDO getEditableDetails(EntityManager managerDtmask, String reqId)
			throws DAOException;

	public List<String> getReqIdList(String userId, EntityManager managerDtmask, String reqIdtoken)
			throws DAOException;

	public Long getOnBoardingRecordsCount(String role, String userId, String type,
			EntityManager managerDtmask) throws DAOException;

	public List<TdmOnboardReqDO> searchOnBoardingRecords(String role, int offSet,
			int recordsperpage, boolean pageNationOnOffFlag, String userId, String type,
			EntityManager managerDtmask) throws DAOException;

	public String onBoardingRecId(EntityManager managerDtmask);

	public String maskingRecId(EntityManager managerDtmask);

	public boolean cancelOnBoardingReq(EntityManager managerDtmask, String reqId)
			throws DAOException;

	public boolean cancelMaskingReq(EntityManager managerDtmask, String reqId) throws DAOException;

	public List<RequestorDO> getSavedDtlsforExport(String role, String userId, String type,
			EntityManager managerDtmask) throws DAOException;

	public RequestorDO getSavedDtls(String id, EntityManager managerDtmask) throws DAOException;

	public RequestorDO getSaveReqDtls(RequestorDO requestorDO, EntityManager managerUser)
			throws DAOException;

	public boolean dtMaskCancelReq(String reqId, EntityManager managerDtmask) throws DAOException;

	public boolean getCheckExistingReqYesNo(String userId, EntityManager managerDtmask)
			throws DAOException;

	public boolean deleteRow(String type, String Id, EntityManager managerDtmask)
			throws DAOException;

	public void removeDuplicateRecordsFromRequestorCHDO(String Id, EntityManager managerDtmask)
			throws DAOException;
}
