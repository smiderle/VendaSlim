package br.com.vendaslim.ws.infra;

import java.util.Calendar;
import java.util.List;

import org.hibernate.criterion.Restrictions;

import br.com.vendaslim.ws.domain.MensagemIntegration;
import br.com.vendaslim.ws.domain.Repository;

@SuppressWarnings("unchecked")
public class MensagemHibernate  extends RepositoryHibernate implements Repository{
	
	public List<MensagemIntegration> buscarPorDataAlteracao(Calendar dtHrAlteracao, Integer idEmpresa, Integer idRepresentante) {
		return this.session.createCriteria(MensagemIntegration.class)
				.add(Restrictions.ge("dtHrCadastro", dtHrAlteracao))
				.add(Restrictions.eq("idEmpresa", idEmpresa))
				.add(Restrictions.eqOrIsNull("IdRepresentanetDestino", idRepresentante)).list();
	}

}
