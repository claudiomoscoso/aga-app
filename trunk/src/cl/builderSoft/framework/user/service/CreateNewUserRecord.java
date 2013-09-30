package cl.builderSoft.framework.user.service;

import java.sql.Connection;
import java.util.Calendar;

import cl.builderSoft.framework.database.BSExecutor;
import cl.builderSoft.framework.exception.BSException;
import cl.builderSoft.framework.exception.BSUserException;
import cl.builderSoft.framework.log.BSLog;
import cl.builderSoft.framework.mvc.ioService.BSServiceData;
import cl.builderSoft.framework.service.BSAbstractProcess;
import cl.builderSoft.framework.service.BSProcess;
import cl.builderSoft.framework.user.UserBean;
import cl.builderSoft.framework.user.UserService;
import cl.builderSoft.framework.user.UserServiceImpl;
import cl.builderSoft.framework.util.BSDateTimeUtil;
import cl.builderSoft.framework.util.BSFactory;
import cl.builderSoft.framework.util.BSSecurity;
import cl.builderSoft.framework.util.BSStaticManager;

public class CreateNewUserRecord extends BSAbstractProcess implements BSProcess {
	public BSServiceData execute(BSServiceData serviceData) throws BSException {
		Connection conn = BSFactory.getConnectionManager().getConnection(UserService.ALIAS);
		executeTransaction(serviceData, conn);
		BSFactory.getConnectionManager().releaseConnection(conn, UserService.ALIAS);
		return null;
	}

	public BSServiceData executeTransaction(BSServiceData serviceData, Connection conn) throws BSException {
		String userCreator = null;

		try {
			userCreator = serviceData.getSessionFieldString("User/cID", null);
		} catch (BSException e) {
			throw new BSUserException("No se encuentra ID de usuario creador", true);
		}

		String login = serviceData.getRequestFieldString("fldLogin", null);
		String name = serviceData.getRequestFieldString("fldName", null);
		String mail = serviceData.getRequestFieldString("fldMail", null);
		String rol = serviceData.getRequestFieldString("fldRol", null);
		String phone = serviceData.getRequestFieldString("fldPhone", null);
		String movil = serviceData.getRequestFieldString("fldMovil", null);
		String password1 = serviceData.getRequestFieldString("fldPassword1", null);
		String password2 = serviceData.getRequestFieldString("fldPassword2", null);

		UserService userFunction = new UserServiceImpl();
		UserBean user = userFunction.searchByLogin(login);

		if (!password1.equals(password2)) {
			throw new BSUserException("La passwords no coinciden, reintente", true);
		} else {
			if (user == null) {
				String sql = "INSERT tUser(cID, cLogin, cName, cMail, cRol, cPhone, cMovil, cDeleted, ";
				sql += "cCreationDate, cCreatorID, cInfo, cPassword, cEnable) ";
				sql += "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";

				// Connection conn =
				// BSFactory.getConnectionManager().getConnection(UserService.ALIAS);
				BSExecutor exec = BSFactory.getExecutor(UserService.ALIAS);

				BSSecurity sec = new BSSecurity();
				password1 = sec.cript(password1);
				sec = null;

				Object[] params = new Object[13];
				params[0] = exec.getLastCode(conn, "tUser");
				exec.closeStatement();
				params[1] = login;
				params[2] = name;
				params[3] = mail;
				params[4] = rol;
				params[5] = phone;
				params[6] = movil;
				params[7] = new Boolean(false);
				params[8] = BSDateTimeUtil.calendarToSQL(Calendar.getInstance());
				params[9] = userCreator;
				params[10] = "";
				params[11] = password1;
				params[12] = new Boolean(true);

				exec.update(conn, sql, params);
				exec.closeStatement();
				BSFactory.getConnectionManager().commit(conn);

				// BSFactory.getConnectionManager().releaseConnection(conn,
				// UserService.ALIAS);
				serviceData.addResponseField("NewUserID", (String) params[0]);
				BSLog.saveActionUser(serviceData, BSStaticManager.buildArrayObject(userCreator, login));
			} else {
				throw new BSUserException("Login ya esta asignado a otro usuario, favor reintente con otro", true);
			}
		}
		return null;
	}

}