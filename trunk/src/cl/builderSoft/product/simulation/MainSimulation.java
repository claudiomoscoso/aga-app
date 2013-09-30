/*
 * Created on 08-01-2008
 */
package cl.builderSoft.product.simulation;

import java.sql.Connection;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;

import cl.builderSoft.framework.exception.BSException;
import cl.builderSoft.framework.exception.BSProgrammerException;
import cl.builderSoft.framework.mvc.ioService.BSServiceData;
import cl.builderSoft.framework.service.BSAbstractProcess;
import cl.builderSoft.framework.service.BSProcess;
import cl.builderSoft.framework.util.BSConfigManager;
import cl.builderSoft.framework.util.BSConstants;
import cl.builderSoft.framework.util.BSStaticManager;
import cl.builderSoft.framework.util.BSXMLManager;

/**
 * @author cmoscoso
 */
public class MainSimulation extends BSAbstractProcess implements BSProcess {

	public BSServiceData execute(BSServiceData serviceData) throws BSException {
		return executeTransaction(serviceData, null);
	}

	public BSServiceData executeTransaction(BSServiceData serviceData, Connection conn) throws BSException {
		String processName = serviceData.getProcessName();
		String serviceName = serviceData.getServiceName();
		Document serviceDataDocument = serviceData.getDocument();
		String sessionID = serviceDataDocument.selectSingleNode("/Service/Session/@ID").getText();

		String pathFile = BSConfigManager.getConfigPath() + "Simulation" + BSConstants.FILE_SEPARATOR + processName
				+ ".xml";
		String fileContent = (String) BSStaticManager.getFileContent(pathFile);

		Document fileDocument = null;

		try {
			fileDocument = BSXMLManager.stringToDocument(fileContent);
		} catch (BSProgrammerException e) {
			throw new BSProgrammerException("No se pudo interpretar correctamente el archivo [" + pathFile + "]", true);
		}

		putSimulationInResponse(serviceDataDocument, fileDocument);

		/**
		 * <code>
        serviceDataDocument.clearContent();
        serviceDataDocument.add((Element)fileDocument.getRootElement().clone());

        serviceData.setProcessName(processName);
        serviceData.setServiceName(serviceName);
        serviceDataDocument.selectSingleNode("/Service/Session/@ID").setText(sessionID);
        </code>
		 */
		return null;
	}

	private void putSimulationInResponse(Document serviceDataDocument, Document fileDocument) {
		List nodeList = fileDocument.selectNodes("/Service/Response/Fields/*");
		Element object = null;
		Element targetElement = (Element) serviceDataDocument.selectSingleNode("/Service/Response/Fields");

		for (Iterator iterator = nodeList.iterator(); iterator.hasNext();) {
			object = (Element) iterator.next();
			targetElement.add((Element) object.clone());
		}

	}

}