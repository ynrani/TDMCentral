/*---------------------------------------------------------------------------------------
 * Object Name: TDMDataMaskingMapperImpl.Java
 * --------------------------------------------------------------------------------------
 *
 * Copyright: 2015 Capgemini Financial Services
 *---------------------------------------------------------------------------------------*/

package com.tesda.model.mapper.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.tesda.constants.TDMConstants;
import com.tesda.constants.TDMExceptionCode;
import com.tesda.controller.LoginController;
import com.tesda.exception.ServiceException;
import com.tesda.model.DO.ReqChDO;
import com.tesda.model.DO.RequestorDO;
import com.tesda.model.DO.TdmOnboadReqNoTabDO;
import com.tesda.model.DO.TdmOnboardReqDO;
import com.tesda.model.DTO.TdmDataMaskingDTO;
import com.tesda.model.DTO.TdmDataMaskingNoOfAppsDTO;
import com.tesda.model.DTO.TdmOnBoardReqDTO;
import com.tesda.model.mapper.TDMDataMaskingMapper;

/**
 * Involves in Data masking and On boarding request related stuff. Mapper class
 * which is used to convert the DTO objects to corresponding DO objects and vice
 * versa. DTO objects are used to display data on UI where as Do objects are
 * used to persist the data in data base.
 */
