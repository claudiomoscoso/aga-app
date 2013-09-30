package cl.builderSoft.framework.user;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
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
import cl.builderSoft.framework.util.BSDateTimeUtil;
import cl.builderSoft.framework.util.BSFactory;
import cl.builderSoft.framework.util.BSStaticManager;
import cl.builderSoft.framework.util.BSXMLManager;
import cl.builderSoft.framework.util.BSConstants;

public class UserServiceImpl implements UserService {
    private static final String XML_INFO = BSXMLManager.TAG_ISO + "<Info/>";

    public UserBean searchByLogin(String login) throws BSException {
        UserBean user = null;
        String sql = "SELECT U.cID, U.cLogin, U.cName, U.cMail, U.cRol, U.cPhone, U.cMovil, ";
        sql += "U.cDeleted, U.cCreationDate, U.cCreatorID, U.cInfo, U.cEnable ";
        sql += "FROM tUser as U ";
        sql += "WHERE U.cLogin=? AND cDeleted=0";

        Connection conn = BSFactory.getConnectionManager().getConnection(UserService.ALIAS);
        BSExecutor exec = BSFactory.getExecutor(UserService.ALIAS);

        // try {
        ResultSet rs = exec.queryResultSet(conn, sql, BSStaticManager.buildArrayObject(login));

        try {
            if (rs.next()) {
                user = new UserBean();
                user.setId(rs.getString("cID"));
                user.setLogin(rs.getString("cLogin"));
                user.setName(rs.getString("cName"));
                user.setMail(rs.getString("cMail"));
                user.setRol(rs.getString("cRol"));
                user.setPhone(rs.getString("cPhone"));
                user.setMovil(rs.getString("cMovil"));
                user.setDeleted(rs.getBoolean("cDeleted"));

                Date date = (Date) rs.getDate("cCreationDate").clone();
                Calendar calendar = BSDateTimeUtil.createCalendar(date);
                // Calendar calendar = BSDateTimeUtil.createCalendar(date
                // .getYear(), date.getMonth(), date.getDate());
                user.setCreationDate(calendar);

                user.setCreatorID(rs.getLong("cCreatorID"));
                user.setInfo(rs.getString("cInfo"));
                user.setEnable(rs.getBoolean("cEnable"));
            }
        } catch (SQLException e) {
            BSLog log = new BSLog();
            log.event(BSConstants.UNKNOW, BSLogType.DATA_BASE_EXCEPTION, "Error al buscar usuario " + login + " "
                    + e.getMessage());
            log = null;

            throw new BSDataBaseException("Error al buscar usuario " + login + " " + e.getMessage(), false);
        } finally {
            BSFactory.getConnectionManager().releaseConnection(conn, UserService.ALIAS);
            exec.closeStatement();
            rs = null;
        }

        return user;
    }

    public String toXML(UserBean user, String nodeName) {
        StringBuffer userString = new StringBuffer(1024);
        userString.append("<" + nodeName + ">");
        if (user != null) {
            userString.append("<cID>" + user.getId() + "</cID>");
            userString.append("<cLogin>" + user.getLogin() + "</cLogin>");
            userString.append("<cName>" + user.getName() + "</cName>");
            userString.append("<cMail>" + user.getMail() + "</cMail>");
            userString.append("<cRol>" + user.getRol() + "</cRol>");
            userString.append("<cPhone>" + user.getPhone() + "</cPhone>");
            userString.append("<cMovil>" + user.getMovil() + "</cMovil>");

            userString.append("<cCreationDate>" + BSDateTimeUtil.calendarToSQL(user.getCreationDate()) + "</cCreationDate>");
            userString.append("<cCreatorID>" + user.getCreatorID() + "</cCreatorID>");
            userString.append(infoAsXML(user));
            userString.append("<cEnable>" + user.isEnable() + "</cEnable>");
        }
        userString.append("</" + nodeName + ">");

        return userString.toString();
    }

