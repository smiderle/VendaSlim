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
@Table(name="TABPRECO", uniqueConstraints={@UniqueConstraint(columnNames={"IDEMPRESA", "IDFILIAL", "DESCRICAO"},
	name="UK_PARCELAMENTO_DESCRICAO")} )
@IdClass(TabelaPrecoPK.class)
public class TabelaPreco implements Domain {
	
	@Id
	private Filial filial;
	@Id
	private Integer idTabelaPreco;
	
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

	public Filial getFilial() {
		return filial;
	}

	public void setFilial(Filial filial) {
		this.filial = filial;
	}

	public Integer getIdTabelaPreco() {
		return idTabelaPreco;
	}

	public void setIdTabelaPreco(Integer idTabelaPreco) {
		this.idTabelaPreco = idTabelaPreco;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (acrescimo ? 1231 : 1237);
		result = prime * result + (inativo ? 1231 : 1237);
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((filial == null) ? 0 : filial.hashCode());
		result = prime * result
				+ ((idTabelaPreco == null) ? 0 : idTabelaPreco.hashCode());
		long temp;
		temp = Double.doubleToLongBits(percentual);
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
		TabelaPreco other = (TabelaPreco) obj;
		if (acrescimo != other.acrescimo)
			return false;
		if (inativo != other.inativo)
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (filial == null) {
			if (other.filial != null)
				return false;
		} else if (!filial.equals(other.filial))
			return false;
		if (idTabelaPreco == null) {
			if (other.idTabelaPreco != null)
				return false;
		} else if (!idTabelaPreco.equals(other.idTabelaPreco))
			return false;
		if (Double.doubleToLongBits(percentual) != Double
				.doubleToLongBits(other.percentual))
			return false;
		return true;
	}
	
	
}
