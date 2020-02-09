package com.tesda.model.mapper;

import java.util.List;
import java.util.Map;

import com.tesda.exception.ServiceException;
import com.tesda.model.DO.ReqChDO;
import com.tesda.model.DO.RequestorDO;
import com.tesda.model.DO.TdmOnboardReqDO;
import com.tesda.model.DTO.TdmDataMaskingDTO;
import com.tesda.model.DTO.TdmOnBoardReqDTO;

/**
 * TDMDataMaskingMapper is the Mapper class which is used to convert the DTO objects to corresponding DO objects and
 * vice versa.
 */
public interface TDMDataMaskingMapper
{

	public RequestorDO convertMaskDTOtoDO(RequestorDO requestorDO,
			TdmDataMaskingDTO tdgDataMaskingDTO, String seq, boolean page1, boolean page2,
			boolean page3, boolean reqTypeCR) throws ServiceException;

	public TdmDataMaskingDTO convertDOtoMaskDTO(TdmDataMaskingDTO tdgDataMaskingDTO,
			RequestorDO requestorDO, boolean page1, boolean page2, boolean page3)
			throws ServiceException;

	public TdmDataMaskingDTO convertDOtoMaskDTOs(TdmDataMaskingDTO tdgDataMaskingDTO,
			List<RequestorDO> requestorDOs, boolean b, boolean c, boolean d)
			throws ServiceException;

	public TdmOnBoardReqDTO convertTdmOnboardReqDOToDO(TdmOnboardReqDO tdmOnboardReqDO)
			throws ServiceException;

	public TdmOnboardReqDO convertTdmOnBoardReqDTOToDO(TdmOnBoardReqDTO tdmOnboardReqDTO, String seq)
			throws ServiceException;

	public List<TdmDataMaskingDTO> converTdmOnboardReqDOtoDTO(List<TdmOnboardReqDO> requestorDOs)
			throws ServiceException;

	public TdmDataMaskingDTO converDOtoRequestorDTO(List<RequestorDO> requestorDOs);

	public TdmDataMaskingDTO converDOtoRequestDTOForReqId(Map<RequestorDO, List<ReqChDO>> map);
}
