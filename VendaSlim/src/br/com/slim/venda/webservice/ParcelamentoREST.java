package br.com.slim.venda.webservice;


import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import br.com.slim.venda.integration.domain.ClienteIntegration;
import br.com.slim.venda.integration.domain.Endpoints;
import br.com.slim.venda.parcelamento.ParcelamentoVO;
import br.com.slim.venda.usuario.UsuarioVO;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class ParcelamentoREST {
	
	public List<ParcelamentoVO> getAllCustomerByChangeDate(Long dtHrAlteracao) throws Exception {
		
		ServiceRest services = new ServiceRest();
		
		services.setResource(Endpoints.RESOURCE_PARCELAMENTO);
		services.setMethod(Endpoints.METODO_PARCELAMENTO_GETALLSUBDIVISION);
		services.setParameter("changeDate", dtHrAlteracao);
		services.setParameter("idEmpresa", UsuarioVO.idEmpresa);
		services.setParameter("idFilial", UsuarioVO.idFilial);
		
		String[] resposta = services.get();

		if (resposta[0].equals("200")) {
			Gson gson = new Gson();
			ArrayList<ParcelamentoVO> listaParcelamentos = new ArrayList<ParcelamentoVO>();
			JsonParser parser = new JsonParser();
			JsonArray array = parser.parse(resposta[1]).getAsJsonArray();
			
			for (int i = 0; i < array.size(); i++) {
				listaParcelamentos.add(gson.fromJson(array.get(i), ParcelamentoVO.class));
			}
			return listaParcelamentos;
		} else {
			throw new Exception(resposta[1]);
		}
	}
}
