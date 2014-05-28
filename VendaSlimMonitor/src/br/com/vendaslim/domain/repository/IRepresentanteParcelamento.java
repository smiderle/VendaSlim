package br.com.vendaslim.domain.repository;

import br.com.vendaslim.domain.Repository;
import br.com.vendaslim.domain.cadastro.Filial;

public interface IRepresentanteParcelamento extends Repository{
	/**
	 * Verifica se existe algum representante esta utilizando o parcelamento
	 * @param 
	 * @return
	 */
	public boolean representanteVinculadoParcela(Filial filial,Integer idParcela);
	
	public boolean grupoRepresentanteVinculadoParcela(Filial filial,Integer idParcela);

}
