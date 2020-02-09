package com.tdm.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.tdm.constant.MessageConstant;
import com.tdm.dao.TDMDataMiningDAO;
import com.tdm.exception.DAOException;
import com.tdm.model.DO.NoOfDriverStg;
import com.tdm.model.DO.NoOfNameInsuStg;
import com.tdm.model.DO.NoOfVehiStg;
import com.tdm.model.DO.NoOfVioStg;
import com.tdm.model.DO.PolicySummaryStg;
import com.tdm.model.DO.TdmReservationDO;
import com.tdm.model.DTO.TDMAutoPolicySearchDTO;
import com.tdm.model.DTO.TDMPolicyPropertyNewSearchDTO;
import com.tdm.model.DTO.TDMReservedTestDataDTO;

@Component(MessageConstant.DAO_COMPONET_DM)
public class TDMDataMiningDAOImpl implements TDMDataMiningDAO {

	final static Logger logger = Logger.getLogger(TDMDataMiningDAOImpl.class);

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

	@Override
	public List<PolicySummaryStg> searchAutoPolicy(
			TDMAutoPolicySearchDTO tdmAtsSearchDTO, int offSet,
			int recordsperpage, boolean pageNationOnOffFlag,
			StringBuffer policyProps, String policytype,
			EntityManager managerCsaauser) throws DAOException {
		try {
			Boolean isNoOfDrivers = StringUtils.isNotEmpty(tdmAtsSearchDTO
					.getNoOfDrivers());
			Boolean isNoOfVehicles = StringUtils.isNotEmpty(tdmAtsSearchDTO
					.getNoOfVehi());
			Boolean isNoOfViolations = StringUtils.isNotEmpty(tdmAtsSearchDTO
					.getNoOfViola());
			Boolean isNoOfNameInsured = StringUtils.isNotEmpty(tdmAtsSearchDTO
					.getNoOfNamedInsu());
			Boolean isMinDue = StringUtils.isNotEmpty(tdmAtsSearchDTO
					.getMinimumDue());
			Boolean isTotalDue = StringUtils.isNotEmpty(tdmAtsSearchDTO
					.getTotalDue());

			StringBuffer query = new StringBuffer(
					"SELECT p.* ,d.COUNT  ,ins.COUNT noOfInsured ,vh.COUNT noOfVehicles ,vs.COUNT noOfViolations ,am.MIN_AMT_DUE ,am.AMT_DUE "

							+ " FROM DM_POLICY_SUMMARY_STG p "
							+ " LEFT OUTER JOIN DM_NO_OF_DRIVER_STG d ON p.POLICYDETAIL_ID=d.POLICYDETAIL_ID "
							+ " LEFT OUTER  JOIN DM_NO_OF_NAME_INSU_STG ins ON  p.POLICYDETAIL_ID=ins.POLICYDETAIL_ID "
							+ " LEFT OUTER  JOIN DM_NO_OF_VEHI_STG vh ON  p.POLICYDETAIL_ID=vh.POLICYDETAIL_ID "
							+ " LEFT OUTER  JOIN DM_NO_OF_VIO_STG vs ON  p.POLICYDETAIL_ID=vs.POLICYDETAIL_ID "
							+ " LEFT OUTER  JOIN DM_AMOUNT_STG am ON  p.POLICYNUMBER=am.POLICYNUMBER "
							+ "WHERE p.lob='AUTO'  AND p.CURRENTREVISIONIND=1 ");

			if (StringUtils.isNotEmpty(tdmAtsSearchDTO.getAddRiskCovge())
					|| StringUtils.isNotEmpty(tdmAtsSearchDTO.getPolicyCovge())) {
				query.append(" AND p.TXTYPE in ('endorsement', 'policy', 'renewal') ");
			}

			// Reservation not in
			if (null != policyProps && !policyProps.toString().isEmpty()) {
				query.append(" AND p.policynumber NOT IN ( ");
				policyProps = policyProps.replace(0, 1, "");
				query.append(policyProps);
				query.append(") ");
			}
//			query.append(" AND p.POLICYNUMBER not like 'Q%' ");
			// Product Type
			if (StringUtils.isNotEmpty(tdmAtsSearchDTO.getAddproductType())) {
				query.append(" AND p.productcd ='")
						.append(tdmAtsSearchDTO.getAddproductType())
						.append('\'');
			}
			// Policy Status
			if (StringUtils.isNotEmpty(tdmAtsSearchDTO.getPolicyStage())) {
				if (tdmAtsSearchDTO.getPolicyStage().equalsIgnoreCase("Active")) {
					query.append(" AND p.policystatuscd='issued' AND P.timedpolicystatuscd='inForce' ");
				} else if (tdmAtsSearchDTO.getPolicyStage().equalsIgnoreCase(
						"Cancelled")) {
					query.append(" AND p.policystatuscd='cancelled' AND P.timedpolicystatuscd='inForce' ");
				} else if (tdmAtsSearchDTO.getPolicyStage().equalsIgnoreCase(
						"Pending")) {
					query.append(" AND p.policystatuscd = 'issued'");
					query.append(" AND p.timedpolicystatuscd = 'inForcePending' ");
				} else if (tdmAtsSearchDTO.getPolicyStage().equalsIgnoreCase(
						"Expired")) {
					query.append(" AND p.policystatuscd = 'issued'");
					query.append(" AND p.timedpolicystatuscd = 'expired' ");
				} else if (tdmAtsSearchDTO.getPolicyStage().equalsIgnoreCase(
						"Lapsed")) {
					query.append(" AND p.policystatuscd = 'customerDeclined' ");
					query.append(" AND p.timedpolicystatuscd = 'customerDeclined' ");
					query.append(" AND p.txtype != 'quote' ");
				}
			}
			// Risk State
			if (StringUtils.isNotEmpty(tdmAtsSearchDTO.getRiskState())) {
				query.append(" AND p.riskstatecd ='")
						.append(tdmAtsSearchDTO.getRiskState()).append('\'');
			}
			// Policy Term
			if (StringUtils.isNotEmpty(tdmAtsSearchDTO.getPolicyTerm())) {
				query.append(" AND p.contracttermtypecd ='")
						.append(tdmAtsSearchDTO.getPolicyTerm()).append('\'');
			}
			// Payment Plan
			if (StringUtils.isNotEmpty(tdmAtsSearchDTO.getAddPaymentPlan())) {
				if (tdmAtsSearchDTO.getAddPaymentPlan().equalsIgnoreCase(
						"Annual")) {
					query.append(" AND p.paymentPlancd in ('annualSS_R','annualCA','annualSS','annualCA_R')");
				} else if (tdmAtsSearchDTO.getAddPaymentPlan()
						.equalsIgnoreCase("Monthly")) {
					query.append(" AND p.paymentPlancd in ('standartCA','Monthly6SS_R','MonthlySS_R','standartSS_R','MonthlySS','standart6SS','Monthly6SS','standartCA_R','standartSS','standart6SS_R')");
				} else if (tdmAtsSearchDTO.getAddPaymentPlan()
						.equalsIgnoreCase("Monthly-EFT")) {
					query.append(" AND p.paymentPlancd in ('lowDeposit6SS_NY_R','lowDepositSS_R','lowDepositSS_NY','lowDepositMoSS_NY_R','lowDepositSS','lowDepositMoSS_R','lowDepositCA','lowDepositMoSS_NY','lowDepositSS_NY_R','lowDepositCA_R','lowDeposit6SS','lowDeposit6SS_NY','lowDepositMo6SS_R','lowDeposit6SS_R','lowDepositMoSS','lowDepositMo6SS','zeroDownMoSS','zeroDownCA','zeroDown6SS','interimCA','zeroDownSS','zeroDownMo6SS','zeroDownCA_R','interimCA_R')");
				} else if (tdmAtsSearchDTO.getAddPaymentPlan()
						.equalsIgnoreCase("Quarterly")) {
					query.append(" AND p.paymentPlancd in ('quaterlyCA_R','quaterlySS','quaterly6SS_R','quaterlyCA','quaterlySS_R','quaterly6SS')");
				} else if (tdmAtsSearchDTO.getAddPaymentPlan()
						.equalsIgnoreCase("Semi-Annual")) {
					query.append(" AND p.paymentPlancd in ('semiAnnualCA_R','semiAnnualSS_R','semiAnnualSS','semiAnnual6SS','semiAnnualCA','semiAnnual6SS_R')");
				}
			}
			// Policy Coverage
			if (StringUtils.isNotEmpty(tdmAtsSearchDTO.getPolicyCovge())) {
				String codes = tdmAtsSearchDTO.getPolicyCovge().replaceAll(",",
						"\',\'");
				StringBuilder sb = new StringBuilder();
				if (codes.contains("BPIP")) {
					codes = sb.append(codes).append('\'').append(",")
							.append("'").append("PIP").toString();
				}
				if (codes.contains("ADDPIP")) {

					codes = sb.append(codes).append('\'').append(",")
							.append("'").append("APIP").toString();
				}
				if (codes.contains("PIPWORKLOSS")) {

					codes = sb.append(codes).append('\'').append(",")
							.append("'").append("IL").toString();
				}
				if (codes.contains("UM")) {
					codes = sb.append(codes).append('\'').append(",")
							.append("'").append("SUM").toString();
				}
				int coverageCodeSize = StringUtils.countMatches(codes, ",") + 1;
				String pivotQuery = " DM_POLICY_SUMMARY_STG p INNER JOIN (select POLICYDETAIL_ID from DM_COVERAGE_STG where COVERAGECD in ('"
						+ codes
						+ "') group by POLICYDETAIL_ID having count(distinct COVERAGECD) = "
						+ coverageCodeSize
						+ " order by POLICYDETAIL_ID) pt on pt.POLICYDETAIL_ID = p.POLICYDETAIL_ID ";

				query.replace(
						query.indexOf("DM_POLICY_SUMMARY_STG p"),
						query.indexOf("DM_POLICY_SUMMARY_STG p")
								+ "DM_POLICY_SUMMARY_STG p".toString().length(),
						pivotQuery);

			}

			// Risk Level Coverage
			if (StringUtils.isNotEmpty(tdmAtsSearchDTO.getAddRiskCovge())
					|| StringUtils
							.isNotEmpty(tdmAtsSearchDTO.getDeathBenifit())) {
				String codes = "";
				String riskCovgeCodes = "";
				if (StringUtils.isNotEmpty(tdmAtsSearchDTO.getAddRiskCovge())) {
					riskCovgeCodes = tdmAtsSearchDTO.getAddRiskCovge()
							.replaceAll(",", "\',\'");
					// riskCovgeCodes = "'" + riskCovgeCodes + "'";
				}

				if (StringUtils.isNotEmpty(riskCovgeCodes)) {
					StringBuilder sb = new StringBuilder();
					if (riskCovgeCodes.contains("UMPDDED")) {
						riskCovgeCodes = sb.append(riskCovgeCodes).append('\'')
								.append(",").append("'").append("UMPD")
								.toString();
					}

					codes = riskCovgeCodes;
				}
				if (StringUtils.isNotEmpty(codes)) {
					String deathBenifit = tdmAtsSearchDTO.getAutoDeath();
					String totalDisability = tdmAtsSearchDTO.getAutoTotDisa();
					if (StringUtils.isNotEmpty(deathBenifit)
							&& deathBenifit.equalsIgnoreCase("Yes")
							&& StringUtils.isNotEmpty(totalDisability)
							&& totalDisability.equalsIgnoreCase("Yes")) {
						codes += " ','ADB', 'TD ";
					}else if(StringUtils.isNotEmpty(deathBenifit)
							&& deathBenifit.equalsIgnoreCase("Yes")
							&& StringUtils.isNotEmpty(totalDisability)
							&& totalDisability.equalsIgnoreCase("No")){
						codes += " ','ADB";
					}
					else if(StringUtils.isNotEmpty(deathBenifit)
							&& deathBenifit.equalsIgnoreCase("No")
							&& StringUtils.isNotEmpty(totalDisability)
							&& totalDisability.equalsIgnoreCase("Yes")){
						codes += " ','TD";
					}
					int coverageCodeSize = StringUtils.countMatches(codes, ",") + 1;
					String pivotQuery = " DM_POLICY_SUMMARY_STG p INNER JOIN (select POLICYDETAIL_ID from DM_COVERAGE_RISK_STG where COVERAGECD in ('"
							+ codes
							+ "') group by POLICYDETAIL_ID having count(distinct COVERAGECD) = "
							+ coverageCodeSize
							+ " order by POLICYDETAIL_ID) pt1 on pt1.POLICYDETAIL_ID = p.POLICYDETAIL_ID ";

					query.replace(query.indexOf("DM_POLICY_SUMMARY_STG p"),
							query.indexOf("DM_POLICY_SUMMARY_STG p")
									+ "DM_POLICY_SUMMARY_STG p".toString()
											.length(), pivotQuery);

				}
			}else  if ((StringUtils.isNotEmpty(tdmAtsSearchDTO.getAutoDeath())
					&& !tdmAtsSearchDTO.getAutoDeath().equalsIgnoreCase("Any") )
					|| (StringUtils
							.isNotEmpty(tdmAtsSearchDTO.getAutoTotDisa()) && !tdmAtsSearchDTO
							.getAutoTotDisa().equalsIgnoreCase("Any"))
					) {

				String deathBenifit = tdmAtsSearchDTO.getAutoDeath();
				String totalDisability = tdmAtsSearchDTO.getAutoTotDisa();
				query.append(" AND CR.COVERAGECD in( ");
				if (StringUtils.isNotEmpty(deathBenifit)
						&& deathBenifit.equalsIgnoreCase("Yes")) {
					query.append(" 'ADB'");
				} else if (StringUtils.isNotEmpty(totalDisability)
						&& totalDisability.equalsIgnoreCase("Yes")) {
					query.append(" 'TD'");
				}
				query.append(" ) ");

				String automobileQuery = "DM_POLICY_SUMMARY_STG p INNER JOIN DM_COVERAGE_RISK_STG CR on CR.POLICYDETAIL_ID = p.POLICYDETAIL_ID ";
				query.replace(query.indexOf("DM_POLICY_SUMMARY_STG p"),
						query.indexOf("DM_POLICY_SUMMARY_STG p")
								+ " DM_POLICY_SUMMARY_STG p".toString()
										.length(), automobileQuery);
			}

			// No Of Drivers
			if (isNoOfDrivers) {
				query.append(" AND d.COUNT =").append(
						tdmAtsSearchDTO.getNoOfDrivers());
			}

			// No Of Named Insu
			if (isNoOfNameInsured) {
				query.append(" AND ins.COUNT =").append(
						tdmAtsSearchDTO.getNoOfNamedInsu());
			}

			// No Of Vehi
			if (isNoOfVehicles) {

				query.append(" AND vh.COUNT =").append(
						tdmAtsSearchDTO.getNoOfVehi());
			}

			// No Of Vio
			if (isNoOfViolations) {
				query.append(" AND vs.COUNT =").append(
						tdmAtsSearchDTO.getNoOfViola());
			}
			// Minimum Due && Total Due
			if (StringUtils.isNotEmpty(tdmAtsSearchDTO.getMinimumDue())) {

				if (tdmAtsSearchDTO.getMinimumDue().equalsIgnoreCase("Yes")) {
					// Minimum Amount due
					query.append(" AND am.MIN_AMT_DUE_FLAG='").append("Y")
							.append('\'');
				} else if (tdmAtsSearchDTO.getMinimumDue().equalsIgnoreCase(
						"No")) {
					query.append(" AND am.MIN_AMT_DUE_FLAG='").append("N")
							.append('\'');
				}

			}

			if (StringUtils.isNotEmpty(tdmAtsSearchDTO.getTotalDue())) {

				if (tdmAtsSearchDTO.getTotalDue().equalsIgnoreCase("Yes")) {
					// Minimum Amount due
					query.append(" AND am.AMT_DUE_FLAG='").append("Y")
							.append('\'');
				} else if (tdmAtsSearchDTO.getTotalDue().equalsIgnoreCase("No")) {
					query.append(" AND am.AMT_DUE_FLAG='").append("N")
							.append('\'');
				}
			}
			// Auto Pay
			if (StringUtils.isNotEmpty(tdmAtsSearchDTO.getAutoPay())) {
				if (tdmAtsSearchDTO.getAutoPay().equalsIgnoreCase("Yes")) {
					// Minimum Amount due
					query.append(" AND p.AUTOPAY_ELIGIBILITY_FLAG='").append("Y")
							.append('\'');
				} else if (tdmAtsSearchDTO.getAutoPay().equalsIgnoreCase("No")) {
					query.append(" AND p.AUTOPAY_ELIGIBILITY_FLAG='").append("N")
							.append('\'');
				}
			}

			long startTime = System.currentTimeMillis();

			@SuppressWarnings(AppConstant.UNCHECKED)
			List<PolicySummaryStg> policySummaryStgDOs = managerCsaauser
					.createNativeQuery(query.toString()).setFirstResult(0)
					.setMaxResults(1000).getResultList();
			policySummaryStgDOs = populateAutoPolicySummaryStgList(policySummaryStgDOs);

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
			illegalStateEx.printStackTrace();
			throw new DAOException(messageSource.getMessage(
					"NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION", null, null),
					illegalStateEx);
		} catch (IllegalArgumentException illegalArgEx) {
			logger.error("MessageConstant.INVALID_QUERY_EXCEPTION"
					+ illegalArgEx);
			illegalArgEx.printStackTrace();
			throw new DAOException(messageSource.getMessage(
					"INVALID_QUERY_EXCEPTION", null, null), illegalArgEx);
		} catch (NullPointerException nullPointerEx) {
			nullPointerEx.printStackTrace();
			nullPointerEx.printStackTrace();
			logger.error("MessageConstant.NULL_POINTER_EXCEPTION"
					+ nullPointerEx);
			throw new DAOException(messageSource.getMessage(
					"NULL_POINTER_EXCEPTION", null, null), nullPointerEx);
		} catch (Exception otherEx) {
			otherEx.printStackTrace();
			logger.error("MessageConstant.DATABASE_EXCEPTION" + otherEx);
			throw new DAOException(messageSource.getMessage(
					"DATABASE_EXCEPTION", null, null), otherEx);
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
					summaryStgs.add(policySummaryStg);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return summaryStgs;

	}

	private List<PolicySummaryStg> populatePolicySummaryStgList(
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
							.setCurrentrevisionind(dataArr[10] != null ? dataArr[10]
									.toString() : null);

					summaryStgs.add(policySummaryStg);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return summaryStgs;

	}

	private List<PolicySummaryStg> populatePropertyPolicySummaryStgList(
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
							.setProductcd(dataArr[4] != null ? dataArr[4]
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
							.setCurrentrevisionind(dataArr[13] != null ? dataArr[13]
									.toString() : null);
					policySummaryStg
							.setTotalAmountDue(dataArr[17] != null ? Double
									.valueOf(dataArr[17].toString()) : null);

					summaryStgs.add(policySummaryStg);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return summaryStgs;

	}

	@Override
	public List<PolicySummaryStg> searchPropertyPolicy(
			TDMPolicyPropertyNewSearchDTO tdmPolicyPropertySearchDTO,
			int offSet, int recordsperpage, boolean pageNationOnOffFlag,
			StringBuffer policyProps, String policytype,
			EntityManager managerCsaauser) throws DAOException {
		try {
			StringBuffer query = new StringBuffer(
					"SELECT p.*, am.AMT_DUE FROM DM_POLICY_SUMMARY_STG p  LEFT OUTER JOIN DM_AMOUNT_STG am ON  p.POLICYNUMBER=am.POLICYNUMBER "
							+ "WHERE p.lob='HOME' AND p.CURRENTREVISIONIND=1 ");

			// Reservation not in
			if (null != policyProps && !policyProps.toString().isEmpty()) {
				query.append(" AND p.policynumber NOT IN ( ");
				policyProps = policyProps.replace(0, 1, "");
				query.append(policyProps);
				query.append(") ");
			}

//			query.append(" AND p.POLICYNUMBER not like 'Q%' ");
			// Product Type
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
					.getAddproductType())) {
				query.append(" AND p.productcd ='")
						.append(tdmPolicyPropertySearchDTO.getAddproductType())
						.append('\'');
			}

			// Policy Type
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
					.getPolicyType())) {
				query.append(" AND p.policyformcd ='")
						.append(tdmPolicyPropertySearchDTO.getPolicyType())
						.append('\'');
			}

