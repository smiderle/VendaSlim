package br.com.slim.venda.analitico;

import br.com.slim.venda.analitico.EstoqueAnaliticoVO.EstoqueAnalitico;

public class EstoqueAnaliticoSQL {
	
	public static String createTable(){
		String SQL = "CREATE TABLE IF NOT EXISTS "+EstoqueAnalitico.TABELA+" (" +
						EstoqueAnalitico.IDSEQUENCIA+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
						EstoqueAnalitico.IDITEM+" INTEGER NOT NULL , "+
						EstoqueAnalitico.IDEMPRESA+" INTEGER NOT NULL, "+
						EstoqueAnalitico.DTMOVIMENTACAO+" LONG,"+
						EstoqueAnalitico.QUANTIDADE+" DOUBLE,"+
						EstoqueAnalitico.TIPOMOVIMENTACAO+" INTEGER );";
						
		return SQL;
}

}
