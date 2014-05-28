package br.com.slim.venda.integration.repository;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import br.com.slim.venda.cidade.CidadeVO;
import br.com.slim.venda.cidade.CidadeVO.Cidade;
import br.com.slim.venda.cliente.ClienteVO;
import br.com.slim.venda.cliente.ClienteVO.Cliente;
import br.com.slim.venda.dataBase.SQLiteHelper;
import br.com.slim.venda.integration.domain.ClienteIntegration;
import br.com.slim.venda.parcelamento.ParcelamentoVO;
import br.com.slim.venda.parcelamento.ParcelamentoVO.Parcela;
import br.com.slim.venda.sincroniza.SincronizaVO;
import br.com.slim.venda.tabelaPreco.TabelaPrecoVO;
import br.com.slim.venda.tabelaPreco.TabelaPrecoVO.TabelaPreco;
import br.com.slim.venda.usuario.UsuarioVO;

public class ClienteIntegrationDAO {
	String CATEGORIA = "CLIENTE";
	SQLiteHelper sqlLiteHelper;
	SQLiteDatabase db =  null;
	
	
	public ClienteIntegrationDAO(Context context) {
		sqlLiteHelper = new SQLiteHelper(context);
		this.db = sqlLiteHelper.getWritableDatabase();
	}
	private ContentValues getContentValues(final ClienteIntegration Cliente){
		ContentValues cv = new ContentValues();		
		cv.put(br.com.slim.venda.cliente.ClienteVO.Cliente.NOME, Cliente.getNome());
		cv.put(br.com.slim.venda.cliente.ClienteVO.Cliente.BAIRRO, Cliente.getBairro());
		cv.put(br.com.slim.venda.cliente.ClienteVO.Cliente.CEP, Cliente.getCep());
		cv.put(br.com.slim.venda.cliente.ClienteVO.Cliente.CONTATO, Cliente.getContato());
		cv.put(br.com.slim.venda.cliente.ClienteVO.Cliente.CPF, Cliente.getCpfCnpj());
		cv.put(br.com.slim.venda.cliente.ClienteVO.Cliente.DESCMAX, Cliente.getDescMax());
		cv.put(br.com.slim.venda.cliente.ClienteVO.Cliente.DTNASCIMENTO,Cliente.getDtNascimentoLong());
		cv.put(br.com.slim.venda.cliente.ClienteVO.Cliente.EMAIL, Cliente.getEmail());
		cv.put(br.com.slim.venda.cliente.ClienteVO.Cliente.FONECELULAR, Cliente.getCelular());
		cv.put(br.com.slim.venda.cliente.ClienteVO.Cliente.FONECOMERCIAL, Cliente.getFoneComercial());
		cv.put(br.com.slim.venda.cliente.ClienteVO.Cliente.FONERESIDENCIAL, Cliente.getFoneResidencial());
		cv.put(br.com.slim.venda.cliente.ClienteVO.Cliente.IDCIDADE, Cliente.getIdCidade());
		cv.put(br.com.slim.venda.cliente.ClienteVO.Cliente.IDCLIENTE, Cliente.getIdCliente());
		cv.put(br.com.slim.venda.cliente.ClienteVO.Cliente.IDEMPRESA, Cliente.getIdEmpresa());
		cv.put(br.com.slim.venda.cliente.ClienteVO.Cliente.IDFILIAL, Cliente.getIdFilial());
		
		//cv.put(br.com.slim.venda.cliente.ClienteVO.Cliente.IDFORMAPAGAMENTO, Cliente.getIdFormaPagamento());
		cv.put(br.com.slim.venda.cliente.ClienteVO.Cliente.IDFORMAPARCELAMENTO, Cliente.getIdParcelamento());
		cv.put(br.com.slim.venda.cliente.ClienteVO.Cliente.IDTABPRECO, Cliente.getIdTabelaPreco());
		cv.put(br.com.slim.venda.cliente.ClienteVO.Cliente.LIMITECREDITO, Cliente.getLimiteCredito());
		cv.put(br.com.slim.venda.cliente.ClienteVO.Cliente.NOME, Cliente.getNome());
		cv.put(br.com.slim.venda.cliente.ClienteVO.Cliente.NUMERO, Cliente.getNumero());
		cv.put(br.com.slim.venda.cliente.ClienteVO.Cliente.RG, Cliente.getIncricao());
		cv.put(br.com.slim.venda.cliente.ClienteVO.Cliente.RUA, Cliente.getRua());
		cv.put(br.com.slim.venda.cliente.ClienteVO.Cliente.TIPO, Cliente.getTipoPessoa());
		cv.put(br.com.slim.venda.cliente.ClienteVO.Cliente.OBSERVACAO, Cliente.getObservacao());
		cv.put(br.com.slim.venda.cliente.ClienteVO.Cliente.IDREPRESENTANTE, Cliente.getIdRepresentante());
		return cv;
	}
	
