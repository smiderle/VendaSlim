package br.com.vendaslim.ws.domain;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

@Entity
@Table(name="MENSAGEM")
@IdClass(MensagemPK.class)
public class MensagemIntegration implements Domain{

	@Id
	private Integer idEmpresa;
	
	@Id
	private Integer idMensagem;
	
	@Column(name="REPRESENTANTE_ORIGEM")
	private Integer IdRepresentanetOrigem;
	
	@Column(name="REPRESENTANTE_DESTINO")
	private Integer IdRepresentanetDestino;
		
	@Column(name="USUARIO_ORIGEM")
	private Integer usuarioOrigem;
	
	@Column(name="USUARIO_DESTINO")
	private Integer usuarioDestino;
	
	@Column
	private String mensagem;
	
	@Column
	private String titulo;
	
	@Type(type="br.com.vendaslim.ws.hiberante.type.DateLongType")
	@Column(name="DTHRCADASTRO")
	private Long dtHrCadastroLong;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DTHRCADASTRO", insertable=false, updatable=false)
	private Calendar dtHrCadastro;


	public Integer getIdEmpresa() {
		return idEmpresa;
	}


	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}


	public Integer getIdMensagem() {
		return idMensagem;
	}


	public void setIdMensagem(Integer idMensagem) {
		this.idMensagem = idMensagem;
	}


	public Integer getIdRepresentanetOrigem() {
		return IdRepresentanetOrigem;
	}


	public void setIdRepresentanetOrigem(Integer idRepresentanetOrigem) {
		IdRepresentanetOrigem = idRepresentanetOrigem;
	}


	public Integer getIdRepresentanetDestino() {
		return IdRepresentanetDestino;
	}


	public void setIdRepresentanetDestino(Integer idRepresentanetDestino) {
		IdRepresentanetDestino = idRepresentanetDestino;
	}


	public Integer getUsuarioOrigem() {
		return usuarioOrigem;
	}


	public void setUsuarioOrigem(Integer usuarioOrigem) {
		this.usuarioOrigem = usuarioOrigem;
	}


	public Integer getUsuarioDestino() {
		return usuarioDestino;
	}


	public void setUsuarioDestino(Integer usuarioDestino) {
		this.usuarioDestino = usuarioDestino;
	}


	public String getMensagem() {
		return mensagem;
	}


	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public Long getDtHrCadastroLong() {
		return dtHrCadastroLong;
	}


	public void setDtHrCadastroLong(Long dtHrCadastroLong) {
		this.dtHrCadastroLong = dtHrCadastroLong;
	}


	public Calendar getDtHrCadastro() {
		return dtHrCadastro;
	}


	public void setDtHrCadastro(Calendar dtHrCadastro) {
		this.dtHrCadastro = dtHrCadastro;
	}
	
	

}