package com.nt.view;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.nt.model.SaleOrder;

public class SaleOrderPdfView extends AbstractPdfView{

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		//create resource name
		response.addHeader("Content-Disposition", "attachment;filename=saleOrder.pdf");
		
		//create paragraph
		Font font=new Font(Font.HELVETICA,25,Font.BOLDITALIC,Color.BLUE);
		Paragraph p=new Paragraph("WELCOME TO SALE ORDER",font);
		p.setAlignment(Element.ALIGN_CENTER);
		//add document
		document.add(p);
		
		//reading Data
		@SuppressWarnings("unchecked")
		List<SaleOrder> list=(List<SaleOrder>)model.get("list");
		PdfPTable table=new PdfPTable(6);
		table.setSpacingBefore(4.0f);
		table.setTotalWidth(new float[] {1.0f,1.0f,1.0f,1.0f,1.0f,1.0f});
		table.addCell("ID");
		table.addCell("CODE");
		table.addCell("REFERENCE NO");
		table.addCell("STOCKMODE");
		table.addCell("STOCKSOURCE");
		table.addCell("DESCRIPTION");
		
		for(SaleOrder so:list){
			table.addCell(so.getId().toString());
			table.addCell(so.getOrderCode());
			table.addCell(so.getReferenceNo());
			table.addCell(so.getStockMode());
			table.addCell(so.getStockSource());
			table.addCell(so.getDescription());	
		}
		document.add(table);
		document.add(new Phrase());
		
		
	}

}
