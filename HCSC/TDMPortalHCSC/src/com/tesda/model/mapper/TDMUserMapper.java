/*---------------------------------------------------------------------------------------
 * Object Name: TDMAdminServiceImpl.Java
 * --------------------------------------------------------------------------------------
 *
 * Copyright: 2015 Capgemini Financial Services
 *---------------------------------------------------------------------------------------*/

package com.tesda.model.mapper;

import java.util.List;

import com.tesda.model.DO.TDMLoginUsersDO;
import com.tesda.model.DO.TdmUserDO;
import com.tesda.model.DO.TdmUsersAuthDO;
import com.tesda.model.DTO.TDMLoginUsersDTO;
import com.tesda.model.DTO.TdmUserAuthDTO;
import com.tesda.model.DTO.TdmUserDTO;

/**
 * TDMUserMapper is the Mapper class which is used to convert the DTO objects to corresponding DO objects and
 * vice versa.
 */
public interface TDMUserMapper
{

	public TdmUserDTO converTdmUserDOToUserSearchResultDTO(TdmUserDO tdmUserDo,
			TdmUsersAuthDO tdmUsersAuthDo);

	public TdmUserDTO converTdmUserDOToUserSearchResultDTO(TdmUserDO tdmUserDo);

	public List<TdmUserDTO> converTdmUserDOToUserSearchResultListDTO(List<TdmUserDO> listTdmUserDo);

	public TdmUserDO converTdmUserDTOToUserDO(TdmUserDTO tdmUserDo, TdmUserAuthDTO tdmUsersAuthDo);

	public TdmUserDO converTdmUserDTOToUserDO(TdmUserDTO tdmUserDo);

	public List<TdmUserDO> converTdmUserDTOToUserDO(List<TdmUserDTO> listTdmUserDo);

	public TDMLoginUsersDO converTdmUserLoginDTOToUserLoginDO(TDMLoginUsersDTO tdmLoginUsersDTO);

	public List<TDMLoginUsersDTO> converTdmUserLoginDOToUserLoginDTO(
			List<TDMLoginUsersDO> loginUsersDo);
}
