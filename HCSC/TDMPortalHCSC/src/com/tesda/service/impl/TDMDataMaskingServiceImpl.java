/*---------------------------------------------------------------------------------------
 * Object Name: TDMDataMaskingServiceImpl.Java
 * --------------------------------------------------------------------------------------
 *
 * Copyright: 2015 Capgemini Financial Services
 *---------------------------------------------------------------------------------------*/

package com.tesda.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tesda.constants.MessageConstants;
import com.tesda.constants.TDMConstants;
import com.tesda.constants.TDMExceptionCode;
import com.tesda.dao.TDMDataMaskingDAO;
import com.tesda.exception.DAOException;
import com.tesda.exception.ServiceException;
import com.tesda.model.DO.ReqChDO;
import com.tesda.model.DO.RequestorDO;
import com.tesda.model.DO.TdmOnboardReqDO;
import com.tesda.model.DTO.TdmDataMaskingDTO;
import com.tesda.model.DTO.TdmDataMaskingNoOfAppsDTO;
import com.tesda.model.DTO.TdmOnBoardReqDTO;
import com.tesda.model.mapper.TDMDataMaskingMapper;
import com.tesda.service.TDMDataMaskingService;

/**
 * Service class which provides the following services.
 * Creates,cancels and modifies the masking and on boarding requests.
 * Gets the saved  masking and on boarding details.
 */

@Component
@Service(TDMConstants.DATAMASKING_SERVICE)
@Transactional(propagation = Propagation.REQUIRED)
public class TDMDataMaskingServiceImpl extends TdmBaseServiceImpl implements TDMDataMaskingService
{

	private static final Logger logger = LoggerFactory.getLogger(TDMDataMaskingServiceImpl.class);

	@Autowired
	TDMDataMaskingDAO tDMDataMaskingDAO;

	@Autowired
	TDMDataMaskingMapper tdmDataMaskingMapper;

	@Override
	public Long getReservedRecordsCount(String role, String userId, String type)
			throws ServiceException
	{
		logger.info(MessageConstants.DATA_MASKING_SERVICEIMPL + MessageConstants.GET_RESV_REC_CNT);
		try
		{
			EntityManager managerDtmask = openDataMaskingEntityManager();
			Long lResult = tDMDataMaskingDAO.getDataMaskingRecordsCount(role, userId, type,
					managerDtmask);
			closeDataMaskingEntityManager(managerDtmask);
			logger.info(MessageConstants.DATA_MASKING_SERVICEIMPL
					+ MessageConstants.GET_RESV_REC_CNT + MessageConstants.LOG_INFO_RETURN);
			return lResult;
		}
		catch (NullPointerException ex)
		{
			logger.error(MessageConstants.DATA_MASKING_SERVICEIMPL
					+ MessageConstants.GET_RESV_REC_CNT, ex);
			throw new ServiceException(TDMExceptionCode.NULL_POINTR, ex);
		}
		catch (DAOException de)
		{
			logger.error(MessageConstants.DATA_MASKING_SERVICEIMPL
					+ MessageConstants.GET_RESV_REC_CNT, de);
			throw new ServiceException(de.getErrorCode(), de.getMessage());
		}
	}

