package br.com.vendaslim.ws.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="WEBSINC")
public class Websinc implements Domain{
	
	@Id
	@SequenceGenerator(name="sequence", sequenceName="websinc_sequencia_seq")
	@GeneratedValue(strategy=GenerationType.AUTO, generator="sequence")
	private Integer sequencia;

	@Column
	private Integer idEmpresa;
	@Column
	private Integer idFilial;
	@Column
	private Integer idRepresentante;
	@Column
	private String comando;
	/*@Column
	private Integer grupo;
	@Column
	private Integer tipo;*/
	
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
	public Integer getIdRepresentante() {
		return idRepresentante;
	}
	public void setIdRepresentante(Integer idRepresentante) {
		this.idRepresentante = idRepresentante;
	}
	public String getComando() {
		return comando;
	}
	public void setComando(String comando) {
		this.comando = comando;
	}	
}
