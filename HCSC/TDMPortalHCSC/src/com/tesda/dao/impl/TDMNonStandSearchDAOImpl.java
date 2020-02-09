/*---------------------------------------------------------------------------------------
 * Object Name: TDMNonStandSearchDAOImpl.Java
 * 
 * --------------------------------------------------------------------------------------
 *
 * Copyright: 2015 Capgemini Financial Services
 *---------------------------------------------------------------------------------------*/

package com.tesda.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.tesda.constants.MessageConstants;
import com.tesda.constants.TDMConstants;
import com.tesda.constants.TDMExceptionCode;
import com.tesda.dao.TDMNonStandSearchDao;
import com.tesda.exception.DAOException;
import com.tesda.model.DO.TDMSearchDetailsDO;
import com.tesda.model.DO.TdmBlendedMemberDO;
import com.tesda.model.DO.TdmLookupDO;
import com.tesda.model.DO.TdmReservationDO;
import com.tesda.model.DO.TdmSubscriberDetailsDO;
import com.tesda.model.DO.TdmUserDO;
import com.tesda.model.DTO.TDMNonStandardSearchDTO;

/*
 *  Transaction class used between the TDMNonStandSearchServiceImpl class and Data base.
 *  Used to get the Find test data form search field options from DB.
 *  Gets the search results based on search criteria choose in Find Test data.
 *  Reserves the selected search result records.
 *  Un reserves the selected records from My reservation dash board.
 *  Gets the wild card search results for the fields Account Name, Account Name and Subscriber Id.
 */

@Component(TDMConstants.NONSTANDSRCH_DAOIMPL)
public class TDMNonStandSearchDAOImpl implements TDMNonStandSearchDao
{

	private static final Logger logger = LoggerFactory.getLogger(TDMNonStandSearchDAOImpl.class);

	/**
	 * 
	 */
	@SuppressWarnings(TDMConstants.UNCHECKED)
	@Override
	public List<TdmLookupDO> getTDMSearchFields(EntityManager managerLookup) throws DAOException
	{
		try
		{
			logger.info(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.GET_TDMSRCHFLDS);
			List<TdmLookupDO> nonStandFields = managerLookup.createQuery(
					"SELECT l FROM TdmLookupDO l").getResultList();

			logger.info(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.GET_TDMSRCHFLDS
					+ MessageConstants.LOG_INFO_RETURN);
			return nonStandFields;
		}
		catch (NoResultException ex)
		{
			logger.info(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.GET_TDMSRCHFLDS,
					ex.getMessage());
			throw new DAOException(TDMExceptionCode.NORESULT, ex);
		}
		catch (IllegalStateException ex)
		{
			logger.info(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.GET_TDMSRCHFLDS,
					ex.getMessage());
			throw new DAOException(TDMExceptionCode.ILLEGAL_STATE, ex);
		}
		catch (IllegalArgumentException ex)
		{
			logger.info(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.GET_TDMSRCHFLDS,
					ex.getMessage());
			throw new DAOException(TDMExceptionCode.ILLEGAL_ARGUMENT, ex);
		}
		catch (Exception ex)
		{
			logger.info(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.GET_TDMSRCHFLDS,
					ex.getMessage());
			throw new DAOException(TDMExceptionCode.EXCEPTION, ex);
		}
	}

	/**
	 * 
	 */
	@Override
	public List<TdmReservationDO> saveReserveData(List<TdmReservationDO> tdmResrveList,
			String testCaseName, String testCaseId, EntityManager managerUsr) throws DAOException
	{
		logger.info(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.SAVE_RSERV_DATA);
		List<TdmReservationDO> reservationDOs = null;
		try
		{
			if (null != tdmResrveList && 0 < tdmResrveList.size())
			{
				managerUsr.getTransaction().begin();
				reservationDOs = new ArrayList<TdmReservationDO>();
				for (TdmReservationDO tdmReserveDo : tdmResrveList)
				{
					if (tdmReserveDo != null)
					{
						tdmReserveDo.setTestCaseId(testCaseId);
						tdmReserveDo.setTestCaseName(testCaseName);
						tdmReserveDo = managerUsr.merge(tdmReserveDo);
						reservationDOs.add(tdmReserveDo);
					}
				}
				managerUsr.getTransaction().commit();
			}
			logger.info(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.SAVE_RSERV_DATA
					+ MessageConstants.LOG_INFO_RETURN);
			return reservationDOs;
		}
		catch (NoResultException ex)
		{
			logger.error(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.SAVE_RSERV_DATA,
					ex.getMessage());
			throw new DAOException(TDMExceptionCode.NORESULT, ex);
		}
		catch (IllegalStateException ex)
		{
			logger.error(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.SAVE_RSERV_DATA,
					ex.getMessage());
			throw new DAOException(TDMExceptionCode.ILLEGAL_STATE, ex);
		}
		catch (IllegalArgumentException ex)
		{
			logger.error(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.SAVE_RSERV_DATA,
					ex.getMessage());
			throw new DAOException(TDMExceptionCode.ILLEGAL_ARGUMENT, ex);
		}
		catch (Exception ex)
		{
			logger.error(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.SAVE_RSERV_DATA,
					ex.getMessage());
			throw new DAOException(TDMExceptionCode.EXCEPTION, ex);
		}
	}

