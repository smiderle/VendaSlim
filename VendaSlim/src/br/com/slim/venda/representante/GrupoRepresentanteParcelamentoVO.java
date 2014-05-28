package br.com.slim.venda.representante;


public class GrupoRepresentanteParcelamentoVO {
	
	private Integer idEmpresa;	
	private Integer idFilial;
	private Integer idParcelamento;	
	private Integer idGrupo;
	
	public static final class GrupoRepresentanteParcelamento{
		private GrupoRepresentanteParcelamento() {}
		
		public static final String TABELA = "GRUPREPPARCELA";	
		public static final String IDEMPRESA = "IDEMPRESA";
		public static final String IDFILIAL = "IDFILIAL";		
		public static final String IDGRUPO = "IDGRUPO";
		public static final String IDPARCELA = "IDPARCELA";
		
			
		public static final String[] COLUNAS = new String[]{IDEMPRESA,IDFILIAL,IDGRUPO,IDPARCELA};
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

	public Integer getIdParcelamento() {
		return idParcelamento;
	}

	public void setIdParcelamento(Integer idParcelamento) {
		this.idParcelamento = idParcelamento;
	}

	public Integer getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}
}
