package cl.builderSoft.aga.certificate.solicitud.service;

import java.sql.Connection;

import org.dom4j.Node;

import cl.builderSoft.framework.exception.BSException;
import cl.builderSoft.framework.mvc.ioService.BSServiceData;
import cl.builderSoft.framework.service.BSAbstractProcess;
import cl.builderSoft.framework.service.BSProcess;

public class ReadAnalisisComponent extends BSAbstractProcess implements
		BSProcess {

	public BSServiceData execute(BSServiceData serviceData) throws BSException {
		String index = serviceData.getRequestFieldString("Index", null);

		String xpath = "ListComponentByProduct/Record[@ID='" + index + "']";
		Node sessionNode = (Node) (serviceData.getDocument().getRootElement()
				.element("Session").clone());
		Node listComponentByProduct = sessionNode.selectSingleNode(xpath);

		serviceData.addResponseNode(listComponentByProduct);
		return null;
	}

	public BSServiceData executeTransaction(BSServiceData serviceData,
			Connection conn) throws BSException {

		return null;
	}

}
