package br.com.slim.venda.representante;


import br.com.slim.venda.representante.RepresentanteFilialVO.RepresentanteFilial;

public class RepresentanteFilialSQL {
	public static String createTable(){
		String SQL = "CREATE TABLE IF NOT EXISTS "+RepresentanteFilial.TABELA+" (" +
				RepresentanteFilial.IDFILIAL+" INTEGER NOT NULL, " +
				RepresentanteFilial.IDEMPRESA+" INTEGER NOT NULL , "+				
				RepresentanteFilial.IDREPRESENTANTE+" INTEGER NOT NULL , "+
				RepresentanteFilial.IDGRUPO+" INTEGER , "+
				RepresentanteFilial.COMICAOVENDA +" DOUBLE,"+
				RepresentanteFilial.GRUPOSPRODUTO+" TEXT ,"+
				RepresentanteFilial.DESCMAX+" DOUBLE,"+
				RepresentanteFilial.MINVENDA+" DOUBLE,"+
				RepresentanteFilial.VISUALIZATODOSCLIENTES+" BOOLEAN, "+
				"PRIMARY KEY("+RepresentanteFilial.IDFILIAL+", "+RepresentanteFilial.IDEMPRESA+","+RepresentanteFilial.IDREPRESENTANTE +"))";
		return SQL;
	}
}