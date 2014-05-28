package br.com.slim.venda.parcelamento;

import java.util.List;

import android.content.Context;

public class ParcelamentoController {
	
	private Context context;
	
	public ParcelamentoController(Context context) {
		this.context = context;
	}
	
	
	public void salvar(List<ParcelamentoVO> lsParcelas){
		ParcelamentoDAO parcelaDAO = new ParcelamentoDAO(this.context);
		for (ParcelamentoVO mensagemVO : lsParcelas) {
			 parcelaDAO.insertOrUpdate(mensagemVO);
		}
		parcelaDAO.close();
	}	
}