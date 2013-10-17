package cl.builderSoft.utilpdf.components;


import cl.builderSoft.utilpdf.PdfComponent;
import cl.builderSoft.utilpdf.Resources;

import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfWriter;

public class Paragraph extends PdfComponent {
	String text = "";
	int alignment = Element.ALIGN_LEFT;
	Font font;
	
	public Paragraph(String text) {
		this(text, Resources.fontNormal);
	}
	
	public Paragraph(String text, Font font) {
		this.text = text;
		this.font = font;
	}
	
	public void setFont(Font font) {
		this.font = font;
	}
	
	public void setAlignment(int alignment) {
		this.alignment = alignment;
	}
	
	public Element render(PdfWriter writer) {
		return render();
	}
	
	public Element render() {
		com.lowagie.text.Paragraph p = new com.lowagie.text.Paragraph(text, font);
		p.setAlignment(alignment);
		return p;
	}
}
