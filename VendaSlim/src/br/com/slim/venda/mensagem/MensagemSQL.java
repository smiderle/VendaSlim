package br.com.slim.venda.mensagem;

import br.com.slim.venda.mensagem.MensagemVO.Mensagem;

public class MensagemSQL {
	public static String createTable(){
		String SQL = "CREATE TABLE IF NOT EXISTS "+Mensagem.TABELA+" (" +
				Mensagem.IDMENSAGEM+" INTEGER NOT NULL, " +
				Mensagem.IDEMPRESA+" INTEGER NOT NULL , "+
				Mensagem.DTHRCADASTRO+" LONG , "+
				Mensagem.IDREPRESENTANETDESTINO+" INTEGER ,"+
				Mensagem.IDREPRESENTANETORIGEM+" INTEGER ,"+
				Mensagem.TEXTO+" TEXT,"+
				Mensagem.TITULO+" TEXT,"+
				Mensagem.USUARIODESTINO+" INTEGER ,"+
				Mensagem.USUARIOORIGEM+" INTEGER ,"+
				"PRIMARY KEY("+Mensagem.IDEMPRESA+", "+Mensagem.IDMENSAGEM+"))";
		return SQL;
	}
}