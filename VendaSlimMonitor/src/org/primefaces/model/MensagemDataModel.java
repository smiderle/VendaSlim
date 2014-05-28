package org.primefaces.model;

import java.util.List;

import javax.faces.model.ListDataModel;

import br.com.vendaslim.domain.cadastro.Mensagem;

public class MensagemDataModel extends ListDataModel<Mensagem> implements SelectableDataModel<Mensagem>{

	public MensagemDataModel(List<Mensagem> mensagens) {	
		super(mensagens);
	}

	public MensagemDataModel() {	
	}

	@SuppressWarnings("unchecked")
	@Override
	public Mensagem getRowData(String rowKey) {
		List<Mensagem> mensagens = (List<Mensagem>) getWrappedData();
		for(Mensagem mensagem : mensagens) {  
			if(mensagem.getIdMensagem().toString().equals(rowKey))  
				return mensagem;
		}
		return null;
	}

	@Override
	public Object getRowKey(Mensagem mensagem) {
		return mensagem.getIdMensagem();
	}
}
