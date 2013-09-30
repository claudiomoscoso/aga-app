/*
 * Created on 17-05-2007
 */
package cl.builderSoft.framework.user.service;

import java.sql.Connection;
import java.util.List;

import org.dom4j.Node;

import cl.builderSoft.framework.exception.BSException;
import cl.builderSoft.framework.mvc.ioService.BSServiceData;
import cl.builderSoft.framework.service.BSAbstractProcess;
import cl.builderSoft.framework.service.BSProcess;
import cl.builderSoft.framework.user.UserBean;
import cl.builderSoft.framework.user.UserService;
import cl.builderSoft.framework.user.UserServiceImpl;
import cl.builderSoft.framework.util.BSFactory;

/**
 * @author cmoscoso
 */
public class CreateNewUserField extends BSAbstractProcess implements BSProcess {

    public BSServiceData execute(BSServiceData serviceData) throws BSException {
        Connection conn = BSFactory.getConnectionManager().getConnection(UserService.ALIAS);
        executeTransaction(serviceData, conn);
        BSFactory.getConnectionManager().releaseConnection(conn, UserService.ALIAS);
        return null;
    }

    public BSServiceData executeTransaction(BSServiceData serviceData, Connection conn) throws BSException {
        List fields = serviceData.getDocument().selectNodes("/Service/Request/Fields/*");
        Node fieldNode = null;
        String fieldNameString = null;
        String fieldValueString = null;
        int size = fields.size();
        String idUser = serviceData.getDocument().selectSingleNode("/Service/Response/Fields/NewUserID").getText();

        UserService userService = new UserServiceImpl();
        UserBean user = userService.searchById(idUser);

        //        BSLog.debug(serviceData.getDocument().asXML());

        for (int i = 0; i < size; i++) {
            fieldNode = (Node) fields.get(i);
            fieldNameString = fieldNode.getName();
            fieldValueString = fieldNode.getText();

            if (fieldNameString.startsWith("prp")) {
                userService.setAttribute(user, fieldNameString, fieldValueString);
            }
        }
        userService.saveUser(user);

        return null;
    }
}