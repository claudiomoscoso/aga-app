package cl.builderSoft.framework.user.service;

import java.sql.Connection;

import cl.builderSoft.framework.database.BSExecutor;
import cl.builderSoft.framework.exception.BSException;
import cl.builderSoft.framework.log.BSLog;
import cl.builderSoft.framework.mvc.ioService.BSServiceData;
import cl.builderSoft.framework.service.BSAbstractProcess;
import cl.builderSoft.framework.service.BSProcess;
import cl.builderSoft.framework.user.UserService;
import cl.builderSoft.framework.util.BSFactory;
import cl.builderSoft.framework.util.BSStaticManager;

public class UpdateRol extends BSAbstractProcess implements BSProcess {
    public BSServiceData execute(BSServiceData serviceData) throws BSException {
        String userlogon = serviceData.getUserLogon();

        String id = serviceData.getRequestFieldString("fldID", null);
        String name = serviceData.getRequestFieldString("fldName", null);

        String sql = "UPDATE tRol SET cName=? WHERE cID=?";

        Connection conn = BSFactory.getConnectionManager().getConnection(UserService.ALIAS);
        BSExecutor exec = BSFactory.getExecutor(UserService.ALIAS);

        Object[] params = new Object[2];
        params[0] = name;
        params[1] = id;

        exec.update(conn, sql, params);
        exec.closeStatement();
        BSFactory.getConnectionManager().releaseConnection(conn, UserService.ALIAS);

        BSLog.saveActionUser(serviceData, BSStaticManager.buildArrayObject(userlogon, id));

        //        serviceData.setServiceName("USR-ListRol");
        return null;
    }

    public BSServiceData executeTransaction(BSServiceData serviceData, Connection conn) throws BSException {
        return null;
    }

}