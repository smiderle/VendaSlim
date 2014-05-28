package br.com.vendaslim.ws.conexao;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {
	
	private static final SessionFactory sessionFactory = configureSessionFactory();
	
	private static SessionFactory configureSessionFactory() throws HibernateException {
		
		try {
			Configuration cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");
			SessionFactory sf =cfg.buildSessionFactory(); 
			return sf;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	    /*Configuration configuration = new Configuration();  
	    configuration.configure();  
	    ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();          
	    return configuration.buildSessionFactory(serviceRegistry);	      */
	} 

	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
}
