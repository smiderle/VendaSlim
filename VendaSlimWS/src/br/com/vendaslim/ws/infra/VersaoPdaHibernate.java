package br.com.vendaslim.ws.infra;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.vendaslim.ws.domain.Repository;
import br.com.vendaslim.ws.domain.VersaoPda;

public class VersaoPdaHibernate  extends RepositoryHibernate implements Repository{
	/**
	 * Retorna o ultimo registro de versão para determinado device inserido no banco.
	 * @param serial
	 * @return
	 */
	
	public VersaoPda buscaPorSerial(String serial){
		return (VersaoPda) this.session.createCriteria(VersaoPda.class)
				.add(Restrictions.eq("serial", serial))
				.addOrder(Order.desc("id"))
				.setMaxResults(1)
				.uniqueResult();
	}
}