	@SuppressWarnings(TDMConstants.UNCHECKED)
	@Override
	public List<Object[]> getTDMNonStandardSearchRecords(TDMNonStandardSearchDTO tdmNonStandDTO,
			int offSet, int recordsperpage, EntityManager subscrManager) throws DAOException
	{
		try
		{
			logger.info(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.GET_NS_RECORDS);
			boolean isDisregard = false;
			String acName = tdmNonStandDTO.getAccountName();
			String acNum = tdmNonStandDTO.getAccountNum();
			String subscrbrId = tdmNonStandDTO.getSubscId();
			if (false
					|| (StringUtils.isNotEmpty(acName) && StringUtils.isNotEmpty(acName.trim()))
					|| (StringUtils.isNotEmpty(acNum) && StringUtils.isNotEmpty(acNum.trim()))
					|| (StringUtils.isNotEmpty(subscrbrId) && StringUtils.isNotEmpty(subscrbrId
							.trim())))
			{
				/*
				 * In case user provides AccountName or AccountNumber or
				 * Subscriber ID or combination of these three in the search
				 * criteria, do not include the rest of fields in search
				 * criteria. Search will happen only on these three fields.
				 */
				isDisregard = true;
			}
			StringBuffer sb = new StringBuffer();
			StringBuffer query = new StringBuffer(
					"SELECT distinct A.* from (SELECT "
							+ " SD1.SUBS_ID, SD1.MBR_CAT,SD1.FIRST_NAME, SD1.LAST_NAME,SD1.GNDR_CD,SD1.DOB,SD1.ZIPCODE,"
							+ " SD1.GRP_NBR,SD1.ACCOUNT_NAME, SD1.MBR_EFF_DT, "
							+ " SD1.MBR_END_DT,SD1.MBRSHP_COVRG_GRP_SECT_EFF_DT,SD1.MBRSHP_COVRG_GRP_SECT_END_DT,"
							+ " SD1.MBR_ORIG_EFF_DT,"
							+ " LTRIM(COALESCE(SD1.MED_CLAIM_IND,' ')|| ' '||COALESCE(SD1.PRESCRIPTION_CLAIM_IND,' ' )|| ' '||COALESCE(SD1.DENTAL_CLAIM_IND,' ')) AS EXISTING_CLAIMS,"
							+ " SD1.PRIMERY_COVG_IND , "
							+ " SD1.STATE_CD, SD1.EXCHANGE_TYPE, SD1.BLNDED_TYPE, SD1.PROD_TYPE, SD1.MBR_ID, SD1.FUNDING_IND, SD1.PCP_PCPMG"
							+ " REPLACE1 from  SUB_DEP_MCG_BLND_DENORM SD WHERE  0=0 ");

			if (tdmNonStandDTO.getSubscriberIds() != null
					&& tdmNonStandDTO.getSubscriberIds().size() > 0)
			{
				List<String> subIdList = tdmNonStandDTO.getSubscriberIds();
				for (String subId : subIdList)
				{
					sb.append(TDMConstants.COMMA).append("'").append(subId).append("'");
				}
			}

			if (StringUtils.isNotEmpty(tdmNonStandDTO.getMemCat()) && !isDisregard)
			{
				if (!TDMConstants.ANY.equalsIgnoreCase(tdmNonStandDTO.getMemCat()))
				{
					if (tdmNonStandDTO.getMemCat().equalsIgnoreCase(TDMConstants.BLENDED))
					{
						query.append(" AND SD.BLNDED_TYPE IS NOT NULL");
					}
					else
					{
						if (tdmNonStandDTO.getMemCat().equalsIgnoreCase(TDMConstants.RETAIL))
						{
							if (tdmNonStandDTO.getRetailOnOff().equalsIgnoreCase(
									TDMConstants.ONEXCHANGE)
									|| tdmNonStandDTO.getRetailOnOff().equalsIgnoreCase(
											TDMConstants.OFFEXCHANGE))
							{
								query.append(" AND SD.EXCHANGE_TYPE ='")
										.append(tdmNonStandDTO.getRetailOnOff().toUpperCase())
										.append('\'');
							}
						}
						query.append(" AND SD.MBR_CAT ='")
								.append(tdmNonStandDTO.getMemCat().toUpperCase()).append('\'');
					}
				}
				else
				{
					query.append(" AND SD.MBR_CAT IS NOT NULL");
				}
			}

			if (StringUtils.isNotEmpty(tdmNonStandDTO.getAgeGroup())
					&& (!TDMConstants.ANY.equalsIgnoreCase(tdmNonStandDTO.getAgeGroup()) && !isDisregard))
			{
				String age = tdmNonStandDTO.getAgeGroup();
				if (age.contains("and"))
				{
					query.append(" AND YEAR(current_date- date(SD.DOB))" + age.split("and")[0]);
					query.append(" AND YEAR(current_date- date(SD.DOB))" + age.split("and")[1]);
				}
				else
				{
					query.append(" AND  YEAR(current_date- date(SD.DOB))" + age);
				}
			}
			if (StringUtils.isNotEmpty(tdmNonStandDTO.getOriginalEffDate()) && !isDisregard)
			{

				/*
				 * if
				 * (tdmNonStandDTO.getOnBefAfterOED().equalsIgnoreCase("ON-BEFORE"
				 * )) { query.append(" AND  SD.MBR_ORIG_EFF_DT <='")
				 * .append(tdmNonStandDTO.getOriginalEffDate()).append('\''); }
				 * else { query.append(" AND  SD.MBR_ORIG_EFF_DT >='")
				 * .append(tdmNonStandDTO.getOriginalEffDate()).append('\''); }
				 */

				query.append(" AND  SD.MBR_ORIG_EFF_DT ='")
						.append(tdmNonStandDTO.getOriginalEffDate()).append('\'');
			}

			if (StringUtils.isNotEmpty(tdmNonStandDTO.getSubscRelation()) && !isDisregard)
			{
				String subRel = tdmNonStandDTO.getSubscRelation();
				if (!TDMConstants.ANY.equalsIgnoreCase(subRel))
				{
					if (subRel.equalsIgnoreCase(TDMConstants.SUBSCRIBER_ONLY))
					{
						query.append(" AND SD.SUBS_INLY_IND ='Y'");
					}
					else
					{
						query.append(" AND SD.SUBS_INLY_IND ='N'");

						if (subRel.equalsIgnoreCase(TDMConstants.SUB_STUDENT))
						{
							query.append(" AND SD.RELSHP ='STUDENT'");
						}
						else if (subRel.equalsIgnoreCase(TDMConstants.SUB_SPOUSE_DEP))
						{
							query = new StringBuffer(
									query.toString()
											.replaceAll("WHERE",
													" INNER JOIN SUB_DEP_MCG_BLND_DENORM D2 ON (SD.SUBS_ID=D2.SUBS_ID and D2.RELSHP!='SPOUSE') WHERE "));
							query.append(" AND SD.RELSHP ='SPOUSE'");
						}
						else if (subRel.equalsIgnoreCase(TDMConstants.SUB_DEPS))
						{
							query.append(" AND SD.RELSHP !='SPOUSE'");
						}
						else if (subRel.equalsIgnoreCase(TDMConstants.SUB_SPOUSE))
						{
							query.append(" AND SD.RELSHP ='SPOUSE'");
						}
					}
				}
				else
				{
					query.append(" AND SD.RELSHP IS NOT NULL");
				}
			}
			if (StringUtils.isNotEmpty(tdmNonStandDTO.getProvState()) && !isDisregard)
			{
				String state = tdmNonStandDTO.getProvState();
				if (!TDMConstants.ANY.equalsIgnoreCase(state))
				{
					if (state.replaceAll(" ", "").equalsIgnoreCase(TDMConstants.STATE_IL))
					{
						query.append(" AND  SD.STATE_CD ='IL'");
					}
					else if (state.replaceAll(" ", "").equalsIgnoreCase(TDMConstants.STATE_MT))
					{
						query.append(" AND SD.STATE_CD ='MT'");
					}
					else if (state.replaceAll(" ", "").equalsIgnoreCase(TDMConstants.STATE_NM))
					{
						query.append(" AND  SD.STATE_CD ='NM'");
					}
					else if (state.replaceAll(" ", "").equalsIgnoreCase(TDMConstants.STATE_OK))
					{
						query.append(" AND  SD.STATE_CD ='OK'");
					}
					else if (state.replaceAll(" ", "").equalsIgnoreCase(TDMConstants.STATE_TX))
					{
						query.append(" AND  SD.STATE_CD ='TX'");
					}
				}
				else
				{
					query.append(" AND  SD.STATE_CD IS NOT NULL");
				}
			}
			if ((StringUtils.isNotEmpty(tdmNonStandDTO.getAccountName()) && StringUtils
					.isNotEmpty(tdmNonStandDTO.getAccountName().replaceAll(" ", "")))
					&& StringUtils.isEmpty(tdmNonStandDTO.getSubscId().replaceAll(" ", "")))
			{
				query.append(" AND  SD.ACCOUNT_NAME ='")
						.append(tdmNonStandDTO.getAccountName().trim()).append('\'');
			}
			if ((StringUtils.isNotEmpty(tdmNonStandDTO.getAccountNum()) && StringUtils
					.isNotEmpty(tdmNonStandDTO.getAccountNum().replaceAll(" ", "")))
					&& StringUtils.isEmpty(tdmNonStandDTO.getSubscId().replaceAll(" ", "")))
			{
				query.append(" AND SD.ACCOUNT_NUMBER ='")
						.append(tdmNonStandDTO.getAccountNum().trim()).append('\'');
			}

			if (StringUtils.isNotEmpty(tdmNonStandDTO.getSubscId())
					&& StringUtils.isNotEmpty(tdmNonStandDTO.getSubscId().replaceAll(" ", "")))
			{
				query.append(" AND SD.SUBS_ID ='").append(tdmNonStandDTO.getSubscId().trim())
						.append('\'');
			}

			if (StringUtils.isNotEmpty(tdmNonStandDTO.getPlanType()) && (!isDisregard))
			{
				String prodCD = tdmNonStandDTO.getPlanType();
				if (tdmNonStandDTO.getMemCat().equalsIgnoreCase(TDMConstants.RETAIL)
						&& (!TDMConstants.ANY.equalsIgnoreCase(tdmNonStandDTO.getSubscStatus())))
				{

					if (prodCD.contains(","))
					{
						prodCD = prodCD.replaceAll(",", "' OR SD.PROD_TYPE='");
						query.append(" AND ( SD.PROD_TYPE='").append(prodCD)
								.append("' AND SD.PROD_TYPE IS NOT NULL)");
					}
					else
					{
						if (!TDMConstants.ANY.equalsIgnoreCase(tdmNonStandDTO.getPlanType()))
						{

							query.append(" AND ( SD.PROD_TYPE='").append(prodCD)
									.append("' AND SD.PROD_TYPE IS NOT NULL)");
						}
						else
						{
							query.append(" AND SD.PROD_TYPE IS NOT NULL ");
						}
					}
				}
				else
				{
					if (prodCD.contains(","))
					{
						prodCD = prodCD.replaceAll(",", "','");
					}
					if (!TDMConstants.ANY.equalsIgnoreCase(tdmNonStandDTO.getPlanType()))
					{
						query.append(" AND  SD.PROD_TYPE IN ('").append(prodCD).append("')");
					}
					else
					{
						query.append(" AND SD.PROD_TYPE IS NOT NULL ");
					}
				}
			}

			if (StringUtils.isNotEmpty(tdmNonStandDTO.getCoverageCode()) && !isDisregard)
			{
				String coverage = tdmNonStandDTO.getCoverageCode();
				if (coverage.equalsIgnoreCase(TDMConstants.ALL))
				{
					query.append(" AND SD.MEDICAL_COVG_IND ='MEDICAL'");
					query.append(" AND SD.DENTAL_COVG_IND ='DENTAL'");
					query.append(" AND SD.VISION_COVG_IND ='VISION'");
					query.append(" AND SD.PRESC_COVG_IND ='DRUG'");
				}
				else if (coverage.equalsIgnoreCase(TDMConstants.ANY))
				{
					/*
					 * query.append(" AND (SD.MEDICAL_COVG_IND IS NOT NULL");
					 * query.append(" OR SD.DENTAL_COVG_IND IS NOT NULL");
					 * query.append(" OR SD.VISION_COVG_IND IS NOT NULL");
					 * query.append(" OR SD.PRESC_COVG_IND IS NOT NULL) ");
					 */
				}
				else if (coverage.contains(TDMConstants.COMMA))
				{
					String[] covers = coverage.split(",");
					for (String cov : covers)
					{
						if (cov.equalsIgnoreCase(TDMConstants.MEDICAL))
						{
							query.append(" AND SD.MEDICAL_COVG_IND ='MEDICAL'");
						}
						if (cov.equalsIgnoreCase(TDMConstants.DENTAL))
						{
							query.append(" AND SD.DENTAL_COVG_IND ='DENTAL'");
						}
						if (cov.equalsIgnoreCase(TDMConstants.VISION))
						{
							query.append(" AND SD.VISION_COVG_IND ='VISION'");
						}
						if (cov.replaceAll(" ", "").equalsIgnoreCase(TDMConstants.PRESCRIPTION_RX)
								|| cov.equalsIgnoreCase(TDMConstants.RX))
						{
							query.append(" AND SD.PRESC_COVG_IND ='DRUG'");
						}
					}
				}
				else
				{
					if (coverage.equalsIgnoreCase(TDMConstants.MEDICAL))
					{
						query.append(" AND SD.PRIMERY_COVG_IND like '%MEDICAL%'");
					}
					else if (coverage.equalsIgnoreCase(TDMConstants.MEDICAL_ONLY))
					{
						query.append(" AND SD.MEDICAL_COVG_IND ='MEDICAL'");
						query.append(" AND SD.COVG_ONLY_IND ='M' ");
					}
					else if (coverage.equalsIgnoreCase(TDMConstants.DENTAL))
					{
						query.append(" AND SD.PRIMERY_COVG_IND like '%DENTAL%'");
					}
					else if (coverage.equalsIgnoreCase(TDMConstants.DENTAL_ONLY))
					{
						query.append(" AND SD.DENTAL_COVG_IND ='DENTAL'");
						query.append(" AND SD.COVG_ONLY_IND ='D' ");
					}
					else if (coverage.equalsIgnoreCase(TDMConstants.VISION))
					{
						query.append(" AND SD.PRIMERY_COVG_IND like '%VISION%'");
					}
					else if (coverage.equalsIgnoreCase(TDMConstants.VISION_ONLY))
					{
						query.append(" AND SD.VISION_COVG_IND ='VISION'");
						query.append(" AND SD.COVG_ONLY_IND ='V' ");
					}
					else if (coverage.replaceAll(" ", "").equalsIgnoreCase(
							TDMConstants.PRESCRIPTION_RX)
							|| coverage.equalsIgnoreCase(TDMConstants.RX))
					{
						query.append(" AND SD.PRIMERY_COVG_IND like '%DRUG%'");
					}
					else if (coverage.equalsIgnoreCase(TDMConstants.PRESCRIPTION_RX_ONLY))
					{
						query.append(" AND SD.PRESC_COVG_IND ='DRUG'");
						query.append(" AND SD.COVG_ONLY_IND ='P' ");
					}
				}
			}

			if (StringUtils.isNotEmpty(tdmNonStandDTO.getSubscStatus()) && !isDisregard)
			{
				String statSub = tdmNonStandDTO.getSubscStatus().trim();
				if (!TDMConstants.ANY.equalsIgnoreCase(statSub))
				{
					if (statSub.equalsIgnoreCase(TDMConstants.SUB_ACTIVE))
					{
						query.append(" AND SD.SUBS_STATUS ='ACTIVE SUBSCRIBER'");
					}
					else if (statSub.equalsIgnoreCase(TDMConstants.SUB_CANCELED))
					{
						query.append(" AND SD.SUBS_STATUS ='CANCELLED SUBSCRIBER'");
					}
					else if (statSub.equalsIgnoreCase(TDMConstants.SUB_ACTIV_ACTV_DPEN))
					{
						query.append(" AND SD.DEPT_STATUS ='ACTIVE DEPENDENT'");
						query.append(" AND SD.SUBS_STATUS ='ACTIVE SUBSCRIBER'");
					}
					else if (statSub.equalsIgnoreCase(TDMConstants.SUB_ACTIV_CANCEL_DEPN))
					{
						query.append(" AND SD.SUBS_STATUS ='ACTIVE SUBSCRIBER'");
						query.append(" AND SD.DEPT_STATUS ='CANCELLED DEPENDENT'");
					}
					else if (statSub.equalsIgnoreCase(TDMConstants.SUB_CANCEL_CANCEL_SPOUSE))
					{
						query.append(" AND SD.SUBS_STATUS ='CANCELLED SUBSCRIBER'");
						query.append(" AND SD.DEPT_STATUS ='CANCELLED DEPENDENT'");
						query.append(" AND SD.RELSHP ='SPOUSE'");
					}
					else if (statSub.equalsIgnoreCase(TDMConstants.SUB_CANCEL_CANCEL_DEPN))
					{
						query.append(" AND SD.SUBS_STATUS ='CANCELLED SUBSCRIBER'");
						query.append(" AND SD.DEPT_STATUS ='CANCELLED DEPENDENT'");
						query.append(" AND SD.RELSHP  !='SPOUSE'");
						query.append(" AND SD.RELSHP IS NOT NULL");
					}
				}
				else
				{
					query.append(" AND SD.SUBS_STATUS !='0'");
				}

				/*
				 * if (statSub.contains(TDMConstants.SUB_ACTIVE)) {
				 * query.append(
				 * " AND SD.MBRSHP_COVRG_GRP_SECT_END_DT > CURRENT_DATE "); } CR
				 * 06 fix revert back
				 */
			}

			if ((StringUtils.isNotEmpty(tdmNonStandDTO.getFundingInd()) && !TDMConstants.ANY
					.equalsIgnoreCase(tdmNonStandDTO.getFundingInd())) && !isDisregard)
			{
				String fundInd = tdmNonStandDTO.getFundingInd();
				if (fundInd.contains(TDMConstants.COMMA))
				{
					String[] fundsInds = fundInd.split(",");
					StringBuffer fundBuffer = new StringBuffer();
					for (String fundIn : fundsInds)
					{
						String fund = fundIn.split("-")[0];
						fundBuffer.append(fund + ",");
					}
					String fund = fundBuffer.toString();
					fund = fund.substring(0, fund.length() - 1);
					fund = fund.replaceAll(",", "','");
					query.append(" AND SD.FUNDING_IND IN (" + fund + ") ");
				}
				else
				{
					String fund = fundInd.split("-")[0];
					query.append(" AND SD.FUNDING_IND ='" + fund + "'");
				}
			}
			if (StringUtils.isNotEmpty(tdmNonStandDTO.getExtClaim()) && (!isDisregard))
			{

				String claim = tdmNonStandDTO.getExtClaim();
				if (claim.contains(TDMConstants.ALL))
				{
					query.append(" AND SD.MED_CLAIM_IND = 'MEDICAL'");
					query.append(" AND SD.DENTAL_CLAIM_IND ='DENTAL'");
					query.append(" AND SD.PRESCRIPTION_CLAIM_IND ='PRESCRIPTION'");
				}
				else if (claim.contains(TDMConstants.ANY))
				{
					query.append(" AND (SD.MED_CLAIM_IND IS NOT NULL");
					query.append(" OR SD.DENTAL_CLAIM_IND  IS NOT NULL ");
					query.append(" OR SD.PRESCRIPTION_CLAIM_IND  IS NOT NULL) ");
				}
				else if (claim.contains(TDMConstants.COMMA))
				{
					String[] claims = claim.split(",");

					if (!claim.contains(TDMConstants.MEDICAL))
					{
						query.append(" AND SD.MED_CLAIM_IND IS  NULL");
					}
					if (!claim.contains(TDMConstants.DENTAL))
					{
						query.append(" AND SD.DENTAL_CLAIM_IND  IS  NULL ");
					}
					if (!claim.contains(TDMConstants.PRESCRIPTION_RX))
					{
						query.append(" AND SD.PRESCRIPTION_CLAIM_IND  IS  NULL ");
					}

					for (String clm : claims)
					{
						if (clm.equalsIgnoreCase(TDMConstants.MEDICAL))
						{
							query.append(" AND SD.MED_CLAIM_IND = 'MEDICAL'");
						}
						else if (clm.equalsIgnoreCase(TDMConstants.DENTAL))
						{
							query.append(" AND SD.DENTAL_CLAIM_IND ='DENTAL'");
						}
						else if (clm.replaceAll(" ", "").equalsIgnoreCase(
								TDMConstants.PRESCRIPTION_RX))
						{
							query.append(" AND SD.PRESCRIPTION_CLAIM_IND ='PRESCRIPTION'");
						}
					}
				}
				else if (TDMConstants.NONE.equalsIgnoreCase(claim))
				{
					query.append(" AND (SD.MED_CLAIM_IND IS  NULL");
					query.append(" AND SD.DENTAL_CLAIM_IND  IS  NULL ");
					query.append(" AND SD.PRESCRIPTION_CLAIM_IND  IS  NULL) ");
				}
				else
				{
					if (claim.equalsIgnoreCase(TDMConstants.MEDICAL))
					{
						query.append(" AND SD.MED_CLAIM_IND = 'MEDICAL'");
						query.append(" AND SD.DENTAL_CLAIM_IND IS NULL");
						query.append(" AND SD.PRESCRIPTION_CLAIM_IND IS NULL");
					}
					else if (claim.equalsIgnoreCase(TDMConstants.DENTAL))
					{
						query.append(" AND SD.DENTAL_CLAIM_IND ='DENTAL'");
						query.append(" AND SD.MED_CLAIM_IND IS NULL");
						query.append(" AND SD.PRESCRIPTION_CLAIM_IND IS NULL");
					}
					else if (claim.replaceAll(" ", "").equalsIgnoreCase(
							TDMConstants.PRESCRIPTION_RX))
					{
						query.append(" AND SD.PRESCRIPTION_CLAIM_IND ='PRESCRIPTION'");
						query.append(" AND SD.DENTAL_CLAIM_IND IS NULL");
						query.append(" AND SD.MED_CLAIM_IND IS NULL");
					}
				}
			}

			if (tdmNonStandDTO.getSubscriberIds() != null
					&& tdmNonStandDTO.getSubscriberIds().size() > 0)
			{
				query.append(" AND SD.SUBS_ID NOT IN ( '0'");
				query.append(sb.toString());
				query.append(")");
			}
			query.append(" FETCH FIRST 5000 rows only) REPLACE2 A ORDER BY SUBS_ID, MBR_END_DT,MBRSHP_COVRG_GRP_SECT_END_DT DESC  ");

			if ((tdmNonStandDTO.getCoverageCode().contains(TDMConstants.COMMA) || TDMConstants.ANY
					.equalsIgnoreCase(tdmNonStandDTO.getCoverageCode())) && !isDisregard)
			{

				String updatedQuery = query.toString().replace("REPLACE1",
						"  from SUB_DEP_MCG_BLND_DENORM SD1 WHERE SUBS_ID IN( SELECT SD.SUBS_ID  ");

				updatedQuery = updatedQuery.replace("REPLACE2", " ) ");

				query = new StringBuffer(updatedQuery);
			}
			else
			{
				String updatedQuery = query.toString().replaceAll("SD1", "SD");
				updatedQuery = updatedQuery.replace("REPLACE1", "  ");
				updatedQuery = updatedQuery.replace("REPLACE2", "  ");
				query = new StringBuffer(updatedQuery);
			}
			List<Object[]> list = subscrManager.createNativeQuery(query.toString())
					.setFirstResult(offSet).setMaxResults(recordsperpage).getResultList();
			logger.info(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.GET_NS_RECORDS
					+ MessageConstants.LOG_INFO_RETURN);
			return list;
		}
		catch (NoResultException ex)
		{
			logger.error(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.GET_NS_RECORDS,
					ex.getMessage());
			throw new DAOException(TDMExceptionCode.NORESULT, ex);
		}
		catch (IllegalStateException ex)
		{
			logger.error(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.GET_NS_RECORDS,
					ex.getMessage());
			throw new DAOException(TDMExceptionCode.ILLEGAL_STATE, ex);
		}
		catch (IllegalArgumentException ex)
		{
			logger.error(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.GET_NS_RECORDS,
					ex.getMessage());
			throw new DAOException(TDMExceptionCode.ILLEGAL_ARGUMENT, ex);
		}
		catch (Exception ex)
		{
			logger.error(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.GET_NS_RECORDS,
					ex.getMessage());
			throw new DAOException(TDMExceptionCode.EXCEPTION, ex);
		}
	}

