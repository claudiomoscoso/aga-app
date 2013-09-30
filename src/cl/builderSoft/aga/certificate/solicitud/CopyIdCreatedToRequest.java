package cl.builderSoft.aga.certificate.solicitud;

import java.sql.Connection;

import cl.builderSoft.framework.exception.BSException;
import cl.builderSoft.framework.mvc.ioService.BSServiceData;
import cl.builderSoft.framework.service.BSAbstractProcess;
import cl.builderSoft.framework.service.BSProcess;

public class CopyIdCreatedToRequest extends BSAbstractProcess implements BSProcess {

	public BSServiceData execute(BSServiceData serviceData) throws BSException {
		serviceData.addRequestField("fldID", serviceData.getResponseString("CreateSolicitud", null));
		return null;
	}

	public BSServiceData executeTransaction(BSServiceData serviceData, Connection conn) throws BSException {

		return null;
	}

}
