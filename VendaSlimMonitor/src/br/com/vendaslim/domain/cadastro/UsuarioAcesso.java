package br.com.vendaslim.domain.cadastro;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.vendaslim.domain.Domain;

@Table
@Entity
public class UsuarioAcesso implements Domain{
	
	@ManyToOne
	@JoinColumn(name="idUsuario")
	@Id	
	private Usuario usuario;
	
	@Id
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dtHrAcesso;

	public Calendar getDtHrAcesso() {
		return dtHrAcesso;
	}

	public void setDtHrAcesso(Calendar dtHrAcesso) {
		this.dtHrAcesso = dtHrAcesso;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}	
}
