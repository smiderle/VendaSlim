package org.primefaces.model;

import java.util.List;

import javax.faces.model.ListDataModel;

import br.com.vendaslim.domain.cadastro.Cidade;

public class CidadeDataModel extends ListDataModel<Cidade> implements SelectableDataModel<Cidade>{

	public CidadeDataModel(List<Cidade> cidades) {
		super(cidades);
	}
	
	public CidadeDataModel() {	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Cidade getRowData(String rowKey) {
		List<Cidade> cidades = (List<Cidade>) getWrappedData();
        for(Cidade cidade : cidades) {  
            if(cidade.getIdCidade().toString().equals(rowKey))  
                return cidade;
        }
		return null;
	}

	@Override
	public Object getRowKey(Cidade cidade) {
		return cidade.getIdCidade();
	}
}
