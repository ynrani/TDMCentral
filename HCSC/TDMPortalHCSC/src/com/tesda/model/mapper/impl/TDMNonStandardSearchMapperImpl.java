/*---------------------------------------------------------------------------------------
 * Object Name: TDMNonStandardSearchMapperImpl.Java
 * --------------------------------------------------------------------------------------
 *
 * Copyright: 2015 Capgemini Financial Services
 *---------------------------------------------------------------------------------------*/

package com.tesda.model.mapper.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.tesda.constants.TDMConstants;
import com.tesda.model.DO.TdmMcgDetailsDO;
import com.tesda.model.DO.TdmReservationDO;
import com.tesda.model.DO.TdmSubscriberDetailsDO;
import com.tesda.model.DTO.DependentDetailsDTO;
import com.tesda.model.DTO.TDMNonStandReservationDTO;
import com.tesda.model.DTO.TDMNonStandardSearchDTO;
import com.tesda.model.DTO.TdmNonStandardSearchResultListDTO;
import com.tesda.model.mapper.TDMNonStandardSearchMapper;

/*
 * Involves in Find Test Data, Reserve/UnReserve. 
 * Mapper class which is used to convert the DTO objects to corresponding DO objects and
 * vice versa.
 * DTO objects are used to display data on UI where as Do objects are used to persist the
 * data in data base.
 */

@Component
@Service("tdmNonStandSearchMapper")
public class TDMNonStandardSearchMapperImpl implements TDMNonStandardSearchMapper
{

