package br.com.vendaslim.domain.pedido;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.vendaslim.domain.Domain;
import br.com.vendaslim.domain.cadastro.Filial;
import br.com.vendaslim.domain.cliente.Cliente;
import br.com.vendaslim.domain.pedidoItem.PedidoItem;
import br.com.vendaslim.domain.representante.RepresentanteFilial;


@Entity
@Table(name="PEDIDO")
@IdClass(PedidoPK.class)
public class Pedido implements Domain{
	
	@Id
	private Filial filial;
	@Id
	private Integer idPedido;
	
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="IDEMPRESA",referencedColumnName="IDEMPRESA", insertable=false, updatable=false),
		@JoinColumn(name="IDFILIAL",referencedColumnName="IDFILIAL", insertable=false, updatable=false),
		@JoinColumn(name="IDCLIENTE", referencedColumnName="IDCLIENTE", insertable=false, updatable=false),		
	})
	private Cliente cliente;
	
	
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="IDEMPRESA",referencedColumnName="IDEMPRESA", insertable=false, updatable=false),
		@JoinColumn(name="IDFILIAL",referencedColumnName="IDFILIAL", insertable=false, updatable=false),
		@JoinColumn(name="IDREPRESENTANTE", referencedColumnName="IDREPRESENTANTE", insertable=false, updatable=false),		
	})
	private RepresentanteFilial representanteFilial;
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="IDEMPRESA",referencedColumnName="IDEMPRESA", insertable=false, updatable=false),
		@JoinColumn(name="IDFILIAL",referencedColumnName="IDFILIAL", insertable=false, updatable=false),
		@JoinColumn(name="TABELAPRECO", referencedColumnName="IDTABELA", insertable=false, updatable=false),		
	})
	private TabelaPreco tabelaPreco;
	
	
	@ManyToOne
	@JoinColumns({		
		@JoinColumn(name="IDEMPRESA",referencedColumnName="IDEMPRESA", insertable=false, updatable=false),
		@JoinColumn(name="IDFILIAL",referencedColumnName="IDFILIAL", insertable=false, updatable=false),
		@JoinColumn(name="PARCELAMENTO", referencedColumnName="IDPARCELAMENTO", insertable=false, updatable=false),		
	})
	private Parcelamento parcelamento;
	
	
	@OneToMany
	@JoinColumns({		
		@JoinColumn(name="IDEMPRESA",referencedColumnName="IDEMPRESA", insertable=false, updatable=false),
		@JoinColumn(name="IDFILIAL",referencedColumnName="IDFILIAL", insertable=false, updatable=false),
		@JoinColumn(name="IDPEDIDO", referencedColumnName="IDPEDIDO", insertable=false, updatable=false),		
	})
	private List<PedidoItem> pedidoItens;
	
	@Column(name="valor_bruto")
	private Double valorBruto;
	
	@Column(name="valor_liquido")
	private Double valorLiquido;
	
	@Column(name="desconto_total")
	private Double descontoTotal;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dtHrAlteracao;
	
	@Column
	private Status status;
	
	@Column
	private String observacao;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dtHrEmissao;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public RepresentanteFilial getRepresentanteFilial() {
		return representanteFilial;
	}

	public void setRepresentanteFilial(RepresentanteFilial representanteFilial) {
		this.representanteFilial = representanteFilial;
	}

	public TabelaPreco getTabelaPreco() {
		return tabelaPreco;
	}

	public void setTabelaPreco(TabelaPreco tabelaPreco) {
		this.tabelaPreco = tabelaPreco;
	}

	public Parcelamento getParcelamento() {
		return parcelamento;
	}

	public void setParcelamento(Parcelamento parcelamento) {
		this.parcelamento = parcelamento;
	}

	public Double getValorBruto() {
		return valorBruto;
	}

	public void setValorBruto(Double valorBruto) {
		this.valorBruto = valorBruto;
	}

	public Double getValorLiquido() {
		return valorLiquido;
	}

	public void setValorLiquido(Double valorLiquido) {
		this.valorLiquido = valorLiquido;
	}

	public Double getDescontoTotal() {
		return descontoTotal;
	}

	public void setDescontoTotal(Double descontoTotal) {
		this.descontoTotal = descontoTotal;
	}

	public Calendar getDtHrAlteracao() {
		return dtHrAlteracao;
	}

	public void setDtHrAlteracao(Calendar dtHrAlteracao) {
		this.dtHrAlteracao = dtHrAlteracao;
	}
		
	@Enumerated(EnumType.ORDINAL)
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Date getDtHrEmissao() {
		if(dtHrEmissao != null)
			return dtHrEmissao.getTime();
		else 
			return null;
	}

	public void setDtHrEmissao(Calendar dtHrEmissao) {
		this.dtHrEmissao = dtHrEmissao;
	}

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

	public List<PedidoItem> getPedidoItens() {
		return pedidoItens;
	}

	public void setPedidoItens(List<PedidoItem> pedidoItens) {
		this.pedidoItens = pedidoItens;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	
	
	
	

}
