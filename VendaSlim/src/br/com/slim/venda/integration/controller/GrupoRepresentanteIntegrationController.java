package br.com.slim.venda.integration.controller;

import java.util.List;

import android.content.Context;
import br.com.slim.venda.integration.domain.GrupoRepresentanteIntegration;
import br.com.slim.venda.integration.repository.GrupoRepresentanteIntegrationDAO;

public class GrupoRepresentanteIntegrationController {
	
	private Context context;
	
	public GrupoRepresentanteIntegrationController(Context context) {
		this.context = context;
	}
	
	
	public void salvar(List<GrupoRepresentanteIntegration> lsGrupoRepresentanteIntegration){
		GrupoRepresentanteIntegrationDAO grupoRepresentanteIntegrationDAO = new GrupoRepresentanteIntegrationDAO(this.context);
		for (GrupoRepresentanteIntegration grupoRepresentanteIntegration : lsGrupoRepresentanteIntegration) {
			grupoRepresentanteIntegrationDAO.insertOrUpdate(grupoRepresentanteIntegration);
		}
		grupoRepresentanteIntegrationDAO.close();
	}	
}