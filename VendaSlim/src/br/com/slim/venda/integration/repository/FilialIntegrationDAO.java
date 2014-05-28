package br.com.slim.venda.integration.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import br.com.slim.venda.dataBase.SQLiteHelper;
import br.com.slim.venda.filial.FilialVO.Filial;
import br.com.slim.venda.integration.domain.FilialIntegration;

public class FilialIntegrationDAO {
	String CATEGORIA = "FilialIntegrationDAO";
	SQLiteHelper sqlLiteHelper;
	SQLiteDatabase db =  null;
	
	
	public FilialIntegrationDAO(Context context) {
		sqlLiteHelper = new SQLiteHelper(context);
		this.db = sqlLiteHelper.getWritableDatabase();
	}
	
	public void insertOrUpdate(final FilialIntegration filialIntegration){
		StringBuffer sb = new StringBuffer();
				sb.append("INSERT OR REPLACE INTO ");
				sb.append(Filial.TABELA);
				sb.append("(");
					sb.append(Filial.BAIRRO);
					sb.append(",");
					sb.append(Filial.CEP);
					sb.append(",");
					sb.append(Filial.FAX);
					sb.append(",");
					sb.append(Filial.FONE);
					sb.append(",");
					sb.append(Filial.IDEMPRESA);
					sb.append(",");
					sb.append(Filial.IDFILIAL);
					sb.append(",");
					sb.append(Filial.NOMEFANTASIA);
					sb.append(",");
					sb.append(Filial.NUMERO);
					sb.append(",");
					sb.append(Filial.RAZAOSOCIAL);
					sb.append(",");
					sb.append(Filial.RUA);
					sb.append(",");
					sb.append(Filial.WEBSITE);									
				sb.append(")");
				sb.append("VALUES(?,?,?,?,?,?,?,?,?,?,?)");
				
				String[] values = {
						filialIntegration.getBairro(),
						filialIntegration.getCep(),
						filialIntegration.getFax(),
						filialIntegration.getFone(),
						filialIntegration.getIdEmpresa()+"",
						filialIntegration.getIdFilial()+"",
						filialIntegration.getNomeFantasia()+"",
						filialIntegration.getNumero(),
						filialIntegration.getRazaoSocial(),
						filialIntegration.getRua(),
						filialIntegration.getWebsite()
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
