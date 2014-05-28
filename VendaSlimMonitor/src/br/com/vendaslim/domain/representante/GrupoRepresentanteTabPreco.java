package br.com.vendaslim.domain.representante;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.vendaslim.domain.Domain;
import br.com.vendaslim.domain.cadastro.Filial;
import br.com.vendaslim.domain.pedido.TabelaPreco;

@Entity
@Table(name="GRUPREPTABPRECO")
@IdClass(GrupoRepresentanteTabPrecoPK.class)
public class GrupoRepresentanteTabPreco implements Domain {
	
	@Id
	private Filial filial;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="IDEMPRESA",nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="IDFILIAL",nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="IDGRUPO",nullable=false, insertable=false, updatable=false)
	})	
	private GrupoRepresentante grupoRepresentante;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumns({@JoinColumn(name="IDEMPRESA",insertable=false, updatable=false, nullable=false), 
		@JoinColumn(name="IDFILIAL",insertable=false, updatable=false,nullable=false), 
		@JoinColumn(name="IDTABELA",insertable=false, updatable=false,nullable=false)})	
	private TabelaPreco tabelaPreco;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dtHrAlteracao;
	
	@Id	
	private Integer idTabPreco;
	
	@Id
	private Integer idGrupo;
	
	
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

	public Filial getFilial() {
		return filial;
	}

	public void setFilial(Filial filial) {
		this.filial = filial;
	}

	public TabelaPreco getTabelaPreco() {
		return tabelaPreco;
	}

	public void setTabelaPreco(TabelaPreco tabelaPreco) {
		this.tabelaPreco = tabelaPreco;
	}

	public GrupoRepresentante getGrupoRepresentante() {
		return grupoRepresentante;
	}

	public void setGrupoRepresentante(GrupoRepresentante grupoRepresentante) {
		this.grupoRepresentante = grupoRepresentante;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((filial == null) ? 0 : filial.hashCode());
		result = prime
				* result
				+ ((grupoRepresentante == null) ? 0 : grupoRepresentante
						.hashCode());
		result = prime * result + ((idGrupo == null) ? 0 : idGrupo.hashCode());
		result = prime * result
				+ ((idTabPreco == null) ? 0 : idTabPreco.hashCode());
		result = prime * result
				+ ((tabelaPreco == null) ? 0 : tabelaPreco.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GrupoRepresentanteTabPreco other = (GrupoRepresentanteTabPreco) obj;
		if (filial == null) {
			if (other.filial != null)
				return false;
		} else if (!filial.equals(other.filial))
			return false;
		if (grupoRepresentante == null) {
			if (other.grupoRepresentante != null)
				return false;
		} else if (!grupoRepresentante.equals(other.grupoRepresentante))
			return false;
		if (idGrupo == null) {
			if (other.idGrupo != null)
				return false;
		} else if (!idGrupo.equals(other.idGrupo))
			return false;
		if (idTabPreco == null) {
			if (other.idTabPreco != null)
				return false;
		} else if (!idTabPreco.equals(other.idTabPreco))
			return false;
		if (tabelaPreco == null) {
			if (other.tabelaPreco != null)
				return false;
		} else if (!tabelaPreco.equals(other.tabelaPreco))
			return false;
		return true;
	}

	public Calendar getDtHrAlteracao() {
		return dtHrAlteracao;
	}

	public void setDtHrAlteracao(Calendar dtHrAlteracao) {
		this.dtHrAlteracao = dtHrAlteracao;
	}

	
	
	
}
