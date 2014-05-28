package br.com.vendaslim.ws.infra;

import java.util.Calendar;
import java.util.List;

import org.hibernate.criterion.Restrictions;

import br.com.vendaslim.ws.domain.Repository;
import br.com.vendaslim.ws.domain.RepresentanteParcelamentoIntegration;


@SuppressWarnings("unchecked")
public class RepresentanteParcelamentoHibernate extends RepositoryHibernate implements Repository{

	public List<RepresentanteParcelamentoIntegration> buscarPorDataAlteracao(Calendar dtHrAlteracao, Integer idEmpresa, Integer idFilial, Integer idRepresentante) {		
		return this.session.createCriteria(RepresentanteParcelamentoIntegration.class)
				.add(Restrictions.ge("dtHrAlteracao", dtHrAlteracao))
				.add(Restrictions.eq("idEmpresa", idEmpresa))
				.add(Restrictions.eq("idFilial", idFilial))
				.add(Restrictions.eq("idRepresentante", idRepresentante)).list();
	}
}