	@Override
	public TdmDataMaskingDTO saveReqDtls(TdmDataMaskingDTO tdgDataMaskingDTO, boolean page1,
			boolean page2, boolean page3) throws ServiceException
	{
		logger.info(MessageConstants.DATA_MASKING_SERVICEIMPL + MessageConstants.SAVE_REQDTLS);
		RequestorDO requestorDO = null;
		String seq = null;
		try
		{
			EntityManager managerDtmask = openDataMaskingEntityManager();
			if (page1 && StringUtils.isEmpty(tdgDataMaskingDTO.getEdit()))
			{
				if (StringUtils.isEmpty(tdgDataMaskingDTO.getId()))
				{
					seq = tDMDataMaskingDAO.maskingRecId(managerDtmask);
				}
			}
			requestorDO = tDMDataMaskingDAO.getSavedDtls(tdgDataMaskingDTO.getId(), managerDtmask);
			managerDtmask.clear();
			requestorDO = tdmDataMaskingMapper.convertMaskDTOtoDO(requestorDO, tdgDataMaskingDTO,
					seq, page1, page2, page3, false);
			if (page3 && requestorDO.getId().contains(TDMConstants.CR))
			{
				tDMDataMaskingDAO.removeDuplicateRecordsFromRequestorCHDO(requestorDO.getId(),
						managerDtmask);
			}
			requestorDO = tDMDataMaskingDAO.getSaveReqDtls(requestorDO, managerDtmask);
			tdgDataMaskingDTO.setId(requestorDO.getId());
			closeDataMaskingEntityManager(managerDtmask);
			logger.info(MessageConstants.DATA_MASKING_SERVICEIMPL + MessageConstants.SAVE_REQDTLS
					+ MessageConstants.LOG_INFO_RETURN);
			return tdgDataMaskingDTO;
		}
		catch (NullPointerException nullPointerEx)
		{
			logger.error(MessageConstants.DATA_MASKING_SERVICEIMPL + MessageConstants.SAVE_REQDTLS,
					nullPointerEx);
			throw new ServiceException(TDMExceptionCode.NULL_POINTR, nullPointerEx);
		}
		catch (DAOException daoEx)
		{
			logger.error(MessageConstants.DATA_MASKING_SERVICEIMPL + MessageConstants.SAVE_REQDTLS,
					daoEx);
			throw new ServiceException(daoEx.getErrorCode(), daoEx);
		}
		catch (Exception otherEx)
		{
			logger.error(MessageConstants.DATA_MASKING_SERVICEIMPL + MessageConstants.SAVE_REQDTLS,
					otherEx);
			throw new ServiceException(TDMExceptionCode.EXCEPTION, otherEx);
		}
	}

	@Override
	public TdmDataMaskingDTO getSavedDtls(TdmDataMaskingDTO tdgDataMaskingDTO, boolean page1,
			boolean page2, boolean page3) throws ServiceException
	{
		logger.info(MessageConstants.DATA_MASKING_SERVICEIMPL + MessageConstants.GET_SAVED_DTLS);
		RequestorDO requestorDO = null;
		try
		{
			EntityManager managerDtmask = openDataMaskingEntityManager();
			requestorDO = tDMDataMaskingDAO.getSavedDtls(tdgDataMaskingDTO.getId(), managerDtmask);
			tdgDataMaskingDTO = tdmDataMaskingMapper.convertDOtoMaskDTO(tdgDataMaskingDTO,
					requestorDO, page1, page2, page3);
			closeDataMaskingEntityManager(managerDtmask);
			if (null != tdgDataMaskingDTO)
			{
				if (null == tdgDataMaskingDTO.getTdgDataMaskingNoOfAppsDTOs())
				{
					List<TdmDataMaskingNoOfAppsDTO> tdgDataMaskingNoOfAppsDTOs = new ArrayList<TdmDataMaskingNoOfAppsDTO>();
					TdmDataMaskingNoOfAppsDTO tdgDataMaskingNoOfAppsDTO = new TdmDataMaskingNoOfAppsDTO();
					tdgDataMaskingNoOfAppsDTOs.add(tdgDataMaskingNoOfAppsDTO);
					tdgDataMaskingDTO.setTdgDataMaskingNoOfAppsDTOs(tdgDataMaskingNoOfAppsDTOs);
				}
			}
			logger.info(MessageConstants.DATA_MASKING_SERVICEIMPL + MessageConstants.GET_SAVED_DTLS
					+ MessageConstants.LOG_INFO_RETURN);
			return tdgDataMaskingDTO;
		}
		catch (NullPointerException nullPointerEx)
		{
			logger.error(MessageConstants.DATA_MASKING_SERVICEIMPL
					+ MessageConstants.GET_SAVED_DTLS, nullPointerEx);
			throw new ServiceException(TDMExceptionCode.NULL_POINTR, nullPointerEx);
		}
		catch (DAOException daoEx)
		{
			logger.error(MessageConstants.DATA_MASKING_SERVICEIMPL
					+ MessageConstants.GET_SAVED_DTLS, daoEx);
			throw new ServiceException(daoEx.getErrorCode(), daoEx);
		}
		catch (Exception otherEx)
		{
			logger.error(MessageConstants.DATA_MASKING_SERVICEIMPL
					+ MessageConstants.GET_SAVED_DTLS, otherEx);
			throw new ServiceException(TDMExceptionCode.EXCEPTION, otherEx);
		}
	}

