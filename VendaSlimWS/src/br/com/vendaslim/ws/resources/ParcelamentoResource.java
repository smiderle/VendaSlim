package br.com.vendaslim.ws.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.vendaslim.ws.controller.ParcelamentoController;
import br.com.vendaslim.ws.domain.GrupoRepresentanteTabPrecoIntegration;
import br.com.vendaslim.ws.domain.ParcelamentoIntegration;
import br.com.vendaslim.ws.support.ApiResponse;
import br.com.vendaslim.ws.support.ServiceResponse;
import br.com.vendaslim.ws.support.TaxExceptionWapper;

import com.google.gson.Gson;

@Path("/subdivision")
@XmlRootElement
public class ParcelamentoResource extends Resource{


	@GET
	@Path("/getAllSubdivisionByChangeDate")
	@Produces("application/json")
	public String getAllSubdivisionByChangeDate(
			@QueryParam("changeDate") long changeDate,			
			@QueryParam("idEmpresa") int idEmpresa, 
			@QueryParam("idFilial") int idFilial) {
		
		ApiResponse<ServiceResponse<List<ParcelamentoIntegration>>> apiResponse = new ApiResponse<ServiceResponse<List<ParcelamentoIntegration>>>();

		try{
			openTransaction();

			ParcelamentoController controler = new ParcelamentoController();
			List<ParcelamentoIntegration> lsParcelamento = new ArrayList<ParcelamentoIntegration>();

			lsParcelamento = controler.buscarPorDataAlteracao(changeDate, idEmpresa, idFilial);

			final ServiceResponse<List<ParcelamentoIntegration>> response = new ServiceResponse<List<ParcelamentoIntegration>>(lsParcelamento, lsParcelamento != null ?  lsParcelamento.size() : 0l);
			apiResponse.setResult(response);
			apiResponse.setStatus(ApiResponse.STATUS_SUCCESS);
			apiResponse.setMessage(ApiResponse.STATUS_SUCCESS);
		} catch (Exception e) {
			apiResponse.setStatus(ApiResponse.STATUS_FAILURE);
			apiResponse.setCode("500");			
			apiResponse.setMessage("Problemas na sincronização. Tente novamente mais tarde!");			
			e.printStackTrace();
		} finally{
			closeTransaction();
		}
		return new Gson().toJson(apiResponse);
	}


}
