/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.builderSoft.utilpdf;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import cl.builderSoft.utilpdf.components.BoxComponent;
import cl.builderSoft.utilpdf.components.BoxTableComponent;
import cl.builderSoft.utilpdf.components.FieldTableSimple;
import cl.builderSoft.utilpdf.components.HorizontalPanel;
import cl.builderSoft.utilpdf.components.Image;
import cl.builderSoft.utilpdf.components.Paragraph;
import cl.builderSoft.utilpdf.components.Signature;
import cl.builderSoft.utilpdf.components.Title;

/**
 *
 * @author mmanriquez_ex
 */
public class PdfComprobanteAtencion_ extends PdfDocument {

    String titulo = "COMPROBANTE DE ATENCIÓN AL CLIENTE";
    private static final String TAB = "    ";

//    public PdfComprobanteAtencion() {
//        setWaterMark("Copia No Válida");
//    }

    class ComprobanteCliente{
    	
    	private List<String> archivos = new ArrayList<String>();
    	
    	private String folio = "1234";
    	private String nombreSanitaria = "";
    	private Date fechaRecepcion = new Date();
    	private String nombreCliente = "";
    	private String telefono = "";
    	private String direccion = "";
    	private String villaPobla = "";
    	private String comuna = "";
    	private String nombreIndustria = "";
    	private String patrocinante = "";
    	private String origenAtencion = "";
    	private String tipoAtencion = "";
    	private String clasificacion = "";
    	private String motivo = "";
    	private String detalleAtencion = "";
    	private String analista = "";
    	private String nota = "";
    	private String numeroCliente = "";
    	
		public String getNumeroCliente() {
			return numeroCliente;
		}
		public void setNumeroCliente(String numeroCliente) {
			this.numeroCliente = numeroCliente;
		}
		public String getDetalleAtencion() {
			return detalleAtencion;
		}
		public void setDetalleAtencion(String detalleAtencion) {
			this.detalleAtencion = detalleAtencion;
		}
		public String getAnalista() {
			return analista;
		}
		public void setAnalista(String analista) {
			this.analista = analista;
		}
		public String getNota() {
			return nota;
		}
		public void setNota(String nota) {
			this.nota = nota;
		}
		public List<String> getArchivos() {
			return archivos;
		}
		public void setArchivos(List<String> archivos) {
			this.archivos = archivos;
		}
		public String getFolio() {
			return folio;
		}
		public void setFolio(String folio) {
			this.folio = folio;
		}
		public String getNombreSanitaria() {
			return nombreSanitaria;
		}
		public void setNombreSanitaria(String nombreSanitaria) {
			this.nombreSanitaria = nombreSanitaria;
		}
		public Date getFechaRecepcion() {
			return fechaRecepcion;
		}
		public void setFechaRecepcion(Date fechaRecepcion) {
			this.fechaRecepcion = fechaRecepcion;
		}
		public String getNombreCliente() {
			return nombreCliente;
		}
		public void setNombreCliente(String nombreCliente) {
			this.nombreCliente = nombreCliente;
		}
		public String getTelefono() {
			return telefono;
		}
		public void setTelefono(String telefono) {
			this.telefono = telefono;
		}
		public String getDireccion() {
			return direccion;
		}
		public void setDireccion(String direccion) {
			this.direccion = direccion;
		}
		public String getVillaPobla() {
			return villaPobla;
		}
		public void setVillaPobla(String villaPobla) {
			this.villaPobla = villaPobla;
		}
		public String getComuna() {
			return comuna;
		}
		public void setComuna(String comuna) {
			this.comuna = comuna;
		}
		public String getNombreIndustria() {
			return nombreIndustria;
		}
		public void setNombreIndustria(String nombreIndustria) {
			this.nombreIndustria = nombreIndustria;
		}
		public String getPatrocinante() {
			return patrocinante;
		}
		public void setPatrocinante(String patrocinante) {
			this.patrocinante = patrocinante;
		}
		public String getOrigenAtencion() {
			return origenAtencion;
		}
		public void setOrigenAtencion(String origenAtencion) {
			this.origenAtencion = origenAtencion;
		}
		public String getTipoAtencion() {
			return tipoAtencion;
		}
		public void setTipoAtencion(String tipoAtencion) {
			this.tipoAtencion = tipoAtencion;
		}
		public String getClasificacion() {
			return clasificacion;
		}
		public void setClasificacion(String clasificacion) {
			this.clasificacion = clasificacion;
		}
		public String getMotivo() {
			return motivo;
		}
		public void setMotivo(String motivo) {
			this.motivo = motivo;
		}
    	
    	
    }
    
    ComprobanteCliente atencion = new ComprobanteCliente();