	@Override
	public TdmDataMaskingDTO getAllDtMaskRequestedRecord(String userId) throws ServiceException
	{
		logger.info(MessageConstants.DATA_MASKING_SERVICEIMPL
				+ MessageConstants.GETALL_DTMASK_REQREC);
		try
		{
			EntityManager managerDtmask = openDataMaskingEntityManager();
			List<RequestorDO> requestorDOs = tDMDataMaskingDAO.getAllDtMaskRequestedRecord(userId,
					managerDtmask);
			closeDataMaskingEntityManager(managerDtmask);
			logger.info(MessageConstants.DATA_MASKING_SERVICEIMPL
					+ MessageConstants.GETALL_DTMASK_REQREC + MessageConstants.LOG_INFO_RETURN);
			return tdmDataMaskingMapper.converDOtoRequestorDTO(requestorDOs);
		}
		catch (NullPointerException ex)
		{
			logger.error(MessageConstants.DATA_MASKING_SERVICEIMPL
					+ MessageConstants.GETALL_DTMASK_REQREC, ex);
			throw new ServiceException(TDMExceptionCode.NULL_POINTR, ex);
		}
		catch (DAOException de)
		{
			logger.error(MessageConstants.DATA_MASKING_SERVICEIMPL
					+ MessageConstants.GETALL_DTMASK_REQREC, de);
			throw new ServiceException(de.getErrorCode(), de.getMessage());
		}
	}

	@Override
	public TdmDataMaskingDTO getDtMaskRequestedRecord(String userId, String reqId)
			throws ServiceException
	{
		logger.info(MessageConstants.DATA_MASKING_SERVICEIMPL + MessageConstants.GET_DTMASK_REQREC);
		try
		{
			EntityManager managerDtmask = openDataMaskingEntityManager();
			Map<RequestorDO, List<ReqChDO>> map = tDMDataMaskingDAO.getDtMaskRequestedRecord(
					managerDtmask, userId, reqId);
			closeDataMaskingEntityManager(managerDtmask);
			logger.info(MessageConstants.DATA_MASKING_SERVICEIMPL
					+ MessageConstants.GET_DTMASK_REQREC + MessageConstants.LOG_INFO_RETURN);
			return tdmDataMaskingMapper.converDOtoRequestDTOForReqId(map);
		}
		catch (NullPointerException ex)
		{
			logger.error(MessageConstants.DATA_MASKING_SERVICEIMPL
					+ MessageConstants.GET_DTMASK_REQREC, ex);
			throw new ServiceException(TDMExceptionCode.NULL_POINTR, ex);
		}
		catch (DAOException de)
		{
			logger.error(MessageConstants.DATA_MASKING_SERVICEIMPL
					+ MessageConstants.GET_DTMASK_REQREC, de);
			throw new ServiceException(de.getErrorCode(), de.getMessage());
		}

	}

	@Override
	public TdmDataMaskingDTO getAllDtMaskRequestedRecordForPagination(int offSet,
			int recordsperpage, boolean pageNationOnOffFlag, TdmDataMaskingDTO tdgDataMaskingDTO,
			String type) throws ServiceException
	{
		logger.info(MessageConstants.DATA_MASKING_SERVICEIMPL
				+ MessageConstants.GET_ALLDTMSK_REQREC_PGN + MessageConstants.LOG_INFO_RETURN);
		try
		{
			EntityManager managerDtmask = openDataMaskingEntityManager();
			List<RequestorDO> requestorDOs = tDMDataMaskingDAO.searchDataMaskingRecords(
					tdgDataMaskingDTO.getRole(), offSet, recordsperpage, pageNationOnOffFlag,
					tdgDataMaskingDTO.getUserId(), type, managerDtmask);
			closeDataMaskingEntityManager(managerDtmask);
			logger.info(MessageConstants.DATA_MASKING_SERVICEIMPL
					+ MessageConstants.GET_ALLDTMSK_REQREC_PGN + MessageConstants.LOG_INFO_RETURN);
			return tdmDataMaskingMapper.convertDOtoMaskDTOs(tdgDataMaskingDTO, requestorDOs, true,
					true, true);
		}
		catch (NullPointerException nullPointerEx)
		{
			logger.error(MessageConstants.DATA_MASKING_SERVICEIMPL
					+ MessageConstants.GET_ALLDTMSK_REQREC_PGN, nullPointerEx);
			throw new ServiceException(TDMExceptionCode.NULL_POINTR, nullPointerEx);
		}
		catch (DAOException daoEx)
		{
			logger.error(MessageConstants.DATA_MASKING_SERVICEIMPL
					+ MessageConstants.GET_ALLDTMSK_REQREC_PGN, daoEx);
			throw new ServiceException(daoEx.getErrorCode(), daoEx);
		}
		catch (Exception otherEx)
		{
			logger.error(MessageConstants.DATA_MASKING_SERVICEIMPL
					+ MessageConstants.GET_ALLDTMSK_REQREC_PGN, otherEx);
			throw new ServiceException(TDMExceptionCode.EXCEPTION, otherEx);
		}
	}

