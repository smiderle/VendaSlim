package br.com.vendaslim.domain.repository;

import java.util.List;

import br.com.vendaslim.domain.Repository;
import br.com.vendaslim.domain.pedido.Pedido;

public interface IPedidoItemRepository extends Repository{
	
	/*public List<Pedido> buscaPorRepresentante(RepresentanteFilial representanteFilial);
	public List<Pedido> buscaPorCliente(Cliente  cliente);
	public List<Pedido> buscaPorData(Calendar dtInicial, Calendar dtFinal);*/
	public List<Pedido> buscaPorPedido(Pedido pedido);
}
