package br.com.vendaslim.ws.resources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.vendaslim.ws.controller.PedidoController;
import br.com.vendaslim.ws.domain.ClienteIntegration;
import br.com.vendaslim.ws.domain.ParcelamentoIntegration;
import br.com.vendaslim.ws.domain.PedidoIntegration;
import br.com.vendaslim.ws.domain.RepresentanteTabPrecoIntegration;
import br.com.vendaslim.ws.support.ApiResponse;
import br.com.vendaslim.ws.support.ServiceResponse;
import br.com.vendaslim.ws.support.TaxExceptionWapper;

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
		ApiResponse<ServiceResponse<List<PedidoIntegration>>> apiResponse = new ApiResponse<ServiceResponse<List<PedidoIntegration>>>();

		try{
			openTransaction();
			Gson gson = new Gson();
			ArrayList<PedidoIntegration> lsOrders = new ArrayList<PedidoIntegration>();
			JsonParser parser = new JsonParser();
			JsonArray array = parser.parse(orders).getAsJsonArray();

			for (int i = 0 ; i < array.size();i++) {
				lsOrders.add(gson.fromJson(array.get(i),PedidoIntegration.class));
			}

			List<PedidoIntegration> lsClienteIntegrationMobile = null;
			if(lsOrders.size() > 0){
				PedidoController controller = new PedidoController();

				lsClienteIntegrationMobile =  controller.save(lsOrders);
				if(lsClienteIntegrationMobile != null)
					Collections.reverse(lsClienteIntegrationMobile);				
			}

			final ServiceResponse<List<PedidoIntegration>> response = new ServiceResponse<List<PedidoIntegration>>(lsClienteIntegrationMobile, lsClienteIntegrationMobile != null ?  lsClienteIntegrationMobile.size() : 0l);
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
}
