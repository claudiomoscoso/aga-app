package cl.builderSoft.aga.certificate.solicitud.service;

import java.sql.Connection;

import org.dom4j.Element;

import cl.builderSoft.framework.exception.BSException;
import cl.builderSoft.framework.log.BSLog;
import cl.builderSoft.framework.mvc.ioService.BSServiceData;
import cl.builderSoft.framework.service.BSAbstractProcess;
import cl.builderSoft.framework.service.BSProcess;

public class SaveAnalisisComponent extends BSAbstractProcess implements BSProcess {

	public BSServiceData execute(BSServiceData serviceData) throws BSException {
		// BSLog.debug(serviceData.getDocument().asXML());
		String index = serviceData.getRequestFieldString("Index", null);
		String requerido = serviceData.getRequestFieldString("Requerido", "");
		String analisis = serviceData.getRequestFieldString("Analisis", "");
		String desviacionRelativa = serviceData.getRequestFieldString("DesviacionRelativa", "");
		String desviacionAbsoluta = serviceData.getRequestFieldString("DesviacionAbsoluta", "");

		String path = "/Service/Session/ListComponentByProduct/Record[@ID='" + index + "']";

		Element listComponentByProduct = (Element) serviceData.getDocument().selectSingleNode(path);

		listComponentByProduct.element("cRequerido").setText(requerido);
		listComponentByProduct.element("cAnalisis").setText(analisis);
		listComponentByProduct.element("cDesviacionRelativa").setText(desviacionRelativa);
		listComponentByProduct.element("cDesviacionAbsoluta").setText(desviacionAbsoluta);

		// BSLog.debug(serviceData.getDocument().asXML());
		return null;
	}

	public BSServiceData executeTransaction(BSServiceData serviceData, Connection conn) throws BSException {
		return null;
	}

}
