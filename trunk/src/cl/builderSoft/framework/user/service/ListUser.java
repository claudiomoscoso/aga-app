package cl.builderSoft.framework.user.service;

import java.sql.Connection;

import cl.builderSoft.framework.database.BSExecutor;
import cl.builderSoft.framework.exception.BSException;
import cl.builderSoft.framework.mvc.ioService.BSServiceData;
import cl.builderSoft.framework.service.BSAbstractProcess;
import cl.builderSoft.framework.service.BSProcess;
import cl.builderSoft.framework.user.UserService;
import cl.builderSoft.framework.util.BSConfigManager;
import cl.builderSoft.framework.util.BSFactory;

public class ListUser extends BSAbstractProcess implements BSProcess {
	// public BSResponse execute(BSRequest request) throws BSException {
	public BSServiceData execute(BSServiceData serviceData) throws BSException {
		// BSResponse response = BSFactory.getBSResponse(request);

		String sql = "SELECT U.cID, U.cLogin, U.cName, U.cMail, U.cRol, U.cPhone, U.cMovil, U.cDeleted, U.cCreationDate, U.cCreatorID, U.cInfo, U.cPassword, U.cEnable, R.cName AS RolName ";
		sql += "FROM tUser as U ";
		sql += "LEFT JOIN tRol AS R on U.cRol = R.cID ";
		sql += "WHERE U.cDeleted=0 AND U.cID!='" + serviceData.getSessionFieldString("User/cID", null) + "' ";

		String systemAdministratorRol = getSystemAdministratorRol();

		if (!isAdministrator(serviceData, systemAdministratorRol)) {
			sql += "AND U.cRol != '" + systemAdministratorRol + "'";
		}
		sql += " ORDER BY U.cName";
		Connection conn = BSFactory.getConnectionManager().getConnection(UserService.ALIAS);
		BSExecutor exec = BSFactory.getExecutor(UserService.ALIAS);

		// response.addField(exec.executeQueryDocument(sql, null, conn,
		// "listUser").getRootElement());
		serviceData.addResponseNode(exec.query(conn, sql, null, "listUser").getRootElement());
		exec.closeStatement();
		BSFactory.getConnectionManager().releaseConnection(conn, UserService.ALIAS);

		return null;
	}

	protected boolean isAdministrator(BSServiceData serviceData) throws BSException {
		return isAdministrator(serviceData, getSystemAdministratorRol());
	}

	protected boolean isAdministrator(BSServiceData serviceData, String systemAdministratorRol) throws BSException {
		boolean out = false;
		String rolUser = serviceData.getSessionFieldString("User/cRol", null);
//		systemAdministratorRol = getSystemAdministratorRol();
		out =  rolUser.equals(systemAdministratorRol);

		return out;
	}

	protected String getSystemAdministratorRol() throws BSException {
		return BSConfigManager.getInstance().getProperty(UserService.ALIAS, "/Module/SystemAdministratorRol", "");

	}

	public BSServiceData executeTransaction(BSServiceData serviceData, Connection conn) throws BSException {
		return null;
	}

}