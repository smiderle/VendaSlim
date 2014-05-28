package br.com.slim.venda.representante;

import br.com.slim.venda.representante.RepresentanteParcelamentoVO.RepresentanteParcelamento;

public class RepresentanteParcelamentoSQL {
	public static String createTable(){
		String SQL = "CREATE TABLE IF NOT EXISTS "+RepresentanteParcelamento.TABELA+" (" +
				RepresentanteParcelamento.IDREPRESENTANTE+" INTEGER NOT NULL, " +
				RepresentanteParcelamento.IDEMPRESA+" INTEGER NOT NULL , "+
				RepresentanteParcelamento.IDFILIAL+" INTEGER NOT NULL , "+
				RepresentanteParcelamento.IDPARCELAMENTO+" INTEGER NOT NULL,"+				
				"PRIMARY KEY("+RepresentanteParcelamento.IDREPRESENTANTE+", "+RepresentanteParcelamento.IDEMPRESA+","+RepresentanteParcelamento.IDFILIAL + ","+RepresentanteParcelamento.IDPARCELAMENTO +"))";
		return SQL;
	}
}