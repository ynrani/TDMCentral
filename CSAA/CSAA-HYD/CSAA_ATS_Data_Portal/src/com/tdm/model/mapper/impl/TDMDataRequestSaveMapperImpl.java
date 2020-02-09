package com.tdm.model.mapper.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.tdm.constant.MessageConstant;
import com.tdm.exception.ServiceException;
import com.tdm.model.DO.ServiceRequestDO;
import com.tdm.model.DO.ServiceRequestDetailsDO;
import com.tdm.model.DTO.AutoPolicyParamsDTO;
import com.tdm.model.DTO.PropertyPolicyParamsDTO;
import com.tdm.model.DTO.TDMDataCreationDTO;
import com.tdm.model.mapper.TDMDataRequestSaveMapper;
@Component
@Service(MessageConstant.TDM_DATA_CREATION_MAPPER)
public class TDMDataRequestSaveMapperImpl implements TDMDataRequestSaveMapper {
	
	final static Logger logger = Logger.getLogger(TDMDataRequestSaveMapperImpl.class);
	
	private static final String AUTO = "AUTO";
	private static final String AUTO_RISK_STATE = "Auto Risk State";
	private static final String AUTO_AUTOMATION_SCENARIO = "Automation Scenario";
	private static final String AUTO_PAYMENT_PLAN = "Auto Payment Plan";
	private static final String AUTO_NUMBER_OF_DRIVERS = "Auto Number Of Drivers";
	private static final String AUTO_NUMBER_OF_VEHICLES = "Auto Number Of Vehicles";
	private static final String AUTO_NUMBER_OF_POLICIES = "Auto Number Of Policies";
	private static final String AUTO_ADDITIONAL_INFO = "Auto Additional Information";
	private static final String AUTO_SENARIO_TYPE = "Policy with N drivers and N vehicles";
	private static final String AUTO_EFFECTIVE_DATE = "Auto Effective Date";
	private static final String PROPERTY = "PROPERTY";
	private static final String PROPERTY_RISK_STATE = "Property Risk State";
	private static final String PROPERTY_AUTOMATION_SCENARIO = "Property Automation Scenario";
	private static final String PROPERTY_PAYMENT_PLAN = "Property Payment Plan";
	private static final String PROPERTY_NUMBER_OF_POLICIES = "Property Number Of Policies";
	private static final String PROPERTY_POLICIES_TYPE = "Property Policy Type";
	private static final String PROPERTY_ADDITIONAL_INFO = "Property Additional Information";
	private static final String AUTO_PRODUCT_INFO =  "Auto Product Type";
	private static final String PROPERTY_PRODUCT_INFO =  "Property Product Type";
	private static final String PROPERTY_EFFECTIVE_DATE = "Property Effective Date";
	private static final String PROPERTY_MORTGAGEE = "Property Mortgage";
	private static final String PROPERTY_ADDITIONAL_INTEREST = "Property Additional Interest";
	
