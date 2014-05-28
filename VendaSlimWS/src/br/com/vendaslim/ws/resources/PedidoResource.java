package br.com.vendaslim.ws.resources;

import java.util.ArrayList;
import java.util.Collections;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.vendaslim.ws.controller.PedidoController;
import br.com.vendaslim.ws.domain.ClienteIntegration;
import br.com.vendaslim.ws.domain.PedidoIntegration;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

@Path("/order")
@XmlRootElement
public class PedidoResource extends Resource{
	
	@POST
	@Path("addOrders")
	@Produces("application/json")
	@Consumes("application/json")
	public String addOrders(String orders){
		openTransaction();
		Gson gson = new Gson();
		ArrayList<PedidoIntegration> lsOrders = new ArrayList<PedidoIntegration>();
		JsonParser parser = new JsonParser();
		JsonArray array = parser.parse(orders).getAsJsonArray();
		String retorno ="";
		for (int i = 0 ; i < array.size();i++) {
			lsOrders.add(gson.fromJson(array.get(i),PedidoIntegration.class));
		}
		if(lsOrders.size() > 0){
			PedidoController controller = new PedidoController();

			ArrayList<PedidoIntegration> lsClienteIntegrationMobile =  controller.save(lsOrders);
			if(lsClienteIntegrationMobile != null)
				Collections.reverse(lsClienteIntegrationMobile);
			retorno = new Gson().toJson(lsClienteIntegrationMobile);
		}		
		closeTransaction();
		return retorno;
	}
}
