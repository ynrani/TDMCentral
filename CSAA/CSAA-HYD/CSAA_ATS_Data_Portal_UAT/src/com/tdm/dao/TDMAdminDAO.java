package com.tdm.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.web.bind.annotation.ResponseBody;

import com.tdm.exception.DAOException;
import com.tdm.exception.ServiceException;
import com.tdm.model.DO.ServiceRequestDO;
import com.tdm.model.DO.TdmUserDO;

public interface TDMAdminDAO
{

	public boolean saveUserDetails(TdmUserDO userdo, boolean bEdit, EntityManager managerUser)
			throws DAOException;

	public List<TdmUserDO> getAllUser(TdmUserDO userdo, int offSet, int recordsperpage, boolean b,
			EntityManager managerUser) throws DAOException;

	public TdmUserDO getEditUser(String userId, EntityManager managerUser) throws DAOException;

	public String deleteUserByUserId(String userId, EntityManager managerUser) throws DAOException;

	public Long searchUserRecordsCount(TdmUserDO userdo, EntityManager managerUser)
			throws DAOException;

	boolean validateUserId(String userId, EntityManager managerUser) throws DAOException;

	public String getUserRole(TdmUserDO userdo, EntityManager managerUser) throws DAOException;
	
	//public List<ServiceRequestDO> getRequestByUserId(String userId, EntityManager managerUser) throws DAOException;
	public List<ServiceRequestDO> getRequestByUserId(String userId,String userRole,
			EntityManager managerUser, int offSet, int recordsperpage, boolean b)
			throws DAOException;

	public List<ServiceRequestDO> getAllRequest(EntityManager managerUser,
			int offSet, int recordsperpage, boolean b) throws DAOException;

	public Long getReqByUserIDRecordsCount(String userId,
			EntityManager managerUser) throws DAOException;

	public Long getReqRecordsCount(EntityManager managerUser)
			throws DAOException;
	
	
	public String getLastLoginTime(String userId, EntityManager managerUser)throws ServiceException;
	
	public String updateLoginTime(String userId,String lastLogin, EntityManager managerUser)throws ServiceException;
	public String checkUserAvailability(String userId,EntityManager managerUser)throws ServiceException;
	public String checkEmailAvailability(String emailId,EntityManager managerUser) throws ServiceException;
	
}
