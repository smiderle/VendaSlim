package br.com.vendaslim.ws.infra;

import java.util.Calendar;
import java.util.List;

import org.hibernate.criterion.Restrictions;

import br.com.vendaslim.ws.domain.ClienteIntegration;
import br.com.vendaslim.ws.domain.GrupoProdutoIntegration;
import br.com.vendaslim.ws.domain.ItemIntegration;
import br.com.vendaslim.ws.domain.Repository;

@SuppressWarnings("unchecked")
public class ProdutoHibernate  extends RepositoryHibernate implements Repository{
	
	public List<ItemIntegration> buscarPorDataAlteracao(Calendar dtHrAlteracao, Integer idEmpresa, Integer idFilial) {
		return this.session.createCriteria(ItemIntegration.class)
				.add(Restrictions.ge("dtHrAlteracao", dtHrAlteracao))
				.add(Restrictions.eq("idEmpresa", idEmpresa))
				.add(Restrictions.eq("idFilial", idFilial)).list();
	}

}
