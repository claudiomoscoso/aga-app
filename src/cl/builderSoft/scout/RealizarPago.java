package cl.builderSoft.scout;

import java.sql.Connection;
import java.util.Calendar;

import cl.builderSoft.framework.database.BSExecutor;
import cl.builderSoft.framework.exception.BSException;
import cl.builderSoft.framework.exception.BSProgrammerException;
import cl.builderSoft.framework.mvc.ioService.BSServiceData;
import cl.builderSoft.framework.service.BSAbstractProcess;
import cl.builderSoft.framework.service.BSProcess;
import cl.builderSoft.framework.util.BSFactory;
import cl.builderSoft.framework.util.BSStaticManager;

/**
 * @author cmoscoso
 */
public class RealizarPago extends BSAbstractProcess implements BSProcess {

    public BSServiceData execute(BSServiceData serviceData) throws BSException {
        Connection conn = BSFactory.getConnectionManager().getConnection("SCO");
        BSFactory.getConnectionManager().setAutoCommit(conn, false);
        try {
            executeTransaction(serviceData, conn);
            BSFactory.getConnectionManager().commit(conn);
        } catch (BSException e) {
            BSFactory.getConnectionManager().rollback(conn);
            throw e;
        }

        BSFactory.getConnectionManager().releaseConnection(conn, "SCO");
        return null;
    }

    public BSServiceData executeTransaction(BSServiceData serviceData, Connection conn) throws BSException {
        String[] scoutsArray = serviceData.getRequestFieldArray("fldScout", new String[0]);
        int size = scoutsArray.length;

        BSExecutor exec = BSFactory.getExecutor("SCO");
        ConsolidadoGrupos consolidado = new ConsolidadoGrupos();

        for (int i = 0; i < size; i++) {
            realizarPago(conn, exec, scoutsArray[i], consolidado);
        }

        return null;
    }

    private void realizarPago(Connection conn, BSExecutor exec, String rut, ConsolidadoGrupos consolidado) throws BSException {
        String sql = "UPDATE tScout SET cFechaPago=?, cValorPago=? WHERE cRUT=?";
        int valor = getValorCargo(conn, exec, rut, consolidado);
        Calendar calendar = Calendar.getInstance();

        exec.update(conn, sql, BSStaticManager.buildArrayObject(calendar, new Integer(valor), rut));
        exec.closeStatement();
    }

    private int getValorCargo(Connection conn, BSExecutor exec, String rut, ConsolidadoGrupos consolidado) throws BSException {
        String sql = "SELECT g.cCategoria FROM tScout AS s LEFT JOIN tGrupo AS g ON s.cGrupo = g.cID WHERE s.cRUT = ?;";
        Calendar calendar = Calendar.getInstance();
        String categoria = exec.queryField(conn, sql, BSStaticManager.buildArrayObject(rut));
        exec.closeStatement();
        
        String valorString = consolidado.getValorCategoria(conn, exec, categoria, calendar);
        int out = 0;

        try {
            out = Integer.parseInt(valorString);
        } catch (NumberFormatException e) {
            throw new BSProgrammerException("Valor de categoria para [" + rut + "] no corresponde.", false);
        }

        return out;
    }
}