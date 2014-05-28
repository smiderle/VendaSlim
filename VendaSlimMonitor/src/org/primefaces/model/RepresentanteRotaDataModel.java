package org.primefaces.model;

import java.util.List;

import javax.faces.model.ListDataModel;

import br.com.vendaslim.domain.representante.RepresentanteRota;

public class RepresentanteRotaDataModel extends ListDataModel<RepresentanteRota> implements SelectableDataModel<RepresentanteRota>{

	public RepresentanteRotaDataModel(List<RepresentanteRota> representantesRota) {
		super(representantesRota);
	}
	
	public RepresentanteRotaDataModel() {
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public RepresentanteRota getRowData(String rowKey) {
		List<RepresentanteRota> representantesRotas = (List<RepresentanteRota>) getWrappedData();
        for(RepresentanteRota representanteRota : representantesRotas) {  
            if(representanteRota.getDtHrRota().toString().equals(rowKey))  
                return representanteRota;
        }
		return null;
	}

	@Override
	public Object getRowKey(RepresentanteRota representanteRota) {
		return representanteRota.getDtHrRota();
	}
}
