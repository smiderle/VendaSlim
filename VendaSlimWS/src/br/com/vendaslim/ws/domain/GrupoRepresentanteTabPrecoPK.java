package br.com.vendaslim.ws.domain;

import java.io.Serializable;

import javax.persistence.Column;

public class GrupoRepresentanteTabPrecoPK implements Serializable{
	
	@Column
	private Integer idEmpresa;
	
	@Column
	private Integer idFilial;
		
	@Column(name="IDTABELA")	
	private Integer idTabPreco;
	
	@Column(name="IDGRUPO")	
	private Integer idGrupo;

	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public Integer getIdFilial() {
		return idFilial;
	}

	public void setIdFilial(Integer idFilial) {
		this.idFilial = idFilial;
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
