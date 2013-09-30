package cl.builderSoft.utilpdf.components;


import cl.builderSoft.utilpdf.PdfComponent;
import cl.builderSoft.utilpdf.Resources;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Cell;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

public class FieldsTable extends PdfComponent {
	int columns = 2;
	String labels[];
	Object values[];
	String title;
	int colspan[];
	int width = 100;
	int colwidth[];
	
	public FieldsTable(String labels[]) {
		this(null, labels);
	}
	
	public FieldsTable(String title, String labels[]) {
		this.labels = labels;
		this.title = title;
	}
	
	public void setColspan(int colspan[]) {
		this.colspan = colspan;
	}
	
	public void setValues(Object values[]) {
		this.values = values;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setColWidth(int colwidth[]) {
		this.colwidth = colwidth;
	}

	public Element render() {
		try {
			PdfPTable table = new PdfPTable(columns);
			table.setSpacingBefore(8);
			PdfPCell c = table.getDefaultCell();
			c.setBorder(Rectangle.BOX);
			c.setPadding(0);
			c.setLeading(0,0);
			table.setWidthPercentage(width);
			if (colwidth!=null) table.setWidths(colwidth);

			if (title!=null) {
				table.setHeaderRows(2);
				com.lowagie.text.Paragraph p = new com.lowagie.text.Paragraph(title, Resources.fontBold);
				PdfPCell cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
				cell.setColspan(columns);
				cell.setPaddingTop(1);
				table.addCell(cell);
			} else {
				table.setHeaderRows(1);
			}
			int column = 0, row = 0, start = 0;
			for(int i=0; i<labels.length; ) {
				com.lowagie.text.Paragraph p;
				if (row%2 == 0) {
					p = new com.lowagie.text.Paragraph(labels[i], Resources.fontBold);
					System.out.println("lbl:" + labels[i]);
				} else {
					Object o = values[i];
					p = new com.lowagie.text.Paragraph(Resources.getObjectDespliegue(o), Resources.fontNormal);
					System.out.println("val:" + o);
				}
				PdfPCell cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
				cell.setPaddingTop(1);
				int span= colspan!=null?colspan[i]:1;
				System.out.println("addcell");
				i++;
				column++;
				if (i==labels.length && column<columns) {
					System.out.println("setcolspan=" + (columns-column+1));
					cell.setColspan(columns-column+1);
				} else if (colspan!=null && span!=1) {
					System.out.println("setcolspan=" + (span));
					cell.setColspan(span);
					column+=span-1;
				}
				table.addCell(cell);
				if (column>=columns || i==labels.length) {
					row++;
					column=0;
					if (row % 2 == 0) start = i;
					else i = start;
					System.out.println("next.i=" + i);
				}
			}
			return table;
		} catch (BadElementException be) {
			return new Paragraph(be.getMessage()).render();
		} catch (DocumentException de) {
			return new Paragraph(de.getMessage()).render();
		}
		
	}
}
