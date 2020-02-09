/*---------------------------------------------------------------------------------------
 * Object Name: TDMNonstandardSearchResultExcelView.Java
 * --------------------------------------------------------------------------------------
 *
 * Copyright: 2015 Capgemini Financial Services
 *---------------------------------------------------------------------------------------*/

package com.tesda.view;

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

import com.tesda.model.DTO.TDMNonStandReservationDTO;

/**
 * Class which will prepare the data to export in excel format. Exports the On
 * My Reservation dash board records to excel.
 */
public class TDMNonStandardSearchReservationsExcel extends AbstractExcelView
{
	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		HSSFSheet excelSheet = workbook.createSheet("Test Data Reservation Records");
		response.setHeader("Content-Disposition",
				"attachment; filename=\"Test_Data_Reservation_Records.xls\"");
		HSSFCellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(HSSFColor.LIME.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setWrapText(true);

		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.DARK_TEAL.index);
		style.setFont(font);

		HSSFCellStyle style2 = workbook.createCellStyle();

		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style2.setWrapText(true);
		setExcelHeader(excelSheet, style);

		@SuppressWarnings("unchecked")
		List<TDMNonStandReservationDTO> tdmNonStandSearchDTO = (List<TDMNonStandReservationDTO>) model
				.get("tdmNonStandResrvationDTOList");
		setExcelRows(excelSheet, tdmNonStandSearchDTO, style2);
	}

	public void setExcelHeader(HSSFSheet excelSheet, HSSFCellStyle style)
	{
		HSSFRow excelHeader = excelSheet.createRow(2);

		excelSheet.setDisplayGridlines(false);

		int count = 0;
		excelHeader = excelSheet.createRow(2);
		excelHeader = excelSheet.createRow(4);
		excelHeader.createCell(count++).setCellValue("Expires On");
		excelHeader.createCell(count++).setCellValue("Subscriber ID");
		excelHeader.createCell(count++).setCellValue("Member Type");
		excelHeader.createCell(count++).setCellValue("First Name");
		excelHeader.createCell(count++).setCellValue("Last Name");
		excelHeader.createCell(count++).setCellValue("Gender");
		excelHeader.createCell(count++).setCellValue("Date Of Birth");
		excelHeader.createCell(count++).setCellValue("Corp ID");
		excelHeader.createCell(count++).setCellValue("Home Zip Code");
		excelHeader.createCell(count++).setCellValue("Group Number");
		excelHeader.createCell(count++).setCellValue("Account Name");
		excelHeader.createCell(count++).setCellValue("Product Type");
		excelHeader.createCell(count++).setCellValue("PCP/MG");
		excelHeader.createCell(count++).setCellValue("Funding Indicator");
		excelHeader.createCell(count++).setCellValue("Blended Category");
		excelHeader.createCell(count++).setCellValue("Exchange Type");
		excelHeader.createCell(count++).setCellValue("Member Effective Date");
		excelHeader.createCell(count++).setCellValue("Member End Date");
		excelHeader.createCell(count++).setCellValue(
				"Membership Coverage Group Section Effective Date");
		excelHeader.createCell(count++).setCellValue("Membership Coverage Group Section End Date");
		excelHeader.createCell(count++).setCellValue("Original Effective Date");
		excelHeader.createCell(count++).setCellValue("Coverage");
		excelHeader.createCell(count++).setCellValue("Existing Claim(s)");
		excelHeader.createCell(count++).setCellValue("Test Case ID");
		excelHeader.createCell(count++).setCellValue("Test Case Name");
		excelHeader.createCell(count++).setCellValue("Project ID");
		excelHeader.createCell(count++).setCellValue("Reserved Date");
		excelHeader.createCell(count++).setCellValue("Reserved By");

		for (int i = 0; i < count; i++)
		{
			excelHeader.getCell(i).setCellStyle(style);
			excelSheet.autoSizeColumn(i);
		}
	}

	public HSSFColor setColor(HSSFWorkbook workbook, byte r, byte g, byte b)
	{
		HSSFPalette palette = workbook.getCustomPalette();
		HSSFColor hssfColor = null;
		try
		{
			hssfColor = palette.findColor(r, g, b);
			if (hssfColor == null)
			{
				palette.setColorAtIndex(HSSFColor.LAVENDER.index, r, g, b);
				hssfColor = palette.getColor(HSSFColor.LAVENDER.index);
			}
		}
		catch (Exception e)
		{
			logger.error(e);
		}
		return hssfColor;
	}

	public void setExcelRows(HSSFSheet excelSheet,
			List<TDMNonStandReservationDTO> tdmNonStandSearchDTO, HSSFCellStyle style)
	{
		int record = 5;
		if (null != tdmNonStandSearchDTO)
		{
			for (TDMNonStandReservationDTO tdgDataMaskingDTO : tdmNonStandSearchDTO)
			{
				int count = 0;
				HSSFRow excelRow = excelSheet.createRow(record++);
				excelRow.createCell(count++).setCellValue(tdgDataMaskingDTO.getUnreservDate());
				excelRow.createCell(count++).setCellValue(tdgDataMaskingDTO.getSubscrId());
				excelRow.createCell(count++).setCellValue(tdgDataMaskingDTO.getMembrType());
				excelRow.createCell(count++).setCellValue(tdgDataMaskingDTO.getFirstName());
				excelRow.createCell(count++).setCellValue(tdgDataMaskingDTO.getLastName());
				excelRow.createCell(count++).setCellValue(tdgDataMaskingDTO.getGender());
				excelRow.createCell(count++).setCellValue(tdgDataMaskingDTO.getDob());
				excelRow.createCell(count++).setCellValue(tdgDataMaskingDTO.getCorpId());
				excelRow.createCell(count++).setCellValue(tdgDataMaskingDTO.getHomeZipCode());
				excelRow.createCell(count++).setCellValue(tdgDataMaskingDTO.getGroupNum());
				excelRow.createCell(count++).setCellValue(tdgDataMaskingDTO.getAccountName());
				excelRow.createCell(count++).setCellValue(tdgDataMaskingDTO.getProductType());
				excelRow.createCell(count++).setCellValue(tdgDataMaskingDTO.getPcpMG());
				excelRow.createCell(count++).setCellValue(tdgDataMaskingDTO.getFundingInd());
				excelRow.createCell(count++).setCellValue(tdgDataMaskingDTO.getBlendedCat());
				excelRow.createCell(count++).setCellValue(tdgDataMaskingDTO.getExchangeType());
				excelRow.createCell(count++).setCellValue(tdgDataMaskingDTO.getMemEffDate());
				excelRow.createCell(count++).setCellValue(tdgDataMaskingDTO.getMemEndDate());
				excelRow.createCell(count++).setCellValue(tdgDataMaskingDTO.getMcgSecEffDate());
				excelRow.createCell(count++).setCellValue(tdgDataMaskingDTO.getMcgSecEndDate());
				excelRow.createCell(count++).setCellValue(tdgDataMaskingDTO.getOriginalEffDate());
				excelRow.createCell(count++).setCellValue(tdgDataMaskingDTO.getCoverage());
				excelRow.createCell(count++).setCellValue(tdgDataMaskingDTO.getExtClaimType());
				excelRow.createCell(count++).setCellValue(tdgDataMaskingDTO.getTestCaseId());
				excelRow.createCell(count++).setCellValue(tdgDataMaskingDTO.getTestCaseName());
				excelRow.createCell(count++).setCellValue(tdgDataMaskingDTO.getProjectId());
				excelRow.createCell(count++).setCellValue(tdgDataMaskingDTO.getReserveDate());
				excelRow.createCell(count++).setCellValue(tdgDataMaskingDTO.getReservedBy());

				for (int i = 0; i < count; i++)
				{
					excelRow.getCell(i).setCellStyle(style);
					excelSheet.autoSizeColumn(i);
				}
			}
		}
	}
}
