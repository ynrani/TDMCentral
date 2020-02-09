/*---------------------------------------------------------------------------------------
 * Object Name: TDMUserMapperImp.Java
 * 
 * Modification Block:
 * --------------------------------------------------------------------------------------
 * S.No. Name                Date      Bug Fix no. Desc
 * --------------------------------------------------------------------------------------
 * 1     Seshadri Chowdary          12/06/15  NA          Created
 * --------------------------------------------------------------------------------------
 *
 * Copyright: 2015 <CapGemini>
 *---------------------------------------------------------------------------------------*/

package com.tdm.model.mapper.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.tdm.constant.MessageConstant;
import com.tdm.constant.RequestStatusENUM;
import com.tdm.exception.ServiceException;
import com.tdm.model.DO.DCAccessLogDO;
import com.tdm.model.DO.DGAutomationScenariosDO;
import com.tdm.model.DO.ServiceRequestDO;
import com.tdm.model.DO.ServiceRequestDetailsDO;
import com.tdm.model.DO.TdmUserDO;
import com.tdm.model.DO.TdmUsersAuthDO;
import com.tdm.model.DTO.AutoPolicyParamsDTO;
import com.tdm.model.DTO.DCAccessLogDTO;
import com.tdm.model.DTO.PropertyPolicyParamsDTO;
import com.tdm.model.DTO.TDMDataCreationDTO;
import com.tdm.model.DTO.TdmUserAuthDTO;
import com.tdm.model.DTO.TdmUserDTO;
import com.tdm.model.mapper.TDMUserMapper;
import com.tdm.service.impl.TdmBaseServiceImpl;

/**
 * 
 * @author Seshadri Chowdary
 *
 */
@Component
@Service(MessageConstant.MAPPER_ADMIN)
public class TDMUserMapperImp extends TdmBaseServiceImpl implements TDMUserMapper
{
	private static Logger logger = Logger.getLogger(TDMUserMapperImp.class);

