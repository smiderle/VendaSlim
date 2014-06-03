package br.com.vendaslim.ws.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.vendaslim.ws.controller.FilialController;
import br.com.vendaslim.ws.controller.FilialMobileConfigController;
import br.com.vendaslim.ws.domain.FilialIntegration;
import br.com.vendaslim.ws.domain.FilialMobileConfigIntegration;
import br.com.vendaslim.ws.support.ApiResponse;
import br.com.vendaslim.ws.support.ServiceResponse;
import br.com.vendaslim.ws.support.TaxExceptionWapper;

import com.google.gson.Gson;

@Path("/branchOffice")
@XmlRootElement
public class FilialResource extends Resource{
	
	
	@GET
	@Path("/getAllBranchOfficeByChangeDate")
	@Produces("application/json")
	public String getAllBranchOfficeByChangeDate(
			@QueryParam("changeDate") long changeDate,
			@QueryParam("idEmpresa") int idEmpresa) throws Exception {
		
		ApiResponse<ServiceResponse<List<FilialIntegration>>> apiResponse = new ApiResponse<ServiceResponse<List<FilialIntegration>>>();
		
		List<FilialIntegration> lsFilial;
		try {
			openTransaction();
			FilialController controller = new FilialController();
			lsFilial = controller.buscarPorDataAlteracao(changeDate, idEmpresa);
			
			final ServiceResponse<List<FilialIntegration>> response = new ServiceResponse<List<FilialIntegration>>(lsFilial, lsFilial != null ?  lsFilial.size() : 0l);
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
	
	@GET
	@Path("/getAllBranchOfficeConfigByChangeDate")
	@Produces("application/json")
	public String getAllBranchOfficeConfigByChangeDate(
			@QueryParam("changeDate") long changeDate,
			@QueryParam("idFilial") int idFilial,
			@QueryParam("idEmpresa") int idEmpresa) throws Exception {
		
		ApiResponse<ServiceResponse<List<FilialMobileConfigIntegration> >> apiResponse = new ApiResponse<ServiceResponse<List<FilialMobileConfigIntegration> >>();
		
		
		try{
			openTransaction();
			FilialMobileConfigController controller = new FilialMobileConfigController();
			List<FilialMobileConfigIntegration> lsFilial =  controller.buscarPorDataAlteracao(changeDate, idEmpresa, idFilial);
			
			final ServiceResponse<List<FilialMobileConfigIntegration> > response = new ServiceResponse<List<FilialMobileConfigIntegration> >(lsFilial, lsFilial != null ?  lsFilial.size() : 0l);
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