	/**
	 * 
	 */
	@Override
	public List<TdmNonStandardSearchResultListDTO> converTdmSbscrDtlsDOtoTDMNonStandardSearchDTO(
			TDMNonStandardSearchDTO tdmSearchDTO, List<TdmSubscriberDetailsDO> tdmSubscDetailsDo)
	{
		SimpleDateFormat format = new SimpleDateFormat(TDMConstants.MM_DD_YYYY);
		TdmNonStandardSearchResultListDTO tdmNonStandSearchDTO = null;
		List<TdmNonStandardSearchResultListDTO> tdmNonStandSearchDTOList = null;
		if (tdmSubscDetailsDo != null && 0 < tdmSubscDetailsDo.size())
		{
			int count = 0;
			tdmNonStandSearchDTOList = new ArrayList<TdmNonStandardSearchResultListDTO>();
			for (TdmSubscriberDetailsDO tdmSubscrDtlsDO : tdmSubscDetailsDo)
			{
				tdmNonStandSearchDTO = new TdmNonStandardSearchResultListDTO();
				tdmNonStandSearchDTO.setAgeGroup(String.valueOf(tdmSubscrDtlsDO.getAge()));
				tdmNonStandSearchDTO.setMemId(String.valueOf(tdmSubscrDtlsDO.getMembrId()));
				tdmNonStandSearchDTO.setAccountName(tdmSubscrDtlsDO.getAccountName());
				tdmNonStandSearchDTO.setAcNum(String.valueOf(tdmSubscrDtlsDO.getAccountNum()));
				tdmNonStandSearchDTO.setMemType(tdmSubscrDtlsDO.getMembrCat());
				tdmNonStandSearchDTO.setFirstName(tdmSubscrDtlsDO.getFirstName());
				tdmNonStandSearchDTO.setLastName(tdmSubscrDtlsDO.getLastName());
				tdmNonStandSearchDTO.setDob(format.format(tdmSubscrDtlsDO.getDob()));
				tdmNonStandSearchDTO.setHomeZipCode(tdmSubscrDtlsDO.getZipCode());
				if (tdmSubscrDtlsDO.getMcgDetails() != null
						&& tdmSubscrDtlsDO.getMcgDetails().size() > 0)
				{
					Set<String> coverages = new HashSet<String>();
					List<TdmMcgDetailsDO> mcgDtails = tdmSubscrDtlsDO.getMcgDetails();
					String[] subCovers = tdmSearchDTO.getCoverageCode().split(",");
					for (String coverage : subCovers)
					{
						if ((mcgDtails.get(count).getDentalCovgInd() != null && !mcgDtails
								.get(count).getDentalCovgInd().trim()
								.equalsIgnoreCase(TDMConstants.NULL))
								&& (coverage.equalsIgnoreCase(TDMConstants.DENTAL))
								|| coverage.equalsIgnoreCase(TDMConstants.ANY))
						{
							coverages.add(mcgDtails.get(count).getDentalCovgInd());
						}
						if ((mcgDtails.get(count).getMedicalCovgInd() != null && !mcgDtails
								.get(count).getMedicalCovgInd().trim()
								.equalsIgnoreCase(TDMConstants.NULL))
								&& (coverage.equalsIgnoreCase(TDMConstants.MEDICAL) || coverage
										.equalsIgnoreCase(TDMConstants.ANY)))
						{
							coverages.add(mcgDtails.get(count).getMedicalCovgInd());

						}
						if ((mcgDtails.get(count).getVisionCoverId() != null && !mcgDtails
								.get(count).getVisionCoverId().trim()
								.equalsIgnoreCase(TDMConstants.NULL))
								&& (coverage.equalsIgnoreCase(TDMConstants.VISION) || coverage
										.equalsIgnoreCase(TDMConstants.ANY)))
						{
							coverages.add(mcgDtails.get(count).getVisionCoverId());
						}
						if ((mcgDtails.get(count).getPrescCovgInd() != null && !mcgDtails
								.get(count).getPrescCovgInd().trim()
								.equalsIgnoreCase(TDMConstants.NULL))
								&& (coverage.equalsIgnoreCase(TDMConstants.RX) || coverage
										.equalsIgnoreCase(TDMConstants.ANY)))
						{
							coverages.add(mcgDtails.get(count).getPrescCovgInd());
						}

					}
					tdmNonStandSearchDTO.setGroupNum(mcgDtails.get(count).getGrpNbr());
					tdmNonStandSearchDTO.setMcgSecEffDate(mcgDtails.get(count)
							.getMembrshpCovrgGrpSectEffctDate().toString());
					tdmNonStandSearchDTO.setMcgSecEndDate(mcgDtails.get(count)
							.getMembrshpCovrgGrpSectEndDate().toString());
					tdmNonStandSearchDTO.setCoverageCode(coverages.toString().replaceAll(
							"\\[|\\]|null,", ""));
					if (tdmSearchDTO.getMemCat().equalsIgnoreCase(TDMConstants.RETAIL))
						tdmNonStandSearchDTO
								.setExchangeType(mcgDtails.get(count).getExchangeType());
				}

				tdmNonStandSearchDTO.setAcName(tdmSubscrDtlsDO.getAccountName());
				tdmNonStandSearchDTO.setMemEffDateGov(tdmSubscrDtlsDO.getMembrEffectiveDate()
						.toString());
				tdmNonStandSearchDTO.setMemEndDateGroup(tdmSubscrDtlsDO.getMembrEndDate()
						.toString());
				tdmNonStandSearchDTO.setOriginalEffDate(tdmSubscrDtlsDO.getMembrOrigEffDate()
						.toString());
				String gender = tdmSubscrDtlsDO.getGender();
				if (gender.equalsIgnoreCase("M"))
				{
					tdmNonStandSearchDTO.setGender(TDMConstants.MALE);
				}
				else
				{
					tdmNonStandSearchDTO.setGender(TDMConstants.FEMALE);
				}
				StringBuffer extClaim = new StringBuffer();
				String[] extClaims = tdmSearchDTO.getExtClaim().split(",");
				for (String extClm : extClaims)
				{
					if ((extClm != null && !extClm.trim().equalsIgnoreCase(TDMConstants.NULL))
							&& (extClm.equalsIgnoreCase(TDMConstants.DENTAL) || extClm
									.equalsIgnoreCase(TDMConstants.ANY)))
					{
						extClaim.append(tdmSubscrDtlsDO.getDentalClaimInd() + "  ");
					}

					if ((extClm != null && !extClm.trim().equalsIgnoreCase(TDMConstants.NULL))
							&& (extClm.equalsIgnoreCase(TDMConstants.MEDICAL) || extClm
									.equalsIgnoreCase(TDMConstants.ANY)))
					{
						extClaim.append(tdmSubscrDtlsDO.getMedClaimInd() + "  ");
					}
					if ((extClm != null && !extClm.trim().equalsIgnoreCase(TDMConstants.NULL))
							&& (extClm.equalsIgnoreCase(TDMConstants.PRESCRIPTION) || extClm
									.equalsIgnoreCase(TDMConstants.ANY)))
					{
						extClaim.append(tdmSubscrDtlsDO.getPrescClaimInd() + "  ");
					}
				}

				tdmNonStandSearchDTO.setExtClaim(extClaim.toString().replaceAll(TDMConstants.NULL,
						" "));
				tdmNonStandSearchDTO.setSubscId(String.valueOf(tdmSubscrDtlsDO.getSubscriberId()));
				if (tdmSearchDTO.getMemCat().equalsIgnoreCase(TDMConstants.BLENDED))
					tdmNonStandSearchDTO.setBlendGroup(tdmSubscrDtlsDO.getBlendDo()
							.getBlendedType());

				tdmNonStandSearchDTOList.add(tdmNonStandSearchDTO);
			}
		}
		return tdmNonStandSearchDTOList;
	}

