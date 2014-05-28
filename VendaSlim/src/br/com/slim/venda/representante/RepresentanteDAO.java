package br.com.slim.venda.representante;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.com.slim.venda.dataBase.SQLiteHelper;
import br.com.slim.venda.item.ItemVO.Item;
import br.com.slim.venda.representante.RepresentanteVO.Representante;

public class RepresentanteDAO {
	String CATEGORIA = "REPRESENTANTE_DAO";
	SQLiteHelper sqlLiteHelper;
	SQLiteDatabase db =  null;
	
	
	public RepresentanteDAO(Context context) {
		sqlLiteHelper = new SQLiteHelper(context);
		this.db = sqlLiteHelper.getWritableDatabase();
	}
	
	public RepresentanteVO getRepresentanteByLogin(String login, String senha){
		String[] args = {login.toUpperCase(), senha.toUpperCase()};
		Cursor c  = db.rawQuery("SELECT "+Representante.IDEMPRESA+", "+Representante.IDREPRESENTANTE
					+" FROM "+ Representante.TABELA+" where UPPER("+Representante.LOGIN+") = ? AND UPPER("+Representante.SENHA+") = ? ", args);
		
		//Cursor c = db.query(Representante.TABELA, Representante.COLUNAS, Representante.LOGIN+" = ? AND "+ Representante.SENHA+" = ? ", new String[]{login, senha}, null,null,null,null);
		RepresentanteVO repVO = null;
		if(c.moveToFirst()){	
			repVO = new RepresentanteVO();
			int idxIdEmpresa = c.getColumnIndex(Representante.IDEMPRESA);
			int idxIdRepresentante = c.getColumnIndex(Representante.IDREPRESENTANTE);
			
			repVO.setIdEmpresa(c.getInt(idxIdEmpresa));
			repVO.setIdRepresentante(c.getInt(idxIdRepresentante));
		
		}
		return repVO;
	}
	
	public void insertOrUpdate(final RepresentanteVO representanteVO){
		StringBuffer sb = new StringBuffer();
				sb.append("INSERT OR REPLACE INTO ");
				sb.append(Representante.TABELA);
				sb.append("(");
					sb.append(Representante.IDEMPRESA);					
					sb.append(",");
					sb.append(Representante.IDREPRESENTANTE);
					sb.append(",");
					sb.append(Representante.INATIVO);
					sb.append(",");
					sb.append(Representante.LOGIN);
					sb.append(",");
					sb.append(Representante.NOME);
					sb.append(",");
					sb.append(Representante.SENHA);
				sb.append(")");
				sb.append("VALUES(?,?,?,?,?,?)");
				
				String[] values = {
						representanteVO.getIdEmpresa()+"",
						representanteVO.getIdRepresentante()+"",
						representanteVO.isInativo()+"",
						representanteVO.getLogin()+"",
						representanteVO.getNome(),
						representanteVO.getSenha()+"",
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
