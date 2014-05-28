package br.com.slim.venda.cliente;

import br.com.slim.venda.pedido.PedidoVO;
import br.com.slim.venda.pedidopgto.PedidoPgtoDAO;
import br.com.slim.venda.usuario.UsuarioVO;
import br.com.slim.venda.util.Util;
import android.content.Context;

public class ClienteModel {
	
	private Context context;
	ClienteDAO clienteDAO;
	
	public ClienteModel(Context context) {
		this.context = context;
	}
	
	public int save(ClienteVO clienteVO){
		ClienteDAO clienteDAO = new ClienteDAO(context);
		int inserts = 0;
		if(clienteVO.getIdCliente() == null || clienteVO.getIdCliente() == 0){
			long idCliente = clienteDAO.getNextId(UsuarioVO.idFilial);
			clienteVO.setAlterado("F");
			clienteVO.setIdCliente((int)idCliente);
			clienteVO.setIdEmpresa(UsuarioVO.idEmpresa);
			clienteVO.setIdFilial(UsuarioVO.idFilial);
			inserts = clienteDAO.insert(clienteVO);
		} else {
			clienteVO.setAlterado("T");
			inserts =clienteDAO.update(clienteVO);
		}
		return inserts;
	}

	/**
	 * Retorna o limite de crédito disponivel, menos o valor passado por parametro
	 * @param valorVenda
	 * @param clienteVO
	 * @return
	 */
	public double limiteDisponivel(ClienteVO clienteVO){
		PedidoPgtoDAO pedidoPgtoDAO = new PedidoPgtoDAO(context);
		double pendente = pedidoPgtoDAO.getTotalTitulosAbertosPorCliente(clienteVO);
		return clienteVO.getLimiteCredito() - pendente;
	}
	/**
	 * Retorna o limite de crédito disponivel, menos o valor do pedido passado por parametro.
	 * Utilizado na edição dos pedidos para validar o limite de crédito
	 * @param valorVenda
	 * @param clienteVO
	 * @return
	 */
	public double limiteDisponivelMenosPedidoAtual(ClienteVO clienteVO, PedidoVO pedidoVO){
		PedidoPgtoDAO pedidoPgtoDAO = new PedidoPgtoDAO(context);
		double pendente = pedidoPgtoDAO.getTotalTitulosAbertosPorCliente(clienteVO);
		double valorPedidoAtual = pedidoPgtoDAO.getTotalTitulosAbertosPorPedido(pedidoVO);
		return Util.arredondaDouble((clienteVO.getLimiteCredito() +valorPedidoAtual) -  pendente );
	}
	
	public double totalTitulosVencidos(final ClienteVO clienteVO){
		PedidoPgtoDAO pedidoPgtoDAO = new PedidoPgtoDAO(context);
		return pedidoPgtoDAO.getTotalTitulosVencidosPorCliente(clienteVO);
	}
	
	
	
}
