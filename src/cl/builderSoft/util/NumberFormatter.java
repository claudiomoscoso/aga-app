package cl.builderSoft.util;

import java.text.NumberFormat;

public  class NumberFormatter {
		
	
	private static String formatDecimalNumber;
	private static String formatIntegerNumber;
	
	public NumberFormatter() {
		formatDecimalNumber = "###,####,###.00";
		formatIntegerNumber = "####,###,###";
	}

	public NumberFormatter(String formatDecimalNumber, String formatIntegerNumber) {
		NumberFormatter.formatDecimalNumber =  formatDecimalNumber;
		NumberFormatter.formatIntegerNumber = formatIntegerNumber;
	}

	public NumberFormatter(String formatDecimalNumber) {
		NumberFormatter.formatDecimalNumber =  formatDecimalNumber;
		NumberFormatter.formatIntegerNumber = formatDecimalNumber;
	}

	
	public static String format(Object o){
		try{
			NumberFormat nformat = null; 		
			double number = Double.parseDouble(o.toString());
			long solrac = (long)number;
			
//			if(number - solrac != 0)
//				nformat  =  NumberFormat.format(formatDecimalNumber);

//			else
//				nformat = NumberFormat.getFormat(formatIntegerNumber);
			return nformat.format(number);
		}catch(NumberFormatException e){
			return o.toString();
		}
	}
	
	public static String format(Object o, String parse){
		try{		
			NumberFormat nformat =  null;//NumberFormat.getFormat(parse);
			double number = Double.parseDouble(o.toString());
			return nformat.format(number);
		}catch(NumberFormatException e){
			return o.toString();
		}		
	}
	

	
}
