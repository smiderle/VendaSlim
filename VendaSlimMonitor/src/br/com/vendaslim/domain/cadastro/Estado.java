package br.com.vendaslim.domain.cadastro;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import br.com.vendaslim.domain.Domain;

@Entity
public class Estado implements Domain{
	@Id
	private Integer idEstado;
	@Column
	private String nome;
	@Column
	private String uf;
	
	@OneToMany(mappedBy="estado",fetch=FetchType.LAZY)
	public List<Cidade> cidades;
	
	
	public Integer getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public List<Cidade> getCidades() {
		return cidades;
	}
	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}
	
	
}
