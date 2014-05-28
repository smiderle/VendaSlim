package br.com.slim.venda.integration.controller;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import br.com.slim.venda.integration.domain.PedidoIntegration;
import br.com.slim.venda.integration.repository.PedidoIntegrationDAO;

public class PedidoIntegrationController {
	
private Context context;
	
	public PedidoIntegrationController(Context context) {
		this.context = context;
	}
	
	
	public ArrayList<PedidoIntegration> getAllNaoSincronizado(){
		PedidoIntegrationDAO dao = new PedidoIntegrationDAO(context);
		ArrayList<PedidoIntegration> lsPedido =  dao.getAllNaoSincronizado();
		return lsPedido;
	}
	
	public void update(List<PedidoIntegration> pedidos){
		PedidoIntegrationDAO dao = new PedidoIntegrationDAO(context);
		for (PedidoIntegration pedidoIntegration : pedidos) {
			dao.updatePedidoSincronizado(pedidoIntegration);
			if(pedidoIntegration.getIdPedido() != pedidoIntegration.getIdPedidoMobile()){
				dao.updatePedidoItemNewIDPedido(pedidoIntegration);
				dao.updatePedidoPgtoNewIDPedido(pedidoIntegration);
			}
		}
		dao.close();
	}

}