	@Override
	public TdmUserDTO converTdmUserDOToUserSearchResultDTO(TdmUserDO tdmUserDo,
			TdmUsersAuthDO tdmUsersAuthDo) throws ServiceException {
		logger.info(MessageConstant.TDM_ADMIN_MAPPER + MessageConstant.TDM_ADMIN_MAPPER_DO_TO_DTO
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			TdmUserDTO tdmUserDTO = new TdmUserDTO();
			TdmUserAuthDTO tdmUserAuthDTO = new TdmUserAuthDTO();
			tdmUserDTO.setCreated(false);
			tdmUserDTO.setEmailId(tdmUserDo.getEmailId());
			tdmUserDTO.setMobileNo(tdmUserDo.getMobileNo());
			tdmUserDTO.setPassword(tdmUserDo.getPassword());
			tdmUserDTO.setUserId(tdmUserDo.getUserId());
			tdmUserDTO.setUsername(tdmUserDo.getUsername());
			tdmUserDTO.setEnabled(tdmUserDo.getEnabled());
			tdmUserAuthDTO.setTdmUserDTO(tdmUserDTO);
			if (tdmUsersAuthDo != null)
				tdmUserAuthDTO.setRole(tdmUsersAuthDo.getRole());
			tdmUserDTO.setTdmUserAuthDTO(tdmUserAuthDTO);
			logger.info(MessageConstant.TDM_ADMIN_MAPPER
					+ MessageConstant.TDM_ADMIN_MAPPER_DO_TO_DTO + MessageConstant.LOG_INFO_RETURN);
			return tdmUserDTO;
		} catch (NullPointerException nullPointerEx) {
			logger.error(MessageConstant.TDM_ADMIN_MAPPER
					+ MessageConstant.TDM_ADMIN_MAPPER_DO_TO_DTO
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new ServiceException(MessageConstant.NULL_POINTER_EXCEPTION, nullPointerEx);
		} catch (Exception otherEx) {
			logger.error(MessageConstant.TDM_ADMIN_MAPPER
					+ MessageConstant.TDM_ADMIN_MAPPER_DO_TO_DTO
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new ServiceException(MessageConstant.SERVICE_EXCEPTION, otherEx);
		}
	}

	@Override
	public TdmUserDTO converTdmUserDOToUserSearchResultDTO(TdmUserDO tdmUserDo)
			throws ServiceException {
		logger.info(MessageConstant.TDM_ADMIN_MAPPER + MessageConstant.TDM_ADMIN_MAPPER_DO_TO_DTO
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			TdmUserDTO tdmUserDTO = new TdmUserDTO();
			TdmUserAuthDTO tdmUserAuthDTO = new TdmUserAuthDTO();
			tdmUserDTO.setCreated(false);
			tdmUserDTO.setEmailId(tdmUserDo.getEmailId());
			tdmUserDTO.setMobileNo(tdmUserDo.getMobileNo());
			tdmUserDTO.setPassword(tdmUserDo.getPassword());
			tdmUserDTO.setUserId(tdmUserDo.getUserId());
			tdmUserDTO.setUsername(tdmUserDo.getUsername());
			tdmUserDTO.setEnabled(tdmUserDo.getEnabled());
			tdmUserAuthDTO.setTdmUserDTO(tdmUserDTO);
			if (tdmUserDo.getTdmUsersAuths() != null)
				tdmUserAuthDTO.setRole(tdmUserDo.getTdmUsersAuths().getRole());
			tdmUserDTO.setTdmUserAuthDTO(tdmUserAuthDTO);
			logger.info(MessageConstant.TDM_ADMIN_MAPPER
					+ MessageConstant.TDM_ADMIN_MAPPER_DO_TO_DTO + MessageConstant.LOG_INFO_RETURN);
			return tdmUserDTO;
		} catch (NullPointerException nullPointerEx) {
			logger.error(MessageConstant.TDM_ADMIN_MAPPER
					+ MessageConstant.TDM_ADMIN_MAPPER_DO_TO_DTO
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new ServiceException(MessageConstant.NULL_POINTER_EXCEPTION, nullPointerEx);
		} catch (Exception otherEx) {
			logger.error(MessageConstant.TDM_ADMIN_MAPPER
					+ MessageConstant.TDM_ADMIN_MAPPER_DO_TO_DTO
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new ServiceException(MessageConstant.SERVICE_EXCEPTION, otherEx);
		}
	}

	@Override
	public List<TdmUserDTO> converTdmUserDOToUserSearchResultListDTO(List<TdmUserDO> listTdmUserDo)
			throws ServiceException {
		logger.info(MessageConstant.TDM_ADMIN_MAPPER + MessageConstant.TDM_ADMIN_MAPPER_DO_TO_DTOs
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			List<TdmUserDTO> listTdmUserDTO = new ArrayList<TdmUserDTO>();
			for (TdmUserDO tdmUserDO : listTdmUserDo) {
				listTdmUserDTO.add(converTdmUserDOToUserSearchResultDTO(tdmUserDO));
			}
			logger.info(MessageConstant.TDM_ADMIN_MAPPER
					+ MessageConstant.TDM_ADMIN_MAPPER_DO_TO_DTOs + MessageConstant.LOG_INFO_RETURN);
			return listTdmUserDTO;
		} catch (NullPointerException nullPointerEx) {
			logger.error(MessageConstant.TDM_ADMIN_MAPPER
					+ MessageConstant.TDM_ADMIN_MAPPER_DO_TO_DTOs
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new ServiceException(MessageConstant.NULL_POINTER_EXCEPTION, nullPointerEx);
		} catch (Exception otherEx) {
			logger.error(MessageConstant.TDM_ADMIN_MAPPER
					+ MessageConstant.TDM_ADMIN_MAPPER_DO_TO_DTOs
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new ServiceException(MessageConstant.SERVICE_EXCEPTION, otherEx);
		}
	}

	@Override
	public TdmUserDO converTdmUserDTOToUserDO(TdmUserDTO tdmUserDTo, TdmUserAuthDTO tdmUsersAuthDo)
			throws ServiceException {
		logger.info(MessageConstant.TDM_ADMIN_MAPPER + MessageConstant.TDM_ADMIN_MAPPER_DTO_TO_DO
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			TdmUserDO tdmUserDO = new TdmUserDO();
			TdmUsersAuthDO tdmUsersAuthDO = new TdmUsersAuthDO();
			tdmUserDO.setEmailId(tdmUserDTo.getEmailId());
			tdmUserDO.setMobileNo(tdmUserDTo.getMobileNo());
			tdmUserDO.setPassword(tdmUserDTo.getPassword());
			tdmUserDO.setUserId(tdmUserDTo.getUserId());
			tdmUserDO.setUsername(tdmUserDTo.getUsername());
			tdmUserDO.setEnabled(tdmUserDTo.getEnabled());
			tdmUsersAuthDO.setTdmUser(tdmUserDO);
			if (tdmUsersAuthDo != null)
				tdmUsersAuthDO.setRole(tdmUsersAuthDo.getRole());
			tdmUserDO.setTdmUsersAuths(tdmUsersAuthDO);
			logger.info(MessageConstant.TDM_ADMIN_MAPPER
					+ MessageConstant.TDM_ADMIN_MAPPER_DTO_TO_DO + MessageConstant.LOG_INFO_RETURN);
			return tdmUserDO;
		} catch (NullPointerException nullPointerEx) {
			logger.error(MessageConstant.TDM_ADMIN_MAPPER
					+ MessageConstant.TDM_ADMIN_MAPPER_DTO_TO_DO
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new ServiceException(MessageConstant.NULL_POINTER_EXCEPTION, nullPointerEx);
		} catch (Exception otherEx) {
			logger.error(MessageConstant.TDM_ADMIN_MAPPER
					+ MessageConstant.TDM_ADMIN_MAPPER_DTO_TO_DO
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new ServiceException(MessageConstant.SERVICE_EXCEPTION, otherEx);
		}
	}

	@Override
	public TdmUserDO converTdmUserDTOToUserDO(TdmUserDTO tdmUserDTO) throws ServiceException {
		logger.info(MessageConstant.TDM_ADMIN_MAPPER + MessageConstant.TDM_ADMIN_MAPPER_DTO_TO_DO
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			TdmUserDO tdmUserDO = new TdmUserDO();
			TdmUsersAuthDO tdmUsersAuthDO = new TdmUsersAuthDO();
			TdmUserAuthDTO tdmUserAuthDTO = tdmUserDTO.getTdmUserAuthDTO();
			tdmUserDO.setEmailId(tdmUserDTO.getEmailId());
			tdmUserDO.setMobileNo(tdmUserDTO.getMobileNo());
			tdmUserDO.setPassword(tdmUserDTO.getPassword());
			tdmUserDO.setManager(tdmUserDTO.getManager());
			tdmUserDO.setAccessReason(tdmUserDTO.getAccessReason());
			tdmUserDO.setUserId(tdmUserDTO.getUserId());
			tdmUserDO.setUsername(tdmUserDTO.getUsername());
			tdmUserDO.setEnabled(tdmUserDTO.getEnabled());
			tdmUsersAuthDO.setTdmUser(tdmUserDO);
			if (tdmUserAuthDTO != null)
				tdmUsersAuthDO.setRole(tdmUserAuthDTO.getRole());
			tdmUserDO.setTdmUsersAuths(tdmUsersAuthDO);
			logger.info(MessageConstant.TDM_ADMIN_MAPPER
					+ MessageConstant.TDM_ADMIN_MAPPER_DTO_TO_DO + MessageConstant.LOG_INFO_RETURN);
			return tdmUserDO;
		} catch (NullPointerException nullPointerEx) {
			logger.error(MessageConstant.TDM_ADMIN_MAPPER
					+ MessageConstant.TDM_ADMIN_MAPPER_DTO_TO_DO
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new ServiceException(MessageConstant.NULL_POINTER_EXCEPTION, nullPointerEx);
		} catch (Exception otherEx) {
			logger.error(MessageConstant.TDM_ADMIN_MAPPER
					+ MessageConstant.TDM_ADMIN_MAPPER_DTO_TO_DO
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new ServiceException(MessageConstant.SERVICE_EXCEPTION, otherEx);
		}
	}

	@Override
	public List<TdmUserDO> converTdmUserDTOToUserDO(List<TdmUserDTO> listTdmUserDTO)
			throws ServiceException {
		logger.info(MessageConstant.TDM_ADMIN_MAPPER + MessageConstant.TDM_ADMIN_MAPPER_DTO_TO_DO
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			List<TdmUserDO> listTdmUserDo = new ArrayList<TdmUserDO>();
			for (TdmUserDTO tdmUserDTO : listTdmUserDTO) {
				listTdmUserDo.add(converTdmUserDTOToUserDO(tdmUserDTO));
			}
			logger.info(MessageConstant.TDM_ADMIN_MAPPER
					+ MessageConstant.TDM_ADMIN_MAPPER_DTO_TO_DO + MessageConstant.LOG_INFO_RETURN);
			return listTdmUserDo;
		} catch (NullPointerException nullPointerEx) {
			logger.error(MessageConstant.TDM_ADMIN_MAPPER
					+ MessageConstant.TDM_ADMIN_MAPPER_DTO_TO_DO
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new ServiceException(MessageConstant.NULL_POINTER_EXCEPTION, nullPointerEx);
		} catch (Exception otherEx) {
			logger.error(MessageConstant.TDM_ADMIN_MAPPER
					+ MessageConstant.TDM_ADMIN_MAPPER_DTO_TO_DO
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new ServiceException(MessageConstant.SERVICE_EXCEPTION, otherEx);
		}
	}
	
	
	@Override
	public List<DCAccessLogDTO> convertDCAccessLogListDOToDCAccessLogListDTO(List<DCAccessLogDO> dcAccessLogListDO)
			throws ServiceException {
		logger.info(MessageConstant.TDM_ADMIN_MAPPER + MessageConstant.TDM_ADMIN_MAPPER_DO_TO_DTOs
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			List<DCAccessLogDTO> listDCAccessLogDTO = new ArrayList<DCAccessLogDTO>();
			for (DCAccessLogDO dcAccessLogDO : dcAccessLogListDO) {
				listDCAccessLogDTO.add(convertDCAccessLogDOToDCAccessLogDTO(dcAccessLogDO));
			}
			logger.info(MessageConstant.TDM_ADMIN_MAPPER
					+ MessageConstant.TDM_ADMIN_MAPPER_DO_TO_DTOs + MessageConstant.LOG_INFO_RETURN);
			return listDCAccessLogDTO;
		} catch (NullPointerException nullPointerEx) {
			logger.error(MessageConstant.TDM_ADMIN_MAPPER
					+ MessageConstant.TDM_ADMIN_MAPPER_DO_TO_DTOs
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new ServiceException(MessageConstant.NULL_POINTER_EXCEPTION, nullPointerEx);
		} catch (Exception otherEx) {
			logger.error(MessageConstant.TDM_ADMIN_MAPPER
					+ MessageConstant.TDM_ADMIN_MAPPER_DO_TO_DTOs
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new ServiceException(MessageConstant.SERVICE_EXCEPTION, otherEx);
		}
	}

	@Override
	public DCAccessLogDTO convertDCAccessLogDOToDCAccessLogDTO(DCAccessLogDO dcAccessLogDO)throws ServiceException {
		logger.info(MessageConstant.TDM_ADMIN_MAPPER + MessageConstant.TDM_ADMIN_MAPPER_DO_TO_DTO
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		
		
		try {
			String status = "";
			logger.info("serviceRequestDO.getRequestStatus() :: "+dcAccessLogDO.getRequestStatus());
			
			for (RequestStatusENUM dir : RequestStatusENUM.values()) {
				if(dcAccessLogDO.getRequestStatus() == dir.getRequestStatusCode()){
					status = dir.getRequestStatusDesc();
				}
			}
			DCAccessLogDTO dcAccessLogDTO = new DCAccessLogDTO();
			if(dcAccessLogDO.getModifiedDate()!=null) {
				SimpleDateFormat sd = new SimpleDateFormat("MM-dd-YYYY HH:mm:ss");
				Date date = dcAccessLogDO.getModifiedDate();
				String strDate=sd.format(date);
				dcAccessLogDTO.setModifiedDate(strDate);
			}
			dcAccessLogDTO.setRequestId(dcAccessLogDO.getRequestId()!=null?dcAccessLogDO.getRequestId():null);
			dcAccessLogDTO.setModifiedBy(dcAccessLogDO.getModifiedBy()!=null?dcAccessLogDO.getModifiedBy().toString():null);
			dcAccessLogDTO.setRequestStatus(status);
			dcAccessLogDTO.setComments(dcAccessLogDO.getComments()!=null?dcAccessLogDO.getComments():null);

			logger.info(MessageConstant.TDM_ADMIN_MAPPER
					+ MessageConstant.TDM_ADMIN_MAPPER_DO_TO_DTO + MessageConstant.LOG_INFO_RETURN);
			return dcAccessLogDTO;
		} catch (NullPointerException nullPointerEx) {
			logger.error(MessageConstant.TDM_ADMIN_MAPPER
					+ MessageConstant.TDM_ADMIN_MAPPER_DO_TO_DTO
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new ServiceException(MessageConstant.NULL_POINTER_EXCEPTION, nullPointerEx);
		} catch (Exception otherEx) {
			logger.error(MessageConstant.TDM_ADMIN_MAPPER
					+ MessageConstant.TDM_ADMIN_MAPPER_DO_TO_DTO
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new ServiceException(MessageConstant.SERVICE_EXCEPTION, otherEx);
		}
	}
	
	@Override
	public List<TDMDataCreationDTO> convertServiceRequestDOToTDMDataCreationListDTO(List<ServiceRequestDO> listServiceRequestDO)
			throws ServiceException {
		logger.info(MessageConstant.TDM_ADMIN_MAPPER + MessageConstant.TDM_ADMIN_MAPPER_DO_TO_DTOs
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			List<TDMDataCreationDTO> listTdmDCDTO = new ArrayList<TDMDataCreationDTO>();
			for (ServiceRequestDO serviceRequestDO : listServiceRequestDO) {
				listTdmDCDTO.add(convertServiceRequestDOToTDMDataCreationDTO(serviceRequestDO));
			}
			logger.info(MessageConstant.TDM_ADMIN_MAPPER
					+ MessageConstant.TDM_ADMIN_MAPPER_DO_TO_DTOs + MessageConstant.LOG_INFO_RETURN);
			return listTdmDCDTO;
		} catch (NullPointerException nullPointerEx) {
			logger.error(MessageConstant.TDM_ADMIN_MAPPER
					+ MessageConstant.TDM_ADMIN_MAPPER_DO_TO_DTOs
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new ServiceException(MessageConstant.NULL_POINTER_EXCEPTION, nullPointerEx);
		} catch (Exception otherEx) {
			logger.error(MessageConstant.TDM_ADMIN_MAPPER
					+ MessageConstant.TDM_ADMIN_MAPPER_DO_TO_DTOs
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new ServiceException(MessageConstant.SERVICE_EXCEPTION, otherEx);
		}
	}
	
	@Override
	public TDMDataCreationDTO convertServiceRequestDOToTDMDataCreationDTO(ServiceRequestDO serviceRequestDO)throws ServiceException {
		logger.info(MessageConstant.TDM_ADMIN_MAPPER + MessageConstant.TDM_ADMIN_MAPPER_DO_TO_DTO
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			TDMDataCreationDTO tdmdatacreationDTO = new TDMDataCreationDTO();
			String status="";
			String formatCreateOnDate = "";
			String formatExpectedDate = "";
			String formatLastModifiedDate="";
			logger.debug("serviceRequestDO.getCreateDate() :: "+serviceRequestDO.getCreateDate());
			if(serviceRequestDO.getCreateDate()!=null){
				formatCreateOnDate = formatDatetoString1(serviceRequestDO.getCreateDate());
			}
			
			if(serviceRequestDO.getExpectedDate()!=null){
				formatExpectedDate = formatDatetoStringDatePicker(serviceRequestDO.getExpectedDate());
			}
			if(serviceRequestDO.getLastModifiedDate()!=null)
			{
				formatLastModifiedDate=formatDatetoString1(serviceRequestDO.getLastModifiedDate());
			}
			logger.info("serviceRequestDO.getRequestStatus() :: "+serviceRequestDO.getRequestStatus());
			for (RequestStatusENUM dir : RequestStatusENUM.values()) {
				if(serviceRequestDO.getRequestStatus().intValue() == dir.getRequestStatusCode()){
					status = dir.getRequestStatusDesc();
				}
			}

			tdmdatacreationDTO.setRequestId(serviceRequestDO.getRequestId()!=null?serviceRequestDO.getRequestId():null);
			logger.info("serviceRequestDO.getCreateDate() :: "+serviceRequestDO.getCreateDate());
			tdmdatacreationDTO.setCreatedOn(serviceRequestDO.getCreateDate()!=null?formatCreateOnDate:null);
			tdmdatacreationDTO.setStatus(serviceRequestDO.getRequestStatus()!=null?status:null);
			tdmdatacreationDTO.setSubject(serviceRequestDO.getSubject()!=null?serviceRequestDO.getSubject():null);
		
			tdmdatacreationDTO.setAssignedToId(serviceRequestDO.getAssignedTo()!=null?serviceRequestDO.getAssignedTo():null);
			tdmdatacreationDTO.setConsumerGroupName(serviceRequestDO.getConsumerGroupName()!=null?serviceRequestDO.getConsumerGroupName():null);
			tdmdatacreationDTO.setPriorityName(serviceRequestDO.getPriorityName()!=null?serviceRequestDO.getPriorityName():null);

			tdmdatacreationDTO.setRequestedBy(serviceRequestDO.getRequestedBy()!=null?serviceRequestDO.getRequestedBy():null);
			tdmdatacreationDTO.setExpectedDate(serviceRequestDO.getExpectedDate()!=null?formatExpectedDate:null);
			tdmdatacreationDTO.setExpectedDate(serviceRequestDO.getExpectedDate()!=null?formatExpectedDate:null);
			tdmdatacreationDTO.setServiceType(serviceRequestDO.getServiceType()!=null?serviceRequestDO.getServiceType():null);
			tdmdatacreationDTO.setApplicationOwner(serviceRequestDO.getApplicationOwner()!=null?serviceRequestDO.getApplicationOwner():null);
			tdmdatacreationDTO.setApprover(serviceRequestDO.getApprover()!=null?serviceRequestDO.getApprover():null);
			
			tdmdatacreationDTO.setAssignedToId(serviceRequestDO.getAssignedTo()!=null?serviceRequestDO.getAssignedTo():null);
			tdmdatacreationDTO.setAssignedGroup(serviceRequestDO.getAssignedGroupId()!=null?String.valueOf(serviceRequestDO.getAssignedGroupId()):null);
			tdmdatacreationDTO.setConsumerGroup(serviceRequestDO.getConsumerGroup()!=null?String.valueOf(serviceRequestDO.getConsumerGroup()):null);
			tdmdatacreationDTO.setPriority(serviceRequestDO.getPriority()!=null?String.valueOf(serviceRequestDO.getPriority()):null);
			tdmdatacreationDTO.setDataSource(serviceRequestDO.getDataSource()!=null?String.valueOf(serviceRequestDO.getDataSource()):null);
			tdmdatacreationDTO.setEnvironment(serviceRequestDO.getEnvironment()!=null?String.valueOf(serviceRequestDO.getEnvironment()):null);
			tdmdatacreationDTO.setLastModifiedDate(serviceRequestDO.getLastModifiedDate()!=null?formatLastModifiedDate:null);
			tdmdatacreationDTO.setEnvDesc(serviceRequestDO.getEnvDesc()!=null?serviceRequestDO.getEnvDesc():null);
			logger.info(MessageConstant.TDM_ADMIN_MAPPER
					+ MessageConstant.TDM_ADMIN_MAPPER_DO_TO_DTO + MessageConstant.LOG_INFO_RETURN);
			return tdmdatacreationDTO;
		} catch (NullPointerException nullPointerEx) {
			logger.error(MessageConstant.TDM_ADMIN_MAPPER
					+ MessageConstant.TDM_ADMIN_MAPPER_DO_TO_DTO
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new ServiceException(MessageConstant.NULL_POINTER_EXCEPTION, nullPointerEx);
		} catch (Exception otherEx) {
			logger.error(MessageConstant.TDM_ADMIN_MAPPER
					+ MessageConstant.TDM_ADMIN_MAPPER_DO_TO_DTO
					+ MessageConstant.LOG_ERROR_EXCEPTION+":: "+otherEx);
			throw new ServiceException(MessageConstant.SERVICE_EXCEPTION, otherEx);
		}
	}
	
	@Override
	public ServiceRequestDO convertTdmDatacreationDtoToDO(TDMDataCreationDTO tdmDataCreationDto){  
		if(null == tdmDataCreationDto) {
			return null;
		}
		ServiceRequestDO serviceRequestDO = null;
		try {
			serviceRequestDO = new ServiceRequestDO();
		  if(tdmDataCreationDto.getRequestId()!=null && tdmDataCreationDto.getRequestId().trim().length()>0)
			{
			serviceRequestDO.setRequestId(tdmDataCreationDto.getRequestId());
			}
			serviceRequestDO.setServiceType(tdmDataCreationDto.getServiceType());
			serviceRequestDO.setRequestedBy(tdmDataCreationDto.getRequestedBy()!=null?tdmDataCreationDto.getRequestedBy():" ");
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			SimpleDateFormat createdOnsdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			try {
				Date createDate = createdOnsdf.parse(tdmDataCreationDto.getCreatedOn()!=null?tdmDataCreationDto.getCreatedOn():null);
				serviceRequestDO.setCreateDate(createDate);
			
				Date expectedDate = sdf.parse(tdmDataCreationDto.getExpectedDate()!=null?tdmDataCreationDto.getExpectedDate():null);
				serviceRequestDO.setExpectedDate(expectedDate);
			}catch(Exception e) {
				if(serviceRequestDO.getCreateDate() == null) {
					serviceRequestDO.setCreateDate(new Date());
				}
				if(serviceRequestDO.getExpectedDate() == null) {
					serviceRequestDO.setExpectedDate(new Date());
				}
			}
			serviceRequestDO.setApplicationOwner(tdmDataCreationDto.getApplicationOwner()!=null?tdmDataCreationDto.getApplicationOwner():"Arun");
			Long assignedGroupId = tdmDataCreationDto.getAssignedGroup()!= null ?Long.valueOf(tdmDataCreationDto.getAssignedGroup()):null;
			serviceRequestDO.setAssignedGroupId(assignedGroupId);
			serviceRequestDO.setApprover(tdmDataCreationDto.getApprover()!=null?tdmDataCreationDto.getApprover():"");
			serviceRequestDO.setAssignedTo(tdmDataCreationDto.getAssignedToId());
			Integer resuestStatusId = tdmDataCreationDto.getStatus()!= null ?Integer.valueOf(tdmDataCreationDto.getStatus()):null;
			serviceRequestDO.setRequestStatus(resuestStatusId);
			serviceRequestDO.setSubject(tdmDataCreationDto.getSubject()!=null?tdmDataCreationDto.getSubject():"");
			Long consumerGroupId = tdmDataCreationDto.getConsumerGroup()!= null ?Long.valueOf(tdmDataCreationDto.getConsumerGroup()):null;
			serviceRequestDO.setConsumerGroup(consumerGroupId);
			Long priorityId = tdmDataCreationDto.getPriority()!= null ?Long.valueOf(tdmDataCreationDto.getPriority()):null;
			serviceRequestDO.setPriority(priorityId);
			Long datasourceId = tdmDataCreationDto.getDataSource()!= null ?Long.valueOf(tdmDataCreationDto.getDataSource()):null;
			serviceRequestDO.setDataSource(datasourceId);
			Long environmentId = tdmDataCreationDto.getEnvironment()!= null ?Long.valueOf(tdmDataCreationDto.getEnvironment()):null;
			serviceRequestDO.setEnvironment(environmentId);
			Integer statusId = tdmDataCreationDto.getStatus()!= null ?Integer.valueOf(tdmDataCreationDto.getStatus()):null;
			serviceRequestDO.setRequestStatus(statusId);
			serviceRequestDO.setRequestedBy(tdmDataCreationDto.getUserId());
			serviceRequestDO.setUpdatedBy(tdmDataCreationDto.getUserId());
			serviceRequestDO.setEnvDesc(tdmDataCreationDto.getEnvDesc());

			if (null != tdmDataCreationDto.getAutoPolicyParamList()
					&& !tdmDataCreationDto.getAutoPolicyParamList().isEmpty() && tdmDataCreationDto.getAutoPolicyParamList().get(0).getScenarioNo() != null) {
				//int count=1;
				List<ServiceRequestDetailsDO> autoSRList = new ArrayList<ServiceRequestDetailsDO>();
				for (AutoPolicyParamsDTO autoPolicyParams : tdmDataCreationDto.getAutoPolicyParamList()) {
					//autoPolicyParams.setScenarioNo(count+"");
					String userId = tdmDataCreationDto.getUserId();
					String AUTO = "AUTO";
					if(autoPolicyParams.getRiskState() != null && !autoPolicyParams.getRiskState().isEmpty()) {
						if(autoPolicyParams.getScenarioNo().trim()!=null &&!autoPolicyParams.getScenarioNo().trim().isEmpty())
						{
						ServiceRequestDetailsDO detailsDO1 = new ServiceRequestDetailsDO(autoPolicyParams.getScenarioNo(), "Auto Risk State", autoPolicyParams.getRiskState(), AUTO, userId);
						autoSRList.add(detailsDO1);
						}
					}
					if(autoPolicyParams.getAutoScenarioType()!= null && !autoPolicyParams.getAutoScenarioType().isEmpty()) {
						if(autoPolicyParams.getScenarioNo().trim()!=null &&!autoPolicyParams.getScenarioNo().trim().isEmpty())
						{
						ServiceRequestDetailsDO detailsDO1 = new ServiceRequestDetailsDO(autoPolicyParams.getScenarioNo(), "Automation Scenario", autoPolicyParams.getAutoScenarioType(), AUTO, userId);
						autoSRList.add(detailsDO1);
						}
					}
					
					
					
					if(autoPolicyParams.getPaymentPlan()!=null && !autoPolicyParams.getPaymentPlan().isEmpty())
					{
						if(autoPolicyParams.getScenarioNo().trim()!=null &&!autoPolicyParams.getScenarioNo().trim().isEmpty())
						{
							ServiceRequestDetailsDO detailsDO2 = new ServiceRequestDetailsDO(autoPolicyParams.getScenarioNo(), "Auto Payment Plan", autoPolicyParams.getPaymentPlan(), AUTO, userId);

							autoSRList.add(detailsDO2);
						}
					}
					if(autoPolicyParams.getNoOfDrivers()!=null && !autoPolicyParams.getNoOfDrivers().isEmpty())
					{ 

						if(autoPolicyParams.getScenarioNo().trim()!=null &&!autoPolicyParams.getScenarioNo().trim().isEmpty())
						{
							ServiceRequestDetailsDO detailsDO3 = new ServiceRequestDetailsDO(autoPolicyParams.getScenarioNo(), "Auto Number Of Drivers", autoPolicyParams.getNoOfDrivers(), AUTO, userId);
							autoSRList.add(detailsDO3);
						}
					}
					if( autoPolicyParams.getNoOfVehicles()!=null && ! autoPolicyParams.getNoOfVehicles().isEmpty())
					{
						if(autoPolicyParams.getScenarioNo().trim()!=null &&!autoPolicyParams.getScenarioNo().trim().isEmpty())
						{
							ServiceRequestDetailsDO detailsDO4 = new ServiceRequestDetailsDO(autoPolicyParams.getScenarioNo(), "Auto Number Of Vehicles", autoPolicyParams.getNoOfVehicles(), AUTO, userId);
							autoSRList.add(detailsDO4);
						}
					}
					if(autoPolicyParams.getNoOfPolicies()!=null && !autoPolicyParams.getNoOfPolicies().isEmpty())
					{
						if(autoPolicyParams.getScenarioNo().trim()!=null &&!autoPolicyParams.getScenarioNo().trim().isEmpty())
						{
					ServiceRequestDetailsDO detailsDO5 = new ServiceRequestDetailsDO(autoPolicyParams.getScenarioNo(), "Auto Number Of Policies", autoPolicyParams.getNoOfPolicies(), AUTO, userId);
					autoSRList.add(detailsDO5);
						}
					}
					if(autoPolicyParams.getAdditionalInformation()!=null && !autoPolicyParams.getAdditionalInformation().isEmpty())
					{
						if(autoPolicyParams.getScenarioNo().trim()!=null &&!autoPolicyParams.getScenarioNo().trim().isEmpty())
						{
					 ServiceRequestDetailsDO detailsDO6 = new ServiceRequestDetailsDO(autoPolicyParams.getScenarioNo(), "Auto Additional Information", autoPolicyParams.getAdditionalInformation(), AUTO, userId);
					autoSRList.add(detailsDO6);
						}
					}
					if(autoPolicyParams.getProductType()!=null && !autoPolicyParams.getProductType().isEmpty())
					{
						if(autoPolicyParams.getProductType().trim()!=null &&!autoPolicyParams.getProductType().trim().isEmpty())
						{
					 ServiceRequestDetailsDO detailsDO7 = new ServiceRequestDetailsDO(autoPolicyParams.getScenarioNo(), "Auto Product Type", autoPolicyParams.getProductType(), AUTO, userId);
					autoSRList.add(detailsDO7);
						}
					}
					if(autoPolicyParams.getTerm()!=null && !autoPolicyParams.getTerm().isEmpty())
					{
						if(autoPolicyParams.getTerm().trim()!=null &&!autoPolicyParams.getTerm().trim().isEmpty())
						{
					 ServiceRequestDetailsDO detailsDO8 = new ServiceRequestDetailsDO(autoPolicyParams.getScenarioNo(), "Auto Term", autoPolicyParams.getTerm(), AUTO, userId);
					autoSRList.add(detailsDO8);
						}
					}
					if (autoPolicyParams.getEffectiveDate() != null
							&& !autoPolicyParams.getEffectiveDate().isEmpty()) {
						if (autoPolicyParams.getScenarioNo().trim() != null
								&& !autoPolicyParams.getScenarioNo().trim()
										.isEmpty()) {

							ServiceRequestDetailsDO detailsDO1 = new ServiceRequestDetailsDO(
									autoPolicyParams
											.getScenarioNo(),
									"Auto Effective Date",
									autoPolicyParams.getEffectiveDate(), AUTO,
									userId);
							autoSRList.add(detailsDO1);
						}
					}
					
					//count++;
				}
				serviceRequestDO.setAutoRequestDetailList(autoSRList);
			}
			
			

			// set property params to SERVICEREQUEST_DETAILS
			if (null != tdmDataCreationDto.getPropertyPolicyParamList()
					&& !tdmDataCreationDto.getPropertyPolicyParamList().isEmpty() && tdmDataCreationDto.getPropertyPolicyParamList().get(0).getScenarioNo() != null) {
				//int count1=1;
				List<ServiceRequestDetailsDO> propertySRList = new ArrayList<ServiceRequestDetailsDO>();
				for (PropertyPolicyParamsDTO propertyPolicyParams : tdmDataCreationDto.getPropertyPolicyParamList()) {
					//propertyPolicyParams.setScenarioNo(count1+"");
					String userId = tdmDataCreationDto.getUserId();
					String PROPERTY = "PROPERTY";
					
					if(propertyPolicyParams.getRiskState() != null && !propertyPolicyParams.getRiskState().isEmpty()) {
						if(propertyPolicyParams.getScenarioNo().trim()!=null &&!propertyPolicyParams.getScenarioNo().trim().isEmpty())
						{
							ServiceRequestDetailsDO detailsDO1 = new ServiceRequestDetailsDO(propertyPolicyParams.getScenarioNo(), "Property Risk State", propertyPolicyParams.getRiskState(),PROPERTY, userId);
							propertySRList.add(detailsDO1);
						}
					}
					
					
					if(propertyPolicyParams.getAutomationScenario() != null && !propertyPolicyParams.getAutomationScenario().isEmpty()) {
						if(propertyPolicyParams.getScenarioNo().trim()!=null &&!propertyPolicyParams.getScenarioNo().trim().isEmpty())
						{
						ServiceRequestDetailsDO detailsDO1 = new ServiceRequestDetailsDO(propertyPolicyParams.getScenarioNo(), "Property Automation Scenario", propertyPolicyParams.getAutomationScenario(),PROPERTY, userId);
						propertySRList.add(detailsDO1);
						}
					}
					if(propertyPolicyParams.getPaymentPlan()!=null &&!propertyPolicyParams.getPaymentPlan().isEmpty())
					{
						if(propertyPolicyParams.getScenarioNo().trim()!=null &&!propertyPolicyParams.getScenarioNo().trim().isEmpty())
						{
							ServiceRequestDetailsDO detailsDO2 = new ServiceRequestDetailsDO(propertyPolicyParams.getScenarioNo(), "Property Payment Plan", propertyPolicyParams.getPaymentPlan(), PROPERTY, userId);
							propertySRList.add(detailsDO2);
						}
					}
					if(propertyPolicyParams.getPolicyType()!=null && !propertyPolicyParams.getPolicyType().isEmpty())
					{
						if(propertyPolicyParams.getScenarioNo().trim()!=null &&!propertyPolicyParams.getScenarioNo().trim().isEmpty())
						{
							ServiceRequestDetailsDO detailsDO3 = new ServiceRequestDetailsDO(propertyPolicyParams.getScenarioNo(), "Property Policy Type", propertyPolicyParams.getPolicyType(), PROPERTY, userId);
							propertySRList.add(detailsDO3);
						}
					}
					if(propertyPolicyParams.getNoOfPolicies()!=null&&!propertyPolicyParams.getNoOfPolicies().isEmpty())
					{
						if(propertyPolicyParams.getScenarioNo().trim()!=null &&!propertyPolicyParams.getScenarioNo().trim().isEmpty())
						{
							ServiceRequestDetailsDO detailsDO4 = new ServiceRequestDetailsDO(propertyPolicyParams.getScenarioNo(), "Property Number Of Policies", propertyPolicyParams.getNoOfPolicies(), PROPERTY, userId);
							propertySRList.add(detailsDO4);
						}
					}
					if(propertyPolicyParams.getAdditionalInformation()!=null&&!propertyPolicyParams.getAdditionalInformation().isEmpty())
					{
						if(propertyPolicyParams.getScenarioNo().trim()!=null &&!propertyPolicyParams.getScenarioNo().trim().isEmpty())
						{
							ServiceRequestDetailsDO detailsDO5 = new ServiceRequestDetailsDO(propertyPolicyParams.getScenarioNo(), "Property Additional Information", propertyPolicyParams.getAdditionalInformation(), PROPERTY, userId);
							propertySRList.add(detailsDO5);
						}
					}
					if(propertyPolicyParams.getProduct()!=null&&!propertyPolicyParams.getProduct().isEmpty())
					{
						if(propertyPolicyParams.getProduct().trim()!=null &&!propertyPolicyParams.getProduct().trim().isEmpty())
						{
							ServiceRequestDetailsDO detailsDO6= new ServiceRequestDetailsDO(propertyPolicyParams.getScenarioNo(), "Property Product Type", propertyPolicyParams.getProduct(), PROPERTY, userId);
							propertySRList.add(detailsDO6);
						}
					}
					if(propertyPolicyParams.getMortgage()!=null&&!propertyPolicyParams.getMortgage().isEmpty())
					{
						if(propertyPolicyParams.getMortgage().trim()!=null &&!propertyPolicyParams.getMortgage().trim().isEmpty())
						{
							ServiceRequestDetailsDO detailsDO7 = new ServiceRequestDetailsDO(propertyPolicyParams.getScenarioNo(), "Property Mortgage", propertyPolicyParams.getMortgage(), PROPERTY, userId);
							propertySRList.add(detailsDO7);
						}
					}
					if(propertyPolicyParams.getAdditionalInterest()!=null&&!propertyPolicyParams.getAdditionalInterest().isEmpty())
					{
						if(propertyPolicyParams.getAdditionalInterest().trim()!=null &&!propertyPolicyParams.getAdditionalInterest().trim().isEmpty())
						{
							ServiceRequestDetailsDO detailsDO8 = new ServiceRequestDetailsDO(propertyPolicyParams.getScenarioNo(), "Property Additional Interest", propertyPolicyParams.getAdditionalInterest(), PROPERTY, userId);
							propertySRList.add(detailsDO8);
						}
					}
					if(propertyPolicyParams.getEffectiveDate()!=null && !propertyPolicyParams.getEffectiveDate().isEmpty())
					{
						if(propertyPolicyParams.getScenarioNo().trim()!=null &&!propertyPolicyParams.getScenarioNo().trim().isEmpty())
						{
							ServiceRequestDetailsDO detailsDO3 = new ServiceRequestDetailsDO(propertyPolicyParams.getScenarioNo(), "Property Effective Date", propertyPolicyParams.getEffectiveDate(), PROPERTY, userId);
							propertySRList.add(detailsDO3);
						}
					}
				}
				serviceRequestDO.setPropertyRequestDetailList(propertySRList);
			}
		}catch(NullPointerException nullPointerEx) {
			nullPointerEx.printStackTrace();
		}
		catch(Exception otherEx) {
			otherEx.printStackTrace();
		}
		return serviceRequestDO;
	}
	@Override
	public TDMDataCreationDTO convertDGAutomationDOToDGAutoationDTO(List<DGAutomationScenariosDO> autoandpropertylist)
	{
		TDMDataCreationDTO dgAutomationScenariosDTO =new TDMDataCreationDTO();
            ArrayList<DGAutomationScenariosDO> autoList=new ArrayList<DGAutomationScenariosDO>();
            ArrayList<AutoPolicyParamsDTO> autoList1=new ArrayList<AutoPolicyParamsDTO>();
            ArrayList<DGAutomationScenariosDO> propertyList=new ArrayList<DGAutomationScenariosDO>();
            ArrayList<PropertyPolicyParamsDTO> propertyList1=new ArrayList<PropertyPolicyParamsDTO>();
		for(DGAutomationScenariosDO dgAutomationScenariosDO:autoandpropertylist)
		{ 
			
			String dgType=dgAutomationScenariosDO.getDgType();
			if(dgType.equals("AUTO"))
			{
				autoList.add(dgAutomationScenariosDO);
			}
			else
			{
				propertyList.add(dgAutomationScenariosDO);
			}
			
		}
		for(DGAutomationScenariosDO autoScenario:autoList)
		{
			AutoPolicyParamsDTO autoScenarioDTO=new AutoPolicyParamsDTO();
			autoScenarioDTO.setScenarioId(autoScenario.getScenarioId());
			autoScenarioDTO.setDescription(autoScenario.getDescription());
			String isActiveStr = autoScenario.getIsActive();
			autoScenarioDTO.setIsActive(isActiveStr!=null && !isActiveStr.isEmpty() 
					&& isActiveStr.equalsIgnoreCase("Y") ? Boolean.TRUE:Boolean.FALSE);
			autoList1.add(autoScenarioDTO);
		}
		for(DGAutomationScenariosDO propertyScenario:propertyList)
		{
			PropertyPolicyParamsDTO dgPropertyScearioDTO=new PropertyPolicyParamsDTO();
			dgPropertyScearioDTO.setScenarioId(propertyScenario.getScenarioId());
			dgPropertyScearioDTO.setDescription(propertyScenario.getDescription());
			String isActiveStr = propertyScenario.getIsActive();
			dgPropertyScearioDTO.setIsActive(isActiveStr!=null && !isActiveStr.isEmpty() 
					&& isActiveStr.equalsIgnoreCase("Y") ? Boolean.TRUE:Boolean.FALSE);
			propertyList1.add(dgPropertyScearioDTO);
		}
		dgAutomationScenariosDTO.setAutoPolicyParamList(autoList1);
		dgAutomationScenariosDTO.setPropertyPolicyParamList(propertyList1);
		return dgAutomationScenariosDTO;
	}
	public String formatDatetoString(Date date) {
	    String formatDate = "";
	    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		formatDate = formatter.format(date);
		return formatDate;
	}
	public String formatDatetoStringDatePicker(Date date) {
	    String formatDate = "";
	    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		formatDate = formatter.format(date);
		return formatDate;
	}
	public String formatDatetoString1(Date date) {
	    String formatDate = "";
	    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		formatDate = formatter.format(date);
		return formatDate;
	}
	
}
