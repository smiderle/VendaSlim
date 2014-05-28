package br.com.slim.venda.representante;

import br.com.slim.venda.representante.RepresentanteRotaVO.RepresentanteRota;

public class RepresentanteRotaSQL {

	public static String createTable(){
		String SQL = "CREATE TABLE IF NOT EXISTS "+RepresentanteRota.TABELA+" (" +
				RepresentanteRota.IDREPRESENTANTE+" INTEGER NOT NULL, " +
				RepresentanteRota.IDEMPRESA+" INTEGER NOT NULL , "+				
				RepresentanteRota.DTHRROTA+" LONG NOT NULL,"+
				RepresentanteRota.LATITUDE+" DOUBLE NOT NULL,"+
				RepresentanteRota.LONGITUDE+" DOUBLE NOT NULL," +
				RepresentanteRota.SINCRONIZADO+" INTEGER, "+				
				"PRIMARY KEY("+RepresentanteRota.IDREPRESENTANTE+", "+RepresentanteRota.IDEMPRESA+","+RepresentanteRota.DTHRROTA+"))";
		return SQL;
	}
}
