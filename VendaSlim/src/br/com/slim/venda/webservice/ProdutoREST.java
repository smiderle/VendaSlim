package br.com.slim.venda.webservice;


import java.util.ArrayList;
import java.util.List;

import br.com.slim.venda.integration.domain.Endpoints;
import br.com.slim.venda.integration.domain.FilialIntegration;
import br.com.slim.venda.integration.domain.GrupoProdutoIntegration;
import br.com.slim.venda.item.ItemVO;
import br.com.slim.venda.usuario.UsuarioVO;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

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
			Gson gson = new Gson();
			ArrayList<GrupoProdutoIntegration> lsGrupoProduto = new ArrayList<GrupoProdutoIntegration>();
			JsonParser parser = new JsonParser();
			JsonArray array = parser.parse(resposta[1]).getAsJsonArray();
			
			for (int i = 0; i < array.size(); i++) {
				lsGrupoProduto.add(gson.fromJson(array.get(i), GrupoProdutoIntegration.class));
			}
			return lsGrupoProduto;
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
			Gson gson = new Gson();
			ArrayList<ItemVO> lsItem = new ArrayList<ItemVO>();
			JsonParser parser = new JsonParser();
			JsonArray array = parser.parse(resposta[1]).getAsJsonArray();
			
			for (int i = 0; i < array.size(); i++) {
				lsItem.add(gson.fromJson(array.get(i), ItemVO.class));
			}
			return lsItem;
		} else {
			throw new Exception(resposta[1]);
		}
	}
}