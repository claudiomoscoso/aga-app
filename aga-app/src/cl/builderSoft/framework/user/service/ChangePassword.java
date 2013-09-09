package cl.builderSoft.framework.user.service;

import java.sql.Connection;

import cl.builderSoft.framework.database.BSExecutor;
import cl.builderSoft.framework.exception.BSException;
import cl.builderSoft.framework.exception.BSUserException;
import cl.builderSoft.framework.log.BSLog;
import cl.builderSoft.framework.mvc.ioService.BSServiceData;
import cl.builderSoft.framework.service.BSAbstractProcess;
import cl.builderSoft.framework.service.BSProcess;
import cl.builderSoft.framework.user.UserService;
import cl.builderSoft.framework.util.BSFactory;
import cl.builderSoft.framework.util.BSSecurity;
import cl.builderSoft.framework.util.BSStaticManager;

public class ChangePassword extends BSAbstractProcess implements BSProcess {
    public BSServiceData execute(BSServiceData serviceData) throws BSException {

        String password1 = serviceData.getRequestFieldString("fldPassword1", null);
        String password2 = serviceData.getRequestFieldString("fldPassword2", null);

        if (password1.equals(password2)) {
            Connection conn = BSFactory.getConnectionManager().getConnection(UserService.ALIAS);
            BSExecutor exec = BSFactory.getExecutor(UserService.ALIAS);
            String sql = "UPDATE tUser SET cPassword=? WHERE cID=?";

            BSSecurity sec = new BSSecurity();
            password1 = sec.cript(password1);
            sec = null;

            Object[] params = new Object[2];
            params[0] = password1;
            params[1] = serviceData.getRequestFieldString("fldID", null);

            exec.update(conn, sql, params);
            exec.closeStatement();
            BSFactory.getConnectionManager().releaseConnection(conn, UserService.ALIAS);
            BSLog.saveActionUser(serviceData, BSStaticManager.buildArrayObject(params[1]));
        } else {
            throw new BSUserException("Las password ingresadas son distintas, reingrese", false);
        }

        return null;
    }

    public BSServiceData executeTransaction(BSServiceData serviceData, Connection conn) throws BSException {
        return null;
    }

}