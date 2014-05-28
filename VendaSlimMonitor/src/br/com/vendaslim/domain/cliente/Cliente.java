package br.com.vendaslim.domain.cliente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.vendaslim.domain.Domain;
import br.com.vendaslim.domain.cadastro.Empresa;
import br.com.vendaslim.domain.cadastro.Filial;
import br.com.vendaslim.domain.cadastro.Pessoa;
import br.com.vendaslim.domain.pedido.Parcelamento;
import br.com.vendaslim.domain.pedido.TabelaPreco;
import br.com.vendaslim.domain.representante.Representante;

@Entity
@Table(name="CLIENTE")
@IdClass(ClientePK.class)
public class Cliente extends Pessoa implements Domain{

	@Id
	private Filial filial;
	
	
	@Id
	private Integer idCliente;
	
	@Column(name="parcelamento")
	private Integer idParcelamento;
	
	/*@Column(name="tabpreco")
	private Integer idTabPreco;*/
	
	@ManyToOne
	@JoinColumns(
			{
				@JoinColumn(name="IDEMPRESA", referencedColumnName="IDEMPRESA",   insertable=false, updatable=false),
				@JoinColumn(name="IDFILIAL", referencedColumnName="IDFILIAL", insertable=false, updatable=false),
				@JoinColumn(name="TABPRECO", referencedColumnName="IDTABELA",  insertable=false, updatable=false)
			}
			)
	private TabelaPreco tabelaPreco;
	
	@ManyToOne
	@JoinColumns(
			{
				@JoinColumn(name="IDEMPRESA", referencedColumnName="IDEMPRESA",   insertable=false, updatable=false),
				@JoinColumn(name="IDFILIAL", referencedColumnName="IDFILIAL", insertable=false, updatable=false),
				@JoinColumn(name="PARCELAMENTO", referencedColumnName="IDPARCELAMENTO",  insertable=false, updatable=false)
			}
			)
	private Parcelamento parcelamento;
	
	@Column(name="TABPRECO")
	private Integer idTabelaPreco;
		
	@Column
	private Double descMax;
	
	@Column(name="desconto_padrao")
	private Double descPadrao;
	
	@Column(name="limite_credito")
	private Double limiteCredito;

	@Column
	private String apelido;
	
	@ManyToOne
	@JoinColumns(
			{
				@JoinColumn(name="IDEMPRESA", referencedColumnName="IDEMPRESA",   insertable=false, updatable=false),				
				@JoinColumn(name="representante_padrao", referencedColumnName="IDREPRESENTANTE",  insertable=false, updatable=false)
			}
			)
	//@Column(name="representante_padrao")	
	private Representante representante;
	
	@Column(name="representante_padrao")
	private Integer idRepresentante;
	
	public Filial getFilial() {
		return filial;
	}

	public void setFilial(Filial filial) {
		this.filial = filial;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public Double getDescMax() {
		return descMax;
	}

	public void setDescMax(Double descMax) {
		this.descMax = descMax;
	}

	public Integer getIdParcelamento() {
		return idParcelamento;
	}

	public void setIdParcelamento(Integer idParcelamento) {
		this.idParcelamento = idParcelamento;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public TabelaPreco getTabelaPreco() {
		return tabelaPreco;
	}

	public void setTabelaPreco(TabelaPreco tabelaPreco) {
		if(tabelaPreco != null)
			setIdTabelaPreco(tabelaPreco.getIdTabelaPreco());
		else 
			setIdTabelaPreco(null);
		this.tabelaPreco = tabelaPreco;
	}

	public Integer getIdTabelaPreco() {
		return idTabelaPreco;
	}

	public void setIdTabelaPreco(Integer idTabelaPreco) {
		this.idTabelaPreco = idTabelaPreco;
	}

	public Parcelamento getParcelamento() {
		return parcelamento;
	}

	public void setParcelamento(Parcelamento parcelamento) {
		if(parcelamento != null)
			setIdParcelamento(parcelamento.getIdParcelamento());
		else
			setIdParcelamento(null);
		this.parcelamento = parcelamento;
	}

	public Double getLimiteCredito() {
		return limiteCredito;
	}

	public void setLimiteCredito(Double limiteCredito) {
		this.limiteCredito = limiteCredito;
	}

	public Double getDescPadrao() {
		return descPadrao;
	}

	public void setDescPadrao(Double descPadrao) {
		this.descPadrao = descPadrao;
	}

	public Representante getRepresentante() {
		return representante;
	}

	public void setRepresentante(Representante representante) {
		if(representante != null)
			setIdRepresentante(representante.getIdRepresentante());
		else 
			setIdRepresentante(null);
		this.representante = representante;
	}

	public Integer getIdRepresentante() {
		return idRepresentante;
	}

	public void setIdRepresentante(Integer idRepresentante) {
		this.idRepresentante = idRepresentante;
	}
	
	
	
	
}
