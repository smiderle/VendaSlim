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
@Table(name="GRUPREP")
@IdClass(GrupoRepresentantePK.class)
public class GrupoRepresentanteIntegration implements Domain{

	@Id
	private Integer idEmpresa;
	@Id
	private Integer idFilial;
	@Id
	private Integer idGrupo;
	
	@Column
	private String descricao;
	
	@Column
	private Double descMax;
	
	@Column	
	private Double minVenda;
	
	@Column	
	private Double comicaoVenda;
		
	@Column
	private Boolean visualizaTodosClientes;		
	
	@Column	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dtHrAlteracao;
		
	@Column(name="GRUPOPRODUTO")
	private String gruposProdutosStr;

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

	public Integer getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
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

	public Double getMinVenda() {
		return minVenda;
	}

	public void setMinVenda(Double minVenda) {
		this.minVenda = minVenda;
	}

	public Double getComicaoVenda() {
		return comicaoVenda;
	}

	public void setComicaoVenda(Double comicaoVenda) {
		this.comicaoVenda = comicaoVenda;
	}

	public Boolean getVisualizaTodosClientes() {
		return visualizaTodosClientes;
	}

	public void setVisualizaTodosClientes(Boolean visualizaTodosClientes) {
		this.visualizaTodosClientes = visualizaTodosClientes;
	}

	public Calendar getDtHrAlteracao() {
		return dtHrAlteracao;
	}

	public void setDtHrAlteracao(Calendar dtHrAlteracao) {
		this.dtHrAlteracao = dtHrAlteracao;
	}

	public String getGruposProdutosStr() {
		return gruposProdutosStr;
	}

	public void setGruposProdutosStr(String gruposProdutosStr) {
		this.gruposProdutosStr = gruposProdutosStr;
	}
}
