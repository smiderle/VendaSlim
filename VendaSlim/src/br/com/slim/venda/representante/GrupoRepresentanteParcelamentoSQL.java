package br.com.slim.venda.representante;

import br.com.slim.venda.representante.GrupoRepresentanteParcelamentoVO.GrupoRepresentanteParcelamento;

public class GrupoRepresentanteParcelamentoSQL {
	public static String createTable(){
		String SQL = "CREATE TABLE IF NOT EXISTS "+GrupoRepresentanteParcelamento.TABELA+" (" +
				GrupoRepresentanteParcelamento.IDFILIAL+" INTEGER NOT NULL, " +
				GrupoRepresentanteParcelamento.IDEMPRESA+" INTEGER NOT NULL , "+
				GrupoRepresentanteParcelamento.IDGRUPO+" INTEGER NOT NULL , "+
				GrupoRepresentanteParcelamento.IDPARCELA+" INTEGER NOT NULL ,"+
				"PRIMARY KEY("+GrupoRepresentanteParcelamento.IDFILIAL+", "+
					GrupoRepresentanteParcelamento.IDEMPRESA+","+
					GrupoRepresentanteParcelamento.IDGRUPO +","+
					GrupoRepresentanteParcelamento.IDPARCELA +"))";
		return SQL;
	}
}