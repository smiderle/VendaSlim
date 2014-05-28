package br.com.vendaslim.infra.produto;

import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.com.vendaslim.domain.cadastro.Filial;
import br.com.vendaslim.domain.cliente.Cliente;
import br.com.vendaslim.domain.produto.GrupoProduto;
import br.com.vendaslim.domain.produto.Produto;
import br.com.vendaslim.domain.repository.IProdutoRepository;
import br.com.vendaslim.infra.ConstantsRepository;
import br.com.vendaslim.infra.RepositoryHibernate;

public class ProdutoHibernate extends RepositoryHibernate implements  IProdutoRepository{

	@SuppressWarnings("unchecked")
	@Override
	public List<Produto> buscarTodosPorFilial(Filial filial) {
		return session.createCriteria(Produto.class)
				.add(Restrictions.eq("filial", filial))
				.addOrder(Order.asc("idProduto")).list();
	}

	@Override
	public Produto buscaPorId(Produto produto) {
		return (Produto) this.session.createCriteria(Produto.class)
				.add(Restrictions.eq("filial", produto.getFilial()))
				.add(Restrictions.eq("idProduto", produto.getIdProduto())).uniqueResult();
	}
	
	@Override
	public Integer buscaMaiorIdPorFilial(Filial filial) {
		Integer maior = (Integer)this.session.createCriteria(Produto.class)
				.add(Restrictions.eq("filial", filial))
				.setProjection(Projections.max("idProduto")).uniqueResult();
		return maior == null ? 0 : maior;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Produto> buscaPorGrupo(GrupoProduto grupoProduto) {
		return session.createCriteria(Produto.class)
				.add(Restrictions.eq("grupoProduto", grupoProduto))
				.setMaxResults(50)
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Produto> buscaPorFiltro(Filial filial, String filtro, Integer codigo) {
		return this.session.createCriteria(Produto.class)
				.add(Restrictions.or(
						Restrictions.like("descricao", filtro, MatchMode.START).ignoreCase(),						
						Restrictions.eq("idProduto", codigo)))
				.add(Restrictions.eq("filial", filial))
				.setMaxResults(ConstantsRepository.MAX_RESULT_PRODUTOS)
				.list();
	}

}
