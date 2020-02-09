/*---------------------------------------------------------------------------------------
 * Object Name: TDMNonStandSearchService.Java
 * 
 * --------------------------------------------------------------------------------------
 *
 * Copyright: 2015 Capgemini Financial Services
 *---------------------------------------------------------------------------------------*/

package com.tesda.service;

import java.util.List;

import com.tesda.exception.ServiceException;
import com.tesda.model.DTO.AutoEmailDTO;
import com.tesda.model.DTO.DependentDetailsDTO;
import com.tesda.model.DTO.ForgotPassword;
import com.tesda.model.DTO.TDMLoginUsersDTO;
import com.tesda.model.DTO.TDMNonStandReservationDTO;
import com.tesda.model.DTO.TDMNonStandSearchFieldsDTO;
import com.tesda.model.DTO.TDMNonStandardSearchDTO;

public interface TDMNonStandSearchService
{
	public TDMNonStandSearchFieldsDTO getSearchFields() throws ServiceException;

	public TDMNonStandardSearchDTO getNonStandSearchRecords(TDMNonStandardSearchDTO tdm,
			int offSet, int recordsperpage) throws ServiceException;

	public int saveReservedData(TDMNonStandardSearchDTO tdmNonSrchDTO, String userName)
			throws ServiceException;

	public List<TDMNonStandReservationDTO> getReservedRecords(String role, String userId,
			int offSet, int recordsPerPage) throws ServiceException;

	public int unReserveReservedRecords(TDMNonStandardSearchDTO tdmNonStandSearchDTO, String userId)
			throws ServiceException;

	public List<String> getAccountNameNumberList(String type, String reqToken)
			throws ServiceException;

	public long getNonStandardSearchRecordCount(TDMNonStandardSearchDTO tdmNonStandSearchDTO,
			String userId) throws ServiceException;

	public long getReservedRecordCount(String role, String userId) throws ServiceException;

	public Boolean forgotPassword(ForgotPassword forgotPasswordDTO) throws ServiceException;

	public Boolean l1l2SupportNS(AutoEmailDTO autoEmailDto) throws ServiceException;

	public List<DependentDetailsDTO> getDependentDetails(String subId) throws ServiceException;

	public AutoEmailDTO getUserDetails(AutoEmailDTO autoEmailDto, String userId)
			throws ServiceException;

	public TDMNonStandardSearchDTO getDependentDetailsToExport(
			TDMNonStandardSearchDTO tdmNonStandSearchDTO) throws ServiceException;

	public void saveSearchDetails(TDMNonStandardSearchDTO tdmNonStandSearchDTO, String userId) throws ServiceException;
}
