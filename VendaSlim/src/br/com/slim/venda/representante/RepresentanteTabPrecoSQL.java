package br.com.slim.venda.representante;

import br.com.slim.venda.representante.RepresentanteTabPrecoVO.RepresentanteTabPreco;

public class RepresentanteTabPrecoSQL {
	public static String createTable(){
		String SQL = "CREATE TABLE IF NOT EXISTS "+RepresentanteTabPreco.TABELA+" (" +
				RepresentanteTabPreco.IDREPRESENTANTE+" INTEGER NOT NULL, " +
				RepresentanteTabPreco.IDEMPRESA+" INTEGER NOT NULL , "+
				RepresentanteTabPreco.IDFILIAL+" INTEGER NOT NULL , "+
				RepresentanteTabPreco.IDTABELAPRECO+" INTEGER NOT NULL,"+				
				"PRIMARY KEY("+RepresentanteTabPreco.IDREPRESENTANTE+", "+RepresentanteTabPreco.IDEMPRESA+","+RepresentanteTabPreco.IDFILIAL + ","+RepresentanteTabPreco.IDTABELAPRECO +"))";
		return SQL;
	}
}