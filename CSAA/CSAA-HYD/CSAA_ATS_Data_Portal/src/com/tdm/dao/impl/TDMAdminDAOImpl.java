/*---------------------------------------------------------------------------------------
 * Object Name: TDMAdminDAOImpl.Java
 * 
 * Modification Block:
 * --------------------------------------------------------------------------------------
 * S.No. Name                Date      Bug Fix no. Desc
 * --------------------------------------------------------------------------------------
 * 1     Seshadri Chowdary          12/06/15  NA          Created
 * --------------------------------------------------------------------------------------
 *
 * Copyright: 2015 <CapGemini>
 *---------------------------------------------------------------------------------------*/

package com.tdm.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.tdm.constant.AppConstant;
import com.tdm.constant.MessageConstant;
import com.tdm.dao.DataCreationRequestDAO;
import com.tdm.dao.TDMAdminDAO;
import com.tdm.exception.DAOException;
import com.tdm.exception.ServiceException;
import com.tdm.model.DO.ServiceRequestDO;
import com.tdm.model.DO.TdmUserDO;
import com.tdm.model.DO.TdmUsersAuthDO;
import com.tdm.model.DO.TdmUsersAuthDOPK;

/**
 * 
 * 
 * @author Seshadri Chowdary
 *
 */

@Component(MessageConstant.TDM_ADMIN_DAO)
public class TDMAdminDAOImpl implements TDMAdminDAO {
	@Autowired
	DataCreationRequestDAO dcRequestDAO;
	private static Logger logger = Logger.getLogger(TDMAdminDAOImpl.class);

