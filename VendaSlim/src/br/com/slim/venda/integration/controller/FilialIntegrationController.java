package br.com.slim.venda.integration.controller;

import java.util.List;

import android.content.Context;
import br.com.slim.venda.integration.domain.FilialIntegration;
import br.com.slim.venda.integration.domain.FilialMobileConfigIntegration;
import br.com.slim.venda.integration.repository.FilialIntegrationDAO;
import br.com.slim.venda.integration.repository.FilialMobileConfigIntegrationDAO;

public class FilialIntegrationController {
	
	private Context context;
	
	public FilialIntegrationController(Context context) {
		this.context = context;
	}
	
	public void salvar(List<FilialIntegration> filiais){
		FilialIntegrationDAO filialIntegrationDAO = new FilialIntegrationDAO(this.context);
		for (FilialIntegration clienteIntegration : filiais) {
			filialIntegrationDAO.insertOrUpdate(clienteIntegration);			
		}
		filialIntegrationDAO.close();
	}
	
	
	public void salvarConfig(List<FilialMobileConfigIntegration> filiaisConfig){
		FilialMobileConfigIntegrationDAO filialMobileIntegrationDAO = new FilialMobileConfigIntegrationDAO(this.context);
		for (FilialMobileConfigIntegration filialMobileConfigIntegration : filiaisConfig) {
			filialMobileIntegrationDAO.insertOrUpdate(filialMobileConfigIntegration);			
		}
		filialMobileIntegrationDAO.close();
	}
}