	@Override
	public TdmDataMaskingDTO getUserDetails(TdmDataMaskingDTO tdgDataMaskingDTO)
			throws ServiceException
	{
		logger.info(MessageConstants.DATA_MASKING_SERVICEIMPL + MessageConstants.GET_USER_DETAILS);
		try
		{
			EntityManager managerUser = openUserEntityManager();
			tdgDataMaskingDTO = tDMDataMaskingDAO.getUserDetails(managerUser,
					tdgDataMaskingDTO.getUserId());
			closeDataMaskingEntityManager(managerUser);
			logger.info(MessageConstants.DATA_MASKING_SERVICEIMPL
					+ MessageConstants.GET_USER_DETAILS + MessageConstants.LOG_INFO_RETURN);
			return tdgDataMaskingDTO;
		}
		catch (NullPointerException ex)
		{
			logger.error(MessageConstants.DATA_MASKING_SERVICEIMPL
					+ MessageConstants.GET_USER_DETAILS, ex);
			throw new ServiceException(TDMExceptionCode.NULL_POINTR, ex);
		}
		catch (DAOException de)
		{
			logger.error(MessageConstants.DATA_MASKING_SERVICEIMPL
					+ MessageConstants.GET_USER_DETAILS, de);
			throw new ServiceException(de.getErrorCode(), de.getMessage());
		}
	}

	@Override
	public TdmOnBoardReqDTO getUserDetails(String userId, TdmOnBoardReqDTO tdmOnBoardReqDTO)
			throws ServiceException
	{
		logger.info(MessageConstants.DATA_MASKING_SERVICEIMPL + MessageConstants.GET_USER_DETAILS
				+ MessageConstants.TWO_PARAMS);
		try
		{
			EntityManager managerUser = openUserEntityManager();
			tdmOnBoardReqDTO = tDMDataMaskingDAO.getUserDetailsOnboard(userId, managerUser,
					tdmOnBoardReqDTO);
			closeUserEntityManager(managerUser);
			List<TdmDataMaskingNoOfAppsDTO> tdgDataMaskingNoOfAppsDTOs = new ArrayList<TdmDataMaskingNoOfAppsDTO>();
			TdmDataMaskingNoOfAppsDTO tdgDataMaskingNoOfAppsDTO = new TdmDataMaskingNoOfAppsDTO();
			tdgDataMaskingNoOfAppsDTOs.add(tdgDataMaskingNoOfAppsDTO);
			tdmOnBoardReqDTO.setTdgDataMaskingNoOfAppsDTOs(tdgDataMaskingNoOfAppsDTOs);
			logger.info(MessageConstants.DATA_MASKING_SERVICEIMPL
					+ MessageConstants.GET_USER_DETAILS + MessageConstants.TWO_PARAMS
					+ MessageConstants.LOG_INFO_RETURN);
			return tdmOnBoardReqDTO;
		}
		catch (NullPointerException ex)
		{
			logger.error(MessageConstants.DATA_MASKING_SERVICEIMPL
					+ MessageConstants.GET_USER_DETAILS + MessageConstants.TWO_PARAMS, ex);
			throw new ServiceException(TDMExceptionCode.NULL_POINTR, ex);
		}
		catch (DAOException de)
		{
			logger.error(MessageConstants.DATA_MASKING_SERVICEIMPL
					+ MessageConstants.GET_USER_DETAILS + MessageConstants.TWO_PARAMS, de);
			throw new ServiceException(de.getErrorCode(), de.getMessage());
		}
	}

