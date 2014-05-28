package br.com.vendaslim.ws.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


@Entity
@Table(name="PEDIDO")
@IdClass(PedidoPK.class)
public class PedidoIntegration implements Domain{
	
	@Id
	private Integer idFilial;
	
	@Id
	private Integer idEmpresa;
			
	@Id
	private Integer idPedido;
	
	/**
	 * Atributo criado para retornar para o miobile o novo id do pedido, 
	 * caso o id do pedido cadastrado no mobile já exista no banco do monitor.
	 */
	@Transient
	private Integer idPedidoMobile;
	
	@Column
	private Integer idCliente;
	
	@Column
	private Integer idRepresentante;
	
	@Column(name="tabelapreco")
	private Integer idTabelaPreco;
	
	@Column(name="parcelamento")
	private Integer idParcelamento;
		
	@Column(name="valor_bruto")
	private Double valorBruto;
	
	@Column(name="valor_liquido")
	private Double valorLiquido;
	
	@Column(name="desconto_total")
	private Double descontoTotal;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dtHrAlteracao;
	
	@Column
	private Integer status;
	
	@Column
	private String observacao;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dtHrEmissao;
	
	@OneToMany(mappedBy="pedidoIntegration", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<PedidoItemIntegration> lsPedidoItemIntegration;

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

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public Integer getIdRepresentante() {
		return idRepresentante;
	}

	public void setIdRepresentante(Integer idRepresentante) {
		this.idRepresentante = idRepresentante;
	}

	public Integer getIdTabelaPreco() {
		return idTabelaPreco;
	}

	public void setIdTabelaPreco(Integer idTabelaPreco) {
		this.idTabelaPreco = idTabelaPreco;
	}

	public Integer getIdParcelamento() {
		return idParcelamento;
	}

	public void setIdParcelamento(Integer idParcelamento) {
		this.idParcelamento = idParcelamento;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Calendar getDtHrEmissao() {
		return dtHrEmissao;
	}

	public void setDtHrEmissao(Calendar dtHrEmissao) {
		this.dtHrEmissao = dtHrEmissao;
	}

	public List<PedidoItemIntegration> getLsPedidoItemIntegration() {
		return lsPedidoItemIntegration;
	}

	public void setLsPedidoItemIntegration(
			List<PedidoItemIntegration> lsPedidoItemIntegration) {
		this.lsPedidoItemIntegration = lsPedidoItemIntegration;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Integer getIdPedidoMobile() {
		return idPedidoMobile;
	}

	public void setIdPedidoMobile(Integer idPedidoMobile) {
		this.idPedidoMobile = idPedidoMobile;
	}	
	
}
