package br.com.slim.venda.representante;


public class GrupoRepresentanteTabPrecoVO {
	
	private Integer idEmpresa;
	private Integer idFilial;	
	private Integer idTabPreco;
	private Integer idGrupo;

	public static final class GrupoRepresentanteTabPreco{
		private GrupoRepresentanteTabPreco() {}
		
		public static final String TABELA = "GRUPREPTABPRECO";	
		public static final String IDEMPRESA = "IDEMPRESA";
		public static final String IDFILIAL = "IDFILIAL";		
		public static final String IDGRUPO = "IDGRUPO";
		public static final String IDTABELA = "IDTABELA";
		
			
		public static final String[] COLUNAS = new String[]{IDEMPRESA,IDFILIAL,IDGRUPO,IDTABELA};
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

	public Integer getIdTabPreco() {
		return idTabPreco;
	}

	public void setIdTabPreco(Integer idTabPreco) {
		this.idTabPreco = idTabPreco;
	}

	public Integer getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}

}
