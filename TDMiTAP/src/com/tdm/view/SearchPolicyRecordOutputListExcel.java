/*---------------------------------------------------------------------------------------
 * Object Name: SearchPolicyRecordOutputListExcel.Java
 * 
 * Modification Block:
 * --------------------------------------------------------------------------------------
 * S.No. Name                Date      Bug Fix no. Desc
 * --------------------------------------------------------------------------------------
 * 1     Seshadri Chowdary          3/09/15  NA          Created
 * --------------------------------------------------------------------------------------
 *
 * Copyright: 2015 <CapGemini>
 *---------------------------------------------------------------------------------------*/
package com.tdm.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.tdm.model.DTO.TdmPolicyCenterSearchResultDTO;

/**
 * 
 * @author Seshadri Chowdary
 *
 */
public class SearchPolicyRecordOutputListExcel extends AbstractExcelView
{

	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HSSFSheet excelSheet = workbook.createSheet("Policy Property Search Reserve Record");

		HSSFCellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(HSSFColor.LIME.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);

		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.DARK_TEAL.index);
		style.setFont(font);

		HSSFCellStyle style2 = workbook.createCellStyle();

		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);

		// cell.setCellStyle(style);
		setExcelHeader(excelSheet, style);

		@SuppressWarnings("unchecked")
		List<TdmPolicyCenterSearchResultDTO> searchPolicyPropOutputList = (List<TdmPolicyCenterSearchResultDTO>) model
				.get("tdmPolicyCenterSearchDTO");
		setExcelRows(excelSheet, searchPolicyPropOutputList, style2);

	}

	public void setExcelHeader(HSSFSheet excelSheet, HSSFCellStyle style) {
		HSSFRow excelHeader = excelSheet.createRow(2);

		excelSheet.setDisplayGridlines(false);

		excelHeader = excelSheet.createRow(2);
		//excelHeader.createCell(0).setCellValue("User ID ");

		excelHeader = excelSheet.createRow(4);
		excelHeader.createCell(0).setCellValue("Test Case Id");
		excelHeader.createCell(1).setCellValue("Test Case Name");
		excelHeader.createCell(2).setCellValue("Policy Number");
		excelHeader.createCell(3).setCellValue("Account Number");
		excelHeader.createCell(4).setCellValue("Policy Type");
		excelHeader.createCell(5).setCellValue("First Name");
		excelHeader.createCell(6).setCellValue("Last Name");
		excelHeader.createCell(7).setCellValue("Insurer Type");
		excelHeader.createCell(8).setCellValue("Email Address1");
		excelHeader.createCell(9).setCellValue("Email Address2");
		excelHeader.createCell(10).setCellValue("Address Line1");
		excelHeader.createCell(11).setCellValue("Address Line2");
		excelHeader.createCell(12).setCellValue("Address Line3");
		excelHeader.createCell(13).setCellValue("Original Effective Date");
		excelHeader.createCell(14).setCellValue("Expiration Date");
		excelHeader.createCell(15).setCellValue("Phone Number");
		excelHeader.createCell(16).setCellValue("Country");
		
		excelHeader.getCell(0).setCellStyle(style);
		excelHeader.getCell(1).setCellStyle(style);
		excelHeader.getCell(2).setCellStyle(style);
		excelHeader.getCell(3).setCellStyle(style);
		excelHeader.getCell(4).setCellStyle(style);
		excelHeader.getCell(5).setCellStyle(style);
		excelHeader.getCell(6).setCellStyle(style);
		excelHeader.getCell(7).setCellStyle(style);
		excelHeader.getCell(8).setCellStyle(style);
		// excelHeader.getCell(9).setCellStyle(style);
		// excelHeader.getCell(10).setCellStyle(style);
		// excelHeader.getCell(11).setCellStyle(style);
		// excelHeader.getCell(12).setCellStyle(style);
		excelHeader.getCell(9).setCellStyle(style);
		excelHeader.getCell(10).setCellStyle(style);
		excelHeader.getCell(11).setCellStyle(style);
		excelHeader.getCell(12).setCellStyle(style);
		excelHeader.getCell(13).setCellStyle(style);
		excelHeader.getCell(14).setCellStyle(style);
		excelHeader.getCell(15).setCellStyle(style);
		excelHeader.getCell(16).setCellStyle(style);
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
			logger.error(e);
		}

		return hssfColor;
	}

	public void setExcelRows(HSSFSheet excelSheet,
			List<TdmPolicyCenterSearchResultDTO> searchPolicyPropOutputList, HSSFCellStyle style) {
		int record = 5;

		for (TdmPolicyCenterSearchResultDTO tdmPolicyPropertySearchResultDTO : searchPolicyPropOutputList) {
			HSSFRow excelRow = excelSheet.createRow(record++);
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

			excelRow.createCell(0).setCellValue(tdmPolicyPropertySearchResultDTO.getTestCaseId());
			excelRow.createCell(1).setCellValue(tdmPolicyPropertySearchResultDTO.getTestCaseName());

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
			excelRow.getCell(16).setCellStyle(style);

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
			excelSheet.autoSizeColumn(16);
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