	@SuppressWarnings(TDMConstants.UNCHECKED)
	@Override
	public List<TdmSubscriberDetailsDO> getTDMNonStandSearchRecords(
			TDMNonStandardSearchDTO tdmNonStandDTO, int offSet, int recordsperpage,
			EntityManager subscrManager) throws DAOException
	{
		try
		{
			logger.info(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.GET_NS_RECORDS);
			StringBuffer sb = new StringBuffer();
			StringBuffer query = new StringBuffer(
					"SELECT s FROM TdmSubscriberDetailsDO s INNER JOIN s.mcgDetails smcg where 0=0");

			if (tdmNonStandDTO.getSubscriberIds() != null
					&& tdmNonStandDTO.getSubscriberIds().size() > 0)
			{
				List<String> subIdList = tdmNonStandDTO.getSubscriberIds();
				for (String subId : subIdList)
				{
					sb.append(TDMConstants.COMMA).append("'").append(subId).append("'");
				}
			}

			if (StringUtils.isNotEmpty(tdmNonStandDTO.getMemCat())
					&& !TDMConstants.ANY.equalsIgnoreCase(tdmNonStandDTO.getMemCat()))
			{
				if (tdmNonStandDTO.getMemCat().equalsIgnoreCase("BLENDED"))
				{
					query = new StringBuffer(query.toString().replaceAll("where 0=0",
							" INNER JOIN s.blendDo where 0=0"));
					List<Integer> blenPtyIds = tdmNonStandDTO.getBlendedPtyIDs();
					if (blenPtyIds != null && blenPtyIds.size() > 0)
					{
						query.append(" AND s.subscrPtyId IN(0");
						for (int ptyId : blenPtyIds)
						{
							query.append(TDMConstants.COMMA + ptyId);
						}
						query.append(")");
					}
				}
				else
				{
					query.append(" AND s.membrCat ='")
							.append(tdmNonStandDTO.getMemCat().toUpperCase()).append('\'');

					if (tdmNonStandDTO.getMemCat().equalsIgnoreCase(TDMConstants.RETAIL))
					{
						if (tdmNonStandDTO.getRetailOnOff().equalsIgnoreCase(
								TDMConstants.ONEXCHANGE)
								|| tdmNonStandDTO.getRetailOnOff().equalsIgnoreCase(
										TDMConstants.OFFEXCHANGE))
						{
							query.append(" AND smcg.exchangeType ='")
									.append(tdmNonStandDTO.getRetailOnOff().toUpperCase())
									.append('\'');
						}
					}
				}
			}
			if (StringUtils.isNotEmpty(tdmNonStandDTO.getAgeGroup())
					&& !TDMConstants.ANY.equalsIgnoreCase(tdmNonStandDTO.getAgeGroup()))
			{
				String age = tdmNonStandDTO.getAgeGroup();
				if (age.contains("and"))
				{
					query.append(" AND  ((days(current_date) - days(date(s.dob))) / 365) "
							+ age.split("and")[0] + "");
					query.append(" AND ((days(current_date) - days(date(s.dob))) / 365)  "
							+ age.split("and")[1] + "");
				}
				else
				{
					query.append(" AND  ((days(current_date) - days(date(s.dob))) / 365) " + age
							+ " ");
				}
			}
			if (StringUtils.isNotEmpty(tdmNonStandDTO.getOriginalEffDate()))
			{
				query.append(" AND  s.membrOrigEffDate ='")
						.append(tdmNonStandDTO.getOriginalEffDate()).append('\'');
			}

			if (StringUtils.isNotEmpty(tdmNonStandDTO.getSubscRelation())
					&& !TDMConstants.ANY.equalsIgnoreCase(tdmNonStandDTO.getSubscRelation()))
			{
				String subRel = tdmNonStandDTO.getSubscRelation();
				if (subRel.equalsIgnoreCase(TDMConstants.SUBSCRIBER_ONLY))
				{
					query.append(" AND  s.subsInlyInd ='Y'");
				}
				else
				{
					query.append(" AND  s.subsInlyInd ='N'");
					query.append(" AND s.subscriberId IN ( SELECT distinct sdep.subscriberId FROM  TdmDependentDetailsDO sdep where 0=0 ");

					if (tdmNonStandDTO.getSubscriberIds() != null
							&& tdmNonStandDTO.getSubscriberIds().size() > 0)
					{
						query.append(" AND sdep.subscriberId NOT IN ( '0'");
						query.append(sb.toString());
						query.append(")");
					}
					if (subRel.equalsIgnoreCase(TDMConstants.SUB_STUDENT))
					{
						query.append(" AND sdep.relshp ='STUDENT'");
					}
					else if (subRel.equalsIgnoreCase(TDMConstants.SUB_SPOUSE_DEP))
					{
						query.append(" AND sdep.relshp ='SPOUSE'");
						query.append(" AND sdep.relshp IN('STUDENT', 'CHILD', 'OTHERS')");
					}
					else if (subRel.equalsIgnoreCase(TDMConstants.SUB_DEPS))
					{
						query.append(" AND sdep.relshp !='SPOUSE'");
					}
					else if (subRel.equalsIgnoreCase(TDMConstants.SUB_SPOUSE))
					{
						query.append(" AND sdep.relshp ='SPOUSE'");
					}

					String statSub = tdmNonStandDTO.getSubscStatus().trim();
					if (statSub.equalsIgnoreCase(TDMConstants.SUB_ACTIV_ACTV_DPEN))
					{
						query.append(" AND sdep.deptStatus ='ACTIVE DEPENDENT'");
					}
					else if (statSub.equalsIgnoreCase(TDMConstants.SUB_ACTIV_CANCEL_DEPN))
					{
						query.append(" AND sdep.deptStatus ='CANCELLED DEPENDENT'");
					}
					query.append(" )");
				}
			}
			if (StringUtils.isNotEmpty(tdmNonStandDTO.getProvState())
					&& !TDMConstants.ANY.equalsIgnoreCase(tdmNonStandDTO.getProvState()))
			{
				String state = tdmNonStandDTO.getProvState();
				if (state.equalsIgnoreCase(TDMConstants.STATE_IL))
				{
					query.append(" AND  s.state ='IL'");
				}
				else if (state.equalsIgnoreCase(TDMConstants.STATE_MT))
				{
					query.append(" AND  s.state ='MT'");
				}
				else if (state.equalsIgnoreCase(TDMConstants.STATE_NM))
				{
					query.append(" AND  s.state ='NM'");
				}
				else if (state.equalsIgnoreCase(TDMConstants.STATE_OK))
				{
					query.append(" AND  s.state ='OK'");
				}
				else if (state.equalsIgnoreCase(TDMConstants.STATE_TX))
				{
					query.append(" AND  s.state ='TX'");
				}
			}
			if (StringUtils.isNotEmpty(tdmNonStandDTO.getAccountName()))
			{
				query.append(" AND  s.accountName ='")
						.append(tdmNonStandDTO.getAccountName().trim()).append('\'');
			}
			if (StringUtils.isNotEmpty(tdmNonStandDTO.getAccountNum()))
			{
				query.append(" AND  s.accountNum ='").append(tdmNonStandDTO.getAccountNum().trim())
						.append('\'');
			}

			if (StringUtils.isNotEmpty(tdmNonStandDTO.getSubscId())
					&& !TDMConstants.ANY.equalsIgnoreCase(tdmNonStandDTO.getSubscId()))
			{
				query.append(" AND s.subscriberId ='").append(tdmNonStandDTO.getSubscId().trim())
						.append('\'');
			}

			if (StringUtils.isNotEmpty(tdmNonStandDTO.getPlanType())
					&& !TDMConstants.ANY.equalsIgnoreCase(tdmNonStandDTO.getPlanType()))
			{

				String prodCD = tdmNonStandDTO.getPlanType();
				if (prodCD.contains(","))
				{
					prodCD = prodCD.replaceAll(",", "','");
				}
				query.append(" AND  smcg.prodType  IN ('").append(prodCD).append("')");
			}

			if (StringUtils.isNotEmpty(tdmNonStandDTO.getCoverageCode())
					&& !TDMConstants.ANY.equalsIgnoreCase(tdmNonStandDTO.getCoverageCode()))
			{
				String coverage = tdmNonStandDTO.getCoverageCode();
				if (coverage.equalsIgnoreCase(TDMConstants.ALL))
				{
					query.append(" AND smcg.medicalCovgInd ='MEDICAL'");
					query.append(" AND smcg.dentalCovgInd ='DENTAL'");
					query.append(" AND smcg.visionCoverId ='VISION'");
					query.append(" AND smcg.prescCovgInd ='DRUG'");
				}
				else if (coverage.contains(TDMConstants.COMMA))
				{
					String[] covers = coverage.split(",");
					for (String cov : covers)
					{
						if (cov.equalsIgnoreCase(TDMConstants.MEDICAL))
						{
							query.append(" AND smcg.medicalCovgInd ='MEDICAL'");
						}
						if (cov.equalsIgnoreCase(TDMConstants.DENTAL))
						{
							query.append(" AND  smcg.dentalCovgInd ='DENTAL'");
						}
						if (cov.equalsIgnoreCase(TDMConstants.VISION))
						{
							query.append(" AND smcg.visionCoverId ='VISION'");
						}
						if (cov.equalsIgnoreCase(TDMConstants.PRESCRIPTION_RX))
						{
							query.append(" AND smcg.prescCovgInd ='DRUG'");
						}
					}
				}
				else
				{
					if (coverage.equalsIgnoreCase(TDMConstants.MEDICAL))
					{
						query.append(" AND smcg.medicalCovgInd ='MEDICAL'");
					}
					else if (coverage.equalsIgnoreCase(TDMConstants.DENTAL))
					{
						query.append(" AND  smcg.dentalCovgInd ='DENTAL'");
					}
					else if (coverage.equalsIgnoreCase(TDMConstants.VISION))
					{
						query.append(" AND smcg.visionCoverId ='VISION'");
					}
					else if (coverage.equalsIgnoreCase(TDMConstants.PRESCRIPTION_RX))
					{
						query.append(" AND smcg.prescCovgInd ='DRUG'");
					}
				}
			}

			if (StringUtils.isNotEmpty(tdmNonStandDTO.getSubscStatus())
					&& !TDMConstants.ANY.equalsIgnoreCase(tdmNonStandDTO.getSubscStatus()))
			{
				String statSub = tdmNonStandDTO.getSubscStatus().trim();
				if (statSub.equalsIgnoreCase(TDMConstants.SUB_ACTIVE)
						|| statSub.equalsIgnoreCase(TDMConstants.SUB_ACTIV_CANCEL_DEPN)
						|| statSub.equalsIgnoreCase(TDMConstants.SUB_ACTIV_CANCEL_DEPN))
				{
					query.append(" AND s.subscrStatus ='ACTIVE SUBSCRIBER'");
				}
				else if (statSub.equalsIgnoreCase(TDMConstants.SUB_CANCELED))
				{
					query.append(" AND s.subscrStatus ='CANCELLED SUBSCRIBER'");
				}
			}
			if (StringUtils.isNotEmpty(tdmNonStandDTO.getExtClaim())
					&& !TDMConstants.ANY.equalsIgnoreCase(tdmNonStandDTO.getExtClaim()))
			{

				String claim = tdmNonStandDTO.getExtClaim();
				if (claim.contains(TDMConstants.ALL))
				{
					query.append(" AND s.medClaimInd = 'MEDICAL'");
					query.append(" AND s.dentalClaimInd ='DENTAL'");
					query.append(" AND s.prescClaimInd ='PRESCRIPTION'");
				}
				else if (claim.contains(TDMConstants.COMMA))
				{
					String[] claims = claim.split(",");
					for (String clm : claims)
					{
						if (clm.equalsIgnoreCase(TDMConstants.MEDICAL))
						{
							query.append(" AND s.medClaimInd = 'MEDICAL'");
						}
						else if (clm.equalsIgnoreCase(TDMConstants.DENTAL))
						{
							query.append(" AND s.dentalClaimInd ='DENTAL'");
						}
						else if (clm.equalsIgnoreCase(TDMConstants.PRESCRIPTION))
						{
							query.append(" AND s.prescClaimInd ='PRESCRIPTION'");
						}
					}

				}
				else
				{
					if (claim.equalsIgnoreCase(TDMConstants.MEDICAL))
					{
						query.append(" AND s.medClaimInd = 'MEDICAL'");
					}
					else if (claim.equalsIgnoreCase(TDMConstants.DENTAL))
					{
						query.append(" AND s.dentalClaimInd ='DENTAL'");
					}
					else if (claim.equalsIgnoreCase(TDMConstants.PRESCRIPTION))
					{
						query.append(" AND s.prescClaimInd ='PRESCRIPTION'");
					}
				}

			}

			if (tdmNonStandDTO.getSubscriberIds() != null
					&& tdmNonStandDTO.getSubscriberIds().size() > 0)
			{
				query.append(" AND s.subscriberId NOT IN ( '0'");
				query.append(sb.toString());
				query.append(")");
			}
			List<TdmSubscriberDetailsDO> list = subscrManager.createQuery(query.toString())
					.setFirstResult(offSet).setMaxResults(recordsperpage).getResultList();
			logger.info(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.GET_NS_RECORDS
					+ MessageConstants.LOG_INFO_RETURN);
			System.out.println("------- Query ------" + query);
			return list;
		}
		catch (NoResultException ex)
		{
			logger.error(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.GET_NS_RECORDS,
					ex.getMessage());
			throw new DAOException(TDMExceptionCode.NORESULT, ex);
		}
		catch (IllegalStateException ex)
		{
			logger.error(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.GET_NS_RECORDS,
					ex.getMessage());
			throw new DAOException(TDMExceptionCode.ILLEGAL_STATE, ex);
		}
		catch (IllegalArgumentException ex)
		{
			logger.error(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.GET_NS_RECORDS,
					ex.getMessage());
			throw new DAOException(TDMExceptionCode.ILLEGAL_ARGUMENT, ex);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			logger.error(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.GET_NS_RECORDS,
					ex.getMessage());
			throw new DAOException(TDMExceptionCode.EXCEPTION, ex);
		}
	}

