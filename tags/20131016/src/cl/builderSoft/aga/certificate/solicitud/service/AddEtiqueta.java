package cl.builderSoft.aga.certificate.solicitud.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;

import cl.builderSoft.aga.AGAAbstract;
import cl.builderSoft.framework.database.BSExecutor;
import cl.builderSoft.framework.exception.BSDataBaseException;
import cl.builderSoft.framework.exception.BSException;
import cl.builderSoft.framework.exception.BSProgrammerException;
import cl.builderSoft.framework.mvc.ioService.BSServiceData;
import cl.builderSoft.framework.service.BSProcess;
import cl.builderSoft.framework.util.BSConfigManager;
import cl.builderSoft.framework.util.BSFactory;
import cl.builderSoft.framework.util.BSStaticManager;
import cl.builderSoft.util.access.AccessJDBCUtil;
import cl.builderSoft.utilpdf.Resources;

public class AddEtiqueta extends AGAAbstract implements BSProcess {
	private static String SLC = "SLC";

	public BSServiceData execute(BSServiceData serviceData) throws BSException {
		BSServiceData out = null;
		String mdbPath = BSConfigManager.getInstance().getProperty("AGA", "/Module/MDB", "");
		String user = BSConfigManager.getInstance().getProperty("AGA", "/Module/MDB-user", "");
		String password = BSConfigManager.getInstance().getProperty("AGA", "/Module/MDB-password", "");

		Connection conn = null;
		try {
			conn = AccessJDBCUtil.getAccessDBConnection(mdbPath, user, password);
			out = executeTransaction(serviceData, conn);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new BSDataBaseException("" + e.getErrorCode());
		} finally {
			if (conn != null) {
				try {
					if (!conn.isClosed()) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
					throw new BSDataBaseException("Closing mdb file " + e.getErrorCode());
				} finally {
					conn = null;
				}
			}
		}
		return out;
	}

	public BSServiceData executeTransaction(BSServiceData serviceData, Connection connMDB) throws BSException {
		String id = serviceData.getRequestFieldString("Numero", null);
		BSExecutor execMDB = BSFactory.getExecutor("MDB");

		Connection connSol = BSFactory.getConnectionManager().getConnection(SLC);
		BSExecutor execSol = BSFactory.getExecutor(SLC);

		Document solicitudDoc = getSolicitud(connSol, execSol, id);

		// BSLog.debug(solicitudDoc.asXML());

		String sql = "DELETE FROM etiqueta;";
		execMDB.update(connMDB, sql, null);
		execMDB.closeStatement();

		sql = "INSERT INTO etiqueta(ananro) VALUES(0);";

		execMDB.update(connMDB, sql, null);
		execMDB.closeStatement();

		Element recordElement = solicitudDoc.getRootElement().element("Record");

		cabeceraEtiqueta(connMDB, execMDB, sql, recordElement);

		Document componentsDocument = getComponentes(connSol, execSol, id);
		List componentsList = componentsDocument.getRootElement().elements("Record");

		// BSLog.debug(componentsDocument.asXML());

		for (int i = 0; i < componentsList.size() && i <= 7; i++) {
			executeSerie(connMDB, execMDB, componentsList, i);
		}

		detalle1Tecnico(connMDB, id, execMDB, connSol, execSol, recordElement);
		detalle2Tecnico(connMDB, id, execMDB, connSol, execSol, recordElement);

		return null;
	}

	private void detalle2Tecnico(Connection connMDB, String id, BSExecutor execMDB, Connection connSol, BSExecutor execSol,
			Element recordElement) throws BSException {
		String sql = null;
		Object[] prms;
		int j;
		sql = "UPDATE etiqueta SET anapatr=?, anacomen=?, observ=?, anacod=?, ananom=?, anaqco=?;";

		j = 0;
		prms = createEmptyString(6);
		prms[j++] = getPatronsOfSolicitud(connSol, execSol, id);
		prms[j++] = recordElement.element("cComentario").getTextTrim();
		prms[j++] = "."; // recordElement.element("cNivelConfianza").getTextTrim();
		prms[j++] = recordElement.element("cCodigoProducto").getTextTrim();
		prms[j++] = recordElement.element("nombreProducto").getTextTrim();
		prms[j++] = recordElement.element("nombreResponsable").getTextTrim();

		noEmptyStringInArray(prms);
		execMDB.update(connMDB, sql, prms);
		execMDB.closeStatement();

	}

