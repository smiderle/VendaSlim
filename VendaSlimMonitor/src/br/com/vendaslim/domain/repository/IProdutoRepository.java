package br.com.vendaslim.domain.repository;

import java.util.List;

import br.com.vendaslim.domain.Repository;
import br.com.vendaslim.domain.cadastro.Filial;
import br.com.vendaslim.domain.produto.GrupoProduto;
import br.com.vendaslim.domain.produto.Produto;

public interface IProdutoRepository extends Repository{
	public List<Produto> buscarTodosPorFilial(Filial filial);
	public Integer buscaMaiorIdPorFilial(Filial filial);
	public Produto buscaPorId(Produto produto);
	public List<Produto> buscaPorGrupo(GrupoProduto grupoProduto);
	public List<Produto> buscaPorFiltro(Filial filial, String filtro, Integer codigo);
}