	@SuppressWarnings(TDMConstants.UNCHECKED)
	@Override
	public List<TdmReservationDO> getReservedRecords(String role, String userId, int offset,
			int recPerPage, EntityManager managerUsr) throws DAOException
	{
		logger.info(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.GET_RSERVE_RECORDS);
		try
		{
			List<TdmReservationDO> tdmReservationDO = null;
			if (TDMConstants.ROLE_ADMIN.equalsIgnoreCase(role))
			{
				tdmReservationDO = managerUsr
						.createQuery("SELECT t FROM TdmReservationDO t where t.locked='Y'")
						.setFirstResult(offset).setMaxResults(recPerPage).getResultList();
			}
			else
			{
				tdmReservationDO = managerUsr
						.createQuery(
								"SELECT t FROM TdmReservationDO t where t.userId='" + userId
										+ "' AND t.locked='Y'").setFirstResult(offset)
						.setMaxResults(recPerPage).getResultList();
			}

			logger.info(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.GET_RSERVE_RECORDS
					+ MessageConstants.LOG_INFO_RETURN);
			return tdmReservationDO;
		}
		catch (NoResultException ex)
		{
			logger.error(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.GET_RSERVE_RECORDS,
					ex.getMessage());
			throw new DAOException(TDMExceptionCode.NORESULT, ex);
		}
		catch (IllegalStateException ex)
		{
			logger.error(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.GET_RSERVE_RECORDS,
					ex.getMessage());
			throw new DAOException(TDMExceptionCode.ILLEGAL_STATE, ex);
		}
		catch (IllegalArgumentException ex)
		{
			logger.error(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.GET_RSERVE_RECORDS,
					ex.getMessage());
			throw new DAOException(TDMExceptionCode.ILLEGAL_ARGUMENT, ex);
		}
		catch (Exception ex)
		{
			logger.error(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.GET_RSERVE_RECORDS,
					ex.getMessage());
			throw new DAOException(TDMExceptionCode.EXCEPTION, ex);
		}
	}

