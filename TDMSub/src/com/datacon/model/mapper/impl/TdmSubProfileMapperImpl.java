package com.datacon.model.mapper.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.datacon.model.DO.DataConConnectionsDO;
import com.datacon.model.DO.ProjectDO;
import com.datacon.model.DO.SubsetCriteriaDO;
import com.datacon.model.DO.TdmUserDO;
import com.datacon.model.DTO.ProfilerDashboardDTO;
import com.datacon.model.DTO.ProfilerDashboardListDTO;
import com.datacon.model.DTO.TdmProfilerDTO;
import com.datacon.model.mapper.TdmSubProfileMapper;

@Service("tdmSubProfileMapper")
public class TdmSubProfileMapperImpl implements TdmSubProfileMapper{
	@Override
	public ProjectDO convertTdmProfileDTOTOProjectDO(TdmProfilerDTO tdmProfilerDTO,
			String requestValues){
		ProjectDO dos = new ProjectDO();
		SubsetCriteriaDO subsetDo = new SubsetCriteriaDO();
		TdmUserDO userDo = new TdmUserDO();
		DataConConnectionsDO connectionDO = new DataConConnectionsDO();
		if(tdmProfilerDTO != null){
			dos.setProjectName(tdmProfilerDTO.getProfilerName());
			dos.setDateCreated(new Timestamp(new Date().getTime()));
			
			userDo.setUserId(tdmProfilerDTO.getUserId());
			
			connectionDO.setConnectionName(tdmProfilerDTO.getSelectedConnectionName());
			
			subsetDo.setDataConConnection(connectionDO);
			subsetDo.setProject(dos);
			subsetDo.setSubsetCondTab(tdmProfilerDTO.getFinalTabs());
			subsetDo.setSubsetCondCol(requestValues);
			subsetDo.setStartTable(tdmProfilerDTO.getStartTable());
			
			List<SubsetCriteriaDO> listResult = new ArrayList<SubsetCriteriaDO>();
			listResult.add(subsetDo);
			dos.setUser(userDo);
			dos.setSubsetCriterias(listResult);
		}
		return dos;
	}

	@Override
	public ProfilerDashboardListDTO convertProjectDOToProfilerDashboardListDTO(
			List<ProjectDO> listProjectDO){
		ProfilerDashboardListDTO listDto =  new ProfilerDashboardListDTO();
		List<ProfilerDashboardDTO> listChildVals = new ArrayList<ProfilerDashboardDTO>();
		if(listProjectDO != null && !listProjectDO.isEmpty()){
			for(ProjectDO projectDO : listProjectDO){
				ProfilerDashboardDTO dto = new ProfilerDashboardDTO();
				dto.setProfilerId(projectDO.getProjectId());
				dto.setProfilerName(projectDO.getProjectName());
				dto.setConnectionName(projectDO.getSubsetCriterias().get(0).getDataConConnection().getConnectionName());
				dto.setCreatedBy(projectDO.getUser().getUsername());
				dto.setSelectedTables(projectDO.getSubsetCriterias().get(0).getSubsetCondTab());
				
				listChildVals.add(dto);
			}
			listDto.setListProfilerDashboardDTO(listChildVals);
		}
		return listDto;
	}

	@Override
	public TdmProfilerDTO covertProjectDOToTDMProfilerDTO(ProjectDO projectDo){
		TdmProfilerDTO dos = new TdmProfilerDTO();
		if(projectDo != null){
			dos.setProfilerName(projectDo.getProjectName());
			dos.setConnectionName(projectDo.getSubsetCriterias().get(0).getDataConConnection().getConnectionName());
			dos.setFinalTabs(projectDo.getSubsetCriterias().get(0).getSubsetCondTab());
			
			dos.setPassedConditions(projectDo.getSubsetCriterias().get(0).getSubsetCondCol());
			dos.setSelectedConnectionName(projectDo.getSubsetCriterias().get(0).getDataConConnection().getConnectionName());
			dos.setSelectedTables(projectDo.getSubsetCriterias().get(0).getSubsetCondTab());
			
			//dos.setDateCreated(new Timestamp(new Date().getTime()));
			
		}
		return dos;
	}
}
