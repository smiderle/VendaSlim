package br.com.slim.venda.representante;


public class RepresentanteParcelamentoVO {

	private Integer idEmpresa;
	private Integer idFilial;
	private Integer idParcelamento;
	private Integer idRepresentante;
	
	
	public static final class RepresentanteParcelamento{
		private RepresentanteParcelamento() {}
		
		public static final String TABELA = "REPRESENTANTE_PARCELAMENTO";	
		public static final String IDEMPRESA = "IDEMPRESA";
		public static final String IDFILIAL = "IDFILIAL";
		public static final String IDREPRESENTANTE = "IDREPRESENTANTE";
		public static final String IDPARCELAMENTO = "IDPARCELAMENTO";
		
		
			
		public static final String[] COLUNAS = new String[]{IDEMPRESA,IDFILIAL, IDREPRESENTANTE, IDPARCELAMENTO};
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
	public Integer getIdRepresentante() {
		return idRepresentante;
	}
	public void setIdRepresentante(Integer idRepresentante) {
		this.idRepresentante = idRepresentante;
	}
}
