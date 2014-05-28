package br.com.vendaslim.domain.cadastro;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class MensagemPK implements Serializable{
	
	@ManyToOne
	@JoinColumn(name="IDEMPRESA")
	private Empresa empresa;
	
	@Column(name="IDMENSAGEM")
	private Integer idMensagem;

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Integer getIdMensagem() {
		return idMensagem;
	}

	public void setIdMensagem(Integer idMensagem) {
		this.idMensagem = idMensagem;
	}
}
