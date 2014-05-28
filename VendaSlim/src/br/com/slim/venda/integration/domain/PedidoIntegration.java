package br.com.slim.venda.integration.domain;

import java.util.ArrayList;
import java.util.Calendar;


public class PedidoIntegration {
	

	private Integer idFilial;
	private Integer idEmpresa;
	private Integer idPedido;
	private Integer idCliente;
	private Integer idRepresentante;
	private Integer idTabelaPreco;
	private Integer idParcelamento;
	private Double valorBruto;
	private Double valorLiquido;
	private Double descontoTotal;
	private Calendar dtHrAlteracao;
	private Integer status;
	private Calendar dtHrEmissao;
	private String observacao;
	
	private Integer idPedidoMobile;
	
	private ArrayList<PedidoItemIntegration> lsPedidoItemIntegration;

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

	public ArrayList<PedidoItemIntegration> getLsPedidoItemIntegration() {
		return lsPedidoItemIntegration;
	}

	public void setLsPedidoItemIntegration(
			ArrayList<PedidoItemIntegration> lsPedidoItemIntegration) {
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
