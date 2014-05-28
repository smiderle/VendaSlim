package br.com.vendaslim.ws.domain;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="TABPRECO")
@IdClass(TabelaPrecoPK.class)
public class TabelaPrecoIntegration implements Domain{
	
	@Id
	private Integer idEmpresa;
	
	@Id
	private Integer idFilial;
	
	@Id	
	private Integer idTabPreco;
			
	@Column(name="DESCRICAO", length=40)
	private String descricao;
	
	@Column(name="PERCENTUAL")
	private Double percentual;
	
	@Column(name="ACRESCIMO")
	private boolean acrescimo;
	
	@Column(name="INATIVO")	
	private boolean inativo;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dtHrAlteracao;

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getPercentual() {
		return percentual;
	}

	public void setPercentual(Double percentual) {
		this.percentual = percentual;
	}

	public boolean isAcrescimo() {
		return acrescimo;
	}

	public void setAcrescimo(boolean acrescimo) {
		this.acrescimo = acrescimo;
	}

	public boolean isInativo() {
		return inativo;
	}

	public void setInativo(boolean inativo) {
		this.inativo = inativo;
	}

	public Calendar getDtHrAlteracao() {
		return dtHrAlteracao;
	}

	public void setDtHrAlteracao(Calendar dtHrAlteracao) {
		this.dtHrAlteracao = dtHrAlteracao;
	}
	
}