	@Override
	public TdmOnBoardReqDTO getSaveOnboardingReq(TdmOnBoardReqDTO tdmOnboardReqDTO)
			throws ServiceException
	{
		logger.info(MessageConstants.DATA_MASKING_SERVICEIMPL + MessageConstants.GETSAVE_ONBRD_REQ);
		try
		{
			EntityManager managerDtmask = openDataMaskingEntityManager();
			String seq = null;
			if (StringUtils.isEmpty(tdmOnboardReqDTO.getOnboardReqId()))
			{
				seq = tDMDataMaskingDAO.onBoardingRecId(managerDtmask);
			}
			TdmOnboardReqDO tdmOnboardReqDO = tdmDataMaskingMapper.convertTdmOnBoardReqDTOToDO(
					tdmOnboardReqDTO, seq);
			tdmOnboardReqDO = tDMDataMaskingDAO
					.getSaveOnboardingReq(managerDtmask, tdmOnboardReqDO);
			tdmOnboardReqDTO.setOnboardReqId(tdmOnboardReqDO.getOnboardReqId());
			closeDataMaskingEntityManager(managerDtmask);
			logger.info(MessageConstants.DATA_MASKING_SERVICEIMPL
					+ MessageConstants.GETSAVE_ONBRD_REQ + MessageConstants.LOG_INFO_RETURN);
			return tdmOnboardReqDTO;
		}
		catch (NullPointerException ex)
		{
			logger.error(MessageConstants.DATA_MASKING_SERVICEIMPL
					+ MessageConstants.GETSAVE_ONBRD_REQ, ex);
			throw new ServiceException(TDMExceptionCode.NULL_POINTR, ex);
		}
		catch (DAOException de)
		{
			logger.error(MessageConstants.DATA_MASKING_SERVICEIMPL
					+ MessageConstants.GETSAVE_ONBRD_REQ, de);
			throw new ServiceException(de.getErrorCode(), de.getMessage());
		}
	}

	@Override
	public TdmOnBoardReqDTO getEditableDetails(String reqId) throws ServiceException
	{
		logger.info(MessageConstants.DATA_MASKING_SERVICEIMPL + MessageConstants.GET_EDITABLE_DTLS);
		try
		{
			EntityManager managerDtmask = openDataMaskingEntityManager();
			TdmOnboardReqDO tdmOnboardReqDO = tDMDataMaskingDAO.getEditableDetails(managerDtmask,
					reqId);
			TdmOnBoardReqDTO tdmOnboardReqDTO = tdmDataMaskingMapper
					.convertTdmOnboardReqDOToDO(tdmOnboardReqDO);
			closeDataMaskingEntityManager(managerDtmask);
			logger.info(MessageConstants.DATA_MASKING_SERVICEIMPL
					+ MessageConstants.GET_EDITABLE_DTLS + MessageConstants.LOG_INFO_RETURN);
			return tdmOnboardReqDTO;
		}
		catch (NullPointerException ex)
		{
			logger.error(MessageConstants.DATA_MASKING_SERVICEIMPL
					+ MessageConstants.GET_EDITABLE_DTLS, ex);
			throw new ServiceException(TDMExceptionCode.NULL_POINTR, ex);
		}
		catch (DAOException de)
		{
			logger.error(MessageConstants.DATA_MASKING_SERVICEIMPL
					+ MessageConstants.GET_EDITABLE_DTLS, de);
			throw new ServiceException(de.getErrorCode(), de.getMessage());
		}
	}

	@Override
	public List<String> getReqIdList(String userId, String reqIdtoken) throws ServiceException
	{
		logger.info(MessageConstants.DATA_MASKING_SERVICEIMPL + MessageConstants.GET_REQID_LIST);
		try
		{
			EntityManager managerDtmask = openDataMaskingEntityManager();
			List<String> reqIds = null;
			List<String> reqList = tDMDataMaskingDAO
					.getReqIdList(userId, managerDtmask, reqIdtoken);
			if (null != reqList && 0 < reqList.size())
			{
				reqIds = filterReqIDs(reqList);
			}
			closeDataMaskingEntityManager(managerDtmask);
			logger.info(MessageConstants.DATA_MASKING_SERVICEIMPL + MessageConstants.GET_REQID_LIST
					+ MessageConstants.LOG_INFO_RETURN);
			return reqIds;
		}
		catch (NullPointerException ex)
		{
			logger.error(MessageConstants.DATA_MASKING_SERVICEIMPL
					+ MessageConstants.GET_REQID_LIST, ex);
			throw new ServiceException(TDMExceptionCode.NULL_POINTR, ex);
		}
		catch (DAOException de)
		{
			logger.error(MessageConstants.DATA_MASKING_SERVICEIMPL
					+ MessageConstants.GET_REQID_LIST, de);
			throw new ServiceException(de.getErrorCode(), de.getMessage());
		}

	}

