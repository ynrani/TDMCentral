/*
 * Object Name : TdmPolicyCenterSearchMapperImpl.java
 * Modification Block
 * ------------------------------------------------------------------
 * S.No.	Name 			Date			Bug_Fix_No			Desc
 * ------------------------------------------------------------------
 * 	1.	  vkrish14		4:11:12 PM				Created
 * ------------------------------------------------------------------
 * Copyrights: 2015 Capgemini.com
 */
package com.tdm.model.mapper.impl;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.tdm.constant.AppConstant;
import com.tdm.exception.ServiceException;
import com.tdm.model.DO.PctlCountryDO;
import com.tdm.model.DO.PctlPolicycontactroleDO;
import com.tdm.model.DO.PctlStateDO;
import com.tdm.model.DO.TdmReservationDO;
import com.tdm.model.DTO.PctlCountryDTO;
import com.tdm.model.DTO.PctlPolicycontactroleDTO;
import com.tdm.model.DTO.PctlStateDTO;
import com.tdm.model.DTO.TdmPolicyCenterSearchDTO;
import com.tdm.model.DTO.TdmPolicyCenterSearchResultDTO;
import com.tdm.model.mapper.TdmPolicyCenterSearchMapper;

/**
 * @author vkrish14
 *
 */
@Component
@Service("tdmPolicyCenterSearchMapper")
public class TdmPolicyCenterSearchMapperImpl implements TdmPolicyCenterSearchMapper{