@Service(TDMConstants.MAPPER_DMASK)
public class TDMDataMaskingMapperImpl implements TDMDataMaskingMapper
{
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Override
	public RequestorDO convertMaskDTOtoDO(RequestorDO requestorDO,
			TdmDataMaskingDTO tdgDataMaskingDTO, String seq, boolean page1, boolean page2,
			boolean page3, boolean reqTypeCR) throws ServiceException
	{
		try
		{
			if (null != tdgDataMaskingDTO)
			{
				if (null == requestorDO)
				{
					requestorDO = new RequestorDO();
				}
				if (page1)
				{

					SimpleDateFormat format = new SimpleDateFormat(TDMConstants.MM_DD_YYYY);
					if (StringUtils.isNotEmpty(tdgDataMaskingDTO.getId()))
					{
						if (tdgDataMaskingDTO.isReqTypeCR())
						{
							int crNo = tdgDataMaskingDTO.getVno() + 1;
							if (tdgDataMaskingDTO.getId().contains(TDMConstants.CR))
							{
								requestorDO.setId(tdgDataMaskingDTO.getId().replace(
										tdgDataMaskingDTO.getId().substring(
												tdgDataMaskingDTO.getId().indexOf(TDMConstants.C),
												tdgDataMaskingDTO.getId().length()),
										TDMConstants.CR + crNo));
								tdgDataMaskingDTO.setId(requestorDO.getId());
							}
							else
							{
								requestorDO.setId(tdgDataMaskingDTO.getId() + TDMConstants.CR
										+ (tdgDataMaskingDTO.getVno() + 1));
								tdgDataMaskingDTO.setId(requestorDO.getId());
							}
							requestorDO.setVno(tdgDataMaskingDTO.getVno() + 1);
						}
						else
						{
							requestorDO.setId(tdgDataMaskingDTO.getId());
							requestorDO.setVno(tdgDataMaskingDTO.getVno());
						}
					}
					else
					{
						requestorDO.setId(TDMConstants.MR + seq);
						requestorDO.setVno(0);
					}
					requestorDO.setStatus(TDMConstants.OPEN);
					requestorDO.setUserName(tdgDataMaskingDTO.getUserId());
					requestorDO.setName(tdgDataMaskingDTO.getUserName());
					requestorDO.setEmailid(tdgDataMaskingDTO.getEmailId());
					requestorDO.setPhoneno(tdgDataMaskingDTO.getPhoneNo());
					requestorDO.setDepartment(tdgDataMaskingDTO.getDeptName());
					if (tdgDataMaskingDTO.getNeededBy() != null
							&& StringUtils.isNotEmpty(tdgDataMaskingDTO.getNeededBy()))
					{
						requestorDO.setNeededby(format.parse(tdgDataMaskingDTO.getNeededBy()));
					}
					requestorDO.setProjectName(tdgDataMaskingDTO.getProjName());
					requestorDO.setProjectPhase(tdgDataMaskingDTO.getProjPhase());
					requestorDO.setProjectId(tdgDataMaskingDTO.getProjectId());
					requestorDO.setEnvironment(tdgDataMaskingDTO.getEnvType());
					requestorDO.setRequestTime(new Timestamp(new Date().getTime()));
					if (null != requestorDO.getReqChDOs()
							&& requestorDO.getId().contains(TDMConstants.CR))
					{
						for (int i = 0; i < requestorDO.getReqChDOs().size(); i++)
						{
							requestorDO.getReqChDOs().get(i).setPId(requestorDO.getId());
							requestorDO.getReqChDOs().get(i).setId(null);
						}
					}
				}
				if (page2)
				{
					requestorDO.setHlfd(tdgDataMaskingDTO.getPage2Q1());
					requestorDO.setDsmech(tdgDataMaskingDTO.getPage2Q2());
					requestorDO.setOdsmech(tdgDataMaskingDTO.getPage2Q3());
					requestorDO.setProdnonprodUpsteam(tdgDataMaskingDTO.getPage2Q4());
					requestorDO.setNonEnglishChar(tdgDataMaskingDTO.getPage2Q5());
					requestorDO.setDfdchart(tdgDataMaskingDTO.getPage2Q6());
					requestorDO.setOatpsource(tdgDataMaskingDTO.getPage2Q7());
					requestorDO.setDsADU(tdgDataMaskingDTO.getPage2Q8());
					requestorDO.setPiiMnpi(tdgDataMaskingDTO.getPage2Q9());
					requestorDO.setMaskNonProd(tdgDataMaskingDTO.getPage2Q10());
					requestorDO.setValMaskData(tdgDataMaskingDTO.getPage2Q11());
					requestorDO.setProdSensElem(tdgDataMaskingDTO.getPage2Q12());
					requestorDO.setRequestTime(new Timestamp(new Date().getTime()));
				}
				if (page3)
				{
					requestorDO.setNonEngLang(tdgDataMaskingDTO.getPage3Q1());
					requestorDO.setNoOfTable(tdgDataMaskingDTO.getPage3Q2());
					requestorDO.setCountDatabase(tdgDataMaskingDTO.getPage3Q3());
					requestorDO.setDsSchemaChnage(tdgDataMaskingDTO.getPage3Q4());
					requestorDO.setVolDataDataMask(tdgDataMaskingDTO.getPage3Q5());
					requestorDO.setPlaceMaskingStg(tdgDataMaskingDTO.getPage3Q6());
					requestorDO.setNonProdMask(tdgDataMaskingDTO.getPage3Q7());
					requestorDO.setDataMaskDev(tdgDataMaskingDTO.getPage3Q8());
					requestorDO.setDataMaskOngoingSupport(tdgDataMaskingDTO.getPage3Q9());
					requestorDO.setSlaDataMasking(tdgDataMaskingDTO.getPage3Q10());
					requestorDO.setRequestTime(new Timestamp(new Date().getTime()));
					requestorDO.setStatus(TDMConstants.STATUS_SUBMITTED);
					requestorDO.setNoOfApps(tdgDataMaskingDTO.getPage3NoOfApps());
					ReqChDO reqChDO = null;
					List<ReqChDO> reqChDOList = new ArrayList<ReqChDO>();
					for (TdmDataMaskingNoOfAppsDTO tdgDataMaskingNoOfAppsDTO : tdgDataMaskingDTO
							.getTdgDataMaskingNoOfAppsDTOs())
					{
						reqChDO = new ReqChDO();
						reqChDO.setRequestor(requestorDO);
						reqChDO.setPId(requestorDO.getId());
						reqChDO.setAppName(tdgDataMaskingNoOfAppsDTO.getAppName());
						reqChDO.setDbName(tdgDataMaskingNoOfAppsDTO.getDbName());
						reqChDO.setNoOfTable(tdgDataMaskingNoOfAppsDTO.getNoOfTables());
						if (StringUtils.isNotEmpty(tdgDataMaskingNoOfAppsDTO.getAppName()))
						{
							reqChDOList.add(reqChDO);
						}
					}
					requestorDO.setChngReqCmmt(tdgDataMaskingDTO.getChngReqCmmt());
					requestorDO.setReqChDOs(reqChDOList);
				}
			}
			return requestorDO;
		}
		catch (NullPointerException nullPointerEx)
		{
			throw new ServiceException(TDMExceptionCode.NULL_POINTR, nullPointerEx);
		}
		catch (Exception otherEx)
		{
			otherEx.printStackTrace();
			throw new ServiceException(TDMExceptionCode.EXCEPTION, otherEx);
		}
	}

