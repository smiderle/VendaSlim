package br.com.slim.venda.device;

import java.util.Calendar;

public class Device {
	
	private Integer id;
	private String serial;
	private String vesaoAndroid;
	private Calendar dtHrAlteracao;
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
