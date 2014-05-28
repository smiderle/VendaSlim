package br.com.slim.venda.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;

public class Convert {
	
	public static String toDateStr(Long dateLong){
		Date date = new Date(dateLong);
		return new SimpleDateFormat("dd/MM/yyyy").format(date) ;
	}
	
	public static String toDateTimeStr(Long dateLong){
		Date date = new Date(dateLong);
		return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(date) ;
	}
	
	public static Long dateTimeToLong(String dateTime){		
		try {
			return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(dateTime).getTime();
		} catch (ParseException e) {
			return new Date().getTime();
		}
		
	}
	
	public static Long dateToLong(String dateTime){		
		try {
			return new SimpleDateFormat("dd/MM/yyyy").parse(dateTime).getTime();
		} catch (ParseException e) {
			return new Date().getTime();
		}
		
	}
	
	public static Double toDouble(String value){
		double valor;
		try {
			valor = Util.arredondaDouble(Double.parseDouble(value));
			
		} catch (NumberFormatException e) {
			valor = 0;
		}
		return valor;
	}
}
