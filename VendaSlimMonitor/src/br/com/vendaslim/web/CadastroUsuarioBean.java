package br.com.vendaslim.web;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.vendaslim.controler.FilialControler;
import br.com.vendaslim.controler.RepresentanteControler;
import br.com.vendaslim.controler.UsuarioControler;
import br.com.vendaslim.domain.cadastro.Empresa;
import br.com.vendaslim.domain.cadastro.Filial;
import br.com.vendaslim.domain.cadastro.Usuario;
import br.com.vendaslim.domain.representante.Representante;
import br.com.vendaslim.web.util.ContextoUtil;

@ManagedBean(name="cadastroUsuarioBean")
public class CadastroUsuarioBean {

	Usuario usuario = new Usuario();
	private List<Usuario> lsUsuario = null;
	private List<Representante> lsRepresentantes = null;
	private List<Filial> lsFilial = null;
	public String confirmaSenha;
	private String msgLogin = "";

	public Usuario getUsuario() {
		return usuario;
	}

	public String getConfirmaSenha() {
		return confirmaSenha;
	}


	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}

	public String save(){
		FacesContext context = FacesContext.getCurrentInstance();
		if(usuario.getLogin() != null && usuario.getLogin().trim().length() > 0)
			if(loginDisponivel()){			
				if(!usuario.getSenha().equals(confirmaSenha)){
					FacesMessage facesMessage = new FacesMessage("As senhas estão diferentes.");
					context.addMessage(null, facesMessage);
					return null;
				} else {
					UsuarioControler usuarioControler = new UsuarioControler();
					usuarioControler.save(usuario);
					return "/restrito/lista/lista_usuarios?faces-redirect=true";
				}		
			} else {
				FacesMessage facesMessage = new FacesMessage( FacesMessage.SEVERITY_WARN,"Login já cadastrado.", "Por favor, escolha outro login para continuar.");
				context.addMessage(null, facesMessage);
				return null;
			}		
		else {
			FacesMessage facesMessage = new FacesMessage( FacesMessage.SEVERITY_ERROR,"Informe um login.", "Por favor, informe um login para continuar.");
			context.addMessage(null, facesMessage);
			return null;
		}
	}

	public String novo(){
		return "/restrito/cadastro/cadastro_usuario?faces-redirect=true";
	}
	
	public String editar(){
		return "/restrito/cadastro/cadastro_usuario";
	}
	
	public String voltar(){
		return "/restrito/lista/lista_usuarios?faces-redirect=true";
	}

	public List<Usuario> listarUsuario(){
		if(lsUsuario == null){			
			Empresa empresa = ContextoUtil.getContextBean().getUsuarioLogado().getFilial().getEmpresa();
			UsuarioControler usuarioControler = new UsuarioControler();
			this.lsUsuario = usuarioControler.list(empresa);
		}
		return this.lsUsuario;

	}

	/*public List<Representante> listaRepresentante(Filial filial){
		RepresentanteControler controler = new RepresentanteControler();
		this.lsRepresentantes = controler.list(filial);
		return lsRepresentantes;		
	}*/

	public List<Filial> listaFilial(){
		if(lsFilial == null){
			Filial filialLogado = ContextoUtil.getContextBean().getFilialLogado();
			FilialControler filialControler = new FilialControler();		
			this.lsFilial = filialControler.list(filialLogado.getEmpresa());
		}
		return lsFilial;
	}
	
	
	public boolean loginDisponivel(){
		if(usuario.getLogin() == null || usuario.getLogin().trim().length() == 0){
			return false;
		}
		UsuarioControler usuarioControler = new UsuarioControler();
		boolean loginDisponivel = usuarioControler.findByLogin(usuario.getLogin()) == null;
		if(loginDisponivel){
			msgLogin = "Este login esta disponivel";
		} else {
			msgLogin = "Este login já esta em uso";
		}
		return loginDisponivel;
	}
	

	public void handleRepresentChange(Filial filial){
		//listaRepresentante(filial);
	}



	public List<Representante> getLsRepresentantes() {
		return lsRepresentantes;
	}



	public void setLsRepresentantes(List<Representante> lsRepresentantes) {
		this.lsRepresentantes = lsRepresentantes;
	}

	public List<Usuario> getLsUsuario() {
		return lsUsuario;
	}

	public void setLsUsuario(List<Usuario> lsUsuario) {
		this.lsUsuario = lsUsuario;
	}

	public List<Filial> getLsFilial() {
		return lsFilial;
	}

	public void setLsFilial(List<Filial> lsFilial) {
		this.lsFilial = lsFilial;
	}

	public String getMsgLogin() {
		return msgLogin;
	}

	public void setMsgLogin(String msgLogin) {
		this.msgLogin = msgLogin;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	



}
