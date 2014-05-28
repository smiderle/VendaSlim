package br.com.vendaslim.ws.resources;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
	public String getAllByChangeDate(
			@QueryParam("changeDate") long changeDate,			
			@QueryParam("idEmpresa") int idEmpresa, 
			@QueryParam("idFilial") int idFilial) throws Exception{

		openTransaction();
		
		ClienteController controler = new ClienteController();
		List<ClienteIntegration> clientes = new ArrayList<ClienteIntegration>();
		
		clientes = controler.buscarPorDataAlteracao(changeDate, idEmpresa, idFilial);

		closeTransaction();
		System.out.println(clientes.size());
		System.out.println(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date(changeDate)));
		return new Gson().toJson(clientes);
	}
	
	@POST
	@Path("addCustomers")
	@Produces("application/json")
	@Consumes("application/json")
	public String addCustomers(String customers){
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
		ArrayList<ClienteIntegration> lsClienteIntegrationMobile =  controller.insert(lsClientes);
		String retorno = new Gson().toJson(lsClienteIntegrationMobile);
		
		closeTransaction();
		return retorno;
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