	@Override
	public TdmPolicyCenterSearchDTO converObjectToTdmPolicyCenterSearchDTO(
			List<Object[]> lstObjects, List<TdmReservationDO> reservationDOs, String userId,Map<Long,String> countriesValues,Map<Long,String> insurerTypes)
			throws ServiceException{
		TdmPolicyCenterSearchDTO tdmPolicyCenterSearchDTO = new TdmPolicyCenterSearchDTO();
		List<TdmPolicyCenterSearchResultDTO> lsttdmPolicyCenterSearchResultDTO = new ArrayList<TdmPolicyCenterSearchResultDTO>();
		List<String> listPolicyNumbers = new ArrayList<String>();
		if(reservationDOs != null && !reservationDOs.isEmpty()){
			for(TdmReservationDO reserve : reservationDOs)
				listPolicyNumbers.add(reserve.getReserveDataId());
		}
		if(null != lstObjects && !lstObjects.isEmpty()){
			for(Object[] objs: lstObjects){
				TdmPolicyCenterSearchResultDTO tdmPolicyCenterSearchResultDTO = new TdmPolicyCenterSearchResultDTO();
				int i=0;
				//for(int i=0;i<objs.length;i++){
				if(objs[i] !=null && StringUtils.isNotEmpty(String.valueOf(objs[i]))){
					if(listPolicyNumbers.contains(String.valueOf(objs[i])) || "Unassigned".equalsIgnoreCase(String.valueOf(objs[i])))
						continue;
					tdmPolicyCenterSearchResultDTO.setPolicynumber(String.valueOf(objs[i]));
					listPolicyNumbers.add(tdmPolicyCenterSearchResultDTO.getPolicynumber());
				}i++;
				if(objs[i] !=null && StringUtils.isNotEmpty(String.valueOf(objs[i])))
					tdmPolicyCenterSearchResultDTO.setAccoutnumber(String.valueOf(objs[i]));
				i++;
				if(objs[i] !=null && StringUtils.isNotEmpty(String.valueOf(objs[i])))
					tdmPolicyCenterSearchResultDTO.setEmailAddress(String.valueOf(objs[i]));
				i++;
				if(objs[i] !=null && StringUtils.isNotEmpty(String.valueOf(objs[i])))
					tdmPolicyCenterSearchResultDTO.setEmailAddress2(String.valueOf(objs[i]));
				i++;
				if(objs[i] !=null && StringUtils.isNotEmpty(String.valueOf(objs[i])))
					tdmPolicyCenterSearchResultDTO.setWorkPhoneNo(String.valueOf(objs[i]));
				i++;
				if(objs[i] !=null && StringUtils.isNotEmpty(String.valueOf(objs[i])))
					tdmPolicyCenterSearchResultDTO.setFirstName(String.valueOf(objs[i]));
				i++;
				if(objs[i] !=null && StringUtils.isNotEmpty(String.valueOf(objs[i])))
					tdmPolicyCenterSearchResultDTO.setLastName(String.valueOf(objs[i]));
				i++;
				if(objs[i] !=null && StringUtils.isNotEmpty(String.valueOf(objs[i])))
					tdmPolicyCenterSearchResultDTO.setAddressLine1(String.valueOf(objs[i]));
				i++;
				if(objs[i] !=null && StringUtils.isNotEmpty(String.valueOf(objs[i])))
					tdmPolicyCenterSearchResultDTO.setAddressLine2(String.valueOf(objs[i]));
				i++;
				if(objs[i] !=null && StringUtils.isNotEmpty(String.valueOf(objs[i])))
					tdmPolicyCenterSearchResultDTO.setAddressLine3(String.valueOf(objs[i]));
				i++;
				if(objs[i] !=null && StringUtils.isNotEmpty(String.valueOf(objs[i])))
					tdmPolicyCenterSearchResultDTO.setState(String.valueOf(objs[i]));
				i++;
				if(objs[i] !=null && StringUtils.isNotEmpty(String.valueOf(objs[i])))
					tdmPolicyCenterSearchResultDTO.setCitydenorm(String.valueOf(objs[i]));
				i++;
				if(objs[i] !=null && StringUtils.isNotEmpty(String.valueOf(objs[i])))
					tdmPolicyCenterSearchResultDTO.setCountry(countriesValues.get(Long.parseLong(String.valueOf(objs[i]))));
				i++;
				if(objs[i] !=null && StringUtils.isNotEmpty(String.valueOf(objs[i])))
					tdmPolicyCenterSearchResultDTO.setCode(String.valueOf(objs[i]));
				i++;
				if(objs[i] !=null && StringUtils.isNotEmpty(String.valueOf(objs[i])))
					tdmPolicyCenterSearchResultDTO.setProductType(String.valueOf(objs[i]));
				i++;
				if(objs[i] !=null && StringUtils.isNotEmpty(String.valueOf(objs[i])))
					tdmPolicyCenterSearchResultDTO.setOriginalEffectiveDate(String.valueOf(objs[i]));
				i++;
					//tdmPolicyCenterSearchResultDTO.setOriginalEffectiveDate(DateUtil.converDateToString((Date)objs[i]));
				if(objs[i] !=null && StringUtils.isNotEmpty(String.valueOf(objs[i])))
					tdmPolicyCenterSearchResultDTO.setExpirationDate(String.valueOf(objs[i]));
				i++;
					//tdmPolicyCenterSearchResultDTO.setExpirationDate(DateUtil.converDateToString((Date)objs[i]));
				if(objs[i] !=null && StringUtils.isNotEmpty(String.valueOf(objs[i])))
					tdmPolicyCenterSearchResultDTO.setInsurerType(insurerTypes.get(Long.parseLong(String.valueOf(objs[i]))));
				i++;
				/*if(objs[i] !=null && StringUtils.isNotEmpty(String.valueOf(objs[i])))
					tdmPolicyCenterSearchResultDTO.setNumberOfViolations(Integer.parseInt(""+objs[i]));
				i++;
				if(objs[i] !=null && StringUtils.isNotEmpty(String.valueOf(objs[i])))
					tdmPolicyCenterSearchResultDTO.setNumberOfAccidents(Integer.parseInt(""+objs[i]));
				i++;
				if(objs[i] !=null && StringUtils.isNotEmpty(String.valueOf(objs[i])))
					tdmPolicyCenterSearchResultDTO.setCarrier(String.valueOf(objs[i]));
				i++;*/
					
					lsttdmPolicyCenterSearchResultDTO.add(tdmPolicyCenterSearchResultDTO);
				//}
			}
			tdmPolicyCenterSearchDTO.setListTdmPolicyCenterSearchResultDTO(lsttdmPolicyCenterSearchResultDTO);
		}
		return tdmPolicyCenterSearchDTO;
	}
	
