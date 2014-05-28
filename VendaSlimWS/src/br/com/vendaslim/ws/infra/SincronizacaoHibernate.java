package br.com.vendaslim.ws.infra;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.vendaslim.ws.domain.Domain;
import br.com.vendaslim.ws.domain.Repository;
import br.com.vendaslim.ws.domain.Sincronizacao;


@SuppressWarnings("unchecked")
public class SincronizacaoHibernate extends RepositoryHibernate implements Repository{

	public Sincronizacao buscarUltimaSincronizacao(Integer idRepresentante, Integer idEmpresa, Integer idFilial) {
		return (Sincronizacao) this.session.createCriteria(Sincronizacao.class)
				.add(Restrictions.ge("idRepresentante", idRepresentante))
				.add(Restrictions.eq("idEmpresa", idEmpresa))
				.add(Restrictions.eq("idFilial", idFilial))
				.add(Restrictions.eq("sincCompleta", true))
				.addOrder(Order.desc("dtHrSincronizacao"))
				.setMaxResults(1).uniqueResult();
	}

	@Override
	public void save(Domain domain){
		super.save(domain);
	}
}