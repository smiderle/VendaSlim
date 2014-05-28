package br.com.slim.venda.item;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.com.slim.venda.dataBase.SQLiteHelper;
import br.com.slim.venda.integration.domain.GrupoProdutoIntegration;
import br.com.slim.venda.item.ItemVO.Item;
import br.com.slim.venda.itemGrupo.GrupoProdutoVO;
import br.com.slim.venda.itemGrupo.GrupoProdutoVO.GrupoProduto;
import br.com.slim.venda.usuario.UsuarioVO;

public class ItemDAO {	
	String CATEGORIA = "ITEM";
	SQLiteHelper sqlLiteHelper;
	SQLiteDatabase db =  null;
		
	public ItemDAO(Context context) {
		this.db = context.openOrCreateDatabase(SQLiteHelper.DATABASENAME, Context.MODE_PRIVATE, null);
	}
	
	public Cursor getCursorByPrefix(String prefixo){
		try{			
			String clausula = Item.DESCRICAO +" like '%" +prefixo+"%' OR " +Item.IDITEM +" = '"+prefixo+"'";
			return db.query(Item.TABELA, Item.COLUNAS, clausula, null, null, null, null, null);	

		} catch (SQLException e) {
			Log.e(CATEGORIA, "Erro ao buscar os itens: " + e.toString());
			return null;
		}
	}
	
	public Cursor getCursorByIdItemGrupo(Integer idItemGrupo){
		try{			
			String clausula = Item.IDGRUPO +" = " +idItemGrupo;
			return db.query(Item.TABELA, Item.COLUNAS, clausula, null, null, null, null, null);			
		} catch (SQLException e) {
			Log.e(CATEGORIA, "Erro ao buscar os itens: " + e.toString());
			return null;
		}
	}	
	
	public Cursor getCursorByIdItem(int idItem, int idEmpresa){
		return db.query(Item.TABELA, Item.COLUNAS, Item.IDITEM +" = ? AND "+ Item.IDEMPRESA +" = ? ", new String[]{idItem+"", idEmpresa+""}, null,null,null,null);
	}
	
	public int updateEstoque(ItemVO itemVO){
		ContentValues cv = new ContentValues();
		cv.put(Item.ESTOQUE, itemVO.getEstoque());
		
		int updates =  db.update(Item.TABELA, cv, Item.IDITEM+" = ? AND "+Item.IDEMPRESA +" = ?" , new String[]{itemVO.getIdItem()+"", itemVO.getIdEmpresa()+""});		
		return updates;
	}
	
	public void updateEstoque(int idItem, double quantidade){
		ContentValues cv = new ContentValues();
		cv.put(Item.ESTOQUE, idItem);
		db.execSQL("UPDATE "+Item.TABELA+" set "+Item.ESTOQUE+" = "+Item.ESTOQUE+"+ ("+quantidade+")" +
				"WHERE  "+Item.IDITEM+" = ? AND "+Item.IDEMPRESA+" = ?", new String[]{idItem+"", UsuarioVO.idEmpresa+""});
	}	
	
	public ItemVO getItemByID(int idItem, int idEmpresa){
		return getItemByID(getCursorByIdItem(idItem, idEmpresa));
	}
	
	private ItemVO getItemByID(Cursor c){
		ItemVO itemVO = null;
		if(c.moveToFirst()){
			int idxIdItem = c.getColumnIndex(Item.IDITEM);
			int idxIdEmpresa = c.getColumnIndex(Item.IDEMPRESA);
			int idxDescricao = c.getColumnIndex(Item.DESCRICAO);
			int idxEstoque = c.getColumnIndex(Item.ESTOQUE);
			int idxGrupo = c.getColumnIndex(Item.IDGRUPO);
			int idxDescMax = c.getColumnIndex(Item.DESCMAX);
			int idxPreco = c.getColumnIndex(Item.PRECOVENDA);
			int idxPrecoCompra = c.getColumnIndex(Item.PRECOCOMPRA);
			
			itemVO = new ItemVO();
			itemVO.setIdItem(c.getInt(idxIdItem));
			itemVO.setIdEmpresa(c.getInt(idxIdEmpresa));
			itemVO.setDescricao(c.getString(idxDescricao));
			itemVO.setEstoque(c.getDouble(idxEstoque));
			itemVO.setItemGrupo(new GrupoProdutoVO(c.getInt(idxGrupo)));
			itemVO.setDescMax(c.getDouble(idxDescMax));
			itemVO.setPreco(c.getDouble(idxPreco));
			itemVO.setPrecoTabPreco(c.getDouble(idxPreco));			
			itemVO.setPrecoCompra(c.getDouble(idxPrecoCompra));
		}
		c.close();		
		return itemVO;
	}
	
