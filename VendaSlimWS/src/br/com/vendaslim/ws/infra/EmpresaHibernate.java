package br.com.vendaslim.ws.infra;

import org.hibernate.criterion.Projections;

import br.com.vendaslim.ws.domain.EmpresaIntegration;
import br.com.vendaslim.ws.domain.Repository;

public class EmpresaHibernate extends RepositoryHibernate implements Repository{
	
	public Integer buscaMariorId(){
		Integer maior = (Integer)this.session.createCriteria(EmpresaIntegration.class)				
				.setProjection(Projections.max("idEmpresa")).uniqueResult();
				return maior == null ? 0 : maior;
	}
}
