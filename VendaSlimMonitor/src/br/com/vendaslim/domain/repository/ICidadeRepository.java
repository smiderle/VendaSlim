package br.com.vendaslim.domain.repository;

import java.util.List;

import br.com.vendaslim.domain.Repository;
import br.com.vendaslim.domain.cadastro.Cidade;

public interface ICidadeRepository extends Repository{
	public List<Cidade> lista(String nome);
	public Cidade buscaPorCodigo(Integer idCidade);	
	public List<Cidade> lista(String nome, Integer idCidade);
}
