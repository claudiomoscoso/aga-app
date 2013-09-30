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

public class UpdateUser extends BSAbstractProcess implements BSProcess {
    public BSServiceData execute(BSServiceData serviceData) throws BSException {
        String userlogon = serviceData.getUserLogon();

        String id = serviceData.getRequestFieldString("fldID", null);
        String name = serviceData.getRequestFieldString("fldName", null);
        String mail = serviceData.getRequestFieldString("fldMail", null);
        String rol = serviceData.getRequestFieldString("fldRol", null);
        String phone = serviceData.getRequestFieldString("fldPhone", null);
        String movil = serviceData.getRequestFieldString("fldMovil", null);

        String sql = "UPDATE tUser SET cName=?, cMail=?, cRol=?, cPhone=?, cMovil=? ";
        sql += "WHERE cID=?";

        Connection conn = BSFactory.getConnectionManager().getConnection(UserService.ALIAS);
        BSExecutor exec = BSFactory.getExecutor(UserService.ALIAS);

        Object[] params = new Object[6];
        params[0] = name;
        params[1] = mail;
        params[2] = rol;

        params[3] = phone;
        params[4] = movil;
        params[5] = id;

        exec.update(conn, sql, params);
        exec.closeStatement();
        BSFactory.getConnectionManager().releaseConnection(conn, UserService.ALIAS);

        BSLog.saveActionUser(serviceData, BSStaticManager.buildArrayObject(userlogon, id));

        executeTransaction(serviceData, null);
        return null;
    }

    public BSServiceData executeTransaction(BSServiceData serviceData, Connection conn) throws BSException {
        /*
         * String id = serviceData.getRequestFieldString("fldID", null);
         * 
         * UserService userService =
         * (UserService)BSFactory.createInstance(UserService.CLASS_NAME);
         * UserBean user = userService.searchById(id);
         * userService.saveUser(user);
         */
        serviceData.addResponseField("NewUserID", serviceData.getRequestFieldString("fldID", null));
        BSProcess updateField = new CreateNewUserField();
        updateField.execute(serviceData);
        updateField = null;

        return null;
    }
}