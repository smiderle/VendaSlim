package br.com.vendaslim.infra;

public class ConstantsRepository {
	
	public static final Integer MAX_RESULT_CLIENTES = 20;
	public static final Integer MAX_RESULT_PEDIDOS = 30;
	public static final Integer MAX_RESULT_MENSAGENS = 30;
	public static final Integer MAX_RESULT_PRODUTOS = 20;

	//Foi criado o metodo para a xhtml poder pegar esse atributo.
	public Integer getMaxResultClientes() {
		return MAX_RESULT_CLIENTES;
	}

	public Integer getMaxResultPedidos() {
		return MAX_RESULT_PEDIDOS;
	}

	public Integer getMaxResultMensagens() {
		return MAX_RESULT_MENSAGENS;
	}

	
	
	
	
	
}
