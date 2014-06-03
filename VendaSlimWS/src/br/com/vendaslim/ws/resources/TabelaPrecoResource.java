package br.com.vendaslim.ws.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.vendaslim.ws.controller.TabelaPrecoController;
import br.com.vendaslim.ws.domain.ClienteIntegration;
import br.com.vendaslim.ws.domain.TabelaPrecoIntegration;
import br.com.vendaslim.ws.support.ApiResponse;
import br.com.vendaslim.ws.support.ServiceResponse;
import br.com.vendaslim.ws.support.TaxExceptionWapper;

import com.google.gson.Gson;

@Path("/priceTable")
@XmlRootElement
public class TabelaPrecoResource extends Resource{

	
	@GET
	@Path("/getAllPriceTableByChangeDate")
	@Produces("application/json")
	public String getAllPriceTableByChangeDate(
			@QueryParam("changeDate") long changeDate,			
			@QueryParam("idEmpresa") int idEmpresa, 
			@QueryParam("idFilial") int idFilial) throws Exception{

		ApiResponse<ServiceResponse<List<TabelaPrecoIntegration>>> apiResponse = new ApiResponse<ServiceResponse<List<TabelaPrecoIntegration>>>();

		try {
			openTransaction();

			TabelaPrecoController controler = new TabelaPrecoController();
			List<TabelaPrecoIntegration> lsTabelaPreco = new ArrayList<TabelaPrecoIntegration>();

			lsTabelaPreco = controler.buscarPorDataAlteracao(changeDate, idEmpresa, idFilial);

			final ServiceResponse<List<TabelaPrecoIntegration>> response = new ServiceResponse<List<TabelaPrecoIntegration>>(lsTabelaPreco, lsTabelaPreco != null ?  lsTabelaPreco.size() : 0l);
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
