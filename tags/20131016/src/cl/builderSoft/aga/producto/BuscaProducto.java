package cl.builderSoft.aga.producto;

import java.sql.Connection;
import java.sql.SQLException;

import org.dom4j.Document;

import cl.builderSoft.framework.database.BSExecutor;
import cl.builderSoft.framework.exception.BSException;
import cl.builderSoft.framework.log.BSLog;
import cl.builderSoft.framework.mvc.ioService.BSServiceData;
import cl.builderSoft.framework.service.BSAbstractProcess;
import cl.builderSoft.framework.service.BSProcess;
import cl.builderSoft.framework.util.BSConfigManager;
import cl.builderSoft.framework.util.BSFactory;
import cl.builderSoft.framework.util.BSStaticManager;
import cl.builderSoft.util.access.AccessJDBCUtil;

public class BuscaProducto extends BSAbstractProcess implements BSProcess {

	public BSServiceData execute(BSServiceData serviceData) throws BSException {
		BSServiceData out = null;
		String mdbPath = BSConfigManager.getInstance().getProperty("AGA", "/Module/MDB", "");
		try {
			Connection conn = AccessJDBCUtil.getAccessDBConnection(mdbPath);
			out = executeTransaction(serviceData, conn);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return out;
	}

	public BSServiceData executeTransaction(BSServiceData serviceData, Connection conn) throws BSException {
		String sql = "SELECT count(PROCOD) AS Cuantos FROM PRODUCTOS WHERE PROCOD=?";
		BSExecutor exec = BSFactory.getExecutor("MDB");
		String codigo = serviceData.getRequestFieldString("codigoGe", null);
		Document producto = exec.query(conn, sql, BSStaticManager.buildArrayObject(codigo), "Producto");

		serviceData.addResponseNode(producto.getRootElement());

		BSLog.debug(producto.asXML());

		return null;
	}
}
