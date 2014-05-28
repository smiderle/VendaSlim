package br.com.vendaslim.controler;

import java.util.GregorianCalendar;
import java.util.List;

import br.com.vendaslim.domain.cadastro.Empresa;
import br.com.vendaslim.domain.cadastro.Usuario;
import br.com.vendaslim.domain.cadastro.UsuarioAcesso;
import br.com.vendaslim.domain.repository.IUsuarioRepository;
import br.com.vendaslim.util.DAOFactory;

public class UsuarioControler {
	
	private IUsuarioRepository usuarioDAO;
	
	public UsuarioControler() {
		this.usuarioDAO = DAOFactory.criaUsuarioDAO();
	}
	
	public void save(Usuario usuario){
		usuarioDAO.save(usuario);
	}
	
	public Usuario findByLogin(String login){
		return this.usuarioDAO.findByLogin(login);
	}
	
	public List<Usuario> list(Empresa empresa){
		return this.usuarioDAO.list(empresa);
	}
	
	public void registraAcesso(Usuario usuario){
		UsuarioAcesso usuarioAcesso = new UsuarioAcesso();
		usuarioAcesso.setUsuario(usuario);
		usuarioAcesso.setDtHrAcesso(new GregorianCalendar());
		this.usuarioDAO.registraAcesso(usuarioAcesso);
	}
}
