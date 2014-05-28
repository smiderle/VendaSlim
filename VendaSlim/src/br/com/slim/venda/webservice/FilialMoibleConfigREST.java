package br.com.slim.venda.webservice;


import java.util.ArrayList;
import java.util.List;

import br.com.slim.venda.integration.domain.Endpoints;
import br.com.slim.venda.integration.domain.FilialMobileConfigIntegration;
import br.com.slim.venda.usuario.UsuarioVO;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class FilialMoibleConfigREST {
	
	public List<FilialMobileConfigIntegration> getAllBranchOfficeByChangeDate(Long dtHrAlteracao) throws Exception {
		
		ServiceRest services = new ServiceRest();
		
		services.setResource(Endpoints.RESOURCE_FILIAL);
		services.setMethod(Endpoints.METODO_FILIAL_GETALLBRANCHOFFICECONFIG);
		services.setParameter("changeDate", dtHrAlteracao);
		services.setParameter("idFilial", UsuarioVO.idFilial);
		services.setParameter("idEmpresa", UsuarioVO.idEmpresa);
		
		
		String[] resposta = services.get();

		if (resposta[0].equals("200")) {
			Gson gson = new Gson();
			ArrayList<FilialMobileConfigIntegration> listaFilialMobileConfig = new ArrayList<FilialMobileConfigIntegration>();
			JsonParser parser = new JsonParser();
			JsonArray array = parser.parse(resposta[1]).getAsJsonArray();
			
			for (int i = 0; i < array.size(); i++) {
				listaFilialMobileConfig.add(gson.fromJson(array.get(i), FilialMobileConfigIntegration.class));
			}
			return listaFilialMobileConfig;
		} else {
			throw new Exception(resposta[1]);
		}
	}
}