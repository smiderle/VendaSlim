package br.com.slim.venda.filial;

import br.com.slim.venda.filial.FilialVO.Filial;


public class FilialSQL {
	
	public static String createTable(){
		String SQL = "CREATE TABLE IF NOT EXISTS "+Filial.TABELA+" (" +
						Filial.IDEMPRESA+" INTEGER NOT NULL , " +
						Filial.IDFILIAL+" INTEGER  NOT NULL , "+						
						Filial.BAIRRO+" TEXT ,"+
						Filial.CEP+" TEXT,"+
						Filial.FAX+" TEXT,"+
						Filial.FONE+" TEXT,"+
						Filial.NOMEFANTASIA+" TEXT," +
						Filial.NUMERO+" TEXT, "+
						Filial.RAZAOSOCIAL+" TEXT ,"+
						Filial.RUA+" TEXT, "+
						Filial.WEBSITE+" TEXT, "+
						"PRIMARY KEY("+Filial.IDEMPRESA+", "+Filial.IDFILIAL+")  );";
						
		return SQL;
}

}
