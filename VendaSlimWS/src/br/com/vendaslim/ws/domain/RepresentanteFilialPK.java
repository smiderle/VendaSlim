package br.com.vendaslim.ws.domain;

import java.io.Serializable;

import javax.persistence.Column;

public class RepresentanteFilialPK implements Serializable{
	
	@Column
	private Integer idEmpresa;
	@Column
	private Integer idFilial;
	@Column	
	private Integer idRepresentante;

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

	public Integer getIdRepresentante() {
		return idRepresentante;
	}

	public void setIdRepresentante(Integer idRepresentante) {
		this.idRepresentante = idRepresentante;
	}
	
	
}
