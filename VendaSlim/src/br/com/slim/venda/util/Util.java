package br.com.slim.venda.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.format.DateFormat;

public class Util {

	public static double arredondaDouble(double valor){
		BigDecimal  valorExato = new BigDecimal(valor).setScale(2, RoundingMode.HALF_DOWN);
		return valorExato.doubleValue();
	}
	
	public static String arredondaDoubleToString(double valor){
		DecimalFormat format = new DecimalFormat("0.00");
		return format.format(arredondaDouble(valor));
	}
	
	public static double aplicaPercentual(double valor, double percentual, boolean acrescimo){
		double valorPercentual = valor * (percentual /100);
		if(acrescimo){
			return arredondaDouble(valor + valorPercentual);
		} else {
			return arredondaDouble(valor - valorPercentual);
			
		}
	}
	
	
	public static boolean dateIsAfterOf(long dtLong1, long  dtLong2){
		Calendar calendar1 = new GregorianCalendar();
		calendar1.setTimeInMillis(dtLong1);
		
		Calendar calendar2 = new GregorianCalendar();
		calendar2.setTimeInMillis(dtLong2);
		
		calendar1.set(Calendar.HOUR_OF_DAY, 0);
		calendar1.set(Calendar.MINUTE, 0);
		calendar1.set(Calendar.SECOND, 0);
		calendar1.set(Calendar.MILLISECOND, 0);
		calendar2.set(Calendar.HOUR_OF_DAY, 0);
		calendar2.set(Calendar.MINUTE, 0);
		calendar2.set(Calendar.SECOND, 0);
		calendar2.set(Calendar.MILLISECOND, 0);
		
		return calendar1.after(calendar2);
	}
	
	public static String getDataPorExtenso(long longDate){
		Date date = new Date(longDate);		
		Locale locale = new Locale("pt", "BR");
		return new SimpleDateFormat("EEEE ',' dd 'de' MMMM 'de' yyyy ", locale).format(date);
	}
	
	public static boolean isCpfValid(String cpf){
		String strCpf = cpf;
		if (strCpf == null || strCpf.equals("")) {
			return false;
		} 

		int d1, d2;
		int digito1, digito2, resto;
		int digitoCPF;
		String nDigResult;

		d1 = d2 = 0;
		digito1 = digito2 = resto = 0;

		for (int nCount = 1; nCount < strCpf.length() - 1; nCount++) {
			digitoCPF = Integer.valueOf(strCpf.substring(nCount - 1, nCount)).intValue();

			//multiplique a ultima casa por 2 a seguinte por 3 a seguinte por 4 e assim por diante.  
			d1 = d1 + (11 - nCount) * digitoCPF;

			//para o segundo digito repita o procedimento incluindo o primeiro digito calculado no passo anterior.  
			d2 = d2 + (12 - nCount) * digitoCPF;
		}

		//Primeiro resto da divisão por 11.  
		resto = (d1 % 11);

		//Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior.  
		if (resto < 2) {
			digito1 = 0;
		} else {
			digito1 = 11 - resto;
		}

		d2 += 2 * digito1;

		//Segundo resto da divisão por 11.  
		resto = (d2 % 11);

		//Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior.  
		if (resto < 2) {
			digito2 = 0;
		} else {
			digito2 = 11 - resto;
		}

		//Digito verificador do CPF que está sendo validado.  
		String nDigVerific = strCpf.substring(strCpf.length() - 2, strCpf.length());

		//Concatenando o primeiro resto com o segundo.  
		nDigResult = String.valueOf(digito1) + String.valueOf(digito2);

		//comparar o digito verificador do cpf com o primeiro resto + o segundo resto.  
		return nDigVerific.equals(nDigResult);		    
	}	
}