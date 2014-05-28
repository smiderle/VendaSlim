package br.com.slim.venda.representante;

public class RepresentanteFilialVO {
	
	private Integer idEmpresa;
	private Integer idFilial;
	private Integer idRepresentante;
	private Integer idGrupo;
	private Double descMax;
	private Double comicaoVenda;
	private boolean visualizaTodosClientes;
	private Double minVenda;
	private String gruposProdutosStr;
	

	public static final class RepresentanteFilial{
		private RepresentanteFilial() {}
		
		public static final String TABELA = "REPRESENTANTE_FILIAL";	
		public static final String IDEMPRESA = "IDEMPRESA";
		public static final String IDFILIAL = "IDFILIAL";
		public static final String IDREPRESENTANTE = "IDREPRESENTANTE";
		public static final String IDGRUPO = "IDGRUPO";
		public static final String DESCMAX = "DESCMAX";
		public static final String COMICAOVENDA = "COMICAOVENDA";
		public static final String MINVENDA = "MINVENDA";
		public static final String VISUALIZATODOSCLIENTES = "VISUALIZATODOSCLIENTES";
		public static final String GRUPOSPRODUTO = "GRUPOSPRODUTO";
		
			
		public static final String[] COLUNAS = new String[]{IDEMPRESA,IDFILIAL,IDGRUPO, IDREPRESENTANTE, DESCMAX, MINVENDA, COMICAOVENDA, VISUALIZATODOSCLIENTES, GRUPOSPRODUTO};
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
	
	
	
}
