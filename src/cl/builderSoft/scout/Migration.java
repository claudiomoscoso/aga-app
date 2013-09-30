/*
 * Created on 25-05-2007
 */
package cl.builderSoft.scout;

import java.sql.Connection;
import java.sql.ResultSet;

import cl.builderSoft.framework.database.BSConnectionManager;
import cl.builderSoft.framework.database.BSExecutor;
import cl.builderSoft.framework.exception.BSException;
import cl.builderSoft.framework.mvc.ioService.BSServiceData;
import cl.builderSoft.framework.service.BSAbstractProcess;
import cl.builderSoft.framework.service.BSProcess;
import cl.builderSoft.framework.util.BSFactory;

/**
 * @author cmoscoso
 */
public class Migration extends BSAbstractProcess implements BSProcess {
    private static final String[] tables = { "tApoderado", "tCargo", "tCategoria", "tComuna", "tDistrito", "tDYG", "tGrupo",
            "tMMBB", "tNacionalidad", "tPersona", "tR_DYG_Cargo", "tReligion", "tScout", "tUnidad", "tZona" };

    public BSServiceData execute(BSServiceData serviceData) throws BSException {
        BSConnectionManager connectionManager = BSFactory.getConnectionManager();
        Connection connDesa = connectionManager.getConnection("SCO");
        Connection connProd = connectionManager.getConnection("PRD");
        connectionManager.setAutoCommit(connDesa, false);
        connectionManager.setAutoCommit(connProd, false);
        BSExecutor execDesa = BSFactory.getExecutor("SCO");
        BSExecutor execProd = BSFactory.getExecutor("PRD");
//        Object[] columns = null;

        try {
            for (int i = 0; i < tables.length; i++) {
                //                columns = getColumns(connProd, execProd, tables[i]);
                clearTable(connDesa, execDesa, tables[i]);
                ResultSet dataProduction = getDataFromProduction(connProd, execProd, tables[i]);
                saveToDesa(dataProduction, tables[i]);
            }
            connectionManager.commit(connProd);
            connectionManager.commit(connDesa);
        } catch (BSException e) {
            connectionManager.rollback(connProd);
            connectionManager.rollback(connDesa);
            throw e;
        } finally {
            connectionManager.setAutoCommit(connDesa, true);
            connectionManager.setAutoCommit(connProd, true);
            connectionManager.releaseConnection(connDesa, "SCO");
            connectionManager.releaseConnection(connDesa, "PRD");
        }

        return null;
    }

    private void clearTable(Connection connDesa, BSExecutor execDesa, String tableName) throws BSException {
        //execDesa.update(connDesa, "DELETE FROM " + tableName, null);
    }

    private void saveToDesa(ResultSet dataProduction, String tableName) {

    }

    private ResultSet getDataFromProduction(Connection connProd, BSExecutor execProd, String tableName) throws BSException {
        ResultSet out = execProd.queryResultSet(connProd, "SELECT * FROM " + tableName, null);
        execProd.closeStatement();
        return out;
    }

    /**
     * <code>
     private Object[] getColumns(Connection conn, BSExecutor exec, String tableName) throws BSException {
     String sql = "SELECT * FROM " + tableName;
     String [] out =null;
     ResultSet result = exec.queryResultSet(conn, sql, null);

     ResultSetMetaData metaData = null;
     int cols = 0;
     try {
     metaData = result.getMetaData();
     cols = metaData.getColumnCount();
     out[cols]= new String[cols];  

     for (int i = 1; i < cols + 1; i++) {
     
     fieldName = metaData.getColumnName(i);
     
     } catch (SQLException e) {
     throw new BSDataBaseException(e.getMessage(), false);
     }

     return null;
     
     }  </code>
     */

    public BSServiceData executeTransaction(BSServiceData serviceData, Connection conn) throws BSException {

        return null;
    }
}