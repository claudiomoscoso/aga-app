package cl.builderSoft.aga;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import cl.builderSoft.framework.service.BSAbstractProcess;

public class AGAAbstract extends BSAbstractProcess {
	public String formatToOneDecimal(String numberString) {
		String out = null;
		try {
			double numberDouble = Double.parseDouble(numberString);
			NumberFormat formatter = new DecimalFormat("0.0");
			out = formatter.format(numberDouble);
		} catch (Exception e) {
			out = "0.0";
		}
		return out;
	}

	public static String formatNumber(String n) {
		return formatNumber(n, "0.00");
	}

	public static String formatNumber(String n, String pattern) {
		String out = null;
		try {
			double volumenGasDouble = Double.parseDouble(n);

			NumberFormat formatter = new DecimalFormat(pattern);
			out = formatter.format(volumenGasDouble);
			out = out.replaceAll("[,]", ".");
		} catch (Exception e) {
			out = n;
		}
		return out;
	}

}
