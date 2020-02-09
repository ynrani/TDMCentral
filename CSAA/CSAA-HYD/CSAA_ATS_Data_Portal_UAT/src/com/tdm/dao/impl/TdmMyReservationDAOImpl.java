package com.tdm.dao.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.tdm.constant.AppConstant;
import com.tdm.constant.MessageConstant;
import com.tdm.dao.TdmMyReservationDAO;
import com.tdm.exception.DAOException;
import com.tdm.model.DO.NoOfDriverStg;
import com.tdm.model.DO.NoOfNameInsuStg;
import com.tdm.model.DO.NoOfVehiStg;
import com.tdm.model.DO.NoOfVioStg;
import com.tdm.model.DO.PolicySummaryStg;
import com.tdm.model.DO.TdmReservationDO;

@Component("tdmMyReservationDAOImpl")
public class TdmMyReservationDAOImpl implements TdmMyReservationDAO {

	final static Logger logger = Logger
			.getLogger(TdmMyReservationDAOImpl.class);

	@Autowired
	private MessageSource messageSource;

	@Override
	public List<TdmReservationDO> getReservedRecords(String searchType,
			String userName, int offSet, int recordsperpage,
			boolean pageNationOnOffFlag, EntityManager managerCsaaUser)
			throws DAOException {
		try {
			logger.info("In TdmMyReservationDAOImpl.getReservedRecords()  : ");

			@SuppressWarnings("unchecked")
			List<TdmReservationDO> list = managerCsaaUser.createQuery(
					"SELECT p FROM TdmReservationDO p where p.reserveDataType='"
							+ searchType + "' AND p.userId='" + userName + "'")
					.getResultList();
			// .setFirstResult(offSet).setMaxResults(recordsperpage).getResultList();

			return list;
		} catch (IllegalStateException illegalStateEx) {
			logger.error("MessageConstant.NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION "
					+ illegalStateEx);
			throw new DAOException(messageSource.getMessage(
					"NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION", null, null),
					illegalStateEx);

		} catch (IllegalArgumentException illegalArgEx) {
			logger.error("MessageConstant.INVALID_QUERY_EXCEPTION "
					+ illegalArgEx);
			throw new DAOException(messageSource.getMessage(
					"INVALID_QUERY_EXCEPTION", null, null), illegalArgEx);

		} catch (NullPointerException nullPointerEx) {
			logger.error("MessageConstant.NULL_POINTER_EXCEPTION "
					+ nullPointerEx);
			throw new DAOException(messageSource.getMessage(
					"NULL_POINTER_EXCEPTION", null, null), nullPointerEx);

		} catch (Exception otherEx) {
			logger.error("MessageConstant.DATABASE_EXCEPTION " + otherEx);
			throw new DAOException(messageSource.getMessage(
					"DATABASE_EXCEPTION", null, null), otherEx);

		}

	}

