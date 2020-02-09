package com.tdm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Repository;

import com.tdm.constant.UIScreenENUM;
import com.tdm.dao.SearchDAO;
import com.tdm.exception.DAOException;
import com.tdm.model.DO.AssignedGroup;
import com.tdm.model.DO.AssignedGroupDO;
import com.tdm.model.DO.ConsumerGroup;
import com.tdm.model.DO.ConsumerGroupDO;
import com.tdm.model.DO.DGAutomationScenariosDO;
import com.tdm.model.DO.ServiceRequestDO;
import com.tdm.model.DO.ServiceRequestDetailsDO;
import com.tdm.model.DO.TDMDataSource;
import com.tdm.model.DO.TDMDataSourceDO;
import com.tdm.model.DO.TDMEnvironment;
import com.tdm.model.DO.TDMEnvironmentDO;
import com.tdm.model.DO.TDMRequestPriority;
import com.tdm.model.DO.TDMRequestPriorityDO;
import com.tdm.model.DO.TDMRequestStatus;
import com.tdm.model.DO.TDMRequestStatusDO;
import com.tdm.model.DO.TDMSupportUser;
import com.tdm.model.DO.TDMSupportUserDO;
import com.tdm.model.DTO.FieldListDTO;
import com.tdm.model.DTO.TDMDataCreationDTO;

@Repository
public class SearchDAOImpl implements SearchDAO {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private DataSource tdmUserDS;
	private TDMDataCreationDTO tdmDataCreationDTO;
	private AssignedGroup assignedGroup;
	private ConsumerGroup consumerGroup;
	private TDMDataSource tdmDataSource;
	private TDMSupportUser tdmSupportUser;
	private TDMEnvironment tdmEnvironment;
	private TDMRequestStatus tdmRequestStatus;
	private TDMRequestPriority tdmRequestPriority;

	final static Logger logger = Logger.getLogger(SearchDAOImpl.class);

	private Map<String, Map<String, List<FieldListDTO>>> pageDataMap = new HashMap<String, Map<String, List<FieldListDTO>>>();

	public static final String USER_DS = "jdbc/tdmUserDS";

	@Override
	public Map<String, List<FieldListDTO>> getAutoSearchPageData(String pageName) {
		if (pageName == null || pageName.isEmpty()) {
			return null;
		}

		// To check the cache is holding the lookup data
		if (pageDataMap.containsKey(pageName)) {
			return pageDataMap.get(pageName);
		}
		PreparedStatement lookupPS = null;
		String dataDict = null;
		long startTime = System.currentTimeMillis();
		Map<String, List<FieldListDTO>> listDataMap = new HashMap<String, List<FieldListDTO>>();
		try {
			// To get the DB connection
			Connection conCsaaUser = tdmUserDS.getConnection();
			if (pageName.equals(UIScreenENUM.AUTO_DATA_SEARCH.getPageName())) {
				dataDict = UIScreenENUM.AUTO_DATA_SEARCH.getDataDict();
			} else if (pageName.equals(UIScreenENUM.PROPERTY_DATA_SEARCH.getPageName())){
				dataDict = UIScreenENUM.PROPERTY_DATA_SEARCH.getDataDict();
			} else if (pageName.equals(UIScreenENUM.DG_AUTOMATION_DATA.getPageName())){
				dataDict = UIScreenENUM.DG_AUTOMATION_DATA.getDataDict();
			} else if (pageName.equals(UIScreenENUM.DG_MANUAL_DATA.getPageName())){
				dataDict = UIScreenENUM.DG_MANUAL_DATA.getDataDict();
			}
			lookupPS = conCsaaUser
					.prepareStatement("select List_name, LIST_VALUE, value_code from CFG_FIELD_LIST "
							+ " where List_Name in("
							+ dataDict
							+ ") and is_Active='Y'"+" ORDER BY SORT_ORDER");
			// Begin

			lookupPS.setFetchSize(1000);
			ResultSet lookupRS = lookupPS.executeQuery();

			while (lookupRS.next()) {

				String listName = lookupRS.getString(1);
				List<FieldListDTO> lookupList1 = listDataMap.get(listName);
				if (lookupList1 != null && lookupList1.size() > 0) {
					populateAutoSearchData(listDataMap, lookupRS, listName,
							lookupList1);
				} else {
					List<FieldListDTO> lookupSet = new ArrayList<FieldListDTO>();
					populateAutoSearchData(listDataMap, lookupRS, listName,
							lookupSet);
				}

			}
			if (lookupPS != null) {
				lookupPS.close();
			}

			// closing resource result sets
			if (lookupRS != null) {
				lookupRS.close();
			}

			if (conCsaaUser != null) {
				conCsaaUser.close();
			}

			long stopTime = System.currentTimeMillis();
			long elapsedTime = stopTime - startTime;
			System.out.println("****************  >  Lookup Data  "
					+ elapsedTime);

			// Setting data to the cache
			pageDataMap.put(pageName, listDataMap);

			// returning the final lookup data

		} catch (Exception otherEx) {
			logger.error("MessageConstant.DATABASE_EXCEPTION" + otherEx);
			// throw new
			// DAOException(messageSource.getMessage("DATABASE_EXCEPTION", null,
			// null),
			// otherEx);

		}
		return listDataMap;
	}

