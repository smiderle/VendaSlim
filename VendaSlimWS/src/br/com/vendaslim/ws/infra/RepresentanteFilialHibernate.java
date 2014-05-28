package br.com.vendaslim.ws.infra;

import java.util.Calendar;
import java.util.List;

import org.hibernate.criterion.Restrictions;

import br.com.vendaslim.ws.domain.Repository;
import br.com.vendaslim.ws.domain.RepresentanteFilialIntegration;


@SuppressWarnings("unchecked")
public class RepresentanteFilialHibernate extends RepositoryHibernate implements Repository{

	public List<RepresentanteFilialIntegration> buscarPorDataAlteracao(Calendar dtHrAlteracao, Integer idEmpresa) {		
		return this.session.createCriteria(RepresentanteFilialIntegration.class)
				.add(Restrictions.ge("dtHrAlteracao", dtHrAlteracao))
				.add(Restrictions.eq("idEmpresa", idEmpresa)).list();
	}
}
