package br.com.slim.venda.tabelaPreco;

import br.com.slim.venda.tabelaPreco.TabelaPrecoVO.TabelaPreco;

public class TabelaPrecoSQL {
	public static String createTable(){
		String SQL = "CREATE TABLE IF NOT EXISTS "+TabelaPreco.TABELA+" (" +
				TabelaPreco.IDTABPRECO +" INTEGER NOT NULL, "+
				TabelaPreco.IDEMPRESA +" INTEGER NOT NULL, "+
				TabelaPreco.IDFILIAL+" INTEGER NOT NULL, "+
				TabelaPreco.DESCRICAO +" TEXT NOT NULL , " +				
				TabelaPreco.PERCENTUAL +" DOUBLE,"+
				TabelaPreco.ACRESCIMO+" BOOLEAN, " +
				TabelaPreco.INATIVO+" BOOLEAN, " +
				"PRIMARY KEY("+TabelaPreco.IDEMPRESA+", "+TabelaPreco.IDFILIAL+","+TabelaPreco.IDTABPRECO+"))";						

		return SQL;
	}	


}
