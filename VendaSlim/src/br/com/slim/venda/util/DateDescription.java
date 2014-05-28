package br.com.slim.venda.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateDescription {
	private Calendar calendar = new GregorianCalendar();
	
	public DateDescription(Date date) {
		this.calendar.setTime(date);
	}
	
	public DateDescription(long longDate) {
		this.calendar.setTimeInMillis(longDate);
	}
	
	private void getDescription(){
		calendar.get(Calendar.MONTH);
	}
	
	
	private String getDescricaoSemana(int diaSemana){
		switch (diaSemana) {		
		case Calendar.MONDAY:
			return "Domingo";
		case Calendar.SUNDAY:
			return "Segunda-Feira";
		case Calendar.TUESDAY:
			return "Terça-Feira";
		case Calendar.WEDNESDAY:
			return "Quarta-Feira";
		case Calendar.THURSDAY:
			return "Quita-Feira";
		case Calendar.FRIDAY:
			return "Sexta-Feira";
		case Calendar.SATURDAY:
			return "Sabado";
		default:
			return "";
		}
	}
	
	
	
	

}
