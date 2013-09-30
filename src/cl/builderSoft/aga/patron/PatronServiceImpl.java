package cl.builderSoft.aga.patron;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Node;

import cl.builderSoft.framework.database.BSExecutor;
import cl.builderSoft.framework.exception.BSDataBaseException;
import cl.builderSoft.framework.exception.BSException;
import cl.builderSoft.framework.exception.BSProgrammerException;
import cl.builderSoft.framework.log.BSLog;
import cl.builderSoft.framework.log.BSLogType;
import cl.builderSoft.framework.util.BSConstants;
import cl.builderSoft.framework.util.BSFactory;
import cl.builderSoft.framework.util.BSStaticManager;
import cl.builderSoft.framework.util.BSXMLManager;
import cl.builderSoft.util.DateFormatter;

public class PatronServiceImpl implements PatronService {
    private static final String XML_INFO = BSXMLManager.TAG_ISO + "<Info/>";


    public String toXML(PatronBean patron, String nodeName) {
        StringBuffer patronString = new StringBuffer(1024);
        patronString.append("<" + nodeName + ">");
        if (patron != null) {
            patronString.append("<cID>" + patron.getId() + "</cID>");
            patronString.append("<cNumero>" + patron.getNumero() + "</cNumero>");
            patronString.append("<cCompuesto>" + patron.getCompuesto() + "</cCompuesto>");
            patronString.append("<cComposicion>" + patron.getComposicion() + "</cComposicion>");
            patronString.append("<cCilindro>" + patron.getCilindro() + "</cCilindro>");
            patronString.append("<cFechaExpiracion>" + patron.getFechaExpiracion() + "</cFechaExpiracion>");
        }
        patronString.append("</" + nodeName + ">");

        return patronString.toString();
    }

    private String infoAsXML(PatronBean patron) {
        String infoString = patron.getInfo();
        Document infoDocument = null;
        String out = "";
        try {
            infoDocument = BSXMLManager.stringToDocument(infoString);
            out = infoDocument.getRootElement().asXML();
            infoDocument.clearContent();
            infoDocument = null;
        } catch (BSProgrammerException e) {
            out = "<Info/>";
        }
        return out;
    }

    public String toXML(PatronBean user) {
        return toXML(user, "User");
    }


    public String toXML(List users) {
        return toXML(users, "users");
    }

    public String toXML(List users, String nodeName) {
        StringBuffer out = new StringBuffer();
        if (users == null) {
            out.append("<" + nodeName + "/>");
        } else {
            int len = users.size();
            PatronBean user = null;
            out.append("<" + nodeName + ">");
            for (int i = 0; i < len; i++) {
                user = (PatronBean) users.get(i);
                out.append(toXML(user));
            }
            out.append("</" + nodeName + ">");
        }
        return out.toString();
    }

    public PatronBean searchById(String id) throws BSException {
        PatronBean patron = null;
        String sql = "SELECT  P.cID, P.cNumero, P.cCompuesto, P.cComposicion, P.cCilindro, P.cFechaExpiracion ";
        sql += "FROM tPatron as P ";
        sql += "WHERE P.cID = ? ";

        Connection conn = BSFactory.getConnectionManager().getConnection(PatronService.ALIAS);
        BSExecutor exec = BSFactory.getExecutor(PatronService.ALIAS);

        // try {
        ResultSet rs = exec.queryResultSet(conn, sql, BSStaticManager.buildArrayObject(id));

        try {
            if (rs.next()) {
                patron = new PatronBean();
                patron.setId(rs.getString("cID"));
                patron.setNumero(rs.getString("cNumero"));
                patron.setCompuesto(rs.getString("cCompuesto"));
                patron.setComposicion(rs.getString("cComposicion"));
                patron.setCilindro(rs.getString("cCilindro"));
                
                Date date = (Date) rs.getDate("cFechaExpiracion").clone();
                DateFormatter dateFormatter = new DateFormatter(DateFormatter.FORMAT_DD_MM_YYYY);
                patron.setFechaExpiracion(dateFormatter.format(date));
            }
        } catch (SQLException e) {
            BSLog log = new BSLog();
            log.event(BSConstants.UNKNOW, BSLogType.DATA_BASE_EXCEPTION, "Error al buscar usuario " + id + " "
                    + e.getMessage());
            log = null;

            throw new BSDataBaseException("Error al buscar usuario " + id + " " + e.getMessage(), false);
        } finally {
            BSFactory.getConnectionManager().releaseConnection(conn, PatronService.ALIAS);
            exec.closeStatement();
            rs = null;
        }

        return patron;
    }