	@Override
	public TdmPolicyCenterSearchDTO converObjectToTdmPolicyCenterSearchDTOForReservation(
			List<Object[]> lstObjects, List<TdmReservationDO> reservationDOs, String userId,Map<Long,String> mapCountries,Map<Long,String> insurerTypes)
			throws ServiceException{
		TdmPolicyCenterSearchDTO tdmPolicyCenterSearchDTO = new TdmPolicyCenterSearchDTO();
		List<TdmPolicyCenterSearchResultDTO> lsttdmPolicyCenterSearchResultDTO = new ArrayList<TdmPolicyCenterSearchResultDTO>();
		DateFormat myFormat = new SimpleDateFormat(AppConstant.MMDDYYYY);
		Calendar cal = Calendar.getInstance();
		Date date2 = cal.getTime();
		List<String> listPolicyNumbers = new ArrayList<String>();
		if(null != lstObjects && !lstObjects.isEmpty()){
			for(Object[] objs: lstObjects){
				TdmPolicyCenterSearchResultDTO tdmPolicyCenterSearchResultDTO = new TdmPolicyCenterSearchResultDTO();
				int i=0;
				if(objs[i] !=null && StringUtils.isNotEmpty(String.valueOf(objs[i])) ){
					if(listPolicyNumbers.contains(String.valueOf(objs[i])))
						continue;
					tdmPolicyCenterSearchResultDTO.setPolicynumber(String.valueOf(objs[i]));
					listPolicyNumbers.add(tdmPolicyCenterSearchResultDTO.getPolicynumber());
				}i++;
				if(objs[i] !=null && StringUtils.isNotEmpty(String.valueOf(objs[i])))
					tdmPolicyCenterSearchResultDTO.setAccoutnumber(String.valueOf(objs[i]));
				i++;
				if(objs[i] !=null && StringUtils.isNotEmpty(String.valueOf(objs[i])))
					tdmPolicyCenterSearchResultDTO.setEmailAddress(String.valueOf(objs[i]));
				i++;
				if(objs[i] !=null && StringUtils.isNotEmpty(String.valueOf(objs[i])))
					tdmPolicyCenterSearchResultDTO.setEmailAddress2(String.valueOf(objs[i]));
				i++;
				if(objs[i] !=null && StringUtils.isNotEmpty(String.valueOf(objs[i])))
					tdmPolicyCenterSearchResultDTO.setWorkPhoneNo(String.valueOf(objs[i]));
				i++;
				if(objs[i] !=null && StringUtils.isNotEmpty(String.valueOf(objs[i])))
					tdmPolicyCenterSearchResultDTO.setFirstName(String.valueOf(objs[i]));
				i++;
				if(objs[i] !=null && StringUtils.isNotEmpty(String.valueOf(objs[i])))
					tdmPolicyCenterSearchResultDTO.setLastName(String.valueOf(objs[i]));
				i++;
				if(objs[i] !=null && StringUtils.isNotEmpty(String.valueOf(objs[i])))
					tdmPolicyCenterSearchResultDTO.setAddressLine1(String.valueOf(objs[i]));
				i++;
				if(objs[i] !=null && StringUtils.isNotEmpty(String.valueOf(objs[i])))
					tdmPolicyCenterSearchResultDTO.setAddressLine2(String.valueOf(objs[i]));
				i++;
				if(objs[i] !=null && StringUtils.isNotEmpty(String.valueOf(objs[i])))
					tdmPolicyCenterSearchResultDTO.setAddressLine3(String.valueOf(objs[i]));
				i++;
				if(objs[i] !=null && StringUtils.isNotEmpty(String.valueOf(objs[i])))
					tdmPolicyCenterSearchResultDTO.setState(String.valueOf(objs[i]));
				i++;
				if(objs[i] !=null && StringUtils.isNotEmpty(String.valueOf(objs[i])))
					tdmPolicyCenterSearchResultDTO.setCitydenorm(String.valueOf(objs[i]));
				i++;
				if(objs[i] !=null && StringUtils.isNotEmpty(String.valueOf(objs[i])))
					tdmPolicyCenterSearchResultDTO.setCountry(mapCountries.get(Long.parseLong(String.valueOf(objs[i]))));
				i++;
				if(objs[i] !=null && StringUtils.isNotEmpty(String.valueOf(objs[i])))
					tdmPolicyCenterSearchResultDTO.setCode(String.valueOf(objs[i]));
				i++;
				if(objs[i] !=null && StringUtils.isNotEmpty(String.valueOf(objs[i])))
					tdmPolicyCenterSearchResultDTO.setProductType(String.valueOf(objs[i]));
				i++;
				if(objs[i] !=null && StringUtils.isNotEmpty(String.valueOf(objs[i])))
					tdmPolicyCenterSearchResultDTO.setOriginalEffectiveDate(String.valueOf(objs[i]));
				i++;
					//tdmPolicyCenterSearchResultDTO.setOriginalEffectiveDate(DateUtil.converDateToString((Date)objs[i]));
				if(objs[i] !=null && StringUtils.isNotEmpty(String.valueOf(objs[i])))
					tdmPolicyCenterSearchResultDTO.setExpirationDate(String.valueOf(objs[i]));
				i++;
					//tdmPolicyCenterSearchResultDTO.setExpirationDate(DateUtil.converDateToString((Date)objs[i]));
				if(objs[i] !=null && StringUtils.isNotEmpty(String.valueOf(objs[i])))
					tdmPolicyCenterSearchResultDTO.setInsurerType(insurerTypes.get(Long.parseLong(String.valueOf(objs[i]))));
				i++;
				if (null != reservationDOs && 0 < reservationDOs.size()) {
					for (int j = 0; j < reservationDOs.size(); j++) {
						if (null != reservationDOs.get(j).getReserveDataType()
								&& reservationDOs.get(j).getReserveDataType()
										.equalsIgnoreCase("PC")) {
							if (tdmPolicyCenterSearchResultDTO.getPolicynumber().equalsIgnoreCase(reservationDOs.get(j).getReserveDataId())) {
								tdmPolicyCenterSearchResultDTO.setReservedYN(AppConstant.YES);
								tdmPolicyCenterSearchResultDTO.setTestCaseId(reservationDOs.get(j)
										.getTestCaseId());
								tdmPolicyCenterSearchResultDTO.setTestCaseName(reservationDOs
										.get(j).getTestCaseName());
								tdmPolicyCenterSearchResultDTO.setUserId(reservationDOs.get(j)
										.getUserId());
								Date date1 = reservationDOs.get(j).getUnlockTime();
								long diff = date2.getTime() - date1.getTime();
								switch ((int) (TimeUnit.DAYS.convert(diff,
										TimeUnit.MILLISECONDS))) {
									case 0:
										date1 = DateUtils.addDays(date1, 5);
										break;
									case 1:
										date1 = DateUtils.addDays(date1, 4);
										break;
									case 2:
										date1 = DateUtils.addDays(date1, 3);
										break;
									case 3:
										date1 = DateUtils.addDays(date1, 2);
										break;
									case 4:
										date1 = DateUtils.addDays(date1, 1);
										break;
									case 5:
										date1 = DateUtils.addDays(date1, 0);
										break;
									default:
										break;
								}
								tdmPolicyCenterSearchResultDTO
										.setRevExpairDate(myFormat.format(date1));
							}
						}
					}
				}
					
					lsttdmPolicyCenterSearchResultDTO.add(tdmPolicyCenterSearchResultDTO);
				//}
			}
			tdmPolicyCenterSearchDTO.setListTdmPolicyCenterSearchResultDTO(lsttdmPolicyCenterSearchResultDTO);
		}
		return tdmPolicyCenterSearchDTO;
	}

