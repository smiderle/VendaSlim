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
@Table(name="PRODUTO")
@IdClass(ProdutoPK.class)
public class ItemIntegration implements Domain{
	

	@Id
	private Integer idEmpresa;
	
	@Id
	private Integer idFilial;
	
	@Id
	private Integer idItem;
	
	@Column
	private Integer idGrupo;
	@Column
	private String descricao;	
	@Column
	private String referencia;
	@Column
	private String codbar;
	@Column(name="quantidade")
	private Double estoque;
	@Column
	private Double descMax;
	@Column
	private Double precoVenda;
	@Column
	private Double precoPromocao;
	@Column
	private boolean promocao;
	@Column
	private boolean inativo;	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dtHrAlteracao;
	
	@Column
	private String unidade;
	
	
	
	

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getDescMax() {
		return descMax;
	}

	public void setDescMax(Double descMax) {
		this.descMax = descMax;
	}

	public Calendar getDtHrAlteracao() {
		return dtHrAlteracao;
	}

	public void setDtHrAlteracao(Calendar dtHrAlteracao) {
		this.dtHrAlteracao = dtHrAlteracao;
	}
	public Integer getIdItem() {
		return idItem;
	}

	public void setIdItem(Integer idItem) {
		this.idItem = idItem;
	}

	public Integer getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getCodbar() {
		return codbar;
	}

	public void setCodbar(String codbar) {
		this.codbar = codbar;
	}

	

	public Double getEstoque() {
		return estoque;
	}

	public void setEstoque(Double estoque) {
		this.estoque = estoque;
	}

	public Double getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(Double precoVenda) {
		this.precoVenda = precoVenda;
	}

	public Double getPrecoPromocao() {
		return precoPromocao;
	}

	public void setPrecoPromocao(Double precoPromocao) {
		this.precoPromocao = precoPromocao;
	}

	public boolean isPromocao() {
		return promocao;
	}

	public void setPromocao(boolean promocao) {
		this.promocao = promocao;
	}

	public boolean isInativo() {
		return inativo;
	}

	public void setInativo(boolean inativo) {
		this.inativo = inativo;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}
	
	
}
