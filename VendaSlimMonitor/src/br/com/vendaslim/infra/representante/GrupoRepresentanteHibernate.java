package br.com.vendaslim.infra.representante;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.com.vendaslim.domain.cadastro.Empresa;
import br.com.vendaslim.domain.cadastro.Filial;
import br.com.vendaslim.domain.repository.IGrupoRepresentanteRepository;
import br.com.vendaslim.domain.representante.GrupoRepresentante;
import br.com.vendaslim.domain.representante.GrupoRepresentanteTabPreco;
import br.com.vendaslim.infra.RepositoryHibernate;

@SuppressWarnings("unchecked")
public class GrupoRepresentanteHibernate extends RepositoryHibernate implements IGrupoRepresentanteRepository{	
	
	@Override
	public List<GrupoRepresentante> list(Empresa empresa) {
		//return this.session.createCriteria(UsuarioGrupo.class).list();
		Criterion criterio = Restrictions.eq("filial.empresa", empresa);
		return this.session.createCriteria(GrupoRepresentante.class)
				.add(criterio)				
				.addOrder(Order.asc("filial.idFilial"))				
				.addOrder(Order.asc("idGrupo"))
				.list();
	}
	
	
	@Override
	public List<GrupoRepresentante> list(Filial filial) {
		//return this.session.createCriteria(UsuarioGrupo.class).list();
		Criterion criterio = Restrictions.eq("filial", filial);
		return this.session.createCriteria(GrupoRepresentante.class)
				.add(criterio)
				.addOrder(Order.asc("filial.idFilial"))				
				.addOrder(Order.asc("idGrupo"))
				.list();
	}
	
	public GrupoRepresentante buscaPorID(Filial filial, Integer idGrupo){
		Criterion criterion = Restrictions.and( 
				Restrictions.eq("filial", filial),
				Restrictions.eq("idGrupo", idGrupo));		
		
		return (GrupoRepresentante) this.session.createCriteria(GrupoRepresentante.class)
					.add(criterion).uniqueResult();
	}
	
	public void deleteGrupoRepresentanteTabPreco(GrupoRepresentante grupoRepresentante){
		String hql = "delete from GrupoRepresentanteTabPreco where filial = :filial and idGrupo = :idGrupo";
		this.session.createQuery(hql)
			.setEntity("filial", grupoRepresentante.getFilial())
			.setParameter("idGrupo", grupoRepresentante.getIdGrupo())
			.executeUpdate();
	}
	
	public void deleteGrupoRepresentanteParcelamento(GrupoRepresentante grupoRepresentante){
		String hql = "delete from GrupoRepresentanteParcelamento where filial = :filial and idGrupo = :idGrupo";
		this.session.createQuery(hql)
			.setEntity("filial", grupoRepresentante.getFilial())
			.setParameter("idGrupo", grupoRepresentante.getIdGrupo())
			.executeUpdate();
	}

	@Override
	public Integer buscaMaiorIdPorFilial(Filial filial) {
		Integer maxID = (Integer) this.session.createCriteria(GrupoRepresentante.class)
						.add(Restrictions.eq("filial", filial))
						.setProjection(Projections.max("idGrupo")).uniqueResult();
		return maxID == null ? 0 : maxID;
	}
}
