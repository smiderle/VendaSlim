package br.com.slim.venda.integration.domain;


public class GrupoRepresentanteIntegration {

	private Integer idEmpresa;
	private Integer idFilial;
	private Integer idGrupo;
	private String descricao;
	private Double descMax;	
	private Double minVenda;	
	private Double comicaoVenda;
	private Boolean visualizaTodosClientes;
	
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
}
