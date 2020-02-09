/*
 * Object Name : TdmPolicyCenterSearchDAOImpl.java
 * Modification Block
 * ------------------------------------------------------------------
 * S.No.	Name 			Date			Bug_Fix_No			Desc
 * ------------------------------------------------------------------
 * 	1.	  vkrish14		4:51:41 PM				Created
 * ------------------------------------------------------------------
 * Copyrights: 2015 Capgemini.com
 */
package com.tdm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tdm.constant.MessageConstant;
import com.tdm.dao.TdmPolicyCenterSearchDAO;
import com.tdm.exception.DAOException;
import com.tdm.model.DO.PctlCountryDO;
import com.tdm.model.DO.PctlPolicycontactroleDO;
import com.tdm.model.DO.PctlStateDO;
import com.tdm.model.DO.TdmReservationDO;
import com.tdm.model.DTO.TdmPolicyCenterSearchDTO;

/**
 * @author vkrish14
 *
 */
@Component("tdmPolicyCenterSearchDAO")
public class TdmPolicyCenterSearchDAOImpl implements TdmPolicyCenterSearchDAO{

	private static Logger logger = Logger.getLogger(TdmPolicyCenterSearchDAOImpl.class);
	
	@Autowired
	private MessageSource messageSource;
	
	/*private static final String searchQuery0="select distinct (pc_account.AccountNumber),pc_policyperiod.PolicyNumber,pc_contact.WorkPhone,pc_contact.FirstName,pc_contact.LastName,pc_contact.EmailAddress1,pc_contact.EmailAddress2,pc_contact.CityDenorm"
			+",pc_address.AddressLine1,pc_address.AddressLine2,pc_address.AddressLine3,pc_address.State,pc_address.CityDenorm,pc_address.Country,pc_producercode.Code,pc_policy.ProductCode,pc_policy.OriginalEffectiveDate"
			+",pc_policyaddress.ExpirationDate from pc_contact JOIN pc_accountcontact on pc_contact.id=pc_accountcontact.contact JOIN pc_account on pc_account.id=pc_accountcontact.account JOIN pc_policy on pc_policy.AccountID=pc_account.id ";*/
			
	// Before change query 
	/*private static final String searchQuery0="select distinct(pc_policyperiod.policynumber),pc_account.AccountNumber,pc_contact.EmailAddress2,pc_contact.WorkPhone,pc_contact.FirstName"
												+",pc_contact.LastName,pc_address.AddressLine1,pc_address.AddressLine2,pc_address.State,pc_address.CityDenorm,pc_address.Country,pc_producercode.Code"
												+",pc_policy.ProductCode,pc_policy.OriginalEffectiveDate,pc_policyaddress.ExpirationDate,pc_effectivedatedfields.SecondaryNamedInsured,pc_policycontactrole.NumberOfViolations,pc_policycontactrole.NumberOfAccidents"
												+",pc_priorpolicy.Carrier from pc_account JOIN pc_policy on pc_policy.AccountID=pc_account.id ";*/

	/*private static final String searchQuery0="select distinct(policyperiod.policynumber),account.accountnumber,contact.emailaddress2,contact.workphone,contact.firstname"
												+",contact.lastname,contact.citydenorm,address.addressline1,address.addressline2,address.state,address.citydenorm,address.country,producercode.code,policyperiod.policynumber"
												+",policy.productcode,policy.originaleffectiveDate,pc_policyaddress.ExpirationDate,pc_effectivedatedfields.SecondaryNamedInsured,pc_policycontactrole.NumberOfViolations,pc_policycontactrole.NumberOfAccidents"
												+",pc_priorpolicy.Carrier from pc_account JOIN pc_policy on pc_policy.AccountID=pc_account.id ";*/
	/*private static final String searchQuery1=" JOIN pc_address on pc_address.account=pc_account.ID ";
			private static final String searchQuery2 =  "LEFT JOIN pc_contact on pc_contact.PrimaryAddressID=pc_address.ID ";
			private static final String searchQuery3= " LEFT JOIN pc_policyaddress on pc_policyaddress.address=pc_address.id ";
	private static final String searchQuery4=" JOIN pc_policyperiod on pc_policyperiod.PolicyID=pc_policy.id ";
	private static final String searchQuery5 = " LEFT JOIN pc_producercode on pc_address.ID=pc_producercode.AddressID LEFT JOIN pc_effectivedatedfields on pc_producercode.id=pc_effectivedatedfields.ProducerCodeID LEFT JOIN pc_policycontactrole on pc_policycontactrole.ContactDenorm=pc_contact.ID LEFT JOIN pc_priorpolicy on pc_priorpolicy.PolicyID=pc_policy.id ";
*/	
	
