package br.com.slim.venda.pedidopgto;

import java.util.ArrayList;
import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.com.slim.venda.cliente.ClienteVO;
import br.com.slim.venda.cliente.ClienteVO.Cliente;
import br.com.slim.venda.dataBase.SQLiteHelper;
import br.com.slim.venda.pedido.PedidoVO;
import br.com.slim.venda.pedido.PedidoVO.Pedido;
import br.com.slim.venda.pedidoItem.PedidoItemVO.PedidoItem;
import br.com.slim.venda.pedidopgto.PedidoPgtoVO.PedidoPgto;
import br.com.slim.venda.usuario.UsuarioVO;

public class PedidoPgtoDAO {
	String CATEGORIA = "PEDIDOPGTO"	;
	SQLiteHelper sqlLiteHelper;
	SQLiteDatabase db =  null;
	
	public PedidoPgtoDAO(Context context) {
		sqlLiteHelper = new SQLiteHelper(context);
		this.db = sqlLiteHelper.getWritableDatabase();
	}
	
	public ArrayList<PedidoPgtoVO> getAllByPedido(int idPedido, int idFilial){
		Cursor c = getCursorAllByPedido(idPedido, idFilial);
		return getAll(c);
	}
	
	public int deleteByPedido(final PedidoVO pedidoVO){
		return db.delete(PedidoPgto.TABELA, PedidoPgto.IDPEDIDO +" = ? AND "+PedidoPgto.IDFILIAL +" = ? ", new String[]{pedidoVO.getIdPedido()+"",UsuarioVO.idFilial+""});
	}
	
	public void updateSetPaga(PedidoPgtoVO pedidoPgtoVO){
		ContentValues cv = new ContentValues();
		cv.put(PedidoPgto.DTPAGAMENTO, pedidoPgtoVO.getDtPagamento());
		cv.put(PedidoPgto.VALORPAGO, pedidoPgtoVO.getValorPago());
		cv.put(PedidoPgto.PARCELAPAGA, PedidoPgtoVO.PAGAMENTO_TOTAL);
		db.update(PedidoPgto.TABELA, cv, 
				PedidoPgto.IDSEQUENCIA +" = ? AND "+PedidoPgto.IDPEDIDO +" = ? AND "+
						PedidoPgto.IDFILIAL +" = ?" , new String[]{pedidoPgtoVO.getIdSequencia()+"", 
						pedidoPgtoVO.getIdPedido()+"", pedidoPgtoVO.getIdFilial()+""});
	}
	
	public Cursor getCursorAllByPedido(int idPedido, int idFilial){		
		String sql = "SELECT "+PedidoPgto.DTVENCIMENTO+","+
		PedidoPgto.IDCLIENTE+","+
		PedidoPgto.IDEMPRESA+","+
		PedidoPgto.IDFILIAL+","+
		PedidoPgto.IDPEDIDO+","+
		PedidoPgto.IDSEQUENCIA+","+
		PedidoPgto.PARCELAPAGA+","+
		PedidoPgto.VALORPAGO+","+
		PedidoPgto.VALORPARCELA+","+
		PedidoPgto.DTPAGAMENTO+
		" FROM "+PedidoPgto.TABELA+
		" WHERE "+PedidoPgto.IDPEDIDO+" = "+ idPedido +" AND " +
		PedidoPgto.IDFILIAL+" = "+ idFilial;
		
		return db.rawQuery(sql, null);
		
	}
	
	
	public ArrayList<PedidoPgtoVO> getAll(Cursor c){
		ArrayList<PedidoPgtoVO> lsPedidoPgtoVO = new ArrayList<PedidoPgtoVO>();
		if(c.moveToFirst()){
			int idxDtVcto = c.getColumnIndex(PedidoPgto.DTVENCIMENTO);
			int idxDtPgto = c.getColumnIndex(PedidoPgto.DTPAGAMENTO);
			int idxIdCliente = c.getColumnIndex(PedidoPgto.IDCLIENTE);
			int idxIdEmpresa = c.getColumnIndex(PedidoPgto.IDEMPRESA);
			int idxIdFilial = c.getColumnIndex(PedidoPgto.IDFILIAL);
			int idxIdParcela = c.getColumnIndex(PedidoPgto.IDPARCELAMENTO);
			int idxIdPedido = c.getColumnIndex(PedidoPgto.IDPEDIDO);
			int idxIdSequencia = c.getColumnIndex(PedidoPgto.IDSEQUENCIA);			
			int idxParcelaPaga = c.getColumnIndex(PedidoPgto.PARCELAPAGA);
			int idxValorPago = c.getColumnIndex(PedidoPgto.VALORPAGO);
			int idxValorParcela = c.getColumnIndex(PedidoPgto.VALORPARCELA);			
			
			
			do{
				PedidoPgtoVO pedidoPgtoVO = new PedidoPgtoVO();
				pedidoPgtoVO.setDtVencimento(c.getLong(idxDtVcto));
				pedidoPgtoVO.setIdEmpresa(c.getInt(idxIdEmpresa));
				pedidoPgtoVO.setIdFilial(c.getInt(idxIdFilial));				
				pedidoPgtoVO.setIdPedido(c.getInt(idxIdPedido));
				pedidoPgtoVO.setIdSequencia(c.getInt(idxIdSequencia));
				pedidoPgtoVO.setParcelaPaga(c.getInt(idxParcelaPaga));
				pedidoPgtoVO.setValorPago(c.getDouble(idxValorPago));
				pedidoPgtoVO.setValorParcela(c.getDouble(idxValorParcela));		
				pedidoPgtoVO.setDtPagamento(c.getLong(idxDtPgto));
				lsPedidoPgtoVO.add(pedidoPgtoVO);
			}while(c.moveToNext());
		}
		
		return lsPedidoPgtoVO;
	}
	
