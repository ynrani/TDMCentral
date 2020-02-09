/*---------------------------------------------------------------------------------------
 * Object Name: TdmLookupDO.Java
 * 
 * --------------------------------------------------------------------------------------
 *
 * Copyright: 2015 Capgemini Financial Services
 *---------------------------------------------------------------------------------------*/

package com.tesda.model.DO;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.tesda.constants.TDMConstants;

/*
 * The persistent class for the LIST_VALUE_LKP database table.
 */

@Entity
@Table(name = TDMConstants.LIST_VALUE_LKP_TABLE)
@NamedQuery(name = "TdmLookupDO.findAll", query = "SELECT l FROM TdmLookupDO l")
public class TdmLookupDO implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "LIST_VAL_ID")
	private int listValId;

	@Column(name = "LIST_VAL_NM")
	private String listValName;

	@Column(name = "LIST_VAL_CD")
	private String listValCd;

	@Column(name = "LIST_VALUE")
	private String listValue;

	public int getListValId()
	{
		return listValId;
	}

	public void setListValId(int listValId)
	{
		this.listValId = listValId;
	}

	public String getListValName()
	{
		return listValName;
	}

	public void setListValName(String listValName)
	{
		this.listValName = listValName;
	}

	public String getListValCd()
	{
		return listValCd;
	}

	public void setListValCd(String listValCd)
	{
		this.listValCd = listValCd;
	}

	public String getListValue()
	{
		return listValue;
	}

	public void setListValue(String listValue)
	{
		this.listValue = listValue;
	}
}
