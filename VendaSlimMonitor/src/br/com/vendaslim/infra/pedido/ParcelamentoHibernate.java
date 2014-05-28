package br.com.vendaslim.infra.pedido;

import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.com.vendaslim.domain.Domain;
import br.com.vendaslim.domain.cadastro.Filial;
import br.com.vendaslim.domain.pedido.Parcelamento;
import br.com.vendaslim.domain.pedido.TabelaPreco;
import br.com.vendaslim.domain.repository.IParcelamentoRepository;
import br.com.vendaslim.infra.RepositoryHibernate;

@SuppressWarnings("unchecked")
public class ParcelamentoHibernate extends RepositoryHibernate implements  IParcelamentoRepository{

	@Override
	public Domain buscaPorDescricao(String descricao, Filial filial) {
		return (Parcelamento) session.createCriteria(Parcelamento.class)
				.add(Restrictions.and(						
						Restrictions.eq("descricao", descricao),
						Restrictions.eq("filial", filial))).uniqueResult();	
	}

	
	@Override
	public List<Parcelamento> lista(Filial filial) {
		return session.createCriteria(Parcelamento.class)
				.add(Restrictions.eq("filial", filial))
				.addOrder(Order.asc("idParcelamento")).list();
	}


	@Override
	public Integer buscaMaiorIdPorFilial(Filial filial) {
		Integer maior = (Integer)this.session.createCriteria(Parcelamento.class)
				.add(Restrictions.eq("filial", filial))
				.setProjection(Projections.max("idParcelamento")).uniqueResult();
		return maior == null ? 0 : maior;
	}


	@Override
	public Parcelamento buscaPorId(Parcelamento parcelamento) {
		return (Parcelamento) this.session.createCriteria(Parcelamento.class)
				.add(Restrictions.eq("filial", parcelamento.getFilial()))
				.add(Restrictions.eq("idParcelamento", parcelamento.getIdParcelamento())).uniqueResult();
	}


	@Override
	public boolean existeDescricaoParcelamento(Parcelamento parcelamento) {
		return (Parcelamento) this.session.createCriteria(Parcelamento.class)
				.add(Restrictions.eq("filial", parcelamento.getFilial()))
				.add(Restrictions.eq("descricao", parcelamento.getDescricao())).uniqueResult() != null;
	}

	
	

}
