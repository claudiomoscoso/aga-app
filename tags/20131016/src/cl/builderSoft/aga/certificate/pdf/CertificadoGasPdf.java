package cl.builderSoft.aga.certificate.pdf;

import java.io.File;
import java.util.Date;
import java.util.List;

import cl.builderSoft.aga.certificate.vo.CertificadoGasVo;
import cl.builderSoft.aga.certificate.vo.ComponentesBySolicitud;
import cl.builderSoft.aga.certificate.vo.ListLote;
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
import cl.builderSoft.utilpdf.components.Table;
import cl.builderSoft.utilpdf.components.Title;

import com.lowagie.text.Font;

public class CertificadoGasPdf extends PdfDocument {
	private String TITLE = "HIQ CERTIFICATE";

	CertificadoGasVo certificadoGasVo;

	public CertificadoGasPdf() {
	}

	public void addElements(List list) throws Exception {
		putDateTitle(list);
		// putEncabezado2(list);
		putEncabezado2_new(list);
		putEncabezado3_old(list);
		putGlosa(list);
		putTableComponentsBySolicitud(list);
		putTableEnvases(list);

		putLeyenda(list);
		putComentarios(list);

		putResponsable(list);

		putFooter1(list);
		// putFooter2(list);

		putImagen(list);

	}

	private void putLeyenda(List list) {
		String text = "Todos los productos son manufacturados con materiales y equipos trazables al sistema internacional de unidades (SI).";
		Paragraph paragraph3 = new Paragraph(text, Resources.fontNormal);
		list.add(paragraph3);

	}

	private void putResponsable(List list) {
		Font[] fontResponsable = new Font[] { Resources.FONT_TITLE_3, Resources.fontNormal, Resources.fontBold,
				Resources.fontNormalCompuesto };
		String[] responsable = { certificadoGasVo.getResponsable(), "\n", "Responsable del análisis / ",
				"Responsable for the analysis" };
		ParagraphCompuesto pResponsable = new ParagraphCompuesto(responsable, fontResponsable);
		
		 list.add(new Paragraph("\n\n"));
		list.add(pResponsable);
	}

	private void putFooter1(List list) {
		// RESPONSABLE
		Font[] fontLugar = new Font[] { Resources.FONT_TITLE_3, Resources.fontNormal, Resources.fontBold,
				Resources.fontNormalCompuesto };
		String[] lugar = { certificadoGasVo.getLugarProduccion(), "\n", "Lugar de producción / ", "Site" };
		ParagraphCompuesto pResponsable = new ParagraphCompuesto(lugar, fontLugar);

		 list.add(new Paragraph("\n\n"));

		// HeaderFooter footer = new HeaderFooter(new Phrase("-"), new
		// Phrase("-"));
		// footer.setAlignment(Element.ALIGN_CENTER);
		// list.add(footer);

		list.add(pResponsable);

	}

	private void putFooter2(List list) {
		list.add(new Paragraph("\n\n\n"));
		// FOOT
		String lines[] = { "Vicente  Reyes 722-Maipú           Teléfono  +56-2 531 2455           CL-PRO 0010 E",
				"Casilla 164953, Stgo 9             FAX      +56-2 531 1447                         ",
				"SANTIAGO, Chile                    Atención Clientes: 800 800 242 - 800 800 112    " };
		Signature signature = new Signature(lines);
		signature.setWidth(70);

		HorizontalPanel footer = new HorizontalPanel(new PdfComponent[] { signature });
		list.add(footer);
	}

	private void putImagen(List list) {
//		Image image = new Image("aga_iso.GIF");
		Image image = new Image("SGS.png");
		image.setPosition(0, 0);
		image.setSize(120, 72);

		String lines[] = { "Vicente  Reyes 722-Maipú           Teléfono  +56-2 531 2455           CL-PRO 0010 E",
				"Casilla 164953, Stgo 9             FAX      +56-2 531 1447                         ",
				"SANTIAGO, Chile                    Atención Clientes: 800 800 242 - 800 800 112    " };
		Signature signature = new Signature(lines);
		signature.setWidth(70);

		HorizontalPanel footer = new HorizontalPanel(new PdfComponent[] { image, signature });
		footer.setWidths(new int[] { 3, 97 });
		footer.setAlignments(new int[] { PdfComponent.ALIGN_RIGHT, PdfComponent.ALIGN_CENTER });

		list.add(new Paragraph("\n\n\n\n\n\n"));
		list.add(footer);

	}

