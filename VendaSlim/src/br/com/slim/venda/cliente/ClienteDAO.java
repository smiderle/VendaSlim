package br.com.slim.venda.cliente;

import java.util.ArrayList;
import java.util.List;

import br.com.slim.venda.R;
import br.com.slim.venda.cidade.CidadeVO;
import br.com.slim.venda.cidade.CidadeVO.Cidade;
import br.com.slim.venda.cliente.ClienteVO.Cliente;
import br.com.slim.venda.dataBase.SQLiteHelper;
import br.com.slim.venda.item.ItemVO.Item;
import br.com.slim.venda.parcelamento.ParcelamentoVO;
import br.com.slim.venda.parcelamento.ParcelamentoVO.Parcela;
import br.com.slim.venda.pedido.PedidoVO;
import br.com.slim.venda.pedido.PedidoVO.Pedido;
import br.com.slim.venda.sincroniza.SincronizaVO;
import br.com.slim.venda.tabelaPreco.TabelaPrecoVO;
import br.com.slim.venda.tabelaPreco.TabelaPrecoVO.TabelaPreco;
import br.com.slim.venda.usuario.UsuarioVO;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.TextView;

public class ClienteDAO {
	String CATEGORIA = "CLIENTE";
	SQLiteHelper sqlLiteHelper;
	SQLiteDatabase db =  null;
	
	public ClienteDAO(Context context) {
		sqlLiteHelper = new SQLiteHelper(context);
		this.db = sqlLiteHelper.getWritableDatabase();
	}
	
	public Cursor getCursorClienteById(int idCliente){
		String query = "SELECT * FROM "+Cliente.TABELA +" cliente LEFT JOIN "+TabelaPreco.TABELA+" tab "+ 
				" ON (cliente." + Cliente.IDTABPRECO +" = tab."+TabelaPreco.IDTABPRECO +" AND " +
				 " cliente."+Cliente.IDEMPRESA +" = tab."+TabelaPreco.IDEMPRESA +")" +
				" LEFT JOIN "+ Parcela.TABELA +" parcela ON( cliente."+Cliente.IDFORMAPARCELAMENTO +" = parcela."+
				Parcela.IDPARCELAMENTO +" AND cliente." +Cliente.IDEMPRESA +" = parcela."+ Parcela.IDEMPRESA+
				") LEFT JOIN "+Cidade.TABELA +" cidade "+
				" ON cliente."+Cliente.IDCIDADE +" = cidade." +Cidade.IDCIDADE+
				" WHERE cliente."+Cliente.IDCLIENTE +"= ? AND cliente."+ Cliente.IDEMPRESA +" = ? ";
		return db.rawQuery(query, new String[]{idCliente+"", UsuarioVO.idEmpresa+""});
	}
	
	public Cursor getCursorByFiltro(String filtro){		
		return db.query(Cliente.TABELA, Cliente.COLUNAS, 
				Cliente.NOME +" like '%"+filtro+"%'  OR "+Cliente.IDCLIENTE+" = ? OR "+Cliente.CPF +" = ? ", 
				new String[]{filtro,filtro}, null, null, Cliente.NOME, null);
	}
	
	public Cursor getCursorAll(){
		try{
			return db.query(Cliente.TABELA, Cliente.COLUNAS, null, null, null, null, Cliente.NOME, null);			
		} catch (SQLException e) {
			Log.e(CATEGORIA, "Erro ao buscar os Clientes: " + e.toString());
			return null;
		}
	}
	
	public ArrayList<ClienteVO> getAll(){
		Cursor c = getCursorAll();
		return getAllBasic(c);
	}
	
	public ArrayList<ClienteVO> getAllByFiltro(String filtro){
		Cursor c = getCursorByFiltro(filtro);
		return getAllBasic(c);
	}
	
	public ClienteVO getClienteByID(int idCliente){
		Cursor c = getCursorClienteById(idCliente);
		return getCliente(c);
	}
	
