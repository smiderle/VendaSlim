package br.com.vendaslim.domain.pedido;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import br.com.vendaslim.domain.cadastro.Filial;

public class PedidoPK implements Serializable{
	
	@ManyToOne(fetch = FetchType.EAGER)	
	@JoinColumns(
			{
				@JoinColumn(name="IDEMPRESA", referencedColumnName="IDEMPRESA"),
				@JoinColumn(name="IDFILIAL", referencedColumnName="IDFILIAL")
			}
			)
	private Filial filial;
	
	@Column
	private Integer idPedido;

	public Filial getFilial() {
		return filial;
	}

	public void setFilial(Filial filial) {
		this.filial = filial;
	}

	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}
}
