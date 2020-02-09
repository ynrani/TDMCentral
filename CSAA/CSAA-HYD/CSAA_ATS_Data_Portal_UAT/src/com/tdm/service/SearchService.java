package com.tdm.service;

import java.util.List;
import java.util.Map;

import com.tdm.model.DTO.FieldListDTO;
import com.tdm.model.DTO.TDMDataCreationDTO;

public interface SearchService {

	public Map<String, List<FieldListDTO>> getAutoSearchPageData(String tableName);
	public   TDMDataCreationDTO getAllDetails();
	public   TDMDataCreationDTO getServiceRequestByReqId(String requestId);
	public   TDMDataCreationDTO getReqDetailDataByReqId(String requestId);
	public    TDMDataCreationDTO    getAutoAndPropertyScenarios();
}