	private static final String searchQuery0="select distinct(pc_policyperiod.PolicyNumber),pc_account.AccountNumber,pc_contact.EmailAddress1,pc_contact.EmailAddress2,pc_contact.WorkPhone,pc_contact.FirstName,pc_contact.LastName,pc_address.AddressLine1,pc_address.AddressLine2,pc_address.AddressLine3,pc_address.State,pc_address.CityDenorm,pc_address.Country,pc_producercode.Code,pc_policy.ProductCode,"
			+"pc_policy.OriginalEffectiveDate,pc_policyaddress.ExpirationDate,pc_policycontactrole.subtype from pc_contact  JOIN pc_policycontactrole on pc_contact.id=pc_policycontactrole.ContactDenorm";
	private static final String searchQuery1=" JOIN pc_accountcontactrole on pc_policycontactrole.AccountContactRole=pc_accountcontactrole.id JOIN pc_accountcontact on pc_accountcontact.id=pc_accountcontactrole.AccountContact JOIN pc_account on pc_account.id=pc_accountcontact.account ";
	private static final String searchQuery2=" JOIN pc_policy on pc_policy.AccountID=pc_account.id ";
	private static final String searchQuery3= " JOIN pc_address on pc_address.account=pc_account.ID ";
	private static final String searchQuery4=" LEFT OUTER JOIN pc_policyaddress on pc_policyaddress.address=pc_address.id JOIN pc_policyperiod on pc_policyperiod.PolicyID=pc_policy.id  ";
	private static final String searchQuery5=" LEFT OUTER JOIN pc_producercode on pc_address.ID=pc_producercode.AddressID ";

	@Override
	public Long searchPolicyRecordsCount(TdmPolicyCenterSearchDTO tdmPolicySearchDTO,
			StringBuffer policys, EntityManager managerPolicySearch) throws DAOException{
		logger.info(MessageConstant.TDM_FTD_POLICY_DAO
				+ MessageConstant.TDM_FTD_SER_GET_RESERVE_CNT + MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			Long list = (Long) managerPolicySearch.createQuery(
					"SELECT COUNT(*) FROM TdmReservationDO p where p.reserveDataId='" + policys + "'").getSingleResult();
			logger.info(MessageConstant.TDM_FTD_POLICY_DAO
					+ MessageConstant.TDM_FTD_SER_GET_RESERVE_CNT + MessageConstant.LOG_INFO_RETURN);
			return list;
		} catch (IllegalStateException illegalStateEx) {
			logger.error(MessageConstant.TDM_FTD_POLICY_DAO
					+ MessageConstant.TDM_FTD_SER_GET_RESERVE_CNT
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION,
					illegalStateEx);
		} catch (IllegalArgumentException illegalArgEx) {
			logger.error(MessageConstant.TDM_FTD_POLICY_DAO
					+ MessageConstant.TDM_FTD_SER_GET_RESERVE_CNT
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.INVALID_QUERY_EXCEPTION, illegalArgEx);
		} catch (NullPointerException nullPointerEx) {
			logger.error(MessageConstant.TDM_FTD_POLICY_DAO
					+ MessageConstant.TDM_FTD_SER_GET_RESERVE_CNT
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.NULL_POINTER_EXCEPTION, nullPointerEx);
		} catch (Exception otherEx) {
			logger.error(MessageConstant.TDM_FTD_POLICY_DAO
					+ MessageConstant.TDM_FTD_SER_GET_RESERVE_CNT
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.DATABASE_EXCEPTION, otherEx);
		}
	}

