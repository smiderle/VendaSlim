package br.com.vendaslim.web;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.vendaslim.domain.cadastro.Filial;
import br.com.vendaslim.web.util.ContextoUtil;

@ManagedBean
@ViewScoped
public class CadastroFilialBean {
	
	public CadastroFilialBean() {
		this.filial = ContextoUtil.getContextBean().getFilial();
	}
	
	private Filial filial;


	public Filial getFilial() {
		return filial;
	}


	public void setFilial(Filial filial) {
		this.filial = filial;
	}
}
