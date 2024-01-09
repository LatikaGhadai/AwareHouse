package com.nt.view;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.nt.model.PurchaseOrder;



public class PurchaseOrderPdfView extends AbstractPdfView{

	@Override
	protected void buildPdfDocument(Map<String, Object> model, com.lowagie.text.Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//download document
		response.addHeader("Content-Disposition", "attachment;filename=shipments.pdf");
		//create paragraph
		Font font=new Font(Font.HELVETICA,25,Font.BOLDITALIC,Color.BLUE);
		Paragraph p=new Paragraph("WELCOME TO APPLICATION",font);
		p.setAlignment(Element.ALIGN_CENTER);
		//add element to document
		document.add(p);	
		
		//reading data
		@SuppressWarnings("unchecked")
		List<PurchaseOrder> list=(List<PurchaseOrder>)model.get("list");
		
		PdfPTable table=new PdfPTable(6);//no of column
		table.setSpacingBefore(4.0f);
		table.setTotalWidth(new float[] {1.0f,1.0f,1.0f,1.0f,1.0f,1.5f});
		table.addCell("ID");
		table.addCell("CODE");
		table.addCell("REFERENCE NO");
		table.addCell("QUALITY CHECK");
		table.addCell("DEFAULT STATUS");
		table.addCell("DESCRIPTION");
		
		for(PurchaseOrder po:list){
			table.addCell(po.getId().toString());
			table.addCell(po.getOrderCode());
			table.addCell(po.getReferenceNo());
			table.addCell(po.getQualityCheck());
			table.addCell(po.getDefaultStatus());
			table.addCell(po.getDescription());	
		}
	}
}