	private static final String AUTO_TERM =  "Auto Term";
	
	
	@Override
	public ServiceRequestDO convertTdmDatacreationDtoToDO(TDMDataCreationDTO tdmDataCreationDto){  
		if(null == tdmDataCreationDto) {
			return null;
		}
		ServiceRequestDO serviceRequestDO = null;
		try {
			serviceRequestDO = new ServiceRequestDO();
			serviceRequestDO.setRequestedBy(tdmDataCreationDto.getRequestedBy()!=null?tdmDataCreationDto.getRequestedBy():" ");
			SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy");
			try {
				Date createDate = sdf.parse(tdmDataCreationDto.getCreatedOn()!=null?tdmDataCreationDto.getCreatedOn():null);
				if(createDate == null){
					createDate = new Date();
				}
				serviceRequestDO.setCreateDate(createDate);
				Date expectedDate = sdf.parse(tdmDataCreationDto.getExpectedDate()!=null?tdmDataCreationDto.getExpectedDate():null);
				serviceRequestDO.setExpectedDate(expectedDate);
			}catch(Exception e) {
				serviceRequestDO.setCreateDate(null);
				serviceRequestDO.setExpectedDate(null);
			}
			serviceRequestDO.setApplicationOwner(tdmDataCreationDto.getApplicationOwner()!=null?tdmDataCreationDto.getApplicationOwner():"Arun");
			Long assignedGroupId = tdmDataCreationDto.getAssignedGroup()!= null ?Long.valueOf(tdmDataCreationDto.getAssignedGroup()):null;
			serviceRequestDO.setAssignedGroupId(assignedGroupId);
			serviceRequestDO.setApprover(tdmDataCreationDto.getApprover()!=null?tdmDataCreationDto.getApprover():"");
			String assignedToId = tdmDataCreationDto.getAssignedToId()!= null ?tdmDataCreationDto.getAssignedToId():null;
			serviceRequestDO.setAssignedTo(assignedToId);
			Integer resuestStatusId = tdmDataCreationDto.getStatus()!= null ?Integer.valueOf(tdmDataCreationDto.getStatus()):null;
			serviceRequestDO.setRequestStatus(resuestStatusId);
			serviceRequestDO.setSubject(tdmDataCreationDto.getSubject()!=null?tdmDataCreationDto.getSubject():"");
			Long consumerGroupId = tdmDataCreationDto.getConsumerGroup()!= null ?Long.valueOf(tdmDataCreationDto.getConsumerGroup()):null;
			serviceRequestDO.setConsumerGroup(consumerGroupId);
			Long priorityId = tdmDataCreationDto.getPriority()!= null ?Long.valueOf(tdmDataCreationDto.getPriority()):null;
			serviceRequestDO.setPriority(priorityId);
			Long datasourceId = tdmDataCreationDto.getDataSource()!= null ?Long.valueOf(tdmDataCreationDto.getDataSource()):null;
			serviceRequestDO.setDataSource(datasourceId);
			Long environmentId = tdmDataCreationDto.getEnvironment()!= null ?Long.valueOf(tdmDataCreationDto.getEnvironment()):null;
			serviceRequestDO.setEnvironment(environmentId);
			Integer statusId = tdmDataCreationDto.getStatus()!= null ?Integer.valueOf(tdmDataCreationDto.getStatus()):null;
			serviceRequestDO.setRequestStatus(statusId);
			serviceRequestDO.setRequestedBy(tdmDataCreationDto.getUserId());
			serviceRequestDO.setUpdatedBy(tdmDataCreationDto.getUserId());

			if (null != tdmDataCreationDto.getAutoPolicyParamList()
					&& !tdmDataCreationDto.getAutoPolicyParamList().isEmpty()) {
				List<ServiceRequestDetailsDO> autoSRList = new ArrayList<ServiceRequestDetailsDO>();
				for (AutoPolicyParamsDTO autoPolicyParams : tdmDataCreationDto.getAutoPolicyParamList()) {
					String userId = tdmDataCreationDto.getUserId();
					String AUTO = "AUTO";
					if(autoPolicyParams.getRiskState() != null && !autoPolicyParams.getRiskState().isEmpty()) {
						ServiceRequestDetailsDO detailsDO1 = new ServiceRequestDetailsDO(autoPolicyParams.getScenarioNo(), "Auto Risk State", autoPolicyParams.getRiskState(), AUTO, userId);
						autoSRList.add(detailsDO1);
					}
					ServiceRequestDetailsDO detailsDO2 = new ServiceRequestDetailsDO(autoPolicyParams.getScenarioNo(), "Auto Payment Plan", autoPolicyParams.getPaymentPlan(), AUTO, userId);
					autoSRList.add(detailsDO2);
					ServiceRequestDetailsDO detailsDO3 = new ServiceRequestDetailsDO(autoPolicyParams.getScenarioNo() ,"Auto Number Of Drivers", autoPolicyParams.getNoOfDrivers(), AUTO, userId);
					autoSRList.add(detailsDO3);
					ServiceRequestDetailsDO detailsDO4 = new ServiceRequestDetailsDO(autoPolicyParams.getScenarioNo(), "Auto Number Of Vehicles", autoPolicyParams.getNoOfVehicles(), AUTO, userId);
					autoSRList.add(detailsDO4);
					ServiceRequestDetailsDO detailsDO5 = new ServiceRequestDetailsDO(autoPolicyParams.getScenarioNo(), "Auto Number Of Policies", autoPolicyParams.getNoOfPolicies(), AUTO, userId);
					autoSRList.add(detailsDO5);
					ServiceRequestDetailsDO detailsDO6 = new ServiceRequestDetailsDO(autoPolicyParams.getScenarioNo(), "Auto Additional Information", autoPolicyParams.getAdditionalInformation(), AUTO, userId);
					autoSRList.add(detailsDO6);
				}
				serviceRequestDO.setAutoRequestDetailList(autoSRList);
			}

			
			// set property params to SERVICEREQUEST_DETAILS
			if (null != tdmDataCreationDto.getPropertyPolicyParamList()
					&& !tdmDataCreationDto.getPropertyPolicyParamList().isEmpty()) {
				//int count1=1;
				List<ServiceRequestDetailsDO> propertySRList = new ArrayList<ServiceRequestDetailsDO>();
				for (PropertyPolicyParamsDTO propertyPolicyParams : tdmDataCreationDto.getPropertyPolicyParamList()) {
					//propertyPolicyParams.setScenarioNo(count1+"");
					String userId = tdmDataCreationDto.getUserId();
					String PROPERTY = "PROPERTY";

					if(propertyPolicyParams.getRiskState() != null && !propertyPolicyParams.getRiskState().isEmpty()) {
						if(propertyPolicyParams.getScenarioNo().trim()!=null &&!propertyPolicyParams.getScenarioNo().trim().isEmpty())
						{
							ServiceRequestDetailsDO detailsDO1 = new ServiceRequestDetailsDO(propertyPolicyParams.getScenarioNo(), "Property Risk State", propertyPolicyParams.getRiskState(),PROPERTY, userId);
							propertySRList.add(detailsDO1);
						}
					}
					if(propertyPolicyParams.getPaymentPlan()!=null &&!propertyPolicyParams.getPaymentPlan().isEmpty())
					{
						if(propertyPolicyParams.getScenarioNo().trim()!=null &&!propertyPolicyParams.getScenarioNo().trim().isEmpty())
						{
							ServiceRequestDetailsDO detailsDO2 = new ServiceRequestDetailsDO(propertyPolicyParams.getScenarioNo(), "Property Payment Plan", propertyPolicyParams.getPaymentPlan(), PROPERTY, userId);
							propertySRList.add(detailsDO2);
						}
					}
					if(propertyPolicyParams.getPolicyType()!=null && !propertyPolicyParams.getPolicyType().isEmpty())
					{
						if(propertyPolicyParams.getScenarioNo().trim()!=null &&!propertyPolicyParams.getScenarioNo().trim().isEmpty())
						{
							ServiceRequestDetailsDO detailsDO3 = new ServiceRequestDetailsDO(propertyPolicyParams.getScenarioNo(), "Property Policy Type", propertyPolicyParams.getPolicyType(), PROPERTY, userId);
							propertySRList.add(detailsDO3);
						}
					}
					if(propertyPolicyParams.getNoOfPolicies()!=null&&!propertyPolicyParams.getNoOfPolicies().isEmpty())
					{
						if(propertyPolicyParams.getScenarioNo().trim()!=null &&!propertyPolicyParams.getScenarioNo().trim().isEmpty())
						{
							ServiceRequestDetailsDO detailsDO4 = new ServiceRequestDetailsDO(propertyPolicyParams.getScenarioNo(), "Property Number Of Policies", propertyPolicyParams.getNoOfPolicies(), PROPERTY, userId);
							propertySRList.add(detailsDO4);
						}
					}
					if(propertyPolicyParams.getAdditionalInformation()!=null&&!propertyPolicyParams.getAdditionalInformation().isEmpty())
					{
						if(propertyPolicyParams.getScenarioNo().trim()!=null &&!propertyPolicyParams.getScenarioNo().trim().isEmpty())
						{
							ServiceRequestDetailsDO detailsDO5 = new ServiceRequestDetailsDO(propertyPolicyParams.getScenarioNo(), "Property Additional Information", propertyPolicyParams.getAdditionalInformation(), PROPERTY, userId);
							propertySRList.add(detailsDO5);
						}
					}
					//count1++;
				}
				serviceRequestDO.setPropertyRequestDetailList(propertySRList);
			}
		}catch(NullPointerException nullPointerEx) {
			nullPointerEx.printStackTrace();
		}
		catch(Exception otherEx) {
			otherEx.printStackTrace();
		}
		return serviceRequestDO;
	}
	