	private void putGlosa(List list) {
		// list.add(new Paragraph("\n"));
		String text = "Los cilindros certificados cumplen con la siguiente especificación:";
		Paragraph paragraph = new Paragraph(text, Resources.fontNormal);
		list.add(paragraph);
		// list.add(new Paragraph("\n"));

	}

	private void putComentarios(List list) {
		// list.add(new Paragraph("\n\n"));
		Font[] fontComentario = new Font[] { Resources.fontBold, Resources.fontNormalCompuesto, Resources.fontNormal,
				Resources.fontBold, Resources.fontNormal, Resources.fontNormal };

		String[] comentario = { "Comentarios / ", "Comments : ", certificadoGasVo.getComentarios() + "\n",
				"Métodos de análisis utilizados : ", certificadoGasVo.getMetodosAnalisis() };
		// "\n" + certificadoGasVo.getCertificado() };

		ParagraphCompuesto pComentario = new ParagraphCompuesto(comentario, fontComentario);

		list.add(pComentario);

	}

	private void putTableEnvases(List list) {
		// TABLA LOTES
		list.add(new Paragraph("\n"));
		int lenEnvases = certificadoGasVo.getEnvases().length;
		int lenColumnas = calculateCols(lenEnvases, 5); // lenEnvases / 5 >= 1 ?
														// lenEnvases / 5 : 1;

		// ++lenColumnas;

		String cols[] = new String[lenColumnas];
		for (int i = 0; i < lenColumnas; i++) {
			cols[i] = "Número de envases";
		}

		Table table = new Table(null, cols);
		table.setWidth(70);
		// final ComponentesBySolicitud[] componentesBySolicituds =
		// certificadoGasVo.getComponentesBySolicitud();
		table.setRows(certificadoGasVo.getEnvases(), new RowsProvider() {
			public Object[] getRow(Object o) {
				ListLote listLote = (ListLote) o;
				return new Object[] { listLote.getNumeroEnvase() };
			}
		});
		list.add(table);

	}

	public int calculateCols(int items, int itemsPorCol) {
		int out = 1;

		double divDouble = (double) items / (double) itemsPorCol;
		if (items % itemsPorCol == 0) {
			out = items / itemsPorCol;
		} else {
			out = ((int) divDouble + 1);
		}
		return out;
	}

	private void putTableComponentsBySolicitud(List list) {
		// TABLA CILINDROS
		String cols[] = { "Impurezas", "Especificación", "Unidad" };
		// impurities, requested espec., unit
		Table table = new Table(null, cols);
		table.setWidth(70);
		// final ComponentesBySolicitud[] componentesBySolicituds =
		// certificadoGasVo.getComponentesBySolicitud();
		table.setRows(certificadoGasVo.getComponentesBySolicitud(), new RowsProvider() {
			public Object[] getRow(Object o) {
				ComponentesBySolicitud componentes = (ComponentesBySolicitud) o;
				return new Object[] { componentes.getImpurezas(), componentes.getEspecificacion(), componentes.getUnidad() };
			}
		});
		list.add(table);

	}

	/**
	 * <code>
	private void putEncabezado3(List list) {
		// DATOS DEL PRODUCTO
		Font[] fontGlosa = new Font[] { Resources.fontNormal };
		String[] sGlosa = { "Tipo de Cilindro / " };

		ParagraphCompuesto pGlosa = new ParagraphCompuesto(sGlosa, fontGlosa);
		// pGlosa.setNewLine(false);

		String[] sValue = { "XXXXXXXXXXXXXXXXXXXXX" };
		ParagraphCompuesto pValue = new ParagraphCompuesto(sValue);
		// pValue.setNewLine(true);

		String[] sGlosa2 = { "Presion Llenado / " };
		Font[] fontGlosa2 = new Font[] { Resources.fontNormal };
		ParagraphCompuesto pGlosa2 = new ParagraphCompuesto(sGlosa2, fontGlosa2);
		pGlosa2.setNewLine2(true);

		String[] sValue2 = { "YYYYYYYYYYYYYYYYYYYYYYY" };
		ParagraphCompuesto pValue2 = new ParagraphCompuesto(sValue2);
		// pValue2.setNewLine(true);

		HorizontalTablePanel hGlosa = new HorizontalTablePanel(new PdfComponent[] { pGlosa, pValue, pGlosa2, pValue2 });
		hGlosa.setWidths(new int[] { 25, 25, 25, 25 });
		list.add(hGlosa);
	}
	</code>
	 */

