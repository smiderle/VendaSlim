package br.com.vendaslim.domain.cadastro;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.vendaslim.domain.Domain;
import br.com.vendaslim.domain.representante.Representante;

@Entity
@IdClass(MensagemPK.class)
public class Mensagem implements Domain{

	@Id
	private Empresa empresa;
	
	@Id
	private Integer idMensagem;

	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="IDEMPRESA",referencedColumnName="IDEMPRESA", nullable=false, insertable=false, updatable=false),		
		@JoinColumn(name="REPRESENTANTE_ORIGEM", referencedColumnName="IDREPRESENTANTE", nullable=false, insertable=false, updatable=false)
	})
	private Representante representantenOrigem;
	
	@Column(name="REPRESENTANTE_ORIGEM")
	private Integer IdRepresentanetOrigem;
	
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="IDEMPRESA",referencedColumnName="IDEMPRESA", nullable=false, insertable=false, updatable=false),		
		@JoinColumn(name="REPRESENTANTE_DESTINO", referencedColumnName="IDREPRESENTANTE", nullable=false, insertable=false, updatable=false)
	})
	private Representante representantenDestino;
	
	@Column(name="REPRESENTANTE_DESTINO")
	private Integer IdRepresentanetDestino;
	
	@ManyToOne
	@JoinColumn(name="USUARIO_ORIGEM")
	private Usuario usuarioOrigem;
	
	@ManyToOne
	@JoinColumn(name="USUARIO_DESTINO")
	private Usuario usuarioDestino;
	
	@Column
	private String mensagem;
	
	@Column
	private String titulo;
	
	@Column(insertable=false, updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtHrCadastro;

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Integer getIdMensagem() {
		return idMensagem;
	}

	public void setIdMensagem(Integer idMensagem) {
		this.idMensagem = idMensagem;
	}

	public Representante getRepresentantenOrigem() {
		return representantenOrigem;
	}

	public void setRepresentantenOrigem(Representante representantenOrigem) {
		if(representantenOrigem != null)
			setIdRepresentanetOrigem(representantenOrigem.getIdRepresentante());
		this.representantenOrigem = representantenOrigem;
	}

	public Representante getRepresentantenDestino() {
		return representantenDestino;
	}

	public void setRepresentantenDestino(Representante representantenDestino) {
		if(representantenDestino != null)
			setIdRepresentanetDestino(representantenDestino.getIdRepresentante());
		this.representantenDestino = representantenDestino;
	}

	public Usuario getUsuarioOrigem() {
		return usuarioOrigem;
	}

	public void setUsuarioOrigem(Usuario usuarioOrigem) {
		this.usuarioOrigem = usuarioOrigem;
	}

	public Usuario getUsuarioDestino() {
		return usuarioDestino;
	}

	public void setUsuarioDestino(Usuario usuarioDestino) {
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

	public Date getDtHrCadastro() {
		return dtHrCadastro;
	}

	public void setDtHrCadastro(Date dtHrCadastro) {
		this.dtHrCadastro = dtHrCadastro;
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
	
	
	
	
	
	
}
