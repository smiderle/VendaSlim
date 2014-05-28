package br.com.slim.venda.integration.repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.com.slim.venda.cliente.ClienteVO.Cliente;
import br.com.slim.venda.dataBase.SQLiteHelper;
import br.com.slim.venda.integration.domain.ClienteIntegration;
import br.com.slim.venda.integration.domain.PedidoIntegration;
import br.com.slim.venda.integration.domain.PedidoItemIntegration;
import br.com.slim.venda.pedido.PedidoVO.Pedido;
import br.com.slim.venda.pedidoItem.PedidoItemVO;
import br.com.slim.venda.pedidoItem.PedidoItemVO.PedidoItem;
import br.com.slim.venda.pedidopgto.PedidoPgtoVO.PedidoPgto;
import br.com.slim.venda.sincroniza.SincronizaVO;
import br.com.slim.venda.usuario.UsuarioVO;

public class PedidoIntegrationDAO {
	

	String CATEGORIA = "PEDIDO_INTEGRATION";
	SQLiteHelper sqlLiteHelper;
	SQLiteDatabase db =  null;

	
	public PedidoIntegrationDAO(Context context) {
		sqlLiteHelper = new SQLiteHelper(context);
		this.db = sqlLiteHelper.getWritableDatabase();
	}
	
	public ArrayList<PedidoIntegration> getAllNaoSincronizado(){
		Cursor c = db.query(Pedido.TABELA, Pedido.COLUNAS, Pedido.IDFILIAL +" = ? AND " +Pedido.SINC +" = ? ", 
				new String[]{UsuarioVO.idFilial+"", SincronizaVO.NAO_SINCRONIZADO}, null, null, Pedido.IDPEDIDO);
		return getAllPedido(c);
	}
	

	public ArrayList<PedidoIntegration> getAllPedido(Cursor c){
		ArrayList<PedidoIntegration> lsPedidoVO = new ArrayList<PedidoIntegration>();
		if(c.moveToFirst()){
			int idxDescTotal= c.getColumnIndex(Pedido.DESCTOTAL);
			int idxDtEmissao= c.getColumnIndex(Pedido.DTEMISAO);
			int idxIdCliente= c.getColumnIndex(Pedido.IDCLIENTE);
			int idxIdEmpresa= c.getColumnIndex(Pedido.IDEMPRESA);
			int idxIdFilial= c.getColumnIndex(Pedido.IDFILIAL);			
			int idxIdParcelamento = c.getColumnIndex(Pedido.IDPARCELAMENTO);
			int idxIdPedido = c.getColumnIndex(Pedido.IDPEDIDO);			
			int idxIdTabePreco= c.getColumnIndex(Pedido.IDTABPRECO);
			int idxIdUsuario= c.getColumnIndex(Pedido.IDUSUARIO);
			int idxObservacao= c.getColumnIndex(Pedido.OBSERVACAO);
			int idxTotalLiquido= c.getColumnIndex(Pedido.TOTALIQUIDO);
			int idxTotalBruto= c.getColumnIndex(Pedido.TOTALBRUTO);
			
			
			do{
				PedidoIntegration pedidoVO = new PedidoIntegration();
				Calendar dtHrEmissa= new GregorianCalendar();
				dtHrEmissa.setTimeInMillis(c.getLong(idxDtEmissao));
				
				pedidoVO.setIdCliente(c.getInt(idxIdCliente));
				pedidoVO.setDescontoTotal(c.getDouble(idxDescTotal));				
				pedidoVO.setDtHrEmissao(dtHrEmissa);
				pedidoVO.setDtHrAlteracao(dtHrEmissa);
				pedidoVO.setIdEmpresa(c.getInt(idxIdEmpresa));
				pedidoVO.setIdFilial(c.getInt(idxIdFilial));
				pedidoVO.setIdParcelamento(c.getInt(idxIdParcelamento));
				pedidoVO.setIdPedido(c.getInt(idxIdPedido));
				pedidoVO.setIdRepresentante(c.getInt(idxIdUsuario));
				pedidoVO.setIdTabelaPreco(c.getInt(idxIdTabePreco));
				pedidoVO.setValorLiquido(c.getDouble(idxTotalLiquido));
				pedidoVO.setValorBruto(c.getDouble(idxTotalBruto));
				pedidoVO.setObservacao(c.getString(idxObservacao));
			
				ArrayList<PedidoItemIntegration> lsPedidoItem = getAllPedidoItemByIdPedido(pedidoVO.getIdPedido());
				
				pedidoVO.setLsPedidoItemIntegration(lsPedidoItem);
				
				lsPedidoVO.add(pedidoVO);
			} while(c.moveToNext());
			
					
		}
		
		c.close();	
		return lsPedidoVO;
	}
	
