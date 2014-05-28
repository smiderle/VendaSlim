package br.com.slim.venda.versao;

import java.util.Date;

public class VersaoPdaVO {
	
	private String serial;	
	private String versao;	
	private Integer licenca;	
	private String build;
	private Date dataExpiracao;
	private boolean versaoExpirada;
	
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	public String getVersao() {
		return versao;
	}
	public void setVersao(String versao) {
		this.versao = versao;
	}
	public Integer getLicenca() {
		return licenca;
	}
	public void setLicenca(Integer licenca) {
		this.licenca = licenca;
	}
	public String getBuild() {
		return build;
	}
	public void setBuild(String build) {
		this.build = build;
	}
	
	public Date getDataExpiracao() {
		return dataExpiracao;
	}
	public void setDataExpiracao(Date dataExpiracao) {
		this.dataExpiracao = dataExpiracao;
	}
	public boolean isVersaoExpirada() {
		return versaoExpirada;
	}
	public void setVersaoExpirada(boolean versaoExpirada) {
		this.versaoExpirada = versaoExpirada;
	}






	public static final class Versao{
		private Versao() {}
		
		public static final String TABELA = "VERSAOPDA";
		public static final String SERIAL = "SERIAL";
		public static final String VERSAO = "VERSAO";
		public static final String LICENCA = "LICENCA";
		public static final String BUILD = "BUILD";
		public static final String DATAEXPIRACAO = "DATAEXPIRACAO";
		public static final String VERSAOEXPIRADA = "VERSAOEXPIRADA";
				
		public static final String[] COLUNAS = new String[]{SERIAL,VERSAO,LICENCA,BUILD,DATAEXPIRACAO,VERSAOEXPIRADA};
	}

	
	

}