	/**
	 * @param tdmNonSrchDTO
	 * @param userId
	 * @return
	 */
	@Override
	public List<TdmReservationDO> converTDMNonStandardSearchDTOtoTdmReservationDO(
			TDMNonStandardSearchDTO tdmNonSrchDTO, String userId)
	{
		SimpleDateFormat format = new SimpleDateFormat(TDMConstants.MM_DD_YYYY);
		List<TdmReservationDO> reservelist = null;
		TdmReservationDO tdmReserveDo = null;
		if ((null != tdmNonSrchDTO && null != tdmNonSrchDTO.getTdmNonStandardSrchResultListDTOs())
				&& 0 < tdmNonSrchDTO.getTdmNonStandardSrchResultListDTOs().size())
		{
			reservelist = new ArrayList<TdmReservationDO>();
			for (TdmNonStandardSearchResultListDTO dto : tdmNonSrchDTO
					.getTdmNonStandardSrchResultListDTOs())
			{
				if (dto.getReservedYN() != null)
				{
					tdmReserveDo = new TdmReservationDO();
					tdmReserveDo.setSubscrId(dto.getSubscId());
					tdmReserveDo.setAccountName(dto.getAcName());
					tdmReserveDo.setAccountNum(dto.getAcNum());
					tdmReserveDo.setBlendedCat(null);
					tdmReserveDo.setCoverage(dto.getCoverageCode());
					tdmReserveDo.setDob(dto.getDob());
					tdmReserveDo.setFirstName(dto.getFirstName());
					tdmReserveDo.setLastName(dto.getLastName());
					tdmReserveDo.setExtClaimType(null);
					tdmReserveDo.setLocked("Y");
					tdmReserveDo.setMembrType(dto.getMemType());
					tdmReserveDo.setHomeZipCode(dto.getHomeZipCode());
					tdmReserveDo.setUserId(userId);
					tdmReserveDo.setProjectId(tdmNonSrchDTO.getApplicationId());
					tdmReserveDo.setRecCreateDate(new Timestamp(new Date().getTime()));
					tdmReserveDo.setReserveDate(new Timestamp(new Date().getTime()));
					tdmReserveDo.setGroupNum(dto.getGroupNum());
					tdmReserveDo.setGender(dto.getGender());
					tdmReserveDo.setExtClaimType(dto.getExtClaim());
					tdmReserveDo.setAccountNum(dto.getAcNum());
					tdmReserveDo.setExchangeType(dto.getExchangeType());
					tdmReserveDo.setProductType(dto.getProductType());
					tdmReserveDo.setCorpId(dto.getProvState());
					tdmReserveDo.setFundingInd(dto.getFundingInd());
					tdmReserveDo.setPcpcpmgId(dto.getPcpMG());
					try
					{
						tdmReserveDo.setMemEffDt(format.parse(dto.getMemEffDateGov()));
						tdmReserveDo.setMemEndDt(format.parse(dto.getMemEndDateGroup()));
						tdmReserveDo.setMemOrginalEffDt(format.parse(dto.getOriginalEffDate()));
						tdmReserveDo.setMemShipCovGrpSecEffDt(format.parse(dto.getMcgSecEffDate()));
						tdmReserveDo.setMemShipCovGrpSecEndDt(format.parse(dto.getMcgSecEndDate()));
					}
					catch (ParseException e)
					{
						e.printStackTrace();
					}
					reservelist.add(tdmReserveDo);
				}
			}
		}
		return reservelist;
	}

