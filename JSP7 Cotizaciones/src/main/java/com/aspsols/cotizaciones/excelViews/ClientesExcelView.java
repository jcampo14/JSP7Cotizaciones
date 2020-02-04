package com.aspsols.cotizaciones.excelViews;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.aspsols.cotizaciones.model.ViewClientes;

public class ClientesExcelView extends AbstractXlsView {

	@SuppressWarnings("deprecation")
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setHeader("Content-Disposition", "attachment; filename=\"clientes-data-base.xls\"");

		@SuppressWarnings("unchecked")
		List<ViewClientes> clientes = (List<ViewClientes>) model.get("clientes");

		// create excel xls sheet
		Sheet sheet = workbook.createSheet("Clientes Detail");
		sheet.setDefaultColumnWidth(30);

		// create style for header cells
		CellStyle style = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setFontName("Arial");
		style.setFillForegroundColor(HSSFColor.BLUE.index);
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		font.setBold(true);
		font.setColor(HSSFColor.WHITE.index);
		style.setFont(font);

		// create header row
		Row header = sheet.createRow(0);
		header.createCell(0).setCellValue("Nit Cliente");
		header.getCell(0).setCellStyle(style);
		header.createCell(1).setCellValue("Nombre Cliente");
		header.getCell(1).setCellStyle(style);
		header.createCell(2).setCellValue("Direccion");
		header.getCell(2).setCellStyle(style);
		header.createCell(3).setCellValue("Telefono");
		header.getCell(3).setCellStyle(style);
		header.createCell(4).setCellValue("Email");
		header.getCell(4).setCellStyle(style);
		header.createCell(5).setCellValue("Pais");
		header.getCell(5).setCellStyle(style);
		header.createCell(6).setCellValue("Departamento");
		header.getCell(6).setCellStyle(style);
		header.createCell(7).setCellValue("Ciudad");
		header.getCell(7).setCellStyle(style);
		header.createCell(8).setCellValue("Contacto Persona");
		header.getCell(8).setCellStyle(style);
		header.createCell(9).setCellValue("Contacto Cargo");
		header.getCell(9).setCellStyle(style);
		int rowCount = 1;

		for (ViewClientes cliente : clientes) {
			Row row = sheet.createRow(rowCount++);
			row.createCell(0).setCellValue(cliente.getNitCliente());
			row.createCell(1).setCellValue(cliente.getNombreCliente());
			row.createCell(3).setCellValue(cliente.getDireccion());
			row.createCell(2).setCellValue(cliente.getTelefono());
			row.createCell(4).setCellValue(cliente.getEmail());
			row.createCell(5).setCellValue(cliente.getPais());
			row.createCell(6).setCellValue(cliente.getDepartamento());
			row.createCell(7).setCellValue(cliente.getCiudad());
			row.createCell(8).setCellValue(cliente.getContactoPersona());
			row.createCell(9).setCellValue(cliente.getContactoCargo());
		}
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
	}

}