			// Risk State
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
					.getPolicyState())) {
				query.append(" AND p.riskstatecd ='")
						.append(tdmPolicyPropertySearchDTO.getPolicyState())
						.append('\'');
			}
			// Policy Status
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
					.getPolicyStage())) {
				if (tdmPolicyPropertySearchDTO.getPolicyStage()
						.equalsIgnoreCase("Active")) {
					query.append(" AND p.policystatuscd='issued' AND P.timedpolicystatuscd='inForce' ");
				} else if (tdmPolicyPropertySearchDTO.getPolicyStage()
						.equalsIgnoreCase("Cancelled")) {
					query.append(" AND p.policystatuscd='cancelled' AND P.timedpolicystatuscd='inForce' ");
				} else if (tdmPolicyPropertySearchDTO.getPolicyStage()
						.equalsIgnoreCase("Pending")) {
					query.append(" AND p.policystatuscd = 'issued'");
					query.append(" AND p.timedpolicystatuscd = 'inForcePending' ");
				} else if (tdmPolicyPropertySearchDTO.getPolicyStage()
						.equalsIgnoreCase("Expired")) {
					query.append(" AND p.policystatuscd = 'issued'");
					query.append(" AND p.timedpolicystatuscd = 'expired' ");
				} else if (tdmPolicyPropertySearchDTO.getPolicyStage()
						.equalsIgnoreCase("Lapsed")) {
					query.append(" AND p.policystatuscd = 'customerDeclined'");
					query.append(" AND p.timedpolicystatuscd = 'customerDeclined' ");
					query.append(" AND p.txtype != 'quote' ");
				}
			}

			// Payment Plan
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
					.getAddPaymentPlan())) {
				if (tdmPolicyPropertySearchDTO.getAddPaymentPlan()
						.equalsIgnoreCase("Annual")) {
					query.append(" AND p.paymentPlancd in ('annualSS_R','annualCA','annualSS','annualCA_R')");
				} else if (tdmPolicyPropertySearchDTO.getAddPaymentPlan()
						.equalsIgnoreCase("Monthly")) {
					query.append(" AND p.paymentPlancd in ('standartCA','Monthly6SS_R','MonthlySS_R','standartSS_R','MonthlySS','standart6SS','Monthly6SS','standartCA_R','standartSS','standart6SS_R')");
				} else if (tdmPolicyPropertySearchDTO.getAddPaymentPlan()
						.equalsIgnoreCase("Monthly EFT")) {
					query.append(" AND p.paymentPlancd in ('lowDeposit6SS_NY_R','lowDepositSS_R','lowDepositSS_NY','lowDepositMoSS_NY_R','lowDepositSS','lowDepositMoSS_R','lowDepositCA','lowDepositMoSS_NY','lowDepositSS_NY_R','lowDepositCA_R','lowDeposit6SS','lowDeposit6SS_NY','lowDepositMo6SS_R','lowDeposit6SS_R','lowDepositMoSS','lowDepositMo6SS','zeroDownMoSS','zeroDownCA','zeroDown6SS','interimCA','zeroDownSS','zeroDownMo6SS','zeroDownCA_R','interimCA_R')");
				} else if (tdmPolicyPropertySearchDTO.getAddPaymentPlan()
						.equalsIgnoreCase("Quarterly")) {
					query.append(" AND p.paymentPlancd in ('quaterlyCA_R','quaterlySS','quaterly6SS_R','quaterlyCA','quaterlySS_R','quaterly6SS')");
				} else if (tdmPolicyPropertySearchDTO.getAddPaymentPlan()
						.equalsIgnoreCase("Semi-Annual")) {
					query.append(" AND p.paymentPlancd in ('semiAnnualCA_R','semiAnnualSS_R','semiAnnualSS','semiAnnual6SS','semiAnnualCA','semiAnnual6SS_R')");
				} else if (tdmPolicyPropertySearchDTO.getAddPaymentPlan()
						.equalsIgnoreCase("Mortgagee Paid")) {
					query.append(" AND p.paymentPlancd in ('mortgageeHO','mortgageeHO_R') ");
				}
			}
			// Policy Coverage
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
					.getPolicyCovge())) {
				String codes = tdmPolicyPropertySearchDTO.getPolicyCovge()
						.replaceAll(",", "\',\'");
				int coverageCodeSize = StringUtils.countMatches(codes, ",") + 1;
				String pivotQuery = " DM_POLICY_SUMMARY_STG p INNER JOIN (select POLICYDETAIL_ID from DM_COVERAGE_RISK_STG where COVERAGECD in ('"
						+ codes
						+ "') group by POLICYDETAIL_ID having count(distinct COVERAGECD) = "
						+ coverageCodeSize
						+ " order by POLICYDETAIL_ID) pt on pt.POLICYDETAIL_ID = p.POLICYDETAIL_ID ";

				query.replace(
						query.indexOf("DM_POLICY_SUMMARY_STG p"),
						query.indexOf("DM_POLICY_SUMMARY_STG p")
								+ "DM_POLICY_SUMMARY_STG p".toString().length(),
						pivotQuery);

				query.append(" AND p.TXTYPE in ('endorsement', 'policy', 'renewal')  ");
			}

			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
					.getTotalDueFlag())
					|| StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
							.getTotalDueFlag())) {

				if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
						.getTotalDueFlag()) && !tdmPolicyPropertySearchDTO
						.getTotalDueFlag().equalsIgnoreCase("Any")) {
					// Total Amount due
					query.append(" AND am.AMT_DUE_FLAG='")
							.append(tdmPolicyPropertySearchDTO
									.getTotalDueFlag().equalsIgnoreCase("Yes") ? "Y"
									: "N").append('\'');
				}
			}
			if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
					.getMinDueFlag())
					|| StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
							.getMinDueFlag())) {
				if (StringUtils.isNotEmpty(tdmPolicyPropertySearchDTO
						.getMinDueFlag()) && !tdmPolicyPropertySearchDTO
						.getMinDueFlag().equalsIgnoreCase("Any")) {
					// Min Amount due
					query.append(" AND am.MIN_AMT_DUE_FLAG='")
							.append(tdmPolicyPropertySearchDTO.getMinDueFlag()
									.equalsIgnoreCase("Yes") ? "Y" : "N")
							.append('\'');
				}

			}

			// query.append(" ORDER BY p.policynumber");
			long startTime = System.currentTimeMillis();

			@SuppressWarnings(AppConstant.UNCHECKED)
			List<PolicySummaryStg> policySummaryStgDOs = managerCsaauser
					.createNativeQuery(query.toString()).setFirstResult(0)
					.setMaxResults(1000).getResultList();

			policySummaryStgDOs = populatePropertyPolicySummaryStgList(policySummaryStgDOs);

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
			otherEx.printStackTrace();
			throw new DAOException(messageSource.getMessage(
					"DATABASE_EXCEPTION", null, null), otherEx);
		}
		return reservationDOs;
	}

	@Override
	public List<TdmReservationDO> getReservedRecords(String userId,
			String searchType, EntityManager managerCsaaUser)
			throws DAOException {
		try {
			long startTime = System.currentTimeMillis();
			@SuppressWarnings("unchecked")
			List<TdmReservationDO> list = managerCsaaUser
					.createQuery(
							" FROM TdmReservationDO p where p.reserveDataType IN ('"
									+ searchType + "')").setFirstResult(0)
					.setMaxResults(1000).getResultList();

			/*
			 * List<TdmReservationDO> list = managerCsaaUser.createQuery(
			 * " FROM TdmReservationDO p where p.reserveDataType IN ('" +
			 * searchType + "')  AND p.userId='" + userId + "'")
			 * .getResultList();
			 */
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

	public TDMPolicyPropertyNewSearchDTO getTDMReservedPropertyTestDataListPerUser(
			TDMPolicyPropertyNewSearchDTO tdmPolicyPropertySearchDTO)
			throws Exception {

		try {
			Connection dbConnection = tdmUserDS.getConnection();

			String searchCriteria = tdmPolicyPropertySearchDTO.getSearchCriti();
			if (org.springframework.util.StringUtils.hasText(searchCriteria)) {

				searchCriteria = searchCriteria.substring(18);
			}

			System.out.println("searchCriteria >>>>>>>>" + searchCriteria);

			PreparedStatement preparedStmt = dbConnection
					.prepareStatement("SELECT USER_ID,TEST_CASE_ID,  COUNT(RESERVE_DATA) FROM DM_RESERVATION WHERE LOCKED = ?  and trim(RESERVE_DATA)=trim(?)  and RESERVE_DATA_TYPE = ? GROUP BY user_id,TEST_CASE_ID");
			// PreparedStatement preparedStmt = dbConnection
			// .prepareStatement("SELECT usr.USERNAME ,  count(dmr.RESERVE_DATA) FROM DM_RESERVATION dmr INNER JOIN USERS usr on dmr.USER_ID = usr.USER_ID WHERE dmr.LOCKED = ?  and trim(dmr.RESERVE_DATA)=trim(?)  and dmr.RESERVE_DATA_TYPE = ? GROUP BY usr.username");

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
				tdmReservedTestDataDTO.setTestCaseId(result.getString(2));
				tdmReservedTestDataDTO.setNoOfRecordsResvByUser(result
						.getInt(3));

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
		return tdmPolicyPropertySearchDTO;
	}

	public TDMAutoPolicySearchDTO getTDMReservedAutoTestDataListPerUser(
			TDMAutoPolicySearchDTO tdmPolicyPropertySearchDTO) throws Exception {

		try {
			Connection dbConnection = tdmUserDS.getConnection();

			String searchCriteria = tdmPolicyPropertySearchDTO.getSearchCriti();
			if (org.springframework.util.StringUtils.hasText(searchCriteria)) {

				searchCriteria = searchCriteria.substring(18);
			}

			System.out.println("searchCriteria >>>>>>>>" + searchCriteria);

			PreparedStatement preparedStmt = dbConnection
					.prepareStatement("SELECT USER_ID,TEST_CASE_ID,  COUNT(RESERVE_DATA) FROM DM_RESERVATION WHERE LOCKED = ?  and trim(RESERVE_DATA)=trim(?)  and RESERVE_DATA_TYPE = ? GROUP BY user_id,TEST_CASE_ID");
			// PreparedStatement preparedStmt = dbConnection
			// .prepareStatement("SELECT usr.USERNAME ,  count(dmr.RESERVE_DATA) FROM DM_RESERVATION dmr INNER JOIN USERS usr on dmr.USER_ID = usr.USER_ID WHERE dmr.LOCKED = ?  and trim(dmr.RESERVE_DATA)=trim(?)  and dmr.RESERVE_DATA_TYPE = ? GROUP BY usr.username");

			preparedStmt.setString(1, "Y");
			preparedStmt.setString(2, searchCriteria);
			preparedStmt.setString(3, "CSAA_AU");
			ResultSet result = preparedStmt.executeQuery();
			TDMReservedTestDataDTO tdmReservedTestDataDTO = null;
			List<TDMReservedTestDataDTO> tdmReservedTestDataDTOList = new ArrayList<TDMReservedTestDataDTO>();
			int rowCount = 1;
			while (result.next()) {
				tdmReservedTestDataDTO = new TDMReservedTestDataDTO();
				tdmReservedTestDataDTO.setSno(rowCount);
				tdmReservedTestDataDTO.setUserId(result.getString(1));
				tdmReservedTestDataDTO.setTestCaseId(result.getString(2));
				tdmReservedTestDataDTO.setNoOfRecordsResvByUser(result
						.getInt(3));

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
		return tdmPolicyPropertySearchDTO;
	}

	public int getTDMAutoPolicyReservationPerUser(
			TDMAutoPolicySearchDTO tdmPolicyPropertySearchDTO) throws Exception {
		int reservedRecordsByUser = 0;
		try {
			Connection dbConnection = tdmUserDS.getConnection();

			String searchCriteria = tdmPolicyPropertySearchDTO.getSearchCriti();
			if (org.springframework.util.StringUtils.hasText(searchCriteria)) {

				searchCriteria = searchCriteria.substring(18);
			}

			System.out.println("searchCriteria >>>>>>>>" + searchCriteria);

			PreparedStatement preparedStmt = dbConnection
					.prepareStatement("SELECT COUNT(USER_ID) FROM DM_RESERVATION WHERE LOCKED = ?  and trim(RESERVE_DATA)=trim(?)  and RESERVE_DATA_TYPE = ? and USER_ID = ? GROUP BY user_id");
			preparedStmt.setString(1, "Y");
			preparedStmt.setString(2, searchCriteria);
			preparedStmt.setString(3, "CSAA_AU");
			preparedStmt.setString(3, tdmPolicyPropertySearchDTO.getUserId());
			ResultSet result = preparedStmt.executeQuery();

			while (result.next()) {
				reservedRecordsByUser = result.getInt(1);
			}

		} catch (Exception e) {
			throw e;
		}
		return reservedRecordsByUser;
	}

	public int getTDMPropertyPolicyReservationPerUser(
			TDMAutoPolicySearchDTO tdmPolicyPropertySearchDTO) throws Exception {
		int reservedRecordsByUser = 0;
		try {
			Connection dbConnection = tdmUserDS.getConnection();

			String searchCriteria = tdmPolicyPropertySearchDTO.getSearchCriti();
			if (org.springframework.util.StringUtils.hasText(searchCriteria)) {

				searchCriteria = searchCriteria.substring(18);
			}

			System.out.println("searchCriteria >>>>>>>>" + searchCriteria);

			PreparedStatement preparedStmt = dbConnection
					.prepareStatement("SELECT COUNT(USER_ID) FROM DM_RESERVATION WHERE LOCKED = ?  and trim(RESERVE_DATA)=trim(?)  and RESERVE_DATA_TYPE = ? and USER_ID = ? GROUP BY user_id");
			preparedStmt.setString(1, "Y");
			preparedStmt.setString(2, searchCriteria);
			preparedStmt.setString(3, "CSAA_PO");
			preparedStmt.setString(3, tdmPolicyPropertySearchDTO.getUserId());
			ResultSet result = preparedStmt.executeQuery();

			while (result.next()) {
				reservedRecordsByUser = result.getInt(1);
			}

		} catch (Exception e) {
			throw e;
		}
		return reservedRecordsByUser;
	}

	public List<PolicySummaryStg> getPoliciesToReserve(
			TDMAutoPolicySearchDTO tdmPolicyPropertySearchDTO,
			EntityManager managerCsaauser) throws DAOException {
		List<PolicySummaryStg> policySummaryStgDOs = null;
		try {

			String policyNmbersList = tdmPolicyPropertySearchDTO
					.getPolicyNumberList();

			if (org.springframework.util.StringUtils.hasText(policyNmbersList)) {

				policyNmbersList = policyNmbersList.replaceAll("\"", "");
			}
			String[] policyNmbers = policyNmbersList.split(",");

			StringBuilder sb = new StringBuilder();
			for (String str : policyNmbers) {
				sb.append("'").append(str).append('\'').append(",");
			}
			String str = sb.toString();

			str = str.substring(0, str.length() - 1);

			// StringBuffer query = new StringBuffer(
			// "SELECT p.* FROM DM_POLICY_SUMMARY_STG p WHERE p.policynumber in("
			// + str + ") ");
			// query.append(" AND  p.CURRENTREVISIONIND='1' ");
			StringBuffer query = new StringBuffer(
					" SELECT p.* ,d.COUNT  ,ins.COUNT noOfInsured ,vh.COUNT noOfVehicles ,vs.COUNT noOfViolations ,am.MIN_AMT_DUE ,am.AMT_DUE "
							//+ " ,RANK() OVER (PARTITION BY p.policynumber  ORDER BY p.transactioneffectivedate desc ) rank "
							+ " FROM DM_POLICY_SUMMARY_STG p "
							+ " LEFT OUTER JOIN DM_NO_OF_DRIVER_STG d ON p.POLICYDETAIL_ID=d.POLICYDETAIL_ID "
							+ " LEFT OUTER  JOIN DM_NO_OF_NAME_INSU_STG ins ON  p.POLICYDETAIL_ID=ins.POLICYDETAIL_ID "
							+ " LEFT OUTER  JOIN DM_NO_OF_VEHI_STG vh ON  p.POLICYDETAIL_ID=vh.POLICYDETAIL_ID "
							+ " LEFT OUTER  JOIN DM_NO_OF_VIO_STG vs ON  p.POLICYDETAIL_ID=vs.POLICYDETAIL_ID "
							+ " LEFT OUTER  JOIN DM_AMOUNT_STG am ON  p.POLICYNUMBER=am.POLICYNUMBER "
							+ "WHERE p.lob='AUTO'  AND p.CURRENTREVISIONIND=1 AND p.policynumber in( "+ str + " ) "
							+ " AND p.transactioneffectivedate IN(SELECT MAX(ab.transactioneffectivedate) from DM_POLICY_SUMMARY_STG ab WHERE p.policynumber=ab.policynumber ) ");

			policySummaryStgDOs = managerCsaauser
					.createNativeQuery(query.toString()).setFirstResult(0)
					.setMaxResults(1000).getResultList();

			policySummaryStgDOs = populateAutoPolicySummaryStgList(policySummaryStgDOs);

		} catch (Exception e) {

		}
		return policySummaryStgDOs;
	}

	public List<PolicySummaryStg> getPoliciesToReserve(
			TDMPolicyPropertyNewSearchDTO tdmPolicyPropertySearchDTO,
			EntityManager managerCsaauser) throws DAOException {
		List<PolicySummaryStg> policySummaryStgDOs = null;
		try {

			String policyNmbersList = tdmPolicyPropertySearchDTO
					.getPolicyNumberList();

			if (org.springframework.util.StringUtils.hasText(policyNmbersList)) {

				policyNmbersList = policyNmbersList.replaceAll("\"", "");
			}
			String[] policyNmbers = policyNmbersList.split(",");

			StringBuilder sb = new StringBuilder();
			for (String str : policyNmbers) {
				sb.append("'").append(str).append('\'').append(",");
			}
			String str = sb.toString();

			str = str.substring(0, str.length() - 1);

			StringBuffer query = new StringBuffer(
					" SELECT p.*  "
					+ "FROM DM_POLICY_SUMMARY_STG p WHERE p.policynumber in(" + str + ") ");
			query.append(" AND p.transactioneffectivedate IN(SELECT MAX(ab.transactioneffectivedate) "
					+ "from DM_POLICY_SUMMARY_STG ab WHERE p.policynumber=ab.policynumber ) ");
			policySummaryStgDOs = managerCsaauser
					.createNativeQuery(query.toString()).setFirstResult(0)
					.setMaxResults(1000).getResultList();

			policySummaryStgDOs = populatePolicySummaryStgList(policySummaryStgDOs);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return policySummaryStgDOs;
	}

}
