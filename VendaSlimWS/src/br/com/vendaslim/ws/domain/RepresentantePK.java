package br.com.vendaslim.ws.domain;

import java.io.Serializable;

import javax.persistence.Column;


public class RepresentantePK implements Serializable{

	@Column(name="idempresa")			
	private Integer idEmpresa;
	
	@Column(name="idrepresentante")
	private Integer idRepresentante;

	
	
	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public Integer getIdRepresentante() {
		return idRepresentante;
	}

	public void setIdRepresentante(Integer idRepresentante) {
		this.idRepresentante = idRepresentante;
	}
	
	
		

}
