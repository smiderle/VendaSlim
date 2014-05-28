package br.com.vendaslim.infra.pedido;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.vendaslim.domain.cadastro.Filial;
import br.com.vendaslim.domain.cliente.Cliente;
import br.com.vendaslim.domain.pedido.Parcelamento;
import br.com.vendaslim.domain.pedido.Pedido;
import br.com.vendaslim.domain.pedido.Status;
import br.com.vendaslim.domain.pedido.TabelaPreco;
import br.com.vendaslim.domain.pedidoItem.PedidoItem;
import br.com.vendaslim.domain.produto.Produto;
import br.com.vendaslim.domain.repository.IPedidoRepository;
import br.com.vendaslim.domain.representante.Representante;
import br.com.vendaslim.infra.ConstantsRepository;
import br.com.vendaslim.infra.RepositoryHibernate;

public class PedidoHibernate extends RepositoryHibernate implements IPedidoRepository{

	@SuppressWarnings("unchecked")
	@Override
	public List<Pedido> buscaPorFiltro(Integer idPedido, Filial filial, Representante representante,
			Cliente cliente, Calendar dtInicial, Calendar dtFinal, Status status) {
		
		
		Criteria criteria = this.session.createCriteria(Pedido.class)
				.add(Restrictions.eq("filial", filial));
		
		if(idPedido != null && idPedido != 0)
			criteria.add(Restrictions.eq("idPedido", idPedido));
		
		if(status != null)
			criteria.add(Restrictions.eq("status", status.ordinal()));
		
		if(dtInicial != null && dtFinal != null)
			criteria.add(Restrictions.and(
					Restrictions.ge("dtHrEmissao", dtInicial),
					Restrictions.le("dtHrEmissao", dtFinal)));
		
		if(cliente != null)
			criteria.add(Restrictions.eq("cliente", cliente));
		
		if(representante != null)
			criteria.add(Restrictions.eq("representanteFilial.idRepresentante", representante.getIdRepresentante()));
		
		
		
		return criteria
				.setMaxResults(ConstantsRepository.MAX_RESULT_PEDIDOS)
				.addOrder(Order.asc("idPedido"))
				.list();
		
		
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pedido> buscaUltimosPedidos(Filial filial) {
		return this.session.createCriteria(Pedido.class)
				.add(Restrictions.eq("filial", filial))
				.setMaxResults(20)
				.addOrder(Order.asc("dtHrEmissao"))
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PedidoItem> loadPedidoItem(Pedido pedido) {
		return this.session.createCriteria(PedidoItem.class)
				.add(Restrictions.eq("filial", pedido.getFilial()))
				.add(Restrictions.eq("idPedido", pedido.getIdPedido())).list();
		
	}

	@Override
	public PedidoItem buscaPedidoItemPorProduto(Filial filial, Produto produto) {
		return (PedidoItem) this.session.createCriteria(PedidoItem.class)
				.add(Restrictions.eq("filial", filial))
				.add(Restrictions.eq("produto", produto))
				.setMaxResults(1)
				.uniqueResult();

	}

	@Override
	public Pedido buscaPedidoPorTabelaPreco(TabelaPreco tabelaPreco) {
		return (Pedido) this.session.createCriteria(Pedido.class)
				.add(Restrictions.eq("tabelaPreco", tabelaPreco))								
				.setMaxResults(1)				
				.uniqueResult();
	}

	@Override
	public Pedido buscaPedidoPorParcelamento(Parcelamento parcelamento) {
		return (Pedido) this.session.createCriteria(Pedido.class)
				.add(Restrictions.eq("parcelamento", parcelamento))								
				.setMaxResults(1)				
				.uniqueResult();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pedido> buscaPedidoPorCliente(Cliente cliente) {
		return  this.session.createCriteria(Pedido.class)
				.add(Restrictions.eq("cliente", cliente))								
				.setMaxResults(ConstantsRepository.MAX_RESULT_CLIENTES)
				.list();
	}

}