	private void cabeceraEtiqueta(Connection connMDB, BSExecutor execMDB, String sql, Element recordElement) throws BSException {
		Object[] prms = createEmptyString(10);
		sql = "UPDATE etiqueta SET "
				+ "anafec=?, anacli=?, ananro=?, analote=?, anacon=?, anaord=?, anaval=?, anapre=?, anavol=?, anacil=?;";

		int j = 0;
		prms[j++] = recordElement.element("cFechaCreacion").getTextTrim();
		prms[j++] = recordElement.element("cNombreCliente").getTextTrim();
		prms[j++] = recordElement.element("cAnalisis").getTextTrim();
		prms[j++] = recordElement.element("cLoteAGA").getTextTrim();
		prms[j++] = recordElement.element("cContacto").getTextTrim();
		prms[j++] = recordElement.element("cOrden").getTextTrim();
		prms[j++] = recordElement.element("cNombreValvula").getTextTrim();
		prms[j++] = recordElement.element("cPoLlenado").getTextTrim();

		prms[j++] = this.formatToOneDecimal(recordElement.element("volumenGas").getTextTrim());

		prms[j++] = recordElement.element("cNombreTipoCilindro").getTextTrim() + "-"
				+ recordElement.element("cLitros").getTextTrim();

		noEmptyStringInArray(prms);

		execMDB.update(connMDB, sql, prms);
		execMDB.closeStatement();
	}

	private void detalle1Tecnico(Connection connMDB, String id, BSExecutor execMDB, Connection connSol, BSExecutor execSol,
			Element recordElement) throws BSException {
		String sql;
		Object[] prms;
		int j;
		sql = "UPDATE etiqueta SET " + "anamed=?, anaglosa=?, anaconf=?, anatol=?, anaexp=?, "
				+ "anatmin=?, anapuso=?, anamet=?;";

		j = 0;
		prms = createEmptyString(8);
		prms[j++] = recordElement.element("nombreTProducto").getTextTrim();
		prms[j++] = recordElement.element("cMetodoPreparacion").getTextTrim();
		prms[j++] = recordElement.element("cNivelConfianza").getTextTrim();
		prms[j++] = recordElement.element("cToleranciaPreparacion").getTextTrim();
		prms[j++] = recordElement.element("cEstabilidadGarantizada").getTextTrim();
		prms[j++] = recordElement.element("cTMinUso").getTextTrim();
		prms[j++] = recordElement.element("cPMinUso").getTextTrim();
		prms[j++] = getMetodosOfSolicitud(connSol, execSol, id);

		noEmptyStringInArray(prms);
		execMDB.update(connMDB, sql, prms);
		execMDB.closeStatement();
	}

	private void executeSerie(Connection connMDB, BSExecutor execMDB, List componentsList, int index) throws BSException {
		Object recordObject = null;
		Element componentElement = null;

		int j = 0;

		recordObject = componentsList.get(index);
		String sql = null;

		if (recordObject != null) {
			Object[] prms = null;
			componentElement = (Element) recordObject;
			if (componentElement.element("cRelleno").getTextTrim().equals("0")) {
				prms = createEmptyString(7);

				prms[j++] = componentElement.element("cNombre").getTextTrim();
				prms[j++] = componentElement.element("cSigla").getTextTrim();
				prms[j++] = componentElement.element("cRequerido").getTextTrim();
				prms[j++] = componentElement.element("cAnalisis").getTextTrim();
				prms[j++] = componentElement.element("cUnidad").getTextTrim();
				// prms[j++] = getDesviacionAbsoluta(componentElement);
				prms[j++] = "+/- " + componentElement.element("cDesviacionRelativa").getTextTrim();

				double desviacionRelativa = toDouble(componentElement.element("cDesviacionRelativa").getTextTrim());
				double analisis = toDouble(componentElement.element("cAnalisis").getTextTrim());

				prms[j++] = "+/- " + Resources.formatNumber("" + (desviacionRelativa / 100) * analisis)
						+ componentElement.element("cUnidad").getTextTrim();

				if (index < 7) {
					String indexString = "" + (index + 1);
					sql = "UPDATE etiqueta SET COMP" + indexString + "=?, SIGLA"+ indexString + "=?, REQ" + indexString
							+ "=?, VAL" + indexString + "=?, UND" + indexString + "=?, DESV" + indexString + "=?, DESABS"
							+ indexString + "=?;";
				} else {
					sql = "UPDATE etiqueta SET " + "var13=?, var14=?, var15=?, var16=?, var17=?, var18=?, var19=?";
				}

			} else {
				prms = createEmptyString(3);
				prms[j++] = componentElement.element("cNombre").getTextTrim();
				prms[j++] = componentElement.element("cSigla").getTextTrim();
				// prms[j++] = balanceElement.element("cRelleno").getTextTrim();
				prms[j++] = "Balance"; // componentElement.element("cAnalisis").getTextTrim();
				// prms[j++] = balanceElement.element("cUnidad").getTextTrim();
				// prms[j++] = getDesviacionAbsoluta(balanceElement);
				// prms[j++] =
				// balanceElement.element("cDesviacionRelativa").getTextTrim();
				sql = "UPDATE etiqueta SET COMPB=?, SIGLAB=?, REQB=?;";

			}
			noEmptyStringInArray(prms);
			execMDB.update(connMDB, sql, prms);
			execMDB.closeStatement();

		}
		// j = 16;

	}

