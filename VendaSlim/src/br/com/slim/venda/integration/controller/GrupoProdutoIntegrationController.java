package br.com.slim.venda.integration.controller;

import java.util.List;

import android.content.Context;
import br.com.slim.venda.integration.domain.GrupoProdutoIntegration;
import br.com.slim.venda.integration.repository.GrupoProdutoIntegrationDAO;

public class GrupoProdutoIntegrationController {
	
	private Context context;
	
	public GrupoProdutoIntegrationController(Context context) {
		this.context = context;
	}
	
	
	public void salvar(List<GrupoProdutoIntegration> lsGrupoProdutoIntegration){
		GrupoProdutoIntegrationDAO clienteIntegrationDAO = new GrupoProdutoIntegrationDAO(this.context);
		for (GrupoProdutoIntegration grupoProdutoIntegration : lsGrupoProdutoIntegration) {
			clienteIntegrationDAO.insertOrUpdate(grupoProdutoIntegration);
		}
		clienteIntegrationDAO.close();
	}	
}