	public Integer getNextId(){
		Cursor c = db.rawQuery("SELECT MAX("+Item.IDITEM+") FROM "+Item.TABELA+
				" WHERE "+ Item.IDEMPRESA +" = "+UsuarioVO.idEmpresa, null);
		if(c.moveToFirst()){
			return c.getInt(0);
		} 
		return 1;
	}
	
	public ArrayList<ItemVO> getAll(){
		Cursor c = db.query(Item.TABELA, Item.COLUNAS, Item.INATIVO+" <> 1 ", null, null, null, null, "25");
		return getAll(c);
	}
	
	public ArrayList<ItemVO> getAllActive(){
		Cursor c = db.query(Item.TABELA, Item.COLUNAS, null, null, null, null, Item.DESCRICAO, "25");
		return getAll(c);
	}
	
	public ArrayList<ItemVO> getAllByPrefixo(String prefixo){
		String clausula = Item.DESCRICAO +" like '%" +prefixo+"%' OR " +Item.IDITEM +" = '"+prefixo+"'";
		Cursor c = db.query(Item.TABELA, Item.COLUNAS, clausula, null, null, null, null, null);
		return getAll(c);
	}
	
	public ArrayList<ItemVO> getAllByPrefixoActive(String prefixo){
		String clausula = Item.INATIVO+" <> 1 AND ("+Item.DESCRICAO +" like '%" +prefixo+"%' OR " +Item.IDITEM +" = '"+prefixo+"')";
		Cursor c = db.query(Item.TABELA, Item.COLUNAS, clausula, null, null, null, null, null);
		return getAll(c);
	}
	
	public ArrayList<ItemVO> getAllByIdItemGrupo(Integer idItemGrupo){
		Cursor c =getCursorByIdItemGrupo(idItemGrupo);
		return getAll(c);
	}
	
	public ArrayList<ItemVO> getAll(Cursor c){
		ArrayList<ItemVO> lsItemVO = new ArrayList<ItemVO>();
		if(c.moveToFirst()){
			int idxIdItem = c.getColumnIndex(Item.IDITEM);
			int idxIdEmpresa = c.getColumnIndex(Item.IDEMPRESA);
			int idxDescricao = c.getColumnIndex(Item.DESCRICAO);
			int idxEstoque = c.getColumnIndex(Item.ESTOQUE);
			int idxGrupo = c.getColumnIndex(Item.IDGRUPO);
			int idxDescMax = c.getColumnIndex(Item.DESCMAX);
			int idxPreco = c.getColumnIndex(Item.PRECOVENDA);
			int idxInativo = c.getColumnIndex(Item.INATIVO);
			int idxPrecoCompra = c.getColumnIndex(Item.PRECOCOMPRA);
			
			do{
				ItemVO itemVO = new ItemVO();
				itemVO.setIdItem(c.getInt(idxIdItem));
				itemVO.setIdEmpresa(c.getInt(idxIdEmpresa));
				itemVO.setDescricao(c.getString(idxDescricao));
				itemVO.setEstoque(c.getDouble(idxEstoque));
				itemVO.setItemGrupo(new GrupoProdutoVO(c.getInt(idxGrupo)));
				itemVO.setDescMax(c.getDouble(idxDescMax));
				itemVO.setPreco(c.getDouble(idxPreco));
				itemVO.setPrecoTabPreco(c.getDouble(idxPreco));
				itemVO.setInativo(c.getInt(idxInativo) == 1);
				itemVO.setPrecoCompra(c.getDouble(idxPrecoCompra));
				lsItemVO.add(itemVO);
			} while(c.moveToNext());
		}
		c.close();
		
		return lsItemVO;
	}

