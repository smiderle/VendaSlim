package br.com.slim.venda.representante;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import br.com.slim.venda.dataBase.SQLiteHelper;
import br.com.slim.venda.representante.RepresentanteTabPrecoVO.RepresentanteTabPreco;

public class RepresentanteTabPrecoDAO {
	String CATEGORIA = "REPRESENTANTE_TABPRECO_DAO";
	SQLiteHelper sqlLiteHelper;
	SQLiteDatabase db =  null;
	
	
	public RepresentanteTabPrecoDAO(Context context) {
		sqlLiteHelper = new SQLiteHelper(context);
		this.db = sqlLiteHelper.getWritableDatabase();
	}
	
	public void insertOrUpdate(final RepresentanteTabPrecoVO representanteTabPrecoVO){
		StringBuffer sb = new StringBuffer();
				sb.append("INSERT OR REPLACE INTO ");
				sb.append(RepresentanteTabPreco.TABELA);
				sb.append("(");
					sb.append(RepresentanteTabPreco.IDEMPRESA);
					sb.append(",");
					sb.append(RepresentanteTabPreco.IDFILIAL);
					sb.append(",");
					sb.append(RepresentanteTabPreco.IDREPRESENTANTE);
					sb.append(",");
					sb.append(RepresentanteTabPreco.IDTABELAPRECO);					
				sb.append(")");
				sb.append("VALUES(?,?,?,?)");
				
				String[] values = {						
						representanteTabPrecoVO.getIdEmpresa()+"",
						representanteTabPrecoVO.getIdFilial()+"",
						representanteTabPrecoVO.getIdRepresentante()+"",
						representanteTabPrecoVO.getIdTabPreco()+""
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
