package cl.builderSoft.framework.user.service;

import java.sql.Connection;

import cl.builderSoft.framework.database.BSExecutor;
import cl.builderSoft.framework.exception.BSException;
import cl.builderSoft.framework.mvc.ioService.BSServiceData;
import cl.builderSoft.framework.service.BSAbstractProcess;
import cl.builderSoft.framework.service.BSProcess;
import cl.builderSoft.framework.user.UserService;
import cl.builderSoft.framework.util.BSFactory;

public class GetRolEmpresa extends BSAbstractProcess implements BSProcess {
    public BSServiceData execute(BSServiceData serviceData) throws BSException {
        Connection conn = BSFactory.getConnectionManager().getConnection(UserService.ALIAS);
        BSExecutor exec = BSFactory.getExecutor(UserService.ALIAS);
        String sql = "SELECT cID, cName FROM tRol WHERE cDeleted=0";

        serviceData.addResponseNode(exec.query(conn, sql, null, "Rol").getRootElement());

        exec.closeStatement();
        BSFactory.getConnectionManager().releaseConnection(conn, UserService.ALIAS);

        return null;
    }

    public BSServiceData executeTransaction(BSServiceData serviceData, Connection conn) throws BSException {
        return null;
    }

}