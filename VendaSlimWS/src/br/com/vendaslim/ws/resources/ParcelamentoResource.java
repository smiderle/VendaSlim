package br.com.vendaslim.ws.resources;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.vendaslim.ws.controller.ClienteController;
import br.com.vendaslim.ws.controller.ParcelamentoController;
import br.com.vendaslim.ws.controller.TabelaPrecoController;
import br.com.vendaslim.ws.domain.ClienteIntegration;
import br.com.vendaslim.ws.domain.ParcelamentoIntegration;
import br.com.vendaslim.ws.domain.TabelaPrecoIntegration;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

@Path("/subdivision")
@XmlRootElement
public class ParcelamentoResource extends Resource{

	
	@GET
	@Path("/getAllSubdivisionByChangeDate")
	@Produces("application/json")
	public String getAllSubdivisionByChangeDate(
			@QueryParam("changeDate") long changeDate,			
			@QueryParam("idEmpresa") int idEmpresa, 
			@QueryParam("idFilial") int idFilial) throws Exception{

		openTransaction();
		
		ParcelamentoController controler = new ParcelamentoController();
		List<ParcelamentoIntegration> lsParcelamento = new ArrayList<ParcelamentoIntegration>();
		
		lsParcelamento = controler.buscarPorDataAlteracao(changeDate, idEmpresa, idFilial);

		closeTransaction();
		return new Gson().toJson(lsParcelamento);
	}
	
	
}