	@Override
	public long getTDMNonStandardSearchRecordCount(TDMNonStandardSearchDTO tdmNonStandDTO,
			EntityManager managerSubscr) throws DAOException
	{
		logger.info(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.GET_NS_REC_COUNT);
		try
		{
			boolean isDisregard = false;
			String acName = tdmNonStandDTO.getAccountName();
			String acNum = tdmNonStandDTO.getAccountNum();
			String subscrbrId = tdmNonStandDTO.getSubscId();
			if (false
					|| (StringUtils.isNotEmpty(acName) && StringUtils.isNotEmpty(acName.trim()))
					|| (StringUtils.isNotEmpty(acNum) && StringUtils.isNotEmpty(acNum.trim()))
					|| (StringUtils.isNotEmpty(subscrbrId) && StringUtils.isNotEmpty(subscrbrId
							.trim())))
			{
				/*
				 * In case user provides AccountName or AccountNumber or
				 * Subscriber ID or combination of these three in the search
				 * criteria, do not include the rest of fields in search
				 * criteria. Search will happen only on these three fields.
				 */
				isDisregard = true;
			}
			StringBuffer sb = new StringBuffer();
			StringBuffer query = new StringBuffer(
					"SELECT  count(*) from (SELECT distinct A.* from (SELECT "
							+ " SD1.SUBS_ID, SD1.MBR_CAT,SD1.FIRST_NAME, SD1.LAST_NAME,SD1.GNDR_CD,SD1.DOB,SD1.ZIPCODE,"
							+ " SD1.GRP_NBR,SD1.ACCOUNT_NAME, SD1.MBR_EFF_DT, "
							+ " SD1.MBR_END_DT,SD1.MBRSHP_COVRG_GRP_SECT_EFF_DT,SD1.MBRSHP_COVRG_GRP_SECT_END_DT,"
							+ " SD1.MBR_ORIG_EFF_DT,"
							+ " LTRIM(COALESCE(SD1.MED_CLAIM_IND,' ')|| ' '||COALESCE(SD1.PRESCRIPTION_CLAIM_IND,' ' )|| ' '||COALESCE(SD1.DENTAL_CLAIM_IND,' ')) AS EXISTING_CLAIMS,"
							+ " SD1.PRIMERY_COVG_IND , "
							+ " SD1.STATE_CD, SD1.EXCHANGE_TYPE, SD1.BLNDED_TYPE, SD1.PROD_TYPE, SD1.MBR_ID, SD1.FUNDING_IND, SD1.PCP_PCPMG"
							+ " REPLACE1 from  SUB_DEP_MCG_BLND_DENORM SD WHERE  0=0 ");

			if (tdmNonStandDTO.getSubscriberIds() != null
					&& tdmNonStandDTO.getSubscriberIds().size() > 0)
			{
				List<String> subIdList = tdmNonStandDTO.getSubscriberIds();
				for (String subId : subIdList)
				{
					sb.append(TDMConstants.COMMA).append("'").append(subId).append("'");
				}
			}

			if (StringUtils.isNotEmpty(tdmNonStandDTO.getMemCat()) && !isDisregard)
			{
				if (!TDMConstants.ANY.equalsIgnoreCase(tdmNonStandDTO.getMemCat()))
				{
					if (tdmNonStandDTO.getMemCat().equalsIgnoreCase(TDMConstants.BLENDED))
					{
						query.append(" AND SD.BLNDED_TYPE IS NOT NULL");
					}
					else
					{
						if (tdmNonStandDTO.getMemCat().equalsIgnoreCase(TDMConstants.RETAIL))
						{
							if (tdmNonStandDTO.getRetailOnOff().equalsIgnoreCase(
									TDMConstants.ONEXCHANGE)
									|| tdmNonStandDTO.getRetailOnOff().equalsIgnoreCase(
											TDMConstants.OFFEXCHANGE))
							{
								query.append(" AND SD.EXCHANGE_TYPE ='")
										.append(tdmNonStandDTO.getRetailOnOff().toUpperCase())
										.append('\'');
							}
						}
						query.append(" AND SD.MBR_CAT ='")
								.append(tdmNonStandDTO.getMemCat().toUpperCase()).append('\'');
					}
				}
				else
				{
					query.append(" AND SD.MBR_CAT IS NOT NULL ");
				}
			}

			if (StringUtils.isNotEmpty(tdmNonStandDTO.getAgeGroup())
					&& (!TDMConstants.ANY.equalsIgnoreCase(tdmNonStandDTO.getAgeGroup()) && !isDisregard))
			{
				String age = tdmNonStandDTO.getAgeGroup();
				if (age.contains("and"))
				{
					query.append(" AND YEAR(current_date- date(SD.DOB))" + age.split("and")[0]);
					query.append(" AND YEAR(current_date- date(SD.DOB))" + age.split("and")[1]);
				}
				else
				{
					query.append(" AND  YEAR(current_date- date(SD.DOB))" + age);
				}
			}
			if (StringUtils.isNotEmpty(tdmNonStandDTO.getOriginalEffDate()) && !isDisregard)
			{

				/*
				 * if
				 * (tdmNonStandDTO.getOnBefAfterOED().equalsIgnoreCase("ON-BEFORE"
				 * )) { query.append(" AND  SD.MBR_ORIG_EFF_DT <='")
				 * .append(tdmNonStandDTO.getOriginalEffDate()).append('\''); }
				 * else { query.append(" AND  SD.MBR_ORIG_EFF_DT >='")
				 * .append(tdmNonStandDTO.getOriginalEffDate()).append('\''); }
				 */

				query.append(" AND  SD.MBR_ORIG_EFF_DT ='")
						.append(tdmNonStandDTO.getOriginalEffDate()).append('\'');
			}

			if (StringUtils.isNotEmpty(tdmNonStandDTO.getSubscRelation()) && !isDisregard)
			{
				String subRel = tdmNonStandDTO.getSubscRelation();
				if (!TDMConstants.ANY.equalsIgnoreCase(subRel))
				{
					if (subRel.equalsIgnoreCase(TDMConstants.SUBSCRIBER_ONLY))
					{
						query.append(" AND SD.SUBS_INLY_IND ='Y'");
					}
					else
					{
						query.append(" AND SD.SUBS_INLY_IND ='N'");

						if (subRel.equalsIgnoreCase(TDMConstants.SUB_STUDENT))
						{
							query.append(" AND SD.RELSHP ='STUDENT'");
						}
						else if (subRel.equalsIgnoreCase(TDMConstants.SUB_SPOUSE_DEP))
						{
							query = new StringBuffer(
									query.toString()
											.replaceAll("WHERE",
													" INNER JOIN SUB_DEP_MCG_BLND_DENORM D2 ON (SD.SUBS_ID=D2.SUBS_ID and D2.RELSHP!='SPOUSE') WHERE "));
							query.append(" AND SD.RELSHP ='SPOUSE'");
						}
						else if (subRel.equalsIgnoreCase(TDMConstants.SUB_DEPS))
						{
							query.append(" AND SD.RELSHP !='SPOUSE'");
						}
						else if (subRel.equalsIgnoreCase(TDMConstants.SUB_SPOUSE))
						{
							query.append(" AND SD.RELSHP ='SPOUSE'");
						}
					}
				}
				else
				{
					query.append(" AND SD.RELSHP IS NOT NULL");
				}
			}
			if (StringUtils.isNotEmpty(tdmNonStandDTO.getProvState()) && !isDisregard)
			{
				String state = tdmNonStandDTO.getProvState();
				if (!TDMConstants.ANY.equalsIgnoreCase(state))
				{
					if (state.replaceAll(" ", "").equalsIgnoreCase(TDMConstants.STATE_IL))
					{
						query.append(" AND  SD.STATE_CD ='IL'");
					}
					else if (state.replaceAll(" ", "").equalsIgnoreCase(TDMConstants.STATE_MT))
					{
						query.append(" AND SD.STATE_CD ='MT'");
					}
					else if (state.replaceAll(" ", "").equalsIgnoreCase(TDMConstants.STATE_NM))
					{
						query.append(" AND  SD.STATE_CD ='NM'");
					}
					else if (state.replaceAll(" ", "").equalsIgnoreCase(TDMConstants.STATE_OK))
					{
						query.append(" AND  SD.STATE_CD ='OK'");
					}
					else if (state.replaceAll(" ", "").equalsIgnoreCase(TDMConstants.STATE_TX))
					{
						query.append(" AND  SD.STATE_CD ='TX'");
					}
				}
				else
				{
					query.append(" AND  SD.STATE_CD IS NOT NULL");
				}
			}
			if ((StringUtils.isNotEmpty(tdmNonStandDTO.getAccountName()) && StringUtils
					.isNotEmpty(tdmNonStandDTO.getAccountName().replaceAll(" ", "")))
					&& StringUtils.isEmpty(tdmNonStandDTO.getSubscId().replaceAll(" ", "")))
			{
				query.append(" AND  SD.ACCOUNT_NAME ='")
						.append(tdmNonStandDTO.getAccountName().trim()).append('\'');
			}
			if ((StringUtils.isNotEmpty(tdmNonStandDTO.getAccountNum()) && StringUtils
					.isNotEmpty(tdmNonStandDTO.getAccountNum().replaceAll(" ", "")))
					&& StringUtils.isEmpty(tdmNonStandDTO.getSubscId().replaceAll(" ", "")))
			{
				query.append(" AND SD.ACCOUNT_NUMBER ='")
						.append(tdmNonStandDTO.getAccountNum().trim()).append('\'');
			}

			if (StringUtils.isNotEmpty(tdmNonStandDTO.getSubscId())
					&& StringUtils.isNotEmpty(tdmNonStandDTO.getSubscId().replaceAll(" ", "")))
			{
				query.append(" AND SD.SUBS_ID ='").append(tdmNonStandDTO.getSubscId().trim())
						.append('\'');
			}

			if (StringUtils.isNotEmpty(tdmNonStandDTO.getPlanType()) && (!isDisregard))
			{
				String prodCD = tdmNonStandDTO.getPlanType();
				if (tdmNonStandDTO.getMemCat().equalsIgnoreCase(TDMConstants.RETAIL)
						&& (!TDMConstants.ANY.equalsIgnoreCase(tdmNonStandDTO.getSubscStatus())))
				{

					if (prodCD.contains(","))
					{
						prodCD = prodCD.replaceAll(",", "' OR SD.PROD_TYPE='");
						query.append(" AND ( SD.PROD_TYPE='").append(prodCD)
								.append("' AND SD.PROD_TYPE IS NOT NULL)");
					}
					else
					{
						if (!TDMConstants.ANY.equalsIgnoreCase(tdmNonStandDTO.getPlanType()))
						{

							query.append(" AND ( SD.PROD_TYPE='").append(prodCD)
									.append("' AND SD.PROD_TYPE IS NOT NULL)");
						}
						else
						{
							query.append(" AND SD.PROD_TYPE IS NOT NULL ");
						}
					}
				}
				else
				{
					if (prodCD.contains(","))
					{
						prodCD = prodCD.replaceAll(",", "','");
					}
					if (!TDMConstants.ANY.equalsIgnoreCase(tdmNonStandDTO.getPlanType()))
					{
						query.append(" AND  SD.PROD_TYPE IN ('").append(prodCD).append("')");
					}
					else
					{
						query.append(" AND SD.PROD_TYPE !='0' ");
					}
				}
			}

			if (StringUtils.isNotEmpty(tdmNonStandDTO.getCoverageCode()) && !isDisregard)
			{
				String coverage = tdmNonStandDTO.getCoverageCode();
				if (coverage.equalsIgnoreCase(TDMConstants.ALL))
				{
					query.append(" AND SD.MEDICAL_COVG_IND ='MEDICAL'");
					query.append(" AND SD.DENTAL_COVG_IND ='DENTAL'");
					query.append(" AND SD.VISION_COVG_IND ='VISION'");
					query.append(" AND SD.PRESC_COVG_IND ='DRUG'");
				}
				else if (coverage.equalsIgnoreCase(TDMConstants.ANY))
				{
					query.append(" AND (SD.MEDICAL_COVG_IND IS NOT NULL");
					query.append(" OR SD.DENTAL_COVG_IND IS NOT NULL");
					query.append(" OR SD.VISION_COVG_IND IS NOT NULL");
					query.append(" OR SD.PRESC_COVG_IND IS NOT NULL) ");
				}
				else if (coverage.contains(TDMConstants.COMMA))
				{
					String[] covers = coverage.split(",");
					for (String cov : covers)
					{
						if (cov.equalsIgnoreCase(TDMConstants.MEDICAL))
						{
							query.append(" AND SD.MEDICAL_COVG_IND ='MEDICAL'");
						}
						if (cov.equalsIgnoreCase(TDMConstants.DENTAL))
						{
							query.append(" AND SD.DENTAL_COVG_IND ='DENTAL'");
						}
						if (cov.equalsIgnoreCase(TDMConstants.VISION))
						{
							query.append(" AND SD.VISION_COVG_IND ='VISION'");
						}
						if (cov.replaceAll(" ", "").equalsIgnoreCase(TDMConstants.PRESCRIPTION_RX)
								|| cov.equalsIgnoreCase(TDMConstants.RX))
						{
							query.append(" AND SD.PRESC_COVG_IND ='DRUG'");
						}
					}
				}
				else
				{
					if (coverage.equalsIgnoreCase(TDMConstants.MEDICAL))
					{
						query.append(" AND SD.PRIMERY_COVG_IND like '%MEDICAL%'");
					}
					else if (coverage.equalsIgnoreCase(TDMConstants.MEDICAL_ONLY))
					{
						query.append(" AND SD.MEDICAL_COVG_IND ='MEDICAL'");
						query.append(" AND SD.COVG_ONLY_IND ='M' ");
					}
					else if (coverage.equalsIgnoreCase(TDMConstants.DENTAL))
					{
						query.append(" AND SD.PRIMERY_COVG_IND like '%DENTAL%'");
					}
					else if (coverage.equalsIgnoreCase(TDMConstants.DENTAL_ONLY))
					{
						query.append(" AND SD.DENTAL_COVG_IND ='DENTAL'");
						query.append(" AND SD.COVG_ONLY_IND ='D' ");
					}
					else if (coverage.equalsIgnoreCase(TDMConstants.VISION))
					{
						query.append(" AND SD.PRIMERY_COVG_IND like '%VISION%'");
					}
					else if (coverage.equalsIgnoreCase(TDMConstants.VISION_ONLY))
					{
						query.append(" AND SD.VISION_COVG_IND ='VISION'");
						query.append(" AND SD.COVG_ONLY_IND ='V' ");
					}
					else if (coverage.replaceAll(" ", "").equalsIgnoreCase(
							TDMConstants.PRESCRIPTION_RX)
							|| coverage.equalsIgnoreCase(TDMConstants.RX))
					{
						query.append(" AND SD.PRIMERY_COVG_IND like '%DRUG%'");
					}
					else if (coverage.equalsIgnoreCase(TDMConstants.PRESCRIPTION_RX_ONLY))
					{
						query.append(" AND SD.PRESC_COVG_IND ='DRUG'");
						query.append(" AND SD.COVG_ONLY_IND ='P' ");
					}
				}
			}

			if (StringUtils.isNotEmpty(tdmNonStandDTO.getSubscStatus()) && !isDisregard)
			{
				String statSub = tdmNonStandDTO.getSubscStatus().trim();
				if (!TDMConstants.ANY.equalsIgnoreCase(statSub))
				{
					if (statSub.equalsIgnoreCase(TDMConstants.SUB_ACTIVE))
					{
						query.append(" AND SD.SUBS_STATUS ='ACTIVE SUBSCRIBER'");
					}
					else if (statSub.equalsIgnoreCase(TDMConstants.SUB_CANCELED))
					{
						query.append(" AND SD.SUBS_STATUS ='CANCELLED SUBSCRIBER'");
					}
					else if (statSub.equalsIgnoreCase(TDMConstants.SUB_ACTIV_ACTV_DPEN))
					{
						query.append(" AND SD.DEPT_STATUS ='ACTIVE DEPENDENT'");
						query.append(" AND SD.SUBS_STATUS ='ACTIVE SUBSCRIBER'");
					}
					else if (statSub.equalsIgnoreCase(TDMConstants.SUB_ACTIV_CANCEL_DEPN))
					{
						query.append(" AND SD.SUBS_STATUS ='ACTIVE SUBSCRIBER'");
						query.append(" AND SD.DEPT_STATUS ='CANCELLED DEPENDENT'");
					}
					else if (statSub.equalsIgnoreCase(TDMConstants.SUB_CANCEL_CANCEL_SPOUSE))
					{
						query.append(" AND SD.SUBS_STATUS ='CANCELLED SUBSCRIBER'");
						query.append(" AND SD.DEPT_STATUS ='CANCELLED DEPENDENT'");
						query.append(" AND SD.RELSHP ='SPOUSE'");
					}
					else if (statSub.equalsIgnoreCase(TDMConstants.SUB_CANCEL_CANCEL_DEPN))
					{
						query.append(" AND SD.SUBS_STATUS ='CANCELLED SUBSCRIBER'");
						query.append(" AND SD.DEPT_STATUS ='CANCELLED DEPENDENT'");
						query.append(" AND SD.RELSHP  !='SPOUSE'");
						query.append(" AND SD.RELSHP IS NOT NULL");
					}
				}
				else
				{
					query.append(" AND SD.SUBS_STATUS IS NOT NULL");
				}

				/*
				 * if (statSub.contains(TDMConstants.SUB_ACTIVE)) {
				 * query.append(
				 * " AND SD.MBRSHP_COVRG_GRP_SECT_END_DT > CURRENT_DATE "); }
				 * CR_06 fix revert back
				 */
			}

			if ((StringUtils.isNotEmpty(tdmNonStandDTO.getFundingInd()) && !TDMConstants.ANY
					.equalsIgnoreCase(tdmNonStandDTO.getFundingInd())) && !isDisregard)
			{
				String fundInd = tdmNonStandDTO.getFundingInd();
				if (fundInd.contains(TDMConstants.COMMA))
				{
					String[] fundsInds = fundInd.split(",");
					StringBuffer fundBuffer = new StringBuffer();
					for (String fundIn : fundsInds)
					{
						String fund = fundIn.split("-")[0];
						fundBuffer.append(fund + ",");
					}
					String fund = fundBuffer.toString();
					fund = fund.substring(0, fund.length() - 1);
					fund = fund.replaceAll(",", "','");
					query.append(" AND SD.FUNDING_IND IN (" + fund + ") ");
				}
				else
				{
					String fund = fundInd.split("-")[0];
					query.append(" AND SD.FUNDING_IND ='" + fund + "'");
				}
			}
			if (StringUtils.isNotEmpty(tdmNonStandDTO.getExtClaim()) && (!isDisregard))
			{

				String claim = tdmNonStandDTO.getExtClaim();
				if (claim.contains(TDMConstants.ALL))
				{
					query.append(" AND SD.MED_CLAIM_IND = 'MEDICAL'");
					query.append(" AND SD.DENTAL_CLAIM_IND ='DENTAL'");
					query.append(" AND SD.PRESCRIPTION_CLAIM_IND ='PRESCRIPTION'");
				}
				else if (claim.contains(TDMConstants.ANY))
				{
					query.append(" AND (SD.MED_CLAIM_IND IS NOT NULL");
					query.append(" OR SD.DENTAL_CLAIM_IND  IS NOT NULL ");
					query.append(" OR SD.PRESCRIPTION_CLAIM_IND  IS NOT NULL) ");
				}
				else if (claim.contains(TDMConstants.COMMA))
				{
					String[] claims = claim.split(",");

					if (!claim.contains(TDMConstants.MEDICAL))
					{
						query.append(" AND SD.MED_CLAIM_IND IS  NULL");
					}
					if (!claim.contains(TDMConstants.DENTAL))
					{
						query.append(" AND SD.DENTAL_CLAIM_IND  IS  NULL ");
					}
					if (!claim.contains(TDMConstants.PRESCRIPTION_RX))
					{
						query.append(" AND SD.PRESCRIPTION_CLAIM_IND  IS  NULL ");
					}

					for (String clm : claims)
					{
						if (clm.equalsIgnoreCase(TDMConstants.MEDICAL))
						{
							query.append(" AND SD.MED_CLAIM_IND = 'MEDICAL'");
						}
						else if (clm.equalsIgnoreCase(TDMConstants.DENTAL))
						{
							query.append(" AND SD.DENTAL_CLAIM_IND ='DENTAL'");
						}
						else if (clm.replaceAll(" ", "").equalsIgnoreCase(
								TDMConstants.PRESCRIPTION_RX))
						{
							query.append(" AND SD.PRESCRIPTION_CLAIM_IND ='PRESCRIPTION'");
						}
					}
				}
				else if (TDMConstants.NONE.equalsIgnoreCase(claim))
				{
					query.append(" AND (SD.MED_CLAIM_IND IS  NULL");
					query.append(" AND SD.DENTAL_CLAIM_IND  IS  NULL ");
					query.append(" AND SD.PRESCRIPTION_CLAIM_IND  IS  NULL) ");
				}
				else
				{
					if (claim.equalsIgnoreCase(TDMConstants.MEDICAL))
					{
						query.append(" AND SD.MED_CLAIM_IND = 'MEDICAL'");
						query.append(" AND SD.DENTAL_CLAIM_IND IS NULL");
						query.append(" AND SD.PRESCRIPTION_CLAIM_IND IS NULL");
					}
					else if (claim.equalsIgnoreCase(TDMConstants.DENTAL))
					{
						query.append(" AND SD.DENTAL_CLAIM_IND ='DENTAL'");
						query.append(" AND SD.MED_CLAIM_IND IS NULL");
						query.append(" AND SD.PRESCRIPTION_CLAIM_IND IS NULL");
					}
					else if (claim.replaceAll(" ", "").equalsIgnoreCase(
							TDMConstants.PRESCRIPTION_RX))
					{
						query.append(" AND SD.PRESCRIPTION_CLAIM_IND ='PRESCRIPTION'");
						query.append(" AND SD.DENTAL_CLAIM_IND IS NULL");
						query.append(" AND SD.MED_CLAIM_IND IS NULL");
					}
				}
			}

			if (tdmNonStandDTO.getSubscriberIds() != null
					&& tdmNonStandDTO.getSubscriberIds().size() > 0)
			{
				query.append(" AND SD.SUBS_ID NOT IN ( '0'");
				query.append(sb.toString());
				query.append(")");
			}
			query.append(" FETCH FIRST 5000 rows only) REPLACE2 A ) ");

			/*
			 * (TDMConstants.ANY.equalsIgnoreCase(tdmNonStandDTO.getPlanType())
			 * && TDMConstants.ANY
			 * .equalsIgnoreCase(tdmNonStandDTO.getFundingInd())) &&
			 */
			if ((tdmNonStandDTO.getCoverageCode().contains(TDMConstants.COMMA) || TDMConstants.ANY
					.equalsIgnoreCase(tdmNonStandDTO.getCoverageCode())) && !isDisregard)
			{

				String updatedQuery = query.toString().replace("REPLACE1",
						"  from SUB_DEP_MCG_BLND_DENORM SD1 WHERE SUBS_ID IN( SELECT SD.SUBS_ID  ");

				updatedQuery = updatedQuery.replace("REPLACE2", " ) ");
				query = new StringBuffer(updatedQuery);
			}
			else
			{
				String updatedQuery = query.toString().replaceAll("SD1", "SD");
				updatedQuery = updatedQuery.replace("REPLACE1", "  ");
				updatedQuery = updatedQuery.replace("REPLACE2", "  ");
				query = new StringBuffer(updatedQuery);
			}
			Long count = Long.parseLong(managerSubscr.createNativeQuery(query.toString())
					.getSingleResult().toString());
			logger.info(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.GET_NS_REC_COUNT
					+ MessageConstants.LOG_INFO_RETURN);

			return count;
		}
		catch (NoResultException ex)
		{
			logger.error(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.GET_NS_REC_COUNT,
					ex.getMessage());
			throw new DAOException(TDMExceptionCode.NORESULT, ex);
		}
		catch (IllegalStateException ex)
		{
			logger.error(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.GET_NS_REC_COUNT,
					ex.getMessage());
			throw new DAOException(TDMExceptionCode.ILLEGAL_STATE, ex);
		}
		catch (IllegalArgumentException ex)
		{
			logger.error(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.GET_NS_REC_COUNT,
					ex.getMessage());
			throw new DAOException(TDMExceptionCode.ILLEGAL_ARGUMENT, ex);
		}
		catch (Exception ex)
		{
			logger.error(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.GET_NS_REC_COUNT,
					ex.getMessage());
			throw new DAOException(TDMExceptionCode.EXCEPTION, ex);
		}
	}