	/**
	 * @param reservedList
	 * @return
	 */
	@Override
	public List<TDMNonStandReservationDTO> convertReservationDosToResvationDTO(
			List<TdmReservationDO> reservedList)
	{
		TDMNonStandReservationDTO tdmNonStandReservDTO = null;
		List<TDMNonStandReservationDTO> tdmNonStandSearchDTOList = null;
		if (reservedList != null && 0 < reservedList.size())
		{
			SimpleDateFormat format = new SimpleDateFormat(TDMConstants.MM_DD_YYYY);
			tdmNonStandSearchDTOList = new ArrayList<TDMNonStandReservationDTO>();
			for (TdmReservationDO tdmSubscrDtlsDO : reservedList)
			{
				tdmNonStandReservDTO = new TDMNonStandReservationDTO();
				tdmNonStandReservDTO.setAccountName(tdmSubscrDtlsDO.getAccountName());
				tdmNonStandReservDTO.setAccountNum(tdmSubscrDtlsDO.getAccountNum());
				tdmNonStandReservDTO.setBlendedCat(tdmSubscrDtlsDO.getBlendedCat());
				tdmNonStandReservDTO.setCoverage(tdmSubscrDtlsDO.getCoverage());
				tdmNonStandReservDTO.setDob(tdmSubscrDtlsDO.getDob());
				tdmNonStandReservDTO.setExtClaimType(tdmSubscrDtlsDO.getExtClaimType());
				tdmNonStandReservDTO.setFirstName(tdmSubscrDtlsDO.getFirstName());
				tdmNonStandReservDTO.setGroupNum(tdmSubscrDtlsDO.getGroupNum());
				tdmNonStandReservDTO.setHomeZipCode(tdmSubscrDtlsDO.getHomeZipCode());
				tdmNonStandReservDTO.setLastName(tdmSubscrDtlsDO.getLastName());
				tdmNonStandReservDTO.setMembrType(tdmSubscrDtlsDO.getMembrType());
				tdmNonStandReservDTO.setProjectId(tdmSubscrDtlsDO.getProjectId());
				tdmNonStandReservDTO.setRecCreateDate(format.format(tdmSubscrDtlsDO
						.getRecCreateDate()));
				if (tdmSubscrDtlsDO.getReserveDate() != null)
				{
					tdmNonStandReservDTO.setReserveDate(format.format(tdmSubscrDtlsDO
							.getReserveDate()));
					/*
					 * Calculate the UnReserve date i.e 15 days from reserved
					 * date.
					 */
					Calendar cal = Calendar.getInstance();
					cal.setTime(tdmSubscrDtlsDO.getReserveDate());
					cal.add(Calendar.DATE, 15);
					tdmNonStandReservDTO.setUnreservDate(format.format(cal.getTime()));
				}
				tdmNonStandReservDTO.setSubscrId(tdmSubscrDtlsDO.getSubscrId());
				tdmNonStandReservDTO.setTestCaseId(tdmSubscrDtlsDO.getTestCaseId());
				tdmNonStandReservDTO.setTestCaseName(tdmSubscrDtlsDO.getTestCaseName());
				tdmNonStandReservDTO.setGroupNum(tdmSubscrDtlsDO.getGroupNum());
				tdmNonStandReservDTO.setAccountNum(tdmSubscrDtlsDO.getAccountNum());
				tdmNonStandReservDTO.setExchangeType(tdmSubscrDtlsDO.getExchangeType());
				tdmNonStandReservDTO.setMemEffDate(format.format(tdmSubscrDtlsDO.getMemEffDt()));
				tdmNonStandReservDTO.setMemEndDate(format.format(tdmSubscrDtlsDO.getMemEndDt()));
				tdmNonStandReservDTO.setMcgSecEffDate(format.format(tdmSubscrDtlsDO
						.getMemShipCovGrpSecEffDt()));
				tdmNonStandReservDTO.setMcgSecEndDate(format.format(tdmSubscrDtlsDO
						.getMemShipCovGrpSecEndDt()));
				tdmNonStandReservDTO.setOriginalEffDate(format.format(tdmSubscrDtlsDO
						.getMemOrginalEffDt()));
				tdmNonStandReservDTO.setGender(tdmSubscrDtlsDO.getGender());
				tdmNonStandReservDTO.setReservedBy(tdmSubscrDtlsDO.getUserId());
				tdmNonStandReservDTO.setProductType(tdmSubscrDtlsDO.getProductType());
				tdmNonStandReservDTO.setCorpId(tdmSubscrDtlsDO.getCorpId());
				tdmNonStandReservDTO.setFundingInd(tdmSubscrDtlsDO.getFundingInd());
				tdmNonStandReservDTO.setPcpMG(tdmSubscrDtlsDO.getPcpcpmgId());
				tdmNonStandSearchDTOList.add(tdmNonStandReservDTO);
			}
		}
		return tdmNonStandSearchDTOList;
	}

