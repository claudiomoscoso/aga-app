package cl.builderSoft.utilpdf.components;

import cl.builderSoft.utilpdf.PdfComponent;

import com.lowagie.text.Element;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class BoxTableComponent extends PdfComponent {

    int width = 100;
    PdfComponent values[];

    public BoxTableComponent(PdfComponent values[]) {
        setValues(values);
    }

    public void setValues(PdfComponent[] values) {
        this.values = values;
    }

    public Element render(PdfWriter writer) {
        try {
            PdfPTable table = new PdfPTable(1);
            table.setSpacingBefore(8);
            PdfPCell c = table.getDefaultCell();
            c.setBorder(Rectangle.NO_BORDER);
            table.setWidthPercentage(width);

            for (int i = 0; i < values.length; i++) {
                int border = Rectangle.LEFT | Rectangle.RIGHT;
                if (i == 0) {
                    border |= Rectangle.TOP;
                }
                if (i == values.length - 1) {
                    border |= Rectangle.BOTTOM;
                }
                PdfPCell cell;

                Object o = values[i].render(writer);
                if (o == null) {
                    o = new com.lowagie.text.Paragraph("");
                }
                cell = new PdfPCell((com.lowagie.text.Paragraph) o);
                cell.setPadding(5);
                cell.setBorder(border);
                table.addCell(cell);
            }
            return table;
        } catch (Exception e) {
            return new Paragraph(e.getMessage()).render();
        }
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
