/*---------------------------------------------------------------------------------------
* Object Name: TDMAdminService.Java
* 
 * Modification Block:
* --------------------------------------------------------------------------------------
* S.No. Name                Date      Bug Fix no. Desc
* --------------------------------------------------------------------------------------
* 1     Seshadri Chowdary   11/04/15  NA          Created
* --------------------------------------------------------------------------------------
*
* Copyright: 2015 <CapGemini>
*---------------------------------------------------------------------------------------*/


package com.tesda.service;

import java.util.List;

import com.tesda.exception.ServiceException;
import com.tesda.model.DTO.TdmUserDTO;

public interface TDMAdminService
{
	public String saveUserDetails(TdmUserDTO userdo, boolean bEdit) throws ServiceException;

	public List<TdmUserDTO> getAllUser(TdmUserDTO userdo, int offSet, int recordsperpage, boolean b)
			throws ServiceException;

	public TdmUserDTO getEditUser(String userId) throws ServiceException;

	public String deleteUserByUserId(String userId) throws ServiceException;

	public Long searchUserRecordsCount(TdmUserDTO userdo) throws ServiceException;

	public boolean validateUserId(String userid) throws ServiceException;
}
