package cl.builderSoft.framework.service;

import java.sql.Connection;

import cl.builderSoft.framework.exception.BSException;
import cl.builderSoft.framework.mvc.ioService.BSServiceData;
import cl.builderSoft.framework.util.BSConfigManager;
import cl.builderSoft.framework.util.BSStaticManager;

public class Simulation extends BSAbstractProcess implements BSProcess {

	public BSServiceData execute(BSServiceData serviceData) throws BSException {
		String realPath = BSConfigManager.getConfigPath();

		BSStaticManager.getFileContent(realPath + serviceData.getProcessName()
				+ ".xml");

		return null;
	}

	public BSServiceData executeTransaction(BSServiceData serviceData,
			Connection conn) throws BSException {

		return null;
	}

}
