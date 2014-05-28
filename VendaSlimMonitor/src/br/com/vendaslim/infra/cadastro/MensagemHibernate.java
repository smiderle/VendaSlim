package br.com.vendaslim.infra.cadastro;

import java.util.List;

import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.com.vendaslim.domain.cadastro.Empresa;
import br.com.vendaslim.domain.cadastro.Mensagem;
import br.com.vendaslim.domain.cliente.Cliente;
import br.com.vendaslim.domain.repository.IMensagemRepository;
import br.com.vendaslim.domain.representante.Representante;
import br.com.vendaslim.infra.ConstantsRepository;
import br.com.vendaslim.infra.RepositoryHibernate;

@SuppressWarnings("unchecked")
public class MensagemHibernate extends RepositoryHibernate implements IMensagemRepository {

	
	@Override
	public List<Mensagem> listaTodas(Empresa empresa) {
		String sql = "SELECT * FROM MENSAGEM WHERE MENSAGEM.IDEMPRESA = :idEmpresa ORDER BY IDMENSAGEM DESC";
		
		return this.session.createSQLQuery(sql).addEntity(Mensagem.class)
				.setParameter("idEmpresa", empresa.getIdEmpresa())
				.setMaxResults(ConstantsRepository.MAX_RESULT_MENSAGENS)
				.list();
		
		/*		
		return this.session.createCriteria(Mensagem.class)
				.add(Restrictions.eq("empresa", empresa))
				.setMaxResults(ConstantsRepository.MAX_RESULT_MENSAGENS)
				.list();*/
		
	}

	@Override
	public List<Mensagem> listaTodasPorRepresentante(Empresa empresa,
			Representante representante) {
		return this.session.createCriteria(Mensagem.class)
				.add(Restrictions.eq("empresa", empresa))
				.add(Restrictions.or(
						Restrictions.eq("representante_origem", representante),
						Restrictions.eq("representante_destino", representante)))
				.setMaxResults(ConstantsRepository.MAX_RESULT_MENSAGENS)
				.list();
	}

	@Override
	public Integer buscaMariorId(Empresa empresa) {
		Integer maior = (Integer)this.session.createCriteria(Mensagem.class)
				.add(Restrictions.eq("empresa", empresa))
				.setProjection(Projections.max("idMensagem")).uniqueResult();
				return maior == null ? 0 : maior;
	}
}
