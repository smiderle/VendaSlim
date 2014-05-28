package br.com.slim.venda.pedidopgto;

import br.com.slim.venda.pedidopgto.PedidoPgtoVO.PedidoPgto;

public class PedidoPgtoSQL {
	
	public static String createTable(){
		String SQL = "CREATE TABLE IF NOT EXISTS "+PedidoPgto.TABELA+" ("+					
				PedidoPgto.DTVENCIMENTO+" DATE NOT NULL , "+
				PedidoPgto.DTPAGAMENTO+" LONG , "+
				PedidoPgto.IDEMPRESA+" INTEGER NOT NULL ,"+
				PedidoPgto.IDFILIAL+" INTEGER NOT NULL ,"+
				PedidoPgto.IDPARCELAMENTO+" INTEGER NOT NULL,"+
				PedidoPgto.IDPEDIDO+" INTEGER  NOT NULL,"+
				PedidoPgto.IDSEQUENCIA+"  INTEGER NOT NULL, " +
				PedidoPgto.PARCELAPAGA+" INTEGER,"+
				PedidoPgto.VALORPAGO+" DOUBLE," +
				PedidoPgto.VALORPARCELA+" DOUBLE," +
				PedidoPgto.IDCLIENTE+" INTEGER," +
				"PRIMARY KEY(" +PedidoPgto.IDEMPRESA +","+PedidoPgto.IDFILIAL+"," +PedidoPgto.IDSEQUENCIA +","+PedidoPgto.IDPEDIDO+" ))";
		
		return SQL;
}

	

}
