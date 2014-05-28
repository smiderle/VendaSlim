package br.com.vendaslim.ws.resources;

import org.hibernate.SessionFactory;

import br.com.vendaslim.ws.conexao.HibernateUtil;

public  class Resource {
	protected void openTransaction(){
		SessionFactory sf= HibernateUtil.getSessionFactory();
		sf.getCurrentSession().beginTransaction();
	}
	
	protected void closeTransaction(){
		SessionFactory sf= HibernateUtil.getSessionFactory();
		sf.getCurrentSession().getTransaction().commit();
		sf.getCurrentSession().close();
	}

}
