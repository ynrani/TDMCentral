package com.tdm.constant;

public enum UIScreenENUM {
	AUTO_DATA_SEARCH("AUTO_PROPERTY_SEARCH","'Auto Policy Status', 'Auto Payment Plan', 'Auto Vehicle level coverage','Policy Term', 'Environment', 'Auto Risk State','Auto Product Type','Auto Policy Level Coverage'"),
	PROPERTY_DATA_SEARCH("PROPERTY_DATA_SEARCH","'Environment','Property Product Type','Property Policy Status','Property Risk State','Property Payment Plan','Property Coverage','Property Policy Type'"),
	DG_AUTOMATION_DATA("DG_AUTOMATION_DATA","'Auto Policy Status', 'Auto Payment Plan','Environment', 'Auto Risk State','Property Risk State','Property Payment Plan','Property Policy Type','Automation Auto Scenarios','Automation Property Scenarios'"),
	DG_MANUAL_DATA("DG_MANUAL_DATA","'Auto Policy Status', 'Auto Payment Plan','Environment', 'Auto Risk State','Property Risk State','Property Payment Plan','Property Policy Type'");
    
	private String value;
	private String dataDict;
	private UIScreenENUM(String value,String dataDict){  
		this.value=value;  
		this.dataDict=dataDict;
	}  
	
	public String getPageName() {
		return value;
	}
	public String getDataDict()
	{
		return dataDict;
	}
}
