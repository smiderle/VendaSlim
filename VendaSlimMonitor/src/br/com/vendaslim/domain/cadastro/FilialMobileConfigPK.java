package br.com.vendaslim.domain.cadastro;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class FilialMobileConfigPK implements Serializable{
	
	@ManyToOne
	@JoinColumn(name="IDEMPRESA")
	private Empresa empresa;
	
	@Column(name="IDFILIAL")
	private Integer idFilial;
	
	public Integer getIdFilial() {
		return idFilial;
	}
	public void setIdFilial(Integer idFilial) {
		this.idFilial = idFilial;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}	
}