	@Override
	public List<TdmReservationDO> converTdmPolicyCenterSearchDTOToTdmReservationDO(
			List<TdmPolicyCenterSearchResultDTO> lsttdmPolicyCenterSearchResultDTO, String userName,String searchcriti)
			throws ServiceException{
		List<TdmReservationDO> reservationDOList = null;
		if (null != lsttdmPolicyCenterSearchResultDTO
				&& 0 < lsttdmPolicyCenterSearchResultDTO.size()) {
			TdmReservationDO reservationDO = null;
			TdmPolicyCenterSearchResultDTO tdmPolicyCenterSearchResultDTO = null;
			reservationDOList = new ArrayList<TdmReservationDO>();
			for (int i = 0; i < lsttdmPolicyCenterSearchResultDTO.size(); i++) {
				tdmPolicyCenterSearchResultDTO = lsttdmPolicyCenterSearchResultDTO.get(i);
				if (null != tdmPolicyCenterSearchResultDTO.getReservedYN()) {
					reservationDO = new TdmReservationDO();
					if (null != tdmPolicyCenterSearchResultDTO.getPolicynumber()) {
						reservationDO.setReserveDataId(tdmPolicyCenterSearchResultDTO
								.getPolicynumber());
					}
					reservationDO.setLocked("Y");
					reservationDO.setUserId(userName);
					reservationDO.setUnreserve("N");
					reservationDO.setReserveData(searchcriti.length()>19 ? searchcriti.substring(18) : searchcriti);

					reservationDO.setReserveDataType("PC");
					reservationDO.setUnlockTime(new Timestamp(new Date().getTime()));
					reservationDOList.add(reservationDO);
				}
			}
		}
		return reservationDOList;
	}

