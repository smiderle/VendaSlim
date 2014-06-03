package br.com.slim.venda.webservice;

import java.lang.reflect.Type;
import java.util.List;

import br.com.slim.venda.integration.domain.Endpoints;
import br.com.slim.venda.mensagem.MensagemVO;
import br.com.slim.venda.support.ApiResponse;
import br.com.slim.venda.support.ServiceResponse;
import br.com.slim.venda.usuario.UsuarioVO;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MensagemREST {
	
	public List<MensagemVO> getAllMessageByChangeDate(Long dtHrAlteracao) throws Exception {
		
		ServiceRest services = new ServiceRest();
		
		services.setResource(Endpoints.RESOURCE_MENSAGEM);
		services.setMethod(Endpoints.METODO_MENSAGEM_GETALLMESSAGE);
		services.setParameter("changeDate", dtHrAlteracao);
		services.setParameter("idEmpresa", UsuarioVO.idEmpresa);
		services.setParameter("idRepresentante", UsuarioVO.idUsuairo);
		
		String[] resposta = services.get();

		if (resposta[0].equals("200")) {
			Type serviceType = new TypeToken<ApiResponse<ServiceResponse<List<MensagemVO>>>>() {}.getType();			
			ApiResponse<ServiceResponse<List<MensagemVO>>> apiResponse = new Gson().fromJson(resposta[1], serviceType);
			
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
