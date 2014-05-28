package br.com.vendaslim.ws.domain;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="REPRESENTANTEROTA")
@IdClass(RepresentanteRotaPK.class)
public class RepresentanteRotaIntegration implements Domain{
	@Id
	private Integer idEmpresa;
	
	@Id
	private Integer idRepresentante;
	
	@Id
	private Calendar dtHrRota;
	
	@Column
	private Double latitude;
	
	@Column
	private Double longitude;
	
	@Transient
	private Long dtHrRotaLong;

	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public Integer getIdRepresentante() {
		return idRepresentante;
	}

	public void setIdRepresentante(Integer idRepresentante) {
		this.idRepresentante = idRepresentante;
	}	
	
	public Calendar getDtHrRota() {
		return dtHrRota;
	}

	public void setDtHrRota(Calendar dtHrRota) {
		this.dtHrRota = dtHrRota;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Long getDtHrRotaLong() {
		return dtHrRotaLong;
	}

	public void setDtHrRotaLong(Long dtHrRotaLong) {
		this.dtHrRotaLong = dtHrRotaLong;
	}
	
	

	
}
