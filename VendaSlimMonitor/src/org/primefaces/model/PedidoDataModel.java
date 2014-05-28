package org.primefaces.model;

import java.util.List;

import javax.faces.model.ListDataModel;

import br.com.vendaslim.domain.pedido.Pedido;

public class PedidoDataModel extends ListDataModel<Pedido> implements SelectableDataModel<Pedido>{

	public PedidoDataModel(List<Pedido> pedidos) {	
		super(pedidos);
	}
	
	public PedidoDataModel() {	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Pedido getRowData(String rowKey) {
		List<Pedido> pedidos = (List<Pedido>) getWrappedData();
        for(Pedido pedido : pedidos) {  
            if(pedido.getIdPedido().toString().equals(rowKey))  
                return pedido;
        }
		return null;
	}

	@Override
	public Object getRowKey(Pedido pedido) {
		return pedido.getIdPedido();
	}
}
