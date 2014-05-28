package br.com.vendaslim.web;

import javax.faces.bean.ManagedBean;

import br.com.vendaslim.domain.representante.Representante;

@ManagedBean(name="cadastroGrupoRepBean")
public class CadastroGrupoRepresentanteBean {
	private Representante representante = new Representante();

	
	
	
	
	
	public Representante getRepresentante() {
		return representante;
	}

	public void setRepresentante(Representante representante) {
		this.representante = representante;
	}
	
	
	
}
