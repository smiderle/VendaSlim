package br.com.vendaslim.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Converter {
	/**
	 * 
	 * @param data 
	 * @param fimDia true fim dia seta a ultima hora do dia, caso contrario seta a hora inicial 
	 * @return
	 */
	public static Calendar convertDateToCalendar(Date data, boolean fimDia){
		if(data != null){
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(data);
			if(fimDia){
				calendar.set(Calendar.HOUR_OF_DAY, 23);
				calendar.set(Calendar.MINUTE, 59);
			} else {
				calendar.set(Calendar.HOUR_OF_DAY, 0);
				calendar.set(Calendar.MINUTE, 0);	
			}
			return calendar;
		}
		return null;
		
	}
}
