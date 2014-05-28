package br.com.vendaslim.domain.representante;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.vendaslim.domain.Domain;
import br.com.vendaslim.domain.cadastro.Empresa;
import br.com.vendaslim.domain.cadastro.Pessoa;

@Entity
@Table(name="REPRESENTANTE")
@IdClass(RepresentantePK.class)
public class Representante extends Pessoa implements Domain{
	
	public Representante() {
		this.representanteFilial = new ArrayList<RepresentanteFilial>();	
	}
	
	@Id
	private Empresa empresa;
	
	@Id
	private Integer idRepresentante;
				
	@OneToMany(mappedBy="representante", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<RepresentanteFilial> representanteFilial;
	
	@Column
	private String placa;
	@Column
	private String veiculo;	
	@Column
	private String login;
	@Column
	private String senha;
	
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
	public List<RepresentanteFilial> getRepresentanteFilial() {
		return representanteFilial;
	}
	public void setRepresentanteFilial(List<RepresentanteFilial> representanteFilial) {
		this.representanteFilial = representanteFilial;
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
}
