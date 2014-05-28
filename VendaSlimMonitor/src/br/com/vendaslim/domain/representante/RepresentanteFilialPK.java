package br.com.vendaslim.domain.representante;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import br.com.vendaslim.domain.cadastro.Filial;

public class RepresentanteFilialPK implements Serializable{
	
	@ManyToOne(fetch = FetchType.EAGER)	
	@JoinColumns(
			{
				@JoinColumn(name="IDEMPRESA"),
				@JoinColumn(name="IDFILIAL")
			}
			)
	private Filial filial;

	/*@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns(
			{
				@JoinColumn(name="IDEMPRESA"),
				@JoinColumn(name="IDREPRESENTANTE")
			}
			)
	private Representante representante;*/
	
	private Integer idRepresentante;
	
	public Filial getFilial() {
		return filial;
	}
	public void setFilial(Filial filial) {
		this.filial = filial;
	}
	public Integer getIdRepresentante() {
		return idRepresentante;
	}
	public void setIdRepresentante(Integer idRepresentante) {
		this.idRepresentante = idRepresentante;
	}
	
}
