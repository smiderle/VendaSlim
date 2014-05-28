package br.com.slim.venda.representante;

import java.util.List;

import android.content.Context;

public class RepresentanteController {

	private Context context;
	
	public RepresentanteController(Context context) {
		this.context = context;
	}	
	
	public void salvarRepresentanteFilial(List<RepresentanteFilialVO> lsRepresentanteIntegration){
		RepresentanteFilialDAO representanteFilialDAO = new RepresentanteFilialDAO(this.context);
		for (RepresentanteFilialVO representanteFilial : lsRepresentanteIntegration) {
			representanteFilialDAO.insertOrUpdate(representanteFilial);
		}
		representanteFilialDAO.close();
	}
	
	
	public void salvarRepresentante(List<RepresentanteVO> lsRepresentante){
		RepresentanteDAO representanteDAO = new RepresentanteDAO(this.context);
		for (RepresentanteVO representante : lsRepresentante) {
			representanteDAO.insertOrUpdate(representante);
		}
		representanteDAO.close();
	}
	
	
	public void salvarRepresentanteTabPreco(List<RepresentanteTabPrecoVO> lsRepresentanteTabPreco){
		RepresentanteTabPrecoDAO representanteTabPrecoDAO = new RepresentanteTabPrecoDAO(this.context);
		for (RepresentanteTabPrecoVO representanteTabPreco : lsRepresentanteTabPreco) {
			representanteTabPrecoDAO.insertOrUpdate(representanteTabPreco);
		}
		representanteTabPrecoDAO.close();
	}
	
	
	public void salvarRepresentanteParcelamento(List<RepresentanteParcelamentoVO> lsRepresentanteParcelamento){
		RepresentanteParcelamentoDAO representanteParcelamentoDAO = new RepresentanteParcelamentoDAO(this.context);
		for (RepresentanteParcelamentoVO representanteParcelamento : lsRepresentanteParcelamento) {
			representanteParcelamentoDAO.insertOrUpdate(representanteParcelamento);
		}
		representanteParcelamentoDAO.close();
	}
}