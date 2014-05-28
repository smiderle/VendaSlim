package br.com.slim.venda.integration;


public class WSCep {/*
	
	public String buscarCep(String cep){
		//ProgressDialogBox pd = null;
		try {
			pd  = new ProgressDialogBox();
			pd.show();
			SoapObject soap = new SoapObject("urn:http://www.byjg.com.br", "obterLogradouroAuth");
			soap.addProperty("cep", cep);
			soap.addProperty("usuario", "ladair");
			soap.addProperty("senha", "lada8580");
			
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
			        SoapEnvelope.VER11);
			envelope.setOutputSoapObject(soap);
			HttpTransportSE http = new HttpTransportSE("http://www.byjg.com.br/site/webservice.php/ws/cep");
			
			http.call("", envelope);
			 
			Object msg = envelope.getResponse();			
			return msg != null ? msg.toString() : null;
			
		} catch (SoapFault e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} finally {
			//pd.dismiss();
		}
		return null;
	}
*/}