	@Override
	public TdmDataMaskingDTO convertDOtoMaskDTO(TdmDataMaskingDTO tdgDataMaskingDTO,
			RequestorDO requestorDO, boolean page1, boolean page2, boolean page3)
			throws ServiceException
	{
		try
		{
			List<TdmDataMaskingNoOfAppsDTO> tdgDataMaskingNoOfAppsDTOList = null;
			TdmDataMaskingNoOfAppsDTO tdgDataMaskingNoOfAppsDTO = null;
			if (requestorDO != null)
			{
				if (page1)
				{
					SimpleDateFormat format = new SimpleDateFormat(TDMConstants.MM_DD_YYYY);
					tdgDataMaskingDTO.setId(requestorDO.getId());
					tdgDataMaskingDTO.setUserName(requestorDO.getName());
					tdgDataMaskingDTO.setUserId(requestorDO.getUserName());
					tdgDataMaskingDTO.setEmailId(requestorDO.getEmailid());
					tdgDataMaskingDTO.setPhoneNo(requestorDO.getPhoneno());
					tdgDataMaskingDTO.setDeptName(requestorDO.getDepartment());
					if (requestorDO.getNeededby() != null)
					{
						tdgDataMaskingDTO.setNeededBy(format.format(requestorDO.getNeededby()));
					}
					tdgDataMaskingDTO.setProjName(requestorDO.getProjectName());
					tdgDataMaskingDTO.setProjPhase(requestorDO.getProjectPhase());
					tdgDataMaskingDTO.setEnvType(requestorDO.getEnvironment());
					tdgDataMaskingDTO.setProjectId(requestorDO.getProjectId());
					tdgDataMaskingDTO.setVno(requestorDO.getVno());
				}
				if (page2)
				{
					tdgDataMaskingDTO.setPage2Q1(requestorDO.getHlfd());
					tdgDataMaskingDTO.setPage2Q2(requestorDO.getDsmech());
					tdgDataMaskingDTO.setPage2Q3(requestorDO.getOdsmech());
					tdgDataMaskingDTO.setPage2Q4(requestorDO.getProdnonprodUpsteam());
					tdgDataMaskingDTO.setPage2Q5(requestorDO.getNonEnglishChar());
					tdgDataMaskingDTO.setPage2Q6(requestorDO.getDfdchart());
					tdgDataMaskingDTO.setPage2Q7(requestorDO.getOatpsource());
					tdgDataMaskingDTO.setPage2Q8(requestorDO.getDsADU());
					tdgDataMaskingDTO.setPage2Q9(requestorDO.getPiiMnpi());
					tdgDataMaskingDTO.setPage2Q10(requestorDO.getMaskNonProd());
					tdgDataMaskingDTO.setPage2Q11(requestorDO.getValMaskData());
					tdgDataMaskingDTO.setPage2Q12(requestorDO.getProdSensElem());
				}
				if (page3)
				{
					tdgDataMaskingDTO.setPage3Q1(requestorDO.getNonEngLang());
					tdgDataMaskingDTO.setPage3Q2(requestorDO.getNoOfTable());
					tdgDataMaskingDTO.setPage3Q3(requestorDO.getCountDatabase());
					tdgDataMaskingDTO.setPage3Q4(requestorDO.getDsSchemaChnage());
					tdgDataMaskingDTO.setPage3Q5(requestorDO.getVolDataDataMask());
					tdgDataMaskingDTO.setPage3Q6(requestorDO.getPlaceMaskingStg());
					tdgDataMaskingDTO.setPage3Q7(requestorDO.getNonProdMask());
					tdgDataMaskingDTO.setPage3Q8(requestorDO.getDataMaskDev());
					tdgDataMaskingDTO.setPage3Q9(requestorDO.getDataMaskOngoingSupport());
					tdgDataMaskingDTO.setPage3Q10(requestorDO.getSlaDataMasking());
					tdgDataMaskingDTO.setPage3NoOfApps(requestorDO.getNoOfApps());
					tdgDataMaskingDTO.setChngReqCmmt(requestorDO.getChngReqCmmt());
					if (requestorDO.getReqChDOs() != null && 0 < requestorDO.getReqChDOs().size())
					{
						tdgDataMaskingNoOfAppsDTOList = new ArrayList<TdmDataMaskingNoOfAppsDTO>();
						for (ReqChDO reqChDO : requestorDO.getReqChDOs())
						{
							tdgDataMaskingNoOfAppsDTO = new TdmDataMaskingNoOfAppsDTO();
							tdgDataMaskingNoOfAppsDTO.setAppName(reqChDO.getAppName());
							tdgDataMaskingNoOfAppsDTO.setDbName(reqChDO.getDbName());
							tdgDataMaskingNoOfAppsDTO.setId(String.valueOf(reqChDO.getId()));
							tdgDataMaskingNoOfAppsDTO.setNoOfTables(reqChDO.getNoOfTable());
							tdgDataMaskingNoOfAppsDTOList.add(tdgDataMaskingNoOfAppsDTO);
						}
					}
					tdgDataMaskingDTO.setTdgDataMaskingNoOfAppsDTOs(tdgDataMaskingNoOfAppsDTOList);
				}
				if (page1 && page2 && page3)
				{
					SimpleDateFormat format = new SimpleDateFormat(TDMConstants.MMDDYYYY_HHMMSS);
					tdgDataMaskingDTO.setReqTime(format.format(requestorDO.getRequestTime()));
					tdgDataMaskingDTO.setStatus(requestorDO.getStatus());
				}
			}
			return tdgDataMaskingDTO;
		}
		catch (NullPointerException nullPointerEx)
		{
			nullPointerEx.printStackTrace();
			throw new ServiceException(TDMExceptionCode.NULL_POINTR, nullPointerEx);
		}
		catch (Exception otherEx)
		{
			throw new ServiceException(TDMExceptionCode.EXCEPTION, otherEx);
		}
	}

