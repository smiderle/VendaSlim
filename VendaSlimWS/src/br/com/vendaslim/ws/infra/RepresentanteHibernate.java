package br.com.vendaslim.ws.infra;

import java.util.Calendar;
import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.com.vendaslim.ws.domain.ClienteIntegration;
import br.com.vendaslim.ws.domain.DeviceIntegration;
import br.com.vendaslim.ws.domain.Repository;
import br.com.vendaslim.ws.domain.RepresentanteIntegration;


@SuppressWarnings("unchecked")
public class RepresentanteHibernate extends RepositoryHibernate implements Repository{

	public List<RepresentanteIntegration> buscarPorDataAlteracao(Calendar dtHrAlteracao, Integer idEmpresa) {		
		return this.session.createCriteria(RepresentanteIntegration.class)
				.add(Restrictions.ge("dtHrAlteracao", dtHrAlteracao))
				.add(Restrictions.eq("idEmpresa", idEmpresa)).list();
	}
	
	
	public Integer buscaMariorIdPorEmpresa(Integer idEmpresa){
		Integer maior = (Integer)this.session.createCriteria(RepresentanteIntegration.class)
				.add(Restrictions.eq("idEmpresa", idEmpresa))
				.setProjection(Projections.max("idRepresentante")).uniqueResult();
				return maior == null ? 0 : maior;
	}
	
	/**
	 * Busca o representante com determinado serial
	 * @param serial
	 * @return
	 */
	public RepresentanteIntegration buscarPorSerial(DeviceIntegration deviceIntegration) {		
		return (RepresentanteIntegration) this.session.createCriteria(RepresentanteIntegration.class)
				.add(Restrictions.eq("deviceIntegration", deviceIntegration))
				.addOrder(Order.desc("id"))
				.setMaxResults(1)
				.uniqueResult();				
	}
}
