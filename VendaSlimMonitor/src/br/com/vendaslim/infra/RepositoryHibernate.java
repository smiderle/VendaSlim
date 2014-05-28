package br.com.vendaslim.infra;

import java.util.List;

import javax.management.Query;

import org.hibernate.Session;

import br.com.vendaslim.domain.Domain;
import br.com.vendaslim.domain.Repository;



public abstract class RepositoryHibernate implements Repository{
	protected Session session;
	public void setSession(Session session){
		this.session = session;
	}
	
	@Override
	public void delete(Domain domain) {
		this.session.delete(domain);
		
	}

	@Override
	public void delete(List<? extends Domain> domains) {
		for(Domain domain : domains){
			delete(domain);
		}		
	}

	@Override
	public void save(Domain domain) {		
		this.session.save(domain);		
	}	

	@Override
	public void save(List<? extends Domain> domains) {
		for(Domain domain : domains){
			save(domain);
		}
	}
	

	@Override
	public void saveOrUpdate(Domain domain) {
		this.session.saveOrUpdate(domain);		
	}
	
	@Override
	public void saveOrUpdate(List<? extends Domain> domains) {
		for(Domain domain : domains){
			saveOrUpdate(domain);
		}
	}

	@Override
	public void update(Domain domain) {
		this.session.update(domain);
		
	}

	@Override
	public void update(List<? extends Domain> domains) {
		for(Domain domain : domains){
			update(domain);
		}		
	}
}