	@Override
	public TdmDataMaskingDTO convertDOtoMaskDTOs(TdmDataMaskingDTO tdgDataMaskingDTO,
			List<RequestorDO> requestorDOs, boolean page1, boolean page2, boolean page3)
			throws ServiceException
	{
		try
		{
			List<TdmDataMaskingDTO> tdgDataMaskingDTOs = null;
			TdmDataMaskingDTO tdgDataMaskingDTO1 = null;
			if (null != requestorDOs && 0 < requestorDOs.size())
			{
				tdgDataMaskingDTOs = new ArrayList<TdmDataMaskingDTO>();
				for (RequestorDO requestorDO : requestorDOs)
				{
					tdgDataMaskingDTO1 = new TdmDataMaskingDTO();
					tdgDataMaskingDTOs.add(convertDOtoMaskDTO(tdgDataMaskingDTO1, requestorDO,
							page1, page2, page3));
					tdgDataMaskingDTO1 = null;
				}
			}
			tdgDataMaskingDTO.setTdgDataMaskingDTOs(tdgDataMaskingDTOs);
			return tdgDataMaskingDTO;
		}
		catch (NullPointerException nullPointerEx)
		{
			throw new ServiceException(TDMExceptionCode.NULL_POINTR, nullPointerEx);
		}
		catch (Exception otherEx)
		{
			throw new ServiceException(TDMExceptionCode.EXCEPTION, otherEx);
		}
	}

