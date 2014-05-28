package br.com.vendaslim.ws.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Table
@Entity(name="EMPRESA")
public class EmpresaIntegration implements Domain {
	
	public static final Integer EMPRESA_DEMONSTRACAO = 1;
	@Id
	private Integer idEmpresa;
	@Column	
	private String nomeFantasia;
	@Column
	private String razaoSocial;
	public Integer getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public String getNomeFantasia() {
		return nomeFantasia;
	}
	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	
	

}
