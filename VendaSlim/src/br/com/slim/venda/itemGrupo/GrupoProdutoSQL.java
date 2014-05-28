package br.com.slim.venda.itemGrupo;

import br.com.slim.venda.itemGrupo.GrupoProdutoVO.GrupoProduto;

public class GrupoProdutoSQL {

	public static String createTable(){
		String SQL = "CREATE TABLE IF NOT EXISTS "+GrupoProduto.TABELA+" (" +
				GrupoProduto.IDEMPRESA+" INTEGER  NOT NULL, "+
				GrupoProduto.IDFILIAL+" INTEGER  NOT NULL, "+				
				GrupoProduto.IDGRUPO+" INTEGER NOT NULL , " +
				GrupoProduto.DESCMAX+" DOUBLE ,"+
				GrupoProduto.DESCRICAO+" TEXT NOT NULL," +
				"PRIMARY KEY("+GrupoProduto.IDEMPRESA+", "+GrupoProduto.IDFILIAL+","+GrupoProduto.IDGRUPO+"))";
						
		return SQL;
	}
}
