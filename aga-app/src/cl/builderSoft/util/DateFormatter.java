package cl.builderSoft.util;

import java.util.Date;

public class DateFormatter {
	public static final String FORMAT_DD_MM_YYYY = "dd mm yyyy";
	public static final String FORMAT_DD_MM_YYYY2 = "DD-MM-YYYY";
	public static final String FORMAT_MM_YYYY = "mm yyyy";
	public static final String FORMAT_D_M_Y = "d M y";
	public static final String FORMAT_YYYY_MM = "yyyymm";
	public static final String FORMAT_YYYY_MM_DD = "yyyymmdd";
	private static final String monthsName[] = {"Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"};
	private static final String monthsLongName[] = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
	private static final String SEPARADOR = "/";
	private String format;
	
	public DateFormatter() {
		this.format = "DD-MM-YYYY";
	}

	public DateFormatter(String format) {
		this.format = format;
	}

	public String format(Date d) {
		String f = format;
		String text = "";
		if (FORMAT_DD_MM_YYYY.equals(f)) {
			int date = d.getDate();
			int mes = d.getMonth() + 1;
			int anio = getYear(d);
			if (date < 10)
				text += "0" + date;
			else
				text += date;
			text += SEPARADOR;
			if (mes < 10)
				text += "0" + mes;
			else
				text += mes;
			text += SEPARADOR;
			text += anio;
		}
		else if (FORMAT_MM_YYYY.equals(f)) {
			int mes = d.getMonth() + 1;
			int anio = getYear(d);
			if (mes < 10)
				text += "0" + mes;
			else
				text += mes;
			text += SEPARADOR;
			text += anio;
		}
		else if (FORMAT_YYYY_MM.equals(f)) {
			int mes = d.getMonth() + 1;
			int anio = getYear(d);
			text += anio;
			if (mes < 10)
				text += "0" + mes;
			else
				text += mes;			
		}
		else if (FORMAT_YYYY_MM_DD.equals(f)) {
			int mes = d.getMonth() + 1;
			int anio = getYear(d);
			int date = d.getDate();
			text += anio;
			if (mes < 10)
				text += "0" + mes;
			else
				text += mes;
			if (date < 10)
				text += "0" + date;
			else
				text += date;
		}
		else {
			while (f.length()>0) {
				String c = f.substring(0,1);
				if (c.equals("d")) {
					text = text + d.getDate();
				} else if (c.equals("m")) {
					text = text + d.getMonth();
				} else if (c.equals("M")) {
					text = text + getMonthName(d);
				} else if (c.equals("y")) {
					text = text + getYear(d);
				} else text = text + c;
				f = f.substring(1);
			}
		}
		return text;
	}
	
	public String format(String d) {
		String f = format;
		String text = "";
		if (FORMAT_DD_MM_YYYY.equals(f)) {
			String anio = d.substring(0, 4);
			String mes = d.substring(4, 6);
			String dia = d.substring(6, 8);
			text = dia+SEPARADOR+mes+SEPARADOR+anio;
		}
		else if (FORMAT_MM_YYYY.equals(f)) {
			String anio = d.substring(0, 4);
			String mes = d.substring(4, 6);
			text = mes+SEPARADOR+anio;
		}
		else if (FORMAT_DD_MM_YYYY2.equals(f)) {
			String anio = d.substring(0, 4);
			String mes = d.substring(5, 6);
			String dia = d.substring(7, 8);
			text = dia+SEPARADOR+mes+SEPARADOR+anio;
		}
		return text;
	}

	public static String getMonthName(String f) {
		String mes= null;
		if (FORMAT_DD_MM_YYYY.equals(f) || FORMAT_MM_YYYY.equals(f)) 
			mes = f.substring(4, 6);
		return mes!= null? monthsName[Integer.parseInt(mes)]: null;
	}

	public static String getYear(String f) {
		String anio= null;
		if (FORMAT_DD_MM_YYYY.equals(f) || FORMAT_MM_YYYY.equals(f)) 
			 anio = f.substring(0, 4);
		return anio;
	}
	
	public static String getMonthName(Date d) {
		return monthsName[d.getMonth()];
	}

	/**
	 * 
	 * @param i
	 * @return
	 * @deprecated 
	 */
	public static String getMonthNameByNumberMonth(int i) {
		return monthsName[i-1];
	}	

	/**
	 * Obtiene el nombre del mes.
	 * @param i: numero del mes.
	 * @param longName: Si se desea nombre largo corto.
	 * @return
	 */
	public static String getMonthNameByNumberMonth(int i, boolean longName) {
		if( longName)
			return monthsLongName[i-1];
		return monthsName[i-1];
	}	
	
	public static int getYear(Date d) {
		return d.getYear()+1900;
	}

	public static String[] getMonths() {
		return monthsName;
	}
	
	public static String getHour(Date d){
		return d.getHours() + ":" + d.getMinutes();
	}
}
