package br.com.slim.venda.config;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class ConfigBO {
	
	Context context;
	public ConfigBO(Context context) {
		this.context = context;
	}

	
	public void applyPreferences(){
		/*SharedPreferences prefs  = PreferenceManager.getDefaultSharedPreferences(context);
    	ConfigVO.permiteVendaEstoqueNegativo = prefs.getBoolean("stock_negative", false);
    	ConfigVO.acaoLimiteCredito = prefs.getString("acao_limite_credito", ConfigVO.PREFERENCES_ACAO_BLOQUEAR);
    	ConfigVO.acaoVendaTitulosVencidos = prefs.getString("acao_titulos_vencidos", ConfigVO.PREFERENCES_ACAO_BLOQUEAR);
    	ConfigVO.validarCpf = prefs.getBoolean("valid_cpf", false);
    	ConfigVO.email = prefs.getString("email", null);*/
	}
}
