package br.com.slim.venda.filial;

public class FilialVO {
	
	private Integer idEmpresa;
	private Integer idFilial;
	private String nomeFantasia;
	private String razaoSocial;
	private String fone;
	private String rua;
	private String bairro;
	private String numero;
	private String cep;
	private String fax;
	private String website;
		
	
	public static final class Filial{
		public static final String TABELA = "FILIAL";
		public static final String IDFILIAL = "IDFILIAL";
		public static final String IDEMPRESA = "IDEMPRESA";
		public static final String NOMEFANTASIA = "NOMEFANTASIA";
		public static final String RAZAOSOCIAL = "RAZAOSICIAL";
		public static final String FONE = "FONE";
		public static final String RUA = "RUA";
		public static final String BAIRRO = "BAIRRO";
		public static final String NUMERO = "NUMERO";
		public static final String CEP = "CEP";
		public static final String FAX = "FAX";
		public static final String WEBSITE = "WEBSITE";
		
		public static final String[] COLUNAS = new String[]{IDFILIAL, IDEMPRESA,NOMEFANTASIA, RAZAOSOCIAL, FONE, RUA, BAIRRO, NUMERO, CEP, FAX,WEBSITE};
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



	public String getNomeFantasia() {
		return nomeFantasia;
	}



	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}



	public String getRazaoSocial() {
		return razaoSocial;
	}



	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}



	public String getFone() {
		return fone;
	}



	public void setFone(String fone) {
		this.fone = fone;
	}



	public String getRua() {
		return rua;
	}



	public void setRua(String rua) {
		this.rua = rua;
	}



	public String getBairro() {
		return bairro;
	}



	public void setBairro(String bairro) {
		this.bairro = bairro;
	}



	public String getNumero() {
		return numero;
	}



	public void setNumero(String numero) {
		this.numero = numero;
	}



	public String getCep() {
		return cep;
	}



	public void setCep(String cep) {
		this.cep = cep;
	}



	public String getFax() {
		return fax;
	}



	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getWebsite() {
		return website;
	}



	public void setWebsite(String website) {
		this.website = website;
	}
	
	@Override
	public String toString() {	
		return getIdFilial() + " - "+ getNomeFantasia();
	}
}
