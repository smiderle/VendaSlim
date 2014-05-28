package br.com.vendaslim.controler;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.logging.impl.SLF4JLocationAwareLog;

import br.com.vendaslim.domain.cadastro.Cidade;
import br.com.vendaslim.domain.cadastro.Empresa;
import br.com.vendaslim.domain.cadastro.Filial;
import br.com.vendaslim.domain.cliente.Cliente;
import br.com.vendaslim.domain.pedido.Parcelamento;
import br.com.vendaslim.domain.pedido.Pedido;
import br.com.vendaslim.domain.pedido.TabelaPreco;
import br.com.vendaslim.domain.repository.IClienteRepository;
import br.com.vendaslim.util.DAOFactory;
import br.com.vendaslim.web.util.ContextoUtil;

public class ClienteControler {

	private IClienteRepository clienteRepository;
	
	public ClienteControler() {
		clienteRepository = DAOFactory.criaClienteRepository();
	}
	
	/**
	 * Metodo criado somente para testes
	 */
	public void geraClientes(){
		List<Cliente> clientes = new ArrayList<Cliente>();
		for (int i = 11; i < 1000; i++) {
			Cliente cliente = new Cliente();
			cliente.setIdCliente(i);
			
			
			Empresa empresa = new Empresa();
			empresa.setIdEmpresa(1);
			
			Filial filial = new Filial();
			filial.setIdFilial(1);
			filial.setEmpresa(empresa);
			
			cliente.setFilial(filial);
			cliente.setNome("CLIENTE EMPRESA UM "+ i);
			cliente.setIdRepresentante(5);
			
			Cidade cidade = new Cidade();
			cidade.setIdCidade(50);
			cliente.setCidade(cidade);
			
			cliente.setCelular("213213213");
			cliente.setIdParcelamento(1);
			cliente.setIdTabelaPreco(1);
			clientes.add(cliente);			
		}
		clienteRepository.save(clientes);
		
	}
	
	
	public void salvar(Cliente cliente){
		cliente.setDtHrAlteracao(new GregorianCalendar());
		this.clienteRepository.save(cliente);
	}
	
	public void atualizar(Cliente cliente){
		cliente.setDtHrAlteracao(new GregorianCalendar());
		this.clienteRepository.update(cliente);		
	}
	
	public void excluir(Cliente cliente){
		this.clienteRepository.delete(cliente);		
	}
	
	public boolean exclusaoPermitida(Cliente cliente){
		PedidoControler pedidoController = new PedidoControler();
		List<Pedido> lsPedido = pedidoController.buscaPedidosPorCliente(cliente);
		return lsPedido == null || lsPedido.size() == 0;
	}
	
	//Valida se o Login ou id já existem para a empresa.
	public boolean idDisponivel(Cliente cliente){		
		return this.clienteRepository.buscaPorId(cliente) == null;
	}
	
	
	public Cliente buscaPorNome(String nome, Filial filial){
		if(filial == null)
			filial = ContextoUtil.getContextBean().getFilial();
		return this.clienteRepository.buscaPorNome(nome, filial);
	}
	
	public Integer buscaProximoIdPorFilial(Filial filial){
		return this.clienteRepository.buscaMariorIdPorFilial(filial) + 1;
	}
	
	public Cliente buscaPorId(Cliente cliente){
		return this.clienteRepository.buscaPorId(cliente);
	}	
	
	public List<Cliente> listar(){
		Filial filial = ContextoUtil.getContextBean().getFilial();
		return this.clienteRepository.lista(filial);
	}
	
	public List<Cliente> buscaPorFiltro(String filtro){
		Filial filial = ContextoUtil.getContextBean().getFilial();
		Integer codigo = 0;
		try {
			codigo = Integer.parseInt(filtro);
		} catch (NumberFormatException e) {}
		
		return this.clienteRepository.listaPorFiltro(filial, filtro, codigo);
	}
	
	public boolean clienteUsouTabPreco(Filial filial, TabelaPreco tabelaPreco){
		return this.clienteRepository.clienteUsouTabelaPreco(filial, tabelaPreco);
	}
	
	public boolean clienteUsouParcelamento(Filial filial, Parcelamento parcelamento){
		return this.clienteRepository.clienteUsouParcelamento(filial, parcelamento);
	}
	
	public void delete(Cliente cliente){
		this.clienteRepository.delete(cliente);
	}
}
