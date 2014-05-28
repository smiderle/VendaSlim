package br.com.slim.venda.cliente;

import br.com.slim.venda.cliente.ClienteVO.Cliente;


public class ClienteSQL {
	public static String createTable(){
		String SQL = "CREATE TABLE IF NOT EXISTS "+Cliente.TABELA+" (" +
						Cliente.IDCLIENTE+" INTEGER NOT NULL, " +
						Cliente.IDEMPRESA+" INTEGER NOT NULL , "+
						Cliente.IDFILIAL+" INTEGER NOT NULL , "+
						Cliente.IDREPRESENTANTE+" INTEGER ,"+
						Cliente.NOME+" TEXT NOT NULL,"+
						Cliente.CONTATO+" TEXT,"+
						Cliente.TIPO+" INTEGER,"+
						Cliente.CEP+" TEXT,"+
						Cliente.CPF+" TEXT,"+
						Cliente.RG+" TEXT,"+						
						Cliente.DTNASCIMENTO+" LONG,"+
						Cliente.IDCIDADE+" INTEGER,"+
						Cliente.RUA+" TEXT,"+
						Cliente.NUMERO+" TEXT,"+
						Cliente.BAIRRO+" TEXT,"+
						Cliente.FONERESIDENCIAL+" TEXT,"+
						Cliente.FONECOMERCIAL+" TEXT,"+
						Cliente.FONECELULAR+" TEXT,"+
						Cliente.EMAIL+" TEXT,"+
						Cliente.IDTABPRECO+" INTEGER,"+
						Cliente.IDFORMAPARCELAMENTO+" INTEGER,"+
						Cliente.IDFORMAPAGAMENTO+" INTEGER,"+
						Cliente.LIMITECREDITO+" DOUBLE,"+
						Cliente.DESCMAX+" DOUBLE,"+
						Cliente.OBSERVACAO+" TEXT," +						
						Cliente.SINCRONIZADO+" TEXT," +
						Cliente.ALTERADO+" TEXT," +
						Cliente.INATIVO+" BOOLEAN," +
						"PRIMARY KEY("+Cliente.IDCLIENTE+", "+Cliente.IDEMPRESA+","+Cliente.IDFILIAL +"))";
		return SQL;
}
}
