package br.com.vendaslim.domain.representante;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import br.com.vendaslim.domain.cadastro.Filial;

public class GrupoRepresentanteTabPrecoPK implements Serializable{
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({@JoinColumn(name="IDEMPRESA",nullable=false), @JoinColumn(name="IDFILIAL",nullable=false)})	
	private Filial filial;
	
	
	@Column(name="IDTABELA")	
	private Integer idTabPreco;
	
	@Column(name="IDGRUPO")	
	private Integer idGrupo;

	public Filial getFilial() {
		return filial;
	}

	public void setFilial(Filial filial) {
		this.filial = filial;
	}
	

	public Integer getIdTabPreco() {
		return idTabPreco;
	}

	public void setIdTabPreco(Integer idTabPreco) {
		this.idTabPreco = idTabPreco;
	}

	public Integer getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}

	
}
