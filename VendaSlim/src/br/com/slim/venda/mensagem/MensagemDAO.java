package br.com.slim.venda.mensagem;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import br.com.slim.venda.dataBase.SQLiteHelper;
import br.com.slim.venda.mensagem.MensagemVO.Mensagem;
import br.com.slim.venda.representante.GrupoRepresentanteParcelamentoVO.GrupoRepresentanteParcelamento;


public class MensagemDAO {

	String CATEGORIA = "MensagemDAO";
	SQLiteHelper sqlLiteHelper;
	SQLiteDatabase db =  null;
	
	
	public MensagemDAO(Context context) {
		sqlLiteHelper = new SQLiteHelper(context);
		this.db = sqlLiteHelper.getWritableDatabase();
	}
	
	public void insertOrUpdate(final MensagemVO mensagemVO){
		StringBuffer sb = new StringBuffer();
				sb.append("INSERT OR REPLACE INTO ");
				sb.append(Mensagem.TABELA);
				sb.append("(");
					sb.append(Mensagem.IDEMPRESA);
					sb.append(",");
					sb.append(Mensagem.DTHRCADASTRO);
					sb.append(",");
					sb.append(Mensagem.IDMENSAGEM);
					sb.append(",");
					sb.append(Mensagem.IDREPRESENTANETDESTINO);
					sb.append(",");
					sb.append(Mensagem.IDREPRESENTANETORIGEM);
					sb.append(",");
					sb.append(Mensagem.TEXTO);
					sb.append(",");
					sb.append(Mensagem.TITULO);
					sb.append(",");
					sb.append(Mensagem.USUARIODESTINO);
					sb.append(",");
					sb.append(Mensagem.USUARIOORIGEM);
																
				sb.append(")");
				sb.append("VALUES(?,?,?,?,?,?,?,?,?)");
				
				String[] values = {
						mensagemVO.getIdEmpresa()+"",
						mensagemVO.getDtHrCadastroLong()+"",
						mensagemVO.getIdMensagem()+"",
						mensagemVO.getIdRepresentanetDestino()+"",
						mensagemVO.getIdRepresentanetOrigem()+"",
						mensagemVO.getMensagem(),
						mensagemVO.getTitulo(),
						mensagemVO.getUsuarioDestino()+"",
						mensagemVO.getUsuarioOrigem()+""
				};								

				db.execSQL(sb.toString(), values);
	}
	
	public void close(){
		if(sqlLiteHelper != null){
			sqlLiteHelper.close();
			db.close();
		}
	}

	
}
