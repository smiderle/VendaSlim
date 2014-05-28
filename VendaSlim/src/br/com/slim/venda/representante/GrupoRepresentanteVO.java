package br.com.slim.venda.representante;

public class GrupoRepresentanteVO {
	
	private Integer idEmpresa;
	private Integer idFilial;
	private Integer idGrupo;
	private String descricao;
	private Double descMax;	
	private Double minVenda;	
	private Double comicaoVenda;
	private Boolean visualizaTodosClientes;
	
	public static final class GrupoRepresentante{
		private GrupoRepresentante() {}
		
		public static final String TABELA = "GRUPO_REPRESENTANTE";	
		public static final String IDEMPRESA = "IDEMPRESA";
		public static final String IDFILIAL = "IDFILIAL";		
		public static final String IDGRUPO = "IDGRUPO";
		public static final String DESCRICAO = "DESCRICAO";
		public static final String DESCMAX = "DESCMAX";
		public static final String MINVENDA = "MINVENDA";
		public static final String COMICAOVENDA = "COMICAOVENDA";
			
		public static final String[] COLUNAS = new String[]{IDEMPRESA,IDFILIAL,IDGRUPO, DESCRICAO, DESCMAX, MINVENDA, COMICAOVENDA};
	}
	
		
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
