package com.tdm.model.DTO;

import java.util.List;

public class TdmPolicyPropertySearchResultJsonDTO {
	
	int iTotalRecords;

    int iTotalDisplayRecords;

    String sEcho;

    String sColumns;
    
    List<TdmPolicyPropertySearchResultDTO> aaData;

	public int getiTotalRecords() {
		return iTotalRecords;
	}

	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}

	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

	public String getsEcho() {
		return sEcho;
	}

	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}

	public String getsColumns() {
		return sColumns;
	}

	public void setsColumns(String sColumns) {
		this.sColumns = sColumns;
	}

	public List<TdmPolicyPropertySearchResultDTO> getAaData() {
		return aaData;
	}

	public void setAaData(List<TdmPolicyPropertySearchResultDTO> aaData) {
		this.aaData = aaData;
	}
    
    

}
