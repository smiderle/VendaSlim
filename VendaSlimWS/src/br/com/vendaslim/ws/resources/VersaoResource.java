package br.com.vendaslim.ws.resources;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import br.com.vendaslim.ws.controller.ClienteController;
import br.com.vendaslim.ws.controller.VersaoPdaController;
import br.com.vendaslim.ws.domain.ClienteIntegration;
import br.com.vendaslim.ws.domain.VersaoPda;

@Path("/version")
@XmlRootElement
public class VersaoResource extends Resource{
	
	//http://localhost:8080/VendaSlimWS/getVersionPda?changeDate=1313
	//http://localhost:8080/VendaSlimWS/version/getExpirationDate?serial=355500041422007
	@GET
	@Path("/getVersionPda")
	@Produces("application/json")
	public String getVersionPda(
			@QueryParam("serial") String serial){
		
		openTransaction();
		VersaoPdaController controller = new VersaoPdaController();
		VersaoPda versaoPda = controller.buscaPorSerial(serial);
		
		closeTransaction();
		
		return new Gson().toJson(versaoPda);
		
	}
	
	@GET
	@Path("/getExpirationDate")
	@Produces("application/json")
	public String getExpirationDate(
			@QueryParam("serial") String serial){
		
		openTransaction();
		VersaoPdaController controller = new VersaoPdaController();
		VersaoPda versaoPda = controller.buscaPorSerial(serial);
		boolean versaoExpirada = versaoPda.getDataExpiracao().before(new Date());		
		closeTransaction();
		
		return (versaoPda.getDataExpiracao().getTime()+","+versaoExpirada);
		
	}
	
	@POST
	@Path("addVersionPda")	
	@Consumes("application/json")
	public void addVersionPda(String versionPda){
		openTransaction();
		Gson gson = new Gson();		
		JsonParser parser = new JsonParser();
		JsonObject obj = parser.parse(versionPda).getAsJsonObject();
		
		VersaoPda versaoPda = gson.fromJson(obj, VersaoPda.class);
		//Adiciona 15 dias para a data de expiração
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.add(Calendar.DAY_OF_MONTH, 15);
		versaoPda.setDataExpiracao(calendar.getTime());
		
		
		VersaoPdaController controller = new VersaoPdaController();
		controller.insert(versaoPda);
		closeTransaction();
	}
}
