package br.com.slim.venda.webservice;


import java.lang.reflect.Type;
import java.util.List;

import br.com.slim.venda.integration.domain.Endpoints;
import br.com.slim.venda.integration.domain.FilialMobileConfigIntegration;
import br.com.slim.venda.support.ApiResponse;
import br.com.slim.venda.support.ServiceResponse;
import br.com.slim.venda.usuario.UsuarioVO;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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

			Type serviceType = new TypeToken<ApiResponse<ServiceResponse<List<FilialMobileConfigIntegration>>>>() {}.getType();			
			ApiResponse<ServiceResponse<List<FilialMobileConfigIntegration>>> apiResponse = new Gson().fromJson(resposta[1], serviceType);
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