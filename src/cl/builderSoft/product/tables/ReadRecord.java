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
public class ReadRecord extends AbstractTablesUtils implements BSProcess {

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
        String sql = "SELECT Name FROM " + tableName + " WHERE ID=? AND Deleted=?";
        String app = serviceData.getSessionFieldString(APP_NAME, null);
        String id = serviceData.getRequestFieldString("fldID", null);
        BSExecutor exec = BSFactory.getExecutor(app);

        serviceData.addResponseNode(exec.query(conn, sql, BSStaticManager.buildArrayObject(id, "N"), "Table").getRootElement());
        exec.closeStatement();

        exec = null;
        return null;
    }

}