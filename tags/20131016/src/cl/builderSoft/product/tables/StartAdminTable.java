package cl.builderSoft.product.tables;

import java.sql.Connection;

import cl.builderSoft.framework.exception.BSException;
import cl.builderSoft.framework.mvc.ioService.BSServiceData;
import cl.builderSoft.framework.service.BSProcess;

/**
 * @author cmoscoso
 */
public class StartAdminTable extends AbstractTablesUtils implements BSProcess {

    public BSServiceData execute(BSServiceData serviceData) throws BSException {
        String tableName = serviceData.getRequestFieldString("Table", null);
        String appName = serviceData.getRequestFieldString("App", null);

        setValueToSession(serviceData, TABLE_NAME, tableName);
        setValueToSession(serviceData, APP_NAME, appName);

        return null;
    }

    private void setValueToSession(BSServiceData serviceData, String name, String value) throws BSException {
        if (serviceData.existsSessionField(name)) {
            serviceData.setSessionField(name, value);
        } else {
            serviceData.addSessionField(name, value);
        }
    }

    public BSServiceData executeTransaction(BSServiceData serviceData, Connection conn) throws BSException {

        return null;
    }
}