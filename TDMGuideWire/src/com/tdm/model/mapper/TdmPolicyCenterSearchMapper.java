package com.tdm.model.mapper;

import java.util.List;
import java.util.Map;

import com.tdm.exception.ServiceException;
import com.tdm.model.DO.PctlCountryDO;
import com.tdm.model.DO.PctlPolicycontactroleDO;
import com.tdm.model.DO.PctlStateDO;
import com.tdm.model.DO.TdmReservationDO;
import com.tdm.model.DTO.PctlCountryDTO;
import com.tdm.model.DTO.PctlPolicycontactroleDTO;
import com.tdm.model.DTO.PctlStateDTO;
import com.tdm.model.DTO.TdmPolicyCenterSearchDTO;
import com.tdm.model.DTO.TdmPolicyCenterSearchResultDTO;

public interface TdmPolicyCenterSearchMapper
{

	public TdmPolicyCenterSearchDTO converObjectToTdmPolicyCenterSearchDTO(
			List<Object[]> lstObjects, List<TdmReservationDO> reservationDOs, String userId, Map<Long, String> mapCountries, Map<Long, String> mapInsurers)
			throws ServiceException;

	public List<TdmReservationDO> converTdmPolicyCenterSearchDTOToTdmReservationDO(
			List<TdmPolicyCenterSearchResultDTO> lsttdmPolicyCenterSearchResultDTO, String userName,String searchcriti)
			throws ServiceException;

	TdmPolicyCenterSearchDTO converObjectToTdmPolicyCenterSearchDTOForReservation(
			List<Object[]> lstObjects, List<TdmReservationDO> reservationDOs, String userId,Map<Long,String> mapCountries, Map<Long, String> mapInsurers)
			throws ServiceException;

	public List<PctlStateDTO> convertgetStateCodesDOTOgetStateCodesDTOS(List<PctlStateDO> listResult)throws ServiceException;

	public List<PctlCountryDTO> convertCountryDOTOCountryDTOS(List<PctlCountryDO> listResult)throws ServiceException;

	public List<PctlPolicycontactroleDTO> convertPctlPolicycontactroleDOTOPctlPolicycontactroleDTOS(
			List<PctlPolicycontactroleDO> listResult)throws ServiceException;


}
