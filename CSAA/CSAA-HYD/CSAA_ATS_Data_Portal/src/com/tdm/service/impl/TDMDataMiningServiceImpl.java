/**
 * 
 */
package com.tdm.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tdm.dao.TDMDataMiningDAO;
import com.tdm.exception.DAOException;
import com.tdm.exception.ServiceException;
import com.tdm.model.DO.PolicySummaryStg;
import com.tdm.model.DO.TdmReservationDO;
import com.tdm.model.DTO.TDMAutoPolicySearchDTO;
import com.tdm.model.DTO.TDMPolicyPropertyNewSearchDTO;
import com.tdm.model.DTO.TdmPolicyAutoSearchResultDTO;
import com.tdm.model.DTO.TdmPolicyPropertySearchResultDTO;
import com.tdm.model.mapper.TdmPolicyAutoPropSearchMapper;
import com.tdm.service.TDMDataMiningService;

/**
 * @author ramakell
 *
 */

@Component
@Service("tdmFineTestDataService")
@Transactional(propagation = Propagation.REQUIRED)
public class TDMDataMiningServiceImpl extends TdmBaseServiceImpl implements
		TDMDataMiningService {
	final static Logger logger = Logger
			.getLogger(TDMDataMiningServiceImpl.class);

	@Autowired
	TdmPolicyAutoPropSearchMapper tdmPolicyAutoPropSearchMapper;

	@Autowired
	TDMDataMiningDAO dataMinningDAO;

	@Autowired
	private MessageSource messageSource;

	public TDMAutoPolicySearchDTO searchAutoPolicy(
			TDMAutoPolicySearchDTO atsSearchDTO, int offSet,
			int recordsperpage, boolean pageNationOnOffFlag, String userName)
			throws ServiceException {
		String searchType = "CSAA_AU";
		String policytype = "AUTO";

		List<TdmPolicyAutoSearchResultDTO> tdmPolicyPropertySearchDTOList = null;
		List<PolicySummaryStg> policySummaryStgDOs = null;
		List<TdmReservationDO> reservationDOs1 = null;
		try {

			EntityManager managerCsaaUser = openUserEntityManager();

			/**
			 * 1. going to fetch reserved records
			 */
			reservationDOs1 = dataMinningDAO.getReservedRecords(userName,
					searchType, managerCsaaUser);
			StringBuffer policyProps = new StringBuffer();
			if (null != reservationDOs1 && 0 < reservationDOs1.size()) {
				for (TdmReservationDO reservationDO : reservationDOs1) {
					if (null != reservationDO.getReserveDataType()
							&& reservationDO.getReserveDataType()
									.equalsIgnoreCase(searchType)) {
						policyProps.append(",'"
								+ reservationDO.getReserveDataId() + "'");
					}
				}
			}

			/**
			 * 2. going to fetch remains records apart from reserved based on
			 * criteria
			 */
			policySummaryStgDOs = dataMinningDAO.searchAutoPolicy(atsSearchDTO,
					offSet, recordsperpage, pageNationOnOffFlag, policyProps,
					policytype, managerCsaaUser);

			/**
			 * 3. going to convert DO to DTO class
			 */
			tdmPolicyPropertySearchDTOList = tdmPolicyAutoPropSearchMapper
					.converPolicysummaryToTdmPolicyPropertySearchDTONew(
							policySummaryStgDOs, atsSearchDTO, reservationDOs1,
							userName);

			atsSearchDTO
					.setTdmPolicyAutoSearchResultDTOList(tdmPolicyPropertySearchDTOList);

			// TODO /*dataMinningDAO
			// .getTDMReservedTestDataListPerUser(atsSearchDTO);*/

			closeCsaaUserEntityManager(managerCsaaUser);
			return atsSearchDTO;
		} catch (NullPointerException nullPointerEx) {
			logger.error("MessageConstant.NULL_POINTER_EXCEPTION",
					nullPointerEx);
			throw new ServiceException(messageSource.getMessage(
					"NULL_POINTER_EXCEPTION", null, null), nullPointerEx);

		} catch (DAOException daoEx) {
			logger.error("DAO exception", daoEx);
			throw new ServiceException(daoEx.getErrorCode(), daoEx);

		} catch (Exception otherEx) {
			logger.error("Service exception", otherEx);
			throw new ServiceException(messageSource.getMessage(
					"SERVICE_EXCEPTION", null, null), otherEx);
		}
	}

	@Override
	public int saveReservedDataForAutoPolicy(
			TDMAutoPolicySearchDTO tdmPolicyAutoSearchDTO, String userName,
			String enviro) throws ServiceException {

		try {
			EntityManager managerCsaaUser = openCsaaUserEntityManager();
			List<TdmReservationDO> list = null;

			List<PolicySummaryStg> policySummaryStgDOs = dataMinningDAO
					.getPoliciesToReserve(tdmPolicyAutoSearchDTO,
							managerCsaaUser);

			List<TdmPolicyAutoSearchResultDTO> tdmPolicyPropertySearchDTOList = tdmPolicyAutoPropSearchMapper
					.converPolicysummaryToTdmPolicySearchDTONew(
							policySummaryStgDOs, tdmPolicyAutoSearchDTO,
							userName);

			tdmPolicyAutoSearchDTO
					.setTdmPolicyAutoSearchResultDTOList(tdmPolicyPropertySearchDTOList);

			if (null != tdmPolicyAutoSearchDTO
					.getTdmPolicyAutoSearchResultDTOList()
					&& 0 < tdmPolicyAutoSearchDTO
							.getTdmPolicyAutoSearchResultDTOList().size()) {

				List<TdmReservationDO> reservationDOs = tdmPolicyAutoPropSearchMapper
						.converTdmPolicyAutoSearchResultDTOToTdmReservationDO(
								tdmPolicyAutoSearchDTO
										.getTdmPolicyAutoSearchResultDTOList(),
								tdmPolicyAutoSearchDTO.getSearchCriti(),
								userName, enviro);
				list = dataMinningDAO.saveReservedData(reservationDOs,
						tdmPolicyAutoSearchDTO.getTestCaseId(),
						tdmPolicyAutoSearchDTO.getTestCaseName(),
						managerCsaaUser);
				closeCsaaUserEntityManager(managerCsaaUser);
			}
			return list.size();
		} catch (NullPointerException nullPointerEx) {
			logger.error("MessageConstant.NULL_POINTER_EXCEPTION",
					nullPointerEx);
			throw new ServiceException(messageSource.getMessage(
					"NULL_POINTER_EXCEPTION", null, null), nullPointerEx);

		} catch (Exception otherEx) {
			logger.error("Service exception", otherEx);
			throw new ServiceException(messageSource.getMessage(
					"SERVICE_EXCEPTION", null, null), otherEx);
		}
	}

	@Override
	public int saveReservedDataForPropertyPolicy(
			TDMPolicyPropertyNewSearchDTO tdmPolicyPropertySearchDTO,
			String userName, String enviro) throws ServiceException {

		try {
			List<TdmReservationDO> list = null;

			EntityManager managerCsaaUser = openCsaaUserEntityManager();

			List<PolicySummaryStg> policySummaryStgDOs = dataMinningDAO
					.getPoliciesToReserve(tdmPolicyPropertySearchDTO,
							managerCsaaUser);

			List<TdmPolicyPropertySearchResultDTO> tdmPolicyPropertySearchDTOList = tdmPolicyAutoPropSearchMapper
					.converPolicysummaryToTdmPolicyPropertySearchDTO(
							policySummaryStgDOs, tdmPolicyPropertySearchDTO,
							userName);

			tdmPolicyPropertySearchDTO
					.setTdmPolicyPropertySearchResultDTOList(tdmPolicyPropertySearchDTOList);

			if (null != tdmPolicyPropertySearchDTO
					.getTdmPolicyPropertySearchResultDTOList()
					&& 0 < tdmPolicyPropertySearchDTO
							.getTdmPolicyPropertySearchResultDTOList().size()) {

				List<TdmReservationDO> reservationDOs = tdmPolicyAutoPropSearchMapper
						.converTdmPolicyPropertySearchDTOToTdmReservationDO(
								tdmPolicyPropertySearchDTO
										.getTdmPolicyPropertySearchResultDTOList(),
								tdmPolicyPropertySearchDTO.getSearchCriti(),
								userName, enviro);
				list = dataMinningDAO.saveReservedData(reservationDOs,
						tdmPolicyPropertySearchDTO.getTestCaseId(),
						tdmPolicyPropertySearchDTO.getTestCaseName(),
						managerCsaaUser);
				closeCsaaUserEntityManager(managerCsaaUser);
			}

			return list.size();
		} catch (NullPointerException nullPointerEx) {
			logger.error("MessageConstant.NULL_POINTER_EXCEPTION",
					nullPointerEx);
			throw new ServiceException(messageSource.getMessage(
					"NULL_POINTER_EXCEPTION", null, null), nullPointerEx);

		} catch (DAOException daoEx) {
			logger.error("DAO exception", daoEx);
			throw new ServiceException(daoEx.getErrorCode(), daoEx);

		} catch (Exception otherEx) {
			logger.error("Service exception", otherEx);
			throw new ServiceException(messageSource.getMessage(
					"SERVICE_EXCEPTION", null, null), otherEx);
		}

	}

	@Override
	public TDMPolicyPropertyNewSearchDTO searchPropertyPolicy(
			TDMPolicyPropertyNewSearchDTO tdmPolicyPropertySearchDTO,
			int offSet, int recordsperpage, boolean pageNationOnOffFlag,
			String userName) throws ServiceException {
		String searchType = "CSAA_PO";
		String policytype = "Property";

		List<TdmPolicyPropertySearchResultDTO> tdmPolicyPropertySearchDTOList = null;
		List<PolicySummaryStg> policySummaryStgDOs = null;
		List<TdmReservationDO> reservationDOs1 = null;
		try {

			EntityManager managerCsaaUser = openUserEntityManager();

			/**
			 * 1. going to fetch reserved records //
			 */
			reservationDOs1 = dataMinningDAO.getReservedRecords(userName,
					searchType, managerCsaaUser);
			StringBuffer policyProps = new StringBuffer();
			if (null != reservationDOs1 && 0 < reservationDOs1.size()) {
				for (TdmReservationDO reservationDO : reservationDOs1) {
					if (null != reservationDO.getReserveDataType()
							&& reservationDO.getReserveDataType()
									.equalsIgnoreCase(searchType)) {
						policyProps.append(",'"
								+ reservationDO.getReserveDataId() + "'");
					}
				}
			}

			/**
			 * 2. going to fetch remains records apart from reserved based on
			 * criteria
			 */
			policySummaryStgDOs = dataMinningDAO.searchPropertyPolicy(
					tdmPolicyPropertySearchDTO, offSet, recordsperpage,
					pageNationOnOffFlag, policyProps, policytype,
					managerCsaaUser);

			/**
			 * 3. going to convert DO to DTO class
			 */
			tdmPolicyPropertySearchDTOList = tdmPolicyAutoPropSearchMapper
					.converPolicysummaryToTdmPolicyPropertySearchDTONew(
							policySummaryStgDOs, tdmPolicyPropertySearchDTO,
							reservationDOs1, userName);

			tdmPolicyPropertySearchDTO
					.setTdmPolicyPropertySearchResultDTOList(tdmPolicyPropertySearchDTOList);

			// List<TDMReservedTestDataDTO> tdmReservedTestDataDTOs = new
			// ArrayList<TDMReservedTestDataDTO>();
			// TDMReservedTestDataDTO a1 = new TDMReservedTestDataDTO();
			// a1.setCreatedDate(new Date());
			// a1.setExipryDt(new Date());
			// a1.setDayToExpire(1);
			// a1.setNoOfRecords(10);
			// a1.setTestCaseId("TEST1");
			// a1.setTestCaseName("TST001");
			// a1.setUserId("ramakell");
			//
			// TDMReservedTestDataDTO a2 = new TDMReservedTestDataDTO();
			// a2.setCreatedDate(new Date());
			// a2.setExipryDt(new Date());
			// a2.setDayToExpire(1);
			// a2.setNoOfRecords(5);
			// a2.setTestCaseId("TEST2");
			// a2.setTestCaseName("TST002");
			// a2.setUserId("Narsimha");
			// tdmReservedTestDataDTOs.add(a1);
			// tdmReservedTestDataDTOs.add(a2);

			// List<TDMReservedTestDataDTO> tdmReservedTestDataDTOs =
			// tdmPolicyAutoPropSearchDAO
			// .getTDMReservedTestDataListPerUser(tdmPolicyPropertySearchDTO);

			closeCsaaUserEntityManager(managerCsaaUser);
			return tdmPolicyPropertySearchDTO;
		} catch (NullPointerException nullPointerEx) {
			logger.error("MessageConstant.NULL_POINTER_EXCEPTION",
					nullPointerEx);
			throw new ServiceException(messageSource.getMessage(
					"NULL_POINTER_EXCEPTION", null, null), nullPointerEx);

		} catch (DAOException daoEx) {
			logger.error("DAO exception", daoEx);
			throw new ServiceException(daoEx.getErrorCode(), daoEx);

		} catch (Exception otherEx) {
			logger.error("Service exception", otherEx);
			throw new ServiceException(messageSource.getMessage(
					"SERVICE_EXCEPTION", null, null), otherEx);
		}
	}

	@Override
	@Transactional
	public List<TdmReservationDO> saveReservedData(
			List<TdmReservationDO> reservationDOList, String testCaseId,
			String testCaseName, EntityManager managerCsaaUser)
			throws DAOException {
		List<TdmReservationDO> reservationDOs = null;
		try {
			if (null != reservationDOList && 0 < reservationDOList.size()) {
				managerCsaaUser.getTransaction().begin();
				reservationDOs = new ArrayList<TdmReservationDO>();
				for (TdmReservationDO reservationDO : reservationDOList) {
					if (null != reservationDO
							&& 0 == reservationDO.getRecordId()) {
						reservationDO.setTestCaseId(testCaseId);
						reservationDO.setTestCaseName(testCaseName);
						reservationDO = managerCsaaUser.merge(reservationDO);
						reservationDOs.add(reservationDO);
					}
				}
				managerCsaaUser.getTransaction().commit();
			}
		} catch (IllegalStateException illegalStateEx) {
			logger.error("MessageConstant.NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION"
					+ illegalStateEx);
			throw new DAOException(messageSource.getMessage(
					"NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION", null, null),
					illegalStateEx);
		} catch (IllegalArgumentException illegalArgEx) {
			logger.error("MessageConstant.INVALID_QUERY_EXCEPTION"
					+ illegalArgEx);
			throw new DAOException(messageSource.getMessage(
					"INVALID_QUERY_EXCEPTION", null, null), illegalArgEx);
		} catch (NullPointerException nullPointerEx) {
			logger.error("MessageConstant.NULL_POINTER_EXCEPTION"
					+ nullPointerEx);
			throw new DAOException(messageSource.getMessage(
					"NULL_POINTER_EXCEPTION", null, null), nullPointerEx);
		} catch (Exception otherEx) {
			logger.error("MessageConstant.DATABASE_EXCEPTION" + otherEx);
			throw new DAOException(messageSource.getMessage(
					"DATABASE_EXCEPTION", null, null), otherEx);
		}
		return reservationDOs;
	}

	public TDMPolicyPropertyNewSearchDTO getTDMReservedPropertyTestDataListPerUser(
			TDMPolicyPropertyNewSearchDTO tdmPolicyPropertySearchDTO)
			throws DAOException {
		try {
			tdmPolicyPropertySearchDTO = dataMinningDAO
					.getTDMReservedPropertyTestDataListPerUser(tdmPolicyPropertySearchDTO);
		} catch (Exception otherEx) {
			logger.error("MessageConstant.DATABASE_EXCEPTION" + otherEx);
			throw new DAOException(messageSource.getMessage(
					"DATABASE_EXCEPTION", null, null), otherEx);
		}
		return tdmPolicyPropertySearchDTO;
	}

	public TDMAutoPolicySearchDTO getTDMReservedAutoTestDataListPerUser(
			TDMAutoPolicySearchDTO tdmPolicyPropertySearchDTO)
			throws DAOException {
		try {
			tdmPolicyPropertySearchDTO = dataMinningDAO
					.getTDMReservedAutoTestDataListPerUser(tdmPolicyPropertySearchDTO);
		} catch (Exception otherEx) {
			logger.error("MessageConstant.DATABASE_EXCEPTION" + otherEx);
			throw new DAOException(messageSource.getMessage(
					"DATABASE_EXCEPTION", null, null), otherEx);
		}
		return tdmPolicyPropertySearchDTO;
	}

	public int getTDMAutoPolicyReservationPerUser(
			TDMAutoPolicySearchDTO tdmPolicyPropertySearchDTO)
			throws DAOException {
		try {
			return dataMinningDAO
					.getTDMAutoPolicyReservationPerUser(tdmPolicyPropertySearchDTO);
		} catch (Exception otherEx) {
			logger.error("MessageConstant.DATABASE_EXCEPTION" + otherEx);
			throw new DAOException(messageSource.getMessage(
					"DATABASE_EXCEPTION", null, null), otherEx);
		}

	}

	public int getTDMPropertyPolicyReservationPerUser(
			TDMAutoPolicySearchDTO tdmPolicyPropertySearchDTO)
			throws DAOException {
		try {
			return dataMinningDAO
					.getTDMPropertyPolicyReservationPerUser(tdmPolicyPropertySearchDTO);
		} catch (Exception otherEx) {
			logger.error("MessageConstant.DATABASE_EXCEPTION" + otherEx);
			throw new DAOException(messageSource.getMessage(
					"DATABASE_EXCEPTION", null, null), otherEx);
		}

	}

	@Override
	public int getPoliciesToExport(
			TDMAutoPolicySearchDTO tdmPolicyAutoSearchDTO, String userName)
			throws ServiceException {

		try {
			EntityManager managerCsaaUser = openCsaaUserEntityManager();
			List<TdmReservationDO> list = null;

			List<PolicySummaryStg> policySummaryStgDOs = dataMinningDAO
					.getPoliciesToReserve(tdmPolicyAutoSearchDTO,
							managerCsaaUser);

			List<TdmPolicyAutoSearchResultDTO> tdmPolicyPropertySearchDTOList = tdmPolicyAutoPropSearchMapper
					.converPolicysummaryToTdmPolicySearchDTONew(
							policySummaryStgDOs, tdmPolicyAutoSearchDTO,
							userName);
			tdmPolicyAutoSearchDTO
					.setTdmPolicyAutoSearchResultDTOList(tdmPolicyPropertySearchDTOList);

			return 0;
		} catch (NullPointerException nullPointerEx) {
			logger.error("MessageConstant.NULL_POINTER_EXCEPTION",
					nullPointerEx);
			throw new ServiceException(messageSource.getMessage(
					"NULL_POINTER_EXCEPTION", null, null), nullPointerEx);

		} catch (Exception otherEx) {
			logger.error("Service exception", otherEx);
			throw new ServiceException(messageSource.getMessage(
					"SERVICE_EXCEPTION", null, null), otherEx);
		}
	}

	@Override
	public int getPropertyPoliciesToExport(
			TDMPolicyPropertyNewSearchDTO tdmPolicyAutoSearchDTO,
			String userName) throws ServiceException {
		try {
			EntityManager managerCsaaUser = openCsaaUserEntityManager();
			List<TdmReservationDO> list = null;

			List<PolicySummaryStg> policySummaryStgDOs = dataMinningDAO
					.getPoliciesToReserve(tdmPolicyAutoSearchDTO,
							managerCsaaUser);

			List<TdmPolicyPropertySearchResultDTO> tdmPolicyPropertySearchDTOList = tdmPolicyAutoPropSearchMapper
					.convertPropertPolicysummaryToTdmPolicySearchDTONew(
							policySummaryStgDOs, tdmPolicyAutoSearchDTO,
							userName);
			tdmPolicyAutoSearchDTO
					.setTdmPolicyPropertySearchResultDTOList(tdmPolicyPropertySearchDTOList);

			return 0;
		} catch (NullPointerException nullPointerEx) {
			logger.error("MessageConstant.NULL_POINTER_EXCEPTION",
					nullPointerEx);
			throw new ServiceException(messageSource.getMessage(
					"NULL_POINTER_EXCEPTION", null, null), nullPointerEx);

		} catch (Exception otherEx) {
			logger.error("Service exception", otherEx);
			throw new ServiceException(messageSource.getMessage(
					"SERVICE_EXCEPTION", null, null), otherEx);
		}

	}
}
