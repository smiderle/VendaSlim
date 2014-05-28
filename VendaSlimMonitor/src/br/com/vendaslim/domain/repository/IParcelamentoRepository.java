package br.com.vendaslim.domain.repository;

import java.util.List;

import br.com.vendaslim.domain.ConverterRepository;
import br.com.vendaslim.domain.Repository;
import br.com.vendaslim.domain.cadastro.Filial;
import br.com.vendaslim.domain.pedido.Parcelamento;
import br.com.vendaslim.domain.pedido.TabelaPreco;

public interface IParcelamentoRepository extends Repository, ConverterRepository{
	List<Parcelamento> lista(Filial filial);
	public Parcelamento buscaPorId(Parcelamento parcelamento);
	public Integer buscaMaiorIdPorFilial(Filial filial);
	public boolean existeDescricaoParcelamento(Parcelamento parcelamento);
}