	@Override
	public List<TdmDataMaskingDTO> converTdmOnboardReqDOtoDTO(List<TdmOnboardReqDO> tdmOnboardReqDOs)
			throws ServiceException
	{
		try
		{
			SimpleDateFormat format = new SimpleDateFormat(TDMConstants.MMDDYYYY_HHMMSS);
			TdmDataMaskingDTO tdgDataMaskingDTO = null;
			List<TdmDataMaskingDTO> tdgDataMaskingDTOList = null;
			if (tdmOnboardReqDOs != null && 0 < tdmOnboardReqDOs.size())
			{
				tdgDataMaskingDTOList = new ArrayList<TdmDataMaskingDTO>();
				for (TdmOnboardReqDO tdmOnboardReqDO : tdmOnboardReqDOs)
				{
					tdgDataMaskingDTO = new TdmDataMaskingDTO();
					tdgDataMaskingDTO.setId(tdmOnboardReqDO.getOnboardReqId());
					tdgDataMaskingDTO.setUserName(tdmOnboardReqDO.getUName());
					tdgDataMaskingDTO.setUserId(tdmOnboardReqDO.getUserName());
					tdgDataMaskingDTO.setDeptName(tdmOnboardReqDO.getDept());
					tdgDataMaskingDTO.setProjName(tdmOnboardReqDO.getAppName());
					tdgDataMaskingDTO.setProjPhase(tdmOnboardReqDO.getAppPhase());
					tdgDataMaskingDTO.setReqTime(format.format(tdmOnboardReqDO.getActionDt()));
					tdgDataMaskingDTO.setStatus(tdmOnboardReqDO.getStatus());
					tdgDataMaskingDTO.setDesc(tdmOnboardReqDO.getAppDesc());
					tdgDataMaskingDTOList.add(tdgDataMaskingDTO);
				}
			}
			return tdgDataMaskingDTOList;
		}
		catch (NullPointerException nullPointerEx)
		{
			throw new ServiceException(TDMExceptionCode.NULL_POINTR, nullPointerEx);
		}
		catch (Exception otherEx)
		{
			throw new ServiceException(TDMExceptionCode.EXCEPTION, otherEx);
		}
	}

	@Override
	public TdmOnboardReqDO convertTdmOnBoardReqDTOToDO(TdmOnBoardReqDTO tdmOnboardReqDTO, String seq)
			throws ServiceException
	{
		try
		{
			TdmOnboardReqDO tdmOnboardReqDO = null;
			if (null != tdmOnboardReqDTO)
			{
				tdmOnboardReqDO = new TdmOnboardReqDO();
				if (StringUtils.isNotEmpty(tdmOnboardReqDTO.getOnboardReqId()))
				{
					int crNo = tdmOnboardReqDTO.getVno() + 1;
					if (tdmOnboardReqDTO.getOnboardReqId().contains(TDMConstants.CR))
					{
						tdmOnboardReqDO.setOnboardReqId(tdmOnboardReqDTO.getOnboardReqId().replace(
								tdmOnboardReqDTO.getOnboardReqId().substring(
										tdmOnboardReqDTO.getOnboardReqId().indexOf(TDMConstants.C),
										tdmOnboardReqDTO.getOnboardReqId().length()),
								TDMConstants.CR + crNo));
						tdmOnboardReqDTO.setOnboardReqId(tdmOnboardReqDO.getOnboardReqId());
					}
					else
					{
						tdmOnboardReqDO.setOnboardReqId(tdmOnboardReqDTO.getOnboardReqId()
								+ TDMConstants.CR + (tdmOnboardReqDTO.getVno() + 1));
						tdmOnboardReqDTO.setOnboardReqId(tdmOnboardReqDO.getOnboardReqId());
					}
					tdmOnboardReqDO.setVno(tdmOnboardReqDTO.getVno() + 1);
				}
				else
				{
					tdmOnboardReqDO.setOnboardReqId(TDMConstants.TR + seq);
					tdmOnboardReqDO.setVno(0);
				}
				tdmOnboardReqDO.setAppDesc(tdmOnboardReqDTO.getAppDesc());
				tdmOnboardReqDO.setAppName(tdmOnboardReqDTO.getAppName());
				tdmOnboardReqDO.setAppPhase(tdmOnboardReqDTO.getAppPhase());
				tdmOnboardReqDO.setDept(tdmOnboardReqDTO.getDeptName());
				tdmOnboardReqDO.setEmailId(tdmOnboardReqDTO.getEmailId());
				tdmOnboardReqDO.setEnvName(tdmOnboardReqDTO.getEnvType());
				tdmOnboardReqDO.setPhoneNo(tdmOnboardReqDTO.getPhoneNo());
				tdmOnboardReqDO.setUName(tdmOnboardReqDTO.getUserName());
				tdmOnboardReqDO.setUserName(tdmOnboardReqDTO.getUserId());
				tdmOnboardReqDO.setActionBy(tdmOnboardReqDTO.getUserId());
				tdmOnboardReqDO.setActionDt(new Timestamp(new Date().getTime()));
				tdmOnboardReqDO.setStatus(TDMConstants.STATUS_SUBMITTED);
				if (null != tdmOnboardReqDTO.getTdgDataMaskingNoOfAppsDTOs()
						&& 0 < tdmOnboardReqDTO.getTdgDataMaskingNoOfAppsDTOs().size())
				{
					TdmOnboadReqNoTabDO tdmOnboadReqNoTabDO = null;
					List<TdmOnboadReqNoTabDO> tdmOnboadReqNoTabDOs = new ArrayList<TdmOnboadReqNoTabDO>();
					for (TdmDataMaskingNoOfAppsDTO tdgDataMaskingNoOfAppsDTO : tdmOnboardReqDTO
							.getTdgDataMaskingNoOfAppsDTOs())
					{
						tdmOnboadReqNoTabDO = new TdmOnboadReqNoTabDO();
						tdmOnboadReqNoTabDO.setAppName(tdgDataMaskingNoOfAppsDTO.getAppName());
						tdmOnboadReqNoTabDO.setDbName(tdgDataMaskingNoOfAppsDTO.getDbName());
						tdmOnboadReqNoTabDO.setId(RandomUtils.nextInt());
						tdmOnboadReqNoTabDO.setNoOfTable(tdgDataMaskingNoOfAppsDTO.getNoOfTables());
						tdmOnboadReqNoTabDO.setOnboardReqId(tdmOnboardReqDO.getOnboardReqId());
						tdmOnboadReqNoTabDO.setTdmOnboardReq(tdmOnboardReqDO);
						if (StringUtils.isNotEmpty(tdgDataMaskingNoOfAppsDTO.getAppName()))
						{
							tdmOnboadReqNoTabDOs.add(tdmOnboadReqNoTabDO);
						}
					}
					tdmOnboardReqDO.setTdmOnboadReqNoTabs(tdmOnboadReqNoTabDOs);
				}
			}
			return tdmOnboardReqDO;
		}
		catch (NullPointerException nullPointerEx)
		{
			throw new ServiceException(TDMExceptionCode.NULL_POINTR, nullPointerEx);
		}
		catch (Exception otherEx)
		{
			throw new ServiceException(TDMExceptionCode.EXCEPTION, otherEx);
		}
	}

