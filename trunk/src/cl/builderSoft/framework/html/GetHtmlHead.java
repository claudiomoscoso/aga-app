package cl.builderSoft.framework.html;

import java.sql.Connection;

import cl.builderSoft.framework.database.BSExecutor;
import cl.builderSoft.framework.exception.BSException;
import cl.builderSoft.framework.mvc.ioService.BSServiceData;
import cl.builderSoft.framework.service.BSTag;

public class GetHtmlHead implements BSTag {

	public String execute(BSServiceData serviceData, String tagString)
			throws BSException {
		return "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">";
	}

	public Object execute(String parameter, Connection conn, BSExecutor exec)
			throws BSException {
		// TODO Auto-generated method stub
		return null;
	}

}
