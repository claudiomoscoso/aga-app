package cl.builderSoft.utilpdf.components;

import java.util.List;

import cl.builderSoft.utilpdf.PdfComponent;
import cl.builderSoft.utilpdf.Resources;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Cell;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

public class BlockTable extends PdfComponent {
	
	Object values[];
	List labels;
	
	int widths[] = new int[0];
	int width = 100;
	int valueColumn = 2;
	int separators[];
	
	String title;
	String glosa;
	
	Font fontTitle;
	Font font;

	
	public BlockTable() {
		this(null, null);
	}
	
	public BlockTable(List labels) {
		this(labels, null);
	}
	
	public BlockTable(List labels, Object values[]) {
		fontTitle=Resources.fontBold;
		font= Resources.fontNormal;
		setLabels(labels);
		setValues(values);
	}
	
	public void setLabels(List labels) {
		this.labels = labels;
	}

	public void setValues(Object[] values) {
		this.values = values;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}

	public void setWidths(int widths[]) {
		this.widths = widths;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setSeparators(int separators[]) {
		this.separators = separators;
	}
	
	public Element render() {
		try {
			PdfPTable table = new PdfPTable(widths.length);
			table.setSpacingBefore(8);
			PdfPCell c = table.getDefaultCell();
			c.setBorder(Rectangle.BOX);
			c.setPadding(0);
			c.setLeading(0,0);
			table.setWidthPercentage(width);
			if (widths.length>0) table.setWidths(widths);

			if (title!=null) {
				table.setHeaderRows(1);
				com.lowagie.text.Paragraph p = new com.lowagie.text.Paragraph(title, fontTitle);
				PdfPCell cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
				cell.setColspan(widths.length);
				cell.setBorder(Rectangle.BOX);
				table.addCell(cell);
			} 
			for(int i=0; i<labels.size(); i++){
				Object row[] = (Object[])labels.get(i);
				for(int j=0, col=0; col<row.length+1;col++) {
					int border = Rectangle.LEFT | Rectangle.RIGHT;
					if (j==row.length) border |= Rectangle.RIGHT;
					if (i==0 || isSeparator(i)) border |= Rectangle.TOP;
					if (i==labels.size()-1) border |= Rectangle.BOTTOM;

					PdfPCell cell;
					com.lowagie.text.Paragraph p;
					if (col==valueColumn) {
						Object o = values[i];
						if (o==null) o = "";
						p = new com.lowagie.text.Paragraph(Resources.getObjectDespliegue(o),font);
						cell = new PdfPCell(p);
						//if (o instanceof Long || o instanceof Float || o instanceof Integer || o instanceof Double) 
					    cell.setHorizontalAlignment(Cell.ALIGN_RIGHT);
					} else {
						p = new com.lowagie.text.Paragraph(row[j].toString(),font);
						cell = new PdfPCell(p);
						if (col>0) //cell.setHorizontalAlignment(Element.ALIGN_CENTER);
							cell.setHorizontalAlignment(Cell.ALIGN_RIGHT);
						j++;
					}
					
					cell.setBorder(border);
					cell.setPaddingTop(1);
					table.addCell(cell);
				}
			}
			if(glosa != null){
				PdfPCell cell;
				com.lowagie.text.Paragraph p = new com.lowagie.text.Paragraph(glosa,font);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setColspan(widths.length); 	
				table.addCell(cell);
			}
			return table;
		} catch (BadElementException be) {
			return new Paragraph(be.getMessage()).render();
		} catch (DocumentException de) {
			return new Paragraph(de.getMessage()).render();
		}
	}
	
	private boolean isSeparator(int row) {
		for(int i=0; separators!=null && i<separators.length;i++) {
			if (row==separators[i]) return true;
		}
		return false;
	}

	public Font getFontTitle() {
		return fontTitle;
	}

	public void setFontTitle(Font fontTitle) {
		this.fontTitle = fontTitle;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public String getGlosa() {
		return glosa;
	}

	public void setGlosa(String glosa) {
		this.glosa = glosa;
	}
}
