package br.com.slim.venda.pedido;

import java.util.ArrayList;
import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.com.slim.venda.cliente.ClienteVO;
import br.com.slim.venda.cliente.ClienteVO.Cliente;
import br.com.slim.venda.dataBase.SQLiteHelper;
import br.com.slim.venda.parcelamento.ParcelamentoVO;
import br.com.slim.venda.parcelamento.ParcelamentoVO.Parcela;
import br.com.slim.venda.pedido.PedidoVO.Pedido;
import br.com.slim.venda.pedidopgto.PedidoPgtoVO;
import br.com.slim.venda.pedidopgto.PedidoPgtoVO.PedidoPgto;
import br.com.slim.venda.sincroniza.SincronizaVO;
import br.com.slim.venda.tabelaPreco.TabelaPrecoVO;
import br.com.slim.venda.tabelaPreco.TabelaPrecoVO.TabelaPreco;
import br.com.slim.venda.usuario.UsuarioVO;
import br.com.slim.venda.util.Util;

public class PedidoDAO {

	String CATEGORIA = "PEDIDO";
	SQLiteHelper sqlLiteHelper;
	SQLiteDatabase db =  null;

	
	public PedidoDAO(Context context) {
		sqlLiteHelper = new SQLiteHelper(context);
		this.db = sqlLiteHelper.getWritableDatabase();
	}
	
	private StringBuilder sql_getAll = new StringBuilder("SELECT * FROM "+Pedido.TABELA +" " +
			"JOIN "+Cliente.TABELA+
			" ON("+ Pedido.TABELA+"."+Pedido.IDCLIENTE+" = "+Cliente.TABELA+"."+Cliente.IDCLIENTE+
			" AND "+ Pedido.TABELA+"."+ Pedido.IDEMPRESA+" = "+Cliente.TABELA+"."+Cliente.IDEMPRESA+
			" AND "+ Pedido.TABELA+"."+ Pedido.IDFILIAL+" = "+Cliente.TABELA+"."+Cliente.IDFILIAL+")" +
			" JOIN "+TabelaPreco.TABELA+
			" ON("+ Pedido.TABELA+"."+Pedido.IDTABPRECO+"  = "+TabelaPreco.TABELA+"."+TabelaPreco.IDTABPRECO+") " +
			" JOIN " +Parcela.TABELA+
			" ON ("+ Pedido.TABELA+"."+ Pedido.IDPARCELAMENTO+" = "+Parcela.TABELA+"."+ Parcela.IDPARCELAMENTO+
			" AND "+ Pedido.TABELA+"."+ Pedido.IDEMPRESA +" = " +Parcela.TABELA+"."+Parcela.IDEMPRESA+
			" AND "+ Pedido.TABELA+"."+ Pedido.IDFILIAL +" = " +Parcela.TABELA+"."+Parcela.IDFILIAL+") " +
			" WHERE " +Pedido.TABELA +"."+Pedido.IDFILIAL +" = " +UsuarioVO.idFilial);
	
	
	private String sql_pedidos_titulos_pendentes = "SELECT DISTINCT "+Pedido.TABELA+"."+Pedido.IDPEDIDO+","+Pedido.TABELA+"."+Pedido.DTEMISAO+","+
			Parcela.TABELA+"."+Parcela.DESCRICAO+","+Pedido.TABELA+"."+Pedido.TOTALIQUIDO+
			" FROM " +Pedido.TABELA +
			" JOIN  "+Parcela.TABELA+" ON("+Pedido.TABELA+"."+Pedido.IDPARCELAMENTO+" = "+Parcela.TABELA+"."+Parcela.IDPARCELAMENTO+") " +
			" JOIN " +PedidoPgto.TABELA +
			" ON("+Pedido.TABELA+"."+Pedido.IDPEDIDO+" = "+PedidoPgto.TABELA+"." +PedidoPgto.IDPEDIDO +
			" AND "+Pedido.TABELA+"."+ Pedido.IDEMPRESA+" = "+PedidoPgto.TABELA+"." + PedidoPgto.IDEMPRESA+ 
			" AND "+Pedido.TABELA+"."+ Pedido.IDFILIAL+" = "+PedidoPgto.TABELA+"." + PedidoPgto.IDFILIAL +")"+
			" WHERE "+PedidoPgto.TABELA+"." +PedidoPgto.PARCELAPAGA+" <> ? "+
			" AND "+Pedido.TABELA+"."+Pedido.IDCLIENTE +" = ? " ;	
	
	
	
