package cl.builderSoft.utilpdf;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.Element;

import cl.builderSoft.aga.certificate.pdf.CertificadoGasPdf;
import cl.builderSoft.aga.certificate.vo.CertificadoGasVo;
import cl.builderSoft.aga.certificate.vo.ComponentesBySolicitud;
import cl.builderSoft.aga.certificate.vo.ListLote;
import cl.builderSoft.framework.database.BSConnectionManager;
import cl.builderSoft.framework.database.BSExecutor;
import cl.builderSoft.framework.exception.BSException;
import cl.builderSoft.framework.util.BSFactory;
import cl.builderSoft.util.AgaConstant;

public class PdfServletGas extends PdfServlet {
	private static final long serialVersionUID = -4345221566357972982L;
	public static final String LUGAR_PREPARACION = "PLANTA GASES ESPECIALES";

	protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		String idSolicitud = null;
		byte pdf[];
		try {
			idSolicitud = request.getParameter("id");
			CertificadoGasVo certificadoGasVo = getValueObject(idSolicitud);

			CertificadoGasPdf certificadoGasPdf = new CertificadoGasPdf();
			certificadoGasPdf.setValue(certificadoGasVo);

			pdf = certificadoGasPdf.create();
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

	private CertificadoGasVo getValueObject(String idSolicitud) throws BSException {
		BSConnectionManager connectionManager = BSFactory.getConnectionManager();
		Connection conn = connectionManager.getConnection(AgaConstant.SLC);
		BSExecutor exec = BSFactory.getExecutor(AgaConstant.SLC);
		CertificadoGasVo certificadoVo = new CertificadoGasVo();

		// detalle solicitud
		String nodeName = "PdfGenerator";
		Document solicitudDocument = exec.querySP(conn, "pGetSolicitud", new String[] { idSolicitud }, nodeName);
		exec.closeStatement();

		Element solicitudElement = (Element) solicitudDocument.selectSingleNode("/" + nodeName + "/Record");
		setCertificadoValues(idSolicitud, conn, exec, certificadoVo, solicitudElement);

		// componentes solicitud
		Document componentesDocument = exec.querySP(conn, "pListComponentBySolicitud", new String[] { idSolicitud }, nodeName);
		exec.closeStatement();

		List componentes = componentesDocument.selectNodes("/PdfGenerator/Record");

		setComponentsValues(componentes, certificadoVo);

		// envases solicitud
		Document envasesDocument = exec.querySP(conn, "pListLote", new String[] { idSolicitud }, nodeName);
		exec.closeStatement();

		List envases = envasesDocument.selectNodes("/PdfGenerator/Record");

		setEnvasesValues(envases, certificadoVo);

		return certificadoVo;

	}

	private void setEnvasesValues(List envasesList, CertificadoGasVo certificadoVo) {
		Element envaseElement = null;
		int size = envasesList.size();
		ListLote[] listLotes = new ListLote[size];

		for (int i = 0; i < size; i++) {
			ListLote listLoteVo = new ListLote();
			envaseElement = (Element) envasesList.get(i);

			listLoteVo.setNumeroEnvase(envaseElement.element("cCilindro").getTextTrim());

			listLotes[i] = listLoteVo;
		}

		certificadoVo.setEnvases(listLotes);
	}

	private void setComponentsValues(List componentesList, CertificadoGasVo certificadoVo) {
		Element componenteElement = null;
		int size = componentesList.size();
		ComponentesBySolicitud[] componenteVos = new ComponentesBySolicitud[size];

		for (int i = 0; i < size; i++) {
			ComponentesBySolicitud componenteVo = new ComponentesBySolicitud();
			componenteElement = (Element) componentesList.get(i);

			componenteVo.setImpurezas(componenteElement.element("cSigla").getTextTrim());
			componenteVo.setEspecificacion(componenteElement.element("cRango").getTextTrim());
			componenteVo.setUnidad(componenteElement.element("cUnidad").getTextTrim());

			componenteVos[i] = componenteVo;
		}

		certificadoVo.setComponentesBySolicitudes(componenteVos);
	}

	private void setCertificadoValues(String idSolicitud, Connection conn, BSExecutor exec, CertificadoGasVo certificadoVo,
			Element solicitudElement) throws BSException {
		// BSLog.debug(solicitudElement.asXML());

		certificadoVo.setNombreProducto(solicitudElement.element("nombreProducto").getTextTrim());
		certificadoVo.setProducto(solicitudElement.element("cCodigoProducto").getTextTrim());
		certificadoVo.setNumeroLote(super.getOneCilindroNumber(conn, exec, idSolicitud));
		certificadoVo.setNumeroOrden(solicitudElement.element("cOrden").getTextTrim());
		certificadoVo.setTipoCilindro(solicitudElement.element("cNombreTipoCilindro").getTextTrim());
		certificadoVo.setConexionValvula(solicitudElement.element("cNombreValvula").getTextTrim());
		certificadoVo.setEstabilidadGarantizada(solicitudElement.element("cEstabilidadGarantizada").getTextTrim());
		certificadoVo.setTemperaturaRecomendada("-10 A / to 35°C");
		certificadoVo.setPresionLlenado(solicitudElement.element("cPoLlenado").getTextTrim());
		certificadoVo.setVolumenGas(solicitudElement.element("volumenGas").getTextTrim());
		certificadoVo.setPresionMinUso(solicitudElement.element("cPMinUso").getTextTrim());
		certificadoVo.setComentarios(solicitudElement.element("cComentario").getTextTrim());
		certificadoVo.setMetodosAnalisis(getMetodosAnaliticos(conn, exec, idSolicitud));
		String numeroLote = solicitudElement.element("cLoteAGA").getTextTrim();
		certificadoVo.setNumeroLote(numeroLote.length() < 9 ? numeroLote : numeroLote.substring(numeroLote.length() - 9,
				numeroLote.length()));

		/**
		 * <code>
		certificadoVo.setCliente(solicitudElement.elementTextTrim("cNombreCliente"));
		certificadoVo.setNroAnalisis(solicitudElement.elementTextTrim("cAnalisis"));
		certificadoVo.setNroCilindro(solicitudElement.elementTextTrim("cNumeroCilindro"));
		certificadoVo.setNroOrden(solicitudElement.elementTextTrim("cOrden"));
		certificadoVo.setTipoCilindro(solicitudElement.elementTextTrim("cNombreTipoCilindro"));
		certificadoVo.setConexionValvula(solicitudElement.elementTextTrim("cValvula"));
		certificadoVo.setPresionLlenado(solicitudElement.elementTextTrim("cPoLlenado"));
		certificadoVo.setVolumenGas(solicitudElement.elementTextTrim("volumenGas"));
		certificadoVo.setTipoProducto(solicitudElement.elementTextTrim("nombreTProducto"));
		certificadoVo.setMetodoPreparacion(getMetodosPreparacion(conn, exec, idSolicitud));
		certificadoVo.setNivelConfianza(solicitudElement.elementTextTrim("cNivelConfianza"));
		certificadoVo.setToleranciaPreparacion(solicitudElement.elementTextTrim("cToleranciaPreparacion"));
		certificadoVo.setEstabilidadGarantizada(solicitudElement.elementTextTrim("cEstabilidadGarantizada"));
		certificadoVo.setTemperaturaRecomendada(solicitudElement.elementTextTrim("cTemperaturaRecomendada"));
		certificadoVo.setMetodoAnalitico("metodoAnalitico");
		certificadoVo.setPresionMinimaUso(solicitudElement.elementTextTrim("cPresionMinima"));
		certificadoVo.setPatronEmpleado(getPatronEmpeado(solicitudElement));
		certificadoVo.setCodigoMezcla(solicitudElement.elementTextTrim("cProducto"));
		certificadoVo.setNombreMezcla(solicitudElement.elementTextTrim("nombreProducto"));
		certificadoVo.setComentario(solicitudElement.elementTextTrim("cComentario"));
		certificadoVo.setLugarProduccion(LUGAR_PREPARACION);
		</code>
		 */
		certificadoVo.setResponsable(solicitudElement.elementTextTrim("nombreResponsable"));
		certificadoVo.setCertificado(solicitudElement.elementTextTrim("cCertificado"));

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

	private String getPatronEmpeado(Element solicitudElement) {

		String compuesto = solicitudElement.elementTextTrim("cCompuesto");
		String composicion = solicitudElement.elementTextTrim("cComposicion");

		return "Patrón N° " + compuesto + ", N° " + composicion;
	}

	private String getMetodosPreparacion(Connection conn, BSExecutor exec, String idSolicitud) throws BSException {

		Document componentesDocument = exec.querySP(conn, "pGetMetodosSolicitud", new String[] { idSolicitud }, "PdfGenerator");
		exec.closeStatement();

		List metodos = componentesDocument.selectNodes("/PdfGenerator/Record");

		StringBuffer buffer = new StringBuffer();

		for (int i = 0; i < metodos.size(); i++) {
			buffer.append(metodos.get(i));
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
