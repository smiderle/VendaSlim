package br.com.slim.venda.sincroniza;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import br.com.slim.venda.dataBase.SQLiteHelper;
import br.com.slim.venda.sincroniza.SincronizaVO.Sincroniza;
import br.com.slim.venda.usuario.UsuarioVO;

public class SincronizaDAO {
	

	String CATEGORIA = "SINCRONIZA";
	SQLiteHelper sqlLiteHelper;
	SQLiteDatabase db =  null;

	
	public SincronizaDAO(Context context) {
		sqlLiteHelper = new SQLiteHelper(context);
		this.db = sqlLiteHelper.getWritableDatabase();
	}
	
	public ContentValues getContentValues(SincronizaVO sincronizaVO){
		ContentValues cv = new ContentValues();
		cv.put(Sincroniza.IDFILIAL, sincronizaVO.getIdFilial());
		cv.put(Sincroniza.DTHRSINC, sincronizaVO.getDtHrSincronizacao());
		cv.put(Sincroniza.NOME_TABELA, sincronizaVO.getTabela());
		return cv;
	}
	
	public void insertOrUpdate(SincronizaVO sincronizaVO){
		String[] values = {sincronizaVO.getIdFilial()+"",
				sincronizaVO.getTabela(),
				sincronizaVO.getDtHrSincronizacao()+""};
		
		db.execSQL("INSERT OR REPLACE INTO "+ Sincroniza.TABELA+" (" +Sincroniza.IDFILIAL+", "+Sincroniza.NOME_TABELA+","+Sincroniza.DTHRSINC+")"+
				"VALUES(?,?,?)", values);
	}
	
	public Integer insert(SincronizaVO sincronizaVO) throws SQLiteConstraintException{
		int inserts = -1;
		try {
			ContentValues cv = getContentValues(sincronizaVO);
			inserts = (int) db.insert(Sincroniza.TABELA, null, cv);
		} catch (SQLiteConstraintException e) {
			throw e;
		}
		return inserts;
	}
	
	public Integer update(SincronizaVO sincronizaVO){		
		return update(getContentValues(sincronizaVO));
	}
	
	public Integer update(ContentValues cv){
		return db.update(Sincroniza.TABELA, cv, Sincroniza.IDFILIAL+" = ? AND "+Sincroniza.NOME_TABELA+" = ?",
				new String[]{cv.get(Sincroniza.IDFILIAL)+"", cv.get(Sincroniza.NOME_TABELA)+""});
	}
	
	public long getHoraUltimaSinc(String tabela){
		Cursor c = db.query(Sincroniza.TABELA, Sincroniza.COLUNAS, Sincroniza.IDFILIAL + " = ? AND "+ Sincroniza.NOME_TABELA +" = ?",
				new String[]{UsuarioVO.idFilial+"", tabela},null,null,null);
				
		if(c.moveToFirst()){		
			int idxDtHrSincronizacao = c.getColumnIndex(Sincroniza.DTHRSINC);
			return c.getLong(idxDtHrSincronizacao);
		}
		return 1;
	}
	
	
	public void close(){
		if(sqlLiteHelper != null){
			sqlLiteHelper.close();
		}
	}

}
