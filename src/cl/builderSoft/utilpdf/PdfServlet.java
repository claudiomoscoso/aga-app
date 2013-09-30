package cl.builderSoft.utilpdf;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.Element;

import cl.builderSoft.aga.certificate.pdf.CertificadoMezclaPdf;
import cl.builderSoft.aga.certificate.vo.CertificadoMezclaVo;
import cl.builderSoft.aga.certificate.vo.Componentes;
import cl.builderSoft.framework.database.BSConnectionManager;
import cl.builderSoft.framework.database.BSExecutor;
import cl.builderSoft.framework.exception.BSException;
import cl.builderSoft.framework.log.BSLog;
import cl.builderSoft.framework.util.BSFactory;
import cl.builderSoft.framework.util.BSStaticManager;
import cl.builderSoft.util.AgaConstant;

public class PdfServlet extends HttpServlet {
	private static final long serialVersionUID = -6951081010493403112L;
	public static final String LUGAR_PREPARACION = "PLANTA GASES ESPECIALES";

	protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		String idSolicitud = null;
		byte pdf[];
		try {
			idSolicitud = request.getParameter("id");
			CertificadoMezclaVo certificadoMezclaVo = getValueObject(idSolicitud);

			CertificadoMezclaPdf certificadoMezclaPdf = new CertificadoMezclaPdf();
			certificadoMezclaPdf.setValue(certificadoMezclaVo);

			pdf = certificadoMezclaPdf.create();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e.getMessage());
		}

		baos.write(pdf, 0, pdf.length);

		response.setHeader("Expires", "0");
		response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
		response.setHeader("Pragma", "public");
		response.setHeader("Content-Disposition", "attachment; filename=\"solicitud-" + idSolicitud.trim() + ".pdf\"");
		// setting the content type
		response.setContentType("application/pdf");
		// the contentlength is needed for MSIE!!!
		// System.out.println(baos.size());
		response.setContentLength(baos.size());
		// write ByteArrayOutputStream to the ServletOutputStream
		ServletOutputStream out = response.getOutputStream();
		baos.writeTo(out);
		out.flush();
		out.close();
	}

	private CertificadoMezclaVo getValueObject(String idSolicitud) throws BSException {
		BSConnectionManager connectionManager = BSFactory.getConnectionManager();
		Connection conn = connectionManager.getConnection(AgaConstant.SLC);
		BSExecutor exec = BSFactory.getExecutor(AgaConstant.SLC);

		String nodeName = "PdfGenerator";
		Document solicitudDocument = exec.querySP(conn, "pGetSolicitud", new String[] { idSolicitud }, nodeName);
		exec.closeStatement();

		Document componentesDocument = exec.querySP(conn, "pGetComponentesSolicitudPdf", new String[] { idSolicitud }, nodeName);
		exec.closeStatement();

		List componentes = componentesDocument.selectNodes("/PdfGenerator/Record");

		CertificadoMezclaVo certificadoVo = new CertificadoMezclaVo(componentes.size());

		// System.out.println(document.asXML());
		// PARSEO DATA DESDE XML
		// TODO:miguel: falta numero de cilindro

		Element solicitudElement = (Element) solicitudDocument.selectSingleNode("/" + nodeName + "/Record");
		setCertificadoValues(idSolicitud, conn, exec, certificadoVo, solicitudElement);

		setComponentsValues(componentes, certificadoVo);

		return certificadoVo;

	}

	Element componenteElement = null;

	private void setComponentsValues(List componentesList, CertificadoMezclaVo certificadoVo) {
		Componentes componenteVo = null;
		double desviacionRelativa = 0;
		double analisis = 0;

		int size = componentesList.size();
		for (int i = 0; i < size; i++) {
			componenteVo = new Componentes(); // (Element) componentes.get(i);
			componenteElement = (Element) componentesList.get(i);

			componenteVo.setComponente(componenteElement.element("cNombre").getTextTrim());
			componenteVo.setSigla(componenteElement.element("cSigla").getTextTrim());
			if (i < size - 1) {
				componenteVo.setComposicion(componenteElement.element("cRequerido").getTextTrim());
				componenteVo.setAnalisis(componenteElement.element("cAnalisis").getTextTrim());
				componenteVo.setUnidad(componenteElement.element("cUnidad").getTextTrim());

				desviacionRelativa = toNumber(componenteElement.element("cDesviacionRelativa").getTextTrim());
				analisis = toNumber(componenteElement.element("cAnalisis").getTextTrim());
				/**
				 * <code>
				  componenteVo.setDesviacionAbsoluta("+/- " + ((desviacionRelativa / 100) * analisis) + "ppm");
				 * </code>
				 */
				String n = "" + ((desviacionRelativa / 100) * analisis);
				componenteVo.setDesviacionAbsoluta("+/- " + Resources.formatNumber(n) + " " + componenteVo.getUnidad());

				// (cDesviacionRelativa div 100) * cAnalisis

				double x = toNumber(componenteElement.element("cDesviacionRelativa").getTextTrim());

				componenteVo.setDesviacionRelativa("+/- " + componenteElement.element("cDesviacionRelativa").getTextTrim());
			} else {
				componenteVo.setComposicion(AgaConstant.BALANCE);
			}
			certificadoVo.setComponentes(i, componenteVo);
		}
	}

	private double toNumber(String s) {
		double out = 0;
		try {
			out = Double.parseDouble(s);
		} catch (Exception e) {
			BSLog.debug("Can't parse [" + s + "] numbre");
		}
		return out;
	}

	private void setCertificadoValues(String idSolicitud, Connection conn, BSExecutor exec, CertificadoMezclaVo certificadoVo,
			Element solicitudElement) throws BSException {
		certificadoVo.setCliente(solicitudElement.elementTextTrim("cNombreCliente"));
		certificadoVo.setNroAnalisis(solicitudElement.elementTextTrim("cAnalisis"));
		certificadoVo.setNroCilindro(getOneCilindroNumber(conn, exec, idSolicitud));
		certificadoVo.setNroOrden(solicitudElement.elementTextTrim("cOrden"));
		certificadoVo.setTipoCilindro(solicitudElement.elementTextTrim("cNombreTipoCilindro") + "-"
				+ solicitudElement.elementTextTrim("cLitros") + "lts");
		certificadoVo.setConexionValvula(getNombreValvula(conn, exec, solicitudElement.elementTextTrim("cValvula")));
		certificadoVo.setPresionLlenado(solicitudElement.elementTextTrim("cPoLlenado"));
		certificadoVo.setVolumenGas(formatToOneDecimal(solicitudElement.elementTextTrim("volumenGas")));
		certificadoVo.setTipoProducto(solicitudElement.elementTextTrim("nombreTProducto"));
		certificadoVo.setMetodoPreparacion(solicitudElement.elementTextTrim("cMetodoPreparacion"));
		certificadoVo.setNivelConfianza(solicitudElement.elementTextTrim("cNivelConfianza"));
		certificadoVo.setToleranciaPreparacion(solicitudElement.elementTextTrim("cToleranciaPreparacion"));
		certificadoVo.setEstabilidadGarantizada(solicitudElement.elementTextTrim("cEstabilidadGarantizada"));

		// certificadoVo.setTemperaturaRecomendada(solicitudElement.elementTextTrim("cTemperaturaRecomendada"));
		certificadoVo.setTemperaturaRecomendada(solicitudElement.elementTextTrim("cTMinUso"));

		certificadoVo.setMetodoAnalitico(getMetodosAnaliticos(conn, exec, idSolicitud));
		// certificadoVo.setPresionMinimaUso(solicitudElement.elementTextTrim("cPresionMinima"));
		certificadoVo.setPresionMinimaUso(solicitudElement.elementTextTrim("cPMinUso"));
		certificadoVo.setPatronEmpleado(getPatronEmpeado(conn, exec, idSolicitud));
		certificadoVo.setCodigoMezcla(solicitudElement.elementTextTrim("cCodigoProducto"));
		certificadoVo.setNombreMezcla(solicitudElement.elementTextTrim("nombreProducto"));
		certificadoVo.setComentario(solicitudElement.elementTextTrim("cComentario"));
		certificadoVo.setLugarProduccion(LUGAR_PREPARACION);
		certificadoVo.setResponsable(solicitudElement.elementTextTrim("nombreResponsable"));

		certificadoVo.setContacto(solicitudElement.elementTextTrim("cContacto"));
	}

	private String formatToOneDecimal(String numberString) {
		String out = null;
		try {
			double numberDouble = Double.parseDouble(numberString);
			NumberFormat formatter = new DecimalFormat("0.0");
			out = formatter.format(numberDouble);
		} catch (Exception e) {
			out = "0.0";
		}
		return out;
	}

	protected String getOneCilindroNumber(Connection conn, BSExecutor exec, String idSolicitud) throws BSException {
		String sql = "pListLote";
		Document document = exec.querySP(conn, sql, BSStaticManager.buildArrayObject(idSolicitud), "Lote");
		String out = "";

		Element record = document.getRootElement().element("Record");
		if (record != null) {
			out = record.element("cCilindro").getTextTrim();
			record.clearContent();
		}

		document.clearContent();
		document = null;
		record = null;
		return out;
	}

	private String getNombreValvula(Connection conn, BSExecutor exec, String valvulaCode) throws BSException {
		String sql = "pGetValvula";
		Document document = exec.querySP(conn, sql, BSStaticManager.buildArrayObject(valvulaCode), "Valvula");

		String out = document.getRootElement().element("Record").element("cNombre").getTextTrim();
		document.clearContent();
		document = null;
		return out;
	}

	private String getPatronEmpeado(Connection conn, BSExecutor exec, String idSolicitud) throws BSException {
		String sql = "pListPatronOfSolicitud";
		Document patronsDocument = exec.querySP(conn, sql, BSStaticManager.buildArrayObject(idSolicitud), "Patrons");
		// BSLog.debug(patrons.asXML());

		String out = "Patrón ";
		List patronsList = patronsDocument.getRootElement().elements("Record");
		for (int i = 0; i < patronsList.size(); i++) {
			Element recordElement = (Element) patronsList.get(i);
			out += "N°" + recordElement.elementTextTrim("cNumero") + (i + 1 == patronsList.size() ? "" : ", ");
		}

		return out; // "Patrón N° " + "" + ", N° " + "";
	}

	private String getMetodosAnaliticos(Connection conn, BSExecutor exec, String idSolicitud) throws BSException {
		Document componentesDocument = exec.querySP(conn, "pGetMetodosSolicitud", new String[] { idSolicitud }, "PdfGenerator");
		exec.closeStatement();

		List metodos = componentesDocument.selectNodes("/PdfGenerator/Record");

		StringBuffer buffer = new StringBuffer();

		for (int i = 0; i < metodos.size(); i++) {
			if (i > 0)
				buffer.append(", ");

			buffer.append(((Element) metodos.get(i)).elementTextTrim("cNombre"));
		}

		return buffer.toString();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
}