	@Override
	public List<TdmReservationDO> getPolicyAutoRecords(
			List<String> policyAutoNumbers, EntityManager managerCsaaUser,
			String searchType, String userName) throws DAOException {
		try {
			List<TdmReservationDO> autoReservePolicyDOs = new ArrayList<TdmReservationDO>();
			for (String policyAutoNumber : policyAutoNumbers) {
				if (policyAutoNumber != null) {
					@SuppressWarnings("unchecked")
					List<TdmReservationDO> results = managerCsaaUser
							.createQuery(
									"SELECT p FROM TdmReservationDO p where p.policyNumber='"
											+ policyAutoNumber + "'")
							.getResultList();

					if (!results.isEmpty()) {
						autoReservePolicyDOs.add(results.get(0));// else if
																	// (results.size()
																	// == 1)
																	// return
																	// results.get(0);
					}

					if (0 < (Long) managerCsaaUser
							.createQuery(
									"SELECT count(p.policyNumber)  FROM TdmReservationDO p where p.reserveDataType='"
											+ searchType
											+ "' AND p.userId='"
											+ userName + "'").getSingleResult()) {
						autoReservePolicyDOs
								.add((TdmReservationDO) managerCsaaUser
										.createQuery(
												"SELECT p FROM TdmReservationDO p where p.reserveDataType='"
														+ searchType
														+ "' AND p.userId='"
														+ userName + "'")
										.getSingleResult());
					}

				}
			}

			return autoReservePolicyDOs;
		} catch (IllegalStateException illegalStateEx) {
			logger.error("MessageConstant.NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION "
					+ illegalStateEx);
			throw new DAOException(messageSource.getMessage(
					"NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION", null, null),
					illegalStateEx);

		} catch (IllegalArgumentException illegalArgEx) {
			logger.error("MessageConstant.INVALID_QUERY_EXCEPTION "
					+ illegalArgEx);
			throw new DAOException(messageSource.getMessage(
					"INVALID_QUERY_EXCEPTION", null, null), illegalArgEx);

		} catch (NullPointerException nullPointerEx) {
			logger.error("MessageConstant.NULL_POINTER_EXCEPTION "
					+ nullPointerEx);
			throw new DAOException(messageSource.getMessage(
					"NULL_POINTER_EXCEPTION", null, null), nullPointerEx);

		} catch (Exception otherEx) {
			logger.error("MessageConstant.DATABASE_EXCEPTION " + otherEx);
			throw new DAOException(messageSource.getMessage(
					"DATABASE_EXCEPTION", null, null), otherEx);
		}
	}

	@Override
	public Long getReservedRecordsCount(String searchType, String userName,
			EntityManager managerUser) throws DAOException {
		try {

			Long list = (Long) managerUser.createQuery(
					"SELECT COUNT(*) FROM TdmReservationDO p where p.reserveDataType='"
							+ searchType + "' AND p.userId='" + userName + "'")
					.getSingleResult();

			return list;
		} catch (IllegalStateException illegalStateEx) {
			logger.error("MessageConstant.NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION "
					+ illegalStateEx);
			throw new DAOException(messageSource.getMessage(
					"NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION", null, null),
					illegalStateEx);

		} catch (IllegalArgumentException illegalArgEx) {
			logger.error("MessageConstant.INVALID_QUERY_EXCEPTION "
					+ illegalArgEx);
			throw new DAOException(messageSource.getMessage(
					"INVALID_QUERY_EXCEPTION", null, null), illegalArgEx);
		} catch (NullPointerException nullPointerEx) {
			logger.error("MessageConstant.NULL_POINTER_EXCEPTION "
					+ nullPointerEx);
			throw new DAOException(messageSource.getMessage(
					"NULL_POINTER_EXCEPTION", null, null), nullPointerEx);

		} catch (Exception otherEx) {
			logger.error("MessageConstant.DATABASE_EXCEPTION " + otherEx);
			throw new DAOException(messageSource.getMessage(
					"DATABASE_EXCEPTION", null, null), otherEx);
		}
	}

	@Override
	public boolean unReservedRecordForUser(Long policyAutoNo,
			EntityManager managerCsaaUser) throws DAOException {
		try {
			managerCsaaUser.getTransaction().begin();
			Query q1 = managerCsaaUser
					.createQuery("DELETE FROM TdmReservationDO p where p.reserveDataId =:policyNumber");
			q1.setParameter("policyNumber", String.valueOf(policyAutoNo));
			int count = q1.executeUpdate();
			managerCsaaUser.getTransaction().commit();

			return count > 0 ? true : false;
		} catch (IllegalStateException illegalStateEx) {
			logger.error("MessageConstant.NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION "
					+ illegalStateEx);
			throw new DAOException(messageSource.getMessage(
					"NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION", null, null),
					illegalStateEx);

		} catch (IllegalArgumentException illegalArgEx) {
			logger.error("MessageConstant.INVALID_QUERY_EXCEPTION "
					+ illegalArgEx);
			throw new DAOException(messageSource.getMessage(
					"INVALID_QUERY_EXCEPTION", null, null), illegalArgEx);

		} catch (NullPointerException nullPointerEx) {
			logger.error("MessageConstant.NULL_POINTER_EXCEPTION "
					+ nullPointerEx);
			throw new DAOException(messageSource.getMessage(
					"NULL_POINTER_EXCEPTION", null, null), nullPointerEx);

		} catch (Exception otherEx) {
			logger.error("MessageConstant.DATABASE_EXCEPTION " + otherEx);
			throw new DAOException(messageSource.getMessage(
					"DATABASE_EXCEPTION", null, null), otherEx);
		}

	}

