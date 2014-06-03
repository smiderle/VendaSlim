package br.com.vendaslim.ws.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.vendaslim.ws.controller.ClienteController;
import br.com.vendaslim.ws.domain.ClienteIntegration;
import br.com.vendaslim.ws.support.ApiResponse;
import br.com.vendaslim.ws.support.ServiceResponse;
import br.com.vendaslim.ws.support.TaxExceptionWapper;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

@Path("/customer")
@XmlRootElement
public class ClienteResource extends Resource{

	//http://www.portalandroid.org/comunidade/viewtopic.php?f=7&t=17389
	//http://www.portalandroid.org/comunidade/viewtopic.php?f=119&t=17465
	
	 /*@GET
	    @Produces("text/plain")
	    public String showHelloWorld() {
	     return "Olá mundo!";   
	    }*/
	
	
	@GET
	@Path("/getAllCustomerByChangeDate")
	@Produces("application/json")
	public String getAllCustomerByChangeDate(
			@QueryParam("changeDate") long changeDate,			
			@QueryParam("idEmpresa") int idEmpresa, 
			@QueryParam("idFilial") int idFilial) throws Exception{
		
		ApiResponse<ServiceResponse<List<ClienteIntegration>>> apiResponse = new ApiResponse<ServiceResponse<List<ClienteIntegration>>>();
		
		try {
			openTransaction();

			ClienteController controler = new ClienteController();
			List<ClienteIntegration> clientes = new ArrayList<ClienteIntegration>();
			
			clientes = controler.buscarPorDataAlteracao(changeDate, idEmpresa, idFilial);
			
			final ServiceResponse<List<ClienteIntegration>> response = new ServiceResponse<List<ClienteIntegration>>(clientes, clientes != null ?  clientes.size() : 0l);
			apiResponse.setResult(response);
			apiResponse.setStatus(ApiResponse.STATUS_SUCCESS);
			apiResponse.setMessage(ApiResponse.STATUS_SUCCESS);
		} catch (Exception e) {
			apiResponse.setStatus(ApiResponse.STATUS_FAILURE);
			apiResponse.setCode("500");			
			apiResponse.setMessage("Problemas na sincronização. Tente novamente mais tarde!");			
			e.printStackTrace();
		} finally {
			closeTransaction();
		}
		
		return new Gson().toJson(apiResponse);
	}
	
	@POST
	@Path("addCustomers")
	@Produces("application/json")
	@Consumes("application/json")
	public String addCustomers(String customers){
				
		ApiResponse<ServiceResponse<List<ClienteIntegration>>> apiResponse = new ApiResponse<ServiceResponse<List<ClienteIntegration>>>();
		
		openTransaction();
		try{
			Gson gson = new Gson();
			List<ClienteIntegration> lsClientes = new ArrayList<ClienteIntegration>();
			JsonParser parser = new JsonParser();
			JsonArray array = parser.parse(customers).getAsJsonArray();
			
			for (int i = 0 ; i < array.size();i++) {
				lsClientes.add(gson.fromJson(array.get(i),ClienteIntegration.class));
			}
			ClienteController controller = new ClienteController();
			
			//Clientes, que foram inseridos com um id diferente ao cadastrado no mobile
			List<ClienteIntegration> lsClienteIntegrationMobile =  controller.insert(lsClientes);
			
			final ServiceResponse<List<ClienteIntegration>> response = new ServiceResponse<List<ClienteIntegration>>(lsClienteIntegrationMobile, lsClienteIntegrationMobile != null ?  lsClienteIntegrationMobile.size() : 0l);
			apiResponse.setResult(response);
			apiResponse.setStatus(ApiResponse.STATUS_SUCCESS);
			apiResponse.setMessage(ApiResponse.STATUS_SUCCESS);
		} catch(Exception e){
			apiResponse.setStatus(ApiResponse.STATUS_FAILURE);
			apiResponse.setCode("500");			
			apiResponse.setMessage("Problemas na operação. Tente novamente mais tarde!");			
			e.printStackTrace();
		} finally{

			closeTransaction();
		}
		
		return new Gson().toJson(apiResponse);
	}
	
	@POST
	@Consumes("application/json")
	@Path("addCustomer")
	public String addCustomer(String customers){
		if(customers != null && !customers.trim().equals("")){
			openTransaction();
			Gson gson = new Gson();
			ArrayList<ClienteIntegration> clientes = new ArrayList<ClienteIntegration>();
			JsonParser parser = new JsonParser();
			JsonArray array = parser.parse(customers).getAsJsonArray();

			for (int i = 0; i < array.size(); i++) {
				clientes.add(gson.fromJson(array.get(i), ClienteIntegration.class));
			}
			
			new ClienteController().save(clientes);
			
			closeTransaction();
		}
		
		return "";
	}
	
	
	/*@POST
	@Path("updateCustomers")
	@Produces("application/json")
	@Consumes("application/json")
	public String updateCustomers(String customers){
		openTransaction();
		Gson gson = new Gson();
		ArrayList<ClienteIntegration> lsClientes = new ArrayList<ClienteIntegration>();
		JsonParser parser = new JsonParser();
		JsonArray array = parser.parse(customers).getAsJsonArray();
		
		for (int i = 0 ; i < array.size();i++) {
			lsClientes.add(gson.fromJson(array.get(i),ClienteIntegration.class));
		}
		ClienteController controller = new ClienteController();
		
		//Clientes, que foram inseridos com um id diferente ao cadastrado no mobile
		ArrayList<HashMap<String, Integer>> lsClienteIntegrationMobile =  controller.update(lsClientes);
		String retorno = new Gson().toJson(lsClienteIntegrationMobile);
		
		closeTransaction();
		return retorno;
	}*/
	
	
	/*
	@GET
	@Produces("application/json")
	public Cliente selTodos(){
		
		Cliente cliente = new Cliente();
		cliente.setNome("ladair");
		return cliente;
	}*/
}
