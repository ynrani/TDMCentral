package com.tdm.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Service;

import com.tdm.constant.RequestStatusENUM;
import com.tdm.dao.DataCreationRequestDAO;
import com.tdm.dao.SearchDAO;
import com.tdm.email.EmailNotificationService;
import com.tdm.exception.DAOException;
import com.tdm.exception.ServiceException;
import com.tdm.model.DO.AlterNotificationDO;
import com.tdm.model.DO.DCAccessLogDO;
import com.tdm.model.DO.DGAutoExcelTemplateDO;
import com.tdm.model.DO.ServiceRequestDO;
import com.tdm.model.DO.TDMEnvironmentDO;
import com.tdm.model.DTO.AutoPolicyParamsDTO;
import com.tdm.model.DTO.DCAccessLogDTO;
import com.tdm.model.DTO.DCAutoEmailDTO;
import com.tdm.model.DTO.PropertyPolicyParamsDTO;
import com.tdm.model.DTO.TDMDataCreationDTO;
import com.tdm.model.mapper.TDMUserMapper;
import com.tdm.model.mapper.impl.TDMDataRequestSaveMapperImpl;
import com.tdm.service.DataCreationRequestService;
import com.tdm.util.GenerateExcel;

/***
 * To read the data from DO to DTO and for business logic
 * 
 * @author brahmu
 *
 */
