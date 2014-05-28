package br.com.slim.venda.integration.domain;

public class ClienteIntegration extends Pessoa{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idEmpresa;	
	private Integer idFilial;
	private Integer idCliente;	
	private Integer idParcelamento;	
	private Integer idTabelaPreco;	
	private Double descMax;	
	private Double descPadrao;	
	private Double limiteCredito;
	private String apelido;	
	private Integer idRepresentante;
	private String alterado;
	
	/**
	 * Atributo criado para retornar para o miobile o novo id do cliente, 
	 * caso o id do cliente cadastrado no mobile já exista no banco do monitor.
	 */
	private Integer IdClienteMobile;
	
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
	public Integer getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
	public Integer getIdParcelamento() {
		return idParcelamento;
	}
	public void setIdParcelamento(Integer idParcelamento) {
		this.idParcelamento = idParcelamento;
	}
	public Integer getIdTabelaPreco() {
		return idTabelaPreco;
	}
	public void setIdTabelaPreco(Integer idTabelaPreco) {
		this.idTabelaPreco = idTabelaPreco;
	}
	public Double getDescMax() {
		return descMax;
	}
	public void setDescMax(Double descMax) {
		this.descMax = descMax;
	}
	public Double getDescPadrao() {
		return descPadrao;
	}
	public void setDescPadrao(Double descPadrao) {
		this.descPadrao = descPadrao;
	}
	public Double getLimiteCredito() {
		return limiteCredito;
	}
	public void setLimiteCredito(Double limiteCredito) {
		this.limiteCredito = limiteCredito;
	}
	public String getApelido() {
		return apelido;
	}
	public void setApelido(String apelido) {
		this.apelido = apelido;
	}
	public Integer getIdRepresentante() {
		return idRepresentante;
	}
	public void setIdRepresentante(Integer idRepresentante) {
		this.idRepresentante = idRepresentante;
	}
	public Integer getIdClienteMobile() {
		return IdClienteMobile;
	}
	public void setIdClienteMobile(Integer idClienteMobile) {
		IdClienteMobile = idClienteMobile;
	}
	public String getAlterado() {
		return alterado;
	}
	public void setAlterado(String alterado) {
		this.alterado = alterado;
	}
	
	
}
