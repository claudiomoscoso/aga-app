package cl.builderSoft.utilpdf;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import cl.builderSoft.utilpdf.components.PageBreak;
import cl.builderSoft.utilpdf.components.Paragraph;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;

public abstract class PdfDocument {

	String waterMark = null;

	public static int PAGE_ORIENTATION_HORIZONTAL = 1;
	public static int PAGE_ORIENTATION_VERTICAL = 0;
	int orientation = PAGE_ORIENTATION_VERTICAL;

	public abstract void addElements(List list) throws Exception;

	public void render(Document document, PdfWriter writer) throws Exception {
		ArrayList elements = new ArrayList();
		addElements(elements);
		for (int i = 0; i < elements.size(); i++) {
			PdfComponent component = (PdfComponent) elements.get(i);
			if (component instanceof PageBreak) {
				document.newPage();
			} else {
				document.add(component.render(writer));
			}
		}
		// System.out.println("---------------");

		// HeaderFooter xfooter = new HeaderFooter(new
		// Phrase("XXXXXXXXXXXXXXXXXXXXXXX"), new
		// Phrase("XXXXXXXXXXXXXXXXXXXXXXX"));
		// xfooter.setAlignment(Element.ALIGN_CENTER);
		// document.setFooter(xfooter);

	}

	public byte[] create() throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		Document document = new Document(orientation == PAGE_ORIENTATION_VERTICAL ? PageSize.LETTER : PageSize.LETTER.rotate(),
				50, 50, 50, 50);

		  
		PdfWriter writer = PdfWriter.getInstance(document, baos);
		/**<code>
		PdfPageEventHelper waterMarkHandler = getWaterMarkHandler();
		if (waterMarkHandler != null)
			writer.setPageEvent(waterMarkHandler);
			</code>*/
		document.open();

		render(document, writer);
		//document.newPage();
		
//		List l = new ArrayList();
//		l.add(new Paragraph("Hola Mundo"));
//		
//		document.add(     );
		/**<code>
		HeaderFooter footer = new HeaderFooter(new Phrase("This is page: ", new Font(Resources.fontNormal)), true); 
		footer.setBorder(Rectangle.NO_BORDER);
		footer.setAlignment(Element.ALIGN_CENTER);
		document.setFooter(footer);
		</code>*/
		document.close();
		return baos.toByteArray();
	}

	public PdfPageEventHelper getWaterMarkHandler() {
		if (waterMark != null)
			return new WaterMarkHandler(waterMark);
		return null;
	}

	public void create(File f) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte pdf[] = create();
		baos.write(pdf, 0, pdf.length);
		FileOutputStream fos = new FileOutputStream(f);
		baos.writeTo(fos);
		fos.close();
	}

	public abstract void setValue(Object o);

	public void setWaterMark(String waterMark) {
		this.waterMark = waterMark;
	}

	public void setPageOrientation(int orientation) {
		this.orientation = orientation;
	}

	public static void main(String args[]) throws Exception {
		File f = new File("c:\\test.pdf");
		PdfDocument test = new PdfTest();
		test.setWaterMark("Mi Borrador");
		test.setValue("Prueba de documento");
		test.create(f);
		// Runtime.getRuntime().exec(new String[] {"c:\\", "c:\\test.pdf"});
	}

	protected static class WaterMarkHandler extends PdfPageEventHelper {
		String waterMark = "Borrador";

		public WaterMarkHandler(String waterMark) {
			this.waterMark = waterMark;
		}

		/**
		 * <code>
		public void onStartPage(PdfWriter writer, Document document) {
//			BSLog.debug("Pase por onStartPage()");
			BaseFont font = Resources.getFontWaterMark();
			if (font == null)
				return;
			PdfContentByte cb = writer.getDirectContentUnder();
			cb.saveState();
			cb.setColorFill(Color.LIGHT_GRAY);
			cb.beginText();
			cb.setFontAndSize(font, 10);
//			cb.setFontAndSize(font, 48);
			cb.showTextAligned(Element.ALIGN_CENTER, waterMark, document.getPageSize().getWidth() / 2, document.getPageSize()
					.getHeight() / 2, 45);
			cb.endText();
			cb.restoreState();
		}
		</code>
		 */
	}

}
