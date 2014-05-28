package br.com.slim.venda.webservice;


import java.util.ArrayList;
import java.util.List;

import br.com.slim.venda.integration.domain.Endpoints;
import br.com.slim.venda.integration.domain.FilialIntegration;
import br.com.slim.venda.usuario.UsuarioVO;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class FilialREST {
	
	public List<FilialIntegration> getAllBranchOfficeByChangeDate(Long dtHrAlteracao) throws Exception {
		
		ServiceRest services = new ServiceRest();
		
		services.setResource(Endpoints.RESOURCE_FILIAL);
		services.setMethod(Endpoints.METODO_FILIAL_GETALLBRANCHOFFICE);
		services.setParameter("changeDate", dtHrAlteracao);
		services.setParameter("idEmpresa", UsuarioVO.idEmpresa);
		
		String[] resposta = services.get();

		if (resposta[0].equals("200")) {
			Gson gson = new Gson();
			ArrayList<FilialIntegration> listaFilial = new ArrayList<FilialIntegration>();
			JsonParser parser = new JsonParser();
			JsonArray array = parser.parse(resposta[1]).getAsJsonArray();
			
			for (int i = 0; i < array.size(); i++) {
				listaFilial.add(gson.fromJson(array.get(i), FilialIntegration.class));
			}
			return listaFilial;
		} else {
			throw new Exception(resposta[1]);
		}
	}
}