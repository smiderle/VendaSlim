package br.com.vendaslim.ws.domain;

import java.io.Serializable;

import javax.persistence.Column;

public class PedidoItemPK implements Serializable{
	
	@Column
	private Integer idFilial;
	
	@Column
	private Integer idEmpresa;
			
	@Column
	private Integer idPedido;
	
	@Column
	private Integer sequencia;
	
	@Column
	private Integer idProduto;

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

	public Integer getSequencia() {
		return sequencia;
	}

	public void setSequencia(Integer sequencia) {
		this.sequencia = sequencia;
	}

	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}
	
	

}
