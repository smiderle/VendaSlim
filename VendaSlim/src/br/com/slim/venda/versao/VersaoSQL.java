package br.com.slim.venda.versao;

import br.com.slim.venda.versao.VersaoPdaVO.Versao;

public class VersaoSQL {


	public static String createTable(){
		String SQL = "CREATE TABLE IF NOT EXISTS "+Versao.TABELA+" (" +
				Versao.SERIAL+" TEXT NOT NULL, " +
				Versao.BUILD+" TEXT NOT NULL, " +						
				Versao.LICENCA+" INTEGER NOT NULL , "+
				Versao.VERSAO+" TEXT , "+
				Versao.VERSAOEXPIRADA+" BOOLEAN , "+
				Versao.DATAEXPIRACAO+" long,  "+
				"PRIMARY KEY("+Versao.SERIAL+"))";
		return SQL;
	}


}
