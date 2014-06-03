package br.com.slim.venda.config;

public class ConfigVO {
	public static final int PREFERENCES_ACAO_AVISAR = 1;
	public static final int PREFERENCES_ACAO_BLOQUEAR = 2;
	public static final int PREFERENCES_ACAO_LIBERAR = 3;
	
	public static double juros = 10;
	public static boolean permiteVendaEstoqueNegativo = false;
	public static int acaoLimiteCredito = PREFERENCES_ACAO_AVISAR;
	public static int acaoVendaTitulosVencidos = PREFERENCES_ACAO_AVISAR;
	public static boolean validarCpf = false;
	public static boolean obterLocalizacao = false;
	
	public static String email = "";
	
	public static String ipServer = "192.168.0.106";
	//public static String ipServer = "www.vendaslim.com.br";
	public static String portaServer="8080";
}
