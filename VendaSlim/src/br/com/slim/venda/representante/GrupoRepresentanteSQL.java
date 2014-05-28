package br.com.slim.venda.representante;

import br.com.slim.venda.representante.GrupoRepresentanteVO.GrupoRepresentante;

public class GrupoRepresentanteSQL {
	public static String createTable(){
		String SQL = "CREATE TABLE IF NOT EXISTS "+GrupoRepresentante.TABELA+" (" +
				GrupoRepresentante.IDFILIAL+" INTEGER NOT NULL, " +
				GrupoRepresentante.IDEMPRESA+" INTEGER NOT NULL , "+
				GrupoRepresentante.IDGRUPO+" INTEGER NOT NULL , "+
				GrupoRepresentante.DESCRICAO+" TEXT NOT NULL,"+
				GrupoRepresentante.COMICAOVENDA+" DOUBLE,"+
				GrupoRepresentante.DESCMAX+" DOUBLE,"+
				GrupoRepresentante.MINVENDA+" DOUBLE,"+						
				"PRIMARY KEY("+GrupoRepresentante.IDFILIAL+", "+GrupoRepresentante.IDEMPRESA+","+GrupoRepresentante.IDGRUPO +"))";
		return SQL;
	}
}