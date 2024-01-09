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
import com.nt.model.Grn;

public class GrnPdfView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.addHeader("Content-Disposition", "attachment;filename=grnView.pdf");
		
		//create paragraph
		Font font=new Font(Font.HELVETICA,25,Font.BOLDITALIC,Color.BLUE);
		Paragraph p=new Paragraph("WELCOME TO GRN APPLICATION",font);
		p.setAlignment(Element.ALIGN_CENTER);
		//add document
		document.add(p);
		
		//reading Data
		@SuppressWarnings("unchecked")
		List<Grn> list=(List<Grn>)model.get("list");
		
		PdfPTable table=new PdfPTable(4);
		table.setSpacingBefore(4.0f);
		table.setTotalWidth(new float[] {1.0f,1.0f,1.0f,1.0f});
		table.addCell("ID");
		table.addCell("CODE");
		table.addCell("TYPE");
		table.addCell("DESCRIPTION");
		
		for(Grn grn:list){
			table.addCell(grn.getId().toString());
			table.addCell(grn.getGrnCode());
			table.addCell(grn.getGrnType());
			table.addCell(grn.getDescription());	
		}
		document.add(table);
		document.add(new Phrase());
	}
}
