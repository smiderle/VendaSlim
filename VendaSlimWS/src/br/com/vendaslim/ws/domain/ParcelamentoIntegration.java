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
@Table(name="PARCELAMENTO")
@IdClass(ParcelamentoPK.class)
public class ParcelamentoIntegration implements Domain {
	
		
	@Id
	private Integer idEmpresa;
	
	@Id
	private Integer idFilial;
	
	@Id
	private Integer idParcelamento;

	@Column(name="DESCRICAO", length=40)
	private String descricao;
	
	@Column(name="CARENCIA")
	private Integer carencia;
	
	@Column(name="NROPARCELA")
	private Integer nroParcela;
	
	@Column(name="DIASENTREPARCELA")
	private Integer diasEntreParcela;
	
	@Column(name="PERCENTUAL")
	private Double percentual;
	
	@Column(name="INATIVO", nullable=false)
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

	public Integer getIdParcelamento() {
		return idParcelamento;
	}

	public void setIdParcelamento(Integer idParcelamento) {
		this.idParcelamento = idParcelamento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getCarencia() {
		return carencia;
	}

	public void setCarencia(Integer carencia) {
		this.carencia = carencia;
	}

	public Integer getNroParcela() {
		return nroParcela;
	}

	public void setNroParcela(Integer nroParcela) {
		this.nroParcela = nroParcela;
	}

	public Integer getDiasEntreParcela() {
		return diasEntreParcela;
	}

	public void setDiasEntreParcela(Integer diasEntreParcela) {
		this.diasEntreParcela = diasEntreParcela;
	}

	public Double getPercentual() {
		return percentual;
	}

	public void setPercentual(Double percentual) {
		this.percentual = percentual;
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
