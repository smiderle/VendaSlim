package br.com.vendaslim.domain.representante;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.vendaslim.domain.Domain;
import br.com.vendaslim.domain.cadastro.Empresa;

@Entity
@Table
@IdClass(RepresentanteRotaPK.class)
public class RepresentanteRota implements Domain{
	
	@Id
	private Empresa empresa;
	
	@Id
	private Integer idRepresentante;
	
	@Id
	private Calendar dtHrRota;
	@Column
	private Double latitude;
	@Column
	private Double longitude;
	@Transient
	private String horaFormatada;
	
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
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
	public String getHoraFormatada() {
		if(this.dtHrRota != null){
			SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
			return format.format(dtHrRota.getTime());
		}
		return horaFormatada;
	}
	public void setHoraFormatada(String horaFormatada) {
		this.horaFormatada = horaFormatada;
	}
	
	
}
