package br.com.vendaslim.ws.domain;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="SINCRONIZACAO")
public class Sincronizacao implements Domain{
	
	@Transient
	public static final String TABELA_CLIENTE = "CLIENTE";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idSincronizacao;
	
	@Column
	private Integer idEmpresa;
	
	@Column
	private Integer idFilial;
	
	@Column
	private Integer idRepresentante;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dtHrSincronizacao;

	@Column(name="SINCCOMPLETA")
	private boolean sincCompleta;
	
	@Column
	private String tabela;
	
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

	public Calendar getDtHrSincronizacao() {
		return dtHrSincronizacao;
	}

	public void setDtHrSincronizacao(Calendar dtHrSincronizacao) {
		this.dtHrSincronizacao = dtHrSincronizacao;
	}



	public boolean isSincCompleta() {
		return sincCompleta;
	}

	public void setSincCompleta(boolean sincCompleta) {
		this.sincCompleta = sincCompleta;
	}

	public Integer getIdSincronizacao() {
		return idSincronizacao;
	}


	public String getTabela() {
		return tabela;
	}

	public void setTabela(String tabela) {
		this.tabela = tabela;
	}
	
}
