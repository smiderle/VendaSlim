package br.com.slim.venda.titulos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.slim.venda.parcelamento.ParcelamentoVO.Parcela;

public class Titulo {
	private int idPedido ;
	private int nrParcela ;
	private double valor ;
	private Date dtEmissao;
	ArrayList<Parcela> lsParcela;
	public int getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}
	public int getNrParcela() {
		return nrParcela;
	}
	public void setNrParcela(int nrParcela) {
		this.nrParcela = nrParcela;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public Date getDtEmissao() {
		return dtEmissao;
	}
	public void setDtEmissao(Date dtEmissao) {
		this.dtEmissao = dtEmissao;
	}
	public ArrayList<Parcela> getLsParcela() {
		return lsParcela;
	}
	public void setLsParcela(ArrayList<Parcela> lsParcela) {
		this.lsParcela = lsParcela;
	}
	
	
	

}
