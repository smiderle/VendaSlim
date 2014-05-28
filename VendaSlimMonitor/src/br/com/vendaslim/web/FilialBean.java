package br.com.vendaslim.web;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.vendaslim.controler.FilialControler;
import br.com.vendaslim.domain.cadastro.Empresa;
import br.com.vendaslim.domain.cadastro.Filial;
import br.com.vendaslim.web.util.ContextoUtil;

@ManagedBean
@RequestScoped
public class FilialBean {
	
	private Filial filial = new Filial();
	private List<Filial> filiais = null;

	public void save(){
		FilialControler filialControler = new FilialControler();
		filialControler.save(getFilial());
	}
	
	public List<Filial> list(){
		Filial filialLogado = ContextoUtil.getContextBean().getFilialLogado();
		FilialControler filialControler = new FilialControler();
		return filialControler.list(filialLogado.getEmpresa());
	}
	
	public List<Filial> buscaFilialPorEmpresaLogada(){
		if(this.filiais == null){
			Empresa empresa = ContextoUtil.getContextBean().getEmpresaLogado();
			FilialControler filialControler = new FilialControler();
			this.filiais = filialControler.list(empresa);
		}
		return this.filiais;
	}
	
	public Integer filiaisSize(){
		return buscaFilialPorEmpresaLogada().size();
	}
	
	public Filial getFilial() {
		return filial;
	}

	public void setFilial(Filial filial) {
		this.filial = filial;
	}
}