	@Override
	public boolean saveUserDetails(TdmUserDO userdo, boolean bCreate,
			EntityManager managerUser) throws DAOException {
		logger.info(MessageConstant.TDM_ADMIN_DAO_IMPL
				+ MessageConstant.TDM_ADMIN_DAO_SAVE
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		Boolean userRegister=false;
		try {
			String strMessage = AppConstant.FAILED_SML;
			Random randomno = new Random();
			TdmUsersAuthDO tdmUsersAuthDO = new TdmUsersAuthDO();
			tdmUsersAuthDO.setRole(userdo.getTdmUsersAuths().getRole());
			userdo.setPassword(userdo.getUsername().substring(0, 3)
					+ randomno.nextInt(100) + "$");
			TdmUsersAuthDOPK id = new TdmUsersAuthDOPK();
			id.setUserId(userdo.getUserId());

			/*
			 * String userId=userdo.getUserId();
			 * managerUser.getTransaction().begin(); Query query=
			 * managerUser.createNativeQuery
			 * (" SELECT * FROM USERS  WHERE USER_ID='"+userId+"' "); int result
			 * =query.executeUpdate(); managerUser.getTransaction().commit();
			 */
			// int result=query.getFirstResult();
			/*
			 * if(result==1) {
			 * 
			 * }else {
			 * 
			 * }
			 */

			/*
			 * List<String>
			 * userNameList=(List<String>)managerUser.createQuery("SELECT ");
			 */
			boolean bCheckValidateUserid = true;
			String idrole = "";
			if (bCreate) {
				String no = (String) managerUser.createQuery(
						"SELECT MAX(p.id.userRoleId)  from TdmUsersAuthDO p")
						.getSingleResult();
				int noo = Integer.parseInt(no) + 1;
				idrole = Integer.toString(noo);
			} else {
				List<TdmUsersAuthDO> listUserAuthDo = managerUser
						.createNamedQuery("TdmUsersAuthDO.findByUserId",
								TdmUsersAuthDO.class)
						.setParameter(AppConstant.SESSION_UID,
								userdo.getUserId()).getResultList();
				if (listUserAuthDo != null && !listUserAuthDo.isEmpty()) {
					idrole = listUserAuthDo.iterator().next().getId()
							.getUserRoleId();
					bCheckValidateUserid = false;
				} else {
					return userRegister;
				}

			}
			String userId = userdo.getUserId();
			managerUser.getTransaction().begin();
			Query query = managerUser
					.createNativeQuery(" SELECT * FROM USERS  WHERE USER_ID='"
							+ userId + "' ");
			int result = query.executeUpdate();
			managerUser.getTransaction().commit();
			if (result == 0) {
				id.setUserRoleId(idrole);
				tdmUsersAuthDO.setId(id);
				tdmUsersAuthDO.setTdmUser(userdo);
				userdo.setTdmUsersAuths(tdmUsersAuthDO);
				managerUser.getTransaction().begin();
				managerUser.merge(userdo);
				managerUser.getTransaction().commit();
			 userRegister= true;
			 
				//strMessage = "User Suceessfully created, ATS support team will get back to you";
			}
			
			logger.info(MessageConstant.TDM_ADMIN_DAO_IMPL
					+ MessageConstant.TDM_ADMIN_DAO_SAVE
					+ MessageConstant.LOG_INFO_RETURN);
			return userRegister;
		} catch (IllegalStateException illegalStateEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL
					+ MessageConstant.TDM_ADMIN_DAO_SAVE
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			  userRegister=false;
			throw new DAOException(
					MessageConstant.NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION,
					illegalStateEx);
		} catch (IllegalArgumentException illegalArgEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL
					+ MessageConstant.TDM_ADMIN_DAO_SAVE
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.INVALID_QUERY_EXCEPTION,
					illegalArgEx);
		} catch (NullPointerException nullPointerEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL
					+ MessageConstant.TDM_ADMIN_DAO_SAVE
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.NULL_POINTER_EXCEPTION,
					nullPointerEx);
		} catch (Exception otherEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL
					+ MessageConstant.TDM_ADMIN_DAO_SAVE
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.DATABASE_EXCEPTION, otherEx);
		}

	}

	@Override
	public List<TdmUserDO> getAllUser(TdmUserDO userdo, int offSet,
			int recordsperpage, boolean b, EntityManager managerUser)
			throws DAOException {
		logger.info(MessageConstant.TDM_ADMIN_DAO_IMPL
				+ MessageConstant.TDM_ADMIN_DAO_GET_ALL
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {

			@SuppressWarnings(AppConstant.UNCHECKED)
			List<TdmUserDO> listUser = managerUser
					.createQuery(
							"SELECT p FROM TdmUserDO p where p.userId != '"
									+ userdo.getUserId() + "' ")
					.setFirstResult(offSet).setMaxResults(recordsperpage)
					.getResultList();
			logger.info(MessageConstant.TDM_ADMIN_DAO_IMPL
					+ MessageConstant.TDM_ADMIN_DAO_GET_ALL
					+ MessageConstant.LOG_INFO_RETURN);
			return listUser;
		} catch (IllegalStateException illegalStateEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL
					+ MessageConstant.TDM_ADMIN_DAO_GET_ALL
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(
					MessageConstant.NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION,
					illegalStateEx);
		} catch (IllegalArgumentException illegalArgEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL
					+ MessageConstant.TDM_ADMIN_DAO_GET_ALL
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.INVALID_QUERY_EXCEPTION,
					illegalArgEx);
		} catch (NullPointerException nullPointerEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL
					+ MessageConstant.TDM_ADMIN_DAO_GET_ALL
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.NULL_POINTER_EXCEPTION,
					nullPointerEx);
		} catch (Exception otherEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL
					+ MessageConstant.TDM_ADMIN_DAO_GET_ALL
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.DATABASE_EXCEPTION, otherEx);
		}
	}

	@Override
	public TdmUserDO getEditUser(String userId, EntityManager managerUser)
			throws DAOException {
		logger.info(MessageConstant.TDM_ADMIN_DAO_IMPL
				+ MessageConstant.TDM_ADMIN_DAO_EDIT
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {

			TdmUserDO listUser = (TdmUserDO) managerUser
					.createQuery(
							"SELECT p FROM TdmUserDO p where p.userId='"
									+ userId + "'").getSingleResult();
			managerUser.close();
			logger.info(MessageConstant.TDM_ADMIN_DAO_IMPL
					+ MessageConstant.TDM_ADMIN_DAO_EDIT
					+ MessageConstant.LOG_INFO_RETURN);
			return listUser;
		} catch (IllegalStateException illegalStateEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL
					+ MessageConstant.TDM_ADMIN_DAO_EDIT
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(
					MessageConstant.NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION,
					illegalStateEx);
		} catch (IllegalArgumentException illegalArgEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL
					+ MessageConstant.TDM_ADMIN_DAO_EDIT
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.INVALID_QUERY_EXCEPTION,
					illegalArgEx);
		} catch (NullPointerException nullPointerEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL
					+ MessageConstant.TDM_ADMIN_DAO_EDIT
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.NULL_POINTER_EXCEPTION,
					nullPointerEx);
		} catch (Exception otherEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL
					+ MessageConstant.TDM_ADMIN_DAO_EDIT
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.DATABASE_EXCEPTION, otherEx);
		}
	}

	@Override
	public String deleteUserByUserId(String userId, EntityManager managerUser)
			throws DAOException {
		logger.info(MessageConstant.TDM_ADMIN_DAO_IMPL
				+ MessageConstant.TDM_ADMIN_DAO_DELETE
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			managerUser.getTransaction().begin();
			Query q1 = managerUser
					.createQuery("DELETE FROM  TdmUsersAuthDO p where p.id.userId =:userId");
			q1.setParameter(AppConstant.USER_ID, userId);
			q1.executeUpdate();
			Query q2 = managerUser
					.createQuery("DELETE FROM TdmUserDO p where p.userId =:userId");
			q2.setParameter(AppConstant.USER_ID, userId);
			q2.executeUpdate();
			// int count=q1.executeUpdate();
			managerUser.getTransaction().commit();
			logger.info(MessageConstant.TDM_ADMIN_DAO_IMPL
					+ MessageConstant.TDM_ADMIN_DAO_DELETE
					+ MessageConstant.LOG_INFO_RETURN);
			return null;
		} catch (IllegalStateException illegalStateEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL
					+ MessageConstant.TDM_ADMIN_DAO_DELETE
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(
					MessageConstant.NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION,
					illegalStateEx);
		} catch (IllegalArgumentException illegalArgEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL
					+ MessageConstant.TDM_ADMIN_DAO_DELETE
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.INVALID_QUERY_EXCEPTION,
					illegalArgEx);
		} catch (NullPointerException nullPointerEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL
					+ MessageConstant.TDM_ADMIN_DAO_DELETE
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.NULL_POINTER_EXCEPTION,
					nullPointerEx);
		} catch (Exception otherEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL
					+ MessageConstant.TDM_ADMIN_DAO_DELETE
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.DATABASE_EXCEPTION, otherEx);
		}
	}

	@Override
	public Long searchUserRecordsCount(TdmUserDO userdo,
			EntityManager managerUser) throws DAOException {
		logger.info(MessageConstant.TDM_ADMIN_DAO_IMPL
				+ MessageConstant.TDM_ADMIN_DAO_SEARCH_USER_CNT
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			String query = "SELECT count(*) FROM TdmUserDO p Where p.userId !='"
					+ userdo.getUserId() + "'";
			Long count = (Long) managerUser.createQuery(query)
					.getSingleResult();
			return count;
		} catch (IllegalStateException illegalStateEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL
					+ MessageConstant.TDM_ADMIN_DAO_SEARCH_USER_CNT
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(
					MessageConstant.NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION,
					illegalStateEx);
		} catch (IllegalArgumentException illegalArgEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL
					+ MessageConstant.TDM_ADMIN_DAO_SEARCH_USER_CNT
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.INVALID_QUERY_EXCEPTION,
					illegalArgEx);
		} catch (NullPointerException nullPointerEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL
					+ MessageConstant.TDM_ADMIN_DAO_SEARCH_USER_CNT
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.NULL_POINTER_EXCEPTION,
					nullPointerEx);
		} catch (Exception otherEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL
					+ MessageConstant.TDM_ADMIN_DAO_SEARCH_USER_CNT
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.DATABASE_EXCEPTION, otherEx);
		}
	}

	@Override
	public boolean validateUserId(String userId, EntityManager managerUser)
			throws DAOException {
		logger.info(MessageConstant.TDM_ADMIN_DAO_IMPL
				+ MessageConstant.TDM_ADMIN_DAO_VALIDATE
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			List<TdmUserDO> listUserDo = managerUser
					.createNamedQuery("TdmUserDO.findByUserId", TdmUserDO.class)
					.setParameter("userId", userId).getResultList();
			logger.info(MessageConstant.TDM_ADMIN_DAO_IMPL
					+ MessageConstant.TDM_ADMIN_DAO_VALIDATE
					+ MessageConstant.LOG_INFO_RETURN);
			return listUserDo != null && !listUserDo.isEmpty() ? false : true;
		} catch (IllegalStateException illegalStateEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL
					+ MessageConstant.TDM_ADMIN_DAO_VALIDATE
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(
					MessageConstant.NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION,
					illegalStateEx);
		} catch (IllegalArgumentException illegalArgEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL
					+ MessageConstant.TDM_ADMIN_DAO_VALIDATE
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.INVALID_QUERY_EXCEPTION,
					illegalArgEx);
		} catch (NullPointerException nullPointerEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL
					+ MessageConstant.TDM_ADMIN_DAO_VALIDATE
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.NULL_POINTER_EXCEPTION,
					nullPointerEx);
		} catch (Exception otherEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL
					+ MessageConstant.TDM_ADMIN_DAO_VALIDATE
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.DATABASE_EXCEPTION, otherEx);
		}
	}

	@Override
	public String getUserRole(TdmUserDO userdo, EntityManager managerUser)
			throws DAOException {

		logger.info(MessageConstant.TDM_ADMIN_DAO_IMPL
				+ MessageConstant.TDM_ADMIN_DAO_GETUSERROLE
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		String idrole = "";

		try {

			List<TdmUserDO> listUserAuthDo = managerUser.createQuery(
					" select p from TdmUserDO p where p.userId = '"
							+ userdo.getUserId() + "' ").getResultList();

			logger.info(MessageConstant.TDM_ADMIN_DAO_IMPL
					+ MessageConstant.TDM_ADMIN_DAO_GET_ALL
					+ MessageConstant.LOG_INFO_RETURN);
			if (listUserAuthDo != null && !listUserAuthDo.isEmpty())
				idrole = listUserAuthDo.iterator().next().getTdmUsersAuths()
						.getRole();
		} catch (IllegalStateException illegalStateEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL
					+ MessageConstant.TDM_ADMIN_DAO_GET_ALL
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(
					MessageConstant.NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION,
					illegalStateEx);
		} catch (IllegalArgumentException illegalArgEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL
					+ MessageConstant.TDM_ADMIN_DAO_GET_ALL
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.INVALID_QUERY_EXCEPTION,
					illegalArgEx);
		} catch (NullPointerException nullPointerEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL
					+ MessageConstant.TDM_ADMIN_DAO_GET_ALL
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.NULL_POINTER_EXCEPTION,
					nullPointerEx);
		} catch (Exception otherEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL
					+ MessageConstant.TDM_ADMIN_DAO_GET_ALL
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.DATABASE_EXCEPTION, otherEx);
		}
		return idrole;
	}

	@Override
	public List<ServiceRequestDO> getRequestByUserId(String userId,
			String userRole, EntityManager managerUser, int offSet,
			int recordsperpage, boolean b) throws DAOException {
		logger.info(MessageConstant.TDM_ADMIN_DAO_IMPL
				+ MessageConstant.TDM_ADMIN_DAO_EDIT
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		List<ServiceRequestDO> listRequest=new ArrayList<ServiceRequestDO>();
		ServiceRequestDO serviceRequestDO;
		try {

			if (userRole.equalsIgnoreCase("ROLE_ADMIN")) {
				listRequest = managerUser
						.createNamedQuery(
								"ServiceRequestDO.findRequestBySupportUserId",
								ServiceRequestDO.class)
						.setParameter("assignedTo", userId)
						.setFirstResult(offSet).setMaxResults(recordsperpage)
						.getResultList();
			} else {
					/*listRequest = managerUser
						.createNamedQuery(
								"ServiceRequestDO.findRequestByUserId",
								ServiceRequestDO.class)
						.setParameter("requestedBy", userId)
						.setFirstResult(offSet).setMaxResults(recordsperpage)
						.getResultList();*/
			String	queryStr = "select  sr.REQUEST_ID,sr.CREATE_DATE,sr.REQUEST_STATUS,sr.SUBJECT,al.modified_date from service_request sr inner join DC_ACCESS_LOG al on sr.REQUEST_ID = al.REQUEST_ID WHERE al.modified_date IN (SELECT MAX(B1.MODIFIED_DATE) FROM DC_ACCESS_LOG B1 WHERE B1.REQUEST_ID=SR.REQUEST_ID) AND SR.REQUESTED_by='"+userId+"' ";
             
			List<Object> values = managerUser.createNativeQuery(queryStr).getResultList();
			if (values != null && !values.isEmpty()) {
				for (Object obj : values) {
					Object[] dataArr = (Object[]) obj;
					if (dataArr != null && dataArr.length > 0) {
						serviceRequestDO = new ServiceRequestDO();
						serviceRequestDO
								.setRequestId(dataArr[0] != null ? dataArr[0]
										.toString() : null);
						serviceRequestDO
								.setCreateDate(dataArr[1] != null ? (Date)dataArr[1]:null);
										
						serviceRequestDO
								.setRequestStatus(dataArr[2] != null ? Integer
										.valueOf(dataArr[2].toString()) : null);
						serviceRequestDO
								.setSubject(dataArr[3] != null ? dataArr[3]
										.toString() : null);
						serviceRequestDO
								.setLastModifiedDate(dataArr[4] != null ? (Date)dataArr[4]: null);
						
						listRequest.add(serviceRequestDO);
					}
				}
			}
			
			}

			logger.info(MessageConstant.TDM_ADMIN_DAO_IMPL
					+ MessageConstant.TDM_ADMIN_DAO_EDIT
					+ MessageConstant.LOG_INFO_RETURN);
			return listRequest;
		} catch (IllegalStateException illegalStateEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL
					+ MessageConstant.TDM_ADMIN_DAO_EDIT
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(
					MessageConstant.NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION,
					illegalStateEx);
		} catch (IllegalArgumentException illegalArgEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL
					+ MessageConstant.TDM_ADMIN_DAO_EDIT
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.INVALID_QUERY_EXCEPTION,
					illegalArgEx);
		} catch (NullPointerException nullPointerEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL
					+ MessageConstant.TDM_ADMIN_DAO_EDIT
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.NULL_POINTER_EXCEPTION,
					nullPointerEx);
		} catch (Exception otherEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL
					+ MessageConstant.TDM_ADMIN_DAO_EDIT
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.DATABASE_EXCEPTION, otherEx);
		}
	}

	@Override
	public List<ServiceRequestDO> getAllRequest(EntityManager managerUser,
			int offSet, int recordsperpage, boolean b) throws DAOException {
		logger.info(MessageConstant.TDM_ADMIN_DAO_IMPL
				+ MessageConstant.TDM_ADMIN_DAO_EDIT
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		List<ServiceRequestDO> serviceRequestDOList = new ArrayList<ServiceRequestDO>();
		ServiceRequestDO serviceRequestDO;
		try {

			String queryStr = null;

			/*queryStr = "select sr.REQUEST_ID,sr.service_type,sr.SUBJECT,sr.REQUEST_STATUS,sr.REQUESTED_BY,sr.CREATE_DATE, "
					+ " sr.EXPECTED_DATE,cg.CONSUMER_GROUP_NAME, su.SUPPORT_USER_NAME, rp.PRIORITY_NAME from service_request sr "
					+ " inner join CFG_CONSUMER_GROUP cg on sr.CONSUMER_GROUP = cg.CONSUMER_GROUP_ID "
					+ " inner join CFG_SUPPORT_USER su on su.SUPPORT_USER_ID = sr.ASSIGNED_TO "
					+ " inner join CFG_REQUEST_PRIORITY rp on rp.PRIORITY_ID = sr.PRIORITY "
					+ " where sr.REQUEST_STATUS !=1 order BY EXPECTED_DATE desc ";*/
			// order by changed based on Request_id
			queryStr = "select sr.REQUEST_ID,sr.service_type,sr.SUBJECT,sr.REQUEST_STATUS,sr.REQUESTED_BY,sr.CREATE_DATE, "
					+ " sr.EXPECTED_DATE,cg.CONSUMER_GROUP_NAME, su.SUPPORT_USER_NAME, rp.PRIORITY_NAME from service_request sr "
					+ " inner join CFG_CONSUMER_GROUP cg on sr.CONSUMER_GROUP = cg.CONSUMER_GROUP_ID "
					+ " inner join CFG_SUPPORT_USER su on su.SUPPORT_USER_ID = sr.ASSIGNED_TO "
					+ " inner join CFG_REQUEST_PRIORITY rp on rp.PRIORITY_ID = sr.PRIORITY "
					+ " where sr.REQUEST_STATUS !=1 order BY sr.REQUEST_ID asc ";

			Query query = managerUser.createNativeQuery(queryStr);
			List<Object> values = query.getResultList();
			if (values != null && !values.isEmpty()) {
				for (Object obj : values) {
					Object[] dataArr = (Object[]) obj;
					if (dataArr != null && dataArr.length > 0) {
						serviceRequestDO = new ServiceRequestDO();
						serviceRequestDO
								.setRequestId(dataArr[0] != null ? dataArr[0]
										.toString() : null);
						serviceRequestDO
								.setServiceType(dataArr[1] != null ? dataArr[1]
										.toString() : null);
						serviceRequestDO
								.setSubject(dataArr[2] != null ? dataArr[2]
										.toString() : null);
						serviceRequestDO
								.setRequestStatus(dataArr[3] != null ? Integer
										.valueOf(dataArr[3].toString()) : null);
						serviceRequestDO
								.setRequestedBy(dataArr[4] != null ? dataArr[4]
										.toString() : null);
						serviceRequestDO
								.setCreateDate(dataArr[5] != null ? (Date) dataArr[5]
										: null);
						serviceRequestDO
								.setExpectedDate(dataArr[6] != null ? (Date) dataArr[6]
										: null);
						serviceRequestDO
								.setConsumerGroupName(dataArr[7] != null ? dataArr[7]
										.toString() : null);
						serviceRequestDO
								.setAssignedTo(dataArr[8] != null ? dataArr[8]
										.toString() : null);
						serviceRequestDO
								.setPriorityName(dataArr[9] != null ? dataArr[9]
										.toString() : null);
						serviceRequestDOList.add(serviceRequestDO);
					}
				}
			}

			logger.info(MessageConstant.TDM_ADMIN_DAO_IMPL
					+ MessageConstant.TDM_ADMIN_DAO_EDIT
					+ MessageConstant.LOG_INFO_RETURN);
			return serviceRequestDOList;
		} catch (IllegalStateException illegalStateEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL
					+ MessageConstant.TDM_ADMIN_DAO_EDIT
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(
					MessageConstant.NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION,
					illegalStateEx);
		} catch (IllegalArgumentException illegalArgEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL
					+ MessageConstant.TDM_ADMIN_DAO_EDIT
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.INVALID_QUERY_EXCEPTION,
					illegalArgEx);
		} catch (NullPointerException nullPointerEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL
					+ MessageConstant.TDM_ADMIN_DAO_EDIT
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.NULL_POINTER_EXCEPTION,
					nullPointerEx);
		} catch (Exception otherEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL
					+ MessageConstant.TDM_ADMIN_DAO_EDIT
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.DATABASE_EXCEPTION, otherEx);
		}
	}

	@Override
	public Long getReqByUserIDRecordsCount(String userId,
			EntityManager managerUser) throws DAOException {
		logger.info(MessageConstant.TDM_ADMIN_DAO_IMPL
				+ MessageConstant.TDM_ADMIN_DAO_SEARCH_USER_CNT
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {

			/*
			 * Long count = Integer.valueOf( managerUser .createNamedQuery(
			 * "ServiceRequestDO.findReqCountByUserId", ServiceRequestDO.class)
			 * .setParameter("requestedBy", userId)
			 * .getResultList().size()).longValue();
			 */

			String query = "SELECT count(*) FROM ServiceRequestDO p Where p.requestedBy ='"
					+ userId + "'";
			Long count = (Long) managerUser.createQuery(query)
					.getSingleResult();
			return count;
		} catch (IllegalStateException illegalStateEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL
					+ MessageConstant.TDM_ADMIN_DAO_SEARCH_USER_CNT
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(
					MessageConstant.NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION,
					illegalStateEx);
		} catch (IllegalArgumentException illegalArgEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL
					+ MessageConstant.TDM_ADMIN_DAO_SEARCH_USER_CNT
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.INVALID_QUERY_EXCEPTION,
					illegalArgEx);
		} catch (NullPointerException nullPointerEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL
					+ MessageConstant.TDM_ADMIN_DAO_SEARCH_USER_CNT
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.NULL_POINTER_EXCEPTION,
					nullPointerEx);
		} catch (Exception otherEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL
					+ MessageConstant.TDM_ADMIN_DAO_SEARCH_USER_CNT
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.DATABASE_EXCEPTION, otherEx);
		}
	}

	@Override
	public Long getReqRecordsCount(EntityManager managerUser)
			throws DAOException {
		logger.info(MessageConstant.TDM_ADMIN_DAO_IMPL
				+ MessageConstant.TDM_ADMIN_DAO_SEARCH_USER_CNT
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {

			/*
			 * Long count = Integer.valueOf( managerUser .createNamedQuery(
			 * "ServiceRequestDO.findReqCountByUserId", ServiceRequestDO.class)
			 * .setParameter("requestedBy", userId)
			 * .getResultList().size()).longValue();
			 */

			String query = "SELECT count(*) FROM ServiceRequestDO p where p.requestStatus !=1";
			Long count = (Long) managerUser.createQuery(query)
					.getSingleResult();
			return count;
		} catch (IllegalStateException illegalStateEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL
					+ MessageConstant.TDM_ADMIN_DAO_SEARCH_USER_CNT
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(
					MessageConstant.NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION,
					illegalStateEx);
		} catch (IllegalArgumentException illegalArgEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL
					+ MessageConstant.TDM_ADMIN_DAO_SEARCH_USER_CNT
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.INVALID_QUERY_EXCEPTION,
					illegalArgEx);
		} catch (NullPointerException nullPointerEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL
					+ MessageConstant.TDM_ADMIN_DAO_SEARCH_USER_CNT
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.NULL_POINTER_EXCEPTION,
					nullPointerEx);
		} catch (Exception otherEx) {
			logger.error(MessageConstant.TDM_ADMIN_DAO_IMPL
					+ MessageConstant.TDM_ADMIN_DAO_SEARCH_USER_CNT
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.DATABASE_EXCEPTION, otherEx);
		}
	}

	@Override
	public String getLastLoginTime(String userId, EntityManager managerUser)
			throws ServiceException {
		Object lastLogin = managerUser.createQuery(
				"select p.lastLogin from TdmUserDO p  where p.userId='"
						+ userId + "'").getSingleResult();
		if (lastLogin == null) {
			return (String) lastLogin;
		} else {
			return (String) lastLogin.toString();
		}
	}

	@Override
	public String updateLoginTime(String userId, String lastLogin,
			EntityManager managerUser) throws ServiceException {
		try {

			managerUser.getTransaction().begin();
			/*
			 * Query query =managerUser.createQuery(
			 * "update TdmUserDO p set p.lastLogin=TO_TIMESTAMP("
			 * +lastLogin+",'YYYY-MM-DD HH24:MI:SS')  where  p.userId= '"
			 * +userId+"'");
			 */
			Query query = managerUser
					.createQuery("update TdmUserDO p set p.lastLogin=TO_TIMESTAMP('"
							+ lastLogin
							+ "','MM-DD-YYYY HH24:MI:SS')  where  p.userId= '"
							+ userId + "'");
			query.executeUpdate();
			managerUser.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return "";
	}

	@Override
	public String checkUserAvailability(String userId, EntityManager managerUser) {
		String str;
		
		userId.replaceAll("\"", "");

		userId = "'" + userId + "'";
		List list = managerUser.createNativeQuery(" SELECT * FROM USERS  WHERE USER_ID=" + userId).getResultList();
		

		if (CollectionUtils.isEmpty(list)) {
			str = "SUCCESS";

		} else {
			str = "FAILURE";
		}
		return str;
	}
	@Override
	public String checkEmailAvailability(String emailId, EntityManager managerUser) {
		String str;
		
		emailId.replaceAll("\"", "");

		emailId = "'" + emailId + "'";
		
		List list = managerUser.createNativeQuery(" SELECT * FROM USERS  WHERE EMAIL_ID=" + emailId).getResultList();

		if (CollectionUtils.isEmpty(list)) {
			str = "SUCCESS";

		} else {
			str = "FAILURE";
		}
		return str;
	}
}