	private void noEmptyStringInArray(Object[] a) {
		Object o = null;
		for (int i = 0; i < a.length; i++) {
			o = a[i];
			if (o instanceof String) {
				if (o.toString().length() == 0) {
					a[i] = " ";
				}
			}
		}
	}

	private String[] createEmptyString(int size) {
		String[] out = new String[size];
		for (int i = 0; i < size; i++) {
			out[i] = "";
		}
		return out;
	}

	private Object getPatronsOfSolicitud(Connection connSol, BSExecutor execSol, String id) throws BSException {
		String sql = "pListPatronOfSolicitud";
		Document patronsDocument = execSol.querySP(connSol, sql, BSStaticManager.buildArrayObject(id), "Patrons");
		// BSLog.debug(patrons.asXML());

		String out = "";
		List patronsList = patronsDocument.getRootElement().elements("Record");
		for (int i = 0; i < patronsList.size(); i++) {
			Element recordElement = (Element) patronsList.get(i);
			out += "N°" + recordElement.elementTextTrim("cNumero") + (i + 1 == patronsList.size() ? "" : ", ");
		}

		return out; // "Patrón N° " + "" + ", N° " + "";

	}

	private String getMetodosOfSolicitud(Connection connSol, BSExecutor execSol, String id) throws BSException {
		// private String getMetodosAnaliticos (Connection conn, BSExecutor
		// exec, String idSolicitud) throws BSException {
		Document componentesDocument = execSol.querySP(connSol, "pGetMetodosSolicitud", new String[] { id }, "PdfGenerator");
		execSol.closeStatement();

		List metodos = componentesDocument.selectNodes("/PdfGenerator/Record");

		StringBuffer buffer = new StringBuffer();

		for (int i = 0; i < metodos.size(); i++) {
			if (i > 0)
				buffer.append(", ");

			buffer.append(((Element) metodos.get(i)).elementTextTrim("cNombre"));
		}

		return buffer.toString();
	}

	private String getDesviacionAbsoluta(Element recordElement) {
		/**
		 * <code>
	     (cDesviacionRelativa div 100) * cAnalisis
		  </code>
		 */

		String analisisString = recordElement.element("cAnalisis").getTextTrim();
		String desviacionRelativoString = recordElement.element("cDesviacionRelativa").getTextTrim();

		double analisisInt = stringToDoubleAs0(analisisString);
		double desviacionRelativoInt = stringToDoubleAs0(desviacionRelativoString);
		return "" + desviacionRelativoInt / 100 * analisisInt;
	}

	private double stringToDoubleAs0(String s) {
		double out = 0;
		try {
			out = toDouble(s);
		} catch (BSProgrammerException e) {
			out = 0;
		}
		return out;
	}

	private Document getComponentes(Connection connSol, BSExecutor execSol, String id) throws BSException {
		return executeSpWidthId(connSol, execSol, "pListComponentBySolicitud", id, "Components");
	}

	private Document getSolicitud(Connection connSol, BSExecutor execSol, String id) throws BSException {
		// String sql = "pGetSolicitud";
		// Document out = execSol.querySP(connSol, sql,
		// BSStaticManager.buildArrayObject(id), "Solicitud");
		return executeSpWidthId(connSol, execSol, "pGetSolicitud", id, "Solicitud");
	}

	/**
	 * <code>

	private Document executeSpWidthId(Connection connSol, BSExecutor execSol, String sp, String id) throws BSException {
		return executeSpWidthId(connSol, execSol, sp, id, sp);
	}

</code>
	 */

	private Document executeSpWidthId(Connection connSol, BSExecutor execSol, String sp, String id, String alias)
			throws BSException {
		Document out = execSol.querySP(connSol, sp, BSStaticManager.buildArrayObject(id), alias);
		return out;
	}

}
