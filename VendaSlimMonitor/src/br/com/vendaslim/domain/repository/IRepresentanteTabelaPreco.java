package br.com.vendaslim.domain.repository;

import br.com.vendaslim.domain.Repository;
import br.com.vendaslim.domain.cadastro.Filial;

public interface IRepresentanteTabelaPreco extends Repository{
	/**
	 * Verifica se existe algum representante esta utilizando a tabela de preço
	 * @param tabelaPreco
	 * @return
	 */
	public boolean representanteVinculadotabela(Filial filial,Integer idTabela);
	
	public boolean grupoRepresentanteVinculadotabela(Filial filial,Integer idTabela);

}
