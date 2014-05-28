package br.com.vendaslim.controler;

import java.util.List;

import br.com.vendaslim.domain.cadastro.Filial;
import br.com.vendaslim.domain.produto.GrupoProduto;
import br.com.vendaslim.domain.produto.Produto;
import br.com.vendaslim.domain.repository.IGrupoProdutoRepository;
import br.com.vendaslim.util.DAOFactory;
import br.com.vendaslim.web.util.ContextoUtil;

public class GrupoProdutoControler {
	
	private IGrupoProdutoRepository grupoProdutoHibernate;
	
	public GrupoProdutoControler() {
		this.grupoProdutoHibernate = DAOFactory.criaGrupoProdutoRepository();
	}
	
	
	public List<GrupoProduto> buscaTodosPorFilial(Filial filial){
		return this.grupoProdutoHibernate.buscaTodosPorFilial(filial);
	}
		
	public List<GrupoProduto> buscaPorIds(String ids, Filial filial){
		return this.grupoProdutoHibernate.buscaPorIds(ids, filial);
	}
	
	public Integer buscaProximoIdPorFilial(Filial filial){
		return this.grupoProdutoHibernate.buscaMaiorIdPorFilial(filial) + 1;
	}
		
	public GrupoProduto buscaPorId(GrupoProduto grupoProduto){
		return this.grupoProdutoHibernate.buscaPorId(grupoProduto);
	}
	
	public void salvar(GrupoProduto grupoProduto){
		this.grupoProdutoHibernate.save(grupoProduto);
	}
	
	public void atualizar(GrupoProduto grupoProduto){
		this.grupoProdutoHibernate.update(grupoProduto);
	}
	
	public void delete(GrupoProduto grupoProduto){
		this.grupoProdutoHibernate.delete(grupoProduto);
	}
	
	public List<GrupoProduto> buscaPorFiltro(String filtro){
		Integer idGrupoProduto = 0;			
		try {
			idGrupoProduto = Integer.parseInt(filtro);
		} catch (NumberFormatException e) {}
		Filial filial = ContextoUtil.getContextBean().getFilial();
		return this.grupoProdutoHibernate.buscaPorFiltro(filtro, idGrupoProduto, filial);
	}
	
	public boolean exclusaoPermitida(GrupoProduto grupoProduto){
		ProdutoControler produtoController = new ProdutoControler();
		List<Produto> ls = produtoController.buscaPorGrupo(grupoProduto) ;
		return ls == null || ls.size() == 0;
		
	}	
}
