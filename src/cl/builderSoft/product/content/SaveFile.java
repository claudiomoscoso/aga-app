/*
 * Created on 19-06-2007
 
 */
package cl.builderSoft.product.content;

import java.io.File;
import java.sql.Connection;

import cl.builderSoft.framework.database.BSExecutor;
import cl.builderSoft.framework.exception.BSException;
import cl.builderSoft.framework.exception.BSUserException;
import cl.builderSoft.framework.log.BSLog;
import cl.builderSoft.framework.mvc.ioService.BSServiceData;
import cl.builderSoft.framework.service.BSAbstractProcess;
import cl.builderSoft.framework.service.BSProcess;
import cl.builderSoft.framework.util.BSConfigManager;
import cl.builderSoft.framework.util.BSConstants;
import cl.builderSoft.framework.util.BSFactory;
import cl.builderSoft.framework.util.BSStaticManager;

/**
 * @author cmoscoso
 */
public class SaveFile extends BSAbstractProcess implements BSProcess {

    public BSServiceData execute(BSServiceData serviceData) throws BSException {
        boolean success = true;
        BSException ex = null;
        Connection conn = BSFactory.getConnectionManager().getConnection("ACO");

        BSFactory.getConnectionManager().setAutoCommit(conn, false);
        try {
            executeTransaction(serviceData, conn);
        } catch (BSException e) {
            success = false;
            ex = e;
        }

        if (success) {
            success = saveFile(serviceData);
        }

        if (success) {
            BSFactory.getConnectionManager().commit(conn);
            BSFactory.getConnectionManager().setAutoCommit(conn, true);
            BSFactory.getConnectionManager().releaseConnection(conn, "ACO");
        } else {
            BSFactory.getConnectionManager().rollback(conn);
            BSFactory.getConnectionManager().setAutoCommit(conn, true);
            BSFactory.getConnectionManager().releaseConnection(conn, "ACO");
            throw ex;
        }

        return null;
    }

    private boolean saveFile(BSServiceData serviceData) throws BSException {//D:\\BS\\workspace2\\user\\WebContent\\public\\ACO
        String target = BSConfigManager.getRootPath() + "public" + BSConstants.FILE_SEPARATOR + "ACO";

        String source = serviceData.getRequestFieldString("fldFile", null);
        BSLog.debug(serviceData.getDocument().asXML());

        if (exists(target + BSConstants.FILE_SEPARATOR + BSStaticManager.parseFileName(source))) {
            throw new BSUserException("El archivo '" + BSStaticManager.parseFileName(source)
                    + "' ya existe, favor cargar con otro nombre", false);
        }

        // File (or directory) to be moved
        File file = new File(source);
        // Destination directory
        File dir = new File(target);
        // Move file to new directory
        boolean success = file.renameTo(new File(dir, file.getName()));
        target = null;
        source = null;
        file = null;
        dir = null;

        return success;
    }

    private boolean exists(String source) {
        File file = new File(source);
        boolean out = file.exists();
        file = null;
        return out;
    }

    public BSServiceData executeTransaction(BSServiceData serviceData, Connection conn) throws BSException {
        String sql = "INSERT INTO tFile(cID, cDescription, cFileName) VALUE(?,?,?);";

        Object[] params = new Object[3];
        BSExecutor exec = BSFactory.getExecutor("ACO");

        params[0] = exec.getLastCode(conn, "tFile");
        params[1] = serviceData.getRequestFieldString("fldDesc", null);
        params[2] = serviceData.getRequestFieldString("fldFile", null);

        params[2] = BSStaticManager.parseFileName((String) params[2]);
        BSLog.debug(params[2].toString());
        exec.update(conn, sql, params);
        exec.closeStatement();

        return null;
    }
}