package br.com.vendaslim.ws.infra;

import java.util.Calendar;
import java.util.List;

import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.com.vendaslim.ws.domain.ClienteIntegration;
import br.com.vendaslim.ws.domain.Domain;
import br.com.vendaslim.ws.domain.GrupoProdutoIntegration;
import br.com.vendaslim.ws.domain.ItemIntegration;
import br.com.vendaslim.ws.domain.PedidoIntegration;
import br.com.vendaslim.ws.domain.Repository;

@SuppressWarnings("unchecked")
public class PedidoHibernate  extends RepositoryHibernate implements Repository{
	
	

	@Override
	public void save(Domain domain){
		super.save(domain);
		this.session.flush();
	}
	
	public Integer buscaMariorIdPorFilial(Integer idEmpresa, Integer idFilial){
		Integer maior = (Integer)this.session.createCriteria(PedidoIntegration.class)
				.add(Restrictions.eq("idEmpresa", idEmpresa))
				.add(Restrictions.eq("idFilial", idFilial))
				.setProjection(Projections.max("idPedido")).uniqueResult();
				return maior == null ? 0 : maior;
	}
	
}
