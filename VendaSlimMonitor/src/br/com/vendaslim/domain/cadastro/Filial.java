package br.com.vendaslim.domain.cadastro;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.PrimaryKeyJoinColumns;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.vendaslim.domain.Domain;


@Entity
@Table(name="FILIAL")
@IdClass(FilialPK.class)
public class Filial implements Domain{
	
	public Filial() {
		empresa = new Empresa();
	}
	
	@Id
	private Empresa empresa;
	
	@Id
	private Integer idFilial;	
	
	@Column
	private String fone;
	@Column(nullable=false)
	private String razaoSocial;
	@Column(nullable=false)
	private String nomeFantasia;
		
	//@OneToOne(fetch = FetchType.LAZY, mappedBy = "filial", cascade = CascadeType.ALL)
	
	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@PrimaryKeyJoinColumns({
			@PrimaryKeyJoinColumn(name="IDEMPRESA"),
			@PrimaryKeyJoinColumn(name="IDFILIAL")})
	private FilialMobileConfig filialMobileConfig;
		
	@Temporal(TemporalType.TIMESTAMP)
	@Column(insertable=false, updatable= false)
	private Date dthrCadastro;
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

	public Date getDthrCadastro() {
		return dthrCadastro;
	}
	public void setDthrCadastro(Date dthrCadastro) {
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
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public Integer getIdFilial() {
		return idFilial;
	}
	public void setIdFilial(Integer idFilial) {
		this.idFilial = idFilial;
	}
	
	
	public FilialMobileConfig getFilialMobileConfig() {
		if(filialMobileConfig == null)
			return new FilialMobileConfig();
		return filialMobileConfig;
	}
	public void setFilialMobileConfig(FilialMobileConfig filialMobileConfig) {
		this.filialMobileConfig = filialMobileConfig;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((empresa == null) ? 0 : empresa.hashCode());
		result = prime * result + ((fone == null) ? 0 : fone.hashCode());
		result = prime * result
				+ ((idFilial == null) ? 0 : idFilial.hashCode());
		result = prime * result
				+ ((nomeFantasia == null) ? 0 : nomeFantasia.hashCode());
		result = prime * result
				+ ((razaoSocial == null) ? 0 : razaoSocial.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Filial other = (Filial) obj;
		if (empresa == null) {
			if (other.empresa != null)
				return false;
		} else if (!empresa.equals(other.empresa))
			return false;
		if (fone == null) {
			if (other.fone != null)
				return false;
		} else if (!fone.equals(other.fone))
			return false;
		if (idFilial == null) {
			if (other.idFilial != null)
				return false;
		} else if (!idFilial.equals(other.idFilial))
			return false;
		if (nomeFantasia == null) {
			if (other.nomeFantasia != null)
				return false;
		} else if (!nomeFantasia.equals(other.nomeFantasia))
			return false;
		if (razaoSocial == null) {
			if (other.razaoSocial != null)
				return false;
		} else if (!razaoSocial.equals(other.razaoSocial))
			return false;
		return true;
	}
	
	
	
	
	
	
	
}
