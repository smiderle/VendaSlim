package br.com.vendaslim.ws.domain;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;

public class RepresentanteRotaPK implements Serializable{

	@Column(name="idempresa")			
	private Integer idEmpresa;
	
	@Column(name="idrepresentante")
	private Integer idRepresentante;
		
	@Column(name="dthrrota")
	private Calendar dtHrRota;

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

	public Calendar getDtHrRota() {
		return dtHrRota;
	}

	public void setDtHrRota(Calendar dtHrRota) {
		this.dtHrRota = dtHrRota;
	}

	
	
}
