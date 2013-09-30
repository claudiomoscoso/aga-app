/*
 * Created on 17-05-2007
 */
package cl.builderSoft.framework.user.service;

import java.sql.Connection;

import org.dom4j.Node;

import cl.builderSoft.framework.exception.BSException;
import cl.builderSoft.framework.mvc.ioService.BSServiceData;
import cl.builderSoft.framework.service.BSAbstractProcess;
import cl.builderSoft.framework.service.BSProcess;
import cl.builderSoft.framework.user.UserService;
import cl.builderSoft.framework.util.BSConfigManager;

/**
 * @author cmoscoso
 */
public class GetFields extends BSAbstractProcess implements BSProcess {

    public BSServiceData execute(BSServiceData serviceData) throws BSException {
        Node fields = BSConfigManager.getInstance().getPropertyNode(UserService.ALIAS, "/Module/Fields");
        serviceData.addResponseNode((Node)fields.clone());
        return null;
    }

    public BSServiceData executeTransaction(BSServiceData serviceData, Connection conn) throws BSException {
        return null;
    }
}