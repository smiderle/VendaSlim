package br.com.slim.venda.integration.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;


public class Pessoa implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nome;	
	private Integer tipoPessoa;
	private String cpfCnpj;
	private String contato;
	private String foneComercial;
	private String foneResidencial;
	private String celular;
	private String fax;
	private String cep;	
	private String complemento;	
	private String observacao;	
	private Calendar dthrCadastro;
	private Calendar dtHrAlteracao;	
	private String rua;	
	private String bairro;	
	private String email;	
	private String numero;	
	private String incricao;
	private Long dtNascimentoLong;
	private boolean inativo;		
	private Integer idCidade;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getTipoPessoa() {
		return tipoPessoa;
	}
	public void setTipoPessoa(Integer tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}
	public String getCpfCnpj() {
		return cpfCnpj;
	}
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}
	public String getContato() {
		return contato;
	}
	public void setContato(String contato) {
		this.contato = contato;
	}
	public String getFoneComercial() {
		return foneComercial;
	}
	public void setFoneComercial(String foneComercial) {
		this.foneComercial = foneComercial;
	}
	public String getFoneResidencial() {
		return foneResidencial;
	}
	public void setFoneResidencial(String foneResidencial) {
		this.foneResidencial = foneResidencial;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public Calendar getDthrCadastro() {
		return dthrCadastro;
	}
	public void setDthrCadastro(Calendar dthrCadastro) {
		this.dthrCadastro = dthrCadastro;
	}
	public Calendar getDtHrAlteracao() {
		return dtHrAlteracao;
	}
	public void setDtHrAlteracao(Calendar dtHrAlteracao) {
		this.dtHrAlteracao = dtHrAlteracao;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getIncricao() {
		return incricao;
	}
	public void setIncricao(String incricao) {
		this.incricao = incricao;
	}	
	public boolean isInativo() {
		return inativo;
	}
	public void setInativo(boolean inativo) {
		this.inativo = inativo;
	}
	public Integer getIdCidade() {
		return idCidade;
	}
	public void setIdCidade(Integer idCidade) {
		this.idCidade = idCidade;
	}
	public Long getDtNascimentoLong() {
		return dtNascimentoLong;
	}
	public void setDtNascimentoLong(Long dtNascimentoLong) {
		this.dtNascimentoLong = dtNascimentoLong;
	}
	
	
	
}
