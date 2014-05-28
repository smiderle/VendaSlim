package br.com.vendaslim.ws.domain;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="DEVICES")
public class DeviceIntegration implements Domain{
	
	@Id
	@SequenceGenerator(name="sequence", sequenceName="devices_id_seq")
	@GeneratedValue(strategy=GenerationType.AUTO, generator="sequence")
	private Integer id;
	@Column
	private String serial;
	@Column(name="versao_android")
	private String vesaoAndroid;
	@Column
	private Calendar dtHrAlteracao;
	@Column
	private String email;
	
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
	public String getVesaoAndroid() {
		return vesaoAndroid;
	}
	public void setVesaoAndroid(String vesaoAndroid) {
		this.vesaoAndroid = vesaoAndroid;
	}
	public Calendar getDtHrAlteracao() {
		return dtHrAlteracao;
	}
	public void setDtHrAlteracao(Calendar dtHrAlteracao) {
		this.dtHrAlteracao = dtHrAlteracao;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
	
	

}
