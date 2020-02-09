package com.tdm.model.mapper;

import java.util.List;

import org.hibernate.mapping.Map;

import com.tdm.exception.ServiceException;
import com.tdm.model.DO.DCAccessLogDO;
import com.tdm.model.DO.DGAutomationScenariosDO;
import com.tdm.model.DO.ServiceRequestDO;
import com.tdm.model.DO.TdmUserDO;
import com.tdm.model.DO.TdmUsersAuthDO;
import com.tdm.model.DTO.DCAccessLogDTO;
import com.tdm.model.DTO.DGAutomationScenariosDTO;
import com.tdm.model.DTO.TDMDataCreationDTO;
import com.tdm.model.DTO.TdmUserAuthDTO;
import com.tdm.model.DTO.TdmUserDTO;


public interface TDMUserMapper
{

	public TdmUserDTO converTdmUserDOToUserSearchResultDTO(TdmUserDO tdmUserDo,
			TdmUsersAuthDO tdmUsersAuthDo) throws ServiceException;

	public TdmUserDTO converTdmUserDOToUserSearchResultDTO(TdmUserDO tdmUserDo)
			throws ServiceException;

	public List<TdmUserDTO> converTdmUserDOToUserSearchResultListDTO(List<TdmUserDO> listTdmUserDo)
			throws ServiceException;

	public TdmUserDO converTdmUserDTOToUserDO(TdmUserDTO tdmUserDo, TdmUserAuthDTO tdmUsersAuthDo)
			throws ServiceException;

	public TdmUserDO converTdmUserDTOToUserDO(TdmUserDTO tdmUserDo) throws ServiceException;

	public List<TdmUserDO> converTdmUserDTOToUserDO(List<TdmUserDTO> listTdmUserDo)
			throws ServiceException;
	
	public List<TDMDataCreationDTO> convertServiceRequestDOToTDMDataCreationListDTO(List<ServiceRequestDO> listTdmUserDo)
			throws ServiceException;
	
	public TDMDataCreationDTO convertServiceRequestDOToTDMDataCreationDTO(ServiceRequestDO serviceRequestDO)
			throws ServiceException;
	
	public List<DCAccessLogDTO> convertDCAccessLogListDOToDCAccessLogListDTO(List<DCAccessLogDO> dcAccessLogListDO)
			throws ServiceException;
	
	public DCAccessLogDTO convertDCAccessLogDOToDCAccessLogDTO(DCAccessLogDO dcAccessLogDO)throws ServiceException ;
	
	public ServiceRequestDO convertTdmDatacreationDtoToDO(TDMDataCreationDTO tdmDataCreationDto);
	public    TDMDataCreationDTO  convertDGAutomationDOToDGAutoationDTO(List<DGAutomationScenariosDO> autoandpropertylist);
}
