package com.nt.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.nt.model.Grn;

public class GrnExcelView extends AbstractXlsxView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//download file name
		response.addHeader("Content-Disposition", "attachment:filename=Grn.xlsx");
		//reading data from controller
		@SuppressWarnings("unchecked")
		List<Grn> list=(List<Grn>)model.get("grn");
		//create new sheet
		Sheet sheet=workbook.createSheet("GRN DATA");	
		setHead(sheet);
		setBody(sheet,list);
	}
	
	public void setHead(Sheet sheet) {
		Row row=sheet.createRow(0);
		
		//create cell
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("CODE");
		row.createCell(2).setCellValue("TYPE");
		row.createCell(3).setCellValue("description");	
	}
	
	private void setBody(Sheet sheet,List<Grn> list) {
		int rownum=1;
		for(Grn grn:list) {
			Row row=sheet.createRow(rownum++);
			row.createCell(0).setCellValue(grn.getId());
			row.createCell(1).setCellValue(grn.getGrnCode());
			row.createCell(2).setCellValue(grn.getGrnType());
			row.createCell(3).setCellValue(grn.getDescription());
		}	
	}
}