	@Override
	public void unReserveResrvedRecords(List<TdmReservationDO> tdmUnResrveList, String userId,
			EntityManager managerUsr) throws DAOException
	{
		logger.info(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.UN_RESRVE_RECORDS);
		List<TdmReservationDO> reservationDOs = null;
		try
		{
			if (null != tdmUnResrveList && 0 < tdmUnResrveList.size())
			{
				managerUsr.getTransaction().begin();
				reservationDOs = new ArrayList<TdmReservationDO>();
				for (TdmReservationDO tdmReserveDo : tdmUnResrveList)
				{
					if (tdmReserveDo != null)
					{
						tdmReserveDo.setSubscrId(tdmReserveDo.getSubscrId());
						tdmReserveDo.setUserId(userId);
						tdmReserveDo = managerUsr.merge(tdmReserveDo);
						reservationDOs.add(tdmReserveDo);
					}
				}
				managerUsr.getTransaction().commit();
			}
			logger.info(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.UN_RESRVE_RECORDS
					+ MessageConstants.LOG_INFO_RETURN);
		}
		catch (NoResultException ex)
		{
			logger.error(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.UN_RESRVE_RECORDS,
					ex.getMessage());
			throw new DAOException(TDMExceptionCode.NORESULT, ex);
		}
		catch (IllegalStateException ex)
		{
			logger.error(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.UN_RESRVE_RECORDS,
					ex.getMessage());
			throw new DAOException(TDMExceptionCode.ILLEGAL_STATE, ex);
		}
		catch (IllegalArgumentException ex)
		{
			logger.error(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.UN_RESRVE_RECORDS,
					ex.getMessage());
			throw new DAOException(TDMExceptionCode.ILLEGAL_ARGUMENT, ex);
		}
		catch (Exception ex)
		{
			logger.error(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.UN_RESRVE_RECORDS,
					ex.getMessage());
			throw new DAOException(TDMExceptionCode.EXCEPTION, ex);
		}
	}

	@SuppressWarnings(TDMConstants.UNCHECKED)
	@Override
	public List<String> getSubscriberAcNameNumDetails(String type, String token,
			EntityManager subscrManager) throws DAOException
	{
		List<String> resultList = null;
		try
		{
			if (type.equalsIgnoreCase(TDMConstants.AC_NAME))
			{
				resultList = subscrManager
						.createNativeQuery(
								"SELECT distinct t.ACCOUNT_NAME FROM SUB_DEP_MCG_BLND_DENORM t where t.ACCOUNT_NAME like'"
										+ token.trim().toUpperCase()
										+ "%' order by t.ACCOUNT_NAME asc").setMaxResults(50)
						.getResultList();
			}
			else if (type.equalsIgnoreCase(TDMConstants.AC_NUM))
			{
				resultList = subscrManager
						.createNativeQuery(
								"SELECT distinct t.ACCOUNT_NUMBER FROM SUB_DEP_MCG_BLND_DENORM t where t.ACCOUNT_NUMBER like'"
										+ token.trim() + "%' order by t.ACCOUNT_NUMBER asc")
						.setMaxResults(50).getResultList();
			}
			else if (type.equalsIgnoreCase(TDMConstants.SUBSC_ID))
			{
				resultList = subscrManager
						.createNativeQuery(
								"SELECT distinct t.SUBS_ID FROM SUB_DEP_MCG_BLND_DENORM t where t.SUBS_ID like'"
										+ token.trim() + "%'  order by t.SUBS_ID asc")
						.setMaxResults(50).getResultList();
			}
			return resultList;
		}
		catch (NoResultException ex)
		{
			throw new DAOException(TDMExceptionCode.NORESULT, ex);
		}
		catch (IllegalStateException ex)
		{
			throw new DAOException(TDMExceptionCode.ILLEGAL_STATE, ex);
		}
		catch (IllegalArgumentException ex)
		{
			throw new DAOException(TDMExceptionCode.ILLEGAL_ARGUMENT, ex);
		}
		catch (Exception ex)
		{
			throw new DAOException(TDMExceptionCode.EXCEPTION, ex);
		}
	}

