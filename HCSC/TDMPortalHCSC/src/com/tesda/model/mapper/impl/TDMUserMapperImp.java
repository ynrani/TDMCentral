/*---------------------------------------------------------------------------------------
 * Object Name: TDMUserMapperImp.Java
 * --------------------------------------------------------------------------------------
 *
 * Copyright: 2015 Capgemini Financial Services
 *---------------------------------------------------------------------------------------*/

package com.tesda.model.mapper.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.tesda.constants.TDMConstants;
import com.tesda.model.DO.TDMLoginUsersDO;
import com.tesda.model.DO.TdmUserDO;
import com.tesda.model.DO.TdmUsersAuthDO;
import com.tesda.model.DTO.TDMLoginUsersDTO;
import com.tesda.model.DTO.TdmUserAuthDTO;
import com.tesda.model.DTO.TdmUserDTO;
import com.tesda.model.mapper.TDMUserMapper;

/**
 * Mapper class which is used to convert the DTO objects to corresponding DO objects and
 * vice versa.
 * DTO objects are used to display the data on UI where as DO objects are used to persist the
 * data in data base.
 */
@Component
@Service("tdmUserMapper")
public class TDMUserMapperImp implements TDMUserMapper
{

	@Override
	public TdmUserDTO converTdmUserDOToUserSearchResultDTO(TdmUserDO tdmUserDo,
			TdmUsersAuthDO tdmUsersAuthDo)
	{
		TdmUserDTO tdmUserDTO = new TdmUserDTO();
		TdmUserAuthDTO tdmUserAuthDTO = new TdmUserAuthDTO();
		tdmUserDTO.setCreated(false);
		tdmUserDTO.setEmailId(tdmUserDo.getEmailId());
		tdmUserDTO.setMobileNo(tdmUserDo.getMobileNo());
		tdmUserDTO.setPassword(tdmUserDo.getPassword());
		tdmUserDTO.setUserId(tdmUserDo.getUserId());
		tdmUserDTO.setUsername(tdmUserDo.getUsername());
		tdmUserDTO.setEnabled(tdmUserDo.getEnabled());
		tdmUserAuthDTO.setTdmUserDTO(tdmUserDTO);
		if (tdmUsersAuthDo != null)
			tdmUserAuthDTO.setRole(tdmUsersAuthDo.getRole());

		tdmUserDTO.setTdmUserAuthDTO(tdmUserAuthDTO);
		return tdmUserDTO;
	}

	@Override
	public TdmUserDTO converTdmUserDOToUserSearchResultDTO(TdmUserDO tdmUserDo)
	{
		TdmUserDTO tdmUserDTO = new TdmUserDTO();
		TdmUserAuthDTO tdmUserAuthDTO = new TdmUserAuthDTO();
		tdmUserDTO.setCreated(false);
		tdmUserDTO.setEmailId(tdmUserDo.getEmailId());
		tdmUserDTO.setMobileNo(tdmUserDo.getMobileNo());
		tdmUserDTO.setPassword(tdmUserDo.getPassword());
		tdmUserDTO.setUserId(tdmUserDo.getUserId());
		tdmUserDTO.setUsername(tdmUserDo.getUsername());
		tdmUserDTO.setEnabled(tdmUserDo.getEnabled());
		tdmUserAuthDTO.setTdmUserDTO(tdmUserDTO);
		if (tdmUserDo.getTdmUsersAuths() != null)
			tdmUserAuthDTO.setRole(tdmUserDo.getTdmUsersAuths().getRole());

		tdmUserDTO.setTdmUserAuthDTO(tdmUserAuthDTO);
		return tdmUserDTO;
	}

	@Override
	public List<TdmUserDTO> converTdmUserDOToUserSearchResultListDTO(List<TdmUserDO> listTdmUserDo)
	{
		List<TdmUserDTO> listTdmUserDTO = new ArrayList<TdmUserDTO>();
		for (TdmUserDO tdmUserDO : listTdmUserDo)
		{
			listTdmUserDTO.add(converTdmUserDOToUserSearchResultDTO(tdmUserDO));
		}
		return listTdmUserDTO;
	}

