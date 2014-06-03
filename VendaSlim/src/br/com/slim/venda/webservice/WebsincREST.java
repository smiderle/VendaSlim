package br.com.slim.venda.webservice;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import br.com.slim.venda.integration.domain.Endpoints;
import br.com.slim.venda.support.ApiResponse;
import br.com.slim.venda.support.ServiceResponse;
import br.com.slim.venda.tabelaPreco.TabelaPrecoVO;
import br.com.slim.venda.usuario.UsuarioVO;
import br.com.slim.venda.websinc.Websinc;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

public class WebsincREST {
	
	
	
	public List<Websinc> sincroniza() throws Exception{
		ServiceRest services = new ServiceRest();
		
		services.setResource(Endpoints.RESOURCE_WEBSINC);
		services.setMethod(Endpoints.METODO_WEBSINC_GETDATA);
		services.setParameter("idEmpresa", UsuarioVO.idEmpresa);
		services.setParameter("idFilial", UsuarioVO.idFilial);
		services.setParameter("idRepresentante", UsuarioVO.idUsuairo);
		
		
		String[] resposta = services.get();
		if(resposta[0].equals("200")){
			Type serviceType = new TypeToken<ApiResponse<ServiceResponse<List<Websinc>>>>() {}.getType();			
			ApiResponse<ServiceResponse<List<Websinc>>> apiResponse = new Gson().fromJson(resposta[1], serviceType);
			if(apiResponse.getCode().equals(ApiResponse.CODE_SUCESS)){
				return apiResponse.getResult().getValue();
			} else {
				throw new Exception(apiResponse.getMessage());
			}
		} else {
			throw new Exception(resposta[1]);
		}
	}
	
	public void deleteWebsinc(String sequencias) throws Exception{
		ServiceRest services = new ServiceRest();
		services.setResource(Endpoints.RESOURCE_WEBSINC);
		services.setMethod(Endpoints.METODO_WEBSINC_DELETEWEBSINC);
		services.setParameter("sequencias", sequencias);
		services.get();
	}
	

}
