package cl.builderSoft.framework.service;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;

import cl.builderSoft.framework.database.BSConnectionManager;
import cl.builderSoft.framework.database.BSExecutor;
import cl.builderSoft.framework.exception.BSConfigurationException;
import cl.builderSoft.framework.exception.BSException;
import cl.builderSoft.framework.exception.BSProgrammerException;
import cl.builderSoft.framework.log.BSLog;
import cl.builderSoft.framework.mvc.ioService.BSServiceData;
import cl.builderSoft.framework.util.BSDateTimeUtil;
import cl.builderSoft.framework.util.BSFactory;
import cl.builderSoft.framework.util.BSXMLManager;
import cl.builderSoft.framework.util.BSConstants;

/**
 * @author cmoscoso
 */
public class BSGenericExecStoreProcedure extends BSAbstractGenericDatabaseAccess implements BSProcess {
	private Node commandNode = null;
	private String dataSource = null;
	String processName = null;

	public BSServiceData execute(BSServiceData serviceData) throws BSException {
		String configFile = serviceData.getDocument().selectSingleNode(PATH).getText();
		configFile += WEB_INF + BSConstants.FILE_SEPARATOR + BS_CONFIG + BSConstants.FILE_SEPARATOR + DATA_BASE_CONFIG;
		processName = serviceData.getProcessName();

		Document config = BSXMLManager.fileToDocument(configFile);
		this.commandNode = config.selectSingleNode(XPATH_1 + this.processName + XPATH_2);

		if (commandNode == null) {
			throw new BSConfigurationException("No se ha configurado el Command para el proceso '" + this.processName + "'",
					false);
		}
		BSConnectionManager connectionManager = BSFactory.getConnectionManager();

		dataSource = commandNode.selectSingleNode("@DataSource").getText();
		Connection conn = connectionManager.getConnection(dataSource);

		executeTransaction(serviceData, conn);

		connectionManager.releaseConnection(conn, dataSource);
		return null;
	}

	public BSServiceData executeTransaction(BSServiceData serviceData, Connection conn) throws BSException {
		String sql = commandNode.selectSingleNode("@SQL").getText();
		String typeCommandString = null;
		Node typeCommand = commandNode.selectSingleNode("@Type");

		if (typeCommand == null) {
			typeCommandString = "query";
		} else {
			typeCommandString = typeCommand.getText();
		}

		BSExecutor exec = BSFactory.getExecutor(dataSource);

		Object[] params = this.getParams(commandNode, serviceData, dataSource, conn, exec);
		logQuery(sql, params);

		if (typeCommandString.equalsIgnoreCase("query")) {
			serviceData.addResponseNode(exec.query(conn, sql, params, processName).getRootElement());
		} else if (typeCommandString.equalsIgnoreCase("update")) {
			exec.update(conn, sql, params);
		} else if (typeCommandString.equalsIgnoreCase("sp")) {
			Document doc = exec.querySP(conn, sql, params, processName);
			if (doc != null) {
				serviceData.addResponseNode(doc.getRootElement());
			}
		}
		exec.closeStatement();

		return null;
	}

	private void logQuery(String sql, Object[] params) throws BSException {
		if (BSFactory.getConnectionManager().isDebugmode()) {
			String logMessage = sql;
			boolean haveParams = params != null && params.length > 0;
			if (haveParams) {
				logMessage += haveParams ? "\nwith params:\n[" : "";

				for (int i = 0; i < params.length; i++) {
					logMessage += objectToString(params[i]) + ",";
				}

				logMessage = logMessage.substring(0, logMessage.length() - 1);
				logMessage += "]";
			}

			BSLog.debug(logMessage);
			logMessage = null;
		}

	}

	private String objectToString(Object param) {
		String out = null;
		if (param instanceof String) {
			out = (String) param;
		} else if (param instanceof Integer || param instanceof Double || param instanceof Long) {
			out = "" + param;
		} else if (param instanceof Boolean) {
			out = ((Boolean) param).booleanValue() ? "true" : "false";
		} else if (param instanceof java.util.Calendar || param instanceof java.util.GregorianCalendar) {
			out = BSDateTimeUtil.calendarForUser((Calendar) param);
		} else if (param instanceof java.util.Date) {
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(((java.util.Date) param).getTime());
			out = BSDateTimeUtil.calendarForUser(c);
			c = null;
		} else if (param == null) {
			out = "NULL";
		}
		return out;
	}

