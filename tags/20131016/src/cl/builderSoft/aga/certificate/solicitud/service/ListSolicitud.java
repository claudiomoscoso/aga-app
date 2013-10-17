package cl.builderSoft.aga.certificate.solicitud.service;

import java.sql.Connection;

import cl.builderSoft.aga.certificate.solicitud.SolicitudService;
import cl.builderSoft.framework.database.BSExecutor;
import cl.builderSoft.framework.exception.BSException;
import cl.builderSoft.framework.log.BSLog;
import cl.builderSoft.framework.mvc.ioService.BSServiceData;
import cl.builderSoft.framework.service.BSAbstractProcess;
import cl.builderSoft.framework.service.BSProcess;
import cl.builderSoft.framework.util.BSFactory;

public class ListSolicitud extends BSAbstractProcess implements BSProcess {

	public BSServiceData execute(BSServiceData serviceData) throws BSException {
		StringBuilder sql = new StringBuilder();
		
		/*sql.append("SELECT distinct A.ananro, A.analisis, A.anacod, A.anacli, A.anacil, A.anaord, A.anafec, A.analisis, A.estado");
		sql.append(" FROM analisis as A");
		sql.append(" order by A.analisis");

		sql.append("SELECT TOP 50 D.ananro, D.anacod, A.analisis, A.anacli, A.anacil, A.anaord, A.anafec, A.analisis, A.estado, D.anapro");
		sql.append(" FROM analidet D, analisis A");
		sql.append(" WHERE D.anacod = A.anacod");
		*/
		//sql.append("SELECT top 30 * FROM analisis");
		
	/**	
		sql.append("SELECT t.cNumero, t.cProducto, t.cFechaCreacion, mc.nombre_cliente, c.cDescripcion as des_cilindro, e.cDescripcion ");
		sql.append("FROM tSolicitud t left join DB_FOC90..clientes mc on t.cCliente = mc.cliente, tsolicituddetalle sd, ttipocilindro c, testado e ");
		sql.append("where t.cNumero = sd.cSolicitud and sd.cTipoCilindro = c.cCodigo and t.cEstado = e.cCodigo;");
*/
		sql.append("SELECT t.cNumero, t.cProducto, t.cFechaCreacion, mc.nombre_cliente, c.cDescripcion as des_cilindro, e.cDescripcion ");
		sql.append("FROM tSolicitud t left join DB_FOC90..clientes mc on t.cCliente = mc.cliente, tsolicituddetalle sd, ttipocilindro c, testado e ");
		sql.append("where t.cNumero = sd.cSolicitud and sd.cTipoCilindro = c.cCodigo and t.cEstado = e.cCodigo;");

		/*
SELECT t.cNumero, t.cProducto, t.cFechaCreacion, mc.nombre_cliente--, c.cDescripcion as des_cilindro, e.cDescripcion 
FROM tSolicitud t 
LEFT JOIN DB_FOC90..clientes mc ON t.cCliente = mc.cliente
LEFT JOIN tSolicitudDetalle sd ON t.cNumero = sd.cSolicitud 
LEFT JOIN tTipoCilindro c ON sd.cTipoCilindro = c.cCodigo 
*/

		
		BSLog.debug(sql.toString());
		
		Connection conn = BSFactory.getConnectionManager().getConnection(SolicitudService.ALIAS);
		BSExecutor exec = BSFactory.getExecutor(SolicitudService.ALIAS);

		serviceData.addResponseNode(exec.query(conn, sql.toString(), null, "listSolicitud").getRootElement());
		exec.closeStatement();
		BSFactory.getConnectionManager().releaseConnection(conn, SolicitudService.ALIAS);

		return null;
	}

	public BSServiceData executeTransaction(BSServiceData serviceData,
			Connection conn) throws BSException {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
