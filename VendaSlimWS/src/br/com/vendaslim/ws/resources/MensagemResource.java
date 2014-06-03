package br.com.vendaslim.ws.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.vendaslim.ws.controller.MensagemController;
import br.com.vendaslim.ws.domain.MensagemIntegration;
import br.com.vendaslim.ws.support.ApiResponse;
import br.com.vendaslim.ws.support.ServiceResponse;

import com.google.gson.Gson;

@Path("/message")
@XmlRootElement
public class MensagemResource extends Resource{


	@GET
	@Path("/getAllMessageByChangeDate")
	@Produces("application/json")
	public String getAllMessageByChangeDate(
			@QueryParam("changeDate") long changeDate,			
			@QueryParam("idEmpresa") int idEmpresa,
			@QueryParam("idRepresentante") int idRepresentante) throws Exception {

		ApiResponse<ServiceResponse<List<MensagemIntegration>>> apiResponse = new ApiResponse<ServiceResponse<List<MensagemIntegration>>>();
		try{
			openTransaction();

			MensagemController controller = new MensagemController();
			List<MensagemIntegration> lsMensagem =  controller.buscarPorDataAlteracao(changeDate, idEmpresa, idRepresentante);


			final ServiceResponse<List<MensagemIntegration>> response = new ServiceResponse<List<MensagemIntegration>>(lsMensagem, lsMensagem != null ?  lsMensagem.size() : 0l);
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
