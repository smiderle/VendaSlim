package br.com.vendaslim.ws.infra;

import java.util.Calendar;
import java.util.List;

import org.hibernate.criterion.Restrictions;

import br.com.vendaslim.ws.domain.GrupoRepresentanteParcelamentoIntegration;
import br.com.vendaslim.ws.domain.Repository;

@SuppressWarnings("unchecked")
public class GrupoRepresentanteParcelamentoHibernate  extends RepositoryHibernate implements Repository{
	
	public List<GrupoRepresentanteParcelamentoIntegration> buscarPorDataAlteracao(Calendar dtHrAlteracao, Integer idEmpresa, Integer idFilial) {
		return this.session.createCriteria(GrupoRepresentanteParcelamentoIntegration.class)
				.add(Restrictions.ge("dtHrAlteracao", dtHrAlteracao))
				.add(Restrictions.eq("idEmpresa", idEmpresa))
				.add(Restrictions.eq("idFilial", idFilial)).list();
	}

}
