package br.com.vendaslim.domain;

import br.com.vendaslim.domain.cadastro.Filial;

public interface ConverterRepository {
	Domain buscaPorDescricao(String descricao, Filial filial);
}
