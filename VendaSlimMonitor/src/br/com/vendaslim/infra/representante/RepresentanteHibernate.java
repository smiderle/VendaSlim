package br.com.vendaslim.infra.representante;

import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.com.vendaslim.domain.cadastro.Empresa;
import br.com.vendaslim.domain.cadastro.Filial;
import br.com.vendaslim.domain.repository.IRepresentanteRepository;
import br.com.vendaslim.domain.representante.Representante;
import br.com.vendaslim.domain.representante.RepresentanteFilial;
import br.com.vendaslim.infra.RepositoryHibernate;

public class RepresentanteHibernate extends RepositoryHibernate implements IRepresentanteRepository{
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Representante> lista(Empresa empresa) {
		return this.session.createCriteria(Representante.class)
				.add(Restrictions.eq("empresa", empresa))
				.addOrder(Order.asc("inativo"))
				.addOrder(Order.asc("empresa.idEmpresa"))
				.addOrder(Order.asc("idRepresentante"))				
				.list();		
	}
	
	@Override
	public Representante buscaPorNome(String nome, Filial filial) {		
		return (Representante) this.session.createCriteria(Representante.class)
					.add(Restrictions.and(
							Restrictions.eq("nome", nome), 
							Restrictions.eq("filial", filial))).uniqueResult();
		
	}
	
	
	public Integer buscaMariorIdPorEmpresa(Empresa empresa){
		Integer maior = (Integer)this.session.createCriteria(Representante.class)
							.add(Restrictions.eq("empresa", empresa))
							.setProjection(Projections.max("idRepresentante")).uniqueResult();
		return maior == null ? 0 : maior;
	}

	@Override
	public Representante buscaPorId(Representante representante) {
		return (Representante) this.session.createCriteria(Representante.class)
					.add(Restrictions.eq("empresa", representante.getEmpresa()))
					.add(Restrictions.eq("idRepresentante", representante.getIdRepresentante())).uniqueResult();
			
		
	}

	@Override
	public void deleteRepresentanteTabPreco(Representante representante) {
		String hql = "delete from reptabpreco where idempresa = :idEmpresa and idrepresentante = :idRepresentante";
		this.session.createSQLQuery(hql)
			.setParameter("idEmpresa", representante.getEmpresa().getIdEmpresa())
			.setParameter("idRepresentante",representante.getIdRepresentante())
			.executeUpdate();		
	}

	@Override
	public void deleteRepresentanteParcelamento(Representante representante) {
		String hql = "delete from repparcela where idempresa = :idEmpresa and idrepresentante = :idRepresentante";
		this.session.createSQLQuery(hql)
			.setParameter("idEmpresa", representante.getEmpresa().getIdEmpresa())
			.setParameter("idRepresentante",representante.getIdRepresentante())
			.executeUpdate();		
	}

	@Override
	public void deleteRepresentanteFilial(Representante representante) {
		String hql = "delete from repfilial where idempresa = :idEmpresa and idrepresentante = :idRepresentante";
		this.session.createSQLQuery(hql)
			.setParameter("idEmpresa", representante.getEmpresa().getIdEmpresa())
			.setParameter("idRepresentante",representante.getIdRepresentante())
			.executeUpdate();
	}

	@Override
	public void deleteRepresentante(Representante representante) {
		String hql = "delete from representante where idempresa = :idEmpresa and  idrepresentante = :idRepresentante";
		this.session.createSQLQuery(hql)
			.setParameter("idEmpresa", representante.getEmpresa().getIdEmpresa())
			.setParameter("idRepresentante", representante.getIdRepresentante())
			.executeUpdate();
	}

	@Override
	public Representante buscaPorIdOuLogin(Representante representante) {
		return (Representante) this.session.createCriteria(Representante.class)
				.add(Restrictions.or(
						Restrictions.eq("login", representante.getLogin()),
						Restrictions.eq("idRepresentante", representante.getIdRepresentante())))					
				.add(Restrictions.eq("empresa", representante.getEmpresa())).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Representante> buscaPorFilial(Filial filial) {
		/*return this.session.createCriteria(RepresentanteFilial.class)
				.add(Restrictions.eq("filial", filial)).list();*/
		/*String sql = "select * from representante "+ 
				"join repfilial "+ 
				"on(representante.idempresa = repfilial.idempresa "+
				"and representante.idrepresentante = repfilial.idrepresentante) "+
				"where repfilial.idempresa = :idEmpresa" +
				"repfilial.idfilial = :idFilial";*/
		
		String sql = "SELECT * FROM REPRESENTANTE " +
				"JOIN REPFILIAL " +
				"ON(REPRESENTANTE.IDEMPRESA = REPFILIAL.IDEMPRESA "+
				"AND REPRESENTANTE.IDREPRESENTANTE = REPFILIAL.IDREPRESENTANTE)"+				
				"WHERE REPFILIAL.IDEMPRESA = :idEmpresa " +
				"AND REPFILIAL.IDFILIAL = :idFilial " +
				"ORDER BY REPRESENTANTE.NOME";
				
		
		return this.session.createSQLQuery(sql).addEntity(Representante.class)
				.setParameter("idEmpresa", filial.getEmpresa().getIdEmpresa())
				.setParameter("idFilial", filial.getIdFilial())
				.list();

	}
}