    private String infoAsXML(UserBean user) {
        String infoString = user.getInfo();
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

    public String toXML(UserBean user) {
        return toXML(user, "User");
    }

    public List listByRol(String rol) throws BSException {
        UserBean user = null;
        List users = null;
        String sql = "SELECT U.cID, U.cLogin, U.cName, U.cMail, U.cRol, U.cPhone, U.cMovil, ";
        sql += "U.cDeleted, U.cCreationDate, U.cCreatorID, U.cInfo, U.cEnable ";
        sql += "FROM tUser as U ";
        sql += "WHERE U.cRol=? AND cDeleted=0";

        Connection conn = BSFactory.getConnectionManager().getConnection(UserService.ALIAS);

        BSExecutor exec = BSFactory.getExecutor(UserService.ALIAS);

        ResultSet rs = exec.queryResultSet(conn, sql, BSStaticManager.buildArrayObject(rol));

        try {
            while (rs.next()) {
                if (users == null) {
                    users = new ArrayList();
                }
                user = new UserBean();
                user.setId(rs.getString("cID"));
                user.setLogin(rs.getString("cLogin"));
                user.setName(rs.getString("cName"));
                user.setMail(rs.getString("cMail"));
                user.setRol(rs.getString("cRol"));
                user.setPhone(rs.getString("cPhone"));
                user.setMovil(rs.getString("cMovil"));
                user.setDeleted(rs.getBoolean("cDeleted"));

                Date date = (Date) rs.getDate("cCreationDate").clone();
                Calendar calendar = BSDateTimeUtil.createCalendar(date);
                user.setCreationDate(calendar);

                user.setCreatorID(rs.getLong("cCreatorID"));
                user.setInfo(rs.getString("cInfo"));
                user.setEnable(rs.getBoolean("cEnable"));
                users.add(user);
            }
        } catch (SQLException e) {
            BSLog log = new BSLog();
            log.event(BSConstants.UNKNOW, BSLogType.DATA_BASE_EXCEPTION, "Error al buscar usuarios segun rol " + rol + " "
                    + e.getMessage());
            log = null;
            throw new BSDataBaseException("Error al buscar usuarios segun rol " + rol + " " + e.getMessage(), false);
        } finally {
            exec.closeStatement();
            BSFactory.getConnectionManager().releaseConnection(conn, UserService.ALIAS);
        }

        rs = null;
        return users;
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
            UserBean user = null;
            out.append("<" + nodeName + ">");
            for (int i = 0; i < len; i++) {
                user = (UserBean) users.get(i);
                out.append(toXML(user));
            }
            out.append("</" + nodeName + ">");
        }
        return out.toString();
    }

    public UserBean searchById(String id) throws BSException {
        UserBean user = null;
        String sql = "SELECT U.cID, U.cLogin, U.cName, U.cMail, U.cRol, U.cPhone, U.cMovil, ";
        sql += "U.cDeleted, U.cCreationDate, U.cCreatorID, U.cInfo, U.cEnable ";
        sql += "FROM tUser as U ";
        sql += "WHERE U.cID = ? AND cDeleted=0";

        Connection conn = BSFactory.getConnectionManager().getConnection(UserService.ALIAS);
        BSExecutor exec = BSFactory.getExecutor(UserService.ALIAS);

        // try {
        ResultSet rs = exec.queryResultSet(conn, sql, BSStaticManager.buildArrayObject(id));

        try {
            if (rs.next()) {
                user = new UserBean();
                user.setId(rs.getString("cID"));
                user.setLogin(rs.getString("cLogin"));
                user.setName(rs.getString("cName"));
                user.setMail(rs.getString("cMail"));
                user.setRol(rs.getString("cRol"));
                user.setPhone(rs.getString("cPhone"));
                user.setMovil(rs.getString("cMovil"));
                user.setDeleted(rs.getBoolean("cDeleted"));

                Date date = (Date) rs.getDate("cCreationDate").clone();
                Calendar calendar = BSDateTimeUtil.createCalendar(date);
                // Calendar calendar = BSDateTimeUtil.createCalendar(date
                // .getYear(), date.getMonth(), date.getDate());
                user.setCreationDate(calendar);

                user.setCreatorID(rs.getLong("cCreatorID"));
                user.setInfo(rs.getString("cInfo"));
                user.setEnable(rs.getBoolean("cEnable"));
            }
        } catch (SQLException e) {
            BSLog log = new BSLog();
            log.event(BSConstants.UNKNOW, BSLogType.DATA_BASE_EXCEPTION, "Error al buscar usuario " + id + " "
                    + e.getMessage());
            log = null;

            throw new BSDataBaseException("Error al buscar usuario " + id + " " + e.getMessage(), false);
        } finally {
            BSFactory.getConnectionManager().releaseConnection(conn, UserService.ALIAS);
            exec.closeStatement();
            rs = null;
        }

        return user;
    }

