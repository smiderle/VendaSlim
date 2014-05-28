package br.com.vendaslim.domain.pedidoItem;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.vendaslim.domain.Domain;
import br.com.vendaslim.domain.cadastro.Filial;
import br.com.vendaslim.domain.pedido.Pedido;
import br.com.vendaslim.domain.produto.Produto;


@Entity
@Table(name="PEDIDO_ITEM")
@IdClass(PedidoItemPK.class)
public class PedidoItem implements Domain{

	@Id
	private Filial filial;
	@Id
	private Produto produto;
	
	@Id
	private Integer idPedido;

	@Id
	private Integer sequencia;	
	@Column			
	private Double quantidade;
	@Column
	private Double precoVenda;
	@Column
	private Double desconto;
	
	//Preco de Venda * quantidade
	@Transient
	private Double valorTotal;

	public Filial getFilial() {
		return filial;
	}

	public void setFilial(Filial filial) {
		this.filial = filial;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
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

	public Double getValorTotal() {
		if(getPrecoVenda() != null && getQuantidade() != null)
			return getPrecoVenda() * getQuantidade();
		return null;
	}

	
}
