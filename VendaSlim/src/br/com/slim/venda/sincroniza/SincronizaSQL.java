package br.com.slim.venda.sincroniza;

import br.com.slim.venda.sincroniza.SincronizaVO.Sincroniza;

public class SincronizaSQL {
	
	public static String createTable(){
		String SQL = "CREATE TABLE IF NOT EXISTS "+Sincroniza.TABELA+" (" +
				Sincroniza.IDFILIAL+"  INTEGER NOT NULL , " +
				Sincroniza.NOME_TABELA+" INTEGER NOT NULL, "+
				Sincroniza.DTHRSINC +" LONG NOT NULL,"+
				"PRIMARY KEY("+Sincroniza.IDFILIAL+", "+Sincroniza.NOME_TABELA+"))";
		return SQL;
}

}
