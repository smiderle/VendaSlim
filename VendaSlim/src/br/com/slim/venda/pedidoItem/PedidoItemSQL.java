package br.com.slim.venda.pedidoItem;

import br.com.slim.venda.pedido.PedidoVO.Pedido;
import br.com.slim.venda.pedidoItem.PedidoItemVO.PedidoItem;

public class PedidoItemSQL {
	public static String createTable(){
			String SQL = "CREATE TABLE IF NOT EXISTS "+PedidoItem.TABELA+" ("+					
					PedidoItem.IDEMPRESA+" INTEGER NOT NULL , "+
					PedidoItem.IDFILIAL+" INTEGER NOT NULL , "+
					PedidoItem.IDITEM +" INTEGER NOT NULL ,"+
					PedidoItem.IDPEDIDO+" INTEGER NOT NULL,"+
					PedidoItem.IDSEQUENCIA+" INTEGER NOT NULL,"+
					PedidoItem.PRECOVENDA+" DOUBLE NOT NULL,"+
					PedidoItem.DESCONTO+"  DOUBLE , " +
					PedidoItem.QUANTIDADE+" DOUBLE NOT NULL,"+
					Pedido.DESCTOTAL+" DOUBLE," +
					"PRIMARY KEY(" +PedidoItem.IDITEM +","+PedidoItem.IDEMPRESA +","+PedidoItem.IDFILIAL+","+	PedidoItem.IDSEQUENCIA+"," +PedidoItem.IDPEDIDO+" ))";
			
			return SQL;
	}
}