	public ClienteVO getCliente(Cursor c){
		ClienteVO clienteVO = null;
		if(c.moveToFirst()){
			int idxBairro = c.getColumnIndex(Cliente.BAIRRO);
			int idxCep = c.getColumnIndex(Cliente.CEP);
			int idxContato = c.getColumnIndex(Cliente.CONTATO);
			int idxCPF = c.getColumnIndex(Cliente.CPF);
			int idxDescMax = c.getColumnIndex(Cliente.DESCMAX);
			int idxDtNascimento = c.getColumnIndex(Cliente.DTNASCIMENTO);
			int idxEmail = c.getColumnIndex(Cliente.EMAIL);
			int idxFoneCelular = c.getColumnIndex(Cliente.FONECELULAR);
			int idxFoneComercial = c.getColumnIndex(Cliente.FONECOMERCIAL);
			int idxFoneResidencial = c.getColumnIndex(Cliente.FONERESIDENCIAL);
			int idxIdCidade= c.getColumnIndex(Cliente.IDCIDADE);
			int idxIdCliente= c.getColumnIndex(Cliente.IDCLIENTE);
			int idxIdEmpresa= c.getColumnIndex(Cliente.IDEMPRESA);
			int idxIdFilial= c.getColumnIndex(Cliente.IDFILIAL);
			int idxIdFormaPgto= c.getColumnIndex(Cliente.IDFORMAPAGAMENTO);
			int idxIdFormaParcela = c.getColumnIndex(Cliente.IDFORMAPARCELAMENTO);
			int idxIdTabPreco = c.getColumnIndex(Cliente.IDTABPRECO);
			int idxLimiteCredito= c.getColumnIndex(Cliente.LIMITECREDITO);
			int idxNome= c.getColumnIndex(Cliente.NOME);
			int idxNumero= c.getColumnIndex(Cliente.NUMERO);
			int idxObservacao = c.getColumnIndex(Cliente.OBSERVACAO);
			int idxRg= c.getColumnIndex(Cliente.RG);
			int idxRua= c.getColumnIndex(Cliente.RUA);			
			int idxTipo = c.getColumnIndex(Cliente.TIPO);

			int idxTabPrecoDescricao = c.getColumnIndex(TabelaPreco.DESCRICAO);
			int idxTabPrecoPercentual = c.getColumnIndex(TabelaPreco.PERCENTUAL);
			int idxTabAcrescimo = c.getColumnIndex(TabelaPreco.ACRESCIMO);

			int idxParcelaDescricao = c.getColumnIndex(Parcela.DESCRICAO);
			int idxParcelaNrParcela = c.getColumnIndex(Parcela.NROPARCELA);
			int idxParcelaCarencia = c.getColumnIndex(Parcela.CARENCIA);
			int idxParcelaDiasEntreParcela = c.getColumnIndex(Parcela.DIASENTREPARCELA);
			int idxParcelaPercentual = c.getColumnIndex(Parcela.PERCENTUAL);
			
			int idxCidadeCodigo = c.getColumnIndex(Cidade.IDCIDADE);
			int idxCidadeMunicipio = c.getColumnIndex(Cidade.MUNICIPIO);
			int idxCidadeUf = c.getColumnIndex(Cidade.UF);

			clienteVO = new ClienteVO();
			clienteVO.setBairro(c.getString(idxBairro));
			clienteVO.setCep(c.getString(idxCep));
			
			if(c.getInt(idxIdCidade) != 0)
				clienteVO.getCidadeVO().setIdCidade(c.getInt(idxIdCidade));
			
			clienteVO.setContato(c.getString(idxContato));
			clienteVO.setCpf(c.getString(idxCPF));
			clienteVO.setDescMax(c.getDouble(idxDescMax));
			//clienteVO.setDtNascimento(c.getD(idxBairro)); DT NASCIMENTO
			clienteVO.setEmail(c.getString(idxEmail));
			
			
			clienteVO.setFoneCelular(c.getString(idxFoneCelular));
			clienteVO.setFoneComercial(c.getString(idxFoneComercial));
			clienteVO.setFoneResidencial(c.getString(idxFoneResidencial));
			clienteVO.setLimiteCredito(c.getDouble(idxLimiteCredito));
			clienteVO.setNome(c.getString(idxNome));
			clienteVO.setNumero(c.getString(idxNumero));
			clienteVO.setObservacao(c.getString(idxObservacao));
			clienteVO.setRg(c.getString(idxRg));
			clienteVO.setRua(c.getString(idxRua));
			clienteVO.setTipo(c.getInt(idxTipo));
			
			if(c.getLong(idxDtNascimento) != 0)
				clienteVO.setDtNascimento(c.getLong(idxDtNascimento));
			
			if(c.getInt(idxIdFormaParcela) != 0)
				clienteVO.getParcelamentoVO().setIdParcelamento(c.getInt(idxIdFormaParcela));
			
			if(c.getInt(idxIdCliente) != 0)
				clienteVO.setIdCliente(c.getInt(idxIdCliente));
			
			if(c.getInt(idxIdEmpresa) != 0)
				clienteVO.setIdEmpresa(c.getInt(idxIdEmpresa));
			
			if(c.getInt(idxIdFilial) != 0)
				clienteVO.setIdFilial(c.getInt(idxIdFilial));
			
			if(c.getInt(idxIdFormaPgto) != 0)
				clienteVO.setIdFormaPagamento(c.getInt(idxIdFormaPgto));
			
			if(c.getInt(idxIdTabPreco) != 0)
				clienteVO.getTabPrecovo().setIdTabPreco(c.getInt(idxIdTabPreco));
			
			
			CidadeVO cidadeVO = new CidadeVO();
			cidadeVO.setIdCidade(c.getInt(idxCidadeCodigo));
			cidadeVO.setMunicipio(c.getString(idxCidadeMunicipio));
			cidadeVO.setUF(c.getString(idxCidadeUf));
			
			TabelaPrecoVO tabelaPrecoVO = new TabelaPrecoVO();
			tabelaPrecoVO.setIdEmpresa(c.getInt(idxIdEmpresa));
			tabelaPrecoVO.setIdFilial(c.getInt(idxIdFilial));
			if(c.getInt(idxIdTabPreco) != 0)
				tabelaPrecoVO.setIdTabPreco(c.getInt(idxIdTabPreco));
			tabelaPrecoVO.setDescricao(c.getString(idxTabPrecoDescricao));
			tabelaPrecoVO.setPercentual(c.getDouble(idxTabPrecoPercentual));
			tabelaPrecoVO.setAcrescimo(c.getInt(idxTabAcrescimo) == 1);

			ParcelamentoVO parcelamento = new ParcelamentoVO();
			parcelamento.setIdEmpresa(c.getInt(idxIdEmpresa));
			
			if(c.getInt(idxIdFormaParcela) != 0)
				parcelamento.setIdParcelamento(c.getInt(idxIdFormaParcela));
			
			parcelamento.setDescricao(c.getString(idxParcelaDescricao));
			parcelamento.setDiasEntreParcela(c.getInt(idxParcelaDiasEntreParcela));
			parcelamento.setNroParcela(c.getInt(idxParcelaNrParcela));
			parcelamento.setPercentual(c.getDouble(idxParcelaPercentual));
			parcelamento.setCarencia(c.getInt(idxParcelaCarencia));

			clienteVO.setCidadeVO(cidadeVO);
			clienteVO.setTabPrecovo(tabelaPrecoVO);
			clienteVO.setParcelamentoVO(parcelamento);
		}
		c.close();
		close();	
		return clienteVO;
	}
	
