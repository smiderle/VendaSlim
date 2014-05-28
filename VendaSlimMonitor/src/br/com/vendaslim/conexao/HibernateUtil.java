package br.com.vendaslim.conexao;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	private static final SessionFactory sessionFactory = configureSessionFactory();
	
	private static SessionFactory configureSessionFactory() throws HibernateException {

		try {
			Configuration cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");
			SessionFactory sf =cfg.buildSessionFactory();
			return sf;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	} 

	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
}