@Service
public class DataCreationRequestServiceImpl extends TdmBaseServiceImpl
		implements DataCreationRequestService {

	final static Logger logger = Logger
			.getLogger(DataCreationRequestServiceImpl.class);
	private static final String EMAIL_SUPPORT = "support@csaa.com";
	private static final String AUTO_EXCEL_DATA = "Auto Policy Testdata";
	private static final String PROPERTY_EXCEL_DATA = "HO Policy Testdata";
	private static final String EXCEL_FILE_PATH = "C:\\1\\";
	private static final String CATEGORY_TYPE_USER_INPUT = "User Input";
	private static final String CATEGORY_TYPE_PREDEFINED = "Predefined";
	private static final String CATEGORY_TYPE_DICTINARY = "Dictionary";
	private static final String CATEGORY_TYPE_DEFAULT = "Default";

	@Autowired
	DataCreationRequestDAO saveDataRequestDAO;

	@Autowired
	SearchDAO searchDAO;

	@Autowired
	EmailNotificationService emailNotificationService;

	@Autowired
	TDMDataRequestSaveMapperImpl tdmDataRequestSaveMapper;

	@Autowired
	private MessageSource messageSource;
	DateFormat df = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");

	@Autowired
	TDMUserMapper tdmUserMapper;

	/***
	 * To save the serviceRequest data into DB
	 * 
	 * @param tdmPolicyAutoSearchDTO
	 * @param userName
	 * @param enviro
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public TDMDataCreationDTO saveServiceRequestData(
			TDMDataCreationDTO tdmDataCreationDto, boolean isAutomation)
			throws ServiceException {
		EntityManager entityManager = openCsaaUserEntityManager();
		if (tdmDataCreationDto == null) {
			return null;
		}
		try {
			// dataCreationDTO To DO
			ServiceRequestDO serviceRequestDO = new ServiceRequestDO();
			if (serviceRequestDO.getRequestId() == null) {
				serviceRequestDO.setCreatedBy(tdmDataCreationDto.getUserId());
			}

			serviceRequestDO.setRequestedBy(tdmDataCreationDto.getUserId());
			serviceRequestDO.setUpdatedBy(tdmDataCreationDto.getUserId());

			serviceRequestDO = tdmUserMapper
					.convertTdmDatacreationDtoToDO(tdmDataCreationDto);
			serviceRequestDO = saveDataRequestDAO.saveServiceRequestData(
					serviceRequestDO, entityManager, isAutomation);
			tdmDataCreationDto.setRequestId(serviceRequestDO.getRequestId());

			return tdmDataCreationDto;
		} catch (NullPointerException nullPointerEx) {
			logger.error("MessageConstant.NULL_POINTER_EXCEPTION",
					nullPointerEx);
			throw new ServiceException(messageSource.getMessage(
					"NULL_POINTER_EXCEPTION", null, null), nullPointerEx);

		} catch (Exception otherEx) {
			logger.error("Service exception", otherEx);
			throw new ServiceException(messageSource.getMessage(
					"SERVICE_EXCEPTION", null, null), otherEx);
		}
	}

	/***
	 * To Submit the serviceRequest data into DB
	 * 
	 * @param tdmPolicyAutoSearchDTO
	 * @return
	 * @throws ServiceException
	 *             , DAOException, NoSuchMessageException
	 */
	@Override
	public String processServiceRequest(String userId, String requestId,
			Integer status, Long assignedGroupId, String assignedToId,
			String comments, String exceptedDate) throws ServiceException,
			DAOException, NoSuchMessageException {
		if (requestId == null || requestId == null) {
			return null;
		}
		Date excepteDate = null;
		// Getting the DO for given requestId
		EntityManager entityManager = openCsaaUserEntityManager();
		entityManager.getTransaction().begin();

		ServiceRequestDO serviceRequestDO = saveDataRequestDAO
				.getSeriveRequest(requestId, entityManager);
		if (RequestStatusENUM.OPEN.getRequestStatusCode() == status) {
			serviceRequestDO.setAssignedTo(assignedToId);
			serviceRequestDO.setAssignedGroupId(assignedGroupId);
		} else if (RequestStatusENUM.SENT_FOR_CLARIFICATION
				.getRequestStatusCode() == status) {

		} else if (RequestStatusENUM.RE_OPENED.getRequestStatusCode() == status) {

		}
		// set status to the serviceRequestDO
		try {
			SimpleDateFormat newFormat = new SimpleDateFormat(
					"MM/dd/yyyy HH:mm:ss");

			excepteDate = newFormat.parse(exceptedDate);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		serviceRequestDO.setExpectedDate(excepteDate);
		serviceRequestDO.setRequestStatus(status);
		serviceRequestDO.setUpdatedBy(userId);
		serviceRequestDO = saveDataRequestDAO.saveOrUpdate(serviceRequestDO,
				entityManager);

		// Insert data into DC_ACCESS_LOG
		DCAccessLogDO accessLogDO = createAccessLog(
				serviceRequestDO.getRequestId(), status, comments, userId);
		accessLogDO = saveDataRequestDAO.saveOrUpdate(accessLogDO,
				entityManager);
		entityManager.getTransaction().commit();
		// Generate excel for DG Automation
	/*	if (serviceRequestDO.getRequestStatus() == RequestStatusENUM.NEW
				.getRequestStatusCode() && requestId.startsWith("DGA")) {
			generateExcelsForDGAuto(serviceRequestDO.getRequestId(),
					String.valueOf(serviceRequestDO.getEnvironment()));
		}*/

		// Send Alert By Email
		AlterNotificationDO alterNotificationDO = saveDataRequestDAO.get(
				status, AlterNotificationDO.class, entityManager);
		if (status.intValue() != 1) {
			logger.info("Email Template :: "
					+ alterNotificationDO.getTemplate());
		}
		if (alterNotificationDO.getIsActive().equalsIgnoreCase("Y")
				&& status.intValue() != 1) {
			String templateValue = alterNotificationDO.getTemplate();
			templateValue = templateValue.replaceAll("#Requestor_Name#",
					serviceRequestDO.getRequestedBy());
			templateValue = templateValue.replaceAll("#request_number#",
					serviceRequestDO.getRequestId());
			if (status.intValue() == 5 || status.intValue() == 6
					|| status.intValue() == 8 || status.intValue() == 9
					|| status.intValue() == 10) {
				templateValue = templateValue.replaceAll(
						"#Status_Change_Description#", comments);
			}
			templateValue = templateValue.replaceAll("#support_email#",
					EMAIL_SUPPORT);
			alterNotificationDO.setTemplate(templateValue);
			// TODO emailAlert(alterNotificationDO ,requestId,status);
		}
		return serviceRequestDO.getRequestId();
	}

	/***
	 * To generate DG automation excels
	 * 
	 * @param requestId
	 */
/*	private void generateExcelsForDGAuto(String requestId, String environmentId) {
		EntityManager entityManager = openCsaaUserEntityManager();
		try {

			// Get Request Details from DB
			TDMDataCreationDTO tdmDataCreationDTO = tdmDataRequestSaveMapper
					.convertSRDetailListDOToTDMDataCreationDTO(searchDAO
							.getReqDetailsByReqId(requestId, entityManager));
			tdmDataCreationDTO.setRequestId(requestId);
			// Get environment data from DB for this requestId
			TDMEnvironmentDO environment = entityManager.find(
					TDMEnvironmentDO.class, environmentId);

			// Copy excel files from templates
			GenerateExcel.createExcelFromTemplates(requestId);

			// Get Run Manager excel data
			List<DGAutoExcelTemplateDO> runManagerExcelDataList = entityManager
					.createNamedQuery("DGAutoExcelTemplateDO.findExcelByName",
							DGAutoExcelTemplateDO.class)
					.setParameter("excelName", "Run Manager").getResultList();
			GenerateExcel.generateRunManagerExcel(tdmDataCreationDTO,
					environment, runManagerExcelDataList);

			// Get Auto Policy Test Data
			List<DGAutoExcelTemplateDO> autoPolicyExcelDataList = entityManager
					.createNamedQuery("DGAutoExcelTemplateDO.findExcelByName",
							DGAutoExcelTemplateDO.class)
					.setParameter("excelName", "Auto Policy Testdata")
					.getResultList();
			writeDataToAutoExcel(autoPolicyExcelDataList,
					tdmDataCreationDTO.getAutoPolicyParamList(), requestId);

			Map<String, List<DGAutoExcelTemplateDO>> dataMap = new HashMap<String, List<DGAutoExcelTemplateDO>>();
			// List<DGAutoExcelTemplateDO> autoPolicyEDateRefList =
			// saveDataRequestDAO.getRefrencesForExcelColumnName(
			// "Auto Policy Testdata",
			// "'Effective_Date','State','Product','Policy_Term','Number_of_Insured','Number_of_Vehicles','PaymentPlan'",
			// entityManager);
			// dataMap.put("Effective_Date", autoPolicyEDateRefList);

			// GenerateExcel.generateAutoPolicyExcel(tdmDataCreationDTO.getAutoPolicyParamList(),
			// requestId, autoPolicyExcelDataList, dataMap);

			// Property Policy Test data
			Map<String, List<DGAutoExcelTemplateDO>> propDataMap = new HashMap<String, List<DGAutoExcelTemplateDO>>();
			
			 * List<DGAutoExcelTemplateDO> propertyPolicyExcelDataList =
			 * entityManager
			 * .createNamedQuery("DGAutoExcelTemplateDO.findExcelByName",
			 * DGAutoExcelTemplateDO.class) .setParameter("excelName",
			 * "HO Policy Testdata") .getResultList();
			 * 
			 * writeDataToPropertyExcel(propertyPolicyExcelDataList,
			 * tdmDataCreationDTO.getPropertyPolicyParamList(), requestId);
			 
			// List<DGAutoExcelTemplateDO> propPolicyEDateRefList =
			// saveDataRequestDAO.getRefrencesForExcelColumnName(
			// "HO Policy Testdata",
			// "'Effective_Date','Policy_type','Product','State','Payment_plan','Mortgagee'",
			// entityManager);
			// propDataMap.put("Effective_Date", propPolicyEDateRefList);

			// GenerateExcel.generatePropertyPolicyExcel(tdmDataCreationDTO.getPropertyPolicyParamList(),
			// requestId, propertyPolicyExcelDataList, propDataMap);

		} catch (ServiceException | DAOException | NoSuchMessageException e) {
			logger.warn("Exception occurred in generateExcelsForDGAuto() : "
					+ e.getMessage());
			e.printStackTrace();
		}
	}*/

	/***
	 * Write data to the excel cells
	 * 
	 * @param autoPolicyExcelDataList
	 * @param autoPolicyParamList
	 * @param requestId
	 */
	private void writeDataToAutoExcel(
			List<DGAutoExcelTemplateDO> autoPolicyExcelDataList,
			List<AutoPolicyParamsDTO> autoPolicyParamList, String requestId) {
		FileInputStream file;
		try {
			file = new FileInputStream(new File(EXCEL_FILE_PATH
					+ AUTO_EXCEL_DATA + "_" + requestId + ".xls"));
			HSSFWorkbook workbook = new HSSFWorkbook(file);
			if (workbook != null && autoPolicyExcelDataList != null
					&& !autoPolicyExcelDataList.isEmpty()) {
				for (AutoPolicyParamsDTO autoPolicyParamsDTO : autoPolicyParamList) {

					String state = autoPolicyParamsDTO.getRiskState();
					// Iterate to set data
					for (DGAutoExcelTemplateDO autoExcelTemplateDO : autoPolicyExcelDataList) {
						// get basic params to set data
						String sheetName = autoExcelTemplateDO.getSheetName();
						String colName = autoExcelTemplateDO.getColumnName();
						Integer colId = autoExcelTemplateDO.getColumnId();
						String category = autoExcelTemplateDO.getCategory();
						String dictName = autoExcelTemplateDO.getDictTable();
						HSSFSheet sheet = workbook.getSheet(sheetName);

						logger.info("sheetName : " + sheetName + " colName : "
								+ colName + "category : " + category
								+ " dictName : " + dictName);
						String cellValue = null;
						
						String scenario=autoPolicyParamsDTO
								.getScenarioNo();
						String row =autoPolicyParamsDTO
						.getScenarioNo().substring(scenario.length()-1,scenario.length());
						Integer rowId = Integer.valueOf(row);
						Row currentRow = sheet.getRow(rowId);
						Cell headerCell = sheet.getRow(0).getCell(colId - 1);
						if (currentRow == null) {
							currentRow = sheet.createRow(1);
						}
						Cell cell = currentRow.getCell(colId - 1);
						if (cell == null) {
							cell = currentRow.createCell(colId - 1);
						}
						if (headerCell.getStringCellValue().equalsIgnoreCase(
								"TC_ID")) {
							cell.setCellValue(autoPolicyParamsDTO
									.getScenarioNo());
						} else {
							if (category != null) {
								switch (category) {
								case CATEGORY_TYPE_USER_INPUT:
									cellValue = getUserInputForColumnName(
											autoPolicyParamsDTO, colName);
									break;
								case CATEGORY_TYPE_DICTINARY:
									cellValue = getDataFromRandomTable(
											category, dictName, state, colName);
									break;
								case CATEGORY_TYPE_PREDEFINED:
									cellValue = getDataFromRandomTable(
											category, dictName, state, colName);
									break;

								case CATEGORY_TYPE_DEFAULT:
									cellValue = getDefault(sheetName, category,
											dictName, state, colName);
									break;
								default:
									cellValue = "Default";
									break;
								}
								cell.setCellValue(cellValue);
							} else {
								cell.setCellValue("");
							}
						}
					}

				}
				// Parm list sizer is no of scenarios selected by user
				// 0 cell value should be AUTO_TC_scenariono
				file.close();
				// // Writing the data to the excel back
				FileOutputStream outFile = new FileOutputStream(new File(
						EXCEL_FILE_PATH + AUTO_EXCEL_DATA + "_" + requestId
								+ ".xls"));
				workbook.write(outFile);
				outFile.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/***
	 * Write data to the excel cells
	 * 
	 * @param autoPolicyExcelDataList
	 * @param autoPolicyParamList
	 * @param requestId
	 */
	private void writeDataToPropertyExcel(
			List<DGAutoExcelTemplateDO> autoPolicyExcelDataList,
			List<PropertyPolicyParamsDTO> propertyPolicyParamList,
			String requestId) {
		FileInputStream file;
		try {
			file = new FileInputStream(new File(EXCEL_FILE_PATH
					+ PROPERTY_EXCEL_DATA + "_" + requestId + ".xls"));
			HSSFWorkbook workbook = new HSSFWorkbook(file);
			if (workbook != null && autoPolicyExcelDataList != null
					&& !autoPolicyExcelDataList.isEmpty()) {
				for (PropertyPolicyParamsDTO autoPolicyParamsDTO : propertyPolicyParamList) {
					// Iterate to set data
					for (DGAutoExcelTemplateDO autoExcelTemplateDO : autoPolicyExcelDataList) {
						// get basic params to set data
						String sheetName = autoExcelTemplateDO.getSheetName();
						String colName = autoExcelTemplateDO.getColumnName();
						Integer colId = autoExcelTemplateDO.getColumnId();
						String category = autoExcelTemplateDO.getCategory();
						String dictName = autoExcelTemplateDO.getDictTable();
						HSSFSheet sheet = workbook.getSheet(sheetName);
						String cellValue = null;
						Integer rowId = Integer.valueOf(autoPolicyParamsDTO
								.getScenarioNo());
						Row currentRow = sheet.getRow(rowId);
						Cell headerCell = sheet.getRow(0).getCell(colId - 1);
						if (currentRow == null) {
							currentRow = sheet.createRow(1);
						}
						Cell cell = currentRow.getCell(colId - 1);
						if (cell == null) {
							cell = currentRow.createCell(colId - 1);
						}
						if (headerCell.getStringCellValue().equalsIgnoreCase(
								"TC_ID")) {
							cell.setCellValue("PROPERTY_TC_" + rowId);
						} else {
							if (category != null) {
								switch (category) {
								case CATEGORY_TYPE_USER_INPUT:
									cellValue = getUserInputForColumnName(
											autoPolicyParamsDTO, colName);
									break;
								case CATEGORY_TYPE_DICTINARY:
									/*
									 * case CATEGORY_TYPE_PREDEFINED: cellValue
									 * = getDataFromRandomTable( category,
									 * dictName, colName); break;
									 */
								default:
									cellValue = null;
									break;
								}
								cell.setCellValue(cellValue);
							} else {
								cell.setCellValue("");
							}
						}
					}

				}
				// Parm list sizer is no of scenarios selected by user
				// 0 cell value should be AUTO_TC_scenariono
				file.close();
				// // Writing the data to the excel back
				FileOutputStream outFile = new FileOutputStream(new File(
						EXCEL_FILE_PATH + PROPERTY_EXCEL_DATA + "_" + requestId
								+ ".xls"));
				workbook.write(outFile);
				outFile.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/***
	 * To get the column values from DTO
	 * 
	 * @param autoPolicyParamList
	 * @param colName
	 * @return
	 */
	private String getUserInputForColumnName(
			PropertyPolicyParamsDTO autoPolicyParamsDTO, String colName) {
		if (colName.equals("State")) {
			String state = autoPolicyParamsDTO.getRiskState();
			return (state == null || state.equalsIgnoreCase("Any") ? "AZ"
					: state);
		}
		if (colName.equals("Effective_Date")) {
			String effDate = autoPolicyParamsDTO.getEffectiveDate();
			return (effDate == null || effDate.equalsIgnoreCase(" ") ? " "
					: effDate);
		}
		if (colName.equals("Number_of_Insured")) {
			String noOfInsured = autoPolicyParamsDTO.getNoOfDrivers();
			return (noOfInsured == null || noOfInsured.equalsIgnoreCase("1") ? "1"
					: noOfInsured);
		}
		if (colName.equals("Number_of_Vehicles")) {
			String noOfVehicles = autoPolicyParamsDTO.getNoOfVehicles();
			return (noOfVehicles == null || noOfVehicles.equalsIgnoreCase("1") ? "1"
					: noOfVehicles);
		}
		if (colName.equals("PaymentPlan")) {
			String paymentPlan = autoPolicyParamsDTO.getPaymentPlan();
			return (paymentPlan == null || paymentPlan.equalsIgnoreCase("Any") ? "Monthly"
					: paymentPlan);
		}
		return null;
	}

	private String getUserInputForColumnName(
			AutoPolicyParamsDTO autoPolicyParamsDTO, String colName) {
		if (colName.equalsIgnoreCase("State")
				|| colName.equalsIgnoreCase("Dri_LicenseState")
				|| colName.equalsIgnoreCase("Premium_State")
				|| colName.equalsIgnoreCase("Ins_State")
				|| colName.equalsIgnoreCase("Ins_MailingState")
				|| colName.equalsIgnoreCase("PreFill_State")) {
						
			String state = autoPolicyParamsDTO.getRiskState();
			return (state == null || state.equalsIgnoreCase("Any") ? "AZ"
					: state);
		}
		if (colName.equalsIgnoreCase("Effective_Date")) {
			String effDate = autoPolicyParamsDTO.getEffectiveDate();
			return (effDate == null || effDate.equalsIgnoreCase(" ") ? " "
					: effDate);
		}
		if (colName.equalsIgnoreCase("Product")) {
			String product = autoPolicyParamsDTO.getProductType();
			return (product == null || product.equalsIgnoreCase(" ") ? "AUTO_SS"
					: product);
		}
		if (colName.equalsIgnoreCase("Policy_Term")) {
			String term = autoPolicyParamsDTO.getTerm();
			return (term == null || term.equalsIgnoreCase("Any") ? "ANNUAL"
					: term);
		}
		if (colName.equalsIgnoreCase("Number_of_Insured")
				|| colName.equalsIgnoreCase("No_Of_Insureds")) {
			String noOfInsured = autoPolicyParamsDTO.getNoOfDrivers();
			return (noOfInsured == null || noOfInsured.equalsIgnoreCase("1") ? "1"
					: noOfInsured);
		}
		if (colName.equalsIgnoreCase("Number_of_Vehicles") || colName.equalsIgnoreCase("No_Of_Vehicles")) {
			String noOfVehicles = autoPolicyParamsDTO.getNoOfVehicles();
			return (noOfVehicles == null || noOfVehicles.equalsIgnoreCase("1") ? "1"
					: noOfVehicles);
		}
		if (colName.equalsIgnoreCase("No_Of_Drivers")) {
			String noOfDrivers = autoPolicyParamsDTO.getNoOfDrivers();
			return (noOfDrivers == null || noOfDrivers.equalsIgnoreCase("1") ? "1"
					: noOfDrivers);
		}
		if (colName.equalsIgnoreCase("Payment_Plan")
				|| colName.equalsIgnoreCase("PaymentPlan")) {
			String paymentPlan = autoPolicyParamsDTO.getPaymentPlan();
			return (paymentPlan == null || paymentPlan.equalsIgnoreCase("Any") ? "Monthly"
					: paymentPlan);
		}
		return null;
	}

	/***
	 * Set data to DO object from DTO
	 * 
	 * @param requestId
	 * @param status
	 * @param comments
	 * @param userId
	 * @return
	 */
	private DCAccessLogDO createAccessLog(String requestId, Integer status,
			String comments, String userId) {
		DCAccessLogDO dcAccessLogDO = new DCAccessLogDO();
		dcAccessLogDO.setRequestId(requestId);
		dcAccessLogDO.setRequestStatus(status);
		dcAccessLogDO.setModifiedDate(new java.util.Date());
		dcAccessLogDO.setModifiedBy(userId);
		dcAccessLogDO.setComments(comments);
		return dcAccessLogDO;
	}

	/***
	 * Set data to DTO
	 * 
	 * @param alterNotificationDO
	 * @param requestId
	 * @return
	 * @throws ServiceException
	 */
	private boolean emailAlert(AlterNotificationDO alterNotificationDO,
			String requestId, Integer status) throws ServiceException {
		boolean emailSend = false;
		DCAutoEmailDTO serviceAutoEmailDTO = new DCAutoEmailDTO();
		serviceAutoEmailDTO.setTo("sravankumar2ibm@gmail.com");
		serviceAutoEmailDTO.setFrom("abs@gmail.com");
		if (status.intValue() == 2) {
			serviceAutoEmailDTO
					.setSubject("Submit: ATS Data Central Request # "
							+ requestId + " has been submitted");
		} else if (status.intValue() == 3) {
			serviceAutoEmailDTO.setSubject("Open: ATS Data Central Request # "
					+ requestId + " has been approved");
		} else if (status.intValue() == 4) {
			serviceAutoEmailDTO
					.setSubject("In-Progress: ATS Data Central Request # "
							+ requestId
							+ " status has been changed to In-Progress");
		} else if (status.intValue() == 5) {
			serviceAutoEmailDTO
					.setSubject("Completed: ATS Data Central Request # "
							+ requestId
							+ " status has been changed to Completed");
		} else if (status.intValue() == 6) {
			serviceAutoEmailDTO
					.setSubject("Rejected: ATS Data Central Request # "
							+ requestId
							+ " status has been changed to Rejected");
		} else if (status.intValue() == 7) {
			serviceAutoEmailDTO
					.setSubject("Cancelled: ATS Data Central Request # "
							+ requestId
							+ " status has been changed to Cancelled");
		} else if (status.intValue() == 8) {
			serviceAutoEmailDTO
					.setSubject("Re-Open: ATS Data Central Request # "
							+ requestId + " status has been changed to Re-Open");
		} else if (status.intValue() == 9) {
			serviceAutoEmailDTO
					.setSubject("Pending Clarification: ATS Data Central Request # "
							+ requestId
							+ " status has been changed to Pending Clarification");
		} else if (status.intValue() == 10) {
			serviceAutoEmailDTO
					.setSubject("Closed: ATS Data Central Request # "
							+ requestId + " status has been changed to Closed");
		} else {
			serviceAutoEmailDTO.setSubject("");
		}
		serviceAutoEmailDTO.setSubject("subject");
		emailNotificationService.sendEmail(serviceAutoEmailDTO);
		return emailSend;
	}

	/***
	 * 
	 * @param tdmPolicyAutoSearchDTO
	 * @return
	 * @throws ServiceException
	 *             , DAOException, NoSuchMessageException
	 */
	@Override
	public List<DCAccessLogDTO> getAccessLogByReqId(String requestId)
			throws ServiceException, DAOException, NoSuchMessageException {
		if (requestId == null || requestId == null) {
			return null;
		}

		// Getting the DO for given requestId
		EntityManager entityManager = openCsaaUserEntityManager();
		entityManager.getTransaction().begin();

		// Fetch data From DC_ACCESS_LOG By requestId
		// List<DCAccessLogDO> accessLogDO =
		// saveDataRequestDAO.getAccessLogByReqId(requestId, entityManager);

		List<DCAccessLogDTO> accessLogList = tdmUserMapper
				.convertDCAccessLogListDOToDCAccessLogListDTO(saveDataRequestDAO
						.getAccessLogByReqId(requestId, entityManager));

		entityManager.getTransaction().commit();

		return accessLogList;
	}

	/***
	 * To get all drop down details.
	 * 
	 * @return tdmDataCreationDTO
	 */
	@Override
	public TDMDataCreationDTO getAllDropdownDetails() {
		EntityManager managerCsaaUser = openCsaaUserEntityManager();
		TDMDataCreationDTO tdmDataCreationDTO = null;
		try {
			tdmDataCreationDTO = searchDAO.getAllDropdownData(managerCsaaUser);
		} catch (Exception otherEx) {
			otherEx.printStackTrace();
		}
		return tdmDataCreationDTO;
	}

	/***
	 * To get the data from the DB table FIELD_LIST
	 * 
	 * @param Page
	 *            name
	 * @return dataMap
	 */
	@Override
	public TDMDataCreationDTO getRequestGeneralDetails(String userId) {

		EntityManager managerCsaaUser = openCsaaUserEntityManager();
		TDMDataCreationDTO reqDataDTO = null;
		try {
			reqDataDTO = saveDataRequestDAO.getRequestGeneralDetails(userId,
					managerCsaaUser);
		} catch (Exception otherEx) {
			otherEx.printStackTrace();
		}
		return reqDataDTO;
	}

	private String getDataFromRandomTable(String category, String dictName,
			String state, String columnName) {
		if (category == null || category.isEmpty() || dictName == null
				|| dictName.isEmpty()) {
			return null;
		}
		EntityManager entityManager = openCsaaUserEntityManager();

		String str = null;
		if (dictName.equalsIgnoreCase("Driver DL Number")) {
			String sql = "SELECT * FROM (SELECT VALUE FROM EXCEL_RANDOM_DATA WHERE upper(CATEGORY)='"
					+ category.toUpperCase()
					+ "' AND upper(DICT_NAME) = '"
					+ dictName.toUpperCase()
					+ "' AND upper(STATE)= '"
					+ state
					+ "'  ORDER BY dbms_random.VALUE ) WHERE ROWNUM = 1";
			str = (String) entityManager.createNativeQuery(sql)
					.getSingleResult();

		} else {
			String sql = "SELECT * FROM (SELECT VALUE FROM EXCEL_RANDOM_DATA WHERE upper(CATEGORY)='"
					+ category.toUpperCase()
					+ "' AND upper(DICT_NAME) = '"
					+ dictName.toUpperCase()
					+ "' ORDER BY dbms_random.VALUE ) WHERE ROWNUM = 1";
			str = (String) entityManager.createNativeQuery(sql)
					.getSingleResult();

		}

		entityManager.close();

		return str;
	}

	private String getDefault(String sheetName, String category,
			String dictName, String state, String columnName) {
		if (category == null || category.isEmpty() || dictName == null
				|| dictName.isEmpty() || sheetName == null
				|| sheetName.isEmpty() || columnName == null
				|| columnName.isEmpty()) {
			return null;
		}
		EntityManager entityManager = openCsaaUserEntityManager();

		String sql = "SELECT * FROM (select dict_table from DG_AUTO_EXCEL_TEMPLATE WHERE excel_name = 'Auto Policy Testdata'"
				+ " AND  upper(SHEET_NAME)='"
				+ sheetName.toUpperCase()
				+ "' AND  upper(COLUMN_NAME)='"
				+ columnName.toUpperCase()
				+ "' AND  upper(CATEGORY)='"
				+ category.toUpperCase()
				+ "') WHERE ROWNUM = 1";
		String str = (String) entityManager.createNativeQuery(sql)
				.getSingleResult();

		entityManager.close();

		return str;
	}
		@Override
	public void genarateExcel(String requestId) {
		EntityManager entityManager = openCsaaUserEntityManager();
		try {
			entityManager.getTransaction().begin();
			ServiceRequestDO serviceRequestDO = saveDataRequestDAO
					.getSeriveRequest(requestId, entityManager);
			entityManager.getTransaction().commit();
			// Get Request Details from DB
			TDMDataCreationDTO tdmDataCreationDTO = tdmDataRequestSaveMapper
					.convertSRDetailListDOToTDMDataCreationDTO(searchDAO
							.getReqDetailsByReqId(requestId, entityManager));
			tdmDataCreationDTO.setRequestId(requestId);
			// Get environment data from DB for this requestId
			TDMEnvironmentDO environment = entityManager.find(
					TDMEnvironmentDO.class,String.valueOf(serviceRequestDO.getEnvironment()));
		
			// Copy excel files from templates
			GenerateExcel.createExcelFromTemplates(requestId);

			// Get Run Manager excel data
			List<DGAutoExcelTemplateDO> runManagerExcelDataList = entityManager
					.createNamedQuery("DGAutoExcelTemplateDO.findExcelByName",
							DGAutoExcelTemplateDO.class)
					.setParameter("excelName", "Run Manager").getResultList();
			GenerateExcel.generateRunManagerExcel(tdmDataCreationDTO,
					environment, runManagerExcelDataList);

			// Get Auto Policy Test Data
			List<DGAutoExcelTemplateDO> autoPolicyExcelDataList = entityManager
					.createNamedQuery("DGAutoExcelTemplateDO.findExcelByName",
							DGAutoExcelTemplateDO.class)
					.setParameter("excelName", "Auto Policy Testdata")
					.getResultList();
			writeDataToAutoExcel(autoPolicyExcelDataList,
					tdmDataCreationDTO.getAutoPolicyParamList(), requestId);

			Map<String, List<DGAutoExcelTemplateDO>> dataMap = new HashMap<String, List<DGAutoExcelTemplateDO>>();
			Map<String, List<DGAutoExcelTemplateDO>> propDataMap = new HashMap<String, List<DGAutoExcelTemplateDO>>();
			
	}catch (ServiceException | DAOException | NoSuchMessageException e) {
		logger.warn("Exception occurred in generateExcelsForDGAuto() : "
				+ e.getMessage());
		e.printStackTrace();
	}
		
	
}
}