	@Override
	public List<TdmReservationDO> getReservedRecords(String userName, String searchType,
			EntityManager managerUser) throws DAOException{
		logger.info(MessageConstant.TDM_FTD_POLICY_DAO
				+ MessageConstant.TDM_FTD_SER_GET_RESERVE_CNT + MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			List<TdmReservationDO> list =  (List<TdmReservationDO>) managerUser.createQuery(
					" SELECT p FROM TdmReservationDO p where p.reserveDataType='" + searchType + "'",TdmReservationDO.class).getResultList();
			logger.info(MessageConstant.TDM_FTD_POLICY_DAO
					+ MessageConstant.TDM_FTD_SER_GET_RESERVE_CNT + MessageConstant.LOG_INFO_RETURN);
			return list;
		} catch (IllegalStateException illegalStateEx) {
			logger.error(MessageConstant.TDM_FTD_POLICY_DAO
					+ MessageConstant.TDM_FTD_SER_GET_RESERVE_CNT
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION,
					illegalStateEx);
		} catch (IllegalArgumentException illegalArgEx) {
			logger.error(MessageConstant.TDM_FTD_POLICY_DAO
					+ MessageConstant.TDM_FTD_SER_GET_RESERVE_CNT
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.INVALID_QUERY_EXCEPTION, illegalArgEx);
		} catch (NullPointerException nullPointerEx) {
			logger.error(MessageConstant.TDM_FTD_POLICY_DAO
					+ MessageConstant.TDM_FTD_SER_GET_RESERVE_CNT
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.NULL_POINTER_EXCEPTION, nullPointerEx);
		} catch (Exception otherEx) {
			logger.error(MessageConstant.TDM_FTD_POLICY_DAO
					+ MessageConstant.TDM_FTD_SER_GET_RESERVE_CNT
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.DATABASE_EXCEPTION, otherEx);
		}
	}

	@Override
	public List<TdmReservationDO> getReservedRecords(String userName, EntityManager managerUser)
			throws DAOException{
		try {
			long startTime = System.currentTimeMillis();
			@SuppressWarnings("unchecked")
			List<TdmReservationDO> list = managerUser.createQuery(
					" FROM TdmReservationDO p where p.userId='" + userName + "'").getResultList();
			long stopTime = System.currentTimeMillis();
			long elapsedTime = stopTime - startTime;
			if ((elapsedTime / 60000) > 1) {
				throw new DAOException(
						"Query taking more than one min time for validating the records");
			}
			String str = " FROM TdmReservationDO p where AND p.userId'" + userName + "'";
			logger.info(elapsedTime + ": Millis To execute the query getReservedRecords: " + str);
			return list;
		} catch (IllegalStateException illegalStateEx) {
			logger.error("MessageConstant.NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION" + illegalStateEx);
			throw new DAOException(messageSource.getMessage(
					"NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION", null, null), illegalStateEx);
		} catch (IllegalArgumentException illegalArgEx) {
			logger.error("MessageConstant.INVALID_QUERY_EXCEPTION" + illegalArgEx);
			throw new DAOException(messageSource.getMessage("INVALID_QUERY_EXCEPTION", null, null),
					illegalArgEx);
		} catch (NullPointerException nullPointerEx) {
			logger.error("MessageConstant.NULL_POINTER_EXCEPTION" + nullPointerEx);
			throw new DAOException(messageSource.getMessage("NULL_POINTER_EXCEPTION", null, null),
					nullPointerEx);
		} catch (Exception otherEx) {
			logger.error("MessageConstant.DATABASE_EXCEPTION" + otherEx);
			throw new DAOException(messageSource.getMessage("DATABASE_EXCEPTION", null, null),
					otherEx);
		}
	}


