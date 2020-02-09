/*---------------------------------------------------------------------------------------
 * Object Name: TDMAdminDAOImpl.Java
 * 
 * --------------------------------------------------------------------------------------
 *
 * Copyright: 2015 Capgemini Financial Services
 *---------------------------------------------------------------------------------------*/

package com.tesda.dao.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.tesda.constants.MessageConstants;
import com.tesda.constants.TDMConstants;
import com.tesda.dao.TDMAdminDAO;
import com.tesda.exception.DAOException;
import com.tesda.model.DO.TDMLoginUsersDO;
import com.tesda.model.DO.TdmUserDO;
import com.tesda.model.DO.TdmUsersAuthDO;
import com.tesda.model.DO.TdmUsersAuthDOPK;

/**
 *  Transaction class used between the service layer and Data base.
 *  Gets the user details from DB.
 */

@Component(TDMConstants.ADMIN_DAO)
public class TDMAdminDAOImpl implements TDMAdminDAO
{

	private static final Logger logger = LoggerFactory.getLogger(TDMAdminDAOImpl.class);

	@PersistenceUnit(unitName = TDMConstants.USER_UNIT)
	private EntityManagerFactory factory;

	@Override
	public String saveUserDetails(TdmUserDO userdo, boolean bCreate) throws DAOException
	{
		try
		{
			logger.info(MessageConstants.TDM_ADMIN_DAOIMPL + MessageConstants.SAVE_USER_DETAILS);
			String strMessage = "Failed";
			Random randomno = new Random();
			TdmUsersAuthDO tdmUsersAuthDO = new TdmUsersAuthDO();
			tdmUsersAuthDO.setRole(userdo.getTdmUsersAuths().getRole());
			userdo.setPassword(userdo.getUsername().substring(0, 3) + randomno.nextInt(100) + "$");
			TdmUsersAuthDOPK id = new TdmUsersAuthDOPK();
			id.setUserId(userdo.getUserId());
			EntityManager entityManager = factory.createEntityManager();
			boolean bCheckValidateUserid = true;
			entityManager.getTransaction().begin();
			String idrole = "";
			if (bCreate)
			{
				String no = (String) entityManager.createQuery(
						"SELECT MAX(p.id.userRoleId)  from TdmUsersAuthDO p").getSingleResult();
				int noo = Integer.parseInt(no) + 1;
				idrole = Integer.toString(noo);
			}
			else
			{
				List<TdmUsersAuthDO> listUserAuthDo = entityManager
						.createNamedQuery("TdmUsersAuthDO.findByUserId", TdmUsersAuthDO.class)
						.setParameter("userId", userdo.getUserId()).getResultList();
				if (listUserAuthDo != null && !listUserAuthDo.isEmpty())
					idrole = listUserAuthDo.iterator().next().getId().getUserRoleId();
			}
			if (bCheckValidateUserid)
			{
				id.setUserRoleId(idrole);

				tdmUsersAuthDO.setId(id);
				tdmUsersAuthDO.setTdmUser(userdo);
				userdo.setTdmUsersAuths(tdmUsersAuthDO);

				entityManager.merge(userdo);
				entityManager.getTransaction().commit();
				strMessage = "Success";
			}
			entityManager.close();
			logger.info(MessageConstants.TDM_ADMIN_DAOIMPL + MessageConstants.SAVE_USER_DETAILS
					+ MessageConstants.LOG_INFO_RETURN);
			return strMessage;
		}
		catch (Exception re)
		{
			logger.error(MessageConstants.TDM_ADMIN_DAOIMPL + MessageConstants.SAVE_USER_DETAILS,
					re);
			throw new DAOException(re.getMessage());
		}
	}

	@Override
	public List<TdmUserDO> getAllUser(TdmUserDO userdo, int offSet, int recordsperpage, boolean b)
			throws DAOException
	{
		try
		{
			logger.info(MessageConstants.TDM_ADMIN_DAOIMPL + MessageConstants.GET_ALL_USERS);
			EntityManager entityManager = factory.createEntityManager();
			@SuppressWarnings(TDMConstants.UNCHECKED)
			List<TdmUserDO> listUser = entityManager
					.createQuery(
							"SELECT p FROM TdmUserDO p where p.userId != '" + userdo.getUserId()
									+ "' ").setFirstResult(offSet).setMaxResults(recordsperpage)
					.getResultList();
			entityManager.close();
			logger.info(MessageConstants.TDM_ADMIN_DAOIMPL + MessageConstants.GET_ALL_USERS
					+ MessageConstants.LOG_INFO_RETURN);
			return listUser;
		}
		catch (Exception re)
		{
			logger.error(MessageConstants.TDM_ADMIN_DAOIMPL + MessageConstants.GET_ALL_USERS, re);
			throw new DAOException(re.getMessage());
		}
	}

