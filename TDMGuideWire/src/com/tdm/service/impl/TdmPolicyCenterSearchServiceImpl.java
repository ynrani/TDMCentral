/*
 * Object Name : TdmPolicyCenterSearchServiceImpl.java
 * Modification Block
 * ------------------------------------------------------------------
 * S.No.	Name 			Date			Bug_Fix_No			Desc
 * ------------------------------------------------------------------
 * 	1.	  vkrish14		10:26:46 PM				Created
 * ------------------------------------------------------------------
 * Copyrights: 2015 Capgemini.com
 */
package com.tdm.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tdm.constant.MessageConstant;
import com.tdm.dao.TdmPolicyCenterSearchDAO;
import com.tdm.exception.DAOException;
import com.tdm.exception.ServiceException;
import com.tdm.model.DO.PctlCountryDO;
import com.tdm.model.DO.PctlPolicycontactroleDO;
import com.tdm.model.DO.PctlStateDO;
import com.tdm.model.DO.TdmReservationDO;
import com.tdm.model.DTO.PctlCountryDTO;
import com.tdm.model.DTO.PctlPolicycontactroleDTO;
import com.tdm.model.DTO.PctlStateDTO;
import com.tdm.model.DTO.TdmPolicyCenterSearchDTO;
import com.tdm.model.mapper.TdmPolicyCenterSearchMapper;
import com.tdm.service.TdmPolicyCenterSearchService;

/**
 * @author vkrish14
 *
 */
@Component
@Service("tdmPolicyCenterSearchService")
@Transactional(propagation = Propagation.REQUIRED)
public class TdmPolicyCenterSearchServiceImpl extends TdmBaseServiceImpl implements TdmPolicyCenterSearchService{

	private static Logger logger = Logger.getLogger(TdmPolicyCenterSearchServiceImpl.class);
	
	@Autowired
	TdmPolicyCenterSearchDAO tdmPolicyCenterSearchDAO;
	@Autowired
	TdmPolicyCenterSearchMapper tdmPolicyCenterSearchMapper;
	@Autowired
	private MessageSource messageSource;
	
	@Override
	public int saveReservedData(TdmPolicyCenterSearchDTO tdmPolicyCenterSearchDTO, String userName,
			String enviro) throws ServiceException{
		try {
			List<TdmReservationDO> list = null;

			if (null != tdmPolicyCenterSearchDTO.getListTdmPolicyCenterSearchResultDTO()
					&& 0 < tdmPolicyCenterSearchDTO.getListTdmPolicyCenterSearchResultDTO()
							.size()) {
				EntityManager managerCsaaUser = openUserEntityManager();
				List<TdmReservationDO> reservationDOs = tdmPolicyCenterSearchMapper
						.converTdmPolicyCenterSearchDTOToTdmReservationDO(
								tdmPolicyCenterSearchDTO.getListTdmPolicyCenterSearchResultDTO(),
								userName,tdmPolicyCenterSearchDTO.getSearchCriti());
				list = tdmPolicyCenterSearchDAO.saveReservedData(reservationDOs,
						tdmPolicyCenterSearchDTO.getTestCaseId(),
						tdmPolicyCenterSearchDTO.getTestCaseName(), managerCsaaUser);
				closeCsaaUserEntityManager(managerCsaaUser);
			}

			return list.size();
		} catch (NullPointerException nullPointerEx) {
			logger.error("MessageConstant.NULL_POINTER_EXCEPTION", nullPointerEx);
			throw new ServiceException(messageSource.getMessage("NULL_POINTER_EXCEPTION", null,
					null), nullPointerEx);

		} catch (DAOException daoEx) {
			logger.error("DAO exception", daoEx);
			throw new ServiceException(daoEx.getErrorCode(), daoEx);

		} catch (Exception otherEx) {
			logger.error("Service exception", otherEx);
			throw new ServiceException(messageSource.getMessage("SERVICE_EXCEPTION", null, null),
					otherEx);
		}

	}

