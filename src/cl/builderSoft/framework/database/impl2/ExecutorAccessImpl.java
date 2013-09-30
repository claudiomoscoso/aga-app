package cl.builderSoft.framework.database.impl2;

import java.sql.Connection;
import java.sql.ResultSet;

import org.dom4j.Document;

import cl.builderSoft.framework.database.BSExecutor;
import cl.builderSoft.framework.exception.BSDataBaseException;
import cl.builderSoft.framework.exception.BSException;

public class ExecutorAccessImpl extends AbstractExecutor implements BSExecutor {

	public long getLastCode(String tableName, String moduleName) throws BSException {
		throw new BSDataBaseException("BS091");
	}

	public long getLastCode(String tableName, String moduleName, String fieldName) throws BSException {
		throw new BSDataBaseException("BS091");
	}

	public Document query(Connection conn, String sql, Object[] parameters, String nodeName) throws BSException {
		return super.query(conn, sql, parameters, nodeName);
	}

	public String queryField(Connection conn, String sql, Object[] parameter) throws BSException {
		// throw new BSDataBaseException("BS091");
		return super.queryField(conn, sql, parameter);
	}

	public Document querySP(Connection conn, String storeProcedure, Object[] parameter, String nodeName) throws BSException {
		throw new BSDataBaseException("BS091");
	}

	public boolean update(Connection conn, String sql, Object[] parameter) throws BSException {
//		throw new BSDataBaseException("BS091");
		 return super.update(conn, sql, parameter);
	}

	public boolean updateSP(Connection conn, String storeProcedure, Object[] parameter) throws BSException {
		throw new BSDataBaseException("BS091");
	}

	public void closeStatement() throws BSException {
		super.closeStatement();
	}

	public String getLastCode(Connection conn, String tableName) throws BSException {
		throw new BSDataBaseException("BS091");
	}

	public String getLastCode(Connection conn, String tableName, String fieldName) throws BSException {
		throw new BSDataBaseException("BS091");
	}

	public long getLastCodeLong(Connection conn, String tableName) throws BSException {
		throw new BSDataBaseException("BS091");
	}

	public long getLastCodeLong(Connection conn, String tableName, String fieldName) throws BSException {
		throw new BSDataBaseException("BS091");
	}

	public ResultSet queryResultSet(Connection conn, String sql, Object[] parameters) throws BSException {
		return super.queryResultSet(conn, sql, parameters);
	}

}
