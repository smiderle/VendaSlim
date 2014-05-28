package br.com.slim.venda.pedidoItem;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.com.slim.venda.cliente.ClienteVO.Cliente;
import br.com.slim.venda.dataBase.SQLiteHelper;
import br.com.slim.venda.pedido.PedidoVO;
import br.com.slim.venda.pedido.PedidoVO.Pedido;
import br.com.slim.venda.pedidoItem.PedidoItemVO.PedidoItem;
import br.com.slim.venda.usuario.UsuarioVO;

public class PedidoItemDAO {

	String CATEGORIA = "PEDIDOITEM"	;
	SQLiteHelper sqlLiteHelper;
	SQLiteDatabase db =  null;
	
	public PedidoItemDAO(Context context) {
		sqlLiteHelper = new SQLiteHelper(context);
		this.db = sqlLiteHelper.getWritableDatabase();
	}
	
	public Cursor getCursorByIDPedido(int idPedido, int idFilial){
		return db.query(PedidoItem.TABELA, PedidoItem.COLUNAS,
				PedidoItem.IDPEDIDO +" = ? AND "+PedidoItem.IDFILIAL +" = ?", new String[]{idPedido+"", idFilial+""}, 
				null,null,null,null);
	}
	
	public ArrayList<PedidoItemVO> getAllByIdPedido(int idPedido, int idFilial){
		Cursor c = getCursorByIDPedido(idPedido, idFilial);
		return getAll(c);
	}
	
	
	public ArrayList<PedidoItemVO> getAll(Cursor c){
		ArrayList<PedidoItemVO> lsPedidoItem = new ArrayList<PedidoItemVO>();
		if(c.moveToFirst()){
			int idxIdPedido = c.getColumnIndex(PedidoItem.IDPEDIDO);
			int idxIdItem = c.getColumnIndex(PedidoItem.IDITEM);
			int idxIdEmpresa = c.getColumnIndex(PedidoItem.IDEMPRESA);
			int idxIdFilial = c.getColumnIndex(PedidoItem.IDFILIAL);
			int idxDesconto = c.getColumnIndex(PedidoItem.DESCONTO);
			int idxPrecoVenda = c.getColumnIndex(PedidoItem.PRECOVENDA);
			int idxQuantidade = c.getColumnIndex(PedidoItem.QUANTIDADE);
			int idxSequencia = c.getColumnIndex(PedidoItem.IDSEQUENCIA);
			do{
				PedidoItemVO pedidoItemVO = new PedidoItemVO();
				pedidoItemVO.setIdPedido(c.getInt(idxIdPedido));
				pedidoItemVO.setIdEmpresa(c.getInt(idxIdEmpresa));
				pedidoItemVO.setIdFilial(c.getInt(idxIdFilial));
				pedidoItemVO.getItemVO().setIdItem(c.getInt(idxIdItem));
				pedidoItemVO.getItemVO().setIdEmpresa(c.getInt(idxIdEmpresa));
				pedidoItemVO.getItemVO().setIdFilial(c.getInt(idxIdFilial));
				pedidoItemVO.setDesconto(c.getDouble(idxDesconto));
				pedidoItemVO.setPrecoVenda(c.getDouble(idxPrecoVenda));
				pedidoItemVO.setQuantidade(c.getDouble(idxQuantidade));
				pedidoItemVO.setIdSequencia(c.getInt(idxSequencia));
				lsPedidoItem.add(pedidoItemVO);
			}while(c.moveToNext());
		}
		c.close();
		close();
		return lsPedidoItem;
		
	}
	
	public int deleteByPedido(PedidoVO pedidoVO){
		return db.delete(PedidoItem.TABELA, PedidoItem.IDFILIAL +" =? AND "+PedidoItem.IDPEDIDO +" = ?", 
					new String[]{UsuarioVO.idFilial+"", pedidoVO.getIdPedido()+""});
	}
	
	public int insert(PedidoItemVO pedidoItemVO){
		ContentValues cv = new ContentValues();
		cv.put(PedidoItem.DESCONTO, pedidoItemVO.getDesconto());
		cv.put(PedidoItem.IDEMPRESA, UsuarioVO.idEmpresa);
		cv.put(PedidoItem.IDFILIAL, UsuarioVO.idFilial);
		cv.put(PedidoItem.IDSEQUENCIA, pedidoItemVO.getIdSequencia());
		cv.put(PedidoItem.IDITEM, pedidoItemVO.getItemVO().getIdItem());
		cv.put(PedidoItem.IDPEDIDO, pedidoItemVO.getIdPedido());
		cv.put(PedidoItem.PRECOVENDA, pedidoItemVO.getPrecoVenda());
		cv.put(PedidoItem.QUANTIDADE, pedidoItemVO.getQuantidade());
		int inserts = (int)db.insert(PedidoItem.TABELA, null, cv);
		return inserts;
	}
	
	public void close(){
		if(sqlLiteHelper != null){
			sqlLiteHelper.close();
		}
		if(db.isOpen())
			db.close();
	}
		
}
