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

import com.tesda.model.DTO.DependentDetailsDTO;
import com.tesda.model.DTO.TDMNonStandardSearchDTO;
import com.tesda.model.DTO.TdmNonStandardSearchResultListDTO;

/**
 * Class which will prepare the data to export in excel format. Exports the Find
 * test data result grid records to excel.
 */

public class TDMNonstandardSearchResultExcelView extends AbstractExcelView
{
	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		HSSFSheet excelSheet = workbook.createSheet("Test Data Search Results");
		response.setHeader("Content-Disposition",
				"attachment; filename=\"TestData_Search_Results.xls\"");
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

		TDMNonStandardSearchDTO tdmNonStandSearchDTO = (TDMNonStandardSearchDTO) model
				.get("tdmNonStandSearchDTOList");
		setExcelRows(workbook, excelSheet, tdmNonStandSearchDTO, style2);
	}

	public void setExcelHeader(HSSFSheet excelSheet, HSSFCellStyle style)
	{
		int cell = 0;
		HSSFRow excelHeader = excelSheet.createRow(2);

		excelSheet.setDisplayGridlines(false);
		excelHeader = excelSheet.createRow(2);
		excelHeader = excelSheet.createRow(4);
		excelHeader.createCell(cell++).setCellValue("Subscriber ID");
		excelHeader.createCell(cell++).setCellValue("Member Type");
		excelHeader.createCell(cell++).setCellValue("First Name");
		excelHeader.createCell(cell++).setCellValue("Last Name");
		excelSheet.setColumnWidth(cell, 50);
		excelHeader.createCell(cell++).setCellValue("Gender");
		excelHeader.createCell(cell++).setCellValue("Date of Birth");
		excelHeader.createCell(cell++).setCellValue("Corp ID");
		excelHeader.createCell(cell++).setCellValue("Home Zip Code");
		excelHeader.createCell(cell++).setCellValue("Group Number");
		excelSheet.setColumnWidth(cell, 120);
		excelHeader.createCell(cell++).setCellValue("Account Name");
		excelHeader.createCell(cell++).setCellValue("Product Type");
		excelHeader.createCell(cell++).setCellValue("PCP/MG");
		excelHeader.createCell(cell++).setCellValue("Funding Indicator");
		excelHeader.createCell(cell++).setCellValue("Blended Category");
		excelHeader.createCell(cell++).setCellValue("Exchange Type");
		excelHeader.createCell(cell++).setCellValue("Member Effective Date");
		excelHeader.createCell(cell++).setCellValue("Member End Date");
		excelHeader.createCell(cell++).setCellValue(
				"Membership Coverage Group Section Effective Date");
		excelHeader.createCell(cell++).setCellValue("Membership Coverage Group Section End Date");
		excelHeader.createCell(cell++).setCellValue("Original Effective Date");
		excelHeader.createCell(cell++).setCellValue("Coverage");
		excelHeader.createCell(cell++).setCellValue("Existing Claim(s)");
		excelHeader.createCell(cell++).setCellValue("Relationship");
		excelHeader.createCell(cell++).setCellValue("Relationship Name");
		excelHeader.createCell(cell++).setCellValue("Relationship Code");
		excelHeader.createCell(cell++).setCellValue("Dependent Status");
		excelHeader.createCell(cell++).setCellValue("Dependent Effective Date");
		excelHeader.createCell(cell++).setCellValue("Dependent End Date");

		for (int i = 0; i < cell; i++)
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

	public void setExcelRows(HSSFWorkbook workbook, HSSFSheet excelSheet,
			TDMNonStandardSearchDTO tdmNonStandSearchDTO, HSSFCellStyle style)
	{
		int record = 5;

		List<TdmNonStandardSearchResultListDTO> tdmNonStandResultList = tdmNonStandSearchDTO
				.getTdmNonStandardSrchResultListDTOs();
		Map<String, List<DependentDetailsDTO>> depenDetails = tdmNonStandSearchDTO
				.getTdmDependentDetails();

		if (null != tdmNonStandResultList)
		{
			for (TdmNonStandardSearchResultListDTO tdgDataMaskingDTO : tdmNonStandResultList)
			{
				int cell = 0;
				HSSFRow excelRow = excelSheet.createRow(record++);
				excelRow.createCell(cell++).setCellValue(tdgDataMaskingDTO.getSubscId());
				excelRow.createCell(cell++).setCellValue(tdgDataMaskingDTO.getMemType());
				excelRow.createCell(cell++).setCellValue(tdgDataMaskingDTO.getFirstName());
				excelRow.createCell(cell++).setCellValue(tdgDataMaskingDTO.getLastName());
				excelRow.createCell(cell++).setCellValue(tdgDataMaskingDTO.getGender());
				excelRow.createCell(cell++).setCellValue(tdgDataMaskingDTO.getDob());
				excelRow.createCell(cell++).setCellValue(tdgDataMaskingDTO.getProvState());
				excelRow.createCell(cell++).setCellValue(tdgDataMaskingDTO.getHomeZipCode());
				excelRow.createCell(cell++).setCellValue(tdgDataMaskingDTO.getGroupNum());
				excelRow.createCell(cell++).setCellValue(tdgDataMaskingDTO.getAcName());
				excelRow.createCell(cell++).setCellValue(tdgDataMaskingDTO.getProductType());
				excelRow.createCell(cell++).setCellValue(tdgDataMaskingDTO.getPcpMG());
				excelRow.createCell(cell++).setCellValue(tdgDataMaskingDTO.getFundingInd());
				excelRow.createCell(cell++).setCellValue(tdgDataMaskingDTO.getBlendGroup());
				excelRow.createCell(cell++).setCellValue(tdgDataMaskingDTO.getExchangeType());
				excelRow.createCell(cell++).setCellValue(tdgDataMaskingDTO.getMemEffDateGov());
				excelRow.createCell(cell++).setCellValue(tdgDataMaskingDTO.getMemEndDateGroup());
				excelRow.createCell(cell++).setCellValue(tdgDataMaskingDTO.getMcgSecEffDate());
				excelRow.createCell(cell++).setCellValue(tdgDataMaskingDTO.getMcgSecEndDate());
				excelRow.createCell(cell++).setCellValue(tdgDataMaskingDTO.getOriginalEffDate());
				excelRow.createCell(cell++).setCellValue(tdgDataMaskingDTO.getCoverageCode());
				excelRow.createCell(cell++).setCellValue(tdgDataMaskingDTO.getExtClaim());
				for (int i = 0; i < 6; i++)
				{
					excelRow.createCell(cell++).setCellValue(" ");
				}

				HSSFCellStyle parentStyle = workbook.createCellStyle();
				parentStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				parentStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
				parentStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
				parentStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
				parentStyle.setWrapText(true);
				parentStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
				parentStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

				if ((depenDetails != null && depenDetails.size() > 0)
						&& depenDetails.containsKey(tdgDataMaskingDTO.getSubscId()))
				{
					List<DependentDetailsDTO> depenList = depenDetails.get(tdgDataMaskingDTO
							.getSubscId());
					for (DependentDetailsDTO depenDTO : depenList)
					{
						int depCell = 0;
						if (tdgDataMaskingDTO.getSubscId().equalsIgnoreCase(depenDTO.getSubId()))
						{
							HSSFRow depenRow = excelSheet.createRow(record++);
							depenRow.createCell(depCell++).setCellValue(depenDTO.getSubId());
							depenRow.createCell(depCell++).setCellValue(depenDTO.getMemCategory());
							depenRow.createCell(depCell++).setCellValue(depenDTO.getFirstName());
							depenRow.createCell(depCell++).setCellValue(depenDTO.getLastName());
							depenRow.createCell(depCell++).setCellValue(depenDTO.getGender());
							depenRow.createCell(depCell++).setCellValue(depenDTO.getDob());
							depenRow.createCell(depCell++).setCellValue(depenDTO.getState());
							depenRow.createCell(depCell++).setCellValue(depenDTO.getZipCode());
							for (int i = depCell; i < 22; i++)
							{
								depenRow.createCell(depCell++).setCellValue("  ");
							}

							depenRow.createCell(depCell++).setCellValue(depenDTO.getRelationShip());
							depenRow.createCell(depCell++).setCellValue(
									depenDTO.getRelationShipName());
							depenRow.createCell(depCell++).setCellValue(
									depenDTO.getRelationShipCode());
							depenRow.createCell(depCell++).setCellValue(depenDTO.getDepStatus());
							depenRow.createCell(depCell++).setCellValue(depenDTO.getDeptEffDate());
							depenRow.createCell(depCell++).setCellValue(depenDTO.getDeptEndDate());

							for (int i = 0; i < cell; i++)
							{
								excelRow.getCell(i).setCellStyle(parentStyle);
								depenRow.getCell(i).setCellStyle(style);
								excelSheet.autoSizeColumn(i);
							}
						}
					}
				}
				else
				{
					HSSFCellStyle rowStyle;
					if (depenDetails != null && depenDetails.size() > 0)
					{
						rowStyle = parentStyle;
					}
					else
					{
						rowStyle = style;
					}

					for (int i = 0; i < cell; i++)
					{
						excelRow.getCell(i).setCellStyle(rowStyle);
						excelSheet.autoSizeColumn(i);
					}
				}
			}
		}
	}
}
