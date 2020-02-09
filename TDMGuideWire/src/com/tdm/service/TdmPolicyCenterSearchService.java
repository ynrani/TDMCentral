package com.tdm.service;

import java.util.List;

import com.tdm.exception.ServiceException;
import com.tdm.model.DTO.PctlCountryDTO;
import com.tdm.model.DTO.PctlPolicycontactroleDTO;
import com.tdm.model.DTO.PctlStateDTO;
import com.tdm.model.DTO.TdmPolicyCenterSearchDTO;

public interface TdmPolicyCenterSearchService
{

	public int saveReservedData(TdmPolicyCenterSearchDTO tdmPolicyCenterSearchDTO,
			String userName, String enviro) throws ServiceException;

	public TdmPolicyCenterSearchDTO searchPolicyCenter(
			TdmPolicyCenterSearchDTO tdmPolicyCenterSearchDTO, int offSet, int recordsperpage,
			boolean pageNationOnOffFlag, String userName) throws ServiceException;

	public TdmPolicyCenterSearchDTO searchPolicyRecordsByPolicySearchNew(
			TdmPolicyCenterSearchDTO tdmPolicyCenterSearchDTO, int offSet, int recordsperpage,
			boolean b, String userName);

	public TdmPolicyCenterSearchDTO getReservedRecordForUser(String userId, int offSet,
			int recordsperpage, boolean b) throws ServiceException;

	public Long getReservedRecordsCount(Object object, String userId) throws ServiceException;

	public Boolean unReservedRecordForUser(String parameter) throws ServiceException;

	public List<PctlCountryDTO> getCountrycodeValues() throws ServiceException;

	public List<PctlStateDTO> getStatecodeValues() throws ServiceException;

	Long getTotalRecordsCount(TdmPolicyCenterSearchDTO tdmPolicyCenterSearchDTO, String userName)
			throws ServiceException;

	public List<PctlPolicycontactroleDTO> getInsurerTypeValues() throws ServiceException;

}
