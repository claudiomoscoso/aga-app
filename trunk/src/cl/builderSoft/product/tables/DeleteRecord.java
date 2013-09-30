package cl.builderSoft.product.tables;

import java.sql.Connection;

import cl.builderSoft.framework.database.BSConnectionManager;
import cl.builderSoft.framework.database.BSExecutor;
import cl.builderSoft.framework.exception.BSException;
import cl.builderSoft.framework.mvc.ioService.BSServiceData;
import cl.builderSoft.framework.service.BSProcess;
import cl.builderSoft.framework.util.BSFactory;
import cl.builderSoft.framework.util.BSStaticManager;

/**
 * @author cmoscoso
 */
public class DeleteRecord extends AbstractTablesUtils implements BSProcess {

    public BSServiceData execute(BSServiceData serviceData) throws BSException {
        String app = serviceData.getSessionFieldString(APP_NAME, null);

        BSConnectionManager connMgr = BSFactory.getConnectionManager();
        Connection conn = connMgr.getConnection(app);

        executeTransaction(serviceData, conn);

        connMgr.releaseConnection(conn, app);

        return null;
    }

    public BSServiceData executeTransaction(BSServiceData serviceData, Connection conn) throws BSException {
        String tableName = serviceData.getSessionFieldString(TABLE_NAME, null);
        String sql =  "UPDATE " + tableName + " SET Deleted=? WHERE ID=?";
        String app = serviceData.getSessionFieldString(APP_NAME, null);
        String id = serviceData.getRequestFieldString("fldID", null);
        BSExecutor exec = BSFactory.getExecutor(app);

        exec.update(conn, sql, BSStaticManager.buildArrayObject("Y", id));
        exec.closeStatement();

        exec = null;
        return null;
    }
}