/*---------------------------------------------------------------------------------------
 * Object Name: TDGDataMaskingDAOImpl.Java
 * 
 * --------------------------------------------------------------------------------------
 *
 * Copyright: 2015 Capgemini Financial Services
 *---------------------------------------------------------------------------------------*/

package com.tesda.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.tesda.constants.AppConstant;
import com.tesda.constants.MessageConstants;
import com.tesda.constants.TDMConstants;
import com.tesda.constants.TDMExceptionCode;
import com.tesda.dao.TDMDataMaskingDAO;
import com.tesda.exception.DAOException;
import com.tesda.model.DO.ReqChDO;
import com.tesda.model.DO.RequestorDO;
import com.tesda.model.DO.TdmOnboardReqDO;
import com.tesda.model.DO.TdmUserDO;
import com.tesda.model.DTO.TdmDataMaskingDTO;
import com.tesda.model.DTO.TdmOnBoardReqDTO;

/**
 * Transaction class used between the TDGDataMaskingServiceImpl class and Data
 * base. Used to save the masking Request details. Retrieves existing masking
 * request details. Modifies the existing masking request details. Cancels the
 * created masking request.
 */

@Component(TDMConstants.DATAMSKING_DAOIMPL)
public class TDMDataMaskingDAOImpl implements TDMDataMaskingDAO
{

	private static final Logger logger = LoggerFactory.getLogger(TDMDataMaskingDAOImpl.class);

	@Override
	public String saveMaskingData(RequestorDO requestorDO, EntityManager managerDtmask)
			throws DAOException
	{
		try
		{
			logger.info(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.SAVE_MASKING_DATA);
			managerDtmask.getTransaction().begin();
			requestorDO = managerDtmask.merge(requestorDO);
			managerDtmask.getTransaction().commit();
			logger.info(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.SAVE_MASKING_DATA
					+ MessageConstants.LOG_INFO_RETURN);
			return requestorDO.getId();
		}
		catch (NullPointerException ex)
		{
			logger.error(
					MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.SAVE_MASKING_DATA, ex);
			throw new DAOException(TDMExceptionCode.NULL_POINTR, ex);
		}
		catch (NoResultException ex)
		{
			logger.error(
					MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.SAVE_MASKING_DATA, ex);
			throw new DAOException(TDMExceptionCode.NORESULT, ex);
		}
		catch (IllegalStateException ex)
		{
			logger.error(
					MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.SAVE_MASKING_DATA, ex);
			throw new DAOException(TDMExceptionCode.ILLEGAL_STATE, ex);
		}
		catch (IllegalArgumentException ex)
		{
			logger.error(
					MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.SAVE_MASKING_DATA, ex);
			throw new DAOException(TDMExceptionCode.ILLEGAL_ARGUMENT, ex);
		}
		catch (Exception ex)
		{
			logger.error(
					MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.SAVE_MASKING_DATA, ex);
			throw new DAOException(TDMExceptionCode.EXCEPTION, ex);
		}
	}

