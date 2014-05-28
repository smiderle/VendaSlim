package br.com.slim.venda.representante;


public class RepresentanteTabPrecoVO {	
	
	private Integer idEmpresa;
	private Integer idFilial;	
	private Integer idTabPreco;	
	private Integer idRepresentante;
	
	public static final class RepresentanteTabPreco{
		private RepresentanteTabPreco() {}
		
		public static final String TABELA = "REPRESENTANTE_TABPRECO";	
		public static final String IDEMPRESA = "IDEMPRESA";
		public static final String IDFILIAL = "IDFILIAL";
		public static final String IDREPRESENTANTE = "IDREPRESENTANTE";
		public static final String IDTABELAPRECO = "IDTABELAPRECO";
					
		public static final String[] COLUNAS = new String[]{IDEMPRESA,IDFILIAL, IDREPRESENTANTE, IDTABELAPRECO};
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
	public Integer getIdRepresentante() {
		return idRepresentante;
	}
	public void setIdRepresentante(Integer idRepresentante) {
		this.idRepresentante = idRepresentante;
	}
}