    public void setAttribute(UserBean userBean, String attributeName, String attributeValue) throws BSProgrammerException {
        String xml = userBean.getInfo();
        Document infoDocument = null;
        Node field = null;

        try {
            infoDocument = BSXMLManager.stringToDocument(xml);
        } catch (BSProgrammerException e) {
            infoDocument = BSXMLManager.stringToDocument(UserServiceImpl.XML_INFO);
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

    public String getAttribute(UserBean userBean, String attributeName) throws BSProgrammerException {
        String xml = userBean.getInfo();
        Document infoDocument = null;
        Node field = null;
        String out = null;
        try {
            infoDocument = BSXMLManager.stringToDocument(xml);
        } catch (BSProgrammerException e) {
            infoDocument = BSXMLManager.stringToDocument(UserServiceImpl.XML_INFO);
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
        Connection conn = BSFactory.getConnectionManager().getConnection(UserService.ALIAS);
        BSExecutor exec = BSFactory.getExecutor(UserService.ALIAS);
        boolean out = false;
        out = exec.queryField(conn, sql, BSStaticManager.buildArrayObject(login)) != null;
        return out;
    }

    public boolean existsById(String id) throws BSException {
        String sql = "SELECT cID FROM tUser WHERE cID=?";
        Connection conn = BSFactory.getConnectionManager().getConnection(UserService.ALIAS);
        BSExecutor exec = BSFactory.getExecutor(UserService.ALIAS);
        boolean out = false;
        out = exec.queryField(conn, sql, BSStaticManager.buildArrayObject(new Long(id))) != null;
        return out;
    }

    public void saveUser(UserBean userBean) throws BSException {
        String sql = null;

        if (this.existsById(userBean.getId())) {
            sql = "UPDATE tUser SET cLogin=?, cName=?, cMail=?, cRol=?, cPhone=?, ";
            sql += "cMovil=?, cDeleted=?, cCreationDate=?, cCreatorID=?, cInfo=?, ";
            sql += "cEnable=? WHERE cID = ?;";
        } else {
            throw new BSProgrammerException("El usuario [" + userBean.getLogin() + "] no existe, no puede ser actualizado.",
                    false);
        }

        Connection conn = BSFactory.getConnectionManager().getConnection(UserService.ALIAS);

        BSExecutor exec = BSFactory.getExecutor(UserService.ALIAS);

        Object[] params = new Object[12];

        params[0] = userBean.getLogin();
        params[1] = userBean.getName();
        params[2] = userBean.getMail();
        params[3] = userBean.getRol();
        params[4] = userBean.getPhone();
        params[5] = userBean.getMovil();
        params[6] = new Boolean(userBean.isDeleted());
        params[7] = userBean.getCreationDate();
        params[8] = new Long(userBean.getCreatorID());
        params[9] = userBean.getInfo();
        params[10] = new Boolean(userBean.isEnable());
        params[11] = new Long(userBean.getId());

        exec.update(conn, sql, params);
        exec.closeStatement();

        BSFactory.getConnectionManager().releaseConnection(conn, UserService.ALIAS);
    }

}