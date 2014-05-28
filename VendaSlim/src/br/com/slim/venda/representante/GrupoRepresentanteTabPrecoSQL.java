package br.com.slim.venda.representante;

import br.com.slim.venda.representante.GrupoRepresentanteTabPrecoVO.GrupoRepresentanteTabPreco;

public class GrupoRepresentanteTabPrecoSQL {
	public static String createTable(){
		String SQL = "CREATE TABLE IF NOT EXISTS "+GrupoRepresentanteTabPreco.TABELA+" (" +
				GrupoRepresentanteTabPreco.IDFILIAL+" INTEGER NOT NULL, " +
				GrupoRepresentanteTabPreco.IDEMPRESA+" INTEGER NOT NULL , "+
				GrupoRepresentanteTabPreco.IDGRUPO+" INTEGER NOT NULL , "+
				GrupoRepresentanteTabPreco.IDTABELA+" INTEGER NOT NULL ,"+
				"PRIMARY KEY("+GrupoRepresentanteTabPreco.IDFILIAL+", "+
					GrupoRepresentanteTabPreco.IDEMPRESA+","+
					GrupoRepresentanteTabPreco.IDGRUPO +","+
					GrupoRepresentanteTabPreco.IDTABELA+"))";
		return SQL;
	}
}