	public ArrayList<ClienteVO> getAllBasic(Cursor c){
		ArrayList<ClienteVO> lsCliente = new ArrayList<ClienteVO>();		
		if(c.moveToFirst()){		
			int idxFoneCelular = c.getColumnIndex(Cliente.FONECELULAR);
			int idxFoneComercial = c.getColumnIndex(Cliente.FONECOMERCIAL);
			int idxFoneResidencial = c.getColumnIndex(Cliente.FONERESIDENCIAL);			
			int idxIdCliente= c.getColumnIndex(Cliente.IDCLIENTE);			
			int idxNome= c.getColumnIndex(Cliente.NOME);
			int idxNumero= c.getColumnIndex(Cliente.NUMERO);			
			int idxRua= c.getColumnIndex(Cliente.RUA);
			int idxLimiteCredito = c.getColumnIndex(Cliente.LIMITECREDITO);
			do{
				ClienteVO clienteVO = new ClienteVO();
				
				clienteVO.setFoneCelular(c.getString(idxFoneCelular));
				clienteVO.setFoneComercial(c.getString(idxFoneComercial));
				clienteVO.setFoneResidencial(c.getString(idxFoneResidencial));
				
				clienteVO.setIdCliente(c.getInt(idxIdCliente));				
				clienteVO.setNome(c.getString(idxNome));
				clienteVO.setNumero(c.getString(idxNumero));
				
				clienteVO.setRua(c.getString(idxRua));	
				clienteVO.setLimiteCredito(c.getDouble(idxLimiteCredito));
				lsCliente.add(clienteVO);
				
			}while(c.moveToNext());					
		}
		c.close();
		close();
		return lsCliente;
	}
	
