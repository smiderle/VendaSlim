package br.com.vendaslim.ws.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.vendaslim.ws.controller.WebsincController;
import br.com.vendaslim.ws.domain.Websinc;

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
		
		openTransaction();
		WebsincController controller = new WebsincController();
		List<Websinc> lsWebsinc = controller.buscaPorRepresentante(idEmpresa, idFilial, idRepresentante);
		
		closeTransaction();
		
		return new Gson().toJson(lsWebsinc);
		
	}
	
	
	@GET
	@Path("/deleteWebsinc")
	@Produces("application/json")
	public void deleteWebsinc(
			@QueryParam("sequencias") String sequencias){
		
		openTransaction();
		WebsincController controller = new WebsincController();
		if(sequencias != null && !sequencias.trim().equals(""))
			controller.deleteWebsinc(sequencias);
		
		closeTransaction();		
	}
}
