package cl.builderSoft.aga.patron_bad;

import java.sql.Connection;

import org.dom4j.Document;

import cl.builderSoft.framework.exception.BSException;
import cl.builderSoft.framework.mvc.ioService.BSServiceData;
import cl.builderSoft.framework.service.BSAbstractProcess;
import cl.builderSoft.framework.service.BSProcess;
import cl.builderSoft.framework.util.BSFactory;
import cl.builderSoft.framework.util.BSXMLManager;

public class SearchPatron extends BSAbstractProcess implements BSProcess {

    public BSServiceData execute(BSServiceData serviceData) throws BSException {
    	
//		String sql = "SELECT P.cID, P.cNumero, P.cCompuesto, P.cComposicion, P.cCilindro, P.cFechaExpiracion ";
//
//		sql += "FROM tPatron as P ";
//		sql += "WHERE P.cID='"
//				+ serviceData.getRequestFieldString("fldID", null) + "' ";
//		sql += "ORDER BY P.cCompuesto";
//
//         Connection conn = BSFactory.getConnectionManager().getConnection("SLC");
//         BSExecutor exec = BSFactory.getExecutor("SLC");
//
//         serviceData.addResponseNode(exec.query(conn, sql, null, "SearchPatron").getRootElement());
//         BSFactory.getConnectionManager().releaseConnection(conn, "SLC");
//         exec.closeStatement();
        executeTransaction(serviceData, null);
        return null;
    }

    public BSServiceData executeTransaction(BSServiceData serviceData, Connection conn) throws BSException {
        String id = serviceData.getRequestFieldString("fldID", serviceData.getSessionFieldString("User/cID", null));

        PatronService patronService = (PatronService) BSFactory.createInstance(PatronService.CLASS_NAME);
        PatronBean patronBean = patronService.searchById(id);
        String patronXMLString = patronService.toXML(patronBean, "SearchPatron");
        Document patronDocument = BSXMLManager.stringToDocument(patronXMLString);

        serviceData.addResponseNode(patronDocument.getRootElement());
        patronDocument.clearContent();
        patronDocument = null;
        patronXMLString = null;
        patronBean = null;
        patronService = null;

        return null;
    }
}