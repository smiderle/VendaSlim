package br.com.vendaslim.ws.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.Gson;

import br.com.vendaslim.ws.controller.GrupoProdutoController;
import br.com.vendaslim.ws.controller.ProdutoController;
import br.com.vendaslim.ws.domain.GrupoProdutoIntegration;
import br.com.vendaslim.ws.domain.ItemIntegration;

@Path("/product")
@XmlRootElement
public class ProdutoResource extends Resource{
	
	
	@GET
	@Path("/getAllProductByChangeDate")
	@Produces("application/json")
	public String getAllProductByChangeDate(
			@QueryParam("changeDate") long changeDate,			
			@QueryParam("idEmpresa") int idEmpresa, 
			@QueryParam("idFilial") int idFilial) {
		List<ItemIntegration> lsProduto;
		try {
			openTransaction();
			ProdutoController controller = new ProdutoController();
			lsProduto = controller.buscarPorDataAlteracao(changeDate, idEmpresa, idFilial);
			return new Gson().toJson(lsProduto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeTransaction();
		}
		
		return null;
	}
	
	@GET
	@Path("/getAllProductGroupByChangeDate")
	@Produces("application/json")
	public String getAllProductGroupByChangeDate(
			@QueryParam("changeDate") long changeDate,			
			@QueryParam("idEmpresa") int idEmpresa, 
			@QueryParam("idFilial") int idFilial) {
		
		List<GrupoProdutoIntegration> lsGrupoProduto;
		try {
			openTransaction();
			
			GrupoProdutoController controller = new GrupoProdutoController();
			lsGrupoProduto = new ArrayList<GrupoProdutoIntegration>();
			
			lsGrupoProduto = controller.buscarPorDataAlteracao(changeDate, idEmpresa, idFilial);
			
			return new Gson().toJson(lsGrupoProduto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			closeTransaction();
		}
		
		return null;
		
		
	}

}
