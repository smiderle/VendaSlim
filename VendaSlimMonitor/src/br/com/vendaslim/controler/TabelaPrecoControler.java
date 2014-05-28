package br.com.vendaslim.controler;

import java.util.List;

import br.com.vendaslim.domain.cadastro.Filial;
import br.com.vendaslim.domain.pedido.TabelaPreco;
import br.com.vendaslim.domain.repository.ITabelaPrecoRepository;
import br.com.vendaslim.util.DAOFactory;

public class TabelaPrecoControler {
	
	private ITabelaPrecoRepository repository;
	
	public TabelaPrecoControler() {
		repository = DAOFactory.criaTabelaPrecoRepository();
	}
	
	public List<TabelaPreco> lista(Filial filial){
		return this.repository.lista(filial);
	}
	
	public Integer buscaProximoIdPorFilial(Filial filial){
		return this.repository.buscaMaiorIdPorFilial(filial) + 1;
	}
		
	public TabelaPreco buscaPorId(TabelaPreco tabelaPreco){
		return this.repository.buscaPorId(tabelaPreco);
	}
	
	public void salvar(TabelaPreco tabelaPreco){
		this.repository.save(tabelaPreco);
	}
	
	public void remover(TabelaPreco tabelaPreco){
		this.repository.delete(tabelaPreco);
	}
	
	
	public void atualizar(TabelaPreco tabelaPreco){
		this.repository.update(tabelaPreco);
	}
	
	public boolean existeDescricao(TabelaPreco tabelaPreco){
		return this.repository.existeDescricaoTabela(tabelaPreco);
	}
	
	public boolean exclusaoPermitida(TabelaPreco tabelaPreco, Filial filial){
		boolean existeMovientacao = true;
		PedidoControler pedidoController = new PedidoControler();
		
		existeMovientacao = pedidoController.pedidoUsouTabelaPreco(tabelaPreco);
		if(existeMovientacao)
			return false;
		
		RepresentanteTabelaPrecoController repTabPrecoController = new RepresentanteTabelaPrecoController();
		existeMovientacao = repTabPrecoController.existeDependencia(filial, tabelaPreco.getIdTabelaPreco());
		if(existeMovientacao)
			return false;
		
		
		ClienteControler clienteController = new ClienteControler();
		existeMovientacao =  clienteController.clienteUsouTabPreco(filial, tabelaPreco);
		if(existeMovientacao)
			return false;
		
		return true;
	}
}