	public Cursor getCursorPedidosTituloPendentes(int idCliente){
		return db.rawQuery(sql_pedidos_titulos_pendentes, new String[]{PedidoPgtoVO.PAGAMENTO_TOTAL +"", idCliente+""});				
	}
	
	public Cursor getCursorPedidosTituloTodos(int idCliente){
		return db.rawQuery(sql_pedidos_titulos_pendentes, new String[]{ "9999",idCliente+""});				
	}
	
	
	public Cursor getCursorAll(){

		return db.rawQuery(sql_getAll.append(" ORDER BY "+Pedido.TABELA+"."+Pedido.IDPEDIDO+" DESC LIMiT 50").toString(), null);				
	}
	
	public Cursor getCursorByPrefixo(Integer idPedidoCliente, String nomeCliente){
		sql_getAll.append(" AND "+Pedido.TABELA+"."+Pedido.IDPEDIDO +" = "+ idPedidoCliente +
					" OR " +Cliente.TABELA+"."+Cliente.IDCLIENTE +" = "+ idPedidoCliente +
					" OR " +Cliente.TABELA+"."+ Cliente.NOME +" like '%" +nomeCliente +"%' LIMIT 50 ");
		return db.rawQuery(sql_getAll.toString(), null);
	}
	
	
	public Cursor getCursorByCliente(Integer idCliente){
		sql_getAll.append(" AND "+Pedido.TABELA+"."+Pedido.IDCLIENTE +" = "+ idCliente + " ORDER BY "+Pedido.IDPEDIDO +" DESC LIMIT 50 ");
		return db.rawQuery(sql_getAll.toString(), null);
	}
	
	
	public Cursor getCursorByDate(long dtInicial, long dtFinal){
		sql_getAll.append(" AND "+ Pedido.TABELA+"."+Pedido.DTEMISAO +" >= ? " +
				" AND "+ Pedido.TABELA+"."+Pedido.DTEMISAO +" <= ? LIMIT 50");
		return db.rawQuery(sql_getAll.toString(), new String[]{dtInicial+"", dtFinal+""});
	}
	
	public ArrayList<PedidoVO> getAllPedidosTituloPendente(int idCliente){
		Cursor c = getCursorPedidosTituloPendentes(idCliente);
		return getAll2(c);
	}
	
	public ArrayList<PedidoVO> getAllPedidosTituloTodos(int idCliente){
		Cursor c = getCursorPedidosTituloTodos(idCliente);
		return getAll2(c);
	}
	
	
	
	public ArrayList<PedidoVO> getAll(){
		Cursor c = getCursorAll();
		return getAll(c, false);
	}
		
	public ArrayList<PedidoVO> getAllByPrefixo(Integer idPedidoCliente, String nomeCliente){
		Cursor c = getCursorByPrefixo(idPedidoCliente, nomeCliente);
		return getAll(c, false);
	}
	
	public ArrayList<PedidoVO> getAllByDate(long dtInicial, long dtFinal){
		Cursor c = getCursorByDate(dtInicial, dtFinal);
		return getAll(c, false);
	}
	
