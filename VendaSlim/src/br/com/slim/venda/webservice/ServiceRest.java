package br.com.slim.venda.webservice;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import br.com.slim.venda.integration.domain.Endpoints;
import android.util.Log;

public class ServiceRest {
	
	private LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
	
	private String resource = null;
	private String method = null;
	
	public void setResource(String resource){
		this.resource = resource;
	}
	
	public void setMethod(String method){
		this.method = method;
	}
	
	public void setParameter(String parameter, Integer value){
		setParameter(parameter, String.valueOf(value));
	}
	
	public void setParameter(String parameter, Long value){
		setParameter(parameter, String.valueOf(value));
	}
	
	public void setParameter(String parameter, String value){
		parameters.put(parameter, value);
	}
	
	private String getUrl(){
		StringBuffer parametrosUrl = new StringBuffer();
		
		Set<String> parametros = parameters.keySet();
		for (String parametro : parametros) {
			parametrosUrl.append(parametro);
			parametrosUrl.append("=");
			parametrosUrl.append(parameters.get(parametro));
			parametrosUrl.append("&");
		}
		//Remove o ultimo &
		if(parametrosUrl.length() > 0){		
			parametrosUrl.deleteCharAt(parametrosUrl.length()-1);
			return Endpoints.ENDPOINT+resource+method+"?"+parametrosUrl;
		} else {
			return Endpoints.ENDPOINT+resource+method;
		}		
		
	}
		
	public final String[] get() throws Exception {
		if(resource == null || method == null)
			throw new Exception("É preciso definir o resource e o method");

		 String url = getUrl();
	     String[] result = new String[2];
	     HttpGet httpget = new HttpGet(url);
	     HttpResponse response;

	     try {
	         response = HttpClientSingleton.getHttpClientInstace().execute(httpget);
	         HttpEntity entity = response.getEntity();

	         if (entity != null) {
	             result[0] = String.valueOf(response.getStatusLine().getStatusCode());
	             InputStream instream = entity.getContent();
	             result[1] = toString(instream);
	             //result[1] = "Ocorreu algum problema. Tente novamente mais tarde.";
	             	             
	             instream.close();
	             Log.i("get", "Result from post JsonPost : " + result[0] + " : " + result[1]);
	         }
	     } catch (Exception e) {
	         Log.e("NGVL", "Falha ao acessar Web service", e);
	         result[0] = "0";
	         result[1] = "Ocorreu algum problema. Tente novamente mais tarde.";
	         e.getMessage();
	     }
	     return result;
	    }

	    public final String[] post(String url, String json) {
	     String[] result = new String[2];
	     try {

	         HttpPost httpPost = new HttpPost(new URI(url));
	         httpPost.setHeader("Content-type", "application/json");
	         StringEntity sEntity = new StringEntity(json, "UTF-8");
	         httpPost.setEntity(sEntity);

	         HttpResponse response;
	         response = HttpClientSingleton.getHttpClientInstace().execute(httpPost);
	         HttpEntity entity = response.getEntity();

	         if (entity != null) {
	             result[0] = String.valueOf(response.getStatusLine().getStatusCode());
	             InputStream instream = entity.getContent();
	             result[1] = toString(instream);
	             instream.close();
	             Log.d("post", "Result from post JsonPost : " + result[0] + " : " + result[1]);
	         }

	     } catch (Exception e) {
	         Log.e("NGVL", "Falha ao acessar Web service", e);
	         result[0] = "0";
	         result[1] = "Falha na rede.";
	         e.getMessage();
	     }
	     return result;
	    }

	    private String toString(InputStream is) throws IOException {

	     byte[] bytes = new byte[1024];
	     ByteArrayOutputStream baos = new ByteArrayOutputStream();
	     int lidos;
	     while ((lidos = is.read(bytes)) > 0) {
	         baos.write(bytes, 0, lidos);
	     }
	     return new String(baos.toByteArray());
	    }
}
