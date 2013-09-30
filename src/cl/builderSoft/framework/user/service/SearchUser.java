package cl.builderSoft.framework.user.service;

import java.sql.Connection;

import org.dom4j.Document;

import cl.builderSoft.framework.exception.BSException;
import cl.builderSoft.framework.mvc.ioService.BSServiceData;
import cl.builderSoft.framework.service.BSAbstractProcess;
import cl.builderSoft.framework.service.BSProcess;
import cl.builderSoft.framework.user.UserBean;
import cl.builderSoft.framework.user.UserService;
import cl.builderSoft.framework.util.BSFactory;
import cl.builderSoft.framework.util.BSXMLManager;

public class SearchUser extends BSAbstractProcess implements BSProcess {

    public BSServiceData execute(BSServiceData serviceData) throws BSException {
        /**
         * <code>
         String sql = "SELECT U.cID, U.cLogin, U.cName, U.cMail, U.cRol, U.cPhone, U.cMovil, U.cDeleted, U.cCreationDate, U.cCreatorID, U.cInfo, U.cPassword, U.cEnable, R.cName AS cRolName ";
         sql += "FROM tUser as U ";
         sql += "LEFT JOIN tRol AS R on U.cRol = R.cID ";
         sql += "WHERE U.cID='" + serviceData.getRequestFieldString("fldID", serviceData.getSessionFieldString("User/cID", null))
         + "'";

         Connection conn = BSFactory.getConnectionManager().getConnection(UserService.ALIAS);
         BSExecutor exec = BSFactory.getExecutor(UserService.ALIAS);

         serviceData.addResponseNode(exec.query(conn, sql, null, "SearchUser").getRootElement());

         BSFactory.getConnectionManager().releaseConnection(conn, UserService.ALIAS);
         exec.closeStatement();
         </code>
         */
        executeTransaction(serviceData, null);
        return null;
    }

    public BSServiceData executeTransaction(BSServiceData serviceData, Connection conn) throws BSException {
        String id = serviceData.getRequestFieldString("fldID", serviceData.getSessionFieldString("User/cID", null));

        UserService userService = (UserService) BSFactory.createInstance(UserService.CLASS_NAME);
        UserBean userBean = userService.searchById(id);
        String userXMLString = userService.toXML(userBean, "SearchUser");
        Document userDocument = BSXMLManager.stringToDocument(userXMLString);

        serviceData.addResponseNode(userDocument.getRootElement());
        userDocument.clearContent();
        userDocument = null;
        userXMLString = null;
        userBean = null;
        userService = null;

        return null;
    }
}