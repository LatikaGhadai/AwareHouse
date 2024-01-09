package com.nt.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.nt.model.PurchaseOrder;

public class PurchaseOrderExcelview extends AbstractXlsxView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		       //download file name
				response.addHeader("Content-Disposition", "attachment;filename=Purches.xlsx");
				//reading data from controller
				@SuppressWarnings("unchecked")
				List<PurchaseOrder> list=(List<PurchaseOrder>)model.get("purchaseOrder");
				//create new sheet
				Sheet sheet=workbook.createSheet("Purches");	
				setHead(sheet);
				setBody(sheet,list);
			}
			
			public void setHead(Sheet sheet) {
				Row row=sheet.createRow(0);
				
				//create cell
				row.createCell(0).setCellValue("ID");
				row.createCell(1).setCellValue("CODE");
				row.createCell(2).setCellValue("REFERENCE NO");
				row.createCell(3).setCellValue("QUALITY CHECK");
				row.createCell(4).setCellValue("DESCRIPTION");
			}
			
			private void setBody(Sheet sheet,List<PurchaseOrder> list) {
				int rownum=1;
				for(PurchaseOrder purchaseOrder:list) {
					Row row=sheet.createRow(rownum++);
					row.createCell(0).setCellValue(purchaseOrder.getId());
					row.createCell(1).setCellValue(purchaseOrder.getOrderCode());
					row.createCell(2).setCellValue(purchaseOrder.getReferenceNo());
					row.createCell(3).setCellValue(purchaseOrder.getQualityCheck());
					row.createCell(4).setCellValue(purchaseOrder.getDescription());
				}
			}
		
}
