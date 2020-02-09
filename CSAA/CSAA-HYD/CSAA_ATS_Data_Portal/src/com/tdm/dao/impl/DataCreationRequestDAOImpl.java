package com.tdm.dao.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tdm.dao.DataCreationRequestDAO;
import com.tdm.exception.DAOException;
import com.tdm.model.DO.AlterNotificationDO;
import com.tdm.model.DO.DCAccessLogDO;
import com.tdm.model.DO.DGAutoExcelTemplateDO;
import com.tdm.model.DO.ServiceRequestDO;
import com.tdm.model.DO.ServiceRequestDetailsDO;
import com.tdm.model.DO.TDMObject;
import com.tdm.model.DTO.TDMDataCreationDTO;
import com.tdm.util.DataGenarationUtils;

/****
 * To handle all the data creation requests
 * @author sujillel
 *
 */
@Repository
public class DataCreationRequestDAOImpl implements DataCreationRequestDAO {
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private DataSource tdmUserDS;
	/*@Autowired
	private DataGenarationUtils dataGenarationUtils;*/
	final static Logger logger = Logger.getLogger(DataCreationRequestDAOImpl.class);
	
	@Override
	@Transactional
	public ServiceRequestDO saveServiceRequestData(ServiceRequestDO serviceRequestDO, 
			EntityManager entityManager,boolean isAutomation) throws DAOException {
		String policyType=null;
		if(serviceRequestDO == null) {
			logger.warn("Invalid parameter.");
			return null;
		}
		try {
			entityManager.getTransaction().begin();
			
			if( serviceRequestDO.getRequestId()==null || serviceRequestDO.getRequestId().length()==0 ){
		
			String seqNoStr = String.valueOf(entityManager.createNativeQuery("select "
					+ "SERVICE_REQUEST_ID_SEQ.nextval from dual").getSingleResult());
			if(isAutomation)
			{
				 policyType="DGAREQ";
			}
			else
			{
				 policyType="DGMREQ";
			}
			
			Long seqNo = Long.valueOf(seqNoStr);
			String requestNumber=DataGenarationUtils.getRequestNo(policyType,seqNo);
			serviceRequestDO.setRequestId(requestNumber);
			}
			//Saving the serviceRequest object to DB
			List<ServiceRequestDetailsDO> autoList = serviceRequestDO.getAutoRequestDetailList();
			List<ServiceRequestDetailsDO> propertyList = serviceRequestDO.getPropertyRequestDetailList();
			serviceRequestDO = saveOrUpdate(serviceRequestDO, entityManager);
			//TODO data into ATS_SERVICE_REQUEST_DETAILS
			String requestId = serviceRequestDO.getRequestId();
			/*if(serviceRequestDO!=null && serviceRequestDO.getRequestId()!=null && serviceRequestDO.getRequestId().length()>0 )
			{
			Query query	=entityManager.createNativeQuery("DELETE FROM  SERVICE_REQUEST_DETAILS WHERE REQUEST_ID='"+serviceRequestDO.getRequestId()+"'");
			query.executeUpdate();	
				
			}*/
            if(autoList != null && !autoList.isEmpty()) {
            	for (ServiceRequestDetailsDO serviceRequestDetailsDO : autoList) {
            		serviceRequestDetailsDO.setRequestId(requestId);
            		saveOrUpdate(serviceRequestDetailsDO, entityManager);
            	}
            }
            if(propertyList != null && !propertyList.isEmpty()) {
            	for (ServiceRequestDetailsDO serviceRequestDetailsDO : propertyList) {
            		serviceRequestDetailsDO.setRequestId(requestId);
            		saveOrUpdate(serviceRequestDetailsDO, entityManager);
            	}
            }
            
         // Insert data into DC_ACCESS_LOG
 			DCAccessLogDO accessLogDO = createAccessLog(serviceRequestDO.getRequestId(), serviceRequestDO.getRequestStatus()
 					, "", serviceRequestDO.getUpdatedBy());
 			accessLogDO = saveOrUpdate(accessLogDO, entityManager);
    // 		serviceRequestDO = entityManager.merge(serviceRequestDO);
			entityManager.getTransaction().commit();
			
		} catch (IllegalStateException illegalStateEx) {
			logger.error("MessageConstant.NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION" + illegalStateEx);
			throw new DAOException(messageSource.getMessage(
					"NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION", null, null), illegalStateEx);
		} catch (IllegalArgumentException illegalArgEx) {
			logger.error("MessageConstant.INVALID_QUERY_EXCEPTION" + illegalArgEx);
			throw new DAOException(messageSource.getMessage("INVALID_QUERY_EXCEPTION", null, null),
					illegalArgEx);
		} catch (NullPointerException nullPointerEx) {
			logger.error("MessageConstant.NULL_POINTER_EXCEPTION" + nullPointerEx);
			nullPointerEx.printStackTrace();
			throw new DAOException(messageSource.getMessage("NULL_POINTER_EXCEPTION", null, null),
					nullPointerEx);
		} catch (Exception otherEx) {
			logger.error("MessageConstant.DATABASE_EXCEPTION" + otherEx);
//			throw new DAOException(messageSource.getMessage("DATABASE_EXCEPTION", null, null),
//					otherEx);
			otherEx.printStackTrace();
		}
		return serviceRequestDO;
	}

