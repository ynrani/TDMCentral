package com.datacon.model.mapper;

import java.util.List;

import com.datacon.model.DO.ProjectDO;
import com.datacon.model.DTO.ProfilerDashboardListDTO;
import com.datacon.model.DTO.TdmProfilerDTO;

public interface TdmSubProfileMapper{

	 ProjectDO convertTdmProfileDTOTOProjectDO(TdmProfilerDTO tdmProfilerDTO, String requestValues);

	ProfilerDashboardListDTO convertProjectDOToProfilerDashboardListDTO(
			List<ProjectDO> listProjectDO);

	TdmProfilerDTO covertProjectDOToTDMProfilerDTO(ProjectDO projectDo);
}
