package cl.builderSoft.utilpdf;

import com.lowagie.text.Cell;
import com.lowagie.text.Element;
import com.lowagie.text.pdf.PdfWriter;

public abstract class PdfComponent {
	public static final int ALIGN_LEFT=0;
	public static final int ALIGN_RIGHT=1;
	public static final int ALIGN_CENTER=2;
	public static final int NO_BORDER=0;
	public static final int BOX=15;
	
	public Element render(PdfWriter writer) {
		return render();
	}
	
	public Element render() {
		return null;
	}
	
	public int getCellAlignment(int alignment) {
		switch (alignment) {
		case ALIGN_LEFT : return Cell.ALIGN_LEFT;
		case ALIGN_RIGHT : return Cell.ALIGN_RIGHT;
		}
		return Cell.ALIGN_CENTER;
	}
}