	public void insertOrUpdate(ClienteIntegration clienteIntegration){
		StringBuffer sb = new StringBuffer();
				sb.append("INSERT OR REPLACE INTO ");
				sb.append(Cliente.TABELA);
				sb.append("(");
					sb.append(Cliente.NOME);
					sb.append(",");
					sb.append(Cliente.BAIRRO);
					sb.append(",");
					sb.append(Cliente.CEP);
					sb.append(",");
					sb.append(Cliente.CONTATO);
					sb.append(",");
					sb.append(Cliente.CPF);
					sb.append(",");
					sb.append(Cliente.DESCMAX);
					sb.append(",");
					sb.append(Cliente.DTNASCIMENTO);
					sb.append(",");
					sb.append(Cliente.EMAIL);
					sb.append(",");
					sb.append(Cliente.FONECELULAR);
					sb.append(",");
					sb.append(Cliente.FONECOMERCIAL);
					sb.append(",");
					sb.append(Cliente.FONERESIDENCIAL);
					sb.append(",");
					sb.append(Cliente.IDCIDADE);
					sb.append(",");
					sb.append(Cliente.IDCLIENTE);
					sb.append(",");
					sb.append(Cliente.IDEMPRESA);
					sb.append(",");
					sb.append(Cliente.IDFILIAL);
					sb.append(",");
					sb.append(Cliente.IDFORMAPARCELAMENTO);
					sb.append(",");
					sb.append(Cliente.IDTABPRECO);
					sb.append(",");
					sb.append(Cliente.LIMITECREDITO);
					sb.append(",");
					sb.append(Cliente.NOME);
					sb.append(",");
					sb.append(Cliente.NUMERO);
					sb.append(",");
					sb.append(Cliente.RG);
					sb.append(",");
					sb.append(Cliente.RUA);
					sb.append(",");
					sb.append(Cliente.TIPO);
					sb.append(",");
					sb.append(Cliente.OBSERVACAO);
					sb.append(",");
					sb.append(Cliente.IDREPRESENTANTE);
				sb.append(")");
				sb.append("VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				
				String[] values = {
						clienteIntegration.getNome(),
						clienteIntegration.getBairro(),
						clienteIntegration.getCep(),
						clienteIntegration.getContato(),
						clienteIntegration.getCpfCnpj(),
						clienteIntegration.getDescMax()+"",
						clienteIntegration.getDtNascimentoLong()+"",
						clienteIntegration.getEmail(),
						clienteIntegration.getCelular(),
						clienteIntegration.getFoneComercial(),
						clienteIntegration.getFoneResidencial(),
						clienteIntegration.getIdCidade()+"",
						clienteIntegration.getIdCliente()+"",
						clienteIntegration.getIdEmpresa()+"",
						clienteIntegration.getIdFilial()+"",
						clienteIntegration.getIdParcelamento()+"",
						clienteIntegration.getIdTabelaPreco()+"",
						clienteIntegration.getLimiteCredito()+"",
						clienteIntegration.getNome(),
						clienteIntegration.getNumero(),
						clienteIntegration.getIncricao(),
						clienteIntegration.getRua(),
						clienteIntegration.getTipoPessoa()+"",
						clienteIntegration.getObservacao(),
						clienteIntegration.getIdRepresentante()+""
				};								

				db.execSQL(sb.toString(), values);
	}
	
	
	
	
	
	
	
	public Integer insert(ContentValues cv) throws SQLiteConstraintException{
		int inserts = (int) db.insert(br.com.slim.venda.cliente.ClienteVO.Cliente.TABELA, null, cv);
		//close();
		return inserts;
	}
	
	public void insert(ClienteIntegration cliente) throws SQLiteConstraintException{
		insert(getContentValues(cliente));
	}

	public Integer update(final ClienteIntegration clienteIntegration){
		return update(getContentValues(clienteIntegration));
	}
	
	public Integer update(ContentValues cv){
		return db.update(Cliente.TABELA, cv, Cliente.IDCLIENTE +" = ? AND " +Cliente.IDFILIAL + " = ?"
				, new String[]{cv.get(Cliente.IDCLIENTE) +"", cv.get(Cliente.IDFILIAL)+""});
	}
	
	/**
	 * Altera o id do cliente caso seja um diferente do cadastrado no mobile e seta como sincronizado.
	 * @param clienteIntegration
	 */
	public void updateClienteSincronizado(ClienteIntegration clienteIntegration){
		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE ");
		sb.append(Cliente.TABELA);
		sb.append(" SET ");
		sb.append(Cliente.IDCLIENTE);		
		sb.append(" = ? ,");
		sb.append(Cliente.SINCRONIZADO);
		sb.append(" = ? ");
		sb.append(" WHERE ");
		sb.append(Cliente.IDCLIENTE);
		sb.append(" = ? AND ");
		sb.append(Cliente.IDFILIAL);
		sb.append(" = ? AND ");
		sb.append(Cliente.IDEMPRESA);
		sb.append(" = ? ");
		
		String[] args = new String[]{
				clienteIntegration.getIdCliente()+"",
				SincronizaVO.SINCRONIZADO,
				clienteIntegration.getIdClienteMobile()+"",
				clienteIntegration.getIdFilial()+"",
				clienteIntegration.getIdEmpresa()+""};
		
		
		db.execSQL(sb.toString(), args);
	}
	
	public ArrayList<ClienteIntegration> getAllNaoSincronizado(){
		String where = Cliente.SINCRONIZADO +" =  ?  AND "+Cliente.IDFILIAL +" = ? ";
				
		String[] args = new String[]{SincronizaVO.NAO_SINCRONIZADO, UsuarioVO.idFilial+""};
		
		Cursor c = db.query(Cliente.TABELA, Cliente.COLUNAS, where, args, null, null, Cliente.ALTERADO+","+ Cliente.IDCLIENTE);
		return getCliente(c);
	}
	
	
	
	
	public ArrayList<ClienteIntegration> getCliente(Cursor c){
		ClienteIntegration clienteIntegration = null;
		ArrayList<ClienteIntegration> lsClienteIntegration = new ArrayList<ClienteIntegration>();
		
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
			int idxIdFormaParcela = c.getColumnIndex(Cliente.IDFORMAPARCELAMENTO);
			int idxLimiteCredito= c.getColumnIndex(Cliente.LIMITECREDITO);
			int idxNome= c.getColumnIndex(Cliente.NOME);
			int idxNumero= c.getColumnIndex(Cliente.NUMERO);
			int idxObservacao = c.getColumnIndex(Cliente.OBSERVACAO);
			int idxRg= c.getColumnIndex(Cliente.RG);
			int idxRua= c.getColumnIndex(Cliente.RUA);			
			int idxTipo = c.getColumnIndex(Cliente.TIPO);
			int idxTabPreco = c.getColumnIndex(Cliente.IDTABPRECO);
			int idxIdRepresentante = c.getColumnIndex(Cliente.IDREPRESENTANTE);
			int idxAlterado = c.getColumnIndex(Cliente.ALTERADO);
			
			
			
			do {
				clienteIntegration = new ClienteIntegration();
				clienteIntegration.setBairro(c.getString(idxBairro));
				clienteIntegration.setCep(c.getString(idxCep));
				//Só vai adicionar o idCidade se o indice for null, porque se usar getInt retornara 0
				if(!c.isNull(idxIdCidade))					
					clienteIntegration.setIdCidade(c.getInt(idxIdCidade));
				
				if(!c.isNull(idxIdFormaParcela))
					clienteIntegration.setIdParcelamento(c.getInt(idxIdFormaParcela));
				
				if(!c.isNull(idxIdFormaParcela))
					clienteIntegration.setIdTabelaPreco(c.getInt(idxTabPreco));
				
				clienteIntegration.setContato(c.getString(idxContato));
				clienteIntegration.setCpfCnpj(c.getString(idxCPF));
				clienteIntegration.setDescMax(c.getDouble(idxDescMax));
				clienteIntegration.setDtNascimentoLong(c.getLong(idxDtNascimento));
				clienteIntegration.setEmail(c.getString(idxEmail));
				clienteIntegration.setCelular(c.getString(idxFoneCelular));
				clienteIntegration.setFoneComercial(c.getString(idxFoneComercial));
				clienteIntegration.setFoneResidencial(c.getString(idxFoneResidencial));
				
				clienteIntegration.setIdCliente(c.getInt(idxIdCliente));
				clienteIntegration.setIdClienteMobile(c.getInt(idxIdCliente));
				clienteIntegration.setIdEmpresa(c.getInt(idxIdEmpresa));
				clienteIntegration.setIdFilial(c.getInt(idxIdFilial));			
				clienteIntegration.setLimiteCredito(c.getDouble(idxLimiteCredito));
				clienteIntegration.setNome(c.getString(idxNome));
				clienteIntegration.setNumero(c.getString(idxNumero));
				clienteIntegration.setObservacao(c.getString(idxObservacao));
				clienteIntegration.setIncricao(c.getString(idxRg));
				clienteIntegration.setRua(c.getString(idxRua));
				clienteIntegration.setIdRepresentante(c.getInt(idxIdRepresentante));
				clienteIntegration.setAlterado(c.getString(idxAlterado));
				
				clienteIntegration.setTipoPessoa(c.getInt(idxTipo));
				
				lsClienteIntegration.add(clienteIntegration);
				
			} while(c.moveToNext());
		}
		c.close();
		close();	
		return lsClienteIntegration;
	}
	
	public void close(){
		if(sqlLiteHelper != null){
			sqlLiteHelper.close();
			db.close();
		}
	}
}
