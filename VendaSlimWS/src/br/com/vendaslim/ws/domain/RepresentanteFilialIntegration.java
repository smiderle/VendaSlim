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
@Table(name="REPFILIAL")
@IdClass(RepresentanteFilialPK.class)
public class RepresentanteFilialIntegration implements Domain{
	
	@Id
	private Integer idEmpresa;
	@Id
	private Integer idFilial;
	@Id	
	private Integer idRepresentante;
	
	@Column
	private Integer idGrupo;
	@Column
	private Double descMax;
	@Column
	private Double comicaoVenda;
	@Column(nullable=false)
	private boolean visualizaTodosClientes;
	@Column
	private Double minVenda;
	
	@Column(name="GRUPOPRODUTO")
	private String gruposProdutosStr;
	
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

	public Integer getIdRepresentante() {
		return idRepresentante;
	}

	public void setIdRepresentante(Integer idRepresentante) {
		this.idRepresentante = idRepresentante;
	}

	public Integer getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}

	public Double getDescMax() {
		return descMax;
	}

	public void setDescMax(Double descMax) {
		this.descMax = descMax;
	}

	public Double getComicaoVenda() {
		return comicaoVenda;
	}

	public void setComicaoVenda(Double comicaoVenda) {
		this.comicaoVenda = comicaoVenda;
	}

	public boolean isVisualizaTodosClientes() {
		return visualizaTodosClientes;
	}

	public void setVisualizaTodosClientes(boolean visualizaTodosClientes) {
		this.visualizaTodosClientes = visualizaTodosClientes;
	}

	public Double getMinVenda() {
		return minVenda;
	}

	public void setMinVenda(Double minVenda) {
		this.minVenda = minVenda;
	}

	public String getGruposProdutosStr() {
		return gruposProdutosStr;
	}

	public void setGruposProdutosStr(String gruposProdutosStr) {
		this.gruposProdutosStr = gruposProdutosStr;
	}

	public Calendar getDtHrAlteracao() {
		return dtHrAlteracao;
	}

	public void setDtHrAlteracao(Calendar dtHrAlteracao) {
		this.dtHrAlteracao = dtHrAlteracao;
	}
	
	
	
}
