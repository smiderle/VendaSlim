package br.com.vendaslim.ws.domain;

import java.io.Serializable;

import javax.persistence.Column;

public class ProdutoPK implements Serializable{

	
	@Column
	private Integer idEmpresa;
	
	@Column
	private Integer idFilial;
	
	@Column(name="idProduto")
	private Integer idItem;

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

	public Integer getIdItem() {
		return idItem;
	}

	public void setIdItem(Integer idItem) {
		this.idItem = idItem;
	}

	
		
}
