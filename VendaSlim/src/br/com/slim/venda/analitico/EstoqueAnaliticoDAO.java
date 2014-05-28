package br.com.slim.venda.analitico;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.com.slim.venda.analitico.EstoqueAnaliticoVO.EstoqueAnalitico;
import br.com.slim.venda.dataBase.SQLiteHelper;
import br.com.slim.venda.item.ItemVO;
import br.com.slim.venda.usuario.UsuarioVO;

public class EstoqueAnaliticoDAO {
	
	private Context context;
	private static EstoqueAnaliticoDAO instance;
	private static SQLiteDatabase db;
	
	private EstoqueAnaliticoDAO(Context context) {
		this.context = context;
	}
	
	public static EstoqueAnaliticoDAO getInstance(Context context){
		if(instance == null){
			synchronized (EstoqueAnaliticoDAO.class){
				if(instance == null){
					instance = new EstoqueAnaliticoDAO(context);
					db = context.openOrCreateDatabase(SQLiteHelper.DATABASENAME, 
							Context.MODE_PRIVATE, null);
				}
			}
		}
		return instance;
	}
	
	
	public long insert(EstoqueAnaliticoVO estoqueAnaliticoVO){
		ContentValues cv = new ContentValues();
		cv.put(EstoqueAnalitico.IDITEM, estoqueAnaliticoVO.getItemVO().getIdItem());
		cv.put(EstoqueAnalitico.DTMOVIMENTACAO, estoqueAnaliticoVO.getDateMov());
		cv.put(EstoqueAnalitico.QUANTIDADE, estoqueAnaliticoVO.getQuantidade());
		cv.put(EstoqueAnalitico.TIPOMOVIMENTACAO, estoqueAnaliticoVO.getTipoMov());
		cv.put(EstoqueAnalitico.IDEMPRESA, estoqueAnaliticoVO.getIdEmpresa());
		long insert = db.insert(EstoqueAnalitico.TABELA, null, cv);
		return insert;
	}
	
	
	public ArrayList<EstoqueAnaliticoVO> getAllByItem(ItemVO itemVO){
		Cursor c = db.query(EstoqueAnalitico.TABELA, EstoqueAnalitico.COLUNAS, 
				EstoqueAnalitico.IDITEM +"= ? AND "+EstoqueAnalitico.IDEMPRESA+" = ?", 
				new String[]{itemVO.getIdItem()+"",UsuarioVO.idEmpresa+"" }, null, null, null);
		return getAll(c);
	}
	/**
	 * Retorna a movimentação por Item e tipo de movimentação
	 * @param itemVO
	 * @param tipoMov
	 * @return
	 */
	public ArrayList<EstoqueAnaliticoVO> getAllByItemType(ItemVO itemVO, int tipoMov){
		Cursor c = db.query(EstoqueAnalitico.TABELA, EstoqueAnalitico.COLUNAS, 
				EstoqueAnalitico.IDITEM +"= ? AND "+EstoqueAnalitico.IDEMPRESA+" = ? AND "+
						EstoqueAnalitico.TIPOMOVIMENTACAO+" = ?", 
				new String[]{itemVO.getIdItem()+"",UsuarioVO.idEmpresa+"", tipoMov+"" }, null, null, null);
		return getAll(c);
	}
	
	
	
	public ArrayList<EstoqueAnaliticoVO> getAll(Cursor c){
		ArrayList<EstoqueAnaliticoVO> lsAnalitico = new ArrayList<EstoqueAnaliticoVO>();
		if(c.moveToFirst()){
			int idxDtMovimentacao = c.getColumnIndex(EstoqueAnalitico.DTMOVIMENTACAO);
			int idxQuantidade = c.getColumnIndex(EstoqueAnalitico.QUANTIDADE);
			int idxTipoMov = c.getColumnIndex(EstoqueAnalitico.TIPOMOVIMENTACAO);
			int idxIdItem = c.getColumnIndex(EstoqueAnalitico.IDITEM);
			int idxIdEmpresa = c.getColumnIndex(EstoqueAnalitico.IDEMPRESA);
			
			do{
				EstoqueAnaliticoVO analitico = new EstoqueAnaliticoVO();
				analitico.setDateMov(c.getLong(idxDtMovimentacao));
				analitico.setIdEmpresa(c.getInt(idxIdEmpresa));
				analitico.setQuantidade(c.getDouble(idxQuantidade));
				analitico.setTipoMov(c.getInt(idxTipoMov));
				
				ItemVO itemVO = new ItemVO();
				itemVO.setIdItem(c.getInt(idxIdItem));
				analitico.setItemVO(itemVO);
				lsAnalitico.add(analitico);
			} while(c.moveToNext());
		}
		return lsAnalitico;
	}

}
