package cl.builderSoft.utilpdf.components;

import cl.builderSoft.utilpdf.PdfComponent;

import com.lowagie.text.Element;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class VerticalTablePanel extends PdfComponent {

	PdfComponent components[];
	int width = 100;
	int alignments[];
	
	public VerticalTablePanel() {}
	
	public VerticalTablePanel(PdfComponent components[]) {
		setComponents(components);
	}
	
	public void setComponents(PdfComponent components[]) {
		this.components = components;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setAlignments(int alignments[]) {
		this.alignments = alignments;
	}
	
	public Element render(PdfWriter writer) {
		try {
			PdfPTable table = new PdfPTable(1);
			table.setSpacingBefore(8);
			PdfPCell c = table.getDefaultCell();
			c.setBorder(Rectangle.NO_BORDER);
			c.setLeading(0,0);
			for(int i=0; i<components.length; i++){
				Object o = components[i].render(writer);
				PdfPCell cell;
				if (o instanceof PdfPTable) {
					cell = new PdfPCell((PdfPTable)o);
				} else {
					cell = new PdfPCell(new com.lowagie.text.Paragraph(""));
				}
				cell.setPadding(5);
				cell.setBorder(Rectangle.NO_BORDER);
				if (alignments!=null && i<alignments.length) {
					cell.setVerticalAlignment(getCellAlignment(alignments[i]));
				}
				table.addCell(cell);
			}
			
			return table;
		} catch (Exception e) {
			return new Paragraph(e.getMessage()).render();
		}
	}

}