	private ContentValues getContentValues(final ClienteVO clienteVO){
		ContentValues cv = new ContentValues();		
		cv.put(Cliente.NOME, clienteVO.getNome());
		cv.put(Cliente.BAIRRO, clienteVO.getBairro());
		cv.put(Cliente.CEP, clienteVO.getCep());
		cv.put(Cliente.CONTATO, clienteVO.getContato());
		cv.put(Cliente.CPF, clienteVO.getCpf());
		cv.put(Cliente.DESCMAX, clienteVO.getDescMax());
		cv.put(Cliente.DTNASCIMENTO,clienteVO.getDtNascimento());
		cv.put(Cliente.EMAIL, clienteVO.getEmail());
		cv.put(Cliente.FONECELULAR, clienteVO.getFoneCelular());
		cv.put(Cliente.FONECOMERCIAL, clienteVO.getFoneComercial());
		cv.put(Cliente.FONERESIDENCIAL, clienteVO.getFoneResidencial());
		cv.put(Cliente.IDCIDADE, clienteVO.getCidadeVO() != null && clienteVO.getCidadeVO().getIdCidade()  != null && clienteVO.getCidadeVO().getIdCidade() !=0 ? clienteVO.getCidadeVO().getIdCidade() : null);
		cv.put(Cliente.IDCLIENTE, clienteVO.getIdCliente());
		cv.put(Cliente.SINCRONIZADO, SincronizaVO.NAO_SINCRONIZADO);
		cv.put(Cliente.IDEMPRESA, UsuarioVO.idEmpresa);
		cv.put(Cliente.IDFILIAL, UsuarioVO.idFilial);
		cv.put(Cliente.IDREPRESENTANTE, UsuarioVO.idUsuairo);
		cv.put(Cliente.IDFORMAPAGAMENTO, clienteVO.getIdFormaPagamento());
		cv.put(Cliente.IDFORMAPARCELAMENTO, clienteVO.getParcelamentoVO().getIdParcelamento());
		cv.put(Cliente.IDTABPRECO, clienteVO.getTabPrecovo().getIdTabPreco());
		cv.put(Cliente.LIMITECREDITO, clienteVO.getLimiteCredito());
		cv.put(Cliente.NOME, clienteVO.getNome());
		cv.put(Cliente.NUMERO, clienteVO.getNumero());
		cv.put(Cliente.RG, clienteVO.getRg());
		cv.put(Cliente.RUA, clienteVO.getRua());
		cv.put(Cliente.TIPO, clienteVO.getTipo());
		cv.put(Cliente.OBSERVACAO, clienteVO.getObservacao());
		cv.put(Cliente.ALTERADO, clienteVO.getAlterado());
		
		return cv;
	}
	
	public Integer insert(final ClienteVO clienteVO){	
		return insert(getContentValues(clienteVO));		
	}
	
	public Integer update(final ClienteVO clienteVO){
		return update(getContentValues(clienteVO), clienteVO);
	}
	
	public Integer update(ContentValues cv, final ClienteVO clienteVO){
		return db.update(Cliente.TABELA, cv, Cliente.IDCLIENTE +" = ? AND " +Cliente.IDFILIAL + " = ?"
				, new String[]{cv.get(Cliente.IDCLIENTE) +"", cv.get(Cliente.IDFILIAL)+""});
	}
	
	public Integer insert(ContentValues cv){
		int inserts = (int) db.insert(Cliente.TABELA, null, cv);
		close();
		return inserts;
	}
	
	public long getNextId(Integer idFilial){
		long nextId = 0;
		Cursor c = db.rawQuery("SELECT MAX("+Cliente.IDCLIENTE+") FROM "+Cliente.TABELA+" WHERE " +Cliente.IDFILIAL +" = "+ idFilial, null);
		if(c.moveToFirst()){
			nextId = c.getLong(0);
		}		
		return ++nextId;
	}
	
		
		public void close(){
			if(sqlLiteHelper != null){
				sqlLiteHelper.close();
			}
		}
}
