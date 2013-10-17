package cl.builderSoft.framework.database.impl2;

import java.sql.Connection;

import cl.builderSoft.framework.database.BSExecutor;
import cl.builderSoft.framework.exception.BSException;
import cl.builderSoft.framework.exception.BSProgrammerException;

public class BSAbstractDataService {

	protected void getById(BSExecutor exec, Connection conn, String id) throws BSException {
		String[] fields = this.getFieldNames();
		String table = getTableName();
		String keyField = getPKField();

		String sql = "SELECT " + fieldsToList(fields) + " FROM " + table + " WHERE " + keyField + "=?";

	}

	private String fieldsToList(String[] fields) {
		String out = null;
		
		
		
		
		for (int i = 0; i < fields.length; i++) {
			
		}
		return out;
	}

	private String getPKField() throws BSException {
		throw new BSProgrammerException("getPKField() not implemented", false);
	}

	private String[] getFieldNames() throws BSProgrammerException {
		throw new BSProgrammerException("getFieldNames() not implemented", false);
	}

	private String getTableName() throws BSProgrammerException {
		throw new BSProgrammerException("getTableNames() not implemented", false);
	}

}