	@Override
	public TdmUserDO getEditUser(String userId) throws DAOException
	{
		try
		{
			logger.info(MessageConstants.TDM_ADMIN_DAOIMPL + MessageConstants.GET_EDIT_USER);
			EntityManager entityManager = factory.createEntityManager();
			TdmUserDO listUser = (TdmUserDO) entityManager.createQuery(
					"SELECT p FROM TdmUserDO p where p.userId='" + userId + "'").getSingleResult();
			entityManager.close();
			logger.info(MessageConstants.TDM_ADMIN_DAOIMPL + MessageConstants.GET_EDIT_USER
					+ MessageConstants.LOG_INFO_RETURN);
			return listUser;
		}
		catch (Exception re)
		{
			logger.error(MessageConstants.TDM_ADMIN_DAOIMPL + MessageConstants.GET_EDIT_USER, re);
			throw new DAOException(re.getMessage());
		}
	}

	@Override
	public String deleteUserByUserId(String userId) throws DAOException
	{
		try
		{
			logger.info(MessageConstants.TDM_ADMIN_DAOIMPL + MessageConstants.DELETE_USER_BYID);
			EntityManager entityManager = factory.createEntityManager();
			entityManager.getTransaction().begin();
			Query q1 = entityManager
					.createQuery("DELETE FROM  TdmUsersAuthDO p where p.id.userId =:userId");
			q1.setParameter("userId", userId);
			q1.executeUpdate();
			Query q2 = entityManager.createQuery("DELETE FROM TdmUserDO p where p.userId =:userId");
			q2.setParameter("userId", userId);
			q2.executeUpdate();
			// int count=q1.executeUpdate();
			entityManager.getTransaction().commit();
			entityManager.close();
			logger.info(MessageConstants.TDM_ADMIN_DAOIMPL + MessageConstants.DELETE_USER_BYID
					+ MessageConstants.LOG_INFO_RETURN);
			return null;
		}
		catch (Exception re)
		{
			logger.error(MessageConstants.TDM_ADMIN_DAOIMPL + MessageConstants.DELETE_USER_BYID, re);
			throw new DAOException(re.getMessage());
		}
	}

	@Override
	public Long searchUserRecordsCount(TdmUserDO userdo) throws DAOException
	{
		try
		{
			logger.info(MessageConstants.TDM_ADMIN_DAOIMPL + MessageConstants.SEARCH_RECORDS_COUNT);
			EntityManager entityManager = factory.createEntityManager();
			String query = "SELECT count(*) FROM TdmUserDO p Where p.userId !='"
					+ userdo.getUserId() + "'";
			Long count = (Long) entityManager.createQuery(query).getSingleResult();
			entityManager.close();
			logger.info(MessageConstants.TDM_ADMIN_DAOIMPL + MessageConstants.SEARCH_RECORDS_COUNT
					+ MessageConstants.LOG_INFO_RETURN);
			return count;
		}
		catch (Exception re)
		{
			logger.error(
					MessageConstants.TDM_ADMIN_DAOIMPL + MessageConstants.SEARCH_RECORDS_COUNT, re);
			throw new DAOException(re.getMessage());
		}
	}

	@Override
	public boolean validateUserId(String userId) throws DAOException
	{
		try
		{
			logger.info(MessageConstants.TDM_ADMIN_DAOIMPL + MessageConstants.VALIDATE_USERID);
			EntityManager entityManager = factory.createEntityManager();
			List<TdmUserDO> listUserDo = entityManager
					.createNamedQuery("TdmUserDO.findByUserId", TdmUserDO.class)
					.setParameter("userId", userId).getResultList();
			entityManager.close();
			logger.info(MessageConstants.TDM_ADMIN_DAOIMPL + MessageConstants.VALIDATE_USERID
					+ MessageConstants.LOG_INFO_RETURN);
			return listUserDo != null && !listUserDo.isEmpty() ? false : true;
		}
		catch (Exception re)
		{
			logger.error(MessageConstants.TDM_ADMIN_DAOIMPL + MessageConstants.VALIDATE_USERID, re);
			throw new DAOException(re.getMessage());
		}
	}