	@Override
	public boolean unReservedRecordForUser(String policyPropNo,
			EntityManager managerCsaaUser) throws DAOException {
		try {
			managerCsaaUser.getTransaction().begin();
			Query q1 = managerCsaaUser
					.createQuery("DELETE FROM TdmReservationDO p where p.reserveDataId =:policyNumber");
			q1.setParameter("policyNumber", String.valueOf(policyPropNo));
			int count = q1.executeUpdate();
			managerCsaaUser.getTransaction().commit();

			return count > 0 ? true : false;
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

	}

	@Override
	public List<PolicySummaryStg> getReservedRecords(StringBuffer propIds,
			EntityManager managerCsaaUser) throws DAOException {
		logger.info(MessageConstant.TDM_FTD_POLICY_DAO
				+ MessageConstant.TDM_FTD_POLICY_GET_POL_REC
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {


			StringBuffer query = new StringBuffer(
					"SELECT p.* ,d.COUNT  ,ins.COUNT noOfInsured ,vh.COUNT noOfVehicles ,vs.COUNT noOfViolations ,am.MIN_AMT_DUE ,am.AMT_DUE "

							+ " FROM DM_POLICY_SUMMARY_STG p "
							+ " LEFT OUTER  JOIN DM_NO_OF_DRIVER_STG d ON p.POLICYDETAIL_ID=d.POLICYDETAIL_ID "
							+ " LEFT OUTER  JOIN DM_NO_OF_NAME_INSU_STG ins ON  p.POLICYDETAIL_ID=ins.POLICYDETAIL_ID "
							+ " LEFT OUTER  JOIN DM_NO_OF_VEHI_STG vh ON  p.POLICYDETAIL_ID=vh.POLICYDETAIL_ID "
							+ " LEFT OUTER  JOIN DM_NO_OF_VIO_STG vs ON  p.POLICYDETAIL_ID=vs.POLICYDETAIL_ID "
							+ " LEFT OUTER  JOIN DM_AMOUNT_STG am ON  p.POLICYNUMBER=am.POLICYNUMBER "
							+ " LEFT OUTER  JOIN DM_RESERVATION rsvn ON p.policynumber=rsvn.RESERVE_DATA_ID  ");
			query.append(" WHERE p.transactioneffectivedate IN(SELECT MAX(ab.transactioneffectivedate) from DM_POLICY_SUMMARY_STG ab WHERE p.policynumber=ab.policynumber )  ");

			if (null != propIds) {
				query.append("AND P.policynumber IN ( 'A' ");
				query.append(propIds);
				query.append(") ");
			}

			
			@SuppressWarnings(AppConstant.UNCHECKED)
			List<PolicySummaryStg> policySummaryStgDOs = managerCsaaUser
					.createNativeQuery(query.toString()).setFirstResult(0)
					.setMaxResults(1000).getResultList();
			policySummaryStgDOs = populateAutoPolicySummaryStgList(policySummaryStgDOs);
			logger.info(MessageConstant.TDM_FTD_POLICY_DAO
					+ MessageConstant.TDM_FTD_POLICY_GET_POL_REC
					+ MessageConstant.LOG_INFO_RETURN);
			return policySummaryStgDOs;
		} catch (IllegalStateException illegalStateEx) {
			logger.error(MessageConstant.TDM_FTD_POLICY_DAO
					+ MessageConstant.TDM_FTD_POLICY_GET_POL_REC
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(
					MessageConstant.NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION,
					illegalStateEx);
		} catch (IllegalArgumentException illegalArgEx) {
			logger.error(MessageConstant.TDM_FTD_POLICY_DAO
					+ MessageConstant.TDM_FTD_POLICY_GET_POL_REC
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.INVALID_QUERY_EXCEPTION,
					illegalArgEx);
		} catch (NullPointerException nullPointerEx) {
			logger.error(MessageConstant.TDM_FTD_POLICY_DAO
					+ MessageConstant.TDM_FTD_POLICY_GET_POL_REC
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.NULL_POINTER_EXCEPTION,
					nullPointerEx);
		} catch (Exception otherEx) {
			logger.error(MessageConstant.TDM_FTD_POLICY_DAO
					+ MessageConstant.TDM_FTD_POLICY_GET_POL_REC
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.DATABASE_EXCEPTION, otherEx);
		}
	}
	
	private List<PolicySummaryStg> populateAutoPolicySummaryStgList(
			List<PolicySummaryStg> list) throws Exception {

		List<PolicySummaryStg> summaryStgs = new ArrayList<PolicySummaryStg>();
		try {
			PolicySummaryStg policySummaryStg = null;

			for (Object data : list) {
				Object[] dataArr = (Object[]) data;
				int size = dataArr.length;
				if (dataArr != null && size > 0) {
					policySummaryStg = new PolicySummaryStg();
					policySummaryStg
							.setPolicynumber(dataArr[0] != null ? dataArr[0]
									.toString() : null);
					policySummaryStg
							.setPolicystatuscd(dataArr[3] != null ? dataArr[3]
									.toString() : null);
					policySummaryStg
							.setRiskstatecd(dataArr[9] != null ? dataArr[9]
									.toString() : null);
					policySummaryStg
							.setTimedpolicystatuscd(dataArr[5] != null ? dataArr[5]
									.toString() : null);
					policySummaryStg.setTxtype(dataArr[6] != null ? dataArr[6]
							.toString() : null);
					policySummaryStg
							.setContracttermtypecd(dataArr[7] != null ? dataArr[7]
									.toString() : null);
					policySummaryStg
							.setProductcd(dataArr[8] != null ? dataArr[8]
									.toString() : null);
					SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-DD");
					if (dataArr[10] != null) {
						Date effectivedate = format
								.parse(dataArr[10].toString());
						policySummaryStg.setEffective(effectivedate);
					}

					policySummaryStg
							.setPolicydetailId(dataArr[1] != null ? dataArr[1]
									.toString() : null);
					policySummaryStg
							.setCurrentrevisionind(dataArr[14] != null ? dataArr[14]
									.toString() : null);

					NoOfDriverStg driverStg = new NoOfDriverStg();
					if (dataArr[17] != null) {
						String normalized = dataArr[17].toString()
								.replaceAll("\\s", "").replace(',', '.');
						driverStg.setCount(new BigDecimal(normalized));
					}
					policySummaryStg.setNoOfDriverStg(driverStg);

					NoOfNameInsuStg nameInsuStg = new NoOfNameInsuStg();
					if (dataArr[18] != null) {
						String normalized = dataArr[18].toString()
								.replaceAll("\\s", "").replace(',', '.');
						nameInsuStg.setCount(new BigDecimal(normalized));
					}
					policySummaryStg.setNoOfNameInsuStg(nameInsuStg);

					NoOfVehiStg vehicleStg = new NoOfVehiStg();
					if (dataArr[19] != null) {
						String normalized = dataArr[19].toString()
								.replaceAll("\\s", "").replace(',', '.');
						vehicleStg.setCount(new BigDecimal(normalized));
					}
					policySummaryStg.setNoOfVehiStg(vehicleStg);

					NoOfVioStg vioStg = new NoOfVioStg();
					if (dataArr[20] != null) {
						String normalized = dataArr[20].toString()
								.replaceAll("\\s", "").replace(',', '.');
						vioStg.setCount(new BigDecimal(normalized));
					}
					policySummaryStg.setNoOfVioStg(vioStg);
					if (dataArr[21] != null) {
						String normalized = dataArr[21].toString()
								.replaceAll("\\s", "").replace(',', '.');
						policySummaryStg.setTotalAmountDue(Double
								.valueOf(normalized));
					}
					if (dataArr[22] != null) {
						String testCaseId = dataArr[22].toString();
						policySummaryStg.setTestCaseId(testCaseId);
					}
					summaryStgs.add(policySummaryStg);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return summaryStgs;

	}

}
