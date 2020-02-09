/*---------------------------------------------------------------------------------------
 * Object Name: TDMNonStandardSearchMapper.Java
 * 
 * --------------------------------------------------------------------------------------
 *
 * Copyright: 2015 Capgemini Financial Services
 *---------------------------------------------------------------------------------------*/

package com.tesda.model.mapper;

import java.util.List;

import com.tesda.model.DO.TdmReservationDO;
import com.tesda.model.DO.TdmSubscriberDetailsDO;
import com.tesda.model.DTO.DependentDetailsDTO;
import com.tesda.model.DTO.TDMNonStandReservationDTO;
import com.tesda.model.DTO.TDMNonStandardSearchDTO;
import com.tesda.model.DTO.TdmNonStandardSearchResultListDTO;

public interface TDMNonStandardSearchMapper
{
	List<TdmNonStandardSearchResultListDTO> converTdmSbscrDtlsDOtoTDMNonStandardSearchDTO(
			TDMNonStandardSearchDTO tdmSearchDTO, List<TdmSubscriberDetailsDO> tdmOnboardReqDOs);

	List<TdmNonStandardSearchResultListDTO> converTdmSubscrbrDtlsDOtoTDMNonStandardSearchDTO(
			TDMNonStandardSearchDTO tdmSearchDTO, List<Object[]> tdmOnboardReqDOs);

	List<TdmReservationDO> converTDMNonStandardSearchDTOtoTdmReservationDO(
			TDMNonStandardSearchDTO tdmNonSrchDTO, String userId);

	List<TDMNonStandReservationDTO> convertReservationDosToResvationDTO(
			List<TdmReservationDO> reservedList);

	List<TdmReservationDO> convertReservationDTOsToResvationDO(
			TDMNonStandardSearchDTO tdmNonStandSearchDTO);

	List<DependentDetailsDTO> convertDependentDetailsDoToDtos(List<Object[]> depenDetails);
}
