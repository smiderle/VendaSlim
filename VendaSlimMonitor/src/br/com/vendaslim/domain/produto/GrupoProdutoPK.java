package br.com.vendaslim.domain.produto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import br.com.vendaslim.domain.cadastro.Filial;

public class GrupoProdutoPK implements Serializable{
	
	@ManyToOne(fetch=FetchType.LAZY)	
	@JoinColumns(
			{
				@JoinColumn(name="idempresa"),
				@JoinColumn(name="idfilial")
			}
			)
	private Filial filial;
	
	@Column
	private Integer idGrupo;

	public Filial getFilial() {
		return filial;
	}

	public void setFilial(Filial filial) {
		this.filial = filial;
	}

	public Integer getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}
}
