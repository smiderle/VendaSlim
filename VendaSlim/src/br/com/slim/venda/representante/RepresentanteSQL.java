package br.com.slim.venda.representante;

import br.com.slim.venda.representante.RepresentanteVO.Representante;

public class RepresentanteSQL {
	public static String createTable(){
		String SQL = "CREATE TABLE IF NOT EXISTS "+Representante.TABELA+" (" +
				Representante.IDREPRESENTANTE+" INTEGER NOT NULL, " +
				Representante.IDEMPRESA+" INTEGER NOT NULL , "+				
				Representante.NOME+" TEXT NOT NULL,"+
				Representante.LOGIN+" TEXT NOT NULL,"+
				Representante.SENHA+" TEXT NOT NULL,"+
				Representante.INATIVO+" BOOLEAN,"+
				"PRIMARY KEY("+Representante.IDREPRESENTANTE+", "+Representante.IDEMPRESA+"))";
		return SQL;
	}
}