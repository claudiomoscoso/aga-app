/**
 * $Id: UsingFontFactory.java,v 1.2 2005/04/08 12:56:46 blowagie Exp $
 * $Name:  $
 *
 * This code is part of the 'iText Tutorial'.
 * You can find the complete tutorial at the following address:
 * http://itext.sourceforge.net/tutorial/
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * itext-questions@lists.sourceforge.net
 */

package cl.builderSoft.aga.certificate;

import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.TreeSet;

import com.lowagie.text.Document;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;

public class TestGetFonts {

	/**
	 * Special rendering of Chunks.
	 * 
	 * @param args
	 *            no arguments needed here
	 */
	public static void main(String[] args) throws Exception {

		System.out.println("Fonts in the FontFactory");

		// step 1: creation of a document-object
		Document document = new Document();

		// step 2:
		// we create a writer that listens to the document
		PdfWriter.getInstance(document, new FileOutputStream("d:\\temp\\FontFactory.pdf"));

		// step 3: we open the document
		document.open();
		// step 4:
		String name;
		Paragraph p = new Paragraph("Font Families", FontFactory.getFont(FontFactory.HELVETICA, 16f));
		document.add(p);
		FontFactory.registerDirectories();
		TreeSet families = new TreeSet(FontFactory.getRegisteredFamilies());
		for (Iterator i = families.iterator(); i.hasNext();) {
			name = (String) i.next();
			p = new Paragraph(name);
			document.add(p);
		}
		document.newPage();
		String quick = "quick brown fox jumps over the lazy dog";
		p = new Paragraph("Fonts", FontFactory.getFont(FontFactory.HELVETICA, 16f));
		// TreeSet fonts = new TreeSet(FontFactory.getRegisteredFonts());
		for (Iterator i = families.iterator(); i.hasNext();) {
			name = (String) i.next();
			p = new Paragraph(name);
			document.add(p);
			try {
				if (name.toLowerCase().indexOf("linde") > -1) {
					System.out.println("\"" + name + "\"");
				}
				p = new Paragraph(quick, FontFactory.getFont(name, BaseFont.WINANSI, BaseFont.EMBEDDED));
				document.add(p);
			} catch (Exception e) {
				document.add(new Paragraph(e.getMessage()));
			}
		}

		// step 5: we close the document
		document.close();
	}
}
