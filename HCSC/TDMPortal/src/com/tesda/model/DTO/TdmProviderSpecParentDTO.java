/*---------------------------------------------------------------------------------------
* Object Name: TdmProviderSpecParentDTO.Java
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

public class TdmProviderSpecParentDTO implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String providerSpecialityId;

	private String providerSpecialityName;

	public TdmProviderSpecParentDTO()
	{
	}

	public String getProviderSpecialityId()
	{
		return this.providerSpecialityId;
	}

	public void setProviderSpecialityId(String providerSpecialityId)
	{
		this.providerSpecialityId = providerSpecialityId;
	}

	public String getProviderSpecialityName()
	{
		return this.providerSpecialityName;
	}

	public void setProviderSpecialityName(String providerSpecialityName)
	{
		this.providerSpecialityName = providerSpecialityName;
	}

}