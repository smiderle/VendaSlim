package br.com.vendaslim.controler;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

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
import br.com.vendaslim.util.Converter;
import br.com.vendaslim.util.DAOFactory;
import br.com.vendaslim.web.util.ContextoUtil;

public class PedidoControler {
	
	private IPedidoRepository pedidoRepository;
	
	public PedidoControler() {
		pedidoRepository = DAOFactory.criaPedidoRepository();
	}
		
	public List<Pedido> buscaUltimosPedidos(Filial filial) {
		return pedidoRepository.buscaUltimosPedidos(filial);
	}
	
	public List<Pedido> buscaPorFiltro(Integer idPedido, Representante representante, Cliente cliente,
			Date dtInicial, Date dtFinal, Status status){
		
		Filial filial = ContextoUtil.getContextBean().getFilial();
		Calendar dtInicioCalendar = Converter.convertDateToCalendar(dtInicial, false);
		Calendar dtFimCalendar = Converter.convertDateToCalendar(dtFinal, true);
		
		
		if(dtInicioCalendar == null && dtFimCalendar != null){
			dtInicioCalendar = new GregorianCalendar();
			dtInicioCalendar.set(Calendar.YEAR, 2012);
		}
		else if (dtFimCalendar== null && dtInicioCalendar  != null){
			dtFimCalendar= new GregorianCalendar();
			dtFimCalendar.set(Calendar.YEAR, 2050);
		}
		//Pode ser que o cliente seja diferente de null, porém o id do cliente é null
		if(cliente == null || cliente.getIdCliente() == null || cliente.getIdCliente() == 0){
			cliente = null;
		}
		
		
		return pedidoRepository.buscaPorFiltro(idPedido, filial, representante, cliente, dtInicioCalendar, dtFimCalendar, status);
	}
	
	public List<PedidoItem> loadPedidoItem(Pedido pedido){
		return pedidoRepository.loadPedidoItem(pedido);
	}
	
	public boolean pedidoUsouProduto(Filial filial, Produto produto){
		return pedidoRepository.buscaPedidoItemPorProduto(filial, produto) != null;
	}
	
	public boolean pedidoUsouTabelaPreco(TabelaPreco tabelaPreco){
		return pedidoRepository.buscaPedidoPorTabelaPreco(tabelaPreco) != null;
	}
	
	public boolean pedidoUsouParcelamento(Parcelamento parcelamento){
		return pedidoRepository.buscaPedidoPorParcelamento(parcelamento) != null;
	}
	
	public List<Pedido> buscaPedidosPorCliente(Cliente cliente){
		return pedidoRepository.buscaPedidoPorCliente(cliente);
	}
}
