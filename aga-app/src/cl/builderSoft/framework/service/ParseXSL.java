package cl.builderSoft.framework.service;

import java.sql.Connection;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;

import cl.builderSoft.framework.database.BSExecutor;
import cl.builderSoft.framework.exception.BSException;
import cl.builderSoft.framework.log.BSLog;
import cl.builderSoft.framework.mvc.ioService.BSServiceData;
import cl.builderSoft.framework.util.BSConfigManager;
import cl.builderSoft.framework.util.BSConstants;
import cl.builderSoft.framework.util.BSStaticManager;
import cl.builderSoft.framework.util.BSXMLManager;

public class ParseXSL extends BSAbstractProcess implements BSTag {
    public String execute(BSServiceData serviceData, String tagString) throws BSException {
        Document tagDocument = BSXMLManager.stringToDocument(tagString);
        String fileName = tagDocument.getRootElement().attributeValue("Filename");
        String path = BSConfigManager.getConfigPath();
        String pathFileName = path + "BStags" + BSConstants.FILE_SEPARATOR + fileName;
        String fileContent = (String)BSStaticManager.getFileContent(pathFileName);

        Document serviceDataDocument = serviceData.getDocument();
        Document serviceDataDocumentTemp = (Document) serviceDataDocument.clone();

        //        serviceDataDocumentTemp.getRootElement().add((Element)
        // tagDocument.getRootElement().clone());

        copyFromTagToServiceData(tagDocument, serviceDataDocumentTemp);

        String out = BSXMLManager.XMLmergeXSLT(serviceDataDocumentTemp.asXML(), fileContent);
        serviceDataDocumentTemp.clearContent();
        serviceDataDocumentTemp = null;

        fileContent = null;
        pathFileName = null;
        path = null;
        fileName = null;
        tagDocument = null;

        return out;
    }

    private void copyFromTagToServiceData(Document tagDocument, Document serviceDataDocument) {
        Element serviceElement = (Element) serviceDataDocument.selectSingleNode("/Service");

        List nodes = tagDocument.getRootElement().selectNodes("*");
        int len = nodes.size();
        Node node = null;

        Element tagElement = serviceElement.addElement("TAG");
        for (int i = 0; i < len; i++) {
            node = (Node) nodes.get(i);

            tagElement.add((Node)node.clone());
        }
    }

    public Object execute(String parameter, Connection conn, BSExecutor exec) throws BSException {
        BSLog.debug("Object execute()");
        return "";
    }

}