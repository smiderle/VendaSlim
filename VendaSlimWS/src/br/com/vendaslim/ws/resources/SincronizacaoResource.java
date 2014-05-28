package br.com.vendaslim.ws.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.vendaslim.ws.controller.SincronizacaoController;
import br.com.vendaslim.ws.domain.Sincronizacao;

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
		
		openTransaction();
		SincronizacaoController controler = new SincronizacaoController();
		Sincronizacao novaSincronizacao = controler.geraNova(idrepresentante, idEmpresa, idFilial, null);
		closeTransaction();
		return novaSincronizacao.getDtHrSincronizacao().getTime().getTime()+"";
	}

}
