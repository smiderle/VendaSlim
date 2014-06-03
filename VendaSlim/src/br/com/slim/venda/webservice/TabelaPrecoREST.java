package br.com.slim.venda.webservice;


import java.lang.reflect.Type;
import java.util.List;

import br.com.slim.venda.integration.domain.Endpoints;
import br.com.slim.venda.support.ApiResponse;
import br.com.slim.venda.support.ServiceResponse;
import br.com.slim.venda.tabelaPreco.TabelaPrecoVO;
import br.com.slim.venda.usuario.UsuarioVO;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class TabelaPrecoREST {
	
	public List<TabelaPrecoVO> getAllPriceTableByChangeDate(Long dtHrAlteracao) throws Exception {
		
		ServiceRest services = new ServiceRest();
		
		services.setResource(Endpoints.RESOURCE_TABELAPRECO);
		services.setMethod(Endpoints.METODO_TABPRECO_GETALLPRICETABLE);
		services.setParameter("changeDate", dtHrAlteracao);
		services.setParameter("idEmpresa", UsuarioVO.idEmpresa);
		services.setParameter("idFilial", UsuarioVO.idFilial);
		
		String[] resposta = services.get();

		if (resposta[0].equals("200")) {
			Type serviceType = new TypeToken<ApiResponse<ServiceResponse<List<TabelaPrecoVO>>>>() {}.getType();			
			ApiResponse<ServiceResponse<List<TabelaPrecoVO>>> apiResponse = new Gson().fromJson(resposta[1], serviceType);
			if(apiResponse.getCode().equals(ApiResponse.CODE_SUCESS)){
				return apiResponse.getResult().getValue();
			} else {
				throw new Exception(apiResponse.getMessage());
			}			
		} else {
			throw new Exception(resposta[1]);
		}
	}	
}