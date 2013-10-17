package cl.builderSoft.utilpdf.components;


import cl.builderSoft.utilpdf.PdfComponent;

import com.lowagie.text.Chunk;
import com.lowagie.text.Element;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.Barcode39;
import com.lowagie.text.pdf.BarcodeEAN;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;

public class BarCode extends PdfComponent {
	
	// types
	public final static int EAN = 1;
	public final static int CODE39 = 2;

	// subtypes
	public final static int EAN8 = 1;
	public final static int EAN13 = 2;
	// subtypes
	
	int type, subtype;
	String value;
	
	public BarCode(int type, int subtype, String value) {
		this.type = type;
		this.subtype = subtype;
		this.value = value;
	}

	public Element render(PdfWriter writer) {
		PdfContentByte cb = writer.getDirectContent();
		Image barcode = null;
		if (type == EAN) {
			BarcodeEAN codeEAN = new BarcodeEAN();
			int eanType = BarcodeEAN.EAN8;
			switch (subtype) {
			case EAN8 : eanType = BarcodeEAN.EAN8;break;
			case EAN13 : eanType = BarcodeEAN.EAN13;break;
			}
			codeEAN.setCodeType(eanType);
			codeEAN.setCode(value);
			barcode = codeEAN.createImageWithBarcode(cb, null, null);
		} else if (type == CODE39) {
			Barcode39 code39 = new Barcode39();
			code39.setCode(value);
			code39.setStartStopText(false);
			code39.setGenerateChecksum(true);
			barcode = code39.createImageWithBarcode(cb, null, null);
		}

		return new com.lowagie.text.Paragraph(new Chunk(barcode, 0, 0));		
	}
	
}
