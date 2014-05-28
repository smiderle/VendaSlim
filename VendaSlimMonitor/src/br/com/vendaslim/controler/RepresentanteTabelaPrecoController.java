package br.com.vendaslim.controler;

import br.com.vendaslim.domain.cadastro.Filial;
import br.com.vendaslim.domain.pedido.TabelaPreco;
import br.com.vendaslim.domain.repository.IRepresentanteTabelaPreco;
import br.com.vendaslim.util.DAOFactory;

public class RepresentanteTabelaPrecoController {
	
private IRepresentanteTabelaPreco repository;
	
	public RepresentanteTabelaPrecoController() {
		this.repository = DAOFactory.criaRepresentanteTabPrecoRepository();
	}
	
	public boolean existeDependencia(Filial filial, Integer idTabela){
		if(this.repository.representanteVinculadotabela(filial, idTabela))
			return true;
		if(this.repository.grupoRepresentanteVinculadotabela(filial, idTabela))
			return true;
		return false;
	}

}
