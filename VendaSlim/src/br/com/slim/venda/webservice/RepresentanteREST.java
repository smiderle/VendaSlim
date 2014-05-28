package br.com.slim.venda.webservice;


import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import br.com.slim.venda.device.Device;
import br.com.slim.venda.integration.domain.ClienteIntegration;
import br.com.slim.venda.integration.domain.Endpoints;
import br.com.slim.venda.integration.domain.GrupoRepresentanteIntegration;
import br.com.slim.venda.integration.domain.PedidoIntegration;
import br.com.slim.venda.representante.GrupoRepresentanteParcelamentoVO;
import br.com.slim.venda.representante.GrupoRepresentanteTabPrecoVO;
import br.com.slim.venda.representante.RepresentanteFilialVO;
import br.com.slim.venda.representante.RepresentanteParcelamentoVO;
import br.com.slim.venda.representante.RepresentanteRotaController;
import br.com.slim.venda.representante.RepresentanteRotaVO;
import br.com.slim.venda.representante.RepresentanteTabPrecoVO;
import br.com.slim.venda.representante.RepresentanteVO;
import br.com.slim.venda.representante.RepresentanteVO.Representante;
import br.com.slim.venda.usuario.UsuarioVO;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class RepresentanteREST {
	
	public List<GrupoRepresentanteIntegration> getAllGroupRepresentativeByChangeDate(Long dtHrAlteracao) throws Exception {
		
		ServiceRest services = new ServiceRest();
		
		services.setResource(Endpoints.RESOURCE_REPRESENTANTE);
		services.setMethod(Endpoints.METODO_REPRESENTANTE_GETALLGROUPREPRESENTATIVE);
		services.setParameter("changeDate", dtHrAlteracao);
		services.setParameter("idEmpresa", UsuarioVO.idEmpresa);
		services.setParameter("idFilial", UsuarioVO.idFilial);
		
		String[] resposta = services.get();

		if (resposta[0].equals("200")) {
			Gson gson = new Gson();
			ArrayList<GrupoRepresentanteIntegration> lsGrupoRepresentante = new ArrayList<GrupoRepresentanteIntegration>();
			JsonParser parser = new JsonParser();
			JsonArray array = parser.parse(resposta[1]).getAsJsonArray();
			
			for (int i = 0; i < array.size(); i++) {
				lsGrupoRepresentante.add(gson.fromJson(array.get(i), GrupoRepresentanteIntegration.class));
			}
			return lsGrupoRepresentante;
		} else {
			throw new Exception(resposta[1]);
		}
	}
	

	public List<GrupoRepresentanteParcelamentoVO> getAllGroupRepresentativeSubdivisionByChangeDate(Long dtHrAlteracao) throws Exception {

		ServiceRest services = new ServiceRest();

		services.setResource(Endpoints.RESOURCE_REPRESENTANTE);
		services.setMethod(Endpoints.METODO_REPRESENTANTE_GETALLGROUPREPRESENTATIVESUBDIVISION);
		services.setParameter("changeDate", dtHrAlteracao);
		services.setParameter("idEmpresa", UsuarioVO.idEmpresa);
		services.setParameter("idFilial", UsuarioVO.idFilial);

		String[] resposta = services.get();

		if (resposta[0].equals("200")) {
			Gson gson = new Gson();
			ArrayList<GrupoRepresentanteParcelamentoVO> lsGrupoRepresentanteParcelamento = new ArrayList<GrupoRepresentanteParcelamentoVO>();
			JsonParser parser = new JsonParser();
			JsonArray array = parser.parse(resposta[1]).getAsJsonArray();

			for (int i = 0; i < array.size(); i++) {
				lsGrupoRepresentanteParcelamento.add(gson.fromJson(array.get(i), GrupoRepresentanteParcelamentoVO.class));
			}
			return lsGrupoRepresentanteParcelamento;
		} else {
			throw new Exception(resposta[1]);
		}
	}


	public List<GrupoRepresentanteTabPrecoVO> getAllGroupRepresentativePriceOfTableByChangeDate(Long dtHrAlteracao) throws Exception {

		ServiceRest services = new ServiceRest();

		services.setResource(Endpoints.RESOURCE_REPRESENTANTE);
		services.setMethod(Endpoints.METODO_REPRESENTANTE_GETALLGROUPREPRESENTATIVEPRICEOFTABLE);
		services.setParameter("changeDate", dtHrAlteracao);
		services.setParameter("idEmpresa", UsuarioVO.idEmpresa);
		services.setParameter("idFilial", UsuarioVO.idFilial);

		String[] resposta = services.get();

		if (resposta[0].equals("200")) {
			Gson gson = new Gson();
			ArrayList<GrupoRepresentanteTabPrecoVO> lsGrupoRepresentanteTabPreco = new ArrayList<GrupoRepresentanteTabPrecoVO>();
			JsonParser parser = new JsonParser();
			JsonArray array = parser.parse(resposta[1]).getAsJsonArray();

			for (int i = 0; i < array.size(); i++) {
				lsGrupoRepresentanteTabPreco.add(gson.fromJson(array.get(i), GrupoRepresentanteTabPrecoVO.class));
			}
			return lsGrupoRepresentanteTabPreco;
		} else {
			throw new Exception(resposta[1]);
		}
	}
	
	

	public List<RepresentanteFilialVO> getAllRepresentativeBranchOfficeByChangeDate(Long dtHrAlteracao) throws Exception {
		
		ServiceRest services = new ServiceRest();
		
		services.setResource(Endpoints.RESOURCE_REPRESENTANTE);
		services.setMethod(Endpoints.METODO_REPRESENTANTE_GETALLREPRESENTATIVEBRANCHOFFICE);
		services.setParameter("changeDate", dtHrAlteracao);
		services.setParameter("idEmpresa", UsuarioVO.idEmpresa);
		
		String[] resposta = services.get();

		if (resposta[0].equals("200")) {
			Gson gson = new Gson();
			ArrayList<RepresentanteFilialVO> lsRepresentanteFilial = new ArrayList<RepresentanteFilialVO>();
			JsonParser parser = new JsonParser();
			JsonArray array = parser.parse(resposta[1]).getAsJsonArray();
			
			for (int i = 0; i < array.size(); i++) {
				lsRepresentanteFilial.add(gson.fromJson(array.get(i), RepresentanteFilialVO.class));
			}
			return lsRepresentanteFilial;
		} else {
			throw new Exception(resposta[1]);
		}
	}

	public List<RepresentanteVO> getAllRepresentativeByChangeDate(Long dtHrAlteracao) throws Exception {
		
		ServiceRest services = new ServiceRest();
		
		services.setResource(Endpoints.RESOURCE_REPRESENTANTE);
		services.setMethod(Endpoints.METODO_REPRESENTANTE_GETALLREPRESENTATIVE);
		services.setParameter("changeDate", dtHrAlteracao);
		services.setParameter("idEmpresa", UsuarioVO.idEmpresa);
		
		String[] resposta = services.get();

		if (resposta[0].equals("200")) {
			Gson gson = new Gson();
			ArrayList<RepresentanteVO> lsRepresentante = new ArrayList<RepresentanteVO>();
			JsonParser parser = new JsonParser();
			JsonArray array = parser.parse(resposta[1]).getAsJsonArray();
			
			for (int i = 0; i < array.size(); i++) {
				lsRepresentante.add(gson.fromJson(array.get(i), RepresentanteVO.class));
			}
			return lsRepresentante;
		} else {
			throw new Exception(resposta[1]);
		}
	}

	public List<RepresentanteParcelamentoVO> getAllRepresentativeSubdivisionByChangeDate(Long dtHrAlteracao) throws Exception {

		ServiceRest services = new ServiceRest();

		services.setResource(Endpoints.RESOURCE_REPRESENTANTE);
		services.setMethod(Endpoints.METODO_REPRESENTANTE_GETALLREPRESENTATIVESUBDIVISION);
		services.setParameter("changeDate", dtHrAlteracao);
		services.setParameter("idEmpresa", UsuarioVO.idEmpresa);
		services.setParameter("idFilial", UsuarioVO.idFilial);
		services.setParameter("idRepresentante", UsuarioVO.idUsuairo);

		String[] resposta = services.get();

		if (resposta[0].equals("200")) {
			Gson gson = new Gson();
			ArrayList<RepresentanteParcelamentoVO> lsRepresentanteParcelamento = new ArrayList<RepresentanteParcelamentoVO>();
			JsonParser parser = new JsonParser();
			JsonArray array = parser.parse(resposta[1]).getAsJsonArray();

			for (int i = 0; i < array.size(); i++) {
				lsRepresentanteParcelamento.add(gson.fromJson(array.get(i), RepresentanteParcelamentoVO.class));
			}
			return lsRepresentanteParcelamento;
		} else {
			throw new Exception(resposta[1]);
		}
	}
	
	

	public List<RepresentanteTabPrecoVO> getAllRepresentativePriceOfTableByChangeDate(Long dtHrAlteracao) throws Exception {

		ServiceRest services = new ServiceRest();

		services.setResource(Endpoints.RESOURCE_REPRESENTANTE);
		services.setMethod(Endpoints.METODO_REPRESENTANTE_GETALLREPRESENTATIVEPRICEOFTABLE);
		services.setParameter("changeDate", dtHrAlteracao);
		services.setParameter("idEmpresa", UsuarioVO.idEmpresa);
		services.setParameter("idFilial", UsuarioVO.idFilial);
		services.setParameter("idRepresentante", UsuarioVO.idUsuairo);

		String[] resposta = services.get();

		if (resposta[0].equals("200")) {
			Gson gson = new Gson();
			ArrayList<RepresentanteTabPrecoVO> lsRepresentanteTabPreco = new ArrayList<RepresentanteTabPrecoVO>();
			JsonParser parser = new JsonParser();
			JsonArray array = parser.parse(resposta[1]).getAsJsonArray();

			for (int i = 0; i < array.size(); i++) {
				lsRepresentanteTabPreco.add(gson.fromJson(array.get(i), RepresentanteTabPrecoVO.class));
			}
			return lsRepresentanteTabPreco;
		} else {
			throw new Exception(resposta[1]);
		}
	}
	
	
	
	
	public void addLocationRepresentative(ArrayList<RepresentanteRotaVO> lsRepresentanteRota, Context context) throws Exception {

		Gson gson = new Gson();
		String representanteRotaJson = gson.toJson(lsRepresentanteRota);
		String[] resposta = new ServiceRest().post(Endpoints.REPRESENTANTE_ADDLOCATIONREPRESENTATIVE, representanteRotaJson);
		
		if(resposta[0].equals("200")){
			RepresentanteRotaController controller = new RepresentanteRotaController(context);
			controller.insertOrUpdate(lsRepresentanteRota);
		} else {
			Log.e("LOCATION_REPRESENTATIVE", resposta[1]);
		}
	}
	
	//generateRepresentative
	
	public RepresentanteVO generateRepresentative(Device device) throws Exception {
		Gson gson = new Gson();
		String deviceStr = gson.toJson(device);

		String[] resposta = new ServiceRest().post(Endpoints.REPRESENTANTE_GENERATE, deviceStr);

		if (resposta[0].equals("200")) {
			if(resposta[1] != null && !resposta[1].trim().equals("")){
				JsonParser parser = new JsonParser();
				JsonObject repreJSON = parser.parse(resposta[1]).getAsJsonObject();
				
				RepresentanteVO representante = gson.fromJson(repreJSON, RepresentanteVO.class);
				UsuarioVO.idEmpresa = representante.getIdEmpresa();
				UsuarioVO.idUsuairo = representante.getIdRepresentante();
				return representante;				
			}			
		} else {
			throw new Exception(resposta[1]);
		}
		
		return null;
	}
	
}