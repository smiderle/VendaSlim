package br.com.slim.venda.cliente;

public class ClienteSingleton {
	
	private static ClienteVO clienteVO;
	
	public static ClienteVO getInstance(){
		if(clienteVO == null){
			 clienteVO = new ClienteVO();
		}
		return clienteVO;
	}
	
	public static void remove(){
		clienteVO = null;
	}
}
