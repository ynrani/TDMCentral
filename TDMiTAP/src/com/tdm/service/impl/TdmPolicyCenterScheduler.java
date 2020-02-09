/*
 * Object Name : TdmPolicyCenterScheduler.java
 * Modification Block
 * ------------------------------------------------------------------
 * S.No.	Name 			Date			Bug_Fix_No			Desc
 * ------------------------------------------------------------------
 * 	1.	  vkrish14		9:16:57 PM				Created
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
import javax.persistence.EntityManagerFactory;

import org.apache.log4j.Logger;

import com.tdm.constant.MessageConstant;
import com.tdm.dao.impl.TdmPolicyCenterSearchSchedulerDAOImpl;
import com.tdm.exception.DAOException;
import com.tdm.exception.ServiceException;
import com.tdm.model.DO.PctlCountryDO;
import com.tdm.model.DO.PctlPolicycontactroleDO;
import com.tdm.model.DO.PctlStateDO;
import com.tdm.model.DO.TdmReservationDO;
import com.tdm.model.DTO.PctlCountryDTO;
import com.tdm.model.DTO.PctlStateDTO;
import com.tdm.model.DTO.TdmPolicyCenterSearchDTO;
import com.tdm.model.mapper.impl.TdmPolicyCenterSearchSchedulerMapperImpl;

/**
 * @author vkrish14
 *
 */
public class TdmPolicyCenterScheduler{
	/*private TdmPolicyCenterScheduler runMeTask;

	public void setRunMeTask(TdmPolicyCenterScheduler runMeTask) {
		this.runMeTask = runMeTask;
	}

	protected void executeInternal(JobExecutionContext context)
 throws JobExecutionException{
		TdmPolicyCenterSearchDTO input = new TdmPolicyCenterSearchDTO();
		input.setProductCode("BusinessAuto");
		TdmPolicyCenterSearchDTO tdmPolicyCenterSearchDTOtemp;
		try {
			tdmPolicyCenterSearchDTOtemp = runMeTask.searchPolicyCenter(input, 0, 10, true, "demo");
			if (tdmPolicyCenterSearchDTOtemp != null
					&& tdmPolicyCenterSearchDTOtemp.getListTdmPolicyCenterSearchResultDTO() != null
					&& !tdmPolicyCenterSearchDTOtemp.getListTdmPolicyCenterSearchResultDTO().isEmpty()) {
				for (TdmPolicyCenterSearchResultDTO tdmPolicyCenterSearchResultDTO : tdmPolicyCenterSearchDTOtemp
						.getListTdmPolicyCenterSearchResultDTO()) {
					System.out.println(tdmPolicyCenterSearchResultDTO.getPolicynumber());
				}
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}*/


	private static Logger logger = Logger.getLogger(TdmPolicyCenterScheduler.class);
	
	//@Autowired
	private TdmPolicyCenterSearchSchedulerDAOImpl tdmPolicyCenterSearchDAO;
	//@Autowired
	private TdmPolicyCenterSearchSchedulerMapperImpl tdmPolicyCenterSearchMapper;
	/*@Autowired
	private MessageSource messageSource;*/
	
