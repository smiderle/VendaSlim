package br.com.slim.venda.representante;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import br.com.slim.venda.dataBase.SQLiteHelper;
import br.com.slim.venda.representante.RepresentanteFilialVO.RepresentanteFilial;
import br.com.slim.venda.representante.RepresentanteParcelamentoVO.RepresentanteParcelamento;
import br.com.slim.venda.representante.RepresentanteVO.Representante;

public class RepresentanteParcelamentoDAO {
	String CATEGORIA = "REPRESENTANTE_PARCELAMENTO_DAO";
	SQLiteHelper sqlLiteHelper;
	SQLiteDatabase db =  null;
	
	
	public RepresentanteParcelamentoDAO(Context context) {
		sqlLiteHelper = new SQLiteHelper(context);
		this.db = sqlLiteHelper.getWritableDatabase();
	}
	
	public void insertOrUpdate(final RepresentanteParcelamentoVO representanteParcelamentoVO){
		StringBuffer sb = new StringBuffer();
				sb.append("INSERT OR REPLACE INTO ");
				sb.append(RepresentanteParcelamento.TABELA);
				sb.append("(");
					sb.append(RepresentanteParcelamento.IDEMPRESA);
					sb.append(",");
					sb.append(RepresentanteParcelamento.IDFILIAL);
					sb.append(",");
					sb.append(RepresentanteParcelamento.IDREPRESENTANTE);
					sb.append(",");
					sb.append(RepresentanteParcelamento.IDPARCELAMENTO);					
				sb.append(")");
				sb.append("VALUES(?,?,?,?)");
				
				String[] values = {						
						representanteParcelamentoVO.getIdEmpresa()+"",
						representanteParcelamentoVO.getIdFilial()+"",
						representanteParcelamentoVO.getIdRepresentante()+"",
						representanteParcelamentoVO.getIdParcelamento()+""
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
