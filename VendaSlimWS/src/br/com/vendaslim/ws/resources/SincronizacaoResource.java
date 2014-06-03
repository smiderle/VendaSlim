package br.com.vendaslim.ws.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.vendaslim.ws.controller.SincronizacaoController;
import br.com.vendaslim.ws.domain.RepresentanteIntegration;
import br.com.vendaslim.ws.domain.Sincronizacao;
import br.com.vendaslim.ws.support.ApiResponse;
import br.com.vendaslim.ws.support.ServiceResponse;
import br.com.vendaslim.ws.support.TaxExceptionWapper;

import com.google.gson.Gson;

@Path("/sincronize")
@XmlRootElement
public class SincronizacaoResource extends Resource{

	@GET
	@Path("/getTimeServer")
	@Produces("text/plain")
	public String getTimeServer(
			@QueryParam("idRepresentante") int idrepresentante, 
			@QueryParam("idEmpresa") int idEmpresa, 
			@QueryParam("idFilial") int idFilial){
		
		ApiResponse<ServiceResponse<String>> apiResponse = new ApiResponse<ServiceResponse<String>>();
		
		try {
		openTransaction();
		SincronizacaoController controler = new SincronizacaoController();
		Sincronizacao novaSincronizacao = controler.geraNova(idrepresentante, idEmpresa, idFilial, null);

		final ServiceResponse<String> response = new ServiceResponse<String>(novaSincronizacao.getDtHrSincronizacao().getTime().getTime()+"",1l);		
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
