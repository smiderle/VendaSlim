package org.primefaces.model;

import java.util.List;

import javax.faces.model.ListDataModel;

import br.com.vendaslim.domain.produto.GrupoProduto;

public class GrupoProdutoDataModel extends ListDataModel<GrupoProduto> implements SelectableDataModel<GrupoProduto>{

	public GrupoProdutoDataModel(List<GrupoProduto> gruposProdutos) {
		super(gruposProdutos);
	}
	
	public GrupoProdutoDataModel() {
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public GrupoProduto getRowData(String rowKey) {
		List<GrupoProduto> gruposProdutos = (List<GrupoProduto>) getWrappedData();
        for(GrupoProduto grupoProduto : gruposProdutos) {  
            if(grupoProduto.getIdGrupo().toString().equals(rowKey))  
                return grupoProduto;
        }
		return null;
	}

	@Override
	public Object getRowKey(GrupoProduto grupoProduto) {
		return grupoProduto.getIdGrupo();
	}
}