	/***
	 * To get the DO object for given PK data
	 * @param requestId
	 * @param entityManager
	 * @return
	 * @throws DAOException
	 * @throws NoSuchMessageException
	 */
	@Override
	public ServiceRequestDO getSeriveRequest(String requestId,EntityManager entityManager) 
			throws DAOException, NoSuchMessageException {
		if(requestId == null) {
			logger.warn("Invalid parameter.");
			return null;
		}
		logger.info("Find single record from table based on primarykey - DAO Impl");
		ServiceRequestDO serviceRequestDO = null;
		try {
			serviceRequestDO = entityManager.find(ServiceRequestDO.class, requestId);
		} catch (Exception otherEx) {
			logger.error("MessageConstant.DATABASE_EXCEPTION" + otherEx);
			throw new DAOException(messageSource.getMessage("DATABASE_EXCEPTION", null, null),
					otherEx);
		}
		return serviceRequestDO;
	}

	/***
	 * To get the DO object for given PK data
	 * @param requestId
	 * @param entityManager
	 * @return
	 * @throws DAOException
	 * @throws NoSuchMessageException
	 */
	@Override
	public AlterNotificationDO findAlertTemplate(int alertId, EntityManager entityManager) 
			throws DAOException, NoSuchMessageException {
		if(alertId == 0) {
			logger.warn("Invalid parameter.");
			return null;
		}
		logger.info("Find single record from table based on primarykey - DAO Impl");
		AlterNotificationDO alterNotificationDO = null;
		try {
			alterNotificationDO = entityManager.find(AlterNotificationDO.class, alertId);
		} catch (Exception otherEx) {
			logger.error("MessageConstant.DATABASE_EXCEPTION" + otherEx);
			throw new DAOException(messageSource.getMessage("DATABASE_EXCEPTION", null, null),
					otherEx);
		}
		return alterNotificationDO;
	}
	
	/***
	 * Set data to DO object from DTO
	 * @param requestId
	 * @param status
	 * @param comments
	 * @param userId
	 * @return
	 */
	private DCAccessLogDO createAccessLog(String requestId, Integer status, String comments, String userId) {
		DCAccessLogDO dcAccessLogDO = new DCAccessLogDO();
		dcAccessLogDO.setRequestId(requestId);
		dcAccessLogDO.setRequestStatus(status);
		dcAccessLogDO.setModifiedDate(new java.util.Date());
		dcAccessLogDO.setModifiedBy(userId);
		dcAccessLogDO.setComments(comments);
		dcAccessLogDO.setId(123);
		return dcAccessLogDO;
	}
	
	/***
	 * To get the access log for a requestId
	 * @return 
	 */
	@Override
	public List<DCAccessLogDO> getAccessLogByReqId(String requestId,EntityManager entityManager) 
			throws DAOException, NoSuchMessageException {
		if(requestId == null) {
			logger.warn("Invalid parameter.");
			return null;
		}
		logger.info("Find single record from table based on primarykey - DAO Impl");
		try {

			return entityManager.createNamedQuery("DCAccessLogDO.findAccessLogByReqId"
					, DCAccessLogDO.class).setParameter("requestId", requestId).getResultList();
		} catch (Exception otherEx) {
			logger.error("MessageConstant.DATABASE_EXCEPTION" + otherEx);
			throw new DAOException(messageSource.getMessage("DATABASE_EXCEPTION", null, null),
					otherEx);
		}
	}
	
	@Override
	@Transactional
	public <T extends TDMObject> T saveOrUpdate(T entity, EntityManager entityManager) {
		return entityManager.merge(entity);
	}
	
	@Override
	public <T extends TDMObject> T get(Long id, Class<T> genericType, EntityManager entityManager) {
		T bo = entityManager.find(genericType, id);
		return bo;
	}
	
	@Override
	public <T extends TDMObject> T get(Integer id, Class<T> genericType, EntityManager entityManager) {
		T bo = entityManager.find(genericType, id);
		return bo;
	}
	
	@Override
	public <T extends TDMObject> T get(String id, Class<T> genericType, EntityManager entityManager) {
		T bo = entityManager.find(genericType, id);
		return bo;
	}
	
	/****
	 * To get the user request related details from CFG_APPLICATION table.
	 * @param userId
	 * @param entitymanager
	 * @return
	 */
	@Override
	public TDMDataCreationDTO getRequestGeneralDetails(String userId, EntityManager entitymanager) {
		if(entitymanager == null) {
			return null;
		}
		TDMDataCreationDTO requestDTo = null;
		String queryStr = null;
		try {
				queryStr = "SELECt APPLICATION_OWNER, APPROVER FROM cfg_application ca WHERE application_name = UPPER('PAS') ";
			Query query = entitymanager.createNativeQuery(queryStr);
			List<BigDecimal> values = query.getResultList();
			if(values!= null && !values.isEmpty()) {
				for (Object obj : values) {
					Object[] dataArr = (Object[])obj;
					if(dataArr != null && dataArr.length>0) {
						requestDTo = new TDMDataCreationDTO();
						requestDTo.setApplicationOwner(dataArr[0].toString());
						requestDTo.setApprover(dataArr[1].toString());
						requestDTo.setCreatedOn(new Date().toString());
					}
				}
			}
		} catch (Exception othex) {
			othex.printStackTrace();
		}
		return requestDTo;
	}
	/***
	 * To get the references for the given column name
	 * @param excelName
	 * @param colName
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<DGAutoExcelTemplateDO> getRefrencesForExcelColumnName(
			String excelName, String colName, EntityManager entityManager) {
		if(entityManager==null) {
			return null;
		}
		List<DGAutoExcelTemplateDO> referenceList = entityManager.createNamedQuery("DGAutoExcelTemplateDO.findColumnByName")
				.setParameter("excelName","Auto Policy Testdata")
				.setParameter("columnName","State" )
				.getResultList();
		return referenceList;
	}
}