	/**
	 * @param tdmNonStandSearchDTO
	 * @return
	 */
	@Override
	public List<TdmReservationDO> convertReservationDTOsToResvationDO(
			TDMNonStandardSearchDTO tdmNonStandSearchDTO)
	{
		List<TDMNonStandReservationDTO> tdmNonStandResrvationDTOList = null;
		List<TdmReservationDO> tdmResrvationDos = null;
		if (null != tdmNonStandSearchDTO
				&& tdmNonStandSearchDTO.getTdmNonStandReservationDtos() != null)
		{
			TdmReservationDO tdmReserveDo = null;
			tdmNonStandResrvationDTOList = new ArrayList<TDMNonStandReservationDTO>();
			tdmResrvationDos = new ArrayList<TdmReservationDO>();
			for (TDMNonStandReservationDTO tmdReservDTO : tdmNonStandSearchDTO
					.getTdmNonStandReservationDtos())
			{
				if (tmdReservDTO.getUnreserveYN() != null)
				{
					tdmReserveDo = new TdmReservationDO();
					tdmReserveDo.setSubscrId(tmdReservDTO.getSubscrId());
					tdmReserveDo.setAccountName(tmdReservDTO.getAccountName());
					tdmReserveDo.setAccountNum(tmdReservDTO.getAccountNum());
					tdmReserveDo.setBlendedCat(tmdReservDTO.getBlendedCat());
					tdmReserveDo.setCoverage(tmdReservDTO.getCoverage());
					tdmReserveDo.setDob(tmdReservDTO.getDob());
					tdmReserveDo.setFirstName(tmdReservDTO.getFirstName());
					tdmReserveDo.setLastName(tmdReservDTO.getLastName());
					tdmReserveDo.setExtClaimType(tmdReservDTO.getExtClaimType());
					tdmReserveDo.setLocked("N");
					tdmReserveDo.setMembrType(tmdReservDTO.getMembrType());
					tdmReserveDo.setHomeZipCode(tmdReservDTO.getHomeZipCode());
					tdmReserveDo.setProjectId(tmdReservDTO.getProjectId());
					tdmReserveDo.setUnreservDate(new Timestamp(new Date().getTime()));
					tdmResrvationDos.add(tdmReserveDo);
				}
				else
				{
					tdmNonStandResrvationDTOList.add(tmdReservDTO);
				}
			}
			tdmNonStandSearchDTO.setTdmNonStandReservationDtos(tdmNonStandResrvationDTOList);
		}
		return tdmResrvationDos;
	}

