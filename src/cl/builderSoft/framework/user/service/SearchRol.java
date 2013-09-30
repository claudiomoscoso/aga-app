package cl.builderSoft.framework.user.service;

import java.sql.Connection;

import cl.builderSoft.framework.database.BSExecutor;
import cl.builderSoft.framework.exception.BSException;
import cl.builderSoft.framework.mvc.ioService.BSServiceData;
import cl.builderSoft.framework.service.BSAbstractProcess;
import cl.builderSoft.framework.service.BSProcess;
import cl.builderSoft.framework.user.UserService;
import cl.builderSoft.framework.util.BSFactory;

public class SearchRol extends BSAbstractProcess implements BSProcess {
    public BSServiceData execute(BSServiceData serviceData) throws BSException {
        String sql = "SELECT R.cID, R.cName ";
        sql += "FROM tRol as R ";
        sql += "WHERE R.cID='" + serviceData.getRequestFieldString("fldID", null) + "'";

        Connection conn = BSFactory.getConnectionManager().getConnection(UserService.ALIAS);
        BSExecutor exec = BSFactory.getExecutor(UserService.ALIAS);

        serviceData.addResponseNode(exec.query(conn, sql, null, "SearchRol").getRootElement());

        exec.closeStatement();
        BSFactory.getConnectionManager().releaseConnection(conn, UserService.ALIAS);

        return null;
    }

    public BSServiceData executeTransaction(BSServiceData serviceData, Connection conn) throws BSException {
        return null;
    }
}