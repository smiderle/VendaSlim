package br.com.vendaslim.domain.repository;

import java.util.List;

import br.com.vendaslim.domain.ConverterRepository;
import br.com.vendaslim.domain.Repository;
import br.com.vendaslim.domain.cadastro.Filial;
import br.com.vendaslim.domain.pedido.Parcelamento;
import br.com.vendaslim.domain.pedido.Pedido;
import br.com.vendaslim.domain.pedido.TabelaPreco;

public interface ITabelaPrecoRepository extends Repository, ConverterRepository {
	List<TabelaPreco> lista(Filial filial);
	public TabelaPreco buscaPorId(TabelaPreco tabelaPreco);
	public Integer buscaMaiorIdPorFilial(Filial filial);	
	public boolean existeDescricaoTabela(TabelaPreco tabelaPreco);
	
}
