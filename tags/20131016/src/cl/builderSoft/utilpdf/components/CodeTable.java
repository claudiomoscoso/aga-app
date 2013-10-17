package cl.builderSoft.utilpdf.components;


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

public class CodeTable extends PdfComponent {
	
	public final  static int FIRST_COL_WITHOUT_CODE=0;
	public final  static int FIRST_COL_WITH_CODE=1;
	Code codes[];
	Object values[];
	int codeWidth = 5;
	int typeCodetable;
	int width = 100;
	int widthsDescription[];
	
	Font font;
	Font fontHeader;
	
	public CodeTable() {
		this(null, null, FIRST_COL_WITH_CODE);
	}

	public CodeTable(Code codes[]) {
		this(codes, null, FIRST_COL_WITH_CODE);
	}

	public CodeTable(Code codes[], Object values[]) {
		this(codes, values, FIRST_COL_WITH_CODE);
	}

	public CodeTable(Code codes[], Object values[], int typeCodetable) {
		font=Resources.fontNormal;
		fontHeader= Resources.fontBold;
		this.typeCodetable= typeCodetable; 
		setCodes(codes);
		setValues(values);
	}
	
	public void setCodes(Code[] codes) {
		this.codes = codes;
	}

	public void setValues(Object[] values) {
		this.values = values;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}

	public Element render() {
		try {			
			int nroCols=(typeCodetable == FIRST_COL_WITH_CODE)?codes.length*2: (codes.length*2)-1;
			PdfPTable table = new PdfPTable(nroCols);
			table.setSpacingBefore(8);
			PdfPCell c = table.getDefaultCell();
			c.setBorder(Rectangle.BOX);
			c.setPadding(0);
			c.setLeading(0,0);
			table.setWidthPercentage(width);

			int widths[]= calculateWidths(codes.length);			
			
			table.setWidths(widths);
			
			com.lowagie.text.Paragraph p;
			
			PdfPCell cell;
			for(int i=0; i<codes.length; i++){
				if((typeCodetable == FIRST_COL_WITH_CODE) || (typeCodetable == FIRST_COL_WITHOUT_CODE && i!=0)){				
					p = new com.lowagie.text.Paragraph(codes[i].code, fontHeader);
					cell = new PdfPCell(p);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER );
					cell.setPaddingTop(1);
					table.addCell(cell);
				}
				
				PdfPTable nested = new PdfPTable(1);
				p = new com.lowagie.text.Paragraph(codes[i].description, fontHeader);
				cell = new PdfPCell(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER );
				cell.setPaddingTop(1);
				nested.addCell(cell);

				Object o = values[i];
				if (o==null) o =" ";
				p = new com.lowagie.text.Paragraph(Resources.getObjectDespliegue( o),font);
				cell = new PdfPCell(p);
				//if (o instanceof Float || o instanceof Integer || o instanceof Double) 
				//	cell.setHorizontalAlignment(Cell.ALIGN_RIGHT);
				cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
				cell.setPaddingTop(1);
				nested.addCell(cell);

				table.addCell(nested);
			}
			return table;
		} catch (BadElementException be) {
			return new Paragraph(be.getMessage()).render();
		} catch (DocumentException de) {
			return new Paragraph(de.getMessage()).render();
		}
	}
	
	private int[] calculateWidths(int nroElems) {
		int nroCodsCels=(typeCodetable == FIRST_COL_WITH_CODE)?  nroElems:(nroElems)-1;
		int nroCols=(typeCodetable == FIRST_COL_WITH_CODE)?  nroElems*2:(nroElems*2)-1;
		int widths[]= new int[nroCols];
		
		int restoWidht = width - (nroCodsCels *codeWidth);
		int logDistribucion= typeCodetable == FIRST_COL_WITH_CODE?0:1;
		if (widthsDescription== null){
			int descriptionWidth = restoWidht /nroElems;
				for(int i=0; i<nroCols; i++){
					if (i%2==logDistribucion) widths[i] = codeWidth;
					else widths[i] = descriptionWidth;
				}			
		}
		else{
			for(int i=0; i<nroCols; i++){
				if (i%2==logDistribucion) widths[i] = codeWidth;				
				else {
					int descriptionWidth = restoWidht* widthsDescription[i]/ 100;
					widths[i] = descriptionWidth;
				}
			}			
			
		}
		System.out.println(widths);
		return widths;
	}

	public static class Code {
		String code;
		String description;
		public Code(String code, String description) {
			this.code = code;
			this.description = description;
		}
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public Font getFontHeader() {
		return fontHeader;
	}

	public void setFontHeader(Font fontHeader) {
		this.fontHeader = fontHeader;
	}


	public void setWidthsDescription(int[] widths) {
		this.widthsDescription = widths;
	}

}
