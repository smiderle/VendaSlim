package br.com.vendaslim.ws.domain;

import java.io.Serializable;

import javax.persistence.Column;

public class PedidoPK implements Serializable{
	
	@Column
	private Integer idFilial;
	
	@Column
	private Integer idEmpresa;
			
	@Column
	private Integer idPedido;

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

	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	
	
}
