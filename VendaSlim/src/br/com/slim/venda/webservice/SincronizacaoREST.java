package br.com.slim.venda.webservice;

import br.com.slim.venda.integration.domain.Endpoints;
import br.com.slim.venda.usuario.UsuarioVO;

public class SincronizacaoREST {
	
	public Long getTimeServer() throws Exception{
		
		ServiceRest services = new ServiceRest();
		services.setResource(Endpoints.RESOURCE_SINCRONIZACAO);				
		services.setMethod(Endpoints.METODO_SINCRONIZACAO_GETTIMESERVER);
		services.setParameter("idRepresentante", UsuarioVO.idUsuairo);
		services.setParameter("idEmpresa", UsuarioVO.idEmpresa);
		services.setParameter("idFilial", UsuarioVO.idFilial);
				
		String[] resposta = services.get();
		
		if (resposta[0].equals("200")) {
			return Long.parseLong(resposta[1]);
			
		} else {
			throw new Exception(resposta[1]);
		}
	}
}