	@Override
	public TdmPolicyCenterSearchDTO searchPolicyCenter(
			TdmPolicyCenterSearchDTO tdmPolicyCenterSearchDTO, int offSet, int recordsperpage,
			boolean pageNationOnOffFlag, String userName) throws ServiceException{

		logger.info(MessageConstant.TDM_POLICY_SERVICE
				+ MessageConstant.TDM_FTD_SER_POLICY_SEARCH_REC
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			EntityManager managerUser = openUserEntityManager();
			EntityManager managerPolicySearch = openGuideWireEntityManager();
			List<TdmReservationDO> reservationDOs1 = tdmPolicyCenterSearchDAO.getReservedRecords(
					userName, "PC", managerUser);
			StringBuffer policys = new StringBuffer();
			int icount = 0;
			for (TdmReservationDO reservationDO : reservationDOs1) {
				icount++;
				if (null != reservationDO.getReserveDataType()
						&& reservationDO.getReserveDataType().equalsIgnoreCase("PC")) {
					policys.append("'" + reservationDO.getReserveDataId() + "'");
					if(icount < reservationDOs1.size())
						policys.append(",");
				}
			}
			List<Object[]> listResult = new ArrayList<Object[]>();
			Set<String> setPolicies = new HashSet<String>();
			listResult.addAll(tdmPolicyCenterSearchDAO.searchPolicyCenterRecords(tdmPolicyCenterSearchDTO, offSet, recordsperpage, pageNationOnOffFlag, policys,false, managerPolicySearch,false));
			if(listResult != null && !listResult.isEmpty()){
				for(Object[] obj : listResult){
					if(!"Unassigned".equalsIgnoreCase(String.valueOf(obj[0])))
					setPolicies.add(String.valueOf(obj[0]));
				}
				while (setPolicies.size() < recordsperpage) {
					offSet += recordsperpage;
					List<Object[]> lsttemp = tdmPolicyCenterSearchDAO.searchPolicyCenterRecords(tdmPolicyCenterSearchDTO, offSet, recordsperpage, pageNationOnOffFlag, policys,false, managerPolicySearch,false);
					if (lsttemp == null || lsttemp.isEmpty())
						break;
					for(Object[] obj : lsttemp){
						if(!"Unassigned".equalsIgnoreCase(String.valueOf(obj[0])))
						setPolicies.add(String.valueOf(obj[0]));
					}
					listResult.addAll(lsttemp);
				}
			}
			/*List<TdmReservationDO> reservationDOs = tdmPolicyCenterSearchDAO.getReservedRecords(userName,
					managerUser);*/
			List<PctlCountryDO> countryDos = tdmPolicyCenterSearchDAO.getCountryCodes(managerPolicySearch);
			Map<Long,String> mapCountries = new HashMap<Long,String>();
			for(PctlCountryDO dos : countryDos)
				mapCountries.put(dos.getId(), dos.getName());
			List<PctlPolicycontactroleDO> policyContactDos = tdmPolicyCenterSearchDAO.getInsurerTypes(managerPolicySearch);
			Map<Long,String> mapInsurers = new HashMap<Long,String>();
			for(PctlPolicycontactroleDO dos : policyContactDos)
				mapInsurers.put(dos.getId(), dos.getName());
			TdmPolicyCenterSearchDTO tdmPolicyCenterSearchDTOResult = tdmPolicyCenterSearchMapper.converObjectToTdmPolicyCenterSearchDTO(listResult, null, userName,mapCountries,mapInsurers);
		/*	TdmPolicyCenterSearchDTO tdmPolicySearchResultDTOs = tdmPolicyCenterSearchMapper
					.converObjectToTdmPolicyCenterSearchDTO(tdmPolicySearchResultDTOs);
			if (null == tdmPolicySearchResultDTOs && null != reservationDOs1
					&& !reservationDOs1.isEmpty()) {
				List<MetlifePolicyDO> pcPolicyDOss = tdmPolicySearchDAO.searchPolicyRecords(
						tdmPolicySearchDTO, offSet, recordsperpage, pageNationOnOffFlag, null,
						managerPolicySearch);
				if (null != pcPolicyDOss && !pcPolicyDOss.isEmpty()) {
					autoEmailDTOs = new ArrayList<AutoEmailDTO>();
					for (MetlifePolicyDO tdmProviderDO : pcPolicyDOss) {
						for (TdmReservationDO reservationDO : reservationDOs1) {
							if (tdmProviderDO.getPolicyNumber().equalsIgnoreCase(
									reservationDO.getReserveDataId())) {
								if (!reservationDO.getUserId().equalsIgnoreCase(userName)) {
									autoEmailDTO = new AutoEmailDTO();
									autoEmailDTO.setUserId(reservationDO.getUserId());
									autoEmailDTO.setTestCaseId(reservationDO.getTestCaseId());
									autoEmailDTO.setTestCaseName(reservationDO.getTestCaseName());
									autoEmailDTOs.add(autoEmailDTO);
								}
							}
						}
					}
				}
				tdmPolicySearchDTO.setAutoEmailDTOs(autoEmailDTOs);
			} else {
				tdmPolicySearchDTO.setAutoEmailDTOs(null);
			}*/
			closeGuideWireEntityManager(managerPolicySearch);
			closeUserEntityManager(managerUser);
			logger.info(MessageConstant.TDM_POLICY_SERVICE
					+ MessageConstant.TDM_FTD_SER_POLICY_SEARCH_REC
					+ MessageConstant.LOG_INFO_RETURN);
			return tdmPolicyCenterSearchDTOResult;
		} catch (NullPointerException nullPointerEx) {
			// releaseEntityMgrForRollback(entityManager);
			logger.error(MessageConstant.TDM_POLICY_SERVICE
					+ MessageConstant.TDM_FTD_SER_POLICY_SEARCH_REC
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new ServiceException(MessageConstant.NULL_POINTER_EXCEPTION, nullPointerEx);
		} /*catch (DAOException daoEx) {
			// releaseEntityMgrForRollback(entityManager);
			logger.error(MessageConstant.TDM_POLICY_SERVICE
					+ MessageConstant.TDM_FTD_SER_POLICY_SEARCH_REC
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new ServiceException(daoEx.getErrorCode(), daoEx);
		} */catch (Exception otherEx) {
			// releaseEntityMgrForRollback(entityManager);
			logger.error(MessageConstant.TDM_POLICY_SERVICE
					+ MessageConstant.TDM_FTD_SER_POLICY_SEARCH_REC
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new ServiceException(MessageConstant.SERVICE_EXCEPTION, otherEx);
		}
	
	}

	
	@Override
	public TdmPolicyCenterSearchDTO searchPolicyRecordsByPolicySearchNew(
			TdmPolicyCenterSearchDTO tdmPolicyCenterSearchDTO, int offSet, int recordsperpage,
			boolean b, String userName){
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getTotalRecordsCount(TdmPolicyCenterSearchDTO tdmPolicyCenterSearchDTO,String userName)throws ServiceException{
		// TODO Auto-generated method stub
		try {
			EntityManager managerPolicySearch = openGuideWireEntityManager();
			EntityManager managerUser = openUserEntityManager();
			List<TdmReservationDO> reservationDOs1 = tdmPolicyCenterSearchDAO.getReservedRecords(
					userName, "PC", managerUser);
			StringBuffer policys = new StringBuffer();
			int icount = 0;
			for (TdmReservationDO reservationDO : reservationDOs1) {
				icount++;
				if (null != reservationDO.getReserveDataType()
						&& reservationDO.getReserveDataType().equalsIgnoreCase("PC")) {
					policys.append("'" + reservationDO.getReserveDataId() + "'");
					if(icount < reservationDOs1.size())
						policys.append(",");
				}
			}
			Set<String> lResult = new HashSet<String>();
		List<Object[]> listResult = tdmPolicyCenterSearchDAO.searchPolicyCenterRecords(tdmPolicyCenterSearchDTO, 0, 0, false, policys,true, managerPolicySearch,false);
		if(listResult != null && !listResult.isEmpty()){
			for(Object[] obj : listResult){
				if(!"Unassigned".equalsIgnoreCase(String.valueOf(obj[0])))
				lResult.add(String.valueOf(obj[0]));
			}
		}
		closeGuideWireEntityManager(managerPolicySearch);
		closeUserEntityManager(managerUser);
		logger.info(MessageConstant.TDM_POLICY_SERVICE
				+ MessageConstant.TDM_FTD_SER_POLICY_SEARCH_REC
				+ MessageConstant.LOG_INFO_RETURN);
		return lResult != null ? lResult.size() :0L;
	} catch (NullPointerException nullPointerEx) {
		// releaseEntityMgrForRollback(entityManager);
		logger.error(MessageConstant.TDM_POLICY_SERVICE
				+ MessageConstant.TDM_FTD_SER_POLICY_SEARCH_REC
				+ MessageConstant.LOG_ERROR_EXCEPTION);
		throw new ServiceException(MessageConstant.NULL_POINTER_EXCEPTION, nullPointerEx);
	} /*catch (DAOException daoEx) {
		// releaseEntityMgrForRollback(entityManager);
		logger.error(MessageConstant.TDM_POLICY_SERVICE
				+ MessageConstant.TDM_FTD_SER_POLICY_SEARCH_REC
				+ MessageConstant.LOG_ERROR_EXCEPTION);
		throw new ServiceException(daoEx.getErrorCode(), daoEx);
	} */catch (Exception otherEx) {
		// releaseEntityMgrForRollback(entityManager);
		logger.error(MessageConstant.TDM_POLICY_SERVICE
				+ MessageConstant.TDM_FTD_SER_POLICY_SEARCH_REC
				+ MessageConstant.LOG_ERROR_EXCEPTION);
		throw new ServiceException(MessageConstant.SERVICE_EXCEPTION, otherEx);
	}
	}

	@Override
	public TdmPolicyCenterSearchDTO getReservedRecordForUser(String userId, int offSet,
			int recordsperpage, boolean b) throws ServiceException{

		logger.info(MessageConstant.TDM_POLICY_SERVICE
				+ MessageConstant.TDM_FTD_SER_POLICY_GET_RESAERV_DATA
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			EntityManager managerUser = openUserEntityManager();
			EntityManager managerPolicySearch = openGuideWireEntityManager();
			List<TdmReservationDO> reservationDOs = tdmPolicyCenterSearchDAO.getReservedRecords(
					"PC", userId, offSet, recordsperpage, false, managerUser);
			StringBuffer policys = new StringBuffer();
			int i =0;
			for (TdmReservationDO reservationDO : reservationDOs) {
				i++;
				if (null != reservationDO.getReserveDataType()
						&& reservationDO.getReserveDataType().equalsIgnoreCase("PC")) {
					policys.append("'" + reservationDO.getReserveDataId() + "'");
					if(i < reservationDOs.size())
						policys.append(",");
				}
			}
			//TdmPolicyCenterSearchDTO tdmPolicySearchResultDTO = new TdmPolicyCenterSearchDTO();
			List<Object[]> pcPolicyDOs = tdmPolicyCenterSearchDAO.getPolicyRecords(policys,
					managerPolicySearch);
			List<PctlCountryDO> countryDos = tdmPolicyCenterSearchDAO.getCountryCodes(managerPolicySearch);
			Map<Long,String> mapCountries = new HashMap<Long,String>();
			for(PctlCountryDO dos : countryDos)
				mapCountries.put(dos.getId(), dos.getName());
			List<PctlPolicycontactroleDO> policyContactDos = tdmPolicyCenterSearchDAO.getInsurerTypes(managerPolicySearch);
			Map<Long,String> mapInsurers = new HashMap<Long,String>();
			for(PctlPolicycontactroleDO dos : policyContactDos)
				mapInsurers.put(dos.getId(), dos.getName());
			TdmPolicyCenterSearchDTO tdmPolicySearchResultDTO = tdmPolicyCenterSearchMapper.converObjectToTdmPolicyCenterSearchDTOForReservation(pcPolicyDOs, reservationDOs, userId,mapCountries,mapInsurers);
			/*List<TdmPolicySearchResultDTO> tdmPolicySearchResultDTOs = tdm
					.converObjectToTdmPolicySearchResultDTO(pcPolicyDOs, reservationDOs, userId);
			TdmPolicySearchDTO tdmPolicySearchDTO = new TdmPolicySearchDTO();
			tdmPolicySearchDTO.setTdmPolicySearchResultDTOs(tdmPolicySearchResultDTOs);*/
			closeGuideWireEntityManager(managerPolicySearch);
			closeUserEntityManager(managerUser);
			logger.info(MessageConstant.TDM_POLICY_SERVICE
					+ MessageConstant.TDM_FTD_SER_POLICY_GET_RESAERV_DATA
					+ MessageConstant.LOG_INFO_RETURN);
			return tdmPolicySearchResultDTO;
		} catch (NullPointerException nullPointerEx) {
			// releaseEntityMgrForRollback(entityManager);
			logger.error(MessageConstant.TDM_POLICY_SERVICE
					+ MessageConstant.TDM_FTD_SER_POLICY_GET_RESAERV_DATA
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new ServiceException(MessageConstant.NULL_POINTER_EXCEPTION, nullPointerEx);
		} catch (DAOException daoEx) {
			// releaseEntityMgrForRollback(entityManager);
			logger.error(MessageConstant.TDM_POLICY_SERVICE
					+ MessageConstant.TDM_FTD_SER_POLICY_GET_RESAERV_DATA
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new ServiceException(daoEx.getErrorCode(), daoEx);
		} catch (Exception otherEx) {
			// releaseEntityMgrForRollback(entityManager);
			logger.error(MessageConstant.TDM_POLICY_SERVICE
					+ MessageConstant.TDM_FTD_SER_POLICY_GET_RESAERV_DATA
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new ServiceException(MessageConstant.SERVICE_EXCEPTION, otherEx);
		}
	
	}

	@Override
	public Long getReservedRecordsCount(Object object, String userId) throws ServiceException{
		try {
			EntityManager managerUser = openUserEntityManager();
			Long cnt = tdmPolicyCenterSearchDAO.getReservedRecordsCount("PC", userId, managerUser);
			closeUserEntityManager(managerUser);
			logger.info(MessageConstant.TDM_POLICY_SERVICE
					+ MessageConstant.TDM_FTD_SER_POLICY_GET_RESERVE_CNT
					+ MessageConstant.LOG_INFO_RETURN);
			return cnt;
		} catch (NullPointerException nullPointerEx) {
			// releaseEntityMgrForRollback(entityManager);
			logger.error(MessageConstant.TDM_POLICY_SERVICE
					+ MessageConstant.TDM_FTD_SER_POLICY_GET_RESERVE_CNT
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new ServiceException(MessageConstant.NULL_POINTER_EXCEPTION, nullPointerEx);
		} catch (DAOException daoEx) {
			// releaseEntityMgrForRollback(entityManager);
			logger.error(MessageConstant.TDM_POLICY_SERVICE
					+ MessageConstant.TDM_FTD_SER_POLICY_GET_RESERVE_CNT
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new ServiceException(daoEx.getErrorCode(), daoEx);
		} catch (Exception otherEx) {
			// releaseEntityMgrForRollback(entityManager);
			logger.error(MessageConstant.TDM_POLICY_SERVICE
					+ MessageConstant.TDM_FTD_SER_POLICY_GET_RESERVE_CNT
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new ServiceException(MessageConstant.SERVICE_EXCEPTION, otherEx);
		}
	}

	@Override
	public Boolean unReservedRecordForUser(String parameter) throws ServiceException{

		logger.info(MessageConstant.TDM_POLICY_SERVICE
				+ MessageConstant.TDM_FTD_SER_POLICY_UNRESERVE_REC
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			EntityManager managerUser = openUserEntityManager();
			boolean bln = tdmPolicyCenterSearchDAO.unReservedRecordForUser(parameter, managerUser);
			closeUserEntityManager(managerUser);
			logger.info(MessageConstant.TDM_POLICY_SERVICE
					+ MessageConstant.TDM_FTD_SER_POLICY_UNRESERVE_REC
					+ MessageConstant.LOG_INFO_RETURN);
			return bln;
		} catch (NullPointerException nullPointerEx) {
			// releaseEntityMgrForRollback(entityManager);
			logger.error(MessageConstant.TDM_POLICY_SERVICE
					+ MessageConstant.TDM_FTD_SER_POLICY_UNRESERVE_REC
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new ServiceException(MessageConstant.NULL_POINTER_EXCEPTION, nullPointerEx);
		} catch (DAOException daoEx) {
			// releaseEntityMgrForRollback(entityManager);
			logger.error(MessageConstant.TDM_POLICY_SERVICE
					+ MessageConstant.TDM_FTD_SER_POLICY_UNRESERVE_REC
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new ServiceException(daoEx.getErrorCode(), daoEx);
		} catch (Exception otherEx) {
			// releaseEntityMgrForRollback(entityManager);
			logger.error(MessageConstant.TDM_POLICY_SERVICE
					+ MessageConstant.TDM_FTD_SER_POLICY_UNRESERVE_REC
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new ServiceException(MessageConstant.SERVICE_EXCEPTION, otherEx);
		}
	
	}

	@Override
	public List<PctlCountryDTO> getCountrycodeValues()throws ServiceException{
		// TODO Auto-generated method stub
		try {
			EntityManager managerPolicySearch = openGuideWireEntityManager();
		List<PctlCountryDO> listResult = tdmPolicyCenterSearchDAO.getCountryCodes(managerPolicySearch);
		List<PctlCountryDTO> listPctlCountryDTO = tdmPolicyCenterSearchMapper.convertCountryDOTOCountryDTOS(listResult);
		
		closeGuideWireEntityManager(managerPolicySearch);
		logger.info(MessageConstant.TDM_POLICY_SERVICE
				+ MessageConstant.TDM_FTD_SER_POLICY_SEARCH_REC
				+ MessageConstant.LOG_INFO_RETURN);
		return listPctlCountryDTO;
	} catch (NullPointerException nullPointerEx) {
		// releaseEntityMgrForRollback(entityManager);
		logger.error(MessageConstant.TDM_POLICY_SERVICE
				+ MessageConstant.TDM_FTD_SER_POLICY_SEARCH_REC
				+ MessageConstant.LOG_ERROR_EXCEPTION);
		throw new ServiceException(MessageConstant.NULL_POINTER_EXCEPTION, nullPointerEx);
	} /*catch (DAOException daoEx) {
		// releaseEntityMgrForRollback(entityManager);
		logger.error(MessageConstant.TDM_POLICY_SERVICE
				+ MessageConstant.TDM_FTD_SER_POLICY_SEARCH_REC
				+ MessageConstant.LOG_ERROR_EXCEPTION);
		throw new ServiceException(daoEx.getErrorCode(), daoEx);
	} */catch (Exception otherEx) {
		// releaseEntityMgrForRollback(entityManager);
		logger.error(MessageConstant.TDM_POLICY_SERVICE
				+ MessageConstant.TDM_FTD_SER_POLICY_SEARCH_REC
				+ MessageConstant.LOG_ERROR_EXCEPTION);
		throw new ServiceException(MessageConstant.SERVICE_EXCEPTION, otherEx);
	}
	}

	@Override
	public List<PctlStateDTO> getStatecodeValues()throws ServiceException{
		try {
			EntityManager managerPolicySearch = openGuideWireEntityManager();
		List<PctlStateDO> listResult = tdmPolicyCenterSearchDAO.getStateCodes(managerPolicySearch);
		List<PctlStateDTO> listPctlCountryDTO = tdmPolicyCenterSearchMapper.convertgetStateCodesDOTOgetStateCodesDTOS(listResult);
		
		closeGuideWireEntityManager(managerPolicySearch);
		logger.info(MessageConstant.TDM_POLICY_SERVICE
				+ MessageConstant.TDM_FTD_SER_POLICY_SEARCH_REC
				+ MessageConstant.LOG_INFO_RETURN);
		return listPctlCountryDTO;
	} catch (NullPointerException nullPointerEx) {
		// releaseEntityMgrForRollback(entityManager);
		logger.error(MessageConstant.TDM_POLICY_SERVICE
				+ MessageConstant.TDM_FTD_SER_POLICY_SEARCH_REC
				+ MessageConstant.LOG_ERROR_EXCEPTION);
		throw new ServiceException(MessageConstant.NULL_POINTER_EXCEPTION, nullPointerEx);
	} /*catch (DAOException daoEx) {
		// releaseEntityMgrForRollback(entityManager);
		logger.error(MessageConstant.TDM_POLICY_SERVICE
				+ MessageConstant.TDM_FTD_SER_POLICY_SEARCH_REC
				+ MessageConstant.LOG_ERROR_EXCEPTION);
		throw new ServiceException(daoEx.getErrorCode(), daoEx);
	} */catch (Exception otherEx) {
		// releaseEntityMgrForRollback(entityManager);
		logger.error(MessageConstant.TDM_POLICY_SERVICE
				+ MessageConstant.TDM_FTD_SER_POLICY_SEARCH_REC
				+ MessageConstant.LOG_ERROR_EXCEPTION);
		throw new ServiceException(MessageConstant.SERVICE_EXCEPTION, otherEx);
	}
	}

	@Override
	public List<PctlPolicycontactroleDTO> getInsurerTypeValues()throws ServiceException{
		try {
			EntityManager managerPolicySearch = openGuideWireEntityManager();
		List<PctlPolicycontactroleDO> listResult = tdmPolicyCenterSearchDAO.getInsurerTypes(managerPolicySearch);
		List<PctlPolicycontactroleDTO> listPctlPolicycontactroleDTO = tdmPolicyCenterSearchMapper.convertPctlPolicycontactroleDOTOPctlPolicycontactroleDTOS(listResult);
		
		closeGuideWireEntityManager(managerPolicySearch);
		logger.info(MessageConstant.TDM_POLICY_SERVICE
				+ MessageConstant.TDM_FTD_SER_POLICY_SEARCH_REC
				+ MessageConstant.LOG_INFO_RETURN);
		return listPctlPolicycontactroleDTO;
	} catch (NullPointerException nullPointerEx) {
		// releaseEntityMgrForRollback(entityManager);
		logger.error(MessageConstant.TDM_POLICY_SERVICE
				+ MessageConstant.TDM_FTD_SER_POLICY_SEARCH_REC
				+ MessageConstant.LOG_ERROR_EXCEPTION);
		throw new ServiceException(MessageConstant.NULL_POINTER_EXCEPTION, nullPointerEx);
	} /*catch (DAOException daoEx) {
		// releaseEntityMgrForRollback(entityManager);
		logger.error(MessageConstant.TDM_POLICY_SERVICE
				+ MessageConstant.TDM_FTD_SER_POLICY_SEARCH_REC
				+ MessageConstant.LOG_ERROR_EXCEPTION);
		throw new ServiceException(daoEx.getErrorCode(), daoEx);
	} */catch (Exception otherEx) {
		// releaseEntityMgrForRollback(entityManager);
		logger.error(MessageConstant.TDM_POLICY_SERVICE
				+ MessageConstant.TDM_FTD_SER_POLICY_SEARCH_REC
				+ MessageConstant.LOG_ERROR_EXCEPTION);
		throw new ServiceException(MessageConstant.SERVICE_EXCEPTION, otherEx);
	}
	}
}
