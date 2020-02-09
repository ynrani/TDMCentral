package com.tdm.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.context.NoSuchMessageException;

import com.tdm.exception.DAOException;
import com.tdm.model.DO.AlterNotificationDO;
import com.tdm.model.DO.DCAccessLogDO;
import com.tdm.model.DO.DGAutoExcelTemplateDO;
import com.tdm.model.DO.ServiceRequestDO;
import com.tdm.model.DO.TDMObject;
import com.tdm.model.DTO.TDMDataCreationDTO;

public interface DataCreationRequestDAO {
	
	public ServiceRequestDO saveServiceRequestData(ServiceRequestDO serviceRequestDO, 
			EntityManager entityManager,boolean isAutomation) throws DAOException; 
	
	public TDMDataCreationDTO getRequestGeneralDetails(String userId, EntityManager entitymanager);
	
	public <T extends TDMObject> T saveOrUpdate(T entity, EntityManager entityManager);
	
	public <T extends TDMObject> T get(Long id, Class<T> genericType, EntityManager entityManager);
	public <T extends TDMObject> T get(Integer id, Class<T> genericType, EntityManager entityManager);
	public <T extends TDMObject> T get(String id, Class<T> genericType, EntityManager entityManager);
	
	public AlterNotificationDO findAlertTemplate(int alertId,EntityManager entityManager) 
			throws DAOException, NoSuchMessageException;
	
	public List<DCAccessLogDO> getAccessLogByReqId(String requestId,EntityManager entityManager) 
			throws DAOException, NoSuchMessageException;
	
	public ServiceRequestDO getSeriveRequest(String requestId,EntityManager entityManager) 
			throws DAOException, NoSuchMessageException;
	public List<DGAutoExcelTemplateDO> getRefrencesForExcelColumnName(String excelName, String param ,EntityManager entityManager);

}