	@Override
	public Long getReservedRecordsCountOnBoard(String role, String userId, String type)
			throws ServiceException
	{
		logger.info(MessageConstants.DATA_MASKING_SERVICEIMPL
				+ MessageConstants.GET_RESV_REC_CNT_ONBRD);
		try
		{
			EntityManager managerDtmask = openDataMaskingEntityManager();
			Long lResult = tDMDataMaskingDAO.getOnBoardingRecordsCount(role, userId, type,
					managerDtmask);
			closeDataMaskingEntityManager(managerDtmask);
			logger.info(MessageConstants.DATA_MASKING_SERVICEIMPL
					+ MessageConstants.GET_RESV_REC_CNT_ONBRD + MessageConstants.LOG_INFO_RETURN);
			return lResult;
		}
		catch (NullPointerException ex)
		{
			logger.error(MessageConstants.DATA_MASKING_SERVICEIMPL
					+ MessageConstants.GET_RESV_REC_CNT_ONBRD, ex);
			throw new ServiceException(TDMExceptionCode.NULL_POINTR, ex);
		}
		catch (DAOException de)
		{
			logger.error(MessageConstants.DATA_MASKING_SERVICEIMPL
					+ MessageConstants.GET_RESV_REC_CNT_ONBRD, de);
			throw new ServiceException(de.getErrorCode(), de.getMessage());
		}
	}

	@Override
	public List<TdmDataMaskingDTO> getAllOnBoardRequestedRecordForPagination(String role,
			int offSet, int recordsperpage, boolean pageNationOnOffFlag, String userId, String type)
			throws ServiceException
	{
		logger.info(MessageConstants.DATA_MASKING_SERVICEIMPL
				+ MessageConstants.GET_ALLONBRD_REQREC_PGN);
		try
		{
			EntityManager managerDtmask = openDataMaskingEntityManager();
			List<TdmOnboardReqDO> requestorDOs = tDMDataMaskingDAO.searchOnBoardingRecords(role,
					offSet, recordsperpage, pageNationOnOffFlag, userId, type, managerDtmask);
			closeDataMaskingEntityManager(managerDtmask);
			logger.info(MessageConstants.DATA_MASKING_SERVICEIMPL
					+ MessageConstants.GET_ALLONBRD_REQREC_PGN + MessageConstants.LOG_INFO_RETURN);
			return tdmDataMaskingMapper.converTdmOnboardReqDOtoDTO(requestorDOs);
		}
		catch (NullPointerException ex)
		{
			logger.error(MessageConstants.DATA_MASKING_SERVICEIMPL
					+ MessageConstants.GET_ALLONBRD_REQREC_PGN, ex);
			throw new ServiceException(TDMExceptionCode.NULL_POINTR, ex);
		}
		catch (DAOException de)
		{
			logger.error(MessageConstants.DATA_MASKING_SERVICEIMPL
					+ MessageConstants.GET_ALLONBRD_REQREC_PGN, de);
			throw new ServiceException(de.getErrorCode(), de.getMessage());
		}
	}

	@Override
	public boolean cancelOnBoardingRequest(String reqId) throws ServiceException
	{
		logger.info(MessageConstants.DATA_MASKING_SERVICEIMPL + MessageConstants.CANCEL_ONBRD_REQ);
		try
		{
			EntityManager managerDtmask = openDataMaskingEntityManager();
			boolean isConcelled = tDMDataMaskingDAO.cancelOnBoardingReq(managerDtmask, reqId);
			closeDataMaskingEntityManager(managerDtmask);
			logger.info(MessageConstants.DATA_MASKING_SERVICEIMPL
					+ MessageConstants.CANCEL_ONBRD_REQ + MessageConstants.LOG_INFO_RETURN);
			return isConcelled;
		}
		catch (NullPointerException ex)
		{
			logger.error(MessageConstants.DATA_MASKING_SERVICEIMPL
					+ MessageConstants.CANCEL_ONBRD_REQ, ex);
			throw new ServiceException(TDMExceptionCode.NULL_POINTR, ex);
		}
		catch (DAOException de)
		{
			logger.error(MessageConstants.DATA_MASKING_SERVICEIMPL
					+ MessageConstants.CANCEL_ONBRD_REQ, de);
			throw new ServiceException(de.getErrorCode(), de.getMessage());
		}
	}

