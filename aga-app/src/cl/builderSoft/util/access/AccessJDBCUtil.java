package cl.builderSoft.util.access;

import java.io.File;
import java.sql.DriverManager;
import java.sql.SQLException;

import cl.builderSoft.framework.exception.BSConfigurationException;

public class AccessJDBCUtil {
	private static final String accessDBURLPrefix = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=";
	private static final String accessDBURLSuffix = ";DriverID=22;READONLY=true";
	static {
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		} catch (ClassNotFoundException e) {
			System.err.println("JdbcOdbc Bridge Driver not found!");
			// ABORT ABORT... How? System.exit(1) is not nice from webapp...
		}
	}

	/**
	 * Creates a Connection to a Access Database
	 * 
	 * @throws BSConfigurationException
	 */

	public static java.sql.Connection getAccessDBConnection(String filename) throws SQLException, BSConfigurationException {
		return getAccessDBConnection(filename, "", "");
	}

	public static java.sql.Connection getAccessDBConnection(String filename, String user, String password) throws SQLException,
			BSConfigurationException {
		filename = filename.replace('\\', '/').trim();

		// if (!exists(filename)) {
		// throw new BSConfigurationException("El archivo [" + filename +
		// "] no existe");
		// }

		String databaseURL = accessDBURLPrefix + filename + accessDBURLSuffix;/** + ";logonuser=" + user + ";logonpassword:"
				+ password+";";*/
		System.err.println("Datebase URL: " + databaseURL);
		return DriverManager.getConnection(databaseURL, user, password);
	}

	private static boolean exists(String filename) {
		File file = new File(filename);
		boolean out = file.exists();
		file = null;
		return out;
	}
}