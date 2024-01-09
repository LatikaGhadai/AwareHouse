package com.nt.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.nt.model.Part;

public class PartExcelView extends AbstractXlsxView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//download+fileName
		response.addHeader("Content-Disposition", "attachment;filename=Part.xlsx");
		//reading data from controller
		@SuppressWarnings("unchecked")
		List<Part> list=(List<Part>)model.get("part");
		
		//create new sheet
		Sheet sheet=workbook.createSheet("PART");
		setHead(sheet);
		setBody(sheet, list);
		
	}
	
	private void setHead(Sheet sheet) {
		Row row=sheet.createRow(0);
		//create cell
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("partCode");
		/*
		 * row.createCell(2).setCellValue("partWidth");
		 * row.createCell(3).setCellValue("partLength");
		 * row.createCell(4).setCellValue("partHeight");
		 * row.createCell(5).setCellValue("baseCost");
		 */
		row.createCell(6).setCellValue("baseCurrency");
		row.createCell(7).setCellValue("Description");
	}
	
	private void setBody(Sheet sheet,List<Part> list) {
		int rownum=1;
		for(Part part:list) {
			Row row=sheet.createRow(rownum++);
			row.createCell(0).setCellValue(part.getId());
			row.createCell(1).setCellValue(part.getPartCode());
			/*
			   row.createCell(2).setCellValue(part.getPartWidth().toString());
			   row.createCell(3).setCellValue(part.getPartLength().toString());
			   row.createCell(4).setCellValue(part.getPartHeight().toString());
			   row.createCell(5).setCellValue(part.getBaseCost().toString());
			 */
			row.createCell(6).setCellValue(part.getBaseCurrency());
			row.createCell(7).setCellValue(part.getDescription());
		}
	}

}
