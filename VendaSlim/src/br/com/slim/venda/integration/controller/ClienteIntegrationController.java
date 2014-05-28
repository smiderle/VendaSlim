package br.com.slim.venda.integration.controller;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import br.com.slim.venda.integration.domain.ClienteIntegration;
import br.com.slim.venda.integration.repository.ClienteIntegrationDAO;
import br.com.slim.venda.integration.repository.PedidoIntegrationDAO;

public class ClienteIntegrationController {
	
	private Context context;
	
	public ClienteIntegrationController(Context context) {
		this.context = context;
	}
	
	
	public void salvar(List<ClienteIntegration> clientes){
		ClienteIntegrationDAO clienteIntegrationDAO = new ClienteIntegrationDAO(this.context);
		for (ClienteIntegration clienteIntegration : clientes) {
			clienteIntegrationDAO.insertOrUpdate(clienteIntegration);			
		}
		clienteIntegrationDAO.close();
	}
	
	public void update(List<ClienteIntegration> clientes){
		ClienteIntegrationDAO clienteIntegrationDAO = new ClienteIntegrationDAO(this.context);
		for (ClienteIntegration clienteIntegration : clientes) {
			clienteIntegrationDAO.updateClienteSincronizado(clienteIntegration);
			if(clienteIntegration.getIdCliente() != clienteIntegration.getIdClienteMobile()){
				PedidoIntegrationDAO pedidoDAO = new PedidoIntegrationDAO(this.context);
				pedidoDAO.updateClienteIDPedido(clienteIntegration.getIdClienteMobile(), clienteIntegration.getIdCliente());
			}
		}
		clienteIntegrationDAO.close();
	}
	
	public ArrayList<ClienteIntegration> getAllNaoSincronizado(){
		ClienteIntegrationDAO clienteIntegrationDAO = new ClienteIntegrationDAO(this.context);
		ArrayList<ClienteIntegration> lsCliente = clienteIntegrationDAO.getAllNaoSincronizado();		
		clienteIntegrationDAO.close();
		return lsCliente;
	}
}