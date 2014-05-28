package br.com.vendaslim.domain.pedidoItem;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import br.com.vendaslim.domain.cadastro.Filial;
import br.com.vendaslim.domain.produto.Produto;

public class PedidoItemPK implements Serializable{
	
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
	
	@Column
	private Integer sequencia;

	@ManyToOne
	@JoinColumns(
			{
				@JoinColumn(name="IDEMPRESA", referencedColumnName="IDEMPRESA", insertable=false, updatable=false),
				@JoinColumn(name="IDFILIAL", referencedColumnName="IDFILIAL", insertable=false, updatable=false),
				@JoinColumn(name="IDPRODUTO", referencedColumnName="IDPRODUTO", insertable=false, updatable=false)
			}
			)
	private Produto produto;
	
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

	public Integer getSequencia() {
		return sequencia;
	}

	public void setSequencia(Integer sequencia) {
		this.sequencia = sequencia;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	
	

}
