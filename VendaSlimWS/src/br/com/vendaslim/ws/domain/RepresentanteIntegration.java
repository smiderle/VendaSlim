package br.com.vendaslim.ws.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="REPRESENTANTE")
@IdClass(RepresentantePK.class)
public class RepresentanteIntegration extends Pessoa implements Domain{
	
	
	@Id
	private Integer idEmpresa;
	
	@Id
	private Integer idRepresentante;
	@Column
	private String placa;
	@Column
	private String veiculo;	
	@Column
	private String login;
	@Column
	private String senha;
	@OneToOne (cascade=CascadeType.PERSIST) 
    @JoinColumn(name="id_device")
	private DeviceIntegration deviceIntegration;
	
	
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
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getVeiculo() {
		return veiculo;
	}
	public void setVeiculo(String veiculo) {
		this.veiculo = veiculo;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public DeviceIntegration getDeviceIntegration() {
		return deviceIntegration;
	}
	public void setDeviceIntegration(DeviceIntegration deviceIntegration) {
		this.deviceIntegration = deviceIntegration;
	}	
	
	
}
