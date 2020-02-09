/*---------------------------------------------------------------------------------------
 * Object Name: TDMNonStandSearchServiceImpl.Java
 * --------------------------------------------------------------------------------------
 *
 * Copyright: 2015 Capgemini Financial Services
 *---------------------------------------------------------------------------------------*/

package com.tesda.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.Resource;
import javax.persistence.EntityManager;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tesda.constants.MessageConstants;
import com.tesda.constants.TDMConstants;
import com.tesda.constants.TDMExceptionCode;
import com.tesda.dao.TDMAdminDAO;
import com.tesda.dao.TDMNonStandSearchDao;
import com.tesda.email.EmailNotificationService;
import com.tesda.exception.DAOException;
import com.tesda.exception.ServiceException;
import com.tesda.model.DO.TDMSearchDetailsDO;
import com.tesda.model.DO.TdmLookupDO;
import com.tesda.model.DO.TdmReservationDO;
import com.tesda.model.DO.TdmUserDO;
import com.tesda.model.DTO.AutoEmailDTO;
import com.tesda.model.DTO.DependentDetailsDTO;
import com.tesda.model.DTO.ForgotPassword;
import com.tesda.model.DTO.TDMLoginUsersDTO;
import com.tesda.model.DTO.TDMNonStandReservationDTO;
import com.tesda.model.DTO.TDMNonStandSearchFieldsDTO;
import com.tesda.model.DTO.TDMNonStandardSearchDTO;
import com.tesda.model.DTO.TdmNonStandardSearchResultListDTO;
import com.tesda.model.mapper.TDMNonStandardSearchMapper;
import com.tesda.service.TDMNonStandSearchService;

/**
 * Service class which provides the following services. Finds the test data
 * based on search criteria. Gets the reserved records to display in My
 * reservation dash board. Reserve/UnReserve the Find test data records.
 */

