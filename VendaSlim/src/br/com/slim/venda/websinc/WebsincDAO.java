package br.com.slim.venda.websinc;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import br.com.slim.venda.dataBase.SQLiteHelper;

public class WebsincDAO {
	String CATEGORIA = "WEBSINC";
	SQLiteHelper sqlLiteHelper;
	SQLiteDatabase db =  null;
	
	public WebsincDAO(Context context) {
		sqlLiteHelper = new SQLiteHelper(context);
		this.db = sqlLiteHelper.getWritableDatabase();
	}
	
	
	public void execute(String query){
		db.execSQL(query);
		//db.rawQuery("UPDATE CLIENTE SET IDEMPRESA = IDEMPRESA , NOME = 'FLORIANO PEIXOTO 10' WHERE IDEMPRESA = 15 AND IDFILIAL = 1 AND IDCLIENTE = 9",null);
		/*try{
		db.execSQL("DELETE FROM CLIENTE",null);
		}catch(Exception e){
			e.printStackTrace();
		}*/
	}

}
