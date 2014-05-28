package br.com.slim.venda.item;

import br.com.slim.venda.item.ItemVO.Item;


public class ItemSQL {
	
	public static String createTable(){
		String SQL = "CREATE TABLE IF NOT EXISTS "+Item.TABELA+" (" +
				Item.IDITEM+" INTEGER NOT NULL , " +
				Item.IDEMPRESA+" INTEGER NOT NULL , "+
				Item.IDFILIAL+" INTEGER NOT NULL , " +
				Item.DESCMAX+" DOUBLE,"+
				Item.DESCRICAO+" TEXT NOT NULL,"+
				Item.ESTOQUE+" DOUBLE,"+
				Item.IDGRUPO+" INTEGER,"+
				Item.INATIVO+" BOOLEAN,"+
				Item.PRECOVENDA+" DOUBLE," +
				Item.PRECOCOMPRA+" DOUBLE," +
				Item.PRECOPROMOCAO+" DOUBLE," +
				Item.REFERENCIA+" TEXT," +
				Item.CODBAR+" TEXT," +
				Item.PROMOCAO+" BOOLEAN," +
				Item.UNIDADE+" TEXT," +
				"PRIMARY KEY("+Item.IDITEM+", "+Item.IDEMPRESA+ ","+ Item.IDFILIAL+")  );";
		return SQL;
	}
}
