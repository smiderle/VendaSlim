package br.com.vendaslim.controler;

import java.util.List;

import br.com.vendaslim.domain.cadastro.Filial;
import br.com.vendaslim.domain.pedido.Parcelamento;
import br.com.vendaslim.domain.repository.IParcelamentoRepository;
import br.com.vendaslim.util.DAOFactory;

public class ParcelamentoControler {
	
	private IParcelamentoRepository repository;
	public ParcelamentoControler() {
		this.repository = DAOFactory.criaParcelamentoRepository();
	}
	
	public List<Parcelamento> lista(Filial filial){
		return repository.lista(filial);
	}
	

	public Integer buscaProximoIdPorFilial(Filial filial){
		return this.repository.buscaMaiorIdPorFilial(filial) + 1;
	}
		
	public Parcelamento buscaPorId(Parcelamento parcelamento){
		return this.repository.buscaPorId(parcelamento);
	}
	
	public void salvar(Parcelamento parcelamento){
		this.repository.save(parcelamento);
	}
	
	public void atualizar(Parcelamento parcelamento){
		this.repository.update(parcelamento);
	}
	
	public void remover(Parcelamento parcelamento){
		this.repository.delete(parcelamento);
	}
	
	public boolean existeDescricaoParcelamento (Parcelamento parcelamento){
		return this.repository.existeDescricaoParcelamento(parcelamento);
	}
	
	public boolean exclusaoPermitida(Parcelamento parcelamento, Filial filial){
		PedidoControler pedidoController = new PedidoControler();
		boolean existeDependencia = pedidoController.pedidoUsouParcelamento(parcelamento);
		if(existeDependencia)		
			return false;
		
		RepresentanteParcelamentoController repParcelaController =new RepresentanteParcelamentoController();
		if(repParcelaController.existeDependencia(filial, parcelamento.getIdParcelamento()))
			return false;
		
		ClienteControler clienteController = new ClienteControler();
		existeDependencia = clienteController.clienteUsouParcelamento(filial, parcelamento);
		if(existeDependencia)
			return false;
			
		
		
		return true;
	}
}
