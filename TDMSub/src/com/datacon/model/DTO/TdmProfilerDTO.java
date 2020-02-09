/*
 * Object Name : TdmProfilerDTO.java
 * Modification Block
 * ------------------------------------------------------------------
 * S.No.	Name 			Date			Bug_Fix_No			Desc
 * ------------------------------------------------------------------
 * 	1.	  vkrish14		12:42:14 PM				Created
 * ------------------------------------------------------------------
 * Copyrights: 2015 Capgemini.com
 */
package com.datacon.model.DTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vkrish14
 *
 */
public class TdmProfilerDTO extends AbstractBaseDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String connectionName;
	private String profilerName;
	private List<String> connectionNames;
	private String selectedConnectionName;
	private List<String> listTables;
	private String selectedTables;
	private List<String> listColumns;
	private String finalTabs;
	private String strUrl;
	private String userName;
	private String password;
	private String userId;
	private List<String> listSelectedTabs = new ArrayList<String>();
	private String strPostMethodTables;
	private String passedConditions;
	private String relationTabs;
	private boolean editflag = false;
	private String startTable;
	public String getConnectionName(){
		return connectionName;
	}
	public void setConnectionName(String connectionName){
		this.connectionName = connectionName;
	}
	public String getProfilerName(){
		return profilerName;
	}
	public void setProfilerName(String profilerName){
		this.profilerName = profilerName;
	}
	public List<String> getConnectionNames(){
		return connectionNames;
	}
	public void setConnectionNames(List<String> connectionNames){
		this.connectionNames = connectionNames;
	}
	public String getSelectedConnectionName(){
		return selectedConnectionName;
	}
	public void setSelectedConnectionName(String selectedConnectionName){
		this.selectedConnectionName = selectedConnectionName;
	}
	public List<String> getListTables(){
		return listTables;
	}
	public void setListTables(List<String> listTables){
		this.listTables = listTables;
	}
	public String getSelectedTables(){
		return selectedTables;
	}
	public void setSelectedTables(String selectedTables){
		this.selectedTables = selectedTables;
	}
	public List<String> getListColumns(){
		return listColumns;
	}
	public void setListColumns(List<String> listColumns){
		this.listColumns = listColumns;
	}
	public String getFinalTabs(){
		return finalTabs;
	}
	public void setFinalTabs(String finalTabs){
		this.finalTabs = finalTabs;
	}
	public String getStrUrl(){
		return strUrl;
	}
	public void setStrUrl(String strUrl){
		this.strUrl = strUrl;
	}
	public String getUserName(){
		return userName;
	}
	public void setUserName(String userName){
		this.userName = userName;
	}
	public String getPassword(){
		return password;
	}
	public void setPassword(String password){
		this.password = password;
	}
	public List<String> getListSelectedTabs(){
		return listSelectedTabs;
	}
	public void setListSelectedTabs(List<String> listSelectedTabs){
		this.listSelectedTabs = listSelectedTabs;
	}
	public String getStrPostMethodTables(){
		return strPostMethodTables;
	}
	public void setStrPostMethodTables(String strPostMethodTables){
		this.strPostMethodTables = strPostMethodTables;
	}
	public String getUserId(){
		return userId;
	}
	public void setUserId(String userId){
		this.userId = userId;
	}
	
	public boolean isEditflag(){
		return editflag;
	}
	public void setEditflag(boolean editflag){
		this.editflag = editflag;
	}
	public String getPassedConditions(){
		return passedConditions;
	}
	public void setPassedConditions(String passedConditions){
		this.passedConditions = passedConditions;
	}
	public String getRelationTabs(){
		return relationTabs;
	}
	public void setRelationTabs(String relationTabs){
		this.relationTabs = relationTabs;
	}
	public String getStartTable(){
		return startTable;
	}
	public void setStartTable(String startTable){
		this.startTable = startTable;
	}
}
