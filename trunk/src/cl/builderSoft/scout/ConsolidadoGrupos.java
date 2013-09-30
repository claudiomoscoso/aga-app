/*
 * Created on 21-05-2007
 */
package cl.builderSoft.scout;

import java.sql.Connection;
import java.util.Calendar;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Node;

import cl.builderSoft.framework.database.BSExecutor;
import cl.builderSoft.framework.exception.BSException;
import cl.builderSoft.framework.exception.BSProgrammerException;
import cl.builderSoft.framework.mvc.ioService.BSServiceData;
import cl.builderSoft.framework.service.BSAbstractProcess;
import cl.builderSoft.framework.service.BSProcess;
import cl.builderSoft.framework.util.BSDateTimeUtil;
import cl.builderSoft.framework.util.BSFactory;
import cl.builderSoft.framework.util.BSStaticManager;

/**
 * @author cmoscoso
 */
public class ConsolidadoGrupos extends BSAbstractProcess implements BSProcess {

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
        String sql = "SELECT cID, cNombre, cCategoria, cHabilitado, 0 AS cValorCategoria, 0 AS cCantidad, 0 AS cCancelar FROM tGrupo cNombre;";
        BSExecutor exec = BSFactory.getExecutor("SCO");
        String categoria = null;
        String codigoGrupo = null;
        String valorCategoria = null;
        String cantidad = null;
        String fecha = serviceData.getDocument().selectSingleNode(BSServiceData.DATE_PATH).getText();
        Calendar fechaCalendar = BSDateTimeUtil.stringSQLToCalendar(fecha);
        fecha = null;
        Document consolidado = exec.query(conn, sql, null, "ListGrupos");

        List grupoList = consolidado.selectNodes("/ListGrupos/Record");
        Node grupoNode = null;
        int size = grupoList.size();

        for (int i = 0; i < size; i++) {
            grupoNode = (Node) grupoList.get(i);
            categoria = grupoNode.selectSingleNode("cCategoria").getText();
            codigoGrupo = grupoNode.selectSingleNode("cID").getText();

            valorCategoria = getValorCategoria(conn, exec, categoria, fechaCalendar);
            cantidad = getCantidad(conn, exec, codigoGrupo);

            grupoNode.selectSingleNode("cValorCategoria").setText(valorCategoria);
            grupoNode.selectSingleNode("cCantidad").setText(cantidad);
            grupoNode.selectSingleNode("cCancelar").setText(calculaSaldo(conn, exec, codigoGrupo));
        }

        serviceData.addResponseNode(consolidado.getRootElement());
        sql = null;
        categoria = null;
        codigoGrupo = null;
        valorCategoria = null;
        cantidad = null;
        fecha = null;
        fechaCalendar = null;
        consolidado.clearContent();
        consolidado = null;
        return null;
    }

    private String calculaSaldo(Connection conn, BSExecutor exec, String grupo) {
        String sql = "SELECT SUM( IF(cFechaPago IS NULL, 0, cValorPago) ) FROM tScout WHERE cFechaPago IS NOT NULL AND cGrupo = ?;";

        String out = null;
        try {
            out = exec.queryField(conn, sql, BSStaticManager.buildArrayObject(grupo));
        } catch (BSException e) {
            out = "0";
        }
        return out == null ? "0" : out;
    }

    private String getCantidad(Connection conn, BSExecutor exec, String codigoGrupo) {
        String sql = null; //"SELECT count(cRUT) AS cCantidad FROM tScout WHERE
                           // cGrupo = ?;";
        sql = "SELECT count(cRUT) FROM tScout WHERE cFechaPago IS NOT NULL AND cGrupo = ?;";
        String out = null;
        try {
            out = exec.queryField(conn, sql, BSStaticManager.buildArrayObject(codigoGrupo));
        } catch (BSException e) {
            out = "ERROR";
        }
        return out;
    }

    protected String getValorCategoria(Connection conn, BSExecutor exec, String categoria, Calendar fechaCalendar) throws BSProgrammerException {
        String sql = "SELECT cValor FROM tCategoria WHERE cCategoria = ? AND ? BETWEEN cFechaInicio AND cFechaTermino";
        String out = null;
        try {
            out = exec.queryField(conn, sql, BSStaticManager.buildArrayObject(categoria, fechaCalendar));
            exec.closeStatement();
        } catch (BSException e) {
            throw new BSProgrammerException("Valor de categoria [" + categoria + "] no se encontró.", false);
        }
        return out;
    }

}