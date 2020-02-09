package com.tdm.service;

import java.util.List;

import org.springframework.web.bind.annotation.ResponseBody;

import com.tdm.exception.ServiceException;
import com.tdm.model.DO.TdmUserDO;
import com.tdm.model.DTO.TDMDataCreationDTO;
import com.tdm.model.DTO.TdmUserDTO;
import com.tdm.model.DTO.UserDetailsDTO;

public interface TDMAdminService
{
	public boolean saveUserDetails(TdmUserDTO userdo, boolean bEdit) throws ServiceException;

	public List<TdmUserDTO> getAllUser(TdmUserDTO userdo, int offSet, int recordsperpage, boolean b)
			throws ServiceException;

	public TdmUserDTO getEditUser(String userId) throws ServiceException;

	public String deleteUserByUserId(String userId) throws ServiceException;

	public Long searchUserRecordsCount(TdmUserDTO userdo) throws ServiceException;

	public boolean validateUserId(String userid) throws ServiceException;
	
	public String getUserRole(TdmUserDO userdo) throws ServiceException;
	
	public String getLastLoginTime(String userId)throws ServiceException;
	
	
	public String updateLoginTime(String userId,String lastLogin)throws ServiceException;
	public String checkUserAvailability(String userId)throws ServiceException;
	
//	public List<TDMDataCreationDTO> getRequestByUserId(String userId) throws ServiceException;
	public List<TDMDataCreationDTO> getRequestByUserId(String userId,String userRole, int offSet, int recordsperpage, boolean b) throws ServiceException;
	public List<TDMDataCreationDTO> getAllRequest(int offSet, int recordsperpage, boolean b) throws ServiceException;
	public Long getReqByUserIdRecordCount(String userId) throws ServiceException;
	public Long getReqRecordCount() throws ServiceException;
	public String checkEmailAvailability(String emailId) throws ServiceException;
	//public List<UserDetailsDTO> getUserByUserId(String userId);
}
