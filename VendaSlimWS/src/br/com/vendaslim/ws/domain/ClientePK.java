package br.com.vendaslim.ws.domain;

import java.io.Serializable;

import javax.persistence.Column;

public class ClientePK implements Serializable{
	
	@Column
	private Integer idEmpresa;
	
	@Column
	private Integer idFilial;
	
	@Column
	private Integer idCliente;

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

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
}
