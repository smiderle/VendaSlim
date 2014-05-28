package br.com.slim.venda.webservice;


import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import br.com.slim.venda.integration.domain.Endpoints;
import br.com.slim.venda.integration.domain.PedidoIntegration;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class PedidoREST {
	
	/**
	 * 
	 * @param pedidoss
	 * @return pedidos cujo o id salvo no banco foi diferente do criado no mobile
	 * @throws Exception 
	 */
	public ArrayList<PedidoIntegration> addOrder(List<PedidoIntegration> pedidos) throws Exception{
		Gson gson = new Gson();
		String pedidosJson = gson.toJson(pedidos);
		String[] resposta = new ServiceRest().post(Endpoints.PEDIDO_ADDORDERS, pedidosJson);
		ArrayList<PedidoIntegration> listaPedidoRetorno = new ArrayList<PedidoIntegration>();
		if(resposta[0].equals("200")){
			if(resposta[1] != null && !resposta[1].trim().equals("")){
				JsonParser parser = new JsonParser();
				JsonArray array = parser.parse(resposta[1]).getAsJsonArray();
				for (int i = 0; i < array.size(); i++) {
					listaPedidoRetorno.add(gson.fromJson(array.get(i), PedidoIntegration.class));
				}
			}
		} else {
			Log.e("PEDIDO", resposta[1]);
			throw new Exception(resposta[1]);
		}
		
		return listaPedidoRetorno;
	}

}