	@Override
	public TdmOnBoardReqDTO convertTdmOnboardReqDOToDO(TdmOnboardReqDO tdmOnboardReqDO)
			throws ServiceException
	{
		try
		{
			TdmOnBoardReqDTO tdmOnBoardReqDTO = null;
			if (null != tdmOnboardReqDO)
			{
				tdmOnBoardReqDTO = new TdmOnBoardReqDTO();
				tdmOnBoardReqDTO.setOnboardReqId(tdmOnboardReqDO.getOnboardReqId());
				tdmOnBoardReqDTO.setAppDesc(tdmOnboardReqDO.getAppDesc());
				tdmOnBoardReqDTO.setAppName(tdmOnboardReqDO.getAppName());
				tdmOnBoardReqDTO.setAppPhase(tdmOnboardReqDO.getAppPhase());
				tdmOnBoardReqDTO.setDeptName(tdmOnboardReqDO.getDept());
				tdmOnBoardReqDTO.setEmailId(tdmOnboardReqDO.getEmailId());
				tdmOnBoardReqDTO.setEnvType(tdmOnboardReqDO.getEnvName());
				tdmOnBoardReqDTO.setPhoneNo(tdmOnboardReqDO.getPhoneNo());
				tdmOnBoardReqDTO.setUserName(tdmOnboardReqDO.getUName());
				tdmOnBoardReqDTO.setUserId(tdmOnboardReqDO.getUserName());
				tdmOnBoardReqDTO.setVno(tdmOnboardReqDO.getVno());
				if (null != tdmOnboardReqDO.getTdmOnboadReqNoTabs()
						&& 0 < tdmOnboardReqDO.getTdmOnboadReqNoTabs().size())
				{
					TdmDataMaskingNoOfAppsDTO tdgDataMaskingNoOfAppsDTO = null;
					List<TdmDataMaskingNoOfAppsDTO> tdgDataMaskingNoOfAppsDTOs = new ArrayList<TdmDataMaskingNoOfAppsDTO>();
					for (TdmOnboadReqNoTabDO tdmOnboadReqNoTabDO : tdmOnboardReqDO
							.getTdmOnboadReqNoTabs())
					{
						tdgDataMaskingNoOfAppsDTO = new TdmDataMaskingNoOfAppsDTO();
						tdgDataMaskingNoOfAppsDTO.setAppName(tdmOnboadReqNoTabDO.getAppName());
						tdgDataMaskingNoOfAppsDTO.setDbName(tdmOnboadReqNoTabDO.getDbName());
						tdgDataMaskingNoOfAppsDTO
								.setId(String.valueOf(tdmOnboadReqNoTabDO.getId()));
						tdgDataMaskingNoOfAppsDTO.setNoOfTables(tdmOnboadReqNoTabDO.getNoOfTable());
						tdgDataMaskingNoOfAppsDTOs.add(tdgDataMaskingNoOfAppsDTO);
					}
					tdmOnBoardReqDTO.setTdgDataMaskingNoOfAppsDTOs(tdgDataMaskingNoOfAppsDTOs);
				}
			}
			return tdmOnBoardReqDTO;
		}
		catch (NullPointerException nullPointerEx)
		{
			throw new ServiceException(TDMExceptionCode.NULL_POINTR, nullPointerEx);
		}
		catch (Exception otherEx)
		{
			throw new ServiceException(TDMExceptionCode.EXCEPTION, otherEx);
		}
	}

