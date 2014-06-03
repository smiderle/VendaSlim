package br.com.slim.venda.webservice;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import br.com.slim.venda.device.Device;
import br.com.slim.venda.integration.domain.Endpoints;
import br.com.slim.venda.integration.domain.GrupoRepresentanteIntegration;
import br.com.slim.venda.representante.GrupoRepresentanteParcelamentoVO;
import br.com.slim.venda.representante.GrupoRepresentanteTabPrecoVO;
import br.com.slim.venda.representante.RepresentanteFilialVO;
import br.com.slim.venda.representante.RepresentanteParcelamentoVO;
import br.com.slim.venda.representante.RepresentanteRotaController;
import br.com.slim.venda.representante.RepresentanteRotaVO;
import br.com.slim.venda.representante.RepresentanteTabPrecoVO;
import br.com.slim.venda.representante.RepresentanteVO;
import br.com.slim.venda.support.ApiResponse;
import br.com.slim.venda.support.ServiceResponse;
import br.com.slim.venda.usuario.UsuarioVO;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
			
			Type serviceType = new TypeToken<ApiResponse<ServiceResponse<List<GrupoRepresentanteIntegration>>>>() {}.getType();			
			ApiResponse<ServiceResponse<List<GrupoRepresentanteIntegration>>> apiResponse = new Gson().fromJson(resposta[1], serviceType);

			if(apiResponse.getCode().equals(ApiResponse.CODE_SUCESS)){
				return apiResponse.getResult().getValue();
			} else {
				throw new Exception(apiResponse.getMessage());
			}
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
			Type serviceType = new TypeToken<ApiResponse<ServiceResponse<List<GrupoRepresentanteParcelamentoVO>>>>() {}.getType();			
			ApiResponse<ServiceResponse<List<GrupoRepresentanteParcelamentoVO>>> apiResponse = new Gson().fromJson(resposta[1], serviceType);

			if(apiResponse.getCode().equals(ApiResponse.CODE_SUCESS)){
				return apiResponse.getResult().getValue();
			} else {
				throw new Exception(apiResponse.getMessage());
			}
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
			
			Type serviceType = new TypeToken<ApiResponse<ServiceResponse<List<GrupoRepresentanteTabPrecoVO>>>>() {}.getType();			
			ApiResponse<ServiceResponse<List<GrupoRepresentanteTabPrecoVO>>> apiResponse = new Gson().fromJson(resposta[1], serviceType);

			if(apiResponse.getCode().equals(ApiResponse.CODE_SUCESS)){
				return apiResponse.getResult().getValue();
			} else {
				throw new Exception(apiResponse.getMessage());
			}
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
			Type serviceType = new TypeToken<ApiResponse<ServiceResponse<List<RepresentanteFilialVO>>>>() {}.getType();			
			ApiResponse<ServiceResponse<List<RepresentanteFilialVO>>> apiResponse = new Gson().fromJson(resposta[1], serviceType);

			if(apiResponse.getCode().equals(ApiResponse.CODE_SUCESS)){
				return apiResponse.getResult().getValue();
			} else {
				throw new Exception(apiResponse.getMessage());
			}
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
			
			Type serviceType = new TypeToken<ApiResponse<ServiceResponse<List<RepresentanteVO>>>>() {}.getType();			
			ApiResponse<ServiceResponse<List<RepresentanteVO>>> apiResponse = new Gson().fromJson(resposta[1], serviceType);
			
			if(apiResponse.getCode().equals(ApiResponse.CODE_SUCESS)){
				return apiResponse.getResult().getValue();
			} else {
				throw new Exception(apiResponse.getMessage());
			}
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
			Type serviceType = new TypeToken<ApiResponse<ServiceResponse<List<RepresentanteParcelamentoVO>>>>() {}.getType();			
			ApiResponse<ServiceResponse<List<RepresentanteParcelamentoVO>>> apiResponse = new Gson().fromJson(resposta[1], serviceType);
			
			if(apiResponse.getCode().equals(ApiResponse.CODE_SUCESS)){
				return apiResponse.getResult().getValue();
			} else {
				throw new Exception(apiResponse.getMessage());
			}
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

			Type serviceType = new TypeToken<ApiResponse<ServiceResponse<List<RepresentanteTabPrecoVO>>>>() {}.getType();			
			ApiResponse<ServiceResponse<List<RepresentanteTabPrecoVO>>> apiResponse = new Gson().fromJson(resposta[1], serviceType);
			
			if(apiResponse.getCode().equals(ApiResponse.CODE_SUCESS)){
				return apiResponse.getResult().getValue();
			} else {
				throw new Exception(apiResponse.getMessage());
			}
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
			Type serviceType = new TypeToken<ApiResponse<ServiceResponse<RepresentanteVO>>>() {}.getType();			
			ApiResponse<ServiceResponse<RepresentanteVO>> apiResponse = gson.fromJson(resposta[1], serviceType);
			
			
			if(apiResponse.getCode().equals("200")){
				RepresentanteVO representante = apiResponse.getResult().getValue();
				UsuarioVO.idEmpresa = representante.getIdEmpresa();
				UsuarioVO.idUsuairo = representante.getIdRepresentante();
				return representante;
			} else {
				throw new Exception(apiResponse.getMessage());
			}						
		} else {
			throw new Exception(resposta[1]);
		}		
	}
	
}