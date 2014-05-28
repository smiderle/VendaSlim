package br.com.vendaslim.domain.representante;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.vendaslim.domain.cadastro.Empresa;

public class RepresentanteRotaPK implements Serializable{
	
	@ManyToOne
	@JoinColumn(name="IDEMPRESA")
	private Empresa empresa;
	
	@Column(name="IDREPRESENTANTE")
	private Integer idRepresentante;
	
	@Column(name="DTHRROTA")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dtHrRota;

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
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
