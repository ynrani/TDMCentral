package com.tdm.service.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tdm.dao.TdmPolicyAutoPropSearchDAO;
import com.tdm.exception.DAOException;
import com.tdm.exception.ServiceException;
import com.tdm.model.DO.PolicySummaryStg;
import com.tdm.model.DO.TdmReservationDO;
import com.tdm.model.DTO.TDMAtsSearchDTO;
import com.tdm.model.DTO.TDMAutoPolicySearchDTO;
import com.tdm.model.DTO.TdmPolicyAutoSearchDTO;
import com.tdm.model.DTO.TdmPolicyAutoSearchResultDTO;
import com.tdm.model.DTO.TdmPolicyPropertySearchDTO;
import com.tdm.model.DTO.TdmPolicyPropertySearchResultDTO;
import com.tdm.model.mapper.TdmPolicyAutoPropSearchMapper;
import com.tdm.service.TdmPolicyAutoPropSearchService;

@Component
@Service("tdmPolicyAutoPropSearchService")
@Transactional(propagation = Propagation.REQUIRED)
public class TdmPolicyAutoPropSearchServiceImpl extends TdmBaseServiceImpl
		implements TdmPolicyAutoPropSearchService {
	final static Logger logger = Logger
			.getLogger(TdmPolicyAutoPropSearchServiceImpl.class);

	@Autowired
	TdmPolicyAutoPropSearchDAO tdmPolicyAutoPropSearchDAO;

	@Autowired
	TdmPolicyAutoPropSearchMapper tdmPolicyAutoPropSearchMapper;

	@Autowired
	private MessageSource messageSource;

	@Override
	public TdmPolicyPropertySearchDTO searchPolicyPropRecordsByPolicySearchNew(
			TdmPolicyPropertySearchDTO tdmPolicyPropertySearchDTO, int offSet,
			int recordsperpage, boolean pageNationOnOffFlag, String userName)
			throws ServiceException {
		String searchType = "CSAA_PO";
		String policytype = "Property";

		List<TdmPolicyPropertySearchResultDTO> tdmPolicyPropertySearchDTOList = null;
		List<PolicySummaryStg> policySummaryStgDOs = null;
		List<TdmReservationDO> reservationDOs1 = null;
		try {
			// EntityManager managerCsaaUser = openCsaaUserEntityManager();
			EntityManager managerCsaaUser = openUserEntityManager();

			// random.getRandomValue("DICT_CITY")
			// Query qry =
			// managerCsaaUser.createNativeQuery("select count(*) from DICT_CITY ");
			// Object maxRowObj = qry.getSingleResult();
			// Integer maxRows = qry.getFirstResult();
			// System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>: "
			// +String.valueOf(maxRowObj));
			// TODO get random value for random number
			// Integer rowNum = nextInt(1, maxRows);
			// select * from " + tableName + " where ID = "+ rowNum ;

			/**
			 * 1. going to fetch reserved records
			 */
			reservationDOs1 = tdmPolicyAutoPropSearchDAO.getReservedRecords(
					userName, searchType, managerCsaaUser);
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
			policySummaryStgDOs = tdmPolicyAutoPropSearchDAO
					.searchPolicyPropRecordsByPolicySearchNew(
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
			tdmPolicyAutoPropSearchDAO
					.getTDMReservedTestDataListPerUser(tdmPolicyPropertySearchDTO);

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
	public TdmPolicyAutoSearchDTO searchPolicyAutoRecordsByPolicySearchNew(
			TdmPolicyAutoSearchDTO tdmPolicyAutoSearchDTO, int offSet,
			int recordsperpage, boolean pageNationOnOffFlag, String userName)
			throws ServiceException {
		String searchType = "CSAA_AU";
		String ptype = "AUTO";

		try {
			// EntityManager managerUser = openCsaaUserEntityManager();
			EntityManager managerUser = openUserEntityManager();
			/**
			 * 1. going to fetch reserved records
			 */
			List<TdmReservationDO> reservationDOs1 = tdmPolicyAutoPropSearchDAO
					.getReservedRecords(userName, searchType, managerUser);
			StringBuffer policyautos = new StringBuffer();
			if (null != reservationDOs1 && 0 < reservationDOs1.size()) {
				for (TdmReservationDO reservationDO : reservationDOs1) {
					if (null != reservationDO.getReserveDataType()
							&& reservationDO.getReserveDataType()
									.equalsIgnoreCase(searchType)) {
						policyautos.append(",'"
								+ reservationDO.getReserveDataId() + '\'');
					}
				}
			}
			/**
			 * 2. going to fetch remains records apart from reserved based on
			 * criteria
			 */

			List<PolicySummaryStg> policySummaryStgDOs = tdmPolicyAutoPropSearchDAO
					.searchPolicyAutoRecordsByPolicySearchNew(
							tdmPolicyAutoSearchDTO, offSet, recordsperpage,
							pageNationOnOffFlag, policyautos, ptype,
							managerUser);

			/**
			 * 3. going to convert DO to DTO class
			 */
			List<TdmPolicyAutoSearchResultDTO> tdmPolicyAutoSearchResultDTO = tdmPolicyAutoPropSearchMapper
					.convertPolicysummaryToTDMAutoSearchResultDTO(
							policySummaryStgDOs, tdmPolicyAutoSearchDTO,
							reservationDOs1, userName);
			tdmPolicyAutoSearchDTO
					.setTdmPolicyAutoSearchResultDTOList(tdmPolicyAutoSearchResultDTO);

			closeCsaaUserEntityManager(managerUser);

			return tdmPolicyAutoSearchDTO;
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
	public int saveReservedData(
			TdmPolicyPropertySearchDTO tdmPolicyPropertySearchDTO,
			String userName, String enviro) throws ServiceException {

		try {
			List<TdmReservationDO> list = null;

			if (null != tdmPolicyPropertySearchDTO
					.getTdmPolicyPropertySearchResultDTOList()
					&& 0 < tdmPolicyPropertySearchDTO
							.getTdmPolicyPropertySearchResultDTOList().size()) {
				EntityManager managerCsaaUser = openCsaaUserEntityManager();
				List<TdmReservationDO> reservationDOs = tdmPolicyAutoPropSearchMapper
						.converTdmPolicyPropertySearchDTOToTdmReservationDO(
								tdmPolicyPropertySearchDTO
										.getTdmPolicyPropertySearchResultDTOList(),
								tdmPolicyPropertySearchDTO.getSearchCriti(),
								userName, enviro);
				list = tdmPolicyAutoPropSearchDAO.saveReservedData(
						reservationDOs,
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
	public int saveReservedData(TdmPolicyAutoSearchDTO tdmPolicyAutoSearchDTO,
			String userName, String enviro) throws ServiceException {

		try {
			List<TdmReservationDO> list = null;

			if (null != tdmPolicyAutoSearchDTO
					.getTdmPolicyAutoSearchResultDTOList()
					&& 0 < tdmPolicyAutoSearchDTO
							.getTdmPolicyAutoSearchResultDTOList().size()) {
				EntityManager managerCsaaUser = openCsaaUserEntityManager();
				List<TdmReservationDO> reservationDOs = tdmPolicyAutoPropSearchMapper
						.converTdmPolicyAutoSearchResultDTOToTdmReservationDO(
								tdmPolicyAutoSearchDTO
										.getTdmPolicyAutoSearchResultDTOList(),
								tdmPolicyAutoSearchDTO.getSearchCriti(),
								userName, enviro);
				list = tdmPolicyAutoPropSearchDAO.saveReservedData(
						reservationDOs, tdmPolicyAutoSearchDTO.getTestCaseId(),
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

		} catch (DAOException daoEx) {
			logger.error("DAO exception", daoEx);
			throw new ServiceException(daoEx.getErrorCode(), daoEx);

		} catch (Exception otherEx) {
			logger.error("Service exception", otherEx);
			throw new ServiceException(messageSource.getMessage(
					"SERVICE_EXCEPTION", null, null), otherEx);
		}
	}

	/**
	 * @param min
	 *            generated value. Can't be > then max
	 * @param max
	 *            generated value
	 * @return values in closed range [min, max].
	 *
	 *         public int nextInt(int min, int max) {
	 *         //Assert.assertFalse("min can't be > then max; values:[" + min +
	 *         ", " + max + "]", min > max); if (min == max) { return max; }
	 * 
	 *         return nextInt(max - min + 1) + min; }
	 */
	
	public TDMAutoPolicySearchDTO searchATSAutoRecordsByPolicySearchNew(
			TDMAutoPolicySearchDTO atsSearchDTO , int offSet, int recordsperpage,
			boolean pageNationOnOffFlag, String userName) throws ServiceException{
		String searchType = "CSAA_PO";
		String policytype = "Property";

		List<TdmPolicyPropertySearchResultDTO> tdmPolicyPropertySearchDTOList = null;
		List<PolicySummaryStg> policySummaryStgDOs = null;
		List<TdmReservationDO> reservationDOs1 = null;
		try {
			
			EntityManager managerCsaaUser = openUserEntityManager();

		

			/**
			 * 1. going to fetch reserved records
			 */
			reservationDOs1 = tdmPolicyAutoPropSearchDAO.getReservedRecords(
					userName, searchType, managerCsaaUser);
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
			 *
			policySummaryStgDOs = tdmPolicyAutoPropSearchDAO
					.searchATSAutoRecordsByPolicySearchNew(
							atsSearchDTO, offSet, recordsperpage,
							pageNationOnOffFlag, policyProps, policytype,
							managerCsaaUser);

			/**
			 * 3. going to convert DO to DTO class
			 */
			/*tdmPolicyPropertySearchDTOList = tdmPolicyAutoPropSearchMapper
					.converPolicysummaryToTdmPolicyPropertySearchDTONew(
							policySummaryStgDOs, atsSearchDTO,
							reservationDOs1, userName);*/

			atsSearchDTO
					.setTdmPolicyPropertySearchResultDTOList(tdmPolicyPropertySearchDTOList);

			
//			tdmPolicyAutoPropSearchDAO
//					.getTDMReservedTestDataListPerUser(atsSearchDTO);

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
	public int saveReservedData(TDMAutoPolicySearchDTO tdmPolicyAutoSearchDTO,
			String userName, String enviro) throws ServiceException {

		try {
			List<TdmReservationDO> list = null;

			if (null != tdmPolicyAutoSearchDTO
					.getTdmPolicyAutoSearchResultDTOList()
					&& 0 < tdmPolicyAutoSearchDTO
							.getTdmPolicyAutoSearchResultDTOList().size()) {
				EntityManager managerCsaaUser = openCsaaUserEntityManager();
				List<TdmReservationDO> reservationDOs = tdmPolicyAutoPropSearchMapper
						.converTdmPolicyAutoSearchResultDTOToTdmReservationDO(
								tdmPolicyAutoSearchDTO
										.getTdmPolicyAutoSearchResultDTOList(),
								tdmPolicyAutoSearchDTO.getSearchCriti(),
								userName, enviro);
				list = tdmPolicyAutoPropSearchDAO.saveReservedData(
						reservationDOs, tdmPolicyAutoSearchDTO.getTestCaseId(),
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
	public TDMAtsSearchDTO searchATSAutoRecordsByPolicySearchNew(
			TDMAtsSearchDTO tdmPolicyPropertySearchDTO, int offSet,
			int recordsperpage, boolean pageNationOnOffFlag, String userName)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int saveReservedData(TDMAtsSearchDTO tdmPolicyAutoSearchDTO,
			String userName, String enviro) throws ServiceException {
		// TODO Auto-generated method stub
		return 0;
	}
}
