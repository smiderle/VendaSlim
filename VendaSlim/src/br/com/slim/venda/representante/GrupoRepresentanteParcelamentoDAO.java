package br.com.slim.venda.representante;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import br.com.slim.venda.dataBase.SQLiteHelper;
import br.com.slim.venda.representante.GrupoRepresentanteParcelamentoVO.GrupoRepresentanteParcelamento;


public class GrupoRepresentanteParcelamentoDAO {

	String CATEGORIA = "GRUPO_REPRESENTANTE_PARCELAMENTO_DAO";
	SQLiteHelper sqlLiteHelper;
	SQLiteDatabase db =  null;
	
	
	public GrupoRepresentanteParcelamentoDAO(Context context) {
		sqlLiteHelper = new SQLiteHelper(context);
		this.db = sqlLiteHelper.getWritableDatabase();
	}
	
	public void insertOrUpdate(final GrupoRepresentanteParcelamentoVO grpVO){
		StringBuffer sb = new StringBuffer();
				sb.append("INSERT OR REPLACE INTO ");
				sb.append(GrupoRepresentanteParcelamento.TABELA);
				sb.append("(");
					sb.append(GrupoRepresentanteParcelamento.IDEMPRESA);
					sb.append(",");
					sb.append(GrupoRepresentanteParcelamento.IDFILIAL);
					sb.append(",");
					sb.append(GrupoRepresentanteParcelamento.IDGRUPO);
					sb.append(",");
					sb.append(GrupoRepresentanteParcelamento.IDPARCELA);					
																
				sb.append(")");
				sb.append("VALUES(?,?,?,?)");
				
				String[] values = {
						grpVO.getIdEmpresa()+"",
						grpVO.getIdFilial()+"",
						grpVO.getIdGrupo()+"",
						grpVO.getIdParcelamento()+""											
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
