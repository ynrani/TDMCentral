package com.tdm.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tdm.dao.DataCreationRequestDAO;
import com.tdm.dao.SearchDAO;
import com.tdm.model.DO.DGAutomationScenariosDO;
import com.tdm.model.DO.TdmUserDO;
import com.tdm.model.DTO.DGAutomationScenariosDTO;
import com.tdm.model.DTO.FieldListDTO;
import com.tdm.model.DTO.TDMDataCreationDTO;
import com.tdm.model.mapper.TDMUserMapper;
import com.tdm.model.mapper.impl.TDMDataRequestSaveMapperImpl;
import com.tdm.service.SearchService;

@Component
@Service("searchService")
@Transactional(propagation = Propagation.REQUIRED)
public class SearchServiceImpl extends TdmBaseServiceImpl  implements SearchService {

	final static Logger logger = Logger.getLogger(SearchServiceImpl.class);
	
	@Autowired
	SearchDAO searchDAO;

	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	TDMUserMapper tdmUserMapper;
	
	@Autowired
	DataCreationRequestDAO saveDataRequestDAO;
	
	@Autowired
	TDMDataRequestSaveMapperImpl	tdmDataRequestSaveMapper;
	/***
	 * To get the data from the DB table FIELD_LIST
	 * @param Page name
	 * @return dataMap
	 */
	@Override
	public Map<String, List<FieldListDTO>> getAutoSearchPageData(String pageName) {
		Map<String, List<FieldListDTO>> dataMap = null;
		try {
			dataMap= searchDAO.getAutoSearchPageData(pageName);
		} catch (Exception otherEx) {
			//			throw new ServiceException(messageSource.getMessage("SERVICE_EXCEPTION", null, null),
			//					otherEx);
		}
		return dataMap;
	}
	
	/***
	 * To get all details for loading screen in Data creation screen
	 * @return DCDTO
	 */
	@Override
	public   TDMDataCreationDTO getAllDetails() {
		EntityManager managerCsaaUser = openCsaaUserEntityManager();
		TDMDataCreationDTO tdmDataCreationDTO=null;
		try {
			
			
			tdmDataCreationDTO=searchDAO.getAllDropdownData(managerCsaaUser);
		}catch(Exception otherEx) {
			otherEx.printStackTrace();
		}
		return tdmDataCreationDTO;
	}
	
	@Override
	public   TDMDataCreationDTO getServiceRequestByReqId(String requestId) {
		EntityManager managerCsaaUser = openCsaaUserEntityManager();
		TDMDataCreationDTO tdmDataCreationDTO=null;
		try {
			tdmDataCreationDTO = tdmUserMapper.convertServiceRequestDOToTDMDataCreationDTO(searchDAO.getReqDataByReqId(requestId,managerCsaaUser));
			logger.info("ServiceRequestDetailsDO :: " + tdmDataCreationDTO);
		}catch(Exception otherEx) {
			otherEx.printStackTrace();
		}
		return tdmDataCreationDTO;
	}
	
	@Override
	public   TDMDataCreationDTO getReqDetailDataByReqId(String requestId) {
		EntityManager managerCsaaUser = openCsaaUserEntityManager();
		TDMDataCreationDTO tdmDataCreationDTO=null;
		try {

			tdmDataCreationDTO = tdmDataRequestSaveMapper
					.convertSRDetailListDOToTDMDataCreationDTO(searchDAO
							.getReqDetailsByReqId(requestId, managerCsaaUser));
			//TdmUserDO userDO = saveDataRequestDAO.get(tdmDataCreationDTO.getRequestedBy(), TdmUserDO.class , managerCsaaUser);
			//mDataCreationDTO.setRequestedBy(userDO.getUsername()+" ("+tdmDataCreationDTO.getRequestedBy()+")");
			logger.info("ServiceRequestDetailsDO :: " + tdmDataCreationDTO);
		} catch (Exception otherEx) {
			otherEx.printStackTrace();
		}
		return tdmDataCreationDTO;
	}
	
	/***
	 * To get the automation scenarios from DB
	 * @return DGAutomationScenariosDTO
	 */
	@Override
	public TDMDataCreationDTO getAutoAndPropertyScenarios() {
		EntityManager managerCsaaUser = openCsaaUserEntityManager();
		TDMDataCreationDTO autoAndPropertyScenariosDTO=null;
		try {
			List<DGAutomationScenariosDO> autoScenarioList = managerCsaaUser
					.createNamedQuery("DGAutomationScenariosDO.findAll",
							DGAutomationScenariosDO.class).getResultList();
			 autoAndPropertyScenariosDTO=tdmUserMapper.convertDGAutomationDOToDGAutoationDTO(autoScenarioList);
		} catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			managerCsaaUser.close();
		}
		return autoAndPropertyScenariosDTO;
	}
}
