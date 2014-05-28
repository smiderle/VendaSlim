package br.com.vendaslim.ws.resources;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.vendaslim.ws.controller.DeviceController;
import br.com.vendaslim.ws.controller.GrupoRepresentanteController;
import br.com.vendaslim.ws.controller.GrupoRepresentanteParcelamentoController;
import br.com.vendaslim.ws.controller.GrupoRepresentanteTabPrecoController;
import br.com.vendaslim.ws.controller.RepresentanteController;
import br.com.vendaslim.ws.controller.RepresentanteFilialController;
import br.com.vendaslim.ws.controller.RepresentanteParcelamentoController;
import br.com.vendaslim.ws.controller.RepresentanteRotaController;
import br.com.vendaslim.ws.controller.RepresentanteTabPrecoController;
import br.com.vendaslim.ws.domain.DeviceIntegration;
import br.com.vendaslim.ws.domain.GrupoRepresentanteIntegration;
import br.com.vendaslim.ws.domain.GrupoRepresentanteParcelamentoIntegration;
import br.com.vendaslim.ws.domain.GrupoRepresentanteTabPrecoIntegration;
import br.com.vendaslim.ws.domain.RepresentanteFilialIntegration;
import br.com.vendaslim.ws.domain.RepresentanteIntegration;
import br.com.vendaslim.ws.domain.RepresentanteParcelamentoIntegration;
import br.com.vendaslim.ws.domain.RepresentanteRotaIntegration;
import br.com.vendaslim.ws.domain.RepresentanteTabPrecoIntegration;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Path("/representative")
@XmlRootElement
public class RepresentanteResource extends Resource{
	
	
	@GET
	@Path("/getAllGroupRepresentativeByChangeDate")
	@Produces("application/json")
	public String getAllGroupRepresentativeByChangeDate(
			@QueryParam("changeDate") long changeDate,			
			@QueryParam("idEmpresa") int idEmpresa, 
			@QueryParam("idFilial") int idFilial) throws Exception {
		openTransaction();
		GrupoRepresentanteController controller = new GrupoRepresentanteController();
		List<GrupoRepresentanteIntegration> lsProduto =  controller.buscarPorDataAlteracao(changeDate, idEmpresa, idFilial);
		closeTransaction();
		
		return new Gson().toJson(lsProduto);
	}
	
	
	@GET
	@Path("/getAllGroupRepresentativeSubdivisionByChangeDate")
	@Produces("application/json")
	public String getAllGroupRepresentativeSubdivisionByChangeDate(
			@QueryParam("changeDate") long changeDate,			
			@QueryParam("idEmpresa") int idEmpresa, 
			@QueryParam("idFilial") int idFilial) throws Exception {
		openTransaction();
		GrupoRepresentanteParcelamentoController controller = new GrupoRepresentanteParcelamentoController();
		List<GrupoRepresentanteParcelamentoIntegration> lsProduto =  controller.buscarPorDataAlteracao(changeDate, idEmpresa, idFilial);
		closeTransaction();
		
		return new Gson().toJson(lsProduto);
	}
	
	
	@GET
	@Path("/getAllGroupRepresentativePriceOfTableByChangeDate")
	@Produces("application/json")
	public String getAllGroupRepresentativePriceOfTableByChangeDate(
			@QueryParam("changeDate") long changeDate,			
			@QueryParam("idEmpresa") int idEmpresa, 
			@QueryParam("idFilial") int idFilial) throws Exception {
		openTransaction();
		GrupoRepresentanteTabPrecoController controller = new GrupoRepresentanteTabPrecoController();
		List<GrupoRepresentanteTabPrecoIntegration> ls =  controller.buscarPorDataAlteracao(changeDate, idEmpresa, idFilial);
		closeTransaction();
		
		return new Gson().toJson(ls);
	}
	
	
	@GET
	@Path("/getAllRepresentativeBranchOfficeByChangeDate")
	@Produces("application/json")
	public String getAllRepresentativeBranchOfficeByChangeDate(
			@QueryParam("changeDate") long changeDate,			
			@QueryParam("idEmpresa") int idEmpresa) throws Exception {
		openTransaction();
		RepresentanteFilialController controller = new RepresentanteFilialController();
		List<RepresentanteFilialIntegration> ls =  controller.buscarPorDataAlteracao(changeDate, idEmpresa);
		closeTransaction();
		
		return new Gson().toJson(ls);
	}