	public ArrayList<PedidoVO> getAllByCliente(Integer idCliente){
		Cursor c = getCursorByCliente(idCliente);
		return getAll(c, false);
	}
	
	
	public ArrayList<PedidoVO> getAllNaoSincronizado(){
		Cursor c = db.rawQuery(sql_getAll.append(" AND "+Pedido.TABELA+"." +Pedido.SINC+" = '"+SincronizaVO.NAO_SINCRONIZADO+"' ORDER BY "+Pedido.TABELA+"."+Pedido.DTEMISAO+" ASC ").toString(), null);
		return getAll(c, true);
	}
	
	public ArrayList<PedidoVO> getAllSincronizado(){
		Cursor c = db.rawQuery(sql_getAll.append(" AND "+Pedido.TABELA+"." +Pedido.SINC+" = '"+SincronizaVO.SINCRONIZADO+"' ORDER BY "+Pedido.TABELA+"."+Pedido.IDPEDIDO+" DESC LIMIT 25").toString(), null);
		return getAll(c, false);
	}
	
		
	public int deleteByPedido(final PedidoVO pedidoVO){
		return db.delete(Pedido.TABELA, Pedido.IDPEDIDO +" = ? AND "+Pedido.IDEMPRESA +" = ? AND "+Pedido.IDFILIAL +" = ?  ", new String[]{pedidoVO.getIdPedido()+"",UsuarioVO.idEmpresa+"",UsuarioVO.idFilial+""});
	}
	
	public long getNextId(){
		long nextId = 0;
		Cursor c = db.rawQuery("SELECT MAX("+Pedido.IDPEDIDO+") FROM "+Pedido.TABELA+" WHERE " +Pedido.IDFILIAL +" = "+ UsuarioVO.idFilial, null);
		if(c.moveToFirst()){
			nextId = c.getLong(0);
		}
		close();
		c.close();
		return ++nextId;
	}
	
	public void updateSetSincronizado(PedidoVO pedidoVO){
		ContentValues cv = new ContentValues();
		cv.put(Pedido.SINC,SincronizaVO.SINCRONIZADO);
		db.update(Pedido.TABELA, cv, Pedido.IDPEDIDO+" = ? AND "+Pedido.IDFILIAL+" = ?"
				,new String[]{pedidoVO.getIdPedido()+"", pedidoVO.getIdFilial()+""});
	}
	
	public int insert(PedidoVO pedidoVO){
		ContentValues cv = new ContentValues();
		cv.put(Pedido.DESCTOTAL, pedidoVO.getDescTotal());
		cv.put(Pedido.DTEMISAO, /*pedidoVO.getDtEmisao()*/new Date().getTime());
		cv.put(Pedido.IDCLIENTE, pedidoVO.getClienteVO().getIdCliente());		
		cv.put(Pedido.IDEMPRESA, UsuarioVO.idEmpresa);
		cv.put(Pedido.IDFILIAL, UsuarioVO.idFilial);
		cv.put(Pedido.IDUSUARIO, UsuarioVO.idUsuairo);
		cv.put(Pedido.IDPEDIDO, pedidoVO.getIdPedido());
		cv.put(Pedido.IDTABPRECO, pedidoVO.getTabelaPrecoVO().getIdTabPreco());
		cv.put(Pedido.OBSERVACAO, pedidoVO.getObservacao());
		cv.put(Pedido.TOTALIQUIDO, pedidoVO.getTotalLiquido());
		cv.put(Pedido.TOTALBRUTO, pedidoVO.getTotalBruto());
		cv.put(Pedido.IDPARCELAMENTO, pedidoVO.getParcelamentoVO().getIdParcelamento());
		cv.put(Pedido.SINC, SincronizaVO.NAO_SINCRONIZADO);
		cv.put(Pedido.IDFORMAPAGTO, pedidoVO.getIdFormaPagamento());
		int inserts = (int) db.insert(Pedido.TABELA, null, cv);
		return inserts;
	}
	
	
	public ArrayList<PedidoVO> getAll2(Cursor c){
		ArrayList<PedidoVO> lsPedidoVO = new ArrayList<PedidoVO>();
		if(c.moveToFirst()){
		
			int idxDtEmissao= c.getColumnIndex(Pedido.DTEMISAO);			
			int idxIdPedido = c.getColumnIndex(Pedido.IDPEDIDO);		
			int idxDescricao = c.getColumnIndex(Parcela.DESCRICAO);
			int idxTotalLiquido = c.getColumnIndex(Pedido.TOTALIQUIDO);

			do{
				PedidoVO pedidoVO = new PedidoVO();
				ParcelamentoVO parcelamentoVO = new ParcelamentoVO();
				
				parcelamentoVO.setDescricao(c.getString(idxDescricao));
				pedidoVO.setDtEmisao(c.getLong(idxDtEmissao));
				pedidoVO.setIdPedido(c.getInt(idxIdPedido));
				pedidoVO.setTotalLiquido(c.getDouble(idxTotalLiquido));
				
				pedidoVO.setParcelamentoVO(parcelamentoVO);				
				
				lsPedidoVO.add(pedidoVO);
			} while(c.moveToNext());
			
					
		}
		close();
		c.close();	
		return lsPedidoVO;
	}
	