	@Override
	public TdmUserDO converTdmUserDTOToUserDO(TdmUserDTO tdmUserDTo, TdmUserAuthDTO tdmUsersAuthDo)
	{
		TdmUserDO tdmUserDO = new TdmUserDO();
		TdmUsersAuthDO tdmUsersAuthDO = new TdmUsersAuthDO();
		tdmUserDO.setEmailId(tdmUserDTo.getEmailId());
		tdmUserDO.setMobileNo(tdmUserDTo.getMobileNo());
		tdmUserDO.setPassword(tdmUserDTo.getPassword());
		tdmUserDO.setUserId(tdmUserDTo.getUserId());
		tdmUserDO.setUsername(tdmUserDTo.getUsername());
		tdmUserDO.setEnabled(tdmUserDTo.getEnabled());
		tdmUsersAuthDO.setTdmUser(tdmUserDO);
		if (tdmUsersAuthDo != null)
			tdmUsersAuthDO.setRole(tdmUsersAuthDo.getRole());

		tdmUserDO.setTdmUsersAuths(tdmUsersAuthDO);
		return tdmUserDO;
	}

	@Override
	public TdmUserDO converTdmUserDTOToUserDO(TdmUserDTO tdmUserDTO)
	{
		TdmUserDO tdmUserDO = new TdmUserDO();
		TdmUsersAuthDO tdmUsersAuthDO = new TdmUsersAuthDO();
		TdmUserAuthDTO tdmUserAuthDTO = tdmUserDTO.getTdmUserAuthDTO();
		tdmUserDO.setEmailId(tdmUserDTO.getEmailId());
		tdmUserDO.setMobileNo(tdmUserDTO.getMobileNo());
		tdmUserDO.setPassword(tdmUserDTO.getPassword());
		tdmUserDO.setUserId(tdmUserDTO.getUserId());
		tdmUserDO.setUsername(tdmUserDTO.getUsername());
		tdmUserDO.setEnabled(tdmUserDTO.getEnabled());
		tdmUsersAuthDO.setTdmUser(tdmUserDO);
		if (tdmUserAuthDTO != null)
			tdmUsersAuthDO.setRole(tdmUserAuthDTO.getRole());

		tdmUserDO.setTdmUsersAuths(tdmUsersAuthDO);
		return tdmUserDO;
	}

	@Override
	public List<TdmUserDO> converTdmUserDTOToUserDO(List<TdmUserDTO> listTdmUserDTO)
	{
		List<TdmUserDO> listTdmUserDo = new ArrayList<TdmUserDO>();
		for (TdmUserDTO tdmUserDTO : listTdmUserDTO)
		{
			listTdmUserDo.add(converTdmUserDTOToUserDO(tdmUserDTO));
		}
		return listTdmUserDo;
	}

	@Override
	public TDMLoginUsersDO converTdmUserLoginDTOToUserLoginDO(TDMLoginUsersDTO tdmLoginUsersDTO)
	{
		TDMLoginUsersDO loginUsersDO = new TDMLoginUsersDO();
		loginUsersDO.setUserId(tdmLoginUsersDTO.getUserId());
		loginUsersDO.setUserName(tdmLoginUsersDTO.getUserName());
		loginUsersDO.setApplicationName(tdmLoginUsersDTO.getApplicationName());
		loginUsersDO.setEmailId(tdmLoginUsersDTO.getEmailId());
		loginUsersDO.setRole(tdmLoginUsersDTO.getRole());
		loginUsersDO.setFirstLoginDate(new Timestamp(new Date().getTime()));
		loginUsersDO.setLastLoginDate(new Timestamp(new Date().getTime()));
		return loginUsersDO;
	}

	@Override
	public List<TDMLoginUsersDTO> converTdmUserLoginDOToUserLoginDTO(
			List<TDMLoginUsersDO> loginUsersDo)
	{
		SimpleDateFormat format = new SimpleDateFormat(TDMConstants.DDMMYYYY_HHMMSS);
		List<TDMLoginUsersDTO> tdmLoginUserDTO = null;
		if (loginUsersDo != null && loginUsersDo.size() > 0)
		{
			tdmLoginUserDTO = new ArrayList<TDMLoginUsersDTO>();
			for (TDMLoginUsersDO usersDo : loginUsersDo)
			{
				TDMLoginUsersDTO tdmLoginUsersDTO = new TDMLoginUsersDTO();
				tdmLoginUsersDTO.setUserId(usersDo.getUserId());
				tdmLoginUsersDTO.setUserName(usersDo.getUserName());
				tdmLoginUsersDTO.setApplicationName(usersDo.getApplicationName());
				tdmLoginUsersDTO.setEmailId(usersDo.getEmailId());
				tdmLoginUsersDTO.setRole(usersDo.getRole());
				tdmLoginUsersDTO.setFirstLoginDate(format.format(usersDo.getFirstLoginDate()));
				tdmLoginUsersDTO.setLastLoginDate(format.format(usersDo.getLastLoginDate()));
				tdmLoginUsersDTO.setLoginCount(String.valueOf(usersDo.getLoginCount()));

				tdmLoginUserDTO.add(tdmLoginUsersDTO);
			}
		}
		return tdmLoginUserDTO;
	}
}