@Component
@Service(TDMConstants.NONSTAND_SEARCH_MGMT_SERVICE)
@Transactional(propagation = Propagation.REQUIRED)
public class TDMNonStandSearchServiceImpl extends TdmBaseServiceImpl implements
		TDMNonStandSearchService
{

	private static final Logger logger = LoggerFactory
			.getLogger(TDMNonStandSearchServiceImpl.class);

	@Autowired
	TDMNonStandSearchDao tDMNonStandSearchDAO;

	@Autowired
	TDMAdminDAO tDMAdminDAO;
	@Autowired
	TDMNonStandardSearchMapper tdmNonStandSrchmapr;

	@Resource(name = TDMConstants.EMAIL_NOTIFICATION_SERVICE)
	EmailNotificationService emailNotificationService;

	@Override
	public TDMNonStandSearchFieldsDTO getSearchFields() throws ServiceException
	{
		EntityManager managerLookup = openUserEntityManager();
		try
		{
			TDMNonStandSearchFieldsDTO nonStandSrchFldsDTO = new TDMNonStandSearchFieldsDTO();
			List<TdmLookupDO> nonStandFields = tDMNonStandSearchDAO
					.getTDMSearchFields(managerLookup);
			devideSearchFileds(nonStandFields, nonStandSrchFldsDTO);
			return nonStandSrchFldsDTO;
		}
		catch (NullPointerException de)
		{
			logger.error(TDMConstants.TDMP_SERVICE_ERROR_19, de);
			throw new ServiceException(TDMExceptionCode.NULL_POINTR, de);
		}
		catch (DAOException de)
		{
			logger.error(TDMConstants.TDMP_SERVICE_ERROR_20, de);
			throw new ServiceException(de.getMessage());
		}
		finally
		{
			closeUserEntityManager(managerLookup);
		}
	}

	@Override
	public TDMNonStandardSearchDTO getNonStandSearchRecords(TDMNonStandardSearchDTO tdmSearchDTO,
			int offSet, int recordsperpage) throws ServiceException
	{
		EntityManager managerUsr = openUserEntityManager();
		EntityManager subscriberManager = openSubscriberEntityManager();
		try
		{
			List<TdmReservationDO> reservationList = tDMNonStandSearchDAO
					.getReservedRecords(managerUsr);
			List<String> subIds = null;
			if (reservationList != null && reservationList.size() > 0)
			{
				subIds = new ArrayList<String>();
				for (TdmReservationDO resDo : reservationList)
				{
					subIds.add(resDo.getSubscrId());
				}
				tdmSearchDTO.setSubscriberIds(subIds);
			}

			/*
			 * if (tdmSearchDTO.getMemCat().equalsIgnoreCase("BLENDED")) {
			 * List<TdmBlendedMemberDO> blendedList = tDMNonStandSearchDAO
			 * .getBlendedRecords(subscriberManager); List<Integer> blendPtyIds
			 * = null; if (blendedList != null && blendedList.size() > 0) {
			 * blendPtyIds = new ArrayList<Integer>(); for (TdmBlendedMemberDO
			 * rblendDo : blendedList) {
			 * blendPtyIds.add(rblendDo.getBlndPtyId()); }
			 * tdmSearchDTO.setBlendedPtyIDs(blendPtyIds); } }
			 */

			List<Object[]> subsrcDo = tDMNonStandSearchDAO.getTDMNonStandardSearchRecords(
					tdmSearchDTO, offSet, recordsperpage, subscriberManager);
			List<TdmNonStandardSearchResultListDTO> tdmNonStandSearchDTOList = tdmNonStandSrchmapr
					.converTdmSubscrbrDtlsDOtoTDMNonStandardSearchDTO(tdmSearchDTO, subsrcDo);

			tdmSearchDTO.setTdmNonStandardSrchResultListDTOs(tdmNonStandSearchDTOList);
			return tdmSearchDTO;
		}
		catch (NullPointerException de)
		{
			logger.error(TDMConstants.TDMP_SERVICE_ERROR_21, de);
			throw new ServiceException(TDMExceptionCode.NULL_POINTR, de);
		}
		catch (DAOException de)
		{
			logger.error(TDMConstants.TDMP_SERVICE_ERROR_22, de);
			throw new ServiceException(de.getMessage());
		}
		finally
		{
			closeSubscriberEntityManager(subscriberManager);
			closeUserEntityManager(managerUsr);
		}
	}

	public Map<String, List<DependentDetailsDTO>> parseDependentDetails(
			List<DependentDetailsDTO> depenDtlsDtoLIst)
	{

		Map<String, List<DependentDetailsDTO>> depenMap = null;
		if (depenDtlsDtoLIst != null && depenDtlsDtoLIst.size() > 0)
		{
			depenMap = new HashMap<String, List<DependentDetailsDTO>>();

			for (DependentDetailsDTO depenDTo : depenDtlsDtoLIst)
			{
				List<DependentDetailsDTO> depenList = new ArrayList<DependentDetailsDTO>();
				if (depenMap.containsKey(depenDTo.getSubId()))
				{
					depenList = depenMap.get(depenDTo.getSubId());
					depenList.add(depenDTo);
					depenMap.put(depenDTo.getSubId(), depenList);
				}
				else
				{
					depenList.add(depenDTo);
					depenMap.put(depenDTo.getSubId(), depenList);
				}
			}
		}
		return depenMap;
	}

	@Override
	public int saveReservedData(TDMNonStandardSearchDTO tdmNonSrchDTO, String userId)
			throws ServiceException
	{
		EntityManager usrManager = openUserEntityManager();
		try
		{
			List<TdmReservationDO> tdmResrvationDoList = tdmNonStandSrchmapr
					.converTDMNonStandardSearchDTOtoTdmReservationDO(tdmNonSrchDTO, userId);
			tDMNonStandSearchDAO.saveReserveData(tdmResrvationDoList,
					tdmNonSrchDTO.getTestCaseName(), tdmNonSrchDTO.getTestCaseId(), usrManager);
			return tdmResrvationDoList.size();
		}
		catch (NullPointerException ex)
		{
			ex.printStackTrace();
			throw new ServiceException(TDMExceptionCode.NULL_POINTR, ex);
		}
		catch (DAOException de)
		{
			de.printStackTrace();
			logger.error(TDMConstants.TDMP_SERVICE_ERROR_23, de);
			throw new ServiceException(de.getMessage());
		}
		finally
		{
			closeUserEntityManager(usrManager);
		}
	}

	@Override
	public List<TDMNonStandReservationDTO> getReservedRecords(String role, String userId,
			int offSet, int recPerPage) throws ServiceException
	{
		EntityManager managerUsr = openUserEntityManager();
		try
		{
			List<TdmReservationDO> reservedList = tDMNonStandSearchDAO.getReservedRecords(role,
					userId, offSet, recPerPage, managerUsr);
			List<TDMNonStandReservationDTO> tdmReservedList = tdmNonStandSrchmapr
					.convertReservationDosToResvationDTO(reservedList);
			return tdmReservedList;
		}
		catch (NullPointerException ex)
		{
			ex.printStackTrace();
			throw new ServiceException(TDMExceptionCode.NULL_POINTR, ex);
		}
		catch (DAOException de)
		{
			logger.error(TDMConstants.TDMP_SERVICE_ERROR_24, de);
			throw new ServiceException(de.getMessage());
		}
		finally
		{
			closeUserEntityManager(managerUsr);
		}
	}

	@Override
	public int unReserveReservedRecords(TDMNonStandardSearchDTO tdmNonStandUnreserve, String userId)
			throws ServiceException
	{
		EntityManager managerUsr = openUserEntityManager();
		try
		{
			List<TdmReservationDO> unreservedList = tdmNonStandSrchmapr
					.convertReservationDTOsToResvationDO(tdmNonStandUnreserve);
			tDMNonStandSearchDAO.unReserveResrvedRecords(unreservedList, userId, managerUsr);
			return unreservedList.size();
		}
		catch (NullPointerException ex)
		{
			throw new ServiceException(TDMExceptionCode.NULL_POINTR, ex);
		}
		catch (DAOException de)
		{
			throw new ServiceException(de.getMessage());
		}
		finally
		{
			closeUserEntityManager(managerUsr);
		}
	}

	public void devideSearchFileds(List<TdmLookupDO> nonStandFields,
			TDMNonStandSearchFieldsDTO nonStandSrchFldsDTO)
	{
		Set<String> planType = new TreeSet<String>();
		Set<String> claimType = new TreeSet<String>();
		Set<String> memCatList = new TreeSet<String>();
		Set<String> coverageType = new TreeSet<String>();
		Set<String> memStatus = new TreeSet<String>();
		Set<String> stateList = new TreeSet<String>();
		Set<String> subscRelation = new TreeSet<String>();
		Set<String> ageGroup = new TreeSet<String>();
		Set<String> fundingInd = new TreeSet<String>();
		Set<String> pbms = new TreeSet<String>();

		for (TdmLookupDO tdmLookup : nonStandFields)
		{
			if (tdmLookup.getListValName().equalsIgnoreCase(TDMConstants.PLAN_TYPE))
			{
				planType.add(tdmLookup.getListValue());
			}
			else if (tdmLookup.getListValName().equalsIgnoreCase(TDMConstants.CLAIM_TYPE))
			{
				claimType.add(tdmLookup.getListValue());
			}
			else if (tdmLookup.getListValName().equalsIgnoreCase(TDMConstants.MEM_CATEGORY))
			{
				memCatList.add(tdmLookup.getListValue());
			}
			else if (tdmLookup.getListValName().equalsIgnoreCase(TDMConstants.COVERAGE_TYPE))
			{
				coverageType.add(tdmLookup.getListValue());
			}
			else if (tdmLookup.getListValName().equalsIgnoreCase(TDMConstants.MEM_STATUS))
			{
				memStatus.add(tdmLookup.getListValue());
			}
			else if (tdmLookup.getListValName().equalsIgnoreCase(TDMConstants.STATE))
			{
				stateList.add(tdmLookup.getListValue());
			}
			else if (tdmLookup.getListValName().equalsIgnoreCase(TDMConstants.SUBSCR_RELATION))
			{
				subscRelation.add(tdmLookup.getListValue());
			}
			else if (tdmLookup.getListValName().equalsIgnoreCase(TDMConstants.AGE_GROUP))
			{
				ageGroup.add(tdmLookup.getListValue());
			}
			else if (tdmLookup.getListValName().equalsIgnoreCase(TDMConstants.FUNDING_IND))
			{
				fundingInd.add(tdmLookup.getListValue());
			}
			else if (tdmLookup.getListValName().equalsIgnoreCase(TDMConstants.PBM))
			{
				pbms.add(tdmLookup.getListValue());
			}
		}

		nonStandSrchFldsDTO.setClaimTypes(claimType);
		nonStandSrchFldsDTO.setAgeGroups(ageGroup);
		nonStandSrchFldsDTO.setCoverageTypes(coverageType);
		nonStandSrchFldsDTO.setMemCatagories(memCatList);
		nonStandSrchFldsDTO.setMemStatus(memStatus);
		nonStandSrchFldsDTO.setPlanTypes(planType);
		nonStandSrchFldsDTO.setStateTypes(stateList);
		nonStandSrchFldsDTO.setSubscRelations(subscRelation);
		nonStandSrchFldsDTO.setFundingInd(fundingInd);
		nonStandSrchFldsDTO.setPbms(pbms);
	}

	@Override
	public List<String> getAccountNameNumberList(String type, String reqToken)
			throws ServiceException
	{

		EntityManager subscriberManager = openSubscriberEntityManager();
		try
		{
			List<String> resultList = tDMNonStandSearchDAO.getSubscriberAcNameNumDetails(type,
					reqToken, subscriberManager);
			if (type.equalsIgnoreCase(TDMConstants.AC_NUM)
					|| type.equalsIgnoreCase(TDMConstants.SUBSC_ID))
			{
				resultList = convertAcNumToList(resultList);
			}
			return resultList;
		}
		catch (NullPointerException de)
		{
			logger.error(TDMConstants.TDMP_SERVICE_ERROR_21, de);
			throw new ServiceException(TDMExceptionCode.NULL_POINTR, de);
		}
		catch (DAOException de)
		{
			logger.error(TDMConstants.TDMP_SERVICE_ERROR_22, de);
			throw new ServiceException(de.getMessage());
		}
		finally
		{
			closeSubscriberEntityManager(subscriberManager);
		}
	}

	@Override
	public long getNonStandardSearchRecordCount(TDMNonStandardSearchDTO tdmNonStandSearchDTO,
			String userId) throws ServiceException

	{
		EntityManager subscriberManager = openSubscriberEntityManager();
		try
		{
			Long count = tDMNonStandSearchDAO.getTDMNonStandardSearchRecordCount(
					tdmNonStandSearchDTO, subscriberManager);
			return count;
		}
		catch (NullPointerException de)
		{
			logger.error(TDMConstants.TDMP_SERVICE_ERROR_21, de);
			throw new ServiceException(TDMExceptionCode.NULL_POINTR, de);
		}
		catch (DAOException de)
		{
			logger.error(TDMConstants.TDMP_SERVICE_ERROR_22, de);
			throw new ServiceException(de.getMessage());
		}
		finally
		{
			closeSubscriberEntityManager(subscriberManager);
		}
	}

	@Override
	public Boolean forgotPassword(final ForgotPassword forgotPasswordDTO) throws ServiceException
	{
		logger.info(MessageConstants.TDM_FTD_SERVICE + MessageConstants.TDM_FTD_SER_FORGOT_PASS1
				+ MessageConstants.LOG_INFO_PARAMS_NO);
		EntityManager managerUser = openUserEntityManager();
		try
		{
			TdmUserDO userDO = tDMNonStandSearchDAO.checkAvailabilityOfUserId(
					forgotPasswordDTO.getUserId(), managerUser);
			if (null != userDO && null != userDO.getPassword() && null != userDO.getEmailId())
			{
				forgotPasswordDTO.setPassword(userDO.getPassword());
				forgotPasswordDTO.setUserName(userDO.getUsername());
				forgotPasswordDTO.setEmailId(userDO.getEmailId());
				emailNotificationService.sendEmailNotification(forgotPasswordDTO);
				logger.info(MessageConstants.TDM_FTD_SERVICE
						+ MessageConstants.TDM_FTD_SER_FORGOT_PASS1
						+ MessageConstants.LOG_INFO_RETURN);
				return true;
			}
			return false;
		}
		catch (NullPointerException nullPointerEx)
		{
			nullPointerEx.printStackTrace();
			logger.error(MessageConstants.TDM_FTD_SERVICE
					+ MessageConstants.TDM_FTD_SER_FORGOT_PASS1
					+ MessageConstants.LOG_ERROR_EXCEPTION);
			throw new ServiceException(MessageConstants.NULL_POINTER_EXCEPTION, nullPointerEx);
		}
		catch (DAOException daoEx)
		{
			logger.error(MessageConstants.TDM_FTD_SERVICE
					+ MessageConstants.TDM_FTD_SER_FORGOT_PASS1
					+ MessageConstants.LOG_ERROR_EXCEPTION);
			throw new ServiceException(daoEx.getErrorCode(), daoEx);
		}
		catch (Exception otherEx)
		{
			logger.error(MessageConstants.TDM_FTD_SERVICE
					+ MessageConstants.TDM_FTD_SER_FORGOT_PASS1
					+ MessageConstants.LOG_ERROR_EXCEPTION);
			throw new ServiceException(MessageConstants.SERVICE_EXCEPTION, otherEx);
		}
		finally
		{
			closeUserEntityManager(managerUser);
		}
	}

	public List<String> convertAcNumToList(List<String> resultList)
	{
		List<String> finalList = new ArrayList<String>();

		for (Object obj : resultList)
		{
			finalList.add(String.valueOf(obj));
		}

		return finalList;
	}

	@Override
	public Boolean l1l2SupportNS(AutoEmailDTO autoEmailDto) throws ServiceException
	{

		EntityManager managerUser = openUserEntityManager();
		try
		{
			emailNotificationService.sendEmailNotificationL1L2Support(autoEmailDto);
			logger.info(MessageConstants.TDM_FTD_SERVICE
					+ MessageConstants.TDM_FTD_SER_FORGOT_PASS1 + MessageConstants.LOG_INFO_RETURN);
			return true;
		}
		catch (NullPointerException nullPointerEx)
		{
			logger.error(MessageConstants.TDM_FTD_SERVICE
					+ MessageConstants.TDM_FTD_SER_FORGOT_PASS1
					+ MessageConstants.LOG_ERROR_EXCEPTION);
			throw new ServiceException(MessageConstants.NULL_POINTER_EXCEPTION, nullPointerEx);
		}
		catch (Exception otherEx)
		{
			logger.error(MessageConstants.TDM_FTD_SERVICE
					+ MessageConstants.TDM_FTD_SER_FORGOT_PASS1
					+ MessageConstants.LOG_ERROR_EXCEPTION);
			throw new ServiceException(MessageConstants.SERVICE_EXCEPTION, otherEx);
		}
		finally
		{
			closeUserEntityManager(managerUser);
		}
	}

	@Override
	public long getReservedRecordCount(String role, String userId) throws ServiceException
	{
		EntityManager managerUsr = openUserEntityManager();
		try
		{
			long count = tDMNonStandSearchDAO.getReservedRecordsCount(role, userId, managerUsr);
			return count;
		}
		catch (NullPointerException ex)
		{
			throw new ServiceException(TDMExceptionCode.NULL_POINTR, ex);
		}
		catch (DAOException de)
		{
			throw new ServiceException(de.getMessage());
		}
		finally
		{
			closeUserEntityManager(managerUsr);
		}
	}

	@Override
	public List<DependentDetailsDTO> getDependentDetails(String subId) throws ServiceException
	{
		EntityManager subscriberManager = openSubscriberEntityManager();
		try
		{
			List<Object[]> dependentDetailsDo = tDMNonStandSearchDAO.getDependentDetails(subId,
					subscriberManager);
			List<DependentDetailsDTO> depenDtlsDtoLIst = tdmNonStandSrchmapr
					.convertDependentDetailsDoToDtos(dependentDetailsDo);
			return depenDtlsDtoLIst;
		}
		catch (NullPointerException ex)
		{
			throw new ServiceException(TDMExceptionCode.NULL_POINTR, ex);
		}
		catch (DAOException de)
		{
			throw new ServiceException(de.getMessage());
		}
		finally
		{
			closeSubscriberEntityManager(subscriberManager);
		}
	}

	@Override
	public AutoEmailDTO getUserDetails(AutoEmailDTO autoEmailDto, String userId)
			throws ServiceException
	{
		try
		{
			TdmUserDO usrDo = tDMAdminDAO.getEditUser(userId);
			autoEmailDto.setCc(usrDo.getEmailId());
			autoEmailDto.setUserId(usrDo.getUsername());
			return autoEmailDto;
		}
		catch (NullPointerException ex)
		{
			throw new ServiceException(TDMExceptionCode.NULL_POINTR, ex);
		}
		catch (DAOException de)
		{
			throw new ServiceException(de.getMessage());
		}

	}

	@Override
	public TDMNonStandardSearchDTO getDependentDetailsToExport(TDMNonStandardSearchDTO tdmSearchDTO)
			throws ServiceException
	{
		EntityManager subscriberManager = openSubscriberEntityManager();

		try
		{
			List<String> subIds = new ArrayList<String>();
			List<String> memIds = new ArrayList<String>();
			for (TdmNonStandardSearchResultListDTO dto : tdmSearchDTO
					.getTdmNonStandardSrchResultListDTOs())
			{
				subIds.add(dto.getSubscId());
				memIds.add(dto.getMemId());
			}
			tdmSearchDTO.setSubscriberIds(subIds);
			tdmSearchDTO.setMembrIds(memIds);
			List<Object[]> dependentDetailsDo = tDMNonStandSearchDAO.getDependentDetails(
					tdmSearchDTO, subscriberManager);
			List<DependentDetailsDTO> depenDtlsDtoLIst = tdmNonStandSrchmapr
					.convertDependentDetailsDoToDtos(dependentDetailsDo);
			tdmSearchDTO.setTdmDependentDetails(parseDependentDetails(depenDtlsDtoLIst));
			return tdmSearchDTO;
		}
		catch (NullPointerException de)
		{
			logger.error(TDMConstants.TDMP_SERVICE_ERROR_21, de);
			throw new ServiceException(TDMExceptionCode.NULL_POINTR, de);
		}
		catch (DAOException de)
		{
			logger.error(TDMConstants.TDMP_SERVICE_ERROR_22, de);
			throw new ServiceException(de.getMessage());
		}
		finally
		{
			closeSubscriberEntityManager(subscriberManager);
		}
	}

	@Override
	public void saveSearchDetails(TDMNonStandardSearchDTO tdmNonStandSearchDTO, String userId) throws ServiceException
	{
		EntityManager managerUsr = openUserEntityManager();
		SimpleDateFormat format = new SimpleDateFormat(TDMConstants.MM_DD_YYYY);
		try
		{
			String acName = tdmNonStandSearchDTO.getAccountName();
			String acNum = tdmNonStandSearchDTO.getAccountNum();
			String subscrbrId = tdmNonStandSearchDTO.getSubscId();
			TDMSearchDetailsDO searchDtlsDO =new TDMSearchDetailsDO();
			if(StringUtils.isNotEmpty(subscrbrId) && StringUtils.isNotEmpty(subscrbrId
					.trim()))
			{
				searchDtlsDO.setSubscriberId(subscrbrId);
			}
			else if( (StringUtils.isNotEmpty(acName) && StringUtils.isNotEmpty(acName.trim()))
					|| (StringUtils.isNotEmpty(acNum) && StringUtils.isNotEmpty(acNum.trim())))
			{
				if(StringUtils.isNotEmpty(acNum) && StringUtils.isNotEmpty(acNum.trim()))
				{
					searchDtlsDO.setAccountNum(acNum);
				}
				if(StringUtils.isNotEmpty(acName) && StringUtils.isNotEmpty(acName.trim()))
				{
					searchDtlsDO.setAccountName(acName);
				}			
			}
			else
			{
				searchDtlsDO.setAge(tdmNonStandSearchDTO.getAgeGroup());
				searchDtlsDO.setCorpId(tdmNonStandSearchDTO.getProvState());
				searchDtlsDO.setCoverage(tdmNonStandSearchDTO.getCoverageCode());
				searchDtlsDO.setExchangeType(tdmNonStandSearchDTO.getRetailOnOff());
				searchDtlsDO.setExistingClaims(tdmNonStandSearchDTO.getExtClaim());
				searchDtlsDO.setFundInd(tdmNonStandSearchDTO.getFundingInd());
				searchDtlsDO.setMemberCat(tdmNonStandSearchDTO.getMemCat());
				searchDtlsDO.setMemberType(tdmNonStandSearchDTO.getSubscRelation());
				searchDtlsDO.setProductType(tdmNonStandSearchDTO.getPlanType());
				if( StringUtils.isNotEmpty(tdmNonStandSearchDTO.getOriginalEffDate()))
				{
					searchDtlsDO.setOriginalEffDate(format.parse(tdmNonStandSearchDTO.getOriginalEffDate()));
				}
				
				searchDtlsDO.setSubscrbrStatus(tdmNonStandSearchDTO.getSubscStatus());			
			}
			
			 format = new SimpleDateFormat(TDMConstants.MMDDYYYY_HH_MM_SS);
			searchDtlsDO.setUserId(userId);
			searchDtlsDO.setSearchTime(new Timestamp(format.parse(format.format(new Date())).getTime()));
			searchDtlsDO.setProjectID(tdmNonStandSearchDTO.getApplicationId());
		    tDMNonStandSearchDAO.saveSearchDetails(searchDtlsDO, managerUsr);
		}
		catch (NullPointerException ex)
		{
			throw new ServiceException(TDMExceptionCode.NULL_POINTR, ex);
		}
		catch (DAOException de)
		{
			throw new ServiceException(de.getMessage());
		}
		catch (Exception de)
		{
			throw new ServiceException(de.getMessage());
		}
		finally
		{
			closeUserEntityManager(managerUsr);
		}

	}
}
