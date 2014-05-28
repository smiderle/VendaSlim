package br.com.vendaslim.domain.pedido;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import br.com.vendaslim.domain.cadastro.Filial;

public class ParcelamentoPK implements Serializable{
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns(
			{
				@JoinColumn(name="idempresa"),
				@JoinColumn(name="idfilial")
			}
			)
	private Filial filial;
	
	@Column(name="IDPARCELAMENTO")
	private Integer idParcelamento;

	public Filial getFilial() {
		return filial;
	}

	public void setFilial(Filial filial) {
		this.filial = filial;
	}

	public Integer getIdParcelamento() {
		return idParcelamento;
	}

	public void setIdParcelamento(Integer idParcelamento) {
		this.idParcelamento = idParcelamento;
	}
	
	
	
}