	@Override
	public TDMDataCreationDTO convertSRDetailListDOToTDMDataCreationDTO(List<ServiceRequestDetailsDO> listServiceRequestDetailsDO)
			throws ServiceException {
		logger.info(MessageConstant.TDM_ADMIN_MAPPER + MessageConstant.TDM_ADMIN_MAPPER_DO_TO_DTOs
				+ MessageConstant.LOG_INFO_PARAMS_NO);
		try {
			
			TDMDataCreationDTO tdmDCDTO = new TDMDataCreationDTO();
			
			List<AutoPolicyParamsDTO> autoPolicyList = new ArrayList<AutoPolicyParamsDTO>();
			List<PropertyPolicyParamsDTO> propertyPolicyList = new ArrayList<PropertyPolicyParamsDTO>();

			for (ServiceRequestDetailsDO serviceRequestDetailDO : listServiceRequestDetailsDO) {
				
				if(serviceRequestDetailDO != null){
					
					if(serviceRequestDetailDO.getPolicyType()!=null){
						
						if(serviceRequestDetailDO.getPolicyType().equalsIgnoreCase(AUTO)){
							
							AutoPolicyParamsDTO autoPolicyDTO = new	AutoPolicyParamsDTO();
							autoPolicyDTO.setScenarioNo(serviceRequestDetailDO.getSeqNo().toString());
							 for (String retval: serviceRequestDetailDO.getColumnValue().split("\\|")){
								 if(retval!=null){
									 String[] colDetails = retval.split("#");
									 if(colDetails.length>1){
										 if(colDetails[0]!= null){
											 if(colDetails[0].equalsIgnoreCase(AUTO_RISK_STATE)){
												 autoPolicyDTO.setRiskState(colDetails[1]!=null?colDetails[1]:"");
											 }else if(colDetails[0].equalsIgnoreCase(AUTO_AUTOMATION_SCENARIO)){
												 autoPolicyDTO.setAutomationScenario(colDetails[1]!=null?colDetails[1]:"");
											 }else if(colDetails[0].equalsIgnoreCase(AUTO_PAYMENT_PLAN)){
												 autoPolicyDTO.setPaymentPlan(colDetails[1]!=null?colDetails[1]:"");
											 }else if(colDetails[0].equalsIgnoreCase(AUTO_NUMBER_OF_DRIVERS)){
												 autoPolicyDTO.setNoOfDrivers(colDetails[1]!=null?colDetails[1]:"");
											 }else if(colDetails[0].equalsIgnoreCase(AUTO_NUMBER_OF_VEHICLES)){
												 autoPolicyDTO.setNoOfVehicles(colDetails[1]!=null?colDetails[1]:"");
											 }else if(colDetails[0].equalsIgnoreCase(AUTO_NUMBER_OF_POLICIES)){
												 autoPolicyDTO.setNoOfPolicies(colDetails[1]!=null?colDetails[1]:"");
											 }else if(colDetails[0].equalsIgnoreCase(AUTO_ADDITIONAL_INFO)){
												 autoPolicyDTO.setAdditionalInformation(colDetails[1]!=null?colDetails[1]:"");
											 }else if(colDetails[0].equalsIgnoreCase(AUTO_PAYMENT_PLAN)){
												 autoPolicyDTO.setPaymentPlan(colDetails[1]!=null?colDetails[1]:"");
											 }
											 else if(colDetails[0].equalsIgnoreCase(AUTO_PRODUCT_INFO)){
												 autoPolicyDTO.setProductType(colDetails[1]!=null?colDetails[1]:"");
											 }
											 else if(colDetails[0].equalsIgnoreCase(AUTO_TERM)){
												 autoPolicyDTO.setTerm(colDetails[1]!=null?colDetails[1]:"");
											 }else if (colDetails[0]
													.equalsIgnoreCase(AUTO_EFFECTIVE_DATE)) {
												autoPolicyDTO
														.setEffectiveDate(colDetails[1] != null ? colDetails[1]
																: "");
											}
											 
										 }
										 
										 
									 }
									 
								 }
								
								 
							  }
							 autoPolicyList.add(autoPolicyDTO);
						  }
							
							if(serviceRequestDetailDO.getPolicyType().equalsIgnoreCase(PROPERTY)){
								
								PropertyPolicyParamsDTO propertyPolicyDTO = new	PropertyPolicyParamsDTO();
								propertyPolicyDTO.setScenarioNo(serviceRequestDetailDO.getSeqNo().toString());
								 for (String retval: serviceRequestDetailDO.getColumnValue().split("\\|")){
									 if(retval!=null){
										 
										 String[] colDetails = retval.split("#");
										 if(colDetails.length>1){
											 if(colDetails[0]!= null){
												 
												 if(colDetails[0].equalsIgnoreCase(PROPERTY_RISK_STATE)){
													 propertyPolicyDTO.setRiskState(colDetails[1]);
												 }else if(colDetails[0].equalsIgnoreCase(PROPERTY_PAYMENT_PLAN)){
													 propertyPolicyDTO.setPaymentPlan(colDetails[1]);
												 }else if(colDetails[0].equalsIgnoreCase(PROPERTY_AUTOMATION_SCENARIO)){
													 propertyPolicyDTO.setAutomationScenario(colDetails[1]);
												 }else if(colDetails[0].equalsIgnoreCase(PROPERTY_POLICIES_TYPE)){
													 propertyPolicyDTO.setPolicyType(colDetails[1]);
												 }else if(colDetails[0].equalsIgnoreCase(PROPERTY_NUMBER_OF_POLICIES)){
													 propertyPolicyDTO.setNoOfPolicies(colDetails[1]);
												 }else if(colDetails[0].equalsIgnoreCase(PROPERTY_ADDITIONAL_INFO)){
													 propertyPolicyDTO.setAdditionalInformation(colDetails[1]);
												 }else if(colDetails[0].equalsIgnoreCase(PROPERTY_PRODUCT_INFO)){
													 propertyPolicyDTO.setProduct(colDetails[1]!=null?colDetails[1]:"");
												 }else if (colDetails[0]
															.equalsIgnoreCase(PROPERTY_EFFECTIVE_DATE)) {
														propertyPolicyDTO.setEffectiveDate(colDetails[1] != null ? colDetails[1]
																		: "");
												 }else if(colDetails[0].equalsIgnoreCase(PROPERTY_MORTGAGEE)){
													 propertyPolicyDTO.setMortgage(colDetails[1]!=null?colDetails[1]:"");
												 }else if(colDetails[0].equalsIgnoreCase(PROPERTY_ADDITIONAL_INTEREST)){
													 propertyPolicyDTO.setAdditionalInterest(colDetails[1]!=null?colDetails[1]:"");
												 }
													 
												 
											 }
										 }
										 
										 
									 }
									 
								  }
								 propertyPolicyList.add(propertyPolicyDTO);
							  }
						
					}
					
					
					
				}
				
					
				
			}
			tdmDCDTO.setAutoPolicyParamList(autoPolicyList);
			tdmDCDTO.setPropertyPolicyParamList(propertyPolicyList);
				
	
			logger.info(MessageConstant.TDM_ADMIN_MAPPER
					+ MessageConstant.TDM_ADMIN_MAPPER_DO_TO_DTOs + MessageConstant.LOG_INFO_RETURN);
			return tdmDCDTO;
		} catch (NullPointerException nullPointerEx) {
			logger.error(MessageConstant.TDM_ADMIN_MAPPER
					+ MessageConstant.TDM_ADMIN_MAPPER_DO_TO_DTOs
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new ServiceException(MessageConstant.NULL_POINTER_EXCEPTION, nullPointerEx);
		} catch (Exception otherEx) {
			logger.error(MessageConstant.TDM_ADMIN_MAPPER
					+ MessageConstant.TDM_ADMIN_MAPPER_DO_TO_DTOs
					+ MessageConstant.LOG_ERROR_EXCEPTION);
			throw new ServiceException(MessageConstant.SERVICE_EXCEPTION, otherEx);
		}
	}
	
	

}
