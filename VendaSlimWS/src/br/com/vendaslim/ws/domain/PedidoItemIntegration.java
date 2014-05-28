package br.com.vendaslim.ws.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="PEDIDO_ITEM")
@IdClass(PedidoItemPK.class)
public class PedidoItemIntegration implements Domain{

	@Id
	private Integer idFilial;
	
	@Id
	private Integer idEmpresa;
			
	@Id
	private Integer idPedido;
	
	@Id
	private Integer sequencia;
	
	@Id
	private Integer idProduto;
	
	@Column			
	private Double quantidade;
	@Column
	private Double precoVenda;
	@Column
	private Double desconto;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns(
			{
				@JoinColumn(name="IDEMPRESA", insertable=false, updatable=false),
				@JoinColumn(name="IDFILIAL", insertable=false, updatable=false),
				@JoinColumn(name="IDPEDIDO", insertable=false, updatable=false)
			}
			)
	private PedidoIntegration pedidoIntegration;
	
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
	public Double getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}
	public Double getPrecoVenda() {
		return precoVenda;
	}
	public void setPrecoVenda(Double precoVenda) {
		this.precoVenda = precoVenda;
	}
	public Double getDesconto() {
		return desconto;
	}
	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}
	
	
	
}
