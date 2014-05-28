package br.com.vendaslim.ws.domain;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="versao_pda")
public class VersaoPda implements Domain{
	
	
	@Id
	@SequenceGenerator(name="sequence", sequenceName="versao_pda_id_seq")
	@GeneratedValue(strategy=GenerationType.AUTO, generator="sequence")
	private Integer id;
	@Column
	private String serial;
	@Column
	private String versao;
	@Column
	private Integer licenca;
	@Column
	private String build;
	
	@Column(name="data_expiracao") 
	private Date dataExpiracao;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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

	
}
