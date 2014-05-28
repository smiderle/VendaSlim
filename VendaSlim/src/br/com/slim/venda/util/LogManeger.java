package br.com.slim.venda.util;

import android.util.Log;

public class LogManeger {
	/**
	 * 
	 * @param categoria
	 * @param msg
	 * @param persistir salvar o erro no banco ?
	 */
	public static void e(String categoria, Exception exception, boolean persistir){
		exception.printStackTrace();
		Log.e(categoria, exception.toString());
		
		//implementar a persistencia;
	}

}
