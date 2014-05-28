package br.com.vendaslim.web;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import br.com.vendaslim.controler.FilialControler;
import br.com.vendaslim.controler.UsuarioControler;
import br.com.vendaslim.domain.cadastro.Empresa;
import br.com.vendaslim.domain.cadastro.Filial;
import br.com.vendaslim.domain.cadastro.Usuario;
import br.com.vendaslim.web.util.ContextoUtil;
@ManagedBean(name="contextoBean")
@SessionScoped
public class ContextoBean {
	private Usuario usuarioLogado;	
	//Filial Logado
	private Filial filial;
	//Todas as Filiais da empresa
	private List<Filial> filiais;
	
	public Usuario getUsuarioLogado() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext exteranal = context.getExternalContext();
		String login = exteranal.getRemoteUser();

		if(usuarioLogado == null || !login.equals(this.usuarioLogado.getLogin())){
			if(login != null){
				UsuarioControler controler = new UsuarioControler();
			 	usuarioLogado = controler.findByLogin(login);
			 	controler.registraAcesso(usuarioLogado);
			}
		}
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	@Deprecated
	public Filial getFilialLogado(){
		if(usuarioLogado != null)
			return usuarioLogado.getFilial();
		return null;
	}
	
	public Empresa getEmpresaLogado(){
		if(usuarioLogado != null)
			return usuarioLogado.getFilial().getEmpresa();
		return null;
	}

	public Filial getFilial() {
		return filial;
	}

	public void setFilial(Filial filial) {
		this.filial = filial;
	}
	
	public List<Filial> getFiliais() {
		return filiais;
	}
	
	
	
	public void setFiliais(List<Filial> filiais) {
		this.filiais = filiais;
	}

	public boolean selecionarFiliais(){
		if(this.filiais == null){
			FilialControler filialControler = new FilialControler();
			this.filiais = filialControler.list(getEmpresaLogado());
		}
		
		if(filiais.size() == 1){
			setFilial(filiais.get(0));
			return false;
		} else {
			return true;
		}
	}
}
