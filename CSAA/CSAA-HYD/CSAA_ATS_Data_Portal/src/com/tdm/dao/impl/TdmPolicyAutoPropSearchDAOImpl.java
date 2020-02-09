package com.tdm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tdm.constant.AppConstant;
import com.tdm.dao.TdmPolicyAutoPropSearchDAO;
import com.tdm.exception.DAOException;
import com.tdm.model.DO.PolicySummaryStg;
import com.tdm.model.DO.TdmReservationDO;
import com.tdm.model.DTO.TDMAtsSearchDTO;
import com.tdm.model.DTO.TDMReservedTestDataDTO;
import com.tdm.model.DTO.TdmPolicyAutoSearchDTO;
import com.tdm.model.DTO.TdmPolicyPropertySearchDTO;

@Component("tdmPolicyAutoPropSearchDAO")
public class TdmPolicyAutoPropSearchDAOImpl implements
		TdmPolicyAutoPropSearchDAO {
	final static Logger logger = Logger
			.getLogger(TdmPolicyAutoPropSearchDAOImpl.class);

	@Autowired
	private MessageSource messageSource;
	@Autowired
	private DataSource tdmUserDS;

	public DataSource getTdmUserDS() {
		return tdmUserDS;
	}

	public void setTdmUserDS(DataSource tdmUserDS) {
		this.tdmUserDS = tdmUserDS;
	}

	// TODO

	@Override
	public List<PolicySummaryStg> searchPolicyPropRecordsByPolicySearchNew(
			TdmPolicyPropertySearchDTO tdmPolicyPropertySearchDTO, int offSet,
			int recordsperpage, boolean pageNationOnOffFlag,
			StringBuffer policyProps, String policytype,
			EntityManager managerCsaauser) throws DAOException {
		try {
			StringBuffer query = new StringBuffer(
					"SELECT p FROM PolicySummaryStg p WHERE p.lob='HOME'");

			// Reservation not in
			if (null != policyProps) {
				query.append(" AND p.policynumber NOT IN ( 'A' ");
				query.append(policyProps);
				query.append(") ");
			}

			// Product Type
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
					.getAddproductType())) {
				query.append(" AND p.policyformcd ='")
						.append(tdmPolicyPropertySearchDTO.getAddproductType())
						.append('\'');
			}
			// Policy Stage
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
					.getPolicyStage())) {
				if (tdmPolicyPropertySearchDTO.getPolicyStage()
						.equalsIgnoreCase("Active")) {
					query.append(" AND p.policystatuscd='issued' AND P.timedpolicystatuscd='inForce' ");
				} else if (tdmPolicyPropertySearchDTO.getPolicyStage()
						.equalsIgnoreCase("Cancelled")) {
					query.append(" AND p.policystatuscd='cancelled' ");
				} else if (tdmPolicyPropertySearchDTO.getPolicyStage()
						.equalsIgnoreCase("Pending")) {
					query.append(" AND p.policystatuscd in ('issued','initiated','dataGather','rated','proposed','pendingCompletion','customerDeclined','companyDeclined')");
					query.append(" AND p.timedpolicystatuscd in ('inForcePending','pendingCompletion','proposed','customerDeclined','rated','expired','companyDeclined','initiated','dataGather') ");
				}
			}
			// Risk State
			/*
			 * if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
			 * .getPolicyState())) { query.append(" AND p.riskstatecd ='")
			 * .append(tdmPolicyPropertySearchDTO.getPolicyState())
			 * .append('\''); }
			 * 
			 * // Policy Term if
			 * (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
			 * .getPolicyTerm())) { query.append(" AND p.contracttermtypecd ='")
			 * .append(tdmPolicyPropertySearchDTO.getPolicyTerm())
			 * .append('\''); } // Associate Payments required if
			 * (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
			 * .getAddPayReq())) {
			 * 
			 * }
			 */
			// Risk Level Coverage addRiskCovge
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
					.getAddRiskCovge())) {

				query.replace(query.indexOf("PolicySummaryStg p"),
						query.indexOf("PolicySummaryStg p")
								+ "PolicySummaryStg p".toString().length(),
						"PolicySummaryStg p JOIN p.coverageRiskStgs cr");
				query.append(" AND cr.coveragecd ='")
						.append(tdmPolicyPropertySearchDTO.getAddRiskCovge())
						.append('\'');
			}

			// Associated Documents required
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
					.getAddDocReq())) {
				if ("Y".equalsIgnoreCase(tdmPolicyPropertySearchDTO
						.getAddDocReq())) {
					query.append(" AND p.docYn ='Y' ");
				} else if ("N".equalsIgnoreCase(tdmPolicyPropertySearchDTO
						.getAddDocReq())) {
					query.append(" AND p.docYn ='N' ");
				} else {
					query.append(" AND p.docYn IN ('N','Y')");
				}
			}

			long startTime = System.currentTimeMillis();

			@SuppressWarnings(AppConstant.UNCHECKED)
			List<PolicySummaryStg> policySummaryStgDOs = managerCsaauser
					.createQuery(query.toString()).setFirstResult(offSet)
					.setMaxResults(25).getResultList();

			long stopTime = System.currentTimeMillis();
			long elapsedTime = stopTime - startTime;
			logger.info("Query once associated document is Y:  " + offSet
					+ ": " + query.toString());
			logger.info(elapsedTime
					+ ": Millis To execute the query for policy record fetch");

			return policySummaryStgDOs;
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
			nullPointerEx.printStackTrace();
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
	public List<PolicySummaryStg> searchPolicyAutoRecordsByPolicySearchNew(
			TdmPolicyAutoSearchDTO tdmPolicyAutoSearchDTO, int offSet,
			int recordsperpage, boolean pageNationOnOffFlag,
			StringBuffer policyautos, String ptype,
			EntityManager managerCsaauser) throws DAOException {
		try {
			StringBuffer query = new StringBuffer(
					"SELECT p FROM PolicySummaryStg p WHERE p.lob='AUTO' ");

			// Reservation not in
			if (null != policyautos) {
				query.append(" AND p.policynumber NOT IN ( 'A' ");
				query.append(policyautos);
				query.append(") ");
			}

			// Product Type
			query.append("AND p.productcd NOT  IN ('AAA_PUP_SS')");
			if (StringUtils.isNotEmpty(tdmPolicyAutoSearchDTO
					.getAddproductType())) {
				query.append(" AND p.productcd ='")
						.append(tdmPolicyAutoSearchDTO.getAddproductType())
						.append("' ");
			}

			// Policy Stage
			if (StringUtils.isNotEmpty(tdmPolicyAutoSearchDTO.getPolicyStage())) {
				if (tdmPolicyAutoSearchDTO.getPolicyStage().equalsIgnoreCase(
						"Active")) {
					query.append(" AND p.policystatuscd  ='issued' AND p.timedpolicystatuscd='inForce' ");
				} else if (tdmPolicyAutoSearchDTO.getPolicyStage()
						.equalsIgnoreCase("Cancelled")) {
					query.append(" AND p.policystatuscd='cancelled' ");
				} else if (tdmPolicyAutoSearchDTO.getPolicyStage()
						.equalsIgnoreCase("Pending")) {
					query.append(" AND p.policystatuscd in ('issued','initiated','dataGather','rated','proposed','pendingCompletion','customerDeclined','companyDeclined')");
					query.append(" AND p.timedpolicystatuscd in ('inForcePending','pendingCompletion','proposed','customerDeclined','rated','expired','companyDeclined','initiated','dataGather' ) ");
				}
			}

			// Risk State
			if (StringUtils.isNotEmpty(tdmPolicyAutoSearchDTO.getPolicyState())) {
				query.append(" AND p.riskstatecd ='")
						.append(tdmPolicyAutoSearchDTO.getPolicyState())
						.append("' ");
			}

			// Policy Term
			if (StringUtils.isNotEmpty(tdmPolicyAutoSearchDTO.getPolicyTerm())) {
				query.append(" AND p.contracttermtypecd='")
						.append(tdmPolicyAutoSearchDTO.getPolicyTerm())
						.append("' ");
			}

			// Associate Payments required
			if (StringUtils.isNotEmpty(tdmPolicyAutoSearchDTO.getAssoPayReq())) {

			}

			// Policy Coverage
			if (StringUtils.isNotEmpty(tdmPolicyAutoSearchDTO.getPolicyCovge())) {

				query.replace(query.indexOf("PolicySummaryStg p"),
						query.indexOf("PolicySummaryStg p")
								+ "PolicySummaryStg p".toString().length(),
						"PolicySummaryStg p JOIN p.coverageStgs cs ");

				query.append(" AND cs.coveragecd ='")
						.append(tdmPolicyAutoSearchDTO.getPolicyCovge())
						.append('\'');
			}
			// Risk Level Coverage
			if (StringUtils.isNotEmpty(tdmPolicyAutoSearchDTO.getRiskCovge())) {

				query.replace(query.indexOf("PolicySummaryStg p"),
						query.indexOf("PolicySummaryStg p")
								+ "PolicySummaryStg p".toString().length(),
						"PolicySummaryStg p JOIN p.coverageRiskStgs cr ");

				query.append(" AND cr.coveragecd ='")
						.append(tdmPolicyAutoSearchDTO.getRiskCovge())
						.append('\'');

			}

			// No Of Drivers
			if (StringUtils.isNotEmpty(tdmPolicyAutoSearchDTO.getNoOfDrivers())) {
				query.replace(query.indexOf("PolicySummaryStg p"),
						query.indexOf("PolicySummaryStg p")
								+ "PolicySummaryStg p".toString().length(),
						"PolicySummaryStg p JOIN p.noOfDriverStg noOfDr ");
				query.append(" AND noOfDr.count ='")
						.append(tdmPolicyAutoSearchDTO.getNoOfDrivers())
						.append('\'');
			}

			// No Of Named Insu
			if (StringUtils.isNotEmpty(tdmPolicyAutoSearchDTO
					.getNoOfNamedInsu())) {
				query.replace(query.indexOf("PolicySummaryStg p"),
						query.indexOf("PolicySummaryStg p")
								+ "PolicySummaryStg p".toString().length(),
						"PolicySummaryStg p JOIN p.noOfNameInsuStg noOfNi ");
				query.append(" AND noOfNi.count ='")
						.append(tdmPolicyAutoSearchDTO.getNoOfNamedInsu())
						.append('\'');
			}

			// No Of Vehi
			if (StringUtils.isNotEmpty(tdmPolicyAutoSearchDTO.getNoOfVehi())) {
				query.replace(query.indexOf("PolicySummaryStg p"),
						query.indexOf("PolicySummaryStg p")
								+ "PolicySummaryStg p".toString().length(),
						"PolicySummaryStg p JOIN p.noOfVehiStg noOfVehi ");
				query.append(" AND noOfVehi.count ='")
						.append(tdmPolicyAutoSearchDTO.getNoOfVehi())
						.append('\'');
			}

			// No Of Vio
			if (StringUtils.isNotEmpty(tdmPolicyAutoSearchDTO.getNoOfViola())) {
				query.replace(query.indexOf("PolicySummaryStg p"),
						query.indexOf("PolicySummaryStg p")
								+ "PolicySummaryStg p".toString().length(),
						"PolicySummaryStg p JOIN p.noOfVioStg noOfVio ");
				query.append(" AND noOfVio.count ='")
						.append(tdmPolicyAutoSearchDTO.getNoOfViola())
						.append('\'');
			}

			// Associated Documents required
			if (StringUtils.isNotEmpty(tdmPolicyAutoSearchDTO.getAssoDocReq())) {
				if ("Y".equalsIgnoreCase(tdmPolicyAutoSearchDTO.getAssoDocReq())) {
					query.append(" AND p.docYn ='Y' ");
				} else if ("N".equalsIgnoreCase(tdmPolicyAutoSearchDTO
						.getAssoDocReq())) {
					query.append(" AND p.docYn ='N' ");
				} else {
					query.append(" AND p.docYn IN ('N','Y')");
				}
			}

			long startTime = System.currentTimeMillis();
			@SuppressWarnings(AppConstant.UNCHECKED)
			List<PolicySummaryStg> policySummaryStgDOs = managerCsaauser
					.createQuery(query.toString()).setFirstResult(offSet)
					.setMaxResults(25).getResultList();
			long stopTime = System.currentTimeMillis();
			long elapsedTime = stopTime - startTime;
			if ((elapsedTime / 60000) > 1) {
				throw new DAOException(
						"Query taking more than one min time for validating the records");
			}
			logger.info("Query if document is selected NO:  "
					+ query.toString());
			logger.info(": Millis to execute the query search Policy Auto Records : "
					+ elapsedTime);
			return policySummaryStgDOs;
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
	public List<TdmReservationDO> getReservedRecords(String userId,
			String searchType, EntityManager managerCsaaUser)
			throws DAOException {
		try {
			long startTime = System.currentTimeMillis();
			@SuppressWarnings("unchecked")
			List<TdmReservationDO> list = managerCsaaUser.createQuery(
					" FROM TdmReservationDO p where p.reserveDataType IN ('"
							+ searchType + "')  AND p.userId='" + userId + "'")
					.getResultList();
			long stopTime = System.currentTimeMillis();
			long elapsedTime = stopTime - startTime;
			if ((elapsedTime / 60000) > 1) {
				throw new DAOException(
						"Query taking more than one min time for validating the records");
			}
			String str = " FROM TdmReservationDO p where p.reserveDataType IN ('"
					+ searchType + "') AND p.userId'" + userId + "'";
			logger.info(elapsedTime
					+ ": Millis To execute the query getReservedRecords: "
					+ str);
			return list;
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
			System.out.println(otherEx);
			otherEx.printStackTrace();
			throw new DAOException(messageSource.getMessage(
					"DATABASE_EXCEPTION", null, null), otherEx);
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

	public List<TDMReservedTestDataDTO> getTDMReservedTestDataListPerUser(
			TdmPolicyPropertySearchDTO tdmPolicyPropertySearchDTO)
			throws Exception {

		try {
			Connection dbConnection = tdmUserDS.getConnection();

			String searchCriteria = getSearchCriteria(tdmPolicyPropertySearchDTO);

			System.out.println("searchCriteria >>>>>>>>" + searchCriteria);

			PreparedStatement preparedStmt = dbConnection
					.prepareStatement("SELECT USER_ID,  COUNT(RESERVE_DATA) FROM DM_RESERVATION WHERE LOCKED = ?  and trim(RESERVE_DATA)=trim(?)  and RESERVE_DATA_TYPE = ? GROUP BY user_id");
			preparedStmt.setString(1, "Y");
			preparedStmt.setString(2, searchCriteria);
			preparedStmt.setString(3, "CSAA_PO");
			ResultSet result = preparedStmt.executeQuery();
			TDMReservedTestDataDTO tdmReservedTestDataDTO = null;
			List<TDMReservedTestDataDTO> tdmReservedTestDataDTOList = new ArrayList<TDMReservedTestDataDTO>();
			int rowCount = 1;
			while (result.next()) {
				tdmReservedTestDataDTO = new TDMReservedTestDataDTO();
				tdmReservedTestDataDTO.setSno(rowCount);
				tdmReservedTestDataDTO.setUserId(result.getString(1));
				tdmReservedTestDataDTO.setNoOfRecordsResvByUser(result
						.getInt(2));

				tdmReservedTestDataDTOList.add(tdmReservedTestDataDTO);
				rowCount++;
			}
			if (!org.springframework.util.CollectionUtils
					.isEmpty(tdmReservedTestDataDTOList)) {
				tdmPolicyPropertySearchDTO
						.setReservedTestDataList(tdmReservedTestDataDTOList);
			}
		} catch (Exception e) {
			throw e;
		}
		return null;
	}

	public String getSearchCriteria(
			TdmPolicyPropertySearchDTO tdmPolicyPropertySearchDTO) {
		if (logger.isInfoEnabled()) {
			logger.info("property controller getting search criterai  : ");
		}
		String searchCriteria = "";

		if (null != tdmPolicyPropertySearchDTO) {

			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO.getEnvType())) {

				searchCriteria += messageSource.getMessage("label.env", null,
						null)
						+ " : "
						+ tdmPolicyPropertySearchDTO.getEnvType()
						+ "  ";
			}

			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
					.getPolicyStage())) {
				searchCriteria += " + "
						+ messageSource.getMessage("label.policy.stage", null,
								null) + " : "
						+ tdmPolicyPropertySearchDTO.getPolicyStage() + "  ";
			}
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
					.getPolicyTerm())) {
				searchCriteria += " + "
						+ messageSource.getMessage("label.policy.term", null,
								null) + " : "
						+ tdmPolicyPropertySearchDTO.getPolicyTerm() + "  ";
			}
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
					.getPolicyState())) {
				searchCriteria += " + "
						+ messageSource.getMessage("label.policy.state", null,
								null) + " : "
						+ tdmPolicyPropertySearchDTO.getPolicyState() + "  ";
			}
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
					.getAddproductType())) {
				searchCriteria += " + "
						+ messageSource.getMessage("label.policy.prodType",
								null, null) + " : "
						+ tdmPolicyPropertySearchDTO.getAddproductType() + "  ";
			}
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
					.getAddPolicyCovge())) {
				searchCriteria += " + "
						+ messageSource.getMessage("label.policy.poliCov",
								null, null) + " : "
						+ tdmPolicyPropertySearchDTO.getAddPolicyCovge() + "  ";
			}
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
					.getAddPayMethod())) {
				searchCriteria += " + "
						+ messageSource.getMessage("label.policy.payType",
								null, null) + " : "
						+ tdmPolicyPropertySearchDTO.getAddPayMethod() + "  ";
			}
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
					.getAddPayReq())) {
				String yorn = null;
				if (tdmPolicyPropertySearchDTO.getAddPayReq().equalsIgnoreCase(
						"Y")) {
					yorn = "Yes";
				} else if (tdmPolicyPropertySearchDTO.getAddPayReq()
						.equalsIgnoreCase("N")) {
					yorn = "No";
				} /*
				 * else { yorn = "Any"; }
				 */

				searchCriteria += " + "
						+ messageSource.getMessage(
								"label.policy.assoPayReqAdd", null, null)
						+ " : " + yorn + "  ";
			}

			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
					.getAddDocReq())) {
				String yorn = null;
				if (tdmPolicyPropertySearchDTO.getAddDocReq().equalsIgnoreCase(
						"Y")) {
					yorn = "Yes";
				} else if (tdmPolicyPropertySearchDTO.getAddDocReq()
						.equalsIgnoreCase("N")) {
					yorn = "No";
				} /*
				 * else { yorn = "Any"; }
				 */

				searchCriteria += " + "
						+ messageSource.getMessage("label.policy.assoDoc",
								null, null) + " : " + yorn + "  ";
			}

			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
					.getAddDocType())) {
				searchCriteria += " + "
						+ messageSource.getMessage("label.policy.docTypeAdd",
								null, null) + " : "
						+ tdmPolicyPropertySearchDTO.getAddDocType() + "  ";
			}

			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
					.getAddYearBuilt())) {
				searchCriteria += " + "
						+ messageSource.getMessage("label.policy.year", null,
								null) + " : "
						+ tdmPolicyPropertySearchDTO.getAddYearBuilt() + "  ";
			}
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
					.getAddConType())) {
				searchCriteria += " + "
						+ messageSource.getMessage("label.policy.consType",
								null, null) + " : "
						+ tdmPolicyPropertySearchDTO.getAddConType() + "  ";
			}
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO.getZipCode())) {
				searchCriteria += " + "
						+ messageSource.getMessage("label.policy.zipCode",
								null, null) + " : "
						+ tdmPolicyPropertySearchDTO.getZipCode() + "  ";
			}
			if (StringUtils
					.isNotEmpty(tdmPolicyPropertySearchDTO.getLeinIndi())) {

				String yorn = null;
				if (tdmPolicyPropertySearchDTO.getLeinIndi().equalsIgnoreCase(
						"Y")) {
					yorn = "Yes";
				} else {
					yorn = "No";
				}

				searchCriteria += " + "
						+ messageSource.getMessage("label.policy.leiindi",
								null, null) + " : " + yorn + "  ";
			}
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
					.getOptnlCovge())) {
				searchCriteria += " + "
						+ messageSource.getMessage("label.policy.opnlCovg",
								null, null) + " : "
						+ tdmPolicyPropertySearchDTO.getOptnlCovge() + "  ";
			}

		}

		return searchCriteria;
	}

	@Override
	public List<PolicySummaryStg> searchATSAutoRecordsByPolicySearchNew(
			TDMAtsSearchDTO tdmAtsSearchDTO, int offSet, int recordsperpage,
			boolean pageNationOnOffFlag, StringBuffer policyProps,
			String policytype, EntityManager managerCsaauser)
			throws DAOException {
		try {
			StringBuffer query = new StringBuffer(
					"SELECT p FROM PolicySummaryStg p WHERE p.lob='HOME'");

			// Reservation not in
			/*
			 * if (null != policyProps) {
			 * query.append(" AND p.policynumber NOT IN ( 'A' ");
			 * query.append(policyProps); query.append(") "); }
			 */

			// Product Type
			if (StringUtils.isNotEmpty(tdmAtsSearchDTO.getAddproductType())) {
				query.append(" AND p.productcd ='")
						.append(tdmAtsSearchDTO.getAddproductType())
						.append('\'');
			}

			// Risk State
			if (StringUtils.isNotEmpty(tdmAtsSearchDTO.getRiskState())
					&& !tdmAtsSearchDTO.getAddRiskCovge().equalsIgnoreCase(
							"Any")) {
				query.append(" AND p.riskstatecd ='")
						.append(tdmAtsSearchDTO.getRiskState()).append('\'');
			}

			// Policy Status
			if (StringUtils.isNotEmpty(tdmAtsSearchDTO.getPolicyStage())
					&& !tdmAtsSearchDTO.getPolicyStage()
							.equalsIgnoreCase("Any")) {
				if (tdmAtsSearchDTO.getPolicyStage().equalsIgnoreCase("Active")) {
					query.append(" AND p.policystatuscd='issued' AND P.timedpolicystatuscd='inForce' ");
				} else if (tdmAtsSearchDTO.getPolicyStage().equalsIgnoreCase(
						"Cancelled")) {
					query.append(" AND p.policystatuscd='cancelled' ");
				} else if (tdmAtsSearchDTO.getPolicyStage().equalsIgnoreCase(
						"Pending")) {
					query.append(" AND p.policystatuscd in ('issued','initiated','dataGather','rated','proposed','pendingCompletion','customerDeclined','companyDeclined')");
					query.append(" AND p.timedpolicystatuscd in ('inForcePending','pendingCompletion','proposed','customerDeclined','rated','expired','companyDeclined','initiated','dataGather') ");
				}
			}

			// Payment Plan

			if (StringUtils.isNotEmpty(tdmAtsSearchDTO.getAddPaymentPlan())
					&& !tdmAtsSearchDTO.getAddPaymentPlan().equalsIgnoreCase(
							"Any")) {
				if (tdmAtsSearchDTO.getAddPaymentPlan().equalsIgnoreCase(
						"Annual")) {
					query.append(" AND p.paymentPlancd in ('annualSS_R','annualCA','annualSS','annualCA_R')");
				} else if (tdmAtsSearchDTO.getAddPaymentPlan()
						.equalsIgnoreCase("Monthly")) {
					query.append(" AND p.paymentPlancd in ('standartCA','Monthly6SS_R','MonthlySS_R','standartSS_R','MonthlySS','standart6SS','Monthly6SS','standartCA_R','standartSS','standart6SS_R')");
				} else if (tdmAtsSearchDTO.getAddPaymentPlan()
						.equalsIgnoreCase("Monthly EFT")) {
					query.append(" AND p.paymentPlancd in ('lowDeposit6SS_NY_R','lowDepositSS_R','lowDepositSS_NY','lowDepositMoSS_NY_R','lowDepositSS','lowDepositMoSS_R','lowDepositCA','lowDepositMoSS_NY','lowDepositSS_NY_R','lowDepositCA_R','lowDeposit6SS','lowDeposit6SS_NY','lowDepositMo6SS_R','lowDeposit6SS_R','lowDepositMoSS','lowDepositMo6SS','zeroDownMoSS','zeroDownCA','zeroDown6SS','interimCA','zeroDownSS','zeroDownMo6SS','zeroDownCA_R,'interimCA_R')");
				} else if (tdmAtsSearchDTO.getAddPaymentPlan()
						.equalsIgnoreCase("Quarterly")) {
					query.append(" AND p.paymentPlancd in ('quaterlyCA_R','quaterlySS','quaterly6SS_R','quaterlyCA','quaterlySS_R','quaterly6SS')");
				} else if (tdmAtsSearchDTO.getAddPaymentPlan()
						.equalsIgnoreCase("Semi-Annual")) {
					query.append(" AND p.paymentPlancd in ('semiAnnualCA_R','semiAnnualSS_R','semiAnnualSS','semiAnnual6SS','semiAnnualCA','semiAnnual6SS_R')");
				}
			}
			/*
			 * // Policy Status
			 * 
			 * 
			 * 
			 * // Policy Term if
			 * (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
			 * .getPolicyTerm()) && !tdmPolicyPropertySearchDTO.getPolicyTerm()
			 * .equalsIgnoreCase("Any")) {
			 * query.append(" AND p.contracttermtypecd ='")
			 * .append(tdmPolicyPropertySearchDTO.getPolicyTerm())
			 * .append('\''); }
			 * 
			 * // TODO Payment Plan
			 * 
			 * // TODO Policy Coverage
			 * 
			 * // TODO Vehicle Level Coverage
			 * 
			 * // Associate Payments required if
			 * (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
			 * .getAddPayReq())) {
			 * 
			 * } // Risk Level Coverage addRiskCovge if
			 * (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
			 * .getAddRiskCovge()) &&
			 * !tdmPolicyPropertySearchDTO.getAddRiskCovge()
			 * .equalsIgnoreCase("Any")) {
			 * 
			 * query.replace(query.indexOf("PolicySummaryStg p"),
			 * query.indexOf("PolicySummaryStg p") +
			 * "PolicySummaryStg p".toString().length(),
			 * "PolicySummaryStg p JOIN p.coverageRiskStgs cr");
			 * 
			 * query.append(" AND cr.coveragecd ='")
			 * .append(tdmPolicyPropertySearchDTO.getAddRiskCovge())
			 * .append('\''); }
			 * 
			 * // Risk Level Coverage addRiskCovge if
			 * (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
			 * .getAddRiskCovge()) &&
			 * !tdmPolicyPropertySearchDTO.getAddRiskCovge()
			 * .equalsIgnoreCase("CO")) {
			 * 
			 * query.replace(query.indexOf("PolicySummaryStg p"),
			 * query.indexOf("PolicySummaryStg p") +
			 * "PolicySummaryStg p".toString().length(),
			 * "PolicySummaryStg p JOIN p.coverageRiskStgs cr");
			 * 
			 * query.append(" AND cr.coveragecd ='").append("UMPDDED")
			 * .append('\''); }
			 * 
			 * // Associated Documents required if
			 * (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
			 * .getAddDocReq()) && !tdmPolicyPropertySearchDTO.getAddDocReq()
			 * .equalsIgnoreCase("Any")) { if
			 * ("Y".equalsIgnoreCase(tdmPolicyPropertySearchDTO
			 * .getAddDocReq())) { query.append(" AND p.docYn ='Y' "); } else if
			 * ("N".equalsIgnoreCase(tdmPolicyPropertySearchDTO
			 * .getAddDocReq())) { query.append(" AND p.docYn ='N' "); } else {
			 * query.append(" AND p.docYn IN ('N','Y')"); } }
			 */

			long startTime = System.currentTimeMillis();

			@SuppressWarnings(AppConstant.UNCHECKED)
			List<PolicySummaryStg> policySummaryStgDOs = managerCsaauser
					.createQuery(query.toString()).setFirstResult(offSet)
					.setMaxResults(25).getResultList();

			long stopTime = System.currentTimeMillis();
			long elapsedTime = stopTime - startTime;
			logger.info("Query once associated document is Y:  " + offSet
					+ ": " + query.toString());
			logger.info(elapsedTime
					+ ": Millis To execute the query for policy record fetch");

			return policySummaryStgDOs;
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
			nullPointerEx.printStackTrace();
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

	public List<TDMReservedTestDataDTO> getTDMReservedTestDataListPerUser(
			TDMAtsSearchDTO tdmPolicyPropertySearchDTO) throws Exception {

		try {
			Connection dbConnection = tdmUserDS.getConnection();

			String searchCriteria = getSearchCriteria(tdmPolicyPropertySearchDTO);

			System.out.println("searchCriteria >>>>>>>>" + searchCriteria);

			PreparedStatement preparedStmt = dbConnection
					.prepareStatement("SELECT USER_ID,  COUNT(RESERVE_DATA) FROM DM_RESERVATION WHERE LOCKED = ?  and trim(RESERVE_DATA)=trim(?)  and RESERVE_DATA_TYPE = ? GROUP BY user_id");
			preparedStmt.setString(1, "Y");
			preparedStmt.setString(2, searchCriteria);
			preparedStmt.setString(3, "CSAA_PO");
			ResultSet result = preparedStmt.executeQuery();
			TDMReservedTestDataDTO tdmReservedTestDataDTO = null;
			List<TDMReservedTestDataDTO> tdmReservedTestDataDTOList = new ArrayList<TDMReservedTestDataDTO>();
			int rowCount = 1;
			while (result.next()) {
				tdmReservedTestDataDTO = new TDMReservedTestDataDTO();
				tdmReservedTestDataDTO.setSno(rowCount);
				tdmReservedTestDataDTO.setUserId(result.getString(1));
				tdmReservedTestDataDTO.setNoOfRecordsResvByUser(result
						.getInt(2));

				tdmReservedTestDataDTOList.add(tdmReservedTestDataDTO);
				rowCount++;
			}
			if (!org.springframework.util.CollectionUtils
					.isEmpty(tdmReservedTestDataDTOList)) {
				tdmPolicyPropertySearchDTO
						.setReservedTestDataList(tdmReservedTestDataDTOList);
			}
		} catch (Exception e) {
			throw e;
		}
		return null;
	}

	public String getSearchCriteria(TDMAtsSearchDTO tdmPolicyPropertySearchDTO) {
		if (logger.isInfoEnabled()) {
			logger.info("property controller getting search criterai  : ");
		}
		String searchCriteria = "";

		if (null != tdmPolicyPropertySearchDTO) {

			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO.getEnvType())) {

				searchCriteria += messageSource.getMessage("label.env", null,
						null)
						+ " : "
						+ tdmPolicyPropertySearchDTO.getEnvType()
						+ "  ";
			}

			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
					.getPolicyStage())) {
				searchCriteria += " + "
						+ messageSource.getMessage("label.policy.stage", null,
								null) + " : "
						+ tdmPolicyPropertySearchDTO.getPolicyStage() + "  ";
			}
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
					.getPolicyTerm())) {
				searchCriteria += " + "
						+ messageSource.getMessage("label.policy.term", null,
								null) + " : "
						+ tdmPolicyPropertySearchDTO.getPolicyTerm() + "  ";
			}
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
					.getPolicyState())) {
				searchCriteria += " + "
						+ messageSource.getMessage("label.policy.state", null,
								null) + " : "
						+ tdmPolicyPropertySearchDTO.getPolicyState() + "  ";
			}
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
					.getAddproductType())) {
				searchCriteria += " + "
						+ messageSource.getMessage("label.policy.prodType",
								null, null) + " : "
						+ tdmPolicyPropertySearchDTO.getAddproductType() + "  ";
			}
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
					.getAddPolicyCovge())) {
				searchCriteria += " + "
						+ messageSource.getMessage("label.policy.poliCov",
								null, null) + " : "
						+ tdmPolicyPropertySearchDTO.getAddPolicyCovge() + "  ";
			}
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
					.getAddPayMethod())) {
				searchCriteria += " + "
						+ messageSource.getMessage("label.policy.payType",
								null, null) + " : "
						+ tdmPolicyPropertySearchDTO.getAddPayMethod() + "  ";
			}
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
					.getAddPayReq())) {
				String yorn = null;
				if (tdmPolicyPropertySearchDTO.getAddPayReq().equalsIgnoreCase(
						"Y")) {
					yorn = "Yes";
				} else if (tdmPolicyPropertySearchDTO.getAddPayReq()
						.equalsIgnoreCase("N")) {
					yorn = "No";
				} /*
				 * else { yorn = "Any"; }
				 */

				searchCriteria += " + "
						+ messageSource.getMessage(
								"label.policy.assoPayReqAdd", null, null)
						+ " : " + yorn + "  ";
			}

			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
					.getAddDocReq())) {
				String yorn = null;
				if (tdmPolicyPropertySearchDTO.getAddDocReq().equalsIgnoreCase(
						"Y")) {
					yorn = "Yes";
				} else if (tdmPolicyPropertySearchDTO.getAddDocReq()
						.equalsIgnoreCase("N")) {
					yorn = "No";
				} /*
				 * else { yorn = "Any"; }
				 */

				searchCriteria += " + "
						+ messageSource.getMessage("label.policy.assoDoc",
								null, null) + " : " + yorn + "  ";
			}

			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
					.getAddDocType())) {
				searchCriteria += " + "
						+ messageSource.getMessage("label.policy.docTypeAdd",
								null, null) + " : "
						+ tdmPolicyPropertySearchDTO.getAddDocType() + "  ";
			}

			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
					.getAddYearBuilt())) {
				searchCriteria += " + "
						+ messageSource.getMessage("label.policy.year", null,
								null) + " : "
						+ tdmPolicyPropertySearchDTO.getAddYearBuilt() + "  ";
			}
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
					.getAddConType())) {
				searchCriteria += " + "
						+ messageSource.getMessage("label.policy.consType",
								null, null) + " : "
						+ tdmPolicyPropertySearchDTO.getAddConType() + "  ";
			}
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO.getZipCode())) {
				searchCriteria += " + "
						+ messageSource.getMessage("label.policy.zipCode",
								null, null) + " : "
						+ tdmPolicyPropertySearchDTO.getZipCode() + "  ";
			}
			if (StringUtils
					.isNotEmpty(tdmPolicyPropertySearchDTO.getLeinIndi())) {

				String yorn = null;
				if (tdmPolicyPropertySearchDTO.getLeinIndi().equalsIgnoreCase(
						"Y")) {
					yorn = "Yes";
				} else {
					yorn = "No";
				}

				searchCriteria += " + "
						+ messageSource.getMessage("label.policy.leiindi",
								null, null) + " : " + yorn + "  ";
			}
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
					.getOptnlCovge())) {
				searchCriteria += " + "
						+ messageSource.getMessage("label.policy.opnlCovg",
								null, null) + " : "
						+ tdmPolicyPropertySearchDTO.getOptnlCovge() + "  ";
			}

		}

		return searchCriteria;
	}

}
