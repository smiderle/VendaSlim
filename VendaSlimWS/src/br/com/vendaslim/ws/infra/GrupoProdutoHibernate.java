package br.com.vendaslim.ws.infra;

import java.util.Calendar;
import java.util.List;

import org.hibernate.criterion.Restrictions;

import br.com.vendaslim.ws.domain.ClienteIntegration;
import br.com.vendaslim.ws.domain.GrupoProdutoIntegration;
import br.com.vendaslim.ws.domain.Repository;

@SuppressWarnings("unchecked")
public class GrupoProdutoHibernate  extends RepositoryHibernate implements Repository{
	
	public List<GrupoProdutoIntegration> buscarPorDataAlteracao(Calendar dtHrAlteracao, Integer idEmpresa, Integer idFilial) {		
		return this.session.createCriteria(GrupoProdutoIntegration.class)
				.add(Restrictions.ge("dtHrAlteracao", dtHrAlteracao))
				.add(Restrictions.eq("idEmpresa", idEmpresa))
				.add(Restrictions.eq("idFilial", idFilial)).list();
	}

}
