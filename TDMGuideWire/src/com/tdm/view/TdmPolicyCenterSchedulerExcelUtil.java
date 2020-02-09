/*
 * Object Name : TdmPolicyCenterSchedulerExcelUtil.java
 * Modification Block
 * ------------------------------------------------------------------
 * S.No.	Name 			Date			Bug_Fix_No			Desc
 * ------------------------------------------------------------------
 * 	1.	  vkrish14		8:55:11 AM				Created
 * ------------------------------------------------------------------
 * Copyrights: 2015 Capgemini.com
 */
package com.tdm.view;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.tdm.model.DTO.TdmPolicyCenterSearchResultDTO;

/**
 * @author vkrish14
 *
 */
public class TdmPolicyCenterSchedulerExcelUtil{



	@SuppressWarnings("resource")
	public void buildExcelDocument(List<TdmPolicyCenterSearchResultDTO> searchPolicyPropOutputList,String strFileName,String testCaseId,String testCaseName) throws Exception {
		XSSFWorkbook workbook = null;
		FileOutputStream out = null;
		try{
			File file = new File(strFileName);
			if(file.createNewFile())
				System.out.println("file created");
			System.out.println(file.getAbsolutePath());
			file.setReadable(true);
			file.setWritable(true);
			
			 workbook = new XSSFWorkbook();

			// Create a blank sheet
			XSSFSheet sheet = workbook.createSheet("Policy_Reserved_Record");
			
		//workbook = new HSSFWorkbook(new FileInputStream(file));
		//XSSSheet excelSheet = workbook.createSheet("Policy_Reserved_Record");

		XSSFCellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(HSSFColor.LIME.index);
		style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);

		style.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		style.setBorderTop(XSSFCellStyle.BORDER_THIN);
		style.setBorderRight(XSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(XSSFCellStyle.BORDER_THIN);

		XSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.DARK_TEAL.index);
		style.setFont(font);

		XSSFCellStyle style2 = workbook.createCellStyle();

		style2.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		style2.setBorderTop(XSSFCellStyle.BORDER_THIN);
		style2.setBorderRight(XSSFCellStyle.BORDER_THIN);
		style2.setBorderLeft(XSSFCellStyle.BORDER_THIN);

		// cell.setCellStyle(style);
		setExcelHeader(sheet, style);
			//setExcelHeader(sheet);
			setExcelRows(sheet, searchPolicyPropOutputList,style2,testCaseId,testCaseName);
        out = new FileOutputStream(new File(strFileName));
        workbook.write(out);
		//setExcelRows(excelSheet, searchPolicyPropOutputList, style2);
		}finally{
			/*if(workbook != null)
				workbook.close();*/
			//out.flush();
		}

	}

	public void setExcelHeader(XSSFSheet excelSheet,XSSFCellStyle style) {
		Row row = excelSheet.createRow(1);

		excelSheet.setDisplayGridlines(false);

		//excelHeader = excelSheet.createRow(2);
		//excelHeader.createCell(0).setCellValue("User ID ");

		//excelHeader = excelSheet.createRow(4);
		row.createCell(0).setCellValue("Test Case Id");
		row.createCell(1).setCellValue("Test Case Name");

		row.createCell(2).setCellValue("Policy Number");
		row.createCell(3).setCellValue("Account Number");
		row.createCell(4).setCellValue("Policy Type");
		row.createCell(5).setCellValue("First Name");
		row.createCell(6).setCellValue("Last Name");
		row.createCell(7).setCellValue("Insurer Type");
		row.createCell(8).setCellValue("Email Address1");
		row.createCell(9).setCellValue("Email Address2");
		row.createCell(10).setCellValue("Address Line1");
		row.createCell(11).setCellValue("Address Line2");
		row.createCell(12).setCellValue("Address Line3");
		row.createCell(13).setCellValue("Original Effective Date");
		// excelHeader.createCell(9).setCellValue("Construction Type");
		// excelHeader.createCell(10).setCellValue("Valuation method");
		// excelHeader.createCell(11).setCellValue("Year built");
		// excelHeader.createCell(12).setCellValue("Roof year");
		row.createCell(14).setCellValue("Expiration Date");
		row.createCell(15).setCellValue("Phone Number");
		row.createCell(16).setCellValue("Country");
		/*
		 * 
		 * excelHeader.createCell(14).setCellValue("EFT");
		 * excelHeader.createCell(15).setCellValue("Gender");
		 * excelHeader.createCell(16).setCellValue("Term Date");
		 * excelHeader.createCell(17).setCellValue("Address Line 1");
		 * excelHeader.createCell(18).setCellValue("Address Line 2");
		 * excelHeader.createCell(19).setCellValue("City");
		 * excelHeader.createCell(20).setCellValue("State");
		 */


		row.getCell(0).setCellStyle(style);
		row.getCell(1).setCellStyle(style);
		row.getCell(2).setCellStyle(style);
		row.getCell(3).setCellStyle(style);
		row.getCell(4).setCellStyle(style);
		row.getCell(5).setCellStyle(style);
		row.getCell(6).setCellStyle(style);
		row.getCell(7).setCellStyle(style);
		row.getCell(8).setCellStyle(style);
		row.getCell(9).setCellStyle(style);
		// excelHeader.getCell(10).setCellStyle(style);
		// excelHeader.getCell(11).setCellStyle(style);
		// excelHeader.getCell(12).setCellStyle(style);
		//row.getCell(9).setCellStyle(style);
		row.getCell(10).setCellStyle(style);
		row.getCell(11).setCellStyle(style);
		row.getCell(12).setCellStyle(style);
		row.getCell(13).setCellStyle(style);
		row.getCell(14).setCellStyle(style);
		row.getCell(15).setCellStyle(style);
		row.getCell(16).setCellStyle(style);
		/*excelHeader.getCell(15).setCellStyle(style);*/
		/*
		 * 
		 * excelHeader.getCell(15).setCellStyle(style); excelHeader.getCell(16).setCellStyle(style);
		 * excelHeader.getCell(17).setCellStyle(style); excelHeader.getCell(18).setCellStyle(style);
		 * excelHeader.getCell(19).setCellStyle(style); excelHeader.getCell(20).setCellStyle(style);
		 */

		excelSheet.autoSizeColumn(0);
		excelSheet.autoSizeColumn(1);
		excelSheet.autoSizeColumn(2);
		excelSheet.autoSizeColumn(3);
		excelSheet.autoSizeColumn(4);
		excelSheet.autoSizeColumn(5);
		excelSheet.autoSizeColumn(6);
		excelSheet.autoSizeColumn(7);
		excelSheet.autoSizeColumn(8);
		// excelSheet.autoSizeColumn(9);
		// excelSheet.autoSizeColumn(10);
		// excelSheet.autoSizeColumn(11);
		// excelSheet.autoSizeColumn(12);
		excelSheet.autoSizeColumn(9);
		excelSheet.autoSizeColumn(10);
		excelSheet.autoSizeColumn(11);
		excelSheet.autoSizeColumn(12);
		excelSheet.autoSizeColumn(13);
		excelSheet.autoSizeColumn(14);
		excelSheet.autoSizeColumn(15);
		excelSheet.autoSizeColumn(16);
		
		/*
		 * 
		 * 
		 * excelSheet.autoSizeColumn(15); excelSheet.autoSizeColumn(16);
		 * excelSheet.autoSizeColumn(17); excelSheet.autoSizeColumn(18);
		 * excelSheet.autoSizeColumn(20);
		 */

	}

	public HSSFColor setColor(HSSFWorkbook workbook, byte r, byte g, byte b) {
		HSSFPalette palette = workbook.getCustomPalette();
		HSSFColor hssfColor = null;
		try {
			hssfColor = palette.findColor(r, g, b);
			if (hssfColor == null) {
				palette.setColorAtIndex(HSSFColor.LAVENDER.index, r, g, b);
				hssfColor = palette.getColor(HSSFColor.LAVENDER.index);
			}
		} catch (Exception e) {

		}

		return hssfColor;
	}

	public void setExcelRows(XSSFSheet excelSheet,
			List<TdmPolicyCenterSearchResultDTO> searchPolicyPropOutputList, XSSFCellStyle style,String testCaseId,String testCaseName) {
		int record = 2;

		for (TdmPolicyCenterSearchResultDTO tdmPolicyPropertySearchResultDTO : searchPolicyPropOutputList) {
			Row excelRow = excelSheet.createRow(record++);
			excelRow.createCell(0).setCellValue(testCaseId);
			excelRow.createCell(1).setCellValue(testCaseName);
			excelRow.createCell(2).setCellValue(tdmPolicyPropertySearchResultDTO.getPolicynumber());
			excelRow.createCell(3).setCellValue(tdmPolicyPropertySearchResultDTO.getAccoutnumber());
			excelRow.createCell(4).setCellValue(tdmPolicyPropertySearchResultDTO.getProductType());
			excelRow.createCell(5).setCellValue(tdmPolicyPropertySearchResultDTO.getFirstName());
			excelRow.createCell(6).setCellValue(tdmPolicyPropertySearchResultDTO.getLastName());
			excelRow.createCell(7).setCellValue(tdmPolicyPropertySearchResultDTO.getInsurerType());
			excelRow.createCell(8).setCellValue(tdmPolicyPropertySearchResultDTO.getEmailAddress());
			excelRow.createCell(9).setCellValue(tdmPolicyPropertySearchResultDTO.getEmailAddress2());
			excelRow.createCell(10).setCellValue(
					tdmPolicyPropertySearchResultDTO.getAddressLine1());
			excelRow.createCell(11).setCellValue(
					tdmPolicyPropertySearchResultDTO.getAddressLine2());
			excelRow.createCell(12).setCellValue(
					tdmPolicyPropertySearchResultDTO.getAddressLine3());
			excelRow.createCell(13).setCellValue(tdmPolicyPropertySearchResultDTO.getOriginalEffectiveDate());

			// excelRow.createCell(9).setCellValue(
			// tdmPolicyPropertySearchResultDTO.getConstructionType());
			// excelRow.createCell(10).setCellValue(
			// tdmPolicyPropertySearchResultDTO.getValuationMethod());
			// excelRow.createCell(11).setCellValue(tdmPolicyPropertySearchResultDTO.getYearBuilt());
			// excelRow.createCell(12).setCellValue(tdmPolicyPropertySearchResultDTO.getRoofYear());

			excelRow.createCell(14).setCellValue(tdmPolicyPropertySearchResultDTO.getExpirationDate());
			excelRow.createCell(15).setCellValue(tdmPolicyPropertySearchResultDTO.getWorkPhoneNo());
			excelRow.createCell(16).setCellValue(tdmPolicyPropertySearchResultDTO.getCountry());

			/*
			 * 
			 * 
			 * 
			 * excelRow.createCell(15).setCellValue(tdmPolicyPropertySearchResultDTO.getProvGender())
			 * ; excelRow.createCell(16).setCellValue(
			 * tdmPolicyPropertySearchResultDTO.getTerminationDate());
			 * excelRow.createCell(17).setCellValue
			 * (tdmPolicyPropertySearchResultDTO.getProvAddr1());
			 * excelRow.createCell(18).setCellValue
			 * (tdmPolicyPropertySearchResultDTO.getProvAddr2());
			 * excelRow.createCell(19).setCellValue(tdmPolicyPropertySearchResultDTO.getProvCity());
			 * excelRow
			 * .createCell(20).setCellValue(tdmPolicyPropertySearchResultDTO.getProvState());
			 */

			/*excelRow.createCell(0).setCellValue(tdmPolicyPropertySearchResultDTO.getTestCaseId());
			excelRow.createCell(1).setCellValue(tdmPolicyPropertySearchResultDTO.getTestCaseName());*/

			excelRow.getCell(0).setCellStyle(style);
			excelRow.getCell(1).setCellStyle(style);
			excelRow.getCell(2).setCellStyle(style);
			excelRow.getCell(3).setCellStyle(style);
			excelRow.getCell(4).setCellStyle(style);
			excelRow.getCell(5).setCellStyle(style);
			excelRow.getCell(6).setCellStyle(style);
			excelRow.getCell(7).setCellStyle(style);
			excelRow.getCell(8).setCellStyle(style);
			// excelRow.getCell(9).setCellStyle(style);
			// excelRow.getCell(10).setCellStyle(style);
			// excelRow.getCell(11).setCellStyle(style);
			// excelRow.getCell(12).setCellStyle(style);
			excelRow.getCell(9).setCellStyle(style);
			excelRow.getCell(10).setCellStyle(style);
			excelRow.getCell(11).setCellStyle(style);
			excelRow.getCell(12).setCellStyle(style);
			excelRow.getCell(13).setCellStyle(style);
			excelRow.getCell(14).setCellStyle(style);
			excelRow.getCell(15).setCellStyle(style);
			
			/*
			 * 
			 * 
			 * 
			 * excelRow.getCell(15).setCellStyle(style); excelRow.getCell(16).setCellStyle(style);
			 * excelRow.getCell(17).setCellStyle(style); excelRow.getCell(18).setCellStyle(style);
			 * excelRow.getCell(19).setCellStyle(style); excelRow.getCell(20).setCellStyle(style);
			 */

			excelSheet.autoSizeColumn(0);
			excelSheet.autoSizeColumn(1);
			excelSheet.autoSizeColumn(2);
			excelSheet.autoSizeColumn(3);
			excelSheet.autoSizeColumn(4);
			excelSheet.autoSizeColumn(5);
			excelSheet.autoSizeColumn(6);
			excelSheet.autoSizeColumn(7);
			excelSheet.autoSizeColumn(8);
			// excelSheet.autoSizeColumn(9);
			// excelSheet.autoSizeColumn(10);
			// excelSheet.autoSizeColumn(11);
			// excelSheet.autoSizeColumn(12);
			excelSheet.autoSizeColumn(9);
			excelSheet.autoSizeColumn(10);
			excelSheet.autoSizeColumn(11);
			excelSheet.autoSizeColumn(12);
			excelSheet.autoSizeColumn(13);
			excelSheet.autoSizeColumn(14);
			excelSheet.autoSizeColumn(15);
			
			/*
			 * 
			 * 
			 * 
			 * excelSheet.autoSizeColumn(15); excelSheet.autoSizeColumn(16);
			 * excelSheet.autoSizeColumn(17); excelSheet.autoSizeColumn(18);
			 * excelSheet.autoSizeColumn(19); excelSheet.autoSizeColumn(20);
			 */

		}
	}

}
