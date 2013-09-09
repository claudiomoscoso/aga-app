/*
 * Created on 23-05-2007
 */
package cl.builderSoft.scout;

import java.sql.Connection;

import org.dom4j.Document;
import org.dom4j.Element;

import cl.builderSoft.framework.database.BSExecutor;
import cl.builderSoft.framework.exception.BSException;
import cl.builderSoft.framework.mvc.ioService.BSServiceData;
import cl.builderSoft.framework.service.BSAbstractProcess;
import cl.builderSoft.framework.service.BSProcess;
import cl.builderSoft.framework.util.BSFactory;
import cl.builderSoft.framework.util.BSStaticManager;

/**
 * @author cmoscoso
 */
public class BuscaDYG extends BSAbstractProcess implements BSProcess {

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
        String sql = "SELECT p.cID, p.cRUT, p.cMasculino, p.cFechaRegistro, p.cNombre, "
                + "p.cApellidoPaterno, p.cApellidoMaterno, p.cDireccion, p.cNumero, p.cVilla, "
                + "p.cBlock, p.cDepartamento, p.cComuna, p.cTelefono, p.cCelular, "
                + "p.cMail, p.cTabla, s.cFechaNacimiento, s.cFax, s.cCodigoPostal, "
                + "s.cNacionalidad, s.cReligion, s.cGrupo, s.cUnidad, s.cIdioma1, "
                + "s.cIdiomaLee1, s.cIdiomaHabla1, s.cIdiomaEscribe1, s.cIdioma2, s.cIdiomaLee2, "
                + "s.cIdiomaHabla2, s.cIdiomaEscribe2, s.cObservacion, s.cFechaPago, s.cValorPago, "
                + "s.cInformado, d.cGentilicio, d.cNumeroCasilla, d.cEstadoCivil, d.cOcupacion, "
                + "d.cEmpleador, d.cTitulo, d.cTelefonoLaboral, d.cAnexo, d.cNivelFormacion, "
                + "d.cNumeroMiembroActivo FROM tpersona AS p LEFT JOIN tscout AS s ON p.cID = s.cID "
                + "LEFT JOIN tdyg AS d ON d.cID = p.cID WHERE p.cID = ?;";

        BSExecutor exec = BSFactory.getExecutor("SCO");
        String id = serviceData.getRequestFieldString("fldID", null);
        Object[] idArray = BSStaticManager.buildArrayObject(id);
        Document dygDocument = exec.query(conn, sql, idArray, "BuscaDYG");
        exec.closeStatement();

        sql = "SELECT cCargo FROM tr_dyg_cargo WHERE cDYG = ?;";
        Document cargoDocument = exec.query(conn, sql, idArray, "Cargos");

        Element dygElement = (Element) dygDocument.selectSingleNode("/BuscaDYG/Record");
        dygElement.add((Element) cargoDocument.getRootElement());

        serviceData.addResponseNode((Element)dygElement.clone());
        
        return null;
    }
}