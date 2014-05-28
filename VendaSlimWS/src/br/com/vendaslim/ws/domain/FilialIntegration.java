package br.com.vendaslim.ws.domain;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="FILIAL")
@IdClass(FilialPK.class)
public class FilialIntegration implements Domain{
	
	public static final Integer FILIAL_DEMONSTRACAO = 1;
		
	@Id
	private Integer idEmpresa;
	
	@Id
	private Integer idFilial;	
	
	@Column
	private String fone;
	@Column(nullable=false)
	private String razaoSocial;
	@Column(nullable=false)
	private String nomeFantasia;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dtHrAlteracao;		
	@Column
	private String rua;
	@Column
	private String bairro;	
	@Column
	private String numero;
	
	@Column
	private String cep;
	
	@Column
	private String fax;
	
	@Column
	private String website;

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

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
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

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}
	
	
		
}
