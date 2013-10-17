package cl.builderSoft.utilpdf.components;

import cl.builderSoft.utilpdf.PdfComponent;
import cl.builderSoft.utilpdf.Resources;

import com.lowagie.text.Chunk;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfWriter;

public class ParagraphCompuesto extends PdfComponent {
	String[] texts;
	int alignment = Element.ALIGN_LEFT;
	Font fonts[];
	boolean newLine = false;
	boolean newLine2 = false;

	public void setNewLine(boolean newLine) {
		this.newLine = newLine;
	}

	public void setNewLine2(boolean newLine2) {
		this.newLine2 = newLine2;
	}

	public ParagraphCompuesto(String texts[]) {
		this.texts = texts;
	}

	public ParagraphCompuesto(String texts[], Font fonts[]) {
		this.texts = texts;
		this.fonts = fonts;
	}

	public void setFont(Font fonts[]) {
		this.fonts = fonts;
	}

	public void setAlignment(int alignment) {
		this.alignment = alignment;
	}

	public Element render(PdfWriter writer) {
		return render();
	}

	public Element render() {
		Phrase p = new Phrase();
		for (int i = 0; fonts != null && i < fonts.length; i++) {
			Chunk ck = null;
			if (newLine) {
				ck = new Chunk(texts[i] + "\n", fonts[i]);
			} else if (newLine2 && i % 2 != 0) {
				ck = new Chunk(texts[i] + "\n", fonts[i]);
			} else {
				try {
					ck = new Chunk(texts[i], fonts[i]);
				} catch (Exception e) {
					System.out.println("Paragraph compuest number " + i + ", len " + texts.length + " fonts count "
							+ fonts.length);
					for (int j = 0; j < i; j++) {
						System.out.println("text: " + texts[j]);
					}
					e.printStackTrace();
				}
			}
			p.add(ck);

		}
		Chunk ck = new Chunk("\n", Resources.fontNormal);
		p.add(ck);
		return p;
	}
}