	@Override
	public long getNonStandardSearchRecordCount(TDMNonStandardSearchDTO tdmNonStandDTO,
			EntityManager managerSubscr) throws DAOException
	{
		logger.info(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.GET_NS_REC_COUNT);
		try
		{
			StringBuffer sb = new StringBuffer();
			StringBuffer query = new StringBuffer(
					"SELECT count(*) FROM TdmSubscriberDetailsDO s INNER JOIN s.mcgDetails smcg  where 0=0");
			// query.append(" LEFT OUTER JOIN s.depndDetails sdep ");

			if (tdmNonStandDTO.getSubscriberIds() != null
					&& tdmNonStandDTO.getSubscriberIds().size() > 0)
			{
				List<String> subIdList = tdmNonStandDTO.getSubscriberIds();
				for (String subId : subIdList)
				{
					sb.append(TDMConstants.COMMA).append("'").append(subId).append("'");
				}
			}

			if (StringUtils.isNotEmpty(tdmNonStandDTO.getMemCat())
					&& !TDMConstants.ANY.equalsIgnoreCase(tdmNonStandDTO.getMemCat()))
			{
				if (tdmNonStandDTO.getMemCat().equalsIgnoreCase("BLENDED"))
				{
					List<Integer> blenPtyIds = tdmNonStandDTO.getBlendedPtyIDs();
					if (blenPtyIds != null && blenPtyIds.size() > 0)
					{
						query.append(" AND s.subscrPtyId IN(0");
						for (int ptyId : blenPtyIds)
						{
							query.append(TDMConstants.COMMA + ptyId);
						}
						query.append(")");
					}
				}
				else
				{
					query.append(" AND s.membrCat ='")
							.append(tdmNonStandDTO.getMemCat().toUpperCase()).append('\'');

					if (tdmNonStandDTO.getMemCat().equalsIgnoreCase(TDMConstants.RETAIL))
					{
						if (tdmNonStandDTO.getRetailOnOff().equalsIgnoreCase(
								TDMConstants.ONEXCHANGE)
								|| tdmNonStandDTO.getRetailOnOff().equalsIgnoreCase(
										TDMConstants.OFFEXCHANGE))
						{
							query.append(" AND smcg.exchangeType ='")
									.append(tdmNonStandDTO.getRetailOnOff().toUpperCase())
									.append('\'');
						}
					}
				}
			}
			if (StringUtils.isNotEmpty(tdmNonStandDTO.getAgeGroup())
					&& !TDMConstants.ANY.equalsIgnoreCase(tdmNonStandDTO.getAgeGroup()))
			{
				String age = tdmNonStandDTO.getAgeGroup();
				if (age.contains("and"))
				{
					query.append(" AND  ((days(current_date) - days(date(s.dob))) / 365) "
							+ age.split("and")[0] + "");
					query.append(" AND ((days(current_date) - days(date(s.dob))) / 365)  "
							+ age.split("and")[1] + "");
				}
				else
				{
					query.append(" AND  ((days(current_date) - days(date(s.dob))) / 365) " + age
							+ " ");
				}
			}
			if (StringUtils.isNotEmpty(tdmNonStandDTO.getOriginalEffDate()))
			{
				query.append(" AND  s.membrOrigEffDate ='")
						.append(tdmNonStandDTO.getOriginalEffDate()).append('\'');
			}

			if (StringUtils.isNotEmpty(tdmNonStandDTO.getSubscRelation())
					&& !TDMConstants.ANY.equalsIgnoreCase(tdmNonStandDTO.getSubscRelation()))
			{
				String subRel = tdmNonStandDTO.getSubscRelation();
				if (subRel.equalsIgnoreCase(TDMConstants.SUBSCRIBER_ONLY))
				{
					query.append(" AND  s.subsInlyInd ='Y'");
				}
				else
				{
					query.append(" AND  s.subsInlyInd ='N'");
					query.append(" AND s.subscriberId IN ( SELECT distinct sdep.subscriberId FROM  TdmDependentDetailsDO sdep where 0=0 ");

					if (tdmNonStandDTO.getSubscriberIds() != null
							&& tdmNonStandDTO.getSubscriberIds().size() > 0)
					{
						query.append(" AND sdep.subscriberId NOT IN ( '0'");
						query.append(sb.toString());
						query.append(")");
					}
					if (subRel.equalsIgnoreCase(TDMConstants.SUB_STUDENT))
					{
						query.append(" AND sdep.relshp ='STUDENT'");
					}
					else if (subRel.equalsIgnoreCase(TDMConstants.SUB_SPOUSE_DEP))
					{
						query.append(" AND sdep.relshp ='SPOUSE'");
						query.append(" AND sdep.relshp IN('STUDENT', 'CHILD', 'OTHERS')");
					}
					else if (subRel.equalsIgnoreCase(TDMConstants.SUB_DEPS))
					{
						query.append(" AND sdep.relshp !='SPOUSE'");
					}
					else if (subRel.equalsIgnoreCase(TDMConstants.SUB_SPOUSE))
					{
						query.append(" AND sdep.relshp ='SPOUSE'");
					}
					query.append(" )");
				}
			}
			if (StringUtils.isNotEmpty(tdmNonStandDTO.getProvState())
					&& !TDMConstants.ANY.equalsIgnoreCase(tdmNonStandDTO.getProvState()))
			{
				query.append(" AND  s.state ='").append(tdmNonStandDTO.getProvState()).append('\'');
			}
			if (StringUtils.isNotEmpty(tdmNonStandDTO.getAccountName()))
			{
				query.append(" AND  s.accountName ='")
						.append(tdmNonStandDTO.getAccountName().trim()).append('\'');
			}
			if (StringUtils.isNotEmpty(tdmNonStandDTO.getAccountNum()))
			{
				query.append(" AND  s.accountNum ='").append(tdmNonStandDTO.getAccountNum().trim())
						.append('\'');
			}

			if (StringUtils.isNotEmpty(tdmNonStandDTO.getSubscId())
					&& !TDMConstants.ANY.equalsIgnoreCase(tdmNonStandDTO.getSubscId()))
			{
				query.append(" AND s.subscriberId ='").append(tdmNonStandDTO.getSubscId().trim())
						.append('\'');
			}

			if (StringUtils.isNotEmpty(tdmNonStandDTO.getPlanType())
					&& !TDMConstants.ANY.equalsIgnoreCase(tdmNonStandDTO.getPlanType()))
			{
				/*
				 * query.append(
				 * " AND  s.subscriberId IN (SELECT distinct sm.subscriberId FROM TdmMcgDetailsDO sm where 0=0 "
				 * ); String[] prodCDS =
				 * tdmNonStandDTO.getPlanType().split(","); for (String prodCD :
				 * prodCDS) {
				 * query.append(" AND  smcg.prodType = '").append(prodCD
				 * ).append("'"); } ismcg = true;
				 */

				String prodCD = tdmNonStandDTO.getPlanType();
				if (prodCD.contains(","))
				{
					prodCD = prodCD.replaceAll(",", "','");
				}
				query.append(" AND  smcg.prodType  IN ('").append(prodCD).append("')");
			}

			if (StringUtils.isNotEmpty(tdmNonStandDTO.getCoverageCode())
					&& !TDMConstants.ANY.equalsIgnoreCase(tdmNonStandDTO.getCoverageCode()))
			{
				/*
				 * if (!ismcg) { query.append(
				 * " AND  s.subscriberId IN (SELECT distinct sm.subscriberId FROM TdmMcgDetailsDO sm where 0=0 "
				 * ); }
				 */
				String coverage = tdmNonStandDTO.getCoverageCode();
				if (coverage.equalsIgnoreCase(TDMConstants.ALL))
				{
					query.append(" AND smcg.medicalCovgInd ='MEDICAL'");
					query.append(" AND smcg.dentalCovgInd ='DENTAL'");
					query.append(" AND smcg.visionCoverId ='VISION'");
					query.append(" AND smcg.prescCovgInd ='DRUG'");
				}
				else if (coverage.contains(TDMConstants.COMMA))
				{
					String[] covers = coverage.split(",");
					for (String cov : covers)
					{
						if (cov.equalsIgnoreCase(TDMConstants.MEDICAL))
						{
							query.append(" AND smcg.medicalCovgInd ='MEDICAL'");
						}
						if (cov.equalsIgnoreCase(TDMConstants.DENTAL))
						{
							query.append(" AND  smcg.dentalCovgInd ='DENTAL'");
						}
						if (cov.equalsIgnoreCase(TDMConstants.VISION))
						{
							query.append(" AND smcg.visionCoverId ='VISION'");
						}
						if (cov.equalsIgnoreCase(TDMConstants.PRESCRIPTION_RX))
						{
							query.append(" AND smcg.prescCovgInd ='DRUG'");
						}
					}
				}
				else
				{
					if (coverage.equalsIgnoreCase(TDMConstants.MEDICAL))
					{
						query.append(" AND smcg.medicalCovgInd ='MEDICAL'");
					}
					else if (coverage.equalsIgnoreCase(TDMConstants.DENTAL))
					{
						query.append(" AND  smcg.dentalCovgInd ='DENTAL'");
					}
					if (coverage.equalsIgnoreCase(TDMConstants.VISION))
					{
						query.append(" AND smcg.visionCoverId ='VISION'");
					}
					if (coverage.equalsIgnoreCase(TDMConstants.PRESCRIPTION_RX))
					{
						query.append(" AND smcg.prescCovgInd ='DRUG'");
					}
				}
				// ismcg = true;
			}
			/*
			 * if (ismcg) { query.append(")"); }
			 */
			if (StringUtils.isNotEmpty(tdmNonStandDTO.getSubscStatus())
					&& !TDMConstants.ANY.equalsIgnoreCase(tdmNonStandDTO.getSubscStatus()))
			{
				String statSub = tdmNonStandDTO.getSubscStatus().trim();
				if (statSub.equalsIgnoreCase(TDMConstants.SUB_ACTIVE))
				{
					query.append(" AND s.subscrStatus ='ACTIVE SUBSCRIBER'");
				}
				else if (statSub.equalsIgnoreCase(TDMConstants.SUB_CANCELED))
				{
					query.append(" AND s.subscrStatus ='CANCELLED SUBSCRIBER'");
				}
				else if (statSub.equalsIgnoreCase(TDMConstants.SUB_ACTIV_ACTV_DPEN))
				{
					query.append(" AND s.subscrStatus ='ACTIVE SUBSCRIBER'");
					query.append(" AND sdep.deptStatus ='ACTIVE DEPENDENT'");
				}
				else if (statSub.equalsIgnoreCase(TDMConstants.SUB_ACTIV_CANCEL_DEPN))
				{
					query.append(" AND s.subscrStatus ='ACTIVE SUBSCRIBER'");
					query.append(" AND sdep.deptStatus ='CANCELLED DEPENDENT'");
				}
			}
			if (StringUtils.isNotEmpty(tdmNonStandDTO.getExtClaim())
					&& !TDMConstants.ANY.equalsIgnoreCase(tdmNonStandDTO.getExtClaim()))
			{

				String claim = tdmNonStandDTO.getExtClaim();
				if (claim.contains(TDMConstants.ALL))
				{
					query.append(" AND s.medClaimInd = 'MEDICAL'");
					query.append(" AND s.dentalClaimInd ='DENTAL'");
					query.append(" AND s.prescClaimInd ='PRESCRIPTION'");
				}
				else if (claim.contains(TDMConstants.COMMA))
				{
					String[] claims = claim.split(",");
					for (String clm : claims)
					{
						if (clm.equalsIgnoreCase(TDMConstants.MEDICAL))
						{
							query.append(" AND s.medClaimInd = 'MEDICAL'");
						}
						else if (clm.equalsIgnoreCase(TDMConstants.DENTAL))
						{
							query.append(" AND s.dentalClaimInd ='DENTAL'");
						}
						else if (clm.equalsIgnoreCase(TDMConstants.PRESCRIPTION))
						{
							query.append(" AND s.prescClaimInd ='PRESCRIPTION'");
						}
					}
				}
				else
				{
					if (claim.equalsIgnoreCase(TDMConstants.MEDICAL))
					{
						query.append(" AND s.medClaimInd = 'MEDICAL'");
					}
					else if (claim.equalsIgnoreCase(TDMConstants.DENTAL))
					{
						query.append(" AND s.dentalClaimInd ='DENTAL'");
					}
					else if (claim.equalsIgnoreCase(TDMConstants.PRESCRIPTION))
					{
						query.append(" AND s.prescClaimInd ='PRESCRIPTION'");
					}
				}
			}

			if (tdmNonStandDTO.getSubscriberIds() != null
					&& tdmNonStandDTO.getSubscriberIds().size() > 0)
			{
				query.append(" AND s.subscriberId NOT IN ( '0'");
				query.append(sb.toString());
				query.append(")");
			}
			Long count = (Long) managerSubscr.createQuery(query.toString()).getSingleResult();
			logger.info(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.GET_NS_REC_COUNT
					+ MessageConstants.LOG_INFO_RETURN);
			System.out.println("$$$$$$ Query $$$$$$" + query);
			return count;
		}
		catch (NoResultException ex)
		{
			logger.error(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.GET_NS_REC_COUNT,
					ex.getMessage());
			throw new DAOException(TDMExceptionCode.NORESULT, ex);
		}
		catch (IllegalStateException ex)
		{
			logger.error(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.GET_NS_REC_COUNT,
					ex.getMessage());
			throw new DAOException(TDMExceptionCode.ILLEGAL_STATE, ex);
		}
		catch (IllegalArgumentException ex)
		{
			logger.error(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.GET_NS_REC_COUNT,
					ex.getMessage());
			throw new DAOException(TDMExceptionCode.ILLEGAL_ARGUMENT, ex);
		}
		catch (Exception ex)
		{
			logger.error(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.GET_NS_REC_COUNT,
					ex.getMessage());
			throw new DAOException(TDMExceptionCode.EXCEPTION, ex);
		}
	}

