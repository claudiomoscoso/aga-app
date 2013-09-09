package cl.builderSoft.framework.user.service;

import java.sql.Connection;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;

import cl.builderSoft.framework.database.BSExecutor;
import cl.builderSoft.framework.exception.BSException;
import cl.builderSoft.framework.exception.BSUserException;
import cl.builderSoft.framework.log.BSLog;
import cl.builderSoft.framework.mvc.ioService.BSServiceData;
import cl.builderSoft.framework.service.BSAbstractProcess;
import cl.builderSoft.framework.service.BSProcess;
import cl.builderSoft.framework.user.UserBean;
import cl.builderSoft.framework.user.UserService;
import cl.builderSoft.framework.util.BSFactory;
import cl.builderSoft.framework.util.BSSecurity;
import cl.builderSoft.framework.util.BSStaticManager;
import cl.builderSoft.framework.util.BSXMLManager;

public class ValidUser extends BSAbstractProcess implements BSProcess {

    public BSServiceData execute(BSServiceData serviceData) throws BSException {
        /**
         * <code>
         String sql = "SELECT count(cID) AS activo FROM tUser WHERE cLogin=? AND cPassword=?";

         String login = serviceData.getRequestFieldString("fldLogin", null);
         String password = serviceData.getRequestFieldString("fldPassword", null);
         Object[] arrayWithLogin = BSStaticManager.buildArrayObject(login);
         BSSecurity sec = new BSSecurity();
         password = sec.cript(password);
         sec = null;

         Connection conn = BSFactory.getConnectionManager().getConnection(UserService.ALIAS);
         BSExecutor exec = BSFactory.getExecutor(UserService.ALIAS);

         String active = exec.queryField(conn, sql, BSStaticManager.buildArrayObject(login, password));
         exec.closeStatement();

         if (!active.equals("0")) {
         sql = "SELECT U.cID, U.cLogin, U.cName, U.cMail, U.cRol, U.cPhone, U.cMovil, U.cDeleted, U.cCreationDate, U.cCreatorID, U.cInfo, U.cEnable, R.cName AS RolName ";
         sql += "FROM tUser as U ";
         sql += "LEFT JOIN tRol AS R on U.cRol = R.cID ";
         sql += "WHERE U.cLogin = ? AND U.cDeleted=0;";

         Document user = exec.query(conn, sql, arrayWithLogin, "User");
         BSFactory.getConnectionManager().releaseConnection(conn, UserService.ALIAS);
         Document serviceDocument = serviceData.getDocument();
         Element userElement = (Element) serviceDocument.selectSingleNode(BSServiceData.USER_PATH);

         List fields = user.selectNodes("/User/Record/*");
         Node field = null;
         int size = fields.size();
         for (int i = 0; i < size; i++) {
         field = (Node) fields.get(i);
         userElement.addElement(field.getName()).setText(field.getText().trim());
         }
         serviceDocument.selectSingleNode(BSServiceData.USER_LOGON_PATH).setText(
         user.selectSingleNode("/User/Record/cLogin").getText());
         }
         BSFactory.getConnectionManager().releaseConnection(conn, UserService.ALIAS);
         </code>
         */
        Connection conn = BSFactory.getConnectionManager().getConnection(UserService.ALIAS);
        executeTransaction(serviceData, conn);
        BSFactory.getConnectionManager().releaseConnection(conn, UserService.ALIAS);

        return null;
    }

    public BSServiceData executeTransaction(BSServiceData serviceData, Connection conn) throws BSException {
        BSExecutor exec = BSFactory.getExecutor(UserService.ALIAS);

        String login = getLogin(serviceData);
        String password = getPassword(serviceData);
        Object[] loginArray = BSStaticManager.buildArrayObject(login);
        validaClave(conn, exec, login, password);

        serarchUser(conn, exec, serviceData, login);

        return null;
    }