	@Override
	public boolean cancelMaskingRequest(String reqId) throws ServiceException
	{
		logger.info(MessageConstants.DATA_MASKING_SERVICEIMPL + MessageConstants.CANCEL_MSKING_REQ);
		try
		{
			EntityManager managerDtmask = openDataMaskingEntityManager();
			boolean isConcelled = tDMDataMaskingDAO.cancelMaskingReq(managerDtmask, reqId);
			closeDataMaskingEntityManager(managerDtmask);
			logger.info(MessageConstants.DATA_MASKING_SERVICEIMPL
					+ MessageConstants.CANCEL_MSKING_REQ + MessageConstants.LOG_INFO_RETURN);
			return isConcelled;
		}
		catch (NullPointerException ex)
		{
			logger.error(MessageConstants.DATA_MASKING_SERVICEIMPL
					+ MessageConstants.CANCEL_MSKING_REQ, ex);
			throw new ServiceException(TDMExceptionCode.NULL_POINTR, ex);
		}
		catch (DAOException de)
		{
			logger.error(MessageConstants.DATA_MASKING_SERVICEIMPL
					+ MessageConstants.CANCEL_MSKING_REQ, de);
			throw new ServiceException(de.getErrorCode(), de.getMessage());
		}
	}

	public List<String> filterReqIDs(List<String> reqList)
	{
		List<String> reqIds = new ArrayList<String>();
		String reqId = "";
		for (String s : reqList)
		{
			if (!s.contains(TDMConstants.CR))
			{
				reqId = s;
				int max = 0;
				for (String s1 : reqList)
				{
					if (s1.contains(TDMConstants.CR) && s1.startsWith(s))
					{
						int id = Integer.parseInt(s1.split(TDMConstants.CR)[1]);
						if (max < id)
						{
							max = id;
							reqId = s1;
						}
					}
				}
				reqIds.add(reqId);
			}
		}
		return reqIds;
	}

	@Override
	public TdmDataMaskingDTO getSavedDtlsforExport(String role,
			TdmDataMaskingDTO tdgDataMaskingDTO, boolean page1, boolean page2, boolean page3,
			String type) throws ServiceException
	{
		logger.info(MessageConstants.DATA_MASKING_SERVICEIMPL + MessageConstants.GET_DTLS_TOEXPORT);
		try
		{
			EntityManager managerDtmask = openDataMaskingEntityManager();
			List<RequestorDO> requestorDOs = tDMDataMaskingDAO.getSavedDtlsforExport(role,
					tdgDataMaskingDTO.getUserId(), type, managerDtmask);
			closeDataMaskingEntityManager(managerDtmask);
			logger.info(MessageConstants.DATA_MASKING_SERVICEIMPL
					+ MessageConstants.GET_DTLS_TOEXPORT + MessageConstants.LOG_INFO_RETURN);
			return tdmDataMaskingMapper.convertDOtoMaskDTOs(tdgDataMaskingDTO, requestorDOs, true,
					true, true);
		}
		catch (NullPointerException nullPointerEx)
		{
			logger.error(MessageConstants.DATA_MASKING_SERVICEIMPL
					+ MessageConstants.GET_DTLS_TOEXPORT, nullPointerEx);
			throw new ServiceException(TDMExceptionCode.NULL_POINTR, nullPointerEx);
		}
		catch (DAOException daoEx)
		{
			logger.error(MessageConstants.DATA_MASKING_SERVICEIMPL
					+ MessageConstants.GET_DTLS_TOEXPORT, daoEx);
			throw new ServiceException(daoEx.getErrorCode(), daoEx);
		}
		catch (Exception otherEx)
		{
			logger.error(MessageConstants.DATA_MASKING_SERVICEIMPL
					+ MessageConstants.GET_DTLS_TOEXPORT, otherEx);
			throw new ServiceException(TDMExceptionCode.EXCEPTION, otherEx);
		}
	}

