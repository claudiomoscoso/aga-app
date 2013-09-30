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
public class UpdateDYG extends BSAbstractProcess implements BSProcess {
    public BSServiceData execute(BSServiceData serviceData) throws BSException {
        Connection conn = BSFactory.getConnectionManager().getConnection("SCO");

        BSFactory.getConnectionManager().setAutoCommit(conn, false);

        executeTransaction(serviceData, conn);

        BSFactory.getConnectionManager().releaseConnection(conn, "SCO");
        return null;
    }

    public BSServiceData executeTransaction(BSServiceData serviceData, Connection conn) throws BSException {
        String sql = null;
        sql = "UPDATE tDYG SET cGentilicio=?, cNumeroCasilla=?, cEstadoCivil=?, cOcupacion=?, "
                + "cEmpleador=?, cTitulo=?, cTelefonoLaboral=?, cAnexo=?, cNivelFormacion=?, "
                + "cNumeroMiembroActivo=? WHERE cID=?";

        BSExecutor exec = BSFactory.getExecutor("SCO");
        boolean commit = true;

        String id = serviceData.getRequestFieldString("fldID", null);
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
        params[0] = new Integer(gentilicio);
        params[1] = numeroCasilla;
        params[2] = new Integer(estadoCivil);
        params[3] = ocupacion;
        params[4] = empleador;
        params[5] = titulo;
        params[6] = telefonoLaboral;
        params[7] = anexo;
        params[8] = new Integer(nivelFormacion);
        params[9] = numeroMiembroActivo;
        params[10] = id;

        try {
            exec.update(conn, sql, params);
        } catch (BSException e) {
            commit = false;
        } finally {
            exec.closeStatement();
        }
        if (commit) {
            try {
                resetCargos(conn, exec, id);
            } catch (BSException e) {
                commit = false;
            } finally {
                exec.closeStatement();
            }
        }

        if (commit) {
            Document serviceDocument = serviceData.getDocument();

            List cargos = serviceDocument.selectNodes("/Service/Request/Fields/fldCargo");
            sql = "INSERT INTO tR_DYG_Cargo(cDYG, cCargo) VALUES(?, ?);";
            params = new Object[2];
            params[0] = id;

            for (int i = 0; i < cargos.size(); i++) {
                params[1] = new Integer(((Node) cargos.get(i)).getText());
                try {
                    exec.update(conn, sql, params);
                } catch (BSDataBaseException e) {
                    commit = false;
                    i = cargos.size() + 1;
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
        }

        return null;
    }

    private void resetCargos(Connection conn, BSExecutor exec, String rut) throws BSException {
        String sql = "DELETE FROM tR_DYG_Cargo WHERE cDYG = ?";
        exec.update(conn, sql, BSStaticManager.buildArrayObject(rut));

    }
}