	@Override
	@Transactional
	public List<TdmReservationDO> saveReservedData(List<TdmReservationDO> reservationDOList,
			String testCaseId, String testCaseName, EntityManager managerCsaaUser)
			throws DAOException {
		List<TdmReservationDO> reservationDOs = null;
		try {
			if (null != reservationDOList && 0 < reservationDOList.size()) {
				managerCsaaUser.getTransaction().begin();
				reservationDOs = new ArrayList<TdmReservationDO>();
				for (TdmReservationDO reservationDO : reservationDOList) {
					if (null != reservationDO && 0 == reservationDO.getRecordId()) {
						reservationDO.setTestCaseId(testCaseId);
						reservationDO.setTestCaseName(testCaseName);
						reservationDO.setReserveDataType("PC");
						reservationDO = managerCsaaUser.merge(reservationDO);
						reservationDOs.add(reservationDO);
					}
				}
				managerCsaaUser.getTransaction().commit();
			}
		} catch (IllegalStateException illegalStateEx) {
			logger.error("MessageConstant.NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION" + illegalStateEx);
			throw new DAOException(messageSource.getMessage(
					"NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION", null, null), illegalStateEx);
		} catch (IllegalArgumentException illegalArgEx) {
			logger.error("MessageConstant.INVALID_QUERY_EXCEPTION" + illegalArgEx);
			throw new DAOException(messageSource.getMessage("INVALID_QUERY_EXCEPTION", null, null),
					illegalArgEx);
		} catch (NullPointerException nullPointerEx) {
			logger.error("MessageConstant.NULL_POINTER_EXCEPTION" + nullPointerEx);
			throw new DAOException(messageSource.getMessage("NULL_POINTER_EXCEPTION", null, null),
					nullPointerEx);
		} catch (Exception otherEx) {
			logger.error("MessageConstant.DATABASE_EXCEPTION" + otherEx);
			throw new DAOException(messageSource.getMessage("DATABASE_EXCEPTION", null, null),
					otherEx);
		}
		return reservationDOs;
	}

	@Override
	public boolean unReservedRecordForUser(String policyNo, EntityManager managerUser)
			throws DAOException{
		logger.info(MessageConstant.TDM_FTD_POLICY_DAO
				+ MessageConstant.TDM_FTD_POLICY_UNRESERVE_REC + MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			managerUser.getTransaction().begin();
			Query q1 = managerUser
					.createQuery("DELETE FROM TdmReservationDO p where p.reserveDataId =:reserveDataId");
			q1.setParameter("reserveDataId", String.valueOf(policyNo));
			int count = q1.executeUpdate();
			managerUser.getTransaction().commit();
			logger.info(MessageConstant.TDM_FTD_POLICY_DAO
					+ MessageConstant.TDM_FTD_POLICY_UNRESERVE_REC
					+ MessageConstant.LOG_INFO_RETURN);
			return count > 0 ? true : false;
		} catch (IllegalStateException illegalStateEx) {
			logger.error(MessageConstant.TDM_FTD_POLICY_DAO
					+ MessageConstant.TDM_FTD_POLICY_UNRESERVE_REC
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION,
					illegalStateEx);
		} catch (IllegalArgumentException illegalArgEx) {
			logger.error(MessageConstant.TDM_FTD_POLICY_DAO
					+ MessageConstant.TDM_FTD_POLICY_UNRESERVE_REC
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.INVALID_QUERY_EXCEPTION, illegalArgEx);
		} catch (NullPointerException nullPointerEx) {
			logger.error(MessageConstant.TDM_FTD_POLICY_DAO
					+ MessageConstant.TDM_FTD_POLICY_UNRESERVE_REC
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.NULL_POINTER_EXCEPTION, nullPointerEx);
		} catch (Exception otherEx) {
			logger.error(MessageConstant.TDM_FTD_POLICY_DAO
					+ MessageConstant.TDM_FTD_POLICY_UNRESERVE_REC
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.DATABASE_EXCEPTION, otherEx);
		}
	}