	@Override
	public TdmDataMaskingDTO converDOtoRequestorDTO(List<RequestorDO> requestorDOs)
	{
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a zzz");
		TdmDataMaskingDTO tdgDataMaskingDTO = null;
		List<TdmDataMaskingDTO> tdgDataMaskingDTOList = null;
		if (requestorDOs != null)
		{
			tdgDataMaskingDTOList = new ArrayList<TdmDataMaskingDTO>();
			for (RequestorDO requestorDO : requestorDOs)
			{
				tdgDataMaskingDTO = new TdmDataMaskingDTO();
				tdgDataMaskingDTO.setId(requestorDO.getId());
				tdgDataMaskingDTO.setUserName(requestorDO.getName());
				tdgDataMaskingDTO.setUserId(requestorDO.getUserName());
				tdgDataMaskingDTO.setDeptName(requestorDO.getDepartment());
				tdgDataMaskingDTO.setProjName(requestorDO.getProjectName());
				tdgDataMaskingDTO.setProjPhase(requestorDO.getProjectPhase());
				tdgDataMaskingDTO.setReqTime(format.format(requestorDO.getRequestTime()));
				tdgDataMaskingDTO.setStatus(requestorDO.getStatus());
				tdgDataMaskingDTO.setChngReqCmmt(requestorDO.getChngReqCmmt());
				tdgDataMaskingDTO.setDesc("Masking"
						+ " > "
						+ requestorDO.getEnvironment()
						+ (requestorDO.getProjectName() == null ? "" : " > "
								+ requestorDO.getProjectName())
						+ (requestorDO.getProjectPhase() == null ? "" : " > "
								+ requestorDO.getProjectPhase())
						+ (requestorDO.getNeededby() == null ? "" : " > "
								+ requestorDO.getNeededby()));
				tdgDataMaskingDTOList.add(tdgDataMaskingDTO);
			}
		}
		if (null != tdgDataMaskingDTO)
		{
			tdgDataMaskingDTO.setTdgDataMaskingDTOs(tdgDataMaskingDTOList);
		}
		else
		{
			tdgDataMaskingDTO = new TdmDataMaskingDTO();
		}
		return tdgDataMaskingDTO;
	}