	public int insert(PedidoPgtoVO pedidoPgtoVO){
		ContentValues cv = new ContentValues();
		cv.put(PedidoPgto.DTVENCIMENTO, pedidoPgtoVO.getDtVencimento());
		cv.put(PedidoPgto.IDEMPRESA, UsuarioVO.idEmpresa);
		cv.put(PedidoPgto.IDFILIAL, UsuarioVO.idFilial);
		cv.put(PedidoPgto.IDPARCELAMENTO, pedidoPgtoVO.getParcelamentoVO().getIdParcelamento());
		cv.put(PedidoPgto.IDPEDIDO, pedidoPgtoVO.getIdPedido());
		cv.put(PedidoPgto.IDSEQUENCIA, pedidoPgtoVO.getIdSequencia());
		cv.put(PedidoPgto.PARCELAPAGA, pedidoPgtoVO.getParcelaPaga());
		cv.put(PedidoPgto.VALORPAGO, pedidoPgtoVO.getValorPago());
		cv.put(PedidoPgto.VALORPARCELA, pedidoPgtoVO.getValorParcela());
		cv.put(PedidoPgto.IDCLIENTE, pedidoPgtoVO.getClienteVO().getIdCliente());
		
		int inserts = (int) db.insert(PedidoPgto.TABELA, null, cv);
		return inserts;
	}
	
	
	public ArrayList<ClienteVO> getTotalTitulosAbertos(){
		Cursor c = db.rawQuery(
		"SELECT SUM(PEDIDOPGTO.VALORPARCELA), CLIENTE.IDCLIENTE, CLIENTE.NOME " +
		"FROM PEDIDOPGTO " +
		"JOIN PEDIDO " +
		"ON (PEDIDOPGTO.IDPEDIDO = PEDIDO.IDPEDIDO)" +
		"JOIN CLIENTE " +
		"ON (PEDIDO.IDCLIENTE = CLIENTE.IDCLIENTE) " +
		" WHERE " +PedidoPgto.PARCELAPAGA +" = "+PedidoPgtoVO.PAGAMENTO_PENDENTE+
		" GROUP BY CLIENTE.IDCLIENTE, CLIENTE.NOME", null);
		ClienteVO clienteVO = null;
		ArrayList<ClienteVO> lsCliente = new ArrayList<ClienteVO>();
		if(c.moveToFirst()){
			do{
				clienteVO = new ClienteVO();
				clienteVO.setValorTitulos(c.getDouble(0));
				clienteVO.setIdCliente(c.getInt(1));
				clienteVO.setNome(c.getString(2));
				lsCliente.add(clienteVO);
			}while(c.moveToNext());
		}
		c.close();
		return lsCliente;
	}
	
	public double getTotalTitulosAbertosPorCliente(final ClienteVO clienteVO){	
		
		Cursor c = db.rawQuery(
		"SELECT SUM(PEDIDOPGTO.VALORPARCELA)" +
		"FROM PEDIDOPGTO " +
		"JOIN PEDIDO " +
		"ON (PEDIDOPGTO.IDPEDIDO = PEDIDO.IDPEDIDO)" +
		"JOIN CLIENTE " +
		"ON (PEDIDO.IDCLIENTE = CLIENTE.IDCLIENTE) " +
		" WHERE "/*+PedidoPgto.TABELA+"."+PedidoPgto.PARCELAPAGA +" = "+PedidoPgtoVO.PAGAMENTO_PENDENTE+
		" AND  "*/+Pedido.TABELA+"."+ Pedido.IDCLIENTE +" = "+clienteVO.getIdCliente(), null);
		if(c.moveToFirst()){
			return c.getDouble(0);							
		}
		c.close();
		return 0.0;
	}
	
	public double getTotalTitulosVencidosPorCliente(final ClienteVO clienteVO){
		Cursor c = db.rawQuery(
		"SELECT SUM(PEDIDOPGTO.VALORPARCELA)" +
		"FROM PEDIDOPGTO " +
		"JOIN PEDIDO " +
		"ON (PEDIDOPGTO.IDPEDIDO = PEDIDO.IDPEDIDO)" +
		"JOIN CLIENTE " +
		"ON (PEDIDO.IDCLIENTE = CLIENTE.IDCLIENTE) " +
		" WHERE " +PedidoPgto.PARCELAPAGA +" = "+PedidoPgtoVO.PAGAMENTO_PENDENTE+
		"AND "+ PedidoPgto.DTVENCIMENTO +" < "+ new Date().getTime()+
		" AND  "+Pedido.TABELA+"."+ Pedido.IDCLIENTE +" = "+clienteVO.getIdCliente(), null);
		if(c.moveToFirst()){
			return c.getDouble(0);							
		}
		c.close();
		return 0.0;
	}
	
	public double getTotalTitulosAbertosPorPedido(final PedidoVO pedidoVO){
		Cursor c = db.rawQuery(
		"SELECT SUM(PEDIDOPGTO.VALORPARCELA)" +
		"FROM PEDIDOPGTO " +
		" WHERE " +PedidoPgto.PARCELAPAGA +" = "+PedidoPgtoVO.PAGAMENTO_PENDENTE+
		" AND "+ PedidoPgto.IDFILIAL +" = "+ pedidoVO.getIdFilial()+
		" AND  "+PedidoPgto.IDPEDIDO +" = "+pedidoVO.getIdPedido(), null);
		if(c.moveToFirst()){
			return c.getDouble(0);							
		}
		c.close();
		return 0.0;
	}
	
	public ArrayList<ClienteVO> getTotalTitulosTodos(){
		Cursor c = db.rawQuery(
		"SELECT SUM(PEDIDOPGTO.VALORPARCELA), CLIENTE.IDCLIENTE, CLIENTE.NOME " +
		"FROM PEDIDOPGTO " +
		"JOIN PEDIDO " +
		"ON (PEDIDOPGTO.IDPEDIDO = PEDIDO.IDPEDIDO)" +
		"JOIN CLIENTE " +
		"ON (PEDIDO.IDCLIENTE = CLIENTE.IDCLIENTE) " +
		" GROUP BY CLIENTE.IDCLIENTE, CLIENTE.NOME", null);
		ClienteVO clienteVO = null;
		ArrayList<ClienteVO> lsCliente = new ArrayList<ClienteVO>();
		if(c.moveToFirst()){
			do{
				clienteVO = new ClienteVO();
				clienteVO.setValorTitulos(c.getDouble(0));
				clienteVO.setIdCliente(c.getInt(1));
				clienteVO.setNome(c.getString(2));
				lsCliente.add(clienteVO);
			}while(c.moveToNext());
		}
		c.close();
		return lsCliente;
	}
	
	
	
	public void close(){
		if(sqlLiteHelper != null){
			sqlLiteHelper.close();
		}
		if(db.isOpen())
			db.close();
	}
	
	
}
