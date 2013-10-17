package cl.builderSoft.framework.user.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import cl.builderSoft.framework.database.BSExecutor;
import cl.builderSoft.framework.exception.BSDataBaseException;
import cl.builderSoft.framework.exception.BSException;
import cl.builderSoft.framework.exception.BSUserException;
import cl.builderSoft.framework.log.BSLog;
import cl.builderSoft.framework.mvc.ioService.BSServiceData;
import cl.builderSoft.framework.service.BSAbstractProcess;
import cl.builderSoft.framework.service.BSProcess;
import cl.builderSoft.framework.user.UserService;
import cl.builderSoft.framework.util.BSFactory;
import cl.builderSoft.framework.util.BSStaticManager;

public class DeleteRol extends BSAbstractProcess implements BSProcess {
    public BSServiceData execute(BSServiceData serviceData) throws BSException {
        boolean beElements = false;
        String userlogon = serviceData.getUserLogon();
        String rol = serviceData.getRequestFieldString("fldID", null);
        String sql = "SELECT count(cID) AS cnt FROM tUser WHERE cDeleted=0 AND cRol='" + rol + "'";

        Connection conn = BSFactory.getConnectionManager().getConnection(UserService.ALIAS);
        BSExecutor exec = BSFactory.getExecutor(UserService.ALIAS);

        ResultSet r = exec.queryResultSet(conn, sql, null);
        try {
            if (r.next()) {
                beElements = r.getInt("cnt") > 0;
            }
            r.close();
        } catch (SQLException e1) {
            throw new BSDataBaseException("Se produjo un error de SQL al tratar de eliminar un rol", false);
        } finally {
            r = null;
        }

        if (beElements) {
            throw new BSUserException("Existen usuarios asociados a este rol, favor elimine los usuarios antes del Rol", true);
        } else {
            sql = "UPDATE tRol SET cDeleted=1 WHERE cID='" + rol + "'";

            exec.update(conn, sql, null);
            BSLog.saveActionUser(serviceData, BSStaticManager.buildArrayObject(userlogon, rol));

        }
        exec.closeStatement();
        BSFactory.getConnectionManager().releaseConnection(conn, UserService.ALIAS);

        return null;
    }

    public BSServiceData executeTransaction(BSServiceData serviceData, Connection conn) throws BSException {
        return null;
    }
}