package br.com.vendaslim.ws.infra;

import org.hibernate.criterion.Projections;

import br.com.vendaslim.ws.domain.Repository;
import br.com.vendaslim.ws.domain.UsuarioIntegration;

public class UsuarioHibernate extends RepositoryHibernate implements Repository{
	
	public Integer buscaMariorId(){
		Integer maior = (Integer)this.session.createCriteria(UsuarioIntegration.class)				
				.setProjection(Projections.max("idUsuario")).uniqueResult();
				return maior == null ? 0 : maior;
	}

}
