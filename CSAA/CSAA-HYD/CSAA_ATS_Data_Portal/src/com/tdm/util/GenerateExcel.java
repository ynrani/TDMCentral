package com.tdm.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tdm.dao.DataCreationRequestDAO;
import com.tdm.model.DO.DGAutoExcelTemplateDO;
import com.tdm.model.DO.TDMEnvironmentDO;
import com.tdm.model.DTO.AutoPolicyParamsDTO;
import com.tdm.model.DTO.PropertyPolicyParamsDTO;
import com.tdm.model.DTO.TDMDataCreationDTO;

/***
 * File is to generate excels
 * @author gx3jill
 *
 */
@Component
public class GenerateExcel {

	final static Logger logger = Logger.getLogger(GenerateExcel.class);
	public static final String FILE_PATH = "c:\\";
	public static final String RECORD_COUNT = "1";
	private static final String AUTO_EXCEL_NAME = "Auto Policy Testdata";
	private static final String PROPERTY_EXCEL_NAME = "HO Policy Testdata";
	
	@Autowired
	DataCreationRequestDAO saveDataRequestDAO;

	/****
	 * Create excel with given dataMap and excel name
	 * @param dataMap
	 * @param excelName
	 * @param cellDataMap
	 * @return
	 */
	public static Boolean createExcel(Map<String, String[]> dataMap, String excelName
			, Map<String, List<String[]>> cellDataMap ) {
		if(dataMap == null || dataMap.isEmpty()) {
			logger.warn("No meta data found for excel : " + excelName);
			return Boolean.FALSE;
		}
		HSSFWorkbook workbook = new HSSFWorkbook();
		Set<String> keyset = dataMap.keySet();
		HSSFCellStyle style=workbook.createCellStyle();
		Font font=workbook.createFont();
		font.setFontHeightInPoints((short)12);
		font.setFontName(HSSFFont.FONT_ARIAL);
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		font.setBold(true);
		style.setFont(font);

		style.setFillForegroundColor(HSSFColor.OLIVE_GREEN.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		int rownum = 0;
		for (String key : keyset) {
			HSSFSheet sheet=workbook.createSheet(key);

			Row row = sheet.createRow(rownum++);
			Object [] objArr = dataMap.get(key);
			int cellnum = 0;
			for (Object obj : objArr) {
				Cell cell = row.createCell(cellnum++);
				cell.setCellValue((String)obj);
			}
			if(excelName.startsWith("Run Manager")) {
				if(key.contains("Auto")) {
					// Set data to cell data
					setCellData(sheet, cellDataMap.get("AUTO"));
				} else if(key.contains("Property")) {
					// Set data to cell data
					setCellData(sheet, cellDataMap.get("PROPERTY"));
				} else if(key.contains("Application")) {
					// Set data to cell data
					setCellData(sheet, cellDataMap.get("APPLICATION"));
				}
			}

			if(rownum !=0 ) {
				for(int i=0;i<cellnum;i++){
					row.getCell(i).setCellStyle(style);
				}
			}

			/*for(int i=0;i<keyset.size();i++){
				sheet.autoSizeColumn(i);
			}*/
			sheet.autoSizeColumn(0);
			sheet.autoSizeColumn(1);
			sheet.autoSizeColumn(2);
			sheet.autoSizeColumn(3);
			sheet.autoSizeColumn(4);
			sheet.autoSizeColumn(5);
			sheet.autoSizeColumn(6);
			sheet.autoSizeColumn(7);
			sheet.autoSizeColumn(8);
			sheet.autoSizeColumn(9);
			sheet.autoSizeColumn(10);
			sheet.autoSizeColumn(11);
			sheet.autoSizeColumn(12);
			sheet.autoSizeColumn(13);
			sheet.autoSizeColumn(14);
			sheet.autoSizeColumn(15);
			rownum=0;
		}
		createFinalExcel(workbook, FILE_PATH + excelName + ".xls");

		return Boolean.TRUE;
	}

	static Boolean createFinalExcel(HSSFWorkbook workbook, String excelName) {
		// Create excel in the file system
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(new File( excelName));

			workbook.write(fos);
		} catch (IOException e) {
			e.printStackTrace();
			return Boolean.FALSE;
		} finally {
			if (fos != null) {
				try {
					fos.flush();
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} 
		return Boolean.TRUE;
	}

	/***
	 * Set data to the excel sheets
	 * @param sheet
	 * @param autoList
	 */
	private static void setCellData(HSSFSheet sheet, List<String[]> autoList) {
		if(autoList == null || autoList.isEmpty()) {
			return;
		}
		int rownum = 1;
		for (String[] autoRow : autoList) {
			Row excelRow = sheet.createRow(rownum++);
			for (int i = 0; i < autoRow.length; i++) {
				if(autoRow[i] != null && !autoRow[i].isEmpty()) {
					excelRow.createCell(i).setCellValue(autoRow[i]);
				}
			}
		}
	}

	/***
	 * Setting the header style
	 * @param workbook
	 * @return
	 */
	public static HSSFCellStyle getHeaderStyle(final HSSFWorkbook workbook){
		HSSFCellStyle style=workbook.createCellStyle();
		Font font=workbook.createFont();
		font.setFontHeightInPoints((short)12);
		font.setFontName(HSSFFont.FONT_ARIAL);
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		style.setFont(font);
		return style;
	}

	/***
	 * Generate Run manager excel for give inputs
	 * @param tdmDataCreationDTO
	 * @param runManagerExcelDataList
	 */
	public static void generateRunManagerExcel(
			TDMDataCreationDTO tdmDataCreationDTO,
			TDMEnvironmentDO environment,
			List<DGAutoExcelTemplateDO> runManagerExcelDataList) {
		// Get excel meta data like sheets, columns
		Map<String, String[]> excelMetaDataMap = getExcelMetaData(runManagerExcelDataList);	
		if(excelMetaDataMap.isEmpty()) {
			logger.warn("Run manager excel Meta data is empty, quitting excel generation process.");
		}
		List<AutoPolicyParamsDTO> autoParamsList = tdmDataCreationDTO.getAutoPolicyParamList();
		List<PropertyPolicyParamsDTO> propertyParamsList = tdmDataCreationDTO.getPropertyPolicyParamList();
		Map<String, List<String[]>> cellDataMap = new HashMap<String, List<String[]>>();

		// Handling the Auto sheet Rows
		if(autoParamsList != null && !autoParamsList.isEmpty()) {
			List<String[]> rowDataList = new ArrayList<String[]>();
			for (AutoPolicyParamsDTO autoParamsDTO : autoParamsList) {
				String[] rowData = {"Auto Pilicy Test Data", "AUTO_"+autoParamsDTO.getScenarioNo()
						, autoParamsDTO.getAutomationScenario(), "Yes", environment.getUsername()
						, environment.getPassword(), RECORD_COUNT, environment.getEnvironmentName(), "RunAllIterations"
						, null, null, "Chrome", null, "WINDOWS", autoParamsDTO.getRiskState()};
				rowDataList.add(rowData);
			}
			cellDataMap.put("AUTO", rowDataList);
		}

		// Handling the Property sheet Rows
		if(propertyParamsList != null && !propertyParamsList.isEmpty()) {
			List<String[]> rowDataList = new ArrayList<String[]>();
			for (PropertyPolicyParamsDTO propertyParamsDTO : propertyParamsList) {
				String[] rowData = {"Property Pilicy Test Data", "PROPERTY_"+propertyParamsDTO.getScenarioNo()
						, propertyParamsDTO.getAutomationScenario(), "Yes", environment.getUsername()
						, environment.getPassword(), RECORD_COUNT, environment.getEnvironmentName(), "RunAllIterations"
						, null, null, "Chrome", null, "WINDOWS", propertyParamsDTO.getRiskState(), "1"};
				rowDataList.add(rowData);
			}
			cellDataMap.put("PROPERTY", rowDataList);
		}
		String[] applicationData = {"1", environment.getEnvironmentName(), environment.getApplicationUrl() };
		List<String[]> appList = new ArrayList<String[]>();
		appList.add(applicationData);
		cellDataMap.put("APPLICATION", appList);
		//Create the final excel sheets and set cell data
		createExcel(excelMetaDataMap, "Run Manager_"+tdmDataCreationDTO.getRequestId(), cellDataMap);
	}

	/***
	 * To generate Auto Policy Test data
	 * @param autoPolicyParamList
	 * @param runManagerExcelDataList
	 */
	public static Boolean generateAutoPolicyExcel(
			List<AutoPolicyParamsDTO> autoPolicyParamList,
			String requestId, 
			List<DGAutoExcelTemplateDO> runManagerExcelDataList,
			Map<String, List<DGAutoExcelTemplateDO>> dataMap) {
		String excelName = "Auto Policy Test Data_" + requestId;
		Map<String, String[]> excelMetaDataMap = getExcelMetaData(runManagerExcelDataList);	
		if(excelMetaDataMap ==null  ||excelMetaDataMap.isEmpty()) {
			logger.warn("Auto Policy Test data excel Meta data is empty, quitting excel generation process.");
		}

		if(dataMap == null || dataMap.isEmpty()) {
			logger.warn("No meta data found for excel : " + excelName);
			return Boolean.FALSE;
		}
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFCellStyle style=workbook.createCellStyle();
		Font font=workbook.createFont();
		font.setFontHeightInPoints((short)12);
		font.setFontName(HSSFFont.FONT_ARIAL);
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		font.setBold(true);
		style.setFont(font);

		style.setFillForegroundColor(HSSFColor.OLIVE_GREEN.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		int rownum = 0;
		for (String key : excelMetaDataMap.keySet()) {
			HSSFSheet sheet=workbook.createSheet(key);

			Row row = sheet.createRow(rownum++);
			Object [] objArr = excelMetaDataMap.get(key);
			int cellnum = 0;
			for (Object obj : objArr) {
				Cell cell = row.createCell(cellnum++);
				cell.setCellValue((String)obj);
			}
			if(rownum !=0 ) {
				for(int i=0;i<cellnum;i++){
					row.getCell(i).setCellStyle(style);
				}
			}
			rownum=0;
		}
		if(autoPolicyParamList != null && !autoPolicyParamList.isEmpty()) {
			List<String> dataList = new ArrayList<String>();
			for (AutoPolicyParamsDTO autoPolicyParam : autoPolicyParamList) {
//				dataList.add(e);
			}
		}
		for (String key : dataMap.keySet()) {
			List<DGAutoExcelTemplateDO> autoTemplateList = dataMap.get(key);
			if(autoTemplateList != null && !autoTemplateList.isEmpty()) {
				for (DGAutoExcelTemplateDO dgAutoExcelTemplateDO : autoTemplateList) {
//					setColumnData(workbook, dgAutoExcelTemplateDO.getSheetName(), dgAutoExcelTemplateDO.getColumnName(), dataList);
				}
			}
		}
		createFinalExcel(workbook, FILE_PATH + excelName + ".xls");

		return Boolean.TRUE;

		//Create the final excel sheets and set cell data
		//		createAutoExcel(excelMetaDataMap, excelName, dataMap);
	}


	/***
	 * To generate Property policy test data Excel
	 * @param propertyPolicyParamList
	 * @param runManagerExcelDataList
	 */
	public static void generatePropertyPolicyExcel(
			List<PropertyPolicyParamsDTO> propertyPolicyParamList,
			String requestId,
			List<DGAutoExcelTemplateDO> runManagerExcelDataList, 
			Map<String, List<DGAutoExcelTemplateDO>> dataMap) {
		Map<String, String[]> excelMetaDataMap = getExcelMetaData(runManagerExcelDataList);	
		if(excelMetaDataMap ==null  || excelMetaDataMap.isEmpty()) {
			logger.warn("Property Policy Test data excel Meta data is empty, quitting excel generation process.");
		}
		//Create the final excel sheets and set cell data
		createExcel(excelMetaDataMap, "Property Policy Test Data_" + requestId, null);
	}

	/***
	 * To get the excel meta information from the given templateDO  list
	 * @param excelTemplateList
	 * @return
	 */
	static Map<String, String[]> getExcelMetaData(List<DGAutoExcelTemplateDO> excelTemplateList) {
		if(excelTemplateList == null || excelTemplateList.isEmpty()) {
			logger.warn("Excel template List from DB empty.");
			return null;
		}
		Map<String, String[]> finalexcelMetaDataMap  =new LinkedHashMap<String, String[]>();
		Map<String, List<String>> excelMetaDataMap =new LinkedHashMap<String, List<String>>();
		// Convert to sheets and column headers
		for (DGAutoExcelTemplateDO excelTemplate : excelTemplateList) {
			List<String> colList = new ArrayList<String>();
			String sheetName = excelTemplate.getSheetName();
			if(excelMetaDataMap.containsKey(sheetName)){
				colList = excelMetaDataMap.get(sheetName);
			} 
			colList.add(excelTemplate.getColumnName());
			excelMetaDataMap.put(sheetName, colList);
		}
		for (String sheetName : excelMetaDataMap.keySet()) {
			List<String> dataList = excelMetaDataMap.get(sheetName);
			String[] colArr = dataList.toArray(new String[dataList.size()]);
			finalexcelMetaDataMap.put(sheetName, colArr);
		}
		return finalexcelMetaDataMap;
	}

	/****
	 * To set the given data at given column below rows
	 * @param filename
	 * @param sheetName
	 * @param columnName
	 * @param dataList
	 */
	public static void setColumnData(HSSFWorkbook workbook, String sheetName, String columnName, List<String> dataList ){
		try {
//			FileInputStream file = new FileInputStream(new File(filename));

//			HSSFWorkbook workbook = new HSSFWorkbook(file);
			HSSFSheet sheet = workbook.getSheet(sheetName);
			Row row  = sheet.getRow(0);
			Integer columnIndex = 0;
			Iterator<Cell> cellIterator = row.cellIterator();
			// Find the given column index in the excel
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				if(cell.getStringCellValue().equalsIgnoreCase(columnName)) {
					columnIndex = cell.getColumnIndex();
					break;
				}
			}
			// For setting the data into the excel cells
			if(dataList != null && !dataList.isEmpty()) {
				Integer rowNum = 1;
				for (String value : dataList) {
					Row currentRow = sheet.createRow(rowNum++);
					for (int colIdx = 0; colIdx < columnIndex+1; colIdx++) {
						if(columnIndex == colIdx) {
							currentRow.createCell(colIdx).setCellValue(value);
						} else {
							currentRow.createCell(colIdx);
						}
					}
				}
			}
//			file.close();
//			// Writing the data to the excel back 
//			FileOutputStream outFile =new FileOutputStream(new File(filename));
//			workbook.write(outFile);
//			outFile.close();
		}catch(Exception ioe) {
			ioe.printStackTrace();
		}
	}
	
	/***
	 * To copy excels from Src to destination
	 * @param srcFile
	 * @param destFile
	 * @throws IOException
	 */
	static void copyFile(File srcFile, File destFile) {
		try {
			Long start = System.currentTimeMillis();
			destFile.createNewFile();
			InputStream oInStream = new FileInputStream(srcFile);
			OutputStream oOutStream = new FileOutputStream(destFile);

			// Transfer bytes from in to out
			byte[] oBytes = new byte[1024];
			int nLength;
			BufferedInputStream oBuffInputStream = 
					new BufferedInputStream( oInStream );
			while ((nLength = oBuffInputStream.read(oBytes)) > 0) {
				oOutStream.write(oBytes, 0, nLength);
			}
			oInStream.close();
			oOutStream.close();
			Long end = System.currentTimeMillis();
			System.out.println((end-start));
		} catch (IOException e) {
			logger.info("Error in creating the excels :" +e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	/***
	 * To copy excels from templates
	 * @param requestId
	 */
	public static void createExcelFromTemplates(String requestId) {
		String from_path="C:\\Excel_templates\\" + AUTO_EXCEL_NAME + ".xls";
		File srcFile =new File(from_path);
		File destFile =new File("C:\\1\\" + AUTO_EXCEL_NAME + "_" + requestId + ".xls");
		if(!destFile.exists()){
			copyFile(srcFile, destFile);
		}
		from_path="C:\\Excel_templates\\" + PROPERTY_EXCEL_NAME + ".xls";
		srcFile =new File(from_path);
		destFile =new File("C:\\1\\" + PROPERTY_EXCEL_NAME + "_" + requestId + ".xls");
		if(!destFile.exists()){
			copyFile(srcFile, destFile);
		}
	}
	public static void main(String[] args) {
		createExcelFromTemplates("00001");
	}
}