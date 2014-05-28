package br.com.slim.venda.tabelaPreco;

import java.util.List;

import android.content.Context;

public class TabelaPrecoController {
	
	private Context context;
	
	public TabelaPrecoController(Context context) {
		this.context = context;
	}
	
	
	public void salvar(List<TabelaPrecoVO> lsTabela){
		TabelaPrecoDAO tabelaDAO = new TabelaPrecoDAO(this.context);
		for (TabelaPrecoVO mensagemVO : lsTabela) {
			 tabelaDAO.insertOrUpdate(mensagemVO);
		}
		tabelaDAO.close();
	}
}