    @Override
    public void addElements(List list) throws Exception {

        Image image = new Image("logoSiss.jpg");
        image.setPosition(0, 0);
        image.setSize(250, 250);

        Title title = new Title(titulo);
        Paragraph folio = new Paragraph( "Folio N° " + atencion.getFolio(), Resources.FONT_TITLE_3);
        Paragraph descripcion = new Paragraph("REPUBLICA DE CHILE\nSUPERINTENDENCIA DE SERVICIOS SANITARIOS\nUNIDAD DE ATENCIÓN CLIENTES E INFORMACIONES");
        descripcion.setFont(Resources.fontSmallBold);

        HorizontalPanel header = new HorizontalPanel(new PdfComponent[]{image, folio});
        header.setWidths(new int[]{80, 20});
        header.setAlignments(new int[]{
                    PdfComponent.ALIGN_LEFT, PdfComponent.ALIGN_RIGHT});

        list.add(new Paragraph("\n\n"));
        list.add(header);
        list.add(title);
        list.add(new Paragraph("\n"));

        String headerSanitaria = "Empresa Sanitaria";
        if(atencion.getNombreSanitaria() == null)
            headerSanitaria = "Industria";

        List labelsSolicitud = new Vector();
        labelsSolicitud.add(new String[]{"Fecha Atención", ""});
        labelsSolicitud.add(new String[]{"Nombre del Cliente", "Teléfono"});
        labelsSolicitud.add(new String[]{"Dirección", "Pobl./Villa"});
        labelsSolicitud.add(new String[]{"Comuna", ""});
        labelsSolicitud.add(new String[]{headerSanitaria, atencion.getNombreSanitaria() != null ? "Nº Servicio/Cliente" : ""});
        labelsSolicitud.add(new String[]{"Patrocinante", ""});
//        labelsSolicitud.add(new String[]{"Tipo de documento", "Fecha Documento"});

        String values[] = {
            atencion.getFechaRecepcion().toLocaleString(), "",
            atencion.getNombreCliente(), atencion.getTelefono(),
            atencion.getDireccion(), atencion.getVillaPobla(),
            atencion.getComuna(), "",
            atencion.getNombreSanitaria() == null ? atencion.getNombreIndustria() : atencion.getNombreSanitaria(),  atencion.getNombreSanitaria() != null ? atencion.getNumeroCliente() : "",
            atencion.getPatrocinante(), ""
//            atencion.getTipoAtencion(), ""
        };

        FieldTableSimple fieldTableSimple = new FieldTableSimple(labelsSolicitud, values);
        fieldTableSimple.setWidths(new int[]{20, 40, 15, 25});
        fieldTableSimple.setWidth(100);
        list.add(fieldTableSimple);

        List labelOrigenes = new Vector();
        labelOrigenes.add(new String[]{"Origen de Atención", "Tipo de Atención"});
        labelOrigenes.add(new String[]{"Clasificación", "Motivo"});
        String[] valueOrigenes = new String[]{
            atencion.getOrigenAtencion(), atencion.getTipoAtencion(),
            atencion.getClasificacion(), atencion.getMotivo()
        };

        fieldTableSimple = new FieldTableSimple(labelOrigenes, valueOrigenes);
        fieldTableSimple.setWidths(new int[]{20, 40, 15, 25});
        fieldTableSimple.setWidth(100);
        list.add(fieldTableSimple);


        Paragraph motivo = new Paragraph(atencion.getDetalleAtencion(), Resources.fontSmall);
        BoxTableComponent boxComponent = new BoxTableComponent(new PdfComponent[]{motivo});
        list.add(boxComponent);
        list.add(new Paragraph("\n"));

        Paragraph datosAdjuntos = new Paragraph("Datos Adjuntos:");
        datosAdjuntos.setFont(Resources.fontBold);
        datosAdjuntos.setAlignment(PdfComponent.ALIGN_LEFT);
        list.add(datosAdjuntos);

        List<String> listAdjuntos = atencion.getArchivos();
        String string = "";
        
        for (int i = 0; i < listAdjuntos.size(); i++) {
            string+= listAdjuntos.get(i);
        }

        String boleta = string.contains("BOLETA") ? "Boleta:( X )" : "Boleta:(  )";
        String carta = string.contains("CARTA") ? "Carta:( X )" : "Carta:(  )";
        String documento = string.contains("DOCUMENTO") ? "Documento:( X )" : "Documento:(  )";
        String fotografia = string.contains("FOTOGRAFIA") ? "Fotografía:( X )" : "Fotografía:(  )";
        
        PdfComponent[] components = new PdfComponent[]{new Paragraph( boleta + TAB + carta + TAB + documento + TAB + fotografia + TAB )};
        BoxTableComponent bc = new BoxTableComponent(components);
        list.add(bc);

        BoxTableComponent bc2 = new BoxTableComponent(new PdfComponent[]{new Paragraph(""), new Paragraph("\n")});
        list.add(bc2);

        String lines[] = {atencion.getNombreCliente(), "Cliente"};
        Signature signature1 = new Signature(lines);
        signature1.setWidth(80);

        String lines2[] = {atencion.getAnalista(), "Analista"};
        Signature signature2 = new Signature(lines2);
        signature2.setWidth(40);

        HorizontalPanel panel = new HorizontalPanel();
        panel.setComponents(new PdfComponent[]{signature1, signature2});
        panel.setWidths(new int[]{40, 60});

        list.add(new Paragraph("\n\n\n\n\n"));
        list.add(panel);
        list.add(new Paragraph("\n\n\n\n"));

        Paragraph nota = new Paragraph("NOTA: " + atencion.getNota(), Resources.fontBold);
        BoxComponent boxComponent1 = new BoxComponent(new PdfComponent[]{nota});
        list.add(boxComponent1);

    }

    public static void main(String args[]) throws Exception {
        File f = new File("c:\\comprobanteAtencion.pdf");
        PdfDocument test = new PdfComprobanteAtencion_();
        test.setWaterMark("Mi Borrador");
//        test.setValue("Prueba de documento");
        test.create(f);
//		Runtime.getRuntime().exec(new String[] {"c:\\", "c:\\test.pdf"});
    }

    @Override
    public void setValue(Object o) {
        atencion = (ComprobanteCliente)o;
    }
}
