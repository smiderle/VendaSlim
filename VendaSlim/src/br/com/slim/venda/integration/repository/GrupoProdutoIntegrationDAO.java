package br.com.slim.venda.integration.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import br.com.slim.venda.dataBase.SQLiteHelper;
import br.com.slim.venda.integration.domain.GrupoProdutoIntegration;
import br.com.slim.venda.itemGrupo.GrupoProdutoVO.GrupoProduto;

public class GrupoProdutoIntegrationDAO {
	String CATEGORIA = "GRUPO_PRODUTO_DAO";
	SQLiteHelper sqlLiteHelper;
	SQLiteDatabase db =  null;
	
	
	public GrupoProdutoIntegrationDAO(Context context) {
		sqlLiteHelper = new SQLiteHelper(context);
		this.db = sqlLiteHelper.getWritableDatabase();
	}
	
	public void insertOrUpdate(final GrupoProdutoIntegration grupoProdutoIntegration){
		StringBuffer sb = new StringBuffer();
				sb.append("INSERT OR REPLACE INTO ");
				sb.append(GrupoProduto.TABELA);
				sb.append("(");
					sb.append(GrupoProduto.IDEMPRESA);
					sb.append(",");
					sb.append(GrupoProduto.IDFILIAL);
					sb.append(",");
					sb.append(GrupoProduto.IDGRUPO);
					sb.append(",");
					sb.append(GrupoProduto.DESCMAX);
					sb.append(",");
					sb.append(GrupoProduto.DESCRICAO);											
				sb.append(")");
				sb.append("VALUES(?,?,?,?,?)");
				
				String[] values = {
						grupoProdutoIntegration.getIdEmpresa()+"",
						grupoProdutoIntegration.getIdFilial()+"",
						grupoProdutoIntegration.getIdGrupo()+"",
						grupoProdutoIntegration.getDescMax()+"",
						grupoProdutoIntegration.getDescricao()						
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
