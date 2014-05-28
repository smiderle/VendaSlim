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
import br.com.vendaslim.domain.pedido.Parcelamento;

@Entity
@Table(name="GRUPREPPARCELA")
@IdClass(GrupoRepresentanteParcelamentoPK.class)
public class GrupoRepresentanteParcelamento implements Domain{
	
	@Id	
	private Filial filial;	
	
	@Id
	private Integer idParcelamento;
	
	@Id	
	private Integer idGrupo;

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
		@JoinColumn(name="IDPARCELAMENTO",insertable=false, updatable=false,nullable=false)})	
	private Parcelamento parcelamento;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dtHrAlteracao;

	public Filial getFilial() {
		return filial;
	}

	public void setFilial(Filial filial) {
		this.filial = filial;
	}

	public GrupoRepresentante getGrupoRepresentante() {
		return grupoRepresentante;
	}

	public void setGrupoRepresentante(GrupoRepresentante grupoRepresentante) {
		this.grupoRepresentante = grupoRepresentante;
	}

	public Parcelamento getParcelamento() {
		return parcelamento;
	}

	public void setParcelamento(Parcelamento parcelamento) {
		this.parcelamento = parcelamento;
	}

	public Integer getIdParcelamento() {
		return idParcelamento;
	}

	public void setIdParcelamento(Integer idParcelamento) {
		this.idParcelamento = idParcelamento;
	}

	public Integer getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}

	public Calendar getDtHrAlteracao() {
		return dtHrAlteracao;
	}

	public void setDtHrAlteracao(Calendar dtHrAlteracao) {
		this.dtHrAlteracao = dtHrAlteracao;
	}
	
	
}
