package org.primefaces.model;

import java.util.List;

import javax.faces.model.ListDataModel;

import br.com.vendaslim.domain.cliente.Cliente;

public class ClienteDataModel extends ListDataModel<Cliente> implements SelectableDataModel<Cliente>{

	public ClienteDataModel(List<Cliente> clientes) {	
		super(clientes);
	}

	public ClienteDataModel() {	
	}

	@SuppressWarnings("unchecked")
	@Override
	public Cliente getRowData(String rowKey) {
		List<Cliente> clientes = (List<Cliente>) getWrappedData();
		for(Cliente cliente : clientes) {  
			if(cliente.getIdCliente().toString().equals(rowKey))  
				return cliente;
		}
		return null;
	}

	@Override
	public Object getRowKey(Cliente cliente) {
		return cliente.getIdCliente();
	}
}
