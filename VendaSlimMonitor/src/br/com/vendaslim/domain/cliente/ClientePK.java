package br.com.vendaslim.domain.cliente;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import br.com.vendaslim.domain.cadastro.Filial;

public class ClientePK implements Serializable{

	@ManyToOne
	@JoinColumns(
			{
				@JoinColumn(name="IDEMPRESA", referencedColumnName="IDEMPRESA"),
				@JoinColumn(name="IDFILIAL", referencedColumnName="IDFILIAL")
			}
			)
	private Filial filial;
	
	@Column(name="idCliente")
	private Integer idCliente;

	

	public Filial getFilial() {
		return filial;
	}

	public void setFilial(Filial filial) {
		this.filial = filial;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
}