	/**
	 * @param tdmSearchDTO
	 * @param tdmSubscDetailsDo
	 * @return
	 */
	@Override
	public List<TdmNonStandardSearchResultListDTO> converTdmSubscrbrDtlsDOtoTDMNonStandardSearchDTO(
			TDMNonStandardSearchDTO tdmSearchDTO, List<Object[]> tdmSubscDetailsDo)
	{
		SimpleDateFormat format = new SimpleDateFormat(TDMConstants.MM_DD_YYYY);
		TdmNonStandardSearchResultListDTO tdmNonStandSearchDTO = null;
		List<TdmNonStandardSearchResultListDTO> tdmNonStandSearchDTOList = null;
		if (tdmSubscDetailsDo != null && 0 < tdmSubscDetailsDo.size())
		{
			/*
			 * tdmSubscDetailsDo Is a result set obtained from native query,
			 * please do not disturb the following sequence. It will show effect
			 * on result displayed in find test data. If want to modify, please
			 * check the select criteria in TDMNonStandSearchDAOImpl.java and
			 * method getTDMNonStandardSearchRecords and modify according to the
			 * criteria.
			 */

			tdmNonStandSearchDTOList = new ArrayList<TdmNonStandardSearchResultListDTO>();
			for (Object[] tdmSubscrDtlsDO : tdmSubscDetailsDo)
			{

				int i = 0;
				tdmNonStandSearchDTO = new TdmNonStandardSearchResultListDTO();

				Object obj;
				obj = tdmSubscrDtlsDO[i++];
				if (obj != null)
				{
					tdmNonStandSearchDTO.setSubscId(obj.toString());
				}
				obj = tdmSubscrDtlsDO[i++];
				if (obj != null)
					tdmNonStandSearchDTO.setMemType(obj.toString());
				obj = tdmSubscrDtlsDO[i++];
				if (obj != null)
					tdmNonStandSearchDTO.setFirstName(obj.toString());
				obj = tdmSubscrDtlsDO[i++];
				if (obj != null)
					tdmNonStandSearchDTO.setLastName(obj.toString());

				obj = tdmSubscrDtlsDO[i++];
				String gender = "";
				if (obj != null)
					gender = obj.toString();
				if (gender.equalsIgnoreCase("M"))
				{
					tdmNonStandSearchDTO.setGender(TDMConstants.MALE);
				}
				else
				{
					tdmNonStandSearchDTO.setGender(TDMConstants.FEMALE);
				}

				obj = tdmSubscrDtlsDO[i++];
				if (obj != null)
					tdmNonStandSearchDTO.setDob(format.format(obj));

				obj = tdmSubscrDtlsDO[i++];
				if (obj != null)
					tdmNonStandSearchDTO.setHomeZipCode(obj.toString());

				obj = tdmSubscrDtlsDO[i++];
				if (obj != null)
					tdmNonStandSearchDTO.setGroupNum(obj.toString());

				obj = tdmSubscrDtlsDO[i++];
				if (obj != null)
					tdmNonStandSearchDTO.setAcName(obj.toString());

				obj = tdmSubscrDtlsDO[i++];
				if (obj != null)
					tdmNonStandSearchDTO.setMemEffDateGov(format.format(obj));

				obj = tdmSubscrDtlsDO[i++];
				if (obj != null)
					tdmNonStandSearchDTO.setMemEndDateGroup(format.format(obj));

				obj = tdmSubscrDtlsDO[i++];
				if (obj != null)
					tdmNonStandSearchDTO.setMcgSecEffDate(format.format(obj));

				obj = tdmSubscrDtlsDO[i++];
				if (obj != null)
					tdmNonStandSearchDTO.setMcgSecEndDate(format.format(obj));

				obj = tdmSubscrDtlsDO[i++];
				if (obj != null)
					tdmNonStandSearchDTO.setOriginalEffDate(format.format(obj));

				obj = tdmSubscrDtlsDO[i++];
				if (obj != null)
					tdmNonStandSearchDTO.setExtClaim(obj.toString());

				obj = tdmSubscrDtlsDO[i++];
				if (obj != null)
					tdmNonStandSearchDTO.setCoverageCode(obj.toString());

				obj = tdmSubscrDtlsDO[i++];
				if (obj != null)
					tdmNonStandSearchDTO.setProvState(obj.toString());

				obj = tdmSubscrDtlsDO[i++];
				if (tdmSearchDTO.getMemCat().equalsIgnoreCase(TDMConstants.RETAIL)
						|| (StringUtils.isNotEmpty(tdmSearchDTO.getSubscId()))
						|| (StringUtils.isNotEmpty(tdmSearchDTO.getAccountNum()))
						|| (StringUtils.isNotEmpty(tdmSearchDTO.getAccountName())))
				{
					if (obj != null)
						tdmNonStandSearchDTO.setExchangeType(obj.toString());
				}

				obj = tdmSubscrDtlsDO[i++];
				if (obj != null)
					tdmNonStandSearchDTO.setBlendGroup(obj.toString());

				obj = tdmSubscrDtlsDO[i++];
				if (obj != null)
					tdmNonStandSearchDTO.setProductType(obj.toString());

				obj = tdmSubscrDtlsDO[i++];
				if (obj != null)
					tdmNonStandSearchDTO.setMemId(obj.toString());

				obj = tdmSubscrDtlsDO[i++];
				if (obj != null)
				{
					tdmNonStandSearchDTO.setFundingInd(obj.toString());
				}

				obj = tdmSubscrDtlsDO[i++];
				if (obj != null)
				{
					tdmNonStandSearchDTO.setPcpMG(obj.toString());
				}

				tdmNonStandSearchDTOList.add(tdmNonStandSearchDTO);
			}
		}
		return tdmNonStandSearchDTOList;
	}

