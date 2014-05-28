package br.com.slim.venda.parcelamento;

import br.com.slim.venda.parcelamento.ParcelamentoVO.Parcela;


public class ParcelamentoSQL {
	public static String createTable(){
		String SQL = "CREATE TABLE IF NOT EXISTS "+Parcela.TABELA+" (" +
				Parcela.IDPARCELAMENTO+" INTEGER NOT NULL,"+
				Parcela.IDEMPRESA+" INTEGER NOT NULL,"+
				Parcela.IDFILIAL+" INTEGER NOT NULL,"+
				Parcela.CARENCIA+" INTEGER , " +
				Parcela.DESCRICAO+" TEXT NOT NULL, "+
				Parcela.DIASENTREPARCELA+" INTEGER,"+
				Parcela.NROPARCELA+" INTEGER,"+
				Parcela.PERCENTUAL+" DOUBLE,"+
				Parcela.INATIVO+" BOOLEAN,"+	
				"PRIMARY KEY("+Parcela.IDPARCELAMENTO+", "+Parcela.IDEMPRESA+", "+Parcela.IDFILIAL+ "))";
			
		return SQL;
	}
}