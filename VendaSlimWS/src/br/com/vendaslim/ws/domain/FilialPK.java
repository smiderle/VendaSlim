package br.com.vendaslim.ws.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



public class FilialPK implements Serializable{
		
	@Column(name="IDEMPRESA")
	private Integer idEmpresa;
	
	@Column(name="IDFILIAL")
	private Integer idFilial;
	
	public Integer getIdFilial() {
		return idFilial;
	}
	public void setIdFilial(Integer idFilial) {
		this.idFilial = idFilial;
	}
	public Integer getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
		
}
