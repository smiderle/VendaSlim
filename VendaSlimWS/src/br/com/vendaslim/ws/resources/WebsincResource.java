package br.com.vendaslim.ws.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.vendaslim.ws.controller.WebsincController;
import br.com.vendaslim.ws.domain.Websinc;
import br.com.vendaslim.ws.support.ApiResponse;
import br.com.vendaslim.ws.support.ServiceResponse;

import com.google.gson.Gson;

@Path("/websinc")
@XmlRootElement
public class WebsincResource extends Resource{
	
	
	@GET
	@Path("/getData")
	@Produces("application/json")
	public String getData(
			@QueryParam("idEmpresa") Integer idEmpresa,
			@QueryParam("idFilial") Integer idFilial,
			@QueryParam("idRepresentante") Integer idRepresentante){
		
		ApiResponse<ServiceResponse<List<Websinc>>> apiResponse = new ApiResponse<ServiceResponse<List<Websinc>>>();
		
		try {
			openTransaction();
			WebsincController controller = new WebsincController();
			List<Websinc> lsWebsinc = controller.buscaPorRepresentante(idEmpresa, idFilial, idRepresentante);

			final ServiceResponse<List<Websinc> > response = new ServiceResponse<List<Websinc> >(lsWebsinc, lsWebsinc != null ?  lsWebsinc.size() : 0l);
			apiResponse.setResult(response);
			apiResponse.setStatus(ApiResponse.STATUS_SUCCESS);
			apiResponse.setMessage(ApiResponse.STATUS_SUCCESS);		
		} catch(Exception e){
			apiResponse.setStatus(ApiResponse.STATUS_FAILURE);
			apiResponse.setCode("500");			
			apiResponse.setMessage("Problemas na sincronização. Tente novamente mais tarde!");			
			e.printStackTrace();
		} finally {
			closeTransaction();
		}
		
		return new Gson().toJson(apiResponse);
	}
	
	
	@GET
	@Path("/deleteWebsinc")
	@Produces("application/json")
	public void deleteWebsinc(
			@QueryParam("sequencias") String sequencias){
		
		try {
			openTransaction();
			WebsincController controller = new WebsincController();
			if(sequencias != null && !sequencias.trim().equals(""))
				controller.deleteWebsinc(sequencias);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTransaction();
		}			
	}
}
