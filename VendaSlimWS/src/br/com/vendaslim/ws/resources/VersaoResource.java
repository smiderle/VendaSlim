package br.com.vendaslim.ws.resources;

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

import br.com.vendaslim.ws.controller.VersaoPdaController;
import br.com.vendaslim.ws.domain.RepresentanteIntegration;
import br.com.vendaslim.ws.domain.VersaoPda;
import br.com.vendaslim.ws.support.ApiResponse;
import br.com.vendaslim.ws.support.ServiceResponse;
import br.com.vendaslim.ws.support.TaxExceptionWapper;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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

		final ApiResponse apiResponse = new ApiResponse();

		try {
			openTransaction();
			VersaoPdaController controller = new VersaoPdaController();
			VersaoPda versaoPda = controller.buscaPorSerial(serial);

			final ServiceResponse<VersaoPda> response = new ServiceResponse<VersaoPda>(versaoPda, 1l);
			apiResponse.setResult(response);
			apiResponse.setStatus(ApiResponse.STATUS_SUCCESS);
			apiResponse.setMessage(ApiResponse.STATUS_SUCCESS);
		} catch (Exception e) {
			apiResponse.setStatus(ApiResponse.STATUS_FAILURE);
			apiResponse.setCode("500");
			apiResponse.setResult(new TaxExceptionWapper("999", e.getMessage()));
			apiResponse.setMessage("Problemas na sincronização. Tente novamente mais tarde!");			
			e.printStackTrace();
		} finally{
			closeTransaction();
		}
		return new Gson().toJson(apiResponse);

	}
	
	@GET
	@Path("/getExpirationDate")
	@Produces("application/json")
	public String getExpirationDate(
			@QueryParam("serial") String serial){
		
		ApiResponse<ServiceResponse<String[]>> apiResponse = new ApiResponse<ServiceResponse<String[]>>();

		try {
			openTransaction();
			
		VersaoPdaController controller = new VersaoPdaController();
		VersaoPda versaoPda = controller.buscaPorSerial(serial);
		boolean versaoExpirada = versaoPda.getDataExpiracao().before(new Date());		
		
		String[] retorno = new String[]{versaoPda.getDataExpiracao().getTime()+","+versaoExpirada};
		
		final ServiceResponse<String[]> response = new ServiceResponse<String[]>(retorno, 1l);
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
	
	@POST
	@Path("addVersionPda")	
	@Consumes("application/json")
	public void addVersionPda(String versionPda){
		try {
			openTransaction();
			Gson gson = new Gson();		
			JsonParser parser = new JsonParser();
			JsonObject obj = parser.parse(versionPda).getAsJsonObject();
			
			VersaoPda versaoPda = gson.fromJson(obj, VersaoPda.class);
			//Adiciona 15 dias para a data de expiração
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.add(Calendar.DAY_OF_MONTH, 25);
			versaoPda.setDataExpiracao(calendar.getTime());
			
			
			VersaoPdaController controller = new VersaoPdaController();
			controller.insert(versaoPda);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			closeTransaction();
		}
	}
}
