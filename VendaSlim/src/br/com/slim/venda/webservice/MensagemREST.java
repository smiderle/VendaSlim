package br.com.slim.venda.webservice;

import java.util.ArrayList;
import java.util.List;

import br.com.slim.venda.integration.domain.Endpoints;
import br.com.slim.venda.mensagem.MensagemVO;
import br.com.slim.venda.usuario.UsuarioVO;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

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
			Gson gson = new Gson();
			ArrayList<MensagemVO> listaMensagens = new ArrayList<MensagemVO>();
			JsonParser parser = new JsonParser();
			JsonArray array = parser.parse(resposta[1]).getAsJsonArray();
			
			for (int i = 0; i < array.size(); i++) {
				listaMensagens.add(gson.fromJson(array.get(i), MensagemVO.class));
			}
			return listaMensagens;
		} else {
			throw new Exception(resposta[1]);
		}
	}
}
