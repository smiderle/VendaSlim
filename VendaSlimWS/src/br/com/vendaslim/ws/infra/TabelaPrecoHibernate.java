package br.com.vendaslim.ws.infra;

import java.util.Calendar;
import java.util.List;

import org.hibernate.criterion.Restrictions;

import br.com.vendaslim.ws.domain.Repository;
import br.com.vendaslim.ws.domain.TabelaPrecoIntegration;

@SuppressWarnings("unchecked")
public class TabelaPrecoHibernate  extends RepositoryHibernate implements Repository{
	
	public List<TabelaPrecoIntegration> buscarPorDataAlteracao(Calendar dtHrAlteracao, Integer idEmpresa, Integer idFilial) {
		return this.session.createCriteria(TabelaPrecoIntegration.class)
				.add(Restrictions.ge("dtHrAlteracao", dtHrAlteracao))
				.add(Restrictions.eq("idEmpresa", idEmpresa))
				.add(Restrictions.eq("idFilial", idFilial)).list();
	}

}