	@Override
	public List<TdmReservationDO> getReservedRecords(String searchType, String userId, int offSet,
			int recordsperpage, boolean pageNationOnOffFlag, EntityManager managerUser)
			throws DAOException{
		try {
			long startTime = System.currentTimeMillis();
			@SuppressWarnings("unchecked")
			List<TdmReservationDO> list = managerUser.createQuery(
					" FROM TdmReservationDO p where p.reserveDataType='PC' AND p.userId='" + userId + "'").getResultList();
			long stopTime = System.currentTimeMillis();
			long elapsedTime = stopTime - startTime;
			if ((elapsedTime / 60000) > 1) {
				throw new DAOException(
						"Query taking more than one min time for validating the records");
			}
			String str = " FROM TdmReservationDO p where AND p.userId'" + userId + "'";
			logger.info(elapsedTime + ": Millis To execute the query getReservedRecords: " + str);
			return list;
		} catch (IllegalStateException illegalStateEx) {
			logger.error("MessageConstant.NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION" + illegalStateEx);
			throw new DAOException(messageSource.getMessage(
					"NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION", null, null), illegalStateEx);
		} catch (IllegalArgumentException illegalArgEx) {
			logger.error("MessageConstant.INVALID_QUERY_EXCEPTION" + illegalArgEx);
			throw new DAOException(messageSource.getMessage("INVALID_QUERY_EXCEPTION", null, null),
					illegalArgEx);
		} catch (NullPointerException nullPointerEx) {
			logger.error("MessageConstant.NULL_POINTER_EXCEPTION" + nullPointerEx);
			throw new DAOException(messageSource.getMessage("NULL_POINTER_EXCEPTION", null, null),
					nullPointerEx);
		} catch (Exception otherEx) {
			logger.error("MessageConstant.DATABASE_EXCEPTION" + otherEx);
			throw new DAOException(messageSource.getMessage("DATABASE_EXCEPTION", null, null),
					otherEx);
		}
	}

