package br.com.vendaslim.ws.domain;

import java.io.Serializable;

import javax.persistence.Column;

public class MensagemPK implements Serializable{
	
	@Column
	private Integer idEmpresa;
	
	@Column(name="IDMENSAGEM")
	private Integer idMensagem;

	public Integer getIdMensagem() {
		return idMensagem;
	}

	public void setIdMensagem(Integer idMensagem) {
		this.idMensagem = idMensagem;
	}

	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	
	
}
