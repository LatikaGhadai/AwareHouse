package com.nt.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.nt.model.OrderMethod;

public class OrderMothodExcelView extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//download+filename
		response.addHeader("Content-Disposition", "attachment;filename=OrderMethod.xlsx");
		//reading data  from controller
		@SuppressWarnings("unchecked")
		List<OrderMethod> list=(List<OrderMethod>)model.get("obj");
		//create new sheet
		Sheet sheet=workbook.createSheet("ORDER METHOD");
		setHead(sheet);
		setBody(sheet,list);
	}
	
	private void setHead(Sheet sheet) {
		Row row=sheet.createRow(0);
		//create cell
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("Order Method");
		row.createCell(2).setCellValue("Order Code");
		row.createCell(3).setCellValue("Order Type");
		row.createCell(4).setCellValue("Order Accept");
		row.createCell(5).setCellValue("Description");
				
	}
	private void setBody(Sheet sheet,List<OrderMethod> list) {
		int rownum=1;
		for(OrderMethod om:list) {
			Row row=sheet.createRow(rownum++);
			row.createCell(0).setCellValue(om.getId());
			row.createCell(1).setCellValue(om.getOrderMode());
			row.createCell(2).setCellValue(om.getOrderCode());
			row.createCell(3).setCellValue(om.getOrderType());
			row.createCell(4).setCellValue(om.getOrderAccept().toString());
			row.createCell(5).setCellValue(om.getDescription());
		}
		
	}
	
	
	
	

}