	private void putEncabezado3_old(List list) {
		// DATOS DEL PRODUCTO
		Font[] fontGlosa = getFontParagraph1();
		String[] sGlosa = getTextParagraph1();
		String[] sValue = getDataParagraph1();

		ParagraphCompuesto pGlosa = new ParagraphCompuesto(sGlosa, fontGlosa);
		pGlosa.setNewLine(true);

		// Font[] fontValue1 = getFontData2();
		ParagraphCompuesto pValue = new ParagraphCompuesto(sValue, getFontParagraph4());
		pValue.setNewLine(true);

		Font[] fontGlosa2 = getFontParagraph3();
		String[] sGlosa2 = getTextParagraph2();
		String[] sValue2 = getDataParagraph2();

		ParagraphCompuesto pGlosa2 = new ParagraphCompuesto(sGlosa2, fontGlosa2);
		pGlosa2.setNewLine(true);

		// Font[] fontValue1 = getFontData2();
		ParagraphCompuesto pValue2 = new ParagraphCompuesto(sValue2, getFontParagraph2());
		pValue2.setNewLine(true);

		HorizontalTablePanel hGlosa = new HorizontalTablePanel(new PdfComponent[] { pGlosa, pValue, pGlosa2, pValue2 });
		hGlosa.setWidths(new int[] { 25, 25, 25, 25 });
		list.add(hGlosa);
	}

	private Font[] getFontData2() {
		Font[] fontValue1 = new Font[] { Resources.fontNormal, Resources.fontNormalCompuesto, Resources.fontNormal,
				Resources.fontNormal, Resources.fontNormal, Resources.fontNormal };
		return fontValue1;
	}

	private String[] getDataParagraph3() {
		String[] sValue = { certificadoGasVo.getProducto(), " ", certificadoGasVo.getNumeroLote(), " ",
				certificadoGasVo.getNumeroOrden(), " " };
		return sValue;
	}

	private String[] getDataParagraph2() {
		String[] sValue = { certificadoGasVo.getPresionLlenado() + " Bar", " ", certificadoGasVo.getVolumenGas() + " m3", " ",
				certificadoGasVo.getPresionMinUso() + " Bar", " " };
		return sValue;
	}

	private String[] getDataParagraph1() {
		String[] sValue = { certificadoGasVo.getTipoCilindro(), " ", certificadoGasVo.getConexionValvula(), " ",
				certificadoGasVo.getEstabilidadGarantizada() + " meses", " ", certificadoGasVo.getTemperaturaRecomendada(), " " };
		return sValue;
	}

	private String[] getTextParagraph2() {
		String[] sGlosa = { "Presión de llenado / ", "Fill.preasure (15°): ", "Volumen gas / ", "Gas vol 15°:",
				"Presión mín. uso / ", "Minimum preassure of use:" };
		return sGlosa;
	}

	private String[] getTextParagraph3() {
		String[] sGlosa = { "Código de producto / ", "Product Code", "Nº de Lote / ", "Lote No.", "Nº de Orden / ", "Order No." };
		return sGlosa;
	}

	private String[] getTextParagraph1() {
		String[] sGlosa = { "Tipo de Cilindro / ", "Cylinder Type: ", "Conexión válvula / ", "Volve connection:",
				"Estabilidad garantizada/ ", "Guaranteed Stability:", "Temperatura recomendada / ",
				"Recommended Storage And usage temperature:" };
		return sGlosa;
	}

	private Font[] getFontParagraph4() {
		Font[] fontGlosa = new Font[] { Resources.fontNormal, Resources.fontNormalCompuesto, Resources.fontNormal,
				Resources.fontNormalCompuesto, Resources.fontNormal, Resources.fontNormalCompuesto, Resources.fontNormal,
				Resources.fontNormalCompuesto };
		return fontGlosa;
	}

	private Font[] getFontParagraph3() {
		Font[] fontGlosa = new Font[] { Resources.FONT_TITLE_3, Resources.fontNormalCompuesto, Resources.FONT_TITLE_3,
				Resources.fontNormalCompuesto, Resources.FONT_TITLE_3, Resources.fontNormalCompuesto };
		return fontGlosa;
	}

