package br.com.slim.venda.integration.domain;

import java.util.HashMap;

import br.com.slim.venda.config.ConfigVO;

public class Endpoints {
	
	public static final String ENDPOINT = "http://"+ConfigVO.ipServer+":"+ConfigVO.portaServer+"/VendaSlimWS";
	//public static final String ENDPOINT = "http://"+ConfigVO.ipServer+"/VendaSlimWS";
	public static final String RESOURCE_CLIENTE = "/customer";
	public static final String RESOURCE_PEDIDO = "/order";
	public static final String RESOURCE_FILIAL = "/branchOffice";
	public static final String RESOURCE_PRODUTO = "/product";
	public static final String RESOURCE_REPRESENTANTE = "/representative";
	public static final String RESOURCE_MENSAGEM = "/message";
	public static final String RESOURCE_PARCELAMENTO = "/subdivision";
	public static final String RESOURCE_TABELAPRECO = "/priceTable";
	public static final String RESOURCE_VERSION = "/version";
	public static final String RESOURCE_WEBSINC = "/websinc";
	
	
	
	
	
	public static final String METODO_CLIENTE_GETALL = "/getAllCustomerByChangeDate";
	public static final String METODO_CLIENTE_ADDCUSTOMER = "/addCustomers";	
	public static final String METODO_PEDIDO_ADDORDERS = "/addOrders";
	public static final String METODO_FILIAL_GETALLBRANCHOFFICE = "/getAllBranchOfficeByChangeDate";
	public static final String METODO_FILIAL_GETALLBRANCHOFFICECONFIG = "/getAllBranchOfficeConfigByChangeDate";
	public static final String METODO_MENSAGEM_GETALLMESSAGE = "/getAllMessageByChangeDate";
	public static final String METODO_PARCELAMENTO_GETALLSUBDIVISION = "/getAllSubdivisionByChangeDate";
	public static final String METODO_TABPRECO_GETALLPRICETABLE = "/getAllPriceTableByChangeDate";
	public static final String METODO_PRODUTO_GETALLPRODUCT = "/getAllProductByChangeDate";
	public static final String METODO_VERSAO_ADDVERSAOPDA= "/addVersionPda";
	public static final String METODO_VERSAO_GETVERSAOPDA= "/getVersionPda";
	public static final String METODO_VERSAO_GETEXPIRATIONDATE= "/getExpirationDate";
	public static final String METODO_WEBSINC_GETDATA= "/getData";
	public static final String METODO_WEBSINC_DELETEWEBSINC= "/deleteWebsinc";
	
	
	
	
	public static final String METODO_PRODUTO_GETALLPRODUCTGROUP = "/getAllProductGroupByChangeDate";
	public static final String METODO_REPRESENTANTE_GETALLGROUPREPRESENTATIVE = "/getAllGroupRepresentativeByChangeDate";
	public static final String METODO_REPRESENTANTE_GETALLGROUPREPRESENTATIVESUBDIVISION = "/getAllGroupRepresentativeSubdivisionByChangeDate";
	public static final String METODO_REPRESENTANTE_GETALLGROUPREPRESENTATIVEPRICEOFTABLE = "/getAllGroupRepresentativePriceOfTableByChangeDate";
	public static final String METODO_REPRESENTANTE_GETALLREPRESENTATIVEBRANCHOFFICE = "/getAllRepresentativeBranchOfficeByChangeDate";
	public static final String METODO_REPRESENTANTE_GENERATEREPRESENTATIVE = "/generateRepresentative";
	
	public static final String METODO_REPRESENTANTE_GETALLREPRESENTATIVE = "/getAllRepresentativeByChangeDate";
	public static final String METODO_REPRESENTANTE_GETALLREPRESENTATIVESUBDIVISION = "/getAllRepresentativeSubdivisionByChangeDate";
	public static final String METODO_REPRESENTANTE_GETALLREPRESENTATIVEPRICEOFTABLE = "/getAllRepresentativePriceOfTableByChangeDate";
	public static final String METODO_REPRESENTANTE_ADDLOCATIONREPRESENTATIVE= "/addLocationRepresentative";
	
	
	
	
		
	public static final String METODO_SINCRONIZACAO_GETTIMESERVER = "/getTimeServer";
	public static final String RESOURCE_SINCRONIZACAO = "/sincronize";
	
	
	public static final String CLIENTE_ADDCUSTOMERS = ENDPOINT+RESOURCE_CLIENTE+METODO_CLIENTE_ADDCUSTOMER;
	public static final String VERSAO_ADDVERSION = ENDPOINT+RESOURCE_VERSION+METODO_VERSAO_ADDVERSAOPDA;
	
	public static final String PEDIDO_ADDORDERS = ENDPOINT+RESOURCE_PEDIDO+METODO_PEDIDO_ADDORDERS;
	public static final String REPRESENTANTE_ADDLOCATIONREPRESENTATIVE = ENDPOINT+RESOURCE_REPRESENTANTE+METODO_REPRESENTANTE_ADDLOCATIONREPRESENTATIVE;
	public static final String REPRESENTANTE_GENERATE = ENDPOINT+RESOURCE_REPRESENTANTE+METODO_REPRESENTANTE_GENERATEREPRESENTATIVE;
	
	
	public void geraUrl(String dominio, String porta, String resource, String metodo, HashMap<String, String>... parametros){
		
	}

}
