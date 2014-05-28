package br.com.vendaslim.ws.domain;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;


//Fonte http://altieresm.wordpress.com/2011/07/09/implementando-heranca-com-hibernate/
@MappedSuperclass
public class Pessoa implements Domain{
	
	public Pessoa() {
		tipoPessoa = 1;
	}
	
	@Column
	private String nome;
	@Column	
	private Integer tipoPessoa;
	@Column
	private String cpfCnpj;
	@Column
	private String contato;
	@Column
	private String foneComercial;
	@Column
	private String foneResidencial;
	@Column
	private String celular;
	@Column
	private String fax;
	
	@Column
	private String cep;
	@Column
	private String complemento;
	@Column
	private String observacao;	
	
	@Column(insertable=false, updatable= false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dthrCadastro;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dtHrAlteracao;		
	@Column
	private String rua;
	@Column
	private String bairro;
	@Column
	private String email;
	@Column
	private String numero;	
	@Column(name="inscriestad")
	private String incricao;
	
	/*@Temporal(TemporalType.DATE)
	private Date dtNascimento;*/
	
	//@Column(name="dtNascimento", insertable=false , updatable=false)
	@Type(type="br.com.vendaslim.ws.hiberante.type.DateLongType")
	@Column(name="DTNASCIMENTO", insertable=false, updatable=false)
	private Long dtNascimentoLong;
	
	@Column(nullable=false)
	private Boolean inativo;	
	
	@Column(name="CIDADE")	
	private Integer idCidade;

	
	
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
	
	
	public Boolean getInativo() {
		return inativo;
	}
	public void setInativo(Boolean inativo) {
		this.inativo = inativo;
	}
	public Long getDtNascimentoLong() {
		return dtNascimentoLong;
	}
	public void setDtNascimentoLong(Long dtNascimentoLong) {
		this.dtNascimentoLong = dtNascimentoLong;
	}
	public Integer getIdCidade() {
		return idCidade;
	}
	public void setIdCidade(Integer idCidade) {
		this.idCidade = idCidade;
	}
	
	
}
