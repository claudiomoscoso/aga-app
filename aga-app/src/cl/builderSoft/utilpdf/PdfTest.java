package cl.builderSoft.utilpdf;

import java.io.File;
import java.util.List;
import java.util.Vector;

import cl.builderSoft.utilpdf.components.BarCode;
import cl.builderSoft.utilpdf.components.BlockTable;
import cl.builderSoft.utilpdf.components.CodeTable;
import cl.builderSoft.utilpdf.components.FieldTableSimple;
import cl.builderSoft.utilpdf.components.FieldsTable;
import cl.builderSoft.utilpdf.components.HorizontalPanel;
import cl.builderSoft.utilpdf.components.HorizontalTablePanel;
import cl.builderSoft.utilpdf.components.Image;
import cl.builderSoft.utilpdf.components.Paragraph;
import cl.builderSoft.utilpdf.components.Signature;
import cl.builderSoft.utilpdf.components.Table;
import cl.builderSoft.utilpdf.components.Title;
import cl.builderSoft.utilpdf.components.VerticalTablePanel;

public class PdfTest extends PdfDocument {

    String titulo = "Sin titulo";

    public void addElements(List list) throws Exception {

        BarCode barcode = new BarCode(BarCode.CODE39, 0, "123456");

        Image image = new Image("siss2.jpg");
        image.setPosition(0, 0);
        image.setSize(120, 72);

        Title title = new Title(titulo);

        HorizontalPanel header = new HorizontalPanel(new PdfComponent[]{image, title, barcode});
        header.setAlignments(new int[]{
                    PdfComponent.ALIGN_LEFT, PdfComponent.ALIGN_CENTER, PdfComponent.ALIGN_RIGHT});
        list.add(header);

        list.add(new Paragraph("\nParrafo libre en donde podr�a haber una descripci�n del contenido" + " del documento, o bien informaci�n que se desea presentar en prosa y no" + " en forma tabular.  A continuaci�n vienen multiples ejemplos de componentes"));

        String cols[] = {"pre", "post", "largo"};
        String testTable[] = {"primero", "segundo", "tercero"};
        Table table = new Table("Tabla de prueba", cols);
        table.setRows(testTable, new RowsProvider() {

            public Object[] getRow(Object o) {
                String s = (String) o;
                return new Object[]{s.substring(0, 1), s, new Integer(s.length())};
            }
        });
        list.add(table);

        String labels[] = {"uno", "dos", "tres", "cuatro", "cinco", "seis", "siete"};
        String values[] = {"1", "2", "3", "4", "5", "6", "7"};
        int colspan[] = {1, 1, 1, 1, 2, 1, 1};
        int colwidth[] = {20, 80};

        FieldsTable ftable = new FieldsTable("Prueba tabla multiple", labels);
        ftable.setValues(values);
        ftable.setColspan(colspan);
        ftable.setColWidth(colwidth);
        list.add(ftable);

        // Tabla de codigos/valor/descripcion

        CodeTable.Code codes[] = {
            new CodeTable.Code("03", "RUT"),
            new CodeTable.Code("015", "Vencimiento Impuesto")
        };

        String codeValues[] = {"14.315.505-6", "20-05-2008"};
        CodeTable ctable = new CodeTable(codes, codeValues);
        ctable.setWidth(100);
        list.add(ctable);

        // Tabla de codigos/valor 1

        List blockLabels = new Vector();
        blockLabels.add(new String[]{"Unidad Giradora", "918"});
        blockLabels.add(new String[]{"Rut Fiscalizador", "303"});
        blockLabels.add(new String[]{"N� Formulario Original", "020"});

        Object data[] = {new Long(1284), "11.434.544-7", new Integer(29)};

        BlockTable bt = new BlockTable(blockLabels, data);
        bt.setWidths(new int[]{55, 10, 35});
        bt.setWidth(90);
        list.add(bt);

        // Tabla de codigos/valor 2
        List blockLabels2 = new Vector();
        blockLabels2.add(new String[]{"Impuesto o multa a pagar en el plazo", "091", "(=)"});
        blockLabels2.add(new String[]{"Reajuste (IPC)", "092", "(+)"});
        blockLabels2.add(new String[]{"Intereses y multas", "093", "(+)"});
        blockLabels2.add(new String[]{"Total a pagar fuera de plazo", "094", "(=)"});

        Object data2[] = {new Long(4638364), new Long(73464), new Long(2339), new Long(434344)};

        BlockTable bt2 = new BlockTable(blockLabels2, data2);
        bt2.setTitle("Detalle de impuestos o multas a pagar");
        bt2.setWidths(new int[]{45, 10, 35, 10});
        bt2.setWidth(90);
        bt2.setSeparators(new int[]{3});
        list.add(bt2);

        HorizontalPanel panel = new HorizontalPanel();
        panel.setComponents(new PdfComponent[]{bt, bt2});
        panel.setWidths(new int[]{40, 60});
        list.add(panel);

        String lines[] = {"Nombre y Firma del Contribuyente", "Representante Legal o Persona Adulta"};
        Signature signature = new Signature(lines);
        signature.setWidth(40);

        list.add(new Paragraph("\n\n\n"));
        list.add(signature);

        VerticalTablePanel panel2 = new VerticalTablePanel();
        panel2.setComponents(new PdfComponent[]{bt2, signature});
        list.add(panel2);

        VerticalTablePanel panel3 = new VerticalTablePanel();
        panel3.setComponents(new PdfComponent[]{ctable, bt});
        list.add(panel3);

        HorizontalTablePanel panel4 = new HorizontalTablePanel();
        panel4.setComponents(new PdfComponent[]{panel2, panel3});
        list.add(panel4);

        /*
        PdfContribuyente pc = new PdfContribuyente();
        ContribuyenteTo c = new ContribuyenteTo();
        c.setRut(new Integer(51000000));
        c.setDv("1");
        c.setRazonSocial("Empresa de prueba");
        DireccionTo d = new DireccionTo();
        d.setCalle("Huerfanos");
        d.setNumero("900");
        d.setAreaTelefono(new Short((short)02));
        d.setTelefono(new Long(6941232));
        d.setDepartamento("42");
        d.setCiudad("Santiago");
        ComunaTo comuna = new ComunaTo();
        comuna.setDescripcion("Santiago Centro");
        RegionTo region = new RegionTo();
        region.setDescripcion("Metropolitana");
        comuna.setRegional(region);
        d.setComuna(comuna);
        c.setDireccion(d);
        pc.setValue(c);
        list.add(pc);
         */
        List labelsSimple = new Vector();
        labelsSimple.add(new String[]{"Fecha Atencion", "Nombre Cliente"});
        labelsSimple.add(new String[]{"Telefono", "Direccion"});
        labelsSimple.add(new String[]{"Ciudad", "Patrocinante"});
        labelsSimple.add(new String[]{"Telefono", "Direccion"});
        labelsSimple.add(new String[]{"Ciudad", "Patrocinante"});

        Object object[] = {
            new Long(1284), "11.434.544-7",
            new Integer(29), "test",
            new Integer(123456), new Integer(789012),
            "wawa", "mamo",
            "na", "cya"
        };

        FieldTableSimple fieldTableSimple = new FieldTableSimple(labelsSimple, object);
        fieldTableSimple.setWidths(new int[]{15, 40, 15, 30});
        fieldTableSimple.setWidth(100);
        list.add(fieldTableSimple);

    }
    
    public static void main(String args[]) throws Exception {
        File f = new File("/home/cmoscoso/miguel/test.pdf");
        PdfDocument test = new PdfTest();
        test.setWaterMark("Mi Borrador");
//        test.setValue("Prueba de documento");
        test.create(f);
//		Runtime.getRuntime().exec(new String[] {"c:\\", "c:\\test.pdf"});
    }


    public void setValue(Object o) {
        this.titulo = (String) o;
    }
}
