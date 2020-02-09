package com.tdm.model.mapper;

import java.util.List;

import com.tdm.model.DO.PolicySummaryStg;
import com.tdm.model.DO.TdmReservationDO;
import com.tdm.model.DTO.TDMAutoPolicySearchDTO;
import com.tdm.model.DTO.TDMPolicyPropertyNewSearchDTO;
import com.tdm.model.DTO.TdmPolicyAutoSearchDTO;
import com.tdm.model.DTO.TdmPolicyAutoSearchResultDTO;
import com.tdm.model.DTO.TdmPolicyPropertySearchDTO;
import com.tdm.model.DTO.TdmPolicyPropertySearchResultDTO;

public interface TdmPolicyAutoPropSearchMapper {

	// /Reservation

	public List<TdmPolicyPropertySearchResultDTO> converPolicysummaryToTdmPolicyPropertySearchDTONew(
			List<PolicySummaryStg> policySummaryStgDOs,
			TdmPolicyPropertySearchDTO tdmPolicyPropertySearchDTO,
			List<TdmReservationDO> reservationDOs, String userName);

	public List<TdmPolicyAutoSearchResultDTO> convertPolicysummaryToTDMAutoSearchResultDTO(
			List<PolicySummaryStg> policySummaryStgDOs,
			TdmPolicyAutoSearchDTO tdmPolicyAutoSearchDTO,
			List<TdmReservationDO> reservationDOs, String userName);

	public List<TdmReservationDO> converTdmPolicyAutoSearchResultDTOToTdmReservationDO(
			List<TdmPolicyAutoSearchResultDTO> tdmPolicyAutoSearchResultDTOList,
			String searchcriti, String userName, String enviro);

	public List<TdmReservationDO> converTdmPolicyPropertySearchDTOToTdmReservationDO(
			List<TdmPolicyPropertySearchResultDTO> tdmPolicyPropertySearchResultDTOList,
			String searchcriti, String userName, String enviro);

	public List<TdmPolicyAutoSearchResultDTO> converTdmReservationDOToFTdmPolicyAutoSearchResultDTO(
			List<TdmReservationDO> tdmReservationDOlist, String userName,
			TdmPolicyAutoSearchDTO tdmPolicyAutoSearchDTO);

	public List<TdmPolicyPropertySearchResultDTO> converTdmReservationDOToTdmPolicyPropertySearchDTO(
			List<TdmReservationDO> tdmReservationDOlist, String userName);

	

	public List<TdmPolicyPropertySearchResultDTO> converAtsPolicysummaryToTdmPolicyPropertySearchDTONew(
			List<PolicySummaryStg> policySummaryStgDOs,
			TDMAutoPolicySearchDTO tdmPolicyPropertySearchDTO,
			List<TdmReservationDO> reservationDOs, String userName);
	
	public List<TdmPolicyPropertySearchResultDTO> converPolicysummaryToTdmPolicyPropertySearchDTONew(
			List<PolicySummaryStg> policySummaryStgDOs,
			TDMPolicyPropertyNewSearchDTO tdmPolicyPropertySearchDTO,
			List<TdmReservationDO> reservationDOs, String userName);
	
	
	public List<TdmPolicyAutoSearchResultDTO> converPolicysummaryToTdmPolicyPropertySearchDTONew(
			List<PolicySummaryStg> policySummaryStgDOs,
			TDMAutoPolicySearchDTO tdmPolicyPropertySearchDTO,
			List<TdmReservationDO> reservationDOs, String userName);
	public List<TdmPolicyAutoSearchResultDTO> converPolicysummaryToTdmPolicySearchDTONew(
			List<PolicySummaryStg> policySummaryStgDOs,
			TDMAutoPolicySearchDTO tdmPolicyPropertySearchDTO, String userName);
	
	public List<TdmPolicyPropertySearchResultDTO> converPolicysummaryToTdmPolicyPropertySearchDTO(
			List<PolicySummaryStg> policySummaryStgDOs,
			TDMPolicyPropertyNewSearchDTO tdmPolicyPropertySearchDTO,
			 String userName);
	
	public List<TdmPolicyPropertySearchResultDTO> convertPropertPolicysummaryToTdmPolicySearchDTONew(
			List<PolicySummaryStg> policySummaryStgDOs,
			TDMPolicyPropertyNewSearchDTO tdmPolicyPropertySearchDTO,
			String userName);
}
