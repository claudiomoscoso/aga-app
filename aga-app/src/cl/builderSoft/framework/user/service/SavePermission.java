package cl.builderSoft.framework.user.service;

import java.sql.Connection;
import java.util.List;

import org.dom4j.Node;

import cl.builderSoft.framework.database.BSExecutor;
import cl.builderSoft.framework.exception.BSDataBaseException;
import cl.builderSoft.framework.exception.BSException;
import cl.builderSoft.framework.exception.BSProgrammerException;
import cl.builderSoft.framework.log.BSLog;
import cl.builderSoft.framework.mvc.ioService.BSServiceData;
import cl.builderSoft.framework.service.BSAbstractProcess;
import cl.builderSoft.framework.service.BSProcess;
import cl.builderSoft.framework.user.UserService;
import cl.builderSoft.framework.util.BSFactory;
import cl.builderSoft.framework.util.BSStaticManager;

public class SavePermission extends BSAbstractProcess implements BSProcess {
	public BSServiceData execute(BSServiceData serviceData) throws BSException {
		BSExecutor executor = BSFactory.getExecutor(UserService.ALIAS);
		Connection conn = BSFactory.getConnectionManager().getConnection(UserService.ALIAS);
		String userlogon = serviceData.getUserLogon();

		String hasError = "";
		BSFactory.getConnectionManager().setAutoCommit(conn, false);

		String rol = serviceData.getRequestFieldString("fldRol", null);
		String sql = "DELETE FROM tR_RolOption WHERE cRol='" + rol + "'";

		try {
			executor.update(conn, sql, null);
		} catch (BSDataBaseException e) {
			hasError = e.getMessage();
		} catch (BSProgrammerException e) {
			hasError = e.getMessage();
		} finally {
			executor.closeStatement();
		}

		if (hasError.length() == 0) {
			sql = "INSERT INTO tR_RolOption(cID, cRol, cOption) VALUES(?,?,?)";

			List options = serviceData.getDocument().selectNodes("/Service/Request/Fields/fldOption");
			Node option = null;
			Object[] params = null;
			for (int i = 0; i < options.size(); i++) {
				option = (Node) options.get(i);

				params = BSStaticManager.buildArrayObject(executor.getLastCode(conn, "tR_RolOption"), rol, option
						.getText());
				executor.closeStatement();
				try {
					executor.update(conn, sql, params);
				} catch (BSDataBaseException e) {
					hasError = e.getMessage();
				} catch (BSProgrammerException e) {
					hasError = e.getMessage();
				} finally {
					executor.closeStatement();
				}
			}
			if (hasError.length() == 0) {
				BSLog.saveActionUser(serviceData, BSStaticManager.buildArrayObject(userlogon, rol));
			}
		}
		if (hasError.length() == 0) {
			BSFactory.getConnectionManager().commit(conn);
		} else {
			BSFactory.getConnectionManager().rollback(conn);
			throw new BSDataBaseException(hasError, false);
		}

		BSFactory.getConnectionManager().releaseConnection(conn, UserService.ALIAS);

		return null;
	}

	public BSServiceData executeTransaction(BSServiceData serviceData, Connection conn) throws BSException {

		return null;
	}
}