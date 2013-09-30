package cl.builderSoft.product.tables;

import java.sql.Connection;

import cl.builderSoft.framework.database.BSExecutor;
import cl.builderSoft.framework.exception.BSException;
import cl.builderSoft.framework.service.BSAbstractProcess;

/**
 * @author cmoscoso
 */
abstract class AbstractTablesUtils extends BSAbstractProcess {
    protected static final String TABLE_NAME = "TableName";
    protected static final String APP_NAME = "AppName";
    private static final String CREATE_TABLE = "CREATE TABLE ";
    private static final String BODY_TABLE = " (ID BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, Name VARCHAR(100) NOT NULL, Deleted ENUM('Y','N') DEFAULT 'N');";

    protected boolean tableExists(String tableName, Connection conn, BSExecutor exec) {
        String sql = "SELECT 1 FROM " + tableName;
        boolean out = true;
        try {
            exec.queryResultSet(conn, sql, null);
        } catch (BSException e) {
            out = false;
        }

        return out;
    }

    protected void createTable(String tableName, Connection conn, BSExecutor exec) throws BSException {
        String sql = CREATE_TABLE + tableName + BODY_TABLE;
        exec.update(conn, sql, null);
        exec.closeStatement();
    }
}