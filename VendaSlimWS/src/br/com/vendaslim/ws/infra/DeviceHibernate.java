package br.com.vendaslim.ws.infra;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.vendaslim.ws.domain.DeviceIntegration;
import br.com.vendaslim.ws.domain.Repository;

public class DeviceHibernate  extends RepositoryHibernate implements Repository{
	
	public DeviceIntegration buscarPorSerial(String serial) {
		return (DeviceIntegration) this.session.createCriteria(DeviceIntegration.class)
				.add(Restrictions.eq("serial", serial))		
				.addOrder(Order.desc("id"))
				.setMaxResults(1)
				.uniqueResult();
	}

}
