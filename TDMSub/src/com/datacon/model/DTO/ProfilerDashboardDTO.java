package com.datacon.model.DTO;

public class ProfilerDashboardDTO{
	private String profilerName;
	private String connectionName;
	private String createdBy;
	private String selectedTables;
	private String profilerId;
	
	public String getProfilerName(){
		return profilerName;
	}
	public void setProfilerName(String profilerName){
		this.profilerName = profilerName;
	}
	public String getConnectionName(){
		return connectionName;
	}
	public void setConnectionName(String connectionName){
		this.connectionName = connectionName;
	}
	public String getCreatedBy(){
		return createdBy;
	}
	public void setCreatedBy(String createdBy){
		this.createdBy = createdBy;
	}
	public String getSelectedTables(){
		return selectedTables;
	}
	public void setSelectedTables(String selectedTables){
		this.selectedTables = selectedTables;
	}
	public String getProfilerId(){
		return profilerId;
	}
	public void setProfilerId(String profilerId){
		this.profilerId = profilerId;
	}
	
	
	
}
