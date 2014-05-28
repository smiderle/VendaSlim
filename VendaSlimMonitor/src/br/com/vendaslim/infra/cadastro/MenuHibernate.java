package br.com.vendaslim.infra.cadastro;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.com.vendaslim.domain.cadastro.Menu;
import br.com.vendaslim.domain.repository.IMenuRepository;
import br.com.vendaslim.infra.RepositoryHibernate;

public class MenuHibernate extends RepositoryHibernate implements IMenuRepository{
		
	@SuppressWarnings("unchecked")
	@Override
	public List<Menu> listar() {
		return session.createCriteria(Menu.class).addOrder(Order.asc("ordem")).list();		
	}

	
}