	private Object[] getParams(Node commandNode, BSServiceData serviceData, String dataSource, Connection conn, BSExecutor exec)
			throws BSException {
		Object[] out = null;
		List parameters = commandNode.selectNodes("Parameter");
		int len = parameters.size();

		if (len > 0) {
			Node valueService = null;
			Element parameter = null;
			String type = null;
			String field = null;
			String fieldType = null;
			String fieldValue = null;
			String value = null;
			String defaultString = null;
			String format = null;
			int equalPosition = -1;
			Object oneObject = null;

			out = new Object[len];
			for (int i = 0; i < len; i++) {
				parameter = (Element) parameters.get(i);
				field = parameter.attributeValue("Field");
				type = parameter.attributeValue("Type");
				defaultString = parameter.attributeValue("Default");
				format = parameter.attributeValue("Format");

				equalPosition = field.indexOf("=");
				fieldType = field.substring(0, equalPosition);
				fieldValue = field.substring(equalPosition + 1);

				if (fieldType.equalsIgnoreCase("Path")) {
					valueService = serviceData.getDocument().selectSingleNode(fieldValue);
					if (valueService == null) {
						if (defaultString == null) {
							throw new BSProgrammerException("El campo [" + fieldValue
									+ "] no se encuentra para ejecutar el comando SQL ["
									+ commandNode.selectSingleNode("@SQL").getText() + "] en el servicio ["
									+ serviceData.getServiceName() + "]", false);
						} else {
							value = defaultString;
						}
					} else {
						value = valueService.getText();
					}
				} else if (fieldType.equalsIgnoreCase("Value")) {
					value = fieldValue;
				} else if (fieldType.equalsIgnoreCase("Table")) {
					value = fieldValue;
				} else {
					throw new BSConfigurationException("El tipo de parametro [" + fieldType + "] no es válido", false);
				}
				oneObject = convertToObject(type, value, format, conn, exec);
				out[i] = oneObject;
				putLastCode(type, oneObject, serviceData);

			}
			valueService = null;
			parameter = null;
			type = null;
			field = null;
			fieldType = null;
			fieldValue = null;
			value = null;
			defaultString = null;
			format = null;
		}
		return out;
	}

	private void putLastCode(String type, Object oneObject, BSServiceData serviceData) throws BSException {
		type = type.toLowerCase();
		if (type.indexOf("new") >= 0) {
			serviceData.addResponseField(serviceData.getProcessName(), (String) oneObject);
		}
	}

	private Object convertToObject(String type, String fieldValue, String format, Connection conn, BSExecutor exec)
			throws BSException {
		Object out = null;
		type = type.toLowerCase();
		if (type.indexOf("string") >= 0) {
			// out = BSHTMLManager.clearSpecialChars(fieldValue);
			out = fieldValue;
		} else if (type.indexOf("int") >= 0) {
			out = new Integer(fieldValue);
		} else if (type.indexOf("new") >= 0) {
			String[] insertParams = getFieldKey(fieldValue);
			out = "" + exec.getLastCodeLong(conn, insertParams[0], insertParams[1]);
			insertParams = null;
		} else if (type.indexOf("bool") >= 0) {
			out = new Boolean(fieldValue.equalsIgnoreCase("on") || fieldValue.equalsIgnoreCase("1")
					|| fieldValue.equalsIgnoreCase("Yes") || fieldValue.equalsIgnoreCase("True")
					|| fieldValue.equalsIgnoreCase("Si") || fieldValue.equalsIgnoreCase("Verdadero"));
		} else if (type.indexOf("date") >= 0) {
			if (format == null) {
				format = "yyyy-MM-dd";
			}
			Date date = null;
			DateFormat formatter = new SimpleDateFormat(format);
			try {
				date = (Date) formatter.parse(fieldValue);
			} catch (ParseException e) {
				throw new BSConfigurationException("El formato [" + format + "] no es válido para el dato [" + fieldValue + "]",
						false);
			} finally {
				formatter = null;
			}
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(date.getTime());
			out = calendar;
			date = null;
			calendar = null;
			// out = BSDateTimeUtil.stringSQLToCalendar(fieldValue);
		} else {
			throw new BSConfigurationException("El tipo [" + type + "] no es válido", false);
		}
		return out;
	}

	protected String[] getFieldKey(String key) {
		String[] out = new String[2];
		int pointPosition = key.indexOf('.');

		if (pointPosition > -1) {
			out[0] = key.substring(0, pointPosition);
			out[1] = key.substring(pointPosition + 1);
		} else {
			out[0] = key;
			out[1] = "cID";
		}
		return out;
	}

}
