package br.com.slim.venda.sincroniza;

public class SincronizaVO {
	
	public static final String SINCRONIZADO = "T";
	public static final String NAO_SINCRONIZADO = "F";
	
	private String tabela;
	private long dtHrSincronizacao;
	private Integer idFilial;
	
	public String getTabela() {
		return tabela;
	}
	public void setTabela(String tabela) {
		this.tabela = tabela;
	}
	public long getDtHrSincronizacao() {
		return dtHrSincronizacao;
	}
	public void setDtHrSincronizacao(long dtHrSincronizacao) {
		this.dtHrSincronizacao = dtHrSincronizacao;
	}

	public Integer getIdFilial() {
		return idFilial;
	}
	public void setIdFilial(Integer idFilial) {
		this.idFilial = idFilial;
	}





	public static class Sincroniza{
		private Sincroniza() {}
		public static final String TABELA = "SINCRONIZA";
		public static final String NOME_TABELA = "TABELA";
		public static final String DTHRSINC = "DTHRSINC";
		public static final String IDFILIAL= "IDFILIAL";
		public static final String[] COLUNAS = new String[]{NOME_TABELA, DTHRSINC, IDFILIAL};
	}
}
