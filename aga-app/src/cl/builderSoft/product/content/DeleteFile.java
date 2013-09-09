/*
 * Created on 21-06-2007
 */
package cl.builderSoft.product.content;

import java.io.File;
import java.sql.Connection;

import cl.builderSoft.framework.database.BSExecutor;
import cl.builderSoft.framework.exception.BSException;
import cl.builderSoft.framework.exception.BSSystemException;
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
public class DeleteFile extends BSAbstractProcess implements BSProcess {

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

    public BSServiceData executeTransaction(BSServiceData serviceData, Connection conn) throws BSException {
        String dir = BSConfigManager.getRootPath() + "public" + BSConstants.FILE_SEPARATOR + "ACO"
                + BSConstants.FILE_SEPARATOR;

        String id = serviceData.getRequestFieldString("fldID", null);
        BSExecutor exec = BSFactory.getExecutor("ACO");
        String fileName = getFileName(id, conn, exec);

        // File (or directory) to be moved
        File file = new File(dir + fileName);

        // Move file to new directory

        deleteFile(id, conn, exec);

        boolean success = file.delete();

        file = null;
        dir = null;
        exec = null;
        id = null;

        if (!success) {
            BSLog.debug("No se pudo borrar el archivo: [" + (dir + fileName) + "]");
            throw new BSSystemException("No se pudo borrar el archivo " + fileName, false);
        }
        fileName = null;
        return null;
    }

    private void deleteFile(String id, Connection conn, BSExecutor exec) throws BSException {
        String sql = "DELETE FROM tFile WHERE cID = ?";
        exec.update(conn, sql, BSStaticManager.buildArrayObject(id));
        exec.closeStatement();

    }

    private String getFileName(String id, Connection conn, BSExecutor exec) throws BSException {
        String sql = "SELECT cFileName FROM tFile WHERE cID = ?";
        String out = exec.queryField(conn, sql, BSStaticManager.buildArrayObject(id));
        exec.closeStatement();
        return out;
    }

}