package cl.builderSoft.utilpdf.components;


import cl.builderSoft.utilpdf.PdfComponent;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class HorizontalTablePanel extends PdfComponent {

	PdfComponent components[];
	int width = 100;
	int alignments[];
	int widths[];
	
	public HorizontalTablePanel() {}
	
	public HorizontalTablePanel(PdfComponent components[]) {
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
			PdfPTable table = new PdfPTable(components.length);
			table.setSpacingBefore(8);
			PdfPCell c = table.getDefaultCell();
			c.setBorder(Rectangle.NO_BORDER);
			c.setLeading(0, 0);
			table.setWidthPercentage(width);

			if (widths != null) {
				table.setWidths(widths);
			}
			for (int i = 0; i < components.length; i++) {
				Object o = components[i].render(writer);
				PdfPCell cell;
				if (o instanceof PdfPTable) {
					cell = new PdfPCell((PdfPTable) o);
				} else if (o instanceof com.lowagie.text.Paragraph) {
					cell = new PdfPCell((com.lowagie.text.Paragraph) o);
				} else if (o instanceof com.lowagie.text.Phrase) {
					cell = new PdfPCell((com.lowagie.text.Phrase) o);
				} else {
					cell = new PdfPCell(new com.lowagie.text.Paragraph(""));
				}
				cell.setPadding(5);
				cell.setBorder(Rectangle.NO_BORDER);
				if (alignments != null && i < alignments.length) {
					cell.setHorizontalAlignment(getCellAlignment(alignments[i]));
				}
				table.addCell(cell);
			}
			return table;

		} catch (DocumentException de) {
			return new Paragraph(de.getMessage()).render();
		}
	}
}
