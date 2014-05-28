package br.com.vendaslim.ws.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.vendaslim.ws.controller.MensagemController;
import br.com.vendaslim.ws.domain.MensagemIntegration;

import com.google.gson.Gson;

@Path("/message")
@XmlRootElement
public class MensagemResource extends Resource{
	
	
	@GET
	@Path("/getAllMessageByChangeDate")
	@Produces("application/json")
	public String getAllMessageByChangeDate(
			@QueryParam("changeDate") long changeDate,			
			@QueryParam("idEmpresa") int idEmpresa,
			@QueryParam("idRepresentante") int idRepresentante) throws Exception {
		openTransaction();
		MensagemController controller = new MensagemController();
		List<MensagemIntegration> lsMensagem =  controller.buscarPorDataAlteracao(changeDate, idEmpresa, idRepresentante);
		closeTransaction();
		
		return new Gson().toJson(lsMensagem);
	}
}
