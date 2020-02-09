package com.tdm.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.context.NoSuchMessageException;

import com.tdm.exception.DAOException;
import com.tdm.model.DO.DGAutomationScenariosDO;
import com.tdm.model.DO.ServiceRequestDO;
import com.tdm.model.DO.ServiceRequestDetailsDO;
import com.tdm.model.DTO.FieldListDTO;
import com.tdm.model.DTO.TDMDataCreationDTO;

public interface SearchDAO {
	
	public Map<String, List<FieldListDTO>> getAutoSearchPageData(String pageName) ;
	public TDMDataCreationDTO  getAllDropdownData(EntityManager entitymanager);
	public ServiceRequestDO getReqDataByReqId(String requestId, EntityManager entityManager) throws DAOException, NoSuchMessageException;
	public List<ServiceRequestDetailsDO> getReqDetailsByReqId(String requestId,EntityManager entityManager) 
			throws DAOException, NoSuchMessageException; 
/*	public List getDataAutoPolicy(EntityManager entitymanager);*/
	
}