	@Override
	public boolean dtMaskCancelReq(String reqId) throws ServiceException
	{
		logger.info(MessageConstants.DATA_MASKING_SERVICEIMPL + MessageConstants.DTMASK_CANCEL_REQ);
		try
		{
			EntityManager managerDtmask = openDataMaskingEntityManager();
			boolean checkExistingReq = tDMDataMaskingDAO.dtMaskCancelReq(reqId, managerDtmask);
			closeDataMaskingEntityManager(managerDtmask);
			logger.info(MessageConstants.DATA_MASKING_SERVICEIMPL
					+ MessageConstants.DTMASK_CANCEL_REQ + MessageConstants.LOG_INFO_RETURN);
			return checkExistingReq;
		}
		catch (NullPointerException nullPointerEx)
		{
			logger.error(MessageConstants.DATA_MASKING_SERVICEIMPL
					+ MessageConstants.DTMASK_CANCEL_REQ);
			throw new ServiceException(MessageConstants.NULL_POINTER_EXCEPTION, nullPointerEx);
		}
		catch (DAOException daoEx)
		{
			logger.error(MessageConstants.DATA_MASKING_SERVICEIMPL
					+ MessageConstants.DTMASK_CANCEL_REQ);
			throw new ServiceException(daoEx.getErrorCode(), daoEx);
		}
		catch (Exception otherEx)
		{
			logger.error(MessageConstants.DATA_MASKING_SERVICEIMPL
					+ MessageConstants.DTMASK_CANCEL_REQ);
			throw new ServiceException(MessageConstants.SERVICE_EXCEPTION, otherEx);
		}
	}

	@Override
	public boolean getCheckExistingReqYesNo(TdmDataMaskingDTO tdgDataMaskingDTO)
			throws ServiceException
	{
		logger.info(MessageConstants.DATA_MASKING_SERVICEIMPL
				+ MessageConstants.TDM_DT_MSK_DAO_CHECK_EXISTING);
		try
		{
			EntityManager managerDtmask = openDataMaskingEntityManager();
			boolean checkExistingReq = tDMDataMaskingDAO.getCheckExistingReqYesNo(
					tdgDataMaskingDTO.getUserId(), managerDtmask);
			closeDataMaskingEntityManager(managerDtmask);
			logger.info(MessageConstants.DATA_MASKING_SERVICEIMPL
					+ MessageConstants.TDM_DT_MSK_DAO_CHECK_EXISTING
					+ MessageConstants.LOG_INFO_RETURN);
			return checkExistingReq;
		}
		catch (NullPointerException nullPointerEx)
		{
			logger.error(MessageConstants.DATA_MASKING_SERVICEIMPL
					+ MessageConstants.TDM_DT_MSK_DAO_CHECK_EXISTING);
			throw new ServiceException(MessageConstants.NULL_POINTER_EXCEPTION, nullPointerEx);
		}
		catch (DAOException daoEx)
		{
			logger.error(MessageConstants.DATA_MASKING_SERVICEIMPL
					+ MessageConstants.TDM_DT_MSK_DAO_CHECK_EXISTING);
			throw new ServiceException(daoEx.getErrorCode(), daoEx);
		}
		catch (Exception otherEx)
		{
			logger.error(MessageConstants.DATA_MASKING_SERVICEIMPL
					+ MessageConstants.TDM_DT_MSK_DAO_CHECK_EXISTING);
			throw new ServiceException(MessageConstants.SERVICE_EXCEPTION, otherEx);
		}
	}

	@Override
	public boolean deleteRow(String reqType, String rowId) throws ServiceException
	{
		logger.info(MessageConstants.DATA_MASKING_SERVICEIMPL + MessageConstants.DELETE_ROW);
		try
		{
			EntityManager managerDtmask = openDataMaskingEntityManager();
			boolean checkExistingReq = tDMDataMaskingDAO.deleteRow(reqType, rowId, managerDtmask);
			closeDataMaskingEntityManager(managerDtmask);
			logger.info(MessageConstants.DATA_MASKING_SERVICEIMPL + MessageConstants.DELETE_ROW
					+ MessageConstants.LOG_INFO_RETURN);
			return checkExistingReq;
		}
		catch (NullPointerException nullPointerEx)
		{
			logger.error(MessageConstants.DATA_MASKING_SERVICEIMPL + MessageConstants.DELETE_ROW);
			throw new ServiceException(MessageConstants.NULL_POINTER_EXCEPTION, nullPointerEx);
		}
		catch (DAOException daoEx)
		{
			logger.error(MessageConstants.DATA_MASKING_SERVICEIMPL
					+ MessageConstants.TDM_DT_MSK_DAO_CHECK_EXISTING);
			throw new ServiceException(daoEx.getErrorCode(), daoEx);
		}
		catch (Exception otherEx)
		{
			logger.error(MessageConstants.DATA_MASKING_SERVICEIMPL + MessageConstants.DELETE_ROW);
			throw new ServiceException(MessageConstants.SERVICE_EXCEPTION, otherEx);
		}
	}
}
