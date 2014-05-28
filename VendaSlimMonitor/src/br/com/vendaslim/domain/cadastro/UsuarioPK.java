package br.com.vendaslim.domain.cadastro;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
@Embeddable
public class UsuarioPK implements Serializable{
	
	@Column(name="IDEMPRESA")
	private Integer idEmpresa;
	
	@Column(name="IDFILIAL")
	private Integer idFilial;
	
	@Column(name="IDUSUARIO")
	private Integer idUsuario;
	
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
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	
}
