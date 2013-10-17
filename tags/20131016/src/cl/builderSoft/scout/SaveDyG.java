/*
 * Created on 15-05-2007
 */
package cl.builderSoft.scout;

import java.sql.Connection;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Node;

import cl.builderSoft.framework.database.BSExecutor;
import cl.builderSoft.framework.exception.BSDataBaseException;
import cl.builderSoft.framework.exception.BSException;
import cl.builderSoft.framework.mvc.ioService.BSServiceData;
import cl.builderSoft.framework.service.BSAbstractProcess;
import cl.builderSoft.framework.service.BSProcess;
import cl.builderSoft.framework.util.BSFactory;
import cl.builderSoft.framework.util.BSStaticManager;

/**
 * @author cmoscoso
 */
public class SaveDyG extends BSAbstractProcess implements BSProcess {
    public BSServiceData execute(BSServiceData serviceData) throws BSException {
        Connection conn = BSFactory.getConnectionManager().getConnection("SCO");

        BSFactory.getConnectionManager().setAutoCommit(conn, false);

        executeTransaction(serviceData, conn);

        BSFactory.getConnectionManager().releaseConnection(conn, "SCO");
        return null;
    }

    public BSServiceData executeTransaction(BSServiceData serviceData, Connection conn) throws BSException {
        String sql = "INSERT INTO tDYG (cID, cGentilicio, cNumeroCasilla, cEstadoCivil, cOcupacion, cEmpleador, cTitulo, cTelefonoLaboral, cAnexo, cNivelFormacion, cNumeroMiembroActivo) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        BSExecutor exec = BSFactory.getExecutor("SCO");
        boolean commit = true;
        BSException exception = null;
        String id = serviceData.getResponseString("SavePersona", null);
//        String rut = serviceData.getRequestFieldString("fldRUT", null);
        int gentilicio = serviceData.getRequestFieldInteger("fldGentilicio", null);
        String numeroCasilla = serviceData.getRequestFieldString("fldNumeroCasilla", null);
        int estadoCivil = serviceData.getRequestFieldInteger("fldEstadoCivil", null);
        String ocupacion = serviceData.getRequestFieldString("fldOcupacion", null);
        String empleador = serviceData.getRequestFieldString("fldEmpleador", null);
        String titulo = serviceData.getRequestFieldString("fldTitulo", null);
        String telefonoLaboral = serviceData.getRequestFieldString("fldTelefonoLaboral", null);
        String anexo = serviceData.getRequestFieldString("fldAnexo", null);
        int nivelFormacion = serviceData.getRequestFieldInteger("fldNivelFormacion", null);
        String numeroMiembroActivo = serviceData.getRequestFieldString("fldNumeroMiembroActivo", null);

        Object[] params = new Object[11];
        params[0] = id;
        params[1] = new Integer(gentilicio);
        params[2] = numeroCasilla;
        params[3] = new Integer(estadoCivil);
        params[4] = ocupacion;
        params[5] = empleador;
        params[6] = titulo;
        params[7] = telefonoLaboral;
        params[8] = anexo;
        params[9] = new Integer(nivelFormacion);
        params[10] = numeroMiembroActivo;

        try {
            exec.update(conn, sql, params);
        } catch (BSException e) {
            commit = false;
            exception = e;
        } finally {
            exec.closeStatement();
        }

        if (commit) {
            Document serviceDocument = serviceData.getDocument();
            List cargos = serviceDocument.selectNodes("/Service/Request/Fields/fldCargo");
            sql = "INSERT INTO tR_DYG_Cargo(cDYG, cCargo) VALUES(?, ?);";
            String sqlCargo = "UPDATE tCargo SET cPrioridad = cPrioridad + 1 WHERE cID = ?;";
            params = new Object[2];
            params[0] = id;

            for (int i = 0; i < cargos.size(); i++) {
                params[1] = new Integer(((Node) cargos.get(i)).getText());
                try {
                    exec.update(conn, sql, params);
                } catch (BSDataBaseException e) {
                    commit = false;
                    i = cargos.size() + 1;
                    exception = e;
                } finally {
                    exec.closeStatement();
                }

                try {
                    exec.update(conn, sqlCargo, BSStaticManager.buildArrayObject(params[1]));
                } catch (BSDataBaseException e) {
                    commit = false;
                    i = cargos.size() + 1;
                    exception = e;
                } finally {
                    exec.closeStatement();
                }

            }
            serviceDocument = null;
            cargos = null;
        }

        if (commit) {
            BSFactory.getConnectionManager().commit(conn);
        } else {
            BSFactory.getConnectionManager().rollback(conn);
            throw exception;
        }

        return null;
    }
}