	public long insert(ItemVO itemVO){
		ContentValues cv = getContentValues(itemVO);
		cv.put(Item.IDEMPRESA, UsuarioVO.idEmpresa);
		cv.put(Item.IDITEM, itemVO.getIdItem());
		return insert(cv);
	}
	
	public long insert(ContentValues cv){
		return db.insert(Item.TABELA, null, cv);
	}
	
	public int update(ItemVO itemVO){
		return update(getContentValues(itemVO), itemVO);
	}
	
	public int update(ContentValues cv, ItemVO itemVO){
		return db.update(Item.TABELA, cv, Item.IDITEM +" = ? AND "+Item.IDEMPRESA +" = ?", 
				new String[]{itemVO.getIdItem()+"", itemVO.getIdEmpresa()+""});
	}
	

	private ContentValues getContentValues(ItemVO itemVO){
		ContentValues cv = new ContentValues();
		cv.put(Item.DESCMAX, itemVO.getDescMax());
		cv.put(Item.DESCRICAO, itemVO.getDescricao());
		cv.put(Item.ESTOQUE, itemVO.getEstoque());//		
		cv.put(Item.IDGRUPO, itemVO.getItemGrupo().getIdGrupo());		
		cv.put(Item.INATIVO, itemVO.isInativo());
		cv.put(Item.PRECOVENDA, itemVO.getPreco());
		cv.put(Item.PRECOCOMPRA, itemVO.getPrecoCompra());
		return cv;
	}
	
	
	
	public void insertOrUpdate(final ItemVO itemVO){
		StringBuffer sb = new StringBuffer();
				sb.append("INSERT OR REPLACE INTO ");
				sb.append(Item.TABELA);
				sb.append("(");
					sb.append(Item.IDEMPRESA);
					sb.append(",");
					sb.append(Item.IDFILIAL);
					sb.append(",");					
					sb.append(Item.IDITEM);
					sb.append(",");
					sb.append(Item.IDGRUPO);
					sb.append(",");
					sb.append(Item.DESCMAX);
					sb.append(",");
					sb.append(Item.DESCRICAO);					
					sb.append(",");
					sb.append(Item.CODBAR);
					sb.append(",");
					sb.append(Item.ESTOQUE);					
					sb.append(",");
					sb.append(Item.INATIVO);
					sb.append(",");
					sb.append(Item.PRECOCOMPRA);
					sb.append(",");
					sb.append(Item.PRECOPROMOCAO);					
					sb.append(",");
					sb.append(Item.PRECOVENDA);
					sb.append(",");
					sb.append(Item.PROMOCAO);
					sb.append(",");
					sb.append(Item.REFERENCIA);
					sb.append(",");
					sb.append(Item.UNIDADE);
				sb.append(")");
				sb.append("VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				
				String[] values = {
						itemVO.getIdEmpresa()+"",
						itemVO.getIdFilial()+"",
						itemVO.getIdItem()+"",
						itemVO.getIdGrupo()+"",
						itemVO.getDescMax()+"",
						itemVO.getDescricao()+"",
						itemVO.getCodbar()+"",
						itemVO.getEstoque()+"",
						itemVO.isInativo()+"",
						itemVO.getPrecoCompra()+"",						
						itemVO.getPrecoPromocao()+"",
						itemVO.getPrecoVenda()+"",
						itemVO.isPromocao()+"",
						itemVO.getReferencia()+"",
						itemVO.getUnidade()+"",
												
				};								

				db.execSQL(sb.toString(), values);
	}
		

	public void close(){
		if(db != null ){
			db.close();
		}
	}
}
