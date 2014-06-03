package br.com.slim.venda.webservice;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import br.com.slim.venda.integration.domain.Endpoints;
import br.com.slim.venda.representante.RepresentanteVO;
import br.com.slim.venda.support.ApiResponse;
import br.com.slim.venda.support.ServiceResponse;
import br.com.slim.venda.usuario.UsuarioVO;

public class SincronizacaoREST {
	
	public Long getTimeServer() throws Exception{
		
		ServiceRest services = new ServiceRest();
		services.setResource(Endpoints.RESOURCE_SINCRONIZACAO);				
		services.setMethod(Endpoints.METODO_SINCRONIZACAO_GETTIMESERVER);
		services.setParameter("idRepresentante", UsuarioVO.idUsuairo);
		services.setParameter("idEmpresa", UsuarioVO.idEmpresa);
		services.setParameter("idFilial", UsuarioVO.idFilial);
				
		String[] resposta = services.get();
		
		if (resposta[0].equals("200")) {			
			Type serviceType = new TypeToken<ApiResponse<ServiceResponse<String>>>() {}.getType();			
			ApiResponse<ServiceResponse<String>> apiResponse = new Gson().fromJson(resposta[1], serviceType);
			
			if(apiResponse.getCode().equals(ApiResponse.CODE_SUCESS)){
				return Long.parseLong(apiResponse.getResult().getValue());
			} else {
				throw new Exception(apiResponse.getMessage());
			}
		} else {
			throw new Exception(resposta[1]);
		}
	}
}