	/**
	 * @param dependentDos
	 * @return
	 */
	@Override
	public List<DependentDetailsDTO> convertDependentDetailsDoToDtos(List<Object[]> dependentDos)
	{
		List<DependentDetailsDTO> dependentDetailsList = null;
		if (dependentDos != null && dependentDos.size() > 0)
		{
			SimpleDateFormat format = new SimpleDateFormat(TDMConstants.MM_DD_YYYY);
			dependentDetailsList = new ArrayList<DependentDetailsDTO>();
			for (Object[] depDo : dependentDos)
			{
				int i = 0;
				DependentDetailsDTO depDto = new DependentDetailsDTO();
				Object obj = depDo[i++];
				if (obj != null)
					depDto.setDepStatus(obj.toString());

				obj = depDo[i++];
				if (obj != null)
					depDto.setDeptEffDate(format.format(obj));

				obj = depDo[i++];
				if (obj != null)
					depDto.setDeptEndDate(format.format(obj));

				obj = depDo[i++];
				if (obj != null)
					depDto.setDob(format.format(obj));

				obj = depDo[i++];
				if (obj != null)
					depDto.setFirstName(obj.toString());

				obj = depDo[i++];
				if (obj != null)
					depDto.setLastName(obj.toString());
				obj = depDo[i++];
				if (obj != null)
				{
					String gender = obj.toString();
					if (gender.equalsIgnoreCase("F"))
					{
						depDto.setGender(TDMConstants.FEMALE);
					}
					else if (gender.equalsIgnoreCase("M"))
					{
						depDto.setGender(TDMConstants.MALE);
					}
				}
				obj = depDo[i++];
				if (obj != null)
					depDto.setMemCategory(obj.toString());

				obj = depDo[i++];
				if (obj != null)
					depDto.setRelationShip(obj.toString());

				obj = depDo[i++];
				if (obj != null)
					depDto.setState(obj.toString());

				obj = depDo[i++];
				if (obj != null)
					depDto.setSubPtyId(obj.toString());

				obj = depDo[i++];
				if (obj != null)
					depDto.setSubStatus(obj.toString());

				obj = depDo[i++];
				if (obj != null)
					depDto.setRelationShipCode(obj.toString());

				obj = depDo[i++];
				if (obj != null)
					depDto.setDeptPtyId(obj.toString());

				obj = depDo[i++];
				if (obj != null)
					depDto.setSubId(obj.toString());

				obj = depDo[i++];
				if (obj != null)
					depDto.setRelationShipName(obj.toString());

				obj = depDo[i++];
				if (obj != null)
					depDto.setZipCode(obj.toString());

				dependentDetailsList.add(depDto);
			}
		}
		return dependentDetailsList;
	}
}
