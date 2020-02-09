/*---------------------------------------------------------------------------------------
 * Object Name: TDGDataMaskingService.Java
 * 
 * --------------------------------------------------------------------------------------
 *
 * Copyright: 2015 Capgemini Financial Services
 *---------------------------------------------------------------------------------------*/

package com.tesda.service;

import java.util.List;

import com.tesda.exception.ServiceException;
import com.tesda.model.DTO.TdmDataMaskingDTO;
import com.tesda.model.DTO.TdmOnBoardReqDTO;

public interface TDMDataMaskingService
{
	// public String saveMaskingData(TdgDataMaskingDTO tdgDataMaskingDTO) throws
	// ServiceException;

	public TdmDataMaskingDTO saveReqDtls(TdmDataMaskingDTO tdgDataMaskingDTO, boolean page1,
			boolean page2, boolean page3) throws ServiceException;

	public TdmDataMaskingDTO getSavedDtls(TdmDataMaskingDTO tdgDataMaskingDTO, boolean page1,
			boolean page2, boolean page3) throws ServiceException;

	public TdmDataMaskingDTO getAllDtMaskRequestedRecord(String userId) throws ServiceException;

	public TdmDataMaskingDTO getDtMaskRequestedRecord(String userId, String reqId)
			throws ServiceException;

	public TdmDataMaskingDTO getAllDtMaskRequestedRecordForPagination(int offSet,
			int recordsperpage, boolean pageNationOnOffFlag, TdmDataMaskingDTO tdgDataMaskingDTO,
			String type) throws ServiceException;

	public Long getReservedRecordsCount(String role, String userId, String type)
			throws ServiceException;

	public TdmDataMaskingDTO getUserDetails(TdmDataMaskingDTO tdgDataMaskingDTO)
			throws ServiceException;

	public TdmOnBoardReqDTO getUserDetails(String userId, TdmOnBoardReqDTO tdmOnBoardReqDTO)
			throws ServiceException;

	public TdmOnBoardReqDTO getSaveOnboardingReq(TdmOnBoardReqDTO tdmOnboardReqDTO)
			throws ServiceException;

	public TdmOnBoardReqDTO getEditableDetails(String reqId) throws ServiceException;

	public List<String> getReqIdList(String userId, String reqIdtoken) throws ServiceException;

	public Long getReservedRecordsCountOnBoard(String role, String userId, String type)
			throws ServiceException;

	public List<TdmDataMaskingDTO> getAllOnBoardRequestedRecordForPagination(String role,
			int offSet, int recordsperpage, boolean pageNationOnOffFlag, String userId, String type)
			throws ServiceException;

	public TdmDataMaskingDTO getSavedDtlsforExport(String role,
			TdmDataMaskingDTO tdgDataMaskingDTO, boolean page1, boolean page2, boolean page3,
			String type) throws ServiceException;

	public boolean cancelOnBoardingRequest(String reqId) throws ServiceException;

	public boolean cancelMaskingRequest(String reqId) throws ServiceException;

	public boolean dtMaskCancelReq(String reqId) throws ServiceException;

	public boolean getCheckExistingReqYesNo(TdmDataMaskingDTO tdgDataMaskingDTO)
			throws ServiceException;

	public boolean deleteRow(String reqType, String rowId) throws ServiceException;
}
