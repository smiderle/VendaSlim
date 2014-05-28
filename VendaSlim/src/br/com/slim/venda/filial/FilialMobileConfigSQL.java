package br.com.slim.venda.filial;

import br.com.slim.venda.filial.FilialMobileConfigVO.FilialMobileConfig;

public class FilialMobileConfigSQL {
	
	public static String createTable(){
		String SQL = "CREATE TABLE IF NOT EXISTS "+FilialMobileConfig.TABELA+" (" +
						FilialMobileConfig.IDEMPRESA+" INTEGER NOT NULL , " +
						FilialMobileConfig.IDFILIAL+" INTEGER  NOT NULL , "+						
						FilialMobileConfig.ACAOLIMITECREDITO+" INTEGER ,"+
						FilialMobileConfig.ACAOTITULOVENCIDO+" INTEGER ,"+
						FilialMobileConfig.CADASTRARCLIENTE+" BOOLEAN ,"+
						FilialMobileConfig.DIASVENCIEMENTO+" INTEGER ,"+
						FilialMobileConfig.EMAILPEDIDOS+" BOOLEAN,"+
						FilialMobileConfig.ENVIAREMAILCLIENTE+" BOOLEAN ,"+
						FilialMobileConfig.ESTOQUENEGATIVO+" BOOLEAN,"+
						FilialMobileConfig.EXIBEESTOQUE+" BOOLEAN ,"+
						"PRIMARY KEY("+FilialMobileConfig.IDEMPRESA+", "+FilialMobileConfig.IDEMPRESA+")  );";
						
		return SQL;
}

}
