package cl.builderSoft.product.content;

import java.sql.Connection;

import org.dom4j.Node;

import cl.builderSoft.framework.exception.BSException;
import cl.builderSoft.framework.mvc.ioService.BSServiceData;
import cl.builderSoft.framework.service.BSAbstractProcess;
import cl.builderSoft.framework.service.BSProcess;
import cl.builderSoft.framework.util.BSConfigManager;

public class GetFonts extends BSAbstractProcess implements BSProcess {

    public BSServiceData execute(BSServiceData serviceData) throws BSException {
        BSConfigManager configManager = BSConfigManager.getInstance();
        Node fonts = (Node) configManager.getPropertyNode("ACO", "/Module/Fonts").clone();
        serviceData.addResponseNode(fonts);
        return null;
    }

    public BSServiceData executeTransaction(BSServiceData serviceData, Connection conn) throws BSException {

        return null;
    }

}