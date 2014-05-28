package br.com.slim.venda.websinc;

public class Websinc {
	
	private Integer sequencia;
	private Integer idEmpresa;	
	private Integer idFilial;	
	private Integer idRepresentante;	
	private String comando;
	public Integer getSequencia() {
		return sequencia;
	}
	public void setSequencia(Integer sequencia) {
		this.sequencia = sequencia;
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
	public String getComando() {
		return comando;
	}
	public void setComando(String comando) {
		this.comando = comando;
	}
	public Integer getIdRepresentante() {
		return idRepresentante;
	}
	public void setIdRepresentante(Integer idRepresentante) {
		this.idRepresentante = idRepresentante;
	}	
}
