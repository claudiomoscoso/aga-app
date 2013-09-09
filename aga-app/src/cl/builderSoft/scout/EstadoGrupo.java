/*
 * Created on 19-05-2007
 */
package cl.builderSoft.scout;

import java.sql.Connection;

import cl.builderSoft.framework.database.BSExecutor;
import cl.builderSoft.framework.exception.BSException;
import cl.builderSoft.framework.mvc.ioService.BSServiceData;
import cl.builderSoft.framework.service.BSAbstractProcess;
import cl.builderSoft.framework.service.BSProcess;
import cl.builderSoft.framework.util.BSFactory;
import cl.builderSoft.framework.util.BSStaticManager;

/**
 * @author cmoscoso
 */
public class EstadoGrupo extends BSAbstractProcess implements BSProcess {

    public BSServiceData execute(BSServiceData serviceData) throws BSException {
        Connection conn = BSFactory.getConnectionManager().getConnection("SCO");
        BSFactory.getConnectionManager().setAutoCommit(conn, false);
        try {
            executeTransaction(serviceData, conn);
            BSFactory.getConnectionManager().commit(conn);
        } catch (BSException e) {
            BSFactory.getConnectionManager().rollback(conn);
            throw e;
        }

        BSFactory.getConnectionManager().releaseConnection(conn, "SCO");
        return null;
    }

    public BSServiceData executeTransaction(BSServiceData serviceData, Connection conn) throws BSException {
        BSExecutor exec = BSFactory.getExecutor("SCO");
        String[] grupos = serviceData.getRequestFieldArray("fldGrupo", new String[0]);
        BSFactory.getConnectionManager().setAutoCommit(conn, false);
        int size = grupos.length;

        disableGrupos(conn, exec);
        for (int i = 0; i < size; i++) {
            enableGrupo(conn, exec, grupos[i]);
        }

        return null;
    }

    private void enableGrupo(Connection conn, BSExecutor exec, String idGrupo) throws BSException {
        String sql = "UPDATE tGrupo SET cHabilitado = 1 WHERE cID = ?";
        exec.update(conn, sql, BSStaticManager.buildArrayObject(idGrupo));
        exec.closeStatement();
        sql = null;
    }

    private void disableGrupos(Connection conn, BSExecutor exec) throws BSException {
        String sql = "UPDATE tGrupo SET cHabilitado = 0";
        exec.update(conn, sql, null);
        exec.closeStatement();
        sql = null;
    }

}