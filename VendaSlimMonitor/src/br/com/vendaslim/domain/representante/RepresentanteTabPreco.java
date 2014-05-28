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
@Table(name="REPTABPRECO")
@IdClass(RepresentanteTabPrecoPK.class)
public class RepresentanteTabPreco implements Domain{
	
	@Id
	private Filial filial;	
	@Id
	private Integer idTabPreco;
	@Id
	private Integer idRepresentante;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="IDEMPRESA",nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="IDFILIAL",nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="IDREPRESENTANTE",nullable=false, insertable=false, updatable=false)
	})	
	private RepresentanteFilial representanteFilial;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({@JoinColumn(name="IDEMPRESA",insertable=false, updatable=false, nullable=false), 
		@JoinColumn(name="IDFILIAL",insertable=false, updatable=false,nullable=false), 
		@JoinColumn(name="IDTABELA",insertable=false, updatable=false,nullable=false)})	
	private TabelaPreco tabelaPreco;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dtHrAlteracao;
	
	
	public Filial getFilial() {
		return filial;
	}
	public void setFilial(Filial filial) {
		this.filial = filial;
	}
	
	public RepresentanteFilial getRepresentanteFilial() {
		return representanteFilial;
	}
	public void setRepresentanteFilial(RepresentanteFilial representanteFilial) {
		if(representanteFilial != null && representanteFilial.getRepresentante() != null 
				&& representanteFilial.getRepresentante().getIdRepresentante() != null)
			setIdRepresentante(representanteFilial.getRepresentante().getIdRepresentante());
		this.representanteFilial = representanteFilial;
	}
	public TabelaPreco getTabelaPreco() {
		return tabelaPreco;
	}
	public void setTabelaPreco(TabelaPreco tabelaPreco) {
		if(tabelaPreco != null && tabelaPreco.getIdTabelaPreco() != null)
			setIdTabPreco(tabelaPreco.getIdTabelaPreco());
		this.tabelaPreco = tabelaPreco;
	}
	public Integer getIdTabPreco() {
		return idTabPreco;
	}
	public void setIdTabPreco(Integer idTabPreco) {
		this.idTabPreco = idTabPreco;
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
