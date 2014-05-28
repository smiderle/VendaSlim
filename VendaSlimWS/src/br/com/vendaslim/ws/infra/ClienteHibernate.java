package br.com.vendaslim.ws.infra;

import java.util.Calendar;
import java.util.List;

import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.com.vendaslim.ws.domain.ClienteIntegration;
import br.com.vendaslim.ws.domain.Domain;
import br.com.vendaslim.ws.domain.Repository;


@SuppressWarnings("unchecked")
public class ClienteHibernate extends RepositoryHibernate implements Repository{

	public List<ClienteIntegration> buscarPorDataAlteracao(Calendar dtHrAlteracao, Integer idEmpresa, Integer idFilial) {		
		return this.session.createCriteria(ClienteIntegration.class)
				.add(Restrictions.ge("dtHrAlteracao", dtHrAlteracao))
				.add(Restrictions.eq("idEmpresa", idEmpresa))
				.add(Restrictions.eq("idFilial", idFilial)).list();
	}
	@Override
	public void save(Domain domain){
		super.save(domain);
		this.session.flush();
	}
		
	public Integer buscaMariorIdPorFilial(Integer idEmpresa, Integer idFilial){
		Integer maior = (Integer)this.session.createCriteria(ClienteIntegration.class)
				.add(Restrictions.eq("idEmpresa", idEmpresa))
				.add(Restrictions.eq("idFilial", idFilial))
				.setProjection(Projections.max("idCliente")).uniqueResult();
				return maior == null ? 0 : maior;
	}

}
