package br.com.slim.venda.pedido;

import br.com.slim.venda.pedido.PedidoVO.Pedido;


public class PedidoSQL {

	public static String createTable(){
			String SQL = "CREATE TABLE IF NOT EXISTS "+Pedido.TABELA+" (" +
					Pedido.IDCLIENTE+"  INTEGER NOT NULL , " +
					Pedido.IDEMPRESA+" INTEGER NOT NULL, "+
					Pedido.IDFILIAL+" INTEGER NOT NULL, "+
					Pedido.DTEMISAO +" LONG,"+
					Pedido.IDPEDIDO+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
					Pedido.IDTABPRECO+" INTEGER ,"+
					Pedido.OBSERVACAO+" TEXT,"+
					Pedido.SINC +" TEXT ,"+
					Pedido.TOTALIQUIDO+" DOUBLE ,"+
					Pedido.TOTALBRUTO+" DOUBLE ,"+
					Pedido.DESCTOTAL+" DOUBLE," +
					Pedido.IDPARCELAMENTO+" INTEGER," +
					Pedido.IDFORMAPAGTO+" INTEGER," +
					Pedido.IDUSUARIO +"	INTEGER)";
			return SQL;
	}
}
