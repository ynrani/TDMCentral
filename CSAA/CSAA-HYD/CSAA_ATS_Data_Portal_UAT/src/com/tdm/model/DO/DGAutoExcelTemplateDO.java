package com.tdm.model.DO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the DG_AUTO_EXCEL_TEMPLATE database table.
 * 
 */
@Entity
//@NamedNativeQuery(name = "getFunc", query = "{? =  call get_DG_AUTO_Excel_data_fnc(:excelName) }", resultClass = DGAutoExcelTemplateDO.class, hints = {
//	@QueryHint(name = "org.hibernate.callable", value = "true") })
@NamedQueries({
@NamedQuery(name = "DGAutoExcelTemplateDO.getAllDefaultDataByExcelName", query = "SELECT sr FROM DGAutoExcelTemplateDO sr WHERE sr.excelName =:excelName AND sr.category ='Default'  order by sr.sno asc"),
@NamedQuery(name = "DGAutoExcelTemplateDO.findAll", query = "SELECT sr FROM DGAutoExcelTemplateDO sr"),
@NamedQuery(name = "DGAutoExcelTemplateDO.findExcelByName", query = "SELECT sr FROM DGAutoExcelTemplateDO sr WHERE sr.excelName =:excelName order by sr.sno asc"),
@NamedQuery(name = "DGAutoExcelTemplateDO.findColumnByName", query = "SELECT sr FROM DGAutoExcelTemplateDO sr WHERE sr.excelName =:excelName AND upper(sr.columnName) in (upper(:columnName))  order by sr.sno asc"),
 })
@Table(name = "DG_AUTO_EXCEL_TEMPLATE")
public class DGAutoExcelTemplateDO extends TDMObject {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "SNO")
	private Integer sno;
	
	@Column(name = "ENVIRONMENT")
	private String environment;
	
	@Column(name = "EXCEL_NAME")
	private String excelName;
	
	@Column(name = "SHEET_NAME")
	private String sheetName;
	
	@Column(name = "COLUMN_ID")
	private Integer columnId;
	
	@Column(name = "COLUMN_NAME")
	private String columnName;
	
	@Column(name = "CATEGORY")
	private String category;
	
	@Column(name = "DICT_TABLE")
	private String dictTable;

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public String getExcelName() {
		return excelName;
	}

	public void setExcelName(String excelName) {
		this.excelName = excelName;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public Integer getColumnId() {
		return columnId;
	}

	public void setColumnId(Integer columnId) {
		this.columnId = columnId;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public Integer getSno() {
		return sno;
	}

	public void setSno(Integer sno) {
		this.sno = sno;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDictTable() {
		return dictTable;
	}

	public void setDictTable(String dictTable) {
		this.dictTable = dictTable;
	}

}