	@Override
	public Long getReservedRecordsCount(String searchType, String userId, EntityManager managerUser)
			throws DAOException{
		logger.info(MessageConstant.TDM_FTD_POLICY_DAO
				+ MessageConstant.TDM_FTD_SER_GET_RESERVE_CNT + MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			Long list = (Long) managerUser.createQuery(
					"SELECT COUNT(*) FROM TdmReservationDO p where p.reserveDataType='PC' and  p.userId='" + userId + "'").getSingleResult();
			logger.info(MessageConstant.TDM_FTD_POLICY_DAO
					+ MessageConstant.TDM_FTD_SER_GET_RESERVE_CNT + MessageConstant.LOG_INFO_RETURN);
			return list;
		} catch (IllegalStateException illegalStateEx) {
			logger.error(MessageConstant.TDM_FTD_POLICY_DAO
					+ MessageConstant.TDM_FTD_SER_GET_RESERVE_CNT
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION,
					illegalStateEx);
		} catch (IllegalArgumentException illegalArgEx) {
			logger.error(MessageConstant.TDM_FTD_POLICY_DAO
					+ MessageConstant.TDM_FTD_SER_GET_RESERVE_CNT
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.INVALID_QUERY_EXCEPTION, illegalArgEx);
		} catch (NullPointerException nullPointerEx) {
			logger.error(MessageConstant.TDM_FTD_POLICY_DAO
					+ MessageConstant.TDM_FTD_SER_GET_RESERVE_CNT
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.NULL_POINTER_EXCEPTION, nullPointerEx);
		} catch (Exception otherEx) {
			logger.error(MessageConstant.TDM_FTD_POLICY_DAO
					+ MessageConstant.TDM_FTD_SER_GET_RESERVE_CNT
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new DAOException(MessageConstant.DATABASE_EXCEPTION, otherEx);
		}
	}

	@Override
	public List<Object[]> getPolicyStatus(EntityManager managerPolicySearch) throws DAOException{
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Object[]> searchPolicyCenterRecords(
			TdmPolicyCenterSearchDTO tdmPolicyCenterSearchDTO, int offSet, int recordsperpage,
			boolean pageNationOnOffFlag, StringBuffer policys, boolean btotalRecords,EntityManager managerPolicySearch,boolean isCalledFromReserve)
			throws DAOException{
		List lstResult = null;
		if(null != tdmPolicyCenterSearchDTO && StringUtils.isNotEmpty(tdmPolicyCenterSearchDTO.getProductCode())){
			StringBuffer strFinalQuery = new StringBuffer(searchQuery0);
			
			if(StringUtils.isNotEmpty(tdmPolicyCenterSearchDTO.getInsurerType())){
				strFinalQuery.append(" and pc_policycontactrole.subtype=").append(tdmPolicyCenterSearchDTO.getInsurerType()).append(" ");
			}
			if(StringUtils.isNotEmpty(tdmPolicyCenterSearchDTO.getGender())){
				strFinalQuery.append(" and pc_contact.gender=").append(tdmPolicyCenterSearchDTO.getGender()).append(" ");
			}
			
			
			strFinalQuery.append(searchQuery1);
			if(StringUtils.isNotEmpty(tdmPolicyCenterSearchDTO.getStatus())){
				strFinalQuery.append(" and pc_account.accountstatus='").append(tdmPolicyCenterSearchDTO.getStatus()).append("' ");
			}
			
			strFinalQuery.append(searchQuery2);
			strFinalQuery.append(" and pc_policy.productcode='").append(tdmPolicyCenterSearchDTO.getProductCode()).append("' ");
			if(null !=tdmPolicyCenterSearchDTO.getOriginalEffectvieDate()){
				strFinalQuery.append(" and pc_policy.OriginalEffectiveDate='").append(tdmPolicyCenterSearchDTO.getOriginalEffectvieDate()).append("' ");
			}
			
			
			strFinalQuery.append(searchQuery3);
			if(StringUtils.isNotEmpty(tdmPolicyCenterSearchDTO.getState())){
				strFinalQuery.append(" and pc_address.state='").append(tdmPolicyCenterSearchDTO.getState()).append("' ");	
			}
			if(StringUtils.isNotEmpty(tdmPolicyCenterSearchDTO.getCountry())){
				strFinalQuery.append(" and pc_address.country='").append(tdmPolicyCenterSearchDTO.getCountry()).append("' ");	
			}
			
			strFinalQuery.append(searchQuery4);
			if(StringUtils.isNotEmpty(tdmPolicyCenterSearchDTO.getPolicyNumber())){
				strFinalQuery.append(" and pc_policyperiod.policynumber='").append(tdmPolicyCenterSearchDTO.getPolicyNumber()).append("' ");
			}
			if(policys != null && StringUtils.isNotEmpty(policys.toString())){
				strFinalQuery.append(" and pc_policyperiod.policynumber not in (").append(policys.toString()).append(") ");
			}
				
			
			strFinalQuery.append(searchQuery5);
			
			if(!btotalRecords)
			 lstResult= managerPolicySearch.createNativeQuery(strFinalQuery.toString()).setFirstResult(offSet).setMaxResults(recordsperpage).getResultList();
			else
				lstResult = managerPolicySearch.createNativeQuery(strFinalQuery.toString()).getResultList();
		}
		return lstResult;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getPolicyRecords(StringBuffer policys, EntityManager managerPolicySearch)throws DAOException{
		@SuppressWarnings("rawtypes")
		List lstResult = null;
		if(StringUtils.isNotEmpty(policys.toString())){
			StringBuffer strFinalQuery = new StringBuffer(searchQuery0);
			strFinalQuery.append(searchQuery1);
			strFinalQuery.append(searchQuery2).append(searchQuery3).append(searchQuery4);
				strFinalQuery.append(" and pc_policyperiod.policynumber in (").append(policys).append(") ");
				strFinalQuery.append(searchQuery5);
			    lstResult = managerPolicySearch.createNativeQuery(strFinalQuery.toString()).getResultList();
			}
		return lstResult;
	}

	@Override
	public List<PctlCountryDO> getCountryCodes(EntityManager managerPolicySearch)throws DAOException{
		List<PctlCountryDO> listResult = managerPolicySearch.createNamedQuery("PctlCountryDO.findAll",PctlCountryDO.class).getResultList();
		return listResult;
	}

	@Override
	public List<PctlStateDO> getStateCodes(EntityManager managerPolicySearch)throws DAOException{
		List<PctlStateDO> listResult = managerPolicySearch.createNamedQuery("PctlStateDO.findAll",PctlStateDO.class).getResultList();
		return listResult;
	}

	@Override
	public List<PctlPolicycontactroleDO> getInsurerTypes(EntityManager managerPolicySearch)
			throws DAOException{
		List<PctlPolicycontactroleDO> listResult = managerPolicySearch.createNamedQuery("PctlPolicycontactroleDO.findAll",PctlPolicycontactroleDO.class).getResultList();
		return listResult;
	}
}