    private void serarchUser(Connection conn, BSExecutor exec, BSServiceData serviceData, String login) throws BSException {
        String sql = "SELECT U.cID, U.cLogin, U.cName, U.cMail, U.cRol, U.cPhone, U.cMovil, U.cDeleted, U.cCreationDate, U.cCreatorID, U.cInfo, U.cEnable, R.cName AS RolName ";
        sql += "FROM tUser as U ";
        sql += "LEFT JOIN tRol AS R on U.cRol = R.cID ";
        sql += "WHERE U.cLogin = ? AND U.cDeleted=0;";
        Object[] loginArray = BSStaticManager.buildArrayObject(login);

        UserService userService = (UserService) BSFactory.createInstance(UserService.CLASS_NAME);
        UserBean user = userService.searchByLogin(login);
        String rolName = getRolName(conn, exec, user);

        Document serviceDocument = serviceData.getDocument();
        Element userElement = (Element) serviceDocument.selectSingleNode(BSServiceData.USER_PATH);

        Node userNode = userToNode(user, userService, rolName);

        userToService(userNode, serviceDocument);

        //        List fields = user.selectNodes("/User/Record/*");
        //        Node field = null;
        //        int size = fields.size();
        //        for (int i = 0; i < size; i++) {
        //            field = (Node) fields.get(i);
        //            userElement.addElement(field.getName()).setText(field.getText().trim());
        //        }

        serviceDocument.selectSingleNode(BSServiceData.USER_LOGON_PATH).setText(user.getLogin());
        BSLog.saveActionUser(serviceData, null);
    }

    private void userToService(Node userNode, Document serviceDocument) {
        Element userInService = (Element) serviceDocument.selectSingleNode(BSServiceData.USER_PATH);
        List userFields = userNode.selectNodes("*");
        int size = userFields.size();
        Node field = null;
        String fieldName = null;
        String fieldValue = null;
        for (int i = 0; i < size; i++) {
            field = (Node) userFields.get(i);
            fieldName = field.getName();
            fieldValue = field.getText();

//            BSLog.debug(fieldName);
            if (fieldName.equalsIgnoreCase("Info")) {
                userInService.add((Node) userNode.selectSingleNode("Info").clone());
            } else {
                userInService.addElement(fieldName).setText(fieldValue);
                if (fieldName.equalsIgnoreCase("cLogin")) {
                    serviceDocument.selectSingleNode(BSServiceData.USER_LOGON_PATH).setText(fieldValue);
                }
            }
        }

    }

    private Node userToNode(UserBean user, UserService userService, String rolName) throws BSException {
        String userXML = userService.toXML(user);
        Document userDocument = BSXMLManager.stringToDocument(userXML);
        Node out = userDocument.getRootElement();
        userXML = null;
        userDocument = null;
        return out;
    }

    private String getLogin(BSServiceData serviceData) throws BSException {
        return serviceData.getRequestFieldString("fldLogin", null);
    }

    private String getPassword(BSServiceData serviceData) throws BSException {
        return serviceData.getRequestFieldString("fldPassword", null);
    }

    private void validaClave(Connection conn, BSExecutor exec, String login, String password) throws BSException {
        String sql = "SELECT count(cID) AS activo FROM tUser WHERE cLogin=? AND cPassword=?";

        BSSecurity sec = new BSSecurity();
        password = sec.cript(password);
        sec = null;

        String active = exec.queryField(conn, sql, BSStaticManager.buildArrayObject(login, password));
        exec.closeStatement();

        if (active.equals("0")) {
            throw new BSUserException("Clave no es valida o usuario no existe", false);
        }
    }

    private String getRolName(Connection conn, BSExecutor exec, UserBean user) throws BSException {
        String sql = "SELECT cName FROM tRol WHERE cDeleted = 0 AND cID = ?";
        String out = exec.queryField(conn, sql, BSStaticManager.buildArrayObject(user.getRol()));
        sql = null;
        return out;
    }
}