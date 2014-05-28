package br.com.vendaslim.ws.infra;

import java.util.Calendar;
import java.util.List;

import org.hibernate.criterion.Restrictions;

import br.com.vendaslim.ws.domain.FilialIntegration;
import br.com.vendaslim.ws.domain.Repository;

@SuppressWarnings("unchecked")
public class FilialHibernate  extends RepositoryHibernate implements Repository{
	
	public List<FilialIntegration> buscarPorDataAlteracao(Calendar dtHrAlteracao, Integer idEmpresa) {
		return this.session.createCriteria(FilialIntegration.class)
				.add(Restrictions.ge("dtHrAlteracao", dtHrAlteracao))
				.add(Restrictions.eq("idEmpresa", idEmpresa))
				.list();
	}

}
