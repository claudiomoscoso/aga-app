package cl.builderSoft.aga.certificate.solicitud.service;

import java.sql.Connection;
import java.util.List;

import org.dom4j.Element;
import org.dom4j.Node;

import cl.builderSoft.framework.exception.BSException;
import cl.builderSoft.framework.mvc.ioService.BSServiceData;
import cl.builderSoft.framework.service.BSAbstractProcess;
import cl.builderSoft.framework.service.BSProcess;

public class InitAnalisisComponent extends BSAbstractProcess implements
		BSProcess {

	public BSServiceData execute(BSServiceData serviceData) throws BSException {
		if (serviceData.existsSessionField("ListComponentByProduct")) {
			serviceData.removeSessionField("ListComponentByProduct");
		}
		serviceData.addSessionNode((Node) serviceData.getDocument()
				.selectSingleNode(
						"/Service/Response/Fields/ListComponentByProduct")
				.clone());

		setupIdentifyToNodes(serviceData);

		return null;
	}

	private void setupIdentifyToNodes(BSServiceData serviceData) {
		Element listElement = serviceData.getDocument().getRootElement()
				.element("Session").element("ListComponentByProduct");

		List records = listElement.elements("Record");
		Element record = null;

		for (int i = 0; i < records.size(); i++) {
			record = (Element) records.get(i);
			if (record.element("cRelleno").getTextTrim().equals("1")) {
				record.addAttribute("ID", "-1");
			} else {
				record.addAttribute("ID", "" + (i + 1));
			}
			record.addElement("cRequerido");
			record.addElement("cAnalisis");
			record.addElement("cDesviacionRelativa");
			record.addElement("cDesviacionAbsoluta");

		}

	}

	public BSServiceData executeTransaction(BSServiceData serviceData,
			Connection conn) throws BSException {
		return null;
	}

}