	@GET
	@Path("/getAllRepresentativeSubdivisionByChangeDate")
	@Produces("application/json")
	public String getAllRepresentativeSubdivisionByChangeDate(
			@QueryParam("changeDate") long changeDate,			
			@QueryParam("idEmpresa") int idEmpresa, 
			@QueryParam("idFilial") int idFilial,
			@QueryParam("idRepresentante") int idRepresentante) throws Exception {
		openTransaction();
		RepresentanteParcelamentoController controller = new RepresentanteParcelamentoController();
		List<RepresentanteParcelamentoIntegration> lsProduto =  controller.buscarPorDataAlteracao(changeDate, idEmpresa, idFilial, idRepresentante);
		closeTransaction();
		
		return new Gson().toJson(lsProduto);
	}
	
	
	@GET
	@Path("/getAllRepresentativePriceOfTableByChangeDate")
	@Produces("application/json")
	public String getAllRepresentativePriceOfTableByChangeDate(
			@QueryParam("changeDate") long changeDate,			
			@QueryParam("idEmpresa") int idEmpresa, 
			@QueryParam("idFilial") int idFilial,
			@QueryParam("idRepresentante") int idRepresentante) throws Exception {
		openTransaction();
		RepresentanteTabPrecoController controller = new RepresentanteTabPrecoController();
		List<RepresentanteTabPrecoIntegration> ls =  controller.buscarPorDataAlteracao(changeDate, idEmpresa, idFilial, idRepresentante);
		closeTransaction();
		
		return new Gson().toJson(ls);
	}
	
	
	@GET
	@Path("/getAllRepresentativeByChangeDate")
	@Produces("application/json")
	public String getAllRepresentativeByChangeDate(
			@QueryParam("changeDate") long changeDate,			
			@QueryParam("idEmpresa") int idEmpresa) throws Exception {
		openTransaction();
		RepresentanteController controller = new RepresentanteController();
		List<RepresentanteIntegration> ls =  controller.buscarPorDataAlteracao(changeDate, idEmpresa);
		closeTransaction();
		
		return new Gson().toJson(ls);
	}
	
	
	
	@POST
	@Path("addLocationRepresentative")
	@Produces("application/json")
	@Consumes("application/json")
	public String addLocationRepresentative(String locationRepresentative){
		openTransaction();
		Gson gson = new Gson();
		ArrayList<RepresentanteRotaIntegration> lsLocationRepresentative = new ArrayList<RepresentanteRotaIntegration>();
		JsonParser parser = new JsonParser();
		JsonArray array = parser.parse(locationRepresentative).getAsJsonArray();
		
		for (int i = 0 ; i < array.size();i++) {
			RepresentanteRotaIntegration representanteRota = gson.fromJson(array.get(i),RepresentanteRotaIntegration.class);
			
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTimeInMillis(representanteRota.getDtHrRotaLong());
			
			representanteRota.setDtHrRota(calendar);
			
			lsLocationRepresentative.add(representanteRota);
		}
		
		if(lsLocationRepresentative.size() > 0){
			RepresentanteRotaController controller  = new RepresentanteRotaController();
			controller.save(lsLocationRepresentative);
		}
		
		closeTransaction();
		return "";
	}
	
	
	@POST
	@Path("generateRepresentative")
	@Produces("application/json")
	@Consumes("application/json")
	public String generateRepresentative(String device) throws Exception {
		openTransaction();
		RepresentanteController controller = new RepresentanteController();
		DeviceController deviceController = new DeviceController();
		RepresentanteIntegration representanteGerado = null;
		Gson gson = new Gson();
		JsonParser parser = new JsonParser();
		String email = "";
		
		if(device != null && !device.trim().equals("")){
			JsonObject deviceJSON = parser.parse(device).getAsJsonObject();
			DeviceIntegration deviceIntegration = gson.fromJson(deviceJSON,DeviceIntegration.class);
					
			DeviceIntegration deviceSalvo = deviceController.getDeviceBySerial(deviceIntegration.getSerial());
			
			if(deviceSalvo == null){
				deviceController.save(deviceIntegration);
			} else {
				deviceIntegration = deviceSalvo;
			}
			
			RepresentanteIntegration representante = controller.buscaPorSerial(deviceIntegration);
			
			if(representante == null){
				representanteGerado = controller.geraNovoRepresentanteDemonstracao(deviceIntegration);
			} else {
				representanteGerado = representante;
			}
		}
		
		closeTransaction();
		
		return new Gson().toJson(representanteGerado);
	}


}
