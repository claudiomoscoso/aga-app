package cl.builderSoft.utilpdf.components;


import cl.builderSoft.utilpdf.PdfComponent;
import cl.builderSoft.utilpdf.Resources;

import com.lowagie.text.Element;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

public class Signature extends PdfComponent {

	String lines[];
	int width = 80;
	
	public Signature() {
		this(null);
	}
	
	public Signature(String lines[]) {
		setLines(lines);
	}
	
	public void setLines(String lines[]) {
		this.lines = lines;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public Element render() {
		PdfPTable table;
		try {
			table = new PdfPTable(1);
			
			table.setSpacingBefore(0);
			PdfPCell c = table.getDefaultCell();
			c.setBorder(Rectangle.BOX);
			c.setPadding(0);
			c.setLeading(0,0);
			table.setWidthPercentage(width);
			
			
			c = new PdfPCell(new com.lowagie.text.Paragraph("Sistema de calidad certificado bajo norma ISO 9000"));
			c.setBorder(Rectangle.NO_BORDER);
			table.addCell(c);
			
			for(int i=0; lines!=null && i<lines.length; i++) {
				com.lowagie.text.Paragraph p = new com.lowagie.text.Paragraph(lines[i], Resources.fontSmall2);
				PdfPCell cell = new PdfPCell(p);
				cell.setBorder(Rectangle.NO_BORDER);
				if (i==0) cell.setBorder(Rectangle.TOP);
				cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
				table.addCell(cell);
			}
			
		} catch (Exception e) {
			return new Paragraph(e.getMessage()).render();
		}
		
		return table;
		
	}

}
