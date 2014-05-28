package br.com.slim.venda.versao;

import java.util.ArrayList;
import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.com.slim.venda.dataBase.SQLiteHelper;
import br.com.slim.venda.item.ItemVO;
import br.com.slim.venda.item.ItemVO.Item;
import br.com.slim.venda.usuario.UsuarioVO;
import br.com.slim.venda.versao.VersaoPdaVO.Versao;

public class VersaoDAO {
	
	String CATEGORIA = "VERSAO";
	SQLiteHelper sqlLiteHelper;
	SQLiteDatabase db =  null;
	
	public VersaoDAO(Context context) {
		this.db = context.openOrCreateDatabase(SQLiteHelper.DATABASENAME, Context.MODE_PRIVATE, null);
	}
	
	
	public long insert(VersaoPdaVO versaoPdaVO){
		ContentValues cv = getContentValues(versaoPdaVO);
		return insert(cv);
	}
	
	public long insert(ContentValues cv){
		return db.insert(Versao.TABELA, null, cv);
	}
	
	private ContentValues getContentValues(VersaoPdaVO versaoPdaVO){
		ContentValues cv = new ContentValues();
		cv.put(Versao.BUILD, versaoPdaVO.getBuild());
		if(versaoPdaVO.getDataExpiracao() != null)
			cv.put(Versao.DATAEXPIRACAO, versaoPdaVO.getDataExpiracao().getTime());
		cv.put(Versao.LICENCA, versaoPdaVO.getLicenca());
		cv.put(Versao.SERIAL, versaoPdaVO.getSerial());
		cv.put(Versao.VERSAO, versaoPdaVO.getVersao());
		cv.put(Versao.VERSAOEXPIRADA, versaoPdaVO.isVersaoExpirada());
		return cv;
	}
	
	public VersaoPdaVO getVersaoPda (){
		Cursor c = db.query(Versao.TABELA, Versao.COLUNAS, null, null, null, null, null, null);
		return getVersaoPda(c);
	}
	
	public VersaoPdaVO getVersaoPda(Cursor c){
		VersaoPdaVO versaoPdaVO = new VersaoPdaVO();
		if(c.moveToFirst()){
			int idxBuild = c.getColumnIndex(Versao.BUILD);
			int idxDataExpiracao = c.getColumnIndex(Versao.DATAEXPIRACAO);
			int idxLicenca = c.getColumnIndex(Versao.LICENCA);
			int idxSerial = c.getColumnIndex(Versao.SERIAL);
			int idxVersao = c.getColumnIndex(Versao.VERSAO);
			int idxVersaoExpirada = c.getColumnIndex(Versao.VERSAOEXPIRADA);
			
			versaoPdaVO.setBuild(c.getString(idxBuild));
			versaoPdaVO.setLicenca(c.getInt(idxLicenca));
			versaoPdaVO.setSerial(c.getString(idxSerial));
			versaoPdaVO.setVersao(c.getString(idxVersao));
			versaoPdaVO.setDataExpiracao(new Date(c.getLong(idxDataExpiracao)));
			versaoPdaVO.setVersaoExpirada(c.getInt(idxVersaoExpirada) == 1);
		}
		c.close();
		
		return versaoPdaVO;
	}
	
	
	public void updateDataExpiracao(long dataExpiracao, boolean versaoExpirada){	
		db.execSQL("UPDATE "+Versao.TABELA+" set "+Versao.DATAEXPIRACAO+" = ?, " +Versao.VERSAOEXPIRADA+" = ? ", 
				new String[]{
					dataExpiracao+"", 
					versaoExpirada ? 1+"" : 0+""
				});
	}	
	
	
	

}
