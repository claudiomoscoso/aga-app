package cl.builderSoft.product.tables;

import java.sql.Connection;

import cl.builderSoft.framework.database.BSConnectionManager;
import cl.builderSoft.framework.database.BSExecutor;
import cl.builderSoft.framework.exception.BSException;
import cl.builderSoft.framework.exception.BSProgrammerException;
import cl.builderSoft.framework.mvc.ioService.BSServiceData;
import cl.builderSoft.framework.service.BSProcess;
import cl.builderSoft.framework.util.BSFactory;
import cl.builderSoft.framework.util.BSStaticManager;

/**
 * @author cmoscoso
 */
public class SaveRecord extends AbstractTablesUtils implements BSProcess {

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
        String sql = null;
        String app = serviceData.getSessionFieldString(APP_NAME, null);
        long id = 0;
        String name = serviceData.getRequestFieldString("fldName", null);
        boolean isNew = serviceData.getRequestFieldBoolean("fldNew", null);
        Object[] params = null;

        BSExecutor exec = BSFactory.getExecutor(app);
        if (isNew) {
            sql = "INSERT INTO " + tableName + "(Name) VALUES(?);";
//            id = exec.getLastCodeLong(conn, tableName, "ID");
            params = BSStaticManager.buildArrayObject(name);
        } else {
            sql = "UPDATE " + tableName + " SET Name=? WHERE ID=?";
            try {
                id = serviceData.getRequestFieldLong("fldID", null);
            } catch (BSException e) {
                throw new BSProgrammerException("Se indico que el registro ya existe, pero no se envio un ID de registro", false);
            }
            params = BSStaticManager.buildArrayObject(name, new Long(id));
        }

        exec.update(conn, sql, params);
        exec.closeStatement();

        exec = null;
        return null;
    }
}