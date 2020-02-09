package com.tdm.service;

import java.util.List;

import org.springframework.context.NoSuchMessageException;

import com.tdm.exception.DAOException;
import com.tdm.exception.ServiceException;
import com.tdm.model.DTO.DCAccessLogDTO;
import com.tdm.model.DTO.TDMDataCreationDTO;

public interface DataCreationRequestService {

	public TDMDataCreationDTO saveServiceRequestData(TDMDataCreationDTO dataCreationDTO ,boolean isAutomation) throws ServiceException;
	
	public String processServiceRequest(String userId, String requestId, Integer status, Long assignedGroupId
			, String assignedToId, String comments,String exceptedDate) throws ServiceException, DAOException, NoSuchMessageException;
	
	public List<DCAccessLogDTO>  getAccessLogByReqId(String requestId) throws ServiceException, DAOException, NoSuchMessageException;
	
	public   TDMDataCreationDTO getAllDropdownDetails();
	
	public TDMDataCreationDTO getRequestGeneralDetails(String userId);
/*	public List getDataAutoPolicy();*/
public void genarateExcel(String requestId);
	
}