	private EntityManagerFactory factoryGuidewireUser;
	private EntityManagerFactory factoryUser;
	
	
	public int saveReservedData(TdmPolicyCenterSearchDTO tdmPolicyCenterSearchDTO, String userName,
			String enviro) throws ServiceException{
		try {
			List<TdmReservationDO> list = null;

			if (null != tdmPolicyCenterSearchDTO.getListTdmPolicyCenterSearchResultDTO()
					&& 0 < tdmPolicyCenterSearchDTO.getListTdmPolicyCenterSearchResultDTO()
							.size()) {
				EntityManager managerCsaaUser = openUserEntityManager();
				List<TdmReservationDO> reservationDOs = getTdmPolicyCenterSearchMapper()
						.converTdmPolicyCenterSearchDTOToTdmReservationDO(
								tdmPolicyCenterSearchDTO.getListTdmPolicyCenterSearchResultDTO(),
								userName,tdmPolicyCenterSearchDTO.getSearchCriti());
				list = getTdmPolicyCenterSearchDAO().saveReservedData(reservationDOs,
						tdmPolicyCenterSearchDTO.getTestCaseId(),
						tdmPolicyCenterSearchDTO.getTestCaseName(), managerCsaaUser);
				closeUserEntityManager(managerCsaaUser);
			}

			return list.size();
		} catch (NullPointerException nullPointerEx) {
			logger.error("MessageConstant.NULL_POINTER_EXCEPTION", nullPointerEx);
			throw new ServiceException("Service Excception");

		} catch (DAOException daoEx) {
			logger.error("DAO exception", daoEx);
			throw new ServiceException(daoEx.getErrorCode(), daoEx);

		} catch (Exception otherEx) {
			logger.error("Service exception", otherEx);
			throw new ServiceException("Service Excception");
		}

	}

	
	public TdmPolicyCenterSearchDTO searchPolicyCenter(
			TdmPolicyCenterSearchDTO tdmPolicyCenterSearchDTO, int offSet, int recordsperpage,
			boolean pageNationOnOffFlag, String userName) throws ServiceException{

		logger.info(MessageConstant.TDM_POLICY_SERVICE
				+ MessageConstant.TDM_FTD_SER_POLICY_SEARCH_REC
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			EntityManager managerUser = openUserEntityManager();
			EntityManager managerPolicySearch = openGuideWireEntityManager();
			List<TdmReservationDO> reservationDOs1 = getTdmPolicyCenterSearchDAO().getReservedRecords(
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
			listResult.addAll(getTdmPolicyCenterSearchDAO().searchPolicyCenterRecords(tdmPolicyCenterSearchDTO, offSet, recordsperpage, pageNationOnOffFlag, policys,false, managerPolicySearch,false));
			if(listResult != null && !listResult.isEmpty()){
				for(Object[] obj : listResult){
					if(!"Unassigned".equalsIgnoreCase(String.valueOf(obj[0])))
					setPolicies.add(String.valueOf(obj[0]));
				}
				while (setPolicies.size() <= recordsperpage) {
					offSet += recordsperpage;
					List<Object[]> lsttemp = getTdmPolicyCenterSearchDAO().searchPolicyCenterRecords(tdmPolicyCenterSearchDTO, offSet, recordsperpage, pageNationOnOffFlag, policys,false, managerPolicySearch,false);
					if (lsttemp == null || lsttemp.isEmpty())
						break;
					for(Object[] obj : lsttemp){
						if(!"Unassigned".equalsIgnoreCase(String.valueOf(obj[0])))
						setPolicies.add(String.valueOf(obj[0]));
						if(setPolicies.size() <= recordsperpage){
							listResult.add(obj);
							if(setPolicies.size() == recordsperpage)
							break;
						}
					}
					//listResult.addAll(lsttemp);
				}
			}
			/*List<TdmReservationDO> reservationDOs = tdmPolicyCenterSearchDAO.getReservedRecords(userName,
					managerUser);*/
			List<PctlCountryDO> countryDos = getTdmPolicyCenterSearchDAO().getCountryCodes(managerPolicySearch);
			Map<Long,String> mapCountries = new HashMap<Long,String>();
			for(PctlCountryDO dos : countryDos)
				mapCountries.put(dos.getId(), dos.getName());
			List<PctlPolicycontactroleDO> policyContactDos = tdmPolicyCenterSearchDAO.getInsurerTypes(managerPolicySearch);
			Map<Long,String> mapInsurers = new HashMap<Long,String>();
			for(PctlPolicycontactroleDO dos : policyContactDos)
				mapInsurers.put(dos.getId(), dos.getName());
			TdmPolicyCenterSearchDTO tdmPolicyCenterSearchDTOResult = getTdmPolicyCenterSearchMapper().converObjectToTdmPolicyCenterSearchDTO(listResult, null, userName,mapCountries,mapInsurers);
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

	
	
	public TdmPolicyCenterSearchDTO searchPolicyRecordsByPolicySearchNew(
			TdmPolicyCenterSearchDTO tdmPolicyCenterSearchDTO, int offSet, int recordsperpage,
			boolean b, String userName){
		// TODO Auto-generated method stub
		return null;
	}

	
	public Long getTotalRecordsCount(TdmPolicyCenterSearchDTO tdmPolicyCenterSearchDTO,String userName)throws ServiceException{
		// TODO Auto-generated method stub
		try {
			EntityManager managerPolicySearch = openGuideWireEntityManager();
			EntityManager managerUser = openUserEntityManager();
			List<TdmReservationDO> reservationDOs1 = getTdmPolicyCenterSearchDAO().getReservedRecords(
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
		List<Object[]> listResult = getTdmPolicyCenterSearchDAO().searchPolicyCenterRecords(tdmPolicyCenterSearchDTO, 0, 0, false, policys,true, managerPolicySearch,false);
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

	
	public TdmPolicyCenterSearchDTO getReservedRecordForUser(String userId, int offSet,
			int recordsperpage, boolean b,List<String> policyNumbers) throws ServiceException{

		logger.info(MessageConstant.TDM_POLICY_SERVICE
				+ MessageConstant.TDM_FTD_SER_POLICY_GET_RESAERV_DATA
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			EntityManager managerUser = openUserEntityManager();
			EntityManager managerPolicySearch = openGuideWireEntityManager();
			List<TdmReservationDO> reservationDOs = getTdmPolicyCenterSearchDAO().getReservedRecords(
					"PC", userId, offSet, recordsperpage, false,policyNumbers, managerUser);
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
			List<Object[]> pcPolicyDOs = getTdmPolicyCenterSearchDAO().getPolicyRecords(policys,
					managerPolicySearch);
			List<PctlCountryDO> countryDos = getTdmPolicyCenterSearchDAO().getCountryCodes(managerPolicySearch);
			Map<Long,String> mapCountries = new HashMap<Long,String>();
			for(PctlCountryDO dos : countryDos)
				mapCountries.put(dos.getId(), dos.getName());
			List<PctlPolicycontactroleDO> policyContactDos = tdmPolicyCenterSearchDAO.getInsurerTypes(managerPolicySearch);
			Map<Long,String> mapInsurers = new HashMap<Long,String>();
			for(PctlPolicycontactroleDO dos : policyContactDos)
				mapInsurers.put(dos.getId(), dos.getName());
			TdmPolicyCenterSearchDTO tdmPolicySearchResultDTO = getTdmPolicyCenterSearchMapper().converObjectToTdmPolicyCenterSearchDTOForReservation(pcPolicyDOs, reservationDOs, userId,mapCountries,mapInsurers);
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

	
	public Long getReservedRecordsCount(Object object, String userId) throws ServiceException{
		try {
			EntityManager managerUser = openUserEntityManager();
			Long cnt = getTdmPolicyCenterSearchDAO().getReservedRecordsCount("PC", userId, managerUser);
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

	
	public Boolean unReservedRecordForUser(String parameter) throws ServiceException{

		logger.info(MessageConstant.TDM_POLICY_SERVICE
				+ MessageConstant.TDM_FTD_SER_POLICY_UNRESERVE_REC
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			EntityManager managerUser = openUserEntityManager();
			boolean bln = getTdmPolicyCenterSearchDAO().unReservedRecordForUser(parameter, managerUser);
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

	
	public List<PctlCountryDTO> getCountrycodeValues()throws ServiceException{
		// TODO Auto-generated method stub
		try {
			EntityManager managerPolicySearch = openGuideWireEntityManager();
		List<PctlCountryDO> listResult = getTdmPolicyCenterSearchDAO().getCountryCodes(managerPolicySearch);
		List<PctlCountryDTO> listPctlCountryDTO = getTdmPolicyCenterSearchMapper().convertCountryDOTOCountryDTOS(listResult);
		
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

	
	public List<PctlStateDTO> getStatecodeValues()throws ServiceException{
		try {
			EntityManager managerPolicySearch = openGuideWireEntityManager();
		List<PctlStateDO> listResult = getTdmPolicyCenterSearchDAO().getStateCodes(managerPolicySearch);
		List<PctlStateDTO> listPctlCountryDTO = getTdmPolicyCenterSearchMapper().convertgetStateCodesDOTOgetStateCodesDTOS(listResult);
		
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


	public TdmPolicyCenterSearchSchedulerDAOImpl getTdmPolicyCenterSearchDAO(){
		return tdmPolicyCenterSearchDAO;
	}


	public void setTdmPolicyCenterSearchDAO(TdmPolicyCenterSearchSchedulerDAOImpl tdmPolicyCenterSearchDAO){
		this.tdmPolicyCenterSearchDAO = tdmPolicyCenterSearchDAO;
	}


	public TdmPolicyCenterSearchSchedulerMapperImpl getTdmPolicyCenterSearchMapper(){
		return tdmPolicyCenterSearchMapper;
	}


	public void setTdmPolicyCenterSearchMapper(TdmPolicyCenterSearchSchedulerMapperImpl tdmPolicyCenterSearchMapper){
		this.tdmPolicyCenterSearchMapper = tdmPolicyCenterSearchMapper;
	}


	public EntityManagerFactory getFactoryGuidewireUser(){
		return factoryGuidewireUser;
	}


	public void setFactoryGuidewireUser(EntityManagerFactory factoryGuidewireUser){
		this.factoryGuidewireUser = factoryGuidewireUser;
	}


	public EntityManagerFactory getFactoryUser(){
		return factoryUser;
	}


	public void setFactoryUser(EntityManagerFactory factoryUser){
		this.factoryUser = factoryUser;
	}
	
	public EntityManager openUserEntityManager() {
		EntityManager managerUser = factoryUser.createEntityManager();

		return managerUser;
	}

	public void closeUserEntityManager(EntityManager managerUser) {
		managerUser.close();
	}
	
	public EntityManager openGuideWireEntityManager() {
		EntityManager managerGuidewire = factoryGuidewireUser.createEntityManager();

		return managerGuidewire;
	}

	public void closeGuideWireEntityManager(EntityManager managerGuidewire) {
		managerGuidewire.close();
	}

}
