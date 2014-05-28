package br.com.slim.venda.mensagem;

import java.util.List;

import android.content.Context;

public class MensagemController {
	
	private Context context;
	
	public MensagemController(Context context) {
		this.context = context;
	}
	
	
	public void salvar(List<MensagemVO> lsMensagens){
		MensagemDAO mensagemDAO = new MensagemDAO(this.context);
		for (MensagemVO mensagemVO : lsMensagens) {
			 mensagemDAO.insertOrUpdate(mensagemVO);
		}
		mensagemDAO.close();
	}	
}