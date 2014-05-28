package br.com.vendaslim.ws.domain;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="REPPARCELA")
@IdClass(RepresentanteParcelamentoPK.class)
public class RepresentanteParcelamentoIntegration implements Domain{
	
	@Id
	private Integer idEmpresa;
	
	@Id
	private Integer idFilial;	
	
	@Id
	private Integer idParcelamento;
	
	@Id
	private Integer idRepresentante;
	
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

	public Integer getIdRepresentante() {
		return idRepresentante;
	}

	public void setIdRepresentante(Integer idRepresentante) {
		this.idRepresentante = idRepresentante;
	}

	public Calendar getDtHrAlteracao() {
		return dtHrAlteracao;
	}

	public void setDtHrAlteracao(Calendar dtHrAlteracao) {
		this.dtHrAlteracao = dtHrAlteracao;
	}
}
