package com.tdm.model.mapper;

import java.util.List;

import com.tdm.exception.ServiceException;
import com.tdm.model.DO.DCAccessLogDO;
import com.tdm.model.DO.ServiceRequestDO;
import com.tdm.model.DO.ServiceRequestDetailsDO;
import com.tdm.model.DO.TdmUserDO;
import com.tdm.model.DO.TdmUsersAuthDO;
import com.tdm.model.DTO.DCAccessLogDTO;
import com.tdm.model.DTO.TDMDataCreationDTO;
import com.tdm.model.DTO.TdmUserAuthDTO;
import com.tdm.model.DTO.TdmUserDTO;


public interface TDMDataRequestSaveMapper
{

	public ServiceRequestDO convertTdmDatacreationDtoToDO(TDMDataCreationDTO tdmDataCreationDto);
	
	public TDMDataCreationDTO convertSRDetailListDOToTDMDataCreationDTO(List<ServiceRequestDetailsDO> listServiceRequestDetailsDO)
			throws ServiceException;
	
	
}