	@Override
	public List<PctlStateDTO> convertgetStateCodesDOTOgetStateCodesDTOS(List<PctlStateDO> listResult)throws ServiceException{
		List<PctlStateDTO> listResultTemp = new ArrayList<PctlStateDTO>();
		for(PctlStateDO pctlStateDO :listResult){
			PctlStateDTO pctlStateDTO = new PctlStateDTO();
			BeanUtils.copyProperties(pctlStateDO, pctlStateDTO);
			listResultTemp.add(pctlStateDTO);
		}
		return listResultTemp;
	}

	@Override
	public List<PctlCountryDTO> convertCountryDOTOCountryDTOS(List<PctlCountryDO> listResult)throws ServiceException{
		List<PctlCountryDTO> listResultTemp = new ArrayList<PctlCountryDTO>();
		for(PctlCountryDO pctlCountryDO :listResult){
			PctlCountryDTO pctlStateDTO = new PctlCountryDTO();
			BeanUtils.copyProperties(pctlCountryDO, pctlStateDTO);
			listResultTemp.add(pctlStateDTO);
		}
		return listResultTemp;
	}

	@Override
	public List<PctlPolicycontactroleDTO> convertPctlPolicycontactroleDOTOPctlPolicycontactroleDTOS(
			List<PctlPolicycontactroleDO> listResult) throws ServiceException{
		List<PctlPolicycontactroleDTO> listResultTemp = new ArrayList<PctlPolicycontactroleDTO>();
		for(PctlPolicycontactroleDO pctlPolicycontactroleDO :listResult){
			PctlPolicycontactroleDTO pctlPolicycontactroleDTO = new PctlPolicycontactroleDTO();
			BeanUtils.copyProperties(pctlPolicycontactroleDO, pctlPolicycontactroleDTO);
			listResultTemp.add(pctlPolicycontactroleDTO);
		}
		return listResultTemp;
		
	}
}