	@Override
	public RequestorDO getSaveReqDtls(RequestorDO requestorDO, EntityManager managerDtmask)
			throws DAOException
	{
		try
		{
			logger.info(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.GET_SAVE_REQDTLS);
			if (null != managerDtmask)
			{
				managerDtmask.getTransaction().begin();
				requestorDO = managerDtmask.merge(requestorDO);
				managerDtmask.getTransaction().commit();
			}
			logger.info(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.GET_SAVE_REQDTLS
					+ MessageConstants.LOG_INFO_RETURN);
			return requestorDO;
		}
		catch (IllegalStateException illegalStateEx)
		{
			illegalStateEx.printStackTrace();
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.GET_SAVE_REQDTLS,
					illegalStateEx);
			throw new DAOException(TDMExceptionCode.ILLEGAL_STATE, illegalStateEx);
		}
		catch (IllegalArgumentException illegalArgEx)
		{
			illegalArgEx.printStackTrace();
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.GET_SAVE_REQDTLS,
					illegalArgEx);
			throw new DAOException(TDMExceptionCode.ILLEGAL_ARGUMENT, illegalArgEx);
		}
		catch (NullPointerException nullPointerEx)
		{
			nullPointerEx.printStackTrace();
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.GET_SAVE_REQDTLS,
					nullPointerEx);
			throw new DAOException(TDMExceptionCode.NULL_POINTR, nullPointerEx);
		}
		catch (Exception otherEx)
		{
			otherEx.printStackTrace();
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.GET_SAVE_REQDTLS,
					otherEx);
			throw new DAOException(TDMExceptionCode.EXCEPTION, otherEx);
		}
	}

	@Override
	public RequestorDO getSavedDtls(String reqId, EntityManager managerDtmask) throws DAOException
	{
		logger.info(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.GET_SAVED_DTLS);
		RequestorDO requestorDO = null;
		try
		{
			if (0 < (Long.parseLong(managerDtmask
					.createQuery("SELECT COUNT(*) FROM RequestorDO r WHERE r.id ='" + reqId + "'")
					.getSingleResult().toString())))
			{
				requestorDO = (RequestorDO) managerDtmask.createQuery(
						"SELECT r FROM RequestorDO r WHERE r.id ='" + reqId + "'")
						.getSingleResult();
			}
			logger.info(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.GET_SAVED_DTLS
					+ MessageConstants.LOG_INFO_RETURN);
			return requestorDO;
		}
		catch (IllegalStateException illegalStateEx)
		{
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.GET_SAVED_DTLS,
					illegalStateEx);
			throw new DAOException(TDMExceptionCode.ILLEGAL_STATE, illegalStateEx);
		}
		catch (IllegalArgumentException illegalArgEx)
		{
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.GET_SAVED_DTLS,
					illegalArgEx);
			throw new DAOException(TDMExceptionCode.ILLEGAL_ARGUMENT, illegalArgEx);
		}
		catch (NullPointerException nullPointerEx)
		{
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.GET_SAVED_DTLS,
					nullPointerEx);
			throw new DAOException(TDMExceptionCode.NULL_POINTR, nullPointerEx);
		}
		catch (Exception otherEx)
		{
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.GET_SAVED_DTLS,
					otherEx);
			throw new DAOException(TDMExceptionCode.EXCEPTION, otherEx);
		}
	}

	@SuppressWarnings(TDMConstants.UNCHECKED)
	@Override
	public List<RequestorDO> getAllDtMaskRequestedRecord(String userId, EntityManager managerDtmask)
			throws DAOException
	{
		try
		{
			logger.info(MessageConstants.DATA_MASKING_DAOIMPL
					+ MessageConstants.GETALL_DTMASK_REQREC);
			List<RequestorDO> list = managerDtmask.createQuery(
					"SELECT r FROM RequestorDO r where r.userName='" + userId + "'")
					.getResultList();
			logger.info(MessageConstants.DATA_MASKING_DAOIMPL
					+ MessageConstants.GETALL_DTMASK_REQREC + MessageConstants.LOG_INFO_RETURN);
			return list;
		}
		catch (NullPointerException ex)
		{
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL
					+ MessageConstants.GETALL_DTMASK_REQREC, ex);
			throw new DAOException(TDMExceptionCode.NULL_POINTR, ex);
		}
		catch (NoResultException ex)
		{
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL
					+ MessageConstants.GETALL_DTMASK_REQREC, ex);
			throw new DAOException(TDMExceptionCode.NORESULT, ex);
		}
		catch (IllegalStateException ex)
		{
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL
					+ MessageConstants.GETALL_DTMASK_REQREC, ex);
			throw new DAOException(TDMExceptionCode.ILLEGAL_STATE, ex);
		}
		catch (IllegalArgumentException ex)
		{
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL
					+ MessageConstants.GETALL_DTMASK_REQREC, ex);
			throw new DAOException(TDMExceptionCode.ILLEGAL_ARGUMENT, ex);
		}
		catch (Exception ex)
		{
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL
					+ MessageConstants.GETALL_DTMASK_REQREC, ex);
			throw new DAOException(TDMExceptionCode.EXCEPTION, ex);
		}
	}

	@SuppressWarnings(TDMConstants.UNCHECKED)
	@Override
	public Map<RequestorDO, List<ReqChDO>> getDtMaskRequestedRecord(EntityManager managerDtmask,
			String userId, String reqId) throws DAOException
	{
		try
		{
			logger.info(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.GET_DTMASK_REQREC);
			RequestorDO requestorDO = (RequestorDO) managerDtmask.createQuery(
					"SELECT r FROM RequestorDO r WHERE r.id ='" + reqId + "'").getSingleResult();
			List<ReqChDO> reqChDOList = managerDtmask.createQuery(
					"SELECT r FROM ReqChDO r WHERE r.pId ='" + reqId + "'").getResultList();
			Map<RequestorDO, List<ReqChDO>> map = new HashMap<RequestorDO, List<ReqChDO>>();
			map.put(requestorDO, reqChDOList);
			logger.info(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.GET_DTMASK_REQREC
					+ MessageConstants.LOG_INFO_RETURN);
			return map;
		}
		catch (NullPointerException ex)
		{
			logger.error(
					MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.GET_DTMASK_REQREC, ex);
			throw new DAOException(TDMExceptionCode.NULL_POINTR, ex);
		}
		catch (IllegalStateException ex)
		{
			logger.error(
					MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.GET_DTMASK_REQREC, ex);
			throw new DAOException(TDMExceptionCode.ILLEGAL_STATE, ex);
		}
		catch (NoResultException ex)
		{
			logger.error(
					MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.GET_DTMASK_REQREC, ex);
			throw new DAOException(TDMExceptionCode.NORESULT, ex);
		}
		catch (IllegalArgumentException ex)
		{
			logger.error(
					MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.GET_DTMASK_REQREC, ex);
			throw new DAOException(TDMExceptionCode.ILLEGAL_ARGUMENT, ex);
		}
		catch (Exception ex)
		{
			logger.error(
					MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.GET_DTMASK_REQREC, ex);
			throw new DAOException(TDMExceptionCode.EXCEPTION, ex);
		}
	}

	@SuppressWarnings(TDMConstants.UNCHECKED)
	@Override
	public List<RequestorDO> searchDataMaskingRecords(String role, int offSet, int recordsperpage,
			boolean pageNationOnOffFlag, String userId, String type, EntityManager managerDtmask)
			throws DAOException
	{
		try
		{
			logger.info(MessageConstants.DATA_MASKING_DAOIMPL
					+ MessageConstants.SEARCH_MASKING_RECORDS);
			String query = null;

			if (TDMConstants.ROLE_ADMIN.equalsIgnoreCase(role))
			{
				if (type.equalsIgnoreCase(TDMConstants.FR))
				{
					query = "SELECT r FROM RequestorDO r where r.vno=0 Order By r.id desc";
				}
				else if (type.equalsIgnoreCase("CR"))
				{
					query = "SELECT r FROM RequestorDO r where r.vno != 0 Order By r.id desc";
				}
			}
			else
			{
				if (type.equalsIgnoreCase(TDMConstants.FR))
				{
					query = "SELECT r FROM RequestorDO r where r.userName='" + userId
							+ "' AND r.vno=0 Order By r.id desc";
				}
				else if (type.equalsIgnoreCase("CR"))
				{
					query = "SELECT r FROM RequestorDO r where r.userName='" + userId
							+ "'AND r.vno != 0 Order By r.id desc";
				}
			}

			List<RequestorDO> list = managerDtmask.createQuery(query).setFirstResult(offSet)
					.setMaxResults(recordsperpage).getResultList();
			logger.info(MessageConstants.DATA_MASKING_DAOIMPL
					+ MessageConstants.SEARCH_MASKING_RECORDS + MessageConstants.LOG_INFO_RETURN);
			return list;
		}
		catch (NullPointerException ex)
		{
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL
					+ MessageConstants.SEARCH_MASKING_RECORDS, ex);
			throw new DAOException(TDMExceptionCode.NULL_POINTR, ex);
		}
		catch (NoResultException ex)
		{
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL
					+ MessageConstants.SEARCH_MASKING_RECORDS, ex);
			throw new DAOException(TDMExceptionCode.NORESULT, ex);
		}
		catch (IllegalStateException ex)
		{
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL
					+ MessageConstants.SEARCH_MASKING_RECORDS, ex);
			throw new DAOException(TDMExceptionCode.ILLEGAL_STATE, ex);
		}
		catch (IllegalArgumentException ex)
		{
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL
					+ MessageConstants.SEARCH_MASKING_RECORDS, ex);
			throw new DAOException(TDMExceptionCode.ILLEGAL_ARGUMENT, ex);
		}
		catch (Exception ex)
		{
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL
					+ MessageConstants.SEARCH_MASKING_RECORDS, ex);
			throw new DAOException(TDMExceptionCode.EXCEPTION, ex);
		}
	}

	@Override
	public Long getDataMaskingRecordsCount(String role, String userId, String type,
			EntityManager managerDtmask) throws DAOException
	{
		try
		{
			logger.info(MessageConstants.DATA_MASKING_DAOIMPL
					+ MessageConstants.GET_MSKING_REC_COUNT);
			String query = null;
			if (TDMConstants.ROLE_ADMIN.equalsIgnoreCase(role))
			{
				if (type.equalsIgnoreCase(TDMConstants.FR))
				{
					query = "SELECT COUNT(*) FROM RequestorDO p where p.vno = 0";
				}
				else if (type.equalsIgnoreCase("CR"))
				{
					query = "SELECT COUNT(*) FROM RequestorDO p where p.vno != 0";
				}
			}
			else
			{
				if (type.equalsIgnoreCase(TDMConstants.FR))
				{
					query = "SELECT COUNT(*) FROM RequestorDO p where p.userName='" + userId
							+ "' AND p.vno = 0";
				}
				else if (type.equalsIgnoreCase("CR"))
				{
					query = "SELECT COUNT(*) FROM RequestorDO p where p.userName='" + userId
							+ "' AND p.vno != 0";
				}
			}

			Long list = (Long) managerDtmask.createQuery(query).getSingleResult();
			logger.info(MessageConstants.DATA_MASKING_DAOIMPL
					+ MessageConstants.GET_MSKING_REC_COUNT + MessageConstants.LOG_INFO_RETURN);
			return list;
		}
		catch (NullPointerException ex)
		{
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL
					+ MessageConstants.GET_MSKING_REC_COUNT, ex);
			throw new DAOException(TDMExceptionCode.NULL_POINTR, ex);
		}
		catch (NoResultException ex)
		{
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL
					+ MessageConstants.GET_MSKING_REC_COUNT, ex);
			throw new DAOException(TDMExceptionCode.NORESULT, ex);
		}
		catch (IllegalStateException ex)
		{
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL
					+ MessageConstants.GET_MSKING_REC_COUNT, ex);
			throw new DAOException(TDMExceptionCode.ILLEGAL_STATE, ex);
		}
		catch (IllegalArgumentException ex)
		{
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL
					+ MessageConstants.GET_MSKING_REC_COUNT, ex);
			throw new DAOException(TDMExceptionCode.ILLEGAL_ARGUMENT, ex);
		}
		catch (Exception ex)
		{
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL
					+ MessageConstants.GET_MSKING_REC_COUNT, ex);
			throw new DAOException(TDMExceptionCode.EXCEPTION, ex);
		}
	}

	@Override
	public TdmDataMaskingDTO getUserDetails(EntityManager managerUser, String userId)
			throws DAOException
	{
		TdmDataMaskingDTO tdgDataMaskingDTO = new TdmDataMaskingDTO();
		try
		{
			logger.info(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.GET_USER_DETAILS);
			TdmUserDO userDO = (TdmUserDO) managerUser.createQuery(
					"SELECT p FROM TdmUserDO p where p.userId='" + userId + "'").getSingleResult();
			tdgDataMaskingDTO.setUserName(userDO.getUsername());
			tdgDataMaskingDTO.setEmailId(userDO.getEmailId());
			tdgDataMaskingDTO.setPhoneNo(userDO.getMobileNo());
			logger.info(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.GET_USER_DETAILS
					+ MessageConstants.LOG_INFO_RETURN);
			return tdgDataMaskingDTO;
		}
		catch (NullPointerException ex)
		{
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.GET_USER_DETAILS,
					ex);
			throw new DAOException(TDMExceptionCode.NULL_POINTR, ex);
		}
		catch (NoResultException ex)
		{
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.GET_USER_DETAILS,
					ex);
			throw new DAOException(TDMExceptionCode.NORESULT, ex);
		}
		catch (IllegalStateException ex)
		{
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.GET_USER_DETAILS,
					ex);
			throw new DAOException(TDMExceptionCode.ILLEGAL_STATE, ex);
		}
		catch (IllegalArgumentException ex)
		{
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.GET_USER_DETAILS,
					ex);
			throw new DAOException(TDMExceptionCode.ILLEGAL_ARGUMENT, ex);
		}
		catch (Exception ex)
		{
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.GET_USER_DETAILS,
					ex);
			throw new DAOException(TDMExceptionCode.EXCEPTION, ex);
		}

	}

	@Override
	public TdmOnBoardReqDTO getUserDetailsOnboard(String userId, EntityManager managerUser,
			TdmOnBoardReqDTO tdmOnBoardReqDTO) throws DAOException
	{
		try
		{
			logger.info(MessageConstants.DATA_MASKING_DAOIMPL
					+ MessageConstants.GET_USERDTLS_ONBOARD);
			TdmUserDO userDO = (TdmUserDO) managerUser.createQuery(
					"SELECT p FROM TdmUserDO p where p.userId='" + tdmOnBoardReqDTO.getUserId()
							+ "'").getSingleResult();
			tdmOnBoardReqDTO.setUserName(userDO.getUsername());
			tdmOnBoardReqDTO.setEmailId(userDO.getEmailId());
			tdmOnBoardReqDTO.setPhoneNo(userDO.getMobileNo());
			logger.info(MessageConstants.DATA_MASKING_DAOIMPL
					+ MessageConstants.GET_USERDTLS_ONBOARD + MessageConstants.LOG_INFO_RETURN);
			return tdmOnBoardReqDTO;
		}
		catch (NullPointerException ex)
		{
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL
					+ MessageConstants.GET_USERDTLS_ONBOARD, ex);
			throw new DAOException(TDMExceptionCode.NULL_POINTR, ex);
		}
		catch (NoResultException ex)
		{
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL
					+ MessageConstants.GET_USERDTLS_ONBOARD, ex);
			throw new DAOException(TDMExceptionCode.NORESULT, ex);
		}
		catch (IllegalStateException ex)
		{
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL
					+ MessageConstants.GET_USERDTLS_ONBOARD, ex);
			throw new DAOException(TDMExceptionCode.ILLEGAL_STATE, ex);
		}
		catch (IllegalArgumentException ex)
		{
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL
					+ MessageConstants.GET_USERDTLS_ONBOARD, ex);
			throw new DAOException(TDMExceptionCode.ILLEGAL_ARGUMENT, ex);
		}
		catch (Exception ex)
		{
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL
					+ MessageConstants.GET_USERDTLS_ONBOARD, ex);
			throw new DAOException(TDMExceptionCode.EXCEPTION, ex);
		}
	}

	@Override
	public TdmOnboardReqDO getSaveOnboardingReq(EntityManager managerDtmask,
			TdmOnboardReqDO tdmOnboardReqDO) throws DAOException
	{
		try
		{
			logger.info(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.GETSAVE_ONBRD_REQ);
			managerDtmask.getTransaction().begin();
			managerDtmask.merge(tdmOnboardReqDO);
			managerDtmask.getTransaction().commit();
			logger.info(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.GETSAVE_ONBRD_REQ
					+ MessageConstants.LOG_INFO_RETURN);
			return tdmOnboardReqDO;
		}
		catch (NullPointerException ex)
		{
			logger.error(
					MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.GETSAVE_ONBRD_REQ, ex);
			throw new DAOException(TDMExceptionCode.NULL_POINTR, ex);
		}
		catch (NoResultException ex)
		{
			logger.error(
					MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.GETSAVE_ONBRD_REQ, ex);
			throw new DAOException(TDMExceptionCode.NORESULT, ex);
		}
		catch (IllegalStateException ex)
		{
			logger.error(
					MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.GETSAVE_ONBRD_REQ, ex);
			throw new DAOException(TDMExceptionCode.ILLEGAL_STATE, ex);
		}
		catch (IllegalArgumentException ex)
		{
			logger.error(
					MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.GETSAVE_ONBRD_REQ, ex);
			throw new DAOException(TDMExceptionCode.ILLEGAL_ARGUMENT, ex);
		}
		catch (Exception ex)
		{
			logger.error(
					MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.GETSAVE_ONBRD_REQ, ex);
			throw new DAOException(TDMExceptionCode.EXCEPTION, ex);
		}
	}

	@Override
	public TdmOnboardReqDO getEditableDetails(EntityManager managerDtmask, String reqId)
			throws DAOException
	{
		try
		{
			logger.info(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.GET_EDITABLE_DTLS);
			TdmOnboardReqDO tdmOnboardReqDO = (TdmOnboardReqDO) managerDtmask.createQuery(
					"SELECT p FROM TdmOnboardReqDO p where p.onboardReqId ='" + reqId + "'")
					.getSingleResult();
			logger.info(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.GET_EDITABLE_DTLS
					+ MessageConstants.LOG_INFO_RETURN);
			return tdmOnboardReqDO;
		}
		catch (NullPointerException ex)
		{
			logger.error(
					MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.GET_EDITABLE_DTLS, ex);
			throw new DAOException(TDMExceptionCode.NULL_POINTR, ex);
		}
		catch (NoResultException ex)
		{
			logger.error(
					MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.GET_EDITABLE_DTLS, ex);
			throw new DAOException(TDMExceptionCode.NORESULT, ex);
		}
		catch (IllegalStateException ex)
		{
			logger.error(
					MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.GET_EDITABLE_DTLS, ex);
			throw new DAOException(TDMExceptionCode.ILLEGAL_STATE, ex);
		}
		catch (IllegalArgumentException ex)
		{
			logger.error(
					MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.GET_EDITABLE_DTLS, ex);
			throw new DAOException(TDMExceptionCode.ILLEGAL_ARGUMENT, ex);
		}
		catch (Exception ex)
		{
			logger.error(
					MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.GET_EDITABLE_DTLS, ex);
			throw new DAOException(TDMExceptionCode.EXCEPTION, ex);
		}

	}

	@SuppressWarnings(TDMConstants.UNCHECKED)
	@Override
	public List<String> getReqIdList(String userId, EntityManager managerDtmask, String reqIdtoken)
			throws DAOException
	{
		List<String> reqIds = null;
		try
		{
			logger.info(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.GET_REQID_LIST);
			if (reqIdtoken.toUpperCase().contains(TDMConstants.TR))
			{
				reqIds = managerDtmask.createQuery(
						"SELECT p.onboardReqId FROM TdmOnboardReqDO p where p.onboardReqId like'%"
								+ reqIdtoken + "%' AND P.actionBy='" + userId + "'")
						.getResultList();
			}
			else if (reqIdtoken.toUpperCase().contains(TDMConstants.MR))
			{
				reqIds = managerDtmask.createQuery(
						"SELECT p.id FROM RequestorDO p where p.id like'%" + reqIdtoken
								+ "%' AND p.userName='" + userId + "'").getResultList();
			}
			logger.info(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.GET_REQID_LIST
					+ MessageConstants.LOG_INFO_RETURN);
			return reqIds;
		}
		catch (NullPointerException ex)
		{
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.GET_REQID_LIST,
					ex);
			throw new DAOException(TDMExceptionCode.NULL_POINTR, ex);
		}
		catch (NoResultException ex)
		{
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.GET_REQID_LIST,
					ex);
			throw new DAOException(TDMExceptionCode.NORESULT, ex);
		}
		catch (IllegalStateException ex)
		{
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.GET_REQID_LIST,
					ex);
			throw new DAOException(TDMExceptionCode.ILLEGAL_STATE, ex);
		}
		catch (IllegalArgumentException ex)
		{
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.GET_REQID_LIST,
					ex);
			throw new DAOException(TDMExceptionCode.ILLEGAL_ARGUMENT, ex);
		}
		catch (Exception ex)
		{
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.GET_REQID_LIST,
					ex);
			throw new DAOException(TDMExceptionCode.EXCEPTION, ex);
		}

	}

	@Override
	public Long getOnBoardingRecordsCount(String role, String userId, String type,
			EntityManager managerDtmask) throws DAOException
	{
		try
		{
			logger.info(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.GET_ONBRD_RECCOUNT);
			String query = null;
			if (TDMConstants.ROLE_ADMIN.equalsIgnoreCase(role))
			{
				if (type.equalsIgnoreCase(TDMConstants.FR))
				{
					query = "SELECT COUNT(*) FROM TdmOnboardReqDO p where p.vno = 0";
				}
				else if (type.equalsIgnoreCase(TDMConstants.CR))
				{
					query = "SELECT COUNT(*) FROM TdmOnboardReqDO p where p.vno != 0";
				}
			}
			else
			{
				if (type.equalsIgnoreCase(TDMConstants.FR))
				{
					query = "SELECT COUNT(*) FROM TdmOnboardReqDO p where p.actionBy='" + userId
							+ "' AND p.vno = 0";
				}
				else if (type.equalsIgnoreCase(TDMConstants.CR))
				{
					query = "SELECT COUNT(*) FROM TdmOnboardReqDO p where p.actionBy='" + userId
							+ "' AND p.vno != 0";
				}
			}

			Long list = (Long) managerDtmask.createQuery(query).getSingleResult();
			logger.info(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.GET_ONBRD_RECCOUNT
					+ MessageConstants.LOG_INFO_RETURN);
			return list;
		}
		catch (NullPointerException ex)
		{
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL
					+ MessageConstants.GET_ONBRD_RECCOUNT, ex);
			throw new DAOException(TDMExceptionCode.NULL_POINTR, ex);
		}
		catch (NoResultException ex)
		{
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL
					+ MessageConstants.GET_ONBRD_RECCOUNT, ex);
			throw new DAOException(TDMExceptionCode.NORESULT, ex);
		}
		catch (IllegalStateException ex)
		{
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL
					+ MessageConstants.GET_ONBRD_RECCOUNT, ex);
			throw new DAOException(TDMExceptionCode.ILLEGAL_STATE, ex);
		}
		catch (IllegalArgumentException ex)
		{
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL
					+ MessageConstants.GET_ONBRD_RECCOUNT, ex);
			throw new DAOException(TDMExceptionCode.ILLEGAL_ARGUMENT, ex);
		}
		catch (Exception ex)
		{
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL
					+ MessageConstants.GET_ONBRD_RECCOUNT, ex);
			throw new DAOException(TDMExceptionCode.EXCEPTION, ex);
		}
	}

	@Override
	public List<TdmOnboardReqDO> searchOnBoardingRecords(String role, int offSet,
			int recordsperpage, boolean pageNationOnOffFlag, String userId, String type,
			EntityManager managerDtmask) throws DAOException
	{
		try
		{
			logger.info(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.SEARCH_ONBRD_REC);
			String query = null;
			if (TDMConstants.ROLE_ADMIN.equalsIgnoreCase(role))
			{
				if (type.equalsIgnoreCase(TDMConstants.FR))
				{
					query = "SELECT p FROM TdmOnboardReqDO p where p.vno = 0 Order By p.onboardReqId desc";
				}
				else if (type.equalsIgnoreCase(TDMConstants.CR))
				{
					query = "SELECT p FROM TdmOnboardReqDO p where p.vno != 0 Order By p.onboardReqId desc";
				}
			}
			else
			{
				if (type.equalsIgnoreCase(TDMConstants.FR))
				{
					query = "SELECT p FROM TdmOnboardReqDO p where p.actionBy='" + userId
							+ "' AND p.vno = 0 Order By p.onboardReqId desc";
				}
				else if (type.equalsIgnoreCase(TDMConstants.CR))
				{
					query = "SELECT p FROM TdmOnboardReqDO p where p.actionBy='" + userId
							+ "'  AND p.vno != 0 Order By p.onboardReqId desc";
				}
			}

			@SuppressWarnings(TDMConstants.UNCHECKED)
			List<TdmOnboardReqDO> list = managerDtmask.createQuery(query).setFirstResult(offSet)
					.setMaxResults(recordsperpage).getResultList();
			logger.info(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.SEARCH_ONBRD_REC
					+ MessageConstants.LOG_INFO_RETURN);
			return list;
		}
		catch (NullPointerException ex)
		{
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.SEARCH_ONBRD_REC,
					ex);
			throw new DAOException(TDMExceptionCode.NULL_POINTR, ex);
		}
		catch (NoResultException ex)
		{
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.SEARCH_ONBRD_REC,
					ex);
			throw new DAOException(TDMExceptionCode.NORESULT, ex);
		}
		catch (IllegalStateException ex)
		{
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.SEARCH_ONBRD_REC,
					ex);
			throw new DAOException(TDMExceptionCode.ILLEGAL_STATE, ex);
		}
		catch (IllegalArgumentException ex)
		{
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.SEARCH_ONBRD_REC,
					ex);
			throw new DAOException(TDMExceptionCode.ILLEGAL_ARGUMENT, ex);
		}
		catch (Exception ex)
		{
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.SEARCH_ONBRD_REC,
					ex);
			throw new DAOException(TDMExceptionCode.EXCEPTION, ex);
		}
	}

	@Override
	public String onBoardingRecId(EntityManager managerDtmask)
	{
		String seqNO = (String) managerDtmask
				.createNativeQuery(
						"SELECT LPAD ( (NEXT VALUE FOR  SEQ_ONBOARD_REQ_ID), 6, '0' ) ID FROM sysibm.sysdummy1")
				.getSingleResult();
		return seqNO;
	}

	@Override
	public String maskingRecId(EntityManager managerDtmask)
	{
		String seqNO = (String) managerDtmask
				.createNativeQuery(
						"SELECT LPAD ( (NEXT VALUE FOR  SEQ_MASK_REQ_ID), 6, '0' ) ID FROM sysibm.sysdummy1")
				.getSingleResult();
		return seqNO;
	}

	@Override
	public boolean cancelOnBoardingReq(EntityManager managerDtmask, String reqId)
			throws DAOException
	{
		try
		{
			logger.info(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.CANCEL_ONBRD_REQ);
			Long count = 0L;
			if (!reqId.contains(TDMConstants.CR))
			{
				count = (Long) managerDtmask.createQuery(
						"SELECT COUNT(*) FROM TdmOnboardReqDO p where p.onboardReqId like '%"
								+ reqId + "%' AND p.status='Submitted'").getSingleResult();
				if (count > 1)
				{
					return false;
				}
			}

			TdmOnboardReqDO reqDo = (TdmOnboardReqDO) managerDtmask.createQuery(
					"SELECT p FROM TdmOnboardReqDO p where p.onboardReqId = '" + reqId
							+ "' AND p.status='Submitted'").getSingleResult();
			reqDo.setStatus(TDMConstants.STATUS_CANCELLED);
			managerDtmask.getTransaction().begin();
			reqDo = managerDtmask.merge(reqDo);
			managerDtmask.getTransaction().commit();
			logger.info(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.CANCEL_ONBRD_REQ
					+ MessageConstants.LOG_INFO_RETURN);
			return true;
		}
		catch (NullPointerException ex)
		{
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.CANCEL_ONBRD_REQ,
					ex);
			throw new DAOException(TDMExceptionCode.NULL_POINTR, ex);
		}
		catch (NoResultException ex)
		{
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.CANCEL_ONBRD_REQ,
					ex);
			throw new DAOException(TDMExceptionCode.NORESULT, ex);
		}
		catch (IllegalStateException ex)
		{
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.CANCEL_ONBRD_REQ,
					ex);
			throw new DAOException(TDMExceptionCode.ILLEGAL_STATE, ex);
		}
		catch (IllegalArgumentException ex)
		{
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.CANCEL_ONBRD_REQ,
					ex);
			throw new DAOException(TDMExceptionCode.ILLEGAL_ARGUMENT, ex);
		}
		catch (Exception ex)
		{
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.CANCEL_ONBRD_REQ,
					ex);
			throw new DAOException(TDMExceptionCode.EXCEPTION, ex);
		}
	}

	@Override
	public List<RequestorDO> getSavedDtlsforExport(String role, String userId, String type,
			EntityManager managerDtmask) throws DAOException
	{
		logger.info(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.GET_DTLS_TOEXPORT);
		String query = null;
		try
		{
			if (TDMConstants.ROLE_ADMIN.equalsIgnoreCase(role))
			{
				if (type.equalsIgnoreCase(TDMConstants.FR))
				{
					query = "SELECT p FROM RequestorDO p where  p.vno = 0";
				}
				else if (type.equalsIgnoreCase(TDMConstants.CR))
				{
					query = "SELECT p FROM RequestorDO p where  p.vno != 0";
				}
			}
			else
			{
				if (type.equalsIgnoreCase(TDMConstants.FR))
				{
					query = "SELECT p FROM RequestorDO p where p.userName='" + userId
							+ "' AND p.vno = 0";
				}
				else if (type.equalsIgnoreCase(TDMConstants.CR))
				{
					query = "SELECT p FROM RequestorDO p where p.userName='" + userId
							+ "' AND p.vno != 0";
				}
			}

			@SuppressWarnings(TDMConstants.UNCHECKED)
			List<RequestorDO> list = managerDtmask.createQuery(query).getResultList();
			logger.info(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.GET_DTLS_TOEXPORT
					+ MessageConstants.LOG_INFO_RETURN);
			return list;
		}
		catch (IllegalStateException illegalStateEx)
		{
			throw new DAOException(MessageConstants.DATA_MASKING_DAOIMPL
					+ MessageConstants.GET_DTLS_TOEXPORT, illegalStateEx);
		}
		catch (IllegalArgumentException illegalArgEx)
		{
			throw new DAOException(MessageConstants.DATA_MASKING_DAOIMPL
					+ MessageConstants.GET_DTLS_TOEXPORT, illegalArgEx);
		}
		catch (NullPointerException nullPointerEx)
		{
			throw new DAOException(MessageConstants.DATA_MASKING_DAOIMPL
					+ MessageConstants.GET_DTLS_TOEXPORT, nullPointerEx);
		}
		catch (Exception otherEx)
		{
			throw new DAOException(MessageConstants.DATA_MASKING_DAOIMPL
					+ MessageConstants.GET_DTLS_TOEXPORT, otherEx);
		}
	}

	@Override
	public boolean cancelMaskingReq(EntityManager managerDtmask, String reqId) throws DAOException
	{
		try
		{
			logger.info(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.CANCEL_MSKING_REQ);
			Long count = 0L;
			if (!reqId.contains(TDMConstants.CR))
			{
				count = (Long) managerDtmask.createQuery(
						"SELECT COUNT(*) FROM RequestorDO p where p.id like '%" + reqId
								+ "%' AND p.status='Submitted'").getSingleResult();
				if (count > 1)
				{
					return false;
				}
			}

			RequestorDO reqDo = (RequestorDO) managerDtmask.createQuery(
					"SELECT p FROM RequestorDO p where p.id = '" + reqId
							+ "' AND p.status='Submitted'").getSingleResult();
			reqDo.setStatus(TDMConstants.STATUS_CANCELLED);
			managerDtmask.getTransaction().begin();
			reqDo = managerDtmask.merge(reqDo);
			managerDtmask.getTransaction().commit();
			logger.info(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.CANCEL_MSKING_REQ
					+ MessageConstants.LOG_INFO_RETURN);
			return true;
		}
		catch (NullPointerException ex)
		{
			logger.error(
					MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.CANCEL_MSKING_REQ, ex);
			throw new DAOException(TDMExceptionCode.NULL_POINTR, ex);
		}
		catch (NoResultException ex)
		{
			logger.error(
					MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.CANCEL_MSKING_REQ, ex);
			throw new DAOException(TDMExceptionCode.NORESULT, ex);
		}
		catch (IllegalStateException ex)
		{
			logger.error(
					MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.CANCEL_MSKING_REQ, ex);
			throw new DAOException(TDMExceptionCode.ILLEGAL_STATE, ex);
		}
		catch (IllegalArgumentException ex)
		{
			logger.error(
					MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.CANCEL_MSKING_REQ, ex);
			throw new DAOException(TDMExceptionCode.ILLEGAL_ARGUMENT, ex);
		}
		catch (Exception ex)
		{
			logger.error(
					MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.CANCEL_MSKING_REQ, ex);
			throw new DAOException(TDMExceptionCode.EXCEPTION, ex);
		}
	}

	@Override
	public boolean getCheckExistingReqYesNo(String userId, EntityManager managerDtmask)
			throws DAOException
	{
		logger.info(MessageConstants.DATA_MASKING_DAOIMPL
				+ MessageConstants.TDM_DT_MSK_DAO_CHECK_EXISTING);
		boolean checkExistingReq = false;
		try
		{
			if (0 < Long.parseLong(managerDtmask
					.createQuery(
							"SELECT COUNT(*) FROM RequestorDO r WHERE r.status='"
									+ AppConstant.OPEN + "' AND r.name ='" + userId
									+ "' AND r.id NOT LIKE '%CR%'").getSingleResult().toString()))
			{
				checkExistingReq = true;
			}
			else
			{
				checkExistingReq = false;
			}
			logger.info(MessageConstants.DATA_MASKING_DAOIMPL
					+ MessageConstants.TDM_DT_MSK_DAO_CHECK_EXISTING
					+ MessageConstants.LOG_INFO_RETURN);
			return checkExistingReq;
		}
		catch (IllegalStateException illegalStateEx)
		{
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL
					+ MessageConstants.TDM_DT_MSK_DAO_CHECK_EXISTING);
			throw new DAOException(MessageConstants.NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION,
					illegalStateEx);
		}
		catch (IllegalArgumentException illegalArgEx)
		{
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL
					+ MessageConstants.TDM_DT_MSK_DAO_CHECK_EXISTING);
			throw new DAOException(MessageConstants.INVALID_QUERY_EXCEPTION, illegalArgEx);
		}
		catch (NullPointerException nullPointerEx)
		{
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL
					+ MessageConstants.TDM_DT_MSK_DAO_CHECK_EXISTING);
			throw new DAOException(MessageConstants.NULL_POINTER_EXCEPTION, nullPointerEx);
		}
		catch (Exception otherEx)
		{
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL
					+ MessageConstants.TDM_DT_MSK_DAO_CHECK_EXISTING);
			throw new DAOException(MessageConstants.DATABASE_EXCEPTION, otherEx);
		}
	}

	@Override
	public boolean dtMaskCancelReq(String reqId, EntityManager managerDtmask) throws DAOException
	{
		logger.info(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.DTMASK_CANCEL_REQ
				+ MessageConstants.LOG_INFO_PARAMS_NO);
		boolean checkExistingReq = false;
		try
		{
			managerDtmask.getTransaction().begin();
			int updateCount = managerDtmask.createQuery(
					"UPDATE RequestorDO r SET r.status='" + AppConstant.CANCEL + "' WHERE r.id ='"
							+ reqId + "'").executeUpdate();
			managerDtmask.getTransaction().commit();
			checkExistingReq = true;
			logger.info(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.DTMASK_CANCEL_REQ
					+ MessageConstants.LOG_INFO_RETURN + updateCount);
			return checkExistingReq;
		}
		catch (IllegalStateException illegalStateEx)
		{
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.DTMASK_CANCEL_REQ);
			throw new DAOException(MessageConstants.NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION,
					illegalStateEx);
		}
		catch (IllegalArgumentException illegalArgEx)
		{
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.DTMASK_CANCEL_REQ);
			throw new DAOException(MessageConstants.INVALID_QUERY_EXCEPTION, illegalArgEx);
		}
		catch (NullPointerException nullPointerEx)
		{
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.DTMASK_CANCEL_REQ);
			throw new DAOException(MessageConstants.NULL_POINTER_EXCEPTION, nullPointerEx);
		}
		catch (Exception otherEx)
		{
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.DTMASK_CANCEL_REQ);
			throw new DAOException(MessageConstants.DATABASE_EXCEPTION, otherEx);
		}
	}

	@Override
	public boolean deleteRow(String type, String Id, EntityManager managerDtmask)
			throws DAOException
	{
		logger.info(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.DELETE_ROW);
		boolean deleteRow = false;
		int updateCount = 0;
		try
		{
			managerDtmask.getTransaction().begin();
			if (type.equalsIgnoreCase(TDMConstants._ON))
			{
				updateCount = managerDtmask.createQuery(
						"DELETE FROM  TdmOnboadReqNoTabDO r WHERE r.id=" + Id + "").executeUpdate();
				managerDtmask.getTransaction().commit();
				deleteRow = true;
			}
			else if (type.equalsIgnoreCase(TDMConstants._DM))
			{
				updateCount = managerDtmask.createQuery(
						"DELETE FROM  ReqChDO r WHERE r.id=" + Id + "").executeUpdate();
				managerDtmask.getTransaction().commit();
				deleteRow = true;
			}

			logger.info(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.DELETE_ROW
					+ MessageConstants.LOG_INFO_RETURN + updateCount);
			return deleteRow;
		}
		catch (IllegalStateException illegalStateEx)
		{
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.DELETE_ROW);
			throw new DAOException(MessageConstants.NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION,
					illegalStateEx);
		}
		catch (IllegalArgumentException illegalArgEx)
		{
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.DELETE_ROW);
			throw new DAOException(MessageConstants.INVALID_QUERY_EXCEPTION, illegalArgEx);
		}
		catch (NullPointerException nullPointerEx)
		{
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.DELETE_ROW);
			throw new DAOException(MessageConstants.NULL_POINTER_EXCEPTION, nullPointerEx);
		}
		catch (Exception otherEx)
		{
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.DELETE_ROW);
			throw new DAOException(MessageConstants.DATABASE_EXCEPTION, otherEx);
		}
	}

	@Override
	public void removeDuplicateRecordsFromRequestorCHDO(String Id, EntityManager managerDtmask)
			throws DAOException
	{
		logger.info(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.REMOV_DUPLICATES);
		int updateCount = 0;
		try
		{
			managerDtmask.getTransaction().begin();

			updateCount = managerDtmask.createQuery(
					"DELETE FROM ReqChDO r WHERE r.pId='" + Id + "'").executeUpdate();
			managerDtmask.getTransaction().commit();
			logger.info(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.REMOV_DUPLICATES
					+ MessageConstants.LOG_INFO_RETURN + updateCount);
		}
		catch (IllegalStateException illegalStateEx)
		{
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.REMOV_DUPLICATES);
			throw new DAOException(MessageConstants.NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION,
					illegalStateEx);
		}
		catch (IllegalArgumentException illegalArgEx)
		{
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.REMOV_DUPLICATES);
			throw new DAOException(MessageConstants.INVALID_QUERY_EXCEPTION, illegalArgEx);
		}
		catch (NullPointerException nullPointerEx)
		{
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.REMOV_DUPLICATES);
			throw new DAOException(MessageConstants.NULL_POINTER_EXCEPTION, nullPointerEx);
		}
		catch (Exception otherEx)
		{
			logger.error(MessageConstants.DATA_MASKING_DAOIMPL + MessageConstants.REMOV_DUPLICATES);
			throw new DAOException(MessageConstants.DATABASE_EXCEPTION, otherEx);
		}
	}
}
