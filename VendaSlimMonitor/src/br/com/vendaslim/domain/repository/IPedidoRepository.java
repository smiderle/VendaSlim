package br.com.vendaslim.domain.repository;

import java.util.Calendar;
import java.util.List;

import br.com.vendaslim.domain.Repository;
import br.com.vendaslim.domain.cadastro.Filial;
import br.com.vendaslim.domain.cliente.Cliente;
import br.com.vendaslim.domain.pedido.Parcelamento;
import br.com.vendaslim.domain.pedido.Pedido;
import br.com.vendaslim.domain.pedido.Status;
import br.com.vendaslim.domain.pedido.TabelaPreco;
import br.com.vendaslim.domain.pedidoItem.PedidoItem;
import br.com.vendaslim.domain.produto.Produto;
import br.com.vendaslim.domain.representante.Representante;

public interface IPedidoRepository extends Repository{
	
	/*public List<Pedido> buscaPorRepresentante(RepresentanteFilial representanteFilial);
	public List<Pedido> buscaPorCliente(Cliente  cliente);
	public List<Pedido> buscaPorData(Calendar dtInicial, Calendar dtFinal);*/
	public List<Pedido> buscaPorFiltro(Integer idPedido, Filial filial, Representante representante,Cliente cliente, Calendar dtInicial, Calendar dtFinal, Status status);
	public List<Pedido> buscaUltimosPedidos(Filial filial);
	public List<PedidoItem> loadPedidoItem(Pedido pedido);
	//Verifica se existe pedido com o produto informado
	public PedidoItem buscaPedidoItemPorProduto(Filial filial, Produto produto);
	public Pedido buscaPedidoPorTabelaPreco(TabelaPreco tabelaPreco);
	public Pedido buscaPedidoPorParcelamento(Parcelamento parcelamento);
	public List<Pedido> buscaPedidoPorCliente(Cliente cliente);
}
