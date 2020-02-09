/*---------------------------------------------------------------------------------------
 * Object Name: DataMaskingDashBoardExcel.Java
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.tesda.constants.TDMConstants;
import com.tesda.model.DTO.TdmDataMaskingDTO;

/**
 * Class which will prepare the data to export in excel format.
 * Exports the On Boarding Request dash board records to excel.
 * 
 */
public class DataMaskingDashBoardExcel extends AbstractExcelView
{

	private static final Logger logger = LoggerFactory.getLogger(DataMaskingDashBoardExcel.class);

	boolean isCR = false;
	boolean isMasking = false;

	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		HSSFSheet excelSheet = workbook.createSheet("Data Masking DashBoard Records");
		response.setHeader("Content-Disposition",
				"attachment; filename=\"DataMasking_DashBoard.xls\"");
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

		@SuppressWarnings("unchecked")
		List<TdmDataMaskingDTO> tdgDataMaskingDTOs = (List<TdmDataMaskingDTO>) model
				.get("tdgDataMaskingDTOs");
		setExcelRows(excelSheet, tdgDataMaskingDTOs, style2);

		setExcelHeader(excelSheet, style);

		if (isMasking)
		{
			if (isCR)
			{
				response.setHeader("Content-Disposition",
						"attachment; filename=\"CR_DataMasking_DashBoard.xls\"");
				workbook.setSheetName(0, "Data Masking CR Records");
			}
			else
			{
				response.setHeader("Content-Disposition",
						"attachment; filename=\"DataMasking_DashBoard.xls\"");
				workbook.setSheetName(0, "Data Masking Records");
			}
		}
		else
		{
			if (isCR)
			{
				response.setHeader("Content-Disposition",
						"attachment; filename=\"CR_TDMOnBoarding_DashBoard.xls\"");
				workbook.setSheetName(0, "TDM On Boarding CR Records");
			}
			else
			{
				response.setHeader("Content-Disposition",
						"attachment; filename=\"TDMOnBoarding_DashBoard.xls\"");
				workbook.setSheetName(0, "TDM On Boarding Records");
			}
		}

	}

	public void setExcelHeader(HSSFSheet excelSheet, HSSFCellStyle style)
	{
		HSSFRow excelHeader = excelSheet.createRow(2);

		excelSheet.setDisplayGridlines(false);

		excelHeader = excelSheet.createRow(2);

		excelHeader = excelSheet.createRow(4);
		int count = 0;
		excelHeader.createCell(count++).setCellValue("Request Id");
		excelHeader.createCell(count++).setCellValue("Description");
		excelHeader.createCell(count++).setCellValue("Created By");
		excelHeader.createCell(count++).setCellValue("Project Name");
		excelHeader.createCell(count++).setCellValue("Project Phase");
		excelHeader.createCell(count++).setCellValue("Requested Time");
		excelHeader.createCell(count++).setCellValue("Status");
		if (isCR)
		{
			excelHeader.createCell(count++).setCellValue("Change Request Description");
		}

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
			logger.error(TDMConstants.TDMP_VIEW_ERROR_1, e);
		}

		return hssfColor;
	}

	public void setExcelRows(HSSFSheet excelSheet, List<TdmDataMaskingDTO> tdgDataMaskingDTOs,
			HSSFCellStyle style)
	{
		int record = 5;

		for (TdmDataMaskingDTO tdgDataMaskingDTO : tdgDataMaskingDTOs)
		{
			int count = 0;
			HSSFRow excelRow = excelSheet.createRow(record++);
			excelRow.createCell(count++).setCellValue(tdgDataMaskingDTO.getId());
			if (tdgDataMaskingDTO.getId().contains("CR"))
			{
				isCR = true;
			}
			else
			{
				isCR = false;
			}

			if (tdgDataMaskingDTO.getId().contains("MR"))
			{
				isMasking = true;
			}
			else
			{
				isMasking = false;
			}

			excelRow.createCell(count++).setCellValue(tdgDataMaskingDTO.getDesc());
			excelRow.createCell(count++).setCellValue(tdgDataMaskingDTO.getUserName());
			excelRow.createCell(count++).setCellValue(tdgDataMaskingDTO.getProjName());
			excelRow.createCell(count++).setCellValue(tdgDataMaskingDTO.getProjPhase());
			excelRow.createCell(count++).setCellValue(tdgDataMaskingDTO.getReqTime());
			excelRow.createCell(count++).setCellValue(tdgDataMaskingDTO.getStatus());
			if (isCR)
			{
				excelRow.createCell(count++).setCellValue(tdgDataMaskingDTO.getChngReqCmmt());
			}

			for (int i = 0; i < count; i++)
			{
				excelRow.getCell(i).setCellStyle(style);
				excelSheet.autoSizeColumn(i);
			}
		}
	}
}
