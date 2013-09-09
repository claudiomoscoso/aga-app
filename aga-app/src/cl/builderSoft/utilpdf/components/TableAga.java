package cl.builderSoft.utilpdf.components;


import cl.builderSoft.utilpdf.PdfComponent;
import cl.builderSoft.utilpdf.Resources;
import cl.builderSoft.utilpdf.RowsProvider;

import com.lowagie.text.Cell;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

public class TableAga extends PdfComponent {
	String columns[];
	RowsProvider provider;
	Object[] rows;
	String title;
	int widths[];
	int width = 100;
	Font fontTitle;
	Font fontHeader;
	Font fontCell;
	
	public TableAga(String columns[]) {
		this(null, columns);
	}
	
	public TableAga(String title, String columns[]) {
		this.columns = columns;
		this.title = title;
		fontTitle= Resources.fontBold;
		fontHeader= Resources.fontBold;
		fontCell= Resources.fontNormal;
	}
	
	public void setRows(Object rows[], RowsProvider provider) {
		this.rows = rows;
		this.provider = provider;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setWidths(int widths[]) {
		this.widths = widths;
	}

	protected Object[] getRow(Object rows[], RowsProvider provider, int i) {
		return provider.getRow(rows[i]);
	}
	
	public Element render() {
		try {
			PdfPTable table = new PdfPTable(columns.length);
			table.setSpacingBefore(8);
			PdfPCell c = table.getDefaultCell();
			c.setBorder(Rectangle.BOX);
			c.setPadding(0);
			c.setLeading(0,0);
			table.setWidthPercentage(width);
			if (widths!=null) table.setWidths(widths);
			
			if (title!=null) {
				table.setHeaderRows(2);
				com.lowagie.text.Paragraph p = new com.lowagie.text.Paragraph(title,fontTitle);
				PdfPCell cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_TOP);
				cell.setColspan(columns.length);
				cell.setPaddingTop(1);
				table.addCell(cell);
			} else {
				table.setHeaderRows(1);
			}
			
			for(int i=0; i<columns.length; i++) {
				com.lowagie.text.Paragraph p = new com.lowagie.text.Paragraph(columns[i], fontHeader);
				PdfPCell cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
				cell.setPaddingTop(1);
				if (i == 0 ) cell.setBorder(Rectangle.LEFT | Rectangle.TOP | Rectangle.BOTTOM);
				if (i == 1 ) cell.setBorder(Rectangle.TOP | Rectangle.BOTTOM | Rectangle.RIGHT);
				table.addCell(cell);
			}
			
			for(int i=0; rows!=null && provider!=null && i<rows.length; i++) {
				Object row[] = getRow(rows, provider, i);
				for(int j=0; j<row.length; j++) {
					String value;
					Object o = row[j];					
					value = Resources.getObjectDespliegue(o);
					com.lowagie.text.Paragraph p = new com.lowagie.text.Paragraph(value,fontCell);
					PdfPCell cell = new PdfPCell(p);
					if (o instanceof Float || o instanceof Integer || o instanceof Double) 
						cell.setHorizontalAlignment(Cell.ALIGN_RIGHT);
					cell.setPaddingTop(1);
					cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
					if (i == rows.length - 1) cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM);
					//if (j == 0) cell.setColspan(2);
					//else cell.setColspan(1);
					
					table.addCell(cell);
				}
			}
		
			return table;
		} catch (DocumentException be) {
			return new Paragraph(be.getMessage()).render();
		}
		
	}

	public Font getFontTitle() {
		return fontTitle;
	}

	public void setFontTitle(Font fontTitle) {
		this.fontTitle = fontTitle;
	}

	public Font getFontHeader() {
		return fontHeader;
	}

	public void setFontHeader(Font fontHeader) {
		this.fontHeader = fontHeader;
	}

	public Font getFontCell() {
		return fontCell;
	}

	public void setFontCell(Font fontCell) {
		this.fontCell = fontCell;
	}
}
