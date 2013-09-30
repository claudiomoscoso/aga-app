package cl.builderSoft.framework.user.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.dom4j.Element;
import org.dom4j.Node;

import cl.builderSoft.framework.database.BSExecutor;
import cl.builderSoft.framework.exception.BSDataBaseException;
import cl.builderSoft.framework.exception.BSException;
import cl.builderSoft.framework.mvc.ioService.BSServiceData;
import cl.builderSoft.framework.service.BSAbstractProcess;
import cl.builderSoft.framework.service.BSProcess;
import cl.builderSoft.framework.user.UserService;
import cl.builderSoft.framework.util.BSConfigManager;
import cl.builderSoft.framework.util.BSFactory;
import cl.builderSoft.framework.util.BSStaticManager;

public class GetMenuToSession extends BSAbstractProcess implements BSProcess {
    private static String SQL = "SELECT cID FROM tR_RolOption WHERE cOption=? AND cRol=?";

    private Connection conn = null;

    public BSServiceData execute(BSServiceData serviceData) throws BSException {
        BSConfigManager configManager = BSConfigManager.getInstance();
        if(serviceData.existsSessionField("Menu")){
            return null;
        }
        String rol = serviceData.getRequestFieldString("fldRol", serviceData.getSessionFieldString("User/cRol", null));

        //        BSLog.debug("ROL: [" + rol + "]");

        conn = BSFactory.getConnectionManager().getConnection(UserService.ALIAS);
        BSExecutor exec = BSFactory.getExecutor(UserService.ALIAS);

        Node menu = configManager.getPropertyNode("Menu");

        List options = menu.selectNodes("*");
        for (int i = 0; i < options.size(); i++) {
            checkOption((Node) options.get(i), rol, exec);
        }

        serviceData.addSessionNode(menu);

        BSFactory.getConnectionManager().releaseConnection(conn, UserService.ALIAS);

        return null;
    }

    private void checkOption(Node option, String rol, BSExecutor exec) throws BSException {
        Object[] params = BSStaticManager.buildArrayObject(option.selectSingleNode("./@ID").getText(), rol);
        boolean check = false;

        try {
            ResultSet rs = exec.queryResultSet(conn, GetMenuToSession.SQL, params);

            check = rs.next();
            rs.close();

            exec.closeStatement();

        } catch (BSDataBaseException e) {
            check = false;
        } catch (SQLException e) {
            check = false;
        }

        Element e = ((Element) option);
        e.addAttribute("Selected", check ? "1" : "0");

        List options = option.selectNodes("./*");
        for (int i = 0; i < options.size(); i++) {
            checkOption((Node) options.get(i), rol, exec);
        }
    }

    public BSServiceData executeTransaction(BSServiceData serviceData, Connection conn) throws BSException {
        return null;
    }
}