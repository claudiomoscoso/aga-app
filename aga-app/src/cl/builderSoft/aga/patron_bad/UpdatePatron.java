package cl.builderSoft.aga.patron_bad;

import java.sql.Connection;

import cl.builderSoft.aga.certificate.solicitud.SolicitudService;
import cl.builderSoft.framework.database.BSExecutor;
import cl.builderSoft.framework.exception.BSException;
import cl.builderSoft.framework.mvc.ioService.BSServiceData;
import cl.builderSoft.framework.service.BSAbstractProcess;
import cl.builderSoft.framework.service.BSProcess;
import cl.builderSoft.framework.util.BSFactory;

public class UpdatePatron extends BSAbstractProcess implements BSProcess {
    public BSServiceData execute(BSServiceData serviceData) throws BSException {
        String id = serviceData.getRequestFieldString("fldID", null);
        String numero = serviceData.getRequestFieldString("fldNumero", null);
        String compuesto = serviceData.getRequestFieldString("fldCompuesto", null);
        String composicion = serviceData.getRequestFieldString("fldComposicion", null);
        String cilindro = serviceData.getRequestFieldString("fldCilindro", null);
        String fechaExpiracion = serviceData.getRequestFieldString("fldFechaExpiracion", null); 

        String sql = "UPDATE tPatron SET cNumero = ?, cCompuesto=?, cComposicion=?, cCilindro=?, cFechaExpiracion=? ";
        sql += "WHERE cID=?";

        Connection conn = BSFactory.getConnectionManager().getConnection(SolicitudService.ALIAS);
        BSExecutor exec = BSFactory.getExecutor(SolicitudService.ALIAS);

        Object[] params = new Object[6];
        params[0] = numero;
        params[1] = compuesto;
        params[2] = composicion;
        params[3] = cilindro;
        params[4] = fechaExpiracion;
        params[5] = id;
        
        exec.update(conn, sql, params);
        exec.closeStatement();
        BSFactory.getConnectionManager().releaseConnection(conn, SolicitudService.ALIAS);

        return null;
    }

    public BSServiceData executeTransaction(BSServiceData serviceData, Connection conn) throws BSException {

        return null;
    }
}