package br.com.slim.venda.webservice;

import java.util.ArrayList;
import java.util.List;

import br.com.slim.venda.integration.domain.Endpoints;
import br.com.slim.venda.usuario.UsuarioVO;
import br.com.slim.venda.websinc.Websinc;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

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
			Gson gson = new Gson();
			List<Websinc> listaParcelamentos = new ArrayList<Websinc>();
			JsonParser parser = new JsonParser();
			JsonArray array = parser.parse(resposta[1]).getAsJsonArray();
			
			for (int i = 0; i < array.size(); i++) {
				listaParcelamentos.add(gson.fromJson(array.get(i), Websinc.class));
			}
			return listaParcelamentos;
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
