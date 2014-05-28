package br.com.vendaslim.infra.produto;

import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.com.vendaslim.domain.cadastro.Cidade;
import br.com.vendaslim.domain.cadastro.Empresa;
import br.com.vendaslim.domain.cadastro.Filial;
import br.com.vendaslim.domain.produto.GrupoProduto;
import br.com.vendaslim.domain.produto.Produto;
import br.com.vendaslim.domain.repository.IGrupoProdutoRepository;
import br.com.vendaslim.domain.representante.Representante;
import br.com.vendaslim.infra.ConstantsRepository;
import br.com.vendaslim.infra.RepositoryHibernate;

@SuppressWarnings("unchecked")
public class GrupoProdutoHibernate extends RepositoryHibernate implements IGrupoProdutoRepository{
	
	@Override
	public List<GrupoProduto> buscaTodosPorFilial(Filial filial) {
		return this.session.createCriteria(GrupoProduto.class)
					.add(Restrictions.eq("filial", filial))
					.setMaxResults(10)
					.list();			
		
	}
	

	@Override
	public List<GrupoProduto> buscaPorIds(String ids, Filial filial) {
		if(ids != null && ids.trim().length() > 0){
			StringBuilder sb = new StringBuilder();
			sb.append("from GrupoProduto gp ");
			sb.append("where gp.filial = :filial ");
			sb.append("and gp.idGrupo in (");
			sb.append(ids);
			sb.append(")");
			sb.append("order by gp.idGrupo");
			
			return this.session.createQuery(sb.toString())
					.setParameter("filial", filial).list();		
		} else {
			return null;
		}
	}


	@Override
	public Integer buscaMaiorIdPorFilial(Filial filial) {
		Integer maior = (Integer)this.session.createCriteria(GrupoProduto.class)
				.add(Restrictions.eq("filial", filial))
				.setProjection(Projections.max("idGrupo")).uniqueResult();
		return maior == null ? 0 : maior;
	}


	@Override
	public GrupoProduto buscaPorId(GrupoProduto grupoProduto) {
		return (GrupoProduto) this.session.createCriteria(GrupoProduto.class)
				.add(Restrictions.eq("filial", grupoProduto.getFilial()))
				.add(Restrictions.eq("idGrupo", grupoProduto.getIdGrupo())).uniqueResult();
	}


	@Override
	public List<GrupoProduto> buscaPorFiltro(String prefixo, Integer idGrupo,
			Filial filial) {
		return this.session.createCriteria(GrupoProduto.class)
				.add(Restrictions.or(
						Restrictions.like("descricao", prefixo, MatchMode.START).ignoreCase(),
						Restrictions.eq("idGrupo", idGrupo)))
				.add(Restrictions.eq("filial", filial))
				.setMaxResults(ConstantsRepository.MAX_RESULT_PRODUTOS)
				.list();
	}
}