	private Font[] getFontParagraph2() {
		Font[] fontGlosa = new Font[] { Resources.fontNormal, Resources.fontNormalCompuesto, Resources.fontNormal,
				Resources.fontNormalCompuesto, Resources.fontNormal, Resources.fontNormalCompuesto };
		return fontGlosa;
	}

	private Font[] getFontParagraph1() {
		Font[] fontGlosa = new Font[] { Resources.FONT_TITLE_3, Resources.fontNormalCompuesto, Resources.FONT_TITLE_3,
				Resources.fontNormalCompuesto, Resources.FONT_TITLE_3, Resources.fontNormalCompuesto, Resources.FONT_TITLE_3,
				Resources.fontNormalCompuesto };
		return fontGlosa;
	}

	private void putDateTitle(List list) {
		Title title = new Title(TITLE);
		Paragraph blank = new Paragraph("");

		// FECHA REPORTE Y TITULO

		String[] textFecha = { "Fecha / ", "Date" };
		Font[] fonts = new Font[] { Resources.FONT_TITLE_3, Resources.fontTitle3Compuesto };

		ParagraphCompuesto tituloFecha = new ParagraphCompuesto(textFecha, fonts);
		String fecha = new DateFormatter(DateFormatter.FORMAT_DD_MM_YYYY).format(new Date());
		Paragraph valueFecha = new Paragraph(fecha, Resources.fontNormal);

		HorizontalPanel header = new HorizontalPanel(new PdfComponent[] { blank, tituloFecha });
		HorizontalPanel header2 = new HorizontalPanel(new PdfComponent[] { blank, valueFecha });
		header.setWidths(new int[] { 80, 20 });
		header.setAlignments(new int[] { PdfComponent.ALIGN_LEFT, PdfComponent.ALIGN_RIGHT });
		header2.setAlignments(new int[] { PdfComponent.ALIGN_LEFT, PdfComponent.ALIGN_RIGHT });

		list.add(new Paragraph("\n\n"));
		list.add(header);
		list.add(header2);
		list.add(title);
		list.add(new Paragraph("\n"));
	}

	private void putEncabezado2_new(List list) {
		String[] producto = new String[] { "Nombre del Producto / ", "Product Name: ",
				"\n" + certificadoGasVo.getNombreProducto() };
		Font[] fontProducto = new Font[] { Resources.FONT_TITLE_3, Resources.fontNormalCompuesto, Resources.fontNormal };

		ParagraphCompuesto pProducto = new ParagraphCompuesto(producto, fontProducto);

		Font[] fontGlosa = getFontParagraph3();
		String[] textoGlosa = getTextParagraph3();
		String[] dataValue = getDataParagraph3();

		ParagraphCompuesto pGlosa = new ParagraphCompuesto(textoGlosa, fontGlosa);
		pGlosa.setNewLine(true);

		// Font[] fontValue1 = getFontData2();
		ParagraphCompuesto pValue = new ParagraphCompuesto(dataValue, getFontParagraph2());
		pValue.setNewLine(true);

		HorizontalTablePanel hGlosa = new HorizontalTablePanel(new PdfComponent[] { pProducto, pGlosa, pValue });
		hGlosa.setWidths(new int[] { 50, 25, 25 });
		list.add(hGlosa);
	}

	public void setValue(Object o) {
		certificadoGasVo = (CertificadoGasVo) o;
	}

	public static void main(String[] args) throws Exception {
		File f = new File("c:/miguel/reporteAnaliticoGas.pdf");
		PdfDocument test = new CertificadoGasPdf();
		CertificadoGasVo o = new CertificadoGasVo();

		ComponentesBySolicitud[] componentesBySolicituds = new ComponentesBySolicitud[1];
		componentesBySolicituds[0] = new ComponentesBySolicitud();

		ListLote[] listLotes = new ListLote[25];
		for (int i = 0; i < listLotes.length; i++) {
			listLotes[i] = new ListLote();
		}

		o.setComponentesBySolicitud(componentesBySolicituds);
		o.setEnvases(listLotes);

		test.setValue(o);
		// test.setWaterMark("Mi Borrador");
		test.create(f);

	}

}
