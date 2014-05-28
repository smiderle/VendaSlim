package br.com.vendaslim.ws.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.vendaslim.ws.controller.FilialController;
import br.com.vendaslim.ws.controller.FilialMobileConfigController;
import br.com.vendaslim.ws.domain.FilialIntegration;
import br.com.vendaslim.ws.domain.FilialMobileConfigIntegration;

import com.google.gson.Gson;

@Path("/branchOffice")
@XmlRootElement
public class FilialResource extends Resource{
	
	
	@GET
	@Path("/getAllBranchOfficeByChangeDate")
	@Produces("application/json")
	public String getAllBranchOfficeByChangeDate(
			@QueryParam("changeDate") long changeDate,
			@QueryParam("idEmpresa") int idEmpresa) throws Exception {
		openTransaction();
		FilialController controller = new FilialController();
		List<FilialIntegration> lsFilial =  controller.buscarPorDataAlteracao(changeDate, idEmpresa);
		closeTransaction();
		
		return new Gson().toJson(lsFilial);
	}
	
	
	
	
	
	@GET
	@Path("/getAllBranchOfficeConfigByChangeDate")
	@Produces("application/json")
	public String getAllBranchOfficeConfigByChangeDate(
			@QueryParam("changeDate") long changeDate,
			@QueryParam("idFilial") int idFilial,
			@QueryParam("idEmpresa") int idEmpresa) throws Exception {
		openTransaction();
		FilialMobileConfigController controller = new FilialMobileConfigController();
		List<FilialMobileConfigIntegration> lsFilial =  controller.buscarPorDataAlteracao(changeDate, idEmpresa, idFilial);
		closeTransaction();
		
		return new Gson().toJson(lsFilial);
	}	
}
