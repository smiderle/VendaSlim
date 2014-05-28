package br.com.vendaslim.domain.representante;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import br.com.vendaslim.domain.cadastro.Filial;

public class RepresentanteTabPrecoPK implements Serializable{
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({@JoinColumn(name="IDEMPRESA",nullable=false), @JoinColumn(name="IDFILIAL",nullable=false)})	
	private Filial filial;	
	
	@Column(name="IDTABELA")	
	private Integer idTabPreco;
	
	@Column(name="IDREPRESENTANTE")	
	private Integer idRepresentante;

	public Filial getFilial() {
		return filial;
	}

	public void setFilial(Filial filial) {
		this.filial = filial;
	}
/*
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
	}*/

	public Integer getIdTabPreco() {
		return idTabPreco;
	}

	public void setIdTabPreco(Integer idTabPreco) {
		this.idTabPreco = idTabPreco;
	}

	public Integer getIdRepresentante() {
		return idRepresentante;
	}

	public void setIdRepresentante(Integer idRepresentante) {
		this.idRepresentante = idRepresentante;
	}

}
