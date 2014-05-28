package br.com.slim.venda.cidade;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.com.slim.venda.cidade.CidadeVO.Cidade;
import br.com.slim.venda.dataBase.SQLiteHelper;
import br.com.slim.venda.item.ItemVO.Item;

public class CidadeDAO {


	private Context context;
	private static CidadeDAO instance;
	private static SQLiteDatabase db;

	private CidadeDAO(Context context) {		
		this.context = context;
	}

	public static CidadeDAO getInstance(Context context){
		if(instance == null){
			synchronized (CidadeDAO.class){
				if(instance == null){
					instance = new CidadeDAO(context);
					db = context.openOrCreateDatabase(SQLiteHelper.DATABASENAME, 
							Context.MODE_PRIVATE, null);
				}
			}
		}
		return instance;
	}

	/**
	 * Retorna as cidades que contenha determinada palavra
	 * @param municipio
	 * @return ArrayList<CidadeVO>
	 */
	public ArrayList<CidadeVO> getCities(String municipio){
		Cursor c = db.query(Cidade.TABELA, Cidade.COLUNAS,  null,
				null,null,null,null);
		return getCities(c);
	}

	/**
	 * Retorna a cidade com o código informado
	 * @param codigo
	 * @return CidadeVO
	 */
	public CidadeVO getCity(int codigo){
		Cursor c = db.query(Cidade.TABELA, Cidade.COLUNAS, Cidade.IDCIDADE +" = ?",
				new String[]{codigo+""},null,null,null);
		return getCity(c);
	}

	public CidadeVO getCity(Cursor c){
		CidadeVO cidadeVO = null;
		if(c.moveToFirst()){
			int idxIdCidade = c.getColumnIndex(Cidade.IDCIDADE);
			int idxMunicipio = c.getColumnIndex(Cidade.MUNICIPIO);
			int idxUf = c.getColumnIndex(Cidade.UF);

			cidadeVO = new CidadeVO();
			cidadeVO.setUF(c.getString(idxUf));
			cidadeVO.setIdCidade(c.getInt(idxIdCidade));
			cidadeVO.setMunicipio(c.getString(idxMunicipio));
		}
		c.close();
		return cidadeVO;
	}


	public ArrayList<CidadeVO> getCities(Cursor c){
		ArrayList<CidadeVO> lsCidade = new ArrayList<CidadeVO>();

		if(c.moveToFirst()){
			int idxIdCidade = c.getColumnIndex(Cidade.IDCIDADE);
			int idxMunicipio = c.getColumnIndex(Cidade.MUNICIPIO);
			int idxUf = c.getColumnIndex(Cidade.UF);
			do{
				CidadeVO cidadeVO = new CidadeVO();
				cidadeVO.setUF(c.getString(idxUf));
				cidadeVO.setIdCidade(c.getInt(idxIdCidade));
				cidadeVO.setMunicipio(c.getString(idxMunicipio));
				lsCidade.add(cidadeVO);
			} while(c.moveToNext());
		}
		c.close();
		return lsCidade;
	}

	public void close(){
		if(db.isOpen()){
			db.close();
		}
	}
}
