package cl.builderSoft.aga.patron_bad;

import java.sql.Connection;

import cl.builderSoft.aga.certificate.solicitud.SolicitudService;
import cl.builderSoft.framework.database.BSExecutor;
import cl.builderSoft.framework.exception.BSException;
import cl.builderSoft.framework.exception.BSProgrammerException;
import cl.builderSoft.framework.mvc.ioService.BSServiceData;
import cl.builderSoft.framework.service.BSAbstractProcess;
import cl.builderSoft.framework.service.BSProcess;
import cl.builderSoft.framework.util.BSFactory;

public class DeletePatron extends BSAbstractProcess implements BSProcess {

    public BSServiceData execute(BSServiceData serviceData) throws BSException {
        String patronID = serviceData.getRequestFieldString("fldID", null);
        String sql = "DELETE FROM tPatron where cID='" + patronID + "'";

        Connection conn = BSFactory.getConnectionManager().getConnection(SolicitudService.ALIAS);
        BSExecutor exec = BSFactory.getExecutor(SolicitudService.ALIAS);

        exec.update(conn, sql, null);
        exec.closeStatement();
        BSFactory.getConnectionManager().releaseConnection(conn, SolicitudService.ALIAS);
        throw new BSProgrammerException("BS090");
//        return null;
    }

    public BSServiceData executeTransaction(BSServiceData serviceData, Connection conn) throws BSException {
        return null;
    }
}