/*---------------------------------------------------------------------------------------
 * Object Name: TDMAdminService.Java
 * --------------------------------------------------------------------------------------
 *
 * Copyright: 2015 Capgemini Financial Services
 *---------------------------------------------------------------------------------------*/

package com.tesda.service;

import java.util.List;

import com.tesda.exception.ServiceException;
import com.tesda.model.DTO.TDMLoginUsersDTO;
import com.tesda.model.DTO.TdmUserDTO;

/**
 * Service class which provides the following services. Saves the user details.
 * Edits the user details. Gets the saved user details. Validating the user.
 */
public interface TDMAdminService
{
	public String saveUserDetails(TdmUserDTO userdo, boolean bEdit) throws ServiceException;

	public List<TdmUserDTO> getAllUser(TdmUserDTO userdo, int offSet, int recordsperpage, boolean b)
			throws ServiceException;

	public TdmUserDTO getEditUser(String userId) throws ServiceException;

	public String deleteUserByUserId(String userId) throws ServiceException;

	public Long searchUserRecordsCount(TdmUserDTO userdo) throws ServiceException;

	public boolean validateUserId(String userid) throws ServiceException;

	public void saveLoginUserDetails(TDMLoginUsersDTO loginDto) throws ServiceException;

	public Long searchUserRecordsCount() throws ServiceException;

	public List<TDMLoginUsersDTO> getAllUsers(int offSet, int recordsperpage)
			throws ServiceException;
}
