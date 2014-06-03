package br.com.slim.venda.webservice;


import java.lang.reflect.Type;
import java.util.List;

import br.com.slim.venda.integration.domain.Endpoints;
import br.com.slim.venda.integration.domain.GrupoProdutoIntegration;
import br.com.slim.venda.item.ItemVO;
import br.com.slim.venda.support.ApiResponse;
import br.com.slim.venda.support.ServiceResponse;
import br.com.slim.venda.usuario.UsuarioVO;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ProdutoREST {
	
	public List<GrupoProdutoIntegration> getAllProductGroupByChangeDate(Long dtHrAlteracao) throws Exception {
		
		ServiceRest services = new ServiceRest();
		
		services.setResource(Endpoints.RESOURCE_PRODUTO);
		services.setMethod(Endpoints.METODO_PRODUTO_GETALLPRODUCTGROUP);
		services.setParameter("changeDate", dtHrAlteracao);
		services.setParameter("idEmpresa", UsuarioVO.idEmpresa);
		services.setParameter("idFilial", UsuarioVO.idFilial);
		
		String[] resposta = services.get();

		if (resposta[0].equals("200")) {
			Type serviceType = new TypeToken<ApiResponse<ServiceResponse<List<GrupoProdutoIntegration>>>>() {}.getType();			
			ApiResponse<ServiceResponse<List<GrupoProdutoIntegration>>> apiResponse = new Gson().fromJson(resposta[1], serviceType);
			
			if(apiResponse.getCode().equals(ApiResponse.CODE_SUCESS)){
				return apiResponse.getResult().getValue();
			} else {
				throw new Exception(apiResponse.getMessage());
			}
		} else {
			throw new Exception(resposta[1]);
		}
	}
	
	
	public List<ItemVO> getAllProductByChangeDate(Long dtHrAlteracao) throws Exception {
		
		ServiceRest services = new ServiceRest();
		
		services.setResource(Endpoints.RESOURCE_PRODUTO);
		services.setMethod(Endpoints.METODO_PRODUTO_GETALLPRODUCT);
		services.setParameter("changeDate", dtHrAlteracao);
		services.setParameter("idEmpresa", UsuarioVO.idEmpresa);
		services.setParameter("idFilial", UsuarioVO.idFilial);
		
		String[] resposta = services.get();

		if (resposta[0].equals("200")) {
			Type serviceType = new TypeToken<ApiResponse<ServiceResponse<List<ItemVO>>>>() {}.getType();			
			ApiResponse<ServiceResponse<List<ItemVO>>> apiResponse = new Gson().fromJson(resposta[1], serviceType);
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