/*---------------------------------------------------------------------------------------
* Object Name: TDMClaimTypeMastDTO.Java
* 
 * Modification Block:
* --------------------------------------------------------------------------------------
* S.No. Name                Date      Bug Fix no. 		Desc
* --------------------------------------------------------------------------------------
* 1     Seshadri Chowdary   11/04/15  NA          		Created* 
* --------------------------------------------------------------------------------------
*
* Copyright: 2015 <CapGemini>
*---------------------------------------------------------------------------------------*/

package com.tesda.model.DTO;

import java.io.Serializable;
import java.math.BigDecimal;

public class TDMClaimTypeMastDTO implements Serializable
{
	private static final long serialVersionUID = 1L;

	private BigDecimal id;

	private String claimTypeDesc;

	public TDMClaimTypeMastDTO()
	{
	}

	public BigDecimal getId()
	{
		return this.id;
	}

	public void setId(BigDecimal id)
	{
		this.id = id;
	}

	public String getClaimTypeDesc()
	{
		return this.claimTypeDesc;
	}

	public void setClaimTypeDesc(String claimTypeDesc)
	{
		this.claimTypeDesc = claimTypeDesc;
	}

}