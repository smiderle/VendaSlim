package br.com.vendaslim.ws.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.vendaslim.ws.controller.GrupoProdutoController;
import br.com.vendaslim.ws.controller.ProdutoController;
import br.com.vendaslim.ws.domain.GrupoProdutoIntegration;
import br.com.vendaslim.ws.domain.ItemIntegration;
import br.com.vendaslim.ws.domain.RepresentanteIntegration;
import br.com.vendaslim.ws.support.ApiResponse;
import br.com.vendaslim.ws.support.ServiceResponse;
import br.com.vendaslim.ws.support.TaxExceptionWapper;

import com.google.gson.Gson;

@Path("/product")
@XmlRootElement
public class ProdutoResource extends Resource{
	
	
	@GET
	@Path("/getAllProductByChangeDate")
	@Produces("application/json")
	public String getAllProductByChangeDate(
			@QueryParam("changeDate") long changeDate,			
			@QueryParam("idEmpresa") int idEmpresa, 
			@QueryParam("idFilial") int idFilial) {
						
		ApiResponse<ServiceResponse<List<ItemIntegration>>> apiResponse = new ApiResponse<ServiceResponse<List<ItemIntegration>>>();
		try{			
			openTransaction();
			ProdutoController controller = new ProdutoController();
			List<ItemIntegration> lsProduto = controller.buscarPorDataAlteracao(changeDate, idEmpresa, idFilial);
			final ServiceResponse<List<ItemIntegration>> response = new ServiceResponse<List<ItemIntegration>>(lsProduto, lsProduto != null ?  lsProduto.size() : 0l);
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
	
	@GET
	@Path("/getAllProductGroupByChangeDate")
	@Produces("application/json")
	public String getAllProductGroupByChangeDate(
			@QueryParam("changeDate") long changeDate,			
			@QueryParam("idEmpresa") int idEmpresa, 
			@QueryParam("idFilial") int idFilial) {
		
		ApiResponse<ServiceResponse<List<GrupoProdutoIntegration>>> apiResponse = new ApiResponse<ServiceResponse<List<GrupoProdutoIntegration>>>();
		
		try {
			openTransaction();
			
			GrupoProdutoController controller = new GrupoProdutoController();
			List<GrupoProdutoIntegration> lsGrupoProduto = new ArrayList<GrupoProdutoIntegration>();
			
			lsGrupoProduto = controller.buscarPorDataAlteracao(changeDate, idEmpresa, idFilial);

			final ServiceResponse<List<GrupoProdutoIntegration>> response = new ServiceResponse<List<GrupoProdutoIntegration>>(lsGrupoProduto, lsGrupoProduto != null ?  lsGrupoProduto.size() : 0l);
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
