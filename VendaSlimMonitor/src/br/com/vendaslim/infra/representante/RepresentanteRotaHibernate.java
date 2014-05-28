package br.com.vendaslim.infra.representante;

import java.util.Calendar;
import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.vendaslim.domain.cadastro.Empresa;
import br.com.vendaslim.domain.repository.IRepresentanteRotaRepository;
import br.com.vendaslim.domain.representante.RepresentanteRota;
import br.com.vendaslim.infra.RepositoryHibernate;

public class RepresentanteRotaHibernate extends RepositoryHibernate implements IRepresentanteRotaRepository{

	@SuppressWarnings("unchecked")
	@Override
	public List<RepresentanteRota> buscaTodosPorRepresentante(Empresa empresa,
			Integer idRepresentante, Calendar dthrinicio, Calendar dthrfim) {
			return this.session.createCriteria(RepresentanteRota.class)
					.add(Restrictions.eq("empresa", empresa))
					.add(Restrictions.eq("idRepresentante", idRepresentante))
					.add(Restrictions.between("dtHrRota", dthrinicio, dthrfim))
					.addOrder(Order.asc("dtHrRota")).list();
	}

}