    public void setAttribute(PatronBean userBean, String attributeName, String attributeValue) throws BSProgrammerException {
        String xml = userBean.getInfo();
        Document infoDocument = null;
        Node field = null;

        try {
            infoDocument = BSXMLManager.stringToDocument(xml);
        } catch (BSProgrammerException e) {
            infoDocument = BSXMLManager.stringToDocument(PatronServiceImpl.XML_INFO);
        }

        field = infoDocument.selectSingleNode("/Info/" + attributeName);
        if (field == null) {
            field = infoDocument.getRootElement().addElement(attributeName);
        }
        field.setText(attributeValue);
        userBean.setInfo(infoDocument.asXML());
        infoDocument.clearContent();
        infoDocument = null;
        xml = null;
        field = null;
    }

    public String getAttribute(PatronBean userBean, String attributeName) throws BSProgrammerException {
        String xml = userBean.getInfo();
        Document infoDocument = null;
        Node field = null;
        String out = null;
        try {
            infoDocument = BSXMLManager.stringToDocument(xml);
        } catch (BSProgrammerException e) {
            infoDocument = BSXMLManager.stringToDocument(PatronServiceImpl.XML_INFO);
        }

        field = infoDocument.selectSingleNode("/Info/" + attributeName);
        if (field != null) {
            out = field.getText();
        }

        infoDocument = null;
        xml = null;
        field = null;
        return out;
    }

    public boolean existsByLogin(String login) throws BSException {
        String sql = "SELECT cID FROM tUser WHERE cLogin=?";
        Connection conn = BSFactory.getConnectionManager().getConnection(PatronService.ALIAS);
        BSExecutor exec = BSFactory.getExecutor(PatronService.ALIAS);
        boolean out = false;
        out = exec.queryField(conn, sql, BSStaticManager.buildArrayObject(login)) != null;
        return out;
    }

    public boolean existsById(String id) throws BSException {
        String sql = "SELECT cID FROM tUser WHERE cID=?";
        Connection conn = BSFactory.getConnectionManager().getConnection(PatronService.ALIAS);
        BSExecutor exec = BSFactory.getExecutor(PatronService.ALIAS);
        boolean out = false;
        out = exec.queryField(conn, sql, BSStaticManager.buildArrayObject(new Long(id))) != null;
        return out;
    }

//    public void saveUser(PatronBean userBean) throws BSException {
//        String sql = null;
//
//        if (this.existsById(userBean.getId())) {
//            sql = "UPDATE tUser SET cLogin=?, cName=?, cMail=?, cRol=?, cPhone=?, ";
//            sql += "cMovil=?, cDeleted=?, cCreationDate=?, cCreatorID=?, cInfo=?, ";
//            sql += "cEnable=? WHERE cID = ?;";
//        } else {
//            throw new BSProgrammerException("El usuario [" + userBean.getLogin() + "] no existe, no puede ser actualizado.",
//                    false);
//        }
//
//        Connection conn = BSFactory.getConnectionManager().getConnection(PatronService.ALIAS);
//
//        BSExecutor exec = BSFactory.getExecutor(PatronService.ALIAS);
//
//        Object[] params = new Object[12];
//
//        params[0] = userBean.getLogin();
//        params[1] = userBean.getName();
//        params[2] = userBean.getMail();
//        params[3] = userBean.getRol();
//        params[4] = userBean.getPhone();
//        params[5] = userBean.getMovil();
//        params[6] = new Boolean(userBean.isDeleted());
//        params[7] = userBean.getCreationDate();
//        params[8] = new Long(userBean.getCreatorID());
//        params[9] = userBean.getInfo();
//        params[10] = new Boolean(userBean.isEnable());
//        params[11] = new Long(userBean.getId());
//
//        exec.update(conn, sql, params);
//        exec.closeStatement();
//
//        BSFactory.getConnectionManager().releaseConnection(conn, PatronService.ALIAS);
//    }

}