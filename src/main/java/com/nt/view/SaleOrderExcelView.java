package com.nt.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.nt.model.SaleOrder;

public class SaleOrderExcelView extends AbstractXlsxView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//download file name
		response.addHeader("Content-Disposition", "attachment;filename=SaleOrder.xlsx");
		//reading data from controller
		@SuppressWarnings("unchecked")
		List<SaleOrder> list=(List<SaleOrder>)model.get("saleOrder");
		//create new sheet
		Sheet sheet=workbook.createSheet("SaleOrder");
		setHead(sheet);
	    setBody(sheet,list);	
	}
	
	public void setHead(Sheet sheet) {
		Row row=sheet.createRow(0);
		//create cell
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("CODE");
		row.createCell(2).setCellValue("REFERENCE NO");
		row.createCell(3).setCellValue("STOCK MODE");
		row.createCell(4).setCellValue("STOCK SOURCE");
		row.createCell(5).setCellValue("DESCRIPTION");
	}
	
	public void setBody(Sheet sheet,List<SaleOrder> list) {
		
		int rownum=1;
		for(SaleOrder saleOrder:list) {
			Row row=sheet.createRow(rownum++);
			row.createCell(0).setCellValue(saleOrder.getId());
			row.createCell(1).setCellValue(saleOrder.getOrderCode());
			row.createCell(2).setCellValue(saleOrder.getReferenceNo());
			row.createCell(3).setCellValue(saleOrder.getStockMode());
			row.createCell(4).setCellValue(saleOrder.getStockSource());
			row.createCell(5).setCellValue(saleOrder.getDescription());
		}
		
	}
  
	
	

}
