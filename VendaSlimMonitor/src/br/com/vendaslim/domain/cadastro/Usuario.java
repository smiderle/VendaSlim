package br.com.vendaslim.domain.cadastro;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.NaturalId;

import br.com.vendaslim.domain.Domain;
import br.com.vendaslim.domain.representante.Representante;

@Entity
@Table(name="USUARIO", uniqueConstraints={		
		@UniqueConstraint(columnNames={"LOGIN"}, name="UK_USUARIO_LOGIN")})
public class Usuario implements Domain{
	
	public Usuario() {
		setAtivo(true);
	}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idUsuario;
	
	
	@NaturalId
	@Column(name="LOGIN")
	private String login;
	
	@Column(name="SENHA")
	private String senha;
	@Column(name="NOME", length=50)
	private String nome;
	@Column(name="EMAIL")
	private String email;
	@Column(name="FONE")
	private String fone;
	
	@Column(name="IDREPRESENTANTE")
	private Integer idRepesententa;
	
	@Column	
	private Boolean ativo;
			
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns({@JoinColumn(name="IDEMPRESA",nullable=false), @JoinColumn(name="IDFILIAL",nullable=false)})
	//@JoinColumns({@JoinColumn(name="IDEMPRESA",insertable=false,updatable=false, nullable=false), @JoinColumn(name="IDFILIAL", insertable=false,updatable=false, nullable=false)})
	public Filial filial;
	
	@OneToOne(optional= true, fetch= FetchType.EAGER)
	@JoinColumns(
			{
				@JoinColumn(name="IDEMPRESA", insertable=false, updatable=false),
				@JoinColumn(name="IDREPRESENTANTE", insertable=false, updatable=false),
			}
			)	
	public Representante representante;
	
	@OneToMany(mappedBy="usuario", fetch=FetchType.LAZY)	
	private List<UsuarioAcesso> usuariosAcessos;
	
	
	public Filial getFilial() {
		return filial;
	}

	public void setFilial(Filial filial) {
		this.filial = filial;
	}
	
	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public Representante getRepresentante() {
		return representante;
	}

	public void setRepresentante(Representante representante) {
		if(representante != null)
			setIdRepesententa(representante.getIdRepresentante());
		this.representante = representante;
	}

	

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Integer getIdRepesententa() {		
		return idRepesententa;
	}

	public void setIdRepesententa(Integer idRepesententa) {
		this.idRepesententa = idRepesententa;
	}

	public List<UsuarioAcesso> getUsuariosAcessos() {
		return usuariosAcessos;
	}

	public void setUsuariosAcessos(List<UsuarioAcesso> usuariosAcessos) {
		this.usuariosAcessos = usuariosAcessos;
	}

	
}
