package br.com.vendaslim.ws.controller;

import java.util.ArrayList;

import br.com.vendaslim.ws.conexao.DAOFactory;
import br.com.vendaslim.ws.domain.ClienteIntegration;
import br.com.vendaslim.ws.domain.PedidoIntegration;
import br.com.vendaslim.ws.domain.PedidoItemIntegration;
import br.com.vendaslim.ws.infra.PedidoHibernate;

public class PedidoController {
	
	private PedidoHibernate pedidoHibernate;
	
	public PedidoController() {
		pedidoHibernate = DAOFactory.criaPedidoRepository();
	}
	
	public ArrayList<PedidoIntegration> save(ArrayList<PedidoIntegration> lsPedidoIntegrations){
		ArrayList<PedidoIntegration> newLsPedidoIntegrations = new ArrayList<PedidoIntegration>();
		if(lsPedidoIntegrations.size() > 0){
			PedidoIntegration pedido = lsPedidoIntegrations.get(0);
			Integer idEmpresa = pedido.getIdEmpresa();
			Integer idFilial = pedido.getIdFilial();		
			Integer idPedido =buscaProximoIdPorFilial(idEmpresa, idFilial);
					
			for(PedidoIntegration pedidoIntegration : lsPedidoIntegrations){				
				PedidoIntegration newPedido = new PedidoIntegration();
				newPedido.setIdEmpresa(pedidoIntegration.getIdEmpresa());
				newPedido.setIdFilial(pedidoIntegration.getIdFilial());
				newPedido.setIdPedido(idPedido);
				newPedido.setIdPedidoMobile(pedidoIntegration.getIdPedido());
				newLsPedidoIntegrations.add(newPedido);
				
				if (pedidoIntegration.getIdPedido() != idPedido) {
					pedidoIntegration.setIdPedido(idPedido);
					for (PedidoItemIntegration pedidoItemIntegration : pedidoIntegration.getLsPedidoItemIntegration()) {
						pedidoItemIntegration.setIdPedido(idPedido);
					}
				}
				pedidoHibernate.save(pedidoIntegration);
				idPedido ++;
			}
			//TODO Alterar também o id pedido do parcelamento.
		}
		
		return newLsPedidoIntegrations;
	}	
	
	public Integer buscaProximoIdPorFilial(Integer idEmpresa, Integer idFilial){
		return this.pedidoHibernate.buscaMariorIdPorFilial(idEmpresa,idFilial) + 1;
	}

}
