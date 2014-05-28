package br.com.vendaslim.ws.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.vendaslim.ws.controller.TabelaPrecoController;
import br.com.vendaslim.ws.domain.TabelaPrecoIntegration;

import com.google.gson.Gson;

@Path("/priceTable")
@XmlRootElement
public class TabelaPrecoResource extends Resource{

	
	@GET
	@Path("/getAllPriceTableByChangeDate")
	@Produces("application/json")
	public String getAllPriceTableByChangeDate(
			@QueryParam("changeDate") long changeDate,			
			@QueryParam("idEmpresa") int idEmpresa, 
			@QueryParam("idFilial") int idFilial) throws Exception{

		openTransaction();
		
		TabelaPrecoController controler = new TabelaPrecoController();
		List<TabelaPrecoIntegration> lsTabelaPreco = new ArrayList<TabelaPrecoIntegration>();
		
		lsTabelaPreco = controler.buscarPorDataAlteracao(changeDate, idEmpresa, idFilial);

		closeTransaction();
		return new Gson().toJson(lsTabelaPreco);
	}
	
	
}
