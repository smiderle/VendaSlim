package br.com.vendaslim.web.filter;
import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.hibernate.SessionFactory;

import br.com.vendaslim.conexao.HibernateUtil;

public class ConexaoHibernateFilter implements Filter{

	private SessionFactory sf;

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		this.sf = HibernateUtil.getSessionFactory();
	}
	

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain chain) throws IOException, ServletException {
		
		try {
			this.sf.getCurrentSession().beginTransaction();

			chain.doFilter(servletRequest, servletResponse);

			this.sf.getCurrentSession().getTransaction().commit();
			this.sf.getCurrentSession().close();
		} catch (Throwable e) {
			try {
				if(sf.getCurrentSession().getTransaction().isActive())
					sf.getCurrentSession().getTransaction().rollback();
			} catch (Throwable t) {
				t.printStackTrace();
			}			
			throw new ServletException(e);
		}	
	}

	
	@Override
	public void destroy() {
	}
}
