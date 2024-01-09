package com.nt.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.nt.model.ShipmentType;

public class ShipmentTypeExcelView extends AbstractXlsxView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//download file name
		response.addHeader("Content-Disposition", "attachment:filename=ShipmentType.xlsx");
		//reading data from controller
		@SuppressWarnings("unchecked")
		List<ShipmentType> list=(List<ShipmentType>)model.get("st");
		//create new sheet
		Sheet sheet=workbook.createSheet("SHIPMENT DATA");	
		setHead(sheet);
		setBody(sheet,list);
	}
	
	public void setHead(Sheet sheet) {
		Row row=sheet.createRow(0);
		
		//create cell
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("MODE");
		row.createCell(2).setCellValue("CODE");
		row.createCell(3).setCellValue("enableShipment");
		row.createCell(4).setCellValue("shipmentGrade");
		row.createCell(5).setCellValue("description");	
	}
	
	private void setBody(Sheet sheet,List<ShipmentType> list) {
		int rownum=1;
		for(ShipmentType st:list) {
			Row row=sheet.createRow(rownum++);
			row.createCell(0).setCellValue(st.getId());
			row.createCell(1).setCellValue(st.getShipmentMode());
			row.createCell(2).setCellValue(st.getShipmentCode());
			row.createCell(3).setCellValue(st.getEnableShipment());
			row.createCell(4).setCellValue(st.getShipmentGrade());
			row.createCell(5).setCellValue(st.getDescription());
		}	
	}
}