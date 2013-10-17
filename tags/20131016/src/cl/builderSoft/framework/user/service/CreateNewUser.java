/*
 * Created on 17-05-2007
 */
package cl.builderSoft.framework.user.service;

import java.sql.Connection;

import cl.builderSoft.framework.database.BSConnectionManager;
import cl.builderSoft.framework.exception.BSException;
import cl.builderSoft.framework.mvc.ioService.BSServiceData;
import cl.builderSoft.framework.service.BSAbstractProcess;
import cl.builderSoft.framework.service.BSProcess;
import cl.builderSoft.framework.user.UserService;
import cl.builderSoft.framework.util.BSFactory;

/**
 * @author cmoscoso
 */
public class CreateNewUser extends BSAbstractProcess implements BSProcess {

    public BSServiceData execute(BSServiceData serviceData) throws BSException {
        Connection conn = BSFactory.getConnectionManager().getConnection(UserService.ALIAS);
        executeTransaction(serviceData, conn);
        BSFactory.getConnectionManager().releaseConnection(conn, UserService.ALIAS);
        return null;
    }

    public BSServiceData executeTransaction(BSServiceData serviceData, Connection conn) throws BSException {
        BSConnectionManager connectionManager = BSFactory.getConnectionManager();
        connectionManager.setAutoCommit(conn, false);

        BSProcess createNewUserField = new CreateNewUserField();
        BSProcess createNewUserRecord = new CreateNewUserRecord();
        try {
            createNewUserRecord.executeTransaction(serviceData, conn);
            createNewUserField.executeTransaction(serviceData, conn);
            connectionManager.commit(conn);
        } catch (BSException e) {
            connectionManager.rollback(conn);
            throw e;
        } finally {
            connectionManager.releaseConnection(conn, UserService.ALIAS);
        }

        return null;
    }
}