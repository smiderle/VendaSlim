package br.com.slim.venda.webservice;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import br.com.slim.venda.integration.domain.Endpoints;
import br.com.slim.venda.integration.domain.PedidoIntegration;
import br.com.slim.venda.support.ApiResponse;
import br.com.slim.venda.support.ServiceResponse;
import br.com.slim.venda.websinc.Websinc;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

public class PedidoREST {
	
	/**
	 * 
	 * @param pedidoss
	 * @return pedidos cujo o id salvo no banco foi diferente do criado no mobile
	 * @throws Exception 
	 */
	public List<PedidoIntegration> addOrder(List<PedidoIntegration> pedidos) throws Exception{
		Gson gson = new Gson();
		String pedidosJson = gson.toJson(pedidos);
		String[] resposta = new ServiceRest().post(Endpoints.PEDIDO_ADDORDERS, pedidosJson);
		
		if(resposta[0].equals("200")){
			Type serviceType = new TypeToken<ApiResponse<ServiceResponse<List<PedidoIntegration>>>>() {}.getType();			
			ApiResponse<ServiceResponse<List<PedidoIntegration>>> apiResponse = new Gson().fromJson(resposta[1], serviceType);
			if(apiResponse.getCode().equals(ApiResponse.CODE_SUCESS)){
				return apiResponse.getResult().getValue();
			} else {
				throw new Exception(apiResponse.getMessage());
			}		
		} else {
			Log.e("PEDIDO", resposta[1]);
			throw new Exception(resposta[1]);
		}		
	}

}
