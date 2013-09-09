package cl.builderSoft.aga.patron_bad;

import java.sql.Connection;

import cl.builderSoft.framework.database.BSExecutor;
import cl.builderSoft.framework.exception.BSException;
import cl.builderSoft.framework.mvc.ioService.BSServiceData;
import cl.builderSoft.framework.service.BSAbstractProcess;
import cl.builderSoft.framework.service.BSProcess;
import cl.builderSoft.framework.util.BSFactory;

public class ListPatron extends BSAbstractProcess implements BSProcess {
	
	private static final String SLC = "SLC";
	
	public BSServiceData execute(BSServiceData serviceData) throws BSException {

		String sql = "SELECT P.cID, P.cNumero, P.cCompuesto, P.cComposicion, P.cCilindro, P.cFechaExpiracion ";
		sql += "FROM tPatron as P ";
		sql += "ORDER BY P.cCompuesto";

		Connection conn = BSFactory.getConnectionManager().getConnection(SLC);
		BSExecutor exec = BSFactory.getExecutor(SLC);

		serviceData.addResponseNode(exec.query(conn, sql, null, "listPatron").getRootElement());
		exec.closeStatement();
		BSFactory.getConnectionManager().releaseConnection(conn, SLC);

		return null;
	}

	public BSServiceData executeTransaction(BSServiceData serviceData, Connection conn) throws BSException {
		return null;
	}

}