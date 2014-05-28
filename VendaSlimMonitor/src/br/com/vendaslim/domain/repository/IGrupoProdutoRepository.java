package br.com.vendaslim.domain.repository;

import java.util.List;

import br.com.vendaslim.domain.Repository;
import br.com.vendaslim.domain.cadastro.Empresa;
import br.com.vendaslim.domain.cadastro.Filial;
import br.com.vendaslim.domain.produto.GrupoProduto;
import br.com.vendaslim.domain.produto.Produto;

public interface IGrupoProdutoRepository extends Repository {	
	public List<GrupoProduto> buscaTodosPorFilial(Filial filial);	
	public List<GrupoProduto> buscaPorIds(String ids, Filial filial);
	public GrupoProduto buscaPorId(GrupoProduto grupoProduto);
	public Integer buscaMaiorIdPorFilial(Filial filial);
	public List<GrupoProduto> buscaPorFiltro(String descricao, Integer idGrupo, Filial filial);
}
