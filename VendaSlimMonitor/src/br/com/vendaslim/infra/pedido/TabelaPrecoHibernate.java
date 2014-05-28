package br.com.vendaslim.infra.pedido;

import java.util.List;

import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.com.vendaslim.domain.Domain;
import br.com.vendaslim.domain.cadastro.Filial;
import br.com.vendaslim.domain.pedido.Parcelamento;
import br.com.vendaslim.domain.pedido.TabelaPreco;
import br.com.vendaslim.domain.repository.ITabelaPrecoRepository;
import br.com.vendaslim.infra.RepositoryHibernate;


public class TabelaPrecoHibernate extends RepositoryHibernate implements ITabelaPrecoRepository{

	@SuppressWarnings("unchecked")
	@Override
	public List<TabelaPreco> lista(Filial filial) {
		return session.createCriteria(TabelaPreco.class)
				.add(Restrictions.eq("filial", filial)).list();
		
	}

	@Override
	public Domain buscaPorDescricao(String descricao, Filial filial) {
		return (TabelaPreco) session.createCriteria(TabelaPreco.class)
				.add(Restrictions.and(						
						Restrictions.eq("descricao", descricao),
						Restrictions.eq("filial", filial))).uniqueResult();	
	}
	
	
	@Override
	public Integer buscaMaiorIdPorFilial(Filial filial) {
		Integer maior = (Integer)this.session.createCriteria(TabelaPreco.class)
				.add(Restrictions.eq("filial", filial))
				.setProjection(Projections.max("idTabelaPreco")).uniqueResult();
		return maior == null ? 0 : maior;
	}


	@Override
	public TabelaPreco buscaPorId(TabelaPreco tabelaPreco) {
		return (TabelaPreco) this.session.createCriteria(TabelaPreco.class)
				.add(Restrictions.eq("filial", tabelaPreco.getFilial()))
				.add(Restrictions.eq("idTabelaPreco", tabelaPreco.getIdTabelaPreco())).uniqueResult();
	}

	@Override
	public boolean existeDescricaoTabela(TabelaPreco tabelaPreco) {
		return (TabelaPreco) this.session.createCriteria(TabelaPreco.class)
				.add(Restrictions.eq("filial", tabelaPreco.getFilial()))
				.add(Restrictions.eq("descricao", tabelaPreco.getDescricao())).uniqueResult() != null;
	}

}