	@Override
	public TdmUserDO checkAvailabilityOfUserId(String userId, EntityManager managerUser)
			throws Exception
	{
		logger.info(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.TDM_FTD_CHECK_AVIL_USER
				+ MessageConstants.LOG_INFO_PARAMS_NO);
		try
		{
			String query = "select count(u1.userId) from TdmUserDO u1 where u1.userId ='" + userId
					+ "'";
			Long count = (Long) managerUser.createQuery(query).getSingleResult();
			if (count > 0)
			{
				TdmUserDO userDO = managerUser.find(TdmUserDO.class, userId);
				logger.info(MessageConstants.TDM_NS_DAO_IMPL
						+ MessageConstants.TDM_FTD_CHECK_AVIL_USER
						+ MessageConstants.LOG_INFO_RETURN);
				return userDO;
			}
			else
			{

				return null;
			}
		}
		catch (IllegalStateException illegalStateEx)
		{
			logger.error(MessageConstants.TDM_NS_DAO_IMPL
					+ MessageConstants.TDM_FTD_CHECK_AVIL_USER
					+ MessageConstants.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstants.NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION,
					illegalStateEx);
		}
		catch (IllegalArgumentException illegalArgEx)
		{
			logger.error(MessageConstants.TDM_NS_DAO_IMPL
					+ MessageConstants.TDM_FTD_CHECK_AVIL_USER
					+ MessageConstants.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstants.INVALID_QUERY_EXCEPTION, illegalArgEx);
		}
		catch (NullPointerException nullPointerEx)
		{
			logger.error(MessageConstants.TDM_NS_DAO_IMPL
					+ MessageConstants.TDM_FTD_CHECK_AVIL_USER
					+ MessageConstants.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstants.NULL_POINTER_EXCEPTION, nullPointerEx);
		}
		catch (Exception otherEx)
		{
			logger.error(MessageConstants.TDM_NS_DAO_IMPL
					+ MessageConstants.TDM_FTD_CHECK_AVIL_USER
					+ MessageConstants.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstants.DATABASE_EXCEPTION, otherEx);
		}
	}

	@Override
	public List<TdmReservationDO> getReservedRecords(EntityManager managerUsr) throws DAOException
	{
		logger.info(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.GET_RESRVED_RECORDS_ONEARG);
		try
		{
			@SuppressWarnings(TDMConstants.UNCHECKED)
			List<TdmReservationDO> tdmReservationDO = managerUsr.createQuery(
					"SELECT t FROM TdmReservationDO t where t.locked='Y'").getResultList();
			logger.info(MessageConstants.TDM_NS_DAO_IMPL
					+ MessageConstants.GET_RESRVED_RECORDS_ONEARG
					+ MessageConstants.LOG_INFO_RETURN);
			return tdmReservationDO;
		}
		catch (NoResultException ex)
		{
			logger.error(MessageConstants.TDM_NS_DAO_IMPL
					+ MessageConstants.GET_RESRVED_RECORDS_ONEARG, ex.getMessage());
			throw new DAOException(TDMExceptionCode.NORESULT, ex);
		}
		catch (IllegalStateException ex)
		{
			logger.error(MessageConstants.TDM_NS_DAO_IMPL
					+ MessageConstants.GET_RESRVED_RECORDS_ONEARG, ex.getMessage());
			throw new DAOException(TDMExceptionCode.ILLEGAL_STATE, ex);
		}
		catch (IllegalArgumentException ex)
		{
			logger.error(MessageConstants.TDM_NS_DAO_IMPL
					+ MessageConstants.GET_RESRVED_RECORDS_ONEARG, ex.getMessage());
			throw new DAOException(TDMExceptionCode.ILLEGAL_ARGUMENT, ex);
		}
		catch (Exception ex)
		{
			logger.error(MessageConstants.TDM_NS_DAO_IMPL
					+ MessageConstants.GET_RESRVED_RECORDS_ONEARG, ex.getMessage());
			throw new DAOException(TDMExceptionCode.EXCEPTION, ex);
		}
	}

	@Override
	public List<TdmBlendedMemberDO> getBlendedRecords(EntityManager managerSubscr)
			throws DAOException
	{
		logger.info(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.GET_BLENDED_RECORDS);
		try
		{
			@SuppressWarnings(TDMConstants.UNCHECKED)
			List<TdmBlendedMemberDO> tdmBlendDO = managerSubscr.createQuery(
					"SELECT t FROM TdmBlendedMemberDO t ").getResultList();
			logger.info(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.GET_BLENDED_RECORDS
					+ MessageConstants.LOG_INFO_RETURN);
			return tdmBlendDO;
		}
		catch (NoResultException ex)
		{
			logger.error(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.GET_BLENDED_RECORDS,
					ex.getMessage());
			throw new DAOException(TDMExceptionCode.NORESULT, ex);
		}
		catch (IllegalStateException ex)
		{
			logger.error(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.GET_BLENDED_RECORDS,
					ex.getMessage());
			throw new DAOException(TDMExceptionCode.ILLEGAL_STATE, ex);
		}
		catch (IllegalArgumentException ex)
		{
			logger.error(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.GET_BLENDED_RECORDS,
					ex.getMessage());
			throw new DAOException(TDMExceptionCode.ILLEGAL_ARGUMENT, ex);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			logger.error(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.GET_BLENDED_RECORDS,
					ex.getMessage());
			throw new DAOException(TDMExceptionCode.EXCEPTION, ex);
		}
	}

	@Override
	public long getReservedRecordsCount(String role, String userId, EntityManager managerUsr)
			throws DAOException
	{
		logger.info(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.GET_RSERVE_RECORDSCOUNT);
		try
		{
			Long count = 0L;
			if (TDMConstants.ROLE_ADMIN.equalsIgnoreCase(role))
			{
				count = (Long) managerUsr.createQuery(
						"SELECT COUNT(*) FROM TdmReservationDO t WHERE t.locked='Y'")
						.getSingleResult();
			}
			else
			{
				count = (Long) managerUsr.createQuery(
						"SELECT COUNT(*) FROM TdmReservationDO t where t.userId='" + userId
								+ "' AND t.locked='Y'").getSingleResult();
			}

			logger.info(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.GET_RSERVE_RECORDSCOUNT
					+ MessageConstants.LOG_INFO_RETURN);
			return count;
		}
		catch (NoResultException ex)
		{
			logger.error(MessageConstants.TDM_NS_DAO_IMPL
					+ MessageConstants.GET_RSERVE_RECORDSCOUNT, ex.getMessage());
			throw new DAOException(TDMExceptionCode.NORESULT, ex);
		}
		catch (IllegalStateException ex)
		{
			logger.error(MessageConstants.TDM_NS_DAO_IMPL
					+ MessageConstants.GET_RSERVE_RECORDSCOUNT, ex.getMessage());
			throw new DAOException(TDMExceptionCode.ILLEGAL_STATE, ex);
		}
		catch (IllegalArgumentException ex)
		{
			logger.error(MessageConstants.TDM_NS_DAO_IMPL
					+ MessageConstants.GET_RSERVE_RECORDSCOUNT, ex.getMessage());
			throw new DAOException(TDMExceptionCode.ILLEGAL_ARGUMENT, ex);
		}
		catch (Exception ex)
		{
			logger.error(MessageConstants.TDM_NS_DAO_IMPL
					+ MessageConstants.GET_RSERVE_RECORDSCOUNT, ex.getMessage());
			throw new DAOException(TDMExceptionCode.EXCEPTION, ex);
		}
	}

	@SuppressWarnings(TDMConstants.UNCHECKED)
	@Override
	public List<Object[]> getDependentDetails(String subId, EntityManager managerSubscr)
			throws DAOException
	{
		logger.info(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.GET_DEPNDENT_DETAILS);
		try
		{
			String[] subMemId = subId.split(":");
			StringBuffer query = new StringBuffer();
			query.append(
					"SELECT D.DEPT_STATUS,D.MBR_EFF_DT,D.MBR_END_DT,D.DOB,D.FIRST_NAME,D.LAST_NAME,D.GNDR_CD,D.MBR_CAT,")
					.append(" D.RELSHP,D.STATE_CD,D.SUBS_PTY_ID,D.SUBS_STATUS, D.RELSHP_CD,D.DEPT_PTY_ID,D.SUBS_ID,D.RELSHP_NM,D.ZIPCODE")
					.append(" FROM DEPENDENT_DETAILS D WHERE D.SUBS_PTY_ID =")
					.append(Integer.parseInt(subMemId[0]))
					.append(" AND D.SUBS_ID='" + subMemId[1] + "'");
			List<Object[]> depenDetails = managerSubscr.createNativeQuery(query.toString())
					.getResultList();
			logger.info(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.GET_DEPNDENT_DETAILS
					+ MessageConstants.LOG_INFO_RETURN);
			return depenDetails;
		}
		catch (NoResultException ex)
		{
			logger.error(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.GET_DEPNDENT_DETAILS,
					ex.getMessage());
			throw new DAOException(TDMExceptionCode.NORESULT, ex);
		}
		catch (IllegalStateException ex)
		{
			logger.error(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.GET_DEPNDENT_DETAILS,
					ex.getMessage());
			throw new DAOException(TDMExceptionCode.ILLEGAL_STATE, ex);
		}
		catch (IllegalArgumentException ex)
		{
			logger.error(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.GET_DEPNDENT_DETAILS,
					ex.getMessage());
			throw new DAOException(TDMExceptionCode.ILLEGAL_ARGUMENT, ex);
		}
		catch (Exception ex)
		{
			logger.error(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.GET_DEPNDENT_DETAILS,
					ex.getMessage());
			throw new DAOException(TDMExceptionCode.EXCEPTION, ex);
		}
	}

	@SuppressWarnings(TDMConstants.UNCHECKED)
	@Override
	public List<Object[]> getDependentDetails(TDMNonStandardSearchDTO tdmNonStandDTO,
			EntityManager managerSubscr) throws DAOException
	{
		logger.info(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.GET_DEPNDENT_DETAILS);
		try
		{

			List<String> subIds = tdmNonStandDTO.getSubscriberIds();
			List<String> memIds = tdmNonStandDTO.getMembrIds();

			StringBuffer subId = new StringBuffer();
			StringBuffer memId = new StringBuffer();
			StringBuffer query = new StringBuffer();

			for (String subid : subIds)
			{
				subId.append(",").append("'").append(subid).append("'");
			}

			for (String memid : memIds)
			{
				memId.append(",").append(memid);
			}
			query.append(
					"SELECT D.DEPT_STATUS,D.MBR_EFF_DT,D.MBR_END_DT,D.DOB,D.FIRST_NAME,D.LAST_NAME,D.GNDR_CD,D.MBR_CAT,")
					.append(" D.RELSHP,D.STATE_CD,D.SUBS_PTY_ID,D.SUBS_STATUS, D.RELSHP_CD,D.DEPT_PTY_ID,D.SUBS_ID,D.RELSHP_NM, D.ZIPCODE")
					.append(" FROM DEPENDENT_DETAILS D WHERE D.SUBS_PTY_ID IN (0")
					.append(memId.toString()).append(")").append("  AND D.SUBS_ID IN('0'")
					.append(subId.toString()).append(")");
			List<Object[]> depenDetails = managerSubscr.createNativeQuery(query.toString())
					.getResultList();
			logger.info(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.GET_DEPNDENT_DETAILS
					+ MessageConstants.LOG_INFO_RETURN);
			return depenDetails;
		}
		catch (NoResultException ex)
		{
			logger.error(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.GET_DEPNDENT_DETAILS,
					ex.getMessage());
			throw new DAOException(TDMExceptionCode.NORESULT, ex);
		}
		catch (IllegalStateException ex)
		{
			logger.error(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.GET_DEPNDENT_DETAILS,
					ex.getMessage());
			throw new DAOException(TDMExceptionCode.ILLEGAL_STATE, ex);
		}
		catch (IllegalArgumentException ex)
		{
			logger.error(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.GET_DEPNDENT_DETAILS,
					ex.getMessage());
			throw new DAOException(TDMExceptionCode.ILLEGAL_ARGUMENT, ex);
		}
		catch (Exception ex)
		{
			logger.error(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.GET_DEPNDENT_DETAILS,
					ex.getMessage());
			throw new DAOException(TDMExceptionCode.EXCEPTION, ex);
		}
	}

	@Override
	public void saveSearchDetails(TDMSearchDetailsDO serachDtlsDO, EntityManager managerUser)
			throws Exception
	{
		try
		{
			managerUser.getTransaction().begin();
			managerUser.merge(serachDtlsDO);
			managerUser.getTransaction().commit();

		}
		catch (NoResultException ex)
		{
			logger.error(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.GET_DEPNDENT_DETAILS,
					ex.getMessage());
			throw new DAOException(TDMExceptionCode.NORESULT, ex);
		}
		catch (IllegalStateException ex)
		{
			logger.error(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.GET_DEPNDENT_DETAILS,
					ex.getMessage());
			throw new DAOException(TDMExceptionCode.ILLEGAL_STATE, ex);
		}
		catch (IllegalArgumentException ex)
		{
			logger.error(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.GET_DEPNDENT_DETAILS,
					ex.getMessage());
			throw new DAOException(TDMExceptionCode.ILLEGAL_ARGUMENT, ex);
		}
		catch (Exception ex)
		{
			logger.error(MessageConstants.TDM_NS_DAO_IMPL + MessageConstants.GET_DEPNDENT_DETAILS,
					ex.getMessage());
			throw new DAOException(TDMExceptionCode.EXCEPTION, ex);
		}
	}
}
