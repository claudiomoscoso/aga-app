package cl.builderSoft.utilpdf;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

import cl.builderSoft.aga.AGAAbstract;
import cl.builderSoft.util.DateFormatter;
import cl.builderSoft.util.NumberFormatter;

import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.pdf.BaseFont;

public class Resources extends AGAAbstract{
	public final static int TITLE_LEVEL1 = 0;
	public final static int TITLE_LEVEL2 = 1;
	public final static int TITLE_LEVEL3 = 2;

	/**
	 * <code>
	 http://www.javadocexamples.com/com/lowagie/text/FontFactory/getFont(String%20fontname,float%20size,int%20style).html
	 http://www.mikesdotnetting.com/Article/81/iTextSharp-Working-with-Fonts
	 </code>
	 */
	static {
		FontFactory.registerDirectories();
	}

	private final static String LINDE_ITALIC = "lindedax-italic";
	private final static String LINDE_LIGHT = "lindedax-light";
	private final static String LINDE_LIGHT_ITALIC = "lindedax-lightitalic";

	private final static String LINDE_OFFICE = "lindedaxoffice";
	private final static String LINDE_POWERPOINT = "lindedaxpowerpoint";
	// private final static String LINDE_REGULAR = "jokerman";
	// private final static String LINDE_REGULAR = "LindeDax-Regular";
	private final static String LINDE_REGULAR = "lindedax-regular";

	public final static Font FONT_TITLE_1 = getFont(LINDE_REGULAR, 14);// ,
																		// Font.BOLD);
	public final static Font FONT_TITLE_2 = getFont(LINDE_REGULAR, 12);// ,
																		// Font.BOLD);
	public final static Font FONT_TITLE_3 = getFont(LINDE_REGULAR, 8, Font.BOLD);
	// new Font(Font.HELVETICA, 10, Font.BOLD);
	public final static Font fontTitle3Compuesto = getFont(LINDE_ITALIC, 8); // ,
																				// Font.ITALIC);

	public final static Font fontNormal = getFont(LINDE_REGULAR, 8);// ,
																	// Font.NORMAL);
	public final static Font fontNormalCompuesto = getFont(LINDE_ITALIC, 6);// ,
																			// Font.ITALIC);
	public final static Font fontNormalCompuesto2 = getFont(LINDE_ITALIC, 8);// ,
																				// Font.ITALIC);

	public final static Font fontBold = getFont(LINDE_REGULAR, 8, Font.BOLD);
	public final static Font fontSmall = getFont(LINDE_LIGHT, 7);// ,
																	// Font.NORMAL);
	public final static Font fontSmall2 = getFont(LINDE_LIGHT, 6);// ,
																	// Font.NORMAL);
	public final static Font fontSmallBold = getFont(LINDE_LIGHT, 7);// ,
																		// Font.NORMAL);
	public final static Font font2 = getFont(LINDE_LIGHT, 11);// , Font.NORMAL);
	// public final static Font fontDefault = fontNormal;

	public final static String DEFAULT_RESOURCE_PATH = "cl/builderSoft/resources";
	public static BaseFont fontWatermark = null;

	private static DateFormatter fechaFormater = new DateFormatter(DateFormatter.FORMAT_DD_MM_YYYY);
	private static NumberFormatter numberFormatter = new NumberFormatter("###,###,###,###,###.00", "###,###,###,###,###");
	private static DateFormatter periodoFormater = new DateFormatter(DateFormatter.FORMAT_MM_YYYY);

	public static Font getFontTitle(int level) {
		switch (level) {
		case TITLE_LEVEL1:
			return FONT_TITLE_1;
		case TITLE_LEVEL2:
			return FONT_TITLE_2;
		case TITLE_LEVEL3:
			return FONT_TITLE_3;
		}
		return FONT_TITLE_1;
	}

	private static Font getFont(String name, int size) {
		return getFont(name, size, -1);
	}

	private static Font getFont(String name, int size, int style) {
		Font out = null;
		if (style > -1) {
			out = FontFactory.getFont(name, BaseFont.WINANSI, BaseFont.EMBEDDED, size, style);
		} else {
			out = FontFactory.getFont(name, BaseFont.WINANSI, BaseFont.EMBEDDED, size);
		}
		return out;
		// return FontFactory.getFont(name, size, style);

	}

	// public static BaseFont getFontWaterMark() {
	// if (fontWatermark != null)
	// return fontWatermark;
	// try {
	// fontWatermark = BaseFont.createFont(LINDE_REGULAR, BaseFont.WINANSI,
	// false);
	// } catch (Exception e) {
	// System.out.println("No se pudo crear font para WaterMark en PDF");
	// }
	// return fontWatermark;
	// }

