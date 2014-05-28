package br.com.vendaslim.domain.pedido;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import br.com.vendaslim.domain.Domain;
import br.com.vendaslim.domain.cadastro.Filial;

@Entity
@Table(name="PARCELAMENTO", uniqueConstraints={@UniqueConstraint(columnNames={"IDEMPRESA", "IDFILIAL", "DESCRICAO"},
name="UK_FILIAL_DESCRICAO")} )
@IdClass(ParcelamentoPK.class)
public class Parcelamento implements Domain{

	@Id
	private Filial filial;
	@Id
	private Integer idParcelamento;
	
	@Column(name="DESCRICAO", length=40)
	private String descricao;
	
	@Column(name="CARENCIA")
	private Integer carencia;
	
	@Column(name="NROPARCELA")
	private Integer numeroParcelas;
	
	@Column(name="DIASENTREPARCELA")
	private Integer diasEntreParcelas;
	
	@Column(name="PERCENTUAL")
	private Double percentualAcrescimo;
	
	@Column(name="INATIVO", nullable=false)
	private boolean inativo;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dtHrAlteracao;

	public Filial getFilial() {
		return filial;
	}

	public void setFilial(Filial filial) {
		this.filial = filial;
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

	public Integer getNumeroParcelas() {
		return numeroParcelas;
	}

	public void setNumeroParcelas(Integer numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}

	public Integer getDiasEntreParcelas() {
		return diasEntreParcelas;
	}

	public void setDiasEntreParcelas(Integer diasEntreParcelas) {
		this.diasEntreParcelas = diasEntreParcelas;
	}

	

	public Double getPercentualAcrescimo() {
		return percentualAcrescimo;
	}

	public void setPercentualAcrescimo(Double percentualAcrescimo) {
		this.percentualAcrescimo = percentualAcrescimo;
	}

	

	public boolean isInativo() {
		return inativo;
	}

	public void setInativo(boolean inativo) {
		this.inativo = inativo;
	}

	public Integer getIdParcelamento() {
		return idParcelamento;
	}

	public void setIdParcelamento(Integer idParcelamento) {
		this.idParcelamento = idParcelamento;
	}

	public Calendar getDtHrAlteracao() {
		return dtHrAlteracao;
	}

	public void setDtHrAlteracao(Calendar dtHrAlteracao) {
		this.dtHrAlteracao = dtHrAlteracao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (inativo ? 1231 : 1237);
		result = prime * result
				+ ((carencia == null) ? 0 : carencia.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime
				* result
				+ ((diasEntreParcelas == null) ? 0 : diasEntreParcelas
						.hashCode());
		result = prime * result + ((filial == null) ? 0 : filial.hashCode());
		result = prime * result
				+ ((idParcelamento == null) ? 0 : idParcelamento.hashCode());
		result = prime * result
				+ ((numeroParcelas == null) ? 0 : numeroParcelas.hashCode());
		long temp;
		temp = Double.doubleToLongBits(percentualAcrescimo);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Parcelamento other = (Parcelamento) obj;
		if (inativo != other.inativo)
			return false;
		if (carencia == null) {
			if (other.carencia != null)
				return false;
		} else if (!carencia.equals(other.carencia))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (diasEntreParcelas == null) {
			if (other.diasEntreParcelas != null)
				return false;
		} else if (!diasEntreParcelas.equals(other.diasEntreParcelas))
			return false;
		if (filial == null) {
			if (other.filial != null)
				return false;
		} else if (!filial.equals(other.filial))
			return false;
		if (idParcelamento == null) {
			if (other.idParcelamento != null)
				return false;
		} else if (!idParcelamento.equals(other.idParcelamento))
			return false;
		if (numeroParcelas == null) {
			if (other.numeroParcelas != null)
				return false;
		} else if (!numeroParcelas.equals(other.numeroParcelas))
			return false;
		if (Double.doubleToLongBits(percentualAcrescimo) != Double
				.doubleToLongBits(other.percentualAcrescimo))
			return false;
		return true;
	}
	
	
	
	
}
