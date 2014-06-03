package br.com.slim.venda.webservice;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import br.com.slim.venda.integration.domain.Endpoints;
import br.com.slim.venda.representante.RepresentanteVO;
import br.com.slim.venda.support.ApiResponse;
import br.com.slim.venda.support.ServiceResponse;
import br.com.slim.venda.tabelaPreco.TabelaPrecoVO;
import br.com.slim.venda.usuario.UsuarioVO;
import br.com.slim.venda.versao.VersaoPdaVO;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

public class VersaoREST {
	
	
	
	/**
	 * 
	 * @param clientes
	 * @return clientes cujo o id salvo no banco foi diferente do criado no mobile
	 */
	public void addVersionPda(VersaoPdaVO versaoPda){
		Gson gson = new Gson();
		String versaoJson = gson.toJson(versaoPda);
		new ServiceRest().post(Endpoints.VERSAO_ADDVERSION, versaoJson);	
	}	
	
	public String getExpirationDate(String serial) throws Exception{
		ServiceRest services = new ServiceRest();
		
		services.setResource(Endpoints.RESOURCE_VERSION);
		services.setMethod(Endpoints.METODO_VERSAO_GETEXPIRATIONDATE);
		services.setParameter("serial", serial);
		
		String[] resposta = services.get();
		if(resposta[0].equals("200")){
			Type serviceType = new TypeToken<ApiResponse<ServiceResponse<String[]>>>() {}.getType();			
			ApiResponse<ServiceResponse<String[]>> apiResponse = new Gson().fromJson(resposta[1], serviceType);
			if(apiResponse.getCode().equals(ApiResponse.CODE_SUCESS)){
				return apiResponse.getResult().getValue()[0];
			} else {
				throw new Exception(apiResponse.getMessage());
			}			
		} else {
			throw new Exception(resposta[1]);
		}
		
	}
	
	
	public VersaoPdaVO getVersaoPda(String serial) throws Exception {
		
		ServiceRest services = new ServiceRest();
		
		services.setResource(Endpoints.RESOURCE_VERSION);
		services.setMethod(Endpoints.METODO_VERSAO_GETVERSAOPDA);
		services.setParameter("serial", serial);		
		
		String[] resposta = services.get();

		if (resposta[0].equals("200")) {
			Gson gson = new Gson();
			JsonParser parser = new JsonParser();
			JsonObject obj = parser.parse(resposta[1]).getAsJsonObject();
						
			return gson.fromJson(obj, VersaoPdaVO.class);
			
		} else {
			throw new Exception(resposta[1]);
		}
	}	

}
