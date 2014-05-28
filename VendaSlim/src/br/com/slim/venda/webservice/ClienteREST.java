package br.com.slim.venda.webservice;


import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import br.com.slim.venda.integration.domain.ClienteIntegration;
import br.com.slim.venda.integration.domain.Endpoints;
import br.com.slim.venda.usuario.UsuarioVO;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class ClienteREST {
	
	public List<ClienteIntegration> getAllCustomerByChangeDate(Long dtHrAlteracao) throws Exception {
		
		ServiceRest services = new ServiceRest();
		
		services.setResource(Endpoints.RESOURCE_CLIENTE);
		services.setMethod(Endpoints.METODO_CLIENTE_GETALL);
		services.setParameter("changeDate", dtHrAlteracao);
		services.setParameter("idEmpresa", UsuarioVO.idEmpresa);
		services.setParameter("idFilial", UsuarioVO.idFilial);
		
		String[] resposta = services.get();

		if (resposta[0].equals("200")) {
			Gson gson = new Gson();
			ArrayList<ClienteIntegration> listaCliente = new ArrayList<ClienteIntegration>();
			JsonParser parser = new JsonParser();
			JsonArray array = parser.parse(resposta[1]).getAsJsonArray();
			
			for (int i = 0; i < array.size(); i++) {
				listaCliente.add(gson.fromJson(array.get(i), ClienteIntegration.class));
			}
			return listaCliente;
		} else {
			throw new Exception(resposta[1]);
		}
	}
	
	/**
	 * 
	 * @param clientes
	 * @return clientes cujo o id salvo no banco foi diferente do criado no mobile
	 * @throws Exception 
	 */
	public ArrayList<ClienteIntegration> addCustomers(List<ClienteIntegration> clientes) throws Exception{
		Gson gson = new Gson();
		String clienteJson = gson.toJson(clientes);
		String[] resposta = new ServiceRest().post(Endpoints.CLIENTE_ADDCUSTOMERS, clienteJson);
		ArrayList<ClienteIntegration> listaClienteRetorno = new ArrayList<ClienteIntegration>();
		if(resposta[0].equals("200")){
			JsonParser parser = new JsonParser();
			JsonArray array = parser.parse(resposta[1]).getAsJsonArray();
			for (int i = 0; i < array.size(); i++) {
				listaClienteRetorno.add(gson.fromJson(array.get(i), ClienteIntegration.class));
			}
		} else {
			Log.e("CLIENTE", resposta[1]);
			throw new Exception(resposta[1]);
			
		}
		
		return listaClienteRetorno;
	}	
}