	private void populateAutoSearchData(
			Map<String, List<FieldListDTO>> listDataMap, ResultSet lookupRS,
			String listName, List<FieldListDTO> lookupList1)
			throws SQLException {
		FieldListDTO lookup = new FieldListDTO();
		lookup.setListName(listName);
		lookup.setListValue(lookupRS.getString(2));
		lookup.setValueCode(lookupRS.getString(3));
		lookupList1.add(lookup);
		listDataMap.put(listName, lookupList1);
	}

	public TDMDataCreationDTO getAllDropdownData(EntityManager entitymanager) {
		List<AssignedGroup> assignedGroupDTOList = new ArrayList<AssignedGroup>();
		List<ConsumerGroup> consumerGroupDTOList = new ArrayList<ConsumerGroup>();
		List<TDMDataSource> tdmDataSourceDTOList = new ArrayList<TDMDataSource>();
		List<TDMSupportUser> tdmSupportUserDTOList = new ArrayList<TDMSupportUser>();
		List<TDMEnvironment> tdmEnvironmentDTOList = new ArrayList<TDMEnvironment>();
		List<TDMRequestStatus> tdmRequestStatusDTOList = new ArrayList<TDMRequestStatus>();
		List<TDMRequestPriority> tdmRequestPriorityDTOList = new ArrayList<TDMRequestPriority>();
		tdmDataCreationDTO = new TDMDataCreationDTO();
		try {
			List<AssignedGroupDO> assignedGroupList = entitymanager
					.createNamedQuery("AssignedGroupDO.findAll",
							AssignedGroupDO.class).getResultList();
			List<ConsumerGroupDO> consumerGroupList = entitymanager
					.createNamedQuery("ConsumerGroupDO.findAll",
							ConsumerGroupDO.class).getResultList();
			List<TDMDataSourceDO> dataSourceList = entitymanager
					.createNamedQuery("TDMDataSourceDO.findAll",
							TDMDataSourceDO.class).getResultList();
			List<TDMSupportUserDO> supportUserList = entitymanager
					.createNamedQuery("TDMSupportUserDO.findAll",
							TDMSupportUserDO.class).getResultList();
			List<TDMRequestStatusDO> requestStatusList = entitymanager
					.createNamedQuery("TDMRequestStatusDO.findAll",
							TDMRequestStatusDO.class).getResultList();
			List<TDMRequestPriorityDO> requestPriorityList = entitymanager
					.createNamedQuery("TDMRequestPriorityDO.findAll",
							TDMRequestPriorityDO.class).getResultList();
			List<TDMEnvironmentDO> environmentList = entitymanager
					.createNamedQuery("TDMEnvironmentDO.findAll",
							TDMEnvironmentDO.class).getResultList();

			Iterator<AssignedGroupDO> assignedGroupIterator = assignedGroupList
					.iterator();
			assignedGroupDTOList = new ArrayList<AssignedGroup>();
			while (assignedGroupIterator.hasNext()) {
				assignedGroup =new AssignedGroup();
				AssignedGroupDO assignedGroupDO = (AssignedGroupDO) assignedGroupIterator
						.next();
				assignedGroup.setAssignGroupId(assignedGroupDO
						.getAssignGroupId());
				assignedGroup.setAssignGroupName(assignedGroupDO
						.getAssignGroupName());
				assignedGroup.setCreatedBy(assignedGroupDO.getCreatedBy());
				assignedGroup.setCreatedDate(assignedGroupDO.getCreatedDate());
				assignedGroup.setIsActive(assignedGroupDO.getIsActive());
				assignedGroup.setModifiedBy(assignedGroupDO.getModifiedBy());
				assignedGroup
						.setModifiedDate(assignedGroupDO.getModifiedDate());
				assignedGroupDTOList.add(assignedGroup);
			}
		Iterator<ConsumerGroupDO> consumerGroupIterator = consumerGroupList.iterator();
		consumerGroupDTOList = new ArrayList<ConsumerGroup>();
			while (consumerGroupIterator.hasNext()) {
				consumerGroup=new ConsumerGroup();
				ConsumerGroupDO consumerGroupDO = (ConsumerGroupDO) consumerGroupIterator
						.next();
				consumerGroup.setConsumerGroupId(consumerGroupDO
						.getConsumerGroupId());
				consumerGroup.setConsumerGroupName(consumerGroupDO
						.getConsumerGroupName());
				consumerGroup.setCreatedBy(consumerGroupDO.getCreatedBy());
				consumerGroup.setCreatedDate(consumerGroupDO.getCreatedDate());
				consumerGroup.setIsActive(consumerGroupDO.getIsActive());
				consumerGroup.setModifiedBy(consumerGroupDO.getModifiedBy());
				consumerGroup
						.setModifiedDate(consumerGroupDO.getModifiedDate());
				consumerGroupDTOList.add(consumerGroup);
			}
			Iterator<TDMDataSourceDO> tdmDataSourceIterator = dataSourceList
					.iterator();
			dataSourceList = new ArrayList<TDMDataSourceDO>();
			while (tdmDataSourceIterator.hasNext()) {
				tdmDataSource =new TDMDataSource();
				TDMDataSourceDO tdmDataSourceDO = (TDMDataSourceDO) tdmDataSourceIterator
						.next();
				tdmDataSource
						.setDataSourceId(tdmDataSourceDO.getDataSourceId());
				tdmDataSource.setDataSourceName(tdmDataSourceDO
						.getDataSourceName());
				tdmDataSource.setCreatedBy(tdmDataSourceDO.getCreatedBy());
				tdmDataSource.setCreatedDate(tdmDataSourceDO.getCreatedDate());
				tdmDataSource.setIsActive(tdmDataSourceDO.getIsActive());
				tdmDataSource.setModifiedBy(tdmDataSourceDO.getModifiedBy());
				tdmDataSource.setModifiedDate(tdmDataSourceDO.getModifiedDate());
				tdmDataSourceDTOList.add(tdmDataSource);
			}
			Iterator<TDMSupportUserDO> tdmsupportIterator = supportUserList
					.iterator();
			tdmSupportUserDTOList = new ArrayList<TDMSupportUser>();
			while (tdmsupportIterator.hasNext()) {
				tdmSupportUser =new TDMSupportUser();
				TDMSupportUserDO tdmSupportUserDO = (TDMSupportUserDO) tdmsupportIterator
						.next();
				tdmSupportUser.setAssignedGroupId(tdmSupportUserDO
						.getAssignedGroupId());
				tdmSupportUser.setSupportUserId(tdmSupportUserDO
						.getSupportUserId());
				tdmSupportUser.setSupportUserName(tdmSupportUserDO
						.getSupportUserName());
				tdmSupportUser.setCreatedBy(tdmSupportUserDO.getCreatedBy());
				tdmSupportUser
						.setCreatedDate(tdmSupportUserDO.getCreatedDate());
				tdmSupportUser.setIsActive(tdmSupportUserDO.getIsActive());
				tdmSupportUser.setModifiedBy(tdmSupportUserDO.getModifiedBy());
				tdmSupportUser.setModifiedDate(tdmSupportUserDO
						.getModifiedDate());
				tdmSupportUserDTOList.add(tdmSupportUser);
			}
			Iterator<TDMEnvironmentDO> tdmEnvironmentIterator = environmentList
					.iterator();
			tdmEnvironmentDTOList = new ArrayList<TDMEnvironment>();
			while (tdmEnvironmentIterator.hasNext()) {
				tdmEnvironment =new TDMEnvironment();
				TDMEnvironmentDO tdmEnvironmentDO = (TDMEnvironmentDO) tdmEnvironmentIterator
						.next();
				tdmEnvironment.setEnvironmentId(tdmEnvironmentDO
						.getEnvironmentId());
				tdmEnvironment.setDataSource(tdmEnvironmentDO.getDataSource());
				tdmEnvironment.setReleaseVersion(tdmEnvironmentDO
						.getReleaseVersion());
				tdmEnvironment.setEnvironmentName(tdmEnvironmentDO
						.getEnvironmentName());
				tdmEnvironment.setDataBaseType(tdmEnvironmentDO
						.getDataBaseType());
				tdmEnvironment
						.setServiceName(tdmEnvironmentDO.getServiceName());
				tdmEnvironment.setInstanceName(tdmEnvironmentDO
						.getInstanceName());
				tdmEnvironment.setSchemaName(tdmEnvironmentDO.getSchemaName());
				tdmEnvironment.setUserId(tdmEnvironmentDO.getUserId());
				tdmEnvironment.setConnectionStatus(tdmEnvironmentDO
						.getConnectionStatus());
				tdmEnvironment.setDataBaseStatus(tdmEnvironmentDO
						.getDataBaseStatus());
				tdmEnvironment.setCreatedBy(tdmEnvironmentDO.getCreatedBy());
				tdmEnvironment
						.setCreatedDate(tdmEnvironmentDO.getCreatedDate());
				tdmEnvironment.setIsActive(tdmEnvironmentDO.getIsActive());
				tdmEnvironment.setModifiedBy(tdmEnvironmentDO.getModifiedBy());
				tdmEnvironment.setModifiedDate(tdmEnvironmentDO
						.getModifiedDate());
				tdmEnvironmentDTOList.add(tdmEnvironment);
			}
			Iterator<TDMRequestStatusDO> tdmRequestStatusIterator = requestStatusList
					.iterator();
			tdmRequestStatusDTOList = new ArrayList<TDMRequestStatus>();
			while (tdmRequestStatusIterator.hasNext()) {
				tdmRequestStatus =new TDMRequestStatus();
				TDMRequestStatusDO tdmRequestStatusDO = (TDMRequestStatusDO) tdmRequestStatusIterator
						.next();
				tdmRequestStatus.setRequestStatusId(tdmRequestStatusDO
						.getRequestStatusId());
				tdmRequestStatus.setStatusName(tdmRequestStatusDO
						.getStatusName());
				tdmRequestStatus
						.setCreatedBy(tdmRequestStatusDO.getCreatedBy());
				tdmRequestStatus.setCreatedDate(tdmRequestStatusDO
						.getCreatedDate());
				tdmRequestStatus.setIsActive(tdmRequestStatusDO.getIsActive());
				tdmRequestStatus.setModifiedBy(tdmRequestStatusDO
						.getModifiedBy());
				tdmRequestStatus.setModifiedDate(tdmRequestStatusDO
						.getModifiedDate());
				tdmRequestStatusDTOList.add(tdmRequestStatus);
			}

			Iterator<TDMRequestPriorityDO> tdmRequestPriorityIterator = requestPriorityList
					.iterator();
			tdmRequestPriorityDTOList = new ArrayList<TDMRequestPriority>();
			while (tdmRequestPriorityIterator.hasNext()) {
				tdmRequestPriority =new TDMRequestPriority();
				TDMRequestPriorityDO tdmRequestPriorityDO = (TDMRequestPriorityDO) tdmRequestPriorityIterator
						.next();
				tdmRequestPriority.setPriorityID(tdmRequestPriorityDO
						.getPriorityID());
				tdmRequestPriority.setPriorityName(tdmRequestPriorityDO
						.getPriorityName());
				tdmRequestPriority.setCreatedBy(tdmRequestPriorityDO
						.getCreatedBy());
				tdmRequestPriority.setCreatedDate(tdmRequestPriorityDO
						.getCreatedDate());
				tdmRequestPriority.setIsActive(tdmRequestPriorityDO
						.getIsActive());
				tdmRequestPriority.setModifiedBy(tdmRequestPriorityDO
						.getModifiedBy());
				tdmRequestStatus.setModifiedDate(tdmRequestPriorityDO
						.getModifiedDate());
				tdmRequestPriorityDTOList.add(tdmRequestPriority);
			}
			tdmDataCreationDTO.setAssignedGroupList(assignedGroupDTOList);
			tdmDataCreationDTO.setConsumerGroupList(consumerGroupDTOList);
			tdmDataCreationDTO.setDataSourceList(tdmDataSourceDTOList);
			tdmDataCreationDTO.setSupportUserList(tdmSupportUserDTOList);
			tdmDataCreationDTO.setEnvironmentList(tdmEnvironmentDTOList);
			tdmDataCreationDTO.setRequestStatusList(tdmRequestStatusDTOList);
			tdmDataCreationDTO.setRequestPriorityList(tdmRequestPriorityDTOList);
		} catch (Exception otherEx) {
			logger.error("MessageConstant.DATABASE_EXCEPTION" + otherEx);
			otherEx.printStackTrace();
		}

		return tdmDataCreationDTO;
	}
	
	
	public ServiceRequestDO getReqDataByReqId(String requestId, EntityManager entityManager) throws DAOException, NoSuchMessageException {
		if(entityManager == null) {
			return null;
		}
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
	
	@Override
	public List<ServiceRequestDetailsDO> getReqDetailsByReqId(String requestId,EntityManager entityManager) 
			throws DAOException, NoSuchMessageException {
		if(requestId == null) {
			logger.warn("Invalid parameter.");
			return null;
		}
		logger.info("Find single record from table based on primarykey - DAO Impl");
	    List<ServiceRequestDetailsDO> serviceReqDetailsListDO = new ArrayList<ServiceRequestDetailsDO>();
		ServiceRequestDetailsDO ServiceReqDetailsDO = null;
			
			/*ServiceRequestDetailsDO = entityManager
					.createNamedQuery(
							"ServiceRequestDetailsDO.findReqDetailsByReqId",
							ServiceRequestDetailsDO.class)
					.setParameter("requestId", requestId).getResultList();*/
						
			String queryStr = null;
			try {
				if (requestId != null) {
					queryStr = "select seq_no,policy_type, listagg(column_name||'#'||column_value, '|') within group (order by seq_no) columndetail"
				+ " from service_request_details where REQUEST_ID = '"+requestId+"' group by seq_no, policy_type";
				} 
				Query query = entityManager.createNativeQuery(queryStr);
				//			query.setParameter(1, arg1)
				List<Object> values = query.getResultList();
				if(values!= null && !values.isEmpty()) {
					for (Object obj : values) {
						Object[] dataArr = (Object[])obj;
						if(dataArr != null && dataArr.length>0) {
							ServiceReqDetailsDO = new ServiceRequestDetailsDO();
							ServiceReqDetailsDO.setSeqNo(dataArr[0].toString());
							ServiceReqDetailsDO.setPolicyType(dataArr[1].toString());
							ServiceReqDetailsDO.setColumnValue(dataArr[2].toString());
							serviceReqDetailsListDO.add(ServiceReqDetailsDO);
						}
					}
				}
			return serviceReqDetailsListDO;
			
		} catch (Exception otherEx) {
			logger.error("MessageConstant.DATABASE_EXCEPTION" + otherEx);
			throw new DAOException(messageSource.getMessage("DATABASE_EXCEPTION", null, null),
					otherEx);
		}
		
		
	}
	
	
}
