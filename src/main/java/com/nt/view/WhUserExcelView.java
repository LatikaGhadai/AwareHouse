package com.nt.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.nt.model.WhUserType;

public class WhUserExcelView extends AbstractXlsxView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		//download file name
		response.addHeader("Content-Disposition", "attachment:filename=WhUserType.xlsx");
		//reading data from controller
		@SuppressWarnings("unchecked")
		List<WhUserType> list=(List<WhUserType>)model.get("whUser");
		//create new sheet
		Sheet sheet=workbook.createSheet("WH USER");	
		setHead(sheet);
		setBody(sheet,list);
	}
	
	public void setHead(Sheet sheet) {
		Row row=sheet.createRow(0);
		
		//create cell
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("TYPE");
		row.createCell(2).setCellValue("CODE");
		row.createCell(3).setCellValue("USER FOR");
		row.createCell(4).setCellValue("EMAIL");
		row.createCell(5).setCellValue("CONTACT");
		row.createCell(6).setCellValue("ID TYPE");
		row.createCell(7).setCellValue("OTHER");
		row.createCell(8).setCellValue("ID NUMBER");
	}
	
	private void setBody(Sheet sheet,List<WhUserType> list) {
		int rownum=1;
		for(WhUserType whUsr:list) {
			Row row=sheet.createRow(rownum++);
			row.createCell(0).setCellValue(whUsr.getId());
			row.createCell(1).setCellValue(whUsr.getUserType());
			row.createCell(2).setCellValue(whUsr.getUserCode());
			row.createCell(3).setCellValue(whUsr.getUserFor());
			row.createCell(4).setCellValue(whUsr.getUserEmail());
			row.createCell(5).setCellValue(whUsr.getUserContact());
			row.createCell(6).setCellValue(whUsr.getUserIdType());
			row.createCell(7).setCellValue(whUsr.getIfOther());
			row.createCell(8).setCellValue(whUsr.getIdNumber());
		}
	}
	

}
