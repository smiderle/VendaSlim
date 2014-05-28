package br.com.slim.venda.representante;

import java.util.List;

import android.content.Context;

public class GrupoRepresentanteParcelamentoController {
	
	private Context context;
	
	public GrupoRepresentanteParcelamentoController(Context context) {
		this.context = context;
	}
	
	
	public void salvar(List<GrupoRepresentanteParcelamentoVO> lsGrupoRepresentanteParcelamento){
		GrupoRepresentanteParcelamentoDAO grupoRepresentanteIntegrationDAO = new GrupoRepresentanteParcelamentoDAO(this.context);
		for (GrupoRepresentanteParcelamentoVO grupoRepresentanteParcelamento : lsGrupoRepresentanteParcelamento) {
			 grupoRepresentanteIntegrationDAO.insertOrUpdate(grupoRepresentanteParcelamento);
		}
		grupoRepresentanteIntegrationDAO.close();
	}	
}