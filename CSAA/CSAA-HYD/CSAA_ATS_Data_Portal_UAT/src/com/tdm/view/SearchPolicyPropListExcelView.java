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

import com.tdm.model.DTO.TdmPolicyPropertySearchResultDTO;

public class SearchPolicyPropListExcelView extends AbstractExcelView
{

	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HSSFSheet excelSheet = workbook.createSheet("Property Policy");

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
		List<TdmPolicyPropertySearchResultDTO> searchPolicyPropOutputList = (List<TdmPolicyPropertySearchResultDTO>) model
				.get("tdmPolicyPropertySearchResultDTOs");
		setExcelRows(excelSheet, searchPolicyPropOutputList, style2);

	}

	public void setExcelHeader(HSSFSheet excelSheet, HSSFCellStyle style) {
		HSSFRow excelHeader = excelSheet.createRow(2);

		excelSheet.setDisplayGridlines(false);

		excelHeader = excelSheet.createRow(2);

		excelHeader = excelSheet.createRow(4);
		excelHeader.createCell(0).setCellValue("User ID ");
		excelHeader.createCell(1).setCellValue("Policy Number");
		excelHeader.createCell(2).setCellValue("Product type");
		excelHeader.createCell(3).setCellValue("Policy Status");
		excelHeader.createCell(4).setCellValue("Policy State");
		excelHeader.createCell(5).setCellValue("Total Due");

		excelHeader.getCell(0).setCellStyle(style);
		excelHeader.getCell(1).setCellStyle(style);
		excelHeader.getCell(2).setCellStyle(style);
		excelHeader.getCell(3).setCellStyle(style);
		excelHeader.getCell(4).setCellStyle(style);
		excelHeader.getCell(5).setCellStyle(style);

		excelSheet.autoSizeColumn(0);
		excelSheet.autoSizeColumn(1);
		excelSheet.autoSizeColumn(2);
		excelSheet.autoSizeColumn(3);
		excelSheet.autoSizeColumn(4);
		excelSheet.autoSizeColumn(5);

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
			List<TdmPolicyPropertySearchResultDTO> searchPolicyPropOutputList, HSSFCellStyle style) {
		int record = 5;
		if(searchPolicyPropOutputList == null || searchPolicyPropOutputList.isEmpty()) {
			return;
		}
		for (TdmPolicyPropertySearchResultDTO tdmPolicyPropertySearchResultDTO : searchPolicyPropOutputList) {
			HSSFRow excelRow = excelSheet.createRow(record++);
			excelRow.createCell(0).setCellValue(tdmPolicyPropertySearchResultDTO.getUserId());
			excelRow.createCell(1).setCellValue(tdmPolicyPropertySearchResultDTO.getPolicynumber());
			excelRow.createCell(2).setCellValue(tdmPolicyPropertySearchResultDTO.getProductType());
			excelRow.createCell(3).setCellValue(tdmPolicyPropertySearchResultDTO.getPolicyStage());
			excelRow.createCell(4).setCellValue(tdmPolicyPropertySearchResultDTO.getPolicyState());
			excelRow.createCell(5).setCellValue((tdmPolicyPropertySearchResultDTO.getTotalDue()==null?0D:Double.valueOf(tdmPolicyPropertySearchResultDTO.getTotalDue())));

			excelRow.getCell(0).setCellStyle(style);
			excelRow.getCell(1).setCellStyle(style);
			excelRow.getCell(2).setCellStyle(style);
			excelRow.getCell(3).setCellStyle(style);
			excelRow.getCell(4).setCellStyle(style);
			excelRow.getCell(5).setCellStyle(style);

			excelSheet.autoSizeColumn(0);
			excelSheet.autoSizeColumn(1);
			excelSheet.autoSizeColumn(2);
			excelSheet.autoSizeColumn(3);
			excelSheet.autoSizeColumn(4);
			excelSheet.autoSizeColumn(5);
		}
	}
}