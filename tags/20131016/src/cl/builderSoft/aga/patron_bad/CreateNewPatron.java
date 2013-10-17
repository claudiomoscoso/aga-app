package cl.builderSoft.aga.patron_bad;

import java.sql.Connection;

import cl.builderSoft.framework.database.BSExecutor;
import cl.builderSoft.framework.exception.BSException;
import cl.builderSoft.framework.exception.BSProgrammerException;
import cl.builderSoft.framework.mvc.ioService.BSServiceData;
import cl.builderSoft.framework.service.BSAbstractProcess;
import cl.builderSoft.framework.service.BSProcess;
import cl.builderSoft.framework.util.BSFactory;

public class CreateNewPatron extends BSAbstractProcess implements
		BSProcess {
	public BSServiceData execute(BSServiceData serviceData) throws BSException {
		Connection conn = BSFactory.getConnectionManager().getConnection("SLC");
		executeTransaction(serviceData, conn);
		BSFactory.getConnectionManager().releaseConnection(conn,"SLC");
		throw new BSProgrammerException("BS090");
//		return null;
	}

	public BSServiceData executeTransaction(BSServiceData serviceData,
			Connection conn) throws BSException {

		String numero = serviceData.getRequestFieldString("fldNumero", null);
		String compuesto = serviceData.getRequestFieldString("fldCompuesto", null);
		String composicion = serviceData.getRequestFieldString("fldComposicion", null);
		String cilindro = serviceData.getRequestFieldString("fldCilindro", null);
		String fechaExpiracion = serviceData.getRequestFieldString("fldFechaExpiracion",null);

		String sql = "INSERT tPatron(cID, cNumero,cCompuesto,cComposicion,cCilindro,cFechaExpiracion) ";
		sql += "VALUES(?,?,?,?,?,?)";

		BSExecutor exec = BSFactory.getExecutor("SLC");

		Object[] params = new Object[6];
		params[0] = numero;
		params[1] = numero;
		params[2] = compuesto;
		params[3] = composicion;
		params[4] = cilindro;
		params[5] = fechaExpiracion;
		
		exec.update(conn, sql, params);
		exec.closeStatement();
		BSFactory.getConnectionManager().commit(conn);

		serviceData.addResponseField("NewPatronID", (String) params[0]);
		return null;
	}

}