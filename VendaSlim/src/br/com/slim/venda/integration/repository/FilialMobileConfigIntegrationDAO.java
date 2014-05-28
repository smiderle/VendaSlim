package br.com.slim.venda.integration.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import br.com.slim.venda.dataBase.SQLiteHelper;
import br.com.slim.venda.filial.FilialMobileConfigVO.FilialMobileConfig;
import br.com.slim.venda.integration.domain.FilialMobileConfigIntegration;

public class FilialMobileConfigIntegrationDAO {
	String CATEGORIA = "FILIALCONFIG";
	SQLiteHelper sqlLiteHelper;
	SQLiteDatabase db =  null;
	
	
	public FilialMobileConfigIntegrationDAO(Context context) {
		sqlLiteHelper = new SQLiteHelper(context);
		this.db = sqlLiteHelper.getWritableDatabase();
	}
	
	public void insertOrUpdate(final FilialMobileConfigIntegration filialMobileConfigIntegration){
		StringBuffer sb = new StringBuffer();
				sb.append("INSERT OR REPLACE INTO ");
				sb.append(FilialMobileConfig.TABELA);
				sb.append("(");					
					sb.append(FilialMobileConfig.ACAOLIMITECREDITO);
					sb.append(",");
					sb.append(FilialMobileConfig.ACAOTITULOVENCIDO);
					sb.append(",");
					sb.append(FilialMobileConfig.CADASTRARCLIENTE);
					sb.append(",");
					sb.append(FilialMobileConfig.DIASVENCIEMENTO);
					sb.append(",");
					sb.append(FilialMobileConfig.EMAILPEDIDOS);
					sb.append(",");
					sb.append(FilialMobileConfig.ENVIAREMAILCLIENTE);
					sb.append(",");
					sb.append(FilialMobileConfig.ESTOQUENEGATIVO);
					sb.append(",");
					sb.append(FilialMobileConfig.EXIBEESTOQUE);
					sb.append(",");
					sb.append(FilialMobileConfig.IDEMPRESA);
					sb.append(",");
					sb.append(FilialMobileConfig.IDFILIAL);									
				sb.append(")");
				sb.append("VALUES(?,?,?,?,?,?,?,?,?,?)");
				
				String[] values = {
						
						filialMobileConfigIntegration.getAcaoLimiteCredito()+"",
						filialMobileConfigIntegration.getAcaoTituloVencido()+"",
						filialMobileConfigIntegration.isPermiteCadastrarCliente()+"",
						filialMobileConfigIntegration.getDiasVenciemento()+"",
						filialMobileConfigIntegration.getEmailPedidos()+"",
						filialMobileConfigIntegration.isEnviarEmailCliente()+"",
						filialMobileConfigIntegration.isPermiteVendaEstoqueNegativo()+"",
						filialMobileConfigIntegration.isExibeEstoque()+"",
						filialMobileConfigIntegration.getIdEmpresa()+"",
						filialMobileConfigIntegration.getIdFilial()+""
						
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
