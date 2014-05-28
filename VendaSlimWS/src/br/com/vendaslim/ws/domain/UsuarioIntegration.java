package br.com.vendaslim.ws.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name="USUARIO", uniqueConstraints={		
		@UniqueConstraint(columnNames={"LOGIN"}, name="UK_USUARIO_LOGIN")})
public class UsuarioIntegration implements Domain{
	
	
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
	private boolean ativo;
			
	@Column
	public Integer idFilial;
	
	@Column
	public Integer idEmpresa;

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

	public Integer getIdRepesententa() {
		return idRepesententa;
	}

	public void setIdRepesententa(Integer idRepesententa) {
		this.idRepesententa = idRepesententa;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Integer getIdFilial() {
		return idFilial;
	}

	public void setIdFilial(Integer idFilial) {
		this.idFilial = idFilial;
	}

	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}			
}
