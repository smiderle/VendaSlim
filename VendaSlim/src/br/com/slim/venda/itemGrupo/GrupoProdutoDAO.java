package br.com.slim.venda.itemGrupo;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.com.slim.venda.dataBase.SQLiteHelper;
import br.com.slim.venda.itemGrupo.GrupoProdutoVO.GrupoProduto;

public class GrupoProdutoDAO {
	
	String CATEGORIA = "GRUPO_PRODUTO";
	SQLiteHelper sqlLiteHelper;
	SQLiteDatabase db =  null;
		
	public GrupoProdutoDAO(Context context) {
		sqlLiteHelper = new SQLiteHelper(context);
		this.db = sqlLiteHelper.getWritableDatabase();
	}
	
	public Cursor getCursor(){
		try{			
			return db.query(GrupoProduto.TABELA, GrupoProduto.COLUNAS, null, null, null, null, null, null);			
		} catch (SQLException e) {
			Log.e(CATEGORIA, "Erro ao buscar os itenGrupos: " + e.toString());
			return null;
		}
	}
	
	public ArrayList<GrupoProdutoVO> getAll(){
		ArrayList<GrupoProdutoVO> lsItemGrupo = new ArrayList<GrupoProdutoVO>();
		Cursor c = getCursor();
		if(c.moveToFirst()){
			int idxIdItemGrupo = c.getColumnIndex(GrupoProduto.IDGRUPO);				
			int idxDescricao = c.getColumnIndex(GrupoProduto.DESCRICAO);
			do{
				GrupoProdutoVO itemGrupo = new GrupoProdutoVO();
				itemGrupo.setDescricao(c.getString(idxDescricao));
				itemGrupo.setIdGrupo(c.getInt(idxIdItemGrupo));
				lsItemGrupo.add(itemGrupo);
			} while(c.moveToNext());
		}
		c.close();
		close();
		return lsItemGrupo;
	}
	
	public void close(){
		if(sqlLiteHelper != null){
			sqlLiteHelper.close();
		}
	}

}
