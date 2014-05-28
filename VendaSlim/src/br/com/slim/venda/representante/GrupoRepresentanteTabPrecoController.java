package br.com.slim.venda.representante;

import java.util.List;

import android.content.Context;

public class GrupoRepresentanteTabPrecoController {
	
	private Context context;
	
	public GrupoRepresentanteTabPrecoController(Context context) {
		this.context = context;
	}
	
	
	public void salvar(List<GrupoRepresentanteTabPrecoVO> lsGrupoRepresentanteTabPreco){
		GrupoRepresentanteTabPrecoDAO grupoRepresentanteIntegrationDAO = new GrupoRepresentanteTabPrecoDAO(this.context);
		for (GrupoRepresentanteTabPrecoVO grupoRepresentanteParcelamento : lsGrupoRepresentanteTabPreco) {
			 grupoRepresentanteIntegrationDAO.insertOrUpdate(grupoRepresentanteParcelamento);
		}
		grupoRepresentanteIntegrationDAO.close();
	}	
}