	public void updatePedidoSincronizado(PedidoIntegration pedidoIntegration){
		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE ");
		sb.append(Pedido.TABELA);
		sb.append(" SET ");
		sb.append(Pedido.IDPEDIDO);		
		sb.append(" = ? ,");		
		sb.append(Pedido.SINC);
		sb.append(" = ? ");
		sb.append(" WHERE ");
		sb.append(Pedido.IDEMPRESA);
		sb.append(" = ? AND ");
		sb.append(Pedido.IDFILIAL);
		sb.append(" = ? AND ");
		sb.append(Pedido.IDPEDIDO);
		sb.append(" = ? ");
		
		String[] args = new String[]{
				pedidoIntegration.getIdPedido()+"",
				SincronizaVO.SINCRONIZADO+"",
				pedidoIntegration.getIdEmpresa()+"",
				pedidoIntegration.getIdFilial()+"",
				pedidoIntegration.getIdPedidoMobile()+""};
		
		
		db.execSQL(sb.toString(), args);
	}
	
	public void updateClienteIDPedido(Integer idClienteOld, Integer idClienteNew){
		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE ");
		sb.append(Pedido.TABELA);
		sb.append(" SET ");
		sb.append(Pedido.IDCLIENTE);		
		sb.append(" = ? ");
		sb.append(" WHERE ");
		sb.append(Pedido.IDCLIENTE);
		sb.append(" = ? ");
		
		String[] args = new String[]{
				idClienteNew+"",
				idClienteOld+""};
		
		
		db.execSQL(sb.toString(), args);
	}
	
	/*Atualiza para o novo idPedido caso necessario*/
	public void updatePedidoItemNewIDPedido(PedidoIntegration pedidoIntegration){
		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE ");
		sb.append(PedidoItem.TABELA);
		sb.append(" SET ");
		sb.append(PedidoItem.IDPEDIDO);
		sb.append(" = ? ");
		sb.append(" WHERE ");
		sb.append(PedidoItem.IDEMPRESA);
		sb.append(" = ? AND ");
		sb.append(PedidoItem.IDFILIAL);
		sb.append(" = ? AND ");
		sb.append(PedidoItem.IDPEDIDO);
		sb.append(" = ? ");
		
		String[] args = new String[]{
				pedidoIntegration.getIdPedido()+"",
				pedidoIntegration.getIdEmpresa()+"",
				pedidoIntegration.getIdFilial()+"",
				pedidoIntegration.getIdPedidoMobile()+""};
		
		
		db.execSQL(sb.toString(), args);
	}
	
	/*Atualiza para o novo idPedido caso necessario*/
	public void updatePedidoPgtoNewIDPedido(PedidoIntegration pedidoIntegration){
		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE ");
		sb.append(PedidoPgto.TABELA);
		sb.append(" SET ");
		sb.append(PedidoPgto.IDPEDIDO);
		sb.append(" = ? ");
		sb.append(" WHERE ");
		sb.append(PedidoPgto.IDEMPRESA);
		sb.append(" = ? AND ");
		sb.append(PedidoPgto.IDFILIAL);
		sb.append(" = ? AND ");
		sb.append(PedidoPgto.IDPEDIDO);
		sb.append(" = ? ");
		
		String[] args = new String[]{
				pedidoIntegration.getIdPedido()+"",
				pedidoIntegration.getIdEmpresa()+"",
				pedidoIntegration.getIdFilial()+"",
				pedidoIntegration.getIdPedidoMobile()+""};
		
		
		db.execSQL(sb.toString(), args);
	}
	
	
	
	
	
	public ArrayList<PedidoItemIntegration> getAllPedidoItemByIdPedido(int idPedido){
		Cursor c = db.query(PedidoItem.TABELA, PedidoItem.COLUNAS,
				PedidoItem.IDPEDIDO +" = ? AND "+PedidoItem.IDFILIAL +" = ?", new String[]{idPedido+"", UsuarioVO.idFilial+""}, 
				null,null,null,null);
		return getAllPedidoItem(c);
	}
	
	
	public ArrayList<PedidoItemIntegration> getAllPedidoItem(Cursor c){
		ArrayList<PedidoItemIntegration> lsPedidoItem = new ArrayList<PedidoItemIntegration>();
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
				PedidoItemIntegration pedidoItemVO = new PedidoItemIntegration();
				pedidoItemVO.setIdPedido(c.getInt(idxIdPedido));
				pedidoItemVO.setIdEmpresa(c.getInt(idxIdEmpresa));
				pedidoItemVO.setIdFilial(c.getInt(idxIdFilial));
				pedidoItemVO.setDesconto(c.getDouble(idxDesconto));
				pedidoItemVO.setPrecoVenda(c.getDouble(idxPrecoVenda));
				pedidoItemVO.setQuantidade(c.getDouble(idxQuantidade));
				pedidoItemVO.setIdProduto(c.getInt(idxIdItem));
				pedidoItemVO.setSequencia(c.getInt(idxSequencia));
				lsPedidoItem.add(pedidoItemVO);
			}while(c.moveToNext());
		}
		c.close();
		
		return lsPedidoItem;
		
	}


	
	public void close(){
		if(sqlLiteHelper != null){
			sqlLiteHelper.close();
		}
		if(db.isOpen())
			db.close();
	}

}
