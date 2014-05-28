package br.com.vendaslim.domain.representante;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import br.com.vendaslim.domain.cadastro.Filial;


public class GrupoRepresentantePK implements Serializable{

	@ManyToOne	
	@JoinColumns(
			{
				@JoinColumn(name="idempresa", referencedColumnName="idempresa"),
				@JoinColumn(name="idfilial", referencedColumnName="idfilial")
			}
			)
	private Filial filial;
	
	@Column(name="IDGRUPO")
	private Integer idGrupo;

	public Integer getIdGrupo() {
		return idGrupo;
	}
	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}
	public Filial getFilial() {
		return filial;
	}
	public void setFilial(Filial filial) {
		this.filial = filial;
	}
	
}
