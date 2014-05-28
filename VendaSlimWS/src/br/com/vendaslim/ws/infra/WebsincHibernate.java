package br.com.vendaslim.ws.infra;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.vendaslim.ws.domain.Repository;
import br.com.vendaslim.ws.domain.Websinc;

public class WebsincHibernate  extends RepositoryHibernate implements Repository{
	/**
	 * Retorna o ultimo registro de versão para determinado device inserido no banco.
	 * @param serial
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<Websinc> buscaPorRepresentante(Integer idEmpresa, Integer idFilial, Integer idRepresentante){
		return this.session.createCriteria(Websinc.class)
				.add(Restrictions.eq("idEmpresa", idEmpresa))
				//.add(Restrictions.eq("idFilial", idFilial))
				.add(Restrictions.eq("idRepresentante", idRepresentante))
				.addOrder(Order.asc("sequencia"))
				.list();
	}
	
	public void deleteData(String sequencias){
		Query query = this.session.createQuery("DELETE FROM Websinc WHERE sequencia in ("+sequencias+")");		
		query.executeUpdate();
	}
}