/*---------------------------------------------------------------------------------------
 * Object Name: TDMUserMapperImp.Java
 * --------------------------------------------------------------------------------------
 *
 * Copyright: 2015 Capgemini Financial Services
 *---------------------------------------------------------------------------------------*/

package com.tesda.dao;

import java.util.List;

import com.tesda.exception.DAOException;
import com.tesda.model.DO.TDMLoginUsersDO;
import com.tesda.model.DO.TdmUserDO;

/**
 * Admin DAO interface to define the methods needed for writing or fetching data from the database 
 */
public interface TDMAdminDAO
{

	public String saveUserDetails(TdmUserDO userdo, boolean bEdit) throws DAOException;

	public List<TdmUserDO> getAllUser(TdmUserDO userdo, int offSet, int recordsperpage, boolean b)
			throws DAOException;

	public TdmUserDO getEditUser(String userId) throws DAOException;

	public String deleteUserByUserId(String userId) throws DAOException;

	public Long searchUserRecordsCount(TdmUserDO userdo) throws DAOException;

	boolean validateUserId(String userId) throws DAOException;

	public void saveLoginUserDetails(TDMLoginUsersDO loginUsersDo) throws DAOException;

	public Long searchUserRecordsCount() throws DAOException;

	public List<TDMLoginUsersDO> getAllUsers(int offSet, int recordsperpage) throws DAOException;

}