	@Override
	public TdmDataMaskingDTO converDOtoRequestDTOForReqId(Map<RequestorDO, List<ReqChDO>> map)
	{
		TdmDataMaskingDTO tdgDataMaskingDTO = null;
		List<TdmDataMaskingNoOfAppsDTO> tdgDataMaskingNoOfAppsDTOList = null;
		TdmDataMaskingNoOfAppsDTO tdgDataMaskingNoOfAppsDTO = null;
		RequestorDO requestorDO = null;
		List<ReqChDO> reqChDOList = null;
		if (map != null)
		{
			for (Entry<RequestorDO, List<ReqChDO>> entry : map.entrySet())
			{
				requestorDO = entry.getKey();
				reqChDOList = entry.getValue();
			}
		}
		if (requestorDO != null)
		{
			tdgDataMaskingDTO = new TdmDataMaskingDTO();
			tdgDataMaskingDTO.setId(requestorDO.getId());
			tdgDataMaskingDTO.setUserName(requestorDO.getName());
			tdgDataMaskingDTO.setUserId(requestorDO.getUserName());
			tdgDataMaskingDTO.setEmailId(requestorDO.getEmailid());
			tdgDataMaskingDTO.setPhoneNo(requestorDO.getPhoneno());
			tdgDataMaskingDTO.setDeptName(requestorDO.getDepartment());
			tdgDataMaskingDTO.setNeededBy(String.valueOf(requestorDO.getNeededby()));
			tdgDataMaskingDTO.setProjName(requestorDO.getProjectName());
			tdgDataMaskingDTO.setProjPhase(requestorDO.getProjectPhase());
			tdgDataMaskingDTO.setEnvType(requestorDO.getEnvironment());

			tdgDataMaskingDTO.setReqTime(requestorDO.getRequestTime().toString());
			tdgDataMaskingDTO.setStatus(requestorDO.getStatus());
			tdgDataMaskingDTO.setPage2Q1(requestorDO.getHlfd());
			tdgDataMaskingDTO.setPage2Q2(requestorDO.getDsmech());
			tdgDataMaskingDTO.setPage2Q3(requestorDO.getOdsmech());
			tdgDataMaskingDTO.setPage2Q4(requestorDO.getProdnonprodUpsteam());
			tdgDataMaskingDTO.setPage2Q5(requestorDO.getNonEnglishChar());
			tdgDataMaskingDTO.setPage2Q6(requestorDO.getDfdchart());
			tdgDataMaskingDTO.setPage2Q7(requestorDO.getOatpsource());
			tdgDataMaskingDTO.setPage2Q8(requestorDO.getDsADU());
			tdgDataMaskingDTO.setPage2Q9(requestorDO.getPiiMnpi());
			tdgDataMaskingDTO.setPage2Q10(requestorDO.getMaskNonProd());
			tdgDataMaskingDTO.setPage2Q11(requestorDO.getValMaskData());
			tdgDataMaskingDTO.setPage2Q12(requestorDO.getProdSensElem());

			tdgDataMaskingDTO.setPage3Q1(requestorDO.getNonEngLang());
			tdgDataMaskingDTO.setPage3Q2(requestorDO.getNoOfTable());
			tdgDataMaskingDTO.setPage3Q3(requestorDO.getCountDatabase());
			tdgDataMaskingDTO.setPage3Q4(requestorDO.getDsSchemaChnage());
			tdgDataMaskingDTO.setPage3Q5(requestorDO.getVolDataDataMask());
			tdgDataMaskingDTO.setPage3Q6(requestorDO.getPlaceMaskingStg());
			tdgDataMaskingDTO.setPage3Q7(requestorDO.getNonProdMask());
			tdgDataMaskingDTO.setPage3Q8(requestorDO.getDataMaskDev());
			tdgDataMaskingDTO.setPage3Q9(requestorDO.getDataMaskOngoingSupport());
			tdgDataMaskingDTO.setPage3Q10(requestorDO.getSlaDataMasking());
			tdgDataMaskingDTO.setPage3NoOfApps(requestorDO.getNoOfApps());
		}

		if (reqChDOList != null)
		{
			tdgDataMaskingNoOfAppsDTOList = new ArrayList<TdmDataMaskingNoOfAppsDTO>();
			for (ReqChDO reqChDO : reqChDOList)
			{
				tdgDataMaskingNoOfAppsDTO = new TdmDataMaskingNoOfAppsDTO();
				tdgDataMaskingNoOfAppsDTO.setAppName(reqChDO.getAppName());
				tdgDataMaskingNoOfAppsDTO.setDbName(reqChDO.getDbName());
				tdgDataMaskingNoOfAppsDTO.setId(String.valueOf(reqChDO.getId()));
				tdgDataMaskingNoOfAppsDTO.setNoOfTables(reqChDO.getNoOfTable());
				tdgDataMaskingNoOfAppsDTOList.add(tdgDataMaskingNoOfAppsDTO);
			}
		}
		tdgDataMaskingDTO.setTdgDataMaskingNoOfAppsDTOs(tdgDataMaskingNoOfAppsDTOList);
		return tdgDataMaskingDTO;
	}
}
