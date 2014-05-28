package br.com.vendaslim.controler;

import br.com.vendaslim.domain.cadastro.Filial;
import br.com.vendaslim.domain.repository.IRepresentanteParcelamento;
import br.com.vendaslim.util.DAOFactory;

public class RepresentanteParcelamentoController {
	
private IRepresentanteParcelamento repository;
	
	public RepresentanteParcelamentoController() {
		this.repository = DAOFactory.criaRepresentanteParcelamentoRepository();
	}
	
	public boolean existeDependencia(Filial filial, Integer idParcela){
		if(this.repository.representanteVinculadoParcela(filial, idParcela))
			return true;
		if(this.repository.grupoRepresentanteVinculadoParcela(filial, idParcela))
			return true;
		return false;
	}

}