	public static String getFechaDespliegue(java.util.Date fecha) {
		if (fecha == null)
			return "";
		return fechaFormater.format(fecha);
	}

	public static String getFechaDespliegue(String fecha) {
		if (fecha == null || fecha.equals(""))
			return "";
		return fechaFormater.format(fecha);
	}

	public static String getPeriodoDespliegue(java.util.Date fecha) {
		if (fecha == null)
			return "";
		return periodoFormater.format(fecha);
	}

	public static String getPeriodoDespliegue(String fecha) {
		if (fecha == null)
			return "";
		return periodoFormater.format(fecha);
	}

	public static String getHourDespliegue(java.util.Date fecha) {
		if (fecha == null)
			return "";
		return fecha.getHours() + ":" + fecha.getMinutes();
	}

	public static String getObjectDespliegue(Object o) {
		String result = "";
		if (o == null)
			result = " ";
		else if (o instanceof java.util.Date) {
			java.util.Date fecha = (java.util.Date) o;
			result = getFechaDespliegue(fecha);
		} else if ((o instanceof Integer) || (o instanceof Long) || (o instanceof Double)) {
			result = formatNumbers(o);
		} else {
			result = o.toString();
		}
		return result;
	}

	// TODO: Construcion temporal hasta que se defina formta estandar de formto
	// tanto en gwt como para pdf
	public static String formatNumbers(Object o, String parse) {
		return formatNumbers(o, parse, parse);
	}

	// TODO: Construcion temporal hasta que se defina formta estandar de formto
	// tanto en gwt como para pdf
	public static String formatNumbers(Object o) {
		return formatNumbers(o, "###,###,###,###,###", "###,###,###,###,###.00");
	}

	// TODO: Construcion temporal hasta que se defina formta estandar de formto
	// tanto en gwt como para pdf
	public static String formatNumbers(Object o, String parseInt, String parseFloat) {
		try {
			DecimalFormat nformat = new DecimalFormat();
			DecimalFormatSymbols dfs = new DecimalFormatSymbols();
			dfs.setDecimalSeparator(',');
			dfs.setGroupingSeparator('.');
			nformat.setDecimalFormatSymbols(dfs);
			double number = Double.parseDouble(o.toString());
			long solrac = (long) number;
			if (number - solrac != 0)
				nformat.applyPattern(parseFloat);
			else
				nformat.applyPattern(parseInt);
			return nformat.format(number);
		} catch (NumberFormatException e) {
			return o.toString();
		}
	}


	public static String getRutDespliegue(Object rutDv) {
		if (rutDv == null)
			return "";
		String rutDvO;
		if (rutDv instanceof String)
			rutDvO = (String) rutDv;
		else
			rutDvO = rutDv.toString();
		int tam = rutDvO.length();
		if (tam <= 0)
			return "";
		String rut = rutDvO.substring(0, tam - 1);
		String dv = rutDvO.substring(tam - 1, tam);
		if (!rutIsValid(rut, dv))
			return "Rut inv�lido";
		else
			return formatRut(new Integer(rut), dv);

	}

	public static boolean rutIsValid(String rut, String dv) {
		if (rut.length() > 8)
			return false;
		if (dv.length() > 1)
			return false;
		try {
			return rutIsValid(new Integer(rut), dv);
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static boolean rutIsValid(Integer rut, String dv) {
		if (rut == null || dv == null)
			return false;
		int m = 0, s = 1, t = rut.intValue();
		dv = dv.toUpperCase();
		for (; t != 0; t /= 10)
			s = (s + t % 10 * (9 - m++ % 6)) % 11;
		char v = (char) (s != 0 ? s + 47 : 75);
		return (dv != null && dv.trim().length() > 0 && dv.charAt(0) == v);
	}

	public static String getMonthNameCompleteByNumberMonth(int i) {
		return fechaFormater.getMonthNameByNumberMonth(i, true);
	}

	public static String formatRut(Integer rut, String dv) {
		if ((rut == null) || (dv == null))
			return null;
		try {
			DecimalFormat nformat = new DecimalFormat("##,####,###");
			return nformat.format(rut) + "-" + dv;
		} catch (NumberFormatException e) {
			return rut + "-" + dv;
		}
	}

	public static void main(String[] args) {
		System.out.println(formatRut(new Integer("12345678"), "k"));
		System.out.println(numberFormatter.format(new Double("2342344.333")));
	}
}