	public ArrayList<PedidoVO> getAll(Cursor c, boolean usaSection){
		ArrayList<PedidoVO> lsPedidoVO = new ArrayList<PedidoVO>();
		long dtPedido = 0 ;
		if(c.moveToFirst()){
			int idxDescTotal= c.getColumnIndex(Pedido.DESCTOTAL);
			int idxDtEmissao= c.getColumnIndex(Pedido.DTEMISAO);
			int idxIdCliente= c.getColumnIndex(Pedido.IDCLIENTE);
			int idxIdEmpresa= c.getColumnIndex(Pedido.IDEMPRESA);
			int idxIdFilial= c.getColumnIndex(Pedido.IDFILIAL);
			int idxIdFormaPgto= c.getColumnIndex(Pedido.IDFORMAPAGTO);
			int idxIdParcelamento = c.getColumnIndex(Pedido.IDPARCELAMENTO);
			int idxIdPedido = c.getColumnIndex(Pedido.IDPEDIDO);			
			int idxIdTabePreco= c.getColumnIndex(Pedido.IDTABPRECO);
			int idxIdUsuario= c.getColumnIndex(Pedido.IDUSUARIO);
			int idxSincronizado = c.getColumnIndex(Pedido.SINC);
			int idxObservacao= c.getColumnIndex(Pedido.OBSERVACAO);			
			int idxTotalLiquido= c.getColumnIndex(Pedido.TOTALIQUIDO);
			int idxTotalBruto= c.getColumnIndex(Pedido.TOTALBRUTO);
						
			int idxClienteNome= c.getColumnIndex(Cliente.NOME);
			int idxClienteLimiteCredito = c.getColumnIndex(Cliente.LIMITECREDITO);
			
			int idxParcelaCarencia= c.getColumnIndex(Parcela.CARENCIA);			
			int idxParcelaDiasEntreParcela = c.getColumnIndex(Parcela.DIASENTREPARCELA);
			int idxDescricao = c.getColumnIndex(Parcela.DESCRICAO);
			int idxParcelaIdParcel = c.getColumnIndex(Parcela.IDPARCELAMENTO);
			int idxParcelaNroParcela = c.getColumnIndex(Parcela.NROPARCELA);
			int idxParcelaPercentual = c.getColumnIndex(Parcela.PERCENTUAL);
			
			int idxTabPrecoDescricao = c.getColumnIndex(TabelaPreco.DESCRICAO);
			int idxTabPrecoIdTabPreco = c.getColumnIndex(TabelaPreco.IDTABPRECO);
			int idxTabPrecoPercentual = c.getColumnIndex(TabelaPreco.PERCENTUAL);
			int idxTabAcrescimo = c.getColumnIndex(TabelaPreco.ACRESCIMO);
			
			do{				
				PedidoVO pedidoVO = new PedidoVO();
				//Cliente
				ClienteVO clienteVO = new ClienteVO();
				clienteVO.setNome(c.getString(idxClienteNome));
				clienteVO.setIdCliente(c.getInt(idxIdCliente));
				clienteVO.setLimiteCredito(c.getDouble(idxClienteLimiteCredito));
				
				//Parcelamento
				ParcelamentoVO parcelamentoVO = new ParcelamentoVO();
				parcelamentoVO.setCarencia(c.getInt(idxParcelaCarencia));
				parcelamentoVO.setDescricao(c.getString(idxDescricao));
				parcelamentoVO.setDiasEntreParcela(c.getInt(idxParcelaDiasEntreParcela));
				parcelamentoVO.setIdEmpresa(c.getInt(idxIdEmpresa));
				parcelamentoVO.setIdFilial(c.getInt(idxIdFilial));
				parcelamentoVO.setIdParcelamento(c.getInt(idxParcelaIdParcel));
				parcelamentoVO.setNroParcela(c.getInt(idxParcelaNroParcela));
				parcelamentoVO.setPercentual(c.getDouble(idxParcelaPercentual));
				
				//Tabela de Preço
				TabelaPrecoVO tabelaPrecoVO = new TabelaPrecoVO();
				tabelaPrecoVO.setDescricao(c.getString(idxTabPrecoDescricao));
				tabelaPrecoVO.setIdTabPreco(c.getInt(idxTabPrecoIdTabPreco));
				tabelaPrecoVO.setPercentual(c.getDouble(idxTabPrecoPercentual));
				tabelaPrecoVO.setAcrescimo(c.getInt(idxTabAcrescimo ) == 1);
				tabelaPrecoVO.setIdEmpresa(c.getInt(idxIdEmpresa));
				tabelaPrecoVO.setIdFilial(c.getInt(idxIdFilial));
				
				pedidoVO.setClienteVO(clienteVO);
				pedidoVO.setDescTotal(c.getDouble(idxDescTotal));
				pedidoVO.setDtEmisao(c.getLong(idxDtEmissao));
				pedidoVO.setIdEmpresa(c.getInt(idxIdEmpresa));
				pedidoVO.setIdFilial(c.getInt(idxIdFilial));
				pedidoVO.setIdFormaPagamento(c.getInt(idxIdFormaPgto));
				pedidoVO.setIdPedido(c.getInt(idxIdPedido));
				pedidoVO.setIdUsuario(c.getInt(idxIdUsuario));
				pedidoVO.setSincronizado(c.getString(idxSincronizado));
				pedidoVO.setObservacao(c.getString(idxObservacao));				
				pedidoVO.setParcelamentoVO(parcelamentoVO);
				pedidoVO.setTabelaPrecoVO(tabelaPrecoVO);
				pedidoVO.setTotalLiquido(c.getDouble(idxTotalLiquido));		
				pedidoVO.setTotalBruto(c.getDouble(idxTotalBruto));
				
				
				if(usaSection && Util.dateIsAfterOf(pedidoVO.getDtEmisao(), dtPedido)){
					PedidoVO pedidoSection = new PedidoVO();
					pedidoSection .setSection(true);
					pedidoSection.setDtEmisao(pedidoVO.getDtEmisao());
					dtPedido = pedidoVO.getDtEmisao();
					lsPedidoVO.add(pedidoSection);
				}
				
				lsPedidoVO.add(pedidoVO);
			} while(c.moveToNext());
			
					
		}
		close();
		c.close();	
		return lsPedidoVO;
	}
	
	
	
	public void close(){
		if(sqlLiteHelper != null){
			sqlLiteHelper.close();
		}
		if(db.isOpen())
			db.close();
	}


}
