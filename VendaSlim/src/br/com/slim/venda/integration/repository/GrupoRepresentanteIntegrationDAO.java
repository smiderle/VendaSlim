package br.com.slim.venda.integration.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import br.com.slim.venda.dataBase.SQLiteHelper;
import br.com.slim.venda.integration.domain.GrupoProdutoIntegration;
import br.com.slim.venda.integration.domain.GrupoRepresentanteIntegration;
import br.com.slim.venda.itemGrupo.GrupoProdutoVO.GrupoProduto;
import br.com.slim.venda.representante.GrupoRepresentanteVO.GrupoRepresentante;

public class GrupoRepresentanteIntegrationDAO {
	String CATEGORIA = "GRUPO_REPRESENTANTE_DAO";
	SQLiteHelper sqlLiteHelper;
	SQLiteDatabase db =  null;
	
	
	public GrupoRepresentanteIntegrationDAO(Context context) {
		sqlLiteHelper = new SQLiteHelper(context);
		this.db = sqlLiteHelper.getWritableDatabase();
	}
	
	public void insertOrUpdate(final GrupoRepresentanteIntegration grupoRepresentanteIntegration){
		StringBuffer sb = new StringBuffer();
				sb.append("INSERT OR REPLACE INTO ");
				sb.append(GrupoRepresentante.TABELA);
				sb.append("(");
					sb.append(GrupoRepresentante.IDEMPRESA);
					sb.append(",");
					sb.append(GrupoRepresentante.IDFILIAL);
					sb.append(",");
					sb.append(GrupoRepresentante.IDGRUPO);
					sb.append(",");
					sb.append(GrupoRepresentante.DESCMAX);
					sb.append(",");
					sb.append(GrupoRepresentante.DESCRICAO);
					sb.append(",");
					sb.append(GrupoRepresentante.MINVENDA);
					sb.append(",");
					sb.append(GrupoRepresentante.COMICAOVENDA);
				sb.append(")");
				sb.append("VALUES(?,?,?,?,?,?,?)");
				
				String[] values = {
						grupoRepresentanteIntegration.getIdEmpresa()+"",
						grupoRepresentanteIntegration.getIdFilial()+"",
						grupoRepresentanteIntegration.getIdGrupo()+"",
						grupoRepresentanteIntegration.getDescMax()+"",
						grupoRepresentanteIntegration.getDescricao(),
						grupoRepresentanteIntegration.getMinVenda()+"",
						grupoRepresentanteIntegration.getComicaoVenda()+""
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
