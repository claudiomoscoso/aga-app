package cl.builderSoft.utilpdf.components;


import cl.builderSoft.utilpdf.PdfComponent;

import com.lowagie.text.Cell;
import com.lowagie.text.Element;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfWriter;

public class BoxComponent extends PdfComponent {
	int width = 100;
	PdfComponent values[];
	int aligment = Element.ALIGN_CENTER;
	
	public int getAligment() {
		return aligment;
	}

	public void setAligment(int aligment) {
		this.aligment = aligment;
	}

	public BoxComponent( PdfComponent values[]) {
		setValues(values);
	}

	public void setValues(PdfComponent[] values) {
		this.values = values;
	}

	
	public Element render(PdfWriter writer) {
		try {
			
			com.lowagie.text.Table table = new com.lowagie.text.Table(1);
			table.setBorder(Rectangle.BOX);
			table.setPadding(8);
			table.setWidth(width);
			table.setAlignment(aligment);

			for(int i=0; i< values.length; i++){
				int border = Rectangle.LEFT | Rectangle.RIGHT;
				if (i==0) border |= Rectangle.TOP;
				if (i==values.length-1) border |= Rectangle.BOTTOM;				
				Cell cell;
				PdfComponent p = values[i];
				if (p==null){
					p = new Paragraph("");
				}
				cell = new Cell(p.render());
				table.addCell(cell);
			}
			return table;
		} catch (Exception e) {
			return new Paragraph(e.getMessage()).render();
		}
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

}