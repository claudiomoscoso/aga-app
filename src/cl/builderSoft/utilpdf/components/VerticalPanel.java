package cl.builderSoft.utilpdf.components;

import cl.builderSoft.utilpdf.PdfComponent;

import com.lowagie.text.Cell;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.pdf.PdfWriter;

public class VerticalPanel extends PdfComponent {

	PdfComponent components[];
	int width = 100;
	int alignments[];
	int widths[];
	
	public VerticalPanel() {}
	
	public VerticalPanel(PdfComponent components[]) {
		setComponents(components);
	}
	
	public void setComponents(PdfComponent components[]) {
		this.components = components;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setWidths(int widths[]) {
		this.widths = widths;
	}
	
	public void setAlignments(int alignments[]) {
		this.alignments = alignments;
	}
	
	public Element render(PdfWriter writer) {
		try {
			com.lowagie.text.Table table = new com.lowagie.text.Table(1, components.length);
			table.setBorderWidth(0);
			table.setPadding(2);
			table.setWidth(width);
			if (widths!=null) table.setWidths(widths);
			for(int i=0; i<components.length; i++){
				Cell cell = new Cell(components[i].render(writer));
				cell.setBorderWidth(0);
				if (alignments!=null && i<alignments.length) {
					cell.setVerticalAlignment(getCellAlignment(alignments[i]));
				}
				table.addCell(cell);
			}
			table.setConvert2pdfptable(true);
			
			return table;
		} catch (DocumentException de) {
			return new Paragraph(de.getMessage()).render();
		}
	}

}
