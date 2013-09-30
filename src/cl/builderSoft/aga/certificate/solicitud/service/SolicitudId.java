package cl.builderSoft.aga.certificate.solicitud.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import cl.builderSoft.aga.certificate.solicitud.SolicitudService;
import cl.builderSoft.framework.database.BSExecutor;
import cl.builderSoft.framework.exception.BSException;
import cl.builderSoft.framework.mvc.ioService.BSServiceData;
import cl.builderSoft.framework.service.BSAbstractProcess;
import cl.builderSoft.framework.service.BSProcess;
import cl.builderSoft.framework.util.BSFactory;

public class SolicitudId extends BSAbstractProcess implements BSProcess {

	public BSServiceData execute(BSServiceData serviceData) throws BSException {
		
		String id = serviceData.getRequestFieldString("fldID", "");
		String product = serviceData.getRequestFieldString("fldPro", "");
		String codigoGe = serviceData.getRequestFieldString("codigoGe", "");
		
		serviceData.addResponseField("product", codigoGe.equals("") ? product : codigoGe);

		StringBuilder sql = new StringBuilder();
		

		sql.append("select P.nombre_producto from mydb.producto P where P.producto='" + (codigoGe.equals("") ? product : codigoGe) + "'");
		Connection conn = BSFactory.getConnectionManager().getConnection(SolicitudService.ALIAS);
		BSExecutor exec = BSFactory.getExecutor(SolicitudService.ALIAS);

		serviceData.addResponseNode(exec.query(conn, sql.toString(), null, "producto").getRootElement());
		exec.closeStatement();
		BSFactory.getConnectionManager().releaseConnection(conn, SolicitudService.ALIAS);


		if (id.equals("")){
			
			sql = new StringBuilder();

			sql.append("SELECT * FROM tComponente c;");
			
			conn = BSFactory.getConnectionManager().getConnection(SolicitudService.ALIAS);
			exec = BSFactory.getExecutor(SolicitudService.ALIAS);

			serviceData.addResponseNode(exec.query(conn, sql.toString(), null, "puros").getRootElement());
			exec.closeStatement();
			BSFactory.getConnectionManager().releaseConnection(conn, SolicitudService.ALIAS);
			
			return null;
		} else{
			sql = new StringBuilder();
			
			sql.append("SELECT * ");
			sql.append("FROM tsolicitudcomponente t, tcomponente c ");
			sql.append("where t.cComponente = c.cCodigo and t.cNumeroSolicitud="+id);
			
			conn = BSFactory.getConnectionManager().getConnection(SolicitudService.ALIAS);
			exec = BSFactory.getExecutor(SolicitudService.ALIAS);

			serviceData.addResponseNode(exec.query(conn, sql.toString(), null, "puros").getRootElement());
			exec.closeStatement();
			BSFactory.getConnectionManager().releaseConnection(conn, SolicitudService.ALIAS);

		}
		/*sql.append("SELECT top 1 A.analisis, A.anacli, A.anaord, A.anacon, A.anacomen, C.CLIRUT");
		sql.append(" FROM analisis as A");
		sql.append(" LEFT JOIN clientes as C on A.anacli = C.CLIRAZ");
		sql.append(" WHERE A.ananro="+ id );*/
		
		sql = new StringBuilder();

		sql.append("SELECT * ");
		sql.append("from tsolicitud s, mydb.clientes c ");
		sql.append("where s.cCliente = c.cliente and s.cNumero=" + id);

		conn = BSFactory.getConnectionManager().getConnection(SolicitudService.ALIAS);
		exec = BSFactory.getExecutor(SolicitudService.ALIAS);

		serviceData.addResponseNode(exec.query(conn, sql.toString(), null, "solicitud").getRootElement());
		exec.closeStatement();
		BSFactory.getConnectionManager().releaseConnection(conn, SolicitudService.ALIAS);

		
		//serviceData.addResponseField(getAnalisis());
		
		return null;
	}

	public BSServiceData executeTransaction(BSServiceData serviceData,
			Connection conn) throws BSException {
		// TODO Auto-generated method stub
		return null;
	}
	
	private List getAnalisis(){
		
		
		
		return new ArrayList();
	}

}
