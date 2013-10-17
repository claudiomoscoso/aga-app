package cl.builderSoft.aga.certificate.pdf;

import java.io.File;
import java.util.Date;
import java.util.List;

import cl.builderSoft.aga.certificate.vo.CertificadoMezclaVo;
import cl.builderSoft.aga.certificate.vo.Componentes;
import cl.builderSoft.util.DateFormatter;
import cl.builderSoft.utilpdf.PdfComponent;
import cl.builderSoft.utilpdf.PdfDocument;
import cl.builderSoft.utilpdf.Resources;
import cl.builderSoft.utilpdf.RowsProvider;
import cl.builderSoft.utilpdf.components.HorizontalPanel;
import cl.builderSoft.utilpdf.components.HorizontalTablePanel;
import cl.builderSoft.utilpdf.components.Image;
import cl.builderSoft.utilpdf.components.Paragraph;
import cl.builderSoft.utilpdf.components.ParagraphCompuesto;
import cl.builderSoft.utilpdf.components.Signature;
import cl.builderSoft.utilpdf.components.TableAga;
import cl.builderSoft.utilpdf.components.Title;

import com.lowagie.text.Font;

public class CertificadoMezclaPdf extends PdfDocument {
	private String titulo = "REPORTE ANALITICO";
	
	CertificadoMezclaVo certificadoMezclaVo = null;//new CertificadoMezclaVo(0);

 
	public void addElements(List list) throws Exception {
        Image image = new Image("SGS.png");
//        Image image = new Image("aga_iso.GIF");
        image.setPosition(0, 0);
        image.setSize(120, 72);
		
//		BSLog.debug("aca en PDF");
        Title title = new Title(titulo);
        Paragraph blank = new Paragraph("");
        
        //FECHA REPORTE Y TITULO
        
        String[] textFecha = {"Fecha / ", "Date"};
        Font[] fonts = new Font[]{Resources.FONT_TITLE_3, Resources.fontTitle3Compuesto};
        
        ParagraphCompuesto tituloFecha = new ParagraphCompuesto(textFecha, fonts );
        String fecha = new DateFormatter(DateFormatter.FORMAT_DD_MM_YYYY).format(new Date());
        Paragraph valueFecha = new Paragraph( fecha, Resources.fontNormal);
        
        HorizontalPanel header = new HorizontalPanel(new PdfComponent[]{blank, tituloFecha});
        HorizontalPanel header2 = new HorizontalPanel(new PdfComponent[]{blank, valueFecha});
        header.setWidths(new int[]{80, 20});
        header.setAlignments(new int[]{
                    PdfComponent.ALIGN_LEFT, PdfComponent.ALIGN_RIGHT});
        header2.setAlignments(new int[]{
                PdfComponent.ALIGN_LEFT, PdfComponent.ALIGN_RIGHT});

        list.add(new Paragraph("\n\n"));
        list.add(header);
        list.add(header2);
        list.add(title);
        list.add(new Paragraph("\n"));
        
        //ENCABEZADO CLIENTE
        String[] cliente = new String[]{
        	"Cliente / ", "Customer", certificadoMezclaVo.getCliente(), "",
        	"Contacto / ","Contact",certificadoMezclaVo.getContacto()
        };
        
        Font[] fontCliente = new Font[]{
    		Resources.FONT_TITLE_3, Resources.fontNormalCompuesto, Resources.fontNormal,	Resources.fontNormal,
    		Resources.FONT_TITLE_3, Resources.fontNormalCompuesto, Resources.FONT_TITLE_3
        };
        Font[] fontNormal = new Font[]{Resources.fontNormal, Resources.fontNormalCompuesto};

        ParagraphCompuesto pCliente = new ParagraphCompuesto(cliente, fontCliente);
        pCliente.setNewLine2(true);
        
        //ENCABEZADO CLIENTE - DATOS ANALISIS
        
        Font[] fontAnalisis = new Font[]{
        		Resources.FONT_TITLE_3, Resources.fontNormalCompuesto,
        		Resources.FONT_TITLE_3, Resources.fontNormalCompuesto,
        		Resources.FONT_TITLE_3, Resources.fontNormalCompuesto
            };
        Font[] fontPuntoAnalisis = new Font[]{
        		Resources.fontNormal,
        		Resources.fontNormal,
        		Resources.fontNormal
            };
        String[] sAnalisis = new String[]{
    		"Nº de Análisis / ", "Analysis No.",
    		"Nº de Cilindro / ", "Cylinder No.",
    		"Nº de Orden / ", "Order No."
    	};
        String[] sPuntoAnalisis = new String[]{
        		":",
        		":",
        		":"
        	};
        String[] sValueAnalisis = new String[]{
        		certificadoMezclaVo.getNroAnalisis(),
        		certificadoMezclaVo.getNroCilindro(),
        		certificadoMezclaVo.getNroOrden()
        	};
        
        ParagraphCompuesto pAnalisis = new ParagraphCompuesto(sAnalisis, fontAnalisis);
        pAnalisis.setNewLine2(true);
        
        ParagraphCompuesto pValueAnalisis = new ParagraphCompuesto(sValueAnalisis, fontPuntoAnalisis);
        pValueAnalisis.setNewLine(true);
        
        ParagraphCompuesto pPuntoAnalisis = new ParagraphCompuesto(sPuntoAnalisis, fontPuntoAnalisis);
        pPuntoAnalisis.setNewLine(true);

        HorizontalTablePanel hp = new HorizontalTablePanel(new PdfComponent[]{pCliente, pAnalisis, pPuntoAnalisis, pValueAnalisis});
        hp.setWidths(new int[]{50,20,3,27});
        
        list.add(hp);
        
        //TABLA CILINDROS
        
        String tipoCilindro = certificadoMezclaVo.getTipoCilindro();
        String conexionValvula = certificadoMezclaVo.getConexionValvula();
        String presionLlenado = certificadoMezclaVo.getPresionLlenado();
        String volumenGas = certificadoMezclaVo.getVolumenGas();

        Font[] fontCilindros = new Font[]{Resources.FONT_TITLE_3, Resources.fontNormalCompuesto, Resources.fontNormal, Resources.fontNormal};

        String[] sTipoCilindro = {"Tipo de Cilindro", "Cylinder type", "", tipoCilindro};
        ParagraphCompuesto pTipoCilindro = new ParagraphCompuesto(sTipoCilindro, fontCilindros);
        pTipoCilindro.setNewLine(true);

        String[] sConexionValvula = {"Conexión de válvula", "Valve conexion", "", conexionValvula};
        ParagraphCompuesto pConexionValvula = new ParagraphCompuesto(sConexionValvula, fontCilindros);
        pConexionValvula.setNewLine(true);

        String[] sPresionLlenado = {"Presión de llenado", "Filling presion", "", presionLlenado+" Bar"};
        ParagraphCompuesto pPresionLlenado = new ParagraphCompuesto(sPresionLlenado, fontCilindros);
        pPresionLlenado.setNewLine(true);

        String[] sVolumenGas = {"Volumen de gas", "Gas volume", "", volumenGas + " m3"};
        ParagraphCompuesto pVolumenGas = new ParagraphCompuesto(sVolumenGas, fontCilindros);
        pVolumenGas.setNewLine(true);

        HorizontalTablePanel hCilindros = new HorizontalTablePanel(new PdfComponent[]{pTipoCilindro, pConexionValvula,  pPresionLlenado, pVolumenGas});
        
        
        String[] sCilindro = new String[]{"Datos del Cilindro / ", "Cylinder Data"};
        ParagraphCompuesto pCilindro = new ParagraphCompuesto(sCilindro, fontNormal);
        
        list.add(pCilindro);
        list.add(hCilindros);
        
        //TABLA COMPONENTES

        String cols[] = {"Componente", "", "Composición requerida", "Análisis", "Unidad", "Desviación Absoluta", "Desviación % rel."};
        TableAga table = new TableAga(null, cols);
        table.setRows(certificadoMezclaVo.getComponentes(), new RowsProvider() {
            public Object[] getRow(Object o) {
                Componentes componentes = (Componentes) o;
                return new Object[]{componentes.getComponente(), componentes.getSigla(), componentes.getComposicion(),
                		componentes.getAnalisis(), componentes.getUnidad(), componentes.getDesviacionAbsoluta(),
                		componentes.getDesviacionRelativa()};
            }
        });
        list.add(table);

        //DATOS DEL PRODUCTO
        Font[] fontGlosa = new Font[]{
    		Resources.FONT_TITLE_3, Resources.fontNormalCompuesto, 
    		Resources.FONT_TITLE_3, Resources.fontNormalCompuesto, 
    		Resources.FONT_TITLE_3, Resources.fontNormalCompuesto, 
    		Resources.FONT_TITLE_3, Resources.fontNormalCompuesto, 
    		Resources.FONT_TITLE_3, Resources.fontNormalCompuesto, 
    		Resources.FONT_TITLE_3, Resources.fontNormalCompuesto, 
    		Resources.FONT_TITLE_3, Resources.fontNormalCompuesto, 
    		Resources.FONT_TITLE_3, Resources.fontNormalCompuesto, 
    		Resources.FONT_TITLE_3, Resources.fontNormalCompuesto, 
        };
        Font[] fontPunto = new Font[]{
        		Resources.fontNormal, 
        		Resources.fontNormal, 
        		Resources.fontNormal, 
        		Resources.fontNormal, 
        		Resources.fontNormal, 
        		Resources.fontNormal, 
        		Resources.fontNormal, 
        		Resources.fontNormal, 
        		Resources.fontNormal, 
        		Resources.fontNormal 
            };
        String[] sGlosa = {
    		"Tipo de Producto / ", "Product Type",
    		"Método de preparacion / ", "Preparation methods",
    		"Nivel de confianza / ", "Confidence level",
    		"Tolerancia de preparación / ", "Blend Tolerance",
    		"Estabilidad Garantizada / ", "Shelt life",
    		"Temperatura min. recomendada / ", "Recommended Storage And usage temperature",
    		"Presión mínima de uso / ", "Minimun pressure of",
    		"Método Analítico / ", "Analitical Method",
    		"Patrón Empleado / ", "Standard used",
        };
        ParagraphCompuesto pGlosa = new ParagraphCompuesto(sGlosa, fontGlosa);
        pGlosa.setNewLine2(true);

        String[] sPunto = {
        		":",
        		":",
        		":",
        		":",
        		":",
        		":",
        		"",
        		":",
        		":",
        		":",
            };
            ParagraphCompuesto pPunto = new ParagraphCompuesto(sPunto, fontPunto);
            pPunto.setNewLine(true);

            String[] sValue = {
            		certificadoMezclaVo.getTipoProducto(),
            		certificadoMezclaVo.getMetodoPreparacion(),
            		certificadoMezclaVo.getNivelConfianza(),
            		certificadoMezclaVo.getToleranciaPreparacion(),
            		certificadoMezclaVo.getEstabilidadGarantizada() + " meses",
            		certificadoMezclaVo.getTemperaturaRecomendada() + " °C",
            		"",
            		certificadoMezclaVo.getPresionMinimaUso() + " Bar",
            		certificadoMezclaVo.getMetodoAnalitico(),
            		certificadoMezclaVo.getPatronEmpleado(),
                };
                ParagraphCompuesto pValue = new ParagraphCompuesto(sValue, fontPunto);
                pValue.setNewLine(true);

		HorizontalTablePanel hGlosa = new HorizontalTablePanel(new PdfComponent[] { pGlosa, pPunto, pValue });
		hGlosa.setWidths(new int[] { 40, 3, 57 });
        list.add(hGlosa);
        
      
        //NOTA
        
        String text = "Los productos son manufacturados con equipamiento y estandares de calibración trazables al SI." +
        		"el resultado de las mediciones es trazable a los patrones del laboratorio custodio de los patrones nacionales de masa de " +
        		"Chile y mediante este a las unidades del SI (Sist. internacional de unidades) Tolerancias referidas a normas ISO 6141 y 6143";
        Paragraph paragraph3 = new Paragraph(text, Resources.fontNormal);
        list.add(paragraph3);


        //COMENTARIO
        Font[] fontComentario = new Font[]{
    		Resources.fontBold, Resources.fontNormalCompuesto, Resources.fontNormal, 
    		Resources.fontBold, Resources.fontNormal, 
    		Resources.fontBold, Resources.fontNormal
        };
        
        String[] comentario = {
        	"Comentarios / ", "Comments : ", certificadoMezclaVo.getComentario() + "\n", 
    		"Código de Mezcla : ", certificadoMezclaVo.getCodigoMezcla() + " ", 
    		"Nombre de Mezcla : ", certificadoMezclaVo.getNombreMezcla()
        };
        
        ParagraphCompuesto pComentario = new ParagraphCompuesto(comentario, fontComentario );
        
//        list.add(new Paragraph("\n"));
        list.add(pComentario);

        //LUGAR PREPARACION
        
        Font[] fontPreparacion = new Font[]{Resources.fontBold, Resources.fontNormalCompuesto, Resources.fontNormal};
        String[] preparacion = {"Lugar de preparación / ", "Site:    ", certificadoMezclaVo.getLugarProduccion()};
        ParagraphCompuesto pPreparacion = new ParagraphCompuesto(preparacion, fontPreparacion);
        
        list.add(new Paragraph("\n\n"));
        list.add(pPreparacion);
        
        //RESPONSABLE
        Font[] fontResponsable = new Font[]{Resources.FONT_TITLE_3, Resources.fontNormal, Resources.fontBold, Resources.fontNormalCompuesto };
        String[] responsable = {certificadoMezclaVo.getResponsable(), "\n", "Responsable del análisis / ", "Responsable for the analysis"};
        ParagraphCompuesto pResponsable = new ParagraphCompuesto(responsable, fontResponsable);
        
        list.add(new Paragraph("\n\n"));
        list.add(pResponsable);
        
        //FOOT
        String lines[] = {
        		"Vicente  Reyes 722-Maipú           Teléfono  +56-2 531 2455           CL-PRO 0010 E", 
        		"Casilla 164953, Stgo 9             FAX      +56-2 531 1447                         ", 
        		"SANTIAGO, Chile                    Atención Clientes: 800 800 242 - 800 800 112    " 
        		};
        Signature signature = new Signature(lines);
        signature.setWidth(70);
        
        HorizontalPanel footer = new HorizontalPanel(new PdfComponent[]{image, signature});
        footer.setWidths(new int[]{3,97});
        footer.setAlignments(new int[]{
                PdfComponent.ALIGN_RIGHT, PdfComponent.ALIGN_CENTER});
        
        list.add(new Paragraph("\n\n\n\n\n\n"));
        list.add(footer);
        
//      HeaderFooter xfooter = new HeaderFooter(new Phrase("-"), new Phrase("-"));
//		xfooter.setAlignment(Element.ALIGN_CENTER);
//		list.add(xfooter);
        
	}

 
	public void setValue(Object o) {
		certificadoMezclaVo = (CertificadoMezclaVo)o;
	}
	
	public static void main(String[] args) throws Exception{
        File f = new File("c://miguel/reporteAnalitico.pdf");
        PdfDocument test = new CertificadoMezclaPdf();
        test.setValue(new CertificadoMezclaVo(0));
        //test.setWaterMark("Mi Borrador");
        test.create(f);
		
	}

}
