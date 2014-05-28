package br.com.slim.venda.filial;

public class FilialMobileConfigVO {
	
	private Integer idEmpresa;
	private Integer idFilial;
	private boolean exibeEstoque;
	private boolean permiteVendaEstoqueNegativo;
	private boolean permiteCadastrarCliente;
	private boolean enviarEmailCliente;
	private boolean enviarEmailAdmin;
	private String emailPedidos;
	private Integer acaoLimiteCredito;
	private Integer acaoTituloVencido;
	private Integer diasVenciemento;
	
	public static final class FilialMobileConfig {
		public static final String TABELA = "FILIALCONFIG";
		public static final String IDFILIAL = "IDFILIAL";
		public static final String IDEMPRESA = "IDEMPRESA";
		public static final String EXIBEESTOQUE = "EXIBEESTOQUE";
		public static final String ESTOQUENEGATIVO = "ESTOQUENEGATIVO";
		public static final String CADASTRARCLIENTE = "CADASTRARCLIENTE";
		public static final String ENVIAREMAILCLIENTE = "enviarEmailCliente";
		public static final String EMAILPEDIDOS = "EMAILPEDIDOS";
		public static final String ACAOLIMITECREDITO = "ACAOLIMITECREDITO";
		public static final String ACAOTITULOVENCIDO = "ACAOTITULOVENCIDO";
		public static final String DIASVENCIEMENTO = "DIASVENCIEMENTO";		
		
		public static final String[] COLUNAS = new String[]{IDFILIAL, IDEMPRESA,EXIBEESTOQUE, ESTOQUENEGATIVO, CADASTRARCLIENTE, ENVIAREMAILCLIENTE, EMAILPEDIDOS, ACAOLIMITECREDITO, ACAOTITULOVENCIDO, DIASVENCIEMENTO};
	}

	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public Integer getIdFilial() {
		return idFilial;
	}

	public void setIdFilial(Integer idFilial) {
		this.idFilial = idFilial;
	}

	public boolean isExibeEstoque() {
		return exibeEstoque;
	}

	public void setExibeEstoque(boolean exibeEstoque) {
		this.exibeEstoque = exibeEstoque;
	}

	public boolean isPermiteVendaEstoqueNegativo() {
		return permiteVendaEstoqueNegativo;
	}

	public void setPermiteVendaEstoqueNegativo(boolean permiteVendaEstoqueNegativo) {
		this.permiteVendaEstoqueNegativo = permiteVendaEstoqueNegativo;
	}

	public boolean isPermiteCadastrarCliente() {
		return permiteCadastrarCliente;
	}

	public void setPermiteCadastrarCliente(boolean permiteCadastrarCliente) {
		this.permiteCadastrarCliente = permiteCadastrarCliente;
	}

	public boolean isEnviarEmailCliente() {
		return enviarEmailCliente;
	}

	public void setEnviarEmailCliente(boolean enviarEmailCliente) {
		this.enviarEmailCliente = enviarEmailCliente;
	}

	public boolean isEnviarEmailAdmin() {
		return enviarEmailAdmin;
	}

	public void setEnviarEmailAdmin(boolean enviarEmailAdmin) {
		this.enviarEmailAdmin = enviarEmailAdmin;
	}

	public String getEmailPedidos() {
		return emailPedidos;
	}

	public void setEmailPedidos(String emailPedidos) {
		this.emailPedidos = emailPedidos;
	}

	public Integer getAcaoLimiteCredito() {
		return acaoLimiteCredito;
	}

	public void setAcaoLimiteCredito(Integer acaoLimiteCredito) {
		this.acaoLimiteCredito = acaoLimiteCredito;
	}

	public Integer getAcaoTituloVencido() {
		return acaoTituloVencido;
	}

	public void setAcaoTituloVencido(Integer acaoTituloVencido) {
		this.acaoTituloVencido = acaoTituloVencido;
	}

	public Integer getDiasVenciemento() {
		return diasVenciemento;
	}

	public void setDiasVenciemento(Integer diasVenciemento) {
		this.diasVenciemento = diasVenciemento;
	}
	
	

}
