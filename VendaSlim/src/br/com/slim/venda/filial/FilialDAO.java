package br.com.slim.venda.filial;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.com.slim.venda.dataBase.SQLiteHelper;
import br.com.slim.venda.filial.FilialVO.Filial;

public class FilialDAO {
	
	String CATEGORIA = "FILIAL";
	SQLiteHelper sqlLiteHelper;
	SQLiteDatabase db =  null;

	
	public FilialDAO(Context context) {
		sqlLiteHelper = new SQLiteHelper(context);
		this.db = sqlLiteHelper.getWritableDatabase();
	}
	
	public ArrayList<FilialVO> getAllBasic(){		
		Cursor c = db.query(Filial.TABELA, Filial.COLUNAS, null, null, null, null, Filial.NOMEFANTASIA);
		
		ArrayList<FilialVO> lsFilialVO = null;
		if(c.moveToFirst()){
			lsFilialVO = new ArrayList<FilialVO>();
			int idxIdFilial = c.getColumnIndex(Filial.IDFILIAL);
			int idxIdEmpresa = c.getColumnIndex(Filial.IDEMPRESA);				
			int idxNomeFantasia = c.getColumnIndex(Filial.NOMEFANTASIA);						
			int idxRazaoSocial = c.getColumnIndex(Filial.RAZAOSOCIAL);
			
			do{
				FilialVO filialVO = new FilialVO();
				filialVO.setIdEmpresa(c.getInt(idxIdEmpresa));
				filialVO.setIdFilial(c.getInt(idxIdFilial));
				filialVO.setNomeFantasia(c.getString(idxNomeFantasia));				
				filialVO.setRazaoSocial(c.getString(idxRazaoSocial));
				
				lsFilialVO.add(filialVO);
			} while(c.moveToNext());
		}
		c.close();
		
		return lsFilialVO;
	}
	
	
/*	String SQL = "CREATE TABLE IF NOT EXISTS "+Filial.TABELA+" (" +
			Filial.IDEMPRESA+" INTEGER NOT NULL , " +
			Filial.IDFILIAL+" INTEGER  NOT NULL , "+						
			Filial.BAIRRO+" TEXT ,"+
			Filial.CEP+" TEXT,"+
			Filial.FAX+" TEXT,"+
			Filial.FONE+" TEXT,"+
			Filial.NOMEFANTASIA+" TEXT," +
			Filial.NUMERO+" TEXT, "+
			Filial.RAZAOSOCIAL+" TEXT ,"+
			Filial.RUA+" TEXT, "+
			Filial.WEBSITE+" TEXT, "+
			"PRIMARY KEY("+Filial.IDEMPRESA+", "+Filial.IDEMPRESA+")  );";
			
return SQL;
}*/
	
}
