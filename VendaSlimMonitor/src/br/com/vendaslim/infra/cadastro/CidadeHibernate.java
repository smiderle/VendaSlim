package br.com.vendaslim.infra.cadastro;

import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.com.vendaslim.domain.cadastro.Cidade;
import br.com.vendaslim.domain.repository.ICidadeRepository;
import br.com.vendaslim.infra.RepositoryHibernate;

public class CidadeHibernate extends RepositoryHibernate implements ICidadeRepository {

	@Override
	public List<Cidade> lista(String nome) {
		return null;
	}

	@Override
	public Cidade buscaPorCodigo(Integer idCidade) {
		return (Cidade) this.session.createCriteria(Cidade.class)
							.add(Restrictions.eq("idCidade", idCidade)).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cidade> lista(String prefixo, Integer idCidade) {
		return this.session.createCriteria(Cidade.class)
			.add(Restrictions.or(
					Restrictions.like("nome", prefixo, MatchMode.START).ignoreCase(),
					Restrictions.eq("idCidade", idCidade)))
					.list();
	}

}