	@SuppressWarnings(TDMConstants.UNCHECKED)
	@Override
	public void saveLoginUserDetails(TDMLoginUsersDO loginUsersDo) throws DAOException
	{
		try
		{
			logger.info(MessageConstants.TDM_ADMIN_DAOIMPL
					+ MessageConstants.SAVE_LOGINUSER_DETAILS);
			EntityManager entityManager = factory.createEntityManager();
			entityManager.getTransaction().begin();

			List<TDMLoginUsersDO> userDos = entityManager
					.createQuery(
							"SELECT t FROM TDMLoginUsersDO t WHERE t.userId='"
									+ loginUsersDo.getUserId() + "' AND t.role='"
									+ loginUsersDo.getRole() + "' AND t.applicationName='"
									+ loginUsersDo.getApplicationName() + "'").getResultList();
			if (userDos != null && userDos.size() > 0)
			{
				TDMLoginUsersDO userDo = userDos.get(0);
				userDo.setLastLoginDate(new Timestamp(new Date().getTime()));
				userDo.setLoginCount(userDo.getLoginCount() + 1);
				entityManager.merge(userDo);
			}
			else
			{
				loginUsersDo.setLoginCount(1);
				entityManager.merge(loginUsersDo);
			}
			entityManager.getTransaction().commit();
			entityManager.close();
			logger.info(MessageConstants.TDM_ADMIN_DAOIMPL
					+ MessageConstants.SAVE_LOGINUSER_DETAILS + MessageConstants.LOG_INFO_RETURN);
		}
		catch (Exception re)
		{
			logger.error(MessageConstants.TDM_ADMIN_DAOIMPL
					+ MessageConstants.SAVE_LOGINUSER_DETAILS, re);
			throw new DAOException(re.getMessage());
		}
	}

	@Override
	public Long searchUserRecordsCount() throws DAOException
	{
		try
		{
			logger.info(MessageConstants.TDM_ADMIN_DAOIMPL + MessageConstants.SEARCH_RECORDS_COUNT);
			EntityManager entityManager = factory.createEntityManager();

			Long count = (Long) entityManager.createQuery("SELECT count(*) FROM TDMLoginUsersDO t")
					.getSingleResult();

			entityManager.close();
			logger.info(MessageConstants.TDM_ADMIN_DAOIMPL + MessageConstants.SEARCH_RECORDS_COUNT
					+ MessageConstants.LOG_INFO_RETURN);
			return count;
		}
		catch (Exception re)
		{
			logger.error(
					MessageConstants.TDM_ADMIN_DAOIMPL + MessageConstants.SEARCH_RECORDS_COUNT, re);
			throw new DAOException(re.getMessage());
		}
	}

	@SuppressWarnings(TDMConstants.UNCHECKED)
	@Override
	public List<TDMLoginUsersDO> getAllUsers(int offSet, int recordsperpage) throws DAOException
	{
		try
		{
			logger.info(MessageConstants.TDM_ADMIN_DAOIMPL + MessageConstants.GET_ALLLOGIN_USERS);
			EntityManager entityManager = factory.createEntityManager();

			List<TDMLoginUsersDO> userDos = entityManager
					.createQuery("SELECT t FROM TDMLoginUsersDO t").setFirstResult(offSet)
					.setMaxResults(recordsperpage).getResultList();

			entityManager.close();
			logger.info(MessageConstants.TDM_ADMIN_DAOIMPL + MessageConstants.GET_ALLLOGIN_USERS
					+ MessageConstants.LOG_INFO_RETURN);
			return userDos;
		}
		catch (Exception re)
		{
			logger.error(MessageConstants.TDM_ADMIN_DAOIMPL + MessageConstants.GET_ALLLOGIN_USERS,
					re);
			throw new DAOException(re.getMessage());
		}
	}
}
