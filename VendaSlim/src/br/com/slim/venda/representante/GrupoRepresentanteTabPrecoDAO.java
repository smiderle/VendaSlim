package br.com.slim.venda.representante;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import br.com.slim.venda.dataBase.SQLiteHelper;
import br.com.slim.venda.representante.GrupoRepresentanteParcelamentoVO.GrupoRepresentanteParcelamento;
import br.com.slim.venda.representante.GrupoRepresentanteTabPrecoVO.GrupoRepresentanteTabPreco;


public class GrupoRepresentanteTabPrecoDAO {

	String CATEGORIA = "GRUPO_REPRESENTANTE_TABPRECO_DAO";
	SQLiteHelper sqlLiteHelper;
	SQLiteDatabase db =  null;
	
	
	public GrupoRepresentanteTabPrecoDAO(Context context) {
		sqlLiteHelper = new SQLiteHelper(context);
		this.db = sqlLiteHelper.getWritableDatabase();
	}
	
	public void insertOrUpdate(final GrupoRepresentanteTabPrecoVO grtVO){
		StringBuffer sb = new StringBuffer();
				sb.append("INSERT OR REPLACE INTO ");
				sb.append(GrupoRepresentanteTabPreco.TABELA);
				sb.append("(");
					sb.append(GrupoRepresentanteTabPreco.IDEMPRESA);
					sb.append(",");
					sb.append(GrupoRepresentanteTabPreco.IDFILIAL);
					sb.append(",");
					sb.append(GrupoRepresentanteTabPreco.IDGRUPO);
					sb.append(",");
					sb.append(GrupoRepresentanteTabPreco.IDTABELA);					
																
				sb.append(")");
				sb.append("VALUES(?,?,?,?)");
				
				String[] values = {
						grtVO.getIdEmpresa()+"",
						grtVO.getIdFilial()+"",
						grtVO.getIdGrupo()+"",
						grtVO.getIdTabPreco()+""
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
