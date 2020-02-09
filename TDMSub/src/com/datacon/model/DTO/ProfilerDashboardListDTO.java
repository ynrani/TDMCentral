package com.datacon.model.DTO;

import java.util.List;

public class ProfilerDashboardListDTO{
	private List<ProfilerDashboardDTO> listProfilerDashboardDTO;

	public List<ProfilerDashboardDTO> getListProfilerDashboardDTO(){
		return listProfilerDashboardDTO;
	}

	public void setListProfilerDashboardDTO(List<ProfilerDashboardDTO> listProfilerDashboardDTO){
		this.listProfilerDashboardDTO = listProfilerDashboardDTO;
	}
}
