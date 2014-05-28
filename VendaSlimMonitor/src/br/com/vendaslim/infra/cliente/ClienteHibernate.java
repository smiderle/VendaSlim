package br.com.vendaslim.infra.cliente;

import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.com.vendaslim.domain.cadastro.Filial;
import br.com.vendaslim.domain.cliente.Cliente;
import br.com.vendaslim.domain.pedido.Parcelamento;
import br.com.vendaslim.domain.pedido.TabelaPreco;
import br.com.vendaslim.domain.repository.IClienteRepository;
import br.com.vendaslim.infra.ConstantsRepository;
import br.com.vendaslim.infra.RepositoryHibernate;

public class ClienteHibernate extends RepositoryHibernate implements IClienteRepository{
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Cliente> lista(Filial filial) {
		return this.session.createCriteria(Cliente.class)
				.add(Restrictions.eq("filial", filial))
				.addOrder(Order.asc("inativo"))
				.addOrder(Order.asc("idCliente"))
				.setMaxResults(ConstantsRepository.MAX_RESULT_CLIENTES)
				.list();		
	}
	
	
	@Override
	public Integer buscaMariorIdPorFilial(Filial filial) {
		Integer maior = (Integer)this.session.createCriteria(Cliente.class)
				.add(Restrictions.eq("filial", filial))
				.setProjection(Projections.max("idCliente")).uniqueResult();
				return maior == null ? 0 : maior;
	}

	@Override
	public Cliente buscaPorId(Cliente cliente) {
		return (Cliente) this.session.createCriteria(Cliente.class)
				.add(Restrictions.eq("filial", cliente.getFilial()))
				.add(Restrictions.eq("idCliente", cliente.getIdCliente())).uniqueResult();
	}

	@Override
	public Cliente buscaPorNome(String nome, Filial filial) {
		return (Cliente) this.session.createCriteria(Cliente.class)
				.add(Restrictions.and(
						Restrictions.eq("nome", nome), 
						Restrictions.eq("filial", filial))).uniqueResult();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Cliente> listaPorFiltro(Filial filial, String filtro, Integer codigo) {
		return this.session.createCriteria(Cliente.class)
				.add(Restrictions.or(
						Restrictions.like("nome", filtro, MatchMode.START).ignoreCase(),
						Restrictions.eq("cpfCnpj", filtro),
						Restrictions.eq("idCliente", codigo)))
				.add(Restrictions.eq("filial", filial))
				.setMaxResults(ConstantsRepository.MAX_RESULT_CLIENTES)
				.list();
	}


	@Override
	public boolean clienteUsouTabelaPreco(Filial filial, TabelaPreco tabelaPreco) {
		return this.session.createCriteria(Cliente.class)
				.add(Restrictions.and(
						Restrictions.eq("idTabelaPreco", tabelaPreco.getIdTabelaPreco()), 
						Restrictions.eq("filial", filial)))
					.setMaxResults(1).uniqueResult() != null;
	}


	@Override
	public boolean clienteUsouParcelamento(Filial filial,Parcelamento parcelamento) {
		return this.session.createCriteria(Cliente.class)
				.add(Restrictions.and(
						Restrictions.eq("idParcelamento", parcelamento.getIdParcelamento()), 
						Restrictions.eq("filial", filial)))
					.setMaxResults(1).uniqueResult() != null;
	}
}
