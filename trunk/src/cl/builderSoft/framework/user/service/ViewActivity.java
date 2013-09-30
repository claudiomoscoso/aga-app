package cl.builderSoft.framework.user.service;

import java.sql.Connection;

import org.dom4j.Document;

import cl.builderSoft.framework.database.BSExecutor;
import cl.builderSoft.framework.exception.BSException;
import cl.builderSoft.framework.mvc.ioService.BSServiceData;
import cl.builderSoft.framework.service.BSAbstractProcess;
import cl.builderSoft.framework.service.BSProcess;
import cl.builderSoft.framework.user.UserService;
import cl.builderSoft.framework.util.BSFactory;
import cl.builderSoft.framework.util.BSStaticManager;

public class ViewActivity extends BSAbstractProcess implements BSProcess {
    public BSServiceData execute(BSServiceData serviceData) throws BSException {
        String id = serviceData.getRequestFieldString("fldID", null);
        String sql = "SELECT l.cID, l.cDateEvent, l.cUser, l.cType, l.cMessage ";
        sql += "FROM tLog AS l ";
        sql += "LEFT JOIN tUser AS u ON l.cUser = u.cLogin ";
        sql += "WHERE u.cID = ? ";
        sql += "ORDER BY l.cID";

        BSExecutor exec = BSFactory.getExecutor(UserService.ALIAS);
        Connection conn = BSFactory.getConnectionManager().getConnection(UserService.ALIAS);

        Document activity = exec.query(conn, sql, BSStaticManager.buildArrayObject(id), "Activity");
        exec.closeStatement();
        serviceData.addResponseNode(activity.getRootElement());

        BSFactory.getConnectionManager().releaseConnection(conn, UserService.ALIAS);

        return null;
    }

    public BSServiceData executeTransaction(BSServiceData serviceData, Connection conn) throws